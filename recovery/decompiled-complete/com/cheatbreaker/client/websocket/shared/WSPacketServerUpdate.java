package com.cheatbreaker.client.websocket.shared;

import com.cheatbreaker.client.ui.element.KeybindElement;
import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import io.netty.handler.codec.http.cors.CorsConfig$Builder;
import io.netty.handler.traffic.ChannelTrafficShapingHandler$ToSend;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.network.PacketBuffer;
import net.optifine.config.ConnectedParser$2;

public class WSPacketServerUpdate extends WSPacket {
   public ConnectedParser$2 field_0005;
   public CorsConfig$Builder field_0002;
   public String server;
   public EnumEnchantmentType field_0000;
   public ChannelTrafficShapingHandler$ToSend field_0001;
   public String playerId;
   public KeybindElement field_0003;

   public String getServer() {
      return this.server;
   }

   @Override
   public void handle(AssetsWebSocket var1) {
      var1.handleServerUpdate(this);
   }

   @Override
   public void read(PacketBuffer var1) {
      this.server = var1.readStringFromBuffer(52);
      this.playerId = var1.readStringFromBuffer(100);
   }

   public String getPlayerId() {
      return this.playerId;
   }

   public WSPacketServerUpdate() {
   }

   public WSPacketServerUpdate(String var1, String var2) {
      this.playerId = var1;
      this.server = var2;
   }

   @Override
   public void write(PacketBuffer var1) {
      var1.writeString(this.server);
      var1.writeString(this.playerId);
   }
}
