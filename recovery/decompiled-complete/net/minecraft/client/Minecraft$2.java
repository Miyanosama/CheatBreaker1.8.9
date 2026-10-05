package net.minecraft.client;

import java.util.concurrent.Callable;
import net.optifine.shaders.uniform.ShaderUniform2f;

public class Minecraft$2 implements Callable<String> {
   public ShaderUniform2f field_0001;

   public String call() {
      String var1 = ClientBrandRetriever.getClientModName();
      return !var1.equals("vanilla")
         ? "Definitely; Client brand changed to '" + var1 + "'"
         : (
            Minecraft.class.getSigners() == null
               ? "Very likely; Jar signature invalidated"
               : "Probably not. Jar signature remains and client brand is untouched."
         );
   }

   public Minecraft$2(Minecraft var1) {
      this.field_90051_a = var1;
      super();
   }
}
