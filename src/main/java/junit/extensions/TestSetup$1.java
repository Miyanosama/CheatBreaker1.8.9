package junit.extensions;

import junit.framework.Protectable;
import junit.framework.TestResult;

public class TestSetup$1 implements Protectable {
   // $VF: synthetic field
   public TestResult val$result;
   // $VF: synthetic field
   public TestSetup this$0;

   public TestSetup$1(TestSetup var1, TestResult var2) {
      this.this$0 = var1;
      this.val$result = var2;
   }

   public void protect() throws java.lang.Exception {
      this.this$0.setUp();
      this.this$0.basicRun(this.val$result);
      this.this$0.tearDown();
   }
}
