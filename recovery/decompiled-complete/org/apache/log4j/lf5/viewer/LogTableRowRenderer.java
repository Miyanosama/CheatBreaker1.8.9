package org.apache.log4j.lf5.viewer;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ForEachTransformedValueTask;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import org.apache.log4j.lf5.LogLevel;
import org.apache.log4j.lf5.LogRecord;
import org.java_websocket.enums.ReadyState;

public class LogTableRowRenderer extends DefaultTableCellRenderer {
   public static long field_0002;
   public ConcurrentHashMapV8$ForEachTransformedValueTask field_0004;
   public boolean _highlightFatal = true;
   public ReadyState field_0003;
   public Color _color = new Color(230, 230, 230);

   public Component getTableCellRendererComponent(JTable var1, Object var2, boolean var3, boolean var4, int var5, int var6) {
      if (var5 % 2 == 0) {
         this.setBackground(this._color);
      } else {
         this.setBackground(Color.white);
      }

      FilteredLogTableModel var7 = (FilteredLogTableModel)var1.getModel();
      LogRecord var8 = var7.getFilteredRecord(var5);
      this.setForeground(this.getLogLevelColor(var8.getLevel()));
      return super.getTableCellRendererComponent(var1, var2, var3, var4, var5, var6);
   }

   public Color getLogLevelColor(LogLevel var1) {
      return (Color)LogLevel.getLogLevelColorMap().get(var1);
   }
}
