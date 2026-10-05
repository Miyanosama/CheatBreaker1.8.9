package io.netty.buffer;

import com.cheatbreaker.client.ui.resourcepack.ResourcePackGui;
import io.netty.handler.stream.ChunkedStream;
import io.netty.util.Recycler;
import io.netty.util.internal.PlatformDependent;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.command.WrongUsageException;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.ContainerHorseInventory;
import net.optifine.ConnectedTexturesCompact;
import net.optifine.entity.model.ModelAdapterCow;
import net.optifine.shaders.ShaderProgramData;
import org.apache.log4j.helpers.FileWatchdog;

public class PooledUnsafeDirectByteBuf extends PooledByteBuf<ByteBuffer> {
   public long memoryAddress;
   public static boolean NATIVE_ORDER = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
   public static Recycler<PooledUnsafeDirectByteBuf> RECYCLER = new Recycler<PooledUnsafeDirectByteBuf>() {

      public PooledUnsafeDirectByteBuf newObject(Recycler.Handle var1) {
         return new PooledUnsafeDirectByteBuf(var1, 0);
      }
   };

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
   public ByteBuffer internalNioBuffer(int var1, int var2) {
      this.checkIndex(var1, var2);
      var1 = this.idx(var1);
      return (ByteBuffer)((Buffer)this.internalNioBuffer()).clear().position(var1).limit(var1 + var2);
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuffer var2) {
      this.getBytes(var1, var2, false);
      return this;
   }

   public ByteBuffer newInternalNioBuffer(ByteBuffer var1) {
      return var1.duplicate();
   }

   @Override
   public int getBytes(int var1, GatheringByteChannel var2, int var3) throws java.io.IOException {
      return this.getBytes(var1, var2, var3, false);
   }

   @Override
   public int _getUnsignedMedium(int var1) {
      long var2 = this.addr(var1);
      return (PlatformDependent.getByte(var2) & 0xFF) << 16 | (PlatformDependent.getByte(var2 + 1L) & 0xFF) << 8 | PlatformDependent.getByte(var2 + 2L) & 0xFF;
   }

   @Override
   public int _getInt(int var1) {
      int var2 = PlatformDependent.getInt(this.addr(var1));
      return NATIVE_ORDER ? var2 : Integer.reverseBytes(var2);
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
   public boolean isDirect() {
      return true;
   }

   @Override
   public int readBytes(GatheringByteChannel var1, int var2) throws java.io.IOException {
      this.checkReadableBytes(var2);
      int var3 = this.getBytes(this.readerIndex, var1, var2, true);
      this.readerIndex += var3;
      return var3;
   }

   @Override
   public long memoryAddress() {
      return this.memoryAddress;
   }

   @Override
   public void init(PoolChunk<ByteBuffer> var1, long var2, int var4, int var5, int var6) {
      super.init(var1, var2, var4, var5, var6);
      this.initMemoryAddress();
   }

   public long addr(int var1) {
      return this.memoryAddress + var1;
   }

   @Override
   public boolean hasArray() {
      return false;
   }

   @Override
   public boolean hasMemoryAddress() {
      return true;
   }

   @Override
   public void _setMedium(int var1, int var2) {
      long var3 = this.addr(var1);
      PlatformDependent.putByte(var3, (byte)(var2 >>> 16));
      PlatformDependent.putByte(var3 + 1L, (byte)(var2 >>> 8));
      PlatformDependent.putByte(var3 + 2L, (byte)var2);
   }

   @Override
   public Recycler<?> recycler() {
      return RECYCLER;
   }

   @Override
   public int nioBufferCount() {
      return 1;
   }

   public PooledUnsafeDirectByteBuf(Recycler.Handle var1, int var2) {
      super(var1, var2);
   }

   public void getBytes(int var1, ByteBuffer var2, boolean var3) {
      this.checkIndex(var1);
      int var4 = Math.min(this.capacity() - var1, var2.remaining());
      ByteBuffer var5;
      if (var3) {
         var5 = this.internalNioBuffer();
      } else {
         var5 = this.memory.duplicate();
      }

      var1 = this.idx(var1);
      ((Buffer)var5).clear().position(var1).limit(var1 + var4);
      var2.put(var5);
   }

   @Override
   public void _setLong(int var1, long var2) {
      PlatformDependent.putLong(this.addr(var1), NATIVE_ORDER ? var2 : Long.reverseBytes(var2));
   }

   @Override
   public void _setByte(int var1, int var2) {
      PlatformDependent.putByte(this.addr(var1), (byte)var2);
   }

   @Override
   public int setBytes(int var1, InputStream var2, int var3) throws java.io.IOException {
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
   public void initUnpooled(PoolChunk<ByteBuffer> var1, int var2) {
      super.initUnpooled(var1, var2);
      this.initMemoryAddress();
   }

   public static PooledUnsafeDirectByteBuf newInstance(int var0) {
      PooledUnsafeDirectByteBuf var1 = RECYCLER.get();
      var1.setRefCnt(1);
      var1.maxCapacity(var0);
      return var1;
   }

   @Override
   public int arrayOffset() {
      throw new UnsupportedOperationException("direct buffer");
   }

   @Override
   public byte _getByte(int var1) {
      return PlatformDependent.getByte(this.addr(var1));
   }

   @Override
   public byte[] array() {
      throw new UnsupportedOperationException("direct buffer");
   }

   @Override
   public void _setShort(int var1, int var2) {
      PlatformDependent.putShort(this.addr(var1), NATIVE_ORDER ? (short)var2 : Short.reverseBytes((short)var2));
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

   @Override
   public ByteBuffer nioBuffer(int var1, int var2) {
      this.checkIndex(var1, var2);
      var1 = this.idx(var1);
      return ((ByteBuffer)((Buffer)this.memory.duplicate()).position(var1).limit(var1 + var2)).slice();
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
   public ByteBuf getBytes(int var1, ByteBuf var2, int var3, int var4) {
      this.checkIndex(var1, var4);
      if (var2 == null) {
         throw new NullPointerException("dst");
      } else if (var3 >= 0 && var3 <= var2.capacity() - var4) {
         if (var4 != 0) {
            if (var2.hasMemoryAddress()) {
               PlatformDependent.copyMemory(this.addr(var1), var2.memoryAddress() + var3, var4);
            } else if (var2.hasArray()) {
               PlatformDependent.copyMemory(this.addr(var1), var2.array(), var2.arrayOffset() + var3, var4);
            } else {
               var2.setBytes(var3, this, var1, var4);
            }
         }

         return this;
      } else {
         throw new IndexOutOfBoundsException("dstIndex: " + var3);
      }
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
         throw new IndexOutOfBoundsException("dstIndex: " + var3);
      }
   }

   @Override
   public void _setInt(int var1, int var2) {
      PlatformDependent.putInt(this.addr(var1), NATIVE_ORDER ? var2 : Integer.reverseBytes(var2));
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuffer var2) {
      this.checkIndex(var1, var2.remaining());
      ByteBuffer var3 = this.internalNioBuffer();
      if (var2 == var3) {
         var2 = var2.duplicate();
      }

      var1 = this.idx(var1);
      ((Buffer)var3).clear().position(var1).limit(var1 + var2.remaining());
      var3.put(var2);
      return this;
   }

   @Override
   public long _getLong(int var1) {
      long var2 = PlatformDependent.getLong(this.addr(var1));
      return NATIVE_ORDER ? var2 : Long.reverseBytes(var2);
   }

   @Override
   public ByteBuf getBytes(int var1, OutputStream var2, int var3) throws java.io.IOException {
      this.checkIndex(var1, var3);
      if (var3 != 0) {
         byte[] var4 = new byte[var3];
         PlatformDependent.copyMemory(this.addr(var1), var4, 0, var3);
         var2.write(var4);
      }

      return this;
   }

   @Override
   public int setBytes(int var1, ScatteringByteChannel var2, int var3) throws java.io.IOException {
      this.checkIndex(var1, var3);
      ByteBuffer var4 = this.internalNioBuffer();
      var1 = this.idx(var1);
      ((Buffer)var4).clear().position(var1).limit(var1 + var3);

      try {
         return var2.read(var4);
      } catch (ClosedChannelException var6) {
         return -1;
      }
   }

   public int getBytes(int var1, GatheringByteChannel var2, int var3, boolean var4) throws java.io.IOException {
      this.checkIndex(var1, var3);
      if (var3 == 0) {
         return 0;
      } else {
         ByteBuffer var5;
         if (var4) {
            var5 = this.internalNioBuffer();
         } else {
            var5 = this.memory.duplicate();
         }

         var1 = this.idx(var1);
         ((Buffer)var5).clear().position(var1).limit(var1 + var3);
         return var2.write(var5);
      }
   }

   @Override
   public ByteBuffer[] nioBuffers(int var1, int var2) {
      return new ByteBuffer[]{this.nioBuffer(var1, var2)};
   }

   @Override
   public short _getShort(int var1) {
      short var2 = PlatformDependent.getShort(this.addr(var1));
      return NATIVE_ORDER ? var2 : Short.reverseBytes(var2);
   }

   public void initMemoryAddress() {
      this.memoryAddress = PlatformDependent.directBufferAddress(this.memory) + this.offset;
   }
}
