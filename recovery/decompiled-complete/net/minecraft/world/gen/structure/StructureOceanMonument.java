package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.BiomeGenBase$SpawnListEntry;
import net.optifine.ConnectedTexturesCompact$1;
import recovered.unidentified.UnidentifiedClass0520;

public class StructureOceanMonument extends MapGenStructure {
   public static List<BiomeGenBase> field_175802_d = Arrays.asList(
      BiomeGenBase.ocean, BiomeGenBase.deepOcean, BiomeGenBase.river, BiomeGenBase.frozenOcean, BiomeGenBase.frozenRiver
   );
   public ConnectedTexturesCompact$1 field_0004;
   public int field_175800_f = 32;
   public int field_175801_g = 5;
   public UnidentifiedClass0520 field_0000;
   public GuiMultiplayer field_0002;
   public static List<BiomeGenBase$SpawnListEntry> field_175803_h = Lists.newArrayList();

   @Override
   public String getStructureName() {
      return "Monument";
   }

   @Override
   public StructureStart getStructureStart(int var1, int var2) {
      return new StructureOceanMonument$StartMonument(this.c, this.b, var1, var2);
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

   public List<BiomeGenBase$SpawnListEntry> getScatteredFeatureSpawnList() {
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
      field_175803_h.add(new BiomeGenBase$SpawnListEntry(EntityGuardian.class, 1, 2, 4));
   }
}
