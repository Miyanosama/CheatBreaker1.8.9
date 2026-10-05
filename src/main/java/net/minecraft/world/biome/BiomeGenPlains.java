package net.minecraft.world.biome;

import java.util.Random;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class BiomeGenPlains extends BiomeGenBase {
   public boolean field_150628_aC;

   @Override
   public void decorate(World var1, Random var2, BlockPos var3) {
      double var4 = af.func_151601_a((var3.getX() + 8) / 200.0, (var3.getZ() + 8) / 200.0);
      if (var4 < -0.8) {
         this.as.flowersPerChunk = 15;
         this.as.grassPerChunk = 5;
      } else {
         this.as.flowersPerChunk = 4;
         this.as.grassPerChunk = 10;
         ag.setPlantType(BlockDoublePlant.EnumPlantType.GRASS);

         for (int var6 = 0; var6 < 7; var6++) {
            int var7 = var2.nextInt(16) + 8;
            int var8 = var2.nextInt(16) + 8;
            int var9 = var2.nextInt(var1.getHeight(var3.add(var7, 0, var8)).getY() + 32);
            ag.generate(var1, var2, var3.add(var7, var9, var8));
         }
      }

      if (this.field_150628_aC) {
         ag.setPlantType(BlockDoublePlant.EnumPlantType.SUNFLOWER);

         for (int var10 = 0; var10 < 10; var10++) {
            int var11 = var2.nextInt(16) + 8;
            int var12 = var2.nextInt(16) + 8;
            int var13 = var2.nextInt(var1.getHeight(var3.add(var11, 0, var12)).getY() + 32);
            ag.generate(var1, var2, var3.add(var11, var13, var12));
         }
      }

      super.decorate(var1, var2, var3);
   }

   public BiomeGenPlains(int var1) {
      super(var1);
      this.a(0.8F, 0.4F);
      this.a(e);
      this.au.add(new BiomeGenBase.SpawnListEntry(EntityHorse.class, 5, 2, 6));
      this.as.treesPerChunk = -999;
      this.as.flowersPerChunk = 4;
      this.as.grassPerChunk = 10;
   }

   @Override
   public BlockFlower.EnumFlowerType pickRandomFlower(Random var1, BlockPos var2) {
      double var3 = af.func_151601_a(var2.getX() / 200.0, var2.getZ() / 200.0);
      if (var3 < -0.8) {
         int var6 = var1.nextInt(4);
         switch (var6) {
            case 0:
               return BlockFlower.EnumFlowerType.ORANGE_TULIP;
            case 1:
               return BlockFlower.EnumFlowerType.RED_TULIP;
            case 2:
               return BlockFlower.EnumFlowerType.PINK_TULIP;
            case 3:
            default:
               return BlockFlower.EnumFlowerType.WHITE_TULIP;
         }
      } else if (var1.nextInt(3) > 0) {
         int var5 = var1.nextInt(3);
         return var5 == 0 ? BlockFlower.EnumFlowerType.POPPY : (var5 == 1 ? BlockFlower.EnumFlowerType.HOUSTONIA : BlockFlower.EnumFlowerType.OXEYE_DAISY);
      } else {
         return BlockFlower.EnumFlowerType.DANDELION;
      }
   }

   @Override
   public BiomeGenBase createMutatedBiome(int var1) {
      BiomeGenPlains var2 = new BiomeGenPlains(var1);
      var2.a("Sunflower Plains");
      var2.field_150628_aC = true;
      var2.setColor(9286496);
      var2.aj = 14273354;
      return var2;
   }
}
