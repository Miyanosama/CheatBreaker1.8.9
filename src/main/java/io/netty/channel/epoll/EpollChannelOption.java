package io.netty.channel.epoll;

import io.netty.channel.ChannelOption;
import net.minecraft.client.model.ModelWolf;

public class EpollChannelOption<T> extends ChannelOption<T> {
   public static ChannelOption<Boolean> TCP_CORK = valueOf("TCP_CORK");
   public static ChannelOption<Integer> TCP_KEEPIDLE = valueOf("TCP_KEEPIDLE");
   public static ChannelOption<Integer> TCP_KEEPINTVL = valueOf("TCP_KEEPINTVL");
   public static ChannelOption<Integer> TCP_KEEPCNT = valueOf("TCP_KEEPCNT");
   public static ChannelOption<Boolean> SO_REUSEPORT = valueOf("SO_REUSEPORT");

   public EpollChannelOption(String var1) {
      super(var1);
   }
}
