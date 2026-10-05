package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagByte extends NBTBase$NBTPrimitive {
   public byte data;

   @Override
   public int hashCode() {
      return super.hashCode() ^ this.data;
   }

   @Override
   public long getLong() {
      return this.data;
   }

   public NBTTagByte(byte var1) {
      this.data = var1;
   }

   @Override
   public String toString() {
      return "" + this.data + "b";
   }

   @Override
   public void write(DataOutput var1) {
      var1.writeByte(this.data);
   }

   @Override
   public double getDouble() {
      return this.data;
   }

   @Override
   public byte getByte() {
      return this.data;
   }

   public NBTTagByte() {
   }

   @Override
   public boolean equals(Object var1) {
      if (super.equals(var1)) {
         NBTTagByte var2 = (NBTTagByte)var1;
         return this.data == var2.data;
      } else {
         return false;
      }
   }

   @Override
   public NBTBase copy() {
      return new NBTTagByte(this.data);
   }

   @Override
   public int getInt() {
      return this.data;
   }

   @Override
   public byte getId() {
      return 1;
   }

   @Override
   public float getFloat() {
      return this.data;
   }

   @Override
   public short getShort() {
      return this.data;
   }

   @Override
   public void read(DataInput var1, int var2, NBTSizeTracker var3) {
      var3.read(-5874292940988190614L & 5874292939544330569L);
      this.data = var1.readByte();
   }
}
