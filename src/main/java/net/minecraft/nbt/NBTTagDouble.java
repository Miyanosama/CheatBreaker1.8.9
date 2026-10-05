package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import net.minecraft.util.MathHelper;

public class NBTTagDouble extends NBTBase.NBTPrimitive {
   public double data;

   @Override
   public int getInt() {
      return MathHelper.floor_double(this.data);
   }

   @Override
   public byte getId() {
      return 6;
   }

   public NBTTagDouble() {
   }

   @Override
   public void write(DataOutput var1) throws java.io.IOException {
      var1.writeDouble(this.data);
   }

   @Override
   public long getLong() {
      return (long)Math.floor(this.data);
   }

   @Override
   public NBTBase copy() {
      return new NBTTagDouble(this.data);
   }

   public NBTTagDouble(double var1) {
      this.data = var1;
   }

   @Override
   public void read(DataInput var1, int var2, NBTSizeTracker var3) throws java.io.IOException {
      var3.read(128L);
      this.data = var1.readDouble();
   }

   @Override
   public double getDouble() {
      return this.data;
   }

   @Override
   public int hashCode() {
      long var1 = Double.doubleToLongBits(this.data);
      return super.hashCode() ^ (int)(var1 ^ var1 >>> 32);
   }

   @Override
   public float getFloat() {
      return (float)this.data;
   }

   @Override
   public String toString() {
      return "" + this.data + "d";
   }

   @Override
   public boolean equals(Object var1) {
      if (super.equals(var1)) {
         NBTTagDouble var2 = (NBTTagDouble)var1;
         return this.data == var2.data;
      } else {
         return false;
      }
   }

   @Override
   public byte getByte() {
      return (byte)(MathHelper.floor_double(this.data) & 0xFF);
   }

   @Override
   public short getShort() {
      return (short)(MathHelper.floor_double(this.data) & 65535);
   }
}
