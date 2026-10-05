package net.minecraft.block;

import com.google.common.cache.LoadingCache;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.BlockWorldState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMonsterPlacer;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockPortal extends BlockBreakable {
   public static PropertyEnum<EnumFacing.Axis> AXIS = PropertyEnum.create("axis", EnumFacing.Axis.class, EnumFacing.Axis.X, EnumFacing.Axis.Z);

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      EnumFacing.Axis var5 = var3.getValue(AXIS);
      if (var5 == EnumFacing.Axis.X) {
         BlockPortal.Size var6 = new BlockPortal.Size(var1, var2, EnumFacing.Axis.X);
         if (!var6.func_150860_b() || var6.field_150864_e < var6.field_150868_h * var6.field_150862_g) {
            var1.setBlockState(var2, Blocks.air.getDefaultState());
         }
      } else if (var5 == EnumFacing.Axis.Z) {
         BlockPortal.Size var7 = new BlockPortal.Size(var1, var2, EnumFacing.Axis.Z);
         if (!var7.func_150860_b() || var7.field_150864_e < var7.field_150868_h * var7.field_150862_g) {
            var1.setBlockState(var2, Blocks.air.getDefaultState());
         }
      }
   }

   @Override
   public int quantityDropped(Random var1) {
      return 0;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
      return null;
   }

   @Override
   public void onEntityCollidedWithBlock(World var1, BlockPos var2, IBlockState var3, Entity var4) {
      if (var4.m == null && var4.l == null) {
         var4.setPortal(var2);
      }
   }

   @Override
   public boolean shouldSideBeRendered(IBlockAccess var1, BlockPos var2, EnumFacing var3) {
      EnumFacing.Axis var4 = null;
      IBlockState var5 = var1.getBlockState(var2);
      if (var1.getBlockState(var2).getBlock() == this) {
         var4 = var5.getValue(AXIS);
         if (var4 == null) {
            return false;
         }

         if (var4 == EnumFacing.Axis.Z && var3 != EnumFacing.EAST && var3 != EnumFacing.WEST) {
            return false;
         }

         if (var4 == EnumFacing.Axis.X && var3 != EnumFacing.SOUTH && var3 != EnumFacing.NORTH) {
            return false;
         }
      }

      boolean var6 = var1.getBlockState(var2.west()).getBlock() == this && var1.getBlockState(var2.west(2)).getBlock() != this;
      boolean var7 = var1.getBlockState(var2.east()).getBlock() == this && var1.getBlockState(var2.east(2)).getBlock() != this;
      boolean var8 = var1.getBlockState(var2.north()).getBlock() == this && var1.getBlockState(var2.north(2)).getBlock() != this;
      boolean var9 = var1.getBlockState(var2.south()).getBlock() == this && var1.getBlockState(var2.south(2)).getBlock() != this;
      boolean var10 = var6 || var7 || var4 == EnumFacing.Axis.X;
      boolean var11 = var8 || var9 || var4 == EnumFacing.Axis.Z;
      return var10 && var3 == EnumFacing.WEST
         ? true
         : (var10 && var3 == EnumFacing.EAST ? true : (var11 && var3 == EnumFacing.NORTH ? true : var11 && var3 == EnumFacing.SOUTH));
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return getMetaForAxis(var1.getValue(AXIS));
   }

   @Override
   public Item getItem(World var1, BlockPos var2) {
      return null;
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.TRANSLUCENT;
   }

   public boolean func_176548_d(World var1, BlockPos var2) {
      BlockPortal.Size var3 = new BlockPortal.Size(var1, var2, EnumFacing.Axis.X);
      if (var3.func_150860_b() && var3.field_150864_e == 0) {
         var3.func_150859_c();
         return true;
      } else {
         BlockPortal.Size var4 = new BlockPortal.Size(var1, var2, EnumFacing.Axis.Z);
         if (var4.func_150860_b() && var4.field_150864_e == 0) {
            var4.func_150859_c();
            return true;
         } else {
            return false;
         }
      }
   }

   public BlockPortal() {
      super(Material.portal, false);
      this.setDefaultState(this.M.getBaseState().withProperty(AXIS, EnumFacing.Axis.X));
      this.setTickRandomly(true);
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, AXIS);
   }

   public static int getMetaForAxis(EnumFacing.Axis var0) {
      return var0 == EnumFacing.Axis.X ? 1 : (var0 == EnumFacing.Axis.Z ? 2 : 0);
   }

   @Override
   public void updateTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      super.updateTick(var1, var2, var3, var4);
      if (var1.t.isSurfaceWorld() && var1.Q().getBoolean("doMobSpawning") && var4.nextInt(2000) < var1.getDifficulty().getDifficultyId()) {
         int var5 = var2.getY();
         BlockPos var6 = var2;

         while (!World.doesBlockHaveSolidTopSurface(var1, var6) && var6.getY() > 0) {
            var6 = var6.down();
         }

         if (var5 > 0 && !var1.getBlockState(var6.up()).getBlock().isNormalCube()) {
            Entity var7 = ItemMonsterPlacer.spawnCreature(var1, 57, var6.getX() + 0.5, var6.getY() + 1.1, var6.getZ() + 0.5);
            if (var7 != null) {
               var7.timeUntilPortal = var7.getPortalCooldown();
            }
         }
      }
   }

   @Override
   public void setBlockBoundsBasedOnState(IBlockAccess var1, BlockPos var2) {
      EnumFacing.Axis var3 = var1.getBlockState(var2).getValue(AXIS);
      float var4 = 0.125F;
      float var5 = 0.125F;
      if (var3 == EnumFacing.Axis.X) {
         var4 = 0.5F;
      }

      if (var3 == EnumFacing.Axis.Z) {
         var5 = 0.5F;
      }

      this.a(0.5F - var4, 0.0F, 0.5F - var5, 0.5F + var4, 1.0F, 0.5F + var5);
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(AXIS, (var1 & 3) == 2 ? EnumFacing.Axis.Z : EnumFacing.Axis.X);
   }

   public BlockPattern.PatternHelper func_181089_f(World var1, BlockPos var2) {
      EnumFacing.Axis var3 = EnumFacing.Axis.Z;
      BlockPortal.Size var4 = new BlockPortal.Size(var1, var2, EnumFacing.Axis.X);
      LoadingCache var5 = BlockPattern.func_181627_a(var1, true);
      if (!var4.func_150860_b()) {
         var3 = EnumFacing.Axis.X;
         var4 = new BlockPortal.Size(var1, var2, EnumFacing.Axis.Z);
      }

      if (!var4.func_150860_b()) {
         return new BlockPattern.PatternHelper(var2, EnumFacing.NORTH, EnumFacing.UP, var5, 1, 1, 1);
      } else {
         int[] var6 = new int[EnumFacing.AxisDirection.values().length];
         EnumFacing var7 = var4.field_150866_c.rotateYCCW();
         BlockPos var8 = var4.field_150861_f.up(var4.func_181100_a() - 1);

         for (EnumFacing.AxisDirection var12 : EnumFacing.AxisDirection.values()) {
            BlockPattern.PatternHelper var13 = new BlockPattern.PatternHelper(
               var7.getAxisDirection() == var12 ? var8 : var8.a(var4.field_150866_c, var4.func_181101_b() - 1),
               EnumFacing.getFacingFromAxis(var12, var3),
               EnumFacing.UP,
               var5,
               var4.func_181101_b(),
               var4.func_181100_a(),
               1
            );

            for (int var14 = 0; var14 < var4.func_181101_b(); var14++) {
               for (int var15 = 0; var15 < var4.func_181100_a(); var15++) {
                  BlockWorldState var16 = var13.translateOffset(var14, var15, 1);
                  if (var16.getBlockState() != null && var16.getBlockState().getBlock().getMaterial() != Material.air) {
                     var6[var12.ordinal()]++;
                  }
               }
            }
         }

         EnumFacing.AxisDirection var17 = EnumFacing.AxisDirection.POSITIVE;

         for (EnumFacing.AxisDirection var21 : EnumFacing.AxisDirection.values()) {
            if (var6[var21.ordinal()] < var6[var17.ordinal()]) {
               var17 = var21;
            }
         }

         return new BlockPattern.PatternHelper(
            var7.getAxisDirection() == var17 ? var8 : var8.a(var4.field_150866_c, var4.func_181101_b() - 1),
            EnumFacing.getFacingFromAxis(var17, var3),
            EnumFacing.UP,
            var5,
            var4.func_181101_b(),
            var4.func_181100_a(),
            1
         );
      }
   }

   @Override
   public void randomDisplayTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      if (var4.nextInt(100) == 0) {
         var1.playSound(var2.getX() + 0.5, var2.getY() + 0.5, var2.getZ() + 0.5, "portal.portal", 0.5F, var4.nextFloat() * 0.4F + 0.8F, false);
      }

      for (int var5 = 0; var5 < 4; var5++) {
         double var6 = var2.getX() + var4.nextFloat();
         double var8 = var2.getY() + var4.nextFloat();
         double var10 = var2.getZ() + var4.nextFloat();
         double var12 = (var4.nextFloat() - 0.5) * 0.5;
         double var14 = (var4.nextFloat() - 0.5) * 0.5;
         double var16 = (var4.nextFloat() - 0.5) * 0.5;
         int var18 = var4.nextInt(2) * 2 - 1;
         if (var1.getBlockState(var2.west()).getBlock() != this && var1.getBlockState(var2.east()).getBlock() != this) {
            var6 = var2.getX() + 0.5 + 0.25 * var18;
            var12 = var4.nextFloat() * 2.0F * var18;
         } else {
            var10 = var2.getZ() + 0.5 + 0.25 * var18;
            var16 = var4.nextFloat() * 2.0F * var18;
         }

         var1.spawnParticle(EnumParticleTypes.PORTAL, var6, var8, var10, var12, var14, var16);
      }
   }

   public static class Size {
      public EnumFacing.Axis axis;
      public World world;
      public int field_150868_h;
      public EnumFacing field_150866_c;
      public EnumFacing recoveredField3348;
      public BlockPos field_150861_f;
      public int field_150862_g;
      public int field_150864_e = 0;

      public void func_150859_c() {
         for (int var1 = 0; var1 < this.field_150868_h; var1++) {
            BlockPos var2 = this.field_150861_f.a(this.field_150866_c, var1);

            for (int var3 = 0; var3 < this.field_150862_g; var3++) {
               this.world.a(var2.up(var3), Blocks.portal.getDefaultState().withProperty(BlockPortal.AXIS, this.axis), 2);
            }
         }
      }

      public int func_181100_a() {
         return this.field_150862_g;
      }

      public int func_181101_b() {
         return this.field_150868_h;
      }

      public int method_25932() {
         label56:
         for (this.field_150862_g = 0; this.field_150862_g < 21; this.field_150862_g++) {
            for (int var1 = 0; var1 < this.field_150868_h; var1++) {
               BlockPos var2 = this.field_150861_f.a(this.field_150866_c, var1).up(this.field_150862_g);
               Block var3 = this.world.getBlockState(var2).getBlock();
               if (!this.func_150857_a(var3)) {
                  break label56;
               }

               if (var3 == Blocks.portal) {
                  this.field_150864_e++;
               }

               if (var1 == 0) {
                  var3 = this.world.getBlockState(var2.a(this.recoveredField3348)).getBlock();
                  if (var3 != Blocks.obsidian) {
                     break label56;
                  }
               } else if (var1 == this.field_150868_h - 1) {
                  var3 = this.world.getBlockState(var2.a(this.field_150866_c)).getBlock();
                  if (var3 != Blocks.obsidian) {
                     break label56;
                  }
               }
            }
         }

         for (int var4 = 0; var4 < this.field_150868_h; var4++) {
            if (this.world.getBlockState(this.field_150861_f.a(this.field_150866_c, var4).up(this.field_150862_g)).getBlock() != Blocks.obsidian) {
               this.field_150862_g = 0;
               break;
            }
         }

         if (this.field_150862_g <= 21 && this.field_150862_g >= 3) {
            return this.field_150862_g;
         } else {
            this.field_150861_f = null;
            this.field_150868_h = 0;
            this.field_150862_g = 0;
            return 0;
         }
      }

      public Size(World var1, BlockPos var2, EnumFacing.Axis var3) {
         this.world = var1;
         this.axis = var3;
         if (var3 == EnumFacing.Axis.X) {
            this.recoveredField3348 = EnumFacing.EAST;
            this.field_150866_c = EnumFacing.WEST;
         } else {
            this.recoveredField3348 = EnumFacing.NORTH;
            this.field_150866_c = EnumFacing.SOUTH;
         }

         BlockPos var4 = var2;

         while (var2.getY() > var4.getY() - 21 && var2.getY() > 0 && this.func_150857_a(var1.getBlockState(var2.down()).getBlock())) {
            var2 = var2.down();
         }

         int var5 = this.func_180120_a(var2, this.recoveredField3348) - 1;
         if (var5 >= 0) {
            this.field_150861_f = var2.a(this.recoveredField3348, var5);
            this.field_150868_h = this.func_180120_a(this.field_150861_f, this.field_150866_c);
            if (this.field_150868_h < 2 || this.field_150868_h > 21) {
               this.field_150861_f = null;
               this.field_150868_h = 0;
            }
         }

         if (this.field_150861_f != null) {
            this.field_150862_g = this.method_25932();
         }
      }

      public boolean func_150860_b() {
         return this.field_150861_f != null && this.field_150868_h >= 2 && this.field_150868_h <= 21 && this.field_150862_g >= 3 && this.field_150862_g <= 21;
      }

      public boolean func_150857_a(Block var1) {
         return var1.J == Material.air || var1 == Blocks.fire || var1 == Blocks.portal;
      }

      public int func_180120_a(BlockPos var1, EnumFacing var2) {
         int var3;
         for (var3 = 0; var3 < 22; var3++) {
            BlockPos var4 = var1.a(var2, var3);
            if (!this.func_150857_a(this.world.getBlockState(var4).getBlock()) || this.world.getBlockState(var4.down()).getBlock() != Blocks.obsidian) {
               break;
            }
         }

         Block var5 = this.world.getBlockState(var1.a(var2, var3)).getBlock();
         return var5 == Blocks.obsidian ? var3 : 0;
      }
   }
}
