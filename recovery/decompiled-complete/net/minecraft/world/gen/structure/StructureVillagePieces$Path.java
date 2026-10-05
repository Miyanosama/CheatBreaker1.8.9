package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.block.state.IBlockState;
import net.minecraft.event.ClickEvent$Action;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$6;

public class StructureVillagePieces$Path extends StructureVillagePieces$Road {
   public int length;
   public LogBrokerMonitor$6 field_0002;
   public ClickEvent$Action field_0000;

   @Override
   public void readStructureFromNBT(NBTTagCompound var1) {
      super.readStructureFromNBT(var1);
      this.length = var1.getInteger("Length");
   }

   public static StructureBoundingBox func_175848_a(
      StructureVillagePieces$Start var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6
   ) {
      for (int var7 = 7 * MathHelper.getRandomIntegerInRange(var2, 3, 5); var7 >= 7; var7 -= 7) {
         StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 3, 3, var7, var6);
         if (StructureComponent.findIntersecting(var1, var8) == null) {
            return var8;
         }
      }

      return null;
   }

   @Override
   public void writeStructureToNBT(NBTTagCompound var1) {
      super.writeStructureToNBT(var1);
      var1.setInteger("Length", this.length);
   }

   @Override
   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      IBlockState var4 = this.func_175847_a(Blocks.gravel.getDefaultState());
      IBlockState var5 = this.func_175847_a(Blocks.cobblestone.getDefaultState());

      for (int var6 = this.l.minX; var6 <= this.l.maxX; var6++) {
         for (int var7 = this.l.minZ; var7 <= this.l.maxZ; var7++) {
            BlockPos var8 = new BlockPos(var6, 64, var7);
            if (var3.isVecInside(var8)) {
               var8 = var1.getTopSolidOrLiquidBlock(var8).down();
               var1.a(var8, var4, 2);
               var1.a(var8.down(), var5, 2);
            }
         }
      }

      return true;
   }

   public StructureVillagePieces$Path(StructureVillagePieces$Start var1, int var2, Random var3, StructureBoundingBox var4, EnumFacing var5) {
      super(var1, var2);
      this.m = var5;
      this.l = var4;
      this.length = Math.max(var4.getXSize(), var4.getZSize());
   }

   @Override
   public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
      boolean var4 = false;

      for (int var5 = var3.nextInt(5); var5 < this.length - 8; var5 += 2 + var3.nextInt(5)) {
         StructureComponent var6 = this.getNextComponentNN((StructureVillagePieces$Start)var1, var2, var3, 0, var5);
         if (var6 != null) {
            var5 += Math.max(var6.l.getXSize(), var6.l.getZSize());
            var4 = true;
         }
      }

      for (int var7 = var3.nextInt(5); var7 < this.length - 8; var7 += 2 + var3.nextInt(5)) {
         StructureComponent var8 = this.getNextComponentPP((StructureVillagePieces$Start)var1, var2, var3, 0, var7);
         if (var8 != null) {
            var7 += Math.max(var8.l.getXSize(), var8.l.getZSize());
            var4 = true;
         }
      }

      if (var4 && var3.nextInt(3) > 0 && this.m != null) {
         switch (StructureVillagePieces$1.field_176064_a[this.m.ordinal()]) {
            case 1:
               StructureVillagePieces.access$100(
                  (StructureVillagePieces$Start)var1, var2, var3, this.l.minX - 1, this.l.minY, this.l.minZ, EnumFacing.WEST, this.getComponentType()
               );
               break;
            case 2:
               StructureVillagePieces.access$100(
                  (StructureVillagePieces$Start)var1, var2, var3, this.l.minX - 1, this.l.minY, this.l.maxZ - 2, EnumFacing.WEST, this.getComponentType()
               );
               break;
            case 3:
               StructureVillagePieces.access$100(
                  (StructureVillagePieces$Start)var1, var2, var3, this.l.minX, this.l.minY, this.l.minZ - 1, EnumFacing.NORTH, this.getComponentType()
               );
               break;
            case 4:
               StructureVillagePieces.access$100(
                  (StructureVillagePieces$Start)var1, var2, var3, this.l.maxX - 2, this.l.minY, this.l.minZ - 1, EnumFacing.NORTH, this.getComponentType()
               );
         }
      }

      if (var4 && var3.nextInt(3) > 0 && this.m != null) {
         switch (StructureVillagePieces$1.field_176064_a[this.m.ordinal()]) {
            case 1:
               StructureVillagePieces.access$100(
                  (StructureVillagePieces$Start)var1, var2, var3, this.l.maxX + 1, this.l.minY, this.l.minZ, EnumFacing.EAST, this.getComponentType()
               );
               break;
            case 2:
               StructureVillagePieces.access$100(
                  (StructureVillagePieces$Start)var1, var2, var3, this.l.maxX + 1, this.l.minY, this.l.maxZ - 2, EnumFacing.EAST, this.getComponentType()
               );
               break;
            case 3:
               StructureVillagePieces.access$100(
                  (StructureVillagePieces$Start)var1, var2, var3, this.l.minX, this.l.minY, this.l.maxZ + 1, EnumFacing.SOUTH, this.getComponentType()
               );
               break;
            case 4:
               StructureVillagePieces.access$100(
                  (StructureVillagePieces$Start)var1, var2, var3, this.l.maxX - 2, this.l.minY, this.l.maxZ + 1, EnumFacing.SOUTH, this.getComponentType()
               );
         }
      }
   }

   public StructureVillagePieces$Path() {
   }
}
