package net.minecraft.nbt;

import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.spdy.SpdyHeaders$1;
import java.io.DataInput;
import java.io.DataOutput;
import org.apache.log4j.helpers.FormattingInfo;

public class NBTTagShort extends NBTBase$NBTPrimitive {
   public short data;
   public FormattingInfo field_0000;
   public DefaultHttpDataFactory field_0001;
   public SpdyHeaders$1 field_0002;

   @Override
   public long getLong() {
      return this.data;
   }

   @Override
   public NBTBase copy() {
      return new NBTTagShort(this.data);
   }

   @Override
   public boolean equals(Object var1) {
      if (super.equals(var1)) {
         NBTTagShort var2 = (NBTTagShort)var1;
         return this.data == var2.data;
      } else {
         return false;
      }
   }

   @Override
   public int hashCode() {
      return super.hashCode() ^ this.data;
   }

   @Override
   public byte getId() {
      return 2;
   }

   @Override
   public void read(DataInput var1, int var2, NBTSizeTracker var3) {
      var3.read(-3569715048764898992L & 3569715047081250897L);
      this.data = var1.readShort();
   }

   @Override
   public short getShort() {
      return this.data;
   }

   @Override
   public void write(DataOutput var1) {
      var1.writeShort(this.data);
   }

   public NBTTagShort() {
   }

   @Override
   public double getDouble() {
      return this.data;
   }

   @Override
   public float getFloat() {
      return this.data;
   }

   @Override
   public int getInt() {
      return this.data;
   }

   @Override
   public byte getByte() {
      return (byte)(this.data & 255);
   }

   public NBTTagShort(short var1) {
      this.data = var1;
   }

   @Override
   public String toString() {
      return "" + this.data + "s";
   }
}
