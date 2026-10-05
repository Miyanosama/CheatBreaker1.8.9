package io.netty.buffer;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import net.minecraft.block.BlockBeacon$1$1;
import org.apache.log4j.varia.LevelRangeFilter;

public class DuplicatedByteBuf extends AbstractDerivedByteBuf {
   public BlockBeacon$1$1 __junk1155843713779913515;
   public LevelRangeFilter __junk5873147603793718472;
   public ByteBuf buffer;

   @Override
   public ByteBuf setLong(int var1, long var2) {
      this._setLong(var1, var2);
      return this;
   }

   @Override
   public ByteOrder order() {
      return this.buffer.order();
   }

   @Override
   public ByteBuf setShort(int var1, int var2) {
      this._setShort(var1, var2);
      return this;
   }

   @Override
   public int nioBufferCount() {
      return this.buffer.nioBufferCount();
   }

   @Override
   public int _getUnsignedMedium(int var1) {
      return this.buffer.getUnsignedMedium(var1);
   }

   @Override
   public ByteBuf capacity(int var1) {
      this.buffer.capacity(var1);
      return this;
   }

   @Override
   public boolean isDirect() {
      return this.buffer.isDirect();
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuf var2, int var3, int var4) {
      this.buffer.getBytes(var1, var2, var3, var4);
      return this;
   }

   public DuplicatedByteBuf(ByteBuf var1) {
      super(var1.maxCapacity());
      if (var1 instanceof DuplicatedByteBuf) {
         this.buffer = ((DuplicatedByteBuf)var1).buffer;
      } else {
         this.buffer = var1;
      }

      this.setIndex(var1.readerIndex(), var1.writerIndex());
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuffer var2) {
      this.buffer.setBytes(var1, var2);
      return this;
   }

   @Override
   public long memoryAddress() {
      return this.buffer.memoryAddress();
   }

   @Override
   public int capacity() {
      return this.buffer.capacity();
   }

   @Override
   public long _getLong(int var1) {
      return this.buffer.getLong(var1);
   }

   @Override
   public short getShort(int var1) {
      return this._getShort(var1);
   }

   @Override
   public short _getShort(int var1) {
      return this.buffer.getShort(var1);
   }

   @Override
   public long getLong(int var1) {
      return this._getLong(var1);
   }

   @Override
   public int setBytes(int var1, InputStream var2, int var3) {
      return this.buffer.setBytes(var1, var2, var3);
   }

   @Override
   public ByteBuf setInt(int var1, int var2) {
      this._setInt(var1, var2);
      return this;
   }

   @Override
   public int getUnsignedMedium(int var1) {
      return this._getUnsignedMedium(var1);
   }

   @Override
   public boolean hasMemoryAddress() {
      return this.buffer.hasMemoryAddress();
   }

   @Override
   public int getInt(int var1) {
      return this._getInt(var1);
   }

   @Override
   public int getBytes(int var1, GatheringByteChannel var2, int var3) {
      return this.buffer.getBytes(var1, var2, var3);
   }

   @Override
   public void _setInt(int var1, int var2) {
      this.buffer.setInt(var1, var2);
   }

   @Override
   public int forEachByteDesc(int var1, int var2, ByteBufProcessor var3) {
      return this.buffer.forEachByteDesc(var1, var2, var3);
   }

   @Override
   public ByteBuf slice(int var1, int var2) {
      return this.buffer.slice(var1, var2);
   }

   @Override
   public ByteBuf setBytes(int var1, byte[] var2, int var3, int var4) {
      this.buffer.setBytes(var1, var2, var3, var4);
      return this;
   }

   @Override
   public ByteBuffer[] nioBuffers(int var1, int var2) {
      return this.buffer.nioBuffers(var1, var2);
   }

   @Override
   public int arrayOffset() {
      return this.buffer.arrayOffset();
   }

   @Override
   public ByteBuf setByte(int var1, int var2) {
      this._setByte(var1, var2);
      return this;
   }

   @Override
   public boolean hasArray() {
      return this.buffer.hasArray();
   }

   @Override
   public int forEachByte(int var1, int var2, ByteBufProcessor var3) {
      return this.buffer.forEachByte(var1, var2, var3);
   }

   @Override
   public ByteBuf copy(int var1, int var2) {
      return this.buffer.copy(var1, var2);
   }

   @Override
   public void _setShort(int var1, int var2) {
      this.buffer.setShort(var1, var2);
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2, int var3, int var4) {
      this.buffer.setBytes(var1, var2, var3, var4);
      return this;
   }

   @Override
   public ByteBuf unwrap() {
      return this.buffer;
   }

   @Override
   public void _setLong(int var1, long var2) {
      this.buffer.setLong(var1, var2);
   }

   @Override
   public ByteBuf setMedium(int var1, int var2) {
      this._setMedium(var1, var2);
      return this;
   }

   @Override
   public byte getByte(int var1) {
      return this._getByte(var1);
   }

   @Override
   public byte[] array() {
      return this.buffer.array();
   }

   @Override
   public ByteBuf getBytes(int var1, byte[] var2, int var3, int var4) {
      this.buffer.getBytes(var1, var2, var3, var4);
      return this;
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuffer var2) {
      this.buffer.getBytes(var1, var2);
      return this;
   }

   @Override
   public void _setMedium(int var1, int var2) {
      this.buffer.setMedium(var1, var2);
   }

   @Override
   public ByteBuffer internalNioBuffer(int var1, int var2) {
      return this.nioBuffer(var1, var2);
   }

   @Override
   public byte _getByte(int var1) {
      return this.buffer.getByte(var1);
   }

   @Override
   public int setBytes(int var1, ScatteringByteChannel var2, int var3) {
      return this.buffer.setBytes(var1, var2, var3);
   }

   @Override
   public ByteBuf getBytes(int var1, OutputStream var2, int var3) {
      this.buffer.getBytes(var1, var2, var3);
      return this;
   }

   @Override
   public int _getInt(int var1) {
      return this.buffer.getInt(var1);
   }

   @Override
   public void _setByte(int var1, int var2) {
      this.buffer.setByte(var1, var2);
   }

   @Override
   public ByteBufAllocator alloc() {
      return this.buffer.alloc();
   }
}
