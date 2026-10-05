package io.netty.handler.codec.spdy;

import io.netty.util.internal.StringUtil;
import net.minecraft.network.play.server.S44PacketWorldBorder;
import org.java_websocket.exceptions.WebsocketNotConnectedException;

public class DefaultSpdySynReplyFrame extends DefaultSpdyHeadersFrame implements SpdySynReplyFrame {

   @Override
   public SpdySynReplyFrame setStreamId(int var1) {
      super.setStreamId(var1);
      return this;
   }

   @Override
   public SpdySynReplyFrame setLast(boolean var1) {
      super.setLast(var1);
      return this;
   }

   @Override
   public SpdySynReplyFrame setInvalid() {
      super.setInvalid();
      return this;
   }

   public DefaultSpdySynReplyFrame(int var1) {
      super(var1);
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(StringUtil.simpleClassName(this));
      var1.append("(last: ");
      var1.append(this.isLast());
      var1.append(')');
      var1.append(StringUtil.NEWLINE);
      var1.append("--> Stream-ID = ");
      var1.append(this.streamId());
      var1.append(StringUtil.NEWLINE);
      var1.append("--> Headers:");
      var1.append(StringUtil.NEWLINE);
      this.appendHeaders(var1);
      var1.setLength(var1.length() - StringUtil.NEWLINE.length());
      return var1.toString();
   }
}
