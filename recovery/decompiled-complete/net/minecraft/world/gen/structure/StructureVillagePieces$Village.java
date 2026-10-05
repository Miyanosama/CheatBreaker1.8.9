package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.block.BlockSandStone$EnumType;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.BlockPos$MutableBlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public abstract class StructureVillagePieces$Village extends StructureComponent {
   public int villagersSpawned;
   public boolean isDesertVillage;
   public int h = -1;

   public static boolean canVillageGoDeeper(StructureBoundingBox var0) {
      return var0 != null && var0.minY > 10;
   }

   @Override
   public void a(World var1, IBlockState var2, int var3, int var4, int var5, StructureBoundingBox var6) {
      IBlockState var7 = this.func_175847_a(var2);
      super.a(var1, var7, var3, var4, var5, var6);
   }

   public void a(World var1, StructureBoundingBox var2, int var3, int var4, int var5, int var6) {
      if (this.villagersSpawned < var6) {
         for (int var7 = this.villagersSpawned; var7 < var6; var7++) {
            int var8 = this.a(var3 + var7, var5);
            int var9 = this.d(var4);
            int var10 = this.b(var3 + var7, var5);
            if (!var2.isVecInside(new BlockPos(var8, var9, var10))) {
               break;
            }

            this.villagersSpawned++;
            EntityVillager var11 = new EntityVillager(var1);
            var11.a_(var8 + 0.5, var9, var10 + 0.5, 0.0F, 0.0F);
            var11.onInitialSpawn(var1.E(new BlockPos(var11)), (IEntityLivingData)null);
            var11.setProfession(this.func_180779_c(var7, var11.getProfession()));
            var1.spawnEntityInWorld(var11);
         }
      }
   }

   @Override
   public void readStructureFromNBT(NBTTagCompound var1) {
      this.h = var1.getInteger("HPos");
      this.villagersSpawned = var1.getInteger("VCount");
      this.isDesertVillage = var1.getBoolean("Desert");
   }

   public StructureComponent getNextComponentNN(StructureVillagePieces$Start var1, List<StructureComponent> var2, Random var3, int var4, int var5) {
      if (this.m != null) {
         switch (StructureVillagePieces$1.field_176064_a[this.m.ordinal()]) {
            case 1:
               return StructureVillagePieces.access$000(
                  var1, var2, var3, this.l.minX - 1, this.l.minY + var4, this.l.minZ + var5, EnumFacing.WEST, this.getComponentType()
               );
            case 2:
               return StructureVillagePieces.access$000(
                  var1, var2, var3, this.l.minX - 1, this.l.minY + var4, this.l.minZ + var5, EnumFacing.WEST, this.getComponentType()
               );
            case 3:
               return StructureVillagePieces.access$000(
                  var1, var2, var3, this.l.minX + var5, this.l.minY + var4, this.l.minZ - 1, EnumFacing.NORTH, this.getComponentType()
               );
            case 4:
               return StructureVillagePieces.access$000(
                  var1, var2, var3, this.l.minX + var5, this.l.minY + var4, this.l.minZ - 1, EnumFacing.NORTH, this.getComponentType()
               );
         }
      }

      return null;
   }

   public int func_180779_c(int var1, int var2) {
      return var2;
   }

   public StructureVillagePieces$Village(StructureVillagePieces$Start var1, int var2) {
      super(var2);
      if (var1 != null) {
         this.isDesertVillage = var1.inDesert;
      }
   }

   @Override
   public void a(
      World var1, StructureBoundingBox var2, int var3, int var4, int var5, int var6, int var7, int var8, IBlockState var9, IBlockState var10, boolean var11
   ) {
      IBlockState var12 = this.func_175847_a(var9);
      IBlockState var13 = this.func_175847_a(var10);
      super.a(var1, var2, var3, var4, var5, var6, var7, var8, var12, var13, var11);
   }

   public StructureVillagePieces$Village() {
   }

   public void func_175846_a(boolean var1) {
      this.isDesertVillage = var1;
   }

   @Override
   public void b(World var1, IBlockState var2, int var3, int var4, int var5, StructureBoundingBox var6) {
      IBlockState var7 = this.func_175847_a(var2);
      super.b(var1, var7, var3, var4, var5, var6);
   }

   public StructureComponent getNextComponentPP(StructureVillagePieces$Start var1, List<StructureComponent> var2, Random var3, int var4, int var5) {
      if (this.m != null) {
         switch (StructureVillagePieces$1.field_176064_a[this.m.ordinal()]) {
            case 1:
               return StructureVillagePieces.access$000(
                  var1, var2, var3, this.l.maxX + 1, this.l.minY + var4, this.l.minZ + var5, EnumFacing.EAST, this.getComponentType()
               );
            case 2:
               return StructureVillagePieces.access$000(
                  var1, var2, var3, this.l.maxX + 1, this.l.minY + var4, this.l.minZ + var5, EnumFacing.EAST, this.getComponentType()
               );
            case 3:
               return StructureVillagePieces.access$000(
                  var1, var2, var3, this.l.minX + var5, this.l.minY + var4, this.l.maxZ + 1, EnumFacing.SOUTH, this.getComponentType()
               );
            case 4:
               return StructureVillagePieces.access$000(
                  var1, var2, var3, this.l.minX + var5, this.l.minY + var4, this.l.maxZ + 1, EnumFacing.SOUTH, this.getComponentType()
               );
         }
      }

      return null;
   }

   @Override
   public void writeStructureToNBT(NBTTagCompound var1) {
      var1.setInteger("HPos", this.h);
      var1.setInteger("VCount", this.villagersSpawned);
      var1.setBoolean("Desert", this.isDesertVillage);
   }

   public IBlockState func_175847_a(IBlockState var1) {
      if (this.isDesertVillage) {
         if (var1.getBlock() == Blocks.log || var1.getBlock() == Blocks.log2) {
            return Blocks.sandstone.getDefaultState();
         }

         if (var1.getBlock() == Blocks.cobblestone) {
            return Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.DEFAULT.getMetadata());
         }

         if (var1.getBlock() == Blocks.planks) {
            return Blocks.sandstone.getStateFromMeta(BlockSandStone$EnumType.SMOOTH.getMetadata());
         }

         if (var1.getBlock() == Blocks.oak_stairs) {
            return Blocks.sandstone_stairs.getDefaultState().withProperty(BlockStairs.FACING, var1.getValue(BlockStairs.FACING));
         }

         if (var1.getBlock() == Blocks.stone_stairs) {
            return Blocks.sandstone_stairs.getDefaultState().withProperty(BlockStairs.FACING, var1.getValue(BlockStairs.FACING));
         }

         if (var1.getBlock() == Blocks.gravel) {
            return Blocks.sandstone.getDefaultState();
         }
      }

      return var1;
   }

   public int b(World var1, StructureBoundingBox var2) {
      int var3 = 0;
      int var4 = 0;
      BlockPos$MutableBlockPos var5 = new BlockPos$MutableBlockPos();

      for (int var6 = this.l.minZ; var6 <= this.l.maxZ; var6++) {
         for (int var7 = this.l.minX; var7 <= this.l.maxX; var7++) {
            var5.set(var7, 64, var6);
            if (var2.isVecInside(var5)) {
               var3 += Math.max(var1.getTopSolidOrLiquidBlock(var5).getY(), var1.t.getAverageGroundLevel());
               var4++;
            }
         }
      }

      return var4 == 0 ? -1 : var3 / var4;
   }
}
