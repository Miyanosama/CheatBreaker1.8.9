package net.minecraft.network.play.server;

import io.netty.buffer.UnreleasableByteBuf;
import io.netty.channel.sctp.SctpChannelOption;
import io.netty.util.concurrent.AbstractEventExecutorGroup;
import net.minecraft.network.PacketBuffer;
import recovered.unidentified.UnidentifiedClass1421;

public class S14PacketEntity$S16PacketEntityLook extends S14PacketEntity {
   public SctpChannelOption field_0001;
   public UnreleasableByteBuf field_0002;
   public AbstractEventExecutorGroup field_0000;
   public UnidentifiedClass1421 field_0003;

   public S14PacketEntity$S16PacketEntityLook() {
      this.h = true;
   }

   public S14PacketEntity$S16PacketEntityLook(int var1, byte var2, byte var3, boolean var4) {
      super(var1);
      this.e = var2;
      this.f = var3;
      this.h = true;
      this.g = var4;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      super.readPacketData(var1);
      this.e = var1.readByte();
      this.f = var1.readByte();
      this.g = var1.readBoolean();
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      super.writePacketData(var1);
      var1.writeByte(this.e);
      var1.writeByte(this.f);
      var1.writeBoolean(this.g);
   }
}
