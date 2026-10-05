package net.minecraft.network.play.client;

import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C03PacketPlayer implements Packet<INetHandlerPlayServer> {
   public float e;
   public double b;
   public boolean g;
   public boolean f;
   public double c;
   public boolean h;
   public double a;
   public float d;

   public void setMoving(boolean var1) {
      this.g = var1;
   }

   public double method_05060() {
      return this.a;
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeByte(this.f ? 1 : 0);
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
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

   public static class C04PacketPlayerPosition extends C03PacketPlayer {
      @Override
      public void writePacketData(PacketBuffer var1) throws java.io.IOException {
         var1.writeDouble(this.a);
         var1.writeDouble(this.b);
         var1.writeDouble(this.c);
         super.writePacketData(var1);
      }

      public C04PacketPlayerPosition(double var1, double var3, double var5, boolean var7) {
         this.a = var1;
         this.b = var3;
         this.c = var5;
         this.f = var7;
         this.g = true;
      }

      @Override
      public void readPacketData(PacketBuffer var1) throws java.io.IOException {
         this.a = var1.readDouble();
         this.b = var1.readDouble();
         this.c = var1.readDouble();
         super.readPacketData(var1);
      }

      public C04PacketPlayerPosition() {
         this.g = true;
      }
   }

   public static class C05PacketPlayerLook extends C03PacketPlayer {
      public C05PacketPlayerLook(float var1, float var2, boolean var3) {
         this.d = var1;
         this.e = var2;
         this.f = var3;
         this.h = true;
      }

      @Override
      public void readPacketData(PacketBuffer var1) throws java.io.IOException {
         this.d = var1.readFloat();
         this.e = var1.readFloat();
         super.readPacketData(var1);
      }

      @Override
      public void writePacketData(PacketBuffer var1) throws java.io.IOException {
         var1.writeFloat(this.d);
         var1.writeFloat(this.e);
         super.writePacketData(var1);
      }

      public C05PacketPlayerLook() {
         this.h = true;
      }
   }

   public static class C06PacketPlayerPosLook extends C03PacketPlayer {
      public C06PacketPlayerPosLook() {
         this.g = true;
         this.h = true;
      }

      @Override
      public void readPacketData(PacketBuffer var1) throws java.io.IOException {
         this.a = var1.readDouble();
         this.b = var1.readDouble();
         this.c = var1.readDouble();
         this.d = var1.readFloat();
         this.e = var1.readFloat();
         super.readPacketData(var1);
      }

      @Override
      public void writePacketData(PacketBuffer var1) throws java.io.IOException {
         var1.writeDouble(this.a);
         var1.writeDouble(this.b);
         var1.writeDouble(this.c);
         var1.writeFloat(this.d);
         var1.writeFloat(this.e);
         super.writePacketData(var1);
      }

      public C06PacketPlayerPosLook(double var1, double var3, double var5, float var7, float var8, boolean var9) {
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
}
