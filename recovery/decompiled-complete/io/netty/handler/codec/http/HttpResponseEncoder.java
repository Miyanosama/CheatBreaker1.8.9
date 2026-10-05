package io.netty.handler.codec.http;

import com.cheatbreaker.client.ui.util.font.CBFont;
import io.netty.buffer.ByteBuf;
import net.minecraft.entity.monster.EntitySlime$AISlimeAttack;
import net.minecraft.network.play.server.S06PacketUpdateHealth;

public class HttpResponseEncoder extends HttpObjectEncoder<HttpResponse> {
   public CBFont __junk1701277976555512764;
   public S06PacketUpdateHealth __junk3238978787278141235;
   public EntitySlime$AISlimeAttack __junk3553814880308620387;
   public static byte[] CRLF = new byte[]{13, 10};

   @Override
   public boolean acceptOutboundMessage(Object var1) {
      return super.acceptOutboundMessage(var1) && !(var1 instanceof HttpRequest);
   }

   public void encodeInitialLine(ByteBuf var1, HttpResponse var2) {
      var2.getProtocolVersion().encode(var1);
      var1.writeByte(32);
      var2.getStatus().encode(var1);
      var1.writeBytes(CRLF);
   }
}
