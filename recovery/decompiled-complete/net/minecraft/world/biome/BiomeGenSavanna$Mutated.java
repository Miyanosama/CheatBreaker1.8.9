package net.minecraft.world.biome;

import io.netty.util.concurrent.DefaultThreadFactory$DefaultRunnableDecorator;
import java.util.Random;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDirt$DirtType;
import net.minecraft.block.BlockStoneSlabNew$EnumType;
import net.minecraft.command.server.CommandAchievement;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$RightTurn;

public class BiomeGenSavanna$Mutated extends BiomeGenMutated {
   public DefaultThreadFactory$DefaultRunnableDecorator field_0000;
   public BlockStoneSlabNew$EnumType field_0003;
   public CommandAchievement field_0002;
   public StructureStrongholdPieces$RightTurn field_0001;

   @Override
   public void genTerrainBlocks(World var1, Random var2, ChunkPrimer var3, int var4, int var5, double var6) {
      this.ak = Blocks.grass.getDefaultState();
      this.al = Blocks.dirt.getDefaultState();
      if (var6 > 1.75) {
         this.ak = Blocks.stone.getDefaultState();
         this.al = Blocks.stone.getDefaultState();
      } else if (var6 > -0.5) {
         this.ak = Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt$DirtType.COARSE_DIRT);
      }

      this.b(var1, var2, var3, var4, var5, var6);
   }

   public BiomeGenSavanna$Mutated(int var1, BiomeGenBase var2) {
      super(var1, var2);
      this.as.treesPerChunk = 2;
      this.as.flowersPerChunk = 2;
      this.as.grassPerChunk = 5;
   }

   @Override
   public void decorate(World var1, Random var2, BlockPos var3) {
      this.as.decorate(var1, var2, this, var3);
   }
}
