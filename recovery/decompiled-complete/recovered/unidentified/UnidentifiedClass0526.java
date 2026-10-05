package recovered.unidentified;

import io.netty.handler.codec.EncoderException;
import junit.runner.SimpleTestCollector;
import org.lwjgl.opengl.GL11;

public class UnidentifiedClass0526 {
   public SimpleTestCollector field_0001;
   public boolean field_0003 = false;
   public EncoderException field_0000;
   public int field_0002;

   public void method_03840(boolean var1) {
      if (var1 != this.field_0003) {
         this.field_0003 = var1;
         if (var1) {
            GL11.glEnable(this.field_0002);
         } else {
            GL11.glDisable(this.field_0002);
         }
      }
   }

   public UnidentifiedClass0526(int var1) {
      this.field_0002 = var1;
   }

   public void method_03839() {
      this.method_03840(false);
   }

   public void method_03841() {
      this.method_03840(true);
   }
}
