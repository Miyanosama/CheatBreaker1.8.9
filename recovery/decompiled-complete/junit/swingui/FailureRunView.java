package junit.swingui;

import io.netty.handler.timeout.IdleState;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ListModel;
import junit.framework.Test;
import junit.framework.TestFailure;
import junit.framework.TestResult;
import net.minecraft.client.shader.Shader;

public class FailureRunView implements TestRunView {
   public Shader field_0001;
   public TestRunContext fRunContext;
   public JList fFailureList;
   public IdleState field_0002;

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
      this.fFailureList.setCellRenderer(new FailureRunView$FailureListCellRenderer());
      this.fFailureList.setVisibleRowCount(5);
      this.fFailureList.addListSelectionListener(new FailureRunView$1(this));
   }
}
