package io.netty.channel.epoll;

import com.cheatbreaker.client.ui.mainmenu.BuildInformationMenu;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import net.minecraft.client.Minecraft$15;
import recovered.unidentified.UnidentifiedClass1701;

public class EpollSocketChannel$EpollSocketUnsafe$2 implements ChannelFutureListener {
   public UnidentifiedClass1701 __junk8455184209440190940;
   public Minecraft$15 __junk2557731504500957371;
   public BuildInformationMenu __junk1422059537783670198;

   public EpollSocketChannel$EpollSocketUnsafe$2(EpollSocketChannel$EpollSocketUnsafe var1) {
      this.this$1 = var1;
      super();
   }

   public void operationComplete(ChannelFuture var1) {
      if (var1.isCancelled()) {
         if (EpollSocketChannel.access$300(this.this$1.this$0) != null) {
            EpollSocketChannel.access$300(this.this$1.this$0).cancel(false);
         }

         EpollSocketChannel.access$102(this.this$1.this$0, null);
         this.this$1.close(this.this$1.voidPromise());
      }
   }
}
