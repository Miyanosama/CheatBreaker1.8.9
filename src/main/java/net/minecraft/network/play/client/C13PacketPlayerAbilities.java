package net.minecraft.network.play.client;

import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class C13PacketPlayerAbilities implements Packet<INetHandlerPlayServer> {
   public float recoveredField2838;
   public float recoveredField2839;
   public boolean recoveredField2840;
   public boolean recoveredField2841;
   public boolean recoveredField2842;
   public boolean recoveredField2843;

   public void setFlying(boolean var1) {
      this.recoveredField2842 = var1;
   }

   public boolean method_22833() {
      return this.recoveredField2843;
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      byte var2 = 0;
      if (this.method_22839()) {
         var2 = (byte)(var2 | 1);
      }

      if (this.isFlying()) {
         var2 = (byte)(var2 | 2);
      }

      if (this.method_22833()) {
         var2 = (byte)(var2 | 4);
      }

      if (this.method_22837()) {
         var2 = (byte)(var2 | 8);
      }

      var1.writeByte(var2);
      var1.writeFloat(this.recoveredField2839);
      var1.writeFloat(this.recoveredField2838);
   }

   public C13PacketPlayerAbilities(PlayerCapabilities var1) {
      this.setInvulnerable(var1.disableDamage);
      this.setFlying(var1.isFlying);
      this.setAllowFlying(var1.allowFlying);
      this.setCreativeMode(var1.isCreativeMode);
      this.setFlySpeed(var1.getFlySpeed());
      this.setWalkSpeed(var1.getWalkSpeed());
   }

   public C13PacketPlayerAbilities() {
   }

   public void setCreativeMode(boolean var1) {
      this.recoveredField2840 = var1;
   }

   public void setWalkSpeed(float var1) {
      this.recoveredField2838 = var1;
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      byte var2 = var1.readByte();
      this.setInvulnerable((var2 & 1) > 0);
      this.setFlying((var2 & 2) > 0);
      this.setAllowFlying((var2 & 4) > 0);
      this.setCreativeMode((var2 & 8) > 0);
      this.setFlySpeed(var1.readFloat());
      this.setWalkSpeed(var1.readFloat());
   }

   public void setAllowFlying(boolean var1) {
      this.recoveredField2843 = var1;
   }

   public boolean method_22839() {
      return this.recoveredField2841;
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processPlayerAbilities(this);
   }

   public boolean isFlying() {
      return this.recoveredField2842;
   }

   public void setInvulnerable(boolean var1) {
      this.recoveredField2841 = var1;
   }

   public void setFlySpeed(float var1) {
      this.recoveredField2839 = var1;
   }

   public boolean method_22837() {
      return this.recoveredField2840;
   }
}
