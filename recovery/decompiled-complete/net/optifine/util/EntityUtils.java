package net.optifine.util;

import java.util.HashMap;
import java.util.Map;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.src.Config;
import net.minecraft.world.chunk.storage.NibbleArrayReader;
import net.optifine.entity.model.CustomEntityModel;

public class EntityUtils {
   public static Map<Class, Integer> field_0002 = new HashMap<>();
   public static Map<String, Class> mapClassByName = new HashMap<>();
   public CustomEntityModel field_0001;
   public static Map<String, Integer> field_0003 = new HashMap<>();
   public NibbleArrayReader field_0000;

   public static int getEntityIdByName(String var0) {
      Integer var1 = field_0003.get(var0);
      return var1 == null ? -1 : var1;
   }

   public static int getEntityIdByClass(Class var0) {
      Integer var1 = field_0002.get(var0);
      return var1 == null ? -1 : var1;
   }

   public static int getEntityIdByClass(Entity var0) {
      return var0 == null ? -1 : getEntityIdByClass(var0.getClass());
   }

   static {
      for (int var0 = 0; var0 < 1000; var0++) {
         Class var1 = EntityList.getClassFromID(var0);
         if (var1 != null) {
            String var2 = EntityList.getStringFromID(var0);
            if (var2 != null) {
               if (field_0002.containsKey(var1)) {
                  Config.warn("Duplicate entity class: " + var1 + ", id1: " + field_0002.get(var1) + ", id2: " + var0);
               }

               if (field_0003.containsKey(var2)) {
                  Config.warn("Duplicate entity name: " + var2 + ", id1: " + field_0003.get(var2) + ", id2: " + var0);
               }

               if (mapClassByName.containsKey(var2)) {
                  Config.warn("Duplicate entity name: " + var2 + ", class1: " + mapClassByName.get(var2) + ", class2: " + var1);
               }

               field_0002.put(var1, var0);
               field_0003.put(var2, var0);
               mapClassByName.put(var2, var1);
            }
         }
      }
   }

   public static Class getEntityClassByName(String var0) {
      return mapClassByName.get(var0);
   }
}
