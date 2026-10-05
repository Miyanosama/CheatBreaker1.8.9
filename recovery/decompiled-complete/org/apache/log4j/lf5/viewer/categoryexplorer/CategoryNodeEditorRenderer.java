package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.Component;
import javax.swing.JCheckBox;
import javax.swing.JTree;
import net.minecraft.client.renderer.WorldRenderer$1;
import net.minecraft.world.gen.feature.WorldGenTallGrass;

public class CategoryNodeEditorRenderer extends CategoryNodeRenderer {
   public static long field_0001;
   public WorldRenderer$1 field_0002;
   public WorldGenTallGrass field_0000;

   public JCheckBox getCheckBox() {
      return this._checkBox;
   }

   public Component getTreeCellRendererComponent(JTree var1, Object var2, boolean var3, boolean var4, boolean var5, int var6, boolean var7) {
      return super.getTreeCellRendererComponent(var1, var2, var3, var4, var5, var6, var7);
   }
}
