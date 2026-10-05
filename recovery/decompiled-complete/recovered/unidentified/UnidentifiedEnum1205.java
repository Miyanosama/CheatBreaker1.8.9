package recovered.unidentified;

import com.cheatbreaker.client.websocket.shared.WSPacketServerUpdate;
import io.netty.handler.codec.spdy.SpdyFrameDecoder$1;

public enum UnidentifiedEnum1205 {
   field_0003,
   field_0005,
   field_0004,
   field_0000;

   public WSPacketServerUpdate field_0002;
   public SpdyFrameDecoder$1 field_0001;

   public static UnidentifiedEnum1205 method_08178(String var0) {
      return Enum.valueOf(UnidentifiedEnum1205.class, var0);
   }
}
