package io.netty.channel;

import io.netty.util.concurrent.EventExecutor;

public interface EventLoop extends EventLoopGroup, EventExecutor {
   EventLoopGroup parent();
}
