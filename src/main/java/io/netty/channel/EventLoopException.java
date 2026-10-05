package io.netty.channel;

import io.netty.util.internal.PendingWrite;
import net.minecraft.command.CommandNotFoundException;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.util.EnumFacing;

public class EventLoopException extends ChannelException {
   public static final long serialVersionUID = -8969100344583703616L;

   public EventLoopException(Throwable var1) {
      super(var1);
   }

   public EventLoopException(String var1, Throwable var2) {
      super(var1, var2);
   }

   public EventLoopException(String var1) {
      super(var1);
   }

   public EventLoopException() {
   }
}
