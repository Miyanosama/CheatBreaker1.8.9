package junit.swingui;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.Icon;
import javax.swing.JList;
import javax.swing.UIManager;
import net.minecraft.client.particle.EntityRainFX$Factory;

public class TestSelector$TestCellRenderer extends DefaultListCellRenderer {
   public Icon fSuiteIcon;
   public EntityRainFX$Factory field_0002;
   public Icon fLeafIcon = UIManager.getIcon("Tree.leafIcon");

   public static int typeIndex(String var0) {
      int var1 = var0.lastIndexOf(46);
      int var2 = 0;
      if (var1 > 0) {
         var2 = var1 + 1;
      }

      return var2;
   }

   public static String displayString(String var0) {
      int var1 = var0.lastIndexOf(46);
      return var1 < 0 ? var0 : var0.substring(var1 + 1) + " - " + var0.substring(0, var1);
   }

   public TestSelector$TestCellRenderer() {
      this.fSuiteIcon = UIManager.getIcon("Tree.closedIcon");
   }

   public Component getListCellRendererComponent(JList var1, Object var2, int var3, boolean var4, boolean var5) {
      Component var6 = super.getListCellRendererComponent(var1, var2, var3, var4, var5);
      String var7 = displayString((String)var2);
      if (var7.startsWith("AllTests")) {
         this.setIcon(this.fSuiteIcon);
      } else {
         this.setIcon(this.fLeafIcon);
      }

      this.setText(var7);
      return var6;
   }

   public static boolean matchesKey(String var0, char var1) {
      return var1 == Character.toUpperCase(var0.charAt(typeIndex(var0)));
   }
}
