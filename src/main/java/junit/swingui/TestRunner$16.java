package junit.swingui;

import junit.framework.Test;
import junit.swingui.TestRunner;

public class TestRunner$16 extends Thread {
   public Test recoveredField3719;
   public TestRunner recoveredField3720;

   public TestRunner$16(TestRunner var1, String var2, Test var3) {
      super(var2);
      this.recoveredField3720 = var1;
      this.recoveredField3719 = var3;
   }

   public void run() {
      TestRunner.access$9(this.recoveredField3720, this.recoveredField3719);
      TestRunner.method_00059(this.recoveredField3720, "Running...");
      long var1 = System.currentTimeMillis();
      this.recoveredField3719.run(TestRunner.access$1(this.recoveredField3720));
      if (TestRunner.access$1(this.recoveredField3720).shouldStop()) {
         TestRunner.method_00067(this.recoveredField3720, "Stopped");
      } else {
         long var3 = System.currentTimeMillis();
         long var5 = var3 - var1;
         TestRunner.method_00059(this.recoveredField3720, "Finished: " + this.recoveredField3720.elapsedTimeAsString(var5) + " seconds");
      }

      this.recoveredField3720.method_00048(this.recoveredField3719);
      TestRunner.access$13(this.recoveredField3720, TestRunner.access$12(this.recoveredField3720), "Run");
      TestRunner.access$1402(this.recoveredField3720, null);
      System.gc();
   }
}
