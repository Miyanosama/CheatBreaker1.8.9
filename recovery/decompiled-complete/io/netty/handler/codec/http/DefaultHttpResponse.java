package io.netty.handler.codec.http;

import io.netty.util.internal.StringUtil;
import net.minecraft.entity.ai.EntityAIFindEntityNearestPlayer$1;
import net.minecraft.util.BlockPos$2;
import net.minecraft.util.RegistryNamespacedDefaultedByKey;
import net.minecraft.util.ScreenShotHelper;
import org.java_websocket.handshake.HandshakeImpl1Client;

public class DefaultHttpResponse extends DefaultHttpMessage implements HttpResponse {
   public RegistryNamespacedDefaultedByKey __junk523323454126997541;
   public BlockPos$2 __junk7604563183484340385;
   public org.json.Cookie __junk3658198691542005800;
   public HttpResponseStatus status;
   public HandshakeImpl1Client __junk5732998216232026110;
   public ScreenShotHelper __junk370351437706422656;
   public EntityAIFindEntityNearestPlayer$1 __junk3590689521894143830;

   public DefaultHttpResponse(HttpVersion var1, HttpResponseStatus var2) {
      this(var1, var2, true);
   }

   @Override
   public HttpResponse setProtocolVersion(HttpVersion var1) {
      super.setProtocolVersion(var1);
      return this;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(StringUtil.simpleClassName(this));
      var1.append("(decodeResult: ");
      var1.append(this.getDecoderResult());
      var1.append(')');
      var1.append(StringUtil.NEWLINE);
      var1.append(this.getProtocolVersion().text());
      var1.append(' ');
      var1.append(this.getStatus());
      var1.append(StringUtil.NEWLINE);
      this.appendHeaders(var1);
      var1.setLength(var1.length() - StringUtil.NEWLINE.length());
      return var1.toString();
   }

   @Override
   public HttpResponseStatus getStatus() {
      return this.status;
   }

   @Override
   public HttpResponse setStatus(HttpResponseStatus var1) {
      if (var1 == null) {
         throw new NullPointerException("status");
      } else {
         this.status = var1;
         return this;
      }
   }

   public DefaultHttpResponse(HttpVersion var1, HttpResponseStatus var2, boolean var3) {
      super(var1, var3);
      if (var2 == null) {
         throw new NullPointerException("status");
      } else {
         this.status = var2;
      }
   }
}
