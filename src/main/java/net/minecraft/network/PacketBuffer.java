package net.minecraft.network;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.ByteBufProcessor;
import io.netty.handler.codec.DecoderException;
import io.netty.handler.codec.EncoderException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.nio.charset.Charset;
import java.util.UUID;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTSizeTracker;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;

public class PacketBuffer extends ByteBuf {
   public ByteBuf recoveredField3513;

   @Override
   public int writeBytes(InputStream var1, int var2) throws java.io.IOException {
      return this.recoveredField3513.writeBytes(var1, var2);
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2, int var3) {
      return this.recoveredField3513.setBytes(var1, var2, var3);
   }

   @Override
   public ByteBuf readBytes(byte[] var1) {
      return this.recoveredField3513.readBytes(var1);
   }

   @Override
   public ByteBuf resetWriterIndex() {
      return this.recoveredField3513.resetWriterIndex();
   }

   @Override
   public int capacity() {
      return this.recoveredField3513.capacity();
   }

   @Override
   public ByteBuf skipBytes(int var1) {
      return this.recoveredField3513.skipBytes(var1);
   }

   @Override
   public int maxWritableBytes() {
      return this.recoveredField3513.maxWritableBytes();
   }

   @Override
   public short getUnsignedByte(int var1) {
      return this.recoveredField3513.getUnsignedByte(var1);
   }

   @Override
   public int getInt(int var1) {
      return this.recoveredField3513.getInt(var1);
   }

   @Override
   public long getUnsignedInt(int var1) {
      return this.recoveredField3513.getUnsignedInt(var1);
   }

   @Override
   public int ensureWritable(int var1, boolean var2) {
      return this.recoveredField3513.ensureWritable(var1, var2);
   }

   @Override
   public ByteBuf getBytes(int var1, byte[] var2) {
      return this.recoveredField3513.getBytes(var1, var2);
   }

   @Override
   public ByteBuffer[] nioBuffers() {
      return this.recoveredField3513.nioBuffers();
   }

   @Override
   public ByteBuf writeChar(int var1) {
      return this.recoveredField3513.writeChar(var1);
   }

   @Override
   public boolean hasMemoryAddress() {
      return this.recoveredField3513.hasMemoryAddress();
   }

   @Override
   public int arrayOffset() {
      return this.recoveredField3513.arrayOffset();
   }

   @Override
   public int forEachByteDesc(int var1, int var2, ByteBufProcessor var3) {
      return this.recoveredField3513.forEachByteDesc(var1, var2, var3);
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2) {
      return this.recoveredField3513.setBytes(var1, var2);
   }

   @Override
   public int getUnsignedMedium(int var1) {
      return this.recoveredField3513.getUnsignedMedium(var1);
   }

   @Override
   public ByteBuf copy(int var1, int var2) {
      return this.recoveredField3513.copy(var1, var2);
   }

   @Override
   public ByteBuf capacity(int var1) {
      return this.recoveredField3513.capacity(var1);
   }

   @Override
   public ByteBuf readBytes(int var1) {
      return this.recoveredField3513.readBytes(var1);
   }

   public <T extends Enum<T>> T readEnumValue(Class<T> var1) {
      return (T)var1.getEnumConstants()[this.readVarIntFromBuffer()];
   }

   @Override
   public ByteBuffer internalNioBuffer(int var1, int var2) {
      return this.recoveredField3513.internalNioBuffer(var1, var2);
   }

   @Override
   public int readableBytes() {
      return this.recoveredField3513.readableBytes();
   }

   public byte[] readByteArray() {
      byte[] var1 = new byte[this.readVarIntFromBuffer()];
      this.readBytes(var1);
      return var1;
   }

   @Override
   public int maxCapacity() {
      return this.recoveredField3513.maxCapacity();
   }

   @Override
   public ByteBuf discardReadBytes() {
      return this.recoveredField3513.discardReadBytes();
   }

   @Override
   public ByteBuf readBytes(byte[] var1, int var2, int var3) {
      return this.recoveredField3513.readBytes(var1, var2, var3);
   }

   @Override
   public ByteBuf writeBytes(ByteBuf var1) {
      return this.recoveredField3513.writeBytes(var1);
   }

   @Override
   public ByteBuf copy() {
      return this.recoveredField3513.copy();
   }

   @Override
   public ByteBuf retain() {
      return this.recoveredField3513.retain();
   }

   @Override
   public int readInt() {
      return this.recoveredField3513.readInt();
   }

   @Override
   public boolean isWritable(int var1) {
      return this.recoveredField3513.isWritable(var1);
   }

   @Override
   public short readShort() {
      return this.recoveredField3513.readShort();
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuffer var2) {
      return this.recoveredField3513.setBytes(var1, var2);
   }

   @Override
   public ByteBuf readBytes(ByteBuf var1) {
      return this.recoveredField3513.readBytes(var1);
   }

   @Override
   public double getDouble(int var1) {
      return this.recoveredField3513.getDouble(var1);
   }

   @Override
   public ByteBuf setIndex(int var1, int var2) {
      return this.recoveredField3513.setIndex(var1, var2);
   }

   public ItemStack readItemStackFromBuffer() throws java.io.IOException {
      ItemStack var1 = null;
      int var2 = this.readShort();
      if (var2 >= 0) {
         byte var3 = this.readByte();
         short var4 = this.readShort();
         var1 = new ItemStack(Item.getItemById(var2), var3, var4);
         var1.setTagCompound(this.readNBTTagCompoundFromBuffer());
      }

      return var1;
   }

   @Override
   public byte[] array() {
      return this.recoveredField3513.array();
   }

   @Override
   public boolean release(int var1) {
      return this.recoveredField3513.release(var1);
   }

   @Override
   public ByteBuf setLong(int var1, long var2) {
      return this.recoveredField3513.setLong(var1, var2);
   }

   @Override
   public int bytesBefore(int var1, byte var2) {
      return this.recoveredField3513.bytesBefore(var1, var2);
   }

   @Override
   public ByteBuf writeInt(int var1) {
      return this.recoveredField3513.writeInt(var1);
   }

   @Override
   public boolean equals(Object var1) {
      return this.recoveredField3513.equals(var1);
   }

   @Override
   public boolean isDirect() {
      return this.recoveredField3513.isDirect();
   }

   @Override
   public ByteBuf setMedium(int var1, int var2) {
      return this.recoveredField3513.setMedium(var1, var2);
   }

   @Override
   public ByteBuf writeBytes(byte[] var1, int var2, int var3) {
      return this.recoveredField3513.writeBytes(var1, var2, var3);
   }

   @Override
   public int bytesBefore(byte var1) {
      return this.recoveredField3513.bytesBefore(var1);
   }

   @Override
   public ByteBuf readSlice(int var1) {
      return this.recoveredField3513.readSlice(var1);
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuf var2) {
      return this.recoveredField3513.getBytes(var1, var2);
   }

   public static int getVarIntSize(int var0) {
      for (int var1 = 1; var1 < 5; var1++) {
         if ((var0 & -1 << var1 * 7) == 0) {
            return var1;
         }
      }

      return 5;
   }

   @Override
   public ByteBuf setBoolean(int var1, boolean var2) {
      return this.recoveredField3513.setBoolean(var1, var2);
   }

   public void writeUuid(UUID var1) {
      this.writeLong(var1.getMostSignificantBits());
      this.writeLong(var1.getLeastSignificantBits());
   }

   @Override
   public int writableBytes() {
      return this.recoveredField3513.writableBytes();
   }

   @Override
   public int readMedium() {
      return this.recoveredField3513.readMedium();
   }

   @Override
   public boolean readBoolean() {
      return this.recoveredField3513.readBoolean();
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuf var2, int var3) {
      return this.recoveredField3513.getBytes(var1, var2, var3);
   }

   @Override
   public ByteBuf setZero(int var1, int var2) {
      return this.recoveredField3513.setZero(var1, var2);
   }

   @Override
   public ByteBuf writeBoolean(boolean var1) {
      return this.recoveredField3513.writeBoolean(var1);
   }

   @Override
   public ByteBuf writeMedium(int var1) {
      return this.recoveredField3513.writeMedium(var1);
   }

   @Override
   public int forEachByteDesc(ByteBufProcessor var1) {
      return this.recoveredField3513.forEachByteDesc(var1);
   }

   @Override
   public ByteBuf writeByte(int var1) {
      return this.recoveredField3513.writeByte(var1);
   }

   @Override
   public int bytesBefore(int var1, int var2, byte var3) {
      return this.recoveredField3513.bytesBefore(var1, var2, var3);
   }

   @Override
   public byte getByte(int var1) {
      return this.recoveredField3513.getByte(var1);
   }

   public void writeChatComponent(IChatComponent var1) throws java.io.IOException {
      this.writeString(IChatComponent.Serializer.componentToJson(var1));
   }

   public void writeNBTTagCompoundToBuffer(NBTTagCompound var1) {
      if (var1 == null) {
         this.writeByte(0);
      } else {
         try {
            CompressedStreamTools.write(var1, new ByteBufOutputStream(this));
         } catch (IOException var3) {
            throw new EncoderException(var3);
         }
      }
   }

   @Override
   public long getLong(int var1) {
      return this.recoveredField3513.getLong(var1);
   }

   public void writeByteArray(byte[] var1) {
      this.writeVarIntToBuffer(var1.length);
      this.writeBytes(var1);
   }

   @Override
   public ByteBuf setChar(int var1, int var2) {
      return this.recoveredField3513.setChar(var1, var2);
   }

   @Override
   public int readerIndex() {
      return this.recoveredField3513.readerIndex();
   }

   @Override
   public ByteBuf writeLong(long var1) {
      return this.recoveredField3513.writeLong(var1);
   }

   @Override
   public ByteBuf setFloat(int var1, float var2) {
      return this.recoveredField3513.setFloat(var1, var2);
   }

   @Override
   public int indexOf(int var1, int var2, byte var3) {
      return this.recoveredField3513.indexOf(var1, var2, var3);
   }

   @Override
   public ByteBuf resetReaderIndex() {
      return this.recoveredField3513.resetReaderIndex();
   }

   @Override
   public boolean hasArray() {
      return this.recoveredField3513.hasArray();
   }

   @Override
   public ByteBuf slice() {
      return this.recoveredField3513.slice();
   }

   public PacketBuffer(ByteBuf var1) {
      this.recoveredField3513 = var1;
   }

   @Override
   public ByteBuf readBytes(ByteBuffer var1) {
      return this.recoveredField3513.readBytes(var1);
   }

   @Override
   public ByteBuf writeBytes(ByteBuf var1, int var2, int var3) {
      return this.recoveredField3513.writeBytes(var1, var2, var3);
   }

   @Override
   public char getChar(int var1) {
      return this.recoveredField3513.getChar(var1);
   }

   @Override
   public boolean getBoolean(int var1) {
      return this.recoveredField3513.getBoolean(var1);
   }

   @Override
   public ByteBuf readerIndex(int var1) {
      return this.recoveredField3513.readerIndex(var1);
   }

   @Override
   public String toString() {
      return this.recoveredField3513.toString();
   }

   @Override
   public int getUnsignedShort(int var1) {
      return this.recoveredField3513.getUnsignedShort(var1);
   }

   @Override
   public boolean isWritable() {
      return this.recoveredField3513.isWritable();
   }

   public void writeEnumValue(Enum<?> var1) {
      this.writeVarIntToBuffer(var1.ordinal());
   }

   @Override
   public ByteBuf retain(int var1) {
      return this.recoveredField3513.retain(var1);
   }

   @Override
   public ByteBuf getBytes(int var1, OutputStream var2, int var3) throws java.io.IOException {
      return this.recoveredField3513.getBytes(var1, var2, var3);
   }

   @Override
   public ByteBuf readBytes(ByteBuf var1, int var2, int var3) {
      return this.recoveredField3513.readBytes(var1, var2, var3);
   }

   @Override
   public ByteBuf ensureWritable(int var1) {
      return this.recoveredField3513.ensureWritable(var1);
   }

   public String readStringFromBuffer(int var1) {
      int var2 = this.readVarIntFromBuffer();
      if (var2 > var1 * 4) {
         throw new DecoderException("The received encoded string buffer length is longer than maximum allowed (" + var2 + " > " + var1 * 4 + ")");
      } else if (var2 < 0) {
         throw new DecoderException("The received encoded string buffer length is less than zero! Weird string!");
      } else {
         String var3 = new String(this.readBytes(var2).array(), Charsets.UTF_8);
         if (var3.length() > var1) {
            throw new DecoderException("The received string length is longer than maximum allowed (" + var2 + " > " + var1 + ")");
         } else {
            return var3;
         }
      }
   }

   @Override
   public ByteBuf setInt(int var1, int var2) {
      return this.recoveredField3513.setInt(var1, var2);
   }

   @Override
   public ByteBuf clear() {
      return this.recoveredField3513.clear();
   }

   @Override
   public boolean isReadable(int var1) {
      return this.recoveredField3513.isReadable(var1);
   }

   @Override
   public long readLong() {
      return this.recoveredField3513.readLong();
   }

   @Override
   public long memoryAddress() {
      return this.recoveredField3513.memoryAddress();
   }

   @Override
   public ByteBuf unwrap() {
      return this.recoveredField3513.unwrap();
   }

   @Override
   public short getShort(int var1) {
      return this.recoveredField3513.getShort(var1);
   }

   @Override
   public int writeBytes(ScatteringByteChannel var1, int var2) throws java.io.IOException {
      return this.recoveredField3513.writeBytes(var1, var2);
   }

   @Override
   public ByteBuffer nioBuffer() {
      return this.recoveredField3513.nioBuffer();
   }

   public UUID readUuid() {
      return new UUID(this.readLong(), this.readLong());
   }

   @Override
   public ByteBuf discardSomeReadBytes() {
      return this.recoveredField3513.discardSomeReadBytes();
   }

   @Override
   public float readFloat() {
      return this.recoveredField3513.readFloat();
   }

   @Override
   public int writerIndex() {
      return this.recoveredField3513.writerIndex();
   }

   @Override
   public ByteBuf setBytes(int var1, byte[] var2, int var3, int var4) {
      return this.recoveredField3513.setBytes(var1, var2, var3, var4);
   }

   public long readVarLong() {
      long var1 = 0L;
      int var3 = 0;

      byte var4;
      do {
         var4 = this.readByte();
         var1 |= (long)(var4 & 127) << var3++ * 7;
         if (var3 > 10) {
            throw new RuntimeException("VarLong too big");
         }
      } while ((var4 & 128) == 128);

      return var1;
   }

   @Override
   public ByteBuf markWriterIndex() {
      return this.recoveredField3513.markWriterIndex();
   }

   @Override
   public short readUnsignedByte() {
      return this.recoveredField3513.readUnsignedByte();
   }

   @Override
   public int getBytes(int var1, GatheringByteChannel var2, int var3) throws java.io.IOException {
      return this.recoveredField3513.getBytes(var1, var2, var3);
   }

   @Override
   public int getMedium(int var1) {
      return this.recoveredField3513.getMedium(var1);
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuffer var2) {
      return this.recoveredField3513.getBytes(var1, var2);
   }

   @Override
   public double readDouble() {
      return this.recoveredField3513.readDouble();
   }

   public void writeVarLong(long var1) {
      while ((var1 & -128L) != 0L) {
         this.writeByte((int)(var1 & 127L) | 128);
         var1 >>>= 7;
      }

      this.writeByte((int)var1);
   }

   @Override
   public int readBytes(GatheringByteChannel var1, int var2) throws java.io.IOException {
      return this.recoveredField3513.readBytes(var1, var2);
   }

   @Override
   public ByteBuf setByte(int var1, int var2) {
      return this.recoveredField3513.setByte(var1, var2);
   }

   public NBTTagCompound readNBTTagCompoundFromBuffer() throws java.io.IOException {
      int var1 = this.readerIndex();
      byte var2 = this.readByte();
      if (var2 == 0) {
         return null;
      } else {
         this.readerIndex(var1);
         return CompressedStreamTools.read(new ByteBufInputStream(this), new NBTSizeTracker(2097152L));
      }
   }

   @Override
   public ByteBuffer[] nioBuffers(int var1, int var2) {
      return this.recoveredField3513.nioBuffers(var1, var2);
   }

   @Override
   public ByteBuf order(ByteOrder var1) {
      return this.recoveredField3513.order(var1);
   }

   @Override
   public String toString(Charset var1) {
      return this.recoveredField3513.toString(var1);
   }

   @Override
   public ByteBuf setBytes(int var1, byte[] var2) {
      return this.recoveredField3513.setBytes(var1, var2);
   }

   @Override
   public ByteOrder order() {
      return this.recoveredField3513.order();
   }

   public int readVarIntFromBuffer() {
      int var1 = 0;
      int var2 = 0;

      byte var3;
      do {
         var3 = this.readByte();
         var1 |= (var3 & 127) << var2++ * 7;
         if (var2 > 5) {
            throw new RuntimeException("VarInt too big");
         }
      } while ((var3 & 128) == 128);

      return var1;
   }

   @Override
   public int forEachByte(ByteBufProcessor var1) {
      return this.recoveredField3513.forEachByte(var1);
   }

   @Override
   public char readChar() {
      return this.recoveredField3513.readChar();
   }

   @Override
   public ByteBuffer nioBuffer(int var1, int var2) {
      return this.recoveredField3513.nioBuffer(var1, var2);
   }

   @Override
   public long readUnsignedInt() {
      return this.recoveredField3513.readUnsignedInt();
   }

   @Override
   public int setBytes(int var1, InputStream var2, int var3) throws java.io.IOException {
      return this.recoveredField3513.setBytes(var1, var2, var3);
   }

   public PacketBuffer writeString(String var1) {
      byte[] var2 = var1.getBytes(Charsets.UTF_8);
      if (var2.length > 32767) {
         throw new EncoderException("String too big (was " + var1.length() + " bytes encoded, max " + 32767 + ")");
      } else {
         this.writeVarIntToBuffer(var2.length);
         this.writeBytes(var2);
         return this;
      }
   }

   @Override
   public ByteBuf writeBytes(ByteBuf var1, int var2) {
      return this.recoveredField3513.writeBytes(var1, var2);
   }

   @Override
   public int setBytes(int var1, ScatteringByteChannel var2, int var3) throws java.io.IOException {
      return this.recoveredField3513.setBytes(var1, var2, var3);
   }

   @Override
   public ByteBuf duplicate() {
      return this.recoveredField3513.duplicate();
   }

   @Override
   public ByteBuf setDouble(int var1, double var2) {
      return this.recoveredField3513.setDouble(var1, var2);
   }

   @Override
   public int readUnsignedShort() {
      return this.recoveredField3513.readUnsignedShort();
   }

   @Override
   public ByteBuf slice(int var1, int var2) {
      return this.recoveredField3513.slice(var1, var2);
   }

   @Override
   public int forEachByte(int var1, int var2, ByteBufProcessor var3) {
      return this.recoveredField3513.forEachByte(var1, var2, var3);
   }

   public void writeBlockPos(BlockPos var1) {
      this.writeLong(var1.toLong());
   }

   @Override
   public ByteBuf writeZero(int var1) {
      return this.recoveredField3513.writeZero(var1);
   }

   @Override
   public int refCnt() {
      return this.recoveredField3513.refCnt();
   }

   public void writeItemStackToBuffer(ItemStack var1) {
      if (var1 == null) {
         this.writeShort(-1);
      } else {
         this.writeShort(Item.getIdFromItem(var1.getItem()));
         this.writeByte(var1.stackSize);
         this.writeShort(var1.getMetadata());
         NBTTagCompound var2 = null;
         if (var1.getItem().isDamageable() || var1.getItem().getShareTag()) {
            var2 = var1.getTagCompound();
         }

         this.writeNBTTagCompoundToBuffer(var2);
      }
   }

   @Override
   public ByteBuf writerIndex(int var1) {
      return this.recoveredField3513.writerIndex(var1);
   }

   @Override
   public byte readByte() {
      return this.recoveredField3513.readByte();
   }

   @Override
   public ByteBuf writeBytes(ByteBuffer var1) {
      return this.recoveredField3513.writeBytes(var1);
   }

   @Override
   public ByteBuf writeShort(int var1) {
      return this.recoveredField3513.writeShort(var1);
   }

   @Override
   public int readUnsignedMedium() {
      return this.recoveredField3513.readUnsignedMedium();
   }

   @Override
   public ByteBuf markReaderIndex() {
      return this.recoveredField3513.markReaderIndex();
   }

   @Override
   public ByteBuf getBytes(int var1, ByteBuf var2, int var3, int var4) {
      return this.recoveredField3513.getBytes(var1, var2, var3, var4);
   }

   public BlockPos readBlockPos() {
      return BlockPos.fromLong(this.readLong());
   }

   @Override
   public boolean release() {
      return this.recoveredField3513.release();
   }

   @Override
   public ByteBufAllocator alloc() {
      return this.recoveredField3513.alloc();
   }

   @Override
   public ByteBuf readBytes(OutputStream var1, int var2) throws java.io.IOException {
      return this.recoveredField3513.readBytes(var1, var2);
   }

   @Override
   public ByteBuf writeBytes(byte[] var1) {
      return this.recoveredField3513.writeBytes(var1);
   }

   @Override
   public ByteBuf readBytes(ByteBuf var1, int var2) {
      return this.recoveredField3513.readBytes(var1, var2);
   }

   @Override
   public ByteBuf setBytes(int var1, ByteBuf var2, int var3, int var4) {
      return this.recoveredField3513.setBytes(var1, var2, var3, var4);
   }

   @Override
   public ByteBuf setShort(int var1, int var2) {
      return this.recoveredField3513.setShort(var1, var2);
   }

   @Override
   public int nioBufferCount() {
      return this.recoveredField3513.nioBufferCount();
   }

   @Override
   public ByteBuf writeFloat(float var1) {
      return this.recoveredField3513.writeFloat(var1);
   }

   public IChatComponent readChatComponent() throws java.io.IOException {
      return IChatComponent.Serializer.jsonToComponent(this.readStringFromBuffer(32767));
   }

   @Override
   public float getFloat(int var1) {
      return this.recoveredField3513.getFloat(var1);
   }

   public void writeVarIntToBuffer(int var1) {
      while ((var1 & -128) != 0) {
         this.writeByte(var1 & 127 | 128);
         var1 >>>= 7;
      }

      this.writeByte(var1);
   }

   @Override
   public String toString(int var1, int var2, Charset var3) {
      return this.recoveredField3513.toString(var1, var2, var3);
   }

   @Override
   public ByteBuf writeDouble(double var1) {
      return this.recoveredField3513.writeDouble(var1);
   }

   @Override
   public boolean isReadable() {
      return this.recoveredField3513.isReadable();
   }

   @Override
   public int hashCode() {
      return this.recoveredField3513.hashCode();
   }

   @Override
   public ByteBuf getBytes(int var1, byte[] var2, int var3, int var4) {
      return this.recoveredField3513.getBytes(var1, var2, var3, var4);
   }

   @Override
   public int compareTo(ByteBuf var1) {
      return this.recoveredField3513.compareTo(var1);
   }
}
