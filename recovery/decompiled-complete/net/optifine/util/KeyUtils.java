package net.optifine.util;

import java.util.Arrays;
import java.util.HashSet;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.command.EntityNotFoundException;

public class KeyUtils {
   public EntityNotFoundException field_0000;

   public static void fixKeyConflicts(KeyBinding[] var0, KeyBinding[] var1) {
      HashSet var2 = new HashSet();

      for (int var3 = 0; var3 < var1.length; var3++) {
         KeyBinding var4 = var1[var3];
         var2.add(var4.getKeyCode());
      }

      HashSet var7 = new HashSet<>(Arrays.asList(var0));
      var7.removeAll(Arrays.asList(var1));

      for (KeyBinding var5 : var7) {
         Integer var6 = var5.getKeyCode();
         if (var2.contains(var6)) {
            var5.setKeyCode(0);
         }
      }
   }
}
