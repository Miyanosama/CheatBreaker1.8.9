package io.netty.buffer;

import io.netty.util.ReferenceCounted;

public interface ByteBufHolder extends ReferenceCounted {
   ByteBufHolder retain();

   ByteBufHolder retain(int var1);

   ByteBuf content();

   ByteBufHolder copy();

   ByteBufHolder duplicate();
}
