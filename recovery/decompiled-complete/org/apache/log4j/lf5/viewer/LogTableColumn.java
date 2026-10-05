package org.apache.log4j.lf5.viewer;

import com.cheatbreaker.client.ui.ServerRiskWarningGui;
import io.netty.handler.codec.http.QueryStringDecoder;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import junit.swingui.FailureRunView;
import net.minecraft.client.stream.MetadataPlayerDeath;

public class LogTableColumn implements Serializable {
   public static LogTableColumn NDC = new LogTableColumn("NDC");
   public static LogTableColumn CATEGORY = new LogTableColumn("Category");
   public static LogTableColumn THREAD = new LogTableColumn("Thread");
   public String _label;
   public FailureRunView field_0002;
   public static Map _logTableColumnMap = new HashMap();
   public static LogTableColumn LOCATION = new LogTableColumn("Location");
   public static LogTableColumn[] _log4JColumns = new LogTableColumn[]{
      LogTableColumn.DATE, THREAD, LogTableColumn.MESSAGE_NUM, LogTableColumn.LEVEL, NDC, CATEGORY, LogTableColumn.MESSAGE, LOCATION, LogTableColumn.THROWN
   };
   public static long field_0004;
   public QueryStringDecoder field_0016;
   public static LogTableColumn MESSAGE_NUM = new LogTableColumn("Message #");
   public static LogTableColumn MESSAGE = new LogTableColumn("Message");
   public static LogTableColumn DATE = new LogTableColumn("Date");
   public static LogTableColumn LEVEL = new LogTableColumn("Level");
   public ServerRiskWarningGui field_0011;
   public MetadataPlayerDeath field_0013;
   public static LogTableColumn THROWN = new LogTableColumn("Thrown");

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

   public static LogTableColumn valueOf(String var0) {
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
