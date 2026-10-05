package org.apache.log4j;

import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.Writer;
import org.apache.log4j.helpers.CountingQuietWriter;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.spi.LoggingEvent;

public class RollingFileAppender extends FileAppender {
   public long nextRollover;
   public long maxFileSize = 10485760L;
   public int maxBackupIndex = 1;

   public void setMaximumFileSize(long var1) {
      this.maxFileSize = var1;
   }

   public void setQWForFiles(Writer var1) {
      this.qw = new CountingQuietWriter(var1, this.errorHandler);
   }

   public int getMaxBackupIndex() {
      return this.maxBackupIndex;
   }

   public void subAppend(LoggingEvent var1) {
      super.subAppend(var1);
      if (this.fileName != null && this.qw != null) {
         long var2 = ((CountingQuietWriter)this.qw).getCount();
         if (var2 >= this.maxFileSize && var2 >= this.nextRollover) {
            this.rollOver();
         }
      }
   }

   public void rollOver() {
      if (this.qw != null) {
         long var3 = ((CountingQuietWriter)this.qw).getCount();
         LogLog.debug("rolling over count=" + var3);
         this.nextRollover = var3 + this.maxFileSize;
      }

      LogLog.debug("maxBackupIndex=" + this.maxBackupIndex);
      boolean var10 = true;
      if (this.maxBackupIndex > 0) {
         File var2 = new File(this.fileName + '.' + this.maxBackupIndex);
         if (var2.exists()) {
            var10 = var2.delete();
         }

         for (int var4 = this.maxBackupIndex - 1; var4 >= 1 && var10; var4--) {
            var2 = new File(this.fileName + "." + var4);
            if (var2.exists()) {
               File var1 = new File(this.fileName + '.' + (var4 + 1));
               LogLog.debug("Renaming file " + var2 + " to " + var1);
               var10 = var2.renameTo(var1);
            }
         }

         if (var10) {
            File var7 = new File(this.fileName + "." + 1);
            this.closeFile();
            var2 = new File(this.fileName);
            LogLog.debug("Renaming file " + var2 + " to " + var7);
            var10 = var2.renameTo(var7);
            if (!var10) {
               try {
                  this.setFile(this.fileName, true, this.bufferedIO, this.bufferSize);
               } catch (IOException var6) {
                  if (var6 instanceof InterruptedIOException) {
                     Thread.currentThread().interrupt();
                  }

                  LogLog.error("setFile(" + this.fileName + ", true) call failed.", var6);
               }
            }
         }
      }

      if (var10) {
         try {
            this.setFile(this.fileName, false, this.bufferedIO, this.bufferSize);
            this.nextRollover = 0L;
         } catch (IOException var5) {
            if (var5 instanceof InterruptedIOException) {
               Thread.currentThread().interrupt();
            }

            LogLog.error("setFile(" + this.fileName + ", false) call failed.", var5);
         }
      }
   }

   public void setMaxBackupIndex(int var1) {
      this.maxBackupIndex = var1;
   }

   public synchronized void setFile(String var1, boolean var2, boolean var3, int var4) throws java.io.IOException {
      super.setFile(var1, var2, this.bufferedIO, this.bufferSize);
      if (var2) {
         File var5 = new File(var1);
         ((CountingQuietWriter)this.qw).setCount(var5.length());
      }
   }

   public long getMaximumFileSize() {
      return this.maxFileSize;
   }

   public RollingFileAppender(Layout var1, String var2, boolean var3) throws java.io.IOException {
      super(var1, var2, var3);
      this.nextRollover = 0L;
   }

   public RollingFileAppender() {
      this.nextRollover = 0L;
   }

   public void setMaxFileSize(String var1) {
      this.maxFileSize = OptionConverter.toFileSize(var1, this.maxFileSize + 1L);
   }

   public RollingFileAppender(Layout var1, String var2) throws java.io.IOException {
      super(var1, var2);
      this.nextRollover = 0L;
   }
}
