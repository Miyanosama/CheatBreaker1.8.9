package io.netty.handler.codec.spdy;

import com.jcraft.jzlib.Deflater;
import com.jcraft.jzlib.JZlib;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.udt.nio.NioUdtByteAcceptorChannel;
import io.netty.handler.codec.compression.CompressionException;
import net.minecraft.block.BlockHopper;
import net.minecraft.block.BlockPumpkin$1;
import org.apache.log4j.ConsoleAppender;
import recovered.unidentified.UnidentifiedClass0477;

public class SpdyHeaderBlockJZlibEncoder extends SpdyHeaderBlockRawEncoder {
   public Deflater z = new Deflater();
   public boolean finished;
   public UnidentifiedClass0477 __junk9166839229938231367;
   public ConsoleAppender __junk3793614258491380486;
   public BlockPumpkin$1 __junk6671261561847822190;
   public BlockHopper __junk2873197155212994409;
   public NioUdtByteAcceptorChannel __junk6797952847440832222;

   @Override
   public void end() {
      if (!this.finished) {
         this.finished = true;
         this.z.deflateEnd();
         this.z.next_in = null;
         this.z.next_out = null;
      }
   }

   @Override
   public ByteBuf encode(SpdyHeadersFrame var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("frame");
      } else if (this.finished) {
         return Unpooled.EMPTY_BUFFER;
      } else {
         ByteBuf var2 = super.encode(var1);
         if (var2.readableBytes() == 0) {
            return Unpooled.EMPTY_BUFFER;
         } else {
            ByteBuf var3 = var2.alloc().buffer();
            this.setInput(var2);
            this.encode(var3);
            return var3;
         }
      }
   }

   public void setInput(ByteBuf var1) {
      byte[] var2 = new byte[var1.readableBytes()];
      var1.readBytes(var2);
      this.z.next_in = var2;
      this.z.next_in_index = 0;
      this.z.avail_in = var2.length;
   }

   public SpdyHeaderBlockJZlibEncoder(SpdyVersion var1, int var2, int var3, int var4) {
      super(var1);
      if (var2 < 0 || var2 > 9) {
         throw new IllegalArgumentException("compressionLevel: " + var2 + " (expected: 0-9)");
      } else if (var3 < 9 || var3 > 15) {
         throw new IllegalArgumentException("windowBits: " + var3 + " (expected: 9-15)");
      } else if (var4 >= 1 && var4 <= 9) {
         int var5 = this.z.deflateInit(var2, var3, var4, JZlib.W_ZLIB);
         if (var5 != 0) {
            throw new CompressionException("failed to initialize an SPDY header block deflater: " + var5);
         } else {
            var5 = this.z.deflateSetDictionary(SpdyCodecUtil.SPDY_DICT, SpdyCodecUtil.SPDY_DICT.length);
            if (var5 != 0) {
               throw new CompressionException("failed to set the SPDY dictionary: " + var5);
            }
         }
      } else {
         throw new IllegalArgumentException("memLevel: " + var4 + " (expected: 1-9)");
      }
   }

   public void encode(ByteBuf var1) {
      try {
         byte[] var2 = new byte[(int)Math.ceil(this.z.next_in.length * 1.001) + 12];
         this.z.next_out = var2;
         this.z.next_out_index = 0;
         this.z.avail_out = var2.length;
         int var3 = this.z.deflate(2);
         if (var3 != 0) {
            throw new CompressionException("compression failure: " + var3);
         }

         if (this.z.next_out_index != 0) {
            var1.writeBytes(var2, 0, this.z.next_out_index);
         }
      } finally {
         this.z.next_in = null;
         this.z.next_out = null;
      }
   }
}
