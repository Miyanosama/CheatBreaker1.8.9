package io.netty.util.concurrent;

import java.util.concurrent.ThreadFactory;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.world.gen.structure.StructureMineshaftPieces;

public class DefaultEventExecutorGroup extends MultithreadEventExecutorGroup {

   @Override
   public EventExecutor newChild(ThreadFactory var1, Object... var2) throws java.lang.Exception {
      return new DefaultEventExecutor(this, var1);
   }

   public DefaultEventExecutorGroup(int var1, ThreadFactory var2) {
      super(var1, var2);
   }

   public DefaultEventExecutorGroup(int var1) {
      this(var1, null);
   }
}
