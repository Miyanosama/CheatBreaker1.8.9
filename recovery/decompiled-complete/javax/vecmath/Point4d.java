package javax.vecmath;

import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.rtsp.RtspObjectEncoder;
import java.io.Serializable;
import net.minecraft.entity.monster.EntityEnderman$AIFindPlayer;
import net.minecraft.item.Item$13;
import net.minecraft.network.play.client.C07PacketPlayerDigging;

public class Point4d extends Tuple4d implements Serializable {
   public RtspObjectEncoder field_0003;
   public WebSocketFrame field_0005;
   public C07PacketPlayerDigging field_0002;
   public EntityEnderman$AIFindPlayer field_0004;
   public Item$13 field_0000;
   public static long field_0001;

   public Point4d(Point4f var1) {
      super(var1);
   }

   public void set(Tuple3d var1) {
      this.x = var1.x;
      this.y = var1.y;
      this.z = var1.z;
      this.w = 1.0;
   }

   public Point4d(Tuple4f var1) {
      super(var1);
   }

   public void project(Point4d var1) {
      double var2 = 1.0 / var1.w;
      this.x = var1.x * var2;
      this.y = var1.y * var2;
      this.z = var1.z * var2;
      this.w = 1.0;
   }

   public Point4d(double var1, double var3, double var5, double var7) {
      super(var1, var3, var5, var7);
   }

   public Point4d() {
   }

   public Point4d(Point4d var1) {
      super(var1);
   }

   public Point4d(double[] var1) {
      super(var1);
   }

   public Point4d(Tuple4d var1) {
      super(var1);
   }

   public Point4d(Tuple3d var1) {
      super(var1.x, var1.y, var1.z, 1.0);
   }

   public double distanceL1(Point4d var1) {
      return Math.abs(this.x - var1.x) + Math.abs(this.y - var1.y) + Math.abs(this.z - var1.z) + Math.abs(this.w - var1.w);
   }

   public double distanceSquared(Point4d var1) {
      double var2 = this.x - var1.x;
      double var4 = this.y - var1.y;
      double var6 = this.z - var1.z;
      double var8 = this.w - var1.w;
      return var2 * var2 + var4 * var4 + var6 * var6 + var8 * var8;
   }

   public double distance(Point4d var1) {
      double var2 = this.x - var1.x;
      double var4 = this.y - var1.y;
      double var6 = this.z - var1.z;
      double var8 = this.w - var1.w;
      return Math.sqrt(var2 * var2 + var4 * var4 + var6 * var6 + var8 * var8);
   }

   public double distanceLinf(Point4d var1) {
      double var2 = Math.max(Math.abs(this.x - var1.x), Math.abs(this.y - var1.y));
      double var4 = Math.max(Math.abs(this.z - var1.z), Math.abs(this.w - var1.w));
      return Math.max(var2, var4);
   }
}
