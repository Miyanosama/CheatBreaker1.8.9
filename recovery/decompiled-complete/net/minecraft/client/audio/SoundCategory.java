package net.minecraft.client.audio;

import com.google.common.collect.Maps;
import java.util.Map;
import net.minecraft.client.Minecraft$4;
import net.minecraft.client.particle.MobAppearance$Factory;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;

public enum SoundCategory {
   MUSIC("music", 1),
   BLOCKS("block", 4),
   ANIMALS("neutral", 6),
   MOBS("hostile", 5),
   PLAYERS("player", 7),
   MASTER("master", 0),
   AMBIENT("ambient", 8),
   WEATHER("weather", 3),
   RECORDS("record", 2);
   public String categoryName;
   public static Map<Integer, SoundCategory> ID_CATEGORY_MAP = Maps.newHashMap();
   public MobAppearance$Factory field_0010;
   public static Map<String, SoundCategory> NAME_CATEGORY_MAP = Maps.newHashMap();
   public int categoryId;
   // $VF: synthetic field
   public static SoundCategory[] $VALUES = new SoundCategory[]{
      MASTER, MUSIC, SoundCategory.RECORDS, SoundCategory.WEATHER, BLOCKS, MOBS, ANIMALS, PLAYERS, SoundCategory.AMBIENT
   };
   public Minecraft$4 field_0011;
   public EntityAIMoveThroughVillage field_0013;

   static {
      for (SoundCategory var3 : values()) {
         if (NAME_CATEGORY_MAP.containsKey(var3.getCategoryName()) || ID_CATEGORY_MAP.containsKey(var3.getCategoryId())) {
            throw new Error("Clash in Sound Category ID & Name pools! Cannot insert " + var3);
         }

         NAME_CATEGORY_MAP.put(var3.getCategoryName(), var3);
         ID_CATEGORY_MAP.put(var3.getCategoryId(), var3);
      }
   }

   public SoundCategory(String var3, int var4) {
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
