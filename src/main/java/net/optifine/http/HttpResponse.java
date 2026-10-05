package net.optifine.http;

import java.util.LinkedHashMap;
import java.util.Map;

public class HttpResponse {
   public Map<String, String> headers;
   public int status = 0;
   public byte[] body;
   public String statusLine = null;

   public Map getHeaders() {
      return this.headers;
   }

   public HttpResponse(int var1, String var2, Map var3, byte[] var4) {
      this.headers = new LinkedHashMap<>();
      this.body = null;
      this.status = var1;
      this.statusLine = var2;
      this.headers = var3;
      this.body = var4;
   }

   public int getStatus() {
      return this.status;
   }

   public byte[] getBody() {
      return this.body;
   }

   public String getStatusLine() {
      return this.statusLine;
   }

   public String getHeader(String var1) {
      return this.headers.get(var1);
   }
}
