package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.apache.log4j.helpers.PatternParser$MDCPatternConverter;

public class LogBrokerMonitor$23 implements ActionListener {
   public LogBrokerMonitor this$0;
   public PatternParser$MDCPatternConverter field_0001;

   public void actionPerformed(ActionEvent var1) {
      this.this$0.setMaxRecordConfiguration();
   }

   public LogBrokerMonitor$23(LogBrokerMonitor var1) {
      this.this$0 = var1;
      super();
   }
}
