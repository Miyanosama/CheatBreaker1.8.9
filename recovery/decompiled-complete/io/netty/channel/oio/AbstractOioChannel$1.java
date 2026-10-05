package io.netty.channel.oio;

import com.cheatbreaker.client.module.type.DamageTintModule;
import javax.vecmath.Tuple3b;

public class AbstractOioChannel$1 implements Runnable {
   public Tuple3b __junk7951498691871044201;
   public DamageTintModule __junk3433692658353851977;

   public AbstractOioChannel$1(AbstractOioChannel var1) {
      this.this$0 = var1;
      super();
   }

   @Override
   public void run() {
      if (this.this$0.isReadPending() || this.this$0.config().isAutoRead()) {
         this.this$0.setReadPending(false);
         this.this$0.doRead();
      }
   }
}
