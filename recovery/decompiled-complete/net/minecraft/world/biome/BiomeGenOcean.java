package net.minecraft.world.biome;

import com.jagrosh.discordipc.entities.pipe.Pipe;
import java.util.Random;
import net.minecraft.block.BlockStem$1;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeGenOcean extends BiomeGenBase {
   public BlockStem$1 field_0000;
   public Pipe field_0001;

   @Override
   public BiomeGenBase$TempCategory getTempCategory() {
      return BiomeGenBase$TempCategory.OCEAN;
   }

   public BiomeGenOcean(int var1) {
      super(var1);
      this.au.clear();
   }

   @Override
   public void genTerrainBlocks(World var1, Random var2, ChunkPrimer var3, int var4, int var5, double var6) {
      super.genTerrainBlocks(var1, var2, var3, var4, var5, var6);
   }
}
