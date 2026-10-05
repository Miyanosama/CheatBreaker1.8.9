package org.apache.log4j.lf5.viewer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import org.apache.log4j.lf5.LogRecord;
import org.apache.log4j.lf5.LogRecordFilter;
import org.apache.log4j.lf5.PassingLogRecordFilter;

public class FilteredLogTableModel extends AbstractTableModel {
   public List _filteredRecords;
   public LogRecordFilter _filter = new PassingLogRecordFilter();
   public String[] _colNames;
   public int _maxNumberOfLogRecords;
   public List _allRecords = new ArrayList();

   public int numberOfRecordsToTrim() {
      return this._allRecords.size() - this._maxNumberOfLogRecords;
   }

   public void trimOldestRecords() {
      synchronized (this._allRecords) {
         int var2 = this.numberOfRecordsToTrim();
         if (var2 > 1) {
            List var3 = this._allRecords.subList(0, var2);
            var3.clear();
            this.refresh();
         } else {
            this._allRecords.remove(0);
            this.fastRefresh();
         }
      }
   }

   public void trimRecords() {
      if (this.needsTrimming()) {
         this.trimOldestRecords();
      }
   }

   public String getColumnName(int var1) {
      return this._colNames[var1];
   }

   public synchronized void clear() {
      this._allRecords.clear();
      this._filteredRecords.clear();
      this.fireTableDataChanged();
   }

   public synchronized void fastRefresh() {
      this._filteredRecords.remove(0);
      this.fireTableRowsDeleted(0, 0);
   }

   public synchronized void refresh() {
      this._filteredRecords = this.createFilteredRecordsList();
      this.fireTableDataChanged();
   }

   public List getFilteredRecords() {
      if (this._filteredRecords == null) {
         this.refresh();
      }

      return this._filteredRecords;
   }

   public void setMaxNumberOfLogRecords(int var1) {
      if (var1 > 0) {
         this._maxNumberOfLogRecords = var1;
      }
   }

   public int getColumnCount() {
      return this._colNames.length;
   }

   public LogRecord getFilteredRecord(int var1) {
      List var2 = this.getFilteredRecords();
      int var3 = var2.size();
      return var1 < var3 ? (LogRecord)var2.get(var1) : (LogRecord)var2.get(var3 - 1);
   }

   public FilteredLogTableModel() {
      this._maxNumberOfLogRecords = 5000;
      this._colNames = new String[]{"Date", "Thread", "Message #", "Level", "NDC", "Category", "Message", "Location", "Thrown"};
   }

   public void setLogRecordFilter(LogRecordFilter var1) {
      this._filter = var1;
   }

   public int getTotalRowCount() {
      return this._allRecords.size();
   }

   public LogRecordFilter getLogRecordFilter() {
      return this._filter;
   }

   public Object getValueAt(int var1, int var2) {
      LogRecord var3 = this.getFilteredRecord(var1);
      return this.getColumn(var2, var3);
   }

   public Object getColumn(int var1, LogRecord var2) {
      if (var2 == null) {
         return "NULL Column";
      } else {
         String var3 = new Date(var2.getMillis()).toString();
         switch (var1) {
            case 0:
               return var3 + " (" + var2.getMillis() + ")";
            case 1:
               return var2.getThreadDescription();
            case 2:
               return new Long(var2.getSequenceNumber());
            case 3:
               return var2.getLevel();
            case 4:
               return var2.getNDC();
            case 5:
               return var2.getCategory();
            case 6:
               return var2.getMessage();
            case 7:
               return var2.getLocation();
            case 8:
               return var2.getThrownStackTrace();
            default:
               String var4 = "The column number " + var1 + "must be between 0 and 8";
               throw new IllegalArgumentException(var4);
         }
      }
   }

   public synchronized boolean addLogRecord(LogRecord var1) {
      this._allRecords.add(var1);
      if (!this._filter.passes(var1)) {
         return false;
      } else {
         this.getFilteredRecords().add(var1);
         this.fireTableRowsInserted(this.getRowCount(), this.getRowCount());
         this.trimRecords();
         return true;
      }
   }

   public int getRowCount() {
      return this.getFilteredRecords().size();
   }

   public boolean needsTrimming() {
      return this._allRecords.size() > this._maxNumberOfLogRecords;
   }

   public List createFilteredRecordsList() {
      ArrayList var1 = new ArrayList();

      for (LogRecord var3 : (Iterable<LogRecord>)(Iterable<?>)(this._allRecords)) {
         if (this._filter.passes(var3)) {
            var1.add(var3);
         }
      }

      return var1;
   }
}
