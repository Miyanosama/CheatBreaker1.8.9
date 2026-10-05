package io.netty.channel.group;

import io.netty.channel.Channel;
import java.util.Set;

public interface ChannelGroup extends Comparable<ChannelGroup>, Set<Channel> {
   ChannelGroupFuture write(Object var1);

   ChannelGroupFuture close(ChannelMatcher var1);

   ChannelGroupFuture disconnect(ChannelMatcher var1);

   ChannelGroupFuture write(Object var1, ChannelMatcher var2);

   String name();

   ChannelGroupFuture flushAndWrite(Object var1, ChannelMatcher var2);

   ChannelGroupFuture deregister();

   ChannelGroupFuture writeAndFlush(Object var1, ChannelMatcher var2);

   ChannelGroup flush(ChannelMatcher var1);

   ChannelGroupFuture deregister(ChannelMatcher var1);

   ChannelGroupFuture writeAndFlush(Object var1);

   ChannelGroupFuture close();

   ChannelGroup flush();

   ChannelGroupFuture flushAndWrite(Object var1);

   ChannelGroupFuture disconnect();
}
