package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import net.minecraft.util.MathHelper;

public class NBTTagFloat extends NBTBase.NBTPrimitive {
   public float data;

   @Override
   public float getFloat() {
      return this.data;
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ Float.floatToIntBits(this.data);
   }

   @Override
   public boolean equals(Object var1) {
      if (super.equals(var1)) {
         NBTTagFloat var2 = (NBTTagFloat)var1;
         return this.data == var2.data;
      } else {
         return false;
      }
   }

   @Override
   public void write(DataOutput var1) throws java.io.IOException {
      var1.writeFloat(this.data);
   }

   @Override
   public double getDouble() {
      return this.data;
   }

   @Override
   public byte getId() {
      return 5;
   }

   public NBTTagFloat(float var1) {
      this.data = var1;
   }

   public NBTTagFloat() {
   }

   @Override
   public int getInt() {
      return MathHelper.floor_float(this.data);
   }

   @Override
   public String toString() {
      return "" + this.data + "f";
   }

   @Override
   public void read(DataInput var1, int var2, NBTSizeTracker var3) throws java.io.IOException {
      var3.read(96L);
      this.data = var1.readFloat();
   }

   @Override
   public short getShort() {
      return (short)(MathHelper.floor_float(this.data) & 65535);
   }

   @Override
   public byte getByte() {
      return (byte)(MathHelper.floor_float(this.data) & 0xFF);
   }

   @Override
   public long getLong() {
      return (long)this.data;
   }

   @Override
   public NBTBase copy() {
      return new NBTTagFloat(this.data);
   }
}
