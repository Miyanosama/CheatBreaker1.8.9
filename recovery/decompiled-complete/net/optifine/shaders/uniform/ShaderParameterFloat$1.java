package net.optifine.shaders.uniform;

import net.minecraft.client.gui.stream.GuiIngestServers;
import net.optifine.util.TextureUtils$1;

// $VF: synthetic class
public class ShaderParameterFloat$1 {
   public TextureUtils$1 field_0001;
   public GuiIngestServers field_0002;

   static {
      try {
         field_0000[ShaderParameterFloat.BIOME.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         field_0000[ShaderParameterFloat.TEMPERATURE.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         field_0000[ShaderParameterFloat.RAINFALL.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
