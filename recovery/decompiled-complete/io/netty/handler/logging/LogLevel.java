package io.netty.handler.logging;

import io.netty.buffer.ByteBufProcessor$7;
import io.netty.util.internal.logging.InternalLogLevel;

public enum LogLevel {
   INFO(InternalLogLevel.INFO),
   WARN(InternalLogLevel.WARN),
   DEBUG(InternalLogLevel.DEBUG),
   TRACE(InternalLogLevel.TRACE),
   ERROR(InternalLogLevel.ERROR);

   // $VF: synthetic field
   public static LogLevel[] $VALUES = new LogLevel[]{LogLevel.TRACE, LogLevel.DEBUG, LogLevel.INFO, LogLevel.WARN, LogLevel.ERROR};
   public ByteBufProcessor$7 __junk5990106974672651548;
   public InternalLogLevel internalLevel;

   public LogLevel(InternalLogLevel var3) {
      this.internalLevel = var3;
   }

   public InternalLogLevel toInternalLevel() {
      return this.internalLevel;
   }
}
