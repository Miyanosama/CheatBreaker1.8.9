package org.apache.log4j.lf5.viewer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import net.minecraft.block.BlockPlanks;
import net.minecraft.command.CommandParticle;
import net.optifine.player.CapeUtils;
import org.java_websocket.client.WebSocketClient;

public class LogBrokerMonitor$20 implements ActionListener {
   public BlockPlanks field_0002;
   public CommandParticle field_0004;
   public WebSocketClient field_0001;
   public LogBrokerMonitor this$0;
   public CapeUtils field_0000;

   public void actionPerformed(ActionEvent var1) {
      this.this$0.requestExit();
   }

   public LogBrokerMonitor$20(LogBrokerMonitor var1) {
      this.this$0 = var1;
      super();
   }
}
