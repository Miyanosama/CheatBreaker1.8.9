package net.minecraft.world;

import net.minecraft.world.gen.ChunkProviderSettings$1;

public class WorldType {
   public static WorldType DEFAULT_1_1 = new WorldType(8, "default_1_1", 0).setCanBeCreated(false);
   public int worldTypeId;
   public static WorldType DEFAULT = new WorldType(0, "default", 1).setVersioned();
   public boolean field_0011;
   public boolean canBeCreated;
   public ChunkProviderSettings$1 field_0002;
   public static WorldType LARGE_BIOMES = new WorldType(2, "largeBiomes");
   public String worldType;
   public static WorldType DEBUG_WORLD = new WorldType(5, "debug_all_block_states");
   public static WorldType[] worldTypes = new WorldType[16];
   public static WorldType AMPLIFIED = new WorldType(3, "amplified").setNotificationData();
   public int generatorVersion;
   public static WorldType FLAT = new WorldType(1, "flat");
   public static WorldType CUSTOMIZED = new WorldType(4, "customized");
   public boolean field_0010;

   public WorldType setNotificationData() {
      this.field_0011 = true;
      return this;
   }

   public String getTranslateName() {
      return "generator." + this.worldType;
   }

   public int getGeneratorVersion() {
      return this.generatorVersion;
   }

   public int getWorldTypeID() {
      return this.worldTypeId;
   }

   public WorldType setVersioned() {
      this.field_0010 = true;
      return this;
   }

   public WorldType setCanBeCreated(boolean var1) {
      this.canBeCreated = var1;
      return this;
   }

   public WorldType(int var1, String var2) {
      this(var1, var2, 0);
   }

   public String getTranslatedInfo() {
      return this.getTranslateName() + ".info";
   }

   public boolean method_29047() {
      return this.field_0010;
   }

   public boolean showWorldInfoNotice() {
      return this.field_0011;
   }

   public static WorldType parseWorldType(String var0) {
      for (int var1 = 0; var1 < worldTypes.length; var1++) {
         if (worldTypes[var1] != null && worldTypes[var1].worldType.equalsIgnoreCase(var0)) {
            return worldTypes[var1];
         }
      }

      return null;
   }

   public String getWorldTypeName() {
      return this.worldType;
   }

   public boolean getCanBeCreated() {
      return this.canBeCreated;
   }

   public WorldType(int var1, String var2, int var3) {
      this.worldType = var2;
      this.generatorVersion = var3;
      this.canBeCreated = true;
      this.worldTypeId = var1;
      worldTypes[var1] = this;
   }

   public WorldType getWorldTypeForGeneratorVersion(int var1) {
      return this == DEFAULT && var1 == 0 ? DEFAULT_1_1 : this;
   }
}
