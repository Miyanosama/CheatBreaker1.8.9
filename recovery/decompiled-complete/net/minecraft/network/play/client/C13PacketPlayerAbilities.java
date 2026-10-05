package net.minecraft.network.play.client;

import junit.extensions.ActiveTestSuite$1;
import net.minecraft.client.resources.SimpleReloadableResourceManager$1;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.entity.player.PlayerCapabilities;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import org.apache.log4j.lf5.util.Resource;

public class C13PacketPlayerAbilities implements Packet<INetHandlerPlayServer> {
   public float field_0004;
   public float field_0007;
   public boolean field_0003;
   public Resource field_0006;
   public EntityWolf field_0000;
   public ActiveTestSuite$1 field_0001;
   public boolean field_0008;
   public SimpleReloadableResourceManager$1 field_0005;
   public boolean field_0002;
   public boolean field_0009;

   public void setFlying(boolean var1) {
      this.field_0002 = var1;
   }

   public boolean method_22833() {
      return this.field_0009;
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
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
      var1.writeFloat(this.field_0007);
      var1.writeFloat(this.field_0004);
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
      this.field_0003 = var1;
   }

   public void setWalkSpeed(float var1) {
      this.field_0004 = var1;
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

   public void setAllowFlying(boolean var1) {
      this.field_0009 = var1;
   }

   public boolean method_22839() {
      return this.field_0008;
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processPlayerAbilities(this);
   }

   public boolean isFlying() {
      return this.field_0002;
   }

   public void setInvulnerable(boolean var1) {
      this.field_0008 = var1;
   }

   public void setFlySpeed(float var1) {
      this.field_0007 = var1;
   }

   public boolean method_22837() {
      return this.field_0003;
   }
}
