package org.apache.log4j.pattern;

import net.minecraft.client.renderer.block.model.BreakingFour$1;
import net.minecraft.client.stream.NullStream;
import org.apache.log4j.spi.LocationInfo;
import org.apache.log4j.spi.LoggingEvent;

public class FileLocationPatternConverter extends LoggingEventPatternConverter {
   public BreakingFour$1 field_0001;
   public NullStream field_0002;
   public static FileLocationPatternConverter INSTANCE = new FileLocationPatternConverter();

   public FileLocationPatternConverter() {
      super("File Location", "file");
   }

   public void format(LoggingEvent var1, StringBuffer var2) {
      LocationInfo var3 = var1.getLocationInformation();
      if (var3 != null) {
         var2.append(var3.getFileName());
      }
   }

   public static FileLocationPatternConverter newInstance(String[] var0) {
      return INSTANCE;
   }
}
