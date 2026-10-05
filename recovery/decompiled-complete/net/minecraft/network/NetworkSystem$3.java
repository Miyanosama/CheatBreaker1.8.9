package net.minecraft.network;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.local.LocalEventLoopGroup;
import io.netty.util.internal.chmv8.ForkJoinPool$WorkQueue;
import net.minecraft.client.gui.spectator.categories.TeleportToTeam$TeamSelectionObject;
import net.minecraft.util.LazyLoadBase;
import net.minecraft.world.biome.BiomeGenBase$TempCategory;

public class NetworkSystem$3 extends LazyLoadBase<LocalEventLoopGroup> {
   public ForkJoinPool$WorkQueue field_0001;
   public BiomeGenBase$TempCategory field_0002;
   public TeleportToTeam$TeamSelectionObject field_0000;

   public LocalEventLoopGroup load() {
      return new LocalEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Local Server IO #%d").setDaemon(true).build());
   }
}
