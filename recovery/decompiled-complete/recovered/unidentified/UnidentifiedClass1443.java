package recovered.unidentified;

import io.netty.handler.codec.MessageToMessageEncoder;
import net.minecraft.client.renderer.block.model.ModelBlock$Deserializer;
import net.minecraft.util.EnumFacing;
import net.optifine.CrashReporter$1;

// $VF: synthetic class
public class UnidentifiedClass1443 {
   public MessageToMessageEncoder field_0003;
   public ModelBlock$Deserializer field_0000;
   public CrashReporter$1 field_0002;

   static {
      try {
         field_0001[EnumFacing.NORTH.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         field_0001[EnumFacing.SOUTH.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_0001[EnumFacing.WEST.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_0001[EnumFacing.EAST.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
