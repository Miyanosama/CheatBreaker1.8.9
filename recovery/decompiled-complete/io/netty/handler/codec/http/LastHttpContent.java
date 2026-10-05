package io.netty.handler.codec.http;

public interface LastHttpContent extends HttpContent {
   LastHttpContent EMPTY_LAST_CONTENT = new LastHttpContent$1();

   HttpHeaders trailingHeaders();

   LastHttpContent retain();

   LastHttpContent retain(int var1);

   LastHttpContent copy();
}
