package junit.extensions;

import junit.framework.TestCase;

public class ExceptionTestCase extends TestCase {
   public Class fExpected;

   public ExceptionTestCase(String var1, Class var2) {
      super(var1);
      this.fExpected = var2;
   }

   public void runTest() throws java.lang.Throwable {
      try {
         super.runTest();
      } catch (Exception var2) {
         if (this.fExpected.isAssignableFrom(var2.getClass())) {
            return;
         }

         throw var2;
      }

      fail("Expected exception " + this.fExpected);
   }
}
