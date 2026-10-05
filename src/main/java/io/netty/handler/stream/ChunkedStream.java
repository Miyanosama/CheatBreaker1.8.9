package io.netty.handler.stream;

import com.cheatbreaker.client.nethandler.server.PacketStaffModState;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.io.InputStream;
import java.io.PushbackInputStream;
import junit.runner.Sorter;

public class ChunkedStream implements ChunkedInput<ByteBuf> {
   public long offset;
   public PushbackInputStream in;
   public static final int DEFAULT_CHUNK_SIZE = 8192;
   public int chunkSize;

   @Override
   public boolean isEndOfInput() throws java.lang.Exception {
      int var1 = this.in.read();
      if (var1 < 0) {
         return true;
      } else {
         this.in.unread(var1);
         return false;
      }
   }

   public ChunkedStream(InputStream var1) {
      this(var1, 8192);
   }

   public ByteBuf readChunk(ChannelHandlerContext var1) throws java.lang.Exception {
      if (this.isEndOfInput()) {
         return null;
      } else {
         int var2 = this.in.available();
         int var3;
         if (var2 <= 0) {
            var3 = this.chunkSize;
         } else {
            var3 = Math.min(this.chunkSize, this.in.available());
         }

         boolean var4 = true;
         ByteBuf var5 = var1.alloc().buffer(var3);

         ByteBuf var6;
         try {
            this.offset = this.offset + var5.writeBytes(this.in, var3);
            var4 = false;
            var6 = var5;
         } finally {
            if (var4) {
               var5.release();
            }
         }

         return var6;
      }
   }

   public ChunkedStream(InputStream var1, int var2) {
      if (var1 == null) {
         throw new NullPointerException("in");
      } else if (var2 <= 0) {
         throw new IllegalArgumentException("chunkSize: " + var2 + " (expected: a positive integer)");
      } else {
         if (var1 instanceof PushbackInputStream) {
            this.in = (PushbackInputStream)var1;
         } else {
            this.in = new PushbackInputStream(var1);
         }

         this.chunkSize = var2;
      }
   }

   @Override
   public void close() throws java.lang.Exception {
      this.in.close();
   }

   public long transferredBytes() {
      return this.offset;
   }
}
