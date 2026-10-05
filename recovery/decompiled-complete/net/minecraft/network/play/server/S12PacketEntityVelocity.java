package net.minecraft.network.play.server;

import com.cheatbreaker.client.ui.mainmenu.GradientTextButton;
import io.netty.handler.timeout.ReadTimeoutHandler$ReadTimeoutTask;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.optifine.config.Matches;

public class S12PacketEntityVelocity implements Packet<INetHandlerPlayClient> {
   public int motionY;
   public EntityPlayerMP field_0006;
   public ReadTimeoutHandler$ReadTimeoutTask field_0002;
   public GradientTextButton field_0005;
   public int motionX;
   public int entityID;
   public int motionZ;
   public Matches field_0004;

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleEntityVelocity(this);
   }

   public int getEntityID() {
      return this.entityID;
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.entityID);
      var1.writeShort(this.motionX);
      var1.writeShort(this.motionY);
      var1.writeShort(this.motionZ);
   }

   public int getMotionX() {
      return this.motionX;
   }

   public int getMotionZ() {
      return this.motionZ;
   }

   public int getMotionY() {
      return this.motionY;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.entityID = var1.readVarIntFromBuffer();
      this.motionX = var1.readShort();
      this.motionY = var1.readShort();
      this.motionZ = var1.readShort();
   }

   public S12PacketEntityVelocity(Entity var1) {
      this(var1.F(), var1.v, var1.w, var1.x);
   }

   public S12PacketEntityVelocity() {
   }

   public S12PacketEntityVelocity(int var1, double var2, double var4, double var6) {
      this.entityID = var1;
      double var8 = 3.9;
      if (var2 < -var8) {
         var2 = -var8;
      }

      if (var4 < -var8) {
         var4 = -var8;
      }

      if (var6 < -var8) {
         var6 = -var8;
      }

      if (var2 > var8) {
         var2 = var8;
      }

      if (var4 > var8) {
         var4 = var8;
      }

      if (var6 > var8) {
         var6 = var8;
      }

      this.motionX = (int)(var2 * 8000.0);
      this.motionY = (int)(var4 * 8000.0);
      this.motionZ = (int)(var6 * 8000.0);
   }
}
