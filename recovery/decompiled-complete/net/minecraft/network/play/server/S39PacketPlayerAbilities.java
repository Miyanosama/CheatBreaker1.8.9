package net.minecraft.network.play.server;

import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.command.CommandClone;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class S39PacketPlayerAbilities implements Packet<INetHandlerPlayClient> {
   public boolean field_0003;
   public float field_0006;
   public boolean field_0002;
   public boolean field_0005;
   public ModelBlock field_0000;
   public float field_0001;
   public boolean field_0007;
   public CommandClone field_0004;

   public void setCreativeMode(boolean var1) {
      this.field_0003 = var1;
   }

   public float getFlySpeed() {
      return this.field_0001;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      byte var2 = var1.readByte();
      this.setInvulnerable((var2 & 1) > 0);
      this.setFlying((var2 & 2) > 0);
      this.setAllowFlying((var2 & 4) > 0);
      this.setCreativeMode((var2 & 8) > 0);
      this.setFlySpeed(var1.readFloat());
      this.setWalkSpeed(var1.readFloat());
   }

   public void setWalkSpeed(float var1) {
      this.field_0006 = var1;
   }

   public S39PacketPlayerAbilities(PlayerCapabilities var1) {
      this.setInvulnerable(var1.disableDamage);
      this.setFlying(var1.isFlying);
      this.setAllowFlying(var1.allowFlying);
      this.setCreativeMode(var1.isCreativeMode);
      this.setFlySpeed(var1.getFlySpeed());
      this.setWalkSpeed(var1.getWalkSpeed());
   }

   public void setFlySpeed(float var1) {
      this.field_0001 = var1;
   }

   public boolean isAllowFlying() {
      return this.field_0005;
   }

   public boolean isFlying() {
      return this.field_0002;
   }

   public S39PacketPlayerAbilities() {
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      byte var2 = 0;
      if (this.isInvulnerable()) {
         var2 = (byte)(var2 | 1);
      }

      if (this.isFlying()) {
         var2 = (byte)(var2 | 2);
      }

      if (this.isAllowFlying()) {
         var2 = (byte)(var2 | 4);
      }

      if (this.isCreativeMode()) {
         var2 = (byte)(var2 | 8);
      }

      var1.writeByte(var2);
      var1.writeFloat(this.field_0001);
      var1.writeFloat(this.field_0006);
   }

   public void setFlying(boolean var1) {
      this.field_0002 = var1;
   }

   public void setInvulnerable(boolean var1) {
      this.field_0007 = var1;
   }

   public float getWalkSpeed() {
      return this.field_0006;
   }

   public void setAllowFlying(boolean var1) {
      this.field_0005 = var1;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handlePlayerAbilities(this);
   }

   public boolean isInvulnerable() {
      return this.field_0007;
   }

   public boolean isCreativeMode() {
      return this.field_0003;
   }
}
