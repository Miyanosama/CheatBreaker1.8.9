package io.netty.util.internal;

import io.netty.channel.FailedChannelFuture;
import io.netty.handler.codec.rtsp.RtspRequestEncoder;
import io.netty.util.concurrent.FastThreadLocalThread;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;
import java.util.IdentityHashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class InternalThreadLocalMap extends UnpaddedInternalThreadLocalMap {
   public long rp6;
   public long rp2;
   public long rp8;
   public long rp1;
   public long rp4;
   public long rp5;
   public FailedChannelFuture __junk4543096311020829881;
   public long rp3;
   public long rp7;
   public static Object UNSET = new Object();
   public long rp9;
   public RtspRequestEncoder __junk411231570123604467;

   public static void destroy() {
      slowThreadLocalMap = null;
   }

   public Object indexedVariable(int var1) {
      Object[] var2 = this.indexedVariables;
      return var1 < var2.length ? var2[var1] : UNSET;
   }

   public int size() {
      int var1 = 0;
      if (this.futureListenerStackDepth != 0) {
         var1++;
      }

      if (this.localChannelReaderStackDepth != 0) {
         var1++;
      }

      if (this.handlerSharableCache != null) {
         var1++;
      }

      if (this.counterHashCode != null) {
         var1++;
      }

      if (this.random != null) {
         var1++;
      }

      if (this.typeParameterMatcherGetCache != null) {
         var1++;
      }

      if (this.typeParameterMatcherFindCache != null) {
         var1++;
      }

      if (this.stringBuilder != null) {
         var1++;
      }

      if (this.charsetEncoderCache != null) {
         var1++;
      }

      if (this.charsetDecoderCache != null) {
         var1++;
      }

      for (Object var5 : this.indexedVariables) {
         if (var5 != UNSET) {
            var1++;
         }
      }

      return var1 - 1;
   }

   public static Object[] newIndexedVariableTable() {
      Object[] var0 = new Object[32];
      Arrays.fill(var0, UNSET);
      return var0;
   }

   public Map<Class<?>, Map<String, TypeParameterMatcher>> typeParameterMatcherFindCache() {
      Object var1 = this.typeParameterMatcherFindCache;
      if (var1 == null) {
         this.typeParameterMatcherFindCache = (Map<Class<?>, Map<String, TypeParameterMatcher>>)(var1 = new IdentityHashMap());
      }

      return (Map<Class<?>, Map<String, TypeParameterMatcher>>)var1;
   }

   public static int nextVariableIndex() {
      int var0 = nextIndex.getAndIncrement();
      if (var0 < 0) {
         nextIndex.decrementAndGet();
         throw new IllegalStateException("too many thread-local indexed variables");
      } else {
         return var0;
      }
   }

   public void setLocalChannelReaderStackDepth(int var1) {
      this.localChannelReaderStackDepth = var1;
   }

   public int localChannelReaderStackDepth() {
      return this.localChannelReaderStackDepth;
   }

   public Map<Class<?>, Boolean> handlerSharableCache() {
      Object var1 = this.handlerSharableCache;
      if (var1 == null) {
         this.handlerSharableCache = (Map<Class<?>, Boolean>)(var1 = new WeakHashMap(4));
      }

      return (Map<Class<?>, Boolean>)var1;
   }

   public Map<Charset, CharsetEncoder> charsetEncoderCache() {
      Object var1 = this.charsetEncoderCache;
      if (var1 == null) {
         this.charsetEncoderCache = (Map<Charset, CharsetEncoder>)(var1 = new IdentityHashMap());
      }

      return (Map<Charset, CharsetEncoder>)var1;
   }

   public int futureListenerStackDepth() {
      return this.futureListenerStackDepth;
   }

   public static InternalThreadLocalMap slowGet() {
      ThreadLocal var0 = UnpaddedInternalThreadLocalMap.slowThreadLocalMap;
      if (var0 == null) {
         UnpaddedInternalThreadLocalMap.slowThreadLocalMap = var0 = new ThreadLocal();
      }

      InternalThreadLocalMap var1 = (InternalThreadLocalMap)var0.get();
      if (var1 == null) {
         var1 = new InternalThreadLocalMap();
         var0.set(var1);
      }

      return var1;
   }

   public static InternalThreadLocalMap getIfSet() {
      Thread var0 = Thread.currentThread();
      InternalThreadLocalMap var1;
      if (var0 instanceof FastThreadLocalThread) {
         var1 = ((FastThreadLocalThread)var0).threadLocalMap();
      } else {
         ThreadLocal var2 = UnpaddedInternalThreadLocalMap.slowThreadLocalMap;
         if (var2 == null) {
            var1 = null;
         } else {
            var1 = (InternalThreadLocalMap)var2.get();
         }
      }

      return var1;
   }

   public static InternalThreadLocalMap fastGet(FastThreadLocalThread var0) {
      InternalThreadLocalMap var1 = var0.threadLocalMap();
      if (var1 == null) {
         var0.setThreadLocalMap(var1 = new InternalThreadLocalMap());
      }

      return var1;
   }

   public StringBuilder stringBuilder() {
      StringBuilder var1 = this.stringBuilder;
      if (var1 == null) {
         this.stringBuilder = var1 = new StringBuilder(512);
      } else {
         var1.setLength(0);
      }

      return var1;
   }

   public static void remove() {
      Thread var0 = Thread.currentThread();
      if (var0 instanceof FastThreadLocalThread) {
         ((FastThreadLocalThread)var0).setThreadLocalMap(null);
      } else {
         ThreadLocal var1 = UnpaddedInternalThreadLocalMap.slowThreadLocalMap;
         if (var1 != null) {
            var1.remove();
         }
      }
   }

   public void expandIndexedVariableTableAndSet(int var1, Object var2) {
      Object[] var3 = this.indexedVariables;
      int var4 = var3.length;
      int var5 = var1 | var1 >>> 1;
      var5 |= var5 >>> 2;
      var5 |= var5 >>> 4;
      var5 |= var5 >>> 8;
      var5 |= var5 >>> 16;
      Object[] var6 = Arrays.copyOf(var3, ++var5);
      Arrays.fill(var6, var4, var6.length, UNSET);
      var6[var1] = var2;
      this.indexedVariables = var6;
   }

   public Object removeIndexedVariable(int var1) {
      Object[] var2 = this.indexedVariables;
      if (var1 < var2.length) {
         Object var3 = var2[var1];
         var2[var1] = UNSET;
         return var3;
      } else {
         return UNSET;
      }
   }

   public boolean isIndexedVariableSet(int var1) {
      Object[] var2 = this.indexedVariables;
      return var1 < var2.length && var2[var1] != UNSET;
   }

   public Map<Charset, CharsetDecoder> charsetDecoderCache() {
      Object var1 = this.charsetDecoderCache;
      if (var1 == null) {
         this.charsetDecoderCache = (Map<Charset, CharsetDecoder>)(var1 = new IdentityHashMap());
      }

      return (Map<Charset, CharsetDecoder>)var1;
   }

   public Map<Class<?>, TypeParameterMatcher> typeParameterMatcherGetCache() {
      Object var1 = this.typeParameterMatcherGetCache;
      if (var1 == null) {
         this.typeParameterMatcherGetCache = (Map<Class<?>, TypeParameterMatcher>)(var1 = new IdentityHashMap());
      }

      return (Map<Class<?>, TypeParameterMatcher>)var1;
   }

   public static int lastVariableIndex() {
      return nextIndex.get() - 1;
   }

   public ThreadLocalRandom random() {
      ThreadLocalRandom var1 = this.random;
      if (var1 == null) {
         this.random = var1 = new ThreadLocalRandom();
      }

      return var1;
   }

   public static InternalThreadLocalMap get() {
      Thread var0 = Thread.currentThread();
      return var0 instanceof FastThreadLocalThread ? fastGet((FastThreadLocalThread)var0) : slowGet();
   }

   public InternalThreadLocalMap() {
      super(newIndexedVariableTable());
   }

   public boolean setIndexedVariable(int var1, Object var2) {
      Object[] var3 = this.indexedVariables;
      if (var1 < var3.length) {
         Object var4 = var3[var1];
         var3[var1] = var2;
         return var4 == UNSET;
      } else {
         this.expandIndexedVariableTableAndSet(var1, var2);
         return true;
      }
   }

   public IntegerHolder counterHashCode() {
      return this.counterHashCode;
   }

   public void setCounterHashCode(IntegerHolder var1) {
      this.counterHashCode = var1;
   }

   public void setFutureListenerStackDepth(int var1) {
      this.futureListenerStackDepth = var1;
   }
}
