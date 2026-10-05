package net.minecraft.client.audio;

import com.google.common.collect.Maps;
import java.util.Map;

public enum SoundCategory {
      MASTER("master", 0),
      MUSIC("music", 1),
      RECORDS("record", 2),
      WEATHER("weather", 3),
      BLOCKS("block", 4),
      MOBS("hostile", 5),
      ANIMALS("neutral", 6),
      PLAYERS("player", 7),
      AMBIENT("ambient", 8);
   public String categoryName;
   public static SoundCategory[] $VALUES = new SoundCategory[]{
      MASTER, MUSIC, SoundCategory.RECORDS, SoundCategory.WEATHER, BLOCKS, MOBS, ANIMALS, PLAYERS, SoundCategory.AMBIENT
   };
   public static Map<String, SoundCategory> NAME_CATEGORY_MAP = Maps.newHashMap();
   public int categoryId;
   public static Map<Integer, SoundCategory> ID_CATEGORY_MAP = Maps.newHashMap();

   static {
      for (SoundCategory var3 : values()) {
         if (NAME_CATEGORY_MAP.containsKey(var3.getCategoryName()) || ID_CATEGORY_MAP.containsKey(var3.getCategoryId())) {
            throw new Error("Clash in Sound Category ID & Name pools! Cannot insert " + var3);
         }

         NAME_CATEGORY_MAP.put(var3.getCategoryName(), var3);
         ID_CATEGORY_MAP.put(var3.getCategoryId(), var3);
      }
   }

   SoundCategory(String var3, int var4) {
      this.categoryName = var3;
      this.categoryId = var4;
   }

   public int getCategoryId() {
      return this.categoryId;
   }

   public String getCategoryName() {
      return this.categoryName;
   }

   public static SoundCategory getCategory(String var0) {
      return NAME_CATEGORY_MAP.get(var0);
   }
}
