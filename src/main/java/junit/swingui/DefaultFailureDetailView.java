package junit.swingui;

import java.awt.Component;
import java.awt.Font;
import java.util.StringTokenizer;
import java.util.Vector;
import javax.swing.AbstractListModel;
import javax.swing.JList;
import junit.framework.TestFailure;
import junit.runner.BaseTestRunner;
import junit.runner.FailureDetailView;
import junit.swingui.DefaultFailureDetailView$StackEntryRenderer;

public class DefaultFailureDetailView implements FailureDetailView {
   public JList fList;

   public void showFailure(TestFailure var1) {
      this.getModel().setTrace(BaseTestRunner.getFilteredTrace(var1.trace()));
   }

   public void clear() {
      this.getModel().clear();
   }

   public Component getComponent() {
      if (this.fList == null) {
         this.fList = new JList(new DefaultFailureDetailView.StackTraceListModel());
         this.fList.setFont(new Font("Dialog", 0, 12));
         this.fList.setSelectionMode(0);
         this.fList.setVisibleRowCount(5);
         this.fList.setCellRenderer(new DefaultFailureDetailView$StackEntryRenderer());
      }

      return this.fList;
   }

   public DefaultFailureDetailView.StackTraceListModel getModel() {
      return (DefaultFailureDetailView.StackTraceListModel)this.fList.getModel();
   }

   public static class StackTraceListModel extends AbstractListModel {
      public Vector fLines = new Vector(20);

      public void setTrace(String var1) {
         this.scan(var1);
         this.fireContentsChanged(this, 0, this.fLines.size());
      }

      public void scan(String var1) {
         this.fLines.removeAllElements();
         StringTokenizer var2 = new StringTokenizer(var1, "\n\r", false);

         while (var2.hasMoreTokens()) {
            this.fLines.addElement(var2.nextToken());
         }
      }

      public Object getElementAt(int var1) {
         return this.fLines.elementAt(var1);
      }

      public int getSize() {
         return this.fLines.size();
      }

      public void clear() {
         this.fLines.removeAllElements();
         this.fireContentsChanged(this, 0, this.fLines.size());
      }
   }
}
