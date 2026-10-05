package net.minecraft.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.DecoderException;
import java.util.List;
import java.util.zip.Inflater;

public class NettyCompressionDecoder extends ByteToMessageDecoder {
   public int treshold;
   public Inflater recoveredField2524;

   public NettyCompressionDecoder(int var1) {
      this.treshold = var1;
      this.recoveredField2524 = new Inflater();
   }

   public void setCompressionTreshold(int var1) {
      this.treshold = var1;
   }

   @Override
   public void decode(ChannelHandlerContext var1, ByteBuf var2, List<Object> var3) throws java.lang.Exception {
      if (var2.readableBytes() != 0) {
         PacketBuffer var4 = new PacketBuffer(var2);
         int var5 = var4.readVarIntFromBuffer();
         if (var5 == 0) {
            var3.add(var4.readBytes(var4.readableBytes()));
         } else {
            if (var5 < this.treshold) {
               throw new DecoderException("Badly compressed packet - size of " + var5 + " is below server threshold of " + this.treshold);
            }

            if (var5 > 2097152) {
               throw new DecoderException("Badly compressed packet - size of " + var5 + " is larger than protocol maximum of " + 2097152);
            }

            byte[] var6 = new byte[var4.readableBytes()];
            var4.readBytes(var6);
            this.recoveredField2524.setInput(var6);
            byte[] var7 = new byte[var5];
            this.recoveredField2524.inflate(var7);
            var3.add(Unpooled.wrappedBuffer(var7));
            this.recoveredField2524.reset();
         }
      }
   }
}
