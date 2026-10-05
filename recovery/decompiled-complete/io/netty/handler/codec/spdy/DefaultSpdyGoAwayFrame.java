package io.netty.handler.codec.spdy;

import io.netty.channel.AbstractChannelHandlerContext$7;
import io.netty.util.internal.StringUtil;
import net.minecraft.client.renderer.entity.RenderArrow;
import net.minecraft.network.play.server.S45PacketTitle;

public class DefaultSpdyGoAwayFrame implements SpdyGoAwayFrame {
   public RenderArrow __junk4546159078297202568;
   public SpdySessionStatus status;
   public int lastGoodStreamId;
   public AbstractChannelHandlerContext$7 __junk1815942977886614770;
   public S45PacketTitle __junk1064840294297062117;

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(StringUtil.simpleClassName(this));
      var1.append(StringUtil.NEWLINE);
      var1.append("--> Last-good-stream-ID = ");
      var1.append(this.lastGoodStreamId());
      var1.append(StringUtil.NEWLINE);
      var1.append("--> Status: ");
      var1.append(this.status());
      return var1.toString();
   }

   public DefaultSpdyGoAwayFrame(int var1, int var2) {
      this(var1, SpdySessionStatus.valueOf(var2));
   }

   @Override
   public SpdyGoAwayFrame setStatus(SpdySessionStatus var1) {
      this.status = var1;
      return this;
   }

   @Override
   public SpdyGoAwayFrame setLastGoodStreamId(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("Last-good-stream-ID cannot be negative: " + var1);
      } else {
         this.lastGoodStreamId = var1;
         return this;
      }
   }

   public DefaultSpdyGoAwayFrame(int var1) {
      this(var1, 0);
   }

   @Override
   public SpdySessionStatus status() {
      return this.status;
   }

   public DefaultSpdyGoAwayFrame(int var1, SpdySessionStatus var2) {
      this.setLastGoodStreamId(var1);
      this.setStatus(var2);
   }

   @Override
   public int lastGoodStreamId() {
      return this.lastGoodStreamId;
   }
}
