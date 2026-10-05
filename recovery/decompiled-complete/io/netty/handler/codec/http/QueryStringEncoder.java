package io.netty.handler.codec.http;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.client.renderer.entity.RenderSkeleton$1;
import net.minecraft.init.Bootstrap$12$1;
import net.minecraft.network.play.server.S14PacketEntity$S16PacketEntityLook;

public class QueryStringEncoder {
   public List<QueryStringEncoder$Param> params = new ArrayList<>();
   public String uri;
   public RenderSkeleton$1 __junk495118471746196698;
   public S14PacketEntity$S16PacketEntityLook __junk2730643838619220720;
   public Bootstrap$12$1 __junk8117781216116251282;
   public Charset charset;

   public void addParam(String var1, String var2) {
      if (var1 == null) {
         throw new NullPointerException("name");
      } else {
         this.params.add(new QueryStringEncoder$Param(var1, var2));
      }
   }

   @Override
   public String toString() {
      if (this.params.isEmpty()) {
         return this.uri;
      } else {
         StringBuilder var1 = new StringBuilder(this.uri).append('?');

         for (int var2 = 0; var2 < this.params.size(); var2++) {
            QueryStringEncoder$Param var3 = this.params.get(var2);
            var1.append(encodeComponent(var3.name, this.charset));
            if (var3.value != null) {
               var1.append('=');
               var1.append(encodeComponent(var3.value, this.charset));
            }

            if (var2 != this.params.size() - 1) {
               var1.append('&');
            }
         }

         return var1.toString();
      }
   }

   public QueryStringEncoder(String var1) {
      this(var1, HttpConstants.DEFAULT_CHARSET);
   }

   public QueryStringEncoder(String var1, Charset var2) {
      if (var1 == null) {
         throw new NullPointerException("getUri");
      } else if (var2 == null) {
         throw new NullPointerException("charset");
      } else {
         this.uri = var1;
         this.charset = var2;
      }
   }

   public URI toUri() {
      return new URI(this.toString());
   }

   public static String encodeComponent(String var0, Charset var1) {
      try {
         return URLEncoder.encode(var0, var1.name()).replace("+", "%20");
      } catch (UnsupportedEncodingException var3) {
         throw new UnsupportedCharsetException(var1.name());
      }
   }
}
