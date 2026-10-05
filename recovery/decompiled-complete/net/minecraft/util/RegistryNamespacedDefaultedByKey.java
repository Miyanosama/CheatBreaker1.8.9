package net.minecraft.util;

import io.netty.channel.ThreadPerChannelEventLoopGroup$1;
import net.minecraft.client.particle.EntityHeartFX$AngryVillagerFactory;
import net.minecraft.world.biome.BiomeColorHelper$1;
import org.apache.commons.lang3.Validate;

public class RegistryNamespacedDefaultedByKey<K, V> extends RegistryNamespaced<K, V> {
   public V defaultValue;
   public EntityHeartFX$AngryVillagerFactory field_0003;
   public ThreadPerChannelEventLoopGroup$1 field_0002;
   public K defaultValueKey;
   public BiomeColorHelper$1 field_0004;

   @Override
   public void register(int var1, K var2, V var3) {
      if (this.defaultValueKey.equals(var2)) {
         this.defaultValue = (V)var3;
      }

      super.register(var1, (K)var2, (V)var3);
   }

   @Override
   public V getObject(K var1) {
      Object var2 = super.getObject((K)var1);
      return (V)(var2 == null ? this.defaultValue : var2);
   }

   @Override
   public V getObjectById(int var1) {
      Object var2 = super.getObjectById(var1);
      return (V)(var2 == null ? this.defaultValue : var2);
   }

   public void validateKey() {
      Validate.notNull(this.defaultValueKey);
   }

   public RegistryNamespacedDefaultedByKey(K var1) {
      this.defaultValueKey = (K)var1;
   }
}
