package io.netty.channel;

import io.netty.util.internal.PendingWrite;
import net.minecraft.command.CommandNotFoundException;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.util.EnumFacing$Axis;

public class EventLoopException extends ChannelException {
   public CompressedStreamTools __junk8310155627199290306;
   public EnumFacing$Axis __junk8457376421467737682;
   public CommandNotFoundException __junk2014299276434243258;
   public static long serialVersionUID;
   public PendingWrite __junk603163924271062261;

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
