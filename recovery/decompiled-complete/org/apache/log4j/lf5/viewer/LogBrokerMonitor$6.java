package org.apache.log4j.lf5.viewer;

import com.cheatbreaker.client.util.ClientDiagnosticReport;
import io.netty.channel.DefaultAddressedEnvelope;
import net.minecraft.stats.AchievementList;

public class LogBrokerMonitor$6 {
   public FilteredLogTableModel val$model;
   public ClientDiagnosticReport field_0004;
   public DefaultAddressedEnvelope field_0001;
   public AchievementList field_0003;
   public LogBrokerMonitor this$0;

   public LogBrokerMonitor$6(LogBrokerMonitor var1, FilteredLogTableModel var2) {
      this.this$0 = var1;
      this.val$model = var2;
      super();
   }

   public String toString() {
      return "Maximum number of displayed LogRecords: " + this.val$model._maxNumberOfLogRecords;
   }
}
