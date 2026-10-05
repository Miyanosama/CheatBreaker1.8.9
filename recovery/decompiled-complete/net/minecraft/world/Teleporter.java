package net.minecraft.world;

import com.google.common.collect.Lists;
import io.netty.handler.codec.socks.SocksCmdStatus;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockPortal;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockPattern$PatternHelper;
import net.minecraft.client.gui.MapItemRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ContainerPlayer;
import net.minecraft.network.play.server.S44PacketWorldBorder$Action;
import net.minecraft.util.BlockPos;
import net.minecraft.util.BlockPos$MutableBlockPos;
import net.minecraft.util.EnumFacing$Axis;
import net.minecraft.util.EnumFacing$AxisDirection;
import net.minecraft.util.LongHashMap;
import net.minecraft.util.MathHelper;
import recovered.unidentified.UnidentifiedClass4246;

public class Teleporter {
   public ContainerPlayer field_0004;
   public List<Long> destinationCoordinateKeys;
   public MapItemRenderer field_0003;
   public UnidentifiedClass4246 field_0006;
   public Random random;
   public WorldServer worldServerInstance;
   public S44PacketWorldBorder$Action field_0008;
   public LongHashMap<Teleporter$PortalPosition> destinationCoordinateCache = new LongHashMap<>();
   public SocksCmdStatus field_0002;

   public void placeInPortal(Entity var1, float var2) {
      if (this.worldServerInstance.t.getDimensionId() != 1) {
         if (!this.placeInExistingPortal(var1, var2)) {
            this.makePortal(var1);
            this.placeInExistingPortal(var1, var2);
         }
      } else {
         int var3 = MathHelper.floor_double(var1.s);
         int var4 = MathHelper.floor_double(var1.t) - 1;
         int var5 = MathHelper.floor_double(var1.u);
         byte var6 = 1;
         byte var7 = 0;

         for (int var8 = -2; var8 <= 2; var8++) {
            for (int var9 = -2; var9 <= 2; var9++) {
               for (int var10 = -1; var10 < 3; var10++) {
                  int var11 = var3 + var9 * var6 + var8 * var7;
                  int var12 = var4 + var10;
                  int var13 = var5 + var9 * var7 - var8 * var6;
                  boolean var14 = var10 < 0;
                  this.worldServerInstance
                     .setBlockState(new BlockPos(var11, var12, var13), var14 ? Blocks.obsidian.getDefaultState() : Blocks.air.getDefaultState());
               }
            }
         }

         var1.a_(var3, var4, var5, var1.y, 0.0F);
         var1.v = var1.w = var1.x = 0.0;
      }
   }

   public Teleporter(WorldServer var1) {
      this.destinationCoordinateKeys = Lists.newArrayList();
      this.worldServerInstance = var1;
      this.random = new Random(var1.J());
   }

   public void removeStalePortalLocations(long var1) {
      if (var1 % (5520137192695148661L & 132460L) == (497291494L & 38962176L)) {
         Iterator var3 = this.destinationCoordinateKeys.iterator();
         long var4 = var1 - (682629484L & 1841870177970168126L);

         while (var3.hasNext()) {
            Long var6 = (Long)var3.next();
            Teleporter$PortalPosition var7 = this.destinationCoordinateCache.getValueByKey(var6);
            if (var7 == null || var7.lastUpdateTime < var4) {
               var3.remove();
               this.destinationCoordinateCache.remove(var6);
            }
         }
      }
   }

   public boolean placeInExistingPortal(Entity var1, float var2) {
      short var3 = 128;
      double var4 = -1.0;
      int var6 = MathHelper.floor_double(var1.s);
      int var7 = MathHelper.floor_double(var1.u);
      boolean var8 = true;
      Object var9 = BlockPos.ORIGIN;
      long var10 = ChunkCoordIntPair.chunkXZ2Int(var6, var7);
      if (this.destinationCoordinateCache.containsItem(var10)) {
         Teleporter$PortalPosition var12 = this.destinationCoordinateCache.getValueByKey(var10);
         var4 = 0.0;
         var9 = var12;
         var12.lastUpdateTime = this.worldServerInstance.K();
         var8 = false;
      } else {
         BlockPos var30 = new BlockPos(var1);

         for (int var13 = -128; var13 <= 128; var13++) {
            for (int var15 = -128; var15 <= 128; var15++) {
               BlockPos var16 = var30.add(var13, this.worldServerInstance.getActualHeight() - 1 - var30.getY(), var15);

               while (var16.getY() >= 0) {
                  BlockPos var14 = var16.down();
                  if (this.worldServerInstance.getBlockState(var16).getBlock() == Blocks.portal) {
                     while (this.worldServerInstance.getBlockState(var14 = var16.down()).getBlock() == Blocks.portal) {
                        var16 = var14;
                     }

                     double var17 = var16.distanceSq(var30);
                     if (var4 < 0.0 || var17 < var4) {
                        var4 = var17;
                        var9 = var16;
                     }
                  }

                  var16 = var14;
               }
            }
         }
      }

      if (var4 >= 0.0) {
         if (var8) {
            this.destinationCoordinateCache.add(var10, new Teleporter$PortalPosition(this, (BlockPos)var9, this.worldServerInstance.K()));
            this.destinationCoordinateKeys.add(var10);
         }

         double var31 = ((BlockPos)var9).getX() + 0.5;
         double var32 = ((BlockPos)var9).getY() + 0.5;
         double var34 = ((BlockPos)var9).getZ() + 0.5;
         BlockPattern$PatternHelper var18 = Blocks.portal.func_181089_f(this.worldServerInstance, (BlockPos)var9);
         boolean var19 = var18.getFinger().rotateY().getAxisDirection() == EnumFacing$AxisDirection.NEGATIVE;
         double var20 = var18.getFinger().getAxis() == EnumFacing$Axis.X ? var18.getPos().getZ() : var18.getPos().getX();
         var32 = var18.getPos().getY() + 1 - var1.method_10465().yCoord * var18.func_181119_e();
         if (var19) {
            var20++;
         }

         if (var18.getFinger().getAxis() == EnumFacing$Axis.X) {
            var34 = var20 + (1.0 - var1.method_10465().xCoord) * var18.func_181118_d() * var18.getFinger().rotateY().getAxisDirection().getOffset();
         } else {
            var31 = var20 + (1.0 - var1.method_10465().xCoord) * var18.func_181118_d() * var18.getFinger().rotateY().getAxisDirection().getOffset();
         }

         float var22 = 0.0F;
         float var23 = 0.0F;
         float var24 = 0.0F;
         float var25 = 0.0F;
         if (var18.getFinger().getOpposite() == var1.getTeleportDirection()) {
            var22 = 1.0F;
            var23 = 1.0F;
         } else if (var18.getFinger().getOpposite() == var1.getTeleportDirection().getOpposite()) {
            var22 = -1.0F;
            var23 = -1.0F;
         } else if (var18.getFinger().getOpposite() == var1.getTeleportDirection().rotateY()) {
            var24 = 1.0F;
            var25 = -1.0F;
         } else {
            var24 = -1.0F;
            var25 = 1.0F;
         }

         double var26 = var1.v;
         double var28 = var1.x;
         var1.v = var26 * var22 + var28 * var25;
         var1.x = var26 * var24 + var28 * var23;
         var1.y = var2 - var1.getTeleportDirection().getOpposite().getHorizontalIndex() * 90 + var18.getFinger().getHorizontalIndex() * 90;
         var1.a_(var31, var32, var34, var1.y, var1.z);
         return true;
      } else {
         return false;
      }
   }

   public boolean makePortal(Entity var1) {
      byte var2 = 16;
      double var3 = -1.0;
      int var5 = MathHelper.floor_double(var1.s);
      int var6 = MathHelper.floor_double(var1.t);
      int var7 = MathHelper.floor_double(var1.u);
      int var8 = var5;
      int var9 = var6;
      int var10 = var7;
      int var11 = 0;
      int var12 = this.random.nextInt(4);
      BlockPos$MutableBlockPos var13 = new BlockPos$MutableBlockPos();

      for (int var14 = var5 - var2; var14 <= var5 + var2; var14++) {
         double var15 = var14 + 0.5 - var1.s;

         for (int var17 = var7 - var2; var17 <= var7 + var2; var17++) {
            double var18 = var17 + 0.5 - var1.u;

            label294:
            for (int var20 = this.worldServerInstance.getActualHeight() - 1; var20 >= 0; var20--) {
               if (this.worldServerInstance.isAirBlock(var13.set(var14, var20, var17))) {
                  while (var20 > 0 && this.worldServerInstance.isAirBlock(var13.set(var14, var20 - 1, var17))) {
                     var20--;
                  }

                  for (int var21 = var12; var21 < var12 + 4; var21++) {
                     int var22 = var21 % 2;
                     int var23 = 1 - var22;
                     if (var21 % 4 >= 2) {
                        var22 = -var22;
                        var23 = -var23;
                     }

                     for (int var24 = 0; var24 < 3; var24++) {
                        for (int var25 = 0; var25 < 4; var25++) {
                           for (int var26 = -1; var26 < 4; var26++) {
                              int var27 = var14 + (var25 - 1) * var22 + var24 * var23;
                              int var28 = var20 + var26;
                              int var29 = var17 + (var25 - 1) * var23 - var24 * var22;
                              var13.set(var27, var28, var29);
                              if (var26 < 0 && !this.worldServerInstance.getBlockState(var13).getBlock().getMaterial().isSolid()
                                 || var26 >= 0 && !this.worldServerInstance.isAirBlock(var13)) {
                                 continue label294;
                              }
                           }
                        }
                     }

                     double var55 = var20 + 0.5 - var1.t;
                     double var65 = var15 * var15 + var55 * var55 + var18 * var18;
                     if (var3 < 0.0 || var65 < var3) {
                        var3 = var65;
                        var8 = var14;
                        var9 = var20;
                        var10 = var17;
                        var11 = var21 % 4;
                     }
                  }
               }
            }
         }
      }

      if (var3 < 0.0) {
         for (int var31 = var5 - var2; var31 <= var5 + var2; var31++) {
            double var33 = var31 + 0.5 - var1.s;

            for (int var35 = var7 - var2; var35 <= var7 + var2; var35++) {
               double var37 = var35 + 0.5 - var1.u;

               label231:
               for (int var40 = this.worldServerInstance.getActualHeight() - 1; var40 >= 0; var40--) {
                  if (this.worldServerInstance.isAirBlock(var13.set(var31, var40, var35))) {
                     while (var40 > 0 && this.worldServerInstance.isAirBlock(var13.set(var31, var40 - 1, var35))) {
                        var40--;
                     }

                     for (int var43 = var12; var43 < var12 + 2; var43++) {
                        int var47 = var43 % 2;
                        int var51 = 1 - var47;

                        for (int var56 = 0; var56 < 4; var56++) {
                           for (int var61 = -1; var61 < 4; var61++) {
                              int var66 = var31 + (var56 - 1) * var47;
                              int var70 = var40 + var61;
                              int var71 = var35 + (var56 - 1) * var51;
                              var13.set(var66, var70, var71);
                              if (var61 < 0 && !this.worldServerInstance.getBlockState(var13).getBlock().getMaterial().isSolid()
                                 || var61 >= 0 && !this.worldServerInstance.isAirBlock(var13)) {
                                 continue label231;
                              }
                           }
                        }

                        double var57 = var40 + 0.5 - var1.t;
                        double var67 = var33 * var33 + var57 * var57 + var37 * var37;
                        if (var3 < 0.0 || var67 < var3) {
                           var3 = var67;
                           var8 = var31;
                           var9 = var40;
                           var10 = var35;
                           var11 = var43 % 2;
                        }
                     }
                  }
               }
            }
         }
      }

      int var32 = var8;
      int var34 = var9;
      int var16 = var10;
      int var36 = var11 % 2;
      int var38 = 1 - var36;
      if (var11 % 4 >= 2) {
         var36 = -var36;
         var38 = -var38;
      }

      if (var3 < 0.0) {
         var9 = MathHelper.clamp_int(var9, 70, this.worldServerInstance.getActualHeight() - 10);
         var34 = var9;

         for (int var19 = -1; var19 <= 1; var19++) {
            for (int var41 = 1; var41 < 3; var41++) {
               for (int var44 = -1; var44 < 3; var44++) {
                  int var48 = var32 + (var41 - 1) * var36 + var19 * var38;
                  int var52 = var34 + var44;
                  int var58 = var16 + (var41 - 1) * var38 - var19 * var36;
                  boolean var62 = var44 < 0;
                  this.worldServerInstance
                     .setBlockState(new BlockPos(var48, var52, var58), var62 ? Blocks.obsidian.getDefaultState() : Blocks.air.getDefaultState());
               }
            }
         }
      }

      IBlockState var39 = Blocks.portal.getDefaultState().withProperty(BlockPortal.AXIS, var36 != 0 ? EnumFacing$Axis.X : EnumFacing$Axis.Z);

      for (int var42 = 0; var42 < 4; var42++) {
         for (int var45 = 0; var45 < 4; var45++) {
            for (int var49 = -1; var49 < 4; var49++) {
               int var53 = var32 + (var45 - 1) * var36;
               int var59 = var34 + var49;
               int var63 = var16 + (var45 - 1) * var38;
               boolean var68 = var45 == 0 || var45 == 3 || var49 == -1 || var49 == 3;
               this.worldServerInstance.a(new BlockPos(var53, var59, var63), var68 ? Blocks.obsidian.getDefaultState() : var39, 2);
            }
         }

         for (int var46 = 0; var46 < 4; var46++) {
            for (int var50 = -1; var50 < 4; var50++) {
               int var54 = var32 + (var46 - 1) * var36;
               int var60 = var34 + var50;
               int var64 = var16 + (var46 - 1) * var38;
               BlockPos var69 = new BlockPos(var54, var60, var64);
               this.worldServerInstance.notifyNeighborsOfStateChange(var69, this.worldServerInstance.getBlockState(var69).getBlock());
            }
         }
      }

      return true;
   }
}
