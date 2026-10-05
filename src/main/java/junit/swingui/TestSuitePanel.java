package junit.swingui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;
import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestListener;

public class TestSuitePanel extends JPanel implements TestListener {
   public TestTreeModel fModel;
   public JScrollPane fScrollTree;
   public JTree fTree;

   public void endTest(Test var1) {
      this.fModel.addRunTest(var1);
      this.fireTestChanged(var1, false);
   }

   public TestSuitePanel() {
      super(new BorderLayout());
      this.setPreferredSize(new Dimension(300, 100));
      this.fTree = new JTree();
      this.fTree.setModel(null);
      this.fTree.setRowHeight(20);
      ToolTipManager.sharedInstance().registerComponent(this.fTree);
      this.fTree.putClientProperty("JTree.lineStyle", "Angled");
      this.fScrollTree = new JScrollPane(this.fTree);
      this.add(this.fScrollTree, "Center");
   }

   public void showTestTree(Test var1) {
      this.fModel = new TestTreeModel(var1);
      this.fTree.setModel(this.fModel);
      this.fTree.setCellRenderer(new TestSuitePanel.TestTreeCellRenderer());
   }

   public void addFailure(Test var1, AssertionFailedError var2) {
      this.fModel.addFailure(var1);
      this.fireTestChanged(var1, true);
   }

   public void fireTestChanged(Test var1, boolean var2) {
      SwingUtilities.invokeLater(new TestSuitePanel$1(this, var1, var2));
   }

   public void startTest(Test var1) {
   }

   // $VF: synthetic method
   public static TestTreeModel access$0(TestSuitePanel var0) {
      return var0.fModel;
   }

   // $VF: synthetic method
   public static JTree access$1(TestSuitePanel var0) {
      return var0.fTree;
   }

   public Test getSelectedTest() {
      TreePath[] var1 = this.fTree.getSelectionPaths();
      return var1 != null && var1.length == 1 ? (Test)var1[0].getLastPathComponent() : null;
   }

   public JTree getTree() {
      return this.fTree;
   }

   public void addError(Test var1, Throwable var2) {
      this.fModel.addError(var1);
      this.fireTestChanged(var1, true);
   }

   public static class TestTreeCellRenderer extends DefaultTreeCellRenderer {
      public Icon fErrorIcon;
      public Icon fOkIcon;
      public Icon fFailureIcon;

      public void loadIcons() {
         this.fErrorIcon = TestRunner.getIconResource(this.getClass(), "icons/error.gif");
         this.fOkIcon = TestRunner.getIconResource(this.getClass(), "icons/ok.gif");
         this.fFailureIcon = TestRunner.getIconResource(this.getClass(), "icons/failure.gif");
      }

      public String stripParenthesis(Object var1) {
         String var2 = var1.toString();
         int var3 = var2.indexOf(40);
         return var3 < 1 ? var2 : var2.substring(0, var3);
      }

      public TestTreeCellRenderer() {
         this.loadIcons();
      }

      public Component getTreeCellRendererComponent(JTree var1, Object var2, boolean var3, boolean var4, boolean var5, int var6, boolean var7) {
         Component var8 = super.getTreeCellRendererComponent(var1, var2, var3, var4, var5, var6, var7);
         TreeModel var9 = var1.getModel();
         if (var9 instanceof TestTreeModel) {
            TestTreeModel var10 = (TestTreeModel)var9;
            Test var11 = (Test)var2;
            String var12 = "";
            if (var10.method_25907(var11)) {
               if (this.fFailureIcon != null) {
                  this.setIcon(this.fFailureIcon);
               }

               var12 = " - Failed";
            } else if (var10.method_25920(var11)) {
               if (this.fErrorIcon != null) {
                  this.setIcon(this.fErrorIcon);
               }

               var12 = " - Error";
            } else if (var10.wasRun(var11)) {
               if (this.fOkIcon != null) {
                  this.setIcon(this.fOkIcon);
               }

               var12 = " - Passed";
            }

            if (var8 instanceof JComponent) {
               ((JComponent)var8).setToolTipText(this.getText() + var12);
            }
         }

         this.setText(this.stripParenthesis(var2));
         return var8;
      }
   }
}
