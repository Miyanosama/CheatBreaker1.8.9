package net.minecraft.network.login.server;

import com.cheatbreaker.client.ui.overlay.element.InputFieldElement;
import io.netty.handler.codec.compression.Snappy$1;
import net.minecraft.item.crafting.RecipesArmorDyes;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.login.INetHandlerLoginClient;

public class S03PacketEnableCompression implements Packet<INetHandlerLoginClient> {
   public int compressionTreshold;
   public Snappy$1 field_0003;
   public InputFieldElement field_0000;
   public RecipesArmorDyes field_0002;

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.compressionTreshold);
   }

   public int getCompressionTreshold() {
      return this.compressionTreshold;
   }

   public S03PacketEnableCompression() {
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.compressionTreshold = var1.readVarIntFromBuffer();
   }

   public S03PacketEnableCompression(int var1) {
      this.compressionTreshold = var1;
   }

   public void processPacket(INetHandlerLoginClient var1) {
      var1.handleEnableCompression(this);
   }
}
