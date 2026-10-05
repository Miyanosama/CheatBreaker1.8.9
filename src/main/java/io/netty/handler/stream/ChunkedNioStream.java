package io.netty.handler.stream;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import net.minecraft.world.gen.FlatLayerInfo;
import net.optifine.NaturalTextures;

public class ChunkedNioStream implements ChunkedInput<ByteBuf> {
   public int chunkSize;
   public ReadableByteChannel in;
   public long offset;
   public ByteBuffer byteBuffer;

   @Override
   public void close() throws java.lang.Exception {
      this.in.close();
   }

   @Override
   public boolean isEndOfInput() throws java.lang.Exception {
      if (this.byteBuffer.position() > 0) {
         return false;
      } else if (this.in.isOpen()) {
         int var1 = this.in.read(this.byteBuffer);
         if (var1 < 0) {
            return true;
         } else {
            this.offset += var1;
            return false;
         }
      } else {
         return true;
      }
   }

   public ChunkedNioStream(ReadableByteChannel var1) {
      this(var1, 8192);
   }

   public ChunkedNioStream(ReadableByteChannel var1, int var2) {
      if (var1 == null) {
         throw new NullPointerException("in");
      } else if (var2 <= 0) {
         throw new IllegalArgumentException("chunkSize: " + var2 + " (expected: a positive integer)");
      } else {
         this.in = var1;
         this.offset = 0L;
         this.chunkSize = var2;
         this.byteBuffer = ByteBuffer.allocate(var2);
      }
   }

   public long transferredBytes() {
      return this.offset;
   }

   public ByteBuf readChunk(ChannelHandlerContext var1) throws java.lang.Exception {
      if (this.isEndOfInput()) {
         return null;
      } else {
         int var2 = this.byteBuffer.position();

         do {
            int var3 = this.in.read(this.byteBuffer);
            if (var3 < 0) {
               break;
            }

            var2 += var3;
            this.offset += var3;
         } while (var2 != this.chunkSize);

         ((Buffer)this.byteBuffer).flip();
         boolean var9 = true;
         ByteBuf var4 = var1.alloc().buffer(this.byteBuffer.remaining());

         ByteBuf var5;
         try {
            var4.writeBytes(this.byteBuffer);
            ((Buffer)this.byteBuffer).clear();
            var9 = false;
            var5 = var4;
         } finally {
            if (var9) {
               var4.release();
            }
         }

         return var5;
      }
   }
}
