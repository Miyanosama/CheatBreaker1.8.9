package io.netty.handler.codec.spdy;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;
import net.minecraft.realms.RealmsScrolledSelectionList;
import junit.awtui.AboutDialog;

public class SpdyHeaderBlockZlibDecoder extends SpdyHeaderBlockRawDecoder {
   public static final int DEFAULT_BUFFER_CAPACITY = 4096;
   public ByteBuf decompressed;
   public static SpdyProtocolException INVALID_HEADER_BLOCK = new SpdyProtocolException("Invalid Header Block");
   public Inflater decompressor = new Inflater();

   public int setInput(ByteBuf var1) {
      int var2 = var1.readableBytes();
      if (var1.hasArray()) {
         this.decompressor.setInput(var1.array(), var1.arrayOffset() + var1.readerIndex(), var2);
      } else {
         byte[] var3 = new byte[var2];
         var1.getBytes(var1.readerIndex(), var3);
         this.decompressor.setInput(var3, 0, var3.length);
      }

      return var2;
   }

   @Override
   public void releaseBuffer() {
      if (this.decompressed != null) {
         this.decompressed.release();
         this.decompressed = null;
      }
   }

   @Override
   public void decode(ByteBuf var1, SpdyHeadersFrame var2) throws java.lang.Exception {
      int var3 = this.setInput(var1);

      int var4;
      do {
         var4 = this.decompress(var1.alloc(), var2);
      } while (var4 > 0);

      if (this.decompressor.getRemaining() != 0) {
         throw INVALID_HEADER_BLOCK;
      } else {
         var1.skipBytes(var3);
      }
   }

   public int decompress(ByteBufAllocator var1, SpdyHeadersFrame var2) throws java.lang.Exception {
      this.ensureBuffer(var1);
      byte[] var3 = this.decompressed.array();
      int var4 = this.decompressed.arrayOffset() + this.decompressed.writerIndex();

      try {
         int var5 = this.decompressor.inflate(var3, var4, this.decompressed.writableBytes());
         if (var5 == 0 && this.decompressor.needsDictionary()) {
            try {
               this.decompressor.setDictionary(SpdyCodecUtil.SPDY_DICT);
            } catch (IllegalArgumentException var7) {
               throw INVALID_HEADER_BLOCK;
            }

            var5 = this.decompressor.inflate(var3, var4, this.decompressed.writableBytes());
         }

         if (var2 != null) {
            this.decompressed.writerIndex(this.decompressed.writerIndex() + var5);
            this.decodeHeaderBlock(this.decompressed, var2);
            this.decompressed.discardReadBytes();
         }

         return var5;
      } catch (DataFormatException var8) {
         throw new SpdyProtocolException("Received invalid header block", var8);
      }
   }

   @Override
   public void endHeaderBlock(SpdyHeadersFrame var1) throws java.lang.Exception {
      super.endHeaderBlock(var1);
      this.releaseBuffer();
   }

   public SpdyHeaderBlockZlibDecoder(SpdyVersion var1, int var2) {
      super(var1, var2);
   }

   public void ensureBuffer(ByteBufAllocator var1) {
      if (this.decompressed == null) {
         this.decompressed = var1.heapBuffer(4096);
      }

      this.decompressed.ensureWritable(1);
   }

   @Override
   public void end() {
      super.end();
      this.releaseBuffer();
      this.decompressor.end();
   }
}
