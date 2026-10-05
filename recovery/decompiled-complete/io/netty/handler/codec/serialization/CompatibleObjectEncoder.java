package io.netty.handler.codec.serialization;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.channel.AbstractChannelHandlerContext$1;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.nio.AbstractNioByteChannel$NioByteUnsafe;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.spdy.SpdyCodecUtil;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import net.minecraft.client.stream.ChatController$ChatChannelListener;
import net.minecraft.world.biome.BiomeColorHelper$2;

public class CompatibleObjectEncoder extends MessageToByteEncoder<Serializable> {
   public int resetInterval;
   public ChatController$ChatChannelListener __junk1486849664077951242;
   public static AttributeKey<ObjectOutputStream> OOS = AttributeKey.valueOf(CompatibleObjectEncoder.class.getName() + ".OOS");
   public BiomeColorHelper$2 __junk1358472279593537697;
   public SpdyCodecUtil __junk4705144363519052251;
   public AbstractNioByteChannel$NioByteUnsafe __junk81661928270203884;
   public int writtenObjects;
   public AbstractChannelHandlerContext$1 __junk5783978127708420199;

   public CompatibleObjectEncoder(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("resetInterval: " + var1);
      } else {
         this.resetInterval = var1;
      }
   }

   public void encode(ChannelHandlerContext var1, Serializable var2, ByteBuf var3) {
      Attribute var4 = var1.attr(OOS);
      ObjectOutputStream var5 = (ObjectOutputStream)var4.get();
      if (var5 == null) {
         var5 = this.newObjectOutputStream(new ByteBufOutputStream(var3));
         ObjectOutputStream var6 = var4.setIfAbsent(var5);
         if (var6 != null) {
            var5 = var6;
         }
      }

      synchronized (var5) {
         if (this.resetInterval != 0) {
            this.writtenObjects++;
            if (this.writtenObjects % this.resetInterval == 0) {
               var5.reset();
            }
         }

         var5.writeObject(var2);
         var5.flush();
      }
   }

   public ObjectOutputStream newObjectOutputStream(OutputStream var1) {
      return new ObjectOutputStream(var1);
   }

   public CompatibleObjectEncoder() {
      this(16);
   }
}
