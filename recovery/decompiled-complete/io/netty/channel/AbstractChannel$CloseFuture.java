package io.netty.channel;

import net.minecraft.world.storage.WorldInfo$8;
import org.apache.log4j.pattern.FullLocationPatternConverter;

public class AbstractChannel$CloseFuture extends DefaultChannelPromise {
   public WorldInfo$8 __junk6461961932021892748;
   public FullLocationPatternConverter __junk3668443962701907138;

   @Override
   public ChannelPromise setSuccess() {
      throw new IllegalStateException();
   }

   @Override
   public ChannelPromise setFailure(Throwable var1) {
      throw new IllegalStateException();
   }

   public AbstractChannel$CloseFuture(AbstractChannel var1) {
      super(var1);
   }

   @Override
   public boolean tryFailure(Throwable var1) {
      throw new IllegalStateException();
   }

   public boolean setClosed() {
      return super.trySuccess();
   }

   @Override
   public boolean trySuccess() {
      throw new IllegalStateException();
   }
}
