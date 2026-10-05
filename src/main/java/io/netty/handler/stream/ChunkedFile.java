package io.netty.handler.stream;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.io.File;
import java.io.RandomAccessFile;

public class ChunkedFile implements ChunkedInput<ByteBuf> {
   public long endOffset;
   public int chunkSize;
   public long offset;
   public long startOffset;
   public RandomAccessFile file;

   public long startOffset() {
      return this.startOffset;
   }

   @Override
   public void close() throws java.lang.Exception {
      this.file.close();
   }

   public ChunkedFile(RandomAccessFile var1, long var2, long var4, int var6) throws java.io.IOException {
      if (var1 == null) {
         throw new NullPointerException("file");
      } else if (var2 < 0L) {
         throw new IllegalArgumentException("offset: " + var2 + " (expected: 0 or greater)");
      } else if (var4 < 0L) {
         throw new IllegalArgumentException("length: " + var4 + " (expected: 0 or greater)");
      } else if (var6 <= 0) {
         throw new IllegalArgumentException("chunkSize: " + var6 + " (expected: a positive integer)");
      } else {
         this.file = var1;
         this.offset = this.startOffset = var2;
         this.endOffset = var2 + var4;
         this.chunkSize = var6;
         var1.seek(var2);
      }
   }

   public ChunkedFile(RandomAccessFile var1) throws java.io.IOException {
      this(var1, 8192);
   }

   public ByteBuf readChunk(ChannelHandlerContext var1) throws java.lang.Exception {
      long var2 = this.offset;
      if (var2 >= this.endOffset) {
         return null;
      } else {
         int var4 = (int)Math.min((long)this.chunkSize, this.endOffset - var2);
         ByteBuf var5 = var1.alloc().heapBuffer(var4);
         boolean var6 = true;

         ByteBuf var7;
         try {
            this.file.readFully(var5.array(), var5.arrayOffset(), var4);
            var5.writerIndex(var4);
            this.offset = var2 + var4;
            var6 = false;
            var7 = var5;
         } finally {
            if (var6) {
               var5.release();
            }
         }

         return var7;
      }
   }

   public ChunkedFile(RandomAccessFile var1, int var2) throws java.io.IOException {
      this(var1, 0L, var1.length(), var2);
   }

   public ChunkedFile(File var1, int var2) throws java.io.IOException {
      this(new RandomAccessFile(var1, "r"), var2);
   }

   @Override
   public boolean isEndOfInput() throws java.lang.Exception {
      return this.offset >= this.endOffset || !this.file.getChannel().isOpen();
   }

   public ChunkedFile(File var1) throws java.io.IOException {
      this(var1, 8192);
   }

   public long endOffset() {
      return this.endOffset;
   }

   public long currentOffset() {
      return this.offset;
   }
}
