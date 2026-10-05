package org.apache.log4j.net;

import org.apache.log4j.Level;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.TriggeringEventEvaluator;

public class DefaultEvaluator implements TriggeringEventEvaluator {
   public boolean isTriggeringEvent(LoggingEvent var1) {
      return var1.getLevel().isGreaterOrEqual(Level.ERROR);
   }
}
