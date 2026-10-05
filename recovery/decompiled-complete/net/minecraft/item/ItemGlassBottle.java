package net.minecraft.item;

import io.netty.handler.traffic.GlobalTrafficShapingHandler$1;
import net.minecraft.block.material.Material;
import net.minecraft.client.model.ModelBlaze;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.MovingObjectPosition$MovingObjectType;
import net.minecraft.world.World;
import net.optifine.model.BlockModelUtils;
import net.optifine.util.IntArray;

public class ItemGlassBottle extends Item {
   public IntArray field_0000;
   public ModelBlaze field_0001;
   public BlockModelUtils field_0002;
   public GlobalTrafficShapingHandler$1 field_0003;

   @Override
   public ItemStack onItemRightClick(ItemStack var1, World var2, EntityPlayer var3) {
      MovingObjectPosition var4 = this.a(var2, var3, true);
      if (var4 == null) {
         return var1;
      } else {
         if (var4.typeOfHit == MovingObjectPosition$MovingObjectType.BLOCK) {
            BlockPos var5 = var4.getBlockPos();
            if (!var2.isBlockModifiable(var3, var5)) {
               return var1;
            }

            if (!var3.canPlayerEdit(var5.a(var4.sideHit), var4.sideHit, var1)) {
               return var1;
            }

            if (var2.getBlockState(var5).getBlock().getMaterial() == Material.water) {
               var1.stackSize--;
               var3.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
               if (var1.stackSize <= 0) {
                  return new ItemStack(Items.potionitem);
               }

               if (!var3.bi.addItemStackToInventory(new ItemStack(Items.potionitem))) {
                  var3.dropPlayerItemWithRandomChoice(new ItemStack(Items.potionitem, 1, 0), false);
               }
            }
         }

         return var1;
      }
   }

   public ItemGlassBottle() {
      this.setCreativeTab(CreativeTabs.tabBrewing);
   }
}
