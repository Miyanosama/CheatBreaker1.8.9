package junit.extensions;

import junit.framework.Test;
import junit.framework.TestResult;

public class TestSetup extends TestDecorator {
   public void setUp() throws java.lang.Exception {
   }

   public TestSetup(Test var1) {
      super(var1);
   }

   public void run(TestResult var1) {
      TestSetup$1 var2 = new TestSetup$1(this, var1);
      var1.runProtected(this, var2);
   }

   public void tearDown() throws java.lang.Exception {
   }
}
