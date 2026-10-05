package io.netty.buffer;

import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.util.IllegalReferenceCountException;
import io.netty.util.ResourceLeakDetector;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import net.minecraft.entity.EntityLiving;

public abstract class AbstractByteBuf extends ByteBuf {
   public int markedWriterIndex;
   public int readerIndex;
   public int maxCapacity;
   public int writerIndex;
   public SwappedByteBuf swappedBuf;
   public int markedReaderIndex;
   public static ResourceLeakDetector<ByteBuf> leakDetector = new ResourceLeakDetector<>(ByteBuf.class);

   @Override
   public ByteBuf setFloat(int var1, float var2) {
      this.setInt(var1, Float.floatToRawIntBits(var2));
      return this;
   }

   public void checkReadableBytes(int var1) {
      this.ensureAccessible();
      if (var1 < 0) {
         throw new IllegalArgumentException("minimumReadableBytes: " + var1 + " (expected: >= 0)");
      } else if (this.readerIndex > this.writerIndex - var1) {
         throw new IndexOutOfBoundsException(
            String.format("readerIndex(%d) + length(%d) exceeds writerIndex(%d): %s", this.readerIndex, var1, this.writerIndex, this)
         );
      }
   }

   @Override
   public ByteBuf writeBoolean(boolean var1) {
      this.writeByte(var1 ? 1 : 0);
      return this;
   }

   @Override
   public int forEachByteDesc(int var1, int var2, ByteBufProcessor var3) {
      this.checkIndex(var1, var2);
      return this.forEachByteDesc0(var1, var2, var3);
   }

   public abstract byte _getByte(int var1);

   public abstract short _getShort(int var1);

   @Override
   public ByteBuf writeBytes(ByteBuf var1, int var2) {
      if (var2 > var1.readableBytes()) {
         throw new IndexOutOfBoundsException(String.format("length(%d) exceeds src.readableBytes(%d) where src is: %s", var2, var1.readableBytes(), var1));
      } else {
         this.writeBytes(var1, var1.readerIndex(), var2);
         var1.readerIndex(var1.readerIndex() + var2);
         return this;
      }
   }

   @Override
   public ByteBuf readerIndex(int var1) {
      if (var1 >= 0 && var1 <= this.writerIndex) {
         this.readerIndex = var1;
         return this;
      } else {
         throw new IndexOutOfBoundsException(String.format("readerIndex: %d (expected: 0 <= readerIndex <= writerIndex(%d))", var1, this.writerIndex));
      }
   }

   public abstract void _setShort(int var1, int var2);

   @Override
   public short readUnsignedByte() {
      return (short)(this.readByte() & 255);
   }

   public abstract void _setInt(int var1, int var2);

   @Override
   public ByteBuf resetWriterIndex() {
      this.writerIndex = this.markedWriterIndex;
      return this;
   }

   @Override
   public ByteBuffer[] nioBuffers() {
      return this.nioBuffers(this.readerIndex, this.readableBytes());
   }

   public void checkIndex(int var1) {
      this.ensureAccessible();
      if (var1 < 0 || var1 >= this.capacity()) {
         throw new IndexOutOfBoundsException(String.format("index: %d (expected: range(0, %d))", var1, this.capacity()));
      }
   }

   @Override
   public byte readByte() {
      this.checkReadableBytes(1);
      int var1 = this.readerIndex;
      byte var2 = this.getByte(var1);
      this.readerIndex = var1 + 1;
      return var2;
   }

   @Override
   public long readUnsignedInt() {
      return this.readInt() & 4294967295L;
   }

   public void adjustMarkers(int var1) {
      int var2 = this.markedReaderIndex;
      if (var2 <= var1) {
         this.markedReaderIndex = 0;
         int var3 = this.markedWriterIndex;
         if (var3 <= var1) {
            this.markedWriterIndex = 0;
         } else {
            this.markedWriterIndex = var3 - var1;
         }
      } else {
         this.markedReaderIndex = var2 - var1;
         this.markedWriterIndex -= var1;
      }
   }

   @Override
   public boolean readBoolean() {
      return this.readByte() != 0;
   }

   @Override
   public ByteBuf writeZero(int var1) {
      if (var1 == 0) {
         return this;
      } else {
         this.ensureWritable(var1);
         this.checkIndex(this.writerIndex, var1);
         int var2 = var1 >>> 3;
         int var3 = var1 & 7;

         for (int var4 = var2; var4 > 0; var4--) {
            this.writeLong(0L);
         }

         if (var3 == 4) {
            this.writeInt(0);
         } else if (var3 < 4) {
            for (int var5 = var3; var5 > 0; var5--) {
               this.writeByte(0);
            }
         } else {
            this.writeInt(0);

            for (int var6 = var3 - 4; var6 > 0; var6--) {
               this.writeByte(0);
            }
         }

         return this;
      }
   }

   @Override
   public float getFloat(int var1) {
      return Float.intBitsToFloat(this.getInt(var1));
   }

   @Override
   public ByteBuf setLong(int var1, long var2) {
      this.checkIndex(var1, 8);
      this._setLong(var1, var2);
      return this;
   }

   public abstract long _getLong(int var1);

   @Override
   public ByteBuf getBytes(int var1, ByteBuf var2, int var3) {
      this.getBytes(var1, var2, var2.writerIndex(), var3);
      var2.writerIndex(var2.writerIndex() + var3);
      return this;
   }

   @Override
   public ByteBuf writeShort(int var1) {
      this.ensureAccessible();
      this.ensureWritable(2);
      this._setShort(this.writerIndex, var1);
      this.writerIndex += 2;
      return this;
   }

   @Override
   public int hashCode() {
      return ByteBufUtil.hashCode(this);
   }

   @Override
   public boolean isReadable() {
      return this.writerIndex > this.readerIndex;
   }

   @Override
   public long getUnsignedInt(int var1) {
      return this.getInt(var1) & 4294967295L;
   }

   @Override
   public ByteBuf writeInt(int var1) {
      this.ensureAccessible();
      this.ensureWritable(4);
      this._setInt(this.writerIndex, var1);
      this.writerIndex += 4;
      return this;
   }

   @Override
   public ByteBuf markWriterIndex() {
      this.markedWriterIndex = this.writerIndex;
      return this;
   }

   @Override
   public boolean isReadable(int var1) {
      return this.writerIndex - this.readerIndex >= var1;
   }

   @Override
   public ByteBuf writerIndex(int var1) {
      if (var1 >= this.readerIndex && var1 <= this.capacity()) {
         this.writerIndex = var1;
         return this;
      } else {
         throw new IndexOutOfBoundsException(
            String.format("writerIndex: %d (expected: readerIndex(%d) <= writerIndex <= capacity(%d))", var1, this.readerIndex, this.capacity())
         );
      }
   }

   @Override
   public ByteBuf duplicate() {
      return new DuplicatedByteBuf(this);
   }

   public void checkIndex(int var1, int var2) {
      this.ensureAccessible();
      if (var2 < 0) {
         throw new IllegalArgumentException("length: " + var2 + " (expected: >= 0)");
      } else if (var1 < 0 || var1 > this.capacity() - var2) {
         throw new IndexOutOfBoundsException(String.format("index: %d, length: %d (expected: range(0, %d))", var1, var2, this.capacity()));
      }
   }

   @Override
   public ByteBuf writeBytes(ByteBuf var1) {
      this.writeBytes(var1, var1.readableBytes());
      return this;
   }

   @Override
   public byte getByte(int var1) {
      this.checkIndex(var1);
      return this._getByte(var1);
   }

   @Override
   public String toString(int var1, int var2, Charset var3) {
      if (var2 == 0) {
         return "";
      } else {
         ByteBuffer var4;
         if (this.nioBufferCount() == 1) {
            var4 = this.nioBuffer(var1, var2);
         } else {
            var4 = ByteBuffer.allocate(var2);
            this.getBytes(var1, var4);
            ((Buffer)var4).flip();
         }

         return ByteBufUtil.decodeString(var4, var3);
      }
   }

   @Override
   public int ensureWritable(int var1, boolean var2) {
      if (var1 < 0) {
         throw new IllegalArgumentException(String.format("minWritableBytes: %d (expected: >= 0)", var1));
      } else if (var1 <= this.writableBytes()) {
         return 0;
      } else if (var1 > this.maxCapacity - this.writerIndex && var2) {
         if (this.capacity() == this.maxCapacity()) {
            return 1;
         } else {
            this.capacity(this.maxCapacity());
            return 3;
         }
      } else {
         int var3 = this.calculateNewCapacity(this.writerIndex + var1);
         this.capacity(var3);
         return 2;
      }
   }

   public int calculateNewCapacity(int var1) {
      int var2 = this.maxCapacity;
      int var3 = 4194304;
      if (var1 == 4194304) {
         return 4194304;
      } else if (var1 > 4194304) {
         int var5 = var1 / 4194304 * 4194304;
         if (var5 > var2 - 4194304) {
            var5 = var2;
         } else {
            var5 += 4194304;
         }

         return var5;
      } else {
         int var4 = 64;

         while (var4 < var1) {
            var4 <<= 1;
         }

         return Math.min(var4, var2);
      }
   }

   @Override
   public ByteBuf slice(int var1, int var2) {
      return (ByteBuf)(var2 == 0 ? Unpooled.EMPTY_BUFFER : new SlicedByteBuf(this, var1, var2));
   }

   public SwappedByteBuf newSwappedByteBuf() {
      return new SwappedByteBuf(this);
   }

   public abstract void _setLong(int var1, long var2);

   @Override
   public ByteBuf readBytes(byte[] var1) {
      this.readBytes(var1, 0, var1.length);
      return this;
   }

   @Override
   public int readerIndex() {
      return this.readerIndex;
   }

   @Override
   public int forEachByte(ByteBufProcessor var1) {
      int var2 = this.readerIndex;
      int var3 = this.writerIndex - var2;
      this.ensureAccessible();
      return this.forEachByteAsc0(var2, var3, var1);
   }

   @Override
   public int readMedium() {
      int var1 = this.readUnsignedMedium();
      if ((var1 & 8388608) != 0) {
         var1 |= -16777216;
      }

      return var1;
   }

   public void checkDstIndex(int var1, int var2, int var3, int var4) {
      this.checkIndex(var1, var2);
      if (var3 < 0 || var3 > var4 - var2) {
         throw new IndexOutOfBoundsException(String.format("dstIndex: %d, length: %d (expected: range(0, %d))", var3, var2, var4));
      }
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2, int var3) {
      this.checkIndex(var1, var3);
      if (var2 == null) {
         throw new NullPointerException("src");
      } else if (var3 > var2.readableBytes()) {
         throw new IndexOutOfBoundsException(String.format("length(%d) exceeds src.readableBytes(%d) where src is: %s", var3, var2.readableBytes(), var2));
      } else {
         this.setBytes(var1, var2, var2.readerIndex(), var3);
         var2.readerIndex(var2.readerIndex() + var3);
         return this;
      }
   }

   @Override
   public ByteBuf writeBytes(byte[] var1) {
      this.writeBytes(var1, 0, var1.length);
      return this;
   }

   @Override
   public ByteBuf writeDouble(double var1) {
      this.writeLong(Double.doubleToRawLongBits(var1));
      return this;
   }

   @Override
   public int indexOf(int var1, int var2, byte var3) {
      return ByteBufUtil.indexOf(this, var1, var2, var3);
   }

   @Override
   public int forEachByteDesc(ByteBufProcessor var1) {
      int var2 = this.readerIndex;
      int var3 = this.writerIndex - var2;
      this.ensureAccessible();
      return this.forEachByteDesc0(var2, var3, var1);
   }

   @Override
   public ByteBuf resetReaderIndex() {
      this.readerIndex(this.markedReaderIndex);
      return this;
   }

   @Override
   public ByteBuf writeMedium(int var1) {
      this.ensureAccessible();
      this.ensureWritable(3);
      this._setMedium(this.writerIndex, var1);
      this.writerIndex += 3;
      return this;
   }

   public AbstractByteBuf(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("maxCapacity: " + var1 + " (expected: >= 0)");
      } else {
         this.maxCapacity = var1;
      }
   }

   @Override
   public int writeBytes(InputStream var1, int var2) throws java.io.IOException {
      this.ensureAccessible();
      this.ensureWritable(var2);
      int var3 = this.setBytes(this.writerIndex, var1, var2);
      if (var3 > 0) {
         this.writerIndex += var3;
      }

      return var3;
   }

   @Override
   public ByteBuf clear() {
      this.readerIndex = this.writerIndex = 0;
      return this;
   }

   @Override
   public ByteBuf readBytes(int var1) {
      this.checkReadableBytes(var1);
      if (var1 == 0) {
         return Unpooled.EMPTY_BUFFER;
      } else {
         ByteBuf var2 = Unpooled.buffer(var1, this.maxCapacity);
         var2.writeBytes(this, this.readerIndex, var1);
         this.readerIndex += var1;
         return var2;
      }
   }

   @Override
   public ByteBuffer nioBuffer() {
      return this.nioBuffer(this.readerIndex, this.readableBytes());
   }

   @Override
   public int bytesBefore(int var1, int var2, byte var3) {
      int var4 = this.indexOf(var1, var1 + var2, var3);
      return var4 < 0 ? -1 : var4 - var1;
   }

   public int forEachByteDesc0(int var1, int var2, ByteBufProcessor var3) {
      if (var3 == null) {
         throw new NullPointerException("processor");
      } else if (var2 == 0) {
         return -1;
      } else {
         int var4 = var1 + var2 - 1;

         try {
            do {
               if (!var3.process(this._getByte(var4))) {
                  return var4;
               }
            } while (--var4 >= var1);
         } catch (Exception var6) {
            PlatformDependent.throwException(var6);
         }

         return -1;
      }
   }

   @Override
   public short getShort(int var1) {
      this.checkIndex(var1, 2);
      return this._getShort(var1);
   }

   @Override
   public ByteBuf writeFloat(float var1) {
      this.writeInt(Float.floatToRawIntBits(var1));
      return this;
   }

   @Override
   public int writableBytes() {
      return this.capacity() - this.writerIndex;
   }

   @Override
   public ByteBuf readBytes(ByteBuf var1, int var2, int var3) {
      this.checkReadableBytes(var3);
      this.getBytes(this.readerIndex, var1, var2, var3);
      this.readerIndex += var3;
      return this;
   }

   @Override
   public ByteBuf setZero(int var1, int var2) {
      if (var2 == 0) {
         return this;
      } else {
         this.checkIndex(var1, var2);
         int var3 = var2 >>> 3;
         int var4 = var2 & 7;

         for (int var5 = var3; var5 > 0; var5--) {
            this.setLong(var1, 0L);
            var1 += 8;
         }

         if (var4 == 4) {
            this.setInt(var1, 0);
         } else if (var4 < 4) {
            for (int var7 = var4; var7 > 0; var7--) {
               this.setByte(var1, 0);
               var1++;
            }
         } else {
            this.setInt(var1, 0);
            var1 += 4;

            for (int var8 = var4 - 4; var8 > 0; var8--) {
               this.setByte(var1, 0);
               var1++;
            }
         }

         return this;
      }
   }

   @Override
   public char getChar(int var1) {
      return (char)this.getShort(var1);
   }

   @Override
   public int getUnsignedMedium(int var1) {
      this.checkIndex(var1, 3);
      return this._getUnsignedMedium(var1);
   }

   @Override
   public ByteBuf setByte(int var1, int var2) {
      this.checkIndex(var1);
      this._setByte(var1, var2);
      return this;
   }

   public void ensureAccessible() {
      if (this.refCnt() == 0) {
         throw new IllegalReferenceCountException(0);
      }
   }

   @Override
   public float readFloat() {
      return Float.intBitsToFloat(this.readInt());
   }

   @Override
   public double getDouble(int var1) {
      return Double.longBitsToDouble(this.getLong(var1));
   }

   @Override
   public long readLong() {
      this.checkReadableBytes(8);
      long var1 = this._getLong(this.readerIndex);
      this.readerIndex += 8;
      return var1;
   }

   @Override
   public int readUnsignedMedium() {
      this.checkReadableBytes(3);
      int var1 = this._getUnsignedMedium(this.readerIndex);
      this.readerIndex += 3;
      return var1;
   }

   @Override
   public ByteBuf ensureWritable(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException(String.format("minWritableBytes: %d (expected: >= 0)", var1));
      } else if (var1 <= this.writableBytes()) {
         return this;
      } else if (var1 > this.maxCapacity - this.writerIndex) {
         throw new IndexOutOfBoundsException(
            String.format("writerIndex(%d) + minWritableBytes(%d) exceeds maxCapacity(%d): %s", this.writerIndex, var1, this.maxCapacity, this)
         );
      } else {
         int var2 = this.calculateNewCapacity(this.writerIndex + var1);
         this.capacity(var2);
         return this;
      }
   }

   @Override
   public ByteBuf writeBytes(ByteBuf var1, int var2, int var3) {
      this.ensureAccessible();
      this.ensureWritable(var3);
      this.setBytes(this.writerIndex, var1, var2, var3);
      this.writerIndex += var3;
      return this;
   }

   @Override
   public boolean getBoolean(int var1) {
      return this.getByte(var1) != 0;
   }

   @Override
   public ByteBuf setBytes(int var1, byte[] var2) {
      this.setBytes(var1, var2, 0, var2.length);
      return this;
   }

   @Override
   public boolean isWritable() {
      return this.capacity() > this.writerIndex;
   }

   @Override
   public ByteBuf writeBytes(byte[] var1, int var2, int var3) {
      this.ensureAccessible();
      this.ensureWritable(var3);
      this.setBytes(this.writerIndex, var1, var2, var3);
      this.writerIndex += var3;
      return this;
   }

   @Override
   public int bytesBefore(int var1, byte var2) {
      this.checkReadableBytes(var1);
      return this.bytesBefore(this.readerIndex(), var1, var2);
   }

   @Override
   public ByteBuf readBytes(OutputStream var1, int var2) throws java.io.IOException {
      this.checkReadableBytes(var2);
      this.getBytes(this.readerIndex, var1, var2);
      this.readerIndex += var2;
      return this;
   }

   @Override
   public ByteBuf setDouble(int var1, double var2) {
      this.setLong(var1, Double.doubleToRawLongBits(var2));
      return this;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else {
         return var1 instanceof ByteBuf ? ByteBufUtil.equals(this, (ByteBuf)var1) : false;
      }
   }

   @Override
   public ByteBuf readBytes(ByteBuf var1, int var2) {
      if (var2 > var1.writableBytes()) {
         throw new IndexOutOfBoundsException(String.format("length(%d) exceeds dst.writableBytes(%d) where dst is: %s", var2, var1.writableBytes(), var1));
      } else {
         this.readBytes(var1, var1.writerIndex(), var2);
         var1.writerIndex(var1.writerIndex() + var2);
         return this;
      }
   }

   @Override
   public String toString() {
      if (this.refCnt() == 0) {
         return StringUtil.simpleClassName(this) + "(freed)";
      } else {
         StringBuilder var1 = new StringBuilder();
         var1.append(StringUtil.simpleClassName(this));
         var1.append("(ridx: ");
         var1.append(this.readerIndex);
         var1.append(", widx: ");
         var1.append(this.writerIndex);
         var1.append(", cap: ");
         var1.append(this.capacity());
         if (this.maxCapacity != Integer.MAX_VALUE) {
            var1.append('/');
            var1.append(this.maxCapacity);
         }

         ByteBuf var2 = this.unwrap();
         if (var2 != null) {
            var1.append(", unwrapped: ");
            var1.append(var2);
         }

         var1.append(')');
         return var1.toString();
      }
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2) {
      this.setBytes(var1, var2, var2.readableBytes());
      return this;
   }

   @Override
   public ByteBuf readBytes(ByteBuf var1) {
      this.readBytes(var1, var1.writableBytes());
      return this;
   }

   @Override
   public int forEachByte(int var1, int var2, ByteBufProcessor var3) {
      this.checkIndex(var1, var2);
      return this.forEachByteAsc0(var1, var2, var3);
   }

   @Override
   public int getInt(int var1) {
      this.checkIndex(var1, 4);
      return this._getInt(var1);
   }

   @Override
   public short readShort() {
      this.checkReadableBytes(2);
      short var1 = this._getShort(this.readerIndex);
      this.readerIndex += 2;
      return var1;
   }

   @Override
   public boolean isWritable(int var1) {
      return this.capacity() - this.writerIndex >= var1;
   }

   @Override
   public int readBytes(GatheringByteChannel var1, int var2) throws java.io.IOException {
      this.checkReadableBytes(var2);
      int var3 = this.getBytes(this.readerIndex, var1, var2);
      this.readerIndex += var3;
      return var3;
   }

   @Override
   public ByteBuf setMedium(int var1, int var2) {
      this.checkIndex(var1, 3);
      this._setMedium(var1, var2);
      return this;
   }

   @Override
   public ByteBuf setInt(int var1, int var2) {
      this.checkIndex(var1, 4);
      this._setInt(var1, var2);
      return this;
   }

   @Override
   public int maxCapacity() {
      return this.maxCapacity;
   }

   public abstract void _setMedium(int var1, int var2);

   @Override
   public int compareTo(ByteBuf var1) {
      return ByteBufUtil.compare(this, var1);
   }

   @Override
   public int maxWritableBytes() {
      return this.maxCapacity() - this.writerIndex;
   }

   @Override
   public ByteBuf setChar(int var1, int var2) {
      this.setShort(var1, var2);
      return this;
   }

   @Override
   public long getLong(int var1) {
      this.checkIndex(var1, 8);
      return this._getLong(var1);
   }

   @Override
   public ByteBuf writeBytes(ByteBuffer var1) {
      this.ensureAccessible();
      int var2 = var1.remaining();
      this.ensureWritable(var2);
      this.setBytes(this.writerIndex, var1);
      this.writerIndex += var2;
      return this;
   }

   @Override
   public ByteBuf writeByte(int var1) {
      this.ensureAccessible();
      this.ensureWritable(1);
      this._setByte(this.writerIndex++, var1);
      return this;
   }

   @Override
   public ByteBuf readBytes(byte[] var1, int var2, int var3) {
      this.checkReadableBytes(var3);
      this.getBytes(this.readerIndex, var1, var2, var3);
      this.readerIndex += var3;
      return this;
   }

   @Override
   public ByteBuf copy() {
      return this.copy(this.readerIndex, this.readableBytes());
   }

   public int forEachByteAsc0(int var1, int var2, ByteBufProcessor var3) {
      if (var3 == null) {
         throw new NullPointerException("processor");
      } else if (var2 == 0) {
         return -1;
      } else {
         int var4 = var1 + var2;
         int var5 = var1;

         try {
            do {
               if (!var3.process(this._getByte(var5))) {
                  return var5;
               }
            } while (++var5 < var4);
         } catch (Exception var7) {
            PlatformDependent.throwException(var7);
         }

         return -1;
      }
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuf var2) {
      this.getBytes(var1, var2, var2.writableBytes());
      return this;
   }

   @Override
   public String toString(Charset var1) {
      return this.toString(this.readerIndex, this.readableBytes(), var1);
   }

   @Override
   public char readChar() {
      return (char)this.readShort();
   }

   @Override
   public int readUnsignedShort() {
      return this.readShort() & 65535;
   }

   @Override
   public ByteBuf readSlice(int var1) {
      ByteBuf var2 = this.slice(this.readerIndex, var1);
      this.readerIndex += var1;
      return var2;
   }

   public void checkSrcIndex(int var1, int var2, int var3, int var4) {
      this.checkIndex(var1, var2);
      if (var3 < 0 || var3 > var4 - var2) {
         throw new IndexOutOfBoundsException(String.format("srcIndex: %d, length: %d (expected: range(0, %d))", var3, var2, var4));
      }
   }

   @Override
   public double readDouble() {
      return Double.longBitsToDouble(this.readLong());
   }

   @Override
   public ByteBuf readBytes(ByteBuffer var1) {
      int var2 = var1.remaining();
      this.checkReadableBytes(var2);
      this.getBytes(this.readerIndex, var1);
      this.readerIndex += var2;
      return this;
   }

   public abstract int _getUnsignedMedium(int var1);

   @Override
   public short getUnsignedByte(int var1) {
      return (short)(this.getByte(var1) & 255);
   }

   @Override
   public ByteBuf writeLong(long var1) {
      this.ensureAccessible();
      this.ensureWritable(8);
      this._setLong(this.writerIndex, var1);
      this.writerIndex += 8;
      return this;
   }

   @Override
   public int getMedium(int var1) {
      int var2 = this.getUnsignedMedium(var1);
      if ((var2 & 8388608) != 0) {
         var2 |= -16777216;
      }

      return var2;
   }

   @Override
   public ByteBuf markReaderIndex() {
      this.markedReaderIndex = this.readerIndex;
      return this;
   }

   @Override
   public ByteBuf skipBytes(int var1) {
      this.checkReadableBytes(var1);
      this.readerIndex += var1;
      return this;
   }

   @Override
   public int bytesBefore(byte var1) {
      return this.bytesBefore(this.readerIndex(), this.readableBytes(), var1);
   }

   @Override
   public ByteBuf setShort(int var1, int var2) {
      this.checkIndex(var1, 2);
      this._setShort(var1, var2);
      return this;
   }

   public void maxCapacity(int var1) {
      this.maxCapacity = var1;
   }

   @Override
   public int readableBytes() {
      return this.writerIndex - this.readerIndex;
   }

   @Override
   public int writerIndex() {
      return this.writerIndex;
   }

   public abstract int _getInt(int var1);

   @Override
   public int writeBytes(ScatteringByteChannel var1, int var2) throws java.io.IOException {
      this.ensureAccessible();
      this.ensureWritable(var2);
      int var3 = this.setBytes(this.writerIndex, var1, var2);
      if (var3 > 0) {
         this.writerIndex += var3;
      }

      return var3;
   }

   @Override
   public ByteBuf slice() {
      return this.slice(this.readerIndex, this.readableBytes());
   }

   @Override
   public ByteBuf order(ByteOrder var1) {
      if (var1 == null) {
         throw new NullPointerException("endianness");
      } else if (var1 == this.order()) {
         return this;
      } else {
         SwappedByteBuf var2 = this.swappedBuf;
         if (var2 == null) {
            this.swappedBuf = var2 = this.newSwappedByteBuf();
         }

         return var2;
      }
   }

   @Override
   public ByteBuf discardReadBytes() {
      this.ensureAccessible();
      if (this.readerIndex == 0) {
         return this;
      } else {
         if (this.readerIndex != this.writerIndex) {
            this.setBytes(0, this, this.readerIndex, this.writerIndex - this.readerIndex);
            this.writerIndex = this.writerIndex - this.readerIndex;
            this.adjustMarkers(this.readerIndex);
            this.readerIndex = 0;
         } else {
            this.adjustMarkers(this.readerIndex);
            this.writerIndex = this.readerIndex = 0;
         }

         return this;
      }
   }

   @Override
   public int getUnsignedShort(int var1) {
      return this.getShort(var1) & 65535;
   }

   @Override
   public ByteBuf getBytes(int var1, byte[] var2) {
      this.getBytes(var1, var2, 0, var2.length);
      return this;
   }

   public abstract void _setByte(int var1, int var2);

   @Override
   public ByteBuf setIndex(int var1, int var2) {
      if (var1 >= 0 && var1 <= var2 && var2 <= this.capacity()) {
         this.readerIndex = var1;
         this.writerIndex = var2;
         return this;
      } else {
         throw new IndexOutOfBoundsException(
            String.format("readerIndex: %d, writerIndex: %d (expected: 0 <= readerIndex <= writerIndex <= capacity(%d))", var1, var2, this.capacity())
         );
      }
   }

   @Override
   public int readInt() {
      this.checkReadableBytes(4);
      int var1 = this._getInt(this.readerIndex);
      this.readerIndex += 4;
      return var1;
   }

   @Override
   public ByteBuf discardSomeReadBytes() {
      this.ensureAccessible();
      if (this.readerIndex == 0) {
         return this;
      } else if (this.readerIndex == this.writerIndex) {
         this.adjustMarkers(this.readerIndex);
         this.writerIndex = this.readerIndex = 0;
         return this;
      } else {
         if (this.readerIndex >= this.capacity() >>> 1) {
            this.setBytes(0, this, this.readerIndex, this.writerIndex - this.readerIndex);
            this.writerIndex = this.writerIndex - this.readerIndex;
            this.adjustMarkers(this.readerIndex);
            this.readerIndex = 0;
         }

         return this;
      }
   }

   @Override
   public ByteBuf writeChar(int var1) {
      this.writeShort(var1);
      return this;
   }

   @Override
   public ByteBuf setBoolean(int var1, boolean var2) {
      this.setByte(var1, var2 ? 1 : 0);
      return this;
   }
}
