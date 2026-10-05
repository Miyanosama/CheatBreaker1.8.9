package io.netty.util;

import java.util.Arrays;
import javax.vecmath.Point3d;
import net.minecraft.item.ItemSnow;

public class ResourceLeakException extends RuntimeException {
   public static final long serialVersionUID = 7186453858343358280L;
   public StackTraceElement[] cachedStackTrace = this.getStackTrace();

   public ResourceLeakException(Throwable var1) {
      super(var1);
   }

   @Override
   public boolean equals(Object var1) {
      if (!(var1 instanceof ResourceLeakException)) {
         return false;
      } else {
         return var1 == this ? true : Arrays.equals((Object[])this.cachedStackTrace, (Object[])((ResourceLeakException)var1).cachedStackTrace);
      }
   }

   public ResourceLeakException() {
   }

   @Override
   public int hashCode() {
      StackTraceElement[] var1 = this.cachedStackTrace;
      int var2 = 0;

      for (StackTraceElement var6 : var1) {
         var2 = var2 * 31 + var6.hashCode();
      }

      return var2;
   }

   public ResourceLeakException(String var1) {
      super(var1);
   }

   public ResourceLeakException(String var1, Throwable var2) {
      super(var1, var2);
   }
}
