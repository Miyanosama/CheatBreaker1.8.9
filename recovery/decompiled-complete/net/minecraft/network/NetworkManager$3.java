package net.minecraft.network;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.channel.local.LocalEventLoopGroup;
import net.minecraft.util.LazyLoadBase;
import net.optifine.entity.model.ModelAdapterBoat;

public class NetworkManager$3 extends LazyLoadBase<LocalEventLoopGroup> {
   public ModelAdapterBoat field_0000;

   public LocalEventLoopGroup load() {
      return new LocalEventLoopGroup(0, new ThreadFactoryBuilder().setNameFormat("Netty Local Client IO #%d").setDaemon(true).build());
   }
}
