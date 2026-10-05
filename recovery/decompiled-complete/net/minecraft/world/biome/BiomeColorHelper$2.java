package net.minecraft.world.biome;

import io.netty.handler.codec.http.HttpMethod;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceMappingsTask;
import net.minecraft.util.BlockPos;

public class BiomeColorHelper$2 implements BiomeColorHelper$ColorResolver {
   public ConcurrentHashMapV8$MapReduceMappingsTask field_0000;
   public HttpMethod field_0001;

   @Override
   public int getColorAtPos(BiomeGenBase var1, BlockPos var2) {
      return var1.getFoliageColorAtPos(var2);
   }
}
