package net.minecraft.block;

import java.util.List;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer$EnumChatVisibility;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.play.server.S26PacketMapChunkBulk;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.StatCollector;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.apache.log4j.lf5.viewer.LogFactor5InputDialog$3;

public class BlockWall extends Block {
   public static PropertyEnum<BlockWall$EnumType> VARIANT = PropertyEnum.create("variant", BlockWall$EnumType.class);
   public static PropertyBool EAST = PropertyBool.create("east");
   public static PropertyBool WEST = PropertyBool.create("west");
   public static PropertyBool NORTH = PropertyBool.create("north");
   public EntityPlayer$EnumChatVisibility field_0007;
   public static PropertyBool UP = PropertyBool.create("up");
   public S26PacketMapChunkBulk field_0008;
   public static PropertyBool SOUTH = PropertyBool.create("south");
   public LogFactor5InputDialog$3 field_0000;

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(VARIANT, BlockWall$EnumType.byMetadata(var1));
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
      this.setBlockBoundsBasedOnState(var1, var2);
      this.F = 1.5;
      return super.getCollisionBoundingBox(var1, var2, var3);
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, UP, NORTH, EAST, WEST, SOUTH, VARIANT);
   }

   @Override
   public IBlockState getActualState(IBlockState var1, IBlockAccess var2, BlockPos var3) {
      return var1.withProperty(UP, !var2.isAirBlock(var3.up()))
         .withProperty(NORTH, this.canConnectTo(var2, var3.north()))
         .withProperty(EAST, this.canConnectTo(var2, var3.east()))
         .withProperty(SOUTH, this.canConnectTo(var2, var3.south()))
         .withProperty(WEST, this.canConnectTo(var2, var3.west()));
   }

   @Override
   public boolean isPassable(IBlockAccess var1, BlockPos var2) {
      return false;
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      boolean var3 = this.canConnectTo(var1, var2.north());
      boolean var4 = this.canConnectTo(var1, var2.south());
      boolean var5 = this.canConnectTo(var1, var2.west());
      boolean var6 = this.canConnectTo(var1, var2.east());
      float var7 = 0.25F;
      float var8 = 0.75F;
      float var9 = 0.25F;
      float var10 = 0.75F;
      float var11 = 1.0F;
      if (var3) {
         var9 = 0.0F;
      }

      if (var4) {
         var10 = 1.0F;
      }

      if (var5) {
         var7 = 0.0F;
      }

      if (var6) {
         var8 = 1.0F;
      }

      if (var3 && var4 && !var5 && !var6) {
         var11 = 0.8125F;
         var7 = 0.3125F;
         var8 = 0.6875F;
      } else if (!var3 && !var4 && var5 && var6) {
         var11 = 0.8125F;
         var9 = 0.3125F;
         var10 = 0.6875F;
      }

      this.a(var7, 0.0F, var9, var8, var11, var10);
   }

   public BlockWall(Block var1) {
      super(var1.J);
      this.setDefaultState(
         this.M
            .getBaseState()
            .withProperty(UP, false)
            .withProperty(NORTH, false)
            .withProperty(EAST, false)
            .withProperty(SOUTH, false)
            .withProperty(WEST, false)
            .withProperty(VARIANT, BlockWall$EnumType.NORMAL)
      );
      this.setHardness(var1.blockHardness);
      this.setResistance(var1.blockResistance / 3.0F);
      this.setStepSound(var1.stepSound);
      this.setCreativeTab(CreativeTabs.tabBlock);
   }

   @Override
   public boolean shouldSideBeRendered(IBlockAccess var1, BlockPos var2, EnumFacing var3) {
      return var3 == EnumFacing.DOWN ? super.shouldSideBeRendered(var1, var2, var3) : true;
   }

   @Override
   public int damageDropped(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
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
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(VARIANT).getMetadata();
   }

   public boolean canConnectTo(IBlockAccess var1, BlockPos var2) {
      Block var3 = var1.getBlockState(var2).getBlock();
      return var3 == Blocks.barrier
         ? false
         : (var3 != this && !(var3 instanceof BlockFenceGate) ? (var3.J.isOpaque() && var3.isFullCube() ? var3.J != Material.gourd : false) : true);
   }

   @Override
   public void getSubBlocks(Item var1, CreativeTabs var2, List<ItemStack> var3) {
      for (BlockWall$EnumType var7 : BlockWall$EnumType.values()) {
         var3.add(new ItemStack(var1, 1, var7.getMetadata()));
      }
   }

   @Override
   public String getLocalizedName() {
      return StatCollector.translateToLocal(this.getUnlocalizedName() + "." + BlockWall$EnumType.NORMAL.getUnlocalizedName() + ".name");
   }
}
