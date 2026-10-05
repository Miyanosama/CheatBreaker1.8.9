package io.netty.handler.codec.serialization;

import io.netty.channel.rxtx.RxtxChannelOption;
import java.util.Map;
import com.cheatbreaker.client.util.ClientStartupListener;

public class CachingClassResolver implements ClassResolver {
   public Map<String, Class<?>> classCache;
   public ClassResolver delegate;

   @Override
   public Class<?> resolve(String var1) throws java.lang.ClassNotFoundException {
      Class var2 = this.classCache.get(var1);
      if (var2 != null) {
         return var2;
      } else {
         var2 = this.delegate.resolve(var1);
         this.classCache.put(var1, var2);
         return var2;
      }
   }

   public CachingClassResolver(ClassResolver var1, Map<String, Class<?>> var2) {
      this.delegate = var1;
      this.classCache = var2;
   }
}
