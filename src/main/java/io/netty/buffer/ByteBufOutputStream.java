package io.netty.buffer;

import io.netty.channel.embedded.EmbeddedChannel;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.OutputStream;
import junit.textui.TestRunner;
import net.minecraft.world.gen.feature.WorldGenHellLava;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor;

public class ByteBufOutputStream extends OutputStream implements DataOutput {
   public DataOutputStream utf8out = new DataOutputStream(this);
   public ByteBuf buffer;
   public int startIndex;

   @Override
   public void writeByte(int var1) throws java.io.IOException {
      this.write(var1);
   }

   @Override
   public void write(byte[] var1, int var2, int var3) throws java.io.IOException {
      if (var3 != 0) {
         this.buffer.writeBytes(var1, var2, var3);
      }
   }

   @Override
   public void writeBoolean(boolean var1) throws java.io.IOException {
      this.write(var1 ? 1 : 0);
   }

   public ByteBuf buffer() {
      return this.buffer;
   }

   @Override
   public void writeInt(int var1) throws java.io.IOException {
      this.buffer.writeInt(var1);
   }

   @Override
   public void writeChar(int var1) throws java.io.IOException {
      this.writeShort((short)var1);
   }

   public ByteBufOutputStream(ByteBuf var1) {
      if (var1 == null) {
         throw new NullPointerException("buffer");
      } else {
         this.buffer = var1;
         this.startIndex = var1.writerIndex();
      }
   }

   public int writtenBytes() {
      return this.buffer.writerIndex() - this.startIndex;
   }

   @Override
   public void writeFloat(float var1) throws java.io.IOException {
      this.writeInt(Float.floatToIntBits(var1));
   }

   @Override
   public void writeLong(long var1) throws java.io.IOException {
      this.buffer.writeLong(var1);
   }

   @Override
   public void write(byte[] var1) throws java.io.IOException {
      this.buffer.writeBytes(var1);
   }

   @Override
   public void writeChars(String var1) throws java.io.IOException {
      int var2 = var1.length();

      for (int var3 = 0; var3 < var2; var3++) {
         this.writeChar(var1.charAt(var3));
      }
   }

   @Override
   public void writeUTF(String var1) throws java.io.IOException {
      this.utf8out.writeUTF(var1);
   }

   @Override
   public void writeDouble(double var1) throws java.io.IOException {
      this.writeLong(Double.doubleToLongBits(var1));
   }

   @Override
   public void writeShort(int var1) throws java.io.IOException {
      this.buffer.writeShort((short)var1);
   }

   @Override
   public void write(int var1) throws java.io.IOException {
      this.buffer.writeByte((byte)var1);
   }

   @Override
   public void writeBytes(String var1) throws java.io.IOException {
      int var2 = var1.length();

      for (int var3 = 0; var3 < var2; var3++) {
         this.write((byte)var1.charAt(var3));
      }
   }
}
