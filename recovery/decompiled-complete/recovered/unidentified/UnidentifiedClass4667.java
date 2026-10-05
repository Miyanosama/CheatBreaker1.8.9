package recovered.unidentified;

import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.util.concurrent.DefaultPromise$CauseHolder;
import net.minecraft.pathfinding.PathPoint;
import org.java_websocket.server.WebSocketServer$WebSocketWorker;

public class UnidentifiedClass4667 {
   public DefaultPromise$CauseHolder field_0003;
   public WebSocketServer$WebSocketWorker field_0005;
   public float field_0002;
   public ProtobufVarint32LengthFieldPrepender field_0004;
   public float field_0000;
   public PathPoint field_0001;

   public void method_28167(float var1, float var2) {
      this.field_0000 = var1;
      this.field_0002 = var2;
   }

   public static float method_28168(float var0, float var1, float var2, float var3) {
      var0 -= var2;
      var1 -= var3;
      return var0 * var0 + var1 * var1;
   }

   @Override
   public boolean equals(Object var1) {
      if (var1 == this) {
         return true;
      } else if (!(var1 instanceof UnidentifiedClass4667)) {
         return false;
      } else {
         UnidentifiedClass4667 var2 = (UnidentifiedClass4667)var1;
         return this.field_0000 == var2.field_0000 && this.field_0002 == var2.field_0002;
      }
   }

   public UnidentifiedClass4667(UnidentifiedClass4667 var1) {
      this.field_0000 = var1.field_0000;
      this.field_0002 = var1.field_0002;
   }

   public UnidentifiedClass4667(float var1, float var2) {
      this.field_0000 = var1;
      this.field_0002 = var2;
   }

   public float method_28170(float var1, float var2) {
      var1 -= this.field_0000;
      var2 -= this.field_0002;
      return var1 * var1 + var2 * var2;
   }

   public static float method_28171(float var0, float var1, float var2, float var3) {
      var0 -= var2;
      var1 -= var3;
      return (float)Math.sqrt(var0 * var0 + var1 * var1);
   }

   public UnidentifiedClass4667() {
   }

   public float method_28169(UnidentifiedClass4667 var1) {
      float var2 = var1.field_0000 - this.field_0000;
      float var3 = var1.field_0002 - this.field_0002;
      return var2 * var2 + var3 * var3;
   }

   @Override
   public String toString() {
      return "Vec2f[" + this.field_0000 + ", " + this.field_0002 + "]";
   }

   public void method_28172(UnidentifiedClass4667 var1) {
      this.field_0000 = var1.field_0000;
      this.field_0002 = var1.field_0002;
   }

   public float method_28164(UnidentifiedClass4667 var1) {
      float var2 = var1.field_0000 - this.field_0000;
      float var3 = var1.field_0002 - this.field_0002;
      return (float)Math.sqrt(var2 * var2 + var3 * var3);
   }

   @Override
   public int hashCode() {
      byte var1 = 7;
      int var2 = 31 * var1 + Float.floatToIntBits(this.field_0000);
      return 31 * var2 + Float.floatToIntBits(this.field_0002);
   }

   public float method_28163(float var1, float var2) {
      var1 -= this.field_0000;
      var2 -= this.field_0002;
      return (float)Math.sqrt(var1 * var1 + var2 * var2);
   }
}
