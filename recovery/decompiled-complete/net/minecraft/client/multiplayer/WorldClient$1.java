package net.minecraft.client.multiplayer;

import io.netty.channel.rxtx.DefaultRxtxChannelConfig;
import io.netty.handler.codec.socks.SocksSubnegotiationVersion;
import java.util.concurrent.Callable;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.world.biome.BiomeGenBase$Height;
import net.optifine.entity.model.ModelAdapterHeadSkeleton;

public class WorldClient$1 implements Callable<String> {
   public NetworkPlayerInfo field_0005;
   public SocksSubnegotiationVersion field_0002;
   public ModelAdapterHeadSkeleton field_0004;
   public BiomeGenBase$Height field_0000;
   public DefaultRxtxChannelConfig field_0001;

   public String call() {
      return WorldClient.access$000(this.this$0).size() + " total; " + WorldClient.access$000(this.this$0).toString();
   }

   public WorldClient$1(WorldClient var1) {
      this.this$0 = var1;
      super();
   }
}
