package io.netty.buffer;

import com.cheatbreaker.client.module.type.PotionCounterModule;
import io.netty.channel.epoll.EpollDatagramChannel$DatagramSocketAddress;
import io.netty.handler.timeout.IdleStateHandler$WriterIdleTimeoutTask;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import net.minecraft.client.renderer.entity.RenderCow;
import net.optifine.render.Blender;

public class SlicedByteBuf extends AbstractDerivedByteBuf {
   public ByteBuf buffer;
   public int length;
   public EpollDatagramChannel$DatagramSocketAddress __junk681967176960018290;
   public IdleStateHandler$WriterIdleTimeoutTask __junk9122322367070735361;
   public Blender __junk6245606823556643699;
   public RenderCow __junk9105093987023154836;
   public PotionCounterModule __junk4504437976468902424;
   public int adjustment;

   @Override
   public ByteBufAllocator alloc() {
      return this.buffer.alloc();
   }

   @Override
   public boolean hasMemoryAddress() {
      return this.buffer.hasMemoryAddress();
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuffer var2) {
      this.checkIndex(var1, var2.remaining());
      this.buffer.getBytes(var1 + this.adjustment, var2);
      return this;
   }

   @Override
   public void _setInt(int var1, int var2) {
      this.buffer.setInt(var1 + this.adjustment, var2);
   }

   @Override
   public ByteBuf copy(int var1, int var2) {
      this.checkIndex(var1, var2);
      return this.buffer.copy(var1 + this.adjustment, var2);
   }

   @Override
   public int arrayOffset() {
      return this.buffer.arrayOffset() + this.adjustment;
   }

   @Override
   public int nioBufferCount() {
      return this.buffer.nioBufferCount();
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuf var2, int var3, int var4) {
      this.checkIndex(var1, var4);
      this.buffer.getBytes(var1 + this.adjustment, var2, var3, var4);
      return this;
   }

   @Override
   public byte _getByte(int var1) {
      return this.buffer.getByte(var1 + this.adjustment);
   }

   @Override
   public ByteBuf setBytes(int var1, byte[] var2, int var3, int var4) {
      this.checkIndex(var1, var4);
      this.buffer.setBytes(var1 + this.adjustment, var2, var3, var4);
      return this;
   }

   @Override
   public ByteBuf getBytes(int var1, byte[] var2, int var3, int var4) {
      this.checkIndex(var1, var4);
      this.buffer.getBytes(var1 + this.adjustment, var2, var3, var4);
      return this;
   }

   @Override
   public ByteBuf duplicate() {
      ByteBuf var1 = this.buffer.slice(this.adjustment, this.length);
      var1.setIndex(this.readerIndex(), this.writerIndex());
      return var1;
   }

   @Override
   public int capacity() {
      return this.length;
   }

   @Override
   public void _setByte(int var1, int var2) {
      this.buffer.setByte(var1 + this.adjustment, var2);
   }

   @Override
   public ByteBuffer internalNioBuffer(int var1, int var2) {
      this.checkIndex(var1, var2);
      return this.nioBuffer(var1, var2);
   }

   @Override
   public ByteBuf getBytes(int var1, OutputStream var2, int var3) {
      this.checkIndex(var1, var3);
      this.buffer.getBytes(var1 + this.adjustment, var2, var3);
      return this;
   }

   @Override
   public short _getShort(int var1) {
      return this.buffer.getShort(var1 + this.adjustment);
   }

   @Override
   public void _setMedium(int var1, int var2) {
      this.buffer.setMedium(var1 + this.adjustment, var2);
   }

   @Override
   public int forEachByte(int var1, int var2, ByteBufProcessor var3) {
      int var4 = this.buffer.forEachByte(var1 + this.adjustment, var2, var3);
      return var4 >= this.adjustment ? var4 - this.adjustment : -1;
   }

   @Override
   public ByteBuffer nioBuffer(int var1, int var2) {
      this.checkIndex(var1, var2);
      return this.buffer.nioBuffer(var1 + this.adjustment, var2);
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuffer var2) {
      this.checkIndex(var1, var2.remaining());
      this.buffer.setBytes(var1 + this.adjustment, var2);
      return this;
   }

   @Override
   public ByteBuf capacity(int var1) {
      throw new UnsupportedOperationException("sliced buffer");
   }

   @Override
   public ByteBuf unwrap() {
      return this.buffer;
   }

   @Override
   public ByteOrder order() {
      return this.buffer.order();
   }

   @Override
   public void _setLong(int var1, long var2) {
      this.buffer.setLong(var1 + this.adjustment, var2);
   }

   @Override
   public void _setShort(int var1, int var2) {
      this.buffer.setShort(var1 + this.adjustment, var2);
   }

   @Override
   public int _getUnsignedMedium(int var1) {
      return this.buffer.getUnsignedMedium(var1 + this.adjustment);
   }

   @Override
   public byte[] array() {
      return this.buffer.array();
   }

   @Override
   public long memoryAddress() {
      return this.buffer.memoryAddress() + this.adjustment;
   }

   public SlicedByteBuf(ByteBuf var1, int var2, int var3) {
      super(var3);
      if (var2 >= 0 && var2 <= var1.capacity() - var3) {
         if (var1 instanceof SlicedByteBuf) {
            this.buffer = ((SlicedByteBuf)var1).buffer;
            this.adjustment = ((SlicedByteBuf)var1).adjustment + var2;
         } else if (var1 instanceof DuplicatedByteBuf) {
            this.buffer = var1.unwrap();
            this.adjustment = var2;
         } else {
            this.buffer = var1;
            this.adjustment = var2;
         }

         this.length = var3;
         this.writerIndex(var3);
      } else {
         throw new IndexOutOfBoundsException(var1 + ".slice(" + var2 + ", " + var3 + ')');
      }
   }

   @Override
   public int _getInt(int var1) {
      return this.buffer.getInt(var1 + this.adjustment);
   }

   @Override
   public int setBytes(int var1, ScatteringByteChannel var2, int var3) {
      this.checkIndex(var1, var3);
      return this.buffer.setBytes(var1 + this.adjustment, var2, var3);
   }

   @Override
   public boolean hasArray() {
      return this.buffer.hasArray();
   }

   @Override
   public boolean isDirect() {
      return this.buffer.isDirect();
   }

   @Override
   public int setBytes(int var1, InputStream var2, int var3) {
      this.checkIndex(var1, var3);
      return this.buffer.setBytes(var1 + this.adjustment, var2, var3);
   }

   @Override
   public ByteBuffer[] nioBuffers(int var1, int var2) {
      this.checkIndex(var1, var2);
      return this.buffer.nioBuffers(var1 + this.adjustment, var2);
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2, int var3, int var4) {
      this.checkIndex(var1, var4);
      this.buffer.setBytes(var1 + this.adjustment, var2, var3, var4);
      return this;
   }

   @Override
   public long _getLong(int var1) {
      return this.buffer.getLong(var1 + this.adjustment);
   }

   @Override
   public ByteBuf slice(int var1, int var2) {
      this.checkIndex(var1, var2);
      return var2 == 0 ? Unpooled.EMPTY_BUFFER : this.buffer.slice(var1 + this.adjustment, var2);
   }

   @Override
   public int getBytes(int var1, GatheringByteChannel var2, int var3) {
      this.checkIndex(var1, var3);
      return this.buffer.getBytes(var1 + this.adjustment, var2, var3);
   }

   @Override
   public int forEachByteDesc(int var1, int var2, ByteBufProcessor var3) {
      int var4 = this.buffer.forEachByteDesc(var1 + this.adjustment, var2, var3);
      return var4 >= this.adjustment ? var4 - this.adjustment : -1;
   }
}
