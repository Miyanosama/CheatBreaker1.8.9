package net.optifine.http;

import java.net.Proxy;
import java.util.LinkedHashMap;
import java.util.Map;

public class HttpRequest {
   public String method;
   public int redirects;
   public static final String recoveredField3844 = "GET";
   public static final String recoveredField3845 = "HTTP/1.0";
   public String host = null;
   public Proxy proxy;
   public byte[] body;
   public String file;
   public String http;
   public static final String recoveredField3846 = "POST";
   public int port = 0;
   public static final String recoveredField3847 = "HTTP/1.1";
   public static final String recoveredField3848 = "HEAD";
   public Map<String, String> headers;

   public String getFile() {
      return this.file;
   }

   public String getHttp() {
      return this.http;
   }

   public String getHost() {
      return this.host;
   }

   public HttpRequest(String var1, int var2, Proxy var3, String var4, String var5, String var6, Map<String, String> var7, byte[] var8) {
      this.proxy = Proxy.NO_PROXY;
      this.method = null;
      this.file = null;
      this.http = null;
      this.headers = new LinkedHashMap<>();
      this.body = null;
      this.redirects = 0;
      this.host = var1;
      this.port = var2;
      this.proxy = var3;
      this.method = var4;
      this.file = var5;
      this.http = var6;
      this.headers = var7;
      this.body = var8;
   }

   public Map<String, String> getHeaders() {
      return this.headers;
   }

   public String getMethod() {
      return this.method;
   }

   public int getPort() {
      return this.port;
   }

   public byte[] getBody() {
      return this.body;
   }

   public void setRedirects(int var1) {
      this.redirects = var1;
   }

   public Proxy getProxy() {
      return this.proxy;
   }

   public int getRedirects() {
      return this.redirects;
   }
}
