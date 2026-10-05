package io.netty.handler.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.RecyclableArrayList;
import io.netty.util.internal.TypeParameterMatcher;
import java.util.List;

public abstract class MessageToMessageDecoder<I> extends ChannelInboundHandlerAdapter {
   public TypeParameterMatcher matcher;

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public void channelRead(ChannelHandlerContext var1, Object var2) {
      RecyclableArrayList var3 = RecyclableArrayList.newInstance();
      boolean var13 = false /* VF: Semaphore variable */;

      try {
         var13 = true;
         if (this.acceptInboundMessage(var2)) {
            Object var4 = var2;

            try {
               this.decode(var1, (I)var4, var3);
            } finally {
               ReferenceCountUtil.release(var2);
            }

            var13 = false;
         } else {
            var3.add(var2);
            var13 = false;
         }
      } catch (DecoderException var19) {
         throw var19;
      } catch (Exception var20) {
         throw new DecoderException(var20);
      } finally {
         if (var13) {
            int var7 = var3.size();

            for (int var8 = 0; var8 < var7; var8++) {
               var1.fireChannelRead(var3.get(var8));
            }

            var3.recycle();
         }
      }

      int var22 = var3.size();

      for (int var5 = 0; var5 < var22; var5++) {
         var1.fireChannelRead(var3.get(var5));
      }

      var3.recycle();
   }

   public abstract void decode(ChannelHandlerContext var1, I var2, List<Object> var3);

   public MessageToMessageDecoder() {
      this.matcher = TypeParameterMatcher.find(this, MessageToMessageDecoder.class, "I");
   }

   public MessageToMessageDecoder(Class<? extends I> var1) {
      this.matcher = TypeParameterMatcher.get(var1);
   }

   public boolean acceptInboundMessage(Object var1) {
      return this.matcher.match(var1);
   }
}
