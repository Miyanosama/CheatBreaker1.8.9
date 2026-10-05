package net.minecraft.network.play.server;

import io.netty.handler.codec.spdy.SpdyHttpCodec;
import junit.swingui.DefaultFailureDetailView;
import net.minecraft.network.PacketBuffer;
import org.java_websocket.exceptions.InvalidEncodingException;

public class S14PacketEntity$S17PacketEntityLookMove extends S14PacketEntity {
   public DefaultFailureDetailView field_0001;
   public SpdyHttpCodec field_0002;
   public InvalidEncodingException field_0000;

   @Override
   public void writePacketData(PacketBuffer var1) {
      super.writePacketData(var1);
      var1.writeByte(this.b);
      var1.writeByte(this.c);
      var1.writeByte(this.d);
      var1.writeByte(this.e);
      var1.writeByte(this.f);
      var1.writeBoolean(this.g);
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      super.readPacketData(var1);
      this.b = var1.readByte();
      this.c = var1.readByte();
      this.d = var1.readByte();
      this.e = var1.readByte();
      this.f = var1.readByte();
      this.g = var1.readBoolean();
   }

   public S14PacketEntity$S17PacketEntityLookMove(int var1, byte var2, byte var3, byte var4, byte var5, byte var6, boolean var7) {
      super(var1);
      this.b = var2;
      this.c = var3;
      this.d = var4;
      this.e = var5;
      this.f = var6;
      this.g = var7;
      this.h = true;
   }

   public S14PacketEntity$S17PacketEntityLookMove() {
      this.h = true;
   }
}
