package io.netty.buffer;

import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker08;
import io.netty.util.Recycler;
import io.netty.util.Recycler$Handle;
import io.netty.util.internal.PlatformDependent;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import net.minecraft.enchantment.EnchantmentUntouching;

public class PooledHeapByteBuf extends PooledByteBuf<byte[]> {
   public EnchantmentUntouching __junk8030652818303609812;
   public WebSocketClientHandshaker08 __junk1754967951384719179;
   public static Recycler<PooledHeapByteBuf> RECYCLER = new PooledHeapByteBuf$1();

   @Override
   public byte _getByte(int var1) {
      return this.memory[this.idx(var1)];
   }

   @Override
   public int _getInt(int var1) {
      var1 = this.idx(var1);
      return (this.memory[var1] & 0xFF) << 24 | (this.memory[var1 + 1] & 0xFF) << 16 | (this.memory[var1 + 2] & 0xFF) << 8 | this.memory[var1 + 3] & 0xFF;
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuffer var2) {
      this.checkIndex(var1);
      var2.put(this.memory, this.idx(var1), Math.min(this.capacity() - var1, var2.remaining()));
      return this;
   }

   @Override
   public ByteBuf setBytes(int var1, byte[] var2, int var3, int var4) {
      this.checkSrcIndex(var1, var4, var3, var2.length);
      System.arraycopy(var2, var3, this.memory, this.idx(var1), var4);
      return this;
   }

   public int getBytes(int var1, GatheringByteChannel var2, int var3, boolean var4) {
      this.checkIndex(var1, var3);
      var1 = this.idx(var1);
      ByteBuffer var5;
      if (var4) {
         var5 = this.internalNioBuffer();
      } else {
         var5 = ByteBuffer.wrap(this.memory);
      }

      return var2.write((ByteBuffer)((Buffer)var5).clear().position(var1).limit(var1 + var3));
   }

   @Override
   public int setBytes(int var1, InputStream var2, int var3) {
      this.checkIndex(var1, var3);
      return var2.read(this.memory, this.idx(var1), var3);
   }

   @Override
   public ByteBuffer internalNioBuffer(int var1, int var2) {
      this.checkIndex(var1, var2);
      var1 = this.idx(var1);
      return (ByteBuffer)((Buffer)this.internalNioBuffer()).clear().position(var1).limit(var1 + var2);
   }

   @Override
   public int readBytes(GatheringByteChannel var1, int var2) {
      this.checkReadableBytes(var2);
      int var3 = this.getBytes(this.readerIndex, var1, var2, true);
      this.readerIndex += var3;
      return var3;
   }

   public static PooledHeapByteBuf newInstance(int var0) {
      PooledHeapByteBuf var1 = RECYCLER.get();
      var1.setRefCnt(1);
      var1.maxCapacity(var0);
      return var1;
   }

   @Override
   public int _getUnsignedMedium(int var1) {
      var1 = this.idx(var1);
      return (this.memory[var1] & 0xFF) << 16 | (this.memory[var1 + 1] & 0xFF) << 8 | this.memory[var1 + 2] & 0xFF;
   }

   public PooledHeapByteBuf(Recycler$Handle var1, int var2) {
      super(var1, var2);
   }

   @Override
   public ByteBuf getBytes(int var1, byte[] var2, int var3, int var4) {
      this.checkDstIndex(var1, var4, var3, var2.length);
      System.arraycopy(this.memory, this.idx(var1), var2, var3, var4);
      return this;
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2, int var3, int var4) {
      this.checkSrcIndex(var1, var4, var3, var2.capacity());
      if (var2.hasMemoryAddress()) {
         PlatformDependent.copyMemory(var2.memoryAddress() + var3, this.memory, this.idx(var1), var4);
      } else if (var2.hasArray()) {
         this.setBytes(var1, var2.array(), var2.arrayOffset() + var3, var4);
      } else {
         var2.getBytes(var3, this.memory, this.idx(var1), var4);
      }

      return this;
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuf var2, int var3, int var4) {
      this.checkDstIndex(var1, var4, var3, var2.capacity());
      if (var2.hasMemoryAddress()) {
         PlatformDependent.copyMemory(this.memory, this.idx(var1), var2.memoryAddress() + var3, var4);
      } else if (var2.hasArray()) {
         this.getBytes(var1, var2.array(), var2.arrayOffset() + var3, var4);
      } else {
         var2.setBytes(var3, this.memory, this.idx(var1), var4);
      }

      return this;
   }

   @Override
   public boolean hasMemoryAddress() {
      return false;
   }

   public ByteBuffer newInternalNioBuffer(byte[] var1) {
      return ByteBuffer.wrap(var1);
   }

   @Override
   public byte[] array() {
      return this.memory;
   }

   @Override
   public void _setMedium(int var1, int var2) {
      var1 = this.idx(var1);
      this.memory[var1] = (byte)(var2 >>> 16);
      this.memory[var1 + 1] = (byte)(var2 >>> 8);
      this.memory[var1 + 2] = (byte)var2;
   }

   @Override
   public boolean hasArray() {
      return true;
   }

   @Override
   public int nioBufferCount() {
      return 1;
   }

   @Override
   public ByteBuffer[] nioBuffers(int var1, int var2) {
      return new ByteBuffer[]{this.nioBuffer(var1, var2)};
   }

   @Override
   public ByteBuf copy(int var1, int var2) {
      this.checkIndex(var1, var2);
      ByteBuf var3 = this.alloc().heapBuffer(var2, this.maxCapacity());
      var3.writeBytes(this.memory, this.idx(var1), var2);
      return var3;
   }

   @Override
   public Recycler<?> recycler() {
      return RECYCLER;
   }

   @Override
   public void _setInt(int var1, int var2) {
      var1 = this.idx(var1);
      this.memory[var1] = (byte)(var2 >>> 24);
      this.memory[var1 + 1] = (byte)(var2 >>> 16);
      this.memory[var1 + 2] = (byte)(var2 >>> 8);
      this.memory[var1 + 3] = (byte)var2;
   }

   @Override
   public int getBytes(int var1, GatheringByteChannel var2, int var3) {
      return this.getBytes(var1, var2, var3, false);
   }

   @Override
   public int setBytes(int var1, ScatteringByteChannel var2, int var3) {
      this.checkIndex(var1, var3);
      var1 = this.idx(var1);

      try {
         return var2.read((ByteBuffer)((Buffer)this.internalNioBuffer()).clear().position(var1).limit(var1 + var3));
      } catch (ClosedChannelException var5) {
         return -1;
      }
   }

   @Override
   public void _setLong(int var1, long var2) {
      var1 = this.idx(var1);
      this.memory[var1] = (byte)(var2 >>> 56);
      this.memory[var1 + 1] = (byte)(var2 >>> 48);
      this.memory[var1 + 2] = (byte)(var2 >>> 40);
      this.memory[var1 + 3] = (byte)(var2 >>> 32);
      this.memory[var1 + 4] = (byte)(var2 >>> 24);
      this.memory[var1 + 5] = (byte)(var2 >>> 16);
      this.memory[var1 + 6] = (byte)(var2 >>> 8);
      this.memory[var1 + 7] = (byte)var2;
   }

   @Override
   public boolean isDirect() {
      return false;
   }

   @Override
   public void _setShort(int var1, int var2) {
      var1 = this.idx(var1);
      this.memory[var1] = (byte)(var2 >>> 8);
      this.memory[var1 + 1] = (byte)var2;
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuffer var2) {
      int var3 = var2.remaining();
      this.checkIndex(var1, var3);
      var2.get(this.memory, this.idx(var1), var3);
      return this;
   }

   @Override
   public short _getShort(int var1) {
      var1 = this.idx(var1);
      return (short)(this.memory[var1] << 8 | this.memory[var1 + 1] & 255);
   }

   @Override
   public ByteBuffer nioBuffer(int var1, int var2) {
      this.checkIndex(var1, var2);
      var1 = this.idx(var1);
      ByteBuffer var3 = ByteBuffer.wrap(this.memory, var1, var2);
      return var3.slice();
   }

   @Override
   public void _setByte(int var1, int var2) {
      this.memory[this.idx(var1)] = (byte)var2;
   }

   @Override
   public long memoryAddress() {
      throw new UnsupportedOperationException();
   }

   @Override
   public ByteBuf getBytes(int var1, OutputStream var2, int var3) {
      this.checkIndex(var1, var3);
      var2.write(this.memory, this.idx(var1), var3);
      return this;
   }

   @Override
   public long _getLong(int var1) {
      var1 = this.idx(var1);
      return (this.memory[var1] & 3342634242009052415L & 776358143L) << 56
         | (this.memory[var1 + 1] & 3486942289402974463L & -3486942289902698241L) << 48
         | (this.memory[var1 + 2] & 369172735L & 1231177471L) << 40
         | (this.memory[var1 + 3] & 8653459334381961727L & 655615L) << 32
         | (this.memory[var1 + 4] & 6623455902563664127L & -6623455904345878273L) << 24
         | (this.memory[var1 + 5] & 3874422193234043647L & -3874422195113160449L) << 16
         | (this.memory[var1 + 6] & 167858431L & -1603395352154561793L) << 8
         | this.memory[var1 + 7] & 311198463L & -837858854791279361L;
   }

   @Override
   public int arrayOffset() {
      return this.offset;
   }
}
