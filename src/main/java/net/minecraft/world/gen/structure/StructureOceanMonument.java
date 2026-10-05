package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class StructureOceanMonument extends MapGenStructure {
   public static List<BiomeGenBase> field_175802_d = Arrays.asList(
      BiomeGenBase.ocean, BiomeGenBase.deepOcean, BiomeGenBase.river, BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver
   );
   public int field_175800_f = 32;
   public int field_175801_g = 5;
   public static List<BiomeGenBase.SpawnListEntry> field_175803_h = Lists.newArrayList();

   @Override
   public String getStructureName() {
      return "Monument";
   }

   @Override
   public StructureStart getStructureStart(int var1, int var2) {
      return new StructureOceanMonument.StartMonument(this.c, this.b, var1, var2);
   }

   public StructureOceanMonument() {
   }

   @Override
   public boolean canSpawnStructureAtCoords(int var1, int var2) {
      int var3 = var1;
      int var4 = var2;
      if (var1 < 0) {
         var1 -= this.field_175800_f - 1;
      }

      if (var2 < 0) {
         var2 -= this.field_175800_f - 1;
      }

      int var5 = var1 / this.field_175800_f;
      int var6 = var2 / this.field_175800_f;
      Random var7 = this.c.setRandomSeed(var5, var6, 10387313);
      var5 *= this.field_175800_f;
      var6 *= this.field_175800_f;
      var5 += (var7.nextInt(this.field_175800_f - this.field_175801_g) + var7.nextInt(this.field_175800_f - this.field_175801_g)) / 2;
      var6 += (var7.nextInt(this.field_175800_f - this.field_175801_g) + var7.nextInt(this.field_175800_f - this.field_175801_g)) / 2;
      if (var3 == var5 && var4 == var6) {
         if (this.c.getWorldChunkManager().getBiomeGenerator(new BlockPos(var3 * 16 + 8, 64, var4 * 16 + 8), (BiomeGenBase)null) != BiomeGenBase.deepOcean) {
            return false;
         }

         boolean var8 = this.c.getWorldChunkManager().areBiomesViable(var3 * 16 + 8, var4 * 16 + 8, 29, field_175802_d);
         if (var8) {
            return true;
         }
      }

      return false;
   }

   public List<BiomeGenBase.SpawnListEntry> getScatteredFeatureSpawnList() {
      return field_175803_h;
   }

   public StructureOceanMonument(Map<String, String> var1) {
      this();

      for (Entry var3 : var1.entrySet()) {
         if (((String)var3.getKey()).equals("spacing")) {
            this.field_175800_f = MathHelper.parseIntWithDefaultAndMax((String)var3.getValue(), this.field_175800_f, 1);
         } else if (((String)var3.getKey()).equals("separation")) {
            this.field_175801_g = MathHelper.parseIntWithDefaultAndMax((String)var3.getValue(), this.field_175801_g, 1);
         }
      }
   }

   static {
      field_175803_h.add(new BiomeGenBase.SpawnListEntry(EntityGuardian.class, 1, 2, 4));
   }

   public static class StartMonument extends StructureStart {
      public Set<ChunkCoordIntPair> field_175791_c = Sets.newHashSet();
      public boolean field_175790_d;

      @Override
      public boolean func_175788_a(ChunkCoordIntPair var1) {
         return this.field_175791_c.contains(var1) ? false : super.func_175788_a(var1);
      }

      @Override
      public void func_175787_b(ChunkCoordIntPair var1) {
         super.func_175787_b(var1);
         this.field_175791_c.add(var1);
      }

      public StartMonument() {
      }

      @Override
      public void generateStructure(World var1, Random var2, StructureBoundingBox var3) {
         if (!this.field_175790_d) {
            this.a.clear();
            this.func_175789_b(var1, var2, this.getChunkPosX(), this.getChunkPosZ());
         }

         super.generateStructure(var1, var2, var3);
      }

      public void func_175789_b(World var1, Random var2, int var3, int var4) {
         var2.setSeed(var1.J());
         long var5 = var2.nextLong();
         long var7 = var2.nextLong();
         long var9 = var3 * var5;
         long var11 = var4 * var7;
         var2.setSeed(var9 ^ var11 ^ var1.J());
         int var13 = var3 * 16 + 8 - 29;
         int var14 = var4 * 16 + 8 - 29;
         EnumFacing var15 = EnumFacing.Plane.HORIZONTAL.random(var2);
         this.a.add(new StructureOceanMonumentPieces.MonumentBuilding(var2, var13, var14, var15));
         this.c();
         this.field_175790_d = true;
      }

      @Override
      public void writeToNBT(NBTTagCompound var1) {
         super.writeToNBT(var1);
         NBTTagList var2 = new NBTTagList();

         for (ChunkCoordIntPair var4 : this.field_175791_c) {
            NBTTagCompound var5 = new NBTTagCompound();
            var5.setInteger("X", var4.chunkXPos);
            var5.setInteger("Z", var4.chunkZPos);
            var2.appendTag(var5);
         }

         var1.setTag("Processed", var2);
      }

      public StartMonument(World var1, Random var2, int var3, int var4) {
         super(var3, var4);
         this.func_175789_b(var1, var2, var3, var4);
      }

      @Override
      public void readFromNBT(NBTTagCompound var1) {
         super.readFromNBT(var1);
         if (var1.hasKey("Processed", 9)) {
            NBTTagList var2 = var1.getTagList("Processed", 10);

            for (int var3 = 0; var3 < var2.tagCount(); var3++) {
               NBTTagCompound var4 = var2.getCompoundTagAt(var3);
               this.field_175791_c.add(new ChunkCoordIntPair(var4.getInteger("X"), var4.getInteger("Z")));
            }
         }
      }
   }
}
