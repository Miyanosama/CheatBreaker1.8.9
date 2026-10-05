package io.netty.handler.codec.serialization;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import java.io.DataOutputStream;
import java.io.ObjectOutput;
import java.io.OutputStream;
import net.minecraft.command.server.CommandSummon;
import net.minecraft.nbt.JsonToNBT;

public class ObjectEncoderOutputStream extends OutputStream implements ObjectOutput {
   public DataOutputStream out;
   public int estimatedLength;

   public int size() {
      return this.out.size();
   }

   @Override
   public void close() throws java.io.IOException {
      this.out.close();
   }

   @Override
   public void writeBytes(String var1) throws java.io.IOException {
      this.out.writeBytes(var1);
   }

   @Override
   public void writeLong(long var1) throws java.io.IOException {
      this.out.writeLong(var1);
   }

   @Override
   public void writeChars(String var1) throws java.io.IOException {
      this.out.writeChars(var1);
   }

   @Override
   public void write(byte[] var1) throws java.io.IOException {
      this.out.write(var1);
   }

   public ObjectEncoderOutputStream(OutputStream var1, int var2) {
      if (var1 == null) {
         throw new NullPointerException("out");
      } else if (var2 < 0) {
         throw new IllegalArgumentException("estimatedLength: " + var2);
      } else {
         if (var1 instanceof DataOutputStream) {
            this.out = (DataOutputStream)var1;
         } else {
            this.out = new DataOutputStream(var1);
         }

         this.estimatedLength = var2;
      }
   }

   @Override
   public void writeFloat(float var1) throws java.io.IOException {
      this.out.writeFloat(var1);
   }

   @Override
   public void flush() throws java.io.IOException {
      this.out.flush();
   }

   @Override
   public void writeUTF(String var1) throws java.io.IOException {
      this.out.writeUTF(var1);
   }

   @Override
   public void writeInt(int var1) throws java.io.IOException {
      this.out.writeInt(var1);
   }

   @Override
   public void writeShort(int var1) throws java.io.IOException {
      this.out.writeShort(var1);
   }

   @Override
   public void writeDouble(double var1) throws java.io.IOException {
      this.out.writeDouble(var1);
   }

   @Override
   public void writeByte(int var1) throws java.io.IOException {
      this.out.writeByte(var1);
   }

   @Override
   public void writeBoolean(boolean var1) throws java.io.IOException {
      this.out.writeBoolean(var1);
   }

   @Override
   public void writeObject(Object var1) throws java.io.IOException {
      ByteBufOutputStream var2 = new ByteBufOutputStream(Unpooled.buffer(this.estimatedLength));
      CompactObjectOutputStream var3 = new CompactObjectOutputStream(var2);
      var3.writeObject(var1);
      var3.flush();
      var3.close();
      ByteBuf var4 = var2.buffer();
      int var5 = var4.readableBytes();
      this.writeInt(var5);
      var4.getBytes(0, this, var5);
   }

   @Override
   public void write(byte[] var1, int var2, int var3) throws java.io.IOException {
      this.out.write(var1, var2, var3);
   }

   @Override
   public void write(int var1) throws java.io.IOException {
      this.out.write(var1);
   }

   @Override
   public void writeChar(int var1) throws java.io.IOException {
      this.out.writeChar(var1);
   }

   public ObjectEncoderOutputStream(OutputStream var1) {
      this(var1, 512);
   }
}
