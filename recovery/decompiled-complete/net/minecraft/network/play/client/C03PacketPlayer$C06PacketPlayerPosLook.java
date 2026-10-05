package net.minecraft.network.play.client;

import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.server.S47PacketPlayerListHeaderFooter;
import org.newsclub.net.unix.AFUNIXSocket;
import recovered.unidentified.UnidentifiedClass0243;

public class C03PacketPlayer$C06PacketPlayerPosLook extends C03PacketPlayer {
   public AFUNIXSocket field_0000;
   public S47PacketPlayerListHeaderFooter field_0001;
   public UnidentifiedClass0243 field_0002;

   public C03PacketPlayer$C06PacketPlayerPosLook() {
      this.g = true;
      this.h = true;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.a = var1.readDouble();
      this.b = var1.readDouble();
      this.c = var1.readDouble();
      this.d = var1.readFloat();
      this.e = var1.readFloat();
      super.readPacketData(var1);
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeDouble(this.a);
      var1.writeDouble(this.b);
      var1.writeDouble(this.c);
      var1.writeFloat(this.d);
      var1.writeFloat(this.e);
      super.writePacketData(var1);
   }

   public C03PacketPlayer$C06PacketPlayerPosLook(double var1, double var3, double var5, float var7, float var8, boolean var9) {
      this.a = var1;
      this.b = var3;
      this.c = var5;
      this.d = var7;
      this.e = var8;
      this.f = var9;
      this.h = true;
      this.g = true;
   }
}
