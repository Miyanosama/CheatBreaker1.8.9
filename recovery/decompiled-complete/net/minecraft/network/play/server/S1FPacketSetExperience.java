package net.minecraft.network.play.server;

import net.minecraft.block.BlockCompressedPowered;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.village.Village$VillageAggressor;

public class S1FPacketSetExperience implements Packet<INetHandlerPlayClient> {
   public int totalExperience;
   public float field_149401_a;
   public Village$VillageAggressor field_0001;
   public BlockCompressedPowered field_0003;
   public int level;

   public S1FPacketSetExperience() {
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.field_149401_a = var1.readFloat();
      this.level = var1.readVarIntFromBuffer();
      this.totalExperience = var1.readVarIntFromBuffer();
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleSetExperience(this);
   }

   public S1FPacketSetExperience(float var1, int var2, int var3) {
      this.field_149401_a = var1;
      this.totalExperience = var2;
      this.level = var3;
   }

   public int getTotalExperience() {
      return this.totalExperience;
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeFloat(this.field_149401_a);
      var1.writeVarIntToBuffer(this.level);
      var1.writeVarIntToBuffer(this.totalExperience);
   }

   public int getLevel() {
      return this.level;
   }

   public float func_149397_c() {
      return this.field_149401_a;
   }
}
