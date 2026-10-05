package junit.swingui;

import java.util.Vector;
import javax.swing.Icon;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.tree.TreePath;
import junit.framework.Test;
import junit.framework.TestResult;

public class TestHierarchyRunView implements TestRunView {
   public TestRunContext fTestContext;
   public TestSuitePanel fTreeBrowser;

   public void revealFailure(Test var1) {
      JTree var2 = this.fTreeBrowser.getTree();
      TestTreeModel var3 = (TestTreeModel)var2.getModel();
      Vector var4 = new Vector();
      int var5 = var3.findTest(var1, (Test)var3.getRoot(), var4);
      if (var5 >= 0) {
         Object[] var6 = new Object[var4.size() + 1];
         var4.copyInto(var6);
         Object var7 = var6[var4.size() - 1];
         var6[var4.size()] = var3.getChild(var7, var5);
         TreePath var8 = new TreePath(var6);
         var2.setSelectionPath(var8);
         var2.makeVisible(var8);
      }
   }

   public void testSelected() {
      this.fTestContext.handleTestSelected(this.getSelectedTest());
   }

   public Test getSelectedTest() {
      return this.fTreeBrowser.getSelectedTest();
   }

   public void activate() {
      this.testSelected();
   }

   public void aboutToStart(Test var1, TestResult var2) {
      this.fTreeBrowser.showTestTree(var1);
      var2.addListener(this.fTreeBrowser);
   }

   public void addTab(JTabbedPane var1) {
      Icon var2 = TestRunner.getIconResource(this.getClass(), "icons/hierarchy.gif");
      var1.addTab("Test Hierarchy", var2, this.fTreeBrowser, "The test hierarchy");
   }

   public TestHierarchyRunView(TestRunContext var1) {
      this.fTestContext = var1;
      this.fTreeBrowser = new TestSuitePanel();
      this.fTreeBrowser.getTree().addTreeSelectionListener(new TestHierarchyRunView$1(this));
   }

   public void runFinished(Test var1, TestResult var2) {
      var2.removeListener(this.fTreeBrowser);
   }
}
