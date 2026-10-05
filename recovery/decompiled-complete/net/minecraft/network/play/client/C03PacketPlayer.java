package net.minecraft.network.play.client;

import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.renderer.tileentity.TileEntityBannerRenderer;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C03PacketPlayer implements Packet<INetHandlerPlayServer> {
   public float e;
   public GuiLanguage field_0007;
   public double b;
   public boolean g;
   public boolean f;
   public double c;
   public boolean h;
   public double a;
   public float d;
   public TileEntityBannerRenderer field_0009;

   public void setMoving(boolean var1) {
      this.g = var1;
   }

   public double method_05060() {
      return this.a;
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeByte(this.f ? 1 : 0);
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.f = var1.readUnsignedByte() != 0;
   }

   public C03PacketPlayer(boolean var1) {
      this.f = var1;
   }

   public float method_05065() {
      return this.d;
   }

   public double method_05059() {
      return this.b;
   }

   public boolean method_05064() {
      return this.f;
   }

   public float method_05057() {
      return this.e;
   }

   public boolean method_05058() {
      return this.h;
   }

   public boolean method_05066() {
      return this.g;
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processPlayer(this);
   }

   public double method_05063() {
      return this.c;
   }

   public C03PacketPlayer() {
   }
}
