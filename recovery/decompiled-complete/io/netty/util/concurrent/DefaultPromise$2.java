package io.netty.util.concurrent;

import io.netty.handler.codec.compression.JZlibEncoder$1;
import net.minecraft.world.World$2;
import net.optifine.reflect.ReflectorResolver;

public class DefaultPromise$2 implements Runnable {
   public ReflectorResolver __junk7102964221492181414;
   public JZlibEncoder$1 __junk4512713933979584329;
   public World$2 __junk7668642514181346781;

   public DefaultPromise$2(DefaultPromise var1, GenericFutureListener var2) {
      this.this$0 = var1;
      this.val$l = var2;
      super();
   }

   @Override
   public void run() {
      DefaultPromise.notifyListener0(this.this$0, this.val$l);
      DefaultPromise.access$102(this.this$0, null);
   }
}
