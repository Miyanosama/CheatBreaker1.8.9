package net.minecraft.world.chunk;

import net.minecraft.util.Util$EnumOS;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.gen.MapGenCaves;
import org.apache.log4j.helpers.CountingQuietWriter;
import org.java_websocket.extensions.CompressionExtension;
import recovered.unidentified.UnidentifiedClass0097;

public class NibbleArray {
   public UnidentifiedClass0097 field_0003;
   public byte[] data;
   public Util$EnumOS field_0002;
   public CountingQuietWriter field_0004;
   public CompressionExtension field_0000;
   public WorldSavedData field_0001;
   public MapGenCaves field_0006;

   public void set(int var1, int var2, int var3, int var4) {
      this.setIndex(this.getCoordinateIndex(var1, var2, var3), var4);
   }

   public NibbleArray(byte[] var1) {
      this.data = var1;
      if (var1.length != 2048) {
         throw new IllegalArgumentException("ChunkNibbleArrays should be 2048 bytes not: " + var1.length);
      }
   }

   public int get(int var1, int var2, int var3) {
      return this.getFromIndex(this.getCoordinateIndex(var1, var2, var3));
   }

   public int getCoordinateIndex(int var1, int var2, int var3) {
      return var2 << 8 | var3 << 4 | var1;
   }

   public void setIndex(int var1, int var2) {
      int var3 = this.getNibbleIndex(var1);
      if (this.isLowerNibble(var1)) {
         this.data[var3] = (byte)(this.data[var3] & 240 | var2 & 15);
      } else {
         this.data[var3] = (byte)(this.data[var3] & 15 | (var2 & 15) << 4);
      }
   }

   public int getNibbleIndex(int var1) {
      return var1 >> 1;
   }

   public int getFromIndex(int var1) {
      int var2 = this.getNibbleIndex(var1);
      return this.isLowerNibble(var1) ? this.data[var2] & 15 : this.data[var2] >> 4 & 15;
   }

   public byte[] getData() {
      return this.data;
   }

   public NibbleArray() {
      this.data = new byte[2048];
   }

   public boolean isLowerNibble(int var1) {
      return (var1 & 1) == 0;
   }
}
