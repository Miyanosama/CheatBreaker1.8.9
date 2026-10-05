package net.optifine.reflect;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReflectorResolver {
   public static List<IResolvable> RESOLVABLES = Collections.synchronizedList(new ArrayList<>());
   public static boolean resolved = false;

   public static void register(IResolvable var0) {
      if (!resolved) {
         RESOLVABLES.add(var0);
      } else {
         var0.resolve();
      }
   }

   public static void resolve() {
      if (!resolved) {
         for (IResolvable var1 : RESOLVABLES) {
            var1.resolve();
         }

         resolved = true;
      }
   }
}
