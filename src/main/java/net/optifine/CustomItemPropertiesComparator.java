package net.optifine;

import java.util.Comparator;
import net.minecraft.src.Config;
import net.optifine.CustomItemProperties;

public class CustomItemPropertiesComparator implements Comparator {
   @Override
   public int compare(Object var1, Object var2) {
      CustomItemProperties var3 = (CustomItemProperties)var1;
      CustomItemProperties var4 = (CustomItemProperties)var2;
      return var3.weight != var4.weight
         ? var4.weight - var3.weight
         : (!Config.equals(var3.basePath, var4.basePath) ? var3.basePath.compareTo(var4.basePath) : var3.name.compareTo(var4.name));
   }
}
