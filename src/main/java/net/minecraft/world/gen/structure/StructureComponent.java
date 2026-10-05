package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDirectional;
import net.minecraft.block.BlockDoor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemDoor;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityChest;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraft.world.World;

public abstract class StructureComponent {
   public int componentType;
   public StructureBoundingBox l;
   public EnumFacing m;

   public void func_181138_a(int var1, int var2, int var3) {
      this.l.offset(var1, var2, var3);
   }

   public void a(World var1, IBlockState var2, int var3, int var4, int var5, StructureBoundingBox var6) {
      BlockPos var7 = new BlockPos(this.a(var3, var5), this.d(var4), this.b(var3, var5));
      if (var6.isVecInside(var7)) {
         var1.a(var7, var2, 2);
      }
   }

   public StructureBoundingBox getBoundingBox() {
      return this.l;
   }

   public void randomlyPlaceBlock(World var1, StructureBoundingBox var2, Random var3, float var4, int var5, int var6, int var7, IBlockState var8) {
      if (var3.nextFloat() < var4) {
         this.a(var1, var8, var5, var6, var7, var2);
      }
   }

   public int d(int var1) {
      return this.m == null ? var1 : var1 + this.l.minY;
   }

   public abstract void writeStructureToNBT(NBTTagCompound var1);

   public boolean a(World var1, StructureBoundingBox var2) {
      int var3 = Math.max(this.l.minX - 1, var2.minX);
      int var4 = Math.max(this.l.minY - 1, var2.minY);
      int var5 = Math.max(this.l.minZ - 1, var2.minZ);
      int var6 = Math.min(this.l.maxX + 1, var2.maxX);
      int var7 = Math.min(this.l.maxY + 1, var2.maxY);
      int var8 = Math.min(this.l.maxZ + 1, var2.maxZ);
      BlockPos.MutableBlockPos var9 = new BlockPos.MutableBlockPos();

      for (int var10 = var3; var10 <= var6; var10++) {
         for (int var11 = var5; var11 <= var8; var11++) {
            if (var1.getBlockState(var9.set(var10, var4, var11)).getBlock().getMaterial().isLiquid()) {
               return true;
            }

            if (var1.getBlockState(var9.set(var10, var7, var11)).getBlock().getMaterial().isLiquid()) {
               return true;
            }
         }
      }

      for (int var12 = var3; var12 <= var6; var12++) {
         for (int var14 = var4; var14 <= var7; var14++) {
            if (var1.getBlockState(var9.set(var12, var14, var5)).getBlock().getMaterial().isLiquid()) {
               return true;
            }

            if (var1.getBlockState(var9.set(var12, var14, var8)).getBlock().getMaterial().isLiquid()) {
               return true;
            }
         }
      }

      for (int var13 = var5; var13 <= var8; var13++) {
         for (int var15 = var4; var15 <= var7; var15++) {
            if (var1.getBlockState(var9.set(var3, var15, var13)).getBlock().getMaterial().isLiquid()) {
               return true;
            }

            if (var1.getBlockState(var9.set(var6, var15, var13)).getBlock().getMaterial().isLiquid()) {
               return true;
            }
         }
      }

      return false;
   }

   public void b(World var1, IBlockState var2, int var3, int var4, int var5, StructureBoundingBox var6) {
      int var7 = this.a(var3, var5);
      int var8 = this.d(var4);
      int var9 = this.b(var3, var5);
      if (var6.isVecInside(new BlockPos(var7, var8, var9))) {
         while (
            (var1.isAirBlock(new BlockPos(var7, var8, var9)) || var1.getBlockState(new BlockPos(var7, var8, var9)).getBlock().getMaterial().isLiquid())
               && var8 > 1
         ) {
            var1.a(new BlockPos(var7, var8, var9), var2, 2);
            var8--;
         }
      }
   }

   public void randomlyRareFillWithBlocks(
      World var1, StructureBoundingBox var2, int var3, int var4, int var5, int var6, int var7, int var8, IBlockState var9, boolean var10
   ) {
      float var11 = var6 - var3 + 1;
      float var12 = var7 - var4 + 1;
      float var13 = var8 - var5 + 1;
      float var14 = var3 + var11 / 2.0F;
      float var15 = var5 + var13 / 2.0F;

      for (int var16 = var4; var16 <= var7; var16++) {
         float var17 = (var16 - var4) / var12;

         for (int var18 = var3; var18 <= var6; var18++) {
            float var19 = (var18 - var14) / (var11 * 0.5F);

            for (int var20 = var5; var20 <= var8; var20++) {
               float var21 = (var20 - var15) / (var13 * 0.5F);
               if (!var10 || this.a(var1, var18, var16, var20, var2).getBlock().getMaterial() != Material.air) {
                  float var22 = var19 * var19 + var17 * var17 + var21 * var21;
                  if (var22 <= 1.05F) {
                     this.a(var1, var9, var18, var16, var20, var2);
                  }
               }
            }
         }
      }
   }

   public StructureComponent(int var1) {
      this.componentType = var1;
   }

   public abstract boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3);

   public void fillWithAir(World var1, StructureBoundingBox var2, int var3, int var4, int var5, int var6, int var7, int var8) {
      for (int var9 = var4; var9 <= var7; var9++) {
         for (int var10 = var3; var10 <= var6; var10++) {
            for (int var11 = var5; var11 <= var8; var11++) {
               this.a(var1, Blocks.air.getDefaultState(), var10, var9, var11, var2);
            }
         }
      }
   }

   public NBTTagCompound createStructureBaseNBT() {
      NBTTagCompound var1 = new NBTTagCompound();
      var1.setString("id", MapGenStructureIO.getStructureComponentName(this));
      var1.setTag("BB", this.l.toNBTTagIntArray());
      var1.setInteger("O", this.m == null ? -1 : this.m.getHorizontalIndex());
      var1.setInteger("GD", this.componentType);
      this.writeStructureToNBT(var1);
      return var1;
   }

   public void readStructureBaseNBT(World var1, NBTTagCompound var2) {
      if (var2.hasKey("BB")) {
         this.l = new StructureBoundingBox(var2.getIntArray("BB"));
      }

      int var3 = var2.getInteger("O");
      this.m = var3 == -1 ? null : EnumFacing.getHorizontal(var3);
      this.componentType = var2.getInteger("GD");
      this.readStructureFromNBT(var2);
   }

   public void b(World var1, int var2, int var3, int var4, StructureBoundingBox var5) {
      BlockPos var6 = new BlockPos(this.a(var2, var4), this.d(var3), this.b(var2, var4));
      if (var5.isVecInside(var6)) {
         while (!var1.isAirBlock(var6) && var6.getY() < 255) {
            var1.a(var6, Blocks.air.getDefaultState(), 2);
            var6 = var6.up();
         }
      }
   }

   public IBlockState a(World var1, int var2, int var3, int var4, StructureBoundingBox var5) {
      int var6 = this.a(var2, var4);
      int var7 = this.d(var3);
      int var8 = this.b(var2, var4);
      BlockPos var9 = new BlockPos(var6, var7, var8);
      return !var5.isVecInside(var9) ? Blocks.air.getDefaultState() : var1.getBlockState(var9);
   }

   public int a(int var1, int var2) {
      if (this.m == null) {
         return var1;
      } else {
         switch (this.m) {
            case NORTH:
            case SOUTH:
               return this.l.minX + var1;
            case WEST:
               return this.l.maxX - var2;
            case EAST:
               return this.l.minX + var2;
            default:
               return var1;
         }
      }
   }

   public int getComponentType() {
      return this.componentType;
   }

   public static StructureComponent findIntersecting(List<StructureComponent> var0, StructureBoundingBox var1) {
      for (StructureComponent var3 : var0) {
         if (var3.getBoundingBox() != null && var3.getBoundingBox().intersectsWith(var1)) {
            return var3;
         }
      }

      return null;
   }

   public boolean generateDispenserContents(
      World var1, StructureBoundingBox var2, Random var3, int var4, int var5, int var6, int var7, List<WeightedRandomChestContent> var8, int var9
   ) {
      BlockPos var10 = new BlockPos(this.a(var4, var6), this.d(var5), this.b(var4, var6));
      if (var2.isVecInside(var10) && var1.getBlockState(var10).getBlock() != Blocks.dispenser) {
         var1.a(var10, Blocks.dispenser.getStateFromMeta(this.a(Blocks.dispenser, var7)), 2);
         TileEntity var11 = var1.getTileEntity(var10);
         if (var11 instanceof TileEntityDispenser) {
            WeightedRandomChestContent.generateDispenserContents(var3, var8, (TileEntityDispenser)var11, var9);
         }

         return true;
      } else {
         return false;
      }
   }

   public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
   }

   public void placeDoorCurrentPosition(World var1, StructureBoundingBox var2, Random var3, int var4, int var5, int var6, EnumFacing var7) {
      BlockPos var8 = new BlockPos(this.a(var4, var6), this.d(var5), this.b(var4, var6));
      if (var2.isVecInside(var8)) {
         ItemDoor.placeDoor(var1, var8, var7.rotateYCCW(), Blocks.oak_door);
      }
   }

   public void a(
      World var1, StructureBoundingBox var2, int var3, int var4, int var5, int var6, int var7, int var8, IBlockState var9, IBlockState var10, boolean var11
   ) {
      for (int var12 = var4; var12 <= var7; var12++) {
         for (int var13 = var3; var13 <= var6; var13++) {
            for (int var14 = var5; var14 <= var8; var14++) {
               if (!var11 || this.a(var1, var13, var12, var14, var2).getBlock().getMaterial() != Material.air) {
                  if (var12 != var4 && var12 != var7 && var13 != var3 && var13 != var6 && var14 != var5 && var14 != var8) {
                     this.a(var1, var10, var13, var12, var14, var2);
                  } else {
                     this.a(var1, var9, var13, var12, var14, var2);
                  }
               }
            }
         }
      }
   }

   public StructureComponent() {
   }

   public int a(Block var1, int var2) {
      if (var1 == Blocks.rail) {
         if (this.m == EnumFacing.WEST || this.m == EnumFacing.EAST) {
            if (var2 == 1) {
               return 0;
            }

            return 1;
         }
      } else if (var1 instanceof BlockDoor) {
         if (this.m == EnumFacing.SOUTH) {
            if (var2 == 0) {
               return 2;
            }

            if (var2 == 2) {
               return 0;
            }
         } else {
            if (this.m == EnumFacing.WEST) {
               return var2 + 1 & 3;
            }

            if (this.m == EnumFacing.EAST) {
               return var2 + 3 & 3;
            }
         }
      } else if (var1 != Blocks.stone_stairs
         && var1 != Blocks.oak_stairs
         && var1 != Blocks.nether_brick_stairs
         && var1 != Blocks.stone_brick_stairs
         && var1 != Blocks.sandstone_stairs) {
         if (var1 == Blocks.ladder) {
            if (this.m == EnumFacing.SOUTH) {
               if (var2 == EnumFacing.NORTH.getIndex()) {
                  return EnumFacing.SOUTH.getIndex();
               }

               if (var2 == EnumFacing.SOUTH.getIndex()) {
                  return EnumFacing.NORTH.getIndex();
               }
            } else if (this.m == EnumFacing.WEST) {
               if (var2 == EnumFacing.NORTH.getIndex()) {
                  return EnumFacing.WEST.getIndex();
               }

               if (var2 == EnumFacing.SOUTH.getIndex()) {
                  return EnumFacing.EAST.getIndex();
               }

               if (var2 == EnumFacing.WEST.getIndex()) {
                  return EnumFacing.NORTH.getIndex();
               }

               if (var2 == EnumFacing.EAST.getIndex()) {
                  return EnumFacing.SOUTH.getIndex();
               }
            } else if (this.m == EnumFacing.EAST) {
               if (var2 == EnumFacing.NORTH.getIndex()) {
                  return EnumFacing.EAST.getIndex();
               }

               if (var2 == EnumFacing.SOUTH.getIndex()) {
                  return EnumFacing.WEST.getIndex();
               }

               if (var2 == EnumFacing.WEST.getIndex()) {
                  return EnumFacing.NORTH.getIndex();
               }

               if (var2 == EnumFacing.EAST.getIndex()) {
                  return EnumFacing.SOUTH.getIndex();
               }
            }
         } else if (var1 == Blocks.stone_button) {
            if (this.m == EnumFacing.SOUTH) {
               if (var2 == 3) {
                  return 4;
               }

               if (var2 == 4) {
                  return 3;
               }
            } else if (this.m == EnumFacing.WEST) {
               if (var2 == 3) {
                  return 1;
               }

               if (var2 == 4) {
                  return 2;
               }

               if (var2 == 2) {
                  return 3;
               }

               if (var2 == 1) {
                  return 4;
               }
            } else if (this.m == EnumFacing.EAST) {
               if (var2 == 3) {
                  return 2;
               }

               if (var2 == 4) {
                  return 1;
               }

               if (var2 == 2) {
                  return 3;
               }

               if (var2 == 1) {
                  return 4;
               }
            }
         } else if (var1 == Blocks.tripwire_hook || var1 instanceof BlockDirectional) {
            EnumFacing var3 = EnumFacing.getHorizontal(var2);
            if (this.m == EnumFacing.SOUTH) {
               if (var3 == EnumFacing.SOUTH || var3 == EnumFacing.NORTH) {
                  return var3.getOpposite().getHorizontalIndex();
               }
            } else if (this.m == EnumFacing.WEST) {
               if (var3 == EnumFacing.NORTH) {
                  return EnumFacing.WEST.getHorizontalIndex();
               }

               if (var3 == EnumFacing.SOUTH) {
                  return EnumFacing.EAST.getHorizontalIndex();
               }

               if (var3 == EnumFacing.WEST) {
                  return EnumFacing.NORTH.getHorizontalIndex();
               }

               if (var3 == EnumFacing.EAST) {
                  return EnumFacing.SOUTH.getHorizontalIndex();
               }
            } else if (this.m == EnumFacing.EAST) {
               if (var3 == EnumFacing.NORTH) {
                  return EnumFacing.EAST.getHorizontalIndex();
               }

               if (var3 == EnumFacing.SOUTH) {
                  return EnumFacing.WEST.getHorizontalIndex();
               }

               if (var3 == EnumFacing.WEST) {
                  return EnumFacing.NORTH.getHorizontalIndex();
               }

               if (var3 == EnumFacing.EAST) {
                  return EnumFacing.SOUTH.getHorizontalIndex();
               }
            }
         } else if (var1 == Blocks.piston || var1 == Blocks.sticky_piston || var1 == Blocks.lever || var1 == Blocks.dispenser) {
            if (this.m == EnumFacing.SOUTH) {
               if (var2 == EnumFacing.NORTH.getIndex() || var2 == EnumFacing.SOUTH.getIndex()) {
                  return EnumFacing.getFront(var2).getOpposite().getIndex();
               }
            } else if (this.m == EnumFacing.WEST) {
               if (var2 == EnumFacing.NORTH.getIndex()) {
                  return EnumFacing.WEST.getIndex();
               }

               if (var2 == EnumFacing.SOUTH.getIndex()) {
                  return EnumFacing.EAST.getIndex();
               }

               if (var2 == EnumFacing.WEST.getIndex()) {
                  return EnumFacing.NORTH.getIndex();
               }

               if (var2 == EnumFacing.EAST.getIndex()) {
                  return EnumFacing.SOUTH.getIndex();
               }
            } else if (this.m == EnumFacing.EAST) {
               if (var2 == EnumFacing.NORTH.getIndex()) {
                  return EnumFacing.EAST.getIndex();
               }

               if (var2 == EnumFacing.SOUTH.getIndex()) {
                  return EnumFacing.WEST.getIndex();
               }

               if (var2 == EnumFacing.WEST.getIndex()) {
                  return EnumFacing.NORTH.getIndex();
               }

               if (var2 == EnumFacing.EAST.getIndex()) {
                  return EnumFacing.SOUTH.getIndex();
               }
            }
         }
      } else if (this.m == EnumFacing.SOUTH) {
         if (var2 == 2) {
            return 3;
         }

         if (var2 == 3) {
            return 2;
         }
      } else if (this.m == EnumFacing.WEST) {
         if (var2 == 0) {
            return 2;
         }

         if (var2 == 1) {
            return 3;
         }

         if (var2 == 2) {
            return 0;
         }

         if (var2 == 3) {
            return 1;
         }
      } else if (this.m == EnumFacing.EAST) {
         if (var2 == 0) {
            return 2;
         }

         if (var2 == 1) {
            return 3;
         }

         if (var2 == 2) {
            return 1;
         }

         if (var2 == 3) {
            return 0;
         }
      }

      return var2;
   }

   public boolean generateChestContents(
      World var1, StructureBoundingBox var2, Random var3, int var4, int var5, int var6, List<WeightedRandomChestContent> var7, int var8
   ) {
      BlockPos var9 = new BlockPos(this.a(var4, var6), this.d(var5), this.b(var4, var6));
      if (var2.isVecInside(var9) && var1.getBlockState(var9).getBlock() != Blocks.chest) {
         IBlockState var10 = Blocks.chest.getDefaultState();
         var1.a(var9, Blocks.chest.correctFacing(var1, var9, var10), 2);
         TileEntity var11 = var1.getTileEntity(var9);
         if (var11 instanceof TileEntityChest) {
            WeightedRandomChestContent.generateChestContents(var3, var7, (TileEntityChest)var11, var8);
         }

         return true;
      } else {
         return false;
      }
   }

   public abstract void readStructureFromNBT(NBTTagCompound var1);

   public void a(
      World var1,
      StructureBoundingBox var2,
      int var3,
      int var4,
      int var5,
      int var6,
      int var7,
      int var8,
      boolean var9,
      Random var10,
      StructureComponent.BlockSelector var11
   ) {
      for (int var12 = var4; var12 <= var7; var12++) {
         for (int var13 = var3; var13 <= var6; var13++) {
            for (int var14 = var5; var14 <= var8; var14++) {
               if (!var9 || this.a(var1, var13, var12, var14, var2).getBlock().getMaterial() != Material.air) {
                  var11.selectBlocks(
                     var10, var13, var12, var14, var12 == var4 || var12 == var7 || var13 == var3 || var13 == var6 || var14 == var5 || var14 == var8
                  );
                  this.a(var1, var11.getBlockState(), var13, var12, var14, var2);
               }
            }
         }
      }
   }

   public int b(int var1, int var2) {
      if (this.m == null) {
         return var2;
      } else {
         switch (this.m) {
            case NORTH:
               return this.l.maxZ - var2;
            case SOUTH:
               return this.l.minZ + var2;
            case WEST:
            case EAST:
               return this.l.minZ + var1;
            default:
               return var2;
         }
      }
   }

   public void a(
      World var1,
      StructureBoundingBox var2,
      Random var3,
      float var4,
      int var5,
      int var6,
      int var7,
      int var8,
      int var9,
      int var10,
      IBlockState var11,
      IBlockState var12,
      boolean var13
   ) {
      for (int var14 = var6; var14 <= var9; var14++) {
         for (int var15 = var5; var15 <= var8; var15++) {
            for (int var16 = var7; var16 <= var10; var16++) {
               if (var3.nextFloat() <= var4 && (!var13 || this.a(var1, var15, var14, var16, var2).getBlock().getMaterial() != Material.air)) {
                  if (var14 != var6 && var14 != var9 && var15 != var5 && var15 != var8 && var16 != var7 && var16 != var10) {
                     this.a(var1, var12, var15, var14, var16, var2);
                  } else {
                     this.a(var1, var11, var15, var14, var16, var2);
                  }
               }
            }
         }
      }
   }

   public BlockPos getBoundingBoxCenter() {
      return new BlockPos(this.l.getCenter());
   }

   public abstract static class BlockSelector {
      public IBlockState a = Blocks.air.getDefaultState();

      public IBlockState getBlockState() {
         return this.a;
      }

      public abstract void selectBlocks(Random var1, int var2, int var3, int var4, boolean var5);
   }
}
