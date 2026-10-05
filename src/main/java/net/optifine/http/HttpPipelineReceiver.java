package net.optifine.http;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import net.minecraft.src.Config;

public class HttpPipelineReceiver extends Thread {
   public static final String recoveredField1166 = "Content-Length";
   public static final char recoveredField1167 = 13;
   public HttpPipelineConnection httpPipelineConnection = null;
   public static final char recoveredField1168 = 10;
   public static Charset ASCII = Charset.forName("ASCII");

   @Override
   public void run() {
      while (!Thread.interrupted()) {
         HttpPipelineRequest var1 = null;

         try {
            var1 = this.httpPipelineConnection.getNextRequestReceive();
            InputStream var2 = this.httpPipelineConnection.getInputStream();
            HttpResponse var3 = this.readResponse(var2);
            this.httpPipelineConnection.onResponseReceived(var1, var3);
         } catch (InterruptedException var4) {
            return;
         } catch (Exception var5) {
            this.httpPipelineConnection.onExceptionReceive(var1, var5);
         }
      }
   }

   public HttpResponse readResponse(InputStream var1) throws java.io.IOException {
      String var2 = this.readLine(var1);
      String[] var3 = Config.tokenize(var2, " ");
      if (var3.length < 3) {
         throw new IOException("Invalid status line: " + var2);
      } else {
         String var4 = var3[0];
         int var5 = Config.parseInt(var3[1], 0);
         String var6 = var3[2];
         LinkedHashMap var7 = new LinkedHashMap();

         while (true) {
            String var8 = this.readLine(var1);
            if (var8.length() <= 0) {
               byte[] var12 = null;
               String var13 = (String)var7.get("Content-Length");
               if (var13 != null) {
                  int var14 = Config.parseInt(var13, -1);
                  if (var14 > 0) {
                     var12 = new byte[var14];
                     this.readFull(var12, var1);
                  }
               } else {
                  String var15 = (String)var7.get("Transfer-Encoding");
                  if (Config.equals(var15, "chunked")) {
                     var12 = this.readContentChunked(var1);
                  }
               }

               return new HttpResponse(var5, var2, var7, var12);
            }

            int var9 = var8.indexOf(":");
            if (var9 > 0) {
               String var10 = var8.substring(0, var9).trim();
               String var11 = var8.substring(var9 + 1).trim();
               var7.put(var10, var11);
            }
         }
      }
   }

   public HttpPipelineReceiver(HttpPipelineConnection var1) {
      super("HttpPipelineReceiver");
      this.httpPipelineConnection = var1;
   }

   public String readLine(InputStream var1) throws java.io.IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();
      int var3 = -1;
      boolean var4 = false;

      while (true) {
         int var5 = var1.read();
         if (var5 < 0) {
            break;
         }

         var2.write(var5);
         if (var3 == 13 && var5 == 10) {
            var4 = true;
            break;
         }

         var3 = var5;
      }

      byte[] var7 = var2.toByteArray();
      String var6 = new String(var7, ASCII);
      if (var4) {
         var6 = var6.substring(0, var6.length() - 2);
      }

      return var6;
   }

   public void readFull(byte[] var1, InputStream var2) throws java.io.IOException {
      int var4 = 0;

      while (var4 < var1.length) {
         int var3 = var2.read(var1, var4, var1.length - var4);
         if (var3 < 0) {
            throw new EOFException();
         }

         var4 += var3;
      }
   }

   public byte[] readContentChunked(InputStream var1) throws java.io.IOException {
      ByteArrayOutputStream var2 = new ByteArrayOutputStream();

      int var5;
      do {
         String var3 = this.readLine(var1);
         String[] var4 = Config.tokenize(var3, "; ");
         var5 = Integer.parseInt(var4[0], 16);
         byte[] var6 = new byte[var5];
         this.readFull(var6, var1);
         var2.write(var6);
         this.readLine(var1);
      } while (var5 != 0);

      return var2.toByteArray();
   }
}
