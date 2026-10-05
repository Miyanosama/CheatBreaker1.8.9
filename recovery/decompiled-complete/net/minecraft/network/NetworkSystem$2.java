package net.minecraft.network;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.epoll.EpollEventLoopGroup;
import net.minecraft.block.BlockGlowstone;
import net.minecraft.command.server.CommandSaveOn;
import net.minecraft.util.LazyLoadBase;
import net.optifine.shaders.ProgramStack;

public class NetworkSystem$2 extends LazyLoadBase<EpollEventLoopGroup> {
   public BlockGlowstone field_0001;
   public CommandSaveOn field_0002;
   public ProgramStack field_0000;

   public EpollEventLoopGroup load() {
      return new EpollEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Epoll Server IO #%d").setDaemon(true).build());
   }
}
