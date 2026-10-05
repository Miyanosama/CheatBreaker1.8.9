package io.netty.channel.local;

import com.cheatbreaker.client.util.dash.Station;
import io.netty.channel.MultithreadEventLoopGroup;
import io.netty.util.concurrent.EventExecutor;
import java.util.concurrent.ThreadFactory;
import net.optifine.shaders.config.ShaderOptionSwitchConst;
import net.minecraft.block.BlockPotato;

public class LocalEventLoopGroup extends MultithreadEventLoopGroup {

   public LocalEventLoopGroup(int var1) {
      this(var1, null);
   }

   public LocalEventLoopGroup() {
      this(0);
   }

   public LocalEventLoopGroup(int var1, ThreadFactory var2) {
      super(var1, var2);
   }

   @Override
   public EventExecutor newChild(ThreadFactory var1, Object... var2) throws java.lang.Exception {
      return new LocalEventLoop(this, var1);
   }
}
