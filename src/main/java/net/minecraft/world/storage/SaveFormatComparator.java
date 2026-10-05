package net.minecraft.world.storage;

import net.minecraft.world.WorldSettings;

public class SaveFormatComparator implements Comparable<SaveFormatComparator> {
   public boolean cheatsEnabled;
   public String fileName;
   public long lastTimePlayed;
   public boolean hardcore;
   public boolean requiresConversion;
   public WorldSettings.GameType theEnumGameType;
   public long sizeOnDisk;
   public String displayName;

   public SaveFormatComparator(String var1, String var2, long var3, long var5, WorldSettings.GameType var7, boolean var8, boolean var9, boolean var10) {
      this.fileName = var1;
      this.displayName = var2;
      this.lastTimePlayed = var3;
      this.sizeOnDisk = var5;
      this.theEnumGameType = var7;
      this.requiresConversion = var8;
      this.hardcore = var9;
      this.cheatsEnabled = var10;
   }

   public boolean getCheatsEnabled() {
      return this.cheatsEnabled;
   }

   public int compareTo(SaveFormatComparator var1) {
      return this.lastTimePlayed < var1.lastTimePlayed ? 1 : (this.lastTimePlayed > var1.lastTimePlayed ? -1 : this.fileName.compareTo(var1.fileName));
   }

   public long getLastTimePlayed() {
      return this.lastTimePlayed;
   }

   public String getDisplayName() {
      return this.displayName;
   }

   public long getSizeOnDisk() {
      return this.sizeOnDisk;
   }

   public String getFileName() {
      return this.fileName;
   }

   public WorldSettings.GameType getEnumGameType() {
      return this.theEnumGameType;
   }

   public boolean isHardcoreModeEnabled() {
      return this.hardcore;
   }

   public boolean requiresConversion() {
      return this.requiresConversion;
   }
}
