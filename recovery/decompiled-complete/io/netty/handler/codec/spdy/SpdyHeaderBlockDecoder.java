package io.netty.handler.codec.spdy;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.CookieHeaderNames;
import io.netty.handler.traffic.GlobalTrafficShapingHandler;
import net.minecraft.client.gui.GuiShareToLan;
import net.minecraft.network.play.client.C0APacketAnimation;
import org.slf4j.event.SubstituteLoggingEvent;

public abstract class SpdyHeaderBlockDecoder {
   public CookieHeaderNames __junk6819325343360381209;
   public SpdyFrameEncoder __junk477953877203375160;
   public C0APacketAnimation __junk849939732459621554;
   public SubstituteLoggingEvent __junk6460833714581046461;
   public GlobalTrafficShapingHandler __junk2545657679164286440;
   public GuiShareToLan __junk7718827820255356900;

   public abstract void end();

   public abstract void endHeaderBlock(SpdyHeadersFrame var1);

   public static SpdyHeaderBlockDecoder newInstance(SpdyVersion var0, int var1) {
      return new SpdyHeaderBlockZlibDecoder(var0, var1);
   }

   public abstract void decode(ByteBuf var1, SpdyHeadersFrame var2);
}
