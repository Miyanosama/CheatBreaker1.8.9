package net.minecraft.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityEgg;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;

public class ItemEgg extends Item {
   @Override
   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      if (!var3.bA.isCreativeMode) {
         var1.stackSize--;
      }

      var2.a(var3, "random.bow", 0.5F, 0.4F / (g.nextFloat() * 0.4F + 0.8F));
      if (!var2.D) {
         var2.spawnEntityInWorld(new EntityEgg(var2, var3));
      }

      var3.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
      return var1;
   }

   public ItemEgg() {
      this.h = 16;
      this.setCreativeTab(CreativeTabs.tabMaterials);
   }
}
