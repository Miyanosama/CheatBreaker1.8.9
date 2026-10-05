package net.minecraft.util;

public class AxisAlignedBB {
   public double b;
   public double c;
   public double a;
   public double d;
   public double f;
   public double e;

   @Override
   public String toString() {
      return "box[" + this.a + ", " + this.b + ", " + this.c + " -> " + this.d + ", " + this.e + ", " + this.f + "]";
   }

   public AxisAlignedBB addCoord(double var1, double var3, double var5) {
      double var7 = this.a;
      double var9 = this.b;
      double var11 = this.c;
      double var13 = this.d;
      double var15 = this.e;
      double var17 = this.f;
      if (var1 < 0.0) {
         var7 += var1;
      } else if (var1 > 0.0) {
         var13 += var1;
      }

      if (var3 < 0.0) {
         var9 += var3;
      } else if (var3 > 0.0) {
         var15 += var3;
      }

      if (var5 < 0.0) {
         var11 += var5;
      } else if (var5 > 0.0) {
         var17 += var5;
      }

      return new AxisAlignedBB(var7, var9, var11, var13, var15, var17);
   }

   public MovingObjectPosition calculateIntercept(Vec3 var1, Vec3 var2) {
      Vec3 var3 = var1.getIntermediateWithXValue(var2, this.a);
      Vec3 var4 = var1.getIntermediateWithXValue(var2, this.d);
      Vec3 var5 = var1.getIntermediateWithYValue(var2, this.b);
      Vec3 var6 = var1.getIntermediateWithYValue(var2, this.e);
      Vec3 var7 = var1.getIntermediateWithZValue(var2, this.c);
      Vec3 var8 = var1.getIntermediateWithZValue(var2, this.f);
      if (!this.isVecInYZ(var3)) {
         var3 = null;
      }

      if (!this.isVecInYZ(var4)) {
         var4 = null;
      }

      if (!this.isVecInXZ(var5)) {
         var5 = null;
      }

      if (!this.isVecInXZ(var6)) {
         var6 = null;
      }

      if (!this.isVecInXY(var7)) {
         var7 = null;
      }

      if (!this.isVecInXY(var8)) {
         var8 = null;
      }

      Vec3 var9 = null;
      if (var3 != null) {
         var9 = var3;
      }

      if (var4 != null && (var9 == null || var1.squareDistanceTo(var4) < var1.squareDistanceTo(var9))) {
         var9 = var4;
      }

      if (var5 != null && (var9 == null || var1.squareDistanceTo(var5) < var1.squareDistanceTo(var9))) {
         var9 = var5;
      }

      if (var6 != null && (var9 == null || var1.squareDistanceTo(var6) < var1.squareDistanceTo(var9))) {
         var9 = var6;
      }

      if (var7 != null && (var9 == null || var1.squareDistanceTo(var7) < var1.squareDistanceTo(var9))) {
         var9 = var7;
      }

      if (var8 != null && (var9 == null || var1.squareDistanceTo(var8) < var1.squareDistanceTo(var9))) {
         var9 = var8;
      }

      if (var9 == null) {
         return null;
      } else {
         Object var10 = null;
         if (var9 == var3) {
            var10 = EnumFacing.WEST;
         } else if (var9 == var4) {
            var10 = EnumFacing.EAST;
         } else if (var9 == var5) {
            var10 = EnumFacing.DOWN;
         } else if (var9 == var6) {
            var10 = EnumFacing.UP;
         } else if (var9 == var7) {
            var10 = EnumFacing.NORTH;
         } else {
            var10 = EnumFacing.SOUTH;
         }

         return new MovingObjectPosition(var9, (EnumFacing)var10);
      }
   }

   public AxisAlignedBB(double var1, double var3, double var5, double var7, double var9, double var11) {
      this.a = Math.min(var1, var7);
      this.b = Math.min(var3, var9);
      this.c = Math.min(var5, var11);
      this.d = Math.max(var1, var7);
      this.e = Math.max(var3, var9);
      this.f = Math.max(var5, var11);
   }

   public boolean isVecInside(Vec3 var1) {
      return !(var1.xCoord > this.a) || !(var1.xCoord < this.d)
         ? false
         : (var1.yCoord > this.b && var1.yCoord < this.e ? var1.zCoord > this.c && var1.zCoord < this.f : false);
   }

   public double method_09638(AxisAlignedBB var1, double var2) {
      if (var1.d > this.a && var1.a < this.d && var1.e > this.b && var1.b < this.e) {
         if (var2 > 0.0 && var1.f <= this.c) {
            double var6 = this.c - var1.f;
            if (var6 < var2) {
               var2 = var6;
            }
         } else if (var2 < 0.0 && var1.c >= this.f) {
            double var4 = this.f - var1.c;
            if (var4 > var2) {
               var2 = var4;
            }
         }

         return var2;
      } else {
         return var2;
      }
   }

   public AxisAlignedBB expand(double var1, double var3, double var5) {
      double var7 = this.a - var1;
      double var9 = this.b - var3;
      double var11 = this.c - var5;
      double var13 = this.d + var1;
      double var15 = this.e + var3;
      double var17 = this.f + var5;
      return new AxisAlignedBB(var7, var9, var11, var13, var15, var17);
   }

   public boolean isVecInYZ(Vec3 var1) {
      return var1 == null ? false : var1.yCoord >= this.b && var1.yCoord <= this.e && var1.zCoord >= this.c && var1.zCoord <= this.f;
   }

   public AxisAlignedBB contract(double var1, double var3, double var5) {
      double var7 = this.a + var1;
      double var9 = this.b + var3;
      double var11 = this.c + var5;
      double var13 = this.d - var1;
      double var15 = this.e - var3;
      double var17 = this.f - var5;
      return new AxisAlignedBB(var7, var9, var11, var13, var15, var17);
   }

   public boolean isVecInXZ(Vec3 var1) {
      return var1 == null ? false : var1.xCoord >= this.a && var1.xCoord <= this.d && var1.zCoord >= this.c && var1.zCoord <= this.f;
   }

   public double getAverageEdgeLength() {
      double var1 = this.d - this.a;
      double var3 = this.e - this.b;
      double var5 = this.f - this.c;
      return (var1 + var3 + var5) / 3.0;
   }

   public boolean isVecInXY(Vec3 var1) {
      return var1 == null ? false : var1.xCoord >= this.a && var1.xCoord <= this.d && var1.yCoord >= this.b && var1.yCoord <= this.e;
   }

   public double method_09646(AxisAlignedBB var1, double var2) {
      if (var1.d > this.a && var1.a < this.d && var1.f > this.c && var1.c < this.f) {
         if (var2 > 0.0 && var1.e <= this.b) {
            double var6 = this.b - var1.e;
            if (var6 < var2) {
               var2 = var6;
            }
         } else if (var2 < 0.0 && var1.b >= this.e) {
            double var4 = this.e - var1.b;
            if (var4 > var2) {
               var2 = var4;
            }
         }

         return var2;
      } else {
         return var2;
      }
   }

   public boolean hasNaN() {
      return Double.isNaN(this.a) || Double.isNaN(this.b) || Double.isNaN(this.c) || Double.isNaN(this.d) || Double.isNaN(this.e) || Double.isNaN(this.f);
   }

   public AxisAlignedBB offset(double var1, double var3, double var5) {
      return new AxisAlignedBB(this.a + var1, this.b + var3, this.c + var5, this.d + var1, this.e + var3, this.f + var5);
   }

   public double method_09632(AxisAlignedBB var1, double var2) {
      if (var1.e > this.b && var1.b < this.e && var1.f > this.c && var1.c < this.f) {
         if (var2 > 0.0 && var1.d <= this.a) {
            double var6 = this.a - var1.d;
            if (var6 < var2) {
               var2 = var6;
            }
         } else if (var2 < 0.0 && var1.a >= this.d) {
            double var4 = this.d - var1.a;
            if (var4 > var2) {
               var2 = var4;
            }
         }

         return var2;
      } else {
         return var2;
      }
   }

   public boolean intersectsWith(AxisAlignedBB var1) {
      return !(var1.d > this.a) || !(var1.a < this.d) ? false : (var1.e > this.b && var1.b < this.e ? var1.f > this.c && var1.c < this.f : false);
   }

   public static AxisAlignedBB fromBounds(double var0, double var2, double var4, double var6, double var8, double var10) {
      double var12 = Math.min(var0, var6);
      double var14 = Math.min(var2, var8);
      double var16 = Math.min(var4, var10);
      double var18 = Math.max(var0, var6);
      double var20 = Math.max(var2, var8);
      double var22 = Math.max(var4, var10);
      return new AxisAlignedBB(var12, var14, var16, var18, var20, var22);
   }

   public AxisAlignedBB union(AxisAlignedBB var1) {
      double var2 = Math.min(this.a, var1.a);
      double var4 = Math.min(this.b, var1.b);
      double var6 = Math.min(this.c, var1.c);
      double var8 = Math.max(this.d, var1.d);
      double var10 = Math.max(this.e, var1.e);
      double var12 = Math.max(this.f, var1.f);
      return new AxisAlignedBB(var2, var4, var6, var8, var10, var12);
   }

   public AxisAlignedBB(BlockPos var1, BlockPos var2) {
      this.a = var1.getX();
      this.b = var1.getY();
      this.c = var1.getZ();
      this.d = var2.getX();
      this.e = var2.getY();
      this.f = var2.getZ();
   }
}
