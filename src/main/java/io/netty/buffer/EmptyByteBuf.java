package io.netty.buffer;

import com.cheatbreaker.client.nethandler.server.PacketTitle;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import javax.vecmath.Matrix3d;
import net.minecraft.nbt.NBTTagByteArray;
import net.minecraft.network.play.server.S2BPacketChangeGameState;

public class EmptyByteBuf extends ByteBuf {
   public EmptyByteBuf swapped;
   public static ByteBuffer EMPTY_BYTE_BUFFER = ByteBuffer.allocateDirect(0);
   public static long EMPTY_BYTE_BUFFER_ADDRESS;
   public ByteBufAllocator alloc;
   public ByteOrder order;
   public String str;

   @Override
   public int readableBytes() {
      return 0;
   }

   @Override
   public float readFloat() {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public ByteBuf readBytes(int var1) {
      return this.checkLength(var1);
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuffer var2) {
      return this.checkIndex(var1, var2.remaining());
   }

   @Override
   public boolean isWritable() {
      return false;
   }

   public ByteBuf checkLength(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("length: " + var1 + " (expected: >= 0)");
      } else if (var1 != 0) {
         throw new IndexOutOfBoundsException();
      } else {
         return this;
      }
   }

   @Override
   public ByteBuf markWriterIndex() {
      return this;
   }

   @Override
   public ByteBuf writeByte(int var1) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public ByteBuf setMedium(int var1, int var2) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public int forEachByteDesc(ByteBufProcessor var1) {
      return -1;
   }

   @Override
   public boolean isWritable(int var1) {
      return false;
   }

   @Override
   public ByteBuf writeChar(int var1) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public boolean isReadable() {
      return false;
   }

   @Override
   public boolean release() {
      return false;
   }

   @Override
   public ByteBuf clear() {
      return this;
   }

   @Override
   public ByteBuf ensureWritable(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("minWritableBytes: " + var1 + " (expected: >= 0)");
      } else if (var1 != 0) {
         throw new IndexOutOfBoundsException();
      } else {
         return this;
      }
   }

   @Override
   public int bytesBefore(byte var1) {
      return -1;
   }

   @Override
   public int readUnsignedMedium() {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public int readUnsignedShort() {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public ByteBuf retain() {
      return this;
   }

   @Override
   public int forEachByte(ByteBufProcessor var1) {
      return -1;
   }

   @Override
   public ByteBuf markReaderIndex() {
      return this;
   }

   @Override
   public int writerIndex() {
      return 0;
   }

   @Override
   public ByteBuf writeMedium(int var1) {
      throw new IndexOutOfBoundsException();
   }

   public ByteBuf checkIndex(int var1, int var2) {
      if (var2 < 0) {
         throw new IllegalArgumentException("length: " + var2);
      } else if (var1 == 0 && var2 == 0) {
         return this;
      } else {
         throw new IndexOutOfBoundsException();
      }
   }

   @Override
   public ByteBuf capacity(int var1) {
      throw new ReadOnlyBufferException();
   }

   @Override
   public int getUnsignedShort(int var1) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public short getUnsignedByte(int var1) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public double readDouble() {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public int setBytes(int var1, InputStream var2, int var3) {
      this.checkIndex(var1, var3);
      return 0;
   }

   @Override
   public boolean hasArray() {
      return true;
   }

   @Override
   public int hashCode() {
      return 0;
   }

   @Override
   public char getChar(int var1) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public int getMedium(int var1) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public ByteBuffer[] nioBuffers(int var1, int var2) {
      this.checkIndex(var1, var2);
      return this.nioBuffers();
   }

   @Override
   public boolean hasMemoryAddress() {
      return EMPTY_BYTE_BUFFER_ADDRESS != 0L;
   }

   @Override
   public ByteBuffer[] nioBuffers() {
      return new ByteBuffer[]{EMPTY_BYTE_BUFFER};
   }

   @Override
   public float getFloat(int var1) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public int getBytes(int var1, GatheringByteChannel var2, int var3) {
      this.checkIndex(var1, var3);
      return 0;
   }

   @Override
   public ByteBuf writeBytes(byte[] var1, int var2, int var3) {
      return this.checkLength(var3);
   }

   @Override
   public long readLong() {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public int capacity() {
      return 0;
   }

   @Override
   public ByteBuf writeBytes(byte[] var1) {
      return this.checkLength(var1.length);
   }

   @Override
   public int getInt(int var1) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public double getDouble(int var1) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2, int var3) {
      return this.checkIndex(var1, var3);
   }

   @Override
   public int getUnsignedMedium(int var1) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public String toString() {
      return this.str;
   }

   @Override
   public char readChar() {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public ByteBuf readerIndex(int var1) {
      return this.checkIndex(var1);
   }

   @Override
   public short getShort(int var1) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public int readBytes(GatheringByteChannel var1, int var2) {
      this.checkLength(var2);
      return 0;
   }

   @Override
   public ByteBuf getBytes(int var1, byte[] var2) {
      return this.checkIndex(var1, var2.length);
   }

   @Override
   public int readerIndex() {
      return 0;
   }

   @Override
   public boolean getBoolean(int var1) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public ByteBuf order(ByteOrder var1) {
      if (var1 == null) {
         throw new NullPointerException("endianness");
      } else if (var1 == this.order()) {
         return this;
      } else {
         EmptyByteBuf var2 = this.swapped;
         if (var2 != null) {
            return var2;
         } else {
            this.swapped = var2 = new EmptyByteBuf(this.alloc(), var1);
            return var2;
         }
      }
   }

   @Override
   public String toString(int var1, int var2, Charset var3) {
      this.checkIndex(var1, var2);
      return this.toString(var3);
   }

   @Override
   public ByteBuffer nioBuffer() {
      return EMPTY_BYTE_BUFFER;
   }

   @Override
   public short readShort() {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public ByteBuf slice(int var1, int var2) {
      return this.checkIndex(var1, var2);
   }

   @Override
   public ByteBuf setBoolean(int var1, boolean var2) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public ByteBuffer nioBuffer(int var1, int var2) {
      this.checkIndex(var1, var2);
      return this.nioBuffer();
   }

   @Override
   public ByteBuf readBytes(byte[] var1, int var2, int var3) {
      return this.checkLength(var3);
   }

   @Override
   public ByteBuf duplicate() {
      return this;
   }

   @Override
   public ByteBuf setFloat(int var1, float var2) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public ByteOrder order() {
      return this.order;
   }

   @Override
   public ByteBuf writeBytes(ByteBuffer var1) {
      return this.checkLength(var1.remaining());
   }

   @Override
   public int indexOf(int var1, int var2, byte var3) {
      this.checkIndex(var1);
      this.checkIndex(var2);
      return -1;
   }

   @Override
   public ByteBuf readBytes(ByteBuf var1, int var2) {
      return this.checkLength(var2);
   }

   @Override
   public boolean readBoolean() {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public ByteBuf setLong(int var1, long var2) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public int setBytes(int var1, ScatteringByteChannel var2, int var3) {
      this.checkIndex(var1, var3);
      return 0;
   }

   @Override
   public ByteBuf discardSomeReadBytes() {
      return this;
   }

   @Override
   public ByteBuf setZero(int var1, int var2) {
      return this.checkIndex(var1, var2);
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuffer var2) {
      return this.checkIndex(var1, var2.remaining());
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2, int var3, int var4) {
      return this.checkIndex(var1, var4);
   }

   @Override
   public boolean isReadable(int var1) {
      return false;
   }

   @Override
   public ByteBuf writerIndex(int var1) {
      return this.checkIndex(var1);
   }

   @Override
   public ByteBuf writeBytes(ByteBuf var1) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public ByteBuf resetReaderIndex() {
      return this;
   }

   @Override
   public byte[] array() {
      return EmptyArrays.EMPTY_BYTES;
   }

   @Override
   public int ensureWritable(int var1, boolean var2) {
      if (var1 < 0) {
         throw new IllegalArgumentException("minWritableBytes: " + var1 + " (expected: >= 0)");
      } else {
         return var1 == 0 ? 0 : 1;
      }
   }

   @Override
   public int writeBytes(InputStream var1, int var2) {
      this.checkLength(var2);
      return 0;
   }

   @Override
   public ByteBuf setInt(int var1, int var2) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuf var2) {
      return this.checkIndex(var1, var2.writableBytes());
   }

   @Override
   public ByteBuf readSlice(int var1) {
      return this.checkLength(var1);
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuf var2, int var3, int var4) {
      return this.checkIndex(var1, var4);
   }

   @Override
   public ByteBuf readBytes(ByteBuf var1, int var2, int var3) {
      return this.checkLength(var3);
   }

   @Override
   public int readMedium() {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public ByteBuf writeShort(int var1) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public byte getByte(int var1) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public int writeBytes(ScatteringByteChannel var1, int var2) {
      this.checkLength(var2);
      return 0;
   }

   @Override
   public ByteBuf writeDouble(double var1) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public ByteBuf writeBytes(ByteBuf var1, int var2, int var3) {
      return this.checkLength(var3);
   }

   @Override
   public ByteBuf writeLong(long var1) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public boolean release(int var1) {
      return false;
   }

   @Override
   public int arrayOffset() {
      return 0;
   }

   @Override
   public int bytesBefore(int var1, byte var2) {
      this.checkLength(var1);
      return -1;
   }

   @Override
   public ByteBuf getBytes(int var1, byte[] var2, int var3, int var4) {
      return this.checkIndex(var1, var4);
   }

   @Override
   public int refCnt() {
      return 1;
   }

   @Override
   public long readUnsignedInt() {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public ByteBuf copy() {
      return this;
   }

   @Override
   public ByteBuf unwrap() {
      return null;
   }

   @Override
   public int readInt() {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public ByteBufAllocator alloc() {
      return this.alloc;
   }

   @Override
   public boolean isDirect() {
      return true;
   }

   @Override
   public int writableBytes() {
      return 0;
   }

   @Override
   public int maxCapacity() {
      return 0;
   }

   @Override
   public long getUnsignedInt(int var1) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public int forEachByteDesc(int var1, int var2, ByteBufProcessor var3) {
      this.checkIndex(var1, var2);
      return -1;
   }

   @Override
   public ByteBuf writeBytes(ByteBuf var1, int var2) {
      return this.checkLength(var2);
   }

   static {
      long var0 = 0L;

      try {
         if (PlatformDependent.hasUnsafe()) {
            var0 = PlatformDependent.directBufferAddress(EMPTY_BYTE_BUFFER);
         }
      } catch (Throwable var3) {
      }

      EMPTY_BYTE_BUFFER_ADDRESS = var0;
   }

   @Override
   public ByteBuf slice() {
      return this;
   }

   @Override
   public ByteBuf writeFloat(float var1) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public ByteBuf discardReadBytes() {
      return this;
   }

   public EmptyByteBuf(ByteBufAllocator var1) {
      this(var1, ByteOrder.BIG_ENDIAN);
   }

   @Override
   public int maxWritableBytes() {
      return 0;
   }

   @Override
   public ByteBuf writeInt(int var1) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public ByteBuf skipBytes(int var1) {
      return this.checkLength(var1);
   }

   @Override
   public byte readByte() {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuf var2, int var3) {
      return this.checkIndex(var1, var3);
   }

   @Override
   public ByteBuf readBytes(byte[] var1) {
      return this.checkLength(var1.length);
   }

   @Override
   public ByteBuf retain(int var1) {
      return this;
   }

   @Override
   public ByteBuf setBytes(int var1, byte[] var2) {
      return this.checkIndex(var1, var2.length);
   }

   @Override
   public ByteBuf writeBoolean(boolean var1) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public ByteBuf setByte(int var1, int var2) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public ByteBuf resetWriterIndex() {
      return this;
   }

   @Override
   public ByteBuf readBytes(ByteBuf var1) {
      return this.checkLength(var1.writableBytes());
   }

   @Override
   public ByteBuf getBytes(int var1, OutputStream var2, int var3) {
      return this.checkIndex(var1, var3);
   }

   @Override
   public ByteBuf writeZero(int var1) {
      return this.checkLength(var1);
   }

   @Override
   public ByteBuf readBytes(ByteBuffer var1) {
      return this.checkLength(var1.remaining());
   }

   @Override
   public int bytesBefore(int var1, int var2, byte var3) {
      this.checkIndex(var1, var2);
      return -1;
   }

   @Override
   public long getLong(int var1) {
      throw new IndexOutOfBoundsException();
   }

   public EmptyByteBuf(ByteBufAllocator var1, ByteOrder var2) {
      if (var1 == null) {
         throw new NullPointerException("alloc");
      } else {
         this.alloc = var1;
         this.order = var2;
         this.str = StringUtil.simpleClassName(this) + (var2 == ByteOrder.BIG_ENDIAN ? "BE" : "LE");
      }
   }

   @Override
   public int nioBufferCount() {
      return 1;
   }

   @Override
   public String toString(Charset var1) {
      return "";
   }

   @Override
   public int compareTo(ByteBuf var1) {
      return var1.isReadable() ? -1 : 0;
   }

   @Override
   public boolean equals(Object var1) {
      return var1 instanceof ByteBuf && !((ByteBuf)var1).isReadable();
   }

   public ByteBuf checkIndex(int var1) {
      if (var1 != 0) {
         throw new IndexOutOfBoundsException();
      } else {
         return this;
      }
   }

   @Override
   public ByteBuf setChar(int var1, int var2) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public long memoryAddress() {
      if (this.hasMemoryAddress()) {
         return EMPTY_BYTE_BUFFER_ADDRESS;
      } else {
         throw new UnsupportedOperationException();
      }
   }

   @Override
   public ByteBuf readBytes(OutputStream var1, int var2) {
      return this.checkLength(var2);
   }

   @Override
   public ByteBuf setBytes(int var1, byte[] var2, int var3, int var4) {
      return this.checkIndex(var1, var4);
   }

   @Override
   public ByteBuf copy(int var1, int var2) {
      return this.checkIndex(var1, var2);
   }

   @Override
   public ByteBuffer internalNioBuffer(int var1, int var2) {
      return EMPTY_BYTE_BUFFER;
   }

   @Override
   public ByteBuf setIndex(int var1, int var2) {
      this.checkIndex(var1);
      this.checkIndex(var2);
      return this;
   }

   @Override
   public ByteBuf setDouble(int var1, double var2) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public int forEachByte(int var1, int var2, ByteBufProcessor var3) {
      this.checkIndex(var1, var2);
      return -1;
   }

   @Override
   public ByteBuf setShort(int var1, int var2) {
      throw new IndexOutOfBoundsException();
   }

   @Override
   public short readUnsignedByte() {
      throw new IndexOutOfBoundsException();
   }
}
