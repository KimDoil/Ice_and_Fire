package com.github.alexthe666.iceandfire.entity;

import com.github.alexthe666.citadel.animation.Animation;
import com.github.alexthe666.citadel.animation.AnimationHandler;
import com.github.alexthe666.citadel.animation.IAnimatedEntity;
import com.github.alexthe666.iceandfire.IafConfig;
import com.github.alexthe666.iceandfire.api.FoodUtils;
import com.github.alexthe666.iceandfire.entity.ai.*;
import com.github.alexthe666.iceandfire.entity.util.*;
import com.github.alexthe666.iceandfire.event.ServerEvents;
import com.github.alexthe666.iceandfire.misc.IafSoundRegistry;
import com.github.alexthe666.iceandfire.world.IafWorldRegistry;
import com.google.common.base.Predicate;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.CreeperEntity;
import net.minecraft.entity.monster.EndermanEntity;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.monster.ZombifiedPiglinEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.*;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.List;

public class EntityCockatrice extends TameableEntity implements IAnimatedEntity, IBlacklistedFromStatues, IVillagerFear, IHasCustomizableAttributes {

    public static final Animation ANIMATION_JUMPAT = Animation.create(30);
    public static final Animation ANIMATION_WATTLESHAKE = Animation.create(20);
    public static final Animation ANIMATION_BITE = Animation.create(15);
    public static final Animation ANIMATION_SPEAK = Animation.create(10);
    public static final Animation ANIMATION_EAT = Animation.create(20);
    public static final float VIEW_RADIUS = 0.6F;
    private static final DataParameter<Boolean> HEN = EntityDataManager.defineId(EntityCockatrice.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Boolean> STARING = EntityDataManager.defineId(EntityCockatrice.class, DataSerializers.BOOLEAN);
    private static final DataParameter<Integer> TARGET_ENTITY = EntityDataManager.defineId(EntityCockatrice.class, DataSerializers.INT);
    private static final DataParameter<Integer> TAMING_PLAYER = EntityDataManager.defineId(EntityCockatrice.class, DataSerializers.INT);
    private static final DataParameter<Integer> TAMING_LEVEL = EntityDataManager.defineId(EntityCockatrice.class, DataSerializers.INT);
    private static final DataParameter<Integer> COMMAND = EntityDataManager.defineId(EntityCockatrice.class, DataSerializers.INT);
    private final CockatriceAIStareAttack aiStare;
    private final MeleeAttackGoal aiMelee;
    public float sitProgress;
    public float stareProgress;
    public int ticksStaring = 0;
    public HomePosition homePos;
    public boolean hasHomePosition = false;
    private int animationTick;
    private Animation currentAnimation;
    private boolean isSitting;
    private boolean isStaring;
    private boolean isMeleeMode = false;
    private LivingEntity targetedEntity;
    private int clientSideAttackTime;

    public EntityCockatrice(EntityType<EntityCockatrice> type, World worldIn) {
        super(type, worldIn);
        aiStare = new CockatriceAIStareAttack(this, 1.0D, 0, 15.0F);
        aiMelee = new EntityAIAttackMeleeNoCooldown(this, 1.5D, false);
        IHasCustomizableAttributes.applyAttributesForEntity(type, this);
    }

    public static AttributeModifierMap.MutableAttribute bakeAttributes() {
        return MobEntity.createMobAttributes()
            //HEALTH
            .add(Attributes.MAX_HEALTH, IafConfig.cockatriceMaxHealth)
            //SPEED
            .add(Attributes.MOVEMENT_SPEED, 0.4D)
            //ATTACK
            .add(Attributes.ATTACK_DAMAGE, 5.0D)
            //FOLLOW RANGE
            .add(Attributes.FOLLOW_RANGE, 64.0D)
            //ARMOR
            .add(Attributes.ARMOR, 2.0D);
    }

    protected int getExperienceReward(PlayerEntity player) {
        return 10;
    }

    public boolean getCanSpawnHere() {
        return this.getRandom().nextInt(IafConfig.cockatriceSpawnCheckChance + 1) == 0;
    }

    protected void registerGoals() {
        this.goalSelector.addGoal(1, new SwimGoal(this));
        this.goalSelector.addGoal(3, new CockatriceAIFollowOwner(this, 1.0D, 7.0F, 2.0F));
        this.goalSelector.addGoal(3, new SitGoal(this));
        this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, LivingEntity.class, 14.0F, 1.0D, 1.0D, new Predicate<LivingEntity>() {
            @Override
            public boolean apply(@Nullable LivingEntity entity) {
                if (entity instanceof PlayerEntity) {
                    return !((PlayerEntity) entity).isCreative() && !entity.isSpectator();
                } else {
                    return ServerEvents.doesScareCockatrice(entity) && !ServerEvents.isChicken(entity);
                }
            }
        }));
        this.goalSelector.addGoal(4, new CockatriceAIWander(this, 1.0D));
        this.goalSelector.addGoal(5, new CockatriceAIAggroLook(this));
        this.goalSelector.addGoal(6, new LookAtGoal(this, LivingEntity.class, 6.0F));
        this.goalSelector.addGoal(7, new LookRandomlyGoal(this));
        this.targetSelector.addGoal(1, new CockatriceAITargetItems(this, false));
        this.targetSelector.addGoal(2, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(3, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(4, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(5, new CockatriceAITarget(this, LivingEntity.class, true, new Predicate<Entity>() {
            @Override
            public boolean apply(@Nullable Entity entity) {
                if (entity instanceof PlayerEntity) {
                    return !((PlayerEntity) entity).isCreative() && !entity.isSpectator();
                } else {
                    return ((entity instanceof IMob) && EntityCockatrice.this.isTame() && !(entity instanceof CreeperEntity) && !(entity instanceof ZombifiedPiglinEntity) && !(entity instanceof EndermanEntity) ||
                        ServerEvents.isCockatriceTarget(entity) && !ServerEvents.isChicken(entity));
                }
            }
        }));
    }

    public boolean hasRestriction() {
        return this.hasHomePosition &&
            this.getCommand() == 3 &&
            getHomeDimensionName().equals(DragonUtils.getDimensionName(this.level))
            || super.hasRestriction();
    }

    public SoundCategory getSoundSource() {
        return SoundCategory.HOSTILE;
    }

    public boolean checkSpawnRules(IWorld worldIn, SpawnReason spawnReasonIn) {
        if (worldIn instanceof IServerWorld && !IafWorldRegistry.isDimensionListedForMobs((IServerWorld) level)) {
            return false;
        }
        return super.checkSpawnRules(worldIn, spawnReasonIn);
    }

    @Override
    public BlockPos getRestrictCenter() {
        return this.hasHomePosition && this.getCommand() == 3 && homePos != null ? homePos.getPosition() : super.getRestrictCenter();
    }

    @Override
    public float getRestrictRadius() {
        return 30.0F;
    }

    public String getHomeDimensionName() {
        return this.homePos == null ? "" : homePos.getDimension();
    }

    @Override
    public boolean isAlliedTo(Entity entityIn) {
        if (ServerEvents.isChicken(entityIn)) {
            return true;
        }
        if (this.isTame()) {
            LivingEntity livingentity = this.getOwner();
            if (entityIn == livingentity) {
                return true;
            }
            if (entityIn instanceof TameableEntity) {
                return ((TameableEntity) entityIn).isOwnedBy(livingentity);
            }
            if (livingentity != null) {
                return livingentity.isAlliedTo(entityIn);
            }
        }

        return super.isAlliedTo(entityIn);
    }

    @Override
    public boolean hurt(DamageSource source, float damage) {
        if (source.getEntity() != null && ServerEvents.doesScareCockatrice(source.getEntity())) {
            damage *= 5;
        }
        if (source == DamageSource.IN_WALL) {
            return false;
        }
        return super.hurt(source, damage);
    }

    private boolean canUseStareOn(Entity entity) {
        return (!(entity instanceof IBlacklistedFromStatues) || ((IBlacklistedFromStatues) entity).canBeTurnedToStone()) && !ServerEvents.isCockatriceTarget(entity);
    }

    private void switchAI(boolean melee) {
        if (melee) {
            this.goalSelector.removeGoal(aiStare);
            if (aiMelee != null) {
                this.goalSelector.addGoal(2, aiMelee);
            }
            this.isMeleeMode = true;
        } else {
            this.goalSelector.removeGoal(aiMelee);
            if (aiStare != null) {
                this.goalSelector.addGoal(2, aiStare);
            }
            this.isMeleeMode = false;
        }
    }

    @Override
    public boolean doHurtTarget(Entity entityIn) {
        if (this.isStaring()) {
            return false;
        }
        if (this.getRandom().nextBoolean()) {
            if (this.getAnimation() != ANIMATION_JUMPAT && this.getAnimation() != ANIMATION_BITE) {
                this.setAnimation(ANIMATION_JUMPAT);
            }
            return false;
        } else {
            if (this.getAnimation() != ANIMATION_BITE && this.getAnimation() != ANIMATION_JUMPAT) {
                this.setAnimation(ANIMATION_BITE);
            }
            return false;
        }

    }

    @Override
    public AttributeModifierMap.MutableAttribute getConfigurableAttributes() {
        return bakeAttributes();
    }

    public boolean canMove() {
        return !this.isOrderedToSit() && !(this.getAnimation() == ANIMATION_JUMPAT && this.getAnimationTick() < 7);
    }


    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(HEN, Boolean.valueOf(false));
        this.entityData.define(STARING, Boolean.valueOf(false));
        this.entityData.define(TARGET_ENTITY, Integer.valueOf(0));
        this.entityData.define(TAMING_PLAYER, Integer.valueOf(0));
        this.entityData.define(TAMING_LEVEL, Integer.valueOf(0));
        this.entityData.define(COMMAND, Integer.valueOf(0));
    }

    public boolean hasTargetedEntity() {
        return this.entityData.get(TARGET_ENTITY).intValue() != 0;
    }

    public boolean hasTamingPlayer() {
        return this.entityData.get(TAMING_PLAYER).intValue() != 0;
    }

    @Nullable
    public Entity getTamingPlayer() {
        if (!this.hasTamingPlayer()) {
            return null;
        } else if (this.level.isClientSide) {
            if (this.targetedEntity != null) {
                return this.targetedEntity;
            } else {
                Entity entity = this.level.getEntity(this.entityData.get(TAMING_PLAYER).intValue());
                if (entity instanceof LivingEntity) {
                    this.targetedEntity = (LivingEntity) entity;
                    return this.targetedEntity;
                } else {
                    return null;
                }
            }
        } else {
            return this.level.getEntity(this.entityData.get(TAMING_PLAYER).intValue());
        }
    }

    public void setTamingPlayer(int entityId) {
        this.entityData.set(TAMING_PLAYER, Integer.valueOf(entityId));
    }

    @Nullable
    public LivingEntity getTargetedEntity() {
        boolean blindness = this.hasEffect(Effects.BLINDNESS) || this.getTarget() != null && this.getTarget().hasEffect(Effects.BLINDNESS) || EntityGorgon.isBlindfolded(this.getTarget());
        if (blindness) {
            return null;
        }
        if (!this.hasTargetedEntity()) {
            return null;
        } else if (this.level.isClientSide) {
            if (this.targetedEntity != null) {
                return this.targetedEntity;
            } else {
                Entity entity = this.level.getEntity(this.entityData.get(TARGET_ENTITY).intValue());
                if (entity instanceof LivingEntity) {
                    this.targetedEntity = (LivingEntity) entity;
                    return this.targetedEntity;
                } else {
                    return null;
                }
            }
        } else {
            return this.getTarget();
        }
    }

    public void setTargetedEntity(int entityId) {
        this.entityData.set(TARGET_ENTITY, Integer.valueOf(entityId));
    }

    public void onSyncedDataUpdated(DataParameter<?> key) {
        super.onSyncedDataUpdated(key);
        if (TARGET_ENTITY.equals(key)) {
            this.clientSideAttackTime = 0;
            this.targetedEntity = null;
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundNBT tag) {
        super.addAdditionalSaveData(tag);
        tag.putBoolean("Hen", this.isHen());
        tag.putBoolean("Staring", this.isStaring());
        tag.putInt("TamingLevel", this.getTamingLevel());
        tag.putInt("TamingPlayer", this.entityData.get(TAMING_PLAYER).intValue());
        tag.putInt("Command", this.getCommand());
        tag.putBoolean("HasHomePosition", this.hasHomePosition);
        if (homePos != null && this.hasHomePosition) {
            homePos.write(tag);
        }
    }

    @Override
    public void readAdditionalSaveData(CompoundNBT tag) {
        super.readAdditionalSaveData(tag);
        this.setHen(tag.getBoolean("Hen"));
        this.setStaring(tag.getBoolean("Staring"));
        this.setTamingLevel(tag.getInt("TamingLevel"));
        this.setTamingPlayer(tag.getInt("TamingPlayer"));
        this.setCommand(tag.getInt("Command"));
        this.hasHomePosition = tag.getBoolean("HasHomePosition");
        if (hasHomePosition && tag.getInt("HomeAreaX") != 0 && tag.getInt("HomeAreaY") != 0 && tag.getInt("HomeAreaZ") != 0) {
            homePos = new HomePosition(tag, this.level);
        }
    }

    public boolean isOrderedToSit() {
        if (level.isClientSide) {
            boolean isSitting = (this.entityData.get(DATA_FLAGS_ID).byteValue() & 1) != 0;
            this.isSitting = isSitting;
            return isSitting;
        }
        return isSitting;
    }

    public void setOrderedToSit(boolean sitting) {
        super.setSwimming(sitting);
        if (!level.isClientSide) {
            this.isSitting = sitting;
        }
    }

    public void fall(float distance, float damageMultiplier) {
    }

    @Override
    @Nullable
    public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
        spawnDataIn = super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
        this.setHen(this.getRandom().nextBoolean());
        return spawnDataIn;
    }


    public boolean isHen() {
        return this.entityData.get(HEN).booleanValue();
    }

    public void setHen(boolean hen) {
        this.entityData.set(HEN, Boolean.valueOf(hen));
    }

    public int getTamingLevel() {
        return Integer.valueOf(this.entityData.get(TAMING_LEVEL).intValue());
    }

    public void setTamingLevel(int level) {
        this.entityData.set(TAMING_LEVEL, Integer.valueOf(level));
    }

    public int getCommand() {
        return Integer.valueOf(this.entityData.get(COMMAND).intValue());
    }

    public void setCommand(int command) {
        this.entityData.set(COMMAND, Integer.valueOf(command));
        this.setOrderedToSit(command == 1);
    }

    public boolean isStaring() {
        if (level.isClientSide) {
            return this.isStaring = Boolean.valueOf(this.entityData.get(STARING).booleanValue());
        }
        return isStaring;
    }

    public void setStaring(boolean staring) {
        this.entityData.set(STARING, Boolean.valueOf(staring));
        if (!level.isClientSide) {
            this.isStaring = staring;
        }
    }

    public void forcePreyToLook(MobEntity mob) {
        mob.getLookControl().setLookAt(this.getX(), this.getY() + (double) this.getEyeHeight(), this.getZ(), (float) mob.getMaxHeadYRot(), (float) mob.getMaxHeadXRot());
    }

    @Override
    public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
        boolean flag = player.getItemInHand(hand).getItem() == Items.NAME_TAG || player.getItemInHand(hand).getItem() == Items.LEAD;
        if (flag) {
            return super.mobInteract(player, hand);
        }
        if (player.getItemInHand(hand).getItem() == Items.POISONOUS_POTATO) {
            return super.mobInteract(player, hand);
        }
        if (this.isTame() && this.isOwnedBy(player)) {
            if (FoodUtils.isSeeds(player.getItemInHand(hand)) || player.getItemInHand(hand).getItem() == Items.ROTTEN_FLESH) {
                if (this.getHealth() < this.getMaxHealth()) {
                    this.heal(8);
                    this.playSound(SoundEvents.GENERIC_EAT, 1, 1);
                    player.getItemInHand(hand).shrink(1);
                }
                return ActionResultType.SUCCESS;
            } else if (player.getItemInHand(hand).isEmpty()) {
                if (player.isShiftKeyDown()) {
                    if (this.hasHomePosition) {
                        this.hasHomePosition = false;
                        player.displayClientMessage(new TranslationTextComponent("cockatrice.command.remove_home"), true);
                        return ActionResultType.SUCCESS;
                    } else {
                        BlockPos pos = this.blockPosition();
                        this.homePos = new HomePosition(pos, this.level);
                        this.hasHomePosition = true;
                        player.displayClientMessage(new TranslationTextComponent("cockatrice.command.new_home", pos.getX(), pos.getY(), pos.getZ(), homePos.getDimension()), true);
                        return ActionResultType.SUCCESS;
                    }
                } else {
                    this.setCommand(this.getCommand() + 1);
                    if (this.getCommand() > 3) {
                        this.setCommand(0);
                    }
                    player.displayClientMessage(new TranslationTextComponent("cockatrice.command." + this.getCommand()), true);
                    this.playSound(SoundEvents.ZOMBIE_INFECT, 1, 1);
                    return ActionResultType.SUCCESS;
                }
            }

        }
        return ActionResultType.FAIL;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        LivingEntity attackTarget = this.getTarget();
        if (this.level.getDifficulty() == Difficulty.PEACEFUL && attackTarget instanceof PlayerEntity) {
            this.setTarget(null);
        }
        if (this.isOrderedToSit() && this.getCommand() != 1) {
            this.setOrderedToSit(false);
        }
        if (this.isOrderedToSit() && attackTarget != null) {
            this.setTarget(null);
        }
        if (attackTarget != null && this.isAlliedTo(attackTarget)) {
            this.setTarget(null);
        }
        if (!level.isClientSide) {
            if (attackTarget == null || !attackTarget.isAlive()) {
                this.setTargetedEntity(0);
            } else if (this.isStaring() || this.shouldStareAttack(attackTarget)) {
                this.setTargetedEntity(attackTarget.getId());
            }
        }
        if (this.getAnimation() == ANIMATION_BITE && attackTarget != null && this.getAnimationTick() == 7) {
            double dist = this.distanceToSqr(attackTarget);
            if (dist < 8) {
                attackTarget.hurt(DamageSource.mobAttack(this), ((int) this.getAttribute(Attributes.ATTACK_DAMAGE).getValue()));
            }
        }
        if (this.getAnimation() == ANIMATION_JUMPAT && attackTarget != null) {
            double dist = this.distanceToSqr(attackTarget);
            double d0 = attackTarget.getX() - this.getX();
            double d1 = attackTarget.getZ() - this.getZ();
            float leap = MathHelper.sqrt(d0 * d0 + d1 * d1);
            if (dist <= 16.0D && this.isOnGround() && this.getAnimationTick() > 7 && this.getAnimationTick() < 12) {
                Vector3d Vector3d = this.getDeltaMovement();
                Vector3d Vector3d1 = new Vector3d(attackTarget.getX() - this.getX(), 0.0D, attackTarget.getZ() - this.getZ());
                if (Vector3d1.lengthSqr() > 1.0E-7D) {
                    Vector3d1 = Vector3d1.normalize().scale(0.4D).add(Vector3d.scale(0.2D));
                }
            }
            if (dist < 4 && this.getAnimationTick() > 10) {
                attackTarget.hurt(DamageSource.mobAttack(this), ((int) this.getAttribute(Attributes.ATTACK_DAMAGE).getValue()));
                if ((double) leap >= 1.0E-4D) {
                    attackTarget.setDeltaMovement(attackTarget.getDeltaMovement().add(d0 / (double) leap * 0.800000011920929D + this.getDeltaMovement().x * 0.20000000298023224D, 0, d1 / (double) leap * 0.800000011920929D + this.getDeltaMovement().z * 0.20000000298023224D));
                }
            }
        }
        boolean sitting = isOrderedToSit();
        if (sitting && sitProgress < 20.0F) {
            sitProgress += 0.5F;
        } else if (!sitting && sitProgress > 0.0F) {
            sitProgress -= 0.5F;
        }

        boolean staring = isStaring();
        if (staring && stareProgress < 20.0F) {
            stareProgress += 0.5F;
        } else if (!staring && stareProgress > 0.0F) {
            stareProgress -= 0.5F;
        }
        if (!level.isClientSide) {
            if (staring) {
                ticksStaring++;
            } else {
                ticksStaring = 0;
            }
        }
        if (!level.isClientSide && staring && (attackTarget == null || this.shouldMelee())) {
            this.setStaring(false);
        }
        if (attackTarget != null) {
            this.getLookControl().setLookAt(attackTarget.getX(), attackTarget.getY() + (double) attackTarget.getEyeHeight(), attackTarget.getZ(), (float) this.getMaxHeadYRot(), (float) this.getMaxHeadXRot());
            if (!shouldMelee() && attackTarget instanceof MobEntity) {
                forcePreyToLook((MobEntity) attackTarget);
            }
        }
        boolean blindness = this.hasEffect(Effects.BLINDNESS) || attackTarget != null && attackTarget.hasEffect(Effects.BLINDNESS);
        if (blindness) {
            this.setStaring(false);
        }
        if (!this.level.isClientSide && !blindness && attackTarget != null && EntityGorgon.isEntityLookingAt(this, attackTarget, VIEW_RADIUS) && EntityGorgon.isEntityLookingAt(attackTarget, this, VIEW_RADIUS) && !EntityGorgon.isBlindfolded(attackTarget)) {
            if (!shouldMelee()) {
                if (!this.isStaring()) {
                    this.setStaring(true);
                } else {
                    int attackStrength = this.getFriendsCount(attackTarget);
                    if (this.level.getDifficulty() == Difficulty.HARD) {
                        attackStrength++;
                    }
                    attackTarget.addEffect(new EffectInstance(Effects.WITHER, 10, 2 + Math.min(1, attackStrength)));
                    attackTarget.addEffect(new EffectInstance(Effects.MOVEMENT_SLOWDOWN, 10, Math.min(4, attackStrength)));
                    attackTarget.addEffect(new EffectInstance(Effects.CONFUSION, 200, 0));
                    if (attackStrength >= 2 && attackTarget.tickCount % 40 == 0) {
                        attackTarget.hurt(DamageSource.WITHER, attackStrength - 1);
                    }
                    attackTarget.setLastHurtByMob(this);
                    if (!this.isTame() && attackTarget instanceof PlayerEntity) {
                        this.setTamingPlayer(attackTarget.getId());
                        this.setTamingLevel(this.getTamingLevel() + 1);
                        if (this.getTamingLevel() % 100 == 0) {
                            this.level.broadcastEntityEvent(this, (byte) 46);
                        }
                        if (this.getTamingLevel() >= 1000) {
                            this.level.broadcastEntityEvent(this, (byte) 45);
                            if (this.getTamingPlayer() instanceof PlayerEntity)
                                this.tame((PlayerEntity) this.getTamingPlayer());
                            this.setTarget(null);
                            this.setTamingPlayer(0);
                            this.setTargetedEntity(0);
                        }
                    }
                }
            }
        }
        if (!this.level.isClientSide && attackTarget == null && this.getRandom().nextInt(300) == 0 && this.getAnimation() == NO_ANIMATION) {
            this.setAnimation(ANIMATION_WATTLESHAKE);
        }
        if (!this.level.isClientSide) {
            if (shouldMelee() && !this.isMeleeMode) {
                switchAI(true);
            }
            if (!shouldMelee() && this.isMeleeMode) {
                switchAI(false);
            }
        }

        if (this.level.isClientSide && this.getTargetedEntity() != null && EntityGorgon.isEntityLookingAt(this, this.getTargetedEntity(), VIEW_RADIUS) && EntityGorgon.isEntityLookingAt(this.getTargetedEntity(), this, VIEW_RADIUS) && this.isStaring()) {
            if (this.hasTargetedEntity()) {
                if (this.clientSideAttackTime < this.getAttackDuration()) {
                    ++this.clientSideAttackTime;
                }

                LivingEntity livingEntity = this.getTargetedEntity();

                if (livingEntity != null) {
                    this.getLookControl().setLookAt(livingEntity, 90.0F, 90.0F);
                    this.getLookControl().tick();
                    double d5 = this.getAttackAnimationScale(0.0F);
                    double d0 = livingEntity.getX() - this.getX();
                    double d1 = livingEntity.getY() + (double) (livingEntity.getBbHeight() * 0.5F) - (this.getY() + (double) this.getEyeHeight());
                    double d2 = livingEntity.getZ() - this.getZ();
                    double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
                    d0 = d0 / d3;
                    d1 = d1 / d3;
                    d2 = d2 / d3;
                    double d4 = this.random.nextDouble();

                    while (d4 < d3) {
                        d4 += 1.8D - d5 + this.random.nextDouble() * (1.7D - d5);
                        this.level.addParticle(ParticleTypes.ENTITY_EFFECT, this.getX() + d0 * d4, this.getY() + d1 * d4 + (double) this.getEyeHeight(), this.getZ() + d2 * d4, 0.0D, 0.0D, 0.0D);
                    }
                }
            }
        }
        AnimationHandler.INSTANCE.updateAnimations(this);
    }

    private int getFriendsCount(LivingEntity attackTarget) {
        if (this.getTarget() == null) {
            return 0;
        }
        float dist = IafConfig.cockatriceChickenSearchLength;
        List<EntityCockatrice> list = level.getEntitiesOfClass(EntityCockatrice.class, this.getBoundingBox().expandTowards(dist, dist, dist));
        int i = 0;
        for (EntityCockatrice cockatrice : list) {
            if (!cockatrice.is(this) && cockatrice.getTarget() != null && cockatrice.getTarget() == this.getTarget()) {
                boolean bothLooking = EntityGorgon.isEntityLookingAt(cockatrice, cockatrice.getTarget(), VIEW_RADIUS) && EntityGorgon.isEntityLookingAt(cockatrice.getTarget(), cockatrice, VIEW_RADIUS);
                if (bothLooking) {
                    i++;
                }
            }
        }
        return i;
    }

    public float getAttackAnimationScale(float f) {
        return ((float) this.clientSideAttackTime + f) / (float) this.getAttackDuration();
    }

    public boolean shouldStareAttack(Entity entity) {
        return this.distanceTo(entity) > 5;
    }

    public int getAttackDuration() {
        return 80;
    }

    private boolean shouldMelee() {
        boolean blindness = this.hasEffect(Effects.BLINDNESS) || this.getTarget() != null && this.getTarget().hasEffect(Effects.BLINDNESS);
        if (this.getTarget() != null) {
            return this.distanceTo(this.getTarget()) < 4D || ServerEvents.isCockatriceTarget(this.getTarget()) || blindness || !this.canUseStareOn(this.getTarget());
        }
        return false;
    }

    @Override
    public void travel(Vector3d motionVec) {
        if (!this.canMove() && !this.isVehicle()) {
            motionVec = motionVec.multiply(0, 1, 0);
        }
        super.travel(motionVec);
    }

    public void playAmbientSound() {
        if (this.getAnimation() == this.NO_ANIMATION) {
            this.setAnimation(ANIMATION_SPEAK);
        }
        super.playAmbientSound();
    }

    protected void playHurtSound(DamageSource source) {
        if (this.getAnimation() == this.NO_ANIMATION) {
            this.setAnimation(ANIMATION_SPEAK);
        }
        super.playHurtSound(source);
    }

    @Nullable
    @Override
    public AgeableEntity getBreedOffspring(ServerWorld serverWorld, AgeableEntity ageable) {
        return null;
    }

    @Override
    public int getAnimationTick() {
        return animationTick;
    }

    @Override
    public void setAnimationTick(int tick) {
        animationTick = tick;
    }

    @Override
    public Animation getAnimation() {
        return currentAnimation;
    }

    @Override
    public void setAnimation(Animation animation) {
        currentAnimation = animation;
    }

    @Override
    public Animation[] getAnimations() {
        return new Animation[]{NO_ANIMATION, ANIMATION_JUMPAT, ANIMATION_WATTLESHAKE, ANIMATION_BITE, ANIMATION_SPEAK, ANIMATION_EAT};
    }

    @Override
    public boolean canBeTurnedToStone() {
        return false;
    }

    public boolean isTargetBlocked(Vector3d target) {
        Vector3d Vector3d = new Vector3d(this.getX(), this.getEyeY(), this.getZ());
        return this.level.clip(new RayTraceContext(Vector3d, target, RayTraceContext.BlockMode.COLLIDER, RayTraceContext.FluidMode.NONE, this)).getType() == RayTraceResult.Type.MISS;
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        return IafSoundRegistry.COCKATRICE_IDLE;
    }

    @Nullable
    protected SoundEvent getHurtSound(DamageSource source) {
        return IafSoundRegistry.COCKATRICE_HURT;
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return IafSoundRegistry.COCKATRICE_DIE;
    }

    public void handleEntityEvent(byte id) {
        if (id == 45) {
            this.playEffect(true);
        } else if (id == 46) {
            this.playEffect(false);
        } else {
            super.handleEntityEvent(id);
        }
    }

    protected void playEffect(boolean play) {
        IParticleData enumparticletypes = ParticleTypes.HEART;

        if (!play) {
            enumparticletypes = ParticleTypes.DAMAGE_INDICATOR;
        }

        for (int i = 0; i < 7; ++i) {
            double d0 = this.random.nextGaussian() * 0.02D;
            double d1 = this.random.nextGaussian() * 0.02D;
            double d2 = this.random.nextGaussian() * 0.02D;
            this.level.addParticle(enumparticletypes, this.getX() + (double) (this.random.nextFloat() * this.getBbWidth() * 2.0F) - (double) this.getBbWidth(), this.getY() + 0.5D + (double) (this.random.nextFloat() * this.getBbHeight()), this.getZ() + (double) (this.random.nextFloat() * this.getBbWidth() * 2.0F) - (double) this.getBbWidth(), d0, d1, d2);
        }
    }

    @Override
    public boolean isPersistenceRequired() {
        return true;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }
}
