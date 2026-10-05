package junit.swingui;

import net.minecraft.block.BlockSkull;

public class TestRunner$2 implements Runnable {
   public BlockSkull field_0000;
   public TestRunner this$0;

   public void run() {
      if (TestRunner.access$1(this.this$0) != null) {
         TestRunner.access$0(this.this$0).setRunValue(TestRunner.access$1(this.this$0).runCount());
         TestRunner.access$3(this.this$0).step(TestRunner.access$1(this.this$0).runCount(), TestRunner.access$1(this.this$0).wasSuccessful());
      }
   }

   public TestRunner$2(TestRunner var1) {
      this.this$0 = var1;
   }
}
