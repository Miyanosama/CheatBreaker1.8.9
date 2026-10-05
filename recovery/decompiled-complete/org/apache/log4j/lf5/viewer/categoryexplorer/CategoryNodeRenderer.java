package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultTreeCellRenderer;
import net.minecraft.init.Bootstrap$2;
import net.minecraft.item.ItemTool;

public class CategoryNodeRenderer extends DefaultTreeCellRenderer {
   public JPanel _panel;
   public JCheckBox _checkBox = new JCheckBox();
   public static ImageIcon _sat = null;
   public ItemTool field_0005;
   public static long field_0003;
   public static Color FATAL_CHILDREN = new Color(189, 113, 0);
   public Bootstrap$2 field_0006;

   public Component getTreeCellRendererComponent(JTree var1, Object var2, boolean var3, boolean var4, boolean var5, int var6, boolean var7) {
      CategoryNode var8 = (CategoryNode)var2;
      super.getTreeCellRendererComponent(var1, var2, var3, var4, var5, var6, var7);
      if (var6 == 0) {
         this._checkBox.setVisible(false);
      } else {
         this._checkBox.setVisible(true);
         this._checkBox.setSelected(var8.isSelected());
      }

      String var9 = this.buildToolTip(var8);
      this._panel.setToolTipText(var9);
      if (var8.hasFatalChildren()) {
         this.setForeground(FATAL_CHILDREN);
      }

      if (var8.hasFatalRecords()) {
         this.setForeground(Color.red);
      }

      return this._panel;
   }

   public Dimension getCheckBoxOffset() {
      return new Dimension(0, 0);
   }

   public String buildToolTip(CategoryNode var1) {
      StringBuffer var2 = new StringBuffer();
      var2.append(var1.getTitle()).append(" contains a total of ");
      var2.append(var1.getTotalNumberOfRecords());
      var2.append(" LogRecords.");
      var2.append(" Right-click for more info.");
      return var2.toString();
   }

   public CategoryNodeRenderer() {
      this._panel = new JPanel();
      this._panel.setBackground(UIManager.getColor("Tree.textBackground"));
      if (_sat == null) {
         String var1 = "/org/apache/log4j/lf5/viewer/images/channelexplorer_satellite.gif";
         URL var2 = this.getClass().getResource(var1);
         _sat = new ImageIcon(var2);
      }

      this.setOpaque(false);
      this._checkBox.setOpaque(false);
      this._panel.setOpaque(false);
      this._panel.setLayout(new FlowLayout(0, 0, 0));
      this._panel.add(this._checkBox);
      this._panel.add(this);
      this.setOpenIcon(_sat);
      this.setClosedIcon(_sat);
      this.setLeafIcon(_sat);
   }
}
