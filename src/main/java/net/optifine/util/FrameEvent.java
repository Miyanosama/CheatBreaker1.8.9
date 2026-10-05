package net.optifine.util;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.Minecraft;

public class FrameEvent {
   public static Map<String, Integer> mapEventFrames = new HashMap<>();

   public static boolean isActive(String var0, int var1) {
      synchronized (mapEventFrames) {
         int var3 = Minecraft.getMinecraft().entityRenderer.frameCount;
         Integer var4 = mapEventFrames.get(var0);
         if (var4 == null) {
            var4 = new Integer(var3);
            mapEventFrames.put(var0, var4);
         }

         int var5 = var4;
         if (var3 > var5 && var3 < var5 + var1) {
            return false;
         } else {
            mapEventFrames.put(var0, new Integer(var3));
            return true;
         }
      }
   }
}
