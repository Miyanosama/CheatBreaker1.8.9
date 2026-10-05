package io.netty.handler.codec.serialization;

import io.netty.util.internal.PlatformDependent;
import java.util.HashMap;
import javax.vecmath.AxisAngle4f;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.network.NetHandlerPlayClient$3;

public class ClassResolvers {
   public GuiYesNo __junk6765193035309634220;
   public NetHandlerPlayClient$3 __junk7280534289926975622;
   public AxisAngle4f __junk4640748718206130374;

   public static ClassResolver softCachingConcurrentResolver(ClassLoader var0) {
      return new CachingClassResolver(new ClassLoaderClassResolver(defaultClassLoader(var0)), new SoftReferenceMap<>(PlatformDependent.newConcurrentHashMap()));
   }

   public static ClassResolver cacheDisabled(ClassLoader var0) {
      return new ClassLoaderClassResolver(defaultClassLoader(var0));
   }

   public static ClassResolver weakCachingResolver(ClassLoader var0) {
      return new CachingClassResolver(new ClassLoaderClassResolver(defaultClassLoader(var0)), new WeakReferenceMap<>(new HashMap<>()));
   }

   public static ClassLoader defaultClassLoader(ClassLoader var0) {
      if (var0 != null) {
         return var0;
      } else {
         ClassLoader var1 = PlatformDependent.getContextClassLoader();
         return var1 != null ? var1 : PlatformDependent.getClassLoader(ClassResolvers.class);
      }
   }

   public static ClassResolver softCachingResolver(ClassLoader var0) {
      return new CachingClassResolver(new ClassLoaderClassResolver(defaultClassLoader(var0)), new SoftReferenceMap<>(new HashMap<>()));
   }

   public static ClassResolver weakCachingConcurrentResolver(ClassLoader var0) {
      return new CachingClassResolver(new ClassLoaderClassResolver(defaultClassLoader(var0)), new WeakReferenceMap<>(PlatformDependent.newConcurrentHashMap()));
   }
}
