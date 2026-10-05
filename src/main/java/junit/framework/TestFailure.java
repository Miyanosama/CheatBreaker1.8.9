package junit.framework;

import java.io.PrintWriter;
import java.io.StringWriter;

public class TestFailure {
   public Test fFailedTest;
   public Throwable fThrownException;

   public String toString() {
      StringBuffer var1 = new StringBuffer();
      var1.append(this.fFailedTest + ": " + this.fThrownException.getMessage());
      return var1.toString();
   }

   public String trace() {
      StringWriter var1 = new StringWriter();
      PrintWriter var2 = new PrintWriter(var1);
      this.thrownException().printStackTrace(var2);
      StringBuffer var3 = var1.getBuffer();
      return var3.toString();
   }

   public String exceptionMessage() {
      return this.thrownException().getMessage();
   }

   public Test failedTest() {
      return this.fFailedTest;
   }

   public Throwable thrownException() {
      return this.fThrownException;
   }

   public TestFailure(Test var1, Throwable var2) {
      this.fFailedTest = var1;
      this.fThrownException = var2;
   }

   public boolean isFailure() {
      return this.thrownException() instanceof AssertionFailedError;
   }
}
