package io.netty.handler.timeout;

import net.minecraft.world.gen.feature.WorldGenIceSpike;

public class IdleStateEvent {
   public static IdleStateEvent FIRST_WRITER_IDLE_STATE_EVENT = new IdleStateEvent(IdleState.WRITER_IDLE, true);
   public boolean first;
   public static IdleStateEvent READER_IDLE_STATE_EVENT = new IdleStateEvent(IdleState.READER_IDLE, false);
   public static IdleStateEvent FIRST_ALL_IDLE_STATE_EVENT = new IdleStateEvent(IdleState.ALL_IDLE, true);
   public WorldGenIceSpike __junk5861225010105516773;
   public static IdleStateEvent ALL_IDLE_STATE_EVENT = new IdleStateEvent(IdleState.ALL_IDLE, false);
   public static IdleStateEvent FIRST_READER_IDLE_STATE_EVENT = new IdleStateEvent(IdleState.READER_IDLE, true);
   public IdleState state;
   public static IdleStateEvent WRITER_IDLE_STATE_EVENT = new IdleStateEvent(IdleState.WRITER_IDLE, false);

   public IdleState state() {
      return this.state;
   }

   public boolean isFirst() {
      return this.first;
   }

   public IdleStateEvent(IdleState var1, boolean var2) {
      this.state = var1;
      this.first = var2;
   }
}
