package net.minecraft.item;

import net.minecraft.client.model.ModelSkeleton;
import net.minecraft.client.renderer.chunk.ListedRenderChunk;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.init.Items;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import net.optifine.entity.model.anim.ModelVariableUpdater;
import net.optifine.player.PlayerItemsLayer;

public class ItemBow extends Item {
   public ModelVariableUpdater field_0001;
   public PlayerItemsLayer field_0002;
   public ListedRenderChunk field_0003;
   public static String[] bowPullIconNameArray = new String[]{"pulling_0", "pulling_1", "pulling_2"};
   public ModelSkeleton field_0000;

   @Override
   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      if (var3.bA.isCreativeMode || var3.bi.hasItem(Items.arrow)) {
         var3.setItemInUse(var1, this.getMaxItemUseDuration(var1));
      }

      return var1;
   }

   @Override
   public ItemStack onItemUseFinish(ItemStack var1, World var2, EntityPlayer var3) {
      return var1;
   }

   @Override
   public int getItemEnchantability() {
      return 1;
   }

   @Override
   public void onPlayerStoppedUsing(ItemStack var1, World var2, EntityPlayer var3, int var4) {
      boolean var5 = var3.bA.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, var1) > 0;
      if (var5 || var3.bi.hasItem(Items.arrow)) {
         int var6 = this.getMaxItemUseDuration(var1) - var4;
         float var7 = var6 / 20.0F;
         var7 = (var7 * var7 + var7 * 2.0F) / 3.0F;
         if (var7 < 0.1) {
            return;
         }

         if (var7 > 1.0F) {
            var7 = 1.0F;
         }

         EntityArrow var8 = new EntityArrow(var2, var3, var7 * 2.0F);
         if (var7 == 1.0F) {
            var8.setIsCritical(true);
         }

         int var9 = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, var1);
         if (var9 > 0) {
            var8.setDamage(var8.getDamage() + var9 * 0.5 + 0.5);
         }

         int var10 = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, var1);
         if (var10 > 0) {
            var8.setKnockbackStrength(var10);
         }

         if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, var1) > 0) {
            var8.setFire(100);
         }

         var1.damageItem(1, var3);
         var2.a(var3, "random.bow", 1.0F, 1.0F / (g.nextFloat() * 0.4F + 1.2F) + var7 * 0.5F);
         if (var5) {
            var8.canBePickedUp = 2;
         } else {
            var3.bi.consumeInventoryItem(Items.arrow);
         }

         var3.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
         if (!var2.D) {
            var2.spawnEntityInWorld(var8);
         }
      }
   }

   @Override
   public EnumAction getItemUseAction(ItemStack var1) {
      return EnumAction.BOW;
   }

   public ItemBow() {
      this.h = 1;
      this.setMaxDamage(384);
      this.setCreativeTab(CreativeTabs.tabCombat);
   }

   @Override
   public int getMaxItemUseDuration(ItemStack var1) {
      return 72000;
   }
}
