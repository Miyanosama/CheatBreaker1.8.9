package org.apache.log4j;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.io.Writer;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.QuietWriter;

public class FileAppender extends WriterAppender {
   public int bufferSize;
   public String fileName;
   public boolean fileAppend = true;
   public boolean bufferedIO;

   public void setFile(String var1) {
      String var2 = var1.trim();
      this.fileName = var2;
   }

   public FileAppender(Layout var1, String var2, boolean var3) throws java.io.IOException {
      this.fileName = null;
      this.bufferedIO = false;
      this.bufferSize = 8192;
      this.layout = var1;
      this.setFile(var2, var3, false, this.bufferSize);
   }

   public FileAppender(Layout var1, String var2) throws java.io.IOException {
      this(var1, var2, true);
   }

   public void setAppend(boolean var1) {
      this.fileAppend = var1;
   }

   public void setBufferSize(int var1) {
      this.bufferSize = var1;
   }

   public void setBufferedIO(boolean var1) {
      this.bufferedIO = var1;
      if (var1) {
         this.immediateFlush = false;
      }
   }

   public boolean getBufferedIO() {
      return this.bufferedIO;
   }

   public void reset() {
      this.closeFile();
      this.fileName = null;
      super.reset();
   }

   public void activateOptions() {
      if (this.fileName != null) {
         try {
            this.setFile(this.fileName, this.fileAppend, this.bufferedIO, this.bufferSize);
         } catch (IOException var2) {
            this.errorHandler.error("setFile(" + this.fileName + "," + this.fileAppend + ") call failed.", var2, 4);
         }
      } else {
         LogLog.warn("File option not set for appender [" + this.name + "].");
         LogLog.warn("Are you using FileAppender instead of ConsoleAppender?");
      }
   }

   public FileAppender() {
      this.fileName = null;
      this.bufferedIO = false;
      this.bufferSize = 8192;
   }

   public FileAppender(Layout var1, String var2, boolean var3, boolean var4, int var5) throws java.io.IOException {
      this.fileName = null;
      this.bufferedIO = false;
      this.bufferSize = 8192;
      this.layout = var1;
      this.setFile(var2, var3, var4, var5);
   }

   public int getBufferSize() {
      return this.bufferSize;
   }

   public void closeFile() {
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

   public boolean getAppend() {
      return this.fileAppend;
   }

   public String getFile() {
      return this.fileName;
   }

   public void setQWForFiles(Writer var1) {
      this.qw = new QuietWriter(var1, this.errorHandler);
   }

   public synchronized void setFile(String var1, boolean var2, boolean var3, int var4) throws java.io.IOException {
      LogLog.debug("setFile called: " + var1 + ", " + var2);
      if (var3) {
         this.setImmediateFlush(false);
      }

      this.reset();
      Object var5 = null;

      try {
         var5 = new FileOutputStream(var1, var2);
      } catch (FileNotFoundException var9) {
         label29: {
            String var7 = new File(var1).getParent();
            if (var7 != null) {
               File var8 = new File(var7);
               if (!var8.exists() && var8.mkdirs()) {
                  var5 = new FileOutputStream(var1, var2);
                  break label29;
               }

               throw var9;
            }

            throw var9;
         }
      }

      Object var6 = this.createWriter((OutputStream)var5);
      if (var3) {
         var6 = new BufferedWriter((Writer)var6, var4);
      }

      this.setQWForFiles((Writer)var6);
      this.fileName = var1;
      this.fileAppend = var2;
      this.bufferedIO = var3;
      this.bufferSize = var4;
      this.writeHeader();
      LogLog.debug("setFile ended");
   }
}
