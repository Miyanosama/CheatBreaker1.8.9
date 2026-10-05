package io.netty.channel;

import com.cheatbreaker.client.module.type.IconTextHudModule;
import io.netty.util.internal.InternalThreadLocalMap;
import java.util.Map;

public abstract class ChannelHandlerAdapter implements ChannelHandler {
   public boolean added;

   @Override
   public void handlerAdded(ChannelHandlerContext var1) throws java.lang.Exception {
   }

   @Override
   public void handlerRemoved(ChannelHandlerContext var1) throws java.lang.Exception {
   }

   @Override
   public void exceptionCaught(ChannelHandlerContext var1, Throwable var2) throws java.lang.Exception {
      var1.fireExceptionCaught(var2);
   }

   public boolean isSharable() {
      Class var1 = this.getClass();
      Map var2 = InternalThreadLocalMap.get().handlerSharableCache();
      Boolean var3 = (Boolean)var2.get(var1);
      if (var3 == null) {
         var3 = var1.isAnnotationPresent(ChannelHandler.Sharable.class);
         var2.put(var1, var3);
      }

      return var3;
   }
}
