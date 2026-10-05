package javax.vecmath;

import com.cheatbreaker.client.nethandler.client.PacketClientVoice;
import io.netty.channel.ChannelPipelineException;
import java.io.Serializable;

public class Quat4f extends Tuple4f implements Serializable {
   public static double field_0003;
   public PacketClientVoice field_0005;
   public static double field_0002;
   public ChannelPipelineException field_0004;
   public static long field_0000;
   public static double field_0001;

   public void mul(Quat4f var1, Quat4f var2) {
      if (this != var1 && this != var2) {
         this.w = var1.w * var2.w - var1.x * var2.x - var1.y * var2.y - var1.z * var2.z;
         this.x = var1.w * var2.x + var2.w * var1.x + var1.y * var2.z - var1.z * var2.y;
         this.y = var1.w * var2.y + var2.w * var1.y - var1.x * var2.z + var1.z * var2.x;
         this.z = var1.w * var2.z + var2.w * var1.z + var1.x * var2.y - var1.y * var2.x;
      } else {
         float var5 = var1.w * var2.w - var1.x * var2.x - var1.y * var2.y - var1.z * var2.z;
         float var3 = var1.w * var2.x + var2.w * var1.x + var1.y * var2.z - var1.z * var2.y;
         float var4 = var1.w * var2.y + var2.w * var1.y - var1.x * var2.z + var1.z * var2.x;
         this.z = var1.w * var2.z + var2.w * var1.z + var1.x * var2.y - var1.y * var2.x;
         this.w = var5;
         this.x = var3;
         this.y = var4;
      }
   }

   public void mulInverse(Quat4f var1, Quat4f var2) {
      Quat4f var3 = new Quat4f(var2);
      var3.inverse();
      this.mul(var1, var3);
   }

   public Quat4f(float var1, float var2, float var3, float var4) {
      float var5 = (float)(1.0 / Math.sqrt(var1 * var1 + var2 * var2 + var3 * var3 + var4 * var4));
      this.x = var1 * var5;
      this.y = var2 * var5;
      this.z = var3 * var5;
      this.w = var4 * var5;
   }

   public void interpolate(Quat4f var1, float var2) {
      double var3 = this.x * var1.x + this.y * var1.y + this.z * var1.z + this.w * var1.w;
      if (var3 < 0.0) {
         var1.x = -var1.x;
         var1.y = -var1.y;
         var1.z = -var1.z;
         var1.w = -var1.w;
         var3 = -var3;
      }

      double var5;
      double var7;
      if (1.0 - var3 > 1.0E-6) {
         double var9 = Math.acos(var3);
         double var11 = Math.sin(var9);
         var5 = Math.sin((1.0 - var2) * var9) / var11;
         var7 = Math.sin(var2 * var9) / var11;
      } else {
         var5 = 1.0 - var2;
         var7 = var2;
      }

      this.w = (float)(var5 * this.w + var7 * var1.w);
      this.x = (float)(var5 * this.x + var7 * var1.x);
      this.y = (float)(var5 * this.y + var7 * var1.y);
      this.z = (float)(var5 * this.z + var7 * var1.z);
   }

   public void interpolate(Quat4f var1, Quat4f var2, float var3) {
      double var4 = var2.x * var1.x + var2.y * var1.y + var2.z * var1.z + var2.w * var1.w;
      if (var4 < 0.0) {
         var1.x = -var1.x;
         var1.y = -var1.y;
         var1.z = -var1.z;
         var1.w = -var1.w;
         var4 = -var4;
      }

      double var6;
      double var8;
      if (1.0 - var4 > 1.0E-6) {
         double var10 = Math.acos(var4);
         double var12 = Math.sin(var10);
         var6 = Math.sin((1.0 - var3) * var10) / var12;
         var8 = Math.sin(var3 * var10) / var12;
      } else {
         var6 = 1.0 - var3;
         var8 = var3;
      }

      this.w = (float)(var6 * var1.w + var8 * var2.w);
      this.x = (float)(var6 * var1.x + var8 * var2.x);
      this.y = (float)(var6 * var1.y + var8 * var2.y);
      this.z = (float)(var6 * var1.z + var8 * var2.z);
   }

   public void inverse(Quat4f var1) {
      float var2 = 1.0F / (var1.w * var1.w + var1.x * var1.x + var1.y * var1.y + var1.z * var1.z);
      this.w = var2 * var1.w;
      this.x = -var2 * var1.x;
      this.y = -var2 * var1.y;
      this.z = -var2 * var1.z;
   }

   public void set(Matrix3f var1) {
      float var2 = 0.25F * (var1.m00 + var1.m11 + var1.m22 + 1.0F);
      if (var2 >= 0.0F) {
         if (var2 >= 1.0E-30) {
            this.w = (float)Math.sqrt(var2);
            var2 = 0.25F / this.w;
            this.x = (var1.m21 - var1.m12) * var2;
            this.y = (var1.m02 - var1.m20) * var2;
            this.z = (var1.m10 - var1.m01) * var2;
         } else {
            this.w = 0.0F;
            var2 = -0.5F * (var1.m11 + var1.m22);
            if (var2 >= 0.0F) {
               if (var2 >= 1.0E-30) {
                  this.x = (float)Math.sqrt(var2);
                  var2 = 0.5F / this.x;
                  this.y = var1.m10 * var2;
                  this.z = var1.m20 * var2;
               } else {
                  this.x = 0.0F;
                  var2 = 0.5F * (1.0F - var1.m22);
                  if (var2 >= 1.0E-30) {
                     this.y = (float)Math.sqrt(var2);
                     this.z = var1.m21 / (2.0F * this.y);
                  } else {
                     this.y = 0.0F;
                     this.z = 1.0F;
                  }
               }
            } else {
               this.x = 0.0F;
               this.y = 0.0F;
               this.z = 1.0F;
            }
         }
      } else {
         this.w = 0.0F;
         this.x = 0.0F;
         this.y = 0.0F;
         this.z = 1.0F;
      }
   }

   public void set(Matrix4d var1) {
      double var2 = 0.25 * (var1.m00 + var1.m11 + var1.m22 + var1.m33);
      if (var2 >= 0.0) {
         if (var2 >= 1.0E-30) {
            this.w = (float)Math.sqrt(var2);
            var2 = 0.25 / this.w;
            this.x = (float)((var1.m21 - var1.m12) * var2);
            this.y = (float)((var1.m02 - var1.m20) * var2);
            this.z = (float)((var1.m10 - var1.m01) * var2);
         } else {
            this.w = 0.0F;
            var2 = -0.5 * (var1.m11 + var1.m22);
            if (var2 >= 0.0) {
               if (var2 >= 1.0E-30) {
                  this.x = (float)Math.sqrt(var2);
                  var2 = 0.5 / this.x;
                  this.y = (float)(var1.m10 * var2);
                  this.z = (float)(var1.m20 * var2);
               } else {
                  this.x = 0.0F;
                  var2 = 0.5 * (1.0 - var1.m22);
                  if (var2 >= 1.0E-30) {
                     this.y = (float)Math.sqrt(var2);
                     this.z = (float)(var1.m21 / (2.0 * this.y));
                  } else {
                     this.y = 0.0F;
                     this.z = 1.0F;
                  }
               }
            } else {
               this.x = 0.0F;
               this.y = 0.0F;
               this.z = 1.0F;
            }
         }
      } else {
         this.w = 0.0F;
         this.x = 0.0F;
         this.y = 0.0F;
         this.z = 1.0F;
      }
   }

   public void set(AxisAngle4f var1) {
      float var3 = (float)Math.sqrt(var1.x * var1.x + var1.y * var1.y + var1.z * var1.z);
      if (var3 < 1.0E-6) {
         this.w = 0.0F;
         this.x = 0.0F;
         this.y = 0.0F;
         this.z = 0.0F;
      } else {
         var3 = 1.0F / var3;
         float var2 = (float)Math.sin(var1.angle / 2.0);
         this.w = (float)Math.cos(var1.angle / 2.0);
         this.x = var1.x * var3 * var2;
         this.y = var1.y * var3 * var2;
         this.z = var1.z * var3 * var2;
      }
   }

   public void normalize() {
      float var1 = this.x * this.x + this.y * this.y + this.z * this.z + this.w * this.w;
      if (var1 > 0.0F) {
         var1 = 1.0F / (float)Math.sqrt(var1);
         this.x *= var1;
         this.y *= var1;
         this.z *= var1;
         this.w *= var1;
      } else {
         this.x = 0.0F;
         this.y = 0.0F;
         this.z = 0.0F;
         this.w = 0.0F;
      }
   }

   public void conjugate(Quat4f var1) {
      this.x = -var1.x;
      this.y = -var1.y;
      this.z = -var1.z;
      this.w = var1.w;
   }

   public Quat4f(Quat4d var1) {
      super(var1);
   }

   public Quat4f(float[] var1) {
      float var2 = (float)(1.0 / Math.sqrt(var1[0] * var1[0] + var1[1] * var1[1] + var1[2] * var1[2] + var1[3] * var1[3]));
      this.x = var1[0] * var2;
      this.y = var1[1] * var2;
      this.z = var1[2] * var2;
      this.w = var1[3] * var2;
   }

   public void set(Matrix3d var1) {
      double var2 = 0.25 * (var1.m00 + var1.m11 + var1.m22 + 1.0);
      if (var2 >= 0.0) {
         if (var2 >= 1.0E-30) {
            this.w = (float)Math.sqrt(var2);
            var2 = 0.25 / this.w;
            this.x = (float)((var1.m21 - var1.m12) * var2);
            this.y = (float)((var1.m02 - var1.m20) * var2);
            this.z = (float)((var1.m10 - var1.m01) * var2);
         } else {
            this.w = 0.0F;
            var2 = -0.5 * (var1.m11 + var1.m22);
            if (var2 >= 0.0) {
               if (var2 >= 1.0E-30) {
                  this.x = (float)Math.sqrt(var2);
                  var2 = 0.5 / this.x;
                  this.y = (float)(var1.m10 * var2);
                  this.z = (float)(var1.m20 * var2);
               } else {
                  this.x = 0.0F;
                  var2 = 0.5 * (1.0 - var1.m22);
                  if (var2 >= 1.0E-30) {
                     this.y = (float)Math.sqrt(var2);
                     this.z = (float)(var1.m21 / (2.0 * this.y));
                  } else {
                     this.y = 0.0F;
                     this.z = 1.0F;
                  }
               }
            } else {
               this.x = 0.0F;
               this.y = 0.0F;
               this.z = 1.0F;
            }
         }
      } else {
         this.w = 0.0F;
         this.x = 0.0F;
         this.y = 0.0F;
         this.z = 1.0F;
      }
   }

   public void conjugate() {
      this.x = -this.x;
      this.y = -this.y;
      this.z = -this.z;
   }

   public void mul(Quat4f var1) {
      float var4 = this.w * var1.w - this.x * var1.x - this.y * var1.y - this.z * var1.z;
      float var2 = this.w * var1.x + var1.w * this.x + this.y * var1.z - this.z * var1.y;
      float var3 = this.w * var1.y + var1.w * this.y - this.x * var1.z + this.z * var1.x;
      this.z = this.w * var1.z + var1.w * this.z + this.x * var1.y - this.y * var1.x;
      this.w = var4;
      this.x = var2;
      this.y = var3;
   }

   public Quat4f() {
   }

   public void normalize(Quat4f var1) {
      float var2 = var1.x * var1.x + var1.y * var1.y + var1.z * var1.z + var1.w * var1.w;
      if (var2 > 0.0F) {
         var2 = 1.0F / (float)Math.sqrt(var2);
         this.x = var2 * var1.x;
         this.y = var2 * var1.y;
         this.z = var2 * var1.z;
         this.w = var2 * var1.w;
      } else {
         this.x = 0.0F;
         this.y = 0.0F;
         this.z = 0.0F;
         this.w = 0.0F;
      }
   }

   public Quat4f(Quat4f var1) {
      super(var1);
   }

   public void inverse() {
      float var1 = 1.0F / (this.w * this.w + this.x * this.x + this.y * this.y + this.z * this.z);
      this.w *= var1;
      this.x *= -var1;
      this.y *= -var1;
      this.z *= -var1;
   }

   public Quat4f(Tuple4f var1) {
      float var2 = (float)(1.0 / Math.sqrt(var1.x * var1.x + var1.y * var1.y + var1.z * var1.z + var1.w * var1.w));
      this.x = var1.x * var2;
      this.y = var1.y * var2;
      this.z = var1.z * var2;
      this.w = var1.w * var2;
   }

   public Quat4f(Tuple4d var1) {
      double var2 = 1.0 / Math.sqrt(var1.x * var1.x + var1.y * var1.y + var1.z * var1.z + var1.w * var1.w);
      this.x = (float)(var1.x * var2);
      this.y = (float)(var1.y * var2);
      this.z = (float)(var1.z * var2);
      this.w = (float)(var1.w * var2);
   }

   public void mulInverse(Quat4f var1) {
      Quat4f var2 = new Quat4f(var1);
      var2.inverse();
      this.mul(var2);
   }

   public void set(Matrix4f var1) {
      float var2 = 0.25F * (var1.m00 + var1.m11 + var1.m22 + var1.m33);
      if (var2 >= 0.0F) {
         if (var2 >= 1.0E-30) {
            this.w = (float)Math.sqrt(var2);
            var2 = 0.25F / this.w;
            this.x = (var1.m21 - var1.m12) * var2;
            this.y = (var1.m02 - var1.m20) * var2;
            this.z = (var1.m10 - var1.m01) * var2;
         } else {
            this.w = 0.0F;
            var2 = -0.5F * (var1.m11 + var1.m22);
            if (var2 >= 0.0F) {
               if (var2 >= 1.0E-30) {
                  this.x = (float)Math.sqrt(var2);
                  var2 = 1.0F / (2.0F * this.x);
                  this.y = var1.m10 * var2;
                  this.z = var1.m20 * var2;
               } else {
                  this.x = 0.0F;
                  var2 = 0.5F * (1.0F - var1.m22);
                  if (var2 >= 1.0E-30) {
                     this.y = (float)Math.sqrt(var2);
                     this.z = var1.m21 / (2.0F * this.y);
                  } else {
                     this.y = 0.0F;
                     this.z = 1.0F;
                  }
               }
            } else {
               this.x = 0.0F;
               this.y = 0.0F;
               this.z = 1.0F;
            }
         }
      } else {
         this.w = 0.0F;
         this.x = 0.0F;
         this.y = 0.0F;
         this.z = 1.0F;
      }
   }

   public void set(AxisAngle4d var1) {
      float var3 = (float)(1.0 / Math.sqrt(var1.x * var1.x + var1.y * var1.y + var1.z * var1.z));
      if (var3 < 1.0E-6) {
         this.w = 0.0F;
         this.x = 0.0F;
         this.y = 0.0F;
         this.z = 0.0F;
      } else {
         var3 = 1.0F / var3;
         float var2 = (float)Math.sin(var1.angle / 2.0);
         this.w = (float)Math.cos(var1.angle / 2.0);
         this.x = (float)var1.x * var3 * var2;
         this.y = (float)var1.y * var3 * var2;
         this.z = (float)var1.z * var3 * var2;
      }
   }
}
