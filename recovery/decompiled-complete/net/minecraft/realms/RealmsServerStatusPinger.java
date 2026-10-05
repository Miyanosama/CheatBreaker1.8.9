package net.minecraft.realms;

import com.cheatbreaker.client.module.type.PotionStatusModule;
import com.google.common.collect.Lists;
import io.netty.channel.rxtx.RxtxDeviceAddress;
import io.netty.handler.codec.compression.ZlibCodecFactory;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import java.net.InetAddress;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.gui.GuiOptionsRowList$Row;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.status.client.C00PacketServerQuery;
import net.minecraft.util.ChatComponentText;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class RealmsServerStatusPinger {
   public DefaultUnmarshallerProvider field_0003;
   public PotionStatusModule field_0005;
   public static Logger LOGGER = LogManager.getLogger();
   public ZlibCodecFactory field_0004;
   public List<NetworkManager> connections = Collections.synchronizedList(Lists.newArrayList());
   public GuiOptionsRowList$Row field_0001;
   public RxtxDeviceAddress field_0006;

   public void pingServer(String var1, RealmsServerPing var2) {
      if (var1 != null && !var1.startsWith("0.0.0.0") && !var1.isEmpty()) {
         RealmsServerAddress var3 = RealmsServerAddress.parseString(var1);
         NetworkManager var4 = NetworkManager.createNetworkManagerAndConnect(InetAddress.getByName(var3.method_07947()), var3.method_07949(), false);
         this.connections.add(var4);
         var4.setNetHandler(new RealmsServerStatusPinger$1(this, var2, var4, var1));

         try {
            var4.sendPacket(new C00Handshake(RealmsSharedConstants.field_0006, var3.method_07947(), var3.method_07949(), EnumConnectionState.STATUS));
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
