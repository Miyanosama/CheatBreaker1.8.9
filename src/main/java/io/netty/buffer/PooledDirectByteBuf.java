package io.netty.buffer;

import io.netty.handler.codec.http.HttpObjectDecoder;
import io.netty.util.Recycler;
import io.netty.util.internal.NoOpTypeParameterMatcher;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import net.minecraft.client.resources.model.SimpleBakedModel;
import net.minecraft.world.gen.layer.GenLayerRareBiome;
import net.optifine.reflect.Reflector;

public class PooledDirectByteBuf extends PooledByteBuf<ByteBuffer> {
   public static Recycler<PooledDirectByteBuf> RECYCLER = new Recycler<PooledDirectByteBuf>() {

      public PooledDirectByteBuf newObject(Recycler.Handle var1) {
         return new PooledDirectByteBuf(var1, 0);
      }
   };

   @Override
   public ByteBuf readBytes(byte[] var1, int var2, int var3) {
      this.checkReadableBytes(var3);
      this.getBytes(this.readerIndex, var1, var2, var3, true);
      this.readerIndex += var3;
      return this;
   }

   @Override
   public void _setByte(int var1, int var2) {
      this.memory.put(this.idx(var1), (byte)var2);
   }

   public void getBytes(int var1, byte[] var2, int var3, int var4, boolean var5) {
      this.checkDstIndex(var1, var4, var3, var2.length);
      ByteBuffer var6;
      if (var5) {
         var6 = this.internalNioBuffer();
      } else {
         var6 = this.memory.duplicate();
      }

      var1 = this.idx(var1);
      ((Buffer)var6).clear().position(var1).limit(var1 + var4);
      var6.get(var2, var3, var4);
   }

   @Override
   public int nioBufferCount() {
      return 1;
   }

   public PooledDirectByteBuf(Recycler.Handle var1, int var2) {
      super(var1, var2);
   }

   @Override
   public boolean hasArray() {
      return false;
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
   public ByteBuf readBytes(OutputStream var1, int var2) throws java.io.IOException {
      this.checkReadableBytes(var2);
      this.getBytes(this.readerIndex, var1, var2, true);
      this.readerIndex += var2;
      return this;
   }

   @Override
   public short _getShort(int var1) {
      return this.memory.getShort(this.idx(var1));
   }

   @Override
   public int _getInt(int var1) {
      return this.memory.getInt(this.idx(var1));
   }

   @Override
   public ByteBuffer internalNioBuffer(int var1, int var2) {
      this.checkIndex(var1, var2);
      var1 = this.idx(var1);
      return (ByteBuffer)((Buffer)this.internalNioBuffer()).clear().position(var1).limit(var1 + var2);
   }

   @Override
   public int readBytes(GatheringByteChannel var1, int var2) throws java.io.IOException {
      this.checkReadableBytes(var2);
      int var3 = this.getBytes(this.readerIndex, var1, var2, true);
      this.readerIndex += var3;
      return var3;
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
   public ByteBuffer[] nioBuffers(int var1, int var2) {
      return new ByteBuffer[]{this.nioBuffer(var1, var2)};
   }

   @Override
   public int arrayOffset() {
      throw new UnsupportedOperationException("direct buffer");
   }

   public void getBytes(int var1, OutputStream var2, int var3, boolean var4) throws java.io.IOException {
      this.checkIndex(var1, var3);
      if (var3 != 0) {
         byte[] var5 = new byte[var3];
         ByteBuffer var6;
         if (var4) {
            var6 = this.internalNioBuffer();
         } else {
            var6 = this.memory.duplicate();
         }

         ((Buffer)var6).clear().position(this.idx(var1));
         var6.get(var5);
         var2.write(var5);
      }
   }

   @Override
   public Recycler<?> recycler() {
      return RECYCLER;
   }

   @Override
   public int _getUnsignedMedium(int var1) {
      var1 = this.idx(var1);
      return (this.memory.get(var1) & 0xFF) << 16 | (this.memory.get(var1 + 1) & 0xFF) << 8 | this.memory.get(var1 + 2) & 0xFF;
   }

   @Override
   public boolean isDirect() {
      return true;
   }

   @Override
   public int setBytes(int var1, InputStream var2, int var3) throws java.io.IOException {
      this.checkIndex(var1, var3);
      byte[] var4 = new byte[var3];
      int var5 = var2.read(var4);
      if (var5 <= 0) {
         return var5;
      } else {
         ByteBuffer var6 = this.internalNioBuffer();
         ((Buffer)var6).clear().position(this.idx(var1));
         var6.put(var4, 0, var5);
         return var5;
      }
   }

   @Override
   public ByteBuf getBytes(int var1, OutputStream var2, int var3) throws java.io.IOException {
      this.getBytes(var1, var2, var3, false);
      return this;
   }

   @Override
   public ByteBuf copy(int var1, int var2) {
      this.checkIndex(var1, var2);
      ByteBuf var3 = this.alloc().directBuffer(var2, this.maxCapacity());
      var3.writeBytes(this, var1, var2);
      return var3;
   }

   @Override
   public ByteBuf setBytes(int var1, byte[] var2, int var3, int var4) {
      this.checkSrcIndex(var1, var4, var3, var2.length);
      ByteBuffer var5 = this.internalNioBuffer();
      var1 = this.idx(var1);
      ((Buffer)var5).clear().position(var1).limit(var1 + var4);
      var5.put(var2, var3, var4);
      return this;
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
   public void _setMedium(int var1, int var2) {
      var1 = this.idx(var1);
      this.memory.put(var1, (byte)(var2 >>> 16));
      this.memory.put(var1 + 1, (byte)(var2 >>> 8));
      this.memory.put(var1 + 2, (byte)var2);
   }

   @Override
   public byte _getByte(int var1) {
      return this.memory.get(this.idx(var1));
   }

   @Override
   public void _setInt(int var1, int var2) {
      this.memory.putInt(this.idx(var1), var2);
   }

   @Override
   public byte[] array() {
      throw new UnsupportedOperationException("direct buffer");
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuffer var2) {
      this.getBytes(var1, var2, false);
      return this;
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2, int var3, int var4) {
      this.checkSrcIndex(var1, var4, var3, var2.capacity());
      if (var2.hasArray()) {
         this.setBytes(var1, var2.array(), var2.arrayOffset() + var3, var4);
      } else if (var2.nioBufferCount() > 0) {
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

   public ByteBuffer newInternalNioBuffer(ByteBuffer var1) {
      return var1.duplicate();
   }

   @Override
   public boolean hasMemoryAddress() {
      return false;
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
   public long memoryAddress() {
      throw new UnsupportedOperationException();
   }

   @Override
   public ByteBuf getBytes(int var1, byte[] var2, int var3, int var4) {
      this.getBytes(var1, var2, var3, var4, false);
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

   @Override
   public void _setShort(int var1, int var2) {
      this.memory.putShort(this.idx(var1), (short)var2);
   }

   public static PooledDirectByteBuf newInstance(int var0) {
      PooledDirectByteBuf var1 = RECYCLER.get();
      var1.setRefCnt(1);
      var1.maxCapacity(var0);
      return var1;
   }

   @Override
   public long _getLong(int var1) {
      return this.memory.getLong(this.idx(var1));
   }

   @Override
   public ByteBuffer nioBuffer(int var1, int var2) {
      this.checkIndex(var1, var2);
      var1 = this.idx(var1);
      return ((ByteBuffer)((Buffer)this.memory.duplicate()).position(var1).limit(var1 + var2)).slice();
   }

   @Override
   public int getBytes(int var1, GatheringByteChannel var2, int var3) throws java.io.IOException {
      return this.getBytes(var1, var2, var3, false);
   }

   @Override
   public void _setLong(int var1, long var2) {
      this.memory.putLong(this.idx(var1), var2);
   }
}
