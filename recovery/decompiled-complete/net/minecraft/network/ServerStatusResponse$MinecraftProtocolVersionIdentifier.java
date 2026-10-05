package net.minecraft.network;

import io.netty.handler.codec.socks.SocksInitResponseDecoder;
import net.minecraft.enchantment.EnchantmentUntouching;
import net.minecraft.network.play.server.S0DPacketCollectItem;
import recovered.unidentified.UnidentifiedClass4984;

public class ServerStatusResponse$MinecraftProtocolVersionIdentifier {
   public int protocol;
   public UnidentifiedClass4984 field_0005;
   public S0DPacketCollectItem field_0002;
   public String name;
   public EnchantmentUntouching field_0000;
   public SocksInitResponseDecoder field_0001;

   public String getName() {
      return this.name;
   }

   public ServerStatusResponse$MinecraftProtocolVersionIdentifier(String var1, int var2) {
      this.name = var1;
      this.protocol = var2;
   }

   public int getProtocol() {
      return this.protocol;
   }
}
