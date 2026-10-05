package net.minecraft.world.biome;

import io.netty.handler.codec.http.DefaultHttpRequest;
import io.netty.util.concurrent.DefaultThreadFactory$DefaultRunnableDecorator;
import net.minecraft.tileentity.TileEntityCommandBlock$1;
import net.minecraft.util.BlockPos;
import org.slf4j.helpers.Util$1;

public class BiomeColorHelper$3 implements BiomeColorHelper$ColorResolver {
   public TileEntityCommandBlock$1 field_0001;
   public DefaultHttpRequest field_0003;
   public DefaultThreadFactory$DefaultRunnableDecorator field_0000;
   public Util$1 field_0002;

   @Override
   public int getColorAtPos(BiomeGenBase var1, BlockPos var2) {
      return var1.ar;
   }
}
