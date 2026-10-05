package net.minecraft.network;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.epoll.EpollEventLoopGroup;
import junit.swingui.TestSelector;
import net.minecraft.util.LazyLoadBase;
import net.optifine.config.MatchBlock;

public class NetworkManager$2 extends LazyLoadBase<EpollEventLoopGroup> {
   public TestSelector field_0000;
   public MatchBlock field_0001;

   public EpollEventLoopGroup load() {
      return new EpollEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Epoll Client IO #%d").setDaemon(true).build());
   }
}
