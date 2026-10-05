package net.minecraft.realms;

import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import java.net.InetAddress;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.status.INetHandlerStatusClient;
import net.minecraft.network.status.client.C00PacketServerQuery;
import net.minecraft.network.status.client.C01PacketPing;
import net.minecraft.network.status.server.S00PacketServerInfo;
import net.minecraft.network.status.server.S01PacketPong;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RealmsServerStatusPinger {
   public static Logger LOGGER = LogManager.getLogger();
   public List<NetworkManager> connections = Collections.synchronizedList(Lists.newArrayList());

   public void pingServer(final String var1, final RealmsServerPing var2) throws java.net.UnknownHostException {
      if (var1 != null && !var1.startsWith("0.0.0.0") && !var1.isEmpty()) {
         RealmsServerAddress var3 = RealmsServerAddress.parseString(var1);
         final NetworkManager var4 = NetworkManager.createNetworkManagerAndConnect(InetAddress.getByName(var3.method_07947()), var3.method_07949(), false);
         this.connections.add(var4);
         var4.setNetHandler(
            new INetHandlerStatusClient() {
               public boolean field_154345_e = false;

               @Override
               public void onDisconnect(IChatComponent var1x) {
                  if (!this.field_154345_e) {
                     RealmsServerStatusPinger.LOGGER.error("Can't ping " + var1 + ": " + var1x.getUnformattedText());
                  }
               }

               @Override
               public void handleServerInfo(S00PacketServerInfo var1x) {
                  ServerStatusResponse var2x = var1x.getResponse();
                  if (var2x.getPlayerCountData() != null) {
                     var2.nrOfPlayers = String.valueOf(var2x.getPlayerCountData().getOnlinePlayerCount());
                     if (ArrayUtils.isNotEmpty(var2x.getPlayerCountData().getPlayers())) {
                        StringBuilder var3x = new StringBuilder();

                        for (GameProfile var7 : var2x.getPlayerCountData().getPlayers()) {
                           if (var3x.length() > 0) {
                              var3x.append("\n");
                           }

                           var3x.append(var7.getName());
                        }

                        if (var2x.getPlayerCountData().getPlayers().length < var2x.getPlayerCountData().getOnlinePlayerCount()) {
                           if (var3x.length() > 0) {
                              var3x.append("\n");
                           }

                           var3x.append("... and ")
                              .append(var2x.getPlayerCountData().getOnlinePlayerCount() - var2x.getPlayerCountData().getPlayers().length)
                              .append(" more ...");
                        }

                        var2.playerList = var3x.toString();
                     }
                  } else {
                     var2.playerList = "";
                  }

                  var4.sendPacket(new C01PacketPing(Realms.currentTimeMillis()));
                  this.field_154345_e = true;
               }

               @Override
               public void handlePong(S01PacketPong var1x) {
                  var4.closeChannel(new ChatComponentText("Finished"));
               }
            }
         );

         try {
            var4.sendPacket(new C00Handshake(RealmsSharedConstants.recoveredField51, var3.method_07947(), var3.method_07949(), EnumConnectionState.STATUS));
            var4.sendPacket(new C00PacketServerQuery());
         } catch (Throwable var6) {
            LOGGER.error(var6);
         }
      }
   }

   public void removeAll() {
      synchronized (this.connections) {
         Iterator var2 = this.connections.iterator();

         while (var2.hasNext()) {
            NetworkManager var3 = (NetworkManager)var2.next();
            if (var3.isChannelOpen()) {
               var2.remove();
               var3.closeChannel(new ChatComponentText("Cancelled"));
            }
         }
      }
   }

   public void tick() {
      synchronized (this.connections) {
         Iterator var2 = this.connections.iterator();

         while (var2.hasNext()) {
            NetworkManager var3 = (NetworkManager)var2.next();
            if (var3.isChannelOpen()) {
               var3.processReceivedPackets();
            } else {
               var2.remove();
               var3.checkDisconnected();
            }
         }
      }
   }
}
