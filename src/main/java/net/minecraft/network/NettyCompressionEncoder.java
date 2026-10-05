package net.minecraft.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import java.util.zip.Deflater;
import net.minecraft.network.PacketBuffer;

public class NettyCompressionEncoder extends MessageToByteEncoder<ByteBuf> {
   public Deflater recoveredField1316;
   public byte[] recoveredField1317 = new byte[8192];
   public int recoveredField1318;

   public void setCompressionTreshold(int var1) {
      this.recoveredField1318 = var1;
   }

   public void encode(ChannelHandlerContext var1, ByteBuf var2, ByteBuf var3) {
      int var4 = var2.readableBytes();
      PacketBuffer var5 = new PacketBuffer(var3);
      if (var4 < this.recoveredField1318) {
         var5.writeVarIntToBuffer(0);
         var5.writeBytes(var2);
      } else {
         byte[] var6 = new byte[var4];
         var2.readBytes(var6);
         var5.writeVarIntToBuffer(var6.length);
         this.recoveredField1316.setInput(var6, 0, var4);
         this.recoveredField1316.finish();

         while (!this.recoveredField1316.finished()) {
            int var7 = this.recoveredField1316.deflate(this.recoveredField1317);
            var5.writeBytes(this.recoveredField1317, 0, var7);
         }

         this.recoveredField1316.reset();
      }
   }

   public NettyCompressionEncoder(int var1) {
      this.recoveredField1318 = var1;
      this.recoveredField1316 = new Deflater();
   }
}
