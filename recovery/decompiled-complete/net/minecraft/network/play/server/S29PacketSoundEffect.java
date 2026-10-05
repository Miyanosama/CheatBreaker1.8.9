package net.minecraft.network.play.server;

import junit.framework.Assert;
import net.minecraft.client.multiplayer.ServerData$ServerResourceMode;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.MathHelper;
import org.apache.commons.lang3.Validate;
import org.apache.log4j.helpers.FormattingInfo;

public class S29PacketSoundEffect implements Packet<INetHandlerPlayClient> {
   public FormattingInfo field_0004;
   public int posZ;
   public float soundVolume;
   public ServerData$ServerResourceMode field_0006;
   public int posY = Integer.MAX_VALUE;
   public String soundName;
   public Assert field_0008;
   public int soundPitch;
   public int posX;

   public double getZ() {
      return this.posZ / 8.0F;
   }

   public S29PacketSoundEffect() {
   }

   public double getY() {
      return this.posY / 8.0F;
   }

   public double getX() {
      return this.posX / 8.0F;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.soundName = var1.readStringFromBuffer(256);
      this.posX = var1.readInt();
      this.posY = var1.readInt();
      this.posZ = var1.readInt();
      this.soundVolume = var1.readFloat();
      this.soundPitch = var1.readUnsignedByte();
   }

   public float getPitch() {
      return this.soundPitch / 63.0F;
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeString(this.soundName);
      var1.writeInt(this.posX);
      var1.writeInt(this.posY);
      var1.writeInt(this.posZ);
      var1.writeFloat(this.soundVolume);
      var1.writeByte(this.soundPitch);
   }

   public float getVolume() {
      return this.soundVolume;
   }

   public String getSoundName() {
      return this.soundName;
   }

   public S29PacketSoundEffect(String var1, double var2, double var4, double var6, float var8, float var9) {
      Validate.notNull(var1, "name", new Object[0]);
      this.soundName = var1;
      this.posX = (int)(var2 * 8.0);
      this.posY = (int)(var4 * 8.0);
      this.posZ = (int)(var6 * 8.0);
      this.soundVolume = var8;
      this.soundPitch = (int)(var9 * 63.0F);
      var9 = MathHelper.clamp_float(var9, 0.0F, 255.0F);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleSoundEffect(this);
   }
}
