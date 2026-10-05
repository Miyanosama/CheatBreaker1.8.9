package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityMinecartChest;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;

public class StructureMineshaftPieces {
   public static List<WeightedRandomChestContent> CHEST_CONTENT_WEIGHT_LIST = Lists.newArrayList(
      new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 10),
      new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 5),
      new WeightedRandomChestContent(Items.redstone, 0, 4, 9, 5),
      new WeightedRandomChestContent(Items.dye, EnumDyeColor.BLUE.getDyeDamage(), 4, 9, 5),
      new WeightedRandomChestContent(Items.diamond, 0, 1, 2, 3),
      new WeightedRandomChestContent(Items.coal, 0, 3, 8, 10),
      new WeightedRandomChestContent(Items.bread, 0, 1, 3, 15),
      new WeightedRandomChestContent(Items.iron_pickaxe, 0, 1, 1, 1),
      new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.rail), 0, 4, 8, 1),
      new WeightedRandomChestContent(Items.melon_seeds, 0, 2, 4, 10),
      new WeightedRandomChestContent(Items.pumpkin_seeds, 0, 2, 4, 10),
      new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 3),
      new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 1)
   );

   public static void registerStructurePieces() {
      MapGenStructureIO.registerStructureComponent(StructureMineshaftPieces.Corridor.class, "MSCorridor");
      MapGenStructureIO.registerStructureComponent(StructureMineshaftPieces.Cross.class, "MSCrossing");
      MapGenStructureIO.registerStructureComponent(StructureMineshaftPieces.Room.class, "MSRoom");
      MapGenStructureIO.registerStructureComponent(StructureMineshaftPieces.Stairs.class, "MSStairs");
   }

   public static StructureComponent func_175892_a(List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5, int var6) {
      int var7 = var1.nextInt(100);
      if (var7 >= 80) {
         StructureBoundingBox var8 = StructureMineshaftPieces.Cross.func_175813_a(var0, var1, var2, var3, var4, var5);
         if (var8 != null) {
            return new StructureMineshaftPieces.Cross(var6, var1, var8, var5);
         }
      } else if (var7 >= 70) {
         StructureBoundingBox var9 = StructureMineshaftPieces.Stairs.func_175812_a(var0, var1, var2, var3, var4, var5);
         if (var9 != null) {
            return new StructureMineshaftPieces.Stairs(var6, var1, var9, var5);
         }
      } else {
         StructureBoundingBox var10 = StructureMineshaftPieces.Corridor.func_175814_a(var0, var1, var2, var3, var4, var5);
         if (var10 != null) {
            return new StructureMineshaftPieces.Corridor(var6, var1, var10, var5);
         }
      }

      return null;
   }

   public static StructureComponent func_175890_b(
      StructureComponent var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
   ) {
      if (var7 > 8) {
         return null;
      } else if (Math.abs(var3 - var0.getBoundingBox().minX) <= 80 && Math.abs(var5 - var0.getBoundingBox().minZ) <= 80) {
         StructureComponent var8 = func_175892_a(var1, var2, var3, var4, var5, var6, var7 + 1);
         if (var8 != null) {
            var1.add(var8);
            var8.buildComponent(var0, var1, var2);
         }

         return var8;
      } else {
         return null;
      }
   }

   public static class Corridor extends StructureComponent {
      public boolean hasRails;
      public int sectionCount;
      public boolean hasSpiders;
      public boolean spawnerPlaced;

      @Override
      public boolean generateChestContents(
         World var1, StructureBoundingBox var2, Random var3, int var4, int var5, int var6, List<WeightedRandomChestContent> var7, int var8
      ) {
         BlockPos var9 = new BlockPos(this.a(var4, var6), this.d(var5), this.b(var4, var6));
         if (var2.isVecInside(var9) && var1.getBlockState(var9).getBlock().getMaterial() == Material.air) {
            int var10 = var3.nextBoolean() ? 1 : 0;
            var1.a(var9, Blocks.rail.getStateFromMeta(this.a(Blocks.rail, var10)), 2);
            EntityMinecartChest var11 = new EntityMinecartChest(var1, var9.getX() + 0.5F, var9.getY() + 0.5F, var9.getZ() + 0.5F);
            WeightedRandomChestContent.generateChestContents(var3, var7, var11, var8);
            var1.spawnEntityInWorld(var11);
            return true;
         } else {
            return false;
         }
      }

      @Override
      public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
         int var4 = this.getComponentType();
         int var5 = var3.nextInt(4);
         if (this.m != null) {
            switch (this.m) {
               case NORTH:
                  if (var5 <= 1) {
                     StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.minX, this.l.minY - 1 + var3.nextInt(3), this.l.minZ - 1, this.m, var4);
                  } else if (var5 == 2) {
                     StructureMineshaftPieces.func_175890_b(
                        var1, var2, var3, this.l.minX - 1, this.l.minY - 1 + var3.nextInt(3), this.l.minZ, EnumFacing.WEST, var4
                     );
                  } else {
                     StructureMineshaftPieces.func_175890_b(
                        var1, var2, var3, this.l.maxX + 1, this.l.minY - 1 + var3.nextInt(3), this.l.minZ, EnumFacing.EAST, var4
                     );
                  }
                  break;
               case SOUTH:
                  if (var5 <= 1) {
                     StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.minX, this.l.minY - 1 + var3.nextInt(3), this.l.maxZ + 1, this.m, var4);
                  } else if (var5 == 2) {
                     StructureMineshaftPieces.func_175890_b(
                        var1, var2, var3, this.l.minX - 1, this.l.minY - 1 + var3.nextInt(3), this.l.maxZ - 3, EnumFacing.WEST, var4
                     );
                  } else {
                     StructureMineshaftPieces.func_175890_b(
                        var1, var2, var3, this.l.maxX + 1, this.l.minY - 1 + var3.nextInt(3), this.l.maxZ - 3, EnumFacing.EAST, var4
                     );
                  }
                  break;
               case WEST:
                  if (var5 <= 1) {
                     StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.minX - 1, this.l.minY - 1 + var3.nextInt(3), this.l.minZ, this.m, var4);
                  } else if (var5 == 2) {
                     StructureMineshaftPieces.func_175890_b(
                        var1, var2, var3, this.l.minX, this.l.minY - 1 + var3.nextInt(3), this.l.minZ - 1, EnumFacing.NORTH, var4
                     );
                  } else {
                     StructureMineshaftPieces.func_175890_b(
                        var1, var2, var3, this.l.minX, this.l.minY - 1 + var3.nextInt(3), this.l.maxZ + 1, EnumFacing.SOUTH, var4
                     );
                  }
                  break;
               case EAST:
                  if (var5 <= 1) {
                     StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.maxX + 1, this.l.minY - 1 + var3.nextInt(3), this.l.minZ, this.m, var4);
                  } else if (var5 == 2) {
                     StructureMineshaftPieces.func_175890_b(
                        var1, var2, var3, this.l.maxX - 3, this.l.minY - 1 + var3.nextInt(3), this.l.minZ - 1, EnumFacing.NORTH, var4
                     );
                  } else {
                     StructureMineshaftPieces.func_175890_b(
                        var1, var2, var3, this.l.maxX - 3, this.l.minY - 1 + var3.nextInt(3), this.l.maxZ + 1, EnumFacing.SOUTH, var4
                     );
                  }
            }
         }

         if (var4 < 8) {
            if (this.m != EnumFacing.NORTH && this.m != EnumFacing.SOUTH) {
               for (int var8 = this.l.minX + 3; var8 + 3 <= this.l.maxX; var8 += 5) {
                  int var9 = var3.nextInt(5);
                  if (var9 == 0) {
                     StructureMineshaftPieces.func_175890_b(var1, var2, var3, var8, this.l.minY, this.l.minZ - 1, EnumFacing.NORTH, var4 + 1);
                  } else if (var9 == 1) {
                     StructureMineshaftPieces.func_175890_b(var1, var2, var3, var8, this.l.minY, this.l.maxZ + 1, EnumFacing.SOUTH, var4 + 1);
                  }
               }
            } else {
               for (int var6 = this.l.minZ + 3; var6 + 3 <= this.l.maxZ; var6 += 5) {
                  int var7 = var3.nextInt(5);
                  if (var7 == 0) {
                     StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.minX - 1, this.l.minY, var6, EnumFacing.WEST, var4 + 1);
                  } else if (var7 == 1) {
                     StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.maxX + 1, this.l.minY, var6, EnumFacing.EAST, var4 + 1);
                  }
               }
            }
         }
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         if (this.a(var1, var3)) {
            return false;
         } else {
            boolean var4 = false;
            byte var5 = 2;
            boolean var6 = false;
            byte var7 = 2;
            int var8 = this.sectionCount * 5 - 1;
            this.a(var1, var3, 0, 0, 0, 2, 1, var8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.a(var1, var3, var2, 0.8F, 0, 2, 0, 2, 2, var8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            if (this.hasSpiders) {
               this.a(var1, var3, var2, 0.6F, 0, 0, 0, 2, 1, var8, Blocks.web.getDefaultState(), Blocks.air.getDefaultState(), false);
            }

            for (int var9 = 0; var9 < this.sectionCount; var9++) {
               int var10 = 2 + var9 * 5;
               this.a(var1, var3, 0, 0, var10, 0, 1, var10, Blocks.oak_fence.getDefaultState(), Blocks.air.getDefaultState(), false);
               this.a(var1, var3, 2, 0, var10, 2, 1, var10, Blocks.oak_fence.getDefaultState(), Blocks.air.getDefaultState(), false);
               if (var2.nextInt(4) == 0) {
                  this.a(var1, var3, 0, 2, var10, 0, 2, var10, Blocks.planks.getDefaultState(), Blocks.air.getDefaultState(), false);
                  this.a(var1, var3, 2, 2, var10, 2, 2, var10, Blocks.planks.getDefaultState(), Blocks.air.getDefaultState(), false);
               } else {
                  this.a(var1, var3, 0, 2, var10, 2, 2, var10, Blocks.planks.getDefaultState(), Blocks.air.getDefaultState(), false);
               }

               this.randomlyPlaceBlock(var1, var3, var2, 0.1F, 0, 2, var10 - 1, Blocks.web.getDefaultState());
               this.randomlyPlaceBlock(var1, var3, var2, 0.1F, 2, 2, var10 - 1, Blocks.web.getDefaultState());
               this.randomlyPlaceBlock(var1, var3, var2, 0.1F, 0, 2, var10 + 1, Blocks.web.getDefaultState());
               this.randomlyPlaceBlock(var1, var3, var2, 0.1F, 2, 2, var10 + 1, Blocks.web.getDefaultState());
               this.randomlyPlaceBlock(var1, var3, var2, 0.05F, 0, 2, var10 - 2, Blocks.web.getDefaultState());
               this.randomlyPlaceBlock(var1, var3, var2, 0.05F, 2, 2, var10 - 2, Blocks.web.getDefaultState());
               this.randomlyPlaceBlock(var1, var3, var2, 0.05F, 0, 2, var10 + 2, Blocks.web.getDefaultState());
               this.randomlyPlaceBlock(var1, var3, var2, 0.05F, 2, 2, var10 + 2, Blocks.web.getDefaultState());
               this.randomlyPlaceBlock(var1, var3, var2, 0.05F, 1, 2, var10 - 1, Blocks.torch.getStateFromMeta(EnumFacing.UP.getIndex()));
               this.randomlyPlaceBlock(var1, var3, var2, 0.05F, 1, 2, var10 + 1, Blocks.torch.getStateFromMeta(EnumFacing.UP.getIndex()));
               if (var2.nextInt(100) == 0) {
                  this.generateChestContents(
                     var1,
                     var3,
                     var2,
                     2,
                     0,
                     var10 - 1,
                     WeightedRandomChestContent.func_177629_a(StructureMineshaftPieces.CHEST_CONTENT_WEIGHT_LIST, Items.enchanted_book.getRandom(var2)),
                     3 + var2.nextInt(4)
                  );
               }

               if (var2.nextInt(100) == 0) {
                  this.generateChestContents(
                     var1,
                     var3,
                     var2,
                     0,
                     0,
                     var10 + 1,
                     WeightedRandomChestContent.func_177629_a(StructureMineshaftPieces.CHEST_CONTENT_WEIGHT_LIST, Items.enchanted_book.getRandom(var2)),
                     3 + var2.nextInt(4)
                  );
               }

               if (this.hasSpiders && !this.spawnerPlaced) {
                  int var11 = this.d(0);
                  int var12 = var10 - 1 + var2.nextInt(3);
                  int var13 = this.a(1, var12);
                  var12 = this.b(1, var12);
                  BlockPos var14 = new BlockPos(var13, var11, var12);
                  if (var3.isVecInside(var14)) {
                     this.spawnerPlaced = true;
                     var1.a(var14, Blocks.mob_spawner.getDefaultState(), 2);
                     TileEntity var15 = var1.getTileEntity(var14);
                     if (var15 instanceof TileEntityMobSpawner) {
                        ((TileEntityMobSpawner)var15).getSpawnerBaseLogic().setEntityName("CaveSpider");
                     }
                  }
               }
            }

            for (int var16 = 0; var16 <= 2; var16++) {
               for (int var18 = 0; var18 <= var8; var18++) {
                  byte var20 = -1;
                  IBlockState var22 = this.a(var1, var16, var20, var18, var3);
                  if (var22.getBlock().getMaterial() == Material.air) {
                     byte var23 = -1;
                     this.a(var1, Blocks.planks.getDefaultState(), var16, var23, var18, var3);
                  }
               }
            }

            if (this.hasRails) {
               for (int var17 = 0; var17 <= var8; var17++) {
                  IBlockState var19 = this.a(var1, 1, -1, var17, var3);
                  if (var19.getBlock().getMaterial() != Material.air && var19.getBlock().isFullBlock()) {
                     this.randomlyPlaceBlock(var1, var3, var2, 0.7F, 1, 0, var17, Blocks.rail.getStateFromMeta(this.a(Blocks.rail, 0)));
                  }
               }
            }

            return true;
         }
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound var1) {
         var1.setBoolean("hr", this.hasRails);
         var1.setBoolean("sc", this.hasSpiders);
         var1.setBoolean("hps", this.spawnerPlaced);
         var1.setInteger("Num", this.sectionCount);
      }

      public Corridor() {
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound var1) {
         this.hasRails = var1.getBoolean("hr");
         this.hasSpiders = var1.getBoolean("sc");
         this.spawnerPlaced = var1.getBoolean("hps");
         this.sectionCount = var1.getInteger("Num");
      }

      public static StructureBoundingBox func_175814_a(List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5) {
         StructureBoundingBox var6 = new StructureBoundingBox(var2, var3, var4, var2, var3 + 2, var4);

         int var7;
         for (var7 = var1.nextInt(3) + 2; var7 > 0; var7--) {
            int var8 = var7 * 5;
            switch (var5) {
               case NORTH:
                  var6.maxX = var2 + 2;
                  var6.minZ = var4 - (var8 - 1);
                  break;
               case SOUTH:
                  var6.maxX = var2 + 2;
                  var6.maxZ = var4 + (var8 - 1);
                  break;
               case WEST:
                  var6.minX = var2 - (var8 - 1);
                  var6.maxZ = var4 + 2;
                  break;
               case EAST:
                  var6.maxX = var2 + (var8 - 1);
                  var6.maxZ = var4 + 2;
            }

            if (StructureComponent.findIntersecting(var0, var6) == null) {
               break;
            }
         }

         return var7 > 0 ? var6 : null;
      }

      public Corridor(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
         super(var1);
         this.m = var4;
         this.l = var3;
         this.hasRails = var2.nextInt(3) == 0;
         this.hasSpiders = !this.hasRails && var2.nextInt(23) == 0;
         if (this.m != EnumFacing.NORTH && this.m != EnumFacing.SOUTH) {
            this.sectionCount = var3.getXSize() / 5;
         } else {
            this.sectionCount = var3.getZSize() / 5;
         }
      }
   }

   public static class Cross extends StructureComponent {
      public boolean isMultipleFloors;
      public EnumFacing corridorDirection;

      public static StructureBoundingBox func_175813_a(List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5) {
         StructureBoundingBox var6 = new StructureBoundingBox(var2, var3, var4, var2, var3 + 2, var4);
         if (var1.nextInt(4) == 0) {
            var6.maxY += 4;
         }

         switch (var5) {
            case NORTH:
               var6.minX = var2 - 1;
               var6.maxX = var2 + 3;
               var6.minZ = var4 - 4;
               break;
            case SOUTH:
               var6.minX = var2 - 1;
               var6.maxX = var2 + 3;
               var6.maxZ = var4 + 4;
               break;
            case WEST:
               var6.minX = var2 - 4;
               var6.minZ = var4 - 1;
               var6.maxZ = var4 + 3;
               break;
            case EAST:
               var6.maxX = var2 + 4;
               var6.minZ = var4 - 1;
               var6.maxZ = var4 + 3;
         }

         return StructureComponent.findIntersecting(var0, var6) != null ? null : var6;
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound var1) {
         var1.setBoolean("tf", this.isMultipleFloors);
         var1.setInteger("D", this.corridorDirection.getHorizontalIndex());
      }

      public Cross() {
      }

      @Override
      public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
         int var4 = this.getComponentType();
         switch (this.corridorDirection) {
            case NORTH:
               StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.minX + 1, this.l.minY, this.l.minZ - 1, EnumFacing.NORTH, var4);
               StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.minX - 1, this.l.minY, this.l.minZ + 1, EnumFacing.WEST, var4);
               StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.maxX + 1, this.l.minY, this.l.minZ + 1, EnumFacing.EAST, var4);
               break;
            case SOUTH:
               StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.minX + 1, this.l.minY, this.l.maxZ + 1, EnumFacing.SOUTH, var4);
               StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.minX - 1, this.l.minY, this.l.minZ + 1, EnumFacing.WEST, var4);
               StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.maxX + 1, this.l.minY, this.l.minZ + 1, EnumFacing.EAST, var4);
               break;
            case WEST:
               StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.minX + 1, this.l.minY, this.l.minZ - 1, EnumFacing.NORTH, var4);
               StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.minX + 1, this.l.minY, this.l.maxZ + 1, EnumFacing.SOUTH, var4);
               StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.minX - 1, this.l.minY, this.l.minZ + 1, EnumFacing.WEST, var4);
               break;
            case EAST:
               StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.minX + 1, this.l.minY, this.l.minZ - 1, EnumFacing.NORTH, var4);
               StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.minX + 1, this.l.minY, this.l.maxZ + 1, EnumFacing.SOUTH, var4);
               StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.maxX + 1, this.l.minY, this.l.minZ + 1, EnumFacing.EAST, var4);
         }

         if (this.isMultipleFloors) {
            if (var3.nextBoolean()) {
               StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.minX + 1, this.l.minY + 3 + 1, this.l.minZ - 1, EnumFacing.NORTH, var4);
            }

            if (var3.nextBoolean()) {
               StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.minX - 1, this.l.minY + 3 + 1, this.l.minZ + 1, EnumFacing.WEST, var4);
            }

            if (var3.nextBoolean()) {
               StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.maxX + 1, this.l.minY + 3 + 1, this.l.minZ + 1, EnumFacing.EAST, var4);
            }

            if (var3.nextBoolean()) {
               StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.minX + 1, this.l.minY + 3 + 1, this.l.maxZ + 1, EnumFacing.SOUTH, var4);
            }
         }
      }

      public Cross(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
         super(var1);
         this.corridorDirection = var4;
         this.l = var3;
         this.isMultipleFloors = var3.getYSize() > 3;
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound var1) {
         this.isMultipleFloors = var1.getBoolean("tf");
         this.corridorDirection = EnumFacing.getHorizontal(var1.getInteger("D"));
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         if (this.a(var1, var3)) {
            return false;
         } else {
            if (this.isMultipleFloors) {
               this.a(
                  var1,
                  var3,
                  this.l.minX + 1,
                  this.l.minY,
                  this.l.minZ,
                  this.l.maxX - 1,
                  this.l.minY + 3 - 1,
                  this.l.maxZ,
                  Blocks.air.getDefaultState(),
                  Blocks.air.getDefaultState(),
                  false
               );
               this.a(
                  var1,
                  var3,
                  this.l.minX,
                  this.l.minY,
                  this.l.minZ + 1,
                  this.l.maxX,
                  this.l.minY + 3 - 1,
                  this.l.maxZ - 1,
                  Blocks.air.getDefaultState(),
                  Blocks.air.getDefaultState(),
                  false
               );
               this.a(
                  var1,
                  var3,
                  this.l.minX + 1,
                  this.l.maxY - 2,
                  this.l.minZ,
                  this.l.maxX - 1,
                  this.l.maxY,
                  this.l.maxZ,
                  Blocks.air.getDefaultState(),
                  Blocks.air.getDefaultState(),
                  false
               );
               this.a(
                  var1,
                  var3,
                  this.l.minX,
                  this.l.maxY - 2,
                  this.l.minZ + 1,
                  this.l.maxX,
                  this.l.maxY,
                  this.l.maxZ - 1,
                  Blocks.air.getDefaultState(),
                  Blocks.air.getDefaultState(),
                  false
               );
               this.a(
                  var1,
                  var3,
                  this.l.minX + 1,
                  this.l.minY + 3,
                  this.l.minZ + 1,
                  this.l.maxX - 1,
                  this.l.minY + 3,
                  this.l.maxZ - 1,
                  Blocks.air.getDefaultState(),
                  Blocks.air.getDefaultState(),
                  false
               );
            } else {
               this.a(
                  var1,
                  var3,
                  this.l.minX + 1,
                  this.l.minY,
                  this.l.minZ,
                  this.l.maxX - 1,
                  this.l.maxY,
                  this.l.maxZ,
                  Blocks.air.getDefaultState(),
                  Blocks.air.getDefaultState(),
                  false
               );
               this.a(
                  var1,
                  var3,
                  this.l.minX,
                  this.l.minY,
                  this.l.minZ + 1,
                  this.l.maxX,
                  this.l.maxY,
                  this.l.maxZ - 1,
                  Blocks.air.getDefaultState(),
                  Blocks.air.getDefaultState(),
                  false
               );
            }

            this.a(
               var1,
               var3,
               this.l.minX + 1,
               this.l.minY,
               this.l.minZ + 1,
               this.l.minX + 1,
               this.l.maxY,
               this.l.minZ + 1,
               Blocks.planks.getDefaultState(),
               Blocks.air.getDefaultState(),
               false
            );
            this.a(
               var1,
               var3,
               this.l.minX + 1,
               this.l.minY,
               this.l.maxZ - 1,
               this.l.minX + 1,
               this.l.maxY,
               this.l.maxZ - 1,
               Blocks.planks.getDefaultState(),
               Blocks.air.getDefaultState(),
               false
            );
            this.a(
               var1,
               var3,
               this.l.maxX - 1,
               this.l.minY,
               this.l.minZ + 1,
               this.l.maxX - 1,
               this.l.maxY,
               this.l.minZ + 1,
               Blocks.planks.getDefaultState(),
               Blocks.air.getDefaultState(),
               false
            );
            this.a(
               var1,
               var3,
               this.l.maxX - 1,
               this.l.minY,
               this.l.maxZ - 1,
               this.l.maxX - 1,
               this.l.maxY,
               this.l.maxZ - 1,
               Blocks.planks.getDefaultState(),
               Blocks.air.getDefaultState(),
               false
            );

            for (int var4 = this.l.minX; var4 <= this.l.maxX; var4++) {
               for (int var5 = this.l.minZ; var5 <= this.l.maxZ; var5++) {
                  if (this.a(var1, var4, this.l.minY - 1, var5, var3).getBlock().getMaterial() == Material.air) {
                     this.a(var1, Blocks.planks.getDefaultState(), var4, this.l.minY - 1, var5, var3);
                  }
               }
            }

            return true;
         }
      }
   }

   public static class Room extends StructureComponent {
      public List<StructureBoundingBox> roomsLinkedToTheRoom = Lists.newLinkedList();

      public Room(int var1, Random var2, int var3, int var4) {
         super(var1);
         this.l = new StructureBoundingBox(var3, 50, var4, var3 + 7 + var2.nextInt(6), 54 + var2.nextInt(6), var4 + 7 + var2.nextInt(6));
      }

      @Override
      public void func_181138_a(int var1, int var2, int var3) {
         super.func_181138_a(var1, var2, var3);

         for (StructureBoundingBox var5 : this.roomsLinkedToTheRoom) {
            var5.offset(var1, var2, var3);
         }
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         if (this.a(var1, var3)) {
            return false;
         } else {
            this.a(
               var1,
               var3,
               this.l.minX,
               this.l.minY,
               this.l.minZ,
               this.l.maxX,
               this.l.minY,
               this.l.maxZ,
               Blocks.dirt.getDefaultState(),
               Blocks.air.getDefaultState(),
               true
            );
            this.a(
               var1,
               var3,
               this.l.minX,
               this.l.minY + 1,
               this.l.minZ,
               this.l.maxX,
               Math.min(this.l.minY + 3, this.l.maxY),
               this.l.maxZ,
               Blocks.air.getDefaultState(),
               Blocks.air.getDefaultState(),
               false
            );

            for (StructureBoundingBox var5 : this.roomsLinkedToTheRoom) {
               this.a(
                  var1,
                  var3,
                  var5.minX,
                  var5.maxY - 2,
                  var5.minZ,
                  var5.maxX,
                  var5.maxY,
                  var5.maxZ,
                  Blocks.air.getDefaultState(),
                  Blocks.air.getDefaultState(),
                  false
               );
            }

            this.randomlyRareFillWithBlocks(
               var1, var3, this.l.minX, this.l.minY + 4, this.l.minZ, this.l.maxX, this.l.maxY, this.l.maxZ, Blocks.air.getDefaultState(), false
            );
            return true;
         }
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound var1) {
         NBTTagList var2 = var1.getTagList("Entrances", 11);

         for (int var3 = 0; var3 < var2.tagCount(); var3++) {
            this.roomsLinkedToTheRoom.add(new StructureBoundingBox(var2.getIntArrayAt(var3)));
         }
      }

      @Override
      public void writeStructureToNBT(NBTTagCompound var1) {
         NBTTagList var2 = new NBTTagList();

         for (StructureBoundingBox var4 : this.roomsLinkedToTheRoom) {
            var2.appendTag(var4.toNBTTagIntArray());
         }

         var1.setTag("Entrances", var2);
      }

      public Room() {
      }

      @Override
      public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
         int var4 = this.getComponentType();
         int var5 = this.l.getYSize() - 3 - 1;
         if (var5 <= 0) {
            var5 = 1;
         }

         int var6 = 0;

         while (var6 < this.l.getXSize()) {
            var6 += var3.nextInt(this.l.getXSize());
            if (var6 + 3 > this.l.getXSize()) {
               break;
            }

            StructureComponent var7 = StructureMineshaftPieces.func_175890_b(
               var1, var2, var3, this.l.minX + var6, this.l.minY + var3.nextInt(var5) + 1, this.l.minZ - 1, EnumFacing.NORTH, var4
            );
            if (var7 != null) {
               StructureBoundingBox var8 = var7.getBoundingBox();
               this.roomsLinkedToTheRoom.add(new StructureBoundingBox(var8.minX, var8.minY, this.l.minZ, var8.maxX, var8.maxY, this.l.minZ + 1));
            }

            var6 += 4;
         }

         var6 = 0;

         while (var6 < this.l.getXSize()) {
            var6 += var3.nextInt(this.l.getXSize());
            if (var6 + 3 > this.l.getXSize()) {
               break;
            }

            StructureComponent var16 = StructureMineshaftPieces.func_175890_b(
               var1, var2, var3, this.l.minX + var6, this.l.minY + var3.nextInt(var5) + 1, this.l.maxZ + 1, EnumFacing.SOUTH, var4
            );
            if (var16 != null) {
               StructureBoundingBox var19 = var16.getBoundingBox();
               this.roomsLinkedToTheRoom.add(new StructureBoundingBox(var19.minX, var19.minY, this.l.maxZ - 1, var19.maxX, var19.maxY, this.l.maxZ));
            }

            var6 += 4;
         }

         var6 = 0;

         while (var6 < this.l.getZSize()) {
            var6 += var3.nextInt(this.l.getZSize());
            if (var6 + 3 > this.l.getZSize()) {
               break;
            }

            StructureComponent var17 = StructureMineshaftPieces.func_175890_b(
               var1, var2, var3, this.l.minX - 1, this.l.minY + var3.nextInt(var5) + 1, this.l.minZ + var6, EnumFacing.WEST, var4
            );
            if (var17 != null) {
               StructureBoundingBox var20 = var17.getBoundingBox();
               this.roomsLinkedToTheRoom.add(new StructureBoundingBox(this.l.minX, var20.minY, var20.minZ, this.l.minX + 1, var20.maxY, var20.maxZ));
            }

            var6 += 4;
         }

         var6 = 0;

         while (var6 < this.l.getZSize()) {
            var6 += var3.nextInt(this.l.getZSize());
            if (var6 + 3 > this.l.getZSize()) {
               break;
            }

            StructureComponent var18 = StructureMineshaftPieces.func_175890_b(
               var1, var2, var3, this.l.maxX + 1, this.l.minY + var3.nextInt(var5) + 1, this.l.minZ + var6, EnumFacing.EAST, var4
            );
            if (var18 != null) {
               StructureBoundingBox var21 = var18.getBoundingBox();
               this.roomsLinkedToTheRoom.add(new StructureBoundingBox(this.l.maxX - 1, var21.minY, var21.minZ, this.l.maxX, var21.maxY, var21.maxZ));
            }

            var6 += 4;
         }
      }
   }

   public static class Stairs extends StructureComponent {
      @Override
      public void writeStructureToNBT(NBTTagCompound var1) {
      }

      public static StructureBoundingBox func_175812_a(List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5) {
         StructureBoundingBox var6 = new StructureBoundingBox(var2, var3 - 5, var4, var2, var3 + 2, var4);
         switch (var5) {
            case NORTH:
               var6.maxX = var2 + 2;
               var6.minZ = var4 - 8;
               break;
            case SOUTH:
               var6.maxX = var2 + 2;
               var6.maxZ = var4 + 8;
               break;
            case WEST:
               var6.minX = var2 - 8;
               var6.maxZ = var4 + 2;
               break;
            case EAST:
               var6.maxX = var2 + 8;
               var6.maxZ = var4 + 2;
         }

         return StructureComponent.findIntersecting(var0, var6) != null ? null : var6;
      }

      public Stairs(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
         super(var1);
         this.m = var4;
         this.l = var3;
      }

      @Override
      public void readStructureFromNBT(NBTTagCompound var1) {
      }

      @Override
      public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
         if (this.a(var1, var3)) {
            return false;
         } else {
            this.a(var1, var3, 0, 5, 0, 2, 7, 1, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
            this.a(var1, var3, 0, 0, 7, 2, 2, 8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);

            for (int var4 = 0; var4 < 5; var4++) {
               this.a(
                  var1,
                  var3,
                  0,
                  5 - var4 - (var4 < 4 ? 1 : 0),
                  2 + var4,
                  2,
                  7 - var4,
                  2 + var4,
                  Blocks.air.getDefaultState(),
                  Blocks.air.getDefaultState(),
                  false
               );
            }

            return true;
         }
      }

      @Override
      public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
         int var4 = this.getComponentType();
         if (this.m != null) {
            switch (this.m) {
               case NORTH:
                  StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.minX, this.l.minY, this.l.minZ - 1, EnumFacing.NORTH, var4);
                  break;
               case SOUTH:
                  StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.minX, this.l.minY, this.l.maxZ + 1, EnumFacing.SOUTH, var4);
                  break;
               case WEST:
                  StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.minX - 1, this.l.minY, this.l.minZ, EnumFacing.WEST, var4);
                  break;
               case EAST:
                  StructureMineshaftPieces.func_175890_b(var1, var2, var3, this.l.maxX + 1, this.l.minY, this.l.minZ, EnumFacing.EAST, var4);
            }
         }
      }

      public Stairs() {
      }
   }
}
