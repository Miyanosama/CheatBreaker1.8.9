package io.netty.buffer;

import io.netty.handler.codec.spdy.DefaultSpdyDataFrame;
import io.netty.util.internal.PlatformDependent;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import junit.swingui.TestSuitePanel$1;
import org.java_websocket.enums.Role;

public class UnpooledDirectByteBuf extends AbstractReferenceCountedByteBuf {
   public TestSuitePanel$1 __junk6645492432386611824;
   public DefaultSpdyDataFrame __junk4662000111448668427;
   public int capacity;
   public ByteBuffer tmpNioBuf;
   public ByteBufAllocator alloc;
   public Role __junk7634363458693224088;
   public boolean doNotFree;
   public ByteBuffer buffer;

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2, int var3, int var4) {
      this.checkSrcIndex(var1, var4, var3, var2.capacity());
      if (var2.nioBufferCount() > 0) {
         for (ByteBuffer var8 : var2.nioBuffers(var3, var4)) {
            int var9 = var8.remaining();
            this.setBytes(var1, var8);
            var1 += var9;
         }
      } else {
         var2.getBytes(var3, this, var1, var4);
      }

      return this;
   }

   public void getBytes(int var1, ByteBuffer var2, boolean var3) {
      this.checkIndex(var1);
      if (var2 == null) {
         throw new NullPointerException("dst");
      } else {
         int var4 = Math.min(this.capacity() - var1, var2.remaining());
         ByteBuffer var5;
         if (var3) {
            var5 = this.internalNioBuffer();
         } else {
            var5 = this.buffer.duplicate();
         }

         ((Buffer)var5).clear().position(var1).limit(var1 + var4);
         var2.put(var5);
      }
   }

   @Override
   public int getInt(int var1) {
      this.ensureAccessible();
      return this._getInt(var1);
   }

   @Override
   public ByteBuf setShort(int var1, int var2) {
      this.ensureAccessible();
      this._setShort(var1, var2);
      return this;
   }

   public void getBytes(int var1, OutputStream var2, int var3, boolean var4) {
      this.ensureAccessible();
      if (var3 != 0) {
         if (this.buffer.hasArray()) {
            var2.write(this.buffer.array(), var1 + this.buffer.arrayOffset(), var3);
         } else {
            byte[] var5 = new byte[var3];
            ByteBuffer var6;
            if (var4) {
               var6 = this.internalNioBuffer();
            } else {
               var6 = this.buffer.duplicate();
            }

            ((Buffer)var6).clear().position(var1);
            var6.get(var5);
            var2.write(var5);
         }
      }
   }

   @Override
   public boolean hasMemoryAddress() {
      return false;
   }

   @Override
   public void _setLong(int var1, long var2) {
      this.buffer.putLong(var1, var2);
   }

   @Override
   public boolean isDirect() {
      return true;
   }

   @Override
   public void _setShort(int var1, int var2) {
      this.buffer.putShort(var1, (short)var2);
   }

   @Override
   public void _setMedium(int var1, int var2) {
      this.setByte(var1, (byte)(var2 >>> 16));
      this.setByte(var1 + 1, (byte)(var2 >>> 8));
      this.setByte(var1 + 2, (byte)var2);
   }

   @Override
   public ByteOrder order() {
      return ByteOrder.BIG_ENDIAN;
   }

   @Override
   public ByteBuf setBytes(int var1, byte[] var2, int var3, int var4) {
      this.checkSrcIndex(var1, var4, var3, var2.length);
      ByteBuffer var5 = this.internalNioBuffer();
      ((Buffer)var5).clear().position(var1).limit(var1 + var4);
      var5.put(var2, var3, var4);
      return this;
   }

   @Override
   public long _getLong(int var1) {
      return this.buffer.getLong(var1);
   }

   @Override
   public ByteBuf setInt(int var1, int var2) {
      this.ensureAccessible();
      this._setInt(var1, var2);
      return this;
   }

   @Override
   public int capacity() {
      return this.capacity;
   }

   @Override
   public int setBytes(int var1, ScatteringByteChannel var2, int var3) {
      this.ensureAccessible();
      ByteBuffer var4 = this.internalNioBuffer();
      ((Buffer)var4).clear().position(var1).limit(var1 + var3);

      try {
         return var2.read(this.tmpNioBuf);
      } catch (ClosedChannelException var6) {
         return -1;
      }
   }

   @Override
   public int arrayOffset() {
      throw new UnsupportedOperationException("direct buffer");
   }

   @Override
   public int nioBufferCount() {
      return 1;
   }

   @Override
   public short _getShort(int var1) {
      return this.buffer.getShort(var1);
   }

   public void getBytes(int var1, byte[] var2, int var3, int var4, boolean var5) {
      this.checkDstIndex(var1, var4, var3, var2.length);
      if (var3 >= 0 && var3 <= var2.length - var4) {
         ByteBuffer var6;
         if (var5) {
            var6 = this.internalNioBuffer();
         } else {
            var6 = this.buffer.duplicate();
         }

         ((Buffer)var6).clear().position(var1).limit(var1 + var4);
         var6.get(var2, var3, var4);
      } else {
         throw new IndexOutOfBoundsException(String.format("dstIndex: %d, length: %d (expected: range(0, %d))", var3, var4, var2.length));
      }
   }

   @Override
   public ByteBuf getBytes(int var1, OutputStream var2, int var3) {
      this.getBytes(var1, var2, var3, false);
      return this;
   }

   @Override
   public ByteBuffer[] nioBuffers(int var1, int var2) {
      return new ByteBuffer[]{this.nioBuffer(var1, var2)};
   }

   @Override
   public ByteBuf copy(int var1, int var2) {
      this.ensureAccessible();

      ByteBuffer var3;
      try {
         var3 = (ByteBuffer)((Buffer)this.buffer.duplicate()).clear().position(var1).limit(var1 + var2);
      } catch (IllegalArgumentException var5) {
         throw new IndexOutOfBoundsException("Too many bytes to read - Need " + (var1 + var2));
      }

      return this.alloc().directBuffer(var2, this.maxCapacity()).writeBytes(var3);
   }

   @Override
   public int getUnsignedMedium(int var1) {
      this.ensureAccessible();
      return this._getUnsignedMedium(var1);
   }

   @Override
   public ByteBuffer internalNioBuffer(int var1, int var2) {
      this.checkIndex(var1, var2);
      return (ByteBuffer)((Buffer)this.internalNioBuffer()).clear().position(var1).limit(var1 + var2);
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuffer var2) {
      this.getBytes(var1, var2, false);
      return this;
   }

   @Override
   public byte getByte(int var1) {
      this.ensureAccessible();
      return this._getByte(var1);
   }

   @Override
   public long memoryAddress() {
      throw new UnsupportedOperationException();
   }

   @Override
   public byte[] array() {
      throw new UnsupportedOperationException("direct buffer");
   }

   @Override
   public ByteBuf readBytes(OutputStream var1, int var2) {
      this.checkReadableBytes(var2);
      this.getBytes(this.readerIndex, var1, var2, true);
      this.readerIndex += var2;
      return this;
   }

   public UnpooledDirectByteBuf(ByteBufAllocator var1, int var2, int var3) {
      super(var3);
      if (var1 == null) {
         throw new NullPointerException("alloc");
      } else if (var2 < 0) {
         throw new IllegalArgumentException("initialCapacity: " + var2);
      } else if (var3 < 0) {
         throw new IllegalArgumentException("maxCapacity: " + var3);
      } else if (var2 > var3) {
         throw new IllegalArgumentException(String.format("initialCapacity(%d) > maxCapacity(%d)", var2, var3));
      } else {
         this.alloc = var1;
         this.setByteBuffer(ByteBuffer.allocateDirect(var2));
      }
   }

   @Override
   public int _getUnsignedMedium(int var1) {
      return (this.getByte(var1) & 0xFF) << 16 | (this.getByte(var1 + 1) & 0xFF) << 8 | this.getByte(var1 + 2) & 0xFF;
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuffer var2) {
      this.ensureAccessible();
      ByteBuffer var3 = this.internalNioBuffer();
      if (var2 == var3) {
         var2 = var2.duplicate();
      }

      ((Buffer)var3).clear().position(var1).limit(var1 + var2.remaining());
      var3.put(var2);
      return this;
   }

   @Override
   public long getLong(int var1) {
      this.ensureAccessible();
      return this._getLong(var1);
   }

   public ByteBuffer internalNioBuffer() {
      ByteBuffer var1 = this.tmpNioBuf;
      if (var1 == null) {
         this.tmpNioBuf = var1 = this.buffer.duplicate();
      }

      return var1;
   }

   public ByteBuffer allocateDirect(int var1) {
      return ByteBuffer.allocateDirect(var1);
   }

   public int getBytes(int var1, GatheringByteChannel var2, int var3, boolean var4) {
      this.ensureAccessible();
      if (var3 == 0) {
         return 0;
      } else {
         ByteBuffer var5;
         if (var4) {
            var5 = this.internalNioBuffer();
         } else {
            var5 = this.buffer.duplicate();
         }

         ((Buffer)var5).clear().position(var1).limit(var1 + var3);
         return var2.write(var5);
      }
   }

   @Override
   public ByteBuf capacity(int var1) {
      this.ensureAccessible();
      if (var1 >= 0 && var1 <= this.maxCapacity()) {
         int var2 = this.readerIndex();
         int var3 = this.writerIndex();
         int var4 = this.capacity;
         if (var1 > var4) {
            ByteBuffer var5 = this.buffer;
            ByteBuffer var6 = this.allocateDirect(var1);
            ((Buffer)var5).position(0).limit(var5.capacity());
            ((Buffer)var6).position(0).limit(var5.capacity());
            var6.put(var5);
            ((Buffer)var6).clear();
            this.setByteBuffer(var6);
         } else if (var1 < var4) {
            ByteBuffer var7 = this.buffer;
            ByteBuffer var8 = this.allocateDirect(var1);
            if (var2 < var1) {
               if (var3 > var1) {
                  var3 = var1;
                  this.writerIndex(var1);
               }

               ((Buffer)var7).position(var2).limit(var3);
               ((Buffer)var8).position(var2).limit(var3);
               var8.put(var7);
               ((Buffer)var8).clear();
            } else {
               this.setIndex(var1, var1);
            }

            this.setByteBuffer(var8);
         }

         return this;
      } else {
         throw new IllegalArgumentException("newCapacity: " + var1);
      }
   }

   @Override
   public ByteBuffer nioBuffer(int var1, int var2) {
      this.checkIndex(var1, var2);
      return ((ByteBuffer)((Buffer)this.buffer.duplicate()).position(var1).limit(var1 + var2)).slice();
   }

   @Override
   public ByteBuf setMedium(int var1, int var2) {
      this.ensureAccessible();
      this._setMedium(var1, var2);
      return this;
   }

   @Override
   public ByteBuf setByte(int var1, int var2) {
      this.ensureAccessible();
      this._setByte(var1, var2);
      return this;
   }

   @Override
   public short getShort(int var1) {
      this.ensureAccessible();
      return this._getShort(var1);
   }

   @Override
   public void _setInt(int var1, int var2) {
      this.buffer.putInt(var1, var2);
   }

   @Override
   public void _setByte(int var1, int var2) {
      this.buffer.put(var1, (byte)var2);
   }

   @Override
   public byte _getByte(int var1) {
      return this.buffer.get(var1);
   }

   @Override
   public ByteBufAllocator alloc() {
      return this.alloc;
   }

   public void freeDirect(ByteBuffer var1) {
      PlatformDependent.freeDirectBuffer(var1);
   }

   @Override
   public ByteBuf getBytes(int var1, byte[] var2, int var3, int var4) {
      this.getBytes(var1, var2, var3, var4, false);
      return this;
   }

   @Override
   public ByteBuf readBytes(byte[] var1, int var2, int var3) {
      this.checkReadableBytes(var3);
      this.getBytes(this.readerIndex, var1, var2, var3, true);
      this.readerIndex += var3;
      return this;
   }

   @Override
   public int setBytes(int var1, InputStream var2, int var3) {
      this.ensureAccessible();
      if (this.buffer.hasArray()) {
         return var2.read(this.buffer.array(), this.buffer.arrayOffset() + var1, var3);
      } else {
         byte[] var4 = new byte[var3];
         int var5 = var2.read(var4);
         if (var5 <= 0) {
            return var5;
         } else {
            ByteBuffer var6 = this.internalNioBuffer();
            ((Buffer)var6).clear().position(var1);
            var6.put(var4, 0, var5);
            return var5;
         }
      }
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuf var2, int var3, int var4) {
      this.checkDstIndex(var1, var4, var3, var2.capacity());
      if (var2.hasArray()) {
         this.getBytes(var1, var2.array(), var2.arrayOffset() + var3, var4);
      } else if (var2.nioBufferCount() > 0) {
         for (ByteBuffer var8 : var2.nioBuffers(var3, var4)) {
            int var9 = var8.remaining();
            this.getBytes(var1, var8);
            var1 += var9;
         }
      } else {
         var2.setBytes(var3, this, var1, var4);
      }

      return this;
   }

   @Override
   public ByteBuf unwrap() {
      return null;
   }

   public UnpooledDirectByteBuf(ByteBufAllocator var1, ByteBuffer var2, int var3) {
      super(var3);
      if (var1 == null) {
         throw new NullPointerException("alloc");
      } else if (var2 == null) {
         throw new NullPointerException("initialBuffer");
      } else if (!var2.isDirect()) {
         throw new IllegalArgumentException("initialBuffer is not a direct buffer.");
      } else if (var2.isReadOnly()) {
         throw new IllegalArgumentException("initialBuffer is a read-only buffer.");
      } else {
         int var4 = var2.remaining();
         if (var4 > var3) {
            throw new IllegalArgumentException(String.format("initialCapacity(%d) > maxCapacity(%d)", var4, var3));
         } else {
            this.alloc = var1;
            this.doNotFree = true;
            this.setByteBuffer(var2.slice().order(ByteOrder.BIG_ENDIAN));
            this.writerIndex(var4);
         }
      }
   }

   @Override
   public void deallocate() {
      ByteBuffer var1 = this.buffer;
      if (var1 != null) {
         this.buffer = null;
         if (!this.doNotFree) {
            this.freeDirect(var1);
         }
      }
   }

   @Override
   public int _getInt(int var1) {
      return this.buffer.getInt(var1);
   }

   @Override
   public int getBytes(int var1, GatheringByteChannel var2, int var3) {
      return this.getBytes(var1, var2, var3, false);
   }

   @Override
   public ByteBuf setLong(int var1, long var2) {
      this.ensureAccessible();
      this._setLong(var1, var2);
      return this;
   }

   @Override
   public int readBytes(GatheringByteChannel var1, int var2) {
      this.checkReadableBytes(var2);
      int var3 = this.getBytes(this.readerIndex, var1, var2, true);
      this.readerIndex += var3;
      return var3;
   }

   @Override
   public boolean hasArray() {
      return false;
   }

   public void setByteBuffer(ByteBuffer var1) {
      ByteBuffer var2 = this.buffer;
      if (var2 != null) {
         if (this.doNotFree) {
            this.doNotFree = false;
         } else {
            this.freeDirect(var2);
         }
      }

      this.buffer = var1;
      this.tmpNioBuf = null;
      this.capacity = var1.remaining();
   }

   @Override
   public ByteBuf readBytes(ByteBuffer var1) {
      int var2 = var1.remaining();
      this.checkReadableBytes(var2);
      this.getBytes(this.readerIndex, var1, true);
      this.readerIndex += var2;
      return this;
   }
}
