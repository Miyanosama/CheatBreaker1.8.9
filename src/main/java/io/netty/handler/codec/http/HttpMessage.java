package io.netty.handler.codec.http;

public interface HttpMessage extends HttpObject {
   HttpHeaders headers();

   HttpVersion getProtocolVersion();

   HttpMessage setProtocolVersion(HttpVersion var1);
}
