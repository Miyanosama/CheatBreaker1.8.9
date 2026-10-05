package io.netty.buffer;

import io.netty.channel.epoll.AbstractEpollChannel$1;
import io.netty.util.internal.PlatformDependent;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import net.optifine.entity.model.ModelAdapterGuardian;
import recovered.unidentified.UnidentifiedClass0943;

public class UnpooledUnsafeDirectByteBuf extends AbstractReferenceCountedByteBuf {
   public long memoryAddress;
   public UnidentifiedClass0943 __junk4436834955072135166;
   public ByteBufAllocator alloc;
   public AbstractEpollChannel$1 __junk7245817012267004303;
   public int capacity;
   public static boolean NATIVE_ORDER = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
   public ModelAdapterGuardian __junk8728351959159907923;
   public ByteBuffer tmpNioBuf;
   public ByteBuffer buffer;
   public ByteBufUtil$ThreadLocalUnsafeDirectByteBuf __junk79741394801509326;
   public boolean doNotFree;

   @Override
   public long memoryAddress() {
      return this.memoryAddress;
   }

   @Override
   public void _setShort(int var1, int var2) {
      PlatformDependent.putShort(this.addr(var1), NATIVE_ORDER ? (short)var2 : Short.reverseBytes((short)var2));
   }

   @Override
   public boolean hasArray() {
      return false;
   }

   @Override
   public int readBytes(GatheringByteChannel var1, int var2) {
      this.checkReadableBytes(var2);
      int var3 = this.getBytes(this.readerIndex, var1, var2, true);
      this.readerIndex += var3;
      return var3;
   }

   @Override
   public short _getShort(int var1) {
      short var2 = PlatformDependent.getShort(this.addr(var1));
      return NATIVE_ORDER ? var2 : Short.reverseBytes(var2);
   }

   @Override
   public ByteBuf copy(int var1, int var2) {
      this.checkIndex(var1, var2);
      ByteBuf var3 = this.alloc().directBuffer(var2, this.maxCapacity());
      if (var2 != 0) {
         if (var3.hasMemoryAddress()) {
            PlatformDependent.copyMemory(this.addr(var1), var3.memoryAddress(), var2);
            var3.setIndex(0, var2);
         } else {
            var3.writeBytes(this, var1, var2);
         }
      }

      return var3;
   }

   @Override
   public int setBytes(int var1, InputStream var2, int var3) {
      this.checkIndex(var1, var3);
      byte[] var4 = new byte[var3];
      int var5 = var2.read(var4);
      if (var5 > 0) {
         PlatformDependent.copyMemory(var4, 0, this.addr(var1), var5);
      }

      return var5;
   }

   @Override
   public SwappedByteBuf newSwappedByteBuf() {
      return new UnsafeDirectSwappedByteBuf(this);
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
   public ByteBuffer[] nioBuffers(int var1, int var2) {
      return new ByteBuffer[]{this.nioBuffer(var1, var2)};
   }

   @Override
   public ByteBuf readBytes(ByteBuffer var1) {
      int var2 = var1.remaining();
      this.checkReadableBytes(var2);
      this.getBytes(this.readerIndex, var1, true);
      this.readerIndex += var2;
      return this;
   }

   @Override
   public int getBytes(int var1, GatheringByteChannel var2, int var3) {
      return this.getBytes(var1, var2, var3, false);
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuffer var2) {
      this.getBytes(var1, var2, false);
      return this;
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
      this.memoryAddress = PlatformDependent.directBufferAddress(var1);
      this.tmpNioBuf = null;
      this.capacity = var1.remaining();
   }

   public UnpooledUnsafeDirectByteBuf(ByteBufAllocator var1, int var2, int var3) {
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
         this.setByteBuffer(this.allocateDirect(var2));
      }
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2, int var3, int var4) {
      this.checkIndex(var1, var4);
      if (var2 == null) {
         throw new NullPointerException("src");
      } else if (var3 >= 0 && var3 <= var2.capacity() - var4) {
         if (var4 != 0) {
            if (var2.hasMemoryAddress()) {
               PlatformDependent.copyMemory(var2.memoryAddress() + var3, this.addr(var1), var4);
            } else if (var2.hasArray()) {
               PlatformDependent.copyMemory(var2.array(), var2.arrayOffset() + var3, this.addr(var1), var4);
            } else {
               var2.getBytes(var3, this, var1, var4);
            }
         }

         return this;
      } else {
         throw new IndexOutOfBoundsException("srcIndex: " + var3);
      }
   }

   public UnpooledUnsafeDirectByteBuf(ByteBufAllocator var1, ByteBuffer var2, int var3) {
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
   public ByteBuf getBytes(int var1, ByteBuf var2, int var3, int var4) {
      this.checkIndex(var1, var4);
      if (var2 == null) {
         throw new NullPointerException("dst");
      } else if (var3 >= 0 && var3 <= var2.capacity() - var4) {
         if (var2.hasMemoryAddress()) {
            PlatformDependent.copyMemory(this.addr(var1), var2.memoryAddress() + var3, var4);
         } else if (var2.hasArray()) {
            PlatformDependent.copyMemory(this.addr(var1), var2.array(), var2.arrayOffset() + var3, var4);
         } else {
            var2.setBytes(var3, this, var1, var4);
         }

         return this;
      } else {
         throw new IndexOutOfBoundsException("dstIndex: " + var3);
      }
   }

   @Override
   public long _getLong(int var1) {
      long var2 = PlatformDependent.getLong(this.addr(var1));
      return NATIVE_ORDER ? var2 : Long.reverseBytes(var2);
   }

   @Override
   public ByteBuffer internalNioBuffer(int var1, int var2) {
      this.checkIndex(var1, var2);
      return (ByteBuffer)((Buffer)this.internalNioBuffer()).clear().position(var1).limit(var1 + var2);
   }

   @Override
   public boolean hasMemoryAddress() {
      return true;
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
   public void _setLong(int var1, long var2) {
      PlatformDependent.putLong(this.addr(var1), NATIVE_ORDER ? var2 : Long.reverseBytes(var2));
   }

   @Override
   public int setBytes(int var1, ScatteringByteChannel var2, int var3) {
      this.ensureAccessible();
      ByteBuffer var4 = this.internalNioBuffer();
      ((Buffer)var4).clear().position(var1).limit(var1 + var3);

      try {
         return var2.read(var4);
      } catch (ClosedChannelException var6) {
         return -1;
      }
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

   @Override
   public int _getUnsignedMedium(int var1) {
      long var2 = this.addr(var1);
      return (PlatformDependent.getByte(var2) & 0xFF) << 16
         | (PlatformDependent.getByte(var2 + (-2606272725508653037L & 2606272724850527557L)) & 0xFF) << 8
         | PlatformDependent.getByte(var2 + (5656231084246209610L & -5656231085973880045L)) & 0xFF;
   }

   @Override
   public ByteBuf unwrap() {
      return null;
   }

   @Override
   public int nioBufferCount() {
      return 1;
   }

   @Override
   public void _setInt(int var1, int var2) {
      PlatformDependent.putInt(this.addr(var1), NATIVE_ORDER ? var2 : Integer.reverseBytes(var2));
   }

   @Override
   public ByteBuffer nioBuffer(int var1, int var2) {
      this.checkIndex(var1, var2);
      return ((ByteBuffer)((Buffer)this.buffer.duplicate()).position(var1).limit(var1 + var2)).slice();
   }

   public void freeDirect(ByteBuffer var1) {
      PlatformDependent.freeDirectBuffer(var1);
   }

   @Override
   public int capacity() {
      return this.capacity;
   }

   @Override
   public byte[] array() {
      throw new UnsupportedOperationException("direct buffer");
   }

   @Override
   public void _setByte(int var1, int var2) {
      PlatformDependent.putByte(this.addr(var1), (byte)var2);
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
   public int arrayOffset() {
      throw new UnsupportedOperationException("direct buffer");
   }

   @Override
   public void _setMedium(int var1, int var2) {
      long var3 = this.addr(var1);
      PlatformDependent.putByte(var3, (byte)(var2 >>> 16));
      PlatformDependent.putByte(var3 + (537395201L & -6302221676967819511L), (byte)(var2 >>> 8));
      PlatformDependent.putByte(var3 + (7512838L & 134484035L), (byte)var2);
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
   public ByteBuf setBytes(int var1, byte[] var2, int var3, int var4) {
      this.checkIndex(var1, var4);
      if (var4 != 0) {
         PlatformDependent.copyMemory(var2, var3, this.addr(var1), var4);
      }

      return this;
   }

   @Override
   public ByteBuf getBytes(int var1, byte[] var2, int var3, int var4) {
      this.checkIndex(var1, var4);
      if (var2 == null) {
         throw new NullPointerException("dst");
      } else if (var3 >= 0 && var3 <= var2.length - var4) {
         if (var4 != 0) {
            PlatformDependent.copyMemory(this.addr(var1), var2, var3, var4);
         }

         return this;
      } else {
         throw new IndexOutOfBoundsException(String.format("dstIndex: %d, length: %d (expected: range(0, %d))", var3, var4, var2.length));
      }
   }

   @Override
   public boolean isDirect() {
      return true;
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

   public long addr(int var1) {
      return this.memoryAddress + var1;
   }

   @Override
   public ByteBufAllocator alloc() {
      return this.alloc;
   }

   @Override
   public ByteOrder order() {
      return ByteOrder.BIG_ENDIAN;
   }

   @Override
   public int _getInt(int var1) {
      int var2 = PlatformDependent.getInt(this.addr(var1));
      return NATIVE_ORDER ? var2 : Integer.reverseBytes(var2);
   }

   @Override
   public byte _getByte(int var1) {
      return PlatformDependent.getByte(this.addr(var1));
   }

   @Override
   public ByteBuf getBytes(int var1, OutputStream var2, int var3) {
      this.ensureAccessible();
      if (var3 != 0) {
         byte[] var4 = new byte[var3];
         PlatformDependent.copyMemory(this.addr(var1), var4, 0, var3);
         var2.write(var4);
      }

      return this;
   }
}
