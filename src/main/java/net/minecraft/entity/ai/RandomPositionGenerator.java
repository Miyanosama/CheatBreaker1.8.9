package net.minecraft.entity.ai;

import java.util.Random;
import net.minecraft.entity.EntityCreature;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;

public class RandomPositionGenerator {
   public static Vec3 staticVector = new Vec3(0.0, 0.0, 0.0);

   public static Vec3 findRandomTarget(EntityCreature var0, int var1, int var2) {
      return findRandomTargetBlock(var0, var1, var2, (Vec3)null);
   }

   public static Vec3 findRandomTargetBlockAwayFrom(EntityCreature var0, int var1, int var2, Vec3 var3) {
      staticVector = new Vec3(var0.s, var0.t, var0.u).subtract(var3);
      return findRandomTargetBlock(var0, var1, var2, staticVector);
   }

   public static Vec3 findRandomTargetBlockTowards(EntityCreature var0, int var1, int var2, Vec3 var3) {
      staticVector = var3.subtract(var0.s, var0.t, var0.u);
      return findRandomTargetBlock(var0, var1, var2, staticVector);
   }

   public static Vec3 findRandomTargetBlock(EntityCreature var0, int var1, int var2, Vec3 var3) {
      Random var4 = var0.getRNG();
      boolean var5 = false;
      int var6 = 0;
      int var7 = 0;
      int var8 = 0;
      float var9 = -99999.0F;
      boolean var10;
      if (var0.hasHome()) {
         double var11 = var0.getHomePosition().distanceSq(MathHelper.floor_double(var0.s), MathHelper.floor_double(var0.t), MathHelper.floor_double(var0.u))
            + 4.0;
         double var13 = var0.getMaximumHomeDistance() + var1;
         var10 = var11 < var13 * var13;
      } else {
         var10 = false;
      }

      for (int var17 = 0; var17 < 10; var17++) {
         int var12 = var4.nextInt(2 * var1 + 1) - var1;
         int var19 = var4.nextInt(2 * var2 + 1) - var2;
         int var14 = var4.nextInt(2 * var1 + 1) - var1;
         if (var3 == null || var12 * var3.xCoord + var14 * var3.zCoord >= 0.0) {
            if (var0.hasHome() && var1 > 1) {
               BlockPos var15 = var0.getHomePosition();
               if (var0.s > var15.getX()) {
                  var12 -= var4.nextInt(var1 / 2);
               } else {
                  var12 += var4.nextInt(var1 / 2);
               }

               if (var0.u > var15.getZ()) {
                  var14 -= var4.nextInt(var1 / 2);
               } else {
                  var14 += var4.nextInt(var1 / 2);
               }
            }

            var12 += MathHelper.floor_double(var0.s);
            var19 += MathHelper.floor_double(var0.t);
            var14 += MathHelper.floor_double(var0.u);
            BlockPos var22 = new BlockPos(var12, var19, var14);
            if (!var10 || var0.isWithinHomeDistanceFromPosition(var22)) {
               float var16 = var0.getBlockPathWeight(var22);
               if (var16 > var9) {
                  var9 = var16;
                  var6 = var12;
                  var7 = var19;
                  var8 = var14;
                  var5 = true;
               }
            }
         }
      }

      return var5 ? new Vec3(var6, var7, var8) : null;
   }
}
