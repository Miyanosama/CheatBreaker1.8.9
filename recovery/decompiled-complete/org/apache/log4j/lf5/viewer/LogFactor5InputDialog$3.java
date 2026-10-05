package org.apache.log4j.lf5.viewer;

import io.netty.handler.ssl.util.SimpleTrustManagerFactory$SimpleTrustManagerFactorySpi;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.apache.log4j.spi.ThrowableInformation;

public class LogFactor5InputDialog$3 implements ActionListener {
   public LogBrokerMonitor$6 field_0001;
   public ThrowableInformation field_0003;
   public LogFactor5InputDialog this$0;
   public SimpleTrustManagerFactory$SimpleTrustManagerFactorySpi field_0002;

   public void actionPerformed(ActionEvent var1) {
      this.this$0.hide();
      LogFactor5InputDialog.access$000(this.this$0).setText("");
   }

   public LogFactor5InputDialog$3(LogFactor5InputDialog var1) {
      this.this$0 = var1;
      super();
   }
}
