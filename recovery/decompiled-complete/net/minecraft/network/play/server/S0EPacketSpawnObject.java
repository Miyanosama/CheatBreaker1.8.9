package net.minecraft.network.play.server;

import com.cheatbreaker.client.ui.CompetitiveLeaveWarningGui;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import net.minecraft.block.BlockHalfStoneSlabNew;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.MathHelper;
import net.optifine.util.IntegratedServerUtils;
import org.apache.log4j.pattern.FileDatePatternConverter;

public class S0EPacketSpawnObject implements Packet<INetHandlerPlayClient> {
   public int speedZ;
   public int z;
   public int field_149020_k;
   public int x;
   public int speedX;
   public int y;
   public int pitch;
   public BlockHalfStoneSlabNew field_0009;
   public int speedY;
   public int yaw;
   public int type;
   public InsecureTrustManagerFactory field_0007;
   public CompetitiveLeaveWarningGui field_0008;
   public int entityId;
   public IntegratedServerUtils field_0010;
   public FileDatePatternConverter field_0012;

   public int getSpeedY() {
      return this.speedY;
   }

   public void setX(int var1) {
      this.x = var1;
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeVarIntToBuffer(this.entityId);
      var1.writeByte(this.type);
      var1.writeInt(this.x);
      var1.writeInt(this.y);
      var1.writeInt(this.z);
      var1.writeByte(this.pitch);
      var1.writeByte(this.yaw);
      var1.writeInt(this.field_149020_k);
      if (this.field_149020_k > 0) {
         var1.writeShort(this.speedX);
         var1.writeShort(this.speedY);
         var1.writeShort(this.speedZ);
      }
   }

   public void func_149002_g(int var1) {
      this.field_149020_k = var1;
   }

   public int getY() {
      return this.y;
   }

   public int getX() {
      return this.x;
   }

   public int func_149009_m() {
      return this.field_149020_k;
   }

   public int getType() {
      return this.type;
   }

   public S0EPacketSpawnObject(Entity var1, int var2) {
      this(var1, var2, 0);
   }

   public void setSpeedY(int var1) {
      this.speedY = var1;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.entityId = var1.readVarIntFromBuffer();
      this.type = var1.readByte();
      this.x = var1.readInt();
      this.y = var1.readInt();
      this.z = var1.readInt();
      this.pitch = var1.readByte();
      this.yaw = var1.readByte();
      this.field_149020_k = var1.readInt();
      if (this.field_149020_k > 0) {
         this.speedX = var1.readShort();
         this.speedY = var1.readShort();
         this.speedZ = var1.readShort();
      }
   }

   public int getSpeedZ() {
      return this.speedZ;
   }

   public S0EPacketSpawnObject() {
   }

   public int getEntityID() {
      return this.entityId;
   }

   public int getYaw() {
      return this.yaw;
   }

   public int getZ() {
      return this.z;
   }

   public void setZ(int var1) {
      this.z = var1;
   }

   public int getPitch() {
      return this.pitch;
   }

   public void setSpeedX(int var1) {
      this.speedX = var1;
   }

   public int getSpeedX() {
      return this.speedX;
   }

   public void setY(int var1) {
      this.y = var1;
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleSpawnObject(this);
   }

   public void setSpeedZ(int var1) {
      this.speedZ = var1;
   }

   public S0EPacketSpawnObject(Entity var1, int var2, int var3) {
      this.entityId = var1.F();
      this.x = MathHelper.floor_double(var1.s * 32.0);
      this.y = MathHelper.floor_double(var1.t * 32.0);
      this.z = MathHelper.floor_double(var1.u * 32.0);
      this.pitch = MathHelper.floor_float(var1.z * 256.0F / 360.0F);
      this.yaw = MathHelper.floor_float(var1.y * 256.0F / 360.0F);
      this.type = var2;
      this.field_149020_k = var3;
      if (var3 > 0) {
         double var4 = var1.v;
         double var6 = var1.w;
         double var8 = var1.x;
         double var10 = 3.9;
         if (var4 < -var10) {
            var4 = -var10;
         }

         if (var6 < -var10) {
            var6 = -var10;
         }

         if (var8 < -var10) {
            var8 = -var10;
         }

         if (var4 > var10) {
            var4 = var10;
         }

         if (var6 > var10) {
            var6 = var10;
         }

         if (var8 > var10) {
            var8 = var10;
         }

         this.speedX = (int)(var4 * 8000.0);
         this.speedY = (int)(var6 * 8000.0);
         this.speedZ = (int)(var8 * 8000.0);
      }
   }
}
