package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.Arrays;

public class NBTTagByteArray extends NBTBase {
   public byte[] data;

   public NBTTagByteArray() {
   }

   @Override
   public byte getId() {
      return 7;
   }

   @Override
   public NBTBase copy() {
      byte[] var1 = new byte[this.data.length];
      System.arraycopy(this.data, 0, var1, 0, this.data.length);
      return new NBTTagByteArray(var1);
   }

   public byte[] getByteArray() {
      return this.data;
   }

   @Override
   public boolean equals(Object var1) {
      return super.equals(var1) ? Arrays.equals(this.data, ((NBTTagByteArray)var1).data) : false;
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ Arrays.hashCode(this.data);
   }

   @Override
   public void write(DataOutput var1) throws java.io.IOException {
      var1.writeInt(this.data.length);
      var1.write(this.data);
   }

   @Override
   public String toString() {
      return "[" + this.data.length + " bytes]";
   }

   public NBTTagByteArray(byte[] var1) {
      this.data = var1;
   }

   @Override
   public void read(DataInput var1, int var2, NBTSizeTracker var3) throws java.io.IOException {
      var3.read(192L);
      int var4 = var1.readInt();
      var3.read(8 * var4);
      this.data = new byte[var4];
      var1.readFully(this.data);
   }
}
