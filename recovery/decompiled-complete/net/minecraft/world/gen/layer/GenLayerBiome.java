package net.minecraft.world.gen.layer;

import com.cheatbreaker.client.module.type.cooldowns.CooldownsModule;
import net.minecraft.block.BlockHugeMushroom;
import net.minecraft.command.server.CommandBlockLogic;
import net.minecraft.item.Item$4;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.ChunkProviderSettings$Factory;

public class GenLayerBiome extends GenLayer {
   public BiomeGenBase[] field_151622_e;
   public CommandBlockLogic field_0007;
   public ChunkProviderSettings field_175973_g;
   public Item$4 field_0002;
   public BiomeGenBase[] field_151620_f;
   public BlockHugeMushroom field_0000;
   public CooldownsModule field_0004;
   public BiomeGenBase[] field_151623_c = new BiomeGenBase[]{
      BiomeGenBase.desert, BiomeGenBase.desert, BiomeGenBase.desert, BiomeGenBase.savanna, BiomeGenBase.savanna, BiomeGenBase.plains
   };
   public BiomeGenBase[] field_151621_d = new BiomeGenBase[]{
      BiomeGenBase.forest, BiomeGenBase.roofedForest, BiomeGenBase.extremeHills, BiomeGenBase.plains, BiomeGenBase.birchForest, BiomeGenBase.swampland
   };

   public GenLayerBiome(long var1, GenLayer var3, WorldType var4, String var5) {
      super(var1);
      this.field_151622_e = new BiomeGenBase[]{BiomeGenBase.forest, BiomeGenBase.extremeHills, BiomeGenBase.taiga, BiomeGenBase.plains};
      this.field_151620_f = new BiomeGenBase[]{BiomeGenBase.icePlains, BiomeGenBase.icePlains, BiomeGenBase.icePlains, BiomeGenBase.coldTaiga};
      this.a = var3;
      if (var4 == WorldType.DEFAULT_1_1) {
         this.field_151623_c = new BiomeGenBase[]{
            BiomeGenBase.desert, BiomeGenBase.forest, BiomeGenBase.extremeHills, BiomeGenBase.swampland, BiomeGenBase.plains, BiomeGenBase.taiga
         };
         this.field_175973_g = null;
      } else if (var4 == WorldType.CUSTOMIZED) {
         this.field_175973_g = ChunkProviderSettings$Factory.jsonToFactory(var5).func_177864_b();
      } else {
         this.field_175973_g = null;
      }
   }

   @Override
   public int[] getInts(int var1, int var2, int var3, int var4) {
      int[] var5 = this.a.getInts(var1, var2, var3, var4);
      int[] var6 = IntCache.getIntCache(var3 * var4);

      for (int var7 = 0; var7 < var4; var7++) {
         for (int var8 = 0; var8 < var3; var8++) {
            this.a(var8 + var1, var7 + var2);
            int var9 = var5[var8 + var7 * var3];
            int var10 = (var9 & 3840) >> 8;
            var9 &= -3841;
            if (this.field_175973_g != null && this.field_175973_g.fixedBiome >= 0) {
               var6[var8 + var7 * var3] = this.field_175973_g.fixedBiome;
            } else if (isBiomeOceanic(var9)) {
               var6[var8 + var7 * var3] = var9;
            } else if (var9 == BiomeGenBase.mushroomIsland.az) {
               var6[var8 + var7 * var3] = var9;
            } else if (var9 == 1) {
               if (var10 > 0) {
                  if (this.a(3) == 0) {
                     var6[var8 + var7 * var3] = BiomeGenBase.mesaPlateau.az;
                  } else {
                     var6[var8 + var7 * var3] = BiomeGenBase.mesaPlateau_F.az;
                  }
               } else {
                  var6[var8 + var7 * var3] = this.field_151623_c[this.a(this.field_151623_c.length)].az;
               }
            } else if (var9 == 2) {
               if (var10 > 0) {
                  var6[var8 + var7 * var3] = BiomeGenBase.jungle.az;
               } else {
                  var6[var8 + var7 * var3] = this.field_151621_d[this.a(this.field_151621_d.length)].az;
               }
            } else if (var9 == 3) {
               if (var10 > 0) {
                  var6[var8 + var7 * var3] = BiomeGenBase.megaTaiga.az;
               } else {
                  var6[var8 + var7 * var3] = this.field_151622_e[this.a(this.field_151622_e.length)].az;
               }
            } else if (var9 == 4) {
               var6[var8 + var7 * var3] = this.field_151620_f[this.a(this.field_151620_f.length)].az;
            } else {
               var6[var8 + var7 * var3] = BiomeGenBase.mushroomIsland.az;
            }
         }
      }

      return var6;
   }
}
