package org.apache.log4j;

import net.minecraft.block.BlockIce;
import net.minecraft.event.HoverEvent;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.NOPLogger;

public class DefaultCategoryFactory implements LoggerFactory {
   public NOPLogger field_0001;
   public HoverEvent field_0002;
   public BlockIce field_0000;

   public Logger makeNewLoggerInstance(String var1) {
      return new Logger(var1);
   }
}
