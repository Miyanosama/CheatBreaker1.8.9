package com.cheatbreaker.client.nethandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import javax.crypto.SecretKey;

public class CBOutboundChannel extends ChannelOutboundHandlerAdapter {
   public long recoveredField1528;
   public long long3;
   public byte[] recoveredField1529;
   public long recoveredField1530 = 1L;

   public CBOutboundChannel(SecretKey var1) {
      this.recoveredField1528 = 0L;
      this.recoveredField1529 = "ZB9hEJy5l+u8QARAlX9T0w".getBytes();

      for (byte var5 : var1.getEncoded()) {
         this.recoveredField1530 = (this.recoveredField1530 + (var5 & 255)) % 65521L;
         this.recoveredField1528 = (this.recoveredField1528 + this.recoveredField1530) % 65521L;
      }
   }

   @Override
   public void write(ChannelHandlerContext var1, Object var2, ChannelPromise var3) throws java.lang.Exception {
      try {
         ByteBuf var4 = (ByteBuf)var2;

         while (var4.readableBytes() > 0) {
            int var5 = var4.readByte() & 255;
            this.recoveredField1530 = (this.recoveredField1530 + var5) % 65521L;
            this.recoveredField1528 = (this.recoveredField1528 + this.recoveredField1530) % 65521L;
         }

         var4.readerIndex(0);

         for (byte var8 : this.recoveredField1529) {
            this.recoveredField1530 = (this.recoveredField1530 + (var8 & 255)) % 65521L;
            this.recoveredField1528 = (this.recoveredField1528 + this.recoveredField1530) % 65521L;
         }

         this.long3 = this.recoveredField1528 << 16 | this.recoveredField1530;
         super.write(var1, var2, var3);
      } catch (Throwable var9) {
         throw var9;
      }
   }

   public long method_04624() {
      return this.long3;
   }
}
