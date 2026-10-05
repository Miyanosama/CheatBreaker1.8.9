package net.minecraft.entity.item;

import net.minecraft.stats.AchievementList;
import net.optifine.SmartAnimations;

public enum EntityPainting$EnumArt {
   PIGSCENE("Pigscene", 64, 64, 64, 192),
   AZTEC_2("Aztec2", 16, 16, 48, 0),
   CREEBET("Creebet", 32, 16, 128, 32),
   WITHER("Wither", 32, 32, 160, 128),
   AZTEC("Aztec", 16, 16, 16, 0),
   STAGE("Stage", 32, 32, 64, 128),
   POINTER("Pointer", 64, 64, 0, 192),
   MATCH("Match", 32, 32, 0, 128),
   SKULL_AND_ROSES("SkullAndRoses", 32, 32, 128, 128),
   KEBAB("Kebab", 16, 16, 0, 0),
   PLANT("Plant", 16, 16, 80, 0),
   BURNING_SKULL("BurningSkull", 64, 64, 128, 192),
   COURBET("Courbet", 32, 16, 32, 32),
   SKELETON("Skeleton", 64, 48, 192, 64),
   POOL("Pool", 32, 16, 0, 32),
   SUNSET("Sunset", 32, 16, 96, 32),
   BUST("Bust", 32, 32, 32, 128),
   WASTELAND("Wasteland", 16, 16, 96, 0),
   SEA("Sea", 32, 16, 64, 32),
   WANDERER("Wanderer", 16, 32, 0, 64),
   ALBAN("Alban", 16, 16, 32, 0),
   DONKEY_KONG("DonkeyKong", 64, 48, 192, 112),
   GRAHAM("Graham", 16, 32, 16, 64),
   BOMB("Bomb", 16, 16, 64, 0),
   VOID("Void", 32, 32, 96, 128),
   FIGHTERS("Fighters", 64, 32, 0, 96);

   public SmartAnimations field_0017;
   public int sizeY;
   // $VF: synthetic field
   public static EntityPainting$EnumArt[] $VALUES = new EntityPainting$EnumArt[]{
      EntityPainting$EnumArt.KEBAB,
      EntityPainting$EnumArt.AZTEC,
      EntityPainting$EnumArt.ALBAN,
      AZTEC_2,
      EntityPainting$EnumArt.BOMB,
      EntityPainting$EnumArt.PLANT,
      EntityPainting$EnumArt.WASTELAND,
      EntityPainting$EnumArt.POOL,
      EntityPainting$EnumArt.COURBET,
      EntityPainting$EnumArt.SEA,
      EntityPainting$EnumArt.SUNSET,
      CREEBET,
      EntityPainting$EnumArt.WANDERER,
      EntityPainting$EnumArt.GRAHAM,
      EntityPainting$EnumArt.MATCH,
      EntityPainting$EnumArt.BUST,
      EntityPainting$EnumArt.STAGE,
      EntityPainting$EnumArt.VOID,
      EntityPainting$EnumArt.SKULL_AND_ROSES,
      WITHER,
      EntityPainting$EnumArt.FIGHTERS,
      EntityPainting$EnumArt.POINTER,
      PIGSCENE,
      EntityPainting$EnumArt.BURNING_SKULL,
      EntityPainting$EnumArt.SKELETON,
      EntityPainting$EnumArt.DONKEY_KONG
   };
   public static int field_180001_A = "SkullAndRoses".length();
   public int sizeX;
   public int offsetX;
   public String title;
   public int offsetY;
   public AchievementList field_0010;

   public EntityPainting$EnumArt(String var3, int var4, int var5, int var6, int var7) {
      this.title = var3;
      this.sizeX = var4;
      this.sizeY = var5;
      this.offsetX = var6;
      this.offsetY = var7;
   }
}
