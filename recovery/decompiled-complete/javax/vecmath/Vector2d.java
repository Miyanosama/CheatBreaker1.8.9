package javax.vecmath;

import com.cheatbreaker.client.util.dash.DashHook;
import io.netty.handler.traffic.AbstractTrafficShapingHandler;
import java.io.Serializable;
import javazoom.jl.decoder.LayerIIDecoder$SubbandLayer2Stereo;
import net.minecraft.entity.ai.EntityAIVillagerMate;
import net.minecraft.world.storage.WorldInfo;
import net.optifine.shaders.uniform.ShaderParameterFloat$1;

public class Vector2d extends Tuple2d implements Serializable {
   public WorldInfo field_0003;
   public ShaderParameterFloat$1 field_0006;
   public static long field_0002;
   public EntityAIVillagerMate field_0005;
   public DashHook field_0000;
   public LayerIIDecoder$SubbandLayer2Stereo field_0001;
   public AbstractTrafficShapingHandler field_0004;

   public double dot(Vector2d var1) {
      return this.x * var1.x + this.y * var1.y;
   }

   public Vector2d(double var1, double var3) {
      super(var1, var3);
   }

   public Vector2d(Tuple2d var1) {
      super(var1);
   }

   public Vector2d(Vector2f var1) {
      super(var1);
   }

   public double angle(Vector2d var1) {
      double var2 = this.dot(var1) / (this.length() * var1.length());
      if (var2 < -1.0) {
         var2 = -1.0;
      }

      if (var2 > 1.0) {
         var2 = 1.0;
      }

      return Math.acos(var2);
   }

   public void normalize(Vector2d var1) {
      double var2 = 1.0 / Math.sqrt(var1.x * var1.x + var1.y * var1.y);
      this.x = var1.x * var2;
      this.y = var1.y * var2;
   }

   public Vector2d(Tuple2f var1) {
      super(var1);
   }

   public Vector2d(Vector2d var1) {
      super(var1);
   }

   public void normalize() {
      double var1 = 1.0 / Math.sqrt(this.x * this.x + this.y * this.y);
      this.x *= var1;
      this.y *= var1;
   }

   public Vector2d(double[] var1) {
      super(var1);
   }

   public Vector2d() {
   }

   public double lengthSquared() {
      return this.x * this.x + this.y * this.y;
   }

   public double length() {
      return Math.sqrt(this.x * this.x + this.y * this.y);
   }
}
