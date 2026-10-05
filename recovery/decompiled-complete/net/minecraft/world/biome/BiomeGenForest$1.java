package net.minecraft.world.biome;

import com.cheatbreaker.client.ui.fading.MinMaxFade;
import io.netty.channel.rxtx.RxtxChannelConfig$Stopbits;
import java.util.Random;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.network.play.client.C02PacketUseEntity;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BiomeGenForest$1 extends BiomeGenMutated {
   public C02PacketUseEntity field_0000;
   public MinMaxFade field_0005;
   public ChunkProviderClient field_0002;
   public ModelBox field_0001;
   public RxtxChannelConfig$Stopbits field_0004;

   @Override
   public void decorate(World var1, Random var2, BlockPos var3) {
      this.baseBiome.decorate(var1, var2, var3);
   }

   public BiomeGenForest$1(BiomeGenForest var1, int var2, BiomeGenBase var3) {
      this.field_0003 = var1;
      super(var2, var3);
   }
}
