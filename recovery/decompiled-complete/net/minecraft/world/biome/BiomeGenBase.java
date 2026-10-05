package net.minecraft.world.biome;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import io.netty.util.internal.RecyclableArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.BlockFlower$EnumFlowerType;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockSand$EnumType;
import net.minecraft.block.BlockTallGrass$EnumType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.passive.EntityChicken;
import net.minecraft.entity.passive.EntityCow;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.BlockPos$MutableBlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.NoiseGeneratorPerlin;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenBigTree;
import net.minecraft.world.gen.feature.WorldGenDoublePlant;
import net.minecraft.world.gen.feature.WorldGenSwamp;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenTrees;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.optifine.CustomColorFader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class BiomeGenBase {
   public static BiomeGenBase jungle = new BiomeGenJungle(21, false).setColor(5470985).a("Jungle").a(5470985).a(0.95F, 0.9F);
   public static BiomeGenBase roofedForest = new BiomeGenForest(29, 3).setColor(4215066).a("Roofed Forest");
   public static BiomeGenBase field_0036 = new BiomeGenBeach(16).setColor(16440917).a("Beach").a(0.8F, 0.4F).a(BiomeGenBase.field_0078);
   public static BiomeGenBase desert = new BiomeGenDesert(2).setColor(16421912).a("Desert").b().a(2.0F, 0.0F).a(BiomeGenBase.e);
   public static BiomeGenBase birchForest = new BiomeGenForest(27, 2).a("Birch Forest").setColor(3175492);
   public static BiomeGenBase taigaHills = new BiomeGenTaiga(19, 0).setColor(1456435).a("TaigaHills").a(5159473).a(0.25F, 0.8F).a(BiomeGenBase.g);
   public static BiomeGenBase frozenOcean = new BiomeGenOcean(10).setColor(9474208).a("FrozenOcean").setEnableSnow().a(BiomeGenBase.field_0007).a(0.0F, 0.5F);
   public static BiomeGenBase mesa = new BiomeGenMesa(37, false, false).setColor(14238997).a("Mesa");
   public BiomeDecorator as;
   public static BiomeGenBase megaTaiga = new BiomeGenTaiga(32, 1).setColor(5858897).a("Mega Taiga").a(5159473).a(0.3F, 0.8F).a(BiomeGenBase.field_0026);
   public int ar;
   public static Logger field_0041 = LogManager.getLogger();
   public static BiomeGenBase$Height field_0050 = new BiomeGenBase$Height(-0.5F, 0.0F);
   public static BiomeGenBase field_0027 = new BiomeGenSavanna(36).setColor(10984804).a("Savanna Plateau").a(1.0F, 0.0F).b().a(BiomeGenBase.field_0035);
   public static BiomeGenBase$Height height_Default = new BiomeGenBase$Height(0.1F, 0.2F);
   public IBlockState al;
   public IBlockState ak = Blocks.grass.getDefaultState();
   public float aq;
   public static BiomeGenBase field_0045 = new BiomeGenStoneBeach(25).setColor(10658436).a("Stone Beach").a(0.2F, 0.3F).a(BiomeGenBase.field_0065);
   public float an;
   public static BiomeGenBase field_0008 = new BiomeGenMushroomIsland(15).setColor(10486015).a("MushroomIslandShore").a(0.9F, 1.0F).a(BiomeGenBase.field_0078);
   public float ap;
   public int ai;
   public static BiomeGenBase$Height field_0004 = new BiomeGenBase$Height(-1.8F, 0.1F);
   public static BiomeGenBase field_180279_ad = BiomeGenBase.ocean;
   public static BiomeGenBase taiga = new BiomeGenTaiga(5, 0).setColor(747097).a("Taiga").a(5159473).a(0.25F, 0.8F).a(BiomeGenBase.field_0026);
   public static BiomeGenBase$Height e = new BiomeGenBase$Height(0.125F, 0.05F);
   public WorldGenBigTree worldGeneratorBigTree;
   public static BiomeGenBase$Height field_0078 = new BiomeGenBase$Height(0.0F, 0.025F);
   public static BiomeGenBase plains = new BiomeGenPlains(1).setColor(9286496).a("Plains");
   public List<BiomeGenBase$SpawnListEntry> at;
   public static BiomeGenBase[] biomeList = new BiomeGenBase[256];
   public List<BiomeGenBase$SpawnListEntry> au;
   public static BiomeGenBase ocean = new BiomeGenOcean(0).setColor(112).a("Ocean").a(BiomeGenBase.field_0007);
   public int aj;
   public boolean ay;
   public int az;
   public static BiomeGenBase field_0084 = new BiomeGenSnow(13, false).setColor(10526880).a("Ice Mountains").setEnableSnow().a(BiomeGenBase.g).a(0.0F, 0.5F);
   public WorldGenSwamp aC;
   public static BiomeGenBase river = new BiomeGenRiver(7).setColor(255).a("River").a(field_0050);
   public static BiomeGenBase extremeHills = new BiomeGenHills(3, false).setColor(6316128).a("Extreme Hills").a(BiomeGenBase.field_0055).a(0.2F, 0.3F);
   public String ah;
   public static BiomeGenBase deepOcean = new BiomeGenOcean(24).setColor(48).a("Deep Ocean").a(field_0004);
   public static BiomeGenBase field_0002 = new BiomeGenBeach(26).setColor(16445632).a("Cold Beach").a(0.05F, 0.3F).a(field_0078).setEnableSnow();
   public static NoiseGeneratorPerlin af;
   public static Map<String, BiomeGenBase> BIOME_ID_MAP = Maps.newHashMap();
   public static BiomeGenBase field_0048 = new BiomeGenHills(34, true).setColor(5271632).a("Extreme Hills+").a(BiomeGenBase.field_0055).a(0.2F, 0.3F);
   public static BiomeGenBase field_0052 = new BiomeGenHills(20, true)
      .setColor(7501978)
      .a("Extreme Hills Edge")
      .a(BiomeGenBase.field_0055.attenuate())
      .a(0.2F, 0.3F);
   public static BiomeGenBase mushroomIsland = new BiomeGenMushroomIsland(14).setColor(16711935).a("MushroomIsland").a(0.9F, 1.0F).a(BiomeGenBase.field_0079);
   public static BiomeGenBase icePlains = new BiomeGenSnow(12, false).setColor(16777215).a("Ice Plains").setEnableSnow().a(0.0F, 0.5F).a(e);
   public static BiomeGenBase forestHills = new BiomeGenForest(18, 0).setColor(2250012).a("ForestHills").a(BiomeGenBase.g);
   public static BiomeGenBase$Height field_0055 = new BiomeGenBase$Height(1.0F, 0.5F);
   public static WorldGenDoublePlant ag;
   public static BiomeGenBase$Height field_0065 = new BiomeGenBase$Height(0.1F, 0.8F);
   public float ao;
   public static BiomeGenBase$Height field_0035 = new BiomeGenBase$Height(1.5F, 0.025F);
   public static BiomeGenBase forest = new BiomeGenForest(4, 0).setColor(353825).a("Forest");
   public static BiomeGenBase field_0057 = new BiomeGenTaiga(33, 1).setColor(4542270).a("Mega Taiga Hills").a(5159473).a(0.3F, 0.8F).a(BiomeGenBase.g);
   public static BiomeGenBase$Height g = new BiomeGenBase$Height(0.45F, 0.3F);
   public static BiomeGenBase field_0044 = new BiomeGenForest(28, 2).a("Birch Forest Hills").setColor(2055986).a(g);
   public static Set<BiomeGenBase> explorationBiomesList = Sets.newHashSet();
   public static BiomeGenBase$Height field_0079 = new BiomeGenBase$Height(0.2F, 0.3F);
   public RecyclableArrayList field_0031;
   public static BiomeGenBase field_0053 = new BiomeGenTaiga(31, 0)
      .setColor(2375478)
      .a("Cold Taiga Hills")
      .a(5159473)
      .setEnableSnow()
      .a(-0.5F, 0.4F)
      .a(g)
      .method_26674(16777215);
   public static BiomeGenBase desertHills = new BiomeGenDesert(17).setColor(13786898).a("DesertHills").b().a(2.0F, 0.0F).a(g);
   public static BiomeGenBase hell = new BiomeGenHell(8).setColor(16711680).a("Hell").b().a(2.0F, 0.0F);
   public CustomColorFader field_0069;
   public static BiomeGenBase coldTaiga = new BiomeGenTaiga(30, 0)
      .setColor(3233098)
      .a("Cold Taiga")
      .a(5159473)
      .setEnableSnow()
      .a(-0.5F, 0.4F)
      .a(BiomeGenBase.field_0026)
      .method_26674(16777215);
   public static BiomeGenBase$Height field_0082 = new BiomeGenBase$Height(-0.2F, 0.1F);
   public static BiomeGenBase jungleHills = new BiomeGenJungle(22, false).setColor(2900485).a("JungleHills").a(5470985).a(0.95F, 0.9F).a(g);
   public static NoiseGeneratorPerlin temperatureNoise;
   public static BiomeGenBase mesaPlateau_F = new BiomeGenMesa(38, false, true).setColor(11573093).a("Mesa Plateau F").a(field_0035);
   public static BiomeGenBase$Height field_0026 = new BiomeGenBase$Height(0.2F, 0.2F);
   public static BiomeGenBase mesaPlateau = new BiomeGenMesa(39, false, false).setColor(13274213).a("Mesa Plateau").a(field_0035);
   public int am;
   public static BiomeGenBase field_0059 = new BiomeGenJungle(23, true).setColor(6458135).a("JungleEdge").a(5470985).a(0.95F, 0.8F);
   public static BiomeGenBase frozenRiver = new BiomeGenRiver(11).setColor(10526975).a("FrozenRiver").setEnableSnow().a(field_0050).a(0.0F, 0.5F);
   public List<BiomeGenBase$SpawnListEntry> aw;
   public boolean ax;
   public static BiomeGenBase savanna = new BiomeGenSavanna(35).setColor(12431967).a("Savanna").a(1.2F, 0.0F).b().a(e);
   public static BiomeGenBase swampland = new BiomeGenSwamp(6).setColor(522674).a("Swampland").a(9154376).a(field_0082).a(0.8F, 0.9F);
   public static BiomeGenBase sky = new BiomeGenEnd(9).setColor(8421631).a("The End").b();
   public WorldGenTrees aA;
   public List<BiomeGenBase$SpawnListEntry> av;
   public static BiomeGenBase$Height field_0007 = new BiomeGenBase$Height(-1.0F, 0.1F);

   public BiomeGenBase setEnableSnow() {
      this.ax = true;
      return this;
   }

   public void decorate(World var1, Random var2, BlockPos var3) {
      this.as.decorate(var1, var2, this, var3);
   }

   public List<BiomeGenBase$SpawnListEntry> getSpawnableList(EnumCreatureType var1) {
      switch (BiomeGenBase$1.field_180275_a[var1.ordinal()]) {
         case 1:
            return this.at;
         case 2:
            return this.au;
         case 3:
            return this.av;
         case 4:
            return this.aw;
         default:
            return Collections.emptyList();
      }
   }

   static {
      plains.createMutation();
      desert.createMutation();
      forest.createMutation();
      taiga.createMutation();
      swampland.createMutation();
      icePlains.createMutation();
      jungle.createMutation();
      field_0059.createMutation();
      coldTaiga.createMutation();
      savanna.createMutation();
      field_0027.createMutation();
      mesa.createMutation();
      mesaPlateau_F.createMutation();
      mesaPlateau.createMutation();
      birchForest.createMutation();
      field_0044.createMutation();
      roofedForest.createMutation();
      megaTaiga.createMutation();
      extremeHills.createMutation();
      field_0048.createMutation();
      megaTaiga.createMutatedBiome(field_0057.az + 128).a("Redwood Taiga Hills M");

      for (BiomeGenBase var3 : biomeList) {
         if (var3 != null) {
            if (BIOME_ID_MAP.containsKey(var3.ah)) {
               throw new Error("Biome \"" + var3.ah + "\" is defined as both ID " + BIOME_ID_MAP.get(var3.ah).az + " and " + var3.az);
            }

            BIOME_ID_MAP.put(var3.ah, var3);
            if (var3.az < 128) {
               explorationBiomesList.add(var3);
            }
         }
      }

      explorationBiomesList.remove(hell);
      explorationBiomesList.remove(sky);
      explorationBiomesList.remove(frozenOcean);
      explorationBiomesList.remove(field_0052);
      temperatureNoise = new NoiseGeneratorPerlin(new Random(1212310742L & -5162455789528410414L), 1);
      af = new NoiseGeneratorPerlin(new Random(587934635L & -8959373979926914775L), 1);
      ag = new WorldGenDoublePlant();
   }

   public BlockFlower$EnumFlowerType pickRandomFlower(Random var1, BlockPos var2) {
      return var1.nextInt(3) > 0 ? BlockFlower$EnumFlowerType.DANDELION : BlockFlower$EnumFlowerType.POPPY;
   }

   public BiomeGenBase createMutation() {
      return this.createMutatedBiome(this.az + 128);
   }

   public BiomeGenBase$TempCategory getTempCategory() {
      return this.ap < 0.2 ? BiomeGenBase$TempCategory.COLD : (this.ap < 1.0 ? BiomeGenBase$TempCategory.MEDIUM : BiomeGenBase$TempCategory.WARM);
   }

   public boolean getEnableSnow() {
      return this.isSnowyBiome();
   }

   public int getIntRainfall() {
      return (int)(this.aq * 65536.0F);
   }

   public WorldGenAbstractTree genBigTreeChance(Random var1) {
      return (WorldGenAbstractTree)(var1.nextInt(10) == 0 ? this.worldGeneratorBigTree : this.aA);
   }

   public void genTerrainBlocks(World var1, Random var2, ChunkPrimer var3, int var4, int var5, double var6) {
      this.b(var1, var2, var3, var4, var5, var6);
   }

   public BiomeDecorator createBiomeDecorator() {
      return new BiomeDecorator();
   }

   public WorldGenerator getRandomWorldGenForGrass(Random var1) {
      return new WorldGenTallGrass(BlockTallGrass$EnumType.GRASS);
   }

   public Class<? extends BiomeGenBase> getBiomeClass() {
      return (Class<? extends BiomeGenBase>)this.getClass();
   }

   public float getFloatRainfall() {
      return this.aq;
   }

   public BiomeGenBase a(String var1) {
      this.ah = var1;
      return this;
   }

   public float getSpawningChance() {
      return 0.1F;
   }

   public static BiomeGenBase getBiome(int var0) {
      return getBiomeFromBiomeList(var0, (BiomeGenBase)null);
   }

   public static BiomeGenBase getBiomeFromBiomeList(int var0, BiomeGenBase var1) {
      if (var0 >= 0 && var0 <= biomeList.length) {
         BiomeGenBase var2 = biomeList[var0];
         return var2 == null ? var1 : var2;
      } else {
         field_0041.warn("Biome ID is out of bounds: " + var0 + ", defaulting to 0 (Ocean)");
         return ocean;
      }
   }

   public BiomeGenBase a(float var1, float var2) {
      if (var1 > 0.1F && var1 < 0.2F) {
         throw new IllegalArgumentException("Please avoid temperatures in the range 0.1 - 0.2 because of snow");
      } else {
         this.ap = var1;
         this.aq = var2;
         return this;
      }
   }

   public static BiomeGenBase[] getBiomeGenArray() {
      return biomeList;
   }

   public float getFloatTemperature(BlockPos var1) {
      if (var1.getY() > 64) {
         float var2 = (float)(temperatureNoise.func_151601_a(var1.getX() * 1.0 / 8.0, var1.getZ() * 1.0 / 8.0) * 4.0);
         return this.ap - (var2 + var1.getY() - 64.0F) * 0.05F / 30.0F;
      } else {
         return this.ap;
      }
   }

   public int getFoliageColorAtPos(BlockPos var1) {
      double var2 = MathHelper.clamp_float(this.getFloatTemperature(var1), 0.0F, 1.0F);
      double var4 = MathHelper.clamp_float(this.getFloatRainfall(), 0.0F, 1.0F);
      return ColorizerFoliage.getFoliageColor(var2, var4);
   }

   public boolean isHighHumidity() {
      return this.aq > 0.85F;
   }

   public BiomeGenBase createMutatedBiome(int var1) {
      return new BiomeGenMutated(var1, this);
   }

   public int getGrassColorAtPos(BlockPos var1) {
      double var2 = MathHelper.clamp_float(this.getFloatTemperature(var1), 0.0F, 1.0F);
      double var4 = MathHelper.clamp_float(this.getFloatRainfall(), 0.0F, 1.0F);
      return ColorizerGrass.getGrassColor(var2, var4);
   }

   public BiomeGenBase a(BiomeGenBase$Height var1) {
      this.an = var1.rootHeight;
      this.ao = var1.variation;
      return this;
   }

   public BiomeGenBase method_26674(int var1) {
      this.aj = var1;
      return this;
   }

   public boolean canRain() {
      return this.isSnowyBiome() ? false : this.ay;
   }

   public int getSkyColorByTemp(float var1) {
      var1 /= 3.0F;
      var1 = MathHelper.clamp_float(var1, -1.0F, 1.0F);
      return MathHelper.hsvToRGB(0.62222224F - var1 * 0.05F, 0.5F + var1 * 0.1F, 1.0F);
   }

   public boolean isSnowyBiome() {
      return this.ax;
   }

   public BiomeGenBase b() {
      this.ay = false;
      return this;
   }

   public BiomeGenBase a(int var1) {
      this.am = var1;
      return this;
   }

   public boolean isEqualTo(BiomeGenBase var1) {
      return var1 == this ? true : (var1 == null ? false : this.getBiomeClass() == var1.getBiomeClass());
   }

   public BiomeGenBase(int var1) {
      this.al = Blocks.dirt.getDefaultState();
      this.am = 5169201;
      this.an = height_Default.rootHeight;
      this.ao = height_Default.variation;
      this.ap = 0.5F;
      this.aq = 0.5F;
      this.ar = 16777215;
      this.at = Lists.newArrayList();
      this.au = Lists.newArrayList();
      this.av = Lists.newArrayList();
      this.aw = Lists.newArrayList();
      this.ay = true;
      this.aA = new WorldGenTrees(false);
      this.worldGeneratorBigTree = new WorldGenBigTree(false);
      this.aC = new WorldGenSwamp();
      this.az = var1;
      biomeList[var1] = this;
      this.as = this.createBiomeDecorator();
      this.au.add(new BiomeGenBase$SpawnListEntry(EntitySheep.class, 12, 4, 4));
      this.au.add(new BiomeGenBase$SpawnListEntry(EntityRabbit.class, 10, 3, 3));
      this.au.add(new BiomeGenBase$SpawnListEntry(EntityPig.class, 10, 4, 4));
      this.au.add(new BiomeGenBase$SpawnListEntry(EntityChicken.class, 10, 4, 4));
      this.au.add(new BiomeGenBase$SpawnListEntry(EntityCow.class, 8, 4, 4));
      this.at.add(new BiomeGenBase$SpawnListEntry(EntitySpider.class, 100, 4, 4));
      this.at.add(new BiomeGenBase$SpawnListEntry(EntityZombie.class, 100, 4, 4));
      this.at.add(new BiomeGenBase$SpawnListEntry(EntitySkeleton.class, 100, 4, 4));
      this.at.add(new BiomeGenBase$SpawnListEntry(EntityCreeper.class, 100, 4, 4));
      this.at.add(new BiomeGenBase$SpawnListEntry(EntitySlime.class, 100, 4, 4));
      this.at.add(new BiomeGenBase$SpawnListEntry(EntityEnderman.class, 10, 1, 4));
      this.at.add(new BiomeGenBase$SpawnListEntry(EntityWitch.class, 5, 1, 1));
      this.av.add(new BiomeGenBase$SpawnListEntry(EntitySquid.class, 10, 4, 4));
      this.aw.add(new BiomeGenBase$SpawnListEntry(EntityBat.class, 10, 8, 8));
   }

   public void b(World var1, Random var2, ChunkPrimer var3, int var4, int var5, double var6) {
      int var8 = var1.F();
      IBlockState var9 = this.ak;
      IBlockState var10 = this.al;
      int var11 = -1;
      int var12 = (int)(var6 / 3.0 + 3.0 + var2.nextDouble() * 0.25);
      int var13 = var4 & 15;
      int var14 = var5 & 15;
      BlockPos$MutableBlockPos var15 = new BlockPos$MutableBlockPos();

      for (int var16 = 255; var16 >= 0; var16--) {
         if (var16 <= var2.nextInt(5)) {
            var3.setBlockState(var14, var16, var13, Blocks.bedrock.getDefaultState());
         } else {
            IBlockState var17 = var3.getBlockState(var14, var16, var13);
            if (var17.getBlock().getMaterial() == Material.air) {
               var11 = -1;
            } else if (var17.getBlock() == Blocks.stone) {
               if (var11 == -1) {
                  if (var12 <= 0) {
                     var9 = null;
                     var10 = Blocks.stone.getDefaultState();
                  } else if (var16 >= var8 - 4 && var16 <= var8 + 1) {
                     var9 = this.ak;
                     var10 = this.al;
                  }

                  if (var16 < var8 && (var9 == null || var9.getBlock().getMaterial() == Material.air)) {
                     if (this.getFloatTemperature(var15.set(var4, var16, var5)) < 0.15F) {
                        var9 = Blocks.ice.getDefaultState();
                     } else {
                        var9 = Blocks.water.getDefaultState();
                     }
                  }

                  var11 = var12;
                  if (var16 >= var8 - 1) {
                     var3.setBlockState(var14, var16, var13, var9);
                  } else if (var16 < var8 - 7 - var12) {
                     var9 = null;
                     var10 = Blocks.stone.getDefaultState();
                     var3.setBlockState(var14, var16, var13, Blocks.gravel.getDefaultState());
                  } else {
                     var3.setBlockState(var14, var16, var13, var10);
                  }
               } else if (var11 > 0) {
                  var11--;
                  var3.setBlockState(var14, var16, var13, var10);
                  if (var11 == 0 && var10.getBlock() == Blocks.sand) {
                     var11 = var2.nextInt(4) + Math.max(0, var16 - 63);
                     var10 = var10.getValue(BlockSand.VARIANT) == BlockSand$EnumType.RED_SAND
                        ? Blocks.red_sandstone.getDefaultState()
                        : Blocks.sandstone.getDefaultState();
                  }
               }
            }
         }
      }
   }

   public BiomeGenBase setColor(int var1) {
      this.a(var1, false);
      return this;
   }

   public BiomeGenBase a(int var1, boolean var2) {
      this.ai = var1;
      if (var2) {
         this.aj = (var1 & 16711422) >> 1;
      } else {
         this.aj = var1;
      }

      return this;
   }
}
