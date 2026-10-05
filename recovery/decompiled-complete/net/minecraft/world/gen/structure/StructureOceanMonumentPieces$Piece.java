package net.minecraft.world.gen.structure;

import net.minecraft.block.BlockPrismarine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.IEntityLivingData;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.optifine.entity.model.ModelAdapterSheepWool;

public abstract class StructureOceanMonumentPieces$Piece extends StructureComponent {
   public static IBlockState field_175824_d = StructureOceanMonumentPieces$Piece.b;
   public ModelAdapterSheepWool field_0010;
   public static int field_175831_h = func_175820_a(2, 2, 0);
   public static IBlockState e = Blocks.sea_lantern.getDefaultState();
   public static IBlockState c = Blocks.prismarine.getStateFromMeta(BlockPrismarine.DARK_META);
   public StructureOceanMonumentPieces$RoomDefinition k;
   public static int field_175829_j = func_175820_a(4, 1, 0);
   public static IBlockState a = Blocks.prismarine.getStateFromMeta(BlockPrismarine.ROUGH_META);
   public static int field_175832_i = func_175820_a(0, 1, 0);
   public static IBlockState field_175822_f = Blocks.water.getDefaultState();
   public static int field_175823_g = func_175820_a(2, 0, 0);
   public static IBlockState b = Blocks.prismarine.getStateFromMeta(BlockPrismarine.BRICKS_META);

   public boolean a(World var1, StructureBoundingBox var2, int var3, int var4, int var5) {
      int var6 = this.a(var3, var5);
      int var7 = this.d(var4);
      int var8 = this.b(var3, var5);
      if (var2.isVecInside(new BlockPos(var6, var7, var8))) {
         EntityGuardian var9 = new EntityGuardian(var1);
         var9.setElder(true);
         var9.heal(var9.getMaxHealth());
         var9.a_(var6 + 0.5, var7, var8 + 0.5, 0.0F, 0.0F);
         var9.onInitialSpawn(var1.E(new BlockPos(var9)), (IEntityLivingData)null);
         var1.spawnEntityInWorld(var9);
         return true;
      } else {
         return false;
      }
   }

   public StructureOceanMonumentPieces$Piece() {
      super(0);
   }

   public StructureOceanMonumentPieces$Piece(int var1) {
      super(var1);
   }

   public void a(World var1, StructureBoundingBox var2, int var3, int var4, boolean var5) {
      if (var5) {
         this.a(var1, var2, var3 + 0, 0, var4 + 0, var3 + 2, 0, var4 + 8 - 1, a, a, false);
         this.a(var1, var2, var3 + 5, 0, var4 + 0, var3 + 8 - 1, 0, var4 + 8 - 1, a, a, false);
         this.a(var1, var2, var3 + 3, 0, var4 + 0, var3 + 4, 0, var4 + 2, a, a, false);
         this.a(var1, var2, var3 + 3, 0, var4 + 5, var3 + 4, 0, var4 + 8 - 1, a, a, false);
         this.a(var1, var2, var3 + 3, 0, var4 + 2, var3 + 4, 0, var4 + 2, b, b, false);
         this.a(var1, var2, var3 + 3, 0, var4 + 5, var3 + 4, 0, var4 + 5, b, b, false);
         this.a(var1, var2, var3 + 2, 0, var4 + 3, var3 + 2, 0, var4 + 4, b, b, false);
         this.a(var1, var2, var3 + 5, 0, var4 + 3, var3 + 5, 0, var4 + 4, b, b, false);
      } else {
         this.a(var1, var2, var3 + 0, 0, var4 + 0, var3 + 8 - 1, 0, var4 + 8 - 1, a, a, false);
      }
   }

   public StructureOceanMonumentPieces$Piece(int var1, EnumFacing var2, StructureOceanMonumentPieces$RoomDefinition var3, int var4, int var5, int var6) {
      super(var1);
      this.m = var2;
      this.k = var3;
      int var7 = var3.field_175967_a;
      int var8 = var7 % 5;
      int var9 = var7 / 5 % 5;
      int var10 = var7 / 25;
      if (var2 != EnumFacing.NORTH && var2 != EnumFacing.SOUTH) {
         this.l = new StructureBoundingBox(0, 0, 0, var6 * 8 - 1, var5 * 4 - 1, var4 * 8 - 1);
      } else {
         this.l = new StructureBoundingBox(0, 0, 0, var4 * 8 - 1, var5 * 4 - 1, var6 * 8 - 1);
      }

      switch (StructureOceanMonumentPieces$1.field_175971_a[var2.ordinal()]) {
         case 1:
            this.l.offset(var8 * 8, var10 * 4, -(var9 + var6) * 8 + 1);
            break;
         case 2:
            this.l.offset(var8 * 8, var10 * 4, var9 * 8);
            break;
         case 3:
            this.l.offset(-(var9 + var6) * 8 + 1, var10 * 4, var8 * 8);
            break;
         default:
            this.l.offset(var9 * 8, var10 * 4, var8 * 8);
      }
   }

   public void a(World var1, StructureBoundingBox var2, int var3, int var4, int var5, int var6, int var7, int var8, boolean var9) {
      for (int var10 = var4; var10 <= var7; var10++) {
         for (int var11 = var3; var11 <= var6; var11++) {
            for (int var12 = var5; var12 <= var8; var12++) {
               if (!var9 || this.a(var1, var11, var10, var12, var2).getBlock().getMaterial() != Material.air) {
                  if (this.d(var10) >= var1.F()) {
                     this.a(var1, Blocks.air.getDefaultState(), var11, var10, var12, var2);
                  } else {
                     this.a(var1, field_175822_f, var11, var10, var12, var2);
                  }
               }
            }
         }
      }
   }

   public static int func_175820_a(int var0, int var1, int var2) {
      return var1 * 25 + var2 * 5 + var0;
   }

   @Override
   public void readStructureFromNBT(NBTTagCompound var1) {
   }

   public void a(World var1, StructureBoundingBox var2, int var3, int var4, int var5, int var6, int var7, int var8, IBlockState var9) {
      for (int var10 = var4; var10 <= var7; var10++) {
         for (int var11 = var3; var11 <= var6; var11++) {
            for (int var12 = var5; var12 <= var8; var12++) {
               if (this.a(var1, var11, var10, var12, var2) == field_175822_f) {
                  this.a(var1, var9, var11, var10, var12, var2);
               }
            }
         }
      }
   }

   public boolean func_175818_a(StructureBoundingBox var1, int var2, int var3, int var4, int var5) {
      int var6 = this.a(var2, var3);
      int var7 = this.b(var2, var3);
      int var8 = this.a(var4, var5);
      int var9 = this.b(var4, var5);
      return var1.intersectsWith(Math.min(var6, var8), Math.min(var7, var9), Math.max(var6, var8), Math.max(var7, var9));
   }

   @Override
   public void writeStructureToNBT(NBTTagCompound var1) {
   }

   public StructureOceanMonumentPieces$Piece(EnumFacing var1, StructureBoundingBox var2) {
      super(1);
      this.m = var1;
      this.l = var2;
   }
}
