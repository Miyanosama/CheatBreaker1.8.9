package net.minecraft.network.play.server;

import io.netty.handler.ssl.SslHandler;
import net.minecraft.block.material.MaterialTransparent;
import net.minecraft.client.network.NetHandlerPlayClient$3$1;
import net.minecraft.command.CommandTime;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.MathHelper;
import org.java_websocket.framing.TextFrame;

public class S18PacketEntityTeleport implements Packet<INetHandlerPlayClient> {
   public byte pitch;
   public byte yaw;
   public TextFrame field_0004;
   public CommandTime field_0009;
   public int entityId;
   public int posY;
   public Items field_0011;
   public int posZ;
   public NetHandlerPlayClient$3$1 field_0003;
   public boolean onGround;
   public SslHandler field_0000;
   public MaterialTransparent field_0006;
   public int posX;

   public byte getPitch() {
      return this.pitch;
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.entityId);
      var1.writeInt(this.posX);
      var1.writeInt(this.posY);
      var1.writeInt(this.posZ);
      var1.writeByte(this.yaw);
      var1.writeByte(this.pitch);
      var1.writeBoolean(this.onGround);
   }

   public byte getYaw() {
      return this.yaw;
   }

   public int getZ() {
      return this.posZ;
   }

   public S18PacketEntityTeleport(Entity var1) {
      this.entityId = var1.F();
      this.posX = MathHelper.floor_double(var1.s * 32.0);
      this.posY = MathHelper.floor_double(var1.t * 32.0);
      this.posZ = MathHelper.floor_double(var1.u * 32.0);
      this.yaw = (byte)(var1.y * 256.0F / 360.0F);
      this.pitch = (byte)(var1.z * 256.0F / 360.0F);
      this.onGround = var1.C;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.entityId = var1.readVarIntFromBuffer();
      this.posX = var1.readInt();
      this.posY = var1.readInt();
      this.posZ = var1.readInt();
      this.yaw = var1.readByte();
      this.pitch = var1.readByte();
      this.onGround = var1.readBoolean();
   }

   public boolean getOnGround() {
      return this.onGround;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleEntityTeleport(this);
   }

   public int getY() {
      return this.posY;
   }

   public S18PacketEntityTeleport() {
   }

   public int getEntityId() {
      return this.entityId;
   }

   public int getX() {
      return this.posX;
   }

   public S18PacketEntityTeleport(int var1, int var2, int var3, int var4, byte var5, byte var6, boolean var7) {
      this.entityId = var1;
      this.posX = var2;
      this.posY = var3;
      this.posZ = var4;
      this.yaw = var5;
      this.pitch = var6;
      this.onGround = var7;
   }
}
