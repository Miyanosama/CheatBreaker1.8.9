package io.netty.handler.codec.http.multipart;

import io.netty.handler.codec.DecoderResult;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpVersion;
import net.minecraft.client.renderer.WorldRenderer$1;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import org.apache.log4j.helpers.PatternParser$BasicPatternConverter;
import recovered.unidentified.UnidentifiedClass4292;

public class HttpPostRequestEncoder$WrappedHttpRequest implements HttpRequest {
   public UnidentifiedClass4292 __junk5311052328780295724;
   public PatternParser$BasicPatternConverter __junk9056369245124471592;
   public WorldRenderer$1 __junk2590385743003497333;
   public WorldGenCanopyTree __junk2474468926225069307;
   public HttpRequest request;

   public HttpPostRequestEncoder$WrappedHttpRequest(HttpRequest var1) {
      this.request = var1;
   }

   @Override
   public HttpMethod getMethod() {
      return this.request.getMethod();
   }

   @Override
   public String getUri() {
      return this.request.getUri();
   }

   @Override
   public void setDecoderResult(DecoderResult var1) {
      this.request.setDecoderResult(var1);
   }

   @Override
   public HttpRequest setProtocolVersion(HttpVersion var1) {
      this.request.setProtocolVersion(var1);
      return this;
   }

   @Override
   public HttpHeaders headers() {
      return this.request.headers();
   }

   @Override
   public DecoderResult getDecoderResult() {
      return this.request.getDecoderResult();
   }

   @Override
   public HttpVersion getProtocolVersion() {
      return this.request.getProtocolVersion();
   }

   @Override
   public HttpRequest setUri(String var1) {
      this.request.setUri(var1);
      return this;
   }

   @Override
   public HttpRequest setMethod(HttpMethod var1) {
      this.request.setMethod(var1);
      return this;
   }
}
