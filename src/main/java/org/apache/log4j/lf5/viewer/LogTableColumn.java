package org.apache.log4j.lf5.viewer;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogTableColumn implements Serializable {
   public static final long recoveredField3808 = -4275827753626456547L;
   public static LogTableColumn DATE = new LogTableColumn("Date");
   public static LogTableColumn THREAD = new LogTableColumn("Thread");
   public String _label;
   public static LogTableColumn MESSAGE_NUM = new LogTableColumn("Message #");
   public static LogTableColumn LEVEL = new LogTableColumn("Level");
   public static LogTableColumn NDC = new LogTableColumn("NDC");
   public static LogTableColumn CATEGORY = new LogTableColumn("Category");
   public static LogTableColumn MESSAGE = new LogTableColumn("Message");
   public static LogTableColumn LOCATION = new LogTableColumn("Location");
   public static LogTableColumn THROWN = new LogTableColumn("Thrown");
   public static LogTableColumn[] _log4JColumns = new LogTableColumn[]{
      LogTableColumn.DATE, THREAD, LogTableColumn.MESSAGE_NUM, LogTableColumn.LEVEL, NDC, CATEGORY, LogTableColumn.MESSAGE, LOCATION, LogTableColumn.THROWN
   };
   public static Map _logTableColumnMap = new HashMap();

   static {
      for (int var0 = 0; var0 < _log4JColumns.length; var0++) {
         _logTableColumnMap.put(_log4JColumns[var0].getLabel(), _log4JColumns[var0]);
      }
   }

   public String getLabel() {
      return this._label;
   }

   public int hashCode() {
      return this._label.hashCode();
   }

   public String toString() {
      return this._label;
   }

   public boolean equals(Object var1) {
      boolean var2 = false;
      if (var1 instanceof LogTableColumn && this.getLabel() == ((LogTableColumn)var1).getLabel()) {
         var2 = true;
      }

      return var2;
   }

   public static LogTableColumn valueOf(String var0) throws org.apache.log4j.lf5.viewer.LogTableColumnFormatException {
      LogTableColumn var1 = null;
      if (var0 != null) {
         var0 = var0.trim();
         var1 = (LogTableColumn)_logTableColumnMap.get(var0);
      }

      if (var1 == null) {
         StringBuffer var2 = new StringBuffer();
         var2.append("Error while trying to parse (" + var0 + ") into");
         var2.append(" a LogTableColumn.");
         throw new LogTableColumnFormatException(var2.toString());
      } else {
         return var1;
      }
   }

   public LogTableColumn(String var1) {
      this._label = var1;
   }

   public static LogTableColumn[] getLogTableColumnArray() {
      return _log4JColumns;
   }

   public static List getLogTableColumns() {
      return Arrays.asList(_log4JColumns);
   }
}
