package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.GeneratorBushFeature;

public class StructureStrongholdPieces$Straight extends StructureStrongholdPieces$Stronghold {
   public boolean expandsX;
   public GeneratorBushFeature field_0002;
   public boolean expandsZ;

   public static StructureStrongholdPieces$Straight func_175862_a(
      List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5, int var6
   ) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -1, -1, 0, 5, 5, 7, var5);
      return canStrongholdGoDeeper(var7) && StructureComponent.findIntersecting(var0, var7) == null
         ? new StructureStrongholdPieces$Straight(var6, var1, var7, var5)
         : null;
   }

   @Override
   public void writeStructureToNBT(NBTTagCompound var1) {
      super.writeStructureToNBT(var1);
      var1.setBoolean("Left", this.expandsX);
      var1.setBoolean("Right", this.expandsZ);
   }

   public StructureStrongholdPieces$Straight(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
      super(var1);
      this.m = var4;
      this.d = this.a(var2);
      this.l = var3;
      this.expandsX = var2.nextInt(2) == 0;
      this.expandsZ = var2.nextInt(2) == 0;
   }

   @Override
   public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
      this.a((StructureStrongholdPieces$Stairs2)var1, var2, var3, 1, 1);
      if (this.expandsX) {
         this.b((StructureStrongholdPieces$Stairs2)var1, var2, var3, 1, 2);
      }

      if (this.expandsZ) {
         this.c((StructureStrongholdPieces$Stairs2)var1, var2, var3, 1, 2);
      }
   }

   @Override
   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if (this.a(var1, var3)) {
         return false;
      } else {
         this.a(var1, var3, 0, 0, 0, 4, 4, 6, true, var2, StructureStrongholdPieces.access$200());
         this.a(var1, var2, var3, this.d, 1, 1, 0);
         this.a(var1, var2, var3, StructureStrongholdPieces$Stronghold$Door.NORTH, 1, 1, 6);
         this.randomlyPlaceBlock(var1, var3, var2, 0.1F, 1, 2, 1, Blocks.torch.getDefaultState());
         this.randomlyPlaceBlock(var1, var3, var2, 0.1F, 3, 2, 1, Blocks.torch.getDefaultState());
         this.randomlyPlaceBlock(var1, var3, var2, 0.1F, 1, 2, 5, Blocks.torch.getDefaultState());
         this.randomlyPlaceBlock(var1, var3, var2, 0.1F, 3, 2, 5, Blocks.torch.getDefaultState());
         if (this.expandsX) {
            this.a(var1, var3, 0, 1, 2, 0, 3, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         }

         if (this.expandsZ) {
            this.a(var1, var3, 4, 1, 2, 4, 3, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
         }

         return true;
      }
   }

   public StructureStrongholdPieces$Straight() {
   }

   @Override
   public void readStructureFromNBT(NBTTagCompound var1) {
      super.readStructureFromNBT(var1);
      this.expandsX = var1.getBoolean("Left");
      this.expandsZ = var1.getBoolean("Right");
   }
}
