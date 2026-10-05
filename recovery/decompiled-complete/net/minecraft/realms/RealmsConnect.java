package net.minecraft.realms;

import io.netty.handler.codec.http.HttpHeaders$1;
import net.minecraft.network.NetworkManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RealmsConnect {
   public volatile boolean aborted = false;
   public NetworkManager connection;
   public static Logger LOGGER = LogManager.getLogger();
   public HttpHeaders$1 field_0003;
   public RealmsScreen onlineScreen;

   public RealmsConnect(RealmsScreen var1) {
      this.onlineScreen = var1;
   }

   public void abort() {
      this.aborted = true;
   }

   public void tick() {
      if (this.connection != null) {
         if (this.connection.isChannelOpen()) {
            this.connection.processReceivedPackets();
         } else {
            this.connection.checkDisconnected();
         }
      }
   }

   public void connect(String var1, int var2) {
      Realms.setConnectedToRealms(true);
      new RealmsConnect$1(this, "Realms-connect-task", var1, var2).start();
   }
}
