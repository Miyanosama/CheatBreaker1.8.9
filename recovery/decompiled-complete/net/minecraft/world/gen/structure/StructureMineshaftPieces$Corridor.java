package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import junit.swingui.TestSuitePanel$1;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.gui.GuiScreenServerList;
import net.minecraft.entity.Entity$2;
import net.minecraft.entity.item.EntityMinecartChest;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;
import org.apache.log4j.lf5.viewer.FilteredLogTableModel;

public class StructureMineshaftPieces$Corridor extends StructureComponent {
   public boolean hasRails;
   public Entity$2 field_0006;
   public int sectionCount;
   public TestSuitePanel$1 field_0005;
   public FilteredLogTableModel field_0000;
   public boolean hasSpiders;
   public boolean spawnerPlaced;
   public GuiScreenServerList field_0004;

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
         switch (StructureMineshaftPieces$1.field_175894_a[this.m.ordinal()]) {
            case 1:
               if (var5 <= 1) {
                  StructureMineshaftPieces.access$000(var1, var2, var3, this.l.minX, this.l.minY - 1 + var3.nextInt(3), this.l.minZ - 1, this.m, var4);
               } else if (var5 == 2) {
                  StructureMineshaftPieces.access$000(var1, var2, var3, this.l.minX - 1, this.l.minY - 1 + var3.nextInt(3), this.l.minZ, EnumFacing.WEST, var4);
               } else {
                  StructureMineshaftPieces.access$000(var1, var2, var3, this.l.maxX + 1, this.l.minY - 1 + var3.nextInt(3), this.l.minZ, EnumFacing.EAST, var4);
               }
               break;
            case 2:
               if (var5 <= 1) {
                  StructureMineshaftPieces.access$000(var1, var2, var3, this.l.minX, this.l.minY - 1 + var3.nextInt(3), this.l.maxZ + 1, this.m, var4);
               } else if (var5 == 2) {
                  StructureMineshaftPieces.access$000(
                     var1, var2, var3, this.l.minX - 1, this.l.minY - 1 + var3.nextInt(3), this.l.maxZ - 3, EnumFacing.WEST, var4
                  );
               } else {
                  StructureMineshaftPieces.access$000(
                     var1, var2, var3, this.l.maxX + 1, this.l.minY - 1 + var3.nextInt(3), this.l.maxZ - 3, EnumFacing.EAST, var4
                  );
               }
               break;
            case 3:
               if (var5 <= 1) {
                  StructureMineshaftPieces.access$000(var1, var2, var3, this.l.minX - 1, this.l.minY - 1 + var3.nextInt(3), this.l.minZ, this.m, var4);
               } else if (var5 == 2) {
                  StructureMineshaftPieces.access$000(var1, var2, var3, this.l.minX, this.l.minY - 1 + var3.nextInt(3), this.l.minZ - 1, EnumFacing.NORTH, var4);
               } else {
                  StructureMineshaftPieces.access$000(var1, var2, var3, this.l.minX, this.l.minY - 1 + var3.nextInt(3), this.l.maxZ + 1, EnumFacing.SOUTH, var4);
               }
               break;
            case 4:
               if (var5 <= 1) {
                  StructureMineshaftPieces.access$000(var1, var2, var3, this.l.maxX + 1, this.l.minY - 1 + var3.nextInt(3), this.l.minZ, this.m, var4);
               } else if (var5 == 2) {
                  StructureMineshaftPieces.access$000(
                     var1, var2, var3, this.l.maxX - 3, this.l.minY - 1 + var3.nextInt(3), this.l.minZ - 1, EnumFacing.NORTH, var4
                  );
               } else {
                  StructureMineshaftPieces.access$000(
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
                  StructureMineshaftPieces.access$000(var1, var2, var3, var8, this.l.minY, this.l.minZ - 1, EnumFacing.NORTH, var4 + 1);
               } else if (var9 == 1) {
                  StructureMineshaftPieces.access$000(var1, var2, var3, var8, this.l.minY, this.l.maxZ + 1, EnumFacing.SOUTH, var4 + 1);
               }
            }
         } else {
            for (int var6 = this.l.minZ + 3; var6 + 3 <= this.l.maxZ; var6 += 5) {
               int var7 = var3.nextInt(5);
               if (var7 == 0) {
                  StructureMineshaftPieces.access$000(var1, var2, var3, this.l.minX - 1, this.l.minY, var6, EnumFacing.WEST, var4 + 1);
               } else if (var7 == 1) {
                  StructureMineshaftPieces.access$000(var1, var2, var3, this.l.maxX + 1, this.l.minY, var6, EnumFacing.EAST, var4 + 1);
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
                  WeightedRandomChestContent.func_177629_a(StructureMineshaftPieces.access$100(), Items.enchanted_book.getRandom(var2)),
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
                  WeightedRandomChestContent.func_177629_a(StructureMineshaftPieces.access$100(), Items.enchanted_book.getRandom(var2)),
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

   public StructureMineshaftPieces$Corridor() {
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
         switch (StructureMineshaftPieces$1.field_175894_a[var5.ordinal()]) {
            case 1:
               var6.maxX = var2 + 2;
               var6.minZ = var4 - (var8 - 1);
               break;
            case 2:
               var6.maxX = var2 + 2;
               var6.maxZ = var4 + (var8 - 1);
               break;
            case 3:
               var6.minX = var2 - (var8 - 1);
               var6.maxZ = var4 + 2;
               break;
            case 4:
               var6.maxX = var2 + (var8 - 1);
               var6.maxZ = var4 + 2;
         }

         if (StructureComponent.findIntersecting(var0, var6) == null) {
            break;
         }
      }

      return var7 > 0 ? var6 : null;
   }

   public StructureMineshaftPieces$Corridor(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
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
