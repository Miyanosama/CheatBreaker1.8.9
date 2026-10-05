package io.netty.handler.codec.http;

import io.netty.util.internal.StringUtil;
import java.util.Map.Entry;
import net.minecraft.block.BlockPressurePlate;

public abstract class DefaultHttpMessage extends DefaultHttpObject implements HttpMessage {
   public HttpHeaders headers;
   public HttpVersion version;

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(StringUtil.simpleClassName(this));
      var1.append("(version: ");
      var1.append(this.getProtocolVersion().text());
      var1.append(", keepAlive: ");
      var1.append(HttpHeaders.isKeepAlive(this));
      var1.append(')');
      var1.append(StringUtil.NEWLINE);
      this.appendHeaders(var1);
      var1.setLength(var1.length() - StringUtil.NEWLINE.length());
      return var1.toString();
   }

   public DefaultHttpMessage(HttpVersion var1, boolean var2) {
      if (var1 == null) {
         throw new NullPointerException("version");
      } else {
         this.version = var1;
         this.headers = new DefaultHttpHeaders(var2);
      }
   }

   @Override
   public HttpHeaders headers() {
      return this.headers;
   }

   public DefaultHttpMessage(HttpVersion var1) {
      this(var1, true);
   }

   public void appendHeaders(StringBuilder var1) {
      for (Entry var3 : this.headers()) {
         var1.append((String)var3.getKey());
         var1.append(": ");
         var1.append((String)var3.getValue());
         var1.append(StringUtil.NEWLINE);
      }
   }

   @Override
   public HttpVersion getProtocolVersion() {
      return this.version;
   }

   @Override
   public HttpMessage setProtocolVersion(HttpVersion var1) {
      if (var1 == null) {
         throw new NullPointerException("version");
      } else {
         this.version = var1;
         return this;
      }
   }
}
