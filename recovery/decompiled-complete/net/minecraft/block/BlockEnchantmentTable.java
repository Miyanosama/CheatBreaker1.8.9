package net.minecraft.block;

import io.netty.handler.codec.spdy.SpdyHeaders$HttpNames;
import io.netty.util.internal.logging.Slf4JLogger;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms$Deserializer;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityEnchantmentTable;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import org.java_websocket.handshake.HandshakedataImpl1;
import recovered.unidentified.UnidentifiedClass3676;

public class BlockEnchantmentTable extends BlockContainer {
   public Slf4JLogger field_0002;
   public ItemCameraTransforms$Deserializer field_0003;
   public UnidentifiedClass3676 field_0000;
   public SpdyHeaders$HttpNames field_0001;
   public HandshakedataImpl1 field_0004;

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   public BlockEnchantmentTable() {
      super(Material.rock, MapColor.redColor);
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.75F, 1.0F);
      this.setLightOpacity(0);
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   @Override
   public void randomDisplayTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      super.randomDisplayTick(var1, var2, var3, var4);

      for (int var5 = -2; var5 <= 2; var5++) {
         for (int var6 = -2; var6 <= 2; var6++) {
            if (var5 > -2 && var5 < 2 && var6 == -1) {
               var6 = 2;
            }

            if (var4.nextInt(16) == 0) {
               for (int var7 = 0; var7 <= 1; var7++) {
                  BlockPos var8 = var2.add(var5, var7, var6);
                  if (var1.getBlockState(var8).getBlock() == Blocks.bookshelf) {
                     if (!var1.isAirBlock(var2.add(var5 / 2, 0, var6 / 2))) {
                        break;
                     }

                     var1.spawnParticle(
                        EnumParticleTypes.ENCHANTMENT_TABLE,
                        var2.getX() + 0.5,
                        var2.getY() + 2.0,
                        var2.getZ() + 0.5,
                        var5 + var4.nextFloat() - 0.5,
                        var7 - var4.nextFloat() - 1.0F,
                        var6 + var4.nextFloat() - 0.5
                     );
                  }
               }
            }
         }
      }
   }

   @Override
   public void onBlockPlacedBy(World var1, BlockPos var2, IBlockState var3, EntityLivingBase var4, ItemStack var5) {
      super.onBlockPlacedBy(var1, var2, var3, var4, var5);
      if (var5.hasDisplayName()) {
         TileEntity var6 = var1.getTileEntity(var2);
         if (var6 instanceof TileEntityEnchantmentTable) {
            ((TileEntityEnchantmentTable)var6).setCustomName(var5.getDisplayName());
         }
      }
   }

   @Override
   public TileEntity createNewTileEntity(World var1, int var2) {
      return new TileEntityEnchantmentTable();
   }

   @Override
   public boolean onBlockActivated(World var1, BlockPos var2, IBlockState var3, EntityPlayer var4, EnumFacing var5, float var6, float var7, float var8) {
      if (var1.D) {
         return true;
      } else {
         TileEntity var9 = var1.getTileEntity(var2);
         if (var9 instanceof TileEntityEnchantmentTable) {
            var4.displayGui((TileEntityEnchantmentTable)var9);
         }

         return true;
      }
   }

   @Override
   public int getRenderType() {
      return 3;
   }
}
