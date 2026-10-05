package io.netty.buffer;

import com.cheatbreaker.client.ui.DisconnectConfirmationGui;
import io.netty.channel.group.ChannelMatchers;
import io.netty.util.ResourceLeak;
import io.netty.util.internal.EmptyArrays;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.GatheringByteChannel;
import java.nio.channels.ScatteringByteChannel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import net.minecraft.block.BlockSandStone;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.command.server.CommandTeleport;
import net.minecraft.server.management.UserListEntry;
import org.apache.log4j.pattern.PatternParser;

public class CompositeByteBuf extends AbstractReferenceCountedByteBuf {
   public List<CompositeByteBuf.Component> components = new ArrayList<>();
   public ByteBufAllocator alloc;
   public int maxNumComponents;
   public boolean freed;
   public boolean direct;
   public static ByteBuffer FULL_BYTEBUFFER = (ByteBuffer)((Buffer)ByteBuffer.allocate(1)).position(1);
   public ResourceLeak leak;

   @Override
   public void _setByte(int var1, int var2) {
      this.setByte(var1, var2);
   }

   public List<ByteBuf> decompose(int var1, int var2) {
      this.checkIndex(var1, var2);
      if (var2 == 0) {
         return Collections.emptyList();
      } else {
         int var3 = this.toComponentIndex(var1);
         ArrayList var4 = new ArrayList(this.components.size());
         CompositeByteBuf.Component var5 = this.components.get(var3);
         ByteBuf var6 = var5.buf.duplicate();
         var6.readerIndex(var1 - var5.offset);
         ByteBuf var7 = var6;
         int var8 = var2;

         do {
            int var9 = var7.readableBytes();
            if (var8 <= var9) {
               var7.writerIndex(var7.readerIndex() + var8);
               var4.add(var7);
               break;
            }

            var4.add(var7);
            var8 -= var9;
            var7 = this.components.get(++var3).buf.duplicate();
         } while (var8 > 0);

         for (int var10 = 0; var10 < var4.size(); var10++) {
            var4.set(var10, ((ByteBuf)var4.get(var10)).slice());
         }

         return var4;
      }
   }

   public CompositeByteBuf readBytes(ByteBuf var1, int var2) {
      return (CompositeByteBuf)super.readBytes(var1, var2);
   }

   public ByteBuf component(int var1) {
      return this.internalComponent(var1).duplicate();
   }

   @Override
   public ByteOrder order() {
      return ByteOrder.BIG_ENDIAN;
   }

   @Override
   public byte[] array() {
      if (this.components.size() == 1) {
         return this.components.get(0).buf.array();
      } else {
         throw new UnsupportedOperationException();
      }
   }

   public CompositeByteBuf writeBytes(byte[] var1) {
      return (CompositeByteBuf)super.writeBytes(var1);
   }

   @Override
   public ByteBuffer internalNioBuffer(int var1, int var2) {
      if (this.components.size() == 1) {
         return this.components.get(0).buf.internalNioBuffer(var1, var2);
      } else {
         throw new UnsupportedOperationException();
      }
   }

   @Override
   public String toString() {
      String var1 = super.toString();
      var1 = var1.substring(0, var1.length() - 1);
      return var1 + ", components=" + this.components.size() + ')';
   }

   @Override
   public byte _getByte(int var1) {
      CompositeByteBuf.Component var2 = this.findComponent(var1);
      return var2.buf.getByte(var1 - var2.offset);
   }

   @Override
   public void _setInt(int var1, int var2) {
      CompositeByteBuf.Component var3 = this.findComponent(var1);
      if (var1 + 4 <= var3.endOffset) {
         var3.buf.setInt(var1 - var3.offset, var2);
      } else if (this.order() == ByteOrder.BIG_ENDIAN) {
         this._setShort(var1, (short)(var2 >>> 16));
         this._setShort(var1 + 2, (short)var2);
      } else {
         this._setShort(var1, (short)var2);
         this._setShort(var1 + 2, (short)(var2 >>> 16));
      }
   }

   public CompositeByteBuf setBytes(int var1, byte[] var2) {
      return (CompositeByteBuf)super.setBytes(var1, var2);
   }

   public CompositeByteBuf setDouble(int var1, double var2) {
      return (CompositeByteBuf)super.setDouble(var1, var2);
   }

   @Override
   public int _getInt(int var1) {
      CompositeByteBuf.Component var2 = this.findComponent(var1);
      if (var1 + 4 <= var2.endOffset) {
         return var2.buf.getInt(var1 - var2.offset);
      } else {
         return this.order() == ByteOrder.BIG_ENDIAN
            ? (this._getShort(var1) & 65535) << 16 | this._getShort(var1 + 2) & 65535
            : this._getShort(var1) & 65535 | (this._getShort(var1 + 2) & 65535) << 16;
      }
   }

   public CompositeByteBuf markWriterIndex() {
      return (CompositeByteBuf)super.markWriterIndex();
   }

   public CompositeByteBuf getBytes(int var1, ByteBuf var2) {
      return (CompositeByteBuf)super.getBytes(var1, var2);
   }

   public CompositeByteBuf writeMedium(int var1) {
      return (CompositeByteBuf)super.writeMedium(var1);
   }

   public CompositeByteBuf writeChar(int var1) {
      return (CompositeByteBuf)super.writeChar(var1);
   }

   public CompositeByteBuf(ByteBufAllocator var1, boolean var2, int var3) {
      super(Integer.MAX_VALUE);
      if (var1 == null) {
         throw new NullPointerException("alloc");
      } else {
         this.alloc = var1;
         this.direct = var2;
         this.maxNumComponents = var3;
         this.leak = leakDetector.open(this);
      }
   }

   public CompositeByteBuf setChar(int var1, int var2) {
      return (CompositeByteBuf)super.setChar(var1, var2);
   }

   public CompositeByteBuf(ByteBufAllocator var1, boolean var2, int var3, ByteBuf... var4) {
      super(Integer.MAX_VALUE);
      if (var1 == null) {
         throw new NullPointerException("alloc");
      } else if (var3 < 2) {
         throw new IllegalArgumentException("maxNumComponents: " + var3 + " (expected: >= 2)");
      } else {
         this.alloc = var1;
         this.direct = var2;
         this.maxNumComponents = var3;
         this.addComponents0(0, var4);
         this.consolidateIfNeeded();
         this.setIndex(0, this.capacity());
         this.leak = leakDetector.open(this);
      }
   }

   public CompositeByteBuf readBytes(byte[] var1, int var2, int var3) {
      return (CompositeByteBuf)super.readBytes(var1, var2, var3);
   }

   public CompositeByteBuf getBytes(int var1, ByteBuf var2, int var3, int var4) {
      this.checkDstIndex(var1, var4, var3, var2.capacity());
      if (var4 == 0) {
         return this;
      } else {
         for (int var5 = this.toComponentIndex(var1); var4 > 0; var5++) {
            CompositeByteBuf.Component var6 = this.components.get(var5);
            ByteBuf var7 = var6.buf;
            int var8 = var6.offset;
            int var9 = Math.min(var4, var7.capacity() - (var1 - var8));
            var7.getBytes(var1 - var8, var2, var3, var9);
            var1 += var9;
            var3 += var9;
            var4 -= var9;
         }

         return this;
      }
   }

   @Override
   public void _setLong(int var1, long var2) {
      CompositeByteBuf.Component var4 = this.findComponent(var1);
      if (var1 + 8 <= var4.endOffset) {
         var4.buf.setLong(var1 - var4.offset, var2);
      } else if (this.order() == ByteOrder.BIG_ENDIAN) {
         this._setInt(var1, (int)(var2 >>> 32));
         this._setInt(var1 + 4, (int)var2);
      } else {
         this._setInt(var1, (int)var2);
         this._setInt(var1 + 4, (int)(var2 >>> 32));
      }
   }

   public CompositeByteBuf setFloat(int var1, float var2) {
      return (CompositeByteBuf)super.setFloat(var1, var2);
   }

   public CompositeByteBuf removeComponents(int var1, int var2) {
      this.checkComponentIndex(var1, var2);
      List var3 = this.components.subList(var1, var1 + var2);

      for (CompositeByteBuf.Component var5 : (Iterable<CompositeByteBuf.Component>)(Iterable<?>)(var3)) {
         var5.freeIfNecessary();
      }

      var3.clear();
      this.updateComponentOffsets(var1);
      return this;
   }

   public CompositeByteBuf setByte(int var1, int var2) {
      CompositeByteBuf.Component var3 = this.findComponent(var1);
      var3.buf.setByte(var1 - var3.offset, var2);
      return this;
   }

   public int addComponents0(int var1, Iterable<ByteBuf> var2) {
      if (var2 == null) {
         throw new NullPointerException("buffers");
      } else if (var2 instanceof ByteBuf) {
         return this.addComponent0(var1, (ByteBuf)var2);
      } else {
         if (!(var2 instanceof Collection)) {
            ArrayList var3 = new ArrayList();

            for (ByteBuf var5 : var2) {
               var3.add(var5);
            }

            var2 = var3;
         }

         Collection var6 = (Collection)var2;
         return this.addComponents0(var1, (ByteBuf[])var6.toArray(new ByteBuf[var6.size()]));
      }
   }

   public int toByteIndex(int var1) {
      this.checkComponentIndex(var1);
      return this.components.get(var1).offset;
   }

   public Iterator<ByteBuf> iterator() {
      this.ensureAccessible();
      ArrayList var1 = new ArrayList(this.components.size());

      for (CompositeByteBuf.Component var3 : this.components) {
         var1.add(var3.buf);
      }

      return var1.iterator();
   }

   public int numComponents() {
      return this.components.size();
   }

   public CompositeByteBuf addComponent(ByteBuf var1) {
      this.addComponent0(this.components.size(), var1);
      this.consolidateIfNeeded();
      return this;
   }

   @Override
   public void deallocate() {
      if (!this.freed) {
         this.freed = true;
         int var1 = this.components.size();

         for (int var2 = 0; var2 < var1; var2++) {
            this.components.get(var2).freeIfNecessary();
         }

         if (this.leak != null) {
            this.leak.close();
         }
      }
   }

   public CompositeByteBuf discardSomeReadBytes() {
      return this.discardReadComponents();
   }

   @Override
   public int nioBufferCount() {
      if (this.components.size() == 1) {
         return this.components.get(0).buf.nioBufferCount();
      } else {
         int var1 = 0;
         int var2 = this.components.size();

         for (int var3 = 0; var3 < var2; var3++) {
            CompositeByteBuf.Component var4 = this.components.get(var3);
            var1 += var4.buf.nioBufferCount();
         }

         return var1;
      }
   }

   public void copyTo(int var1, int var2, int var3, ByteBuf var4) {
      int var5 = 0;

      for (int var6 = var3; var2 > 0; var6++) {
         CompositeByteBuf.Component var7 = this.components.get(var6);
         ByteBuf var8 = var7.buf;
         int var9 = var7.offset;
         int var10 = Math.min(var2, var8.capacity() - (var1 - var9));
         var8.getBytes(var1 - var9, var4, var5, var10);
         var1 += var10;
         var5 += var10;
         var2 -= var10;
      }

      var4.writerIndex(var4.capacity());
   }

   public CompositeByteBuf removeComponent(int var1) {
      this.checkComponentIndex(var1);
      this.components.remove(var1).freeIfNecessary();
      this.updateComponentOffsets(var1);
      return this;
   }

   @Override
   public int _getUnsignedMedium(int var1) {
      CompositeByteBuf.Component var2 = this.findComponent(var1);
      if (var1 + 3 <= var2.endOffset) {
         return var2.buf.getUnsignedMedium(var1 - var2.offset);
      } else {
         return this.order() == ByteOrder.BIG_ENDIAN
            ? (this._getShort(var1) & 65535) << 8 | this._getByte(var1 + 2) & 0xFF
            : this._getShort(var1) & 65535 | (this._getByte(var1 + 2) & 0xFF) << 16;
      }
   }

   public CompositeByteBuf setInt(int var1, int var2) {
      return (CompositeByteBuf)super.setInt(var1, var2);
   }

   public CompositeByteBuf readBytes(byte[] var1) {
      return (CompositeByteBuf)super.readBytes(var1);
   }

   public CompositeByteBuf readBytes(ByteBuf var1) {
      return (CompositeByteBuf)super.readBytes(var1);
   }

   public CompositeByteBuf writerIndex(int var1) {
      return (CompositeByteBuf)super.writerIndex(var1);
   }

   public CompositeByteBuf setBytes(int var1, ByteBuf var2, int var3, int var4) {
      this.checkSrcIndex(var1, var4, var3, var2.capacity());
      if (var4 == 0) {
         return this;
      } else {
         for (int var5 = this.toComponentIndex(var1); var4 > 0; var5++) {
            CompositeByteBuf.Component var6 = this.components.get(var5);
            ByteBuf var7 = var6.buf;
            int var8 = var6.offset;
            int var9 = Math.min(var4, var7.capacity() - (var1 - var8));
            var7.setBytes(var1 - var8, var2, var3, var9);
            var1 += var9;
            var3 += var9;
            var4 -= var9;
         }

         return this;
      }
   }

   public CompositeByteBuf readBytes(OutputStream var1, int var2) throws java.io.IOException {
      return (CompositeByteBuf)super.readBytes(var1, var2);
   }

   public CompositeByteBuf consolidate() {
      this.ensureAccessible();
      int var1 = this.numComponents();
      if (var1 <= 1) {
         return this;
      } else {
         CompositeByteBuf.Component var2 = this.components.get(var1 - 1);
         int var3 = var2.endOffset;
         ByteBuf var4 = this.allocBuffer(var3);

         for (int var5 = 0; var5 < var1; var5++) {
            CompositeByteBuf.Component var6 = this.components.get(var5);
            ByteBuf var7 = var6.buf;
            var4.writeBytes(var7);
            var6.freeIfNecessary();
         }

         this.components.clear();
         this.components.add(new CompositeByteBuf.Component(var4));
         this.updateComponentOffsets(0);
         return this;
      }
   }

   @Override
   public boolean isDirect() {
      int var1 = this.components.size();
      if (var1 == 0) {
         return false;
      } else {
         for (int var2 = 0; var2 < var1; var2++) {
            if (!this.components.get(var2).buf.isDirect()) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public boolean hasMemoryAddress() {
      return this.components.size() == 1 ? this.components.get(0).buf.hasMemoryAddress() : false;
   }

   @Override
   public long memoryAddress() {
      if (this.components.size() == 1) {
         return this.components.get(0).buf.memoryAddress();
      } else {
         throw new UnsupportedOperationException();
      }
   }

   public CompositeByteBuf getBytes(int var1, ByteBuffer var2) {
      int var3 = var2.limit();
      int var4 = var2.remaining();
      this.checkIndex(var1, var4);
      if (var4 == 0) {
         return this;
      } else {
         int var5 = this.toComponentIndex(var1);

         try {
            while (var4 > 0) {
               CompositeByteBuf.Component var6 = this.components.get(var5);
               ByteBuf var7 = var6.buf;
               int var8 = var6.offset;
               int var9 = Math.min(var4, var7.capacity() - (var1 - var8));
               ((Buffer)var2).limit(var2.position() + var9);
               var7.getBytes(var1 - var8, var2);
               var1 += var9;
               var4 -= var9;
               var5++;
            }
         } finally {
            ((Buffer)var2).limit(var3);
         }

         return this;
      }
   }

   public CompositeByteBuf writeDouble(double var1) {
      return (CompositeByteBuf)super.writeDouble(var1);
   }

   @Override
   public byte getByte(int var1) {
      return this._getByte(var1);
   }

   public CompositeByteBuf writeBytes(ByteBuf var1, int var2, int var3) {
      return (CompositeByteBuf)super.writeBytes(var1, var2, var3);
   }

   @Override
   public void _setShort(int var1, int var2) {
      CompositeByteBuf.Component var3 = this.findComponent(var1);
      if (var1 + 2 <= var3.endOffset) {
         var3.buf.setShort(var1 - var3.offset, var2);
      } else if (this.order() == ByteOrder.BIG_ENDIAN) {
         this._setByte(var1, (byte)(var2 >>> 8));
         this._setByte(var1 + 1, (byte)var2);
      } else {
         this._setByte(var1, (byte)var2);
         this._setByte(var1 + 1, (byte)(var2 >>> 8));
      }
   }

   @Override
   public ByteBuffer[] nioBuffers() {
      return this.nioBuffers(this.readerIndex(), this.readableBytes());
   }

   public CompositeByteBuf addComponents(int var1, Iterable<ByteBuf> var2) {
      this.addComponents0(var1, var2);
      this.consolidateIfNeeded();
      return this;
   }

   @Override
   public boolean hasArray() {
      return this.components.size() == 1 ? this.components.get(0).buf.hasArray() : false;
   }

   @Override
   public int capacity() {
      return this.components.isEmpty() ? 0 : this.components.get(this.components.size() - 1).endOffset;
   }

   public CompositeByteBuf setShort(int var1, int var2) {
      return (CompositeByteBuf)super.setShort(var1, var2);
   }

   public void updateComponentOffsets(int var1) {
      int var2 = this.components.size();
      if (var2 > var1) {
         CompositeByteBuf.Component var3 = this.components.get(var1);
         if (var1 == 0) {
            var3.offset = 0;
            var3.endOffset = var3.length;
            var1++;
         }

         for (int var4 = var1; var4 < var2; var4++) {
            CompositeByteBuf.Component var5 = this.components.get(var4 - 1);
            CompositeByteBuf.Component var6 = this.components.get(var4);
            var6.offset = var5.endOffset;
            var6.endOffset = var6.offset + var6.length;
         }
      }
   }

   @Override
   public long _getLong(int var1) {
      CompositeByteBuf.Component var2 = this.findComponent(var1);
      if (var1 + 8 <= var2.endOffset) {
         return var2.buf.getLong(var1 - var2.offset);
      } else {
         return this.order() == ByteOrder.BIG_ENDIAN
            ? (this._getInt(var1) & 4294967295L) << 32 | this._getInt(var1 + 4) & 4294967295L
            : this._getInt(var1) & 4294967295L | (this._getInt(var1 + 4) & 4294967295L) << 32;
      }
   }

   public CompositeByteBuf resetReaderIndex() {
      return (CompositeByteBuf)super.resetReaderIndex();
   }

   public CompositeByteBuf capacity(int var1) {
      this.ensureAccessible();
      if (var1 >= 0 && var1 <= this.maxCapacity()) {
         int var2 = this.capacity();
         if (var1 > var2) {
            int var3 = var1 - var2;
            int var5 = this.components.size();
            if (var5 < this.maxNumComponents) {
               ByteBuf var4 = this.allocBuffer(var3);
               var4.setIndex(0, var3);
               this.addComponent0(this.components.size(), var4);
            } else {
               ByteBuf var8 = this.allocBuffer(var3);
               var8.setIndex(0, var3);
               this.addComponent0(this.components.size(), var8);
               this.consolidateIfNeeded();
            }
         } else if (var1 < var2) {
            int var7 = var2 - var1;
            ListIterator var9 = this.components.listIterator(this.components.size());

            while (var9.hasPrevious()) {
               CompositeByteBuf.Component var10 = (CompositeByteBuf.Component)var9.previous();
               if (var7 < var10.length) {
                  CompositeByteBuf.Component var6 = new CompositeByteBuf.Component(var10.buf.slice(0, var10.length - var7));
                  var6.offset = var10.offset;
                  var6.endOffset = var6.offset + var6.length;
                  var9.set(var6);
                  break;
               }

               var7 -= var10.length;
               var9.remove();
            }

            if (this.readerIndex() > var1) {
               this.setIndex(var1, var1);
            } else if (this.writerIndex() > var1) {
               this.writerIndex(var1);
            }
         }

         return this;
      } else {
         throw new IllegalArgumentException("newCapacity: " + var1);
      }
   }

   public CompositeByteBuf writeBytes(ByteBuf var1, int var2) {
      return (CompositeByteBuf)super.writeBytes(var1, var2);
   }

   public CompositeByteBuf setBytes(int var1, byte[] var2, int var3, int var4) {
      this.checkSrcIndex(var1, var4, var3, var2.length);
      if (var4 == 0) {
         return this;
      } else {
         for (int var5 = this.toComponentIndex(var1); var4 > 0; var5++) {
            CompositeByteBuf.Component var6 = this.components.get(var5);
            ByteBuf var7 = var6.buf;
            int var8 = var6.offset;
            int var9 = Math.min(var4, var7.capacity() - (var1 - var8));
            var7.setBytes(var1 - var8, var2, var3, var9);
            var1 += var9;
            var3 += var9;
            var4 -= var9;
         }

         return this;
      }
   }

   @Override
   public int arrayOffset() {
      if (this.components.size() == 1) {
         return this.components.get(0).buf.arrayOffset();
      } else {
         throw new UnsupportedOperationException();
      }
   }

   public CompositeByteBuf writeBoolean(boolean var1) {
      return (CompositeByteBuf)super.writeBoolean(var1);
   }

   @Override
   public int getBytes(int var1, GatheringByteChannel var2, int var3) throws java.io.IOException {
      int var4 = this.nioBufferCount();
      if (var4 == 1) {
         return var2.write(this.internalNioBuffer(var1, var3));
      } else {
         long var5 = var2.write(this.nioBuffers(var1, var3));
         return var5 > 2147483647L ? Integer.MAX_VALUE : (int)var5;
      }
   }

   public CompositeByteBuf.Component findComponent(int var1) {
      this.checkIndex(var1);
      int var2 = 0;
      int var3 = this.components.size();

      while (var2 <= var3) {
         int var4 = var2 + var3 >>> 1;
         CompositeByteBuf.Component var5 = this.components.get(var4);
         if (var1 >= var5.endOffset) {
            var2 = var4 + 1;
         } else {
            if (var1 >= var5.offset) {
               return var5;
            }

            var3 = var4 - 1;
         }
      }

      throw new Error("should not reach here");
   }

   public ByteBuf componentAtOffset(int var1) {
      return this.internalComponentAtOffset(var1).duplicate();
   }

   public CompositeByteBuf markReaderIndex() {
      return (CompositeByteBuf)super.markReaderIndex();
   }

   public CompositeByteBuf readerIndex(int var1) {
      return (CompositeByteBuf)super.readerIndex(var1);
   }

   public CompositeByteBuf writeZero(int var1) {
      return (CompositeByteBuf)super.writeZero(var1);
   }

   @Override
   public short _getShort(int var1) {
      CompositeByteBuf.Component var2 = this.findComponent(var1);
      if (var1 + 2 <= var2.endOffset) {
         return var2.buf.getShort(var1 - var2.offset);
      } else {
         return this.order() == ByteOrder.BIG_ENDIAN
            ? (short)((this._getByte(var1) & 255) << 8 | this._getByte(var1 + 1) & 255)
            : (short)(this._getByte(var1) & 255 | (this._getByte(var1 + 1) & 255) << 8);
      }
   }

   public CompositeByteBuf getBytes(int var1, ByteBuf var2, int var3) {
      return (CompositeByteBuf)super.getBytes(var1, var2, var3);
   }

   @Override
   public ByteBuf copy(int var1, int var2) {
      this.checkIndex(var1, var2);
      ByteBuf var3 = Unpooled.buffer(var2);
      if (var2 != 0) {
         this.copyTo(var1, var2, this.toComponentIndex(var1), var3);
      }

      return var3;
   }

   @Override
   public void _setMedium(int var1, int var2) {
      CompositeByteBuf.Component var3 = this.findComponent(var1);
      if (var1 + 3 <= var3.endOffset) {
         var3.buf.setMedium(var1 - var3.offset, var2);
      } else if (this.order() == ByteOrder.BIG_ENDIAN) {
         this._setShort(var1, (short)(var2 >> 8));
         this._setByte(var1 + 2, (byte)var2);
      } else {
         this._setShort(var1, (short)var2);
         this._setByte(var1 + 2, (byte)(var2 >>> 16));
      }
   }

   public int toComponentIndex(int var1) {
      this.checkIndex(var1);
      int var2 = 0;
      int var3 = this.components.size();

      while (var2 <= var3) {
         int var4 = var2 + var3 >>> 1;
         CompositeByteBuf.Component var5 = this.components.get(var4);
         if (var1 >= var5.endOffset) {
            var2 = var4 + 1;
         } else {
            if (var1 >= var5.offset) {
               return var4;
            }

            var3 = var4 - 1;
         }
      }

      throw new Error("should not reach here");
   }

   public int addComponents0(int var1, ByteBuf... var2) {
      this.checkComponentIndex(var1);
      if (var2 == null) {
         throw new NullPointerException("buffers");
      } else {
         int var3 = 0;

         for (ByteBuf var7 : var2) {
            if (var7 == null) {
               break;
            }

            var3 += var7.readableBytes();
         }

         if (var3 == 0) {
            return var1;
         } else {
            for (ByteBuf var12 : var2) {
               if (var12 == null) {
                  break;
               }

               if (var12.isReadable()) {
                  var1 = this.addComponent0(var1, var12) + 1;
                  int var8 = this.components.size();
                  if (var1 > var8) {
                     var1 = var8;
                  }
               } else {
                  var12.release();
               }
            }

            return var1;
         }
      }
   }

   public CompositeByteBuf setLong(int var1, long var2) {
      return (CompositeByteBuf)super.setLong(var1, var2);
   }

   public CompositeByteBuf getBytes(int var1, OutputStream var2, int var3) throws java.io.IOException {
      this.checkIndex(var1, var3);
      if (var3 == 0) {
         return this;
      } else {
         for (int var4 = this.toComponentIndex(var1); var3 > 0; var4++) {
            CompositeByteBuf.Component var5 = this.components.get(var4);
            ByteBuf var6 = var5.buf;
            int var7 = var5.offset;
            int var8 = Math.min(var3, var6.capacity() - (var1 - var7));
            var6.getBytes(var1 - var7, var2, var8);
            var1 += var8;
            var3 -= var8;
         }

         return this;
      }
   }

   public CompositeByteBuf writeShort(int var1) {
      return (CompositeByteBuf)super.writeShort(var1);
   }

   public void checkComponentIndex(int var1, int var2) {
      this.ensureAccessible();
      if (var1 < 0 || var1 + var2 > this.components.size()) {
         throw new IndexOutOfBoundsException(
            String.format(
               "cIndex: %d, numComponents: %d (expected: cIndex >= 0 && cIndex + numComponents <= totalNumComponents(%d))", var1, var2, this.components.size()
            )
         );
      }
   }

   public CompositeByteBuf writeByte(int var1) {
      return (CompositeByteBuf)super.writeByte(var1);
   }

   public CompositeByteBuf discardReadBytes() {
      this.ensureAccessible();
      int var1 = this.readerIndex();
      if (var1 == 0) {
         return this;
      } else {
         int var2 = this.writerIndex();
         if (var1 == var2 && var2 == this.capacity()) {
            for (CompositeByteBuf.Component var9 : this.components) {
               var9.freeIfNecessary();
            }

            this.components.clear();
            this.setIndex(0, 0);
            this.adjustMarkers(var1);
            return this;
         } else {
            int var3 = this.toComponentIndex(var1);

            for (int var4 = 0; var4 < var3; var4++) {
               this.components.get(var4).freeIfNecessary();
            }

            this.components.subList(0, var3).clear();
            CompositeByteBuf.Component var8 = this.components.get(0);
            int var5 = var1 - var8.offset;
            if (var5 == var8.length) {
               this.components.remove(0);
            } else {
               CompositeByteBuf.Component var6 = new CompositeByteBuf.Component(var8.buf.slice(var5, var8.length - var5));
               this.components.set(0, var6);
            }

            this.updateComponentOffsets(0);
            this.setIndex(0, var2 - var1);
            this.adjustMarkers(var1);
            return this;
         }
      }
   }

   public CompositeByteBuf retain(int var1) {
      return (CompositeByteBuf)super.retain(var1);
   }

   public CompositeByteBuf writeBytes(ByteBuf var1) {
      return (CompositeByteBuf)super.writeBytes(var1);
   }

   public CompositeByteBuf setZero(int var1, int var2) {
      return (CompositeByteBuf)super.setZero(var1, var2);
   }

   public CompositeByteBuf setBytes(int var1, ByteBuf var2) {
      return (CompositeByteBuf)super.setBytes(var1, var2);
   }

   public CompositeByteBuf writeFloat(float var1) {
      return (CompositeByteBuf)super.writeFloat(var1);
   }

   public CompositeByteBuf setBytes(int var1, ByteBuf var2, int var3) {
      return (CompositeByteBuf)super.setBytes(var1, var2, var3);
   }

   public CompositeByteBuf setBytes(int var1, ByteBuffer var2) {
      int var3 = var2.limit();
      int var4 = var2.remaining();
      this.checkIndex(var1, var4);
      if (var4 == 0) {
         return this;
      } else {
         int var5 = this.toComponentIndex(var1);

         try {
            while (var4 > 0) {
               CompositeByteBuf.Component var6 = this.components.get(var5);
               ByteBuf var7 = var6.buf;
               int var8 = var6.offset;
               int var9 = Math.min(var4, var7.capacity() - (var1 - var8));
               ((Buffer)var2).limit(var2.position() + var9);
               var7.setBytes(var1 - var8, var2);
               var1 += var9;
               var4 -= var9;
               var5++;
            }
         } finally {
            ((Buffer)var2).limit(var3);
         }

         return this;
      }
   }

   @Override
   public int setBytes(int var1, ScatteringByteChannel var2, int var3) throws java.io.IOException {
      this.checkIndex(var1, var3);
      if (var3 == 0) {
         return var2.read(FULL_BYTEBUFFER);
      } else {
         int var4 = this.toComponentIndex(var1);
         int var5 = 0;

         do {
            CompositeByteBuf.Component var6 = this.components.get(var4);
            ByteBuf var7 = var6.buf;
            int var8 = var6.offset;
            int var9 = Math.min(var3, var7.capacity() - (var1 - var8));
            int var10 = var7.setBytes(var1 - var8, var2, var9);
            if (var10 == 0) {
               break;
            }

            if (var10 < 0) {
               if (var5 == 0) {
                  return -1;
               }
               break;
            }

            if (var10 == var9) {
               var1 += var9;
               var3 -= var9;
               var5 += var9;
               var4++;
            } else {
               var1 += var10;
               var3 -= var10;
               var5 += var10;
            }
         } while (var3 > 0);

         return var5;
      }
   }

   public int addComponent0(int var1, ByteBuf var2) {
      this.checkComponentIndex(var1);
      if (var2 == null) {
         throw new NullPointerException("buffer");
      } else {
         int var3 = var2.readableBytes();
         if (var3 == 0) {
            return var1;
         } else {
            CompositeByteBuf.Component var4 = new CompositeByteBuf.Component(var2.order(ByteOrder.BIG_ENDIAN).slice());
            if (var1 == this.components.size()) {
               this.components.add(var4);
               if (var1 == 0) {
                  var4.endOffset = var3;
               } else {
                  CompositeByteBuf.Component var5 = this.components.get(var1 - 1);
                  var4.offset = var5.endOffset;
                  var4.endOffset = var4.offset + var3;
               }
            } else {
               this.components.add(var1, var4);
               this.updateComponentOffsets(var1);
            }

            return var1;
         }
      }
   }

   @Override
   public ByteBufAllocator alloc() {
      return this.alloc;
   }

   public ByteBuf internalComponentAtOffset(int var1) {
      return this.findComponent(var1).buf;
   }

   public ByteBuf allocBuffer(int var1) {
      return this.direct ? this.alloc().directBuffer(var1) : this.alloc().heapBuffer(var1);
   }

   public CompositeByteBuf readBytes(ByteBuffer var1) {
      return (CompositeByteBuf)super.readBytes(var1);
   }

   @Override
   public ByteBuffer[] nioBuffers(int var1, int var2) {
      this.checkIndex(var1, var2);
      if (var2 == 0) {
         return EmptyArrays.EMPTY_BYTE_BUFFERS;
      } else {
         ArrayList var3 = new ArrayList(this.components.size());

         for (int var4 = this.toComponentIndex(var1); var2 > 0; var4++) {
            CompositeByteBuf.Component var5 = this.components.get(var4);
            ByteBuf var6 = var5.buf;
            int var7 = var5.offset;
            int var8 = Math.min(var2, var6.capacity() - (var1 - var7));
            switch (var6.nioBufferCount()) {
               case 0:
                  throw new UnsupportedOperationException();
               case 1:
                  var3.add(var6.nioBuffer(var1 - var7, var8));
                  break;
               default:
                  Collections.addAll(var3, var6.nioBuffers(var1 - var7, var8));
            }

            var1 += var8;
            var2 -= var8;
         }

         return (java.nio.ByteBuffer[])var3.toArray(new ByteBuffer[var3.size()]);
      }
   }

   public CompositeByteBuf writeBytes(ByteBuffer var1) {
      return (CompositeByteBuf)super.writeBytes(var1);
   }

   public CompositeByteBuf addComponents(ByteBuf... var1) {
      this.addComponents0(this.components.size(), var1);
      this.consolidateIfNeeded();
      return this;
   }

   public CompositeByteBuf consolidate(int var1, int var2) {
      this.checkComponentIndex(var1, var2);
      if (var2 <= 1) {
         return this;
      } else {
         int var3 = var1 + var2;
         CompositeByteBuf.Component var4 = this.components.get(var3 - 1);
         int var5 = var4.endOffset - this.components.get(var1).offset;
         ByteBuf var6 = this.allocBuffer(var5);

         for (int var7 = var1; var7 < var3; var7++) {
            CompositeByteBuf.Component var8 = this.components.get(var7);
            ByteBuf var9 = var8.buf;
            var6.writeBytes(var9);
            var8.freeIfNecessary();
         }

         this.components.subList(var1 + 1, var3).clear();
         this.components.set(var1, new CompositeByteBuf.Component(var6));
         this.updateComponentOffsets(var1);
         return this;
      }
   }

   public CompositeByteBuf getBytes(int var1, byte[] var2, int var3, int var4) {
      this.checkDstIndex(var1, var4, var3, var2.length);
      if (var4 == 0) {
         return this;
      } else {
         for (int var5 = this.toComponentIndex(var1); var4 > 0; var5++) {
            CompositeByteBuf.Component var6 = this.components.get(var5);
            ByteBuf var7 = var6.buf;
            int var8 = var6.offset;
            int var9 = Math.min(var4, var7.capacity() - (var1 - var8));
            var7.getBytes(var1 - var8, var2, var3, var9);
            var1 += var9;
            var3 += var9;
            var4 -= var9;
         }

         return this;
      }
   }

   public CompositeByteBuf addComponents(Iterable<ByteBuf> var1) {
      this.addComponents0(this.components.size(), var1);
      this.consolidateIfNeeded();
      return this;
   }

   @Override
   public ByteBuffer nioBuffer(int var1, int var2) {
      if (this.components.size() == 1) {
         ByteBuf var3 = this.components.get(0).buf;
         if (var3.nioBufferCount() == 1) {
            return this.components.get(0).buf.nioBuffer(var1, var2);
         }
      }

      ByteBuffer var6 = ByteBuffer.allocate(var2).order(this.order());
      ByteBuffer[] var4 = this.nioBuffers(var1, var2);

      for (int var5 = 0; var5 < var4.length; var5++) {
         var6.put(var4[var5]);
      }

      ((Buffer)var6).flip();
      return var6;
   }

   public CompositeByteBuf setBoolean(int var1, boolean var2) {
      return (CompositeByteBuf)super.setBoolean(var1, var2);
   }

   public ByteBuf internalComponent(int var1) {
      this.checkComponentIndex(var1);
      return this.components.get(var1).buf;
   }

   public void checkComponentIndex(int var1) {
      this.ensureAccessible();
      if (var1 < 0 || var1 > this.components.size()) {
         throw new IndexOutOfBoundsException(String.format("cIndex: %d (expected: >= 0 && <= numComponents(%d))", var1, this.components.size()));
      }
   }

   public CompositeByteBuf readBytes(ByteBuf var1, int var2, int var3) {
      return (CompositeByteBuf)super.readBytes(var1, var2, var3);
   }

   public void consolidateIfNeeded() {
      int var1 = this.components.size();
      if (var1 > this.maxNumComponents) {
         int var2 = this.components.get(var1 - 1).endOffset;
         ByteBuf var3 = this.allocBuffer(var2);

         for (int var4 = 0; var4 < var1; var4++) {
            CompositeByteBuf.Component var5 = this.components.get(var4);
            ByteBuf var6 = var5.buf;
            var3.writeBytes(var6);
            var5.freeIfNecessary();
         }

         CompositeByteBuf.Component var7 = new CompositeByteBuf.Component(var3);
         var7.endOffset = var7.length;
         this.components.clear();
         this.components.add(var7);
      }
   }

   public CompositeByteBuf writeBytes(byte[] var1, int var2, int var3) {
      return (CompositeByteBuf)super.writeBytes(var1, var2, var3);
   }

   public int maxNumComponents() {
      return this.maxNumComponents;
   }

   public CompositeByteBuf discardReadComponents() {
      this.ensureAccessible();
      int var1 = this.readerIndex();
      if (var1 == 0) {
         return this;
      } else {
         int var2 = this.writerIndex();
         if (var1 == var2 && var2 == this.capacity()) {
            for (CompositeByteBuf.Component var8 : this.components) {
               var8.freeIfNecessary();
            }

            this.components.clear();
            this.setIndex(0, 0);
            this.adjustMarkers(var1);
            return this;
         } else {
            int var3 = this.toComponentIndex(var1);

            for (int var4 = 0; var4 < var3; var4++) {
               this.components.get(var4).freeIfNecessary();
            }

            this.components.subList(0, var3).clear();
            CompositeByteBuf.Component var7 = this.components.get(0);
            int var5 = var7.offset;
            this.updateComponentOffsets(0);
            this.setIndex(var1 - var5, var2 - var5);
            this.adjustMarkers(var5);
            return this;
         }
      }
   }

   public CompositeByteBuf addComponent(int var1, ByteBuf var2) {
      this.addComponent0(var1, var2);
      this.consolidateIfNeeded();
      return this;
   }

   public CompositeByteBuf ensureWritable(int var1) {
      return (CompositeByteBuf)super.ensureWritable(var1);
   }

   public CompositeByteBuf skipBytes(int var1) {
      return (CompositeByteBuf)super.skipBytes(var1);
   }

   public CompositeByteBuf retain() {
      return (CompositeByteBuf)super.retain();
   }

   public CompositeByteBuf writeInt(int var1) {
      return (CompositeByteBuf)super.writeInt(var1);
   }

   public CompositeByteBuf writeLong(long var1) {
      return (CompositeByteBuf)super.writeLong(var1);
   }

   public CompositeByteBuf setMedium(int var1, int var2) {
      return (CompositeByteBuf)super.setMedium(var1, var2);
   }

   public CompositeByteBuf(ByteBufAllocator var1, boolean var2, int var3, Iterable<ByteBuf> var4) {
      super(Integer.MAX_VALUE);
      if (var1 == null) {
         throw new NullPointerException("alloc");
      } else if (var3 < 2) {
         throw new IllegalArgumentException("maxNumComponents: " + var3 + " (expected: >= 2)");
      } else {
         this.alloc = var1;
         this.direct = var2;
         this.maxNumComponents = var3;
         this.addComponents0(0, var4);
         this.consolidateIfNeeded();
         this.setIndex(0, this.capacity());
         this.leak = leakDetector.open(this);
      }
   }

   @Override
   public int setBytes(int var1, InputStream var2, int var3) throws java.io.IOException {
      this.checkIndex(var1, var3);
      if (var3 == 0) {
         return var2.read(EmptyArrays.EMPTY_BYTES);
      } else {
         int var4 = this.toComponentIndex(var1);
         int var5 = 0;

         do {
            CompositeByteBuf.Component var6 = this.components.get(var4);
            ByteBuf var7 = var6.buf;
            int var8 = var6.offset;
            int var9 = Math.min(var3, var7.capacity() - (var1 - var8));
            int var10 = var7.setBytes(var1 - var8, var2, var9);
            if (var10 < 0) {
               if (var5 == 0) {
                  return -1;
               }
               break;
            }

            if (var10 == var9) {
               var1 += var9;
               var3 -= var9;
               var5 += var9;
               var4++;
            } else {
               var1 += var10;
               var3 -= var10;
               var5 += var10;
            }
         } while (var3 > 0);

         return var5;
      }
   }

   public CompositeByteBuf addComponents(int var1, ByteBuf... var2) {
      this.addComponents0(var1, var2);
      this.consolidateIfNeeded();
      return this;
   }

   public CompositeByteBuf getBytes(int var1, byte[] var2) {
      return (CompositeByteBuf)super.getBytes(var1, var2);
   }

   public CompositeByteBuf clear() {
      return (CompositeByteBuf)super.clear();
   }

   @Override
   public ByteBuf unwrap() {
      return null;
   }

   public CompositeByteBuf setIndex(int var1, int var2) {
      return (CompositeByteBuf)super.setIndex(var1, var2);
   }

   public CompositeByteBuf resetWriterIndex() {
      return (CompositeByteBuf)super.resetWriterIndex();
   }

   public static final class Component {
      public int endOffset;
      public int offset;
      public int length;
      public ByteBuf buf;

      public void freeIfNecessary() {
         this.buf.release();
      }

      public Component(ByteBuf var1) {
         this.buf = var1;
         this.length = var1.readableBytes();
      }
   }
}
