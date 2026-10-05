package net.minecraft.network.play.server;

import io.netty.handler.codec.socks.SocksCmdRequestDecoder;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import net.minecraft.client.renderer.entity.RenderXPOrb;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.stats.StatBase$4;

public class S13PacketDestroyEntities implements Packet<INetHandlerPlayClient> {
   public SocksCmdRequestDecoder field_0002;
   public int[] entityIDs;
   public ConcurrentHashMapV8 field_0001;
   public RenderXPOrb field_0003;
   public StatBase$4 field_0000;

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.entityIDs.length);

      for (int var2 = 0; var2 < this.entityIDs.length; var2++) {
         var1.writeVarIntToBuffer(this.entityIDs[var2]);
      }
   }

   public int[] getEntityIDs() {
      return this.entityIDs;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.entityIDs = new int[var1.readVarIntFromBuffer()];

      for (int var2 = 0; var2 < this.entityIDs.length; var2++) {
         this.entityIDs[var2] = var1.readVarIntFromBuffer();
      }
   }

   public S13PacketDestroyEntities(int... var1) {
      this.entityIDs = var1;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleDestroyEntities(this);
   }

   public S13PacketDestroyEntities() {
   }
}
