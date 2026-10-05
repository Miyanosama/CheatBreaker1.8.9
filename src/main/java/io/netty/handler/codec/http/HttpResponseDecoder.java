package io.netty.handler.codec.http;

import com.cheatbreaker.client.ui.resourcepack.ResourcePackEntryElement;

public class HttpResponseDecoder extends HttpObjectDecoder {
   public static HttpResponseStatus UNKNOWN_STATUS = new HttpResponseStatus(999, "Unknown");

   @Override
   public HttpMessage createInvalidMessage() {
      return new DefaultHttpResponse(HttpVersion.HTTP_1_0, UNKNOWN_STATUS, this.validateHeaders);
   }

   @Override
   public boolean isDecodingRequest() {
      return false;
   }

   public HttpResponseDecoder() {
   }

   public HttpResponseDecoder(int var1, int var2, int var3, boolean var4) {
      super(var1, var2, var3, true, var4);
   }

   @Override
   public HttpMessage createMessage(String[] var1) {
      return new DefaultHttpResponse(HttpVersion.valueOf(var1[0]), new HttpResponseStatus(Integer.parseInt(var1[1]), var1[2]), this.validateHeaders);
   }

   public HttpResponseDecoder(int var1, int var2, int var3) {
      super(var1, var2, var3, true);
   }
}
