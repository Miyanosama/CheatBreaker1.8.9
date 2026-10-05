package net.optifine.http;

public class HttpPipelineRequest {
   public boolean closed;
   public HttpRequest httpRequest = null;
   public HttpListener httpListener = null;

   public HttpListener getHttpListener() {
      return this.httpListener;
   }

   public boolean isClosed() {
      return this.closed;
   }

   public void setClosed(boolean var1) {
      this.closed = var1;
   }

   public HttpRequest getHttpRequest() {
      return this.httpRequest;
   }

   public HttpPipelineRequest(HttpRequest var1, HttpListener var2) {
      this.closed = false;
      this.httpRequest = var1;
      this.httpListener = var2;
   }
}
