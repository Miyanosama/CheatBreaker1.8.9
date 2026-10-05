package net.minecraft.block;

import com.google.common.collect.Maps;
import io.netty.channel.epoll.EpollDatagramChannelConfig;
import java.util.Map;
import java.util.Random;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockState;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityCreature;
import net.minecraft.init.Blocks;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.EnumWorldBlockLayer;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldProviderEnd;
import recovered.unidentified.UnidentifiedClass0682;

public class BlockFire extends Block {
   public static PropertyBool ALT = PropertyBool.create("alt");
   public static PropertyBool NORTH = PropertyBool.create("north");
   public static PropertyInteger UPPER = PropertyInteger.create("upper", 0, 2);
   public Map<Block, Integer> flammabilities;
   public static PropertyBool WEST = PropertyBool.create("west");
   public static PropertyBool SOUTH = PropertyBool.create("south");
   public UnidentifiedClass0682 field_0012;
   public static PropertyBool EAST = PropertyBool.create("east");
   public static PropertyInteger AGE = PropertyInteger.create("age", 0, 15);
   public EntityCreature field_0004;
   public Map<Block, Integer> encouragements = Maps.newIdentityHashMap();
   public EpollDatagramChannelConfig field_0009;
   public static PropertyBool FLIP = PropertyBool.create("flip");

   @Override
   public boolean isCollidable() {
      return false;
   }

   public int getFlammability(Block var1) {
      Integer var2 = this.flammabilities.get(var1);
      return var2 == null ? 0 : var2;
   }

   @Override
   public boolean canPlaceBlockAt(World var1, BlockPos var2) {
      return World.doesBlockHaveSolidTopSurface(var1, var2.down()) || this.canNeighborCatchFire(var1, var2);
   }

   public boolean canDie(World var1, BlockPos var2) {
      return var1.isRainingAt(var2)
         || var1.isRainingAt(var2.west())
         || var1.isRainingAt(var2.east())
         || var1.isRainingAt(var2.north())
         || var1.isRainingAt(var2.south());
   }

   @Override
   public void randomDisplayTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      if (var4.nextInt(24) == 0) {
         var1.playSound(var2.getX() + 0.5F, var2.getY() + 0.5F, var2.getZ() + 0.5F, "fire.fire", 1.0F + var4.nextFloat(), var4.nextFloat() * 0.7F + 0.3F, false);
      }

      if (!World.doesBlockHaveSolidTopSurface(var1, var2.down()) && !Blocks.fire.canCatchFire(var1, var2.down())) {
         if (Blocks.fire.canCatchFire(var1, var2.west())) {
            for (int var12 = 0; var12 < 2; var12++) {
               double var17 = var2.getX() + var4.nextDouble() * 0.1F;
               double var22 = var2.getY() + var4.nextDouble();
               double var27 = var2.getZ() + var4.nextDouble();
               var1.spawnParticle(EnumParticleTypes.SMOKE_LARGE, var17, var22, var27, 0.0, 0.0, 0.0);
            }
         }

         if (Blocks.fire.canCatchFire(var1, var2.east())) {
            for (int var13 = 0; var13 < 2; var13++) {
               double var18 = var2.getX() + 1 - var4.nextDouble() * 0.1F;
               double var23 = var2.getY() + var4.nextDouble();
               double var28 = var2.getZ() + var4.nextDouble();
               var1.spawnParticle(EnumParticleTypes.SMOKE_LARGE, var18, var23, var28, 0.0, 0.0, 0.0);
            }
         }

         if (Blocks.fire.canCatchFire(var1, var2.north())) {
            for (int var14 = 0; var14 < 2; var14++) {
               double var19 = var2.getX() + var4.nextDouble();
               double var24 = var2.getY() + var4.nextDouble();
               double var29 = var2.getZ() + var4.nextDouble() * 0.1F;
               var1.spawnParticle(EnumParticleTypes.SMOKE_LARGE, var19, var24, var29, 0.0, 0.0, 0.0);
            }
         }

         if (Blocks.fire.canCatchFire(var1, var2.south())) {
            for (int var15 = 0; var15 < 2; var15++) {
               double var20 = var2.getX() + var4.nextDouble();
               double var25 = var2.getY() + var4.nextDouble();
               double var30 = var2.getZ() + 1 - var4.nextDouble() * 0.1F;
               var1.spawnParticle(EnumParticleTypes.SMOKE_LARGE, var20, var25, var30, 0.0, 0.0, 0.0);
            }
         }

         if (Blocks.fire.canCatchFire(var1, var2.up())) {
            for (int var16 = 0; var16 < 2; var16++) {
               double var21 = var2.getX() + var4.nextDouble();
               double var26 = var2.getY() + 1 - var4.nextDouble() * 0.1F;
               double var31 = var2.getZ() + var4.nextDouble();
               var1.spawnParticle(EnumParticleTypes.SMOKE_LARGE, var21, var26, var31, 0.0, 0.0, 0.0);
            }
         }
      } else {
         for (int var5 = 0; var5 < 3; var5++) {
            double var6 = var2.getX() + var4.nextDouble();
            double var8 = var2.getY() + var4.nextDouble() * 0.5 + 0.5;
            double var10 = var2.getZ() + var4.nextDouble();
            var1.spawnParticle(EnumParticleTypes.SMOKE_LARGE, var6, var8, var10, 0.0, 0.0, 0.0);
         }
      }
   }

   @Override
   public int getMetaFromState(IBlockState var1) {
      return var1.getValue(AGE);
   }

   @Override
   public void updateTick(World var1, BlockPos var2, IBlockState var3, Random var4) {
      if (var1.Q().getBoolean("doFireTick")) {
         if (!this.canPlaceBlockAt(var1, var2)) {
            var1.setBlockToAir(var2);
         }

         Block var5 = var1.getBlockState(var2.down()).getBlock();
         boolean var6 = var5 == Blocks.netherrack;
         if (var1.t instanceof WorldProviderEnd && var5 == Blocks.bedrock) {
            var6 = true;
         }

         if (!var6 && var1.isRaining() && this.canDie(var1, var2)) {
            var1.setBlockToAir(var2);
         } else {
            int var7 = var3.getValue(AGE);
            if (var7 < 15) {
               var3 = var3.withProperty(AGE, var7 + var4.nextInt(3) / 2);
               var1.a(var2, var3, 4);
            }

            var1.scheduleUpdate(var2, this, this.tickRate(var1) + var4.nextInt(10));
            if (!var6) {
               if (!this.canNeighborCatchFire(var1, var2)) {
                  if (!World.doesBlockHaveSolidTopSurface(var1, var2.down()) || var7 > 3) {
                     var1.setBlockToAir(var2);
                  }

                  return;
               }

               if (!this.canCatchFire(var1, var2.down()) && var7 == 15 && var4.nextInt(4) == 0) {
                  var1.setBlockToAir(var2);
                  return;
               }
            }

            boolean var8 = var1.isBlockinHighHumidity(var2);
            byte var9 = 0;
            if (var8) {
               var9 = -50;
            }

            this.catchOnFire(var1, var2.east(), 300 + var9, var4, var7);
            this.catchOnFire(var1, var2.west(), 300 + var9, var4, var7);
            this.catchOnFire(var1, var2.down(), 250 + var9, var4, var7);
            this.catchOnFire(var1, var2.up(), 250 + var9, var4, var7);
            this.catchOnFire(var1, var2.north(), 300 + var9, var4, var7);
            this.catchOnFire(var1, var2.south(), 300 + var9, var4, var7);

            for (int var10 = -1; var10 <= 1; var10++) {
               for (int var11 = -1; var11 <= 1; var11++) {
                  for (int var12 = -1; var12 <= 4; var12++) {
                     if (var10 != 0 || var12 != 0 || var11 != 0) {
                        int var13 = 100;
                        if (var12 > 1) {
                           var13 += (var12 - 1) * 100;
                        }

                        BlockPos var14 = var2.add(var10, var12, var11);
                        int var15 = this.getNeighborEncouragement(var1, var14);
                        if (var15 > 0) {
                           int var16 = (var15 + 40 + var1.getDifficulty().getDifficultyId() * 7) / (var7 + 30);
                           if (var8) {
                              var16 /= 2;
                           }

                           if (var16 > 0 && var4.nextInt(var13) <= var16 && (!var1.isRaining() || !this.canDie(var1, var14))) {
                              int var17 = var7 + var4.nextInt(5) / 4;
                              if (var17 > 15) {
                                 var17 = 15;
                              }

                              var1.a(var14, var3.withProperty(AGE, var17), 3);
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   public int getNeighborEncouragement(World var1, BlockPos var2) {
      if (!var1.isAirBlock(var2)) {
         return 0;
      } else {
         int var3 = 0;

         for (EnumFacing var7 : EnumFacing.values()) {
            var3 = Math.max(this.getEncouragement(var1.getBlockState(var2.a(var7)).getBlock()), var3);
         }

         return var3;
      }
   }

   public static void init() {
      Blocks.fire.setFireInfo(Blocks.planks, 5, 20);
      Blocks.fire.setFireInfo(Blocks.double_wooden_slab, 5, 20);
      Blocks.fire.setFireInfo(Blocks.wooden_slab, 5, 20);
      Blocks.fire.setFireInfo(Blocks.oak_fence_gate, 5, 20);
      Blocks.fire.setFireInfo(Blocks.spruce_fence_gate, 5, 20);
      Blocks.fire.setFireInfo(Blocks.birch_fence_gate, 5, 20);
      Blocks.fire.setFireInfo(Blocks.jungle_fence_gate, 5, 20);
      Blocks.fire.setFireInfo(Blocks.dark_oak_fence_gate, 5, 20);
      Blocks.fire.setFireInfo(Blocks.acacia_fence_gate, 5, 20);
      Blocks.fire.setFireInfo(Blocks.oak_fence, 5, 20);
      Blocks.fire.setFireInfo(Blocks.spruce_fence, 5, 20);
      Blocks.fire.setFireInfo(Blocks.birch_fence, 5, 20);
      Blocks.fire.setFireInfo(Blocks.jungle_fence, 5, 20);
      Blocks.fire.setFireInfo(Blocks.dark_oak_fence, 5, 20);
      Blocks.fire.setFireInfo(Blocks.acacia_fence, 5, 20);
      Blocks.fire.setFireInfo(Blocks.oak_stairs, 5, 20);
      Blocks.fire.setFireInfo(Blocks.birch_stairs, 5, 20);
      Blocks.fire.setFireInfo(Blocks.spruce_stairs, 5, 20);
      Blocks.fire.setFireInfo(Blocks.jungle_stairs, 5, 20);
      Blocks.fire.setFireInfo(Blocks.log, 5, 5);
      Blocks.fire.setFireInfo(Blocks.log2, 5, 5);
      Blocks.fire.setFireInfo(Blocks.leaves, 30, 60);
      Blocks.fire.setFireInfo(Blocks.leaves2, 30, 60);
      Blocks.fire.setFireInfo(Blocks.bookshelf, 30, 20);
      Blocks.fire.setFireInfo(Blocks.tnt, 15, 100);
      Blocks.fire.setFireInfo(Blocks.tallgrass, 60, 100);
      Blocks.fire.setFireInfo(Blocks.double_plant, 60, 100);
      Blocks.fire.setFireInfo(Blocks.yellow_flower, 60, 100);
      Blocks.fire.setFireInfo(Blocks.red_flower, 60, 100);
      Blocks.fire.setFireInfo(Blocks.deadbush, 60, 100);
      Blocks.fire.setFireInfo(Blocks.wool, 30, 60);
      Blocks.fire.setFireInfo(Blocks.vine, 15, 100);
      Blocks.fire.setFireInfo(Blocks.coal_block, 5, 5);
      Blocks.fire.setFireInfo(Blocks.hay_block, 60, 20);
      Blocks.fire.setFireInfo(Blocks.carpet, 60, 20);
   }

   public boolean canNeighborCatchFire(World var1, BlockPos var2) {
      for (EnumFacing var6 : EnumFacing.values()) {
         if (this.canCatchFire(var1, var2.a(var6))) {
            return true;
         }
      }

      return false;
   }

   public BlockFire() {
      super(Material.fire);
      this.flammabilities = Maps.newIdentityHashMap();
      this.setDefaultState(
         this.M
            .getBaseState()
            .withProperty(AGE, 0)
            .withProperty(FLIP, false)
            .withProperty(ALT, false)
            .withProperty(NORTH, false)
            .withProperty(EAST, false)
            .withProperty(SOUTH, false)
            .withProperty(WEST, false)
            .withProperty(UPPER, 0)
      );
      this.setTickRandomly(true);
   }

   @Override
   public boolean isOpaqueCube() {
      return false;
   }

   @Override
   public AxisAlignedBB getCollisionBoundingBox(World var1, BlockPos var2, IBlockState var3) {
      return null;
   }

   @Override
   public MapColor getMapColor(IBlockState var1) {
      return MapColor.tntColor;
   }

   @Override
   public boolean isFullCube() {
      return false;
   }

   @Override
   public void onBlockAdded(World var1, BlockPos var2, IBlockState var3) {
      if (var1.t.getDimensionId() > 0 || !Blocks.portal.func_176548_d(var1, var2)) {
         if (!World.doesBlockHaveSolidTopSurface(var1, var2.down()) && !this.canNeighborCatchFire(var1, var2)) {
            var1.setBlockToAir(var2);
         } else {
            var1.scheduleUpdate(var2, this, this.tickRate(var1) + var1.s.nextInt(10));
         }
      }
   }

   @Override
   public int tickRate(World var1) {
      return 30;
   }

   @Override
   public EnumWorldBlockLayer getBlockLayer() {
      return EnumWorldBlockLayer.CUTOUT;
   }

   @Override
   public boolean requiresUpdates() {
      return false;
   }

   @Override
   public int quantityDropped(Random var1) {
      return 0;
   }

   @Override
   public BlockState createBlockState() {
      return new BlockState(this, AGE, NORTH, EAST, SOUTH, WEST, UPPER, FLIP, ALT);
   }

   public void setFireInfo(Block var1, int var2, int var3) {
      this.encouragements.put(var1, var2);
      this.flammabilities.put(var1, var3);
   }

   public boolean canCatchFire(IBlockAccess var1, BlockPos var2) {
      return this.getEncouragement(var1.getBlockState(var2).getBlock()) > 0;
   }

   public void catchOnFire(World var1, BlockPos var2, int var3, Random var4, int var5) {
      int var6 = this.getFlammability(var1.getBlockState(var2).getBlock());
      if (var4.nextInt(var3) < var6) {
         IBlockState var7 = var1.getBlockState(var2);
         if (var4.nextInt(var5 + 10) < 5 && !var1.isRainingAt(var2)) {
            int var8 = var5 + var4.nextInt(5) / 4;
            if (var8 > 15) {
               var8 = 15;
            }

            var1.a(var2, this.getDefaultState().withProperty(AGE, var8), 3);
         } else {
            var1.setBlockToAir(var2);
         }

         if (var7.getBlock() == Blocks.tnt) {
            Blocks.tnt.onBlockDestroyedByPlayer(var1, var2, var7.withProperty(BlockTNT.EXPLODE, true));
         }
      }
   }

   @Override
   public IBlockState getActualState(IBlockState var1, IBlockAccess var2, BlockPos var3) {
      int var4 = var3.getX();
      int var5 = var3.getY();
      int var6 = var3.getZ();
      if (!World.doesBlockHaveSolidTopSurface(var2, var3.down()) && !Blocks.fire.canCatchFire(var2, var3.down())) {
         boolean var7 = (var4 + var5 + var6 & 1) == 1;
         boolean var8 = (var4 / 2 + var5 / 2 + var6 / 2 & 1) == 1;
         int var9 = 0;
         if (this.canCatchFire(var2, var3.up())) {
            var9 = var7 ? 1 : 2;
         }

         return var1.withProperty(NORTH, this.canCatchFire(var2, var3.north()))
            .withProperty(EAST, this.canCatchFire(var2, var3.east()))
            .withProperty(SOUTH, this.canCatchFire(var2, var3.south()))
            .withProperty(WEST, this.canCatchFire(var2, var3.west()))
            .withProperty(UPPER, var9)
            .withProperty(FLIP, var8)
            .withProperty(ALT, var7);
      } else {
         return this.getDefaultState();
      }
   }

   @Override
   public IBlockState getStateFromMeta(int var1) {
      return this.getDefaultState().withProperty(AGE, var1);
   }

   @Override
   public void onNeighborBlockChange(World var1, BlockPos var2, IBlockState var3, Block var4) {
      if (!World.doesBlockHaveSolidTopSurface(var1, var2.down()) && !this.canNeighborCatchFire(var1, var2)) {
         var1.setBlockToAir(var2);
      }
   }

   public int getEncouragement(Block var1) {
      Integer var2 = this.encouragements.get(var1);
      return var2 == null ? 0 : var2;
   }
}
