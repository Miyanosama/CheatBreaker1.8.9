package org.apache.log4j.lf5.viewer.categoryexplorer;

import java.awt.event.MouseEvent;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

public class CategoryExplorerTree extends JTree {
   public CategoryExplorerModel _model;
   public boolean _rootAlreadyExpanded = false;
   public static final long recoveredField925 = 8066257446951323576L;

   public CategoryExplorerModel getExplorerModel() {
      return this._model;
   }

   public CategoryExplorerTree(CategoryExplorerModel var1) {
      super(var1);
      this._model = var1;
      this.init();
   }

   public CategoryExplorerTree() {
      CategoryNode var1 = new CategoryNode("Categories");
      this._model = new CategoryExplorerModel(var1);
      this.setModel(this._model);
      this.init();
   }

   public void ensureRootExpansion() {
      this._model.addTreeModelListener(new CategoryExplorerTree$1(this));
   }

   public String getToolTipText(MouseEvent var1) {
      try {
         return super.getToolTipText(var1);
      } catch (Exception var3) {
         return "";
      }
   }

   public void expandRootNode() {
      if (!this._rootAlreadyExpanded) {
         this._rootAlreadyExpanded = true;
         TreePath var1 = new TreePath(this._model.getRootCategoryNode().getPath());
         this.expandPath(var1);
      }
   }

   public void init() {
      this.putClientProperty("JTree.lineStyle", "Angled");
      CategoryNodeRenderer var1 = new CategoryNodeRenderer();
      this.setEditable(true);
      this.setCellRenderer(var1);
      CategoryNodeEditor var2 = new CategoryNodeEditor(this._model);
      this.setCellEditor(new CategoryImmediateEditor(this, new CategoryNodeRenderer(), var2));
      this.setShowsRootHandles(true);
      this.setToolTipText("");
      this.ensureRootExpansion();
   }
}
