package net.minecraft.world.gen.layer;

import javax.vecmath.Color3b;
import net.minecraft.crash.CrashReport;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.ReportedException;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.ChunkProviderSettings;
import net.minecraft.world.gen.ChunkProviderSettings$Factory;
import recovered.unidentified.UnidentifiedClass0921;
import recovered.unidentified.UnidentifiedClass1353;
import recovered.unidentified.UnidentifiedClass1437;
import recovered.unidentified.UnidentifiedClass1810;
import recovered.unidentified.UnidentifiedClass5091;
import recovered.unidentified.UnidentifiedClass5123;

public abstract class GenLayer {
   public long field_0002;
   public Color3b field_0004;
   public long field_0001;
   public GenLayer a;
   public long field_0000;

   public void a(long var1, long var3) {
      this.field_0000 = this.field_0002;
      this.field_0000 = this.field_0000 * (this.field_0000 * (6364136223850987325L & -48423601051402257L) + (-7629242904443649713L & 1442695040888972111L));
      this.field_0000 += var1;
      this.field_0000 = this.field_0000 * (this.field_0000 * (-9580595414466771L & 6364136223885098943L) + (1442695040890013023L & 1442695040897352559L));
      this.field_0000 += var3;
      this.field_0000 = this.field_0000 * (this.field_0000 * (-2570441593545457859L & 8934577817374785517L) + (1442695040888973135L & 1442695040888969679L));
      this.field_0000 += var1;
      this.field_0000 = this.field_0000 * (this.field_0000 * (6364136224119422781L & -2386920447733694545L) + (2172986815767618943L & -730291774888111665L));
      this.field_0000 += var3;
   }

   public static boolean isBiomeOceanic(int var0) {
      return var0 == BiomeGenBase.ocean.az || var0 == BiomeGenBase.deepOcean.az || var0 == BiomeGenBase.frozenOcean.az;
   }

   public static boolean biomesEqualOrMesaPlateau(int var0, int var1) {
      if (var0 == var1) {
         return true;
      } else if (var0 != BiomeGenBase.mesaPlateau_F.az && var0 != BiomeGenBase.mesaPlateau.az) {
         BiomeGenBase var2 = BiomeGenBase.getBiome(var0);
         BiomeGenBase var3 = BiomeGenBase.getBiome(var1);

         try {
            return var2 != null && var3 != null ? var2.isEqualTo(var3) : false;
         } catch (Throwable var7) {
            CrashReport var5 = CrashReport.makeCrashReport(var7, "Comparing biomes");
            CrashReportCategory var6 = var5.makeCategory("Biomes being compared");
            var6.addCrashSection("Biome A ID", var0);
            var6.addCrashSection("Biome B ID", var1);
            var6.addCrashSectionCallable("Biome A", new UnidentifiedClass1810(var2));
            var6.addCrashSectionCallable("Biome B", new UnidentifiedClass0921(var3));
            throw new ReportedException(var5);
         }
      } else {
         return var1 == BiomeGenBase.mesaPlateau_F.az || var1 == BiomeGenBase.mesaPlateau.az;
      }
   }

   public abstract int[] getInts(int var1, int var2, int var3, int var4);

   public GenLayer(long var1) {
      this.field_0001 = var1;
      this.field_0001 = this.field_0001 * (this.field_0001 * (8897413222430637869L & 6364136223846793007L) + (1442699988825525583L & -4947946536497L));
      this.field_0001 += var1;
      this.field_0001 = this.field_0001 * (this.field_0001 * (9031965399231856429L & -2667829175436083409L) + (8619185445151412591L & 1442695040888963551L));
      this.field_0001 += var1;
      this.field_0001 = this.field_0001 * (this.field_0001 * (6364136223846793005L & 6807179291608317757L) + (6819997398311862639L & 1442695040888975183L));
      this.field_0001 += var1;
   }

   public static GenLayer[] initializeAllBiomeGenerators(long var0, WorldType var2, String var3) {
      GenLayerIsland var4 = new GenLayerIsland(83935269L & 854392857L);
      GenLayerFuzzyZoom var33 = new GenLayerFuzzyZoom(8853376785728956376L & -8853376787736684588L, var4);
      UnidentifiedClass5123 var5 = new UnidentifiedClass5123(7379267121378918417L & 17826407L, var33);
      GenLayerZoom var6 = new GenLayerZoom(6293465L & 1719666643L, var5);
      UnidentifiedClass5123 var7 = new UnidentifiedClass5123(-2187340049538741806L & 268468262L, var6);
      var7 = new UnidentifiedClass5123(-2283132109039196106L & 2283132107863703666L, var7);
      var7 = new UnidentifiedClass5123(-4544816023320123066L & 136414310L, var7);
      GenLayerRemoveTooMuchOcean var8 = new GenLayerRemoveTooMuchOcean(1669448258L & 71575730L, var7);
      GenLayerAddSnow var9 = new GenLayerAddSnow(1146458918L & 536871962L, var8);
      UnidentifiedClass5123 var10 = new UnidentifiedClass5123(-6570192465833610877L & 6570192465270161515L, var9);
      GenLayerEdge var11 = new GenLayerEdge(-2111102488641661870L & 491266306L, var10, GenLayerEdge$Mode.COOL_WARM);
      var11 = new GenLayerEdge(-2893509501313277338L & 26492955L, var11, GenLayerEdge$Mode.HEAT_ICE);
      var11 = new GenLayerEdge(563095555L & 1080857443L, var11, GenLayerEdge$Mode.SPECIAL);
      GenLayerZoom var12 = new GenLayerZoom(9034523390079272918L & -9034523392014766126L, var11);
      var12 = new GenLayerZoom(829407L & 1111496659L, var12);
      UnidentifiedClass5123 var13 = new UnidentifiedClass5123(46332163901145492L & -46332165856360348L, var12);
      GenLayerAddMushroomIsland var14 = new GenLayerAddMushroomIsland(2762356667948501015L & 1279816325L, var13);
      GenLayerDeepOcean var15 = new GenLayerDeepOcean(542253100L & -6168647049286500220L, var14);
      GenLayer var16 = GenLayerZoom.magnify(-4197870673298707479L & 1076954088L, var15, 0);
      ChunkProviderSettings var17 = null;
      int var18 = 4;
      int var19 = var18;
      if (var2 == WorldType.CUSTOMIZED && var3.length() > 0) {
         var17 = ChunkProviderSettings$Factory.jsonToFactory(var3).func_177864_b();
         var18 = var17.biomeSize;
         var19 = var17.riverSize;
      }

      if (var2 == WorldType.LARGE_BIOMES) {
         var18 = 6;
      }

      GenLayer var20 = GenLayerZoom.magnify(4052798676876142586L & -4052798678864966680L, var16, 0);
      GenLayerRiverInit var21 = new GenLayerRiverInit(-6575629083084520987L & 1079002742L, var20);
      GenLayerBiome var22 = new GenLayerBiome(1074352586L & 283281629L, var16, var2, var3);
      GenLayer var23 = GenLayerZoom.magnify(7273307707514291176L & 1612531688L, var22, 2);
      GenLayerBiomeEdge var24 = new GenLayerBiomeEdge(-1484143455142468614L & 1484143453943368685L, var23);
      GenLayer var25 = GenLayerZoom.magnify(-2417947112931752979L & 5381112L, var21, 2);
      GenLayerHills var26 = new GenLayerHills(-1153776663951555607L & 1153776662862932984L, var24, var25);
      GenLayer var27 = GenLayerZoom.magnify(285436905L & 134218728L, var21, 2);
      var27 = GenLayerZoom.magnify(58729448L & 1640697655100249069L, var27, var19);
      UnidentifiedClass1353 var28 = new UnidentifiedClass1353(1075843089L & 671768577L, var27);
      GenLayerSmooth var29 = new GenLayerSmooth(302515176L & 1302463471L, var28);
      var26 = new GenLayerRareBiome(-8825941293600107543L & 603997181L, var26);

      for (int var30 = 0; var30 < var18; var30++) {
         var26 = new GenLayerZoom(1000 + var30, var26);
         if (var30 == 0) {
            var26 = new UnidentifiedClass5123(184765443L & 269488131L, var26);
         }

         if (var30 == 1 || var18 == 1) {
            var26 = new UnidentifiedClass5091(1104032761L & 169953256L, var26);
         }
      }

      GenLayerSmooth var42 = new GenLayerSmooth(-7589793640424142872L & 7589793638618582008L, var26);
      GenLayerRiverMix var31 = new GenLayerRiverMix(1074792564L & -3862234224551317394L, var42, var29);
      UnidentifiedClass1437 var32 = new UnidentifiedClass1437(17991902L & 2433035L, var31);
      var31.initWorldGenSeed(var0);
      var32.initWorldGenSeed(var0);
      return new GenLayer[]{var31, var32, var31};
   }

   public int selectRandom(int... var1) {
      return var1[this.a(var1.length)];
   }

   public int b(int var1, int var2, int var3, int var4) {
      return var2 == var3 && var3 == var4
         ? var2
         : (
            var1 == var2 && var1 == var3
               ? var1
               : (
                  var1 == var2 && var1 == var4
                     ? var1
                     : (
                        var1 == var3 && var1 == var4
                           ? var1
                           : (
                              var1 == var2 && var3 != var4
                                 ? var1
                                 : (
                                    var1 == var3 && var2 != var4
                                       ? var1
                                       : (
                                          var1 == var4 && var2 != var3
                                             ? var1
                                             : (
                                                var2 == var3 && var1 != var4
                                                   ? var2
                                                   : (
                                                      var2 == var4 && var1 != var3
                                                         ? var2
                                                         : (var3 == var4 && var1 != var2 ? var3 : this.selectRandom(var1, var2, var3, var4))
                                                   )
                                             )
                                       )
                                 )
                           )
                     )
               )
         );
   }

   public void initWorldGenSeed(long var1) {
      this.field_0002 = var1;
      if (this.a != null) {
         this.a.initWorldGenSeed(var1);
      }

      this.field_0002 = this.field_0002 * (this.field_0002 * (8782019204327407597L & -2417882980806197441L) + (1442695040897355103L & 8545016793082593615L));
      this.field_0002 = this.field_0002 + this.field_0001;
      this.field_0002 = this.field_0002 * (this.field_0002 * (6726125415550025533L & 6364136224440287023L) + (1442695041024235887L & 1442695040897376719L));
      this.field_0002 = this.field_0002 + this.field_0001;
      this.field_0002 = this.field_0002 * (this.field_0002 * (6364136224421936943L & 6364136223863603133L) + (-4649971017744676897L & 6092666058498883951L));
      this.field_0002 = this.field_0002 + this.field_0001;
   }

   public int a(int var1) {
      int var2 = (int)((this.field_0000 >> 24) % var1);
      if (var2 < 0) {
         var2 += var1;
      }

      this.field_0000 = this.field_0000 * (this.field_0000 * (6364136223848890237L & 6364136224153141037L) + (1442695040888979839L & 1442695040897361231L));
      this.field_0000 = this.field_0000 + this.field_0002;
      return var2;
   }
}
