package net.minecraft.item;

import io.netty.handler.codec.http.HttpContentCompressor;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.stats.StatList;
import net.minecraft.world.World;
import recovered.unidentified.UnidentifiedClass0753;

public class ItemSnowball extends Item {
   public UnidentifiedClass0753 field_0000;
   public HttpContentCompressor field_0001;

   public ItemSnowball() {
      this.h = 16;
      this.setCreativeTab(CreativeTabs.tabMisc);
   }

   @Override
   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      if (!var3.bA.isCreativeMode) {
         var1.stackSize--;
      }

      var2.a(var3, "random.bow", 0.5F, 0.4F / (g.nextFloat() * 0.4F + 0.8F));
      if (!var2.D) {
         var2.spawnEntityInWorld(new EntitySnowball(var2, var3));
      }

      var3.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
      return var1;
   }
}
