package junit.extensions;

import com.cheatbreaker.client.ui.module.CBProfileCreateGui;
import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;

public class ActiveTestSuite extends TestSuite {
   public CBProfileCreateGui field_0000;
   public volatile int fActiveTestDeathCount;

   public ActiveTestSuite() {
   }

   public ActiveTestSuite(String var1) {
      super(var1);
   }

   public synchronized void method_10853() {
      this.fActiveTestDeathCount++;
      this.notifyAll();
   }

   public void runTest(Test var1, TestResult var2) {
      ActiveTestSuite$1 var3 = new ActiveTestSuite$1(this, var1, var2);
      var3.start();
   }

   public ActiveTestSuite(Class var1, String var2) {
      super(var1, var2);
   }

   public ActiveTestSuite(Class var1) {
      super(var1);
   }

   public void run(TestResult var1) {
      this.fActiveTestDeathCount = 0;
      super.run(var1);
      this.waitUntilFinished();
   }

   public synchronized void waitUntilFinished() {
      while (this.fActiveTestDeathCount < this.testCount()) {
         try {
            this.wait();
         } catch (InterruptedException var2) {
            return;
         }
      }
   }
}
