package net.minecraft.util;

public class RegistryDefaulted<K, V> extends RegistrySimple<K, V> {
   public V defaultObject;

   @Override
   public V getObject(K var1) {
      Object var2 = super.getObject((K)var1);
      return (V)(var2 == null ? this.defaultObject : var2);
   }

   public RegistryDefaulted(V var1) {
      this.defaultObject = (V)var1;
   }
}
