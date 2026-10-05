package net.minecraft.block;

import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$1;
import io.netty.handler.codec.serialization.WeakReferenceMap;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityMinecartFurnace;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent$1;

public class BlockJukebox extends BlockContainer {
   public HttpPostRequestDecoder$1 field_0002;
   public static PropertyBool HAS_RECORD = PropertyBool.create("has_record");
   public BlockMelon field_0000;
   public EntityMinecartFurnace field_0001;
   public WeakReferenceMap field_0005;
   public StructureComponent$1 field_0004;

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(HAS_RECORD, var1 > 0);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, HAS_RECORD);
   }

   @Override
   public int getComparatorInputOverride(World var1, BlockPos var2) {
      TileEntity var3 = var1.getTileEntity(var2);
      if (var3 instanceof BlockJukebox$TileEntityJukebox) {
         ItemStack var4 = ((BlockJukebox$TileEntityJukebox)var3).getRecord();
         if (var4 != null) {
            return Item.getIdFromItem(var4.getItem()) + 1 - Item.getIdFromItem(Items.record_13);
         }
      }

      return 0;
   }

   public void dropRecord(World var1, BlockPos var2, IBlockState var3) {
      if (!var1.D) {
         TileEntity var4 = var1.getTileEntity(var2);
         if (var4 instanceof BlockJukebox$TileEntityJukebox) {
            BlockJukebox$TileEntityJukebox var5 = (BlockJukebox$TileEntityJukebox)var4;
            ItemStack var6 = var5.getRecord();
            if (var6 != null) {
               var1.b(1005, var2, 0);
               var1.playRecord(var2, (String)null);
               var5.setRecord((ItemStack)null);
               float var7 = 0.7F;
               double var8 = var1.s.nextFloat() * var7 + (1.0F - var7) * 0.5;
               double var10 = var1.s.nextFloat() * var7 + (1.0F - var7) * 0.2 + 0.6;
               double var12 = var1.s.nextFloat() * var7 + (1.0F - var7) * 0.5;
               ItemStack var14 = var6.copy();
               EntityItem var15 = new EntityItem(var1, var2.getX() + var8, var2.getY() + var10, var2.getZ() + var12, var14);
               var15.setDefaultPickupDelay();
               var1.spawnEntityInWorld(var15);
            }
         }
      }
   }

   @Override
   public void breakBlock(World var1, BlockPos var2, IBlockState var3) {
      this.dropRecord(var1, var2, var3);
      super.breakBlock(var1, var2, var3);
   }

   public void insertRecord(World var1, BlockPos var2, IBlockState var3, ItemStack var4) {
      if (!var1.D) {
         TileEntity var5 = var1.getTileEntity(var2);
         if (var5 instanceof BlockJukebox$TileEntityJukebox) {
            ((BlockJukebox$TileEntityJukebox)var5).setRecord(new ItemStack(var4.getItem(), 1, var4.getMetadata()));
            var1.a(var2, var3.withProperty(HAS_RECORD, true), 2);
         }
      }
   }

   @Override
   public int getRenderType() {
      return 3;
   }

   @Override
   public void dropBlockAsItemWithChance(World var1, BlockPos var2, IBlockState var3, float var4, int var5) {
      if (!var1.D) {
         super.dropBlockAsItemWithChance(var1, var2, var3, var4, 0);
      }
   }

   public BlockJukebox() {
      super(Material.wood, MapColor.dirtColor);
      this.setDefaultState(this.M.getBaseState().withProperty(HAS_RECORD, false));
      this.setCreativeTab(CreativeTabs.tabDecorations);
   }

   @Override
   public TileEntity createNewTileEntity(World var1, int var2) {
      return new BlockJukebox$TileEntityJukebox();
   }

   @Override
   public boolean onBlockActivated(World var1, BlockPos var2, IBlockState var3, EntityPlayer var4, EnumFacing var5, float var6, float var7, float var8) {
      if (var3.getValue(HAS_RECORD)) {
         this.dropRecord(var1, var2, var3);
         var3 = var3.withProperty(HAS_RECORD, false);
         var1.a(var2, var3, 2);
         return true;
      } else {
         return false;
      }
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(HAS_RECORD) ? 1 : 0;
   }

   @Override
   public boolean hasComparatorInputOverride() {
      return true;
   }
}
