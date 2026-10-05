package io.netty.channel.epoll;

import io.netty.channel.MultithreadEventLoopGroup;
import io.netty.util.concurrent.EventExecutor;
import java.util.concurrent.ThreadFactory;
import net.minecraft.client.gui.ServerListEntryLanScan;
import net.minecraft.entity.player.EntityPlayer;
import org.apache.log4j.lf5.StartLogFactor5;
import com.cheatbreaker.client.websocket.AssetsReconnectThread;

public class EpollEventLoopGroup extends MultithreadEventLoopGroup {

   public void setIoRatio(int var1) {
      for (EventExecutor var3 : this.children()) {
         ((EpollEventLoop)var3).setIoRatio(var1);
      }
   }

   public EpollEventLoopGroup(int var1) {
      this(var1, null);
   }

   public EpollEventLoopGroup() {
      this(0);
   }

   public EpollEventLoopGroup(int var1, ThreadFactory var2) {
      this(var1, var2, 128);
   }

   @Override
   public EventExecutor newChild(ThreadFactory var1, Object... var2) throws java.lang.Exception {
      return new EpollEventLoop(this, var1, (Integer)var2[0]);
   }

   public EpollEventLoopGroup(int var1, ThreadFactory var2, int var3) {
      super(var1, var2, var3);
   }
}
