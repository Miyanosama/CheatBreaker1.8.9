package junit.swingui;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import net.minecraft.block.BlockWall;
import net.minecraft.dispenser.BehaviorDefaultDispenseItem;

public class TestHierarchyRunView$1 implements TreeSelectionListener {
   public TestHierarchyRunView this$0;
   public BehaviorDefaultDispenseItem field_0002;
   public BlockWall field_0000;

   public TestHierarchyRunView$1(TestHierarchyRunView var1) {
      this.this$0 = var1;
   }

   public void valueChanged(TreeSelectionEvent var1) {
      this.this$0.testSelected();
   }
}
