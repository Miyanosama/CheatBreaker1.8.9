package junit.swingui;

import junit.framework.Test;

public class TestRunner$1 implements Runnable {
   // $VF: synthetic field
   public Throwable val$t;
   // $VF: synthetic field
   public TestRunner this$0;
   // $VF: synthetic field
   public Test val$test;
   // $VF: synthetic field
   public int val$status;

   public void run() {
      switch (this.val$status) {
         case 1:
            TestRunner.access$0(this.this$0).setErrorValue(TestRunner.access$1(this.this$0).errorCount());
            TestRunner.access$2(this.this$0, this.val$test, this.val$t);
            break;
         case 2:
            TestRunner.access$0(this.this$0).setFailureValue(TestRunner.access$1(this.this$0).failureCount());
            TestRunner.access$2(this.this$0, this.val$test, this.val$t);
      }
   }

   public TestRunner$1(TestRunner var1, int var2, Test var3, Throwable var4) {
      this.this$0 = var1;
      this.val$status = var2;
      this.val$test = var3;
      this.val$t = var4;
   }
}
