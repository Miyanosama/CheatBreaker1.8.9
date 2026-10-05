package net.minecraft.network.play.client;

import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.BlockPos;

public class C08PacketPlayerBlockPlacement implements Packet<INetHandlerPlayServer> {
   public static BlockPos field_179726_a = new BlockPos(-1, -1, -1);
   public float facingX;
   public float facingZ;
   public ItemStack stack;
   public int placedBlockDirection;
   public float facingY;
   public BlockPos position;

   public ItemStack getStack() {
      return this.stack;
   }

   @Override
   public void readPacketData(PacketBuffer var1) {
      this.position = var1.readBlockPos();
      this.placedBlockDirection = var1.readUnsignedByte();
      this.stack = var1.readItemStackFromBuffer();
      this.facingX = var1.readUnsignedByte() / 16.0F;
      this.facingY = var1.readUnsignedByte() / 16.0F;
      this.facingZ = var1.readUnsignedByte() / 16.0F;
   }

   public void processPacket(INetHandlerPlayServer var1) {
      var1.processPlayerBlockPlacement(this);
   }

   public BlockPos getPosition() {
      return this.position;
   }

   @Override
   public void writePacketData(PacketBuffer var1) {
      var1.writeBlockPos(this.position);
      var1.writeByte(this.placedBlockDirection);
      var1.writeItemStackToBuffer(this.stack);
      var1.writeByte((int)(this.facingX * 16.0F));
      var1.writeByte((int)(this.facingY * 16.0F));
      var1.writeByte((int)(this.facingZ * 16.0F));
   }

   public C08PacketPlayerBlockPlacement(BlockPos var1, int var2, ItemStack var3, float var4, float var5, float var6) {
      this.position = var1;
      this.placedBlockDirection = var2;
      this.stack = var3 != null ? var3.copy() : null;
      this.facingX = var4;
      this.facingY = var5;
      this.facingZ = var6;
   }

   public float getPlacedBlockOffsetZ() {
      return this.facingZ;
   }

   public C08PacketPlayerBlockPlacement(ItemStack var1) {
      this(field_179726_a, 255, var1, 0.0F, 0.0F, 0.0F);
   }

   public C08PacketPlayerBlockPlacement() {
   }

   public float getPlacedBlockOffsetX() {
      return this.facingX;
   }

   public int getPlacedBlockDirection() {
      return this.placedBlockDirection;
   }

   public float getPlacedBlockOffsetY() {
      return this.facingY;
   }
}
