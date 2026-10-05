package org.apache.log4j;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.QuietWriter;
import org.apache.log4j.spi.ErrorHandler;
import org.apache.log4j.spi.LoggingEvent;

public class WriterAppender extends AppenderSkeleton {
   public QuietWriter qw;
   public String encoding;
   public boolean immediateFlush = true;

   public void subAppend(LoggingEvent var1) {
      this.qw.write(this.layout.format(var1));
      if (this.layout.ignoresThrowable()) {
         String[] var2 = var1.getThrowableStrRep();
         if (var2 != null) {
            int var3 = var2.length;

            for (int var4 = 0; var4 < var3; var4++) {
               this.qw.write(var2[var4]);
               this.qw.write(Layout.LINE_SEP);
            }
         }
      }

      if (this.shouldFlush(var1)) {
         this.qw.flush();
      }
   }

   public boolean requiresLayout() {
      return true;
   }

   public void activateOptions() {
   }

   public OutputStreamWriter createWriter(OutputStream var1) {
      OutputStreamWriter var2 = null;
      String var3 = this.getEncoding();
      if (var3 != null) {
         try {
            var2 = new OutputStreamWriter(var1, var3);
         } catch (IOException var5) {
            if (var5 instanceof InterruptedIOException) {
               Thread.currentThread().interrupt();
            }

            LogLog.warn("Error initializing output writer.");
            LogLog.warn("Unsupported encoding?");
         }
      }

      if (var2 == null) {
         var2 = new OutputStreamWriter(var1);
      }

      return var2;
   }

   public WriterAppender(Layout var1, Writer var2) {
      this.layout = var1;
      this.setWriter(var2);
   }

   public WriterAppender() {
   }

   public void setEncoding(String var1) {
      this.encoding = var1;
   }

   public void closeWriter() {
      if (this.qw != null) {
         try {
            this.qw.close();
         } catch (IOException var2) {
            if (var2 instanceof InterruptedIOException) {
               Thread.currentThread().interrupt();
            }

            LogLog.error("Could not close " + this.qw, var2);
         }
      }
   }

   public void append(LoggingEvent var1) {
      if (this.checkEntryConditions()) {
         this.subAppend(var1);
      }
   }

   public boolean checkEntryConditions() {
      if (this.closed) {
         LogLog.warn("Not allowed to write to a closed appender.");
         return false;
      } else if (this.qw == null) {
         this.errorHandler.error("No output stream or file set for the appender named [" + this.name + "].");
         return false;
      } else if (this.layout == null) {
         this.errorHandler.error("No layout set for the appender named [" + this.name + "].");
         return false;
      } else {
         return true;
      }
   }

   public void setImmediateFlush(boolean var1) {
      this.immediateFlush = var1;
   }

   public WriterAppender(Layout var1, OutputStream var2) {
      this(var1, new OutputStreamWriter(var2));
   }

   public void reset() {
      this.closeWriter();
      this.qw = null;
   }

   public boolean shouldFlush(LoggingEvent var1) {
      return this.immediateFlush;
   }

   public synchronized void setErrorHandler(ErrorHandler var1) {
      if (var1 == null) {
         LogLog.warn("You have tried to set a null error-handler.");
      } else {
         this.errorHandler = var1;
         if (this.qw != null) {
            this.qw.setErrorHandler(var1);
         }
      }
   }

   public void writeHeader() {
      if (this.layout != null) {
         String var1 = this.layout.E_();
         if (var1 != null && this.qw != null) {
            this.qw.write(var1);
         }
      }
   }

   public synchronized void close() {
      if (!this.closed) {
         this.closed = true;
         this.writeFooter();
         this.reset();
      }
   }

   public synchronized void setWriter(Writer var1) {
      this.reset();
      this.qw = new QuietWriter(var1, this.errorHandler);
      this.writeHeader();
   }

   public String getEncoding() {
      return this.encoding;
   }

   public boolean getImmediateFlush() {
      return this.immediateFlush;
   }

   public void writeFooter() {
      if (this.layout != null) {
         String var1 = this.layout.getFooter();
         if (var1 != null && this.qw != null) {
            this.qw.write(var1);
            this.qw.flush();
         }
      }
   }
}
