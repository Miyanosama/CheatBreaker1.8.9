package net.minecraft.util;

import org.apache.commons.lang3.Validate;

public class RegistryNamespacedDefaultedByKey<K, V> extends RegistryNamespaced<K, V> {
   public V defaultValue;
   public K defaultValueKey;

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
