package net.minecraft.block;

import java.util.List;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.inventory.GuiCrafting;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.boss.EntityWither$1;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityHopper;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings;
import net.optifine.entity.model.ModelAdapterSlime;
import org.java_websocket.framing.TextFrame;

public class BlockHopper extends BlockContainer {
   public GuiCrafting field_0002;
   public ModelAdapterSlime field_0003;
   public WorldSettings field_0000;
   public TextFrame field_0001;
   public static PropertyBool ENABLED = PropertyBool.create("enabled");
   public static PropertyDirection FACING = PropertyDirection.create("facing", new BlockHopper$1());
   public EntityWither$1 field_0006;

   @Override
   public TileEntity createNewTileEntity(World var1, int var2) {
      return new TileEntityHopper();
   }

   @Override
   public void onBlockPlacedBy(World var1, BlockPos var2, IBlockState var3, EntityLivingBase var4, ItemStack var5) {
      super.onBlockPlacedBy(var1, var2, var3, var4, var5);
      if (var5.hasDisplayName()) {
         TileEntity var6 = var1.getTileEntity(var2);
         if (var6 instanceof TileEntityHopper) {
            ((TileEntityHopper)var6).setCustomName(var5.getDisplayName());
         }
      }
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, FACING, ENABLED);
   }

   public BlockHopper() {
      super(Material.iron, MapColor.stoneColor);
      this.setDefaultState(this.M.getBaseState().withProperty(FACING, EnumFacing.DOWN).withProperty(ENABLED, true));
      this.setCreativeTab(CreativeTabs.tabRedstone);
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   @Override
   public boolean hasComparatorInputOverride() {
      return true;
   }

   @Override
   public boolean onBlockActivated(World var1, BlockPos var2, IBlockState var3, EntityPlayer var4, EnumFacing var5, float var6, float var7, float var8) {
      if (var1.D) {
         return true;
      } else {
         TileEntity var9 = var1.getTileEntity(var2);
         if (var9 instanceof TileEntityHopper) {
            var4.displayGUIChest((TileEntityHopper)var9);
            var4.triggerAchievement(StatList.field_181732_P);
         }

         return true;
      }
   }

   @Override
   public void addCollisionBoxesToList(World var1, BlockPos var2, IBlockState var3, AxisAlignedBB var4, List<AxisAlignedBB> var5, Entity var6) {
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 0.625F, 1.0F);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6);
      float var7 = 0.125F;
      this.a(0.0F, 0.0F, 0.0F, var7, 1.0F, 1.0F);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6);
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, var7);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6);
      this.a(1.0F - var7, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6);
      this.a(0.0F, 0.0F, 1.0F - var7, 1.0F, 1.0F, 1.0F);
      super.addCollisionBoxesToList(var1, var2, var3, var4, var5, var6);
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      this.updateState(var1, var2, var3);
   }

   public static EnumFacing getFacing(int var0) {
      return EnumFacing.getFront(var0 & 7);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public int getComparatorInputOverride(World var1, BlockPos var2) {
      return Container.calcRedstone(var1.getTileEntity(var2));
   }

   @Override
   public int getRenderType() {
      return 3;
   }

   public void updateState(World var1, BlockPos var2, IBlockState var3) {
      boolean var4 = !var1.isBlockPowered(var2);
      if (var4 != var3.getValue(ENABLED)) {
         var1.a(var2, var3.withProperty(ENABLED, var4), 4);
      }
   }

   @Override
   public boolean shouldSideBeRendered(IBlockAccess var1, BlockPos var2, EnumFacing var3) {
      return true;
   }

   public static boolean isEnabled(int var0) {
      return (var0 & 8) != 8;
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      int var2 = 0;
      var2 |= var1.getValue(FACING).getIndex();
      if (!var1.getValue(ENABLED)) {
         var2 |= 8;
      }

      return var2;
   }

   @Override
   public IBlockState onBlockPlaced(World var1, BlockPos var2, EnumFacing var3, float var4, float var5, float var6, int var7, EntityLivingBase var8) {
      EnumFacing var9 = var3.getOpposite();
      if (var9 == EnumFacing.UP) {
         var9 = EnumFacing.DOWN;
      }

      return this.getDefaultState().withProperty(FACING, var9).withProperty(ENABLED, true);
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT_MIPPED;
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(FACING, getFacing(var1)).withProperty(ENABLED, isEnabled(var1));
   }

   @Override
   public void breakBlock(World var1, BlockPos var2, IBlockState var3) {
      TileEntity var4 = var1.getTileEntity(var2);
      if (var4 instanceof TileEntityHopper) {
         InventoryHelper.dropInventoryItems(var1, var2, (TileEntityHopper)var4);
         var1.updateComparatorOutputLevel(var2, this);
      }

      super.breakBlock(var1, var2, var3);
   }

   @Override
   public void onBlockAdded(World var1, BlockPos var2, IBlockState var3) {
      this.updateState(var1, var2, var3);
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      this.a(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
   }
}
