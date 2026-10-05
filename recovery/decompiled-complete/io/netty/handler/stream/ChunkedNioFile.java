package io.netty.handler.stream;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.spdy.SpdySessionHandler$ClosingChannelFutureListener;
import io.netty.util.internal.PlatformDependent0$3;
import java.io.File;
import java.io.FileInputStream;
import java.nio.channels.FileChannel;
import org.apache.log4j.chainsaw.Main$1;

public class ChunkedNioFile implements ChunkedInput<ByteBuf> {
   public Main$1 __junk1539933030206923101;
   public long startOffset;
   public SpdySessionHandler$ClosingChannelFutureListener __junk332767562783369506;
   public long endOffset;
   public long offset;
   public FileChannel in;
   public int chunkSize;
   public PlatformDependent0$3 __junk5760845163587422689;

   @Override
   public boolean isEndOfInput() {
      return this.offset >= this.endOffset || !this.in.isOpen();
   }

   public ByteBuf readChunk(ChannelHandlerContext var1) {
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

   public ChunkedNioFile(FileChannel var1, long var2, long var4, int var6) {
      if (var1 == null) {
         throw new NullPointerException("in");
      } else if (var2 < (202385408L & 1386943501L)) {
         throw new IllegalArgumentException("offset: " + var2 + " (expected: 0 or greater)");
      } else if (var4 < (-512185131896389622L & 512185130119399237L)) {
         throw new IllegalArgumentException("length: " + var4 + " (expected: 0 or greater)");
      } else if (var6 <= 0) {
         throw new IllegalArgumentException("chunkSize: " + var6 + " (expected: a positive integer)");
      } else {
         if (var2 != (536880393L & 153649248L)) {
            var1.position(var2);
         }

         this.in = var1;
         this.chunkSize = var6;
         this.offset = this.startOffset = var2;
         this.endOffset = var2 + var4;
      }
   }

   public ChunkedNioFile(FileChannel var1, int var2) {
      this(var1, 71305349L & 694741598329308168L, var1.size(), var2);
   }

   public long currentOffset() {
      return this.offset;
   }

   public long endOffset() {
      return this.endOffset;
   }

   public ChunkedNioFile(File var1) {
      this(new FileInputStream(var1).getChannel());
   }

   public ChunkedNioFile(File var1, int var2) {
      this(new FileInputStream(var1).getChannel(), var2);
   }

   @Override
   public void close() {
      this.in.close();
   }

   public ChunkedNioFile(FileChannel var1) {
      this(var1, 8192);
   }
}
