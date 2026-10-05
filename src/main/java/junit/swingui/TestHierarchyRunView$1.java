package junit.swingui;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

public class TestHierarchyRunView$1 implements TreeSelectionListener {
   // $VF: synthetic field
   public TestHierarchyRunView this$0;

   public TestHierarchyRunView$1(TestHierarchyRunView var1) {
      this.this$0 = var1;
   }

   public void valueChanged(TreeSelectionEvent var1) {
      this.this$0.testSelected();
   }
}
