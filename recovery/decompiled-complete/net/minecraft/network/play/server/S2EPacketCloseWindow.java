package net.minecraft.network.play.server;

import io.netty.channel.nio.AbstractNioMessageChannel$NioMessageUnsafe;
import javax.vecmath.Vector4f;
import net.minecraft.block.BlockTrapDoor$1;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.gen.feature.WorldGenBigTree$FoliageCoordinates;
import org.apache.log4j.Logger;

public class S2EPacketCloseWindow implements Packet<INetHandlerPlayClient> {
   public Vector4f field_0003;
   public Logger field_0005;
   public WorldGenBigTree$FoliageCoordinates field_0002;
   public BlockTrapDoor$1 field_0004;
   public int windowId;
   public AbstractNioMessageChannel$NioMessageUnsafe field_0001;

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleCloseWindow(this);
   }

   public S2EPacketCloseWindow() {
   }

   public S2EPacketCloseWindow(int var1) {
      this.windowId = var1;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.windowId = var1.readUnsignedByte();
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeByte(this.windowId);
   }
}
