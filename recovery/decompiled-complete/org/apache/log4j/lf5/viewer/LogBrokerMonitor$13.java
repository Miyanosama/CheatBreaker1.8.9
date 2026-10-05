package org.apache.log4j.lf5.viewer;

import io.netty.buffer.PooledDirectByteBuf$1;
import io.netty.channel.ChannelOutboundBuffer$1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class LogBrokerMonitor$13 implements ActionListener {
   public LogBrokerMonitor field_0001;
   public PooledDirectByteBuf$1 field_0002;
   public ChannelOutboundBuffer$1 field_0000;

   public void actionPerformed(ActionEvent var1) {
      List var2 = this.field_0001.updateView();
      this.field_0001._table.setView(var2);
   }

   public LogBrokerMonitor$13(LogBrokerMonitor var1) {
      this.field_0001 = var1;
      super();
   }
}
