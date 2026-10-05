package org.apache.log4j.lf5.viewer.categoryexplorer;

import io.netty.util.internal.Cleaner0;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import javax.swing.JTable;
import javax.swing.JTree;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.EventListenerList;
import javax.swing.table.TableCellEditor;
import javax.swing.tree.TreeCellEditor;
import net.minecraft.client.gui.GuiOptionButton;
import net.minecraft.client.particle.EntityParticleEmitter;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$DoubleYRoom;
import org.apache.log4j.helpers.PatternParser$LiteralPatternConverter;
import org.json.JSONTokener;

public class CategoryAbstractCellEditor implements TableCellEditor, TreeCellEditor {
   public PatternParser$LiteralPatternConverter field_0005;
   public EntityParticleEmitter field_0008;
   public JSONTokener field_0004;
   public int _clickCountToStart;
   public ChangeEvent _changeEvent;
   public static Class class$javax$swing$event$CellEditorListener;
   public Object _value;
   public StructureOceanMonumentPieces$DoubleYRoom field_0006;
   public EventListenerList _listenerList = new EventListenerList();
   public GuiOptionButton field_0010;
   public Cleaner0 field_0000;

   public void fireEditingStopped() {
      Object[] var1 = this._listenerList.getListenerList();

      for (int var2 = var1.length - 2; var2 >= 0; var2 -= 2) {
         if (var1[var2]
            == (
               class$javax$swing$event$CellEditorListener == null
                  ? (class$javax$swing$event$CellEditorListener = class$("javax.swing.event.CellEditorListener"))
                  : class$javax$swing$event$CellEditorListener
            )) {
            if (this._changeEvent == null) {
               this._changeEvent = new ChangeEvent(this);
            }

            ((CellEditorListener)var1[var2 + 1]).editingStopped(this._changeEvent);
         }
      }
   }

   public void fireEditingCanceled() {
      Object[] var1 = this._listenerList.getListenerList();

      for (int var2 = var1.length - 2; var2 >= 0; var2 -= 2) {
         if (var1[var2]
            == (
               class$javax$swing$event$CellEditorListener == null
                  ? (class$javax$swing$event$CellEditorListener = class$("javax.swing.event.CellEditorListener"))
                  : class$javax$swing$event$CellEditorListener
            )) {
            if (this._changeEvent == null) {
               this._changeEvent = new ChangeEvent(this);
            }

            ((CellEditorListener)var1[var2 + 1]).editingCanceled(this._changeEvent);
         }
      }
   }

   public void cancelCellEditing() {
      this.fireEditingCanceled();
   }

   public Component getTableCellEditorComponent(JTable var1, Object var2, boolean var3, int var4, int var5) {
      return null;
   }

   public int getClickCountToStart() {
      return this._clickCountToStart;
   }

   public void removeCellEditorListener(CellEditorListener var1) {
      this._listenerList
         .remove(
            class$javax$swing$event$CellEditorListener == null
               ? (class$javax$swing$event$CellEditorListener = class$("javax.swing.event.CellEditorListener"))
               : class$javax$swing$event$CellEditorListener,
            var1
         );
   }

   public boolean shouldSelectCell(EventObject var1) {
      return this.isCellEditable(var1) && (var1 == null || ((MouseEvent)var1).getClickCount() >= this._clickCountToStart);
   }

   public boolean stopCellEditing() {
      this.fireEditingStopped();
      return true;
   }

   public Object getCellEditorValue() {
      return this._value;
   }

   public CategoryAbstractCellEditor() {
      this._changeEvent = null;
      this._clickCountToStart = 1;
   }

   public Component getTreeCellEditorComponent(JTree var1, Object var2, boolean var3, boolean var4, boolean var5, int var6) {
      return null;
   }

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError().initCause(var2);
      }
   }

   public void setClickCountToStart(int var1) {
      this._clickCountToStart = var1;
   }

   public void setCellEditorValue(Object var1) {
      this._value = var1;
   }

   public boolean isCellEditable(EventObject var1) {
      return !(var1 instanceof MouseEvent) || ((MouseEvent)var1).getClickCount() >= this._clickCountToStart;
   }

   public void addCellEditorListener(CellEditorListener var1) {
      this._listenerList
         .add(
            class$javax$swing$event$CellEditorListener == null
               ? (class$javax$swing$event$CellEditorListener = class$("javax.swing.event.CellEditorListener"))
               : class$javax$swing$event$CellEditorListener,
            var1
         );
   }
}
