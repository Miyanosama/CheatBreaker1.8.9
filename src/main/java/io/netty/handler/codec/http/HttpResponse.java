package io.netty.handler.codec.http;

public interface HttpResponse extends HttpMessage {
   HttpResponse setStatus(HttpResponseStatus var1);

   HttpResponse setProtocolVersion(HttpVersion var1);

   HttpResponseStatus getStatus();
}
