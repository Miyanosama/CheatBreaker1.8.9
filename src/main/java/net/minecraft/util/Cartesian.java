package net.minecraft.util;

import net.minecraft.util.Cartesian$Product;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.util.Cartesian_GetList;

public class Cartesian {
   public static <T> T[] method_08278(Class<? super T> var0, Iterable<? extends T> var1) {
      ArrayList var2 = Lists.newArrayList();

      for (Object var4 : var1) {
         var2.add(var4);
      }

      return (T[])var2.toArray(method_08280(var0, var2.size()));
   }

   public static <T> Iterable<List<T>> method_08279(Iterable<? extends Iterable<? extends T>> var0) {
      return method_08282(method_08281(Object.class, var0));
   }

   // $VF: synthetic method
   public static Object[] method_08277(Class var0, int var1) {
      return method_08280(var0, var1);
   }

   public static <T> T[] method_08280(Class<? super T> var0, int var1) {
      return (T[])((Object[])Array.newInstance(var0, var1));
   }

   public static <T> Iterable<T[]> method_08281(Class<T> var0, Iterable<? extends Iterable<? extends T>> var1) {
      return new Cartesian$Product<>(var0, method_08278(Iterable.class, var1));
   }

   public static <T> Iterable<List<T>> method_08282(Iterable<Object[]> var0) {
      return Iterables.transform(var0, new Cartesian_GetList<>());
   }
}
