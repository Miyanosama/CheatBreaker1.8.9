package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.BlockDoublePlant$EnumPlantType;
import net.minecraft.block.BlockFlower$EnumFlowerType;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import net.minecraft.world.gen.feature.WorldGenForest;
import recovered.unidentified.UnidentifiedClass4511;

public class BiomeGenForest extends BiomeGenBase {
   public int field_150632_aF;
   public static WorldGenCanopyTree field_150631_aE = new WorldGenCanopyTree(false);
   public static WorldGenForest field_150630_aD = new WorldGenForest(false, false);
   public static WorldGenForest field_150629_aC = new WorldGenForest(false, true);

   @Override
   public WorldGenAbstractTree genBigTreeChance(Random var1) {
      return (WorldGenAbstractTree)(this.field_150632_aF == 3 && var1.nextInt(3) > 0
         ? field_150631_aE
         : (this.field_150632_aF != 2 && var1.nextInt(5) != 0 ? this.aA : field_150630_aD));
   }

   @Override
   public BlockFlower$EnumFlowerType pickRandomFlower(Random var1, BlockPos var2) {
      if (this.field_150632_aF == 1) {
         double var3 = MathHelper.clamp_double((1.0 + af.func_151601_a(var2.getX() / 48.0, var2.getZ() / 48.0)) / 2.0, 0.0, 0.9999);
         BlockFlower$EnumFlowerType var5 = BlockFlower$EnumFlowerType.values()[(int)(var3 * BlockFlower$EnumFlowerType.values().length)];
         return var5 == BlockFlower$EnumFlowerType.BLUE_ORCHID ? BlockFlower$EnumFlowerType.POPPY : var5;
      } else {
         return super.pickRandomFlower(var1, var2);
      }
   }

   @Override
   public int getGrassColorAtPos(BlockPos var1) {
      int var2 = super.getGrassColorAtPos(var1);
      return this.field_150632_aF == 3 ? (var2 & 16711422) + 2634762 >> 1 : var2;
   }

   @Override
   public BiomeGenBase createMutatedBiome(int var1) {
      if (this.az == BiomeGenBase.forest.az) {
         BiomeGenForest var2 = new BiomeGenForest(var1, 1);
         var2.a(new BiomeGenBase$Height(this.an, this.ao + 0.2F));
         var2.a("Flower Forest");
         var2.a(6976549, true);
         var2.a(8233509);
         return var2;
      } else {
         return (BiomeGenBase)(this.az != BiomeGenBase.birchForest.az && this.az != BiomeGenBase.field_0044.az
            ? new BiomeGenForest$1(this, var1, this)
            : new UnidentifiedClass4511(this, var1, this));
      }
   }

   @Override
   public void decorate(World var1, Random var2, BlockPos var3) {
      if (this.field_150632_aF == 3) {
         for (int var4 = 0; var4 < 4; var4++) {
            for (int var5 = 0; var5 < 4; var5++) {
               int var6 = var4 * 4 + 1 + 8 + var2.nextInt(3);
               int var7 = var5 * 4 + 1 + 8 + var2.nextInt(3);
               BlockPos var8 = var1.getHeight(var3.add(var6, 0, var7));
               if (var2.nextInt(20) == 0) {
                  WorldGenBigMushroom var9 = new WorldGenBigMushroom();
                  var9.generate(var1, var2, var8);
               } else {
                  WorldGenAbstractTree var16 = this.genBigTreeChance(var2);
                  var16.func_175904_e();
                  if (var16.generate(var1, var2, var8)) {
                     var16.func_180711_a(var1, var2, var8);
                  }
               }
            }
         }
      }

      int var11 = var2.nextInt(5) - 3;
      if (this.field_150632_aF == 1) {
         var11 += 2;
      }

      for (int var12 = 0; var12 < var11; var12++) {
         int var13 = var2.nextInt(3);
         if (var13 == 0) {
            ag.setPlantType(BlockDoublePlant$EnumPlantType.SYRINGA);
         } else if (var13 == 1) {
            ag.setPlantType(BlockDoublePlant$EnumPlantType.ROSE);
         } else if (var13 == 2) {
            ag.setPlantType(BlockDoublePlant$EnumPlantType.PAEONIA);
         }

         for (int var14 = 0; var14 < 5; var14++) {
            int var15 = var2.nextInt(16) + 8;
            int var17 = var2.nextInt(16) + 8;
            int var10 = var2.nextInt(var1.getHeight(var3.add(var15, 0, var17)).getY() + 32);
            if (ag.generate(var1, var2, new BlockPos(var3.getX() + var15, var10, var3.getZ() + var17))) {
               break;
            }
         }
      }

      super.decorate(var1, var2, var3);
   }

   public BiomeGenForest(int var1, int var2) {
      super(var1);
      this.field_150632_aF = var2;
      this.as.treesPerChunk = 10;
      this.as.grassPerChunk = 2;
      if (this.field_150632_aF == 1) {
         this.as.treesPerChunk = 6;
         this.as.flowersPerChunk = 100;
         this.as.grassPerChunk = 1;
      }

      this.a(5159473);
      this.a(0.7F, 0.8F);
      if (this.field_150632_aF == 2) {
         this.aj = 353825;
         this.ai = 3175492;
         this.a(0.6F, 0.6F);
      }

      if (this.field_150632_aF == 0) {
         this.au.add(new BiomeGenBase$SpawnListEntry(EntityWolf.class, 5, 4, 4));
      }

      if (this.field_150632_aF == 3) {
         this.as.treesPerChunk = -999;
      }
   }

   @Override
   public BiomeGenBase a(int var1, boolean var2) {
      if (this.field_150632_aF == 2) {
         this.aj = 353825;
         this.ai = var1;
         if (var2) {
            this.aj = (this.aj & 16711422) >> 1;
         }

         return this;
      } else {
         return super.a(var1, var2);
      }
   }
}
