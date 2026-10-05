package junit.swingui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.ToolTipManager;
import javax.swing.tree.TreePath;
import junit.framework.AssertionFailedError;
import junit.framework.Test;
import junit.framework.TestListener;
import net.minecraft.block.BlockHopper;
import net.minecraft.client.renderer.EnumFaceDirection;
import net.minecraft.entity.EnumCreatureType;
import net.optifine.http.HttpRequest;

public class TestSuitePanel extends JPanel implements TestListener {
   public TestTreeModel fModel;
   public JScrollPane fScrollTree;
   public BlockHopper field_0002;
   public EnumFaceDirection field_0004;
   public EnumCreatureType field_0000;
   public JTree fTree;
   public HttpRequest field_0006;

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
      this.fTree.setCellRenderer(new TestSuitePanel$TestTreeCellRenderer());
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

   public static TestTreeModel access$0(TestSuitePanel var0) {
      return var0.fModel;
   }

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
}
