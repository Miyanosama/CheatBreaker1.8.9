package javax.vecmath;

import io.netty.channel.socket.oio.DefaultOioSocketChannelConfig;
import io.netty.handler.codec.http.CookieEncoderUtil;
import java.io.Serializable;
import net.minecraft.world.Explosion;

public class Vector3f extends Tuple3f implements Serializable {
   public CookieEncoderUtil field_0001;
   public Explosion field_0003;
   public static long field_0000;
   public DefaultOioSocketChannelConfig field_0002;

   public float length() {
      return (float)Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
   }

   public Vector3f(Vector3f var1) {
      super(var1);
   }

   public Vector3f() {
   }

   public void cross(Vector3f var1, Vector3f var2) {
      float var3 = var1.y * var2.z - var1.z * var2.y;
      float var4 = var2.x * var1.z - var2.z * var1.x;
      this.z = var1.x * var2.y - var1.y * var2.x;
      this.x = var3;
      this.y = var4;
   }

   public float lengthSquared() {
      return this.x * this.x + this.y * this.y + this.z * this.z;
   }

   public Vector3f(float var1, float var2, float var3) {
      super(var1, var2, var3);
   }

   public Vector3f(Tuple3f var1) {
      super(var1);
   }

   public Vector3f(float[] var1) {
      super(var1);
   }

   public float dot(Vector3f var1) {
      return this.x * var1.x + this.y * var1.y + this.z * var1.z;
   }

   public Vector3f(Vector3d var1) {
      super(var1);
   }

   public void normalize() {
      float var1 = (float)(1.0 / Math.sqrt(this.x * this.x + this.y * this.y + this.z * this.z));
      this.x *= var1;
      this.y *= var1;
      this.z *= var1;
   }

   public Vector3f(Tuple3d var1) {
      super(var1);
   }

   public void normalize(Vector3f var1) {
      float var2 = (float)(1.0 / Math.sqrt(var1.x * var1.x + var1.y * var1.y + var1.z * var1.z));
      this.x = var1.x * var2;
      this.y = var1.y * var2;
      this.z = var1.z * var2;
   }

   public float angle(Vector3f var1) {
      double var2 = this.dot(var1) / (this.length() * var1.length());
      if (var2 < -1.0) {
         var2 = -1.0;
      }

      if (var2 > 1.0) {
         var2 = 1.0;
      }

      return (float)Math.acos(var2);
   }
}
