package io.netty.handler.codec.http;

public interface FullHttpRequest extends FullHttpMessage, HttpRequest {
   FullHttpRequest retain();

   FullHttpRequest setUri(String var1);

   FullHttpRequest setProtocolVersion(HttpVersion var1);

   FullHttpRequest copy();

   FullHttpRequest retain(int var1);

   FullHttpRequest setMethod(HttpMethod var1);
}
