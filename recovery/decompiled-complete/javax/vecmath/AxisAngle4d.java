package javax.vecmath;

import java.io.Serializable;
import net.minecraft.command.PlayerSelector$9;
import net.minecraft.network.rcon.RConConsoleSource;
import net.optifine.expr.ExpressionParser$1;
import net.optifine.util.NumUtils;
import recovered.unidentified.UnidentifiedClass1276;

public class AxisAngle4d implements Serializable, Cloneable {
   public double angle;
   public double x;
   public double y;
   public PlayerSelector$9 field_0006;
   public static long field_0000;
   public ExpressionParser$1 field_0001;
   public static double field_0008;
   public RConConsoleSource field_0005;
   public NumUtils field_0002;
   public double z;

   public AxisAngle4d(AxisAngle4f var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
      this.angle = var1.angle;
   }

   public AxisAngle4d(Vector3d var1, double var2) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
      this.angle = var2;
   }

   public void set(double[] var1) {
      this.x = var1[0];
      this.y = var1[1];
      this.z = var1[2];
      this.angle = var1[3];
   }

   public boolean epsilonEquals(AxisAngle4d var1, double var2) {
      double var4 = this.x - var1.x;
      if ((var4 < 0.0 ? -var4 : var4) > var2) {
         return false;
      } else {
         var4 = this.y - var1.y;
         if ((var4 < 0.0 ? -var4 : var4) > var2) {
            return false;
         } else {
            var4 = this.z - var1.z;
            if ((var4 < 0.0 ? -var4 : var4) > var2) {
               return false;
            } else {
               var4 = this.angle - var1.angle;
               return !((var4 < 0.0 ? -var4 : var4) > var2);
            }
         }
      }
   }

   public double getX() {
      return this.x;
   }

   public void set(double var1, double var3, double var5, double var7) {
      this.x = var1;
      this.y = var3;
      this.z = var5;
      this.angle = var7;
   }

   public boolean equals(AxisAngle4d var1) {
      try {
         return this.x == var1.x && this.y == var1.y && this.z == var1.z && this.angle == var1.angle;
      } catch (NullPointerException var3) {
         return false;
      }
   }

   public void set(AxisAngle4d var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
      this.angle = var1.angle;
   }

   public AxisAngle4d(double[] var1) {
      this.x = var1[0];
      this.y = var1[1];
      this.z = var1[2];
      this.angle = var1[3];
   }

   public void set(Quat4d var1) {
      double var2 = var1.x * var1.x + var1.y * var1.y + var1.z * var1.z;
      if (var2 > 1.0E-12) {
         var2 = Math.sqrt(var2);
         double var4 = 1.0 / var2;
         this.x = var1.x * var4;
         this.y = var1.y * var4;
         this.z = var1.z * var4;
         this.angle = 2.0 * Math.atan2(var2, var1.w);
      } else {
         this.x = 0.0;
         this.y = 1.0;
         this.z = 0.0;
         this.angle = 0.0;
      }
   }

   public void set(AxisAngle4f var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
      this.angle = var1.angle;
   }

   public AxisAngle4d(AxisAngle4d var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
      this.angle = var1.angle;
   }

   public void set(Matrix4d var1) {
      Matrix3d var2 = new Matrix3d();
      var1.get(var2);
      this.x = (float)(var2.m21 - var2.m12);
      this.y = (float)(var2.m02 - var2.m20);
      this.z = (float)(var2.m10 - var2.m01);
      double var3 = this.x * this.x + this.y * this.y + this.z * this.z;
      if (var3 > 1.0E-12) {
         var3 = Math.sqrt(var3);
         double var5 = 0.5 * var3;
         double var7 = 0.5 * (var2.m00 + var2.m11 + var2.m22 - 1.0);
         this.angle = (float)Math.atan2(var5, var7);
         double var9 = 1.0 / var3;
         this.x *= var9;
         this.y *= var9;
         this.z *= var9;
      } else {
         this.x = 0.0;
         this.y = 1.0;
         this.z = 0.0;
         this.angle = 0.0;
      }
   }

   @Override
   public int hashCode() {
      long var1 = 507742241L & 546066635L;
      var1 = (-5652204942945142209L & 5652204941718061087L) * var1 + UnidentifiedClass1276.method_08549(this.x);
      var1 = (1398931679L & -7088808552288969185L) * var1 + UnidentifiedClass1276.method_08549(this.y);
      var1 = (6143455432448151583L & -6143455433558129089L) * var1 + UnidentifiedClass1276.method_08549(this.z);
      var1 = (-1370974804785475489L & 270794911L) * var1 + UnidentifiedClass1276.method_08549(this.angle);
      return (int)(var1 ^ var1 >> 32);
   }

   @Override
   public String toString() {
      return "(" + this.x + ", " + this.y + ", " + this.z + ", " + this.angle + ")";
   }

   public AxisAngle4d(double var1, double var3, double var5, double var7) {
      this.x = var1;
      this.y = var3;
      this.z = var5;
      this.angle = var7;
   }

   public void setX(double var1) {
      this.x = var1;
   }

   public double getZ() {
      return this.z;
   }

   public void set(Matrix3d var1) {
      this.x = (float)(var1.m21 - var1.m12);
      this.y = (float)(var1.m02 - var1.m20);
      this.z = (float)(var1.m10 - var1.m01);
      double var2 = this.x * this.x + this.y * this.y + this.z * this.z;
      if (var2 > 1.0E-12) {
         var2 = Math.sqrt(var2);
         double var4 = 0.5 * var2;
         double var6 = 0.5 * (var1.m00 + var1.m11 + var1.m22 - 1.0);
         this.angle = (float)Math.atan2(var4, var6);
         double var8 = 1.0 / var2;
         this.x *= var8;
         this.y *= var8;
         this.z *= var8;
      } else {
         this.x = 0.0;
         this.y = 1.0;
         this.z = 0.0;
         this.angle = 0.0;
      }
   }

   public double getAngle() {
      return this.angle;
   }

   public void set(Vector3d var1, double var2) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
      this.angle = var2;
   }

   @Override
   public Object clone() {
      try {
         return super.clone();
      } catch (CloneNotSupportedException var2) {
         throw new InternalError();
      }
   }

   public void setAngle(double var1) {
      this.angle = var1;
   }

   @Override
   public boolean equals(Object var1) {
      try {
         AxisAngle4d var2 = (AxisAngle4d)var1;
         return this.x == var2.x && this.y == var2.y && this.z == var2.z && this.angle == var2.angle;
      } catch (NullPointerException var3) {
         return false;
      } catch (ClassCastException var4) {
         return false;
      }
   }

   public void get(double[] var1) {
      var1[0] = this.x;
      var1[1] = this.y;
      var1[2] = this.z;
      var1[3] = this.angle;
   }

   public void set(Matrix3f var1) {
      this.x = var1.m21 - var1.m12;
      this.y = var1.m02 - var1.m20;
      this.z = var1.m10 - var1.m01;
      double var2 = this.x * this.x + this.y * this.y + this.z * this.z;
      if (var2 > 1.0E-12) {
         var2 = Math.sqrt(var2);
         double var4 = 0.5 * var2;
         double var6 = 0.5 * (var1.m00 + var1.m11 + var1.m22 - 1.0);
         this.angle = (float)Math.atan2(var4, var6);
         double var8 = 1.0 / var2;
         this.x *= var8;
         this.y *= var8;
         this.z *= var8;
      } else {
         this.x = 0.0;
         this.y = 1.0;
         this.z = 0.0;
         this.angle = 0.0;
      }
   }

   public void setZ(double var1) {
      this.z = var1;
   }

   public void set(Matrix4f var1) {
      Matrix3d var2 = new Matrix3d();
      var1.get(var2);
      this.x = (float)(var2.m21 - var2.m12);
      this.y = (float)(var2.m02 - var2.m20);
      this.z = (float)(var2.m10 - var2.m01);
      double var3 = this.x * this.x + this.y * this.y + this.z * this.z;
      if (var3 > 1.0E-12) {
         var3 = Math.sqrt(var3);
         double var5 = 0.5 * var3;
         double var7 = 0.5 * (var2.m00 + var2.m11 + var2.m22 - 1.0);
         this.angle = (float)Math.atan2(var5, var7);
         double var9 = 1.0 / var3;
         this.x *= var9;
         this.y *= var9;
         this.z *= var9;
      } else {
         this.x = 0.0;
         this.y = 1.0;
         this.z = 0.0;
         this.angle = 0.0;
      }
   }

   public double getY() {
      return this.y;
   }

   public AxisAngle4d() {
      this.x = 0.0;
      this.y = 0.0;
      this.z = 1.0;
      this.angle = 0.0;
   }

   public void setY(double var1) {
      this.y = var1;
   }

   public void set(Quat4f var1) {
      double var2 = var1.x * var1.x + var1.y * var1.y + var1.z * var1.z;
      if (var2 > 1.0E-12) {
         var2 = Math.sqrt(var2);
         double var4 = 1.0 / var2;
         this.x = var1.x * var4;
         this.y = var1.y * var4;
         this.z = var1.z * var4;
         this.angle = 2.0 * Math.atan2(var2, var1.w);
      } else {
         this.x = 0.0;
         this.y = 1.0;
         this.z = 0.0;
         this.angle = 0.0;
      }
   }
}
