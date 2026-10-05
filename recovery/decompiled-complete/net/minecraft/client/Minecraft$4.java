package net.minecraft.client;

import java.util.concurrent.Callable;
import net.minecraft.world.gen.structure.MapGenStructure$2;
import recovered.unidentified.UnidentifiedClass0517;

public class Minecraft$4 implements Callable<String> {
   public MapGenStructure$2 field_0001;
   public UnidentifiedClass0517 field_0002;

   public String call() {
      StringBuilder var1 = new StringBuilder();

      for (String var3 : this.field_90048_a.gameSettings.resourcePacks) {
         if (var1.length() > 0) {
            var1.append(", ");
         }

         var1.append(var3);
         if (this.field_90048_a.gameSettings.incompatibleResourcePacks.contains(var3)) {
            var1.append(" (incompatible)");
         }
      }

      return var1.toString();
   }

   public Minecraft$4(Minecraft var1) {
      this.field_90048_a = var1;
      super();
   }
}
