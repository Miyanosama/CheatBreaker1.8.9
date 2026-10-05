package io.netty.handler.stream;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.spdy.SpdySessionHandler;
import java.io.File;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import org.apache.log4j.chainsaw.Main$1;

public class ChunkedNioFile implements ChunkedInput<ByteBuf> {
   public long startOffset;
   public long endOffset;
   public long offset;
   public FileChannel in;
   public int chunkSize;

   @Override
   public boolean isEndOfInput() throws java.lang.Exception {
      return this.offset >= this.endOffset || !this.in.isOpen();
   }

   public ByteBuf readChunk(ChannelHandlerContext var1) throws java.lang.Exception {
      long var2 = this.offset;
      if (var2 >= this.endOffset) {
         return null;
      } else {
         int var4 = (int)Math.min((long)this.chunkSize, this.endOffset - var2);
         ByteBuf var5 = var1.alloc().buffer(var4);
         boolean var6 = true;

         ByteBuf var12;
         try {
            int var7 = 0;

            do {
               int var8 = var5.writeBytes(this.in, var4 - var7);
               if (var8 < 0) {
                  break;
               }

               var7 += var8;
            } while (var7 != var4);

            this.offset += var7;
            var6 = false;
            var12 = var5;
         } finally {
            if (var6) {
               var5.release();
            }
         }

         return var12;
      }
   }

   public long startOffset() {
      return this.startOffset;
   }

   public ChunkedNioFile(FileChannel var1, long var2, long var4, int var6) throws java.io.IOException {
      if (var1 == null) {
         throw new NullPointerException("in");
      } else if (var2 < 0L) {
         throw new IllegalArgumentException("offset: " + var2 + " (expected: 0 or greater)");
      } else if (var4 < 0L) {
         throw new IllegalArgumentException("length: " + var4 + " (expected: 0 or greater)");
      } else if (var6 <= 0) {
         throw new IllegalArgumentException("chunkSize: " + var6 + " (expected: a positive integer)");
      } else {
         if (var2 != 0L) {
            var1.position(var2);
         }

         this.in = var1;
         this.chunkSize = var6;
         this.offset = this.startOffset = var2;
         this.endOffset = var2 + var4;
      }
   }

   public ChunkedNioFile(FileChannel var1, int var2) throws java.io.IOException {
      this(var1, 0L, var1.size(), var2);
   }

   public long currentOffset() {
      return this.offset;
   }

   public long endOffset() {
      return this.endOffset;
   }

   public ChunkedNioFile(File var1) throws java.io.IOException {
      this(new FileInputStream(var1).getChannel());
   }

   public ChunkedNioFile(File var1, int var2) throws java.io.IOException {
      this(new FileInputStream(var1).getChannel(), var2);
   }

   @Override
   public void close() throws java.lang.Exception {
      this.in.close();
   }

   public ChunkedNioFile(FileChannel var1) throws java.io.IOException {
      this(var1, 8192);
   }
}
