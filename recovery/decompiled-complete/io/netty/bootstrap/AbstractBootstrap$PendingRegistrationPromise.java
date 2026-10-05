package io.netty.bootstrap;

import io.netty.channel.Channel;
import io.netty.channel.DefaultChannelPromise;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.GlobalEventExecutor;
import net.minecraft.client.model.ModelSilverfish;

public class AbstractBootstrap$PendingRegistrationPromise extends DefaultChannelPromise {
   public ModelSilverfish __junk3150330790826473971;

   @Override
   public EventExecutor executor() {
      return (EventExecutor)(this.channel().isRegistered() ? super.executor() : GlobalEventExecutor.INSTANCE);
   }

   public AbstractBootstrap$PendingRegistrationPromise(Channel var1) {
      super(var1);
   }
}
