package io.netty.handler.codec.http;

import com.cheatbreaker.client.ui.util.font.CBFont;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.network.play.server.S06PacketUpdateHealth;

public class HttpResponseEncoder extends HttpObjectEncoder<HttpResponse> {
   public static byte[] CRLF = new byte[]{13, 10};

   @Override
   public boolean acceptOutboundMessage(Object var1) throws java.lang.Exception {
      return super.acceptOutboundMessage(var1) && !(var1 instanceof HttpRequest);
   }

   public void encodeInitialLine(ByteBuf var1, HttpResponse var2) throws java.lang.Exception {
      var2.getProtocolVersion().encode(var1);
      var1.writeByte(32);
      var2.getStatus().encode(var1);
      var1.writeBytes(CRLF);
   }
}
