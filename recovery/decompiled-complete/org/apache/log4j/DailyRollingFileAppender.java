package org.apache.log4j;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.base64.Base64Encoder;
import io.netty.util.internal.AppendableCharSequence;
import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import net.minecraft.client.renderer.block.model.ModelBlock$Deserializer;
import net.minecraft.client.resources.SimpleResource;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.world.biome.BiomeGenMesa;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

public class DailyRollingFileAppender extends FileAppender {
   public static int field_0021;
   public int checkPeriod;
   public Date now;
   public Base64Encoder field_0013;
   public static int field_0008;
   public static int field_0016;
   public AppendableCharSequence field_0019;
   public static int field_0003;
   public static int field_0007;
   public EntityGuardian field_0011;
   public String datePattern = "'.'yyyy-MM-dd";
   public String scheduledFilename;
   public SimpleResource field_0001;
   public static TimeZone gmtTimeZone = TimeZone.getTimeZone("GMT");
   public static int field_0000;
   public static int field_0017;
   public ByteBuf field_0012;
   public BiomeGenMesa field_0015;
   public RollingCalendar rc;
   public SimpleDateFormat sdf;
   public ModelBlock$Deserializer field_0014;
   public long nextCheck;

   public String getDatePattern() {
      return this.datePattern;
   }

   public DailyRollingFileAppender() {
      this.nextCheck = System.currentTimeMillis() - (-3302530671753815679L & 3302530669711762035L);
      this.now = new Date();
      this.rc = new RollingCalendar();
      this.checkPeriod = -1;
   }

   public void setDatePattern(String var1) {
      this.datePattern = var1;
   }

   public int computeCheckPeriod() {
      RollingCalendar var1 = new RollingCalendar(gmtTimeZone, Locale.getDefault());
      Date var2 = new Date(-1237200152263917568L & 68290602L);
      if (this.datePattern != null) {
         for (int var3 = 0; var3 <= 5; var3++) {
            SimpleDateFormat var4 = new SimpleDateFormat(this.datePattern);
            var4.setTimeZone(gmtTimeZone);
            String var5 = var4.format(var2);
            var1.setType(var3);
            Date var6 = new Date(var1.getNextCheckMillis(var2));
            String var7 = var4.format(var6);
            if (var5 != null && var7 != null && !var5.equals(var7)) {
               return var3;
            }
         }
      }

      return -1;
   }

   public void subAppend(LoggingEvent var1) {
      long var2 = System.currentTimeMillis();
      if (var2 >= this.nextCheck) {
         this.now.setTime(var2);
         this.nextCheck = this.rc.getNextCheckMillis(this.now);

         try {
            this.rollOver();
         } catch (IOException var5) {
            if (var5 instanceof InterruptedIOException) {
               Thread.currentThread().interrupt();
            }

            LogLog.error("rollOver() failed.", var5);
         }
      }

      super.subAppend(var1);
   }

   public DailyRollingFileAppender(Layout var1, String var2, String var3) {
      super(var1, var2, true);
      this.nextCheck = System.currentTimeMillis() - (1608872243455983617L & -1608872244794047999L);
      this.now = new Date();
      this.rc = new RollingCalendar();
      this.checkPeriod = -1;
      this.datePattern = var3;
      this.activateOptions();
   }

   public void activateOptions() {
      super.activateOptions();
      if (this.datePattern != null && this.fileName != null) {
         this.now.setTime(System.currentTimeMillis());
         this.sdf = new SimpleDateFormat(this.datePattern);
         int var1 = this.computeCheckPeriod();
         this.printPeriodicity(var1);
         this.rc.setType(var1);
         File var2 = new File(this.fileName);
         this.scheduledFilename = this.fileName + this.sdf.format(new Date(var2.lastModified()));
      } else {
         LogLog.error("Either File or DatePattern options are not set for appender [" + this.name + "].");
      }
   }

   public void rollOver() {
      if (this.datePattern == null) {
         this.errorHandler.error("Missing DatePattern option in rollOver().");
      } else {
         String var1 = this.fileName + this.sdf.format(this.now);
         if (!this.scheduledFilename.equals(var1)) {
            this.closeFile();
            File var2 = new File(this.scheduledFilename);
            if (var2.exists()) {
               var2.delete();
            }

            File var3 = new File(this.fileName);
            boolean var4 = var3.renameTo(var2);
            if (var4) {
               LogLog.debug(this.fileName + " -> " + this.scheduledFilename);
            } else {
               LogLog.error("Failed to rename [" + this.fileName + "] to [" + this.scheduledFilename + "].");
            }

            try {
               this.setFile(this.fileName, true, this.bufferedIO, this.bufferSize);
            } catch (IOException var6) {
               this.errorHandler.error("setFile(" + this.fileName + ", true) call failed.");
            }

            this.scheduledFilename = var1;
         }
      }
   }

   public void printPeriodicity(int var1) {
      switch (var1) {
         case 0:
            LogLog.debug("Appender [" + this.name + "] to be rolled every minute.");
            break;
         case 1:
            LogLog.debug("Appender [" + this.name + "] to be rolled on top of every hour.");
            break;
         case 2:
            LogLog.debug("Appender [" + this.name + "] to be rolled at midday and midnight.");
            break;
         case 3:
            LogLog.debug("Appender [" + this.name + "] to be rolled at midnight.");
            break;
         case 4:
            LogLog.debug("Appender [" + this.name + "] to be rolled at start of week.");
            break;
         case 5:
            LogLog.debug("Appender [" + this.name + "] to be rolled at start of every month.");
            break;
         default:
            LogLog.warn("Unknown periodicity for appender [" + this.name + "].");
      }
   }
}
