package io.netty.buffer;

import io.netty.channel.epoll.AbstractEpollChannel;
import io.netty.util.internal.PlatformDependent;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import javax.vecmath.AxisAngle4d;
import net.minecraft.client.model.ModelBiped;

public class UnpooledHeapByteBuf extends AbstractReferenceCountedByteBuf {
   public ByteBuffer tmpNioBuf;
   public ByteBufAllocator alloc;
   public byte[] array;

   public UnpooledHeapByteBuf(ByteBufAllocator var1, byte[] var2, int var3) {
      this(var1, var2, 0, var2.length, var3);
   }

   @Override
   public int _getUnsignedMedium(int var1) {
      return (this.array[var1] & 0xFF) << 16 | (this.array[var1 + 1] & 0xFF) << 8 | this.array[var1 + 2] & 0xFF;
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2, int var3, int var4) {
      this.checkSrcIndex(var1, var4, var3, var2.capacity());
      if (var2.hasMemoryAddress()) {
         PlatformDependent.copyMemory(var2.memoryAddress() + var3, this.array, var1, var4);
      } else if (var2.hasArray()) {
         this.setBytes(var1, var2.array(), var2.arrayOffset() + var3, var4);
      } else {
         var2.getBytes(var3, this.array, var1, var4);
      }

      return this;
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuf var2, int var3, int var4) {
      this.checkDstIndex(var1, var4, var3, var2.capacity());
      if (var2.hasMemoryAddress()) {
         PlatformDependent.copyMemory(this.array, var1, var2.memoryAddress() + var3, var4);
      } else if (var2.hasArray()) {
         this.getBytes(var1, var2.array(), var2.arrayOffset() + var3, var4);
      } else {
         var2.setBytes(var3, this.array, var1, var4);
      }

      return this;
   }

   @Override
   public int readBytes(GatheringByteChannel var1, int var2) throws java.io.IOException {
      this.checkReadableBytes(var2);
      int var3 = this.getBytes(this.readerIndex, var1, var2, true);
      this.readerIndex += var3;
      return var3;
   }

   public void setArray(byte[] var1) {
      this.array = var1;
      this.tmpNioBuf = null;
   }

   @Override
   public ByteOrder order() {
      return ByteOrder.BIG_ENDIAN;
   }

   @Override
   public byte getByte(int var1) {
      this.ensureAccessible();
      return this._getByte(var1);
   }

   @Override
   public ByteBuf capacity(int var1) {
      this.ensureAccessible();
      if (var1 >= 0 && var1 <= this.maxCapacity()) {
         int var2 = this.array.length;
         if (var1 > var2) {
            byte[] var3 = new byte[var1];
            System.arraycopy(this.array, 0, var3, 0, this.array.length);
            this.setArray(var3);
         } else if (var1 < var2) {
            byte[] var6 = new byte[var1];
            int var4 = this.readerIndex();
            if (var4 < var1) {
               int var5 = this.writerIndex();
               if (var5 > var1) {
                  var5 = var1;
                  this.writerIndex(var1);
               }

               System.arraycopy(this.array, var4, var6, var4, var5 - var4);
            } else {
               this.setIndex(var1, var1);
            }

            this.setArray(var6);
         }

         return this;
      } else {
         throw new IllegalArgumentException("newCapacity: " + var1);
      }
   }

   @Override
   public int nioBufferCount() {
      return 1;
   }

   @Override
   public ByteBuf copy(int var1, int var2) {
      this.checkIndex(var1, var2);
      byte[] var3 = new byte[var2];
      System.arraycopy(this.array, var1, var3, 0, var2);
      return new UnpooledHeapByteBuf(this.alloc(), var3, this.maxCapacity());
   }

   @Override
   public boolean isDirect() {
      return false;
   }

   @Override
   public int getBytes(int var1, GatheringByteChannel var2, int var3) throws java.io.IOException {
      this.ensureAccessible();
      return this.getBytes(var1, var2, var3, false);
   }

   public ByteBuffer internalNioBuffer() {
      ByteBuffer var1 = this.tmpNioBuf;
      if (var1 == null) {
         this.tmpNioBuf = var1 = ByteBuffer.wrap(this.array);
      }

      return var1;
   }

   public UnpooledHeapByteBuf(ByteBufAllocator var1, byte[] var2, int var3, int var4, int var5) {
      super(var5);
      if (var1 == null) {
         throw new NullPointerException("alloc");
      } else if (var2 == null) {
         throw new NullPointerException("initialArray");
      } else if (var2.length > var5) {
         throw new IllegalArgumentException(String.format("initialCapacity(%d) > maxCapacity(%d)", var2.length, var5));
      } else {
         this.alloc = var1;
         this.setArray(var2);
         this.setIndex(var3, var4);
      }
   }

   public UnpooledHeapByteBuf(ByteBufAllocator var1, int var2, int var3) {
      this(var1, new byte[var2], 0, 0, var3);
   }

   @Override
   public int arrayOffset() {
      return 0;
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuffer var2) {
      this.ensureAccessible();
      var2.get(this.array, var1, var2.remaining());
      return this;
   }

   @Override
   public void deallocate() {
      this.array = null;
   }

   @Override
   public int getUnsignedMedium(int var1) {
      this.ensureAccessible();
      return this._getUnsignedMedium(var1);
   }

   @Override
   public void _setShort(int var1, int var2) {
      this.array[var1] = (byte)(var2 >>> 8);
      this.array[var1 + 1] = (byte)var2;
   }

   @Override
   public ByteBuf setLong(int var1, long var2) {
      this.ensureAccessible();
      this._setLong(var1, var2);
      return this;
   }

   @Override
   public short getShort(int var1) {
      this.ensureAccessible();
      return this._getShort(var1);
   }

   @Override
   public ByteBuf setShort(int var1, int var2) {
      this.ensureAccessible();
      this._setShort(var1, var2);
      return this;
   }

   @Override
   public boolean hasMemoryAddress() {
      return false;
   }

   @Override
   public int setBytes(int var1, InputStream var2, int var3) throws java.io.IOException {
      this.ensureAccessible();
      return var2.read(this.array, var1, var3);
   }

   @Override
   public void _setLong(int var1, long var2) {
      this.array[var1] = (byte)(var2 >>> 56);
      this.array[var1 + 1] = (byte)(var2 >>> 48);
      this.array[var1 + 2] = (byte)(var2 >>> 40);
      this.array[var1 + 3] = (byte)(var2 >>> 32);
      this.array[var1 + 4] = (byte)(var2 >>> 24);
      this.array[var1 + 5] = (byte)(var2 >>> 16);
      this.array[var1 + 6] = (byte)(var2 >>> 8);
      this.array[var1 + 7] = (byte)var2;
   }

   @Override
   public boolean hasArray() {
      return true;
   }

   @Override
   public long _getLong(int var1) {
      return (this.array[var1] & 255L) << 56
         | (this.array[var1 + 1] & 255L) << 48
         | (this.array[var1 + 2] & 255L) << 40
         | (this.array[var1 + 3] & 255L) << 32
         | (this.array[var1 + 4] & 255L) << 24
         | (this.array[var1 + 5] & 255L) << 16
         | (this.array[var1 + 6] & 255L) << 8
         | this.array[var1 + 7] & 255L;
   }

   @Override
   public ByteBuf unwrap() {
      return null;
   }

   @Override
   public short _getShort(int var1) {
      return (short)(this.array[var1] << 8 | this.array[var1 + 1] & 255);
   }

   @Override
   public int getInt(int var1) {
      this.ensureAccessible();
      return this._getInt(var1);
   }

   @Override
   public byte _getByte(int var1) {
      return this.array[var1];
   }

   @Override
   public ByteBuf setInt(int var1, int var2) {
      this.ensureAccessible();
      this._setInt(var1, var2);
      return this;
   }

   @Override
   public byte[] array() {
      this.ensureAccessible();
      return this.array;
   }

   @Override
   public ByteBuf getBytes(int var1, OutputStream var2, int var3) throws java.io.IOException {
      this.ensureAccessible();
      var2.write(this.array, var1, var3);
      return this;
   }

   @Override
   public ByteBufAllocator alloc() {
      return this.alloc;
   }

   @Override
   public int _getInt(int var1) {
      return (this.array[var1] & 0xFF) << 24 | (this.array[var1 + 1] & 0xFF) << 16 | (this.array[var1 + 2] & 0xFF) << 8 | this.array[var1 + 3] & 0xFF;
   }

   public int getBytes(int var1, GatheringByteChannel var2, int var3, boolean var4) throws java.io.IOException {
      this.ensureAccessible();
      ByteBuffer var5;
      if (var4) {
         var5 = this.internalNioBuffer();
      } else {
         var5 = ByteBuffer.wrap(this.array);
      }

      return var2.write((ByteBuffer)((Buffer)var5).clear().position(var1).limit(var1 + var3));
   }

   @Override
   public ByteBuf setMedium(int var1, int var2) {
      this.ensureAccessible();
      this._setMedium(var1, var2);
      return this;
   }

   @Override
   public ByteBuffer[] nioBuffers(int var1, int var2) {
      return new ByteBuffer[]{this.nioBuffer(var1, var2)};
   }

   @Override
   public void _setInt(int var1, int var2) {
      this.array[var1] = (byte)(var2 >>> 24);
      this.array[var1 + 1] = (byte)(var2 >>> 16);
      this.array[var1 + 2] = (byte)(var2 >>> 8);
      this.array[var1 + 3] = (byte)var2;
   }

   @Override
   public int setBytes(int var1, ScatteringByteChannel var2, int var3) throws java.io.IOException {
      this.ensureAccessible();

      try {
         return var2.read((ByteBuffer)((Buffer)this.internalNioBuffer()).clear().position(var1).limit(var1 + var3));
      } catch (ClosedChannelException var5) {
         return -1;
      }
   }

   @Override
   public void _setByte(int var1, int var2) {
      this.array[var1] = (byte)var2;
   }

   @Override
   public ByteBuf setBytes(int var1, byte[] var2, int var3, int var4) {
      this.checkSrcIndex(var1, var4, var3, var2.length);
      System.arraycopy(var2, var3, this.array, var1, var4);
      return this;
   }

   @Override
   public ByteBuf getBytes(int var1, byte[] var2, int var3, int var4) {
      this.checkDstIndex(var1, var4, var3, var2.length);
      System.arraycopy(this.array, var1, var2, var3, var4);
      return this;
   }

   @Override
   public long memoryAddress() {
      throw new UnsupportedOperationException();
   }

   @Override
   public ByteBuffer internalNioBuffer(int var1, int var2) {
      this.checkIndex(var1, var2);
      return (ByteBuffer)((Buffer)this.internalNioBuffer()).clear().position(var1).limit(var1 + var2);
   }

   @Override
   public int capacity() {
      this.ensureAccessible();
      return this.array.length;
   }

   @Override
   public ByteBuffer nioBuffer(int var1, int var2) {
      this.ensureAccessible();
      return ByteBuffer.wrap(this.array, var1, var2).slice();
   }

   @Override
   public void _setMedium(int var1, int var2) {
      this.array[var1] = (byte)(var2 >>> 16);
      this.array[var1 + 1] = (byte)(var2 >>> 8);
      this.array[var1 + 2] = (byte)var2;
   }

   @Override
   public ByteBuf setByte(int var1, int var2) {
      this.ensureAccessible();
      this._setByte(var1, var2);
      return this;
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuffer var2) {
      this.ensureAccessible();
      var2.put(this.array, var1, Math.min(this.capacity() - var1, var2.remaining()));
      return this;
   }

   @Override
   public long getLong(int var1) {
      this.ensureAccessible();
      return this._getLong(var1);
   }
}
