package net.optifine;

import java.util.Comparator;
import net.minecraft.block.BlockLog;

public class CustomItems$1 implements Comparator {
   public BlockLog field_0000;

   @Override
   public int compare(Object var1, Object var2) {
      CustomItemProperties var3 = (CustomItemProperties)var1;
      CustomItemProperties var4 = (CustomItemProperties)var2;
      return var3.layer != var4.layer
         ? var3.layer - var4.layer
         : (
            var3.weight != var4.weight
               ? var4.weight - var3.weight
               : (!var3.basePath.equals(var4.basePath) ? var3.basePath.compareTo(var4.basePath) : var3.name.compareTo(var4.name))
         );
   }
}
