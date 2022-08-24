package com.github.alexthe666.iceandfire.item;

import com.github.alexthe666.iceandfire.IceAndFire;
import com.github.alexthe666.iceandfire.entity.EntityGhostSword;
import com.github.alexthe666.iceandfire.entity.IafEntityRegistry;
import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public class ItemGhostSword extends SwordItem {

    public ItemGhostSword() {
        super(IafItemRegistry.GHOST_SWORD_TOOL_MATERIAL, 5, -1.0F, new Item.Properties().tab(IceAndFire.TAB_ITEMS));
        this.setRegistryName(IceAndFire.MODID, "ghost_sword");
    }

    public static void spawnGhostSwordEntity(ItemStack stack, PlayerEntity playerEntity) {
        if (playerEntity.getCooldowns().isOnCooldown(stack.getItem()))
            return;
        if (playerEntity.getItemInHand(Hand.MAIN_HAND) != stack)
            return;
        final Multimap<Attribute, AttributeModifier> dmg = stack.getAttributeModifiers(EquipmentSlotType.MAINHAND);
        double totalDmg = 0D;
        for (AttributeModifier modifier : dmg.get(Attributes.ATTACK_DAMAGE)) {
            totalDmg += modifier.getAmount();
        }
        playerEntity.playSound(SoundEvents.ZOMBIE_INFECT, 1, 1);
        EntityGhostSword shot = new EntityGhostSword(IafEntityRegistry.GHOST_SWORD.get(), playerEntity.level, playerEntity,
            totalDmg * 0.5F);
        Vector3d vector3d = playerEntity.getViewVector(1.0F);
        Vector3f vector3f = new Vector3f(vector3d);
        shot.shoot(vector3f.x(), vector3f.y(), vector3f.z(), 1.0F, 0.5F);
        playerEntity.level.addFreshEntity(shot);
        stack.hurtAndBreak(1, playerEntity, (entity) -> {
            entity.broadcastBreakEvent(EquipmentSlotType.MAINHAND);
        });
        playerEntity.getCooldowns().addCooldown(stack.getItem(), 10);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity targetEntity, LivingEntity attacker) {
        return super.hurtEnemy(stack, targetEntity, attacker);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("item.iceandfire.legendary_weapon.desc").withStyle(TextFormatting.GRAY));
        tooltip.add(new TranslationTextComponent("item.iceandfire.ghost_sword.desc_0").withStyle(TextFormatting.GRAY));
    }
}