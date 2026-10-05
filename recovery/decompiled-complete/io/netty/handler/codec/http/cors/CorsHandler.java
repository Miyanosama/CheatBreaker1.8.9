package io.netty.handler.codec.http.cors;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import net.minecraft.util.EnumFacing$Axis;

public class CorsHandler extends ChannelDuplexHandler {
   public CorsConfig config;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(CorsHandler.class);
   public HttpRequest request;
   public EnumFacing$Axis __junk6661102571310315113;

   public void handlePreflight(ChannelHandlerContext var1, HttpRequest var2) {
      DefaultFullHttpResponse var3 = new DefaultFullHttpResponse(var2.getProtocolVersion(), HttpResponseStatus.OK);
      if (this.setOrigin(var3)) {
         this.setAllowMethods(var3);
         this.setAllowHeaders(var3);
         this.setAllowCredentials(var3);
         this.setMaxAge(var3);
         this.setPreflightHeaders(var3);
      }

      var1.writeAndFlush(var3).addListener(ChannelFutureListener.CLOSE);
   }

   public void setPreflightHeaders(HttpResponse var1) {
      var1.headers().add(this.config.preflightResponseHeaders());
   }

   public static void forbidden(ChannelHandlerContext var0, HttpRequest var1) {
      var0.writeAndFlush(new DefaultFullHttpResponse(var1.getProtocolVersion(), HttpResponseStatus.FORBIDDEN)).addListener(ChannelFutureListener.CLOSE);
   }

   public boolean setOrigin(HttpResponse var1) {
      String var2 = this.request.headers().get("Origin");
      if (var2 != null) {
         if ("null".equals(var2) && this.config.isNullOriginAllowed()) {
            setAnyOrigin(var1);
            return true;
         }

         if (this.config.isAnyOriginSupported()) {
            if (this.config.isCredentialsAllowed()) {
               this.echoRequestOrigin(var1);
               setVaryHeader(var1);
            } else {
               setAnyOrigin(var1);
            }

            return true;
         }

         if (this.config.origins().contains(var2)) {
            setOrigin(var1, var2);
            setVaryHeader(var1);
            return true;
         }

         logger.debug("Request origin [" + var2 + "] was not among the configured origins " + this.config.origins());
      }

      return false;
   }

   public void setMaxAge(HttpResponse var1) {
      var1.headers().set("Access-Control-Max-Age", this.config.maxAge());
   }

   public void setAllowMethods(HttpResponse var1) {
      var1.headers().set("Access-Control-Allow-Methods", (Iterable<?>)this.config.allowedRequestMethods());
   }

   public static void setAnyOrigin(HttpResponse var0) {
      setOrigin(var0, "*");
   }

   public void setAllowHeaders(HttpResponse var1) {
      var1.headers().set("Access-Control-Allow-Headers", (Iterable<?>)this.config.allowedRequestHeaders());
   }

   public boolean validateOrigin() {
      if (this.config.isAnyOriginSupported()) {
         return true;
      } else {
         String var1 = this.request.headers().get("Origin");
         if (var1 == null) {
            return true;
         } else {
            return "null".equals(var1) && this.config.isNullOriginAllowed() ? true : this.config.origins().contains(var1);
         }
      }
   }

   public CorsHandler(CorsConfig var1) {
      this.config = var1;
   }

   public void setExposeHeaders(HttpResponse var1) {
      if (!this.config.exposedHeaders().isEmpty()) {
         var1.headers().set("Access-Control-Expose-Headers", (Iterable<?>)this.config.exposedHeaders());
      }
   }

   @Override
   public void exceptionCaught(ChannelHandlerContext var1, Throwable var2) {
      logger.error("Caught error in CorsHandler", var2);
      var1.fireExceptionCaught(var2);
   }

   public void echoRequestOrigin(HttpResponse var1) {
      setOrigin(var1, this.request.headers().get("Origin"));
   }

   public static void setVaryHeader(HttpResponse var0) {
      var0.headers().set("Vary", "Origin");
   }

   @Override
   public void write(ChannelHandlerContext var1, Object var2, ChannelPromise var3) {
      if (this.config.isCorsSupportEnabled() && var2 instanceof HttpResponse) {
         HttpResponse var4 = (HttpResponse)var2;
         if (this.setOrigin(var4)) {
            this.setAllowCredentials(var4);
            this.setAllowHeaders(var4);
            this.setExposeHeaders(var4);
         }
      }

      var1.writeAndFlush(var2, var3);
   }

   public static boolean isPreflightRequest(HttpRequest var0) {
      HttpHeaders var1 = var0.headers();
      return var0.getMethod().equals(HttpMethod.OPTIONS) && var1.contains("Origin") && var1.contains("Access-Control-Request-Method");
   }

   public void setAllowCredentials(HttpResponse var1) {
      if (this.config.isCredentialsAllowed()) {
         var1.headers().set("Access-Control-Allow-Credentials", "true");
      }
   }

   public static void setOrigin(HttpResponse var0, String var1) {
      var0.headers().set("Access-Control-Allow-Origin", var1);
   }

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) {
      if (this.config.isCorsSupportEnabled() && var2 instanceof HttpRequest) {
         this.request = (HttpRequest)var2;
         if (isPreflightRequest(this.request)) {
            this.handlePreflight(var1, this.request);
            return;
         }

         if (this.config.isShortCurcuit() && !this.validateOrigin()) {
            forbidden(var1, this.request);
            return;
         }
      }

      var1.fireChannelRead(var2);
   }
}
