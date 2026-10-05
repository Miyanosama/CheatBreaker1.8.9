package junit.swingui;

import junit.framework.Test;
import net.minecraft.entity.passive.EntityRabbit$RabbitMoveHelper;
import net.optifine.CustomLoadingScreens;

public class TestRunner$18 implements Runnable {
   public Test val$test;
   public TestRunner this$0;
   public EntityRabbit$RabbitMoveHelper field_0000;
   public CustomLoadingScreens field_0002;

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
