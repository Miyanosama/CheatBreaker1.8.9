package io.netty.util.concurrent;

import java.util.concurrent.ThreadFactory;
import net.minecraft.client.audio.MusicTicker;
import net.minecraft.world.gen.structure.StructureMineshaftPieces$Corridor;

public class DefaultEventExecutorGroup extends MultithreadEventExecutorGroup {
   public MusicTicker __junk5555151687426946458;
   public StructureMineshaftPieces$Corridor __junk8169967143315099410;

   @Override
   public EventExecutor newChild(ThreadFactory var1, Object... var2) {
      return new DefaultEventExecutor(this, var1);
   }

   public DefaultEventExecutorGroup(int var1, ThreadFactory var2) {
      super(var1, var2);
   }

   public DefaultEventExecutorGroup(int var1) {
      this(var1, null);
   }
}
