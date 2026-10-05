package net.minecraft.world.biome;

import com.google.common.collect.Lists;
import java.util.Random;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.network.ServerStatusResponse$MinecraftProtocolVersionIdentifier$Serializer;
import net.minecraft.network.play.client.C03PacketPlayer$C05PacketPlayerLook;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import recovered.unidentified.UnidentifiedClass3854;

public class BiomeGenMutated extends BiomeGenBase {
   public ServerStatusResponse$MinecraftProtocolVersionIdentifier$Serializer field_0003;
   public BiomeGenBase baseBiome;
   public UnidentifiedClass3854 field_0004;
   public AnimationMetadataSection field_0005;
   public C03PacketPlayer$C05PacketPlayerLook field_0002;
   public MovingObjectPosition field_0001;

   @Override
   public Class<? extends BiomeGenBase> getBiomeClass() {
      return this.baseBiome.getBiomeClass();
   }

   @Override
   public int getGrassColorAtPos(BlockPos var1) {
      return this.baseBiome.getGrassColorAtPos(var1);
   }

   @Override
   public BiomeGenBase$TempCategory getTempCategory() {
      return this.baseBiome.getTempCategory();
   }

   @Override
   public WorldGenAbstractTree genBigTreeChance(Random var1) {
      return this.baseBiome.genBigTreeChance(var1);
   }

   @Override
   public void decorate(World var1, Random var2, BlockPos var3) {
      this.baseBiome.as.decorate(var1, var2, this, var3);
   }

   @Override
   public void genTerrainBlocks(World var1, Random var2, ChunkPrimer var3, int var4, int var5, double var6) {
      this.baseBiome.genTerrainBlocks(var1, var2, var3, var4, var5, var6);
   }

   @Override
   public int getFoliageColorAtPos(BlockPos var1) {
      return this.baseBiome.getFoliageColorAtPos(var1);
   }

   @Override
   public boolean isEqualTo(BiomeGenBase var1) {
      return this.baseBiome.isEqualTo(var1);
   }

   @Override
   public float getSpawningChance() {
      return this.baseBiome.getSpawningChance();
   }

   public BiomeGenMutated(int var1, BiomeGenBase var2) {
      super(var1);
      this.baseBiome = var2;
      this.a(var2.ai, true);
      this.ah = var2.ah + " M";
      this.ak = var2.ak;
      this.al = var2.al;
      this.am = var2.am;
      this.an = var2.an;
      this.ao = var2.ao;
      this.ap = var2.ap;
      this.aq = var2.aq;
      this.ar = var2.ar;
      this.ax = var2.ax;
      this.ay = var2.ay;
      this.au = Lists.newArrayList(var2.au);
      this.at = Lists.newArrayList(var2.at);
      this.aw = Lists.newArrayList(var2.aw);
      this.av = Lists.newArrayList(var2.av);
      this.ap = var2.ap;
      this.aq = var2.aq;
      this.an = var2.an + 0.1F;
      this.ao = var2.ao + 0.2F;
   }
}
