package net.minecraft.item;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.google.common.collect.Multisets;
import java.util.List;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockStone;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.network.Packet;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.storage.MapData;

public class ItemMap extends ItemMapBase {
   @Override
   public void addInformation(ItemStack var1, EntityPlayer var2, List<String> var3, boolean var4) {
      MapData var5 = this.getMapData(var1, var2.o);
      if (var4) {
         if (var5 == null) {
            var3.add("Unknown map");
         } else {
            var3.add("Scaling at 1:" + (1 << var5.scale));
            var3.add("(Level " + var5.scale + "/" + 4 + ")");
         }
      }
   }

   public static MapData loadMapData(int var0, World var1) {
      String var2 = "map_" + var0;
      MapData var3 = (MapData)var1.loadItemData(MapData.class, var2);
      if (var3 == null) {
         var3 = new MapData(var2);
         var1.setItemData(var2, var3);
      }

      return var3;
   }

   public ItemMap() {
      this.setHasSubtypes(true);
   }

   public MapData getMapData(ItemStack var1, World var2) {
      String var3 = "map_" + var1.getMetadata();
      MapData var4 = (MapData)var2.loadItemData(MapData.class, var3);
      if (var4 == null && !var2.D) {
         var1.setItemDamage(var2.getUniqueDataId("map"));
         var3 = "map_" + var1.getMetadata();
         var4 = new MapData(var3);
         var4.scale = 3;
         var4.calculateMapCenter(var2.P().getSpawnX(), var2.P().getSpawnZ(), var4.scale);
         var4.dimension = (byte)var2.t.getDimensionId();
         var4.markDirty();
         var2.setItemData(var3, var4);
      }

      return var4;
   }

   @Override
   public void onUpdate(ItemStack var1, World var2, Entity var3, int var4, boolean var5) {
      if (!var2.D) {
         MapData var6 = this.getMapData(var1, var2);
         if (var3 instanceof EntityPlayer) {
            EntityPlayer var7 = (EntityPlayer)var3;
            var6.updateVisiblePlayers(var7, var1);
         }

         if (var5) {
            this.updateMapData(var2, var3, var6);
         }
      }
   }

   public void updateMapData(World var1, Entity var2, MapData var3) {
      if (var1.t.getDimensionId() == var3.dimension && var2 instanceof EntityPlayer) {
         int var4 = 1 << var3.scale;
         int var5 = var3.xCenter;
         int var6 = var3.zCenter;
         int var7 = MathHelper.floor_double(var2.s - var5) / var4 + 64;
         int var8 = MathHelper.floor_double(var2.u - var6) / var4 + 64;
         int var9 = 128 / var4;
         if (var1.t.getHasNoSky()) {
            var9 /= 2;
         }

         MapData.MapInfo var10 = var3.getMapInfo((EntityPlayer)var2);
         var10.recoveredField2592++;
         boolean var11 = false;

         for (int var12 = var7 - var9 + 1; var12 < var7 + var9; var12++) {
            if ((var12 & 15) == (var10.recoveredField2592 & 15) || var11) {
               var11 = false;
               double var13 = 0.0;

               for (int var15 = var8 - var9 - 1; var15 < var8 + var9; var15++) {
                  if (var12 >= 0 && var15 >= -1 && var12 < 128 && var15 < 128) {
                     int var16 = var12 - var7;
                     int var17 = var15 - var8;
                     boolean var18 = var16 * var16 + var17 * var17 > (var9 - 2) * (var9 - 2);
                     int var19 = (var5 / var4 + var12 - 64) * var4;
                     int var20 = (var6 / var4 + var15 - 64) * var4;
                     HashMultiset var21 = HashMultiset.create();
                     Chunk var22 = var1.getChunkFromBlockCoords(new BlockPos(var19, 0, var20));
                     if (!var22.isEmpty()) {
                        int var23 = var19 & 15;
                        int var24 = var20 & 15;
                        int var25 = 0;
                        double var26 = 0.0;
                        if (var1.t.getHasNoSky()) {
                           int var28 = var19 + var20 * 231871;
                           var28 = var28 * var28 * 31287121 + var28 * 11;
                           if ((var28 >> 20 & 1) == 0) {
                              var21.add(Blocks.dirt.getMapColor(Blocks.dirt.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.DIRT)), 10);
                           } else {
                              var21.add(
                                 Blocks.stone.getMapColor(Blocks.stone.getDefaultState().withProperty(BlockStone.VARIANT, BlockStone.EnumType.STONE)), 100
                              );
                           }

                           var26 = 100.0;
                        } else {
                           BlockPos.MutableBlockPos var37 = new BlockPos.MutableBlockPos();

                           for (int var29 = 0; var29 < var4; var29++) {
                              for (int var30 = 0; var30 < var4; var30++) {
                                 int var31 = var22.getHeightValue(var29 + var23, var30 + var24) + 1;
                                 IBlockState var32 = Blocks.air.getDefaultState();
                                 if (var31 > 1) {
                                    do {
                                       var32 = var22.getBlockState(var37.set(var29 + var23, --var31, var30 + var24));
                                    } while (var32.getBlock().getMapColor(var32) == MapColor.airColor && var31 > 0);

                                    if (var31 > 0 && var32.getBlock().getMaterial().isLiquid()) {
                                       int var33 = var31 - 1;

                                       Block var34;
                                       do {
                                          var34 = var22.getBlock(var29 + var23, var33--, var30 + var24);
                                          var25++;
                                       } while (var33 > 0 && var34.getMaterial().isLiquid());
                                    }
                                 }

                                 var26 += (double)var31 / (var4 * var4);
                                 var21.add(var32.getBlock().getMapColor(var32));
                              }
                           }
                        }

                        var25 /= var4 * var4;
                        double var38 = (var26 - var13) * 4.0 / (var4 + 4) + ((var12 + var15 & 1) - 0.5) * 0.4;
                        byte var40 = 1;
                        if (var38 > 0.6) {
                           var40 = 2;
                        }

                        if (var38 < -0.6) {
                           var40 = 0;
                        }

                        MapColor var41 = Iterables.getFirst(Multisets.copyHighestCountFirst(var21), MapColor.airColor);
                        if (var41 == MapColor.waterColor) {
                           var38 = var25 * 0.1 + (var12 + var15 & 1) * 0.2;
                           var40 = 1;
                           if (var38 < 0.5) {
                              var40 = 2;
                           }

                           if (var38 > 0.9) {
                              var40 = 0;
                           }
                        }

                        var13 = var26;
                        if (var15 >= 0 && var16 * var16 + var17 * var17 < var9 * var9 && (!var18 || (var12 + var15 & 1) != 0)) {
                           byte var42 = var3.colors[var12 + var15 * 128];
                           byte var43 = (byte)(var41.colorIndex * 4 + var40);
                           if (var42 != var43) {
                              var3.colors[var12 + var15 * 128] = var43;
                              var3.updateMapData(var12, var15);
                              var11 = true;
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }

   @Override
   public Packet createMapDataPacket(ItemStack var1, World var2, EntityPlayer var3) {
      return this.getMapData(var1, var2).getMapPacket(var1, var2, var3);
   }

   @Override
   public void onCreated(ItemStack var1, World var2, EntityPlayer var3) {
      if (var1.hasTagCompound() && var1.getTagCompound().getBoolean("map_is_scaling")) {
         MapData var4 = Items.filled_map.getMapData(var1, var2);
         var1.setItemDamage(var2.getUniqueDataId("map"));
         MapData var5 = new MapData("map_" + var1.getMetadata());
         var5.scale = (byte)(var4.scale + 1);
         if (var5.scale > 4) {
            var5.scale = 4;
         }

         var5.calculateMapCenter(var4.xCenter, var4.zCenter, var5.scale);
         var5.dimension = var4.dimension;
         var5.markDirty();
         var2.setItemData("map_" + var1.getMetadata(), var5);
      }
   }
}
