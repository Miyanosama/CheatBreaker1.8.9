package net.minecraft.network.play.server;

import java.util.List;
import java.util.UUID;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.MathHelper;

public class S0CPacketSpawnPlayer implements Packet<INetHandlerPlayClient> {
   public int entityId;
   public byte yaw;
   public int x;
   public UUID playerId;
   public byte pitch;
   public int z;
   public int y;
   public List<DataWatcher.WatchableObject> field_148958_j;
   public int currentItem;
   public DataWatcher watcher;

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleSpawnPlayer(this);
   }

   public S0CPacketSpawnPlayer() {
   }

   public int getEntityID() {
      return this.entityId;
   }

   public byte getPitch() {
      return this.pitch;
   }

   public int getCurrentItemID() {
      return this.currentItem;
   }

   public S0CPacketSpawnPlayer(EntityPlayer var1) {
      this.entityId = var1.F();
      this.playerId = var1.getGameProfile().getId();
      this.x = MathHelper.floor_double(var1.s * 32.0);
      this.y = MathHelper.floor_double(var1.t * 32.0);
      this.z = MathHelper.floor_double(var1.u * 32.0);
      this.yaw = (byte)(var1.y * 256.0F / 360.0F);
      this.pitch = (byte)(var1.z * 256.0F / 360.0F);
      ItemStack var2 = var1.bi.getCurrentItem();
      this.currentItem = var2 == null ? 0 : Item.getIdFromItem(var2.getItem());
      this.watcher = var1.H();
   }

   public int getZ() {
      return this.z;
   }

   public UUID getPlayer() {
      return this.playerId;
   }

   public int getX() {
      return this.x;
   }

   public List<DataWatcher.WatchableObject> func_148944_c() {
      if (this.field_148958_j == null) {
         this.field_148958_j = this.watcher.getAllWatched();
      }

      return this.field_148958_j;
   }

   public int getY() {
      return this.y;
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeVarIntToBuffer(this.entityId);
      var1.writeUuid(this.playerId);
      var1.writeInt(this.x);
      var1.writeInt(this.y);
      var1.writeInt(this.z);
      var1.writeByte(this.yaw);
      var1.writeByte(this.pitch);
      var1.writeShort(this.currentItem);
      this.watcher.writeTo(var1);
   }

   public byte getYaw() {
      return this.yaw;
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.entityId = var1.readVarIntFromBuffer();
      this.playerId = var1.readUuid();
      this.x = var1.readInt();
      this.y = var1.readInt();
      this.z = var1.readInt();
      this.yaw = var1.readByte();
      this.pitch = var1.readByte();
      this.currentItem = var1.readShort();
      this.field_148958_j = DataWatcher.readWatchedListFromPacketBuffer(var1);
   }
}
