package io.netty.handler.codec.http;

public interface HttpRequest extends HttpMessage {
   HttpRequest setUri(String var1);

   HttpRequest setProtocolVersion(HttpVersion var1);

   String getUri();

   HttpMethod getMethod();

   HttpRequest setMethod(HttpMethod var1);
}
