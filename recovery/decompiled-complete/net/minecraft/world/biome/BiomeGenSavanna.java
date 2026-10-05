package net.minecraft.world.biome;

import com.cheatbreaker.client.util.server.ServerMappingLoader;
import java.util.Random;
import net.minecraft.block.BlockDoublePlant$EnumPlantType;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenSavannaTree;
import org.apache.log4j.helpers.PatternParser$LiteralPatternConverter;

public class BiomeGenSavanna extends BiomeGenBase {
   public ServerMappingLoader field_0000;
   public PatternParser$LiteralPatternConverter field_0002;
   public static WorldGenSavannaTree field_150627_aC = new WorldGenSavannaTree(false);

   @Override
   public void decorate(World var1, Random var2, BlockPos var3) {
      ag.setPlantType(BlockDoublePlant$EnumPlantType.GRASS);

      for (int var4 = 0; var4 < 7; var4++) {
         int var5 = var2.nextInt(16) + 8;
         int var6 = var2.nextInt(16) + 8;
         int var7 = var2.nextInt(var1.getHeight(var3.add(var5, 0, var6)).getY() + 32);
         ag.generate(var1, var2, var3.add(var5, var7, var6));
      }

      super.decorate(var1, var2, var3);
   }

   @Override
   public BiomeGenBase createMutatedBiome(int var1) {
      BiomeGenSavanna$Mutated var2 = new BiomeGenSavanna$Mutated(var1, this);
      var2.ap = (this.ap + 1.0F) * 0.5F;
      var2.an = this.an * 0.5F + 0.3F;
      var2.ao = this.ao * 0.5F + 1.2F;
      return var2;
   }

   public BiomeGenSavanna(int var1) {
      super(var1);
      this.au.add(new BiomeGenBase$SpawnListEntry(EntityHorse.class, 1, 2, 6));
      this.as.treesPerChunk = 1;
      this.as.flowersPerChunk = 4;
      this.as.grassPerChunk = 20;
   }

   @Override
   public WorldGenAbstractTree genBigTreeChance(Random var1) {
      return (WorldGenAbstractTree)(var1.nextInt(5) > 0 ? field_150627_aC : this.aA);
   }
}
