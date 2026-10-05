package recovered.unidentified;

import io.netty.handler.codec.marshalling.ChannelBufferByteOutput;
import junit.framework.Protectable;
import junit.framework.TestCase;
import junit.framework.TestResult;
import net.minecraft.client.renderer.entity.RenderBiped;

public class UnidentifiedClass3605 implements Protectable {
   public TestCase field_0002;
   public TestResult field_0004;
   public ChannelBufferByteOutput field_0001;
   public UnidentifiedClass4220 field_0003;
   public RenderBiped field_0000;

   public void protect() {
      this.field_0002.method_25956();
   }

   public UnidentifiedClass3605(TestResult var1, TestCase var2) {
      this.field_0004 = var1;
      this.field_0002 = var2;
   }
}
