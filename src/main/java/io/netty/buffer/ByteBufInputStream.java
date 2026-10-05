package io.netty.buffer;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.InputStream;
import net.minecraft.block.BlockStoneSlab;
import net.minecraft.client.audio.SoundListSerializer;
import net.optifine.entity.model.ModelAdapterRabbit;
import net.optifine.shaders.config.ShaderOptionSwitch;

public class ByteBufInputStream extends InputStream implements DataInput {
   public int startIndex;
   public StringBuilder lineBuf = new StringBuilder();
   public ByteBuf buffer;
   public int endIndex;

   @Override
   public byte readByte() throws java.io.IOException {
      if (!this.buffer.isReadable()) {
         throw new EOFException();
      } else {
         return this.buffer.readByte();
      }
   }

   @Override
   public long readLong() throws java.io.IOException {
      this.checkAvailable(8);
      return this.buffer.readLong();
   }

   @Override
   public short readShort() throws java.io.IOException {
      this.checkAvailable(2);
      return this.buffer.readShort();
   }

   public void checkAvailable(int var1) throws java.io.IOException {
      if (var1 < 0) {
         throw new IndexOutOfBoundsException("fieldSize cannot be a negative number");
      } else if (var1 > this.available()) {
         throw new EOFException("fieldSize is too long! Length is " + var1 + ", but maximum is " + this.available());
      }
   }

   @Override
   public int available() throws java.io.IOException {
      return this.endIndex - this.buffer.readerIndex();
   }

   @Override
   public void reset() throws java.io.IOException {
      this.buffer.resetReaderIndex();
   }

   @Override
   public void readFully(byte[] var1, int var2, int var3) throws java.io.IOException {
      this.checkAvailable(var3);
      this.buffer.readBytes(var1, var2, var3);
   }

   @Override
   public String readUTF() throws java.io.IOException {
      return DataInputStream.readUTF(this);
   }

   @Override
   public int read(byte[] var1, int var2, int var3) throws java.io.IOException {
      int var4 = this.available();
      if (var4 == 0) {
         return -1;
      } else {
         var3 = Math.min(var4, var3);
         this.buffer.readBytes(var1, var2, var3);
         return var3;
      }
   }

   @Override
   public int readUnsignedByte() throws java.io.IOException {
      return this.readByte() & 0xFF;
   }

   @Override
   public long skip(long var1) throws java.io.IOException {
      return var1 > 2147483647L ? this.skipBytes(Integer.MAX_VALUE) : this.skipBytes((int)var1);
   }

   @Override
   public String readLine() throws java.io.IOException {
      this.lineBuf.setLength(0);

      while (this.buffer.isReadable()) {
         short var1 = this.buffer.readUnsignedByte();
         switch (var1) {
            case 13:
               if (this.buffer.isReadable() && (char)this.buffer.getUnsignedByte(this.buffer.readerIndex()) == '\n') {
                  this.buffer.skipBytes(1);
               }
            case 10:
               return this.lineBuf.toString();
            default:
               this.lineBuf.append((char)var1);
         }
      }

      return this.lineBuf.length() > 0 ? this.lineBuf.toString() : null;
   }

   public ByteBufInputStream(ByteBuf var1) {
      this(var1, var1.readableBytes());
   }

   @Override
   public float readFloat() throws java.io.IOException {
      return Float.intBitsToFloat(this.readInt());
   }

   @Override
   public void mark(int var1) {
      this.buffer.markReaderIndex();
   }

   @Override
   public boolean readBoolean() throws java.io.IOException {
      this.checkAvailable(1);
      return this.read() != 0;
   }

   @Override
   public int skipBytes(int var1) throws java.io.IOException {
      int var2 = Math.min(this.available(), var1);
      this.buffer.skipBytes(var2);
      return var2;
   }

   @Override
   public int read() throws java.io.IOException {
      return !this.buffer.isReadable() ? -1 : this.buffer.readByte() & 0xFF;
   }

   public ByteBufInputStream(ByteBuf var1, int var2) {
      if (var1 == null) {
         throw new NullPointerException("buffer");
      } else if (var2 < 0) {
         throw new IllegalArgumentException("length: " + var2);
      } else if (var2 > var1.readableBytes()) {
         throw new IndexOutOfBoundsException("Too many bytes to be read - Needs " + var2 + ", maximum is " + var1.readableBytes());
      } else {
         this.buffer = var1;
         this.startIndex = var1.readerIndex();
         this.endIndex = this.startIndex + var2;
         var1.markReaderIndex();
      }
   }

   public int readBytes() {
      return this.buffer.readerIndex() - this.startIndex;
   }

   @Override
   public int readUnsignedShort() throws java.io.IOException {
      return this.readShort() & 65535;
   }

   @Override
   public char readChar() throws java.io.IOException {
      return (char)this.readShort();
   }

   @Override
   public double readDouble() throws java.io.IOException {
      return Double.longBitsToDouble(this.readLong());
   }

   @Override
   public int readInt() throws java.io.IOException {
      this.checkAvailable(4);
      return this.buffer.readInt();
   }

   @Override
   public void readFully(byte[] var1) throws java.io.IOException {
      this.readFully(var1, 0, var1.length);
   }

   @Override
   public boolean markSupported() {
      return true;
   }
}
