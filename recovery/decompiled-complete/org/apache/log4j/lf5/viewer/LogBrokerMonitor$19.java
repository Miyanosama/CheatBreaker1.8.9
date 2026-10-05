package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.minecraft.entity.passive.EntityVillager$PriceInfo;
import net.minecraft.item.Item$6;

public class LogBrokerMonitor$19 implements ActionListener {
   public EntityVillager$PriceInfo field_0001;
   public LogBrokerMonitor this$0;
   public Item$6 field_0000;

   public LogBrokerMonitor$19(LogBrokerMonitor var1) {
      this.this$0 = var1;
      super();
   }

   public void actionPerformed(ActionEvent var1) {
      this.this$0.requestOpenMRU(var1);
   }
}
