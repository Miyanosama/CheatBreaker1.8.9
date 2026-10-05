package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagEnd extends NBTBase {
   @Override
   public String toString() {
      return "END";
   }

   @Override
   public void write(DataOutput var1) throws java.io.IOException {
   }

   @Override
   public byte getId() {
      return 0;
   }

   @Override
   public void read(DataInput var1, int var2, NBTSizeTracker var3) throws java.io.IOException {
      var3.read(64L);
   }

   @Override
   public NBTBase copy() {
      return new NBTTagEnd();
   }
}
