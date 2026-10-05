package org.apache.log4j;

import com.cheatbreaker.client.ui.fading.CosineFade;
import java.text.MessageFormat;
import net.minecraft.client.resources.FallbackResourceManager$InputStreamLeakedResourceLogger;
import net.minecraft.world.gen.structure.StructureMineshaftPieces$Stairs;
import org.apache.log4j.spi.LoggingEvent;

public class AsyncAppender$DiscardSummary {
   public LoggingEvent maxEvent;
   public CosineFade field_0004;
   public StructureMineshaftPieces$Stairs field_0001;
   public int count;
   public FallbackResourceManager$InputStreamLeakedResourceLogger field_0000;

   public LoggingEvent createEvent() {
      String var1 = MessageFormat.format("Discarded {0} messages due to full event buffer including: {1}", new Integer(this.count), this.maxEvent.getMessage());
      return new LoggingEvent(
         "org.apache.log4j.AsyncAppender.DONT_REPORT_LOCATION", Logger.getLogger(this.maxEvent.getLoggerName()), this.maxEvent.getLevel(), var1, null
      );
   }

   public void add(LoggingEvent var1) {
      if (var1.getLevel().toInt() > this.maxEvent.getLevel().toInt()) {
         this.maxEvent = var1;
      }

      this.count++;
   }

   public AsyncAppender$DiscardSummary(LoggingEvent var1) {
      this.maxEvent = var1;
      this.count = 1;
   }
}
