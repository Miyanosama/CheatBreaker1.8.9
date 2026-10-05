package net.optifine.util;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.client.audio.SoundList;
import net.minecraft.util.EnchantmentNameParts;
import net.minecraft.world.gen.structure.StructureVillagePieces$House4Garden;

public class TimedEvent {
   public StructureVillagePieces$House4Garden field_0001;
   public EnchantmentNameParts field_0003;
   public static Map<String, Long> mapEventTimes = new HashMap<>();
   public SoundList field_0002;

   public static boolean isActive(String var0, long var1) {
      synchronized (mapEventTimes) {
         long var4 = System.currentTimeMillis();
         Long var6 = mapEventTimes.get(var0);
         if (var6 == null) {
            var6 = new Long(var4);
            mapEventTimes.put(var0, var6);
         }

         long var7 = var6;
         if (var4 < var7 + var1) {
            return false;
         } else {
            mapEventTimes.put(var0, new Long(var4));
            return true;
         }
      }
   }
}
