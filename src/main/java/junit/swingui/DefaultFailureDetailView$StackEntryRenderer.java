package junit.swingui;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class DefaultFailureDetailView$StackEntryRenderer extends DefaultListCellRenderer {
   public Component getListCellRendererComponent(JList var1, Object var2, int var3, boolean var4, boolean var5) {
      String var6 = ((String)var2).replace('\t', ' ');
      Component var7 = super.getListCellRendererComponent(var1, var6, var3, var4, var5);
      this.setText(var6);
      this.setToolTipText(var6);
      return var7;
   }
}
