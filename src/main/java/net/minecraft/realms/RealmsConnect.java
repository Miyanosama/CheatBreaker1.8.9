package net.minecraft.realms;

import java.net.InetAddress;
import java.net.UnknownHostException;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetHandlerLoginClient;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.util.ChatComponentTranslation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RealmsConnect {
   public volatile boolean aborted = false;
   public NetworkManager connection;
   public static Logger LOGGER = LogManager.getLogger();
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

   public void connect(final String var1, final int var2) {
      Realms.setConnectedToRealms(true);
      (new Thread("Realms-connect-task") {
            @Override
            public void run() {
               InetAddress var1x = null;

               try {
                  var1x = InetAddress.getByName(var1);
                  if (RealmsConnect.this.aborted) {
                     return;
                  }

                  RealmsConnect.this.connection = NetworkManager.createNetworkManagerAndConnect(
                     var1x, var2, Minecraft.getMinecraft().gameSettings.isUsingNativeTransport()
                  );
                  if (RealmsConnect.this.aborted) {
                     return;
                  }

                  RealmsConnect.this.connection
                     .setNetHandler(
                        new NetHandlerLoginClient(RealmsConnect.this.connection, Minecraft.getMinecraft(), RealmsConnect.this.onlineScreen.getProxy())
                     );
                  if (RealmsConnect.this.aborted) {
                     return;
                  }

                  RealmsConnect.this.connection.sendPacket(new C00Handshake(47, var1, var2, EnumConnectionState.LOGIN));
                  if (RealmsConnect.this.aborted) {
                     return;
                  }

                  RealmsConnect.this.connection.sendPacket(new C00PacketLoginStart(Minecraft.getMinecraft().getSession().getProfile()));
               } catch (UnknownHostException var5) {
                  Realms.clearResourcePack();
                  if (RealmsConnect.this.aborted) {
                     return;
                  }

                  RealmsConnect.LOGGER.error("Couldn't connect to world", var5);
                  Minecraft.getMinecraft().getResourcePackRepository().clearResourcePack();
                  Realms.setScreen(
                     new DisconnectedRealmsScreen(
                        RealmsConnect.this.onlineScreen,
                        "connect.failed",
                        new ChatComponentTranslation("disconnect.genericReason", "Unknown host '" + var1 + "'")
                     )
                  );
               } catch (Exception var6) {
                  Realms.clearResourcePack();
                  if (RealmsConnect.this.aborted) {
                     return;
                  }

                  RealmsConnect.LOGGER.error("Couldn't connect to world", var6);
                  String var3 = var6.toString();
                  if (var1x != null) {
                     String var4 = var1x.toString() + ":" + var2;
                     var3 = var3.replaceAll(var4, "");
                  }

                  Realms.setScreen(
                     new DisconnectedRealmsScreen(
                        RealmsConnect.this.onlineScreen, "connect.failed", new ChatComponentTranslation("disconnect.genericReason", var3)
                     )
                  );
               }
            }
         })
         .start();
   }
}
