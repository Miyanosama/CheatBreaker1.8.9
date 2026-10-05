package io.netty.handler.timeout;

import io.netty.util.concurrent.GlobalEventExecutor;
import net.minecraft.command.server.CommandTeleport;

public enum IdleState {
   WRITER_IDLE,
   READER_IDLE,
   ALL_IDLE;
   public GlobalEventExecutor __junk4589503774595173386;
   // $VF: synthetic field
   public static IdleState[] $VALUES = new IdleState[]{IdleState.READER_IDLE, WRITER_IDLE, IdleState.ALL_IDLE};
   public CommandTeleport __junk823453444063704780;
}
