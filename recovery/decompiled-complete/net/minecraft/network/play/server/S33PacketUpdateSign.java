package net.minecraft.network.play.server;

import io.netty.handler.codec.DecoderResult;
import net.minecraft.client.gui.GuiLockIconButton;
import net.minecraft.client.renderer.entity.layers.LayerHeldItemWitch;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class S33PacketUpdateSign implements Packet<INetHandlerPlayClient> {
   public World world;
   public LayerHeldItemWitch field_0005;
   public BlockPos blockPos;
   public DecoderResult field_0004;
   public IChatComponent[] lines;
   public GuiLockIconButton field_0001;

   public S33PacketUpdateSign() {
   }

   public S33PacketUpdateSign(World var1, BlockPos var2, IChatComponent[] var3) {
      this.world = var1;
      this.blockPos = var2;
      this.lines = new IChatComponent[]{var3[0], var3[1], var3[2], var3[3]};
   }

   public IChatComponent[] getLines() {
      return this.lines;
   }

   public BlockPos getPos() {
      return this.blockPos;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.blockPos = var1.readBlockPos();
      this.lines = new IChatComponent[4];

      for (int var2 = 0; var2 < 4; var2++) {
         this.lines[var2] = var1.readChatComponent();
      }
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeBlockPos(this.blockPos);

      for (int var2 = 0; var2 < 4; var2++) {
         var1.writeChatComponent(this.lines[var2]);
      }
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleUpdateSign(this);
   }
}
