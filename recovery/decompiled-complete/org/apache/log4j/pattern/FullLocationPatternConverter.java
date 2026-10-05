package org.apache.log4j.pattern;

import io.netty.bootstrap.AbstractBootstrap;
import javazoom.jl.player.advanced.AdvancedPlayer;
import org.apache.log4j.helpers.RelativeTimeDateFormat;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;
import recovered.unidentified.UnidentifiedClass3516;

public class FullLocationPatternConverter extends LoggingEventPatternConverter {
   public AbstractBootstrap field_0002;
   public UnidentifiedClass3516 field_0004;
   public AdvancedPlayer field_0001;
   public RelativeTimeDateFormat field_0003;
   public static FullLocationPatternConverter INSTANCE = new FullLocationPatternConverter();

   public static FullLocationPatternConverter newInstance(String[] var0) {
      return INSTANCE;
   }

   public void format(LoggingEvent var1, StringBuffer var2) {
      LocationInfo var3 = var1.getLocationInformation();
      if (var3 != null) {
         var2.append(var3.fullInfo);
      }
   }

   public FullLocationPatternConverter() {
      super("Full Location", "fullLocation");
   }
}
