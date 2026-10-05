package org.apache.log4j.lf5.viewer.categoryexplorer;

import com.cheatbreaker.client.BuildBranch;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler$ServerHandshakeStateEvent;
import java.util.Enumeration;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import junit.swingui.FailureRunView;
import net.minecraft.world.Teleporter;

public class CategoryNode extends DefaultMutableTreeNode {
   public int _numberOfRecordsFromChildren;
   public int _numberOfContainedRecords;
   public boolean _hasFatalRecords;
   public FailureRunView field_0006;
   public boolean _selected = true;
   public Teleporter field_0001;
   public BuildBranch field_0008;
   public WebSocketServerProtocolHandler$ServerHandshakeStateEvent field_0005;
   public static long field_0002;
   public boolean _hasFatalChildren;

   public boolean hasFatalRecords() {
      return this._hasFatalRecords;
   }

   public int getNumberOfContainedRecords() {
      return this._numberOfContainedRecords;
   }

   public void setAllDescendantsDeSelected() {
      Enumeration var1 = this.children();

      while (var1.hasMoreElements()) {
         CategoryNode var2 = (CategoryNode)var1.nextElement();
         var2.setSelected(false);
         var2.setAllDescendantsDeSelected();
      }
   }

   public void setAllDescendantsSelected() {
      Enumeration var1 = this.children();

      while (var1.hasMoreElements()) {
         CategoryNode var2 = (CategoryNode)var1.nextElement();
         var2.setSelected(true);
         var2.setAllDescendantsSelected();
      }
   }

   public int hashCode() {
      return this.getTitle().hashCode();
   }

   public int getTotalNumberOfRecords() {
      return this.getNumberOfRecordsFromChildren() + this.getNumberOfContainedRecords();
   }

   public void resetNumberOfContainedRecords() {
      this._numberOfContainedRecords = 0;
      this._numberOfRecordsFromChildren = 0;
      this._hasFatalRecords = false;
      this._hasFatalChildren = false;
   }

   public void setHasFatalRecords(boolean var1) {
      this._hasFatalRecords = var1;
   }

   public void addRecordToParent() {
      TreeNode var1 = this.getParent();
      if (var1 != null) {
         ((CategoryNode)var1).addRecordFromChild();
      }
   }

   public boolean equals(Object var1) {
      if (var1 instanceof CategoryNode) {
         CategoryNode var2 = (CategoryNode)var1;
         String var3 = this.getTitle().toLowerCase();
         String var4 = var2.getTitle().toLowerCase();
         if (var3.equals(var4)) {
            return true;
         }
      }

      return false;
   }

   public int getNumberOfRecordsFromChildren() {
      return this._numberOfRecordsFromChildren;
   }

   public void setHasFatalChildren(boolean var1) {
      this._hasFatalChildren = var1;
   }

   public void setSelected(boolean var1) {
      if (var1 != this._selected) {
         this._selected = var1;
      }
   }

   public boolean hasFatalChildren() {
      return this._hasFatalChildren;
   }

   public CategoryNode(String var1) {
      this._numberOfContainedRecords = 0;
      this._numberOfRecordsFromChildren = 0;
      this._hasFatalChildren = false;
      this._hasFatalRecords = false;
      this.setUserObject(var1);
   }

   public void addRecord() {
      this._numberOfContainedRecords++;
      this.addRecordToParent();
   }

   public String getTitle() {
      return (String)this.getUserObject();
   }

   public String toString() {
      return this.getTitle();
   }

   public boolean isSelected() {
      return this._selected;
   }

   public void addRecordFromChild() {
      this._numberOfRecordsFromChildren++;
      this.addRecordToParent();
   }
}
