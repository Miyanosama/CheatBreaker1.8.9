package io.netty.util.internal.logging;

import io.netty.buffer.UnsafeDirectSwappedByteBuf;
import net.minecraft.item.ItemAxe;
import net.minecraft.scoreboard.Score;
import net.optifine.entity.model.CustomEntityModelParser;

public enum InternalLogLevel {
      TRACE,
      DEBUG,
      INFO,
      WARN,
      ERROR;
   public static InternalLogLevel[] $VALUES = new InternalLogLevel[]{InternalLogLevel.TRACE, InternalLogLevel.DEBUG, INFO, WARN, ERROR};
}
