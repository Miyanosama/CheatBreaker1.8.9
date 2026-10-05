package net.minecraftforge.common.property;

public interface IUnlistedProperty<V> {
   String valueToString(V var1);

   Class<V> getType();

   String getName();

   boolean isValid(V var1);
}
