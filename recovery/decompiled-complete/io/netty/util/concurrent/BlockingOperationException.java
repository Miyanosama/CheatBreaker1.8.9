package io.netty.util.concurrent;

import io.netty.channel.ChannelOutboundBuffer$2;
import io.netty.channel.epoll.AbstractEpollChannel$1;
import net.minecraft.client.gui.spectator.BaseSpectatorGroup;
import net.minecraft.client.resources.data.BaseMetadataSectionSerializer;
import org.json.HTTPTokener;

public class BlockingOperationException extends IllegalStateException {
   public BaseSpectatorGroup __junk104598921428660896;
   public BaseMetadataSectionSerializer __junk5904574621122927902;
   public ChannelOutboundBuffer$2 __junk8208267146781692754;
   public HTTPTokener __junk4368952329787078283;
   public AbstractEpollChannel$1 __junk46303921020659930;
   public static long serialVersionUID;

   public BlockingOperationException(String var1) {
      super(var1);
   }

   public BlockingOperationException(Throwable var1) {
      super(var1);
   }

   public BlockingOperationException(String var1, Throwable var2) {
      super(var1, var2);
   }

   public BlockingOperationException() {
   }
}
