package junit.extensions;

import junit.framework.Test;
import junit.framework.TestResult;

public class ActiveTestSuite$1 extends Thread {
   // $VF: synthetic field
   public TestResult val$result;
   // $VF: synthetic field
   public ActiveTestSuite this$0;
   // $VF: synthetic field
   public Test val$test;

   public ActiveTestSuite$1(ActiveTestSuite var1, Test var2, TestResult var3) {
      this.this$0 = var1;
      this.val$test = var2;
      this.val$result = var3;
   }

   public void run() {
      try {
         this.val$test.run(this.val$result);
      } finally {
         this.this$0.runFinished();
      }
   }
}
