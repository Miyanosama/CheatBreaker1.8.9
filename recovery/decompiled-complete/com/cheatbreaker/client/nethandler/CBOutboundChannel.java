package com.cheatbreaker.client.nethandler;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import javax.crypto.SecretKey;
import net.minecraft.client.particle.EntitySplashFX$Factory;

public class CBOutboundChannel extends ChannelOutboundHandlerAdapter {
   public long field_0002;
   public long long3;
   public byte[] field_0001;
   public long field_0003 = 34081607L & 5716945807631278129L;
   public EntitySplashFX$Factory field_0000;

   public CBOutboundChannel(SecretKey var1) {
      this.field_0002 = 1165426752L & -8456911783888641638L;
      this.field_0001 = "ZB9hEJy5l+u8QARAlX9T0w".getBytes();

      for (byte var5 : var1.getEncoded()) {
         this.field_0003 = (this.field_0003 + (var5 & 255)) % (352518129L & -2275300159922044939L);
         this.field_0002 = (this.field_0002 + this.field_0003) % (1478688753L & 85786613L);
      }
   }

   @Override
   public void write(ChannelHandlerContext var1, Object var2, ChannelPromise var3) {
      try {
         ByteBuf var4 = (ByteBuf)var2;

         while (var4.readableBytes() > 0) {
            int var5 = var4.readByte() & 255;
            this.field_0003 = (this.field_0003 + var5) % (1095827445L & -5212804759936434183L);
            this.field_0002 = (this.field_0002 + this.field_0003) % (831127539L & 3754035282041700337L);
         }

         var4.readerIndex(0);

         for (byte var8 : this.field_0001) {
            this.field_0003 = (this.field_0003 + (var8 & 255)) % (405340149L & 541327353L);
            this.field_0002 = (this.field_0002 + this.field_0003) % (556204029L & 1510080497L);
         }

         this.long3 = this.field_0002 << 16 | this.field_0003;
         super.write(var1, var2, var3);
      } catch (Throwable var9) {
         throw var9;
      }
   }

   public long method_04624() {
      return this.long3;
   }
}
