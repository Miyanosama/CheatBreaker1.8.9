package javazoom.jl.player.advanced;

import org.apache.log4j.pattern.NameAbbreviator;
import org.apache.log4j.pattern.PatternParser;
import org.json.HTTP;

public class PlaybackEvent {
   public int frame;
   public AdvancedPlayer source;
   public int id;
   public static int STOPPED = 1;
   public static int STARTED = 2;

   public AdvancedPlayer getSource() {
      return this.source;
   }

   public int getId() {
      return this.id;
   }

   public int getFrame() {
      return this.frame;
   }

   public void setSource(AdvancedPlayer var1) {
      this.source = var1;
   }

   public void setId(int var1) {
      this.id = var1;
   }

   public void setFrame(int var1) {
      this.frame = var1;
   }

   public PlaybackEvent(AdvancedPlayer var1, int var2, int var3) {
      this.id = var2;
      this.source = var1;
      this.frame = var3;
   }
}
