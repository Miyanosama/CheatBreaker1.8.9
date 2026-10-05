package net.minecraft.world.biome;

import com.cheatbreaker.client.module.type.TextureOptionsModule;
import io.netty.util.internal.logging.AbstractInternalLogger$1;
import java.util.Random;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDirt$DirtType;
import net.minecraft.block.BlockDoublePlant$EnumPlantType;
import net.minecraft.block.BlockTallGrass$EnumType;
import net.minecraft.client.gui.stream.GuiStreamUnavailable;
import net.minecraft.client.renderer.entity.RenderWitch;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenMegaPineTree;
import net.minecraft.world.gen.feature.WorldGenTaiga1;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.optifine.player.PlayerItemRenderer;

public class BiomeGenTaiga extends BiomeGenBase {
   public static WorldGenMegaPineTree field_150642_aF = new WorldGenMegaPineTree(false, true);
   public static WorldGenTaiga2 field_150640_aD = new WorldGenTaiga2(false);
   public RenderWitch field_0007;
   public static WorldGenBlockBlob field_150643_aG = new WorldGenBlockBlob(Blocks.mossy_cobblestone, 0);
   public TextureOptionsModule field_0009;
   public static WorldGenMegaPineTree field_150641_aE = new WorldGenMegaPineTree(false, false);
   public int field_150644_aH;
   public GuiStreamUnavailable field_0000;
   public AbstractInternalLogger$1 field_0005;
   public static WorldGenTaiga1 field_150639_aC = new WorldGenTaiga1();
   public PlayerItemRenderer field_0002;

   @Override
   public void decorate(World var1, Random var2, BlockPos var3) {
      if (this.field_150644_aH == 1 || this.field_150644_aH == 2) {
         int var4 = var2.nextInt(3);

         for (int var5 = 0; var5 < var4; var5++) {
            int var6 = var2.nextInt(16) + 8;
            int var7 = var2.nextInt(16) + 8;
            BlockPos var8 = var1.getHeight(var3.add(var6, 0, var7));
            field_150643_aG.generate(var1, var2, var8);
         }
      }

      ag.setPlantType(BlockDoublePlant$EnumPlantType.FERN);

      for (int var9 = 0; var9 < 7; var9++) {
         int var10 = var2.nextInt(16) + 8;
         int var11 = var2.nextInt(16) + 8;
         int var12 = var2.nextInt(var1.getHeight(var3.add(var10, 0, var11)).getY() + 32);
         ag.generate(var1, var2, var3.add(var10, var12, var11));
      }

      super.decorate(var1, var2, var3);
   }

   @Override
   public WorldGenAbstractTree genBigTreeChance(Random var1) {
      return (WorldGenAbstractTree)((this.field_150644_aH == 1 || this.field_150644_aH == 2) && var1.nextInt(3) == 0
         ? (this.field_150644_aH != 2 && var1.nextInt(13) != 0 ? field_150641_aE : field_150642_aF)
         : (var1.nextInt(3) == 0 ? field_150639_aC : field_150640_aD));
   }

   @Override
   public BiomeGenBase createMutatedBiome(int var1) {
      return this.az == BiomeGenBase.megaTaiga.az
         ? new BiomeGenTaiga(var1, 2).a(5858897, true).a("Mega Spruce Taiga").a(5159473).a(0.25F, 0.8F).a(new BiomeGenBase$Height(this.an, this.ao))
         : super.createMutatedBiome(var1);
   }

   @Override
   public void genTerrainBlocks(World var1, Random var2, ChunkPrimer var3, int var4, int var5, double var6) {
      if (this.field_150644_aH == 1 || this.field_150644_aH == 2) {
         this.ak = Blocks.grass.getDefaultState();
         this.al = Blocks.dirt.getDefaultState();
         if (var6 > 1.75) {
            this.ak = Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt$DirtType.COARSE_DIRT);
         } else if (var6 > -0.95) {
            this.ak = Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt$DirtType.PODZOL);
         }
      }

      this.b(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public WorldGenerator getRandomWorldGenForGrass(Random var1) {
      return var1.nextInt(5) > 0 ? new WorldGenTallGrass(BlockTallGrass$EnumType.FERN) : new WorldGenTallGrass(BlockTallGrass$EnumType.GRASS);
   }

   public BiomeGenTaiga(int var1, int var2) {
      super(var1);
      this.field_150644_aH = var2;
      this.au.add(new BiomeGenBase$SpawnListEntry(EntityWolf.class, 8, 4, 4));
      this.as.treesPerChunk = 10;
      if (var2 != 1 && var2 != 2) {
         this.as.grassPerChunk = 1;
         this.as.mushroomsPerChunk = 1;
      } else {
         this.as.grassPerChunk = 7;
         this.as.deadBushPerChunk = 1;
         this.as.mushroomsPerChunk = 3;
      }
   }
}
