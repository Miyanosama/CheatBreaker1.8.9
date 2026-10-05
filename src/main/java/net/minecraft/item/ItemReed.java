package net.minecraft.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSnow;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class ItemReed extends Item {
   public Block recoveredField0;

   @Override
   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, BlockPos var4, EnumFacing var5, float var6, float var7, float var8) {
      IBlockState var9 = var3.getBlockState(var4);
      Block var10 = var9.getBlock();
      if (var10 == Blocks.snow_layer && var9.getValue(BlockSnow.LAYERS) < 1) {
         var5 = EnumFacing.UP;
      } else if (!var10.isReplaceable(var3, var4)) {
         var4 = var4.a(var5);
      }

      if (!var2.canPlayerEdit(var4, var5, var1)) {
         return false;
      } else if (var1.stackSize == 0) {
         return false;
      } else {
         if (var3.canBlockBePlaced(this.recoveredField0, var4, false, var5, (Entity)null, var1)) {
            IBlockState var11 = this.recoveredField0.onBlockPlaced(var3, var4, var5, var6, var7, var8, 0, var2);
            if (var3.a(var4, var11, 3)) {
               var11 = var3.getBlockState(var4);
               if (var11.getBlock() == this.recoveredField0) {
                  ItemBlock.setTileEntityNBT(var3, var2, var4, var1);
                  var11.getBlock().onBlockPlacedBy(var3, var4, var11, var2, var1);
               }

               var3.playSoundEffect(
                  var4.getX() + 0.5F,
                  var4.getY() + 0.5F,
                  var4.getZ() + 0.5F,
                  this.recoveredField0.stepSound.getPlaceSound(),
                  (this.recoveredField0.stepSound.getVolume() + 1.0F) / 2.0F,
                  this.recoveredField0.stepSound.getFrequency() * 0.8F
               );
               var1.stackSize--;
               return true;
            }
         }

         return false;
      }
   }

   public ItemReed(Block var1) {
      this.recoveredField0 = var1;
   }
}
