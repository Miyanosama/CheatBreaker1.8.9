package org.apache.log4j.lf5.viewer;

import io.netty.handler.codec.http.HttpContentCompressor$1;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import net.minecraft.block.BlockTripWire;
import net.minecraft.client.Minecraft$14;
import net.minecraft.entity.item.EntityMinecart$EnumMinecartType;
import net.optifine.reflect.ReflectorClass;
import org.apache.log4j.lf5.util.DateFormatManager;

public class LogTable extends JTable {
   public int _colNDC;
   public int _colLevel;
   public int _colThrown;
   public EntityMinecart$EnumMinecartType field_0016;
   public static long field_0004;
   public int _colCategory;
   public int _colDate;
   public HttpContentCompressor$1 field_0014;
   public int _colThread;
   public int _colLocation;
   public int _numCols;
   public ReflectorClass field_0011;
   public LogTableColumn[] _colNames;
   public int _colMessageNum;
   public int _colMessage;
   public BlockTripWire field_0018;
   public Minecraft$14 field_0002;
   public JTextArea _detailTextArea;
   public int _rowHeight = 30;
   public int[] _colWidths;
   public DateFormatManager _dateFormatManager;
   public TableColumn[] _tableColumns;

   public DateFormatManager getDateFormatManager() {
      return this._dateFormatManager;
   }

   public void setFont(Font var1) {
      super.setFont(var1);
      Graphics var2 = this.getGraphics();
      if (var2 != null) {
         FontMetrics var3 = var2.getFontMetrics(var1);
         int var4 = var3.getHeight();
         this._rowHeight = var4 + var4 / 3;
         this.setRowHeight(this._rowHeight);
      }
   }

   public FilteredLogTableModel getFilteredLogTableModel() {
      return (FilteredLogTableModel)this.getModel();
   }

   public void setDetailedView() {
      TableColumnModel var1 = this.getColumnModel();

      for (int var2 = 0; var2 < this._numCols; var2++) {
         var1.removeColumn(this._tableColumns[var2]);
      }

      for (int var3 = 0; var3 < this._numCols; var3++) {
         var1.addColumn(this._tableColumns[var3]);
      }

      this.sizeColumnsToFit(-1);
   }

   public void setView(List var1) {
      TableColumnModel var2 = this.getColumnModel();

      for (int var3 = 0; var3 < this._numCols; var3++) {
         var2.removeColumn(this._tableColumns[var3]);
      }

      Iterator var5 = var1.iterator();
      Vector var4 = this.getColumnNameAndNumber();

      while (var5.hasNext()) {
         var2.addColumn(this._tableColumns[var4.indexOf(var5.next())]);
      }

      this.sizeColumnsToFit(-1);
   }

   public synchronized void clearLogRecords() {
      this.getFilteredLogTableModel().clear();
   }

   public Vector getColumnNameAndNumber() {
      Vector var1 = new Vector();

      for (int var2 = 0; var2 < this._colNames.length; var2++) {
         var1.add(var2, this._colNames[var2]);
      }

      return var1;
   }

   public void setDateFormatManager(DateFormatManager var1) {
      this._dateFormatManager = var1;
   }

   public void init() {
      this.setRowHeight(this._rowHeight);
      this.setSelectionMode(0);
   }

   public LogTable(JTextArea var1) {
      this._numCols = 9;
      this._tableColumns = new TableColumn[this._numCols];
      this._colWidths = new int[]{40, 40, 40, 70, 70, 360, 440, 200, 60};
      this._colNames = LogTableColumn.getLogTableColumnArray();
      this._colDate = 0;
      this._colThread = 1;
      this._colMessageNum = 2;
      this._colLevel = 3;
      this._colNDC = 4;
      this._colCategory = 5;
      this._colMessage = 6;
      this._colLocation = 7;
      this._colThrown = 8;
      this._dateFormatManager = null;
      this.init();
      this._detailTextArea = var1;
      this.setModel(new FilteredLogTableModel());
      Enumeration var2 = this.getColumnModel().getColumns();

      for (int var3 = 0; var2.hasMoreElements(); var3++) {
         TableColumn var4 = (TableColumn)var2.nextElement();
         var4.setCellRenderer(new LogTableRowRenderer());
         var4.setPreferredWidth(this._colWidths[var3]);
         this._tableColumns[var3] = var4;
      }

      ListSelectionModel var5 = this.getSelectionModel();
      var5.addListSelectionListener(new LogTable$LogTableListSelectionListener(this, this));
   }
}
