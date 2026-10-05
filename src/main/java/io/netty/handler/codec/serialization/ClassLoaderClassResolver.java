package io.netty.handler.codec.serialization;

import com.cheatbreaker.client.module.type.ComboCounterModule;
import net.minecraft.world.gen.ChunkProviderGenerate;

public class ClassLoaderClassResolver implements ClassResolver {
   public ClassLoader classLoader;

   public ClassLoaderClassResolver(ClassLoader var1) {
      this.classLoader = var1;
   }

   @Override
   public Class<?> resolve(String var1) throws java.lang.ClassNotFoundException {
      try {
         return this.classLoader.loadClass(var1);
      } catch (ClassNotFoundException var3) {
         return Class.forName(var1, false, this.classLoader);
      }
   }
}
