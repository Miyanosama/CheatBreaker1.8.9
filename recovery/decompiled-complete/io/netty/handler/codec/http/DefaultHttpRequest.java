package io.netty.handler.codec.http;

import com.cheatbreaker.client.ui.mainmenu.MainMenu;
import io.netty.util.internal.StringUtil;
import net.minecraft.entity.passive.EntityPig;
import net.optifine.expr.ExpressionFloatCached;
import recovered.unidentified.UnidentifiedClass1221;

public class DefaultHttpRequest extends DefaultHttpMessage implements HttpRequest {
   public EntityPig __junk1396620225660171419;
   public HttpMethod method;
   public String uri;
   public MainMenu __junk3850811891888699554;
   public UnidentifiedClass1221 __junk4412259784599323429;
   public ExpressionFloatCached __junk5193250961174668864;

   public DefaultHttpRequest(HttpVersion var1, HttpMethod var2, String var3, boolean var4) {
      super(var1, var4);
      if (var2 == null) {
         throw new NullPointerException("method");
      } else if (var3 == null) {
         throw new NullPointerException("uri");
      } else {
         this.method = var2;
         this.uri = var3;
      }
   }

   @Override
   public String getUri() {
      return this.uri;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(StringUtil.simpleClassName(this));
      var1.append("(decodeResult: ");
      var1.append(this.getDecoderResult());
      var1.append(')');
      var1.append(StringUtil.NEWLINE);
      var1.append(this.getMethod());
      var1.append(' ');
      var1.append(this.getUri());
      var1.append(' ');
      var1.append(this.getProtocolVersion().text());
      var1.append(StringUtil.NEWLINE);
      this.appendHeaders(var1);
      var1.setLength(var1.length() - StringUtil.NEWLINE.length());
      return var1.toString();
   }

   public DefaultHttpRequest(HttpVersion var1, HttpMethod var2, String var3) {
      this(var1, var2, var3, true);
   }

   @Override
   public HttpRequest setProtocolVersion(HttpVersion var1) {
      super.setProtocolVersion(var1);
      return this;
   }

   @Override
   public HttpRequest setMethod(HttpMethod var1) {
      if (var1 == null) {
         throw new NullPointerException("method");
      } else {
         this.method = var1;
         return this;
      }
   }

   @Override
   public HttpRequest setUri(String var1) {
      if (var1 == null) {
         throw new NullPointerException("uri");
      } else {
         this.uri = var1;
         return this;
      }
   }

   @Override
   public HttpMethod getMethod() {
      return this.method;
   }
}
