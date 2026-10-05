package net.minecraft.network.play.server;

import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.spdy.SpdyHeaderBlockRawDecoder$State;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.World;

public class S43PacketCamera implements Packet<INetHandlerPlayClient> {
   public int entityId;
   public HttpClientCodec field_0003;
   public ModelQuadruped field_0000;
   public SpdyHeaderBlockRawDecoder$State field_0002;

   public S43PacketCamera() {
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.entityId);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleCamera(this);
   }

   public Entity getEntity(World var1) {
      return var1.getEntityByID(this.entityId);
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.entityId = var1.readVarIntFromBuffer();
   }

   public S43PacketCamera(Entity var1) {
      this.entityId = var1.F();
   }
}
