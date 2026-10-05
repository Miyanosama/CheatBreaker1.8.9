package io.netty.handler.codec.spdy;

import io.netty.util.internal.StringUtil;
import java.util.Map.Entry;
import net.minecraftforge.client.model.TRSRTransformation;
import org.apache.log4j.pattern.RelativeTimePatternConverter;

public class DefaultSpdyHeadersFrame extends DefaultSpdyStreamFrame implements SpdyHeadersFrame {
   public boolean truncated;
   public SpdyHeaders headers = new DefaultSpdyHeaders();
   public TRSRTransformation __junk6237598971141700993;
   public RelativeTimePatternConverter __junk8121738317130800290;
   public boolean invalid;

   @Override
   public SpdyHeadersFrame setStreamId(int var1) {
      super.setStreamId(var1);
      return this;
   }

   @Override
   public SpdyHeadersFrame setTruncated() {
      this.truncated = true;
      return this;
   }

   public DefaultSpdyHeadersFrame(int var1) {
      super(var1);
   }

   @Override
   public boolean isTruncated() {
      return this.truncated;
   }

   @Override
   public SpdyHeadersFrame setInvalid() {
      this.invalid = true;
      return this;
   }

   @Override
   public boolean isInvalid() {
      return this.invalid;
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

   @Override
   public SpdyHeadersFrame setLast(boolean var1) {
      super.setLast(var1);
      return this;
   }

   @Override
   public SpdyHeaders headers() {
      return this.headers;
   }

   public void appendHeaders(StringBuilder var1) {
      for (Entry var3 : this.headers()) {
         var1.append("    ");
         var1.append((String)var3.getKey());
         var1.append(": ");
         var1.append((String)var3.getValue());
         var1.append(StringUtil.NEWLINE);
      }
   }
}
