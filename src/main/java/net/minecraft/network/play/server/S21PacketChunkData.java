package net.minecraft.network.play.server;

import com.google.common.collect.Lists;
import java.util.ArrayList;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.storage.ExtendedBlockStorage;

public class S21PacketChunkData implements Packet<INetHandlerPlayClient> {
   public int chunkX;
   public S21PacketChunkData.Extracted extractedData;
   public boolean field_149279_g;
   public int chunkZ;

   public static S21PacketChunkData.Extracted getExtractedData(Chunk var0, boolean var1, boolean var2, int var3) {
      ExtendedBlockStorage[] var4 = var0.getBlockStorageArray();
      S21PacketChunkData.Extracted var5 = new S21PacketChunkData.Extracted();
      ArrayList var6 = Lists.newArrayList();

      for (int var7 = 0; var7 < var4.length; var7++) {
         ExtendedBlockStorage var8 = var4[var7];
         if (var8 != null && (!var1 || !var8.isEmpty()) && (var3 & 1 << var7) != 0) {
            var5.dataSize |= 1 << var7;
            var6.add(var8);
         }
      }

      var5.data = new byte[func_180737_a(Integer.bitCount(var5.dataSize), var2, var1)];
      int var15 = 0;

      for (ExtendedBlockStorage var9 : (Iterable<ExtendedBlockStorage>)(Iterable<?>)(var6)) {
         char[] var10 = var9.getData();

         for (char var14 : var10) {
            var5.data[var15++] = (byte)(var14 & 255);
            var5.data[var15++] = (byte)(var14 >> '\b' & 0xFF);
         }
      }

      for (ExtendedBlockStorage var20 : (Iterable<ExtendedBlockStorage>)(Iterable<?>)(var6)) {
         var15 = func_179757_a(var20.getBlocklightArray().getData(), var5.data, var15);
      }

      if (var2) {
         for (ExtendedBlockStorage var21 : (Iterable<ExtendedBlockStorage>)(Iterable<?>)(var6)) {
            var15 = func_179757_a(var21.getSkylightArray().getData(), var5.data, var15);
         }
      }

      if (var1) {
         func_179757_a(var0.getBiomeArray(), var5.data, var15);
      }

      return var5;
   }

   public S21PacketChunkData(Chunk var1, boolean var2, int var3) {
      this.chunkX = var1.a;
      this.chunkZ = var1.b;
      this.field_149279_g = var2;
      this.extractedData = getExtractedData(var1, var2, !var1.getWorld().t.getHasNoSky(), var3);
   }

   public int getChunkX() {
      return this.chunkX;
   }

   public int getExtractedSize() {
      return this.extractedData.dataSize;
   }

   public S21PacketChunkData() {
   }

   public boolean func_149274_i() {
      return this.field_149279_g;
   }

   public static int func_180737_a(int var0, boolean var1, boolean var2) {
      int var3 = var0 * 2 * 16 * 16 * 16;
      int var4 = var0 * 16 * 16 * 16 / 2;
      int var5 = var1 ? var0 * 16 * 16 * 16 / 2 : 0;
      int var6 = var2 ? 256 : 0;
      return var3 + var4 + var5 + var6;
   }

   public int getChunkZ() {
      return this.chunkZ;
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeInt(this.chunkX);
      var1.writeInt(this.chunkZ);
      var1.writeBoolean(this.field_149279_g);
      var1.writeShort((short)(this.extractedData.dataSize & 65535));
      var1.writeByteArray(this.extractedData.data);
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handleChunkData(this);
   }

   public static int func_179757_a(byte[] var0, byte[] var1, int var2) {
      System.arraycopy(var0, 0, var1, var2, var0.length);
      return var2 + var0.length;
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.chunkX = var1.readInt();
      this.chunkZ = var1.readInt();
      this.field_149279_g = var1.readBoolean();
      this.extractedData = new S21PacketChunkData.Extracted();
      this.extractedData.dataSize = var1.readShort();
      this.extractedData.data = var1.readByteArray();
   }

   public byte[] getExtractedDataBytes() {
      return this.extractedData.data;
   }

   public static class Extracted {
      public int dataSize;
      public byte[] data;
   }
}
