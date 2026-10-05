package net.minecraft.client.network;

import com.cheatbreaker.client.websocket.client.WSPacketClientCrashReport;
import com.mojang.authlib.GameProfile;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.status.INetHandlerStatusClient;
import net.minecraft.network.status.client.C01PacketPing;
import net.minecraft.network.status.server.S00PacketServerInfo;
import net.minecraft.network.status.server.S01PacketPong;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.lf5.viewer.LogTable;
import org.java_websocket.drafts.Draft_6455;

public class OldServerPinger$1 implements INetHandlerStatusClient {
   public LogTable field_0005;
   public boolean field_147403_d;
   public WSPacketClientCrashReport field_0001;
   public Draft_6455 field_0002;
   public boolean field_183009_e;
   public long field_175092_e;
   public ByteArrayEncoder field_0010;
   public BlockHugeMushroom field_0000;

   public OldServerPinger$1(OldServerPinger var1, NetworkManager var2, ServerData var3) {
      this.field_147405_c = var1;
      this.field_147404_b = var2;
      this.field_147406_a = var3;
      super();
      this.field_147403_d = false;
      this.field_183009_e = false;
      this.field_175092_e = 539035683L & -6939203931204345844L;
   }

   @Override
   public void onDisconnect(IChatComponent var1) {
      if (!this.field_147403_d) {
         OldServerPinger.access$000().error("Can't ping " + this.field_147406_a.serverIP + ": " + var1.getUnformattedText());
         this.field_147406_a.serverMOTD = EnumChatFormatting.DARK_RED + "Can't connect to server.";
         this.field_147406_a.populationInfo = "";
         OldServerPinger.access$100(this.field_147405_c, this.field_147406_a);
      }
   }

   @Override
   public void handleServerInfo(S00PacketServerInfo var1) {
      if (this.field_183009_e) {
         this.field_147404_b.closeChannel(new ChatComponentText("Received unrequested status"));
      } else {
         this.field_183009_e = true;
         ServerStatusResponse var2 = var1.getResponse();
         if (var2.getServerDescription() != null) {
            this.field_147406_a.serverMOTD = var2.getServerDescription().getFormattedText();
         } else {
            this.field_147406_a.serverMOTD = "";
         }

         if (var2.getProtocolVersionInfo() != null) {
            this.field_147406_a.field_0014 = var2.getProtocolVersionInfo().getName();
            this.field_147406_a.field_0011 = var2.getProtocolVersionInfo().getProtocol();
         } else {
            this.field_147406_a.field_0014 = "Old";
            this.field_147406_a.field_0011 = 0;
         }

         if (var2.getPlayerCountData() == null) {
            this.field_147406_a.populationInfo = EnumChatFormatting.DARK_GRAY + "???";
         } else {
            this.field_147406_a.populationInfo = EnumChatFormatting.GRAY
               + ""
               + var2.getPlayerCountData().getOnlinePlayerCount()
               + ""
               + EnumChatFormatting.DARK_GRAY
               + "/"
               + EnumChatFormatting.GRAY
               + var2.getPlayerCountData().getMaxPlayers();
            if (ArrayUtils.isNotEmpty(var2.getPlayerCountData().getPlayers())) {
               StringBuilder var3 = new StringBuilder();

               for (GameProfile var7 : var2.getPlayerCountData().getPlayers()) {
                  if (var3.length() > 0) {
                     var3.append("\n");
                  }

                  var3.append(var7.getName());
               }

               if (var2.getPlayerCountData().getPlayers().length < var2.getPlayerCountData().getOnlinePlayerCount()) {
                  if (var3.length() > 0) {
                     var3.append("\n");
                  }

                  var3.append("... and ")
                     .append(var2.getPlayerCountData().getOnlinePlayerCount() - var2.getPlayerCountData().getPlayers().length)
                     .append(" more ...");
               }

               this.field_147406_a.field_0004 = var3.toString();
            }
         }

         if (var2.getFavicon() != null) {
            String var8 = var2.getFavicon();
            if (var8.startsWith("data:image/png;base64,")) {
               this.field_147406_a.setBase64EncodedIconData(var8.substring("data:image/png;base64,".length()));
            } else {
               OldServerPinger.access$000().error("Invalid server icon (unknown format)");
            }
         } else {
            this.field_147406_a.setBase64EncodedIconData((String)null);
         }

         this.field_175092_e = Minecraft.getSystemTime();
         this.field_147404_b.sendPacket(new C01PacketPing(this.field_175092_e));
         this.field_147403_d = true;
      }
   }

   @Override
   public void handlePong(S01PacketPong var1) {
      long var2 = this.field_175092_e;
      long var4 = Minecraft.getSystemTime();
      this.field_147406_a.pingToServer = var4 - var2;
      this.field_147404_b.closeChannel(new ChatComponentText("Finished"));
   }
}
