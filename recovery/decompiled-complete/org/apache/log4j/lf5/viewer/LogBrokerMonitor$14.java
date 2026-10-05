package org.apache.log4j.lf5.viewer;

import io.netty.buffer.Unpooled;
import io.netty.channel.epoll.EpollSocketChannel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.command.ServerCommandManager;
import org.apache.log4j.net.TelnetAppender$SocketHandler;

public class LogBrokerMonitor$14 implements ActionListener {
   public TelnetAppender$SocketHandler field_0003;
   public EpollSocketChannel field_0005;
   public Unpooled field_0002;
   public ModelManager field_0004;
   public LogBrokerMonitor this$0;
   public ServerCommandManager field_0001;

   public LogBrokerMonitor$14(LogBrokerMonitor var1) {
      this.this$0 = var1;
      super();
   }

   public void actionPerformed(ActionEvent var1) {
      this.this$0.selectAllLogTableColumns(true);
      List var2 = this.this$0.updateView();
      this.this$0._table.setView(var2);
   }
}
