package junit.swingui;

import junit.framework.Test;

public class TestRunner$18 implements Runnable {
   // $VF: synthetic field
   public Test val$test;
   // $VF: synthetic field
   public TestRunner this$0;

   public void run() {
      int var1 = this.val$test.j_();
      TestRunner.access$3(this.this$0).start(var1);
      TestRunner.access$0(this.this$0).setTotal(var1);
   }

   public TestRunner$18(TestRunner var1, Test var2) {
      this.this$0 = var1;
      this.val$test = var2;
   }
}
