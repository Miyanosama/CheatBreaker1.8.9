package org.apache.log4j.helpers;

import io.netty.channel.AbstractChannelHandlerContext$12;
import java.io.FilterWriter;
import java.io.Writer;
import org.apache.log4j.spi.ErrorHandler;
import org.java_websocket.extensions.ExtensionRequestData;

public class QuietWriter extends FilterWriter {
   public ErrorHandler errorHandler;
   public ExtensionRequestData field_0002;
   public AbstractChannelHandlerContext$12 field_0001;

   public void setErrorHandler(ErrorHandler var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Attempted to set null ErrorHandler.");
      } else {
         this.errorHandler = var1;
      }
   }

   public void write(String var1) {
      if (var1 != null) {
         try {
            this.out.write(var1);
         } catch (Exception var3) {
            this.errorHandler.error("Failed to write [" + var1 + "].", var3, 1);
         }
      }
   }

   public void flush() {
      try {
         this.out.flush();
      } catch (Exception var2) {
         this.errorHandler.error("Failed to flush writer,", var2, 2);
      }
   }

   public QuietWriter(Writer var1, ErrorHandler var2) {
      super(var1);
      this.setErrorHandler(var2);
   }
}
