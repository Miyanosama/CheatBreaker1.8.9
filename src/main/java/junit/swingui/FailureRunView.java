package junit.swingui;

import java.awt.Component;
import java.awt.Font;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListModel;
import junit.framework.Test;
import junit.framework.TestFailure;
import junit.framework.TestResult;
import junit.runner.BaseTestRunner;

public class FailureRunView implements TestRunView {
   public TestRunContext fRunContext;
   public JList fFailureList;

   public void addTab(JTabbedPane var1) {
      JScrollPane var2 = new JScrollPane(this.fFailureList, 22, 32);
      Icon var3 = TestRunner.getIconResource(this.getClass(), "icons/error.gif");
      var1.addTab("Failures", var3, var2, "The list of failed tests");
   }

   public void revealFailure(Test var1) {
      this.fFailureList.setSelectedIndex(0);
   }

   public void testSelected() {
      this.fRunContext.handleTestSelected(this.getSelectedTest());
   }

   public Test getSelectedTest() {
      int var1 = this.fFailureList.getSelectedIndex();
      if (var1 == -1) {
         return null;
      } else {
         ListModel var2 = this.fFailureList.getModel();
         TestFailure var3 = (TestFailure)var2.getElementAt(var1);
         return var3.failedTest();
      }
   }

   public void runFinished(Test var1, TestResult var2) {
   }

   public void aboutToStart(Test var1, TestResult var2) {
   }

   public void activate() {
      this.testSelected();
   }

   public FailureRunView(TestRunContext var1) {
      this.fRunContext = var1;
      this.fFailureList = new JList(this.fRunContext.getFailures());
      this.fFailureList.setFont(new Font("Dialog", 0, 12));
      this.fFailureList.setSelectionMode(0);
      this.fFailureList.setCellRenderer(new FailureRunView.FailureListCellRenderer());
      this.fFailureList.setVisibleRowCount(5);
      this.fFailureList.addListSelectionListener(new FailureRunView$1(this));
   }

   public static class FailureListCellRenderer extends DefaultListCellRenderer {
      public Icon fErrorIcon;
      public Icon fFailureIcon;

      public FailureListCellRenderer() {
         this.loadIcons();
      }

      public void loadIcons() {
         this.fFailureIcon = TestRunner.getIconResource(this.getClass(), "icons/failure.gif");
         this.fErrorIcon = TestRunner.getIconResource(this.getClass(), "icons/error.gif");
      }

      public Component getListCellRendererComponent(JList var1, Object var2, int var3, boolean var4, boolean var5) {
         Component var6 = super.getListCellRendererComponent(var1, var2, var3, var4, var5);
         TestFailure var7 = (TestFailure)var2;
         String var8 = var7.failedTest().toString();
         String var9 = var7.exceptionMessage();
         if (var9 != null) {
            var8 = var8 + ":" + BaseTestRunner.truncate(var9);
         }

         if (var7.isFailure()) {
            if (this.fFailureIcon != null) {
               this.setIcon(this.fFailureIcon);
            }
         } else if (this.fErrorIcon != null) {
            this.setIcon(this.fErrorIcon);
         }

         this.setText(var8);
         this.setToolTipText(var8);
         return var6;
      }
   }
}
