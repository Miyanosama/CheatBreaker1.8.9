package io.netty.handler.codec.serialization;

import io.netty.channel.rxtx.RxtxChannelOption;
import java.util.Map;
import net.minecraft.network.NetHandlerPlayServer$4;
import recovered.unidentified.UnidentifiedClass4790;

public class CachingClassResolver implements ClassResolver {
   public UnidentifiedClass4790 __junk2114586759858288423;
   public RxtxChannelOption __junk6443144392732531220;
   public Map<String, Class<?>> classCache;
   public NetHandlerPlayServer$4 __junk2558589956868803622;
   public ClassResolver delegate;

   @Override
   public Class<?> resolve(String var1) {
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
