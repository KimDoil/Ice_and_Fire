package com.github.alexthe666.iceandfire.entity.props;

import com.github.alexthe666.citadel.Citadel;
import com.github.alexthe666.citadel.server.entity.CitadelEntityData;
import com.github.alexthe666.citadel.server.message.PropertiesMessage;
import com.github.alexthe666.iceandfire.IafConfig;
import com.github.alexthe666.iceandfire.entity.EntitySiren;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.MathHelper;

import javax.annotation.Nullable;
import java.util.Random;

public class SirenProperties {
    private static final String SIREN_DATA = "SirenDataIaf";
    private static final String SIREN_CHARMED = "IsCharmed";
    private static final String SIREN_ID = "CharmedBy";
    private static final String SIREN_TIME = "CharmeTime";
    private static final Random rand = new Random();

    private static CompoundNBT getOrCreateCharmData(LivingEntity entity) {
        return getOrCreateCharmData(CitadelEntityData.getCitadelTag(entity));
    }

    private static CompoundNBT getOrCreateCharmData(CompoundNBT entityData) {
        if (entityData.contains(SIREN_DATA, 10)) {
            return (CompoundNBT) entityData.get(SIREN_DATA);
        } else return createDefaultData();
    }

    private static void clearCharmedStatus(LivingEntity entity) {
        CompoundNBT charmData = getOrCreateCharmData(entity);
        clearCharmedStatus(charmData);
        updateCharmData(entity, charmData);
    }

    private static CompoundNBT clearCharmedStatus(CompoundNBT nbt) {
        nbt.putInt(SIREN_TIME, 0);
        nbt.putBoolean(SIREN_CHARMED, false);
        nbt.putInt(SIREN_ID, -1);
        return nbt;
    }

    private static CompoundNBT createDefaultData() {
        CompoundNBT nbt = new CompoundNBT();
        return clearCharmedStatus(nbt);
    }

    public static void updateData(LivingEntity entity) {
        updateData(entity, CitadelEntityData.getCitadelTag(entity));
    }

    private static void updateData(LivingEntity entity, CompoundNBT nbt) {
        CitadelEntityData.setCitadelTag(entity, nbt);
        if (!entity.level.isClientSide()) {
            Citadel.sendMSGToAll(new PropertiesMessage("CitadelPatreonConfig", nbt, entity.getId()));
        }
    }

    private static void updateCharmData(LivingEntity entity, CompoundNBT nbt) {
        CompoundNBT entityData = CitadelEntityData.getOrCreateCitadelTag(entity);
        entityData.put(SIREN_DATA, nbt);
        CitadelEntityData.setCitadelTag(entity, entityData);
        if (!entity.level.isClientSide()) {
            Citadel.sendMSGToAll(new PropertiesMessage("CitadelPatreonConfig", entityData, entity.getId()));
        }
    }

    private static int getSingTime(LivingEntity entity) {
        CompoundNBT nbt = getOrCreateCharmData(entity);
        if (nbt.contains(SIREN_TIME)) {
            return nbt.getInt(SIREN_TIME);
        }
        return 0;
    }

    public static void setCharmedBy(LivingEntity entity, LivingEntity charmedBy) {
        CompoundNBT nbt = getOrCreateCharmData(entity);
        nbt.putInt(SIREN_ID, charmedBy.getId());
        nbt.putBoolean(SIREN_CHARMED, true);
        updateCharmData(entity, nbt);
    }

    private static int getCharmedBy(LivingEntity entity) {
        CompoundNBT nbt = getOrCreateCharmData(entity);
        if (nbt.contains(SIREN_ID)) {
            return nbt.getInt(SIREN_ID);
        }
        return -1;
    }

    @Nullable
    public static EntitySiren getSiren(LivingEntity entity) {
        Entity siren = entity.level.getEntity(getCharmedBy(entity));
        if (siren instanceof EntitySiren) {
            return (EntitySiren) siren;
        }
        return null;
    }

    public static boolean isCharmed(LivingEntity entity) {
        CompoundNBT nbt = getOrCreateCharmData(entity);
        if (!nbt.contains(SIREN_CHARMED)) {
            nbt = createDefaultData();
            updateCharmData(entity, nbt);
        }
        return nbt.getBoolean(SIREN_CHARMED);
    }

    public static void tickCharmedEntity(LivingEntity entity) {
        EntitySiren siren = getSiren(entity);
        if (siren != null && siren.isActuallySinging()) {
            if (EntitySiren.isWearingEarplugs(entity) || getSingTime(entity) > IafConfig.sirenMaxSingTime) {
                clearCharmedStatus(entity);
                siren.singCooldown = IafConfig.sirenTimeBetweenSongs;
            } else {
                if (!siren.isAlive() || entity.distanceTo(siren) > EntitySiren.SEARCH_RANGE * 2 || entity instanceof PlayerEntity && ((PlayerEntity) entity).isCreative()) {
                    clearCharmedStatus(entity);
                    return;
                }
                if (entity.distanceTo(siren) < 5D) {
                    clearCharmedStatus(entity);
                    siren.singCooldown = IafConfig.sirenTimeBetweenSongs;
                    siren.setSinging(false);
                    siren.setTarget(entity);
                    siren.setAggressive(true);
                    siren.triggerOtherSirens(entity);
                    return;
                }
                CompoundNBT sirenData = getOrCreateCharmData(entity);
                sirenData.putBoolean(SIREN_CHARMED, true);
                sirenData.putInt(SIREN_TIME, getSingTime(entity));
                updateCharmData(entity, sirenData);

                if (rand.nextInt(7) == 0) {
                    for (int i = 0; i < 5; i++) {
                        entity.level.addParticle(ParticleTypes.HEART,
                            entity.getX() + ((rand.nextDouble() - 0.5D) * 3),
                            entity.getY() + ((rand.nextDouble() - 0.5D) * 3),
                            entity.getZ() + ((rand.nextDouble() - 0.5D) * 3),
                            0, 0, 0);
                    }
                }
                if (entity.horizontalCollision) {
                    entity.setJumping(true);
                }
                double motionXAdd = (Math.signum(siren.getX() - entity.getX()) * 0.5D - entity.getDeltaMovement().x) * 0.100000000372529;
                double motionYAdd = (Math.signum(siren.getY() - entity.getY() + 1) * 0.5D - entity.getDeltaMovement().y) * 0.100000000372529;
                double motionZAdd = (Math.signum(siren.getZ() - entity.getZ()) * 0.5D - entity.getDeltaMovement().z) * 0.100000000372529;
                entity.setDeltaMovement(entity.getDeltaMovement().add(motionXAdd, motionYAdd, motionZAdd));
                if (entity.isPassenger()) {
                    entity.stopRiding();
                }
                if (!(entity instanceof PlayerEntity)) {
                    double d0 = siren.getX() - entity.getX();
                    double d2 = siren.getZ() - entity.getZ();
                    double d1 = siren.getY() - 1 - entity.getY();
                    double d3 = MathHelper.sqrt(d0 * d0 + d2 * d2);
                    float f = (float) (MathHelper.atan2(d2, d0) * (180D / Math.PI)) - 90.0F;
                    float f1 = (float) (-(MathHelper.atan2(d1, d3) * (180D / Math.PI)));
                    entity.xRot = updateRotation(entity.xRot, f1, 30F);
                    entity.yRot = updateRotation(entity.yRot, f, 30F);
                }
            }
        }
    }

    public static float updateRotation(float angle, float targetAngle, float maxIncrease) {
        float f = MathHelper.wrapDegrees(targetAngle - angle);
        if (f > maxIncrease) {
            f = maxIncrease;
        }
        if (f < -maxIncrease) {
            f = -maxIncrease;
        }
        return angle + f;
    }
}
