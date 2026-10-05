package io.netty.channel.epoll;

import io.netty.channel.AbstractChannel$AbstractUnsafe;
import net.minecraft.network.NetworkSystem$7;

public abstract class AbstractEpollChannel$AbstractEpollUnsafe extends AbstractChannel$AbstractUnsafe {
   public NetworkSystem$7 __junk5025686511689073641;
   public boolean readPending;

   @Override
   public void flush0() {
      if (!this.isFlushPending()) {
         super.flush0();
      }
   }

   public void clearEpollIn0() {
      if ((this.this$0.flags & AbstractEpollChannel.access$000(this.this$0)) != 0) {
         this.this$0.flags = this.this$0.flags & ~AbstractEpollChannel.access$000(this.this$0);
         AbstractEpollChannel.access$100(this.this$0);
      }
   }

   public void epollRdHupReady() {
   }

   public void epollOutReady() {
      super.flush0();
   }

   public AbstractEpollChannel$AbstractEpollUnsafe(AbstractEpollChannel var1) {
      this.this$0 = var1;
      super(var1);
   }

   public abstract void epollInReady();

   public boolean isFlushPending() {
      return (this.this$0.flags & 2) != 0;
   }
}
