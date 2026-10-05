package io.netty.channel.nio;

import io.netty.buffer.ByteBuf;
import io.netty.channel.MultithreadEventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.util.concurrent.EventExecutor;
import java.nio.channels.spi.SelectorProvider;
import java.util.concurrent.ThreadFactory;
import net.minecraft.scoreboard.Score;
import com.cheatbreaker.client.ui.loading.StartupLoadingGui;

public class NioEventLoopGroup extends MultithreadEventLoopGroup {

   @Override
   public EventExecutor newChild(ThreadFactory var1, Object... var2) throws java.lang.Exception {
      return new NioEventLoop(this, var1, (SelectorProvider)var2[0]);
   }

   public NioEventLoopGroup() {
      this(0);
   }

   public NioEventLoopGroup(int var1) {
      this(var1, null);
   }

   public NioEventLoopGroup(int var1, ThreadFactory var2, SelectorProvider var3) {
      super(var1, var2, var3);
   }

   public void setIoRatio(int var1) {
      for (EventExecutor var3 : this.children()) {
         ((NioEventLoop)var3).setIoRatio(var1);
      }
   }

   public void rebuildSelectors() {
      for (EventExecutor var2 : this.children()) {
         ((NioEventLoop)var2).rebuildSelector();
      }
   }

   public NioEventLoopGroup(int var1, ThreadFactory var2) {
      this(var1, var2, SelectorProvider.provider());
   }
}
