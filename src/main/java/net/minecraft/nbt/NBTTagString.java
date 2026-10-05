package net.minecraft.nbt;

import java.io.DataInput;
import java.io.DataOutput;

public class NBTTagString extends NBTBase {
   public String data;

   @Override
   public byte getId() {
      return 8;
   }

   public NBTTagString() {
      this.data = "";
   }

   @Override
   public boolean equals(Object var1) {
      if (!super.equals(var1)) {
         return false;
      } else {
         NBTTagString var2 = (NBTTagString)var1;
         return this.data == null && var2.data == null || this.data != null && this.data.equals(var2.data);
      }
   }

   @Override
   public String getString() {
      return this.data;
   }

   @Override
   public void write(DataOutput var1) throws java.io.IOException {
      var1.writeUTF(this.data);
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ this.data.hashCode();
   }

   @Override
   public NBTBase copy() {
      return new NBTTagString(this.data);
   }

   @Override
   public String toString() {
      return "\"" + this.data.replace("\"", "\\\"") + "\"";
   }

   @Override
   public void read(DataInput var1, int var2, NBTSizeTracker var3) throws java.io.IOException {
      var3.read(288L);
      this.data = var1.readUTF();
      var3.read(16 * this.data.length());
   }

   @Override
   public boolean hasNoTags() {
      return this.data.isEmpty();
   }

   public NBTTagString(String var1) {
      this.data = var1;
      if (var1 == null) {
         throw new IllegalArgumentException("Empty string not allowed");
      }
   }
}
