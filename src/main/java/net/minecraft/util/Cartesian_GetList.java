package net.minecraft.util;

import com.google.common.base.Function;
import java.util.Arrays;
import java.util.List;

public class Cartesian_GetList<T> implements Function<Object[], List<T>> {
   public Cartesian_GetList() {
   }

   public List<T> apply(Object[] var1) {
      return Arrays.asList((T[])var1);
   }
}
