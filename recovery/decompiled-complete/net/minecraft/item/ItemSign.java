package net.minecraft.item;

import net.minecraft.block.BlockStandingSign;
import net.minecraft.block.BlockWallSign;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import recovered.unidentified.UnidentifiedClass5063;

public class ItemSign extends Item {
   public EntityVillager field_0000;
   public SimpleTexture field_0001;
   public UnidentifiedClass5063 field_0002;
   public MovingObjectPosition field_0003;

   public ItemSign() {
      this.h = 16;
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   @Override
   public boolean onItemUse(ItemStack var1, EntityPlayer var2, World var3, BlockPos var4, EnumFacing var5, float var6, float var7, float var8) {
      if (var5 == EnumFacing.DOWN) {
         return false;
      } else if (!var3.getBlockState(var4).getBlock().getMaterial().isSolid()) {
         return false;
      } else {
         var4 = var4.a(var5);
         if (!var2.canPlayerEdit(var4, var5, var1)) {
            return false;
         } else if (!Blocks.standing_sign.canPlaceBlockAt(var3, var4)) {
            return false;
         } else if (var3.D) {
            return true;
         } else {
            if (var5 == EnumFacing.UP) {
               int var9 = MathHelper.floor_double((var2.y + 180.0F) * 16.0F / 360.0F + 0.5) & 15;
               var3.a(var4, Blocks.standing_sign.getDefaultState().withProperty(BlockStandingSign.ROTATION, var9), 3);
            } else {
               var3.a(var4, Blocks.wall_sign.getDefaultState().withProperty(BlockWallSign.FACING, var5), 3);
            }

            var1.stackSize--;
            TileEntity var11 = var3.getTileEntity(var4);
            if (var11 instanceof TileEntitySign && !ItemBlock.setTileEntityNBT(var3, var2, var4, var1)) {
               var2.openEditSign((TileEntitySign)var11);
            }

            return true;
         }
      }
   }
}
