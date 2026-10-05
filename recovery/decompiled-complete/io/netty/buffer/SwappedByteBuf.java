package io.netty.buffer;

import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import net.minecraft.client.Minecraft$11;
import net.minecraft.client.gui.GuiUtilRenderComponents;
import net.minecraft.entity.passive.EntityAnimal;
import net.optifine.shaders.CustomTexture;
import recovered.unidentified.UnidentifiedClass1385;

public class SwappedByteBuf extends ByteBuf {
   public Minecraft$11 __junk6065457635939467507;
   public ByteBuf buf;
   public PoolSubpage __junk6807767412390263941;
   public EntityAnimal __junk7108985393083577914;
   public ByteOrder order;
   public CustomTexture __junk2112113501915193944;
   public GuiUtilRenderComponents __junk2922479948647055377;
   public UnidentifiedClass1385 __junk7350348102813470965;

   @Override
   public double getDouble(int var1) {
      return Double.longBitsToDouble(this.getLong(var1));
   }

   @Override
   public int forEachByte(ByteBufProcessor var1) {
      return this.buf.forEachByte(var1);
   }

   @Override
   public boolean isReadable() {
      return this.buf.isReadable();
   }

   @Override
   public ByteBuffer nioBuffer() {
      return this.buf.nioBuffer().order(this.order);
   }

   @Override
   public int readerIndex() {
      return this.buf.readerIndex();
   }

   @Override
   public ByteBuf writeBytes(byte[] var1, int var2, int var3) {
      this.buf.writeBytes(var1, var2, var3);
      return this;
   }

   @Override
   public ByteBuf resetWriterIndex() {
      this.buf.resetWriterIndex();
      return this;
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuf var2, int var3, int var4) {
      this.buf.getBytes(var1, var2, var3, var4);
      return this;
   }

   @Override
   public short getShort(int var1) {
      return ByteBufUtil.swapShort(this.buf.getShort(var1));
   }

   @Override
   public int bytesBefore(int var1, byte var2) {
      return this.buf.bytesBefore(var1, var2);
   }

   @Override
   public int writerIndex() {
      return this.buf.writerIndex();
   }

   @Override
   public ByteBuf markWriterIndex() {
      this.buf.markWriterIndex();
      return this;
   }

   @Override
   public int nioBufferCount() {
      return this.buf.nioBufferCount();
   }

   @Override
   public ByteBuf readBytes(byte[] var1, int var2, int var3) {
      this.buf.readBytes(var1, var2, var3);
      return this;
   }

   @Override
   public int setBytes(int var1, InputStream var2, int var3) {
      return this.buf.setBytes(var1, var2, var3);
   }

   @Override
   public ByteBuf skipBytes(int var1) {
      this.buf.skipBytes(var1);
      return this;
   }

   @Override
   public int ensureWritable(int var1, boolean var2) {
      return this.buf.ensureWritable(var1, var2);
   }

   @Override
   public ByteBuf setInt(int var1, int var2) {
      this.buf.setInt(var1, ByteBufUtil.swapInt(var2));
      return this;
   }

   @Override
   public ByteBuf resetReaderIndex() {
      this.buf.resetReaderIndex();
      return this;
   }

   @Override
   public int bytesBefore(int var1, int var2, byte var3) {
      return this.buf.bytesBefore(var1, var2, var3);
   }

   @Override
   public ByteBuf setShort(int var1, int var2) {
      this.buf.setShort(var1, ByteBufUtil.swapShort((short)var2));
      return this;
   }

   @Override
   public byte[] array() {
      return this.buf.array();
   }

   @Override
   public ByteBuf readBytes(ByteBuf var1) {
      this.buf.readBytes(var1);
      return this;
   }

   @Override
   public ByteBuf order(ByteOrder var1) {
      if (var1 == null) {
         throw new NullPointerException("endianness");
      } else {
         return (ByteBuf)(var1 == this.order ? this : this.buf);
      }
   }

   @Override
   public ByteBuf writeBytes(ByteBuf var1) {
      this.buf.writeBytes(var1);
      return this;
   }

   @Override
   public int readUnsignedShort() {
      return this.readShort() & 65535;
   }

   @Override
   public ByteBuf writeBoolean(boolean var1) {
      this.buf.writeBoolean(var1);
      return this;
   }

   @Override
   public ByteBuf setDouble(int var1, double var2) {
      this.setLong(var1, Double.doubleToRawLongBits(var2));
      return this;
   }

   @Override
   public int indexOf(int var1, int var2, byte var3) {
      return this.buf.indexOf(var1, var2, var3);
   }

   @Override
   public ByteBuf readSlice(int var1) {
      return this.buf.readSlice(var1).order(this.order);
   }

   @Override
   public ByteBuf writerIndex(int var1) {
      this.buf.writerIndex(var1);
      return this;
   }

   @Override
   public int maxWritableBytes() {
      return this.buf.maxWritableBytes();
   }

   @Override
   public ByteBuf setMedium(int var1, int var2) {
      this.buf.setMedium(var1, ByteBufUtil.swapMedium(var2));
      return this;
   }

   @Override
   public int bytesBefore(byte var1) {
      return this.buf.bytesBefore(var1);
   }

   @Override
   public int hashCode() {
      return this.buf.hashCode();
   }

   @Override
   public ByteBuf capacity(int var1) {
      this.buf.capacity(var1);
      return this;
   }

   @Override
   public ByteBuf copy(int var1, int var2) {
      return this.buf.copy(var1, var2).order(this.order);
   }

   @Override
   public float readFloat() {
      return Float.intBitsToFloat(this.readInt());
   }

   @Override
   public String toString(int var1, int var2, Charset var3) {
      return this.buf.toString(var1, var2, var3);
   }

   @Override
   public ByteBuf duplicate() {
      return this.buf.duplicate().order(this.order);
   }

   @Override
   public ByteBuf setZero(int var1, int var2) {
      this.buf.setZero(var1, var2);
      return this;
   }

   @Override
   public ByteBuf setFloat(int var1, float var2) {
      this.setInt(var1, Float.floatToRawIntBits(var2));
      return this;
   }

   @Override
   public ByteBuf writeMedium(int var1) {
      this.buf.writeMedium(ByteBufUtil.swapMedium(var1));
      return this;
   }

   @Override
   public int readableBytes() {
      return this.buf.readableBytes();
   }

   @Override
   public ByteBuf markReaderIndex() {
      this.buf.markReaderIndex();
      return this;
   }

   @Override
   public ByteBuf getBytes(int var1, byte[] var2, int var3, int var4) {
      this.buf.getBytes(var1, var2, var3, var4);
      return this;
   }

   @Override
   public boolean release(int var1) {
      return this.buf.release(var1);
   }

   @Override
   public int readUnsignedMedium() {
      return this.readMedium() & 16777215;
   }

   public SwappedByteBuf(ByteBuf var1) {
      if (var1 == null) {
         throw new NullPointerException("buf");
      } else {
         this.buf = var1;
         if (var1.order() == ByteOrder.BIG_ENDIAN) {
            this.order = ByteOrder.LITTLE_ENDIAN;
         } else {
            this.order = ByteOrder.BIG_ENDIAN;
         }
      }
   }

   @Override
   public String toString() {
      return "Swapped(" + this.buf.toString() + ')';
   }

   @Override
   public int getMedium(int var1) {
      return ByteBufUtil.swapMedium(this.buf.getMedium(var1));
   }

   @Override
   public ByteBuf setBytes(int var1, byte[] var2) {
      this.buf.setBytes(var1, var2);
      return this;
   }

   @Override
   public int readBytes(GatheringByteChannel var1, int var2) {
      return this.buf.readBytes(var1, var2);
   }

   @Override
   public ByteBuf readBytes(ByteBuffer var1) {
      this.buf.readBytes(var1);
      return this;
   }

   @Override
   public ByteBuf readBytes(byte[] var1) {
      this.buf.readBytes(var1);
      return this;
   }

   @Override
   public int readMedium() {
      return ByteBufUtil.swapMedium(this.buf.readMedium());
   }

   @Override
   public int writableBytes() {
      return this.buf.writableBytes();
   }

   @Override
   public int setBytes(int var1, ScatteringByteChannel var2, int var3) {
      return this.buf.setBytes(var1, var2, var3);
   }

   @Override
   public ByteBuf readBytes(ByteBuf var1, int var2) {
      this.buf.readBytes(var1, var2);
      return this;
   }

   @Override
   public ByteBuf writeFloat(float var1) {
      this.writeInt(Float.floatToRawIntBits(var1));
      return this;
   }

   @Override
   public ByteBuf readerIndex(int var1) {
      this.buf.readerIndex(var1);
      return this;
   }

   @Override
   public int getUnsignedShort(int var1) {
      return this.getShort(var1) & 65535;
   }

   @Override
   public ByteBuf setLong(int var1, long var2) {
      this.buf.setLong(var1, ByteBufUtil.swapLong(var2));
      return this;
   }

   @Override
   public int readInt() {
      return ByteBufUtil.swapInt(this.buf.readInt());
   }

   @Override
   public ByteBuf writeChar(int var1) {
      this.writeShort(var1);
      return this;
   }

   @Override
   public ByteBuf setChar(int var1, int var2) {
      this.setShort(var1, var2);
      return this;
   }

   @Override
   public int forEachByteDesc(int var1, int var2, ByteBufProcessor var3) {
      return this.buf.forEachByteDesc(var1, var2, var3);
   }

   @Override
   public boolean isDirect() {
      return this.buf.isDirect();
   }

   @Override
   public ByteOrder order() {
      return this.order;
   }

   @Override
   public ByteBuf slice() {
      return this.buf.slice().order(this.order);
   }

   @Override
   public boolean getBoolean(int var1) {
      return this.buf.getBoolean(var1);
   }

   @Override
   public ByteBuf readBytes(OutputStream var1, int var2) {
      this.buf.readBytes(var1, var2);
      return this;
   }

   @Override
   public ByteBuf readBytes(int var1) {
      return this.buf.readBytes(var1).order(this.order());
   }

   @Override
   public byte getByte(int var1) {
      return this.buf.getByte(var1);
   }

   @Override
   public ByteBuf setBytes(int var1, byte[] var2, int var3, int var4) {
      this.buf.setBytes(var1, var2, var3, var4);
      return this;
   }

   @Override
   public ByteBuf discardReadBytes() {
      this.buf.discardReadBytes();
      return this;
   }

   @Override
   public ByteBufAllocator alloc() {
      return this.buf.alloc();
   }

   @Override
   public int forEachByte(int var1, int var2, ByteBufProcessor var3) {
      return this.buf.forEachByte(var1, var2, var3);
   }

   @Override
   public ByteBuf writeZero(int var1) {
      this.buf.writeZero(var1);
      return this;
   }

   @Override
   public char getChar(int var1) {
      return (char)this.getShort(var1);
   }

   @Override
   public int refCnt() {
      return this.buf.refCnt();
   }

   @Override
   public char readChar() {
      return (char)this.readShort();
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2, int var3) {
      this.buf.setBytes(var1, var2, var3);
      return this;
   }

   @Override
   public int writeBytes(ScatteringByteChannel var1, int var2) {
      return this.buf.writeBytes(var1, var2);
   }

   @Override
   public ByteBuf unwrap() {
      return this.buf.unwrap();
   }

   @Override
   public int getInt(int var1) {
      return ByteBufUtil.swapInt(this.buf.getInt(var1));
   }

   @Override
   public long getUnsignedInt(int var1) {
      return this.getInt(var1) & 4294967295L & 4294967295L;
   }

   @Override
   public int forEachByteDesc(ByteBufProcessor var1) {
      return this.buf.forEachByteDesc(var1);
   }

   @Override
   public ByteBuf writeBytes(ByteBuffer var1) {
      this.buf.writeBytes(var1);
      return this;
   }

   @Override
   public ByteBuf writeInt(int var1) {
      this.buf.writeInt(ByteBufUtil.swapInt(var1));
      return this;
   }

   @Override
   public short readShort() {
      return ByteBufUtil.swapShort(this.buf.readShort());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else {
         return var1 instanceof ByteBuf ? ByteBufUtil.equals(this, (ByteBuf)var1) : false;
      }
   }

   @Override
   public ByteBuffer internalNioBuffer(int var1, int var2) {
      return this.nioBuffer(var1, var2);
   }

   @Override
   public int compareTo(ByteBuf var1) {
      return ByteBufUtil.compare(this, var1);
   }

   @Override
   public ByteBuf retain(int var1) {
      this.buf.retain(var1);
      return this;
   }

   @Override
   public short getUnsignedByte(int var1) {
      return this.buf.getUnsignedByte(var1);
   }

   @Override
   public int maxCapacity() {
      return this.buf.maxCapacity();
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuffer var2) {
      this.buf.getBytes(var1, var2);
      return this;
   }

   @Override
   public boolean release() {
      return this.buf.release();
   }

   @Override
   public boolean isWritable(int var1) {
      return this.buf.isWritable(var1);
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuf var2, int var3) {
      this.buf.getBytes(var1, var2, var3);
      return this;
   }

   @Override
   public int capacity() {
      return this.buf.capacity();
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2, int var3, int var4) {
      this.buf.setBytes(var1, var2, var3, var4);
      return this;
   }

   @Override
   public ByteBuf writeBytes(ByteBuf var1, int var2) {
      this.buf.writeBytes(var1, var2);
      return this;
   }

   @Override
   public int arrayOffset() {
      return this.buf.arrayOffset();
   }

   @Override
   public int getBytes(int var1, GatheringByteChannel var2, int var3) {
      return this.buf.getBytes(var1, var2, var3);
   }

   @Override
   public ByteBuf writeBytes(ByteBuf var1, int var2, int var3) {
      this.buf.writeBytes(var1, var2, var3);
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
   public ByteBuf writeBytes(byte[] var1) {
      this.buf.writeBytes(var1);
      return this;
   }

   @Override
   public boolean hasMemoryAddress() {
      return this.buf.hasMemoryAddress();
   }

   @Override
   public ByteBuf copy() {
      return this.buf.copy().order(this.order);
   }

   @Override
   public ByteBuf ensureWritable(int var1) {
      this.buf.ensureWritable(var1);
      return this;
   }

   @Override
   public ByteBuf writeByte(int var1) {
      this.buf.writeByte(var1);
      return this;
   }

   @Override
   public ByteBuf setByte(int var1, int var2) {
      this.buf.setByte(var1, var2);
      return this;
   }

   @Override
   public ByteBuf retain() {
      this.buf.retain();
      return this;
   }

   @Override
   public ByteBuf discardSomeReadBytes() {
      this.buf.discardSomeReadBytes();
      return this;
   }

   @Override
   public ByteBuf writeDouble(double var1) {
      this.writeLong(Double.doubleToRawLongBits(var1));
      return this;
   }

   @Override
   public long getLong(int var1) {
      return ByteBufUtil.swapLong(this.buf.getLong(var1));
   }

   @Override
   public ByteBuf readBytes(ByteBuf var1, int var2, int var3) {
      this.buf.readBytes(var1, var2, var3);
      return this;
   }

   @Override
   public int writeBytes(InputStream var1, int var2) {
      return this.buf.writeBytes(var1, var2);
   }

   @Override
   public byte readByte() {
      return this.buf.readByte();
   }

   @Override
   public ByteBuf slice(int var1, int var2) {
      return this.buf.slice(var1, var2).order(this.order);
   }

   @Override
   public ByteBuf setIndex(int var1, int var2) {
      this.buf.setIndex(var1, var2);
      return this;
   }

   @Override
   public boolean isReadable(int var1) {
      return this.buf.isReadable(var1);
   }

   @Override
   public short readUnsignedByte() {
      return this.buf.readUnsignedByte();
   }

   @Override
   public long readLong() {
      return ByteBufUtil.swapLong(this.buf.readLong());
   }

   @Override
   public ByteBuf writeLong(long var1) {
      this.buf.writeLong(ByteBufUtil.swapLong(var1));
      return this;
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2) {
      this.buf.setBytes(var1, var2);
      return this;
   }

   @Override
   public long readUnsignedInt() {
      return this.readInt() & 4294967295L & 4294967295L;
   }

   @Override
   public ByteBuf getBytes(int var1, byte[] var2) {
      this.buf.getBytes(var1, var2);
      return this;
   }

   @Override
   public long memoryAddress() {
      return this.buf.memoryAddress();
   }

   @Override
   public double readDouble() {
      return Double.longBitsToDouble(this.readLong());
   }

   @Override
   public ByteBuffer[] nioBuffers() {
      ByteBuffer[] var1 = this.buf.nioBuffers();

      for (int var2 = 0; var2 < var1.length; var2++) {
         var1[var2] = var1[var2].order(this.order);
      }

      return var1;
   }

   @Override
   public ByteBuffer nioBuffer(int var1, int var2) {
      return this.buf.nioBuffer(var1, var2).order(this.order);
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuffer var2) {
      this.buf.setBytes(var1, var2);
      return this;
   }

   @Override
   public ByteBuf clear() {
      this.buf.clear();
      return this;
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
   public String toString(Charset var1) {
      return this.buf.toString(var1);
   }

   @Override
   public float getFloat(int var1) {
      return Float.intBitsToFloat(this.getInt(var1));
   }

   @Override
   public int getUnsignedMedium(int var1) {
      return this.getMedium(var1) & 16777215;
   }

   @Override
   public boolean readBoolean() {
      return this.buf.readBoolean();
   }

   @Override
   public ByteBuf getBytes(int var1, OutputStream var2, int var3) {
      this.buf.getBytes(var1, var2, var3);
      return this;
   }

   @Override
   public ByteBuf writeShort(int var1) {
      this.buf.writeShort(ByteBufUtil.swapShort((short)var1));
      return this;
   }

   @Override
   public ByteBuffer[] nioBuffers(int var1, int var2) {
      ByteBuffer[] var3 = this.buf.nioBuffers(var1, var2);

      for (int var4 = 0; var4 < var3.length; var4++) {
         var3[var4] = var3[var4].order(this.order);
      }

      return var3;
   }
}
