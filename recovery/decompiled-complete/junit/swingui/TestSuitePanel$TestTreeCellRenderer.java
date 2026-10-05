package junit.swingui;

import java.awt.Component;
import javax.swing.Icon;
import javax.swing.JComponent;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeModel;
import junit.framework.Test;

public class TestSuitePanel$TestTreeCellRenderer extends DefaultTreeCellRenderer {
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

   public TestSuitePanel$TestTreeCellRenderer() {
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
         } else if (var10.method_25919(var11)) {
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
