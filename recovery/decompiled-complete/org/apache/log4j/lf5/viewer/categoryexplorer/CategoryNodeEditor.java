package org.apache.log4j.lf5.viewer.categoryexplorer;

import com.cheatbreaker.client.nethandler.shared.PacketRemoveWaypoint;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.JCheckBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

public class CategoryNodeEditor extends CategoryAbstractCellEditor {
   public CategoryExplorerModel _categoryModel;
   public CategoryNodeEditorRenderer _renderer = new CategoryNodeEditorRenderer();
   public PacketRemoveWaypoint field_0001;
   public JCheckBox _checkBox = this._renderer.getCheckBox();
   public CategoryNode _lastEditedNode;
   public JTree _tree;

   public JMenuItem createSelectDescendantsMenuItem(CategoryNode var1) {
      JMenuItem var2 = new JMenuItem("Select All Descendant Categories");
      var2.addActionListener(new CategoryNodeEditor$4(this, var1));
      return var2;
   }

   public Component getTreeCellEditorComponent(JTree var1, Object var2, boolean var3, boolean var4, boolean var5, int var6) {
      this._lastEditedNode = (CategoryNode)var2;
      this._tree = var1;
      return this._renderer.getTreeCellRendererComponent(var1, var2, var3, var4, var5, var6, true);
   }

   public JMenuItem createExpandMenuItem(CategoryNode var1) {
      JMenuItem var2 = new JMenuItem("Expand All Descendant Categories");
      var2.addActionListener(new CategoryNodeEditor$6(this, var1));
      return var2;
   }

   public TreePath getTreePath(CategoryNode var1) {
      return new TreePath(var1.getPath());
   }

   public void showPropertiesDialog(CategoryNode var1) {
      JOptionPane.showMessageDialog(this._tree, this.getDisplayedProperties(var1), "Category Properties: " + var1.getTitle(), -1);
   }

   public JMenuItem createRemoveMenuItem() {
      JMenuItem var1 = new JMenuItem("Remove All Empty Categories");
      var1.addActionListener(new CategoryNodeEditor$8(this));
      return var1;
   }

   public void collapse(CategoryNode var1) {
      this._tree.collapsePath(this.getTreePath(var1));
   }

   public void showPopup(CategoryNode var1, int var2, int var3) {
      JPopupMenu var4 = new JPopupMenu();
      var4.setSize(150, 400);
      if (var1.getParent() == null) {
         var4.add(this.createRemoveMenuItem());
         var4.addSeparator();
      }

      var4.add(this.createSelectDescendantsMenuItem(var1));
      var4.add(this.createUnselectDescendantsMenuItem(var1));
      var4.addSeparator();
      var4.add(this.createExpandMenuItem(var1));
      var4.add(this.createCollapseMenuItem(var1));
      var4.addSeparator();
      var4.add(this.createPropertiesMenuItem(var1));
      var4.show(this._renderer, var2, var3);
   }

   public CategoryNodeEditor(CategoryExplorerModel var1) {
      this._categoryModel = var1;
      this._checkBox.addActionListener(new CategoryNodeEditor$1(this));
      this._renderer.addMouseListener(new CategoryNodeEditor$2(this));
   }

   public JMenuItem createCollapseMenuItem(CategoryNode var1) {
      JMenuItem var2 = new JMenuItem("Collapse All Descendant Categories");
      var2.addActionListener(new CategoryNodeEditor$7(this, var1));
      return var2;
   }

   public Object getCellEditorValue() {
      return this._lastEditedNode.getUserObject();
   }

   public void expandDescendants(CategoryNode var1) {
      Enumeration var2 = var1.depthFirstEnumeration();

      while (var2.hasMoreElements()) {
         CategoryNode var3 = (CategoryNode)var2.nextElement();
         this.expand(var3);
      }
   }

   public int removeUnusedNodes() {
      int var1 = 0;
      CategoryNode var2 = this._categoryModel.getRootCategoryNode();
      Enumeration var3 = var2.depthFirstEnumeration();

      while (var3.hasMoreElements()) {
         CategoryNode var4 = (CategoryNode)var3.nextElement();
         if (var4.isLeaf() && var4.getNumberOfContainedRecords() == 0 && var4.getParent() != null) {
            this._categoryModel.removeNodeFromParent(var4);
            var1++;
         }
      }

      return var1;
   }

   public void expand(CategoryNode var1) {
      this._tree.expandPath(this.getTreePath(var1));
   }

   public void collapseDescendants(CategoryNode var1) {
      Enumeration var2 = var1.depthFirstEnumeration();

      while (var2.hasMoreElements()) {
         CategoryNode var3 = (CategoryNode)var2.nextElement();
         this.collapse(var3);
      }
   }

   public JMenuItem createPropertiesMenuItem(CategoryNode var1) {
      JMenuItem var2 = new JMenuItem("Properties");
      var2.addActionListener(new CategoryNodeEditor$3(this, var1));
      return var2;
   }

   public JMenuItem createUnselectDescendantsMenuItem(CategoryNode var1) {
      JMenuItem var2 = new JMenuItem("Deselect All Descendant Categories");
      var2.addActionListener(new CategoryNodeEditor$5(this, var1));
      return var2;
   }

   public Object getDisplayedProperties(CategoryNode var1) {
      ArrayList var2 = new ArrayList();
      var2.add("Category: " + var1.getTitle());
      if (var1.hasFatalRecords()) {
         var2.add("Contains at least one fatal LogRecord.");
      }

      if (var1.hasFatalChildren()) {
         var2.add("Contains descendants with a fatal LogRecord.");
      }

      var2.add("LogRecords in this category alone: " + var1.getNumberOfContainedRecords());
      var2.add("LogRecords in descendant categories: " + var1.getNumberOfRecordsFromChildren());
      var2.add("LogRecords in this category including descendants: " + var1.getTotalNumberOfRecords());
      return var2.toArray();
   }
}
