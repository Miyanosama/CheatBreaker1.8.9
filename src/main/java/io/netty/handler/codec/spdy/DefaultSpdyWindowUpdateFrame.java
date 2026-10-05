package io.netty.handler.codec.spdy;

import io.netty.util.internal.StringUtil;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import org.java_websocket.util.NamedThreadFactory;

public class DefaultSpdyWindowUpdateFrame implements SpdyWindowUpdateFrame {
   public int deltaWindowSize;
   public int streamId;

   public DefaultSpdyWindowUpdateFrame(int var1, int var2) {
      this.setStreamId(var1);
      this.setDeltaWindowSize(var2);
   }

   @Override
   public int deltaWindowSize() {
      return this.deltaWindowSize;
   }

   @Override
   public SpdyWindowUpdateFrame setStreamId(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("Stream-ID cannot be negative: " + var1);
      } else {
         this.streamId = var1;
         return this;
      }
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(StringUtil.simpleClassName(this));
      var1.append(StringUtil.NEWLINE);
      var1.append("--> Stream-ID = ");
      var1.append(this.streamId());
      var1.append(StringUtil.NEWLINE);
      var1.append("--> Delta-Window-Size = ");
      var1.append(this.deltaWindowSize());
      return var1.toString();
   }

   @Override
   public SpdyWindowUpdateFrame setDeltaWindowSize(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("Delta-Window-Size must be positive: " + var1);
      } else {
         this.deltaWindowSize = var1;
         return this;
      }
   }

   @Override
   public int streamId() {
      return this.streamId;
   }
}
