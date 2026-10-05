package net.minecraft.world.gen.structure;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class MapGenVillage extends MapGenStructure {
   public static List<BiomeGenBase> villageSpawnBiomes = Arrays.asList(BiomeGenBase.plains, BiomeGenBase.desert, BiomeGenBase.savanna);
   public int terrainType;
   public int field_82665_g = 32;
   public int field_82666_h = 8;

   public MapGenVillage() {
   }

   @Override
   public StructureStart getStructureStart(int var1, int var2) {
      return new MapGenVillage.Start(this.c, this.b, var1, var2, this.terrainType);
   }

   public MapGenVillage(Map<String, String> var1) {
      this();

      for (Entry var3 : var1.entrySet()) {
         if (((String)var3.getKey()).equals("size")) {
            this.terrainType = MathHelper.parseIntWithDefaultAndMax((String)var3.getValue(), this.terrainType, 0);
         } else if (((String)var3.getKey()).equals("distance")) {
            this.field_82665_g = MathHelper.parseIntWithDefaultAndMax((String)var3.getValue(), this.field_82665_g, this.field_82666_h + 1);
         }
      }
   }

   @Override
   public boolean canSpawnStructureAtCoords(int var1, int var2) {
      int var3 = var1;
      int var4 = var2;
      if (var1 < 0) {
         var1 -= this.field_82665_g - 1;
      }

      if (var2 < 0) {
         var2 -= this.field_82665_g - 1;
      }

      int var5 = var1 / this.field_82665_g;
      int var6 = var2 / this.field_82665_g;
      Random var7 = this.c.setRandomSeed(var5, var6, 10387312);
      var5 *= this.field_82665_g;
      var6 *= this.field_82665_g;
      var5 += var7.nextInt(this.field_82665_g - this.field_82666_h);
      var6 += var7.nextInt(this.field_82665_g - this.field_82666_h);
      if (var3 == var5 && var4 == var6) {
         boolean var8 = this.c.getWorldChunkManager().areBiomesViable(var3 * 16 + 8, var4 * 16 + 8, 0, villageSpawnBiomes);
         if (var8) {
            return true;
         }
      }

      return false;
   }

   @Override
   public String getStructureName() {
      return "Village";
   }

   public static class Start extends StructureStart {
      public boolean hasMoreThanTwoComponents;

      @Override
      public void writeToNBT(NBTTagCompound var1) {
         super.writeToNBT(var1);
         var1.setBoolean("Valid", this.hasMoreThanTwoComponents);
      }

      public Start() {
      }

      @Override
      public void readFromNBT(NBTTagCompound var1) {
         super.readFromNBT(var1);
         this.hasMoreThanTwoComponents = var1.getBoolean("Valid");
      }

      public Start(World var1, Random var2, int var3, int var4, int var5) {
         super(var3, var4);
         List var6 = StructureVillagePieces.getStructureVillageWeightedPieceList(var2, var5);
         StructureVillagePieces.Start var7 = new StructureVillagePieces.Start(
            var1.getWorldChunkManager(), 0, var2, (var3 << 4) + 2, (var4 << 4) + 2, var6, var5
         );
         this.a.add(var7);
         var7.buildComponent(var7, this.a, var2);
         List var8 = var7.field_74930_j;
         List var9 = var7.field_74932_i;

         while (!var8.isEmpty() || !var9.isEmpty()) {
            if (var8.isEmpty()) {
               int var10 = var2.nextInt(var9.size());
               StructureComponent var11 = (StructureComponent)var9.remove(var10);
               var11.buildComponent(var7, this.a, var2);
            } else {
               int var13 = var2.nextInt(var8.size());
               StructureComponent var15 = (StructureComponent)var8.remove(var13);
               var15.buildComponent(var7, this.a, var2);
            }
         }

         this.c();
         int var14 = 0;

         for (StructureComponent var12 : this.a) {
            if (!(var12 instanceof StructureVillagePieces.Road)) {
               var14++;
            }
         }

         this.hasMoreThanTwoComponents = var14 > 2;
      }

      @Override
      public boolean isSizeableStructure() {
         return this.hasMoreThanTwoComponents;
      }
   }
}
