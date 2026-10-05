package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagLong extends NBTBase.NBTPrimitive {
   public long data;

   @Override
   public double getDouble() {
      return this.data;
   }

   @Override
   public NBTBase copy() {
      return new NBTTagLong(this.data);
   }

   @Override
   public byte getId() {
      return 4;
   }

   @Override
   public byte getByte() {
      return (byte)(this.data & 255L);
   }

   @Override
   public float getFloat() {
      return (float)this.data;
   }

   @Override
   public String toString() {
      return "" + this.data + "L";
   }

   @Override
   public short getShort() {
      return (short)(this.data & 65535L);
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ (int)(this.data ^ this.data >>> 32);
   }

   @Override
   public boolean equals(Object var1) {
      if (super.equals(var1)) {
         NBTTagLong var2 = (NBTTagLong)var1;
         return this.data == var2.data;
      } else {
         return false;
      }
   }

   public NBTTagLong(long var1) {
      this.data = var1;
   }

   @Override
   public long getLong() {
      return this.data;
   }

   @Override
   public void read(DataInput var1, int var2, NBTSizeTracker var3) throws java.io.IOException {
      var3.read(128L);
      this.data = var1.readLong();
   }

   public NBTTagLong() {
   }

   @Override
   public void write(DataOutput var1) throws java.io.IOException {
      var1.writeLong(this.data);
   }

   @Override
   public int getInt() {
      return (int)(this.data & -1L);
   }
}
