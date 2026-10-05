package io.netty.buffer;

import io.netty.util.ResourceLeak;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;

public class AdvancedLeakAwareByteBuf extends WrappedByteBuf {
   public ResourceLeak leak;
   public WorldGenBlockBlob __junk3414423244262890761;
   public ColorizerGrass __junk585447407609811232;

   @Override
   public ByteBuf setZero(int var1, int var2) {
      this.leak.record();
      return super.setZero(var1, var2);
   }

   @Override
   public int nioBufferCount() {
      this.leak.record();
      return super.nioBufferCount();
   }

   @Override
   public ByteBuf writeBytes(byte[] var1) {
      this.leak.record();
      return super.writeBytes(var1);
   }

   @Override
   public ByteBuf slice() {
      this.leak.record();
      return new AdvancedLeakAwareByteBuf(super.slice(), this.leak);
   }

   @Override
   public char getChar(int var1) {
      this.leak.record();
      return super.getChar(var1);
   }

   @Override
   public ByteBuf readBytes(byte[] var1, int var2, int var3) {
      this.leak.record();
      return super.readBytes(var1, var2, var3);
   }

   @Override
   public ByteBuf readBytes(ByteBuf var1, int var2) {
      this.leak.record();
      return super.readBytes(var1, var2);
   }

   @Override
   public ByteBuf readBytes(ByteBuf var1) {
      this.leak.record();
      return super.readBytes(var1);
   }

   @Override
   public int bytesBefore(int var1, int var2, byte var3) {
      this.leak.record();
      return super.bytesBefore(var1, var2, var3);
   }

   @Override
   public ByteBuf getBytes(int var1, OutputStream var2, int var3) {
      this.leak.record();
      return super.getBytes(var1, var2, var3);
   }

   @Override
   public ByteBuf retain(int var1) {
      this.leak.record();
      return super.retain(var1);
   }

   @Override
   public short getShort(int var1) {
      this.leak.record();
      return super.getShort(var1);
   }

   @Override
   public ByteBuf writeMedium(int var1) {
      this.leak.record();
      return super.writeMedium(var1);
   }

   @Override
   public float getFloat(int var1) {
      this.leak.record();
      return super.getFloat(var1);
   }

   @Override
   public int forEachByteDesc(int var1, int var2, ByteBufProcessor var3) {
      this.leak.record();
      return super.forEachByteDesc(var1, var2, var3);
   }

   @Override
   public ByteBuf setFloat(int var1, float var2) {
      this.leak.record();
      return super.setFloat(var1, var2);
   }

   @Override
   public short readUnsignedByte() {
      this.leak.record();
      return super.readUnsignedByte();
   }

   @Override
   public ByteBuf writeBytes(ByteBuf var1, int var2, int var3) {
      this.leak.record();
      return super.writeBytes(var1, var2, var3);
   }

   @Override
   public ByteBuf setBytes(int var1, byte[] var2) {
      this.leak.record();
      return super.setBytes(var1, var2);
   }

   @Override
   public ByteBuf order(ByteOrder var1) {
      this.leak.record();
      return this.order() == var1 ? this : new AdvancedLeakAwareByteBuf(super.order(var1), this.leak);
   }

   @Override
   public float readFloat() {
      this.leak.record();
      return super.readFloat();
   }

   @Override
   public ByteBuffer nioBuffer() {
      this.leak.record();
      return super.nioBuffer();
   }

   @Override
   public ByteBuf retain() {
      this.leak.record();
      return super.retain();
   }

   @Override
   public int getUnsignedShort(int var1) {
      this.leak.record();
      return super.getUnsignedShort(var1);
   }

   @Override
   public long readLong() {
      this.leak.record();
      return super.readLong();
   }

   @Override
   public ByteBuffer[] nioBuffers() {
      this.leak.record();
      return super.nioBuffers();
   }

   @Override
   public ByteBuf writeZero(int var1) {
      this.leak.record();
      return super.writeZero(var1);
   }

   @Override
   public ByteBuf writeBytes(byte[] var1, int var2, int var3) {
      this.leak.record();
      return super.writeBytes(var1, var2, var3);
   }

   @Override
   public ByteBuf writeFloat(float var1) {
      this.leak.record();
      return super.writeFloat(var1);
   }

   @Override
   public ByteBuf writeBytes(ByteBuffer var1) {
      this.leak.record();
      return super.writeBytes(var1);
   }

   @Override
   public int bytesBefore(byte var1) {
      this.leak.record();
      return super.bytesBefore(var1);
   }

   @Override
   public ByteBuf writeLong(long var1) {
      this.leak.record();
      return super.writeLong(var1);
   }

   @Override
   public long readUnsignedInt() {
      this.leak.record();
      return super.readUnsignedInt();
   }

   @Override
   public int getBytes(int var1, GatheringByteChannel var2, int var3) {
      this.leak.record();
      return super.getBytes(var1, var2, var3);
   }

   @Override
   public char readChar() {
      this.leak.record();
      return super.readChar();
   }

   @Override
   public long getLong(int var1) {
      this.leak.record();
      return super.getLong(var1);
   }

   @Override
   public ByteBuf skipBytes(int var1) {
      this.leak.record();
      return super.skipBytes(var1);
   }

   @Override
   public int bytesBefore(int var1, byte var2) {
      this.leak.record();
      return super.bytesBefore(var1, var2);
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuf var2, int var3) {
      this.leak.record();
      return super.getBytes(var1, var2, var3);
   }

   @Override
   public double getDouble(int var1) {
      this.leak.record();
      return super.getDouble(var1);
   }

   public AdvancedLeakAwareByteBuf(ByteBuf var1, ResourceLeak var2) {
      super(var1);
      this.leak = var2;
   }

   @Override
   public byte getByte(int var1) {
      this.leak.record();
      return super.getByte(var1);
   }

   @Override
   public ByteBuf readBytes(int var1) {
      this.leak.record();
      return super.readBytes(var1);
   }

   @Override
   public ByteBuf readBytes(ByteBuffer var1) {
      this.leak.record();
      return super.readBytes(var1);
   }

   @Override
   public boolean getBoolean(int var1) {
      this.leak.record();
      return super.getBoolean(var1);
   }

   @Override
   public int writeBytes(InputStream var1, int var2) {
      this.leak.record();
      return super.writeBytes(var1, var2);
   }

   @Override
   public int indexOf(int var1, int var2, byte var3) {
      this.leak.record();
      return super.indexOf(var1, var2, var3);
   }

   @Override
   public int readMedium() {
      this.leak.record();
      return super.readMedium();
   }

   @Override
   public ByteBuffer[] nioBuffers(int var1, int var2) {
      this.leak.record();
      return super.nioBuffers(var1, var2);
   }

   @Override
   public String toString(int var1, int var2, Charset var3) {
      this.leak.record();
      return super.toString(var1, var2, var3);
   }

   @Override
   public ByteBuf writeInt(int var1) {
      this.leak.record();
      return super.writeInt(var1);
   }

   @Override
   public ByteBuf writeBoolean(boolean var1) {
      this.leak.record();
      return super.writeBoolean(var1);
   }

   @Override
   public short readShort() {
      this.leak.record();
      return super.readShort();
   }

   @Override
   public ByteBuf getBytes(int var1, byte[] var2) {
      this.leak.record();
      return super.getBytes(var1, var2);
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2, int var3) {
      this.leak.record();
      return super.setBytes(var1, var2, var3);
   }

   @Override
   public boolean release() {
      boolean var1 = super.release();
      if (var1) {
         this.leak.close();
      } else {
         this.leak.record();
      }

      return var1;
   }

   @Override
   public ByteBuf writeByte(int var1) {
      this.leak.record();
      return super.writeByte(var1);
   }

   @Override
   public ByteBuf setShort(int var1, int var2) {
      this.leak.record();
      return super.setShort(var1, var2);
   }

   @Override
   public ByteBuf ensureWritable(int var1) {
      this.leak.record();
      return super.ensureWritable(var1);
   }

   @Override
   public ByteBuffer nioBuffer(int var1, int var2) {
      this.leak.record();
      return super.nioBuffer(var1, var2);
   }

   @Override
   public ByteBuf readBytes(OutputStream var1, int var2) {
      this.leak.record();
      return super.readBytes(var1, var2);
   }

   @Override
   public int readUnsignedMedium() {
      this.leak.record();
      return super.readUnsignedMedium();
   }

   @Override
   public int forEachByte(int var1, int var2, ByteBufProcessor var3) {
      this.leak.record();
      return super.forEachByte(var1, var2, var3);
   }

   @Override
   public int getMedium(int var1) {
      this.leak.record();
      return super.getMedium(var1);
   }

   @Override
   public int ensureWritable(int var1, boolean var2) {
      this.leak.record();
      return super.ensureWritable(var1, var2);
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2, int var3, int var4) {
      this.leak.record();
      return super.setBytes(var1, var2, var3, var4);
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuffer var2) {
      this.leak.record();
      return super.setBytes(var1, var2);
   }

   @Override
   public String toString(Charset var1) {
      this.leak.record();
      return super.toString(var1);
   }

   @Override
   public ByteBuf slice(int var1, int var2) {
      this.leak.record();
      return new AdvancedLeakAwareByteBuf(super.slice(var1, var2), this.leak);
   }

   @Override
   public ByteBuf readSlice(int var1) {
      this.leak.record();
      return new AdvancedLeakAwareByteBuf(super.readSlice(var1), this.leak);
   }

   @Override
   public ByteBuf setChar(int var1, int var2) {
      this.leak.record();
      return super.setChar(var1, var2);
   }

   @Override
   public ByteBuf writeBytes(ByteBuf var1, int var2) {
      this.leak.record();
      return super.writeBytes(var1, var2);
   }

   @Override
   public int readBytes(GatheringByteChannel var1, int var2) {
      this.leak.record();
      return super.readBytes(var1, var2);
   }

   @Override
   public ByteBuffer internalNioBuffer(int var1, int var2) {
      this.leak.record();
      return super.internalNioBuffer(var1, var2);
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuffer var2) {
      this.leak.record();
      return super.getBytes(var1, var2);
   }

   @Override
   public ByteBuf capacity(int var1) {
      this.leak.record();
      return super.capacity(var1);
   }

   @Override
   public ByteBuf readBytes(byte[] var1) {
      this.leak.record();
      return super.readBytes(var1);
   }

   @Override
   public long getUnsignedInt(int var1) {
      this.leak.record();
      return super.getUnsignedInt(var1);
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2) {
      this.leak.record();
      return super.setBytes(var1, var2);
   }

   @Override
   public byte readByte() {
      this.leak.record();
      return super.readByte();
   }

   @Override
   public int setBytes(int var1, ScatteringByteChannel var2, int var3) {
      this.leak.record();
      return super.setBytes(var1, var2, var3);
   }

   @Override
   public ByteBuf duplicate() {
      this.leak.record();
      return new AdvancedLeakAwareByteBuf(super.duplicate(), this.leak);
   }

   @Override
   public ByteBuf copy() {
      this.leak.record();
      return super.copy();
   }

   @Override
   public ByteBuf setMedium(int var1, int var2) {
      this.leak.record();
      return super.setMedium(var1, var2);
   }

   @Override
   public ByteBuf readBytes(ByteBuf var1, int var2, int var3) {
      this.leak.record();
      return super.readBytes(var1, var2, var3);
   }

   @Override
   public short getUnsignedByte(int var1) {
      this.leak.record();
      return super.getUnsignedByte(var1);
   }

   @Override
   public ByteBuf writeBytes(ByteBuf var1) {
      this.leak.record();
      return super.writeBytes(var1);
   }

   @Override
   public ByteBuf setBoolean(int var1, boolean var2) {
      this.leak.record();
      return super.setBoolean(var1, var2);
   }

   @Override
   public int forEachByte(ByteBufProcessor var1) {
      this.leak.record();
      return super.forEachByte(var1);
   }

   @Override
   public ByteBuf discardReadBytes() {
      this.leak.record();
      return super.discardReadBytes();
   }

   @Override
   public int getUnsignedMedium(int var1) {
      this.leak.record();
      return super.getUnsignedMedium(var1);
   }

   @Override
   public ByteBuf getBytes(int var1, byte[] var2, int var3, int var4) {
      this.leak.record();
      return super.getBytes(var1, var2, var3, var4);
   }

   @Override
   public int setBytes(int var1, InputStream var2, int var3) {
      this.leak.record();
      return super.setBytes(var1, var2, var3);
   }

   @Override
   public ByteBuf copy(int var1, int var2) {
      this.leak.record();
      return super.copy(var1, var2);
   }

   @Override
   public int readUnsignedShort() {
      this.leak.record();
      return super.readUnsignedShort();
   }

   @Override
   public ByteBuf writeShort(int var1) {
      this.leak.record();
      return super.writeShort(var1);
   }

   @Override
   public ByteBuf setDouble(int var1, double var2) {
      this.leak.record();
      return super.setDouble(var1, var2);
   }

   @Override
   public ByteBuf setInt(int var1, int var2) {
      this.leak.record();
      return super.setInt(var1, var2);
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuf var2, int var3, int var4) {
      this.leak.record();
      return super.getBytes(var1, var2, var3, var4);
   }

   @Override
   public boolean readBoolean() {
      this.leak.record();
      return super.readBoolean();
   }

   @Override
   public int forEachByteDesc(ByteBufProcessor var1) {
      this.leak.record();
      return super.forEachByteDesc(var1);
   }

   @Override
   public int getInt(int var1) {
      this.leak.record();
      return super.getInt(var1);
   }

   @Override
   public int writeBytes(ScatteringByteChannel var1, int var2) {
      this.leak.record();
      return super.writeBytes(var1, var2);
   }

   @Override
   public ByteBuf writeDouble(double var1) {
      this.leak.record();
      return super.writeDouble(var1);
   }

   @Override
   public ByteBuf setLong(int var1, long var2) {
      this.leak.record();
      return super.setLong(var1, var2);
   }

   @Override
   public ByteBuf writeChar(int var1) {
      this.leak.record();
      return super.writeChar(var1);
   }

   @Override
   public boolean release(int var1) {
      boolean var2 = super.release(var1);
      if (var2) {
         this.leak.close();
      } else {
         this.leak.record();
      }

      return var2;
   }

   @Override
   public double readDouble() {
      this.leak.record();
      return super.readDouble();
   }

   @Override
   public ByteBuf setBytes(int var1, byte[] var2, int var3, int var4) {
      this.leak.record();
      return super.setBytes(var1, var2, var3, var4);
   }

   @Override
   public ByteBuf discardSomeReadBytes() {
      this.leak.record();
      return super.discardSomeReadBytes();
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuf var2) {
      this.leak.record();
      return super.getBytes(var1, var2);
   }

   @Override
   public ByteBuf setByte(int var1, int var2) {
      this.leak.record();
      return super.setByte(var1, var2);
   }

   @Override
   public int readInt() {
      this.leak.record();
      return super.readInt();
   }
}
