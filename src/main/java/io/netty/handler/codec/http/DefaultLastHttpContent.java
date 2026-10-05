package io.netty.handler.codec.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.internal.StringUtil;
import java.util.Map.Entry;
import net.minecraft.client.gui.GuiResourcePackSelected;
import net.minecraft.client.model.ModelWitch;
import net.minecraft.client.stream.ChatController;
import net.optifine.util.StrUtils;

public class DefaultLastHttpContent extends DefaultHttpContent implements LastHttpContent {
   public HttpHeaders trailingHeaders;
   public boolean validateHeaders;

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder(super.toString());
      var1.append(StringUtil.NEWLINE);
      this.appendHeaders(var1);
      var1.setLength(var1.length() - StringUtil.NEWLINE.length());
      return var1.toString();
   }

   @Override
   public LastHttpContent copy() {
      DefaultLastHttpContent var1 = new DefaultLastHttpContent(this.content().copy(), this.validateHeaders);
      var1.trailingHeaders().set(this.trailingHeaders());
      return var1;
   }

   public DefaultLastHttpContent(ByteBuf var1, boolean var2) {
      super(var1);
      this.trailingHeaders = new DefaultLastHttpContent.TrailingHeaders(var2);
      this.validateHeaders = var2;
   }

   public LastHttpContent duplicate() {
      DefaultLastHttpContent var1 = new DefaultLastHttpContent(this.content().duplicate(), this.validateHeaders);
      var1.trailingHeaders().set(this.trailingHeaders());
      return var1;
   }

   public DefaultLastHttpContent() {
      this(Unpooled.buffer(0));
   }

   @Override
   public LastHttpContent retain() {
      super.retain();
      return this;
   }

   @Override
   public LastHttpContent retain(int var1) {
      super.retain(var1);
      return this;
   }

   public DefaultLastHttpContent(ByteBuf var1) {
      this(var1, true);
   }

   @Override
   public HttpHeaders trailingHeaders() {
      return this.trailingHeaders;
   }

   public void appendHeaders(StringBuilder var1) {
      for (Entry var3 : this.trailingHeaders()) {
         var1.append((String)var3.getKey());
         var1.append(": ");
         var1.append((String)var3.getValue());
         var1.append(StringUtil.NEWLINE);
      }
   }

   public static final class TrailingHeaders extends DefaultHttpHeaders {

      public TrailingHeaders(boolean var1) {
         super(var1);
      }

      @Override
      public void validateHeaderName0(CharSequence var1) {
         super.validateHeaderName0(var1);
         if (HttpHeaders.equalsIgnoreCase("Content-Length", var1)
            || HttpHeaders.equalsIgnoreCase("Transfer-Encoding", var1)
            || HttpHeaders.equalsIgnoreCase("Trailer", var1)) {
            throw new IllegalArgumentException("prohibited trailing header: " + var1);
         }
      }
   }
}
