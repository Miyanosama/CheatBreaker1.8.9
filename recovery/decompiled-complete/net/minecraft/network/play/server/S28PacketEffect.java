package net.minecraft.network.play.server;

import net.minecraft.client.renderer.entity.RenderItem$5;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;
import org.java_websocket.framing.PongFrame;
import recovered.unidentified.UnidentifiedClass1096;

public class S28PacketEffect implements Packet<INetHandlerPlayClient> {
   public RenderItem$5 field_0003;
   public BlockPos soundPos;
   public int soundData;
   public UnidentifiedClass1096 field_0004;
   public PongFrame field_0000;
   public int soundType;
   public boolean serverWide;

   public S28PacketEffect(int var1, BlockPos var2, int var3, boolean var4) {
      this.soundType = var1;
      this.soundPos = var2;
      this.soundData = var3;
      this.serverWide = var4;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.soundType = var1.readInt();
      this.soundPos = var1.readBlockPos();
      this.soundData = var1.readInt();
      this.serverWide = var1.readBoolean();
   }

   public int getSoundData() {
      return this.soundData;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleEffect(this);
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeInt(this.soundType);
      var1.writeBlockPos(this.soundPos);
      var1.writeInt(this.soundData);
      var1.writeBoolean(this.serverWide);
   }

   public S28PacketEffect() {
   }

   public int getSoundType() {
      return this.soundType;
   }

   public BlockPos getSoundPos() {
      return this.soundPos;
   }

   public boolean isSoundServerwide() {
      return this.serverWide;
   }
}
