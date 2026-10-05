package junit.extensions;

import junit.framework.Protectable;
import junit.framework.TestResult;
import junit.swingui.FailureRunView$FailureListCellRenderer;
import net.minecraft.inventory.ContainerRepair;

public class TestSetup$1 implements Protectable {
   public ContainerRepair field_0001;
   public TestResult val$result;
   public TestSetup this$0;
   public FailureRunView$FailureListCellRenderer field_0002;

   public TestSetup$1(TestSetup var1, TestResult var2) {
      this.this$0 = var1;
      this.val$result = var2;
   }

   public void protect() {
      this.this$0.setUp();
      this.this$0.basicRun(this.val$result);
      this.this$0.tearDown();
   }
}
