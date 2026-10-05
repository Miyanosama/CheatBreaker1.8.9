package net.minecraft.network.play.server;

import net.minecraft.client.renderer.entity.RenderTntMinecart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class S0APacketUseBed implements Packet<INetHandlerPlayClient> {
   public BlockPos bedPos;
   public int playerID;
   public RenderTntMinecart field_0000;

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleUseBed(this);
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.playerID = var1.readVarIntFromBuffer();
      this.bedPos = var1.readBlockPos();
   }

   public EntityPlayer getPlayer(World var1) {
      return (EntityPlayer)var1.getEntityByID(this.playerID);
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.playerID);
      var1.writeBlockPos(this.bedPos);
   }

   public BlockPos getBedPosition() {
      return this.bedPos;
   }

   public S0APacketUseBed(EntityPlayer var1, BlockPos var2) {
      this.playerID = var1.F();
      this.bedPos = var2;
   }

   public S0APacketUseBed() {
   }
}
