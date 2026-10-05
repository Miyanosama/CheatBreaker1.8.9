package net.minecraft.network.play.server;

import io.netty.handler.ssl.util.SimpleTrustManagerFactory$1;
import java.util.List;
import net.minecraft.block.BlockStem;
import net.minecraft.client.model.ModelZombie;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.DataWatcher$WatchableObject;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityIronGolem$AINearestAttackableTargetNonCreeper$1;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Rotations;
import net.optifine.BetterGrass;

public class S0FPacketSpawnMob implements Packet<INetHandlerPlayClient> {
   public byte headPitch;
   public int y;
   public int velocityZ;
   public int type;
   public int x;
   public List<DataWatcher$WatchableObject> watcher;
   public Rotations field_0017;
   public SimpleTrustManagerFactory$1 field_0012;
   public byte pitch;
   public byte yaw;
   public EntityIronGolem$AINearestAttackableTargetNonCreeper$1 field_0001;
   public BlockStem field_0009;
   public ModelZombie field_0011;
   public DataWatcher field_149043_l;
   public int entityId;
   public BetterGrass field_0015;
   public int velocityX;
   public int z;
   public int velocityY;

   public int getEntityType() {
      return this.type;
   }

   public int getEntityID() {
      return this.entityId;
   }

   public byte getPitch() {
      return this.pitch;
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.entityId);
      var1.writeByte(this.type & 0xFF);
      var1.writeInt(this.x);
      var1.writeInt(this.y);
      var1.writeInt(this.z);
      var1.writeByte(this.yaw);
      var1.writeByte(this.pitch);
      var1.writeByte(this.headPitch);
      var1.writeShort(this.velocityX);
      var1.writeShort(this.velocityY);
      var1.writeShort(this.velocityZ);
      this.field_149043_l.writeTo(var1);
   }

   public int getZ() {
      return this.z;
   }

   public S0FPacketSpawnMob(EntityLivingBase var1) {
      this.entityId = var1.F();
      this.type = (byte)EntityList.getEntityID(var1);
      this.x = MathHelper.floor_double(var1.s * 32.0);
      this.y = MathHelper.floor_double(var1.t * 32.0);
      this.z = MathHelper.floor_double(var1.u * 32.0);
      this.yaw = (byte)(var1.y * 256.0F / 360.0F);
      this.pitch = (byte)(var1.z * 256.0F / 360.0F);
      this.headPitch = (byte)(var1.aK * 256.0F / 360.0F);
      double var2 = 3.9;
      double var4 = var1.v;
      double var6 = var1.w;
      double var8 = var1.x;
      if (var4 < -var2) {
         var4 = -var2;
      }

      if (var6 < -var2) {
         var6 = -var2;
      }

      if (var8 < -var2) {
         var8 = -var2;
      }

      if (var4 > var2) {
         var4 = var2;
      }

      if (var6 > var2) {
         var6 = var2;
      }

      if (var8 > var2) {
         var8 = var2;
      }

      this.velocityX = (int)(var4 * 8000.0);
      this.velocityY = (int)(var6 * 8000.0);
      this.velocityZ = (int)(var8 * 8000.0);
      this.field_149043_l = var1.H();
   }

   public int getY() {
      return this.y;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleSpawnMob(this);
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.entityId = var1.readVarIntFromBuffer();
      this.type = var1.readByte() & 255;
      this.x = var1.readInt();
      this.y = var1.readInt();
      this.z = var1.readInt();
      this.yaw = var1.readByte();
      this.pitch = var1.readByte();
      this.headPitch = var1.readByte();
      this.velocityX = var1.readShort();
      this.velocityY = var1.readShort();
      this.velocityZ = var1.readShort();
      this.watcher = DataWatcher.readWatchedListFromPacketBuffer(var1);
   }

   public int getVelocityX() {
      return this.velocityX;
   }

   public int getVelocityY() {
      return this.velocityY;
   }

   public int getX() {
      return this.x;
   }

   public byte getHeadPitch() {
      return this.headPitch;
   }

   public S0FPacketSpawnMob() {
   }

   public List<DataWatcher$WatchableObject> func_149027_c() {
      if (this.watcher == null) {
         this.watcher = this.field_149043_l.getAllWatched();
      }

      return this.watcher;
   }

   public byte getYaw() {
      return this.yaw;
   }

   public int getVelocityZ() {
      return this.velocityZ;
   }
}
