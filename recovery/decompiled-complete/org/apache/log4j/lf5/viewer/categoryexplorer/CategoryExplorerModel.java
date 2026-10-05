package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.AWTEventMulticaster;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Enumeration;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import net.minecraft.client.renderer.BlockModelShapes$3;
import org.apache.log4j.lf5.LogRecord;

public class CategoryExplorerModel extends DefaultTreeModel {
   public static long field_0002;
   public ActionEvent _event;
   public BlockModelShapes$3 field_0001;
   public boolean _renderFatal = true;
   public ActionListener _listener = null;

   public void setParentSelection(CategoryNode var1, boolean var2) {
      TreeNode[] var3 = this.getPathToRoot(var1);
      int var4 = var3.length;

      for (int var6 = 1; var6 < var4; var6++) {
         CategoryNode var5 = (CategoryNode)var3[var6];
         if (var5.isSelected() != var2) {
            var5.setSelected(var2);
            this.nodeChanged(var5);
         }
      }

      this.notifyActionListeners();
   }

   public CategoryExplorerModel(CategoryNode var1) {
      super(var1);
      this._event = new ActionEvent(this, 1001, "Nodes Selection changed");
   }

   public CategoryNode addCategory(CategoryPath var1) {
      CategoryNode var2 = (CategoryNode)this.getRoot();
      CategoryNode var3 = var2;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         CategoryElement var5 = var1.categoryElementAt(var4);
         Enumeration var6 = var3.children();
         boolean var7 = false;

         while (var6.hasMoreElements()) {
            CategoryNode var8 = (CategoryNode)var6.nextElement();
            String var9 = var8.getTitle().toLowerCase();
            String var10 = var5.getTitle().toLowerCase();
            if (var9.equals(var10)) {
               var7 = true;
               var3 = var8;
               break;
            }
         }

         if (!var7) {
            CategoryNode var11 = new CategoryNode(var5.getTitle());
            this.insertNodeInto(var11, var3, var3.getChildCount());
            this.refresh(var11);
            var3 = var11;
         }
      }

      return var3;
   }

   public void setDescendantSelection(CategoryNode var1, boolean var2) {
      Enumeration var3 = var1.depthFirstEnumeration();

      while (var3.hasMoreElements()) {
         CategoryNode var4 = (CategoryNode)var3.nextElement();
         if (var4.isSelected() != var2) {
            var4.setSelected(var2);
            this.nodeChanged(var4);
         }
      }

      this.notifyActionListeners();
   }

   public synchronized void removeActionListener(ActionListener var1) {
      this._listener = AWTEventMulticaster.remove(this._listener, var1);
   }

   public void notifyActionListeners() {
      if (this._listener != null) {
         this._listener.actionPerformed(this._event);
      }
   }

   public void resetAllNodeCounts() {
      Enumeration var1 = this.getRootCategoryNode().depthFirstEnumeration();

      while (var1.hasMoreElements()) {
         CategoryNode var2 = (CategoryNode)var1.nextElement();
         var2.resetNumberOfContainedRecords();
         this.nodeChanged(var2);
      }
   }

   public synchronized void addActionListener(ActionListener var1) {
      this._listener = AWTEventMulticaster.add(this._listener, var1);
   }

   public void refresh(CategoryNode var1) {
      SwingUtilities.invokeLater(new CategoryExplorerModel$1(this, var1));
   }

   public CategoryNode getCategoryNode(CategoryPath var1) {
      CategoryNode var2 = (CategoryNode)this.getRoot();
      CategoryNode var3 = var2;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         CategoryElement var5 = var1.categoryElementAt(var4);
         Enumeration var6 = var3.children();
         boolean var7 = false;

         while (var6.hasMoreElements()) {
            CategoryNode var8 = (CategoryNode)var6.nextElement();
            String var9 = var8.getTitle().toLowerCase();
            String var10 = var5.getTitle().toLowerCase();
            if (var9.equals(var10)) {
               var7 = true;
               var3 = var8;
               break;
            }
         }

         if (!var7) {
            return null;
         }
      }

      return var3;
   }

   public boolean isCategoryPathActive(CategoryPath var1) {
      CategoryNode var2 = (CategoryNode)this.getRoot();
      CategoryNode var3 = var2;
      boolean var4 = false;

      for (int var5 = 0; var5 < var1.size(); var5++) {
         CategoryElement var6 = var1.categoryElementAt(var5);
         Enumeration var7 = var3.children();
         boolean var8 = false;
         var4 = false;

         while (var7.hasMoreElements()) {
            CategoryNode var9 = (CategoryNode)var7.nextElement();
            String var10 = var9.getTitle().toLowerCase();
            String var11 = var6.getTitle().toLowerCase();
            if (var10.equals(var11)) {
               var8 = true;
               var3 = var9;
               if (var9.isSelected()) {
                  var4 = true;
               }
               break;
            }
         }

         if (!var4 || !var8) {
            return false;
         }
      }

      return var4;
   }

   public TreePath getTreePathToRoot(CategoryNode var1) {
      return var1 == null ? null : new TreePath(this.getPathToRoot(var1));
   }

   public void addLogRecord(LogRecord var1) {
      CategoryPath var2 = new CategoryPath(var1.getCategory());
      this.addCategory(var2);
      CategoryNode var3 = this.getCategoryNode(var2);
      var3.addRecord();
      if (this._renderFatal && var1.isFatal()) {
         TreeNode[] var4 = this.getPathToRoot(var3);
         int var5 = var4.length;

         for (int var7 = 1; var7 < var5 - 1; var7++) {
            CategoryNode var6 = (CategoryNode)var4[var7];
            var6.setHasFatalChildren(true);
            this.nodeChanged(var6);
         }

         var3.setHasFatalRecords(true);
         this.nodeChanged(var3);
      }
   }

   public CategoryNode getRootCategoryNode() {
      return (CategoryNode)this.getRoot();
   }

   public void update(CategoryNode var1, boolean var2) {
      if (var1.isSelected() != var2) {
         if (var2) {
            this.setParentSelection(var1, true);
         } else {
            this.setDescendantSelection(var1, false);
         }
      }
   }

   public CategoryNode getCategoryNode(String var1) {
      CategoryPath var2 = new CategoryPath(var1);
      return this.getCategoryNode(var2);
   }
}
