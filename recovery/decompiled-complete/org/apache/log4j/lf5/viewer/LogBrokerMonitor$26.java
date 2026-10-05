package org.apache.log4j.lf5.viewer;

import io.netty.handler.codec.socks.SocksCmdResponseDecoder$1;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import net.minecraft.client.audio.SoundManager$1;
import net.minecraft.world.chunk.storage.AnvilSaveHandler;

public class LogBrokerMonitor$26 implements ActionListener {
   public SoundManager$1 field_0001;
   public AnvilSaveHandler field_0003;
   public LogBrokerMonitor this$0;
   public SocksCmdResponseDecoder$1 field_0002;

   public void actionPerformed(ActionEvent var1) {
      String var2 = JOptionPane.showInputDialog(this.this$0._logMonitorFrame, "Find text: ", "Search Record Messages", 3);
      this.this$0.setSearchText(var2);
      this.this$0.findSearchText();
   }

   public LogBrokerMonitor$26(LogBrokerMonitor var1) {
      this.this$0 = var1;
      super();
   }
}
