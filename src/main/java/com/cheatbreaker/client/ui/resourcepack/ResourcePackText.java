package com.cheatbreaker.client.ui.resourcepack;

import net.minecraft.client.Minecraft;

public class ResourcePackText {
   public static Minecraft recoveredField2039 = Minecraft.getMinecraft();

   public static String method_30076(String var0, int var1) {
      if (var0.endsWith(".zip")) {
         var0 = var0.substring(0, var0.length() - 4);
      }

      if (recoveredField2039.fontRendererObj.getStringWidth(var0) > var1) {
         var0 = recoveredField2039.fontRendererObj.trimStringToWidth(var0, var1 - recoveredField2039.fontRendererObj.getStringWidth("...")) + "...";
      }

      return var0;
   }
}
