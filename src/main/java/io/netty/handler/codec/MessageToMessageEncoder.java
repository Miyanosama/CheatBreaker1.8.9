package io.netty.handler.codec;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.compression.SnappyFramedEncoder;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.RecyclableArrayList;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.TypeParameterMatcher;
import java.util.List;
import javax.vecmath.TexCoord4f;

public abstract class MessageToMessageEncoder<I> extends ChannelOutboundHandlerAdapter {
   public TypeParameterMatcher matcher;

   public MessageToMessageEncoder(Class<? extends I> var1) {
      this.matcher = TypeParameterMatcher.get(var1);
   }

   public MessageToMessageEncoder() {
      this.matcher = TypeParameterMatcher.find(this, MessageToMessageEncoder.class, "I");
   }

   public abstract void encode(ChannelHandlerContext var1, I var2, List<Object> var3) throws java.lang.Exception ;

   // $VF: Could not verify finally blocks. A semaphore variable has been added to preserve control flow.
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public void write(ChannelHandlerContext var1, Object var2, ChannelPromise var3) throws java.lang.Exception {
      RecyclableArrayList var4 = null;
      boolean var20 = false /* VF: Semaphore variable */;

      try {
         var20 = true;
         if (this.acceptOutboundMessage(var2)) {
            var4 = RecyclableArrayList.newInstance();
            Object var5 = var2;

            try {
               this.encode(var1, (I)var5, var4);
            } finally {
               ReferenceCountUtil.release(var2);
            }

            if (var4.isEmpty()) {
               var4.recycle();
               var4 = null;
               throw new EncoderException(StringUtil.simpleClassName(this) + " must produce at least one message.");
            }

            var20 = false;
         } else {
            var1.write(var2, var3);
            var20 = false;
         }
      } catch (EncoderException var26) {
         throw var26;
      } catch (Throwable var27) {
         throw new EncoderException(var27);
      } finally {
         if (var20) {
            if (var4 != null) {
               int var11 = var4.size() - 1;
               if (var11 == 0) {
                  var1.write(var4.get(0), var3);
               } else if (var11 > 0) {
                  ChannelPromise var12 = var1.voidPromise();
                  boolean var13 = var3 == var12;

                  for (int var14 = 0; var14 < var11; var14++) {
                     ChannelPromise var15;
                     if (var13) {
                        var15 = var12;
                     } else {
                        var15 = var1.newPromise();
                     }

                     var1.write(var4.get(var14), var15);
                  }

                  var1.write(var4.get(var11), var3);
               }

               var4.recycle();
            }
         }
      }

      if (var4 != null) {
         int var29 = var4.size() - 1;
         if (var29 == 0) {
            var1.write(var4.get(0), var3);
         } else if (var29 > 0) {
            ChannelPromise var6 = var1.voidPromise();
            boolean var7 = var3 == var6;

            for (int var8 = 0; var8 < var29; var8++) {
               ChannelPromise var9;
               if (var7) {
                  var9 = var6;
               } else {
                  var9 = var1.newPromise();
               }

               var1.write(var4.get(var8), var9);
            }

            var1.write(var4.get(var29), var3);
         }

         var4.recycle();
      }
   }

   public boolean acceptOutboundMessage(Object var1) throws java.lang.Exception {
      return this.matcher.match(var1);
   }
}
