package io.netty.buffer;

import io.netty.util.internal.StringUtil;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;

public class WrappedByteBuf extends ByteBuf {
   public ByteBuf buf;

   @Override
   public ByteBuf writeByte(int var1) {
      this.buf.writeByte(var1);
      return this;
   }

   @Override
   public short readUnsignedByte() {
      return this.buf.readUnsignedByte();
   }

   @Override
   public boolean equals(Object var1) {
      return this.buf.equals(var1);
   }

   @Override
   public int forEachByteDesc(ByteBufProcessor var1) {
      return this.buf.forEachByteDesc(var1);
   }

   @Override
   public int writableBytes() {
      return this.buf.writableBytes();
   }

   @Override
   public int getInt(int var1) {
      return this.buf.getInt(var1);
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuffer var2) {
      this.buf.setBytes(var1, var2);
      return this;
   }

   @Override
   public ByteBuf setDouble(int var1, double var2) {
      this.buf.setDouble(var1, var2);
      return this;
   }

   @Override
   public boolean isWritable(int var1) {
      return this.buf.isWritable(var1);
   }

   @Override
   public ByteBuf setIndex(int var1, int var2) {
      this.buf.setIndex(var1, var2);
      return this;
   }

   @Override
   public short getShort(int var1) {
      return this.buf.getShort(var1);
   }

   @Override
   public int bytesBefore(int var1, int var2, byte var3) {
      return this.buf.bytesBefore(var1, var2, var3);
   }

   @Override
   public float readFloat() {
      return this.buf.readFloat();
   }

   @Override
   public ByteBuf writeZero(int var1) {
      this.buf.writeZero(var1);
      return this;
   }

   @Override
   public ByteBuf writeBytes(byte[] var1, int var2, int var3) {
      this.buf.writeBytes(var1, var2, var3);
      return this;
   }

   @Override
   public int readMedium() {
      return this.buf.readMedium();
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuf var2, int var3) {
      this.buf.getBytes(var1, var2, var3);
      return this;
   }

   @Override
   public ByteBuf unwrap() {
      return this.buf;
   }

   @Override
   public int setBytes(int var1, InputStream var2, int var3) {
      return this.buf.setBytes(var1, var2, var3);
   }

   @Override
   public ByteBuf setZero(int var1, int var2) {
      this.buf.setZero(var1, var2);
      return this;
   }

   @Override
   public int maxCapacity() {
      return this.buf.maxCapacity();
   }

   @Override
   public ByteBuf copy() {
      return this.buf.copy();
   }

   @Override
   public ByteBuf readSlice(int var1) {
      return this.buf.readSlice(var1);
   }

   @Override
   public ByteBuf readBytes(ByteBuf var1, int var2, int var3) {
      this.buf.readBytes(var1, var2, var3);
      return this;
   }

   @Override
   public ByteBufAllocator alloc() {
      return this.buf.alloc();
   }

   @Override
   public ByteBuf readBytes(int var1) {
      return this.buf.readBytes(var1);
   }

   @Override
   public int getUnsignedShort(int var1) {
      return this.buf.getUnsignedShort(var1);
   }

   @Override
   public int readInt() {
      return this.buf.readInt();
   }

   @Override
   public ByteBuf resetReaderIndex() {
      this.buf.resetReaderIndex();
      return this;
   }

   @Override
   public boolean release(int var1) {
      return this.buf.release(var1);
   }

   @Override
   public String toString() {
      return StringUtil.simpleClassName(this) + '(' + this.buf.toString() + ')';
   }

   @Override
   public ByteBuf writerIndex(int var1) {
      this.buf.writerIndex(var1);
      return this;
   }

   @Override
   public ByteBuf writeLong(long var1) {
      this.buf.writeLong(var1);
      return this;
   }

   @Override
   public byte getByte(int var1) {
      return this.buf.getByte(var1);
   }

   @Override
   public ByteBuf setBytes(int var1, byte[] var2) {
      this.buf.setBytes(var1, var2);
      return this;
   }

   @Override
   public ByteBuf setMedium(int var1, int var2) {
      this.buf.setMedium(var1, var2);
      return this;
   }

   @Override
   public boolean getBoolean(int var1) {
      return this.buf.getBoolean(var1);
   }

   @Override
   public ByteBuf getBytes(int var1, byte[] var2) {
      this.buf.getBytes(var1, var2);
      return this;
   }

   @Override
   public ByteBuffer[] nioBuffers() {
      return this.buf.nioBuffers();
   }

   @Override
   public ByteBuf discardReadBytes() {
      this.buf.discardReadBytes();
      return this;
   }

   @Override
   public double readDouble() {
      return this.buf.readDouble();
   }

   @Override
   public int capacity() {
      return this.buf.capacity();
   }

   @Override
   public ByteBuf readBytes(byte[] var1) {
      this.buf.readBytes(var1);
      return this;
   }

   @Override
   public ByteBuf writeBytes(byte[] var1) {
      this.buf.writeBytes(var1);
      return this;
   }

   @Override
   public int writeBytes(ScatteringByteChannel var1, int var2) {
      return this.buf.writeBytes(var1, var2);
   }

   @Override
   public boolean isReadable() {
      return this.buf.isReadable();
   }

   @Override
   public int getBytes(int var1, GatheringByteChannel var2, int var3) {
      return this.buf.getBytes(var1, var2, var3);
   }

   @Override
   public int forEachByte(int var1, int var2, ByteBufProcessor var3) {
      return this.buf.forEachByte(var1, var2, var3);
   }

   @Override
   public ByteBuf writeDouble(double var1) {
      this.buf.writeDouble(var1);
      return this;
   }

   @Override
   public short getUnsignedByte(int var1) {
      return this.buf.getUnsignedByte(var1);
   }

   @Override
   public ByteBuf discardSomeReadBytes() {
      this.buf.discardSomeReadBytes();
      return this;
   }

   public WrappedByteBuf(ByteBuf var1) {
      if (var1 == null) {
         throw new NullPointerException("buf");
      } else {
         this.buf = var1;
      }
   }

   @Override
   public double getDouble(int var1) {
      return this.buf.getDouble(var1);
   }

   @Override
   public short readShort() {
      return this.buf.readShort();
   }

   @Override
   public ByteBuf writeBytes(ByteBuf var1, int var2) {
      this.buf.writeBytes(var1, var2);
      return this;
   }

   @Override
   public ByteBuf resetWriterIndex() {
      this.buf.resetWriterIndex();
      return this;
   }

   @Override
   public ByteBuf writeFloat(float var1) {
      this.buf.writeFloat(var1);
      return this;
   }

   @Override
   public ByteBuf duplicate() {
      return this.buf.duplicate();
   }

   @Override
   public int setBytes(int var1, ScatteringByteChannel var2, int var3) {
      return this.buf.setBytes(var1, var2, var3);
   }

   @Override
   public ByteBuf getBytes(int var1, OutputStream var2, int var3) {
      this.buf.getBytes(var1, var2, var3);
      return this;
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuf var2) {
      this.buf.getBytes(var1, var2);
      return this;
   }

   @Override
   public boolean isWritable() {
      return this.buf.isWritable();
   }

   @Override
   public ByteBuf markReaderIndex() {
      this.buf.markReaderIndex();
      return this;
   }

   @Override
   public int readerIndex() {
      return this.buf.readerIndex();
   }

   @Override
   public ByteBuf readBytes(OutputStream var1, int var2) {
      this.buf.readBytes(var1, var2);
      return this;
   }

   @Override
   public boolean isDirect() {
      return this.buf.isDirect();
   }

   @Override
   public ByteBuf capacity(int var1) {
      this.buf.capacity(var1);
      return this;
   }

   @Override
   public ByteBuf getBytes(int var1, byte[] var2, int var3, int var4) {
      this.buf.getBytes(var1, var2, var3, var4);
      return this;
   }

   @Override
   public long readUnsignedInt() {
      return this.buf.readUnsignedInt();
   }

   @Override
   public ByteBuf slice() {
      return this.buf.slice();
   }

   @Override
   public ByteOrder order() {
      return this.buf.order();
   }

   @Override
   public long readLong() {
      return this.buf.readLong();
   }

   @Override
   public ByteBuf writeBoolean(boolean var1) {
      this.buf.writeBoolean(var1);
      return this;
   }

   @Override
   public ByteBuf markWriterIndex() {
      this.buf.markWriterIndex();
      return this;
   }

   @Override
   public int indexOf(int var1, int var2, byte var3) {
      return this.buf.indexOf(var1, var2, var3);
   }

   @Override
   public int writerIndex() {
      return this.buf.writerIndex();
   }

   @Override
   public int nioBufferCount() {
      return this.buf.nioBufferCount();
   }

   @Override
   public long getLong(int var1) {
      return this.buf.getLong(var1);
   }

   @Override
   public ByteBuf setShort(int var1, int var2) {
      this.buf.setShort(var1, var2);
      return this;
   }

   @Override
   public ByteBuf setLong(int var1, long var2) {
      this.buf.setLong(var1, var2);
      return this;
   }

   @Override
   public float getFloat(int var1) {
      return this.buf.getFloat(var1);
   }

   @Override
   public boolean release() {
      return this.buf.release();
   }

   @Override
   public ByteBuffer internalNioBuffer(int var1, int var2) {
      return this.buf.internalNioBuffer(var1, var2);
   }

   @Override
   public ByteBuf readBytes(ByteBuf var1, int var2) {
      this.buf.readBytes(var1, var2);
      return this;
   }

   @Override
   public boolean isReadable(int var1) {
      return this.buf.isReadable(var1);
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuf var2, int var3, int var4) {
      this.buf.getBytes(var1, var2, var3, var4);
      return this;
   }

   @Override
   public ByteBuf copy(int var1, int var2) {
      return this.buf.copy(var1, var2);
   }

   @Override
   public ByteBuf readBytes(ByteBuffer var1) {
      this.buf.readBytes(var1);
      return this;
   }

   @Override
   public ByteBuf writeBytes(ByteBuf var1, int var2, int var3) {
      this.buf.writeBytes(var1, var2, var3);
      return this;
   }

   @Override
   public int readBytes(GatheringByteChannel var1, int var2) {
      return this.buf.readBytes(var1, var2);
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuffer var2) {
      this.buf.getBytes(var1, var2);
      return this;
   }

   @Override
   public ByteBuf readBytes(ByteBuf var1) {
      this.buf.readBytes(var1);
      return this;
   }

   @Override
   public ByteBuf retain(int var1) {
      this.buf.retain(var1);
      return this;
   }

   @Override
   public int compareTo(ByteBuf var1) {
      return this.buf.compareTo(var1);
   }

   @Override
   public ByteBuf setByte(int var1, int var2) {
      this.buf.setByte(var1, var2);
      return this;
   }

   @Override
   public int readUnsignedMedium() {
      return this.buf.readUnsignedMedium();
   }

   @Override
   public int bytesBefore(byte var1) {
      return this.buf.bytesBefore(var1);
   }

   @Override
   public boolean hasMemoryAddress() {
      return this.buf.hasMemoryAddress();
   }

   @Override
   public ByteBuffer nioBuffer(int var1, int var2) {
      return this.buf.nioBuffer(var1, var2);
   }

   @Override
   public byte[] array() {
      return this.buf.array();
   }

   @Override
   public ByteBuf readBytes(byte[] var1, int var2, int var3) {
      this.buf.readBytes(var1, var2, var3);
      return this;
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2, int var3) {
      this.buf.setBytes(var1, var2, var3);
      return this;
   }

   @Override
   public ByteBuf clear() {
      this.buf.clear();
      return this;
   }

   @Override
   public char readChar() {
      return this.buf.readChar();
   }

   @Override
   public byte readByte() {
      return this.buf.readByte();
   }

   @Override
   public ByteBuf writeInt(int var1) {
      this.buf.writeInt(var1);
      return this;
   }

   @Override
   public ByteBuffer nioBuffer() {
      return this.buf.nioBuffer();
   }

   @Override
   public int writeBytes(InputStream var1, int var2) {
      return this.buf.writeBytes(var1, var2);
   }

   @Override
   public int maxWritableBytes() {
      return this.buf.maxWritableBytes();
   }

   @Override
   public ByteBuf slice(int var1, int var2) {
      return this.buf.slice(var1, var2);
   }

   @Override
   public ByteBuf writeChar(int var1) {
      this.buf.writeChar(var1);
      return this;
   }

   @Override
   public long getUnsignedInt(int var1) {
      return this.buf.getUnsignedInt(var1);
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2, int var3, int var4) {
      this.buf.setBytes(var1, var2, var3, var4);
      return this;
   }

   @Override
   public ByteBuf setInt(int var1, int var2) {
      this.buf.setInt(var1, var2);
      return this;
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2) {
      this.buf.setBytes(var1, var2);
      return this;
   }

   @Override
   public ByteBuf skipBytes(int var1) {
      this.buf.skipBytes(var1);
      return this;
   }

   @Override
   public String toString(int var1, int var2, Charset var3) {
      return this.buf.toString(var1, var2, var3);
   }

   @Override
   public ByteBuf writeBytes(ByteBuf var1) {
      this.buf.writeBytes(var1);
      return this;
   }

   @Override
   public ByteBuf order(ByteOrder var1) {
      return this.buf.order(var1);
   }

   @Override
   public long memoryAddress() {
      return this.buf.memoryAddress();
   }

   @Override
   public int forEachByte(ByteBufProcessor var1) {
      return this.buf.forEachByte(var1);
   }

   @Override
   public ByteBuf setBytes(int var1, byte[] var2, int var3, int var4) {
      this.buf.setBytes(var1, var2, var3, var4);
      return this;
   }

   @Override
   public int refCnt() {
      return this.buf.refCnt();
   }

   @Override
   public int hashCode() {
      return this.buf.hashCode();
   }

   @Override
   public int readUnsignedShort() {
      return this.buf.readUnsignedShort();
   }

   @Override
   public int getUnsignedMedium(int var1) {
      return this.buf.getUnsignedMedium(var1);
   }

   @Override
   public String toString(Charset var1) {
      return this.buf.toString(var1);
   }

   @Override
   public ByteBuf setFloat(int var1, float var2) {
      this.buf.setFloat(var1, var2);
      return this;
   }

   @Override
   public ByteBuf writeBytes(ByteBuffer var1) {
      this.buf.writeBytes(var1);
      return this;
   }

   @Override
   public int readableBytes() {
      return this.buf.readableBytes();
   }

   @Override
   public ByteBuf setBoolean(int var1, boolean var2) {
      this.buf.setBoolean(var1, var2);
      return this;
   }

   @Override
   public boolean hasArray() {
      return this.buf.hasArray();
   }

   @Override
   public ByteBuf setChar(int var1, int var2) {
      this.buf.setChar(var1, var2);
      return this;
   }

   @Override
   public int bytesBefore(int var1, byte var2) {
      return this.buf.bytesBefore(var1, var2);
   }

   @Override
   public ByteBuf readerIndex(int var1) {
      this.buf.readerIndex(var1);
      return this;
   }

   @Override
   public char getChar(int var1) {
      return this.buf.getChar(var1);
   }

   @Override
   public ByteBuf writeMedium(int var1) {
      this.buf.writeMedium(var1);
      return this;
   }

   @Override
   public int ensureWritable(int var1, boolean var2) {
      return this.buf.ensureWritable(var1, var2);
   }

   @Override
   public int getMedium(int var1) {
      return this.buf.getMedium(var1);
   }

   @Override
   public ByteBuf writeShort(int var1) {
      this.buf.writeShort(var1);
      return this;
   }

   @Override
   public ByteBuf ensureWritable(int var1) {
      this.buf.ensureWritable(var1);
      return this;
   }

   @Override
   public ByteBuf retain() {
      this.buf.retain();
      return this;
   }

   @Override
   public ByteBuffer[] nioBuffers(int var1, int var2) {
      return this.buf.nioBuffers(var1, var2);
   }

   @Override
   public int forEachByteDesc(int var1, int var2, ByteBufProcessor var3) {
      return this.buf.forEachByteDesc(var1, var2, var3);
   }

   @Override
   public boolean readBoolean() {
      return this.buf.readBoolean();
   }

   @Override
   public int arrayOffset() {
      return this.buf.arrayOffset();
   }
}
