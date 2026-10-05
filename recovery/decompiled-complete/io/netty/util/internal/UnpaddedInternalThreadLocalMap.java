package io.netty.util.internal;

import io.netty.handler.codec.compression.ZlibDecoder;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import net.optifine.shaders.uniform.CustomUniforms;

public class UnpaddedInternalThreadLocalMap {
   public ZlibDecoder __junk4643406376506612611;
   public Map<Class<?>, Map<String, TypeParameterMatcher>> typeParameterMatcherFindCache;
   public StringBuilder stringBuilder;
   public int futureListenerStackDepth;
   public Map<Charset, CharsetEncoder> charsetEncoderCache;
   public static AtomicInteger nextIndex = new AtomicInteger();
   public ThreadLocalRandom random;
   public static ThreadLocal<InternalThreadLocalMap> slowThreadLocalMap;
   public int localChannelReaderStackDepth;
   public Map<Charset, CharsetDecoder> charsetDecoderCache;
   public Map<Class<?>, Boolean> handlerSharableCache;
   public Object[] indexedVariables;
   public IntegerHolder counterHashCode;
   public Map<Class<?>, TypeParameterMatcher> typeParameterMatcherGetCache;
   public CustomUniforms __junk7022001986602487550;

   public UnpaddedInternalThreadLocalMap(Object[] var1) {
      this.indexedVariables = var1;
   }
}
