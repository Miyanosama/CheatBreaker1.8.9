package io.netty.handler.codec.spdy;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.CookieHeaderNames;
import io.netty.handler.traffic.GlobalTrafficShapingHandler;
import net.minecraft.client.gui.GuiShareToLan;
import net.minecraft.network.play.client.C0APacketAnimation;
import org.slf4j.event.SubstituteLoggingEvent;

public abstract class SpdyHeaderBlockDecoder {

   public abstract void end();

   public abstract void endHeaderBlock(SpdyHeadersFrame var1) throws java.lang.Exception ;

   public static SpdyHeaderBlockDecoder newInstance(SpdyVersion var0, int var1) {
      return new SpdyHeaderBlockZlibDecoder(var0, var1);
   }

   public abstract void decode(ByteBuf var1, SpdyHeadersFrame var2) throws java.lang.Exception ;
}
