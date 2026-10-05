package io.netty.channel.group;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import java.util.ArrayList;

public class DefaultChannelGroupFuture$1 implements ChannelFutureListener {
   public void operationComplete(ChannelFuture var1) {
      boolean var2 = var1.isSuccess();
      boolean var3;
      synchronized (this.this$0) {
         if (var2) {
            DefaultChannelGroupFuture.access$008(this.this$0);
         } else {
            DefaultChannelGroupFuture.access$108(this.this$0);
         }

         var3 = DefaultChannelGroupFuture.access$000(this.this$0) + DefaultChannelGroupFuture.access$100(this.this$0)
            == DefaultChannelGroupFuture.access$200(this.this$0).size();
         if (!$assertionsDisabled
            && DefaultChannelGroupFuture.access$000(this.this$0) + DefaultChannelGroupFuture.access$100(this.this$0)
               > DefaultChannelGroupFuture.access$200(this.this$0).size()) {
            throw new AssertionError();
         }
      }

      if (var3) {
         if (DefaultChannelGroupFuture.access$100(this.this$0) > 0) {
            ArrayList var8 = new ArrayList(DefaultChannelGroupFuture.access$100(this.this$0));

            for (ChannelFuture var6 : DefaultChannelGroupFuture.access$200(this.this$0).values()) {
               if (!var6.isSuccess()) {
                  var8.add(new DefaultChannelGroupFuture$DefaultEntry<>(var6.channel(), var6.cause()));
               }
            }

            DefaultChannelGroupFuture.access$300(this.this$0, new ChannelGroupException(var8));
         } else {
            DefaultChannelGroupFuture.access$400(this.this$0);
         }
      }
   }

   public DefaultChannelGroupFuture$1(DefaultChannelGroupFuture var1) {
      this.this$0 = var1;
      super();
   }
}
