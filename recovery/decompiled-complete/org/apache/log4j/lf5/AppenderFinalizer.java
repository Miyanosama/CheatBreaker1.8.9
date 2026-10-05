package org.apache.log4j.lf5;

import net.minecraft.client.resources.DefaultResourcePack;
import net.minecraft.network.login.server.S02PacketLoginSuccess;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$12;

public class AppenderFinalizer {
   public LogBrokerMonitor$12 field_0001;
   public LogBrokerMonitor _defaultMonitor = null;
   public S02PacketLoginSuccess field_0000;
   public DefaultResourcePack field_0002;

   public AppenderFinalizer(LogBrokerMonitor var1) {
      this._defaultMonitor = var1;
   }

   public void finalize() {
      System.out.println("Disposing of the default LogBrokerMonitor instance");
      this._defaultMonitor.dispose();
   }
}
