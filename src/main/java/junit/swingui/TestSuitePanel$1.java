package junit.swingui;

import java.util.Vector;
import javax.swing.tree.TreePath;
import junit.framework.Test;

public class TestSuitePanel$1 implements Runnable {
   // $VF: synthetic field
   public Test val$test;
   // $VF: synthetic field
   public TestSuitePanel this$0;
   // $VF: synthetic field
   public boolean val$expand;

   public TestSuitePanel$1(TestSuitePanel var1, Test var2, boolean var3) {
      this.this$0 = var1;
      this.val$test = var2;
      this.val$expand = var3;
   }

   public void run() {
      Vector var1 = new Vector();
      int var2 = TestSuitePanel.access$0(this.this$0).findTest(this.val$test, (Test)TestSuitePanel.access$0(this.this$0).getRoot(), var1);
      if (var2 >= 0) {
         Object[] var3 = new Object[var1.size()];
         var1.copyInto(var3);
         TreePath var4 = new TreePath(var3);
         TestSuitePanel.access$0(this.this$0).fireNodeChanged(var4, var2);
         if (this.val$expand) {
            Object[] var5 = new Object[var1.size() + 1];
            var1.copyInto(var5);
            var5[var1.size()] = TestSuitePanel.access$0(this.this$0).getChild(var4.getLastPathComponent(), var2);
            TreePath var6 = new TreePath(var5);
            TestSuitePanel.access$1(this.this$0).scrollPathToVisible(var6);
         }
      }
   }
}
