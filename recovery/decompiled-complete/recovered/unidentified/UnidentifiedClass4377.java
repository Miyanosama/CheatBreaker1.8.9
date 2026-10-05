package recovered.unidentified;

import io.netty.handler.codec.http.multipart.AbstractDiskHttpData;
import io.netty.handler.codec.spdy.SpdyHttpResponseStreamIdHandler;
import net.minecraft.client.audio.SoundEventAccessorComposite;

public class UnidentifiedClass4377 {
   public SoundEventAccessorComposite field_0003;
   public SpdyHttpResponseStreamIdHandler field_0005;
   public AbstractDiskHttpData field_0002;
   public String field_0004;
   public int[] field_0000;
   public String field_0001;

   public String method_26420() {
      return this.field_0004;
   }

   public UnidentifiedClass4377(String var1, String var2, int[] var3) {
      this.field_0004 = var1;
      this.field_0001 = var2;
      this.field_0000 = var3;
   }

   public String method_26422() {
      return this.field_0001;
   }

   public void method_26421(String var1) {
      this.field_0004 = var1;
   }

   public int[] method_26419() {
      return this.field_0000;
   }
}
