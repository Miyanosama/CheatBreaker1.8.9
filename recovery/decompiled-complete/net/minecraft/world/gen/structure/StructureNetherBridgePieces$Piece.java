package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.WeightedRandomChestContent;

public abstract class StructureNetherBridgePieces$Piece extends StructureComponent {
   public static List<WeightedRandomChestContent> a = Lists.newArrayList(
      new WeightedRandomChestContent[]{
         new WeightedRandomChestContent(Items.diamond, 0, 1, 3, 5),
         new WeightedRandomChestContent(Items.iron_ingot, 0, 1, 5, 5),
         new WeightedRandomChestContent(Items.gold_ingot, 0, 1, 3, 15),
         new WeightedRandomChestContent(Items.golden_sword, 0, 1, 1, 5),
         new WeightedRandomChestContent(Items.golden_chestplate, 0, 1, 1, 5),
         new WeightedRandomChestContent(Items.flint_and_steel, 0, 1, 1, 5),
         new WeightedRandomChestContent(Items.nether_wart, 0, 3, 7, 5),
         new WeightedRandomChestContent(Items.saddle, 0, 1, 1, 10),
         new WeightedRandomChestContent(Items.golden_horse_armor, 0, 1, 1, 8),
         new WeightedRandomChestContent(Items.iron_horse_armor, 0, 1, 1, 5),
         new WeightedRandomChestContent(Items.diamond_horse_armor, 0, 1, 1, 3),
         new WeightedRandomChestContent(Item.getItemFromBlock(Blocks.obsidian), 0, 2, 4, 2)
      }
   );

   @Override
   public void readStructureFromNBT(NBTTagCompound var1) {
   }

   public StructureNetherBridgePieces$Piece() {
   }

   public StructureNetherBridgePieces$Piece(int var1) {
      super(var1);
   }

   public static boolean isAboveGround(StructureBoundingBox var0) {
      return var0 != null && var0.minY > 10;
   }

   public StructureNetherBridgePieces$Piece func_175871_a(
      StructureNetherBridgePieces$Start var1,
      List<StructureNetherBridgePieces$PieceWeight> var2,
      List<StructureComponent> var3,
      Random var4,
      int var5,
      int var6,
      int var7,
      EnumFacing var8,
      int var9
   ) {
      int var10 = this.getTotalWeight(var2);
      boolean var11 = var10 > 0 && var9 <= 30;
      int var12 = 0;

      while (var12 < 5 && var11) {
         var12++;
         int var13 = var4.nextInt(var10);

         for (StructureNetherBridgePieces$PieceWeight var15 : var2) {
            var13 -= var15.field_78826_b;
            if (var13 < 0) {
               if (!var15.func_78822_a(var9) || var15 == var1.theNetherBridgePieceWeight && !var15.field_78825_e) {
                  break;
               }

               StructureNetherBridgePieces$Piece var16 = StructureNetherBridgePieces.access$000(var15, var3, var4, var5, var6, var7, var8, var9);
               if (var16 != null) {
                  var15.field_78827_c++;
                  var1.theNetherBridgePieceWeight = var15;
                  if (!var15.func_78823_a()) {
                     var2.remove(var15);
                  }

                  return var16;
               }
            }
         }
      }

      return StructureNetherBridgePieces$End.func_175884_a(var3, var4, var5, var6, var7, var8, var9);
   }

   public StructureComponent getNextComponentNormal(
      StructureNetherBridgePieces$Start var1, List<StructureComponent> var2, Random var3, int var4, int var5, boolean var6
   ) {
      if (this.m != null) {
         switch (StructureNetherBridgePieces$1.field_175888_a[this.m.ordinal()]) {
            case 1:
               return this.func_175870_a(var1, var2, var3, this.l.minX + var4, this.l.minY + var5, this.l.minZ - 1, this.m, this.getComponentType(), var6);
            case 2:
               return this.func_175870_a(var1, var2, var3, this.l.minX + var4, this.l.minY + var5, this.l.maxZ + 1, this.m, this.getComponentType(), var6);
            case 3:
               return this.func_175870_a(var1, var2, var3, this.l.minX - 1, this.l.minY + var5, this.l.minZ + var4, this.m, this.getComponentType(), var6);
            case 4:
               return this.func_175870_a(var1, var2, var3, this.l.maxX + 1, this.l.minY + var5, this.l.minZ + var4, this.m, this.getComponentType(), var6);
         }
      }

      return null;
   }

   public StructureComponent func_175870_a(
      StructureNetherBridgePieces$Start var1, List<StructureComponent> var2, Random var3, int var4, int var5, int var6, EnumFacing var7, int var8, boolean var9
   ) {
      if (Math.abs(var4 - var1.getBoundingBox().minX) <= 112 && Math.abs(var6 - var1.getBoundingBox().minZ) <= 112) {
         List var10 = var1.primaryWeights;
         if (var9) {
            var10 = var1.secondaryWeights;
         }

         StructureNetherBridgePieces$Piece var11 = this.func_175871_a(var1, var10, var2, var3, var4, var5, var6, var7, var8 + 1);
         if (var11 != null) {
            var2.add(var11);
            var1.field_74967_d.add(var11);
         }

         return var11;
      } else {
         return StructureNetherBridgePieces$End.func_175884_a(var2, var3, var4, var5, var6, var7, var8);
      }
   }

   public int getTotalWeight(List<StructureNetherBridgePieces$PieceWeight> var1) {
      boolean var2 = false;
      int var3 = 0;

      for (StructureNetherBridgePieces$PieceWeight var5 : var1) {
         if (var5.field_78824_d > 0 && var5.field_78827_c < var5.field_78824_d) {
            var2 = true;
         }

         var3 += var5.field_78826_b;
      }

      return var2 ? var3 : -1;
   }

   public StructureComponent getNextComponentX(
      StructureNetherBridgePieces$Start var1, List<StructureComponent> var2, Random var3, int var4, int var5, boolean var6
   ) {
      if (this.m != null) {
         switch (StructureNetherBridgePieces$1.field_175888_a[this.m.ordinal()]) {
            case 1:
               return this.func_175870_a(
                  var1, var2, var3, this.l.minX - 1, this.l.minY + var4, this.l.minZ + var5, EnumFacing.WEST, this.getComponentType(), var6
               );
            case 2:
               return this.func_175870_a(
                  var1, var2, var3, this.l.minX - 1, this.l.minY + var4, this.l.minZ + var5, EnumFacing.WEST, this.getComponentType(), var6
               );
            case 3:
               return this.func_175870_a(
                  var1, var2, var3, this.l.minX + var5, this.l.minY + var4, this.l.minZ - 1, EnumFacing.NORTH, this.getComponentType(), var6
               );
            case 4:
               return this.func_175870_a(
                  var1, var2, var3, this.l.minX + var5, this.l.minY + var4, this.l.minZ - 1, EnumFacing.NORTH, this.getComponentType(), var6
               );
         }
      }

      return null;
   }

   @Override
   public void writeStructureToNBT(NBTTagCompound var1) {
   }

   public StructureComponent getNextComponentZ(
      StructureNetherBridgePieces$Start var1, List<StructureComponent> var2, Random var3, int var4, int var5, boolean var6
   ) {
      if (this.m != null) {
         switch (StructureNetherBridgePieces$1.field_175888_a[this.m.ordinal()]) {
            case 1:
               return this.func_175870_a(
                  var1, var2, var3, this.l.maxX + 1, this.l.minY + var4, this.l.minZ + var5, EnumFacing.EAST, this.getComponentType(), var6
               );
            case 2:
               return this.func_175870_a(
                  var1, var2, var3, this.l.maxX + 1, this.l.minY + var4, this.l.minZ + var5, EnumFacing.EAST, this.getComponentType(), var6
               );
            case 3:
               return this.func_175870_a(
                  var1, var2, var3, this.l.minX + var5, this.l.minY + var4, this.l.maxZ + 1, EnumFacing.SOUTH, this.getComponentType(), var6
               );
            case 4:
               return this.func_175870_a(
                  var1, var2, var3, this.l.minX + var5, this.l.minY + var4, this.l.maxZ + 1, EnumFacing.SOUTH, this.getComponentType(), var6
               );
         }
      }

      return null;
   }
}
