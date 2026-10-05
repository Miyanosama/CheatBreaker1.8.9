package io.netty.channel;

public interface RecvByteBufAllocator {
   RecvByteBufAllocator$Handle newHandle();
}
