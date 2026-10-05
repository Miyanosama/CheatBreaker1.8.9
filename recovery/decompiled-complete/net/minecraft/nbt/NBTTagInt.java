package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import javazoom.jl.player.JavaSoundAudioDevice;

public class NBTTagInt extends NBTBase$NBTPrimitive {
   public JavaSoundAudioDevice field_0001;
   public int data;

   @Override
   public boolean equals(Object var1) {
      if (super.equals(var1)) {
         NBTTagInt var2 = (NBTTagInt)var1;
         return this.data == var2.data;
      } else {
         return false;
      }
   }

   @Override
   public byte getByte() {
      return (byte)(this.data & 0xFF);
   }

   @Override
   public void write(DataOutput var1) {
      var1.writeInt(this.data);
   }

   @Override
   public byte getId() {
      return 3;
   }

   @Override
   public short getShort() {
      return (short)(this.data & 65535);
   }

   @Override
   public NBTBase copy() {
      return new NBTTagInt(this.data);
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ this.data;
   }

   @Override
   public int getInt() {
      return this.data;
   }

   @Override
   public String toString() {
      return "" + this.data;
   }

   public NBTTagInt(int var1) {
      this.data = var1;
   }

   @Override
   public float getFloat() {
      return this.data;
   }

   @Override
   public double getDouble() {
      return this.data;
   }

   @Override
   public long getLong() {
      return this.data;
   }

   public NBTTagInt() {
   }

   @Override
   public void read(DataInput var1, int var2, NBTSizeTracker var3) {
      var3.read(-1159350181439184648L & 1159350179726465634L);
      this.data = var1.readInt();
   }
}
