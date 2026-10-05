package net.minecraft.network.play.server;

import io.netty.handler.codec.http.HttpHeaderEntity;
import net.minecraft.block.BlockRedstoneWire$EnumAttachPosition;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.WorldSettings$GameType;
import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayerBiome;

public class S07PacketRespawn implements Packet<INetHandlerPlayClient> {
   public HttpHeaderEntity field_0003;
   public GenLayerBiome field_0005;
   public BlockRedstoneWire$EnumAttachPosition field_0002;
   public WorldType worldType;
   public EnumDifficulty difficulty;
   public int dimensionID;
   public WorldSettings$GameType gameType;

   public S07PacketRespawn(int var1, EnumDifficulty var2, WorldType var3, WorldSettings$GameType var4) {
      this.dimensionID = var1;
      this.difficulty = var2;
      this.gameType = var4;
      this.worldType = var3;
   }

   public S07PacketRespawn() {
   }

   public WorldType getWorldType() {
      return this.worldType;
   }

   public int getDimensionID() {
      return this.dimensionID;
   }

   public EnumDifficulty getDifficulty() {
      return this.difficulty;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.dimensionID = var1.readInt();
      this.difficulty = EnumDifficulty.getDifficultyEnum(var1.readUnsignedByte());
      this.gameType = WorldSettings$GameType.getByID(var1.readUnsignedByte());
      this.worldType = WorldType.parseWorldType(var1.readStringFromBuffer(16));
      if (this.worldType == null) {
         this.worldType = WorldType.DEFAULT;
      }
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleRespawn(this);
   }

   public WorldSettings$GameType getGameType() {
      return this.gameType;
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.dimensionID);
      var1.writeByte(this.difficulty.getDifficultyId());
      var1.writeByte(this.gameType.getID());
      var1.writeString(this.worldType.getWorldTypeName());
   }
}
