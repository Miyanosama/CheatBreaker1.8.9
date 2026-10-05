package com.cheatbreaker.client.network;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import javax.crypto.SecretKey;

public class CBInboundChannel extends ChannelInboundHandlerAdapter {
   public long recoveredField3466;
   public long recoveredField3467;
   public long recoveredField3468;
   public long recoveredField3469;
   public byte[] recoveredField3470 = "cf2O02b1QJSZOcVHphHucA".getBytes();

   public long method_24511() {
      return this.recoveredField3469;
   }

   public CBInboundChannel(SecretKey var1) {
      this.recoveredField3466 = 1L;
      this.recoveredField3467 = 0L;

      for (byte var5 : var1.getEncoded()) {
         this.recoveredField3466 = (this.recoveredField3466 + (var5 & 255)) % 65521L;
         this.recoveredField3467 = (this.recoveredField3467 + this.recoveredField3466) % 65521L;
      }
   }

   public long method_24513() {
      return this.recoveredField3467;
   }

   public long method_24510() {
      return this.recoveredField3468;
   }

   public byte[] method_24512() {
      return this.recoveredField3470;
   }

   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) {
      ByteBuf var3 = (ByteBuf)var2;

      while (var3.readableBytes() > 0) {
         int var4 = var3.readByte() & 255;
         this.recoveredField3466 = (this.recoveredField3466 + var4) % 65521L;
         this.recoveredField3467 = (this.recoveredField3467 + this.recoveredField3466) % 65521L;
      }

      var3.readerIndex(0);

      for (byte var7 : this.recoveredField3470) {
         this.recoveredField3466 = (this.recoveredField3466 + (var7 & 255)) % 65521L;
         this.recoveredField3467 = (this.recoveredField3467 + this.recoveredField3466) % 65521L;
      }

      this.recoveredField3469 = this.recoveredField3468;
      this.recoveredField3468 = this.recoveredField3467 << 16 | this.recoveredField3466;

      try {
         super.channelRead(var1, var2);
      } catch (Exception var8) {
         var8.printStackTrace();
      }
   }

   public long method_24509() {
      return this.recoveredField3466;
   }
}
