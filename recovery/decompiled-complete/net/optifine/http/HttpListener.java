package net.optifine.http;

public interface HttpListener {
   void failed(HttpRequest var1, Exception var2);

   void finished(HttpRequest var1, HttpResponse var2);
}
