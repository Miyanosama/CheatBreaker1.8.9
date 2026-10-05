package io.netty.handler.logging;

import io.netty.util.internal.logging.InternalLogLevel;

public enum LogLevel {
      TRACE(InternalLogLevel.TRACE),
      DEBUG(InternalLogLevel.DEBUG),
      INFO(InternalLogLevel.INFO),
      WARN(InternalLogLevel.WARN),
      ERROR(InternalLogLevel.ERROR);

   public static LogLevel[] $VALUES = new LogLevel[]{LogLevel.TRACE, LogLevel.DEBUG, LogLevel.INFO, LogLevel.WARN, LogLevel.ERROR};
   public InternalLogLevel internalLevel;

   LogLevel(InternalLogLevel var3) {
      this.internalLevel = var3;
   }

   public InternalLogLevel toInternalLevel() {
      return this.internalLevel;
   }
}
