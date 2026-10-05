package net.minecraft.realms;

import com.mojang.authlib.GameProfile;
import io.netty.handler.codec.socks.SocksCmdRequest$1;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.status.INetHandlerStatusClient;
import net.minecraft.network.status.client.C01PacketPing;
import net.minecraft.network.status.server.S00PacketServerInfo;
import net.minecraft.network.status.server.S01PacketPong;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.optifine.gui.GuiDetailSettingsOF;
import org.apache.commons.lang3.ArrayUtils;

public class RealmsServerStatusPinger$1 implements INetHandlerStatusClient {
   public boolean field_154345_e;
   public SocksCmdRequest$1 field_0004;
   public GuiDetailSettingsOF field_0000;

   @Override
   public void onDisconnect(IChatComponent var1) {
      if (!this.field_154345_e) {
         RealmsServerStatusPinger.access$000().error("Can't ping " + this.field_154343_c + ": " + var1.getUnformattedText());
      }
   }

   @Override
   public void handleServerInfo(S00PacketServerInfo var1) {
      ServerStatusResponse var2 = var1.getResponse();
      if (var2.getPlayerCountData() != null) {
         this.field_154341_a.nrOfPlayers = String.valueOf(var2.getPlayerCountData().getOnlinePlayerCount());
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

            this.field_154341_a.playerList = var3.toString();
         }
      } else {
         this.field_154341_a.playerList = "";
      }

      this.field_154342_b.sendPacket(new C01PacketPing(Realms.currentTimeMillis()));
      this.field_154345_e = true;
   }

   @Override
   public void handlePong(S01PacketPong var1) {
      this.field_154342_b.closeChannel(new ChatComponentText("Finished"));
   }

   public RealmsServerStatusPinger$1(RealmsServerStatusPinger var1, RealmsServerPing var2, NetworkManager var3, String var4) {
      this.field_154344_d = var1;
      this.field_154341_a = var2;
      this.field_154342_b = var3;
      this.field_154343_c = var4;
      super();
      this.field_154345_e = false;
   }
}
