package net.minecraft.network.play.server;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.EnumDifficulty;

public class S41PacketServerDifficulty implements Packet<INetHandlerPlayClient> {
   public boolean difficultyLocked;
   public EnumDifficulty difficulty;

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleServerDifficulty(this);
   }

   public S41PacketServerDifficulty(EnumDifficulty var1, boolean var2) {
      this.difficulty = var1;
      this.difficultyLocked = var2;
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.difficulty = EnumDifficulty.getDifficultyEnum(var1.readUnsignedByte());
   }

   public boolean isDifficultyLocked() {
      return this.difficultyLocked;
   }

   public S41PacketServerDifficulty() {
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeByte(this.difficulty.getDifficultyId());
   }

   public EnumDifficulty getDifficulty() {
      return this.difficulty;
   }
}
