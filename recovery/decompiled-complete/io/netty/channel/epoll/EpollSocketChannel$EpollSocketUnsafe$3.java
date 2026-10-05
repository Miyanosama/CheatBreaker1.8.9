package io.netty.channel.epoll;

import net.minecraft.client.audio.MovingSoundMinecartRiding;
import recovered.unidentified.UnidentifiedClass0672;

public class EpollSocketChannel$EpollSocketUnsafe$3 implements Runnable {
   public MovingSoundMinecartRiding __junk3590675656209504326;
   public UnidentifiedClass0672 __junk4002186423175653697;

   public EpollSocketChannel$EpollSocketUnsafe$3(EpollSocketChannel$EpollSocketUnsafe var1) {
      this.this$1 = var1;
      super();
   }

   @Override
   public void run() {
      this.this$1.epollInReady();
   }
}
