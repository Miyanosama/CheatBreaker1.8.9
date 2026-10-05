package com.cheatbreaker.client.websocket.client;

import com.cheatbreaker.client.websocket.AssetsWebSocket;
import com.cheatbreaker.client.websocket.WSPacket;
import java.security.PublicKey;
import javax.crypto.SecretKey;
import net.minecraft.client.renderer.entity.RenderWitch;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.CryptManager;
import net.minecraft.world.gen.layer.GenLayerRareBiome;
import org.apache.log4j.helpers.PatternParser$MDCPatternConverter;

public class WSPacketClientJoinServerResponse extends WSPacket {
   public RenderWitch field_0004;
   public byte[] field_0002;
   public byte[] field_0003;
   public GenLayerRareBiome field_0000;
   public PatternParser$MDCPatternConverter field_0001;

   public WSPacketClientJoinServerResponse(SecretKey var1, PublicKey var2, byte[] var3) {
      this.field_0003 = CryptManager.encryptData(var2, var1.getEncoded());
      this.field_0002 = CryptManager.encryptData(var2, var3);
   }

   @Override
   public void read(PacketBuffer var1) {
   }

   @Override
   public void write(PacketBuffer var1) {
      this.writeKey(var1, this.field_0003);
      this.writeKey(var1, this.field_0002);
   }

   @Override
   public void handle(AssetsWebSocket var1) {
   }
}
