package net.minecraft.util;

import net.minecraft.crash.CrashReport;

public class ReportedException extends RuntimeException {
   public CrashReport theReportedExceptionCrashReport;

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
