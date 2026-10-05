package io.netty.channel;

public interface MessageSizeEstimator {
   MessageSizeEstimator$Handle newHandle();
}
