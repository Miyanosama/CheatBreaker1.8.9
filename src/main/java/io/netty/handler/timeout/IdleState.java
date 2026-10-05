package io.netty.handler.timeout;

import io.netty.util.concurrent.GlobalEventExecutor;
import net.minecraft.command.server.CommandTeleport;

public enum IdleState {
      READER_IDLE,
      WRITER_IDLE,
      ALL_IDLE;
   public static IdleState[] $VALUES = new IdleState[]{IdleState.READER_IDLE, WRITER_IDLE, IdleState.ALL_IDLE};
}
