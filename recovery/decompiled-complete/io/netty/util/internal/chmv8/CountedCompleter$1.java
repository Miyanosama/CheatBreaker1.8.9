package io.netty.util.internal.chmv8;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import net.minecraft.client.particle.EntityBreakingFX$SlimeFactory;
import recovered.unidentified.UnidentifiedClass1951;
import sun.misc.Unsafe;

public class CountedCompleter$1 implements PrivilegedExceptionAction<Unsafe> {
   public UnidentifiedClass1951 __junk98375101669795794;
   public EntityBreakingFX$SlimeFactory __junk2548424127846482188;

   public Unsafe run() {
      Class<Unsafe> var1 = Unsafe.class;

      for (Field var5 : var1.getDeclaredFields()) {
         var5.setAccessible(true);
         Object var6 = var5.get(null);
         if (var1.isInstance(var6)) {
            return var1.cast(var6);
         }
      }

      throw new NoSuchFieldError("the Unsafe");
   }
}
