package net.minecraft.nbt;

import io.netty.util.Recycler$1;
import java.io.DataInput;
import java.io.DataOutput;
import java.util.Arrays;
import junit.swingui.TestSelector$ParallelSwapper;
import net.minecraft.block.state.pattern.BlockStateHelper;

public class NBTTagByteArray extends NBTBase {
   public Recycler$1 field_0002;
   public BlockStateHelper field_0001;
   public TestSelector$ParallelSwapper field_0000;
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
   public void write(DataOutput var1) {
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
   public void read(DataInput var1, int var2, NBTSizeTracker var3) {
      var3.read(-4414954432613445176L & 4414954431548834534L);
      int var4 = var1.readInt();
      var3.read(8 * var4);
      this.data = new byte[var4];
      var1.readFully(this.data);
   }
}
