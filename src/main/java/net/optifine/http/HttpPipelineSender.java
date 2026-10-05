package net.optifine.http;

import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Map;

public class HttpPipelineSender extends Thread {
   public static final String recoveredField143 = "\r\n";
   public HttpPipelineConnection httpPipelineConnection = null;
   public static Charset ASCII = Charset.forName("ASCII");

   public void connect() throws java.io.IOException {
      String var1 = this.httpPipelineConnection.getHost();
      int var2 = this.httpPipelineConnection.getPort();
      Proxy var3 = this.httpPipelineConnection.getProxy();
      Socket var4 = new Socket(var3);
      var4.connect(new InetSocketAddress(var1, var2), 5000);
      this.httpPipelineConnection.setSocket(var4);
   }

   @Override
   public void run() {
      HttpPipelineRequest var1 = null;

      try {
         this.connect();

         while (!Thread.interrupted()) {
            var1 = this.httpPipelineConnection.getNextRequestSend();
            HttpRequest var2 = var1.getHttpRequest();
            OutputStream var3 = this.httpPipelineConnection.getOutputStream();
            this.writeRequest(var2, var3);
            this.httpPipelineConnection.onRequestSent(var1);
         }
      } catch (InterruptedException var4) {
         return;
      } catch (Exception var5) {
         this.httpPipelineConnection.onExceptionSend(var1, var5);
      }
   }

   public HttpPipelineSender(HttpPipelineConnection var1) {
      super("HttpPipelineSender");
      this.httpPipelineConnection = var1;
   }

   public void writeRequest(HttpRequest var1, OutputStream var2) throws java.io.IOException {
      this.write(var2, var1.getMethod() + " " + var1.getFile() + " " + var1.getHttp() + "\r\n");
      Map var3 = var1.getHeaders();

      for (String var5 : (Iterable<String>)(Iterable<?>)(var3.keySet())) {
         String var6 = var1.getHeaders().get(var5);
         this.write(var2, var5 + ": " + var6 + "\r\n");
      }

      this.write(var2, "\r\n");
   }

   public void write(OutputStream var1, String var2) throws java.io.IOException {
      byte[] var3 = var2.getBytes(ASCII);
      var1.write(var3);
   }
}
