package org.apache.log4j.lf5.viewer.categoryexplorer;

import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.util.ResourceLeakDetector;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.TreePath;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$SimpleRoom;

public class CategoryImmediateEditor extends DefaultTreeCellEditor {
   public StructureOceanMonumentPieces$SimpleRoom field_0002;
   public ZlibCodecFactory field_0004;
   public Icon editingIcon = null;
   public ResourceLeakDetector field_0003;
   public CategoryNodeRenderer renderer;

   public CategoryImmediateEditor(JTree var1, CategoryNodeRenderer var2, CategoryNodeEditor var3) {
      super(var1, var2, var3);
      this.renderer = var2;
      var2.setIcon(null);
      var2.setLeafIcon(null);
      var2.setOpenIcon(null);
      var2.setClosedIcon(null);
      super.editingIcon = null;
   }

   public void determineOffset(JTree var1, Object var2, boolean var3, boolean var4, boolean var5, int var6) {
      this.offset = 0;
   }

   public boolean canEditImmediately(EventObject var1) {
      boolean var2 = false;
      if (var1 instanceof MouseEvent) {
         MouseEvent var3 = (MouseEvent)var1;
         var2 = this.inCheckBoxHitRegion(var3);
      }

      return var2;
   }

   public boolean shouldSelectCell(EventObject var1) {
      boolean var2 = false;
      if (var1 instanceof MouseEvent) {
         MouseEvent var3 = (MouseEvent)var1;
         TreePath var4 = this.tree.getPathForLocation(var3.getX(), var3.getY());
         CategoryNode var5 = (CategoryNode)var4.getLastPathComponent();
         var2 = var5.isLeaf();
      }

      return var2;
   }

   public boolean inCheckBoxHitRegion(MouseEvent var1) {
      TreePath var2 = this.tree.getPathForLocation(var1.getX(), var1.getY());
      if (var2 == null) {
         return false;
      } else {
         CategoryNode var3 = (CategoryNode)var2.getLastPathComponent();
         boolean var4 = false;
         Rectangle var5 = this.tree.getRowBounds(this.lastRow);
         Dimension var6 = this.renderer.getCheckBoxOffset();
         var5.translate(this.offset + var6.width, var6.height);
         var4 = var5.contains(var1.getPoint());
         return true;
      }
   }
}
