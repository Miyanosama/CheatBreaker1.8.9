package io.netty.util;

import io.netty.util.internal.PlatformDependent;
import java.util.concurrent.ConcurrentMap;
import net.minecraft.client.audio.PositionedSound;
import net.minecraft.world.gen.ChunkProviderHell;

public class AttributeKey<T> extends UniqueName {
   public static ConcurrentMap<String, Boolean> names = PlatformDependent.newConcurrentHashMap();

   public AttributeKey(String var1) {
      super(names, var1);
   }

   public static <T> AttributeKey<T> valueOf(String var0) {
      return new AttributeKey<>(var0);
   }
}
