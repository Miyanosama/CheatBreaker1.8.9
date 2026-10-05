package net.minecraft.client.network;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.net.InetAddress;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.entity.passive.EntityVillager$ItemAndEmeraldToItem;
import net.minecraft.entity.player.EntityPlayer$1;
import net.minecraft.init.Bootstrap$11;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.status.client.C00PacketServerQuery;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.GameRules$ValueType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OldServerPinger {
   public EntityVillager$ItemAndEmeraldToItem field_0003;
   public Bootstrap$11 field_0005;
   public static Splitter PING_RESPONSE_SPLITTER = Splitter.on('\u0000').limit(6);
   public GameRules$ValueType field_0004;
   public static Logger logger = LogManager.getLogger();
   public EntityPlayer$1 field_0001;
   public List<NetworkManager> pingDestinations = Collections.synchronizedList(Lists.newArrayList());

   public void clearPendingNetworks() {
      synchronized (this.pingDestinations) {
         Iterator var2 = this.pingDestinations.iterator();

         while (var2.hasNext()) {
            NetworkManager var3 = (NetworkManager)var2.next();
            if (var3.isChannelOpen()) {
               var2.remove();
               var3.closeChannel(new ChatComponentText("Cancelled"));
            }
         }
      }
   }

   public void pingPendingNetworks() {
      synchronized (this.pingDestinations) {
         Iterator var2 = this.pingDestinations.iterator();

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

   public void method_08034(ServerData var1) {
      ServerAddress var2 = ServerAddress.fromString(var1.serverIP);
      NetworkManager var3 = NetworkManager.createNetworkManagerAndConnect(InetAddress.getByName(var2.getIP()), var2.getPort(), false);
      this.pingDestinations.add(var3);
      var1.serverMOTD = "Pinging...";
      var1.pingToServer = -1L & -1L;
      var1.field_0004 = null;
      var3.setNetHandler(new OldServerPinger$1(this, var3, var1));

      try {
         var3.sendPacket(new C00Handshake(47, var2.getIP(), var2.getPort(), EnumConnectionState.STATUS));
         var3.sendPacket(new C00PacketServerQuery());
      } catch (Throwable var5) {
         logger.error(var5);
      }
   }

   public void tryCompatibilityPing(ServerData var1) {
      ServerAddress var2 = ServerAddress.fromString(var1.serverIP);
      new Bootstrap()
         .group(NetworkManager.field_0009.getValue())
         .handler(new OldServerPinger$2(this, var2, var1))
         .channel(NioSocketChannel.class)
         .connect(var2.getIP(), var2.getPort());
   }
}
