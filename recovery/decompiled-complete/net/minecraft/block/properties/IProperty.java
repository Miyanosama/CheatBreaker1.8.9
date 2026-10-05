package net.minecraft.block.properties;

import java.util.Collection;

public interface IProperty<T extends Comparable<T>> {
   Class<T> getValueClass();

   String getName();

   Collection<T> getAllowedValues();

   String getName(T var1);
}
