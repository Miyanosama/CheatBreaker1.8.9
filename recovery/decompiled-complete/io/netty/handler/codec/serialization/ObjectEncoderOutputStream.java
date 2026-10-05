package io.netty.handler.codec.serialization;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.Unpooled;
import java.io.DataOutputStream;
import java.io.ObjectOutput;
import java.io.OutputStream;
import net.minecraft.command.server.CommandSummon;
import net.minecraft.nbt.JsonToNBT$List;

public class ObjectEncoderOutputStream extends OutputStream implements ObjectOutput {
   public JsonToNBT$List __junk536513135183876682;
   public DataOutputStream out;
   public CommandSummon __junk2729815376538296033;
   public int estimatedLength;

   public int size() {
      return this.out.size();
   }

   @Override
   public void close() {
      this.out.close();
   }

   @Override
   public void writeBytes(String var1) {
      this.out.writeBytes(var1);
   }

   @Override
   public void writeLong(long var1) {
      this.out.writeLong(var1);
   }

   @Override
   public void writeChars(String var1) {
      this.out.writeChars(var1);
   }

   @Override
   public void write(byte[] var1) {
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
   public void writeFloat(float var1) {
      this.out.writeFloat(var1);
   }

   @Override
   public void flush() {
      this.out.flush();
   }

   @Override
   public void writeUTF(String var1) {
      this.out.writeUTF(var1);
   }

   @Override
   public void writeInt(int var1) {
      this.out.writeInt(var1);
   }

   @Override
   public void writeShort(int var1) {
      this.out.writeShort(var1);
   }

   @Override
   public void writeDouble(double var1) {
      this.out.writeDouble(var1);
   }

   @Override
   public void writeByte(int var1) {
      this.out.writeByte(var1);
   }

   @Override
   public void writeBoolean(boolean var1) {
      this.out.writeBoolean(var1);
   }

   @Override
   public void writeObject(Object var1) {
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
   public void write(byte[] var1, int var2, int var3) {
      this.out.write(var1, var2, var3);
   }

   @Override
   public void write(int var1) {
      this.out.write(var1);
   }

   @Override
   public void writeChar(int var1) {
      this.out.writeChar(var1);
   }

   public ObjectEncoderOutputStream(OutputStream var1) {
      this(var1, 512);
   }
}
