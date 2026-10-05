package junit.framework;

import junit.framework.Protectable;
import junit.framework.TestCase;
import junit.framework.TestResult;

public class TestResult$1 implements Protectable {
   public TestCase recoveredField2276;
   public TestResult recoveredField2277;

   public void protect() throws java.lang.Throwable {
      this.recoveredField2276.runBare();
   }

   public TestResult$1(TestResult var1, TestCase var2) {
      this.recoveredField2277 = var1;
      this.recoveredField2276 = var2;
   }
}
