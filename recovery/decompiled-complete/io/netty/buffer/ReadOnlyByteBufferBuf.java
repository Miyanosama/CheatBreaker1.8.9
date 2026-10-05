package io.netty.buffer;

import com.cheatbreaker.client.util.dash.DashUtil;
import io.netty.util.internal.StringUtil;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ReadOnlyBufferException;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;

public class ReadOnlyByteBufferBuf extends AbstractReferenceCountedByteBuf {
   public DashUtil __junk6626755080356351108;
   public ByteBufAllocator allocator;
   public ByteBuffer buffer;
   public ByteBuffer tmpNioBuf;

   public ReadOnlyByteBufferBuf(ByteBufAllocator var1, ByteBuffer var2) {
      super(var2.remaining());
      if (!var2.isReadOnly()) {
         throw new IllegalArgumentException("must be a readonly buffer: " + StringUtil.simpleClassName(var2));
      } else {
         this.allocator = var1;
         this.buffer = var2.slice().order(ByteOrder.BIG_ENDIAN);
         this.writerIndex(this.buffer.limit());
      }
   }

   @Override
   public boolean hasArray() {
      return this.buffer.hasArray();
   }

   @Override
   public int setBytes(int var1, InputStream var2, int var3) {
      throw new ReadOnlyBufferException();
   }

   @Override
   public int capacity() {
      return this.maxCapacity();
   }

   @Override
   public ByteOrder order() {
      return ByteOrder.BIG_ENDIAN;
   }

   @Override
   public void _setInt(int var1, int var2) {
      throw new ReadOnlyBufferException();
   }

   @Override
   public ByteBuf unwrap() {
      return null;
   }

   @Override
   public int _getUnsignedMedium(int var1) {
      return (this.getByte(var1) & 0xFF) << 16 | (this.getByte(var1 + 1) & 0xFF) << 8 | this.getByte(var1 + 2) & 0xFF;
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2, int var3, int var4) {
      throw new ReadOnlyBufferException();
   }

   @Override
   public ByteBuf getBytes(int var1, byte[] var2, int var3, int var4) {
      this.checkDstIndex(var1, var4, var3, var2.length);
      if (var3 >= 0 && var3 <= var2.length - var4) {
         ByteBuffer var5 = this.internalNioBuffer();
         ((Buffer)var5).clear().position(var1).limit(var1 + var4);
         var5.get(var2, var3, var4);
         return this;
      } else {
         throw new IndexOutOfBoundsException(String.format("dstIndex: %d, length: %d (expected: range(0, %d))", var3, var4, var2.length));
      }
   }

   @Override
   public void _setByte(int var1, int var2) {
      throw new ReadOnlyBufferException();
   }

   public ByteBuffer internalNioBuffer() {
      ByteBuffer var1 = this.tmpNioBuf;
      if (var1 == null) {
         this.tmpNioBuf = var1 = this.buffer.duplicate();
      }

      return var1;
   }

   @Override
   public byte getByte(int var1) {
      this.ensureAccessible();
      return this._getByte(var1);
   }

   @Override
   public byte _getByte(int var1) {
      return this.buffer.get(var1);
   }

   @Override
   public void _setMedium(int var1, int var2) {
      throw new ReadOnlyBufferException();
   }

   @Override
   public long _getLong(int var1) {
      return this.buffer.getLong(var1);
   }

   @Override
   public long memoryAddress() {
      throw new UnsupportedOperationException();
   }

   @Override
   public short getShort(int var1) {
      this.ensureAccessible();
      return this._getShort(var1);
   }

   @Override
   public short _getShort(int var1) {
      return this.buffer.getShort(var1);
   }

   @Override
   public boolean hasMemoryAddress() {
      return false;
   }

   @Override
   public ByteBufAllocator alloc() {
      return this.allocator;
   }

   @Override
   public ByteBuf setBytes(int var1, byte[] var2, int var3, int var4) {
      throw new ReadOnlyBufferException();
   }

   @Override
   public int getBytes(int var1, GatheringByteChannel var2, int var3) {
      this.ensureAccessible();
      if (var3 == 0) {
         return 0;
      } else {
         ByteBuffer var4 = this.internalNioBuffer();
         ((Buffer)var4).clear().position(var1).limit(var1 + var3);
         return var2.write(var4);
      }
   }

   @Override
   public ByteBuf copy(int var1, int var2) {
      this.ensureAccessible();

      ByteBuffer var3;
      try {
         var3 = (ByteBuffer)((Buffer)this.internalNioBuffer()).clear().position(var1).limit(var1 + var2);
      } catch (IllegalArgumentException var5) {
         throw new IndexOutOfBoundsException("Too many bytes to read - Need " + (var1 + var2));
      }

      ByteBuffer var4 = ByteBuffer.allocateDirect(var2);
      var4.put(var3);
      var4.order(this.order());
      ((Buffer)var4).clear();
      return new UnpooledDirectByteBuf(this.alloc(), var4, this.maxCapacity());
   }

   @Override
   public int getUnsignedMedium(int var1) {
      this.ensureAccessible();
      return this._getUnsignedMedium(var1);
   }

   @Override
   public int arrayOffset() {
      return this.buffer.arrayOffset();
   }

   @Override
   public ByteBuffer[] nioBuffers(int var1, int var2) {
      return new ByteBuffer[]{this.nioBuffer(var1, var2)};
   }

   @Override
   public int _getInt(int var1) {
      return this.buffer.getInt(var1);
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuffer var2) {
      this.checkIndex(var1);
      if (var2 == null) {
         throw new NullPointerException("dst");
      } else {
         int var3 = Math.min(this.capacity() - var1, var2.remaining());
         ByteBuffer var4 = this.internalNioBuffer();
         ((Buffer)var4).clear().position(var1).limit(var1 + var3);
         var2.put(var4);
         return this;
      }
   }

   @Override
   public void _setShort(int var1, int var2) {
      throw new ReadOnlyBufferException();
   }

   @Override
   public boolean isDirect() {
      return this.buffer.isDirect();
   }

   @Override
   public int nioBufferCount() {
      return 1;
   }

   @Override
   public int setBytes(int var1, ScatteringByteChannel var2, int var3) {
      throw new ReadOnlyBufferException();
   }

   @Override
   public ByteBuf getBytes(int var1, OutputStream var2, int var3) {
      this.ensureAccessible();
      if (var3 == 0) {
         return this;
      } else {
         if (this.buffer.hasArray()) {
            var2.write(this.buffer.array(), var1 + this.buffer.arrayOffset(), var3);
         } else {
            byte[] var4 = new byte[var3];
            ByteBuffer var5 = this.internalNioBuffer();
            ((Buffer)var5).clear().position(var1);
            var5.get(var4);
            var2.write(var4);
         }

         return this;
      }
   }

   @Override
   public ByteBuffer nioBuffer(int var1, int var2) {
      return (ByteBuffer)((Buffer)this.buffer.duplicate()).position(var1).limit(var1 + var2);
   }

   @Override
   public void _setLong(int var1, long var2) {
      throw new ReadOnlyBufferException();
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
   public byte[] array() {
      return this.buffer.array();
   }

   @Override
   public ByteBuf capacity(int var1) {
      throw new ReadOnlyBufferException();
   }

   @Override
   public ByteBuffer internalNioBuffer(int var1, int var2) {
      this.ensureAccessible();
      return (ByteBuffer)((Buffer)this.internalNioBuffer()).clear().position(var1).limit(var1 + var2);
   }

   @Override
   public int getInt(int var1) {
      this.ensureAccessible();
      return this._getInt(var1);
   }

   @Override
   public long getLong(int var1) {
      this.ensureAccessible();
      return this._getLong(var1);
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuffer var2) {
      throw new ReadOnlyBufferException();
   }

   @Override
   public void deallocate() {
   }
}
