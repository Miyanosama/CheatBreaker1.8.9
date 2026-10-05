package net.minecraft.realms;

import net.minecraft.world.storage.SaveFormatComparator;

public class RealmsLevelSummary implements Comparable<RealmsLevelSummary> {
   public SaveFormatComparator levelSummary;

   public boolean method_30270() {
      return this.levelSummary.requiresConversion();
   }

   public int compareTo(SaveFormatComparator var1) {
      return this.levelSummary.compareTo(var1);
   }

   public long method_30275() {
      return this.levelSummary.getLastTimePlayed();
   }

   public int compareTo(RealmsLevelSummary var1) {
      return this.levelSummary.getLastTimePlayed() < var1.method_30275()
         ? 1
         : (this.levelSummary.getLastTimePlayed() > var1.method_30275() ? -1 : this.levelSummary.getFileName().compareTo(var1.method_30273()));
   }

   public int getGameMode() {
      return this.levelSummary.getEnumGameType().getID();
   }

   public String method_30274() {
      return this.levelSummary.getDisplayName();
   }

   public RealmsLevelSummary(SaveFormatComparator var1) {
      this.levelSummary = var1;
   }

   public boolean method_30266() {
      return this.levelSummary.isHardcoreModeEnabled();
   }

   public long method_30267() {
      return this.levelSummary.getSizeOnDisk();
   }

   public boolean method_30276() {
      return this.levelSummary.getCheatsEnabled();
   }

   public String method_30273() {
      return this.levelSummary.getFileName();
   }
}
