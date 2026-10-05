package io.netty.handler.codec.serialization;

import com.cheatbreaker.client.module.type.ComboCounterModule;
import io.netty.handler.codec.socks.SocksCmdRequestDecoder$1;
import net.minecraft.world.gen.ChunkProviderGenerate;

public class ClassLoaderClassResolver implements ClassResolver {
   public ClassLoader classLoader;
   public ComboCounterModule __junk2967447300020976290;
   public ChunkProviderGenerate __junk1632243713033213895;
   public SocksCmdRequestDecoder$1 __junk8995218205341280706;

   public ClassLoaderClassResolver(ClassLoader var1) {
      this.classLoader = var1;
   }

   @Override
   public Class<?> resolve(String var1) {
      try {
         return this.classLoader.loadClass(var1);
      } catch (ClassNotFoundException var3) {
         return Class.forName(var1, false, this.classLoader);
      }
   }
}
