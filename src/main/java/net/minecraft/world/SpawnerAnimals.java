package net.minecraft.world;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.Chunk;
import net.optifine.BlockPosM;
import net.optifine.reflect.Reflector;
import net.optifine.reflect.ReflectorForge;

public class SpawnerAnimals {
   public int countChunkPos;
   public Set<ChunkCoordIntPair> eligibleChunksForSpawning = Sets.newHashSet();
   public int lastPlayerChunkX;
   public int lastPlayerChunkZ;
   public static int MOB_COUNT_DIV = (int)Math.pow(17.0, 2.0);
   public Map<Class, EntityLiving> mapSampleEntitiesByClass = new HashMap<>();

   public static boolean canCreatureTypeSpawnAtLocation(EntityLiving.SpawnPlacementType var0, World var1, BlockPos var2) {
      if (!var1.af().contains(var2)) {
         return false;
      } else if (var0 == null) {
         return false;
      } else {
         Block var3 = var1.getBlockState(var2).getBlock();
         if (var0 == EntityLiving.SpawnPlacementType.IN_WATER) {
            return var3.getMaterial().isLiquid()
               && var1.getBlockState(var2.down()).getBlock().getMaterial().isLiquid()
               && !var1.getBlockState(var2.up()).getBlock().isNormalCube();
         } else {
            BlockPos var4 = var2.down();
            IBlockState var5 = var1.getBlockState(var4);
            boolean var6 = Reflector.ForgeBlock_canCreatureSpawn.exists()
               ? Reflector.callBoolean(var5.getBlock(), Reflector.ForgeBlock_canCreatureSpawn, var1, var4, var0)
               : World.doesBlockHaveSolidTopSurface(var1, var4);
            if (!var6) {
               return false;
            } else {
               Block var7 = var1.getBlockState(var4).getBlock();
               boolean var8 = var7 != Blocks.bedrock && var7 != Blocks.barrier;
               return var8 && !var3.isNormalCube() && !var3.getMaterial().isLiquid() && !var1.getBlockState(var2.up()).getBlock().isNormalCube();
            }
         }
      }
   }

   public static void performWorldGenSpawning(World var0, BiomeGenBase var1, int var2, int var3, int var4, int var5, Random var6) {
      List var7 = var1.getSpawnableList(EnumCreatureType.CREATURE);
      if (!var7.isEmpty()) {
         while (var6.nextFloat() < var1.getSpawningChance()) {
            BiomeGenBase.SpawnListEntry var8 = (BiomeGenBase.SpawnListEntry)WeightedRandom.getRandomItem(var0.s, var7);
            int var9 = var8.minGroupCount + var6.nextInt(1 + var8.maxGroupCount - var8.minGroupCount);
            IEntityLivingData var10 = null;
            int var11 = var2 + var6.nextInt(var4);
            int var12 = var3 + var6.nextInt(var5);
            int var13 = var11;
            int var14 = var12;

            for (int var15 = 0; var15 < var9; var15++) {
               boolean var16 = false;

               for (int var17 = 0; !var16 && var17 < 4; var17++) {
                  BlockPos var18 = var0.getTopSolidOrLiquidBlock(new BlockPos(var11, 0, var12));
                  if (canCreatureTypeSpawnAtLocation(EntityLiving.SpawnPlacementType.ON_GROUND, var0, var18)) {
                     EntityLiving var19;
                     try {
                        var19 = var8.entityClass.getConstructor(World.class).newInstance(var0);
                     } catch (Exception var21) {
                        var21.printStackTrace();
                        continue;
                     }

                     if (Reflector.ForgeEventFactory_canEntitySpawn.exists()) {
                        Object var20 = Reflector.call(Reflector.ForgeEventFactory_canEntitySpawn, var19, var0, var11 + 0.5F, var18.getY(), var12 + 0.5F);
                        if (var20 == ReflectorForge.EVENT_RESULT_DENY) {
                           continue;
                        }
                     }

                     var19.a_(var11 + 0.5F, var18.getY(), var12 + 0.5F, var6.nextFloat() * 360.0F, 0.0F);
                     var0.spawnEntityInWorld(var19);
                     var10 = var19.onInitialSpawn(var0.E(new BlockPos(var19)), var10);
                     var16 = true;
                  }

                  var11 += var6.nextInt(5) - var6.nextInt(5);

                  for (var12 += var6.nextInt(5) - var6.nextInt(5);
                     var11 < var2 || var11 >= var2 + var4 || var12 < var3 || var12 >= var3 + var4;
                     var12 = var14 + var6.nextInt(5) - var6.nextInt(5)
                  ) {
                     var11 = var13 + var6.nextInt(5) - var6.nextInt(5);
                  }
               }
            }
         }
      }
   }

   public int findChunksForSpawning(WorldServer var1, boolean var2, boolean var3, boolean var4) {
      if (!var2 && !var3) {
         return 0;
      } else {
         boolean var5 = true;
         EntityPlayer var6 = null;
         if (var1.j.size() == 1) {
            var6 = var1.j.get(0);
            if (this.eligibleChunksForSpawning.size() > 0
               && var6 != null
               && var6.chunkCoordX == this.lastPlayerChunkX
               && var6.chunkCoordZ == this.lastPlayerChunkZ) {
               var5 = false;
            }
         }

         if (var5) {
            this.eligibleChunksForSpawning.clear();
            int var7 = 0;

            for (EntityPlayer var9 : var1.j) {
               if (!var9.isSpectator()) {
                  int var10 = MathHelper.floor_double(var9.s / 16.0);
                  int var11 = MathHelper.floor_double(var9.u / 16.0);
                  byte var12 = 8;

                  for (int var13 = -var12; var13 <= var12; var13++) {
                     for (int var14 = -var12; var14 <= var12; var14++) {
                        boolean var15 = var13 == -var12 || var13 == var12 || var14 == -var12 || var14 == var12;
                        ChunkCoordIntPair var16 = new ChunkCoordIntPair(var13 + var10, var14 + var11);
                        if (!this.eligibleChunksForSpawning.contains(var16)) {
                           var7++;
                           if (!var15 && var1.af().contains(var16)) {
                              this.eligibleChunksForSpawning.add(var16);
                           }
                        }
                     }
                  }
               }
            }

            this.countChunkPos = var7;
            if (var6 != null) {
               this.lastPlayerChunkX = var6.chunkCoordX;
               this.lastPlayerChunkZ = var6.chunkCoordZ;
            }
         }

         int var40 = 0;
         BlockPos var41 = var1.M();
         BlockPosM var42 = new BlockPosM(0, 0, 0);
         new BlockPos.MutableBlockPos();

         for (EnumCreatureType var46 : EnumCreatureType.values()) {
            if ((!var46.getPeacefulCreature() || var3) && (var46.getPeacefulCreature() || var2) && (!var46.getAnimal() || var4)) {
               int var47 = Reflector.ForgeWorld_countEntities.exists()
                  ? Reflector.callInt(var1, Reflector.ForgeWorld_countEntities, var46, true)
                  : var1.countEntities(var46.getCreatureClass());
               int var48 = var46.getMaxNumberOfCreature() * this.countChunkPos / MOB_COUNT_DIV;
               if (var47 <= var48) {
                  java.util.Collection<ChunkCoordIntPair> var49 = this.eligibleChunksForSpawning;
                  if (Reflector.ForgeHooksClient.exists()) {
                     ArrayList var17 = Lists.newArrayList((Iterable)var49);
                     Collections.shuffle(var17);
                     var49 = var17;
                  }

                  label166:
                  for (ChunkCoordIntPair var18 : var49) {
                     BlockPosM var19 = getRandomChunkPosition(var1, var18.chunkXPos, var18.chunkZPos, var42);
                     int var20 = var19.getX();
                     int var21 = var19.getY();
                     int var22 = var19.getZ();
                     Block var23 = var1.getBlockState(var19).getBlock();
                     if (!var23.isNormalCube()) {
                        int var24 = 0;

                        for (int var25 = 0; var25 < 3; var25++) {
                           int var26 = var20;
                           int var27 = var21;
                           int var28 = var22;
                           byte var29 = 6;
                           BiomeGenBase.SpawnListEntry var30 = null;
                           IEntityLivingData var31 = null;

                           for (int var32 = 0; var32 < 4; var32++) {
                              var26 += var1.s.nextInt(var29) - var1.s.nextInt(var29);
                              var27 += var1.s.nextInt(1) - var1.s.nextInt(1);
                              var28 += var1.s.nextInt(var29) - var1.s.nextInt(var29);
                              BlockPos var33 = new BlockPos(var26, var27, var28);
                              float var34 = var26 + 0.5F;
                              float var35 = var28 + 0.5F;
                              if (!var1.isAnyPlayerWithinRangeAt(var34, var27, var35, 24.0) && var41.distanceSq(var34, var27, var35) >= 576.0) {
                                 if (var30 == null) {
                                    var30 = var1.getSpawnListEntryForTypeAt(var46, var33);
                                    if (var30 == null) {
                                       break;
                                    }
                                 }

                                 if (var1.canCreatureTypeSpawnHere(var46, var30, var33)
                                    && canCreatureTypeSpawnAtLocation(EntitySpawnPlacementRegistry.getPlacementForEntity(var30.entityClass), var1, var33)) {
                                    EntityLiving var36;
                                    try {
                                       var36 = this.mapSampleEntitiesByClass.get(var30.entityClass);
                                       if (var36 == null) {
                                          var36 = var30.entityClass.getConstructor(World.class).newInstance(var1);
                                          this.mapSampleEntitiesByClass.put(var30.entityClass, var36);
                                       }
                                    } catch (Exception var39) {
                                       var39.printStackTrace();
                                       return var40;
                                    }

                                    var36.a_(var34, var27, var35, var1.s.nextFloat() * 360.0F, 0.0F);
                                    boolean var37 = Reflector.ForgeEventFactory_canEntitySpawn.exists()
                                       ? ReflectorForge.canEntitySpawn(var36, var1, var34, var27, var35)
                                       : var36.getCanSpawnHere() && var36.isNotColliding();
                                    if (var37) {
                                       this.mapSampleEntitiesByClass.remove(var30.entityClass);
                                       if (!ReflectorForge.doSpecialSpawn(var36, var1, var34, var27, var35)) {
                                          var31 = var36.onInitialSpawn(var1.E(new BlockPos(var36)), var31);
                                       }

                                       if (var36.isNotColliding()) {
                                          var24++;
                                          var1.spawnEntityInWorld(var36);
                                       }

                                       int var38 = Reflector.ForgeEventFactory_getMaxSpawnPackSize.exists()
                                          ? Reflector.callInt(Reflector.ForgeEventFactory_getMaxSpawnPackSize, var36)
                                          : var36.getMaxSpawnedInChunk();
                                       if (var24 >= var38) {
                                          continue label166;
                                       }
                                    }

                                    var40 += var24;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         return var40;
      }
   }

   public static BlockPosM getRandomChunkPosition(World var0, int var1, int var2, BlockPosM var3) {
      Chunk var4 = var0.a(var1, var2);
      int var5 = var1 * 16 + var0.s.nextInt(16);
      int var6 = var2 * 16 + var0.s.nextInt(16);
      int var7 = MathHelper.roundUp(var4.getHeightValue(var5 & 15, var6 & 15) + 1, 16);
      int var8 = var0.s.nextInt(var7 > 0 ? var7 : var4.getTopFilledSegment() + 16 - 1);
      var3.setXyz(var5, var8, var6);
      return var3;
   }

   public SpawnerAnimals() {
      this.lastPlayerChunkX = Integer.MAX_VALUE;
      this.lastPlayerChunkZ = Integer.MAX_VALUE;
   }

   public static BlockPos getRandomChunkPosition(World var0, int var1, int var2) {
      Chunk var3 = var0.a(var1, var2);
      int var4 = var1 * 16 + var0.s.nextInt(16);
      int var5 = var2 * 16 + var0.s.nextInt(16);
      int var6 = MathHelper.roundUp(var3.getHeight(new BlockPos(var4, 0, var5)) + 1, 16);
      int var7 = var0.s.nextInt(var6 > 0 ? var6 : var3.getTopFilledSegment() + 16 - 1);
      return new BlockPos(var4, var7, var5);
   }
}
