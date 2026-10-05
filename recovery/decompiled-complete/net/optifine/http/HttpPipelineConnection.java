package net.optifine.http;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$KeySetView;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Proxy;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.monster.EntityGhast$AIRandomFly;
import net.minecraft.src.Config;
import org.apache.log4j.varia.NullAppender;

public class HttpPipelineConnection {
   public List<HttpPipelineRequest> listRequestsSend;
   public HttpPipelineSender httpPipelineSender;
   public String host = null;
   public static int field_0018;
   public Socket socket;
   public boolean field_0007;
   public static Pattern patternFullUrl = Pattern.compile("^[a-zA-Z]+://.*");
   public int field_0016;
   public InputStream inputStream;
   public int countRequests;
   public OutputStream outputStream;
   public int field_0013 = 0;
   public Proxy proxy = Proxy.NO_PROXY;
   public long keepaliveTimeoutMs;
   public List<HttpPipelineRequest> listRequests = new LinkedList<>();
   public NullAppender field_0020;
   public boolean field_0003;
   public ConcurrentHashMapV8$KeySetView field_0009;
   public HttpPipelineReceiver httpPipelineReceiver;
   public static String field_0019;
   public long timeLastActivityMs;
   public EntityGhast$AIRandomFly field_0001;
   public EntityAIPanic field_0004;
   public static int field_0000;

   public synchronized void method_05649(Exception var1) {
      if (!this.field_0003) {
         this.field_0003 = true;
         this.method_05660(var1);
         if (this.httpPipelineSender != null) {
            this.httpPipelineSender.interrupt();
         }

         if (this.httpPipelineReceiver != null) {
            this.httpPipelineReceiver.interrupt();
         }

         try {
            if (this.socket != null) {
               this.socket.close();
            }
         } catch (IOException var3) {
         }

         this.socket = null;
         this.inputStream = null;
         this.outputStream = null;
      }
   }

   public HttpPipelineConnection(String var1, int var2, Proxy var3) {
      this.listRequestsSend = new LinkedList<>();
      this.socket = null;
      this.inputStream = null;
      this.outputStream = null;
      this.httpPipelineSender = null;
      this.httpPipelineReceiver = null;
      this.countRequests = 0;
      this.field_0007 = false;
      this.keepaliveTimeoutMs = 9181096L & -7998332200910056551L;
      this.field_0016 = 1000;
      this.timeLastActivityMs = System.currentTimeMillis();
      this.field_0003 = false;
      this.host = var1;
      this.field_0013 = var2;
      this.proxy = var3;
      this.httpPipelineSender = new HttpPipelineSender(this);
      this.httpPipelineSender.start();
      this.httpPipelineReceiver = new HttpPipelineReceiver(this);
      this.httpPipelineReceiver.start();
   }

   public synchronized void onExceptionSend(HttpPipelineRequest var1, Exception var2) {
      this.method_05649(var2);
   }

   public String[] split(String var1, char var2) {
      int var3 = var1.indexOf(var2);
      if (var3 < 0) {
         return new String[]{var1};
      } else {
         String var4 = var1.substring(0, var3);
         String var5 = var1.substring(var3 + 1);
         return new String[]{var4, var5};
      }
   }

   public synchronized boolean isClosed() {
      return this.field_0003 ? true : this.countRequests >= this.field_0016;
   }

   public synchronized HttpPipelineRequest getNextRequestReceive() {
      return this.getNextRequest(this.listRequests, false);
   }

   public synchronized OutputStream getOutputStream() {
      while (this.outputStream == null) {
         this.checkTimeout();
         this.wait(830998521L & -7388145001005038616L);
      }

      return this.outputStream;
   }

   public synchronized void onExceptionReceive(HttpPipelineRequest var1, Exception var2) {
      this.method_05649(var2);
   }

   public synchronized void onResponseReceived(HttpPipelineRequest var1, HttpResponse var2) {
      if (!this.field_0003) {
         this.field_0007 = true;
         this.onActivity();
         if (this.listRequests.size() <= 0 || this.listRequests.get(0) != var1) {
            throw new IllegalArgumentException("Response out of order: " + var1);
         }

         this.listRequests.remove(0);
         var1.setClosed(true);
         String var3 = var2.getHeader("Location");
         if (var2.getStatus() / 100 == 3 && var3 != null && var1.getHttpRequest().getRedirects() < 5) {
            try {
               var3 = this.normalizeUrl(var3, var1.getHttpRequest());
               HttpRequest var8 = HttpPipeline.makeRequest(var3, var1.getHttpRequest().getProxy());
               var8.setRedirects(var1.getHttpRequest().getRedirects() + 1);
               HttpPipelineRequest var5 = new HttpPipelineRequest(var8, var1.getHttpListener());
               HttpPipeline.addRequest(var5);
            } catch (IOException var6) {
               var1.getHttpListener().failed(var1.getHttpRequest(), var6);
            }
         } else {
            HttpListener var4 = var1.getHttpListener();
            var4.finished(var1.getHttpRequest(), var2);
         }

         this.checkResponseHeader(var2);
      }
   }

   public int getPort() {
      return this.field_0013;
   }

   public String getHost() {
      return this.host;
   }

   public synchronized boolean addRequest(HttpPipelineRequest var1) {
      if (this.isClosed()) {
         return false;
      } else {
         this.addRequest(var1, this.listRequests);
         this.addRequest(var1, this.listRequestsSend);
         this.countRequests++;
         return true;
      }
   }

   public HttpPipelineRequest getNextRequest(List<HttpPipelineRequest> var1, boolean var2) {
      while (var1.size() <= 0) {
         this.checkTimeout();
         this.wait(1346540538L & 1379305L);
      }

      this.onActivity();
      return var2 ? (HttpPipelineRequest)var1.remove(0) : (HttpPipelineRequest)var1.get(0);
   }

   public void addRequest(HttpPipelineRequest var1, List<HttpPipelineRequest> var2) {
      var2.add(var1);
      this.notifyAll();
   }

   public synchronized HttpPipelineRequest getNextRequestSend() {
      if (this.listRequestsSend.size() <= 0 && this.outputStream != null) {
         this.outputStream.flush();
      }

      return this.getNextRequest(this.listRequestsSend, true);
   }

   public synchronized InputStream getInputStream() {
      while (this.inputStream == null) {
         this.checkTimeout();
         this.wait(9212L & 1187024873L);
      }

      return this.inputStream;
   }

   public int getCountRequests() {
      return this.countRequests;
   }

   public void method_05660(Exception var1) {
      if (this.listRequests.size() > 0) {
         if (!this.field_0007) {
            HttpPipelineRequest var2 = this.listRequests.remove(0);
            var2.getHttpListener().failed(var2.getHttpRequest(), var1);
            var2.setClosed(true);
         }

         while (this.listRequests.size() > 0) {
            HttpPipelineRequest var3 = this.listRequests.remove(0);
            HttpPipeline.addRequest(var3);
         }
      }
   }

   public synchronized boolean method_05641() {
      return this.listRequests.size() > 0;
   }

   public Proxy getProxy() {
      return this.proxy;
   }

   public void onActivity() {
      this.timeLastActivityMs = System.currentTimeMillis();
   }

   public void checkTimeout() {
      if (this.socket != null) {
         long var1 = this.keepaliveTimeoutMs;
         if (this.listRequests.size() > 0) {
            var1 = 3303803202487466888L & -3303803203893079160L;
         }

         long var3 = System.currentTimeMillis();
         if (var3 > this.timeLastActivityMs + var1) {
            this.method_05649(new InterruptedException("Timeout " + var1));
         }
      }
   }

   public synchronized void onRequestSent(HttpPipelineRequest var1) {
      if (!this.field_0003) {
         this.onActivity();
      }
   }

   public HttpPipelineConnection(String var1, int var2) {
      this(var1, var2, Proxy.NO_PROXY);
   }

   public String normalizeUrl(String var1, HttpRequest var2) {
      if (patternFullUrl.matcher(var1).matches()) {
         return var1;
      } else if (var1.startsWith("//")) {
         return "http:" + var1;
      } else {
         String var3 = var2.getHost();
         if (var2.getPort() != 80) {
            var3 = var3 + ":" + var2.getPort();
         }

         if (var1.startsWith("/")) {
            return "http://" + var3 + var1;
         } else {
            String var4 = var2.getFile();
            int var5 = var4.lastIndexOf("/");
            return var5 >= 0 ? "http://" + var3 + var4.substring(0, var5 + 1) + var1 : "http://" + var3 + "/" + var1;
         }
      }
   }

   public synchronized void setSocket(Socket var1) {
      if (!this.field_0003) {
         if (this.socket != null) {
            throw new IllegalArgumentException("Already connected");
         }

         this.socket = var1;
         this.socket.setTcpNoDelay(true);
         this.inputStream = this.socket.getInputStream();
         this.outputStream = new BufferedOutputStream(this.socket.getOutputStream());
         this.onActivity();
         this.notifyAll();
      }
   }

   public void checkResponseHeader(HttpResponse var1) {
      String var2 = var1.getHeader("Connection");
      if (var2 != null && !var2.toLowerCase().equals("keep-alive")) {
         this.method_05649(new EOFException("Connection not keep-alive"));
      }

      String var3 = var1.getHeader("Keep-Alive");
      if (var3 != null) {
         String[] var4 = Config.tokenize(var3, ",;");

         for (int var5 = 0; var5 < var4.length; var5++) {
            String var6 = var4[var5];
            String[] var7 = this.split(var6, '=');
            if (var7.length >= 2) {
               if (var7[0].equals("timeout")) {
                  int var8 = Config.parseInt(var7[1], -1);
                  if (var8 > 0) {
                     this.keepaliveTimeoutMs = var8 * 1000;
                  }
               }

               if (var7[0].equals("max")) {
                  int var9 = Config.parseInt(var7[1], -1);
                  if (var9 > 0) {
                     this.field_0016 = var9;
                  }
               }
            }
         }
      }
   }
}
