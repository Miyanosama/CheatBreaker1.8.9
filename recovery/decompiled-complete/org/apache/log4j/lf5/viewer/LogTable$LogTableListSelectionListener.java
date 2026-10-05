package org.apache.log4j.lf5.viewer;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Entrance;

public class LogTable$LogTableListSelectionListener implements ListSelectionListener {
   public LogTable this$0;
   public StructureNetherBridgePieces$Entrance field_0002;
   public JTable _table;

   public void valueChanged(ListSelectionEvent var1) {
      if (!var1.getValueIsAdjusting()) {
         ListSelectionModel var2 = (ListSelectionModel)var1.getSource();
         if (!var2.isSelectionEmpty()) {
            StringBuffer var3 = new StringBuffer();
            int var4 = var2.getMinSelectionIndex();

            for (int var5 = 0; var5 < this.this$0._numCols - 1; var5++) {
               String var6 = "";
               Object var7 = this._table.getModel().getValueAt(var4, var5);
               if (var7 != null) {
                  var6 = var7.toString();
               }

               var3.append(this.this$0._colNames[var5] + ":");
               var3.append("\t");
               if (var5 == this.this$0._colThread || var5 == this.this$0._colMessage || var5 == this.this$0._colLevel) {
                  var3.append("\t");
               }

               if (var5 == this.this$0._colDate || var5 == this.this$0._colNDC) {
                  var3.append("\t\t");
               }

               var3.append(var6);
               var3.append("\n");
            }

            var3.append(this.this$0._colNames[this.this$0._numCols - 1] + ":\n");
            Object var8 = this._table.getModel().getValueAt(var4, this.this$0._numCols - 1);
            if (var8 != null) {
               var3.append(var8.toString());
            }

            this.this$0._detailTextArea.setText(var3.toString());
         }
      }
   }

   public LogTable$LogTableListSelectionListener(LogTable var1, JTable var2) {
      this.this$0 = var1;
      super();
      this._table = var2;
   }
}
