package io.netty.channel.local;

import com.cheatbreaker.client.util.dash.Station;
import io.netty.channel.MultithreadEventLoopGroup;
import io.netty.util.concurrent.EventExecutor;
import java.util.concurrent.ThreadFactory;
import net.optifine.shaders.config.ShaderOptionSwitchConst;
import recovered.unidentified.UnidentifiedClass4731;

public class LocalEventLoopGroup extends MultithreadEventLoopGroup {
   public UnidentifiedClass4731 __junk8527260358911471625;
   public Station __junk7000560129213470836;
   public ShaderOptionSwitchConst __junk2205482827791959421;

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
   public EventExecutor newChild(ThreadFactory var1, Object... var2) {
      return new LocalEventLoop(this, var1);
   }
}
