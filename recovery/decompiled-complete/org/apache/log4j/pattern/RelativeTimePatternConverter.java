package org.apache.log4j.pattern;

import com.jagrosh.discordipc.entities.pipe.WindowsPipe;
import javazoom.jl.decoder.LayerIIIDecoder$temporaire;
import net.minecraft.entity.Entity$4;
import org.apache.log4j.spi.LoggingEvent;

public class RelativeTimePatternConverter extends LoggingEventPatternConverter {
   public RelativeTimePatternConverter$CachedTimestamp lastTimestamp = new RelativeTimePatternConverter$CachedTimestamp(46926880L & 136446472L, "");
   public Entity$4 field_0003;
   public WindowsPipe field_0000;
   public LayerIIIDecoder$temporaire field_0002;

   public static RelativeTimePatternConverter newInstance(String[] var0) {
      return new RelativeTimePatternConverter();
   }

   public RelativeTimePatternConverter() {
      super("Time", "time");
   }

   public void format(LoggingEvent var1, StringBuffer var2) {
      long var3 = var1.timeStamp;
      if (!this.lastTimestamp.format(var3, var2)) {
         String var5 = Long.toString(var3 - LoggingEvent.getStartTime());
         var2.append(var5);
         this.lastTimestamp = new RelativeTimePatternConverter$CachedTimestamp(var3, var5);
      }
   }
}
