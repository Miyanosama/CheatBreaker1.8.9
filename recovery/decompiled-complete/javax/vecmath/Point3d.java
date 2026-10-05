package javax.vecmath;

import io.netty.buffer.PoolArena;
import io.netty.handler.codec.socks.SocksCmdRequestDecoder$1;
import java.io.Serializable;
import net.minecraft.command.PlayerSelector$11;
import recovered.unidentified.UnidentifiedClass0173;

public class Point3d extends Tuple3d implements Serializable {
   public UnidentifiedClass0173 field_0002;
   public SocksCmdRequestDecoder$1 field_0004;
   public PlayerSelector$11 field_0001;
   public static long field_0003;
   public PoolArena field_0000;

   public Point3d(Point3d var1) {
      super(var1);
   }

   public Point3d() {
   }

   public double distanceL1(Point3d var1) {
      return Math.abs(this.x - var1.x) + Math.abs(this.y - var1.y) + Math.abs(this.z - var1.z);
   }

   public double distanceSquared(Point3d var1) {
      double var2 = this.x - var1.x;
      double var4 = this.y - var1.y;
      double var6 = this.z - var1.z;
      return var2 * var2 + var4 * var4 + var6 * var6;
   }

   public Point3d(Tuple3f var1) {
      super(var1);
   }

   public double distance(Point3d var1) {
      double var2 = this.x - var1.x;
      double var4 = this.y - var1.y;
      double var6 = this.z - var1.z;
      return Math.sqrt(var2 * var2 + var4 * var4 + var6 * var6);
   }

   public Point3d(Point3f var1) {
      super(var1);
   }

   public Point3d(double[] var1) {
      super(var1);
   }

   public Point3d(double var1, double var3, double var5) {
      super(var1, var3, var5);
   }

   public double distanceLinf(Point3d var1) {
      double var2 = Math.max(Math.abs(this.x - var1.x), Math.abs(this.y - var1.y));
      return Math.max(var2, Math.abs(this.z - var1.z));
   }

   public Point3d(Tuple3d var1) {
      super(var1);
   }

   public void project(Point4d var1) {
      double var2 = 1.0 / var1.w;
      this.x = var1.x * var2;
      this.y = var1.y * var2;
      this.z = var1.z * var2;
   }
}
