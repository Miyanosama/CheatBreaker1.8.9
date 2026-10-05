package net.minecraft.network;

import io.netty.channel.socket.nio.NioSocketChannel;
import net.minecraft.client.model.ModelGhast;
import net.minecraft.command.server.CommandListBans;
import net.minecraft.entity.item.EntityEnderCrystal;

public class ThreadQuickExitException extends RuntimeException {
   public CommandListBans field_0002;
   public NioSocketChannel field_0004;
   public static ThreadQuickExitException INSTANCE = new ThreadQuickExitException();
   public ModelGhast field_0003;
   public EntityEnderCrystal field_0000;

   @Override
   public synchronized Throwable fillInStackTrace() {
      this.setStackTrace(new StackTraceElement[0]);
      return this;
   }

   public ThreadQuickExitException() {
      this.setStackTrace(new StackTraceElement[0]);
   }
}
