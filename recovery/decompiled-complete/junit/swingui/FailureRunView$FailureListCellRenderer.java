package junit.swingui;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JList;
import junit.framework.TestFailure;
import junit.runner.BaseTestRunner;
import net.minecraft.block.BlockDoor$EnumDoorHalf;

public class FailureRunView$FailureListCellRenderer extends DefaultListCellRenderer {
   public BlockDoor$EnumDoorHalf field_0001;
   public Icon fErrorIcon;
   public Icon fFailureIcon;

   public FailureRunView$FailureListCellRenderer() {
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
         var8 = var8 + ":" + BaseTestRunner.method_29475(var9);
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
