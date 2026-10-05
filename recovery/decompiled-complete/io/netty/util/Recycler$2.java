package io.netty.util;

import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.internal.UnsafeAtomicReferenceFieldUpdater;
import java.util.Map;
import java.util.WeakHashMap;

public class Recycler$2 extends FastThreadLocal<Map<Recycler$Stack<?>, Recycler$WeakOrderQueue>> {
   public UnsafeAtomicReferenceFieldUpdater __junk7579221517554176593;

   public Map<Recycler$Stack<?>, Recycler$WeakOrderQueue> initialValue() {
      return new WeakHashMap<>();
   }
}
