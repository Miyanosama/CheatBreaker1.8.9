package org.apache.log4j.chainsaw;

import org.apache.log4j.Priority;
import org.apache.log4j.spi.LoggingEvent;

public class EventDetails {
   public String mNDC;
   public long mTimeStamp;
   public String mLocationDetails;
   public String mCategoryName;
   public String mThreadName;
   public Priority mPriority;
   public String[] mThrowableStrRep;
   public String mMessage;

   public EventDetails(LoggingEvent var1) {
      this(
         var1.timeStamp,
         var1.getLevel(),
         var1.getLoggerName(),
         var1.getNDC(),
         var1.getThreadName(),
         var1.getRenderedMessage(),
         var1.getThrowableStrRep(),
         var1.getLocationInformation() == null ? null : var1.getLocationInformation().fullInfo
      );
   }

   public String getThreadName() {
      return this.mThreadName;
   }

   public String[] getThrowableStrRep() {
      return this.mThrowableStrRep;
   }

   public Priority getPriority() {
      return this.mPriority;
   }

   public String getLocationDetails() {
      return this.mLocationDetails;
   }

   public EventDetails(long var1, Priority var3, String var4, String var5, String var6, String var7, String[] var8, String var9) {
      this.mTimeStamp = var1;
      this.mPriority = var3;
      this.mCategoryName = var4;
      this.mNDC = var5;
      this.mThreadName = var6;
      this.mMessage = var7;
      this.mThrowableStrRep = var8;
      this.mLocationDetails = var9;
   }

   public long getTimeStamp() {
      return this.mTimeStamp;
   }

   public String getCategoryName() {
      return this.mCategoryName;
   }

   public String getMessage() {
      return this.mMessage;
   }

   public String getNDC() {
      return this.mNDC;
   }
}
