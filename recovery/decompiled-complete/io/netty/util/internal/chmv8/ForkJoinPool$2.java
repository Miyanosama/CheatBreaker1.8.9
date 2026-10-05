package io.netty.util.internal.chmv8;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import net.minecraft.entity.monster.EntitySlime$SlimeMoveHelper;
import sun.misc.Unsafe;

public class ForkJoinPool$2 implements PrivilegedExceptionAction<Unsafe> {
   public EntitySlime$SlimeMoveHelper __junk3216578928569120338;

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
