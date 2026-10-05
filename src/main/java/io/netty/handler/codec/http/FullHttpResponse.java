package io.netty.handler.codec.http;

public interface FullHttpResponse extends FullHttpMessage, HttpResponse {
   FullHttpResponse setProtocolVersion(HttpVersion var1);

   FullHttpResponse setStatus(HttpResponseStatus var1);

   FullHttpResponse retain(int var1);

   FullHttpResponse copy();

   FullHttpResponse retain();
}
