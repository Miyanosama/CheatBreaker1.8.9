package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ForEachTransformedKeyTask;
import io.netty.util.internal.chmv8.ForkJoinTask;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import javax.vecmath.Tuple4d;
import net.minecraft.network.play.server.S42PacketCombatEvent$1;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.biome.BiomeGenBase;

public class MapGenStronghold extends MapGenStructure {
   public int field_82672_i;
   public ForkJoinTask field_0005;
   public S42PacketCombatEvent$1 field_0001;
   public ChunkCoordIntPair[] structureCoords = new ChunkCoordIntPair[3];
   public Tuple4d field_0000;
   public List<BiomeGenBase> field_151546_e;
   public ConcurrentHashMapV8$ForEachTransformedKeyTask field_0004;
   public boolean ranBiomeCheck;
   public double field_82671_h = 32.0;

   @Override
   public String getStructureName() {
      return "Stronghold";
   }

   @Override
   public StructureStart getStructureStart(int var1, int var2) {
      MapGenStronghold$Start var3 = new MapGenStronghold$Start(this.c, this.b, var1, var2);

      while (var3.getComponents().isEmpty() || ((StructureStrongholdPieces$Stairs2)var3.getComponents().get(0)).strongholdPortalRoom == null) {
         var3 = new MapGenStronghold$Start(this.c, this.b, var1, var2);
      }

      return var3;
   }

   @Override
   public List<BlockPos> D_() {
      ArrayList var1 = Lists.newArrayList();

      for (ChunkCoordIntPair var5 : this.structureCoords) {
         if (var5 != null) {
            var1.add(var5.getCenterBlock(64));
         }
      }

      return var1;
   }

   public MapGenStronghold() {
      this.field_82672_i = 3;
      this.field_151546_e = Lists.newArrayList();

      for (BiomeGenBase var4 : BiomeGenBase.getBiomeGenArray()) {
         if (var4 != null && var4.an > 0.0F) {
            this.field_151546_e.add(var4);
         }
      }
   }

   public MapGenStronghold(Map<String, String> var1) {
      this();

      for (Entry var3 : var1.entrySet()) {
         if (((String)var3.getKey()).equals("distance")) {
            this.field_82671_h = MathHelper.parseDoubleWithDefaultAndMax((String)var3.getValue(), this.field_82671_h, 1.0);
         } else if (((String)var3.getKey()).equals("count")) {
            this.structureCoords = new ChunkCoordIntPair[MathHelper.parseIntWithDefaultAndMax((String)var3.getValue(), this.structureCoords.length, 1)];
         } else if (((String)var3.getKey()).equals("spread")) {
            this.field_82672_i = MathHelper.parseIntWithDefaultAndMax((String)var3.getValue(), this.field_82672_i, 1);
         }
      }
   }

   @Override
   public boolean canSpawnStructureAtCoords(int var1, int var2) {
      if (!this.ranBiomeCheck) {
         Random var3 = new Random();
         var3.setSeed(this.c.J());
         double var4 = var3.nextDouble() * Math.PI * 2.0;
         int var6 = 1;

         for (int var7 = 0; var7 < this.structureCoords.length; var7++) {
            double var8 = (1.25 * var6 + var3.nextDouble()) * this.field_82671_h * var6;
            int var10 = (int)Math.round(Math.cos(var4) * var8);
            int var11 = (int)Math.round(Math.sin(var4) * var8);
            BlockPos var12 = this.c.getWorldChunkManager().findBiomePosition((var10 << 4) + 8, (var11 << 4) + 8, 112, this.field_151546_e, var3);
            if (var12 != null) {
               var10 = var12.getX() >> 4;
               var11 = var12.getZ() >> 4;
            }

            this.structureCoords[var7] = new ChunkCoordIntPair(var10, var11);
            var4 += (Math.PI * 2) * var6 / this.field_82672_i;
            if (var7 == this.field_82672_i) {
               var6 += 2 + var3.nextInt(5);
               this.field_82672_i = this.field_82672_i + 1 + var3.nextInt(2);
            }
         }

         this.ranBiomeCheck = true;
      }

      for (ChunkCoordIntPair var15 : this.structureCoords) {
         if (var1 == var15.chunkXPos && var2 == var15.chunkZPos) {
            return true;
         }
      }

      return false;
   }
}
