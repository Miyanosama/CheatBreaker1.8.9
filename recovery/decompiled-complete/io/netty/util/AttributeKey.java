package io.netty.util;

import io.netty.util.internal.PlatformDependent;
import java.util.concurrent.ConcurrentMap;
import net.minecraft.client.audio.PositionedSound;
import net.minecraft.network.NetworkManager$6;
import net.minecraft.world.gen.ChunkProviderHell;

public class AttributeKey<T> extends UniqueName {
   public PositionedSound __junk7521728572632242959;
   public ChunkProviderHell __junk3726349121518101917;
   public NetworkManager$6 __junk2570291452046726326;
   public static ConcurrentMap<String, Boolean> names = PlatformDependent.newConcurrentHashMap();

   public AttributeKey(String var1) {
      super(names, var1);
   }

   public static <T> AttributeKey<T> valueOf(String var0) {
      return new AttributeKey<>(var0);
   }
}
