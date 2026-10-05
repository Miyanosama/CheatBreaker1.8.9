package org.apache.log4j.net;

import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$NotEnoughDataDecoderException;
import java.io.IOException;
import java.io.InterruptedIOException;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;
import recovered.unidentified.UnidentifiedClass1135;

public class TelnetAppender extends AppenderSkeleton {
   public UnidentifiedClass1135 field_0003;
   public int port = 23;
   public HttpPostRequestDecoder$NotEnoughDataDecoderException field_0001;
   public TelnetAppender$SocketHandler sh;

   public void close() {
      if (this.sh != null) {
         this.sh.close();

         try {
            this.sh.join();
         } catch (InterruptedException var2) {
            Thread.currentThread().interrupt();
         }
      }
   }

   public void activateOptions() {
      try {
         this.sh = new TelnetAppender$SocketHandler(this, this.port);
         this.sh.start();
      } catch (InterruptedIOException var2) {
         Thread.currentThread().interrupt();
         var2.printStackTrace();
      } catch (IOException var3) {
         var3.printStackTrace();
      } catch (RuntimeException var4) {
         var4.printStackTrace();
      }

      super.activateOptions();
   }

   public void append(LoggingEvent var1) {
      if (this.sh != null) {
         this.sh.send(this.layout.format(var1));
         if (this.layout.ignoresThrowable()) {
            String[] var2 = var1.getThrowableStrRep();
            if (var2 != null) {
               StringBuffer var3 = new StringBuffer();

               for (int var4 = 0; var4 < var2.length; var4++) {
                  var3.append(var2[var4]);
                  var3.append("\r\n");
               }

               this.sh.send(var3.toString());
            }
         }
      }
   }

   public int getPort() {
      return this.port;
   }

   public boolean requiresLayout() {
      return true;
   }

   public void setPort(int var1) {
      this.port = var1;
   }
}
