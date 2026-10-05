package net.minecraft.network.play.client;

import net.minecraft.client.audio.SoundHandler$2;
import net.minecraft.command.server.CommandOp;
import net.minecraft.network.PacketBuffer;

public class C03PacketPlayer$C04PacketPlayerPosition extends C03PacketPlayer {
   public CommandOp field_0000;
   public SoundHandler$2 field_0001;

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeDouble(this.a);
      var1.writeDouble(this.b);
      var1.writeDouble(this.c);
      super.writePacketData(var1);
   }

   public C03PacketPlayer$C04PacketPlayerPosition(double var1, double var3, double var5, boolean var7) {
      this.a = var1;
      this.b = var3;
      this.c = var5;
      this.f = var7;
      this.g = true;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.a = var1.readDouble();
      this.b = var1.readDouble();
      this.c = var1.readDouble();
      super.readPacketData(var1);
   }

   public C03PacketPlayer$C04PacketPlayerPosition() {
      this.g = true;
   }
}
