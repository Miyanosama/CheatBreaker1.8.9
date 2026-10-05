package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S2BPacketChangeGameState implements Packet<INetHandlerPlayClient> {
   public int state;
   public float field_149141_c;
   public static String[] MESSAGE_NAMES = new String[]{"tile.bed.notValid"};

   public S2BPacketChangeGameState(int var1, float var2) {
      this.state = var1;
      this.field_149141_c = var2;
   }

   public float func_149137_d() {
      return this.field_149141_c;
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.state = var1.readUnsignedByte();
      this.field_149141_c = var1.readFloat();
   }

   public int getGameState() {
      return this.state;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleChangeGameState(this);
   }

   public S2BPacketChangeGameState() {
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeByte(this.state);
      var1.writeFloat(this.field_149141_c);
   }
}
