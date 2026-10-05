package net.minecraft.network.play.server;

import net.minecraft.client.gui.GuiScreenWorking;
import net.minecraft.entity.boss.EntityWither$1;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import org.apache.log4j.DefaultCategoryFactory;
import org.apache.log4j.spi.NOPLoggerRepository;

public class S06PacketUpdateHealth implements Packet<INetHandlerPlayClient> {
   public EntityWither$1 field_0003;
   public int foodLevel;
   public float health;
   public float saturationLevel;
   public GuiScreenWorking field_0000;
   public DefaultCategoryFactory field_0001;
   public NOPLoggerRepository field_0006;

   public S06PacketUpdateHealth(float var1, int var2, float var3) {
      this.health = var1;
      this.foodLevel = var2;
      this.saturationLevel = var3;
   }

   public S06PacketUpdateHealth() {
   }

   public float getHealth() {
      return this.health;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleUpdateHealth(this);
   }

   public float getSaturationLevel() {
      return this.saturationLevel;
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeFloat(this.health);
      var1.writeVarIntToBuffer(this.foodLevel);
      var1.writeFloat(this.saturationLevel);
   }

   public int getFoodLevel() {
      return this.foodLevel;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.health = var1.readFloat();
      this.foodLevel = var1.readVarIntFromBuffer();
      this.saturationLevel = var1.readFloat();
   }
}
