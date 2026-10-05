package io.netty.util;

public interface ReferenceCounted {
   ReferenceCounted retain();

   ReferenceCounted retain(int var1);

   int refCnt();

   boolean release();

   boolean release(int var1);
}
