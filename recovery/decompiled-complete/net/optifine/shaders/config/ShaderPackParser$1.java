package net.optifine.shaders.config;

import java.util.Comparator;
import net.optifine.CustomGuiProperties$EnumContainer;

public class ShaderPackParser$1 implements Comparator<ShaderOption> {
   public CustomGuiProperties$EnumContainer field_0000;

   public int compare(ShaderOption var1, ShaderOption var2) {
      return var1.getName().compareToIgnoreCase(var2.getName());
   }
}
