package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityFishHook;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ItemFishingRod extends Item {
   @Override
   public int getItemEnchantability() {
      return 1;
   }

   public ItemFishingRod() {
      this.setMaxDamage(64);
      this.c(1);
      this.setCreativeTab(CreativeTabs.tabTools);
   }

   @Override
   public boolean isFull3D() {
      return true;
   }

   @Override
   public boolean isItemTool(ItemStack var1) {
      return super.isItemTool(var1);
   }

   @Override
   public boolean method_12601() {
      return true;
   }

   @Override
   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      if (var3.fishEntity != null) {
         int var4 = var3.fishEntity.handleHookRetraction();
         var1.damageItem(var4, var3);
         var3.swingItem();
      } else {
         var2.a(var3, "random.bow", 0.5F, 0.4F / (g.nextFloat() * 0.4F + 0.8F));
         if (!var2.D) {
            var2.spawnEntityInWorld(new EntityFishHook(var2, var3));
         }

         var3.swingItem();
         var3.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
      }

      return var1;
   }
}
