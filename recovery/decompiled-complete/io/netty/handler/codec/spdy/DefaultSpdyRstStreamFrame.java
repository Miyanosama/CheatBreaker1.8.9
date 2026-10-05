package io.netty.handler.codec.spdy;

import io.netty.util.internal.StringUtil;
import org.slf4j.helpers.Util$1;

public class DefaultSpdyRstStreamFrame extends DefaultSpdyStreamFrame implements SpdyRstStreamFrame {
   public Util$1 __junk6559643283558614691;
   public SpdyStreamStatus status;

   public DefaultSpdyRstStreamFrame(int var1, SpdyStreamStatus var2) {
      super(var1);
      this.setStatus(var2);
   }

   @Override
   public SpdyStreamStatus status() {
      return this.status;
   }

   @Override
   public SpdyRstStreamFrame setStreamId(int var1) {
      super.setStreamId(var1);
      return this;
   }

   public DefaultSpdyRstStreamFrame(int var1, int var2) {
      this(var1, SpdyStreamStatus.valueOf(var2));
   }

   @Override
   public SpdyRstStreamFrame setLast(boolean var1) {
      super.setLast(var1);
      return this;
   }

   @Override
   public SpdyRstStreamFrame setStatus(SpdyStreamStatus var1) {
      this.status = var1;
      return this;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(StringUtil.simpleClassName(this));
      var1.append(StringUtil.NEWLINE);
      var1.append("--> Stream-ID = ");
      var1.append(this.streamId());
      var1.append(StringUtil.NEWLINE);
      var1.append("--> Status: ");
      var1.append(this.status());
      return var1.toString();
   }
}
