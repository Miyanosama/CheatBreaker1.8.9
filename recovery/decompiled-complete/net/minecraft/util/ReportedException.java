package net.minecraft.util;

import io.netty.channel.local.LocalChannel$LocalUnsafe;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.stream.MetadataAchievement;
import net.minecraft.crash.CrashReport;

public class ReportedException extends RuntimeException {
   public CrashReport theReportedExceptionCrashReport;
   public ThreadDownloadImageData field_0003;
   public LocalChannel$LocalUnsafe field_0000;
   public MetadataAchievement field_0002;

   @Override
   public Throwable getCause() {
      return this.theReportedExceptionCrashReport.getCrashCause();
   }

   public CrashReport getCrashReport() {
      return this.theReportedExceptionCrashReport;
   }

   @Override
   public String getMessage() {
      return this.theReportedExceptionCrashReport.getDescription();
   }

   public ReportedException(CrashReport var1) {
      this.theReportedExceptionCrashReport = var1;
   }
}
