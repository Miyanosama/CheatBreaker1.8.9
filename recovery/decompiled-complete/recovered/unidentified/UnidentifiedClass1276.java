package recovered.unidentified;

import io.netty.buffer.ByteBufProcessor$6;
import io.netty.handler.codec.spdy.SpdyHeaderBlockZlibEncoder;
import io.netty.util.collection.IntObjectHashMap;
import net.minecraft.entity.ai.EntitySenses;
import org.java_websocket.drafts.Draft_6455$TranslatedPayloadMetaData;

public class UnidentifiedClass1276 {
   public SpdyHeaderBlockZlibEncoder field_0002;
   public Draft_6455$TranslatedPayloadMetaData field_0004;
   public EntitySenses field_0001;
   public IntObjectHashMap field_0003;
   public ByteBufProcessor$6 field_0000;

   public static long method_08549(double var0) {
      return var0 == 0.0 ? -4780314030031862783L & 302064140L : Double.doubleToLongBits(var0);
   }

   public static int method_08550(float var0) {
      return var0 == 0.0F ? 0 : Float.floatToIntBits(var0);
   }
}
