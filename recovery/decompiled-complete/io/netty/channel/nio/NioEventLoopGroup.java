package io.netty.channel.nio;

import io.netty.buffer.ByteBuf;
import io.netty.channel.AbstractChannelHandlerContext$2;
import io.netty.channel.MultithreadEventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.util.concurrent.EventExecutor;
import java.nio.channels.spi.SelectorProvider;
import java.util.concurrent.ThreadFactory;
import net.minecraft.scoreboard.Score;
import recovered.unidentified.UnidentifiedClass4262;

public class NioEventLoopGroup extends MultithreadEventLoopGroup {
   public ByteBuf __junk4498944089197402845;
   public AbstractChannelHandlerContext$2 __junk2680506097967239651;
   public Score __junk7571576257206592003;
   public EpollChannelOption __junk2932221166729186399;
   public UnidentifiedClass4262 __junk915780734355538910;

   @Override
   public EventExecutor newChild(ThreadFactory var1, Object... var2) {
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
