package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.minecraft.entity.ai.EntityAIBase;
import org.apache.log4j.jmx.LoggerDynamicMBean;

public class LogBrokerMonitor$18 implements ActionListener {
   public LogBrokerMonitor this$0;
   public LoggerDynamicMBean field_0002;
   public EntityAIBase field_0000;

   public LogBrokerMonitor$18(LogBrokerMonitor var1) {
      this.this$0 = var1;
      super();
   }

   public void actionPerformed(ActionEvent var1) {
      this.this$0.requestClose();
   }
}
