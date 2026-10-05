package net.minecraft.block;

import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.util.HttpUtil;
import net.minecraft.world.World;
import org.newsclub.net.unix.NativeUnixSocket;
import recovered.unidentified.UnidentifiedClass4880;

public class BlockBeacon extends BlockContainer {
   public NativeUnixSocket field_0000;
   public UnidentifiedClass4880 field_0001;

   @Override
   public TileEntity createNewTileEntity(World var1, int var2) {
      return new TileEntityBeacon();
   }

   public BlockBeacon() {
      super(Material.glass, MapColor.diamondColor);
      this.setHardness(3.0F);
      this.setCreativeTab(CreativeTabs.tabMisc);
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   @Override
   public void onBlockPlacedBy(World var1, BlockPos var2, IBlockState var3, EntityLivingBase var4, ItemStack var5) {
      super.onBlockPlacedBy(var1, var2, var3, var4, var5);
      if (var5.hasDisplayName()) {
         TileEntity var6 = var1.getTileEntity(var2);
         if (var6 instanceof TileEntityBeacon) {
            ((TileEntityBeacon)var6).setName(var5.getDisplayName());
         }
      }
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public int getRenderType() {
      return 3;
   }

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      TileEntity var5 = var1.getTileEntity(var2);
      if (var5 instanceof TileEntityBeacon) {
         ((TileEntityBeacon)var5).updateBeacon();
         var1.addBlockEvent(var2, this, 1, 0);
      }
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   public static void updateColorAsync(World var0, BlockPos var1) {
      HttpUtil.field_180193_a.submit(new BlockBeacon$1(var0, var1));
   }

   @Override
   public boolean onBlockActivated(World var1, BlockPos var2, IBlockState var3, EntityPlayer var4, EnumFacing var5, float var6, float var7, float var8) {
      if (var1.D) {
         return true;
      } else {
         TileEntity var9 = var1.getTileEntity(var2);
         if (var9 instanceof TileEntityBeacon) {
            var4.displayGUIChest((TileEntityBeacon)var9);
            var4.triggerAchievement(StatList.field_181730_N);
         }

         return true;
      }
   }
}
