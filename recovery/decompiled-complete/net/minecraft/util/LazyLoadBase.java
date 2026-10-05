package net.minecraft.util;

import net.minecraft.client.particle.EntityEnchantmentTableParticleFX$EnchantmentTable;
import net.minecraft.world.WorldServerMulti$1;

public abstract class LazyLoadBase<T> {
   public T value;
   public EntityEnchantmentTableParticleFX$EnchantmentTable field_0001;
   public WorldServerMulti$1 field_0003;
   public boolean isLoaded = false;

   public abstract T load();

   public T getValue() {
      if (!this.isLoaded) {
         this.isLoaded = true;
         this.value = this.load();
      }

      return this.value;
   }
}
