package net.minecraft.realms;

import com.cheatbreaker.client.config.Setting;
import io.netty.handler.ssl.util.FingerprintTrustManagerFactory$2;
import junit.framework.TestFailure;
import net.minecraft.client.audio.SoundManager;
import net.minecraft.world.chunk.storage.AnvilSaveHandler;
import net.minecraft.world.storage.SaveFormatComparator;
import org.apache.log4j.lf5.viewer.LogTable$LogTableListSelectionListener;

public class RealmsLevelSummary implements Comparable<RealmsLevelSummary> {
   public AnvilSaveHandler field_0003;
   public FingerprintTrustManagerFactory$2 field_0005;
   public TestFailure field_0002;
   public Setting field_0004;
   public SaveFormatComparator levelSummary;
   public LogTable$LogTableListSelectionListener field_0001;
   public SoundManager field_0006;

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
