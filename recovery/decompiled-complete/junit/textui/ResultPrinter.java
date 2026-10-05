package junit.textui;

import java.io.PrintStream;
import java.text.NumberFormat;
import java.util.Enumeration;
import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestFailure;
import junit.framework.TestListener;
import junit.framework.TestResult;
import junit.runner.BaseTestRunner;
import org.apache.log4j.Category;
import recovered.unidentified.UnidentifiedClass3953;

public class ResultPrinter implements TestListener {
   public Category field_0001;
   public PrintStream fWriter;
   public UnidentifiedClass3953 field_0000;
   public int fColumn = 0;

   public void printErrors(TestResult var1) {
      this.printDefects(var1.errors(), var1.errorCount(), "error");
   }

   public void printDefect(TestFailure var1, int var2) {
      this.printDefectHeader(var1, var2);
      this.printDefectTrace(var1);
   }

   public void printDefectHeader(TestFailure var1, int var2) {
      this.getWriter().print(var2 + ") " + var1.failedTest());
   }

   public void startTest(Test var1) {
      this.getWriter().print(".");
      if (this.fColumn++ >= 40) {
         this.getWriter().println();
         this.fColumn = 0;
      }
   }

   public void printFailures(TestResult var1) {
      this.printDefects(var1.failures(), var1.failureCount(), "failure");
   }

   public String elapsedTimeAsString(long var1) {
      return NumberFormat.getInstance().format(var1 / 1000.0);
   }

   public ResultPrinter(PrintStream var1) {
      this.fWriter = var1;
   }

   public synchronized void print(TestResult var1, long var2) {
      this.printHeader(var2);
      this.printErrors(var1);
      this.printFailures(var1);
      this.printFooter(var1);
   }

   public void endTest(Test var1) {
   }

   public void addFailure(Test var1, AssertionFailedError var2) {
      this.getWriter().print("F");
   }

   public PrintStream getWriter() {
      return this.fWriter;
   }

   public void printHeader(long var1) {
      this.getWriter().println();
      this.getWriter().println("Time: " + this.elapsedTimeAsString(var1));
   }

   public void printDefectTrace(TestFailure var1) {
      this.getWriter().print(BaseTestRunner.getFilteredTrace(var1.trace()));
   }

   public void printDefects(Enumeration var1, int var2, String var3) {
      if (var2 != 0) {
         if (var2 == 1) {
            this.getWriter().println("There was " + var2 + " " + var3 + ":");
         } else {
            this.getWriter().println("There were " + var2 + " " + var3 + "s:");
         }

         for (int var4 = 1; var1.hasMoreElements(); var4++) {
            this.printDefect((TestFailure)var1.nextElement(), var4);
         }
      }
   }

   public void printWaitPrompt() {
      this.getWriter().println();
      this.getWriter().println("<RETURN> to continue");
   }

   public void printFooter(TestResult var1) {
      if (var1.wasSuccessful()) {
         this.getWriter().println();
         this.getWriter().print("OK");
         this.getWriter().println(" (" + var1.runCount() + " test" + (var1.runCount() == 1 ? "" : "s") + ")");
      } else {
         this.getWriter().println();
         this.getWriter().println("FAILURES!!!");
         this.getWriter().println("Tests run: " + var1.runCount() + ",  Failures: " + var1.failureCount() + ",  Errors: " + var1.errorCount());
      }

      this.getWriter().println();
   }

   public void addError(Test var1, Throwable var2) {
      this.getWriter().print("E");
   }
}
