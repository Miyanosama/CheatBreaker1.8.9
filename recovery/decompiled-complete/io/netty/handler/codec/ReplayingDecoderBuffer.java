package io.netty.handler.codec;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufProcessor;
import io.netty.buffer.SwappedByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.Signal;
import io.netty.util.internal.StringUtil;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import net.minecraft.util.AxisAlignedBB;
import recovered.unidentified.UnidentifiedClass1394;

public class ReplayingDecoderBuffer extends ByteBuf {
   public static ReplayingDecoderBuffer EMPTY_BUFFER = new ReplayingDecoderBuffer(Unpooled.EMPTY_BUFFER);
   public SwappedByteBuf swapped;
   public UnidentifiedClass1394 __junk6951202566192922937;
   public AxisAlignedBB __junk4389551795709112859;
   public static Signal REPLAY = ReplayingDecoder.REPLAY;
   public ByteBuf buffer;
   public boolean terminated;

   @Override
   public ByteOrder order() {
      return this.buffer.order();
   }

   @Override
   public ByteBuf setBytes(int var1, byte[] var2, int var3, int var4) {
      reject();
      return this;
   }

   @Override
   public boolean release(int var1) {
      reject();
      return false;
   }

   @Override
   public ByteBuf readBytes(OutputStream var1, int var2) {
      reject();
      return this;
   }

   @Override
   public int ensureWritable(int var1, boolean var2) {
      reject();
      return 0;
   }

   @Override
   public ByteBuf readBytes(byte[] var1) {
      this.checkReadableBytes(var1.length);
      this.buffer.readBytes(var1);
      return this;
   }

   @Override
   public ByteBuf unwrap() {
      reject();
      return this;
   }

   @Override
   public boolean hasMemoryAddress() {
      return false;
   }

   @Override
   public ByteBuf setChar(int var1, int var2) {
      reject();
      return this;
   }

   public ReplayingDecoderBuffer() {
   }

   @Override
   public ByteBuf writeMedium(int var1) {
      reject();
      return this;
   }

   @Override
   public int getInt(int var1) {
      this.checkIndex(var1, 4);
      return this.buffer.getInt(var1);
   }

   public static void reject() {
      throw new UnsupportedOperationException("not a replayable operation");
   }

   @Override
   public int writerIndex() {
      return this.buffer.writerIndex();
   }

   @Override
   public String toString(int var1, int var2, Charset var3) {
      this.checkIndex(var1, var2);
      return this.buffer.toString(var1, var2, var3);
   }

   @Override
   public ByteBuf setLong(int var1, long var2) {
      reject();
      return this;
   }

   @Override
   public ByteBuf writeShort(int var1) {
      reject();
      return this;
   }

   @Override
   public String toString(Charset var1) {
      reject();
      return null;
   }

   @Override
   public ByteBuf markWriterIndex() {
      reject();
      return this;
   }

   @Override
   public int setBytes(int var1, ScatteringByteChannel var2, int var3) {
      reject();
      return 0;
   }

   @Override
   public ByteBuf readBytes(ByteBuf var1) {
      this.checkReadableBytes(var1.writableBytes());
      this.buffer.readBytes(var1);
      return this;
   }

   @Override
   public ByteBuf setByte(int var1, int var2) {
      reject();
      return this;
   }

   @Override
   public ByteBuf getBytes(int var1, byte[] var2) {
      this.checkIndex(var1, var2.length);
      this.buffer.getBytes(var1, var2);
      return this;
   }

   @Override
   public long memoryAddress() {
      throw new UnsupportedOperationException();
   }

   @Override
   public int bytesBefore(int var1, int var2, byte var3) {
      int var4 = this.buffer.writerIndex();
      if (var1 >= var4) {
         throw REPLAY;
      } else if (var1 <= var4 - var2) {
         return this.buffer.bytesBefore(var1, var2, var3);
      } else {
         int var5 = this.buffer.bytesBefore(var1, var4 - var1, var3);
         if (var5 < 0) {
            throw REPLAY;
         } else {
            return var5;
         }
      }
   }

   @Override
   public ByteBuf readBytes(int var1) {
      this.checkReadableBytes(var1);
      return this.buffer.readBytes(var1);
   }

   @Override
   public float getFloat(int var1) {
      this.checkIndex(var1, 4);
      return this.buffer.getFloat(var1);
   }

   public ReplayingDecoderBuffer(ByteBuf var1) {
      this.setCumulation(var1);
   }

   @Override
   public long getLong(int var1) {
      this.checkIndex(var1, 8);
      return this.buffer.getLong(var1);
   }

   @Override
   public ByteBuf setInt(int var1, int var2) {
      reject();
      return this;
   }

   @Override
   public ByteBuffer[] nioBuffers() {
      reject();
      return null;
   }

   @Override
   public int getBytes(int var1, GatheringByteChannel var2, int var3) {
      reject();
      return 0;
   }

   @Override
   public int refCnt() {
      return this.buffer.refCnt();
   }

   @Override
   public ByteBuf writeBytes(ByteBuf var1) {
      reject();
      return this;
   }

   @Override
   public ByteBuf writeLong(long var1) {
      reject();
      return this;
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuffer var2) {
      reject();
      return this;
   }

   @Override
   public ByteBuf copy() {
      reject();
      return this;
   }

   @Override
   public ByteBuf readerIndex(int var1) {
      this.buffer.readerIndex(var1);
      return this;
   }

   @Override
   public ByteBuffer nioBuffer(int var1, int var2) {
      this.checkIndex(var1, var2);
      return this.buffer.nioBuffer(var1, var2);
   }

   @Override
   public long readLong() {
      this.checkReadableBytes(8);
      return this.buffer.readLong();
   }

   @Override
   public ByteBuf markReaderIndex() {
      this.buffer.markReaderIndex();
      return this;
   }

   @Override
   public short readUnsignedByte() {
      this.checkReadableBytes(1);
      return this.buffer.readUnsignedByte();
   }

   @Override
   public ByteBuf writeDouble(double var1) {
      reject();
      return this;
   }

   public void terminate() {
      this.terminated = true;
   }

   @Override
   public ByteBuffer nioBuffer() {
      reject();
      return null;
   }

   @Override
   public int setBytes(int var1, InputStream var2, int var3) {
      reject();
      return 0;
   }

   @Override
   public int writableBytes() {
      return 0;
   }

   @Override
   public int readUnsignedShort() {
      this.checkReadableBytes(2);
      return this.buffer.readUnsignedShort();
   }

   @Override
   public double getDouble(int var1) {
      this.checkIndex(var1, 8);
      return this.buffer.getDouble(var1);
   }

   @Override
   public ByteBuf writeBytes(byte[] var1, int var2, int var3) {
      reject();
      return this;
   }

   @Override
   public int readInt() {
      this.checkReadableBytes(4);
      return this.buffer.readInt();
   }

   @Override
   public ByteBuf writeBytes(byte[] var1) {
      reject();
      return this;
   }

   @Override
   public ByteBuf setMedium(int var1, int var2) {
      reject();
      return this;
   }

   @Override
   public ByteBuf getBytes(int var1, byte[] var2, int var3, int var4) {
      this.checkIndex(var1, var4);
      this.buffer.getBytes(var1, var2, var3, var4);
      return this;
   }

   @Override
   public int getUnsignedShort(int var1) {
      this.checkIndex(var1, 2);
      return this.buffer.getUnsignedShort(var1);
   }

   @Override
   public short getUnsignedByte(int var1) {
      this.checkIndex(var1, 1);
      return this.buffer.getUnsignedByte(var1);
   }

   public void checkReadableBytes(int var1) {
      if (this.buffer.readableBytes() < var1) {
         throw REPLAY;
      }
   }

   @Override
   public int forEachByte(ByteBufProcessor var1) {
      int var2 = this.buffer.forEachByte(var1);
      if (var2 < 0) {
         throw REPLAY;
      } else {
         return var2;
      }
   }

   @Override
   public int getUnsignedMedium(int var1) {
      this.checkIndex(var1, 3);
      return this.buffer.getUnsignedMedium(var1);
   }

   @Override
   public byte[] array() {
      throw new UnsupportedOperationException();
   }

   @Override
   public ByteBuf readBytes(ByteBuf var1, int var2, int var3) {
      this.checkReadableBytes(var3);
      this.buffer.readBytes(var1, var2, var3);
      return this;
   }

   @Override
   public char getChar(int var1) {
      this.checkIndex(var1, 2);
      return this.buffer.getChar(var1);
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuf var2) {
      reject();
      return this;
   }

   @Override
   public boolean equals(Object var1) {
      return this == var1;
   }

   @Override
   public int forEachByte(int var1, int var2, ByteBufProcessor var3) {
      int var4 = this.buffer.writerIndex();
      if (var1 >= var4) {
         throw REPLAY;
      } else if (var1 <= var4 - var2) {
         return this.buffer.forEachByte(var1, var2, var3);
      } else {
         int var5 = this.buffer.forEachByte(var1, var4 - var1, var3);
         if (var5 < 0) {
            throw REPLAY;
         } else {
            return var5;
         }
      }
   }

   @Override
   public int readerIndex() {
      return this.buffer.readerIndex();
   }

   @Override
   public ByteBuf readBytes(ByteBuffer var1) {
      reject();
      return this;
   }

   @Override
   public int forEachByteDesc(int var1, int var2, ByteBufProcessor var3) {
      if (var1 + var2 > this.buffer.writerIndex()) {
         throw REPLAY;
      } else {
         return this.buffer.forEachByteDesc(var1, var2, var3);
      }
   }

   @Override
   public ByteBuf order(ByteOrder var1) {
      if (var1 == null) {
         throw new NullPointerException("endianness");
      } else if (var1 == this.order()) {
         return this;
      } else {
         SwappedByteBuf var2 = this.swapped;
         if (var2 == null) {
            this.swapped = var2 = new SwappedByteBuf(this);
         }

         return var2;
      }
   }

   @Override
   public boolean release() {
      reject();
      return false;
   }

   @Override
   public int readBytes(GatheringByteChannel var1, int var2) {
      reject();
      return 0;
   }

   @Override
   public ByteBuf writeInt(int var1) {
      reject();
      return this;
   }

   @Override
   public ByteBuf setDouble(int var1, double var2) {
      reject();
      return this;
   }

   @Override
   public ByteBuf slice() {
      reject();
      return this;
   }

   @Override
   public int capacity() {
      return this.terminated ? this.buffer.capacity() : Integer.MAX_VALUE;
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2, int var3) {
      reject();
      return this;
   }

   @Override
   public ByteBuf setIndex(int var1, int var2) {
      reject();
      return this;
   }

   @Override
   public short getShort(int var1) {
      this.checkIndex(var1, 2);
      return this.buffer.getShort(var1);
   }

   @Override
   public ByteBuf readSlice(int var1) {
      this.checkReadableBytes(var1);
      return this.buffer.readSlice(var1);
   }

   @Override
   public double readDouble() {
      this.checkReadableBytes(8);
      return this.buffer.readDouble();
   }

   @Override
   public ByteBuf getBytes(int var1, OutputStream var2, int var3) {
      reject();
      return this;
   }

   @Override
   public ByteBuf writeBytes(ByteBuf var1, int var2) {
      reject();
      return this;
   }

   @Override
   public ByteBufAllocator alloc() {
      return this.buffer.alloc();
   }

   @Override
   public int readableBytes() {
      return this.terminated ? this.buffer.readableBytes() : Integer.MAX_VALUE - this.buffer.readerIndex();
   }

   static {
      EMPTY_BUFFER.terminate();
   }

   @Override
   public int indexOf(int var1, int var2, byte var3) {
      if (var1 == var2) {
         return -1;
      } else if (Math.max(var1, var2) > this.buffer.writerIndex()) {
         throw REPLAY;
      } else {
         return this.buffer.indexOf(var1, var2, var3);
      }
   }

   @Override
   public boolean isWritable() {
      return false;
   }

   @Override
   public ByteBuf writeFloat(float var1) {
      reject();
      return this;
   }

   @Override
   public boolean isReadable() {
      return this.terminated ? this.buffer.isReadable() : true;
   }

   @Override
   public ByteBuf copy(int var1, int var2) {
      this.checkIndex(var1, var2);
      return this.buffer.copy(var1, var2);
   }

   @Override
   public ByteBuf resetWriterIndex() {
      reject();
      return this;
   }

   @Override
   public int forEachByteDesc(ByteBufProcessor var1) {
      if (this.terminated) {
         return this.buffer.forEachByteDesc(var1);
      } else {
         reject();
         return 0;
      }
   }

   @Override
   public ByteBuf resetReaderIndex() {
      this.buffer.resetReaderIndex();
      return this;
   }

   @Override
   public int bytesBefore(byte var1) {
      int var2 = this.buffer.bytesBefore(var1);
      if (var2 < 0) {
         throw REPLAY;
      } else {
         return var2;
      }
   }

   @Override
   public int nioBufferCount() {
      return this.buffer.nioBufferCount();
   }

   @Override
   public float readFloat() {
      this.checkReadableBytes(4);
      return this.buffer.readFloat();
   }

   @Override
   public short readShort() {
      this.checkReadableBytes(2);
      return this.buffer.readShort();
   }

   @Override
   public byte getByte(int var1) {
      this.checkIndex(var1, 1);
      return this.buffer.getByte(var1);
   }

   @Override
   public boolean isWritable(int var1) {
      return false;
   }

   @Override
   public ByteBuf clear() {
      reject();
      return this;
   }

   @Override
   public int writeBytes(InputStream var1, int var2) {
      reject();
      return 0;
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuf var2, int var3, int var4) {
      this.checkIndex(var1, var4);
      this.buffer.getBytes(var1, var2, var3, var4);
      return this;
   }

   @Override
   public ByteBuf writeBytes(ByteBuffer var1) {
      reject();
      return this;
   }

   @Override
   public int readMedium() {
      this.checkReadableBytes(3);
      return this.buffer.readMedium();
   }

   @Override
   public ByteBuf skipBytes(int var1) {
      this.checkReadableBytes(var1);
      this.buffer.skipBytes(var1);
      return this;
   }

   @Override
   public ByteBuf setBoolean(int var1, boolean var2) {
      reject();
      return this;
   }

   @Override
   public int bytesBefore(int var1, byte var2) {
      int var3 = this.buffer.readerIndex();
      return this.bytesBefore(var3, this.buffer.writerIndex() - var3, var2);
   }

   @Override
   public ByteBuf writeBytes(ByteBuf var1, int var2, int var3) {
      reject();
      return this;
   }

   @Override
   public boolean getBoolean(int var1) {
      this.checkIndex(var1, 1);
      return this.buffer.getBoolean(var1);
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuf var2, int var3) {
      reject();
      return this;
   }

   @Override
   public ByteBuf discardReadBytes() {
      reject();
      return this;
   }

   @Override
   public ByteBuf writeChar(int var1) {
      reject();
      return this;
   }

   @Override
   public boolean isDirect() {
      return this.buffer.isDirect();
   }

   @Override
   public boolean hasArray() {
      return false;
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuffer var2) {
      reject();
      return this;
   }

   @Override
   public ByteBuffer[] nioBuffers(int var1, int var2) {
      this.checkIndex(var1, var2);
      return this.buffer.nioBuffers(var1, var2);
   }

   @Override
   public int writeBytes(ScatteringByteChannel var1, int var2) {
      reject();
      return 0;
   }

   @Override
   public long getUnsignedInt(int var1) {
      this.checkIndex(var1, 4);
      return this.buffer.getUnsignedInt(var1);
   }

   @Override
   public ByteBuf writerIndex(int var1) {
      reject();
      return this;
   }

   @Override
   public boolean readBoolean() {
      this.checkReadableBytes(1);
      return this.buffer.readBoolean();
   }

   @Override
   public ByteBuf ensureWritable(int var1) {
      reject();
      return this;
   }

   @Override
   public int arrayOffset() {
      throw new UnsupportedOperationException();
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2, int var3, int var4) {
      reject();
      return this;
   }

   @Override
   public ByteBuf readBytes(byte[] var1, int var2, int var3) {
      this.checkReadableBytes(var3);
      this.buffer.readBytes(var1, var2, var3);
      return this;
   }

   @Override
   public ByteBuf retain() {
      reject();
      return this;
   }

   @Override
   public ByteBuf setFloat(int var1, float var2) {
      reject();
      return this;
   }

   @Override
   public ByteBuf setShort(int var1, int var2) {
      reject();
      return this;
   }

   @Override
   public int readUnsignedMedium() {
      this.checkReadableBytes(3);
      return this.buffer.readUnsignedMedium();
   }

   @Override
   public ByteBuf setZero(int var1, int var2) {
      reject();
      return this;
   }

   public void setCumulation(ByteBuf var1) {
      this.buffer = var1;
   }

   @Override
   public String toString() {
      return StringUtil.simpleClassName(this) + '(' + "ridx=" + this.readerIndex() + ", " + "widx=" + this.writerIndex() + ')';
   }

   @Override
   public long readUnsignedInt() {
      this.checkReadableBytes(4);
      return this.buffer.readUnsignedInt();
   }

   @Override
   public ByteBuf capacity(int var1) {
      reject();
      return this;
   }

   @Override
   public int maxCapacity() {
      return this.capacity();
   }

   @Override
   public boolean isReadable(int var1) {
      return this.terminated ? this.buffer.isReadable(var1) : true;
   }

   @Override
   public ByteBuf slice(int var1, int var2) {
      this.checkIndex(var1, var2);
      return this.buffer.slice(var1, var2);
   }

   @Override
   public int compareTo(ByteBuf var1) {
      reject();
      return 0;
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2) {
      reject();
      return this;
   }

   @Override
   public ByteBuf readBytes(ByteBuf var1, int var2) {
      reject();
      return this;
   }

   @Override
   public ByteBuf writeZero(int var1) {
      reject();
      return this;
   }

   public void checkIndex(int var1, int var2) {
      if (var1 + var2 > this.buffer.writerIndex()) {
         throw REPLAY;
      }
   }

   @Override
   public ByteBuf writeByte(int var1) {
      reject();
      return this;
   }

   @Override
   public ByteBuffer internalNioBuffer(int var1, int var2) {
      this.checkIndex(var1, var2);
      return this.buffer.internalNioBuffer(var1, var2);
   }

   @Override
   public ByteBuf retain(int var1) {
      reject();
      return this;
   }

   @Override
   public ByteBuf duplicate() {
      reject();
      return this;
   }

   @Override
   public ByteBuf setBytes(int var1, byte[] var2) {
      reject();
      return this;
   }

   @Override
   public ByteBuf discardSomeReadBytes() {
      reject();
      return this;
   }

   @Override
   public ByteBuf writeBoolean(boolean var1) {
      reject();
      return this;
   }

   @Override
   public int maxWritableBytes() {
      return 0;
   }

   @Override
   public int hashCode() {
      reject();
      return 0;
   }

   @Override
   public int getMedium(int var1) {
      this.checkIndex(var1, 3);
      return this.buffer.getMedium(var1);
   }

   @Override
   public char readChar() {
      this.checkReadableBytes(2);
      return this.buffer.readChar();
   }

   @Override
   public byte readByte() {
      this.checkReadableBytes(1);
      return this.buffer.readByte();
   }
}
