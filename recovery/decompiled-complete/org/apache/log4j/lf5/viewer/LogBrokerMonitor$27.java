package org.apache.log4j.lf5.viewer;

import io.netty.channel.epoll.EpollSocketChannel$1;
import io.netty.util.internal.PlatformDependent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javazoom.jl.converter.Converter$PrintWriterProgressListener;
import net.optifine.gui.TooltipProviderShaderOptions;
import net.optifine.shaders.config.PropertyDefaultFastFancyOff;
import org.java_websocket.drafts.Draft;

public class LogBrokerMonitor$27 implements ActionListener {
   public PlatformDependent field_0003;
   public LogBrokerMonitor this$0;
   public Converter$PrintWriterProgressListener field_0002;
   public Draft field_0004;
   public TooltipProviderShaderOptions field_0000;
   public PropertyDefaultFastFancyOff field_0001;
   public EpollSocketChannel$1 field_0006;

   public LogBrokerMonitor$27(LogBrokerMonitor var1) {
      this.this$0 = var1;
      super();
   }

   public void actionPerformed(ActionEvent var1) {
      String var2 = JOptionPane.showInputDialog(this.this$0._logMonitorFrame, "Sort by this NDC: ", "Sort Log Records by NDC", 3);
      this.this$0.setNDCTextFilter(var2);
      this.this$0.sortByNDC();
      this.this$0._table.getFilteredLogTableModel().refresh();
      this.this$0.updateStatusLabel();
   }
}
