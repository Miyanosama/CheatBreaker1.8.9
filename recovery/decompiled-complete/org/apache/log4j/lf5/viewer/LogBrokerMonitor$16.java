package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.world.storage.WorldInfo$4;

public class LogBrokerMonitor$16 implements ActionListener {
   public LogBrokerMonitor this$0;
   public WorldInfo$4 field_0002;
   public PathNavigateGround field_0000;

   public LogBrokerMonitor$16(LogBrokerMonitor var1) {
      this.this$0 = var1;
      super();
   }

   public void actionPerformed(ActionEvent var1) {
      this.this$0.requestOpen();
   }
}
