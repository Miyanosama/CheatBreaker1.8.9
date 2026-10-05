package io.netty.util.internal.chmv8;

import java.lang.reflect.Field;
import java.security.PrivilegedExceptionAction;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.server.management.UserListBansEntry;
import sun.misc.Unsafe;

public class ConcurrentHashMapV8$1 implements PrivilegedExceptionAction<Unsafe> {
   public ContainerBeacon __junk4525047926647794379;
   public UserListBansEntry __junk4745845891603553030;

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
