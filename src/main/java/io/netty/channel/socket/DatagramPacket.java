package io.netty.channel.socket;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.channel.DefaultAddressedEnvelope;
import java.net.InetSocketAddress;
import net.minecraft.entity.ai.EntityAIDefendVillage;
import net.minecraft.world.gen.structure.MapGenVillage;

public class DatagramPacket extends DefaultAddressedEnvelope<ByteBuf, InetSocketAddress> implements ByteBufHolder {

   public DatagramPacket retain(int var1) {
      super.retain(var1);
      return this;
   }

   public DatagramPacket retain() {
      super.retain();
      return this;
   }

   public DatagramPacket copy() {
      return new DatagramPacket(this.content().copy(), this.recipient(), this.sender());
   }

   public DatagramPacket(ByteBuf var1, InetSocketAddress var2, InetSocketAddress var3) {
      super(var1, var2, var3);
   }

   public DatagramPacket duplicate() {
      return new DatagramPacket(this.content().duplicate(), this.recipient(), this.sender());
   }

   public DatagramPacket(ByteBuf var1, InetSocketAddress var2) {
      super(var1, var2);
   }
}
