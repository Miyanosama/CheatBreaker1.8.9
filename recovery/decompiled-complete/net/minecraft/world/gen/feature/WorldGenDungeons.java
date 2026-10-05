package net.minecraft.world.gen.feature;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.audio.SoundEventAccessor;
import net.minecraft.client.resources.ResourcePackRepository$3;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing$Plane;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorldGenDungeons extends WorldGenerator {
   public static Logger field_175918_a = LogManager.getLogger();
   public static List<WeightedRandomChestContent> CHESTCONTENT = Lists.newArrayList(
      new WeightedRandomChestContent[]{
         new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 10),
         new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 4, 10),
         new WeightedRandomChestContent(Items.bread, 0, 1, 1, 10),
         new WeightedRandomChestContent(Items.wheat, 0, 1, 4, 10),
         new WeightedRandomChestContent(Items.gunpowder, 0, 1, 4, 10),
         new WeightedRandomChestContent(Items.string, 0, 1, 4, 10),
         new WeightedRandomChestContent(Items.bucket, 0, 1, 1, 10),
         new WeightedRandomChestContent(Items.golden_apple, 0, 1, 1, 1),
         new WeightedRandomChestContent(Items.redstone, 0, 1, 4, 10),
         new WeightedRandomChestContent(Items.record_13, 0, 1, 1, 4),
         new WeightedRandomChestContent(Items.record_cat, 0, 1, 1, 4),
         new WeightedRandomChestContent(Items.name_tag, 0, 1, 1, 10),
         new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 2),
         new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 5),
         new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 1)
      }
   );
   public ResourcePackRepository$3 field_0001;
   public SoundEventAccessor field_0003;
   public static String[] SPAWNERTYPES = new String[]{"Skeleton", "Zombie", "Zombie", "Spider"};

   public String pickMobSpawner(Random var1) {
      return SPAWNERTYPES[var1.nextInt(SPAWNERTYPES.length)];
   }

   @Override
   public boolean generate(World var1, Random var2, BlockPos var3) {
      byte var4 = 3;
      int var5 = var2.nextInt(2) + 2;
      int var6 = -var5 - 1;
      int var7 = var5 + 1;
      byte var8 = -1;
      byte var9 = 4;
      int var10 = var2.nextInt(2) + 2;
      int var11 = -var10 - 1;
      int var12 = var10 + 1;
      int var13 = 0;

      for (int var14 = var6; var14 <= var7; var14++) {
         for (int var15 = -1; var15 <= 4; var15++) {
            for (int var16 = var11; var16 <= var12; var16++) {
               BlockPos var17 = var3.add(var14, var15, var16);
               Material var18 = var1.getBlockState(var17).getBlock().getMaterial();
               boolean var19 = var18.isSolid();
               if (var15 == -1 && !var19) {
                  return false;
               }

               if (var15 == 4 && !var19) {
                  return false;
               }

               if ((var14 == var6 || var14 == var7 || var16 == var11 || var16 == var12) && var15 == 0 && var1.isAirBlock(var17) && var1.isAirBlock(var17.up())) {
                  var13++;
               }
            }
         }
      }

      if (var13 >= 1 && var13 <= 5) {
         for (int var23 = var6; var23 <= var7; var23++) {
            for (int var26 = 3; var26 >= -1; var26--) {
               for (int var28 = var11; var28 <= var12; var28++) {
                  BlockPos var30 = var3.add(var23, var26, var28);
                  if (var23 != var6 && var26 != -1 && var28 != var11 && var23 != var7 && var26 != 4 && var28 != var12) {
                     if (var1.getBlockState(var30).getBlock() != Blocks.chest) {
                        var1.setBlockToAir(var30);
                     }
                  } else if (var30.getY() >= 0 && !var1.getBlockState(var30.down()).getBlock().getMaterial().isSolid()) {
                     var1.setBlockToAir(var30);
                  } else if (var1.getBlockState(var30).getBlock().getMaterial().isSolid() && var1.getBlockState(var30).getBlock() != Blocks.chest) {
                     if (var26 == -1 && var2.nextInt(4) != 0) {
                        var1.a(var30, Blocks.mossy_cobblestone.getDefaultState(), 2);
                     } else {
                        var1.a(var30, Blocks.cobblestone.getDefaultState(), 2);
                     }
                  }
               }
            }
         }

         for (int var24 = 0; var24 < 2; var24++) {
            for (int var27 = 0; var27 < 3; var27++) {
               int var29 = var3.getX() + var2.nextInt(var5 * 2 + 1) - var5;
               int var31 = var3.getY();
               int var32 = var3.getZ() + var2.nextInt(var10 * 2 + 1) - var10;
               BlockPos var33 = new BlockPos(var29, var31, var32);
               if (var1.isAirBlock(var33)) {
                  int var20 = 0;

                  for (EnumFacing var22 : EnumFacing$Plane.HORIZONTAL) {
                     if (var1.getBlockState(var33.a(var22)).getBlock().getMaterial().isSolid()) {
                        var20++;
                     }
                  }

                  if (var20 == 1) {
                     var1.a(var33, Blocks.chest.correctFacing(var1, var33, Blocks.chest.getDefaultState()), 2);
                     List var34 = WeightedRandomChestContent.func_177629_a(CHESTCONTENT, Items.enchanted_book.getRandom(var2));
                     TileEntity var35 = var1.getTileEntity(var33);
                     if (var35 instanceof TileEntityChest) {
                        WeightedRandomChestContent.generateChestContents(var2, var34, (TileEntityChest)var35, 8);
                     }
                     break;
                  }
               }
            }
         }

         var1.a(var3, Blocks.mob_spawner.getDefaultState(), 2);
         TileEntity var25 = var1.getTileEntity(var3);
         if (var25 instanceof TileEntityMobSpawner) {
            ((TileEntityMobSpawner)var25).getSpawnerBaseLogic().setEntityName(this.pickMobSpawner(var2));
         } else {
            field_175918_a.error("Failed to fetch mob spawner entity at (" + var3.getX() + ", " + var3.getY() + ", " + var3.getZ() + ")");
         }

         return true;
      } else {
         return false;
      }
   }
}
