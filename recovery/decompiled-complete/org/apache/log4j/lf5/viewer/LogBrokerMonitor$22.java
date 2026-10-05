package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.minecraft.util.CombatEntry;
import net.optifine.util.KeyUtils;

public class LogBrokerMonitor$22 implements ActionListener {
   public LogBrokerMonitor this$0;
   public CombatEntry field_0002;
   public KeyUtils field_0000;

   public void actionPerformed(ActionEvent var1) {
      this.this$0.resetConfiguration();
   }

   public LogBrokerMonitor$22(LogBrokerMonitor var1) {
      this.this$0 = var1;
      super();
   }
}
