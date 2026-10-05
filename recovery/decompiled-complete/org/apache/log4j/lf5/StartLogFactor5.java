package org.apache.log4j.lf5;

import io.netty.handler.codec.http.HttpObjectEncoder;
import io.netty.handler.ssl.SslHandshakeCompletionEvent;
import net.minecraft.block.BlockBush;
import net.minecraft.block.material.Material$1;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor;

public class StartLogFactor5 {
   public HttpObjectEncoder field_0001;
   public Material$1 field_0003;
   public BlockBush field_0000;
   public SslHandshakeCompletionEvent field_0002;

   public static void main(String[] var0) {
      LogBrokerMonitor var1 = new LogBrokerMonitor(LogLevel.getLog4JLevels());
      var1.setFrameSize(LF5Appender.getDefaultMonitorWidth(), LF5Appender.getDefaultMonitorHeight());
      var1.setFontSize(12);
      var1.show();
   }
}
