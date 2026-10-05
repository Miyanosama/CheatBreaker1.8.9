package junit.awtui;

import junit.awtui.TestRunner;
import junit.framework.Test;

public class TestRunner$10 extends Thread {
   public TestRunner recoveredField3035;
   public Test recoveredField3036;

   public void run() {
      this.recoveredField3035.fTestResult = this.recoveredField3035.createTestResult();
      this.recoveredField3035.fTestResult.addListener(this.recoveredField3035);
      this.recoveredField3035.fProgressIndicator.start(this.recoveredField3036.j_());
      TestRunner.method_28038(this.recoveredField3035, "Running...");
      long var1 = System.currentTimeMillis();
      this.recoveredField3036.run(this.recoveredField3035.fTestResult);
      if (this.recoveredField3035.fTestResult.shouldStop()) {
         TestRunner.method_28030(this.recoveredField3035, "Stopped");
      } else {
         long var3 = System.currentTimeMillis();
         long var5 = var3 - var1;
         TestRunner.method_28038(this.recoveredField3035, "Finished: " + this.recoveredField3035.elapsedTimeAsString(var5) + " seconds");
      }

      this.recoveredField3035.fTestResult = null;
      this.recoveredField3035.fRun.setLabel("Run");
      this.recoveredField3035.fRunner = null;
      System.gc();
   }

   public TestRunner$10(TestRunner var1, Test var2) {
      this.recoveredField3035 = var1;
      this.recoveredField3036 = var2;
   }
}
