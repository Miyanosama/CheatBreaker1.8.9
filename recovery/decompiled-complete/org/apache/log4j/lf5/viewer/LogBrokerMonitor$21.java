package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.minecraft.client.renderer.entity.layers.LayerSnowmanHead;

public class LogBrokerMonitor$21 implements ActionListener {
   public LayerSnowmanHead field_0000;
   public LogBrokerMonitor this$0;

   public void actionPerformed(ActionEvent var1) {
      this.this$0.saveConfiguration();
   }

   public LogBrokerMonitor$21(LogBrokerMonitor var1) {
      this.this$0 = var1;
      super();
   }
}
