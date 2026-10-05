package net.minecraft.world.gen.structure;

import io.netty.handler.codec.rtsp.RtspObjectEncoder;
import java.util.List;
import java.util.Random;
import junit.framework.TestSuite;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class StructureMineshaftPieces$Stairs extends StructureComponent {
   public RealmsBridge field_0001;
   public RtspObjectEncoder field_0002;
   public TestSuite field_0000;

   @Override
   public void writeStructureToNBT(NBTTagCompound var1) {
   }

   public static StructureBoundingBox func_175812_a(List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5) {
      StructureBoundingBox var6 = new StructureBoundingBox(var2, var3 - 5, var4, var2, var3 + 2, var4);
      switch (StructureMineshaftPieces$1.field_175894_a[var5.ordinal()]) {
         case 1:
            var6.maxX = var2 + 2;
            var6.minZ = var4 - 8;
            break;
         case 2:
            var6.maxX = var2 + 2;
            var6.maxZ = var4 + 8;
            break;
         case 3:
            var6.minX = var2 - 8;
            var6.maxZ = var4 + 2;
            break;
         case 4:
            var6.maxX = var2 + 8;
            var6.maxZ = var4 + 2;
      }

      return StructureComponent.findIntersecting(var0, var6) != null ? null : var6;
   }

   public StructureMineshaftPieces$Stairs(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
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
               var1, var3, 0, 5 - var4 - (var4 < 4 ? 1 : 0), 2 + var4, 2, 7 - var4, 2 + var4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false
            );
         }

         return true;
      }
   }

   @Override
   public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
      int var4 = this.getComponentType();
      if (this.m != null) {
         switch (StructureMineshaftPieces$1.field_175894_a[this.m.ordinal()]) {
            case 1:
               StructureMineshaftPieces.access$000(var1, var2, var3, this.l.minX, this.l.minY, this.l.minZ - 1, EnumFacing.NORTH, var4);
               break;
            case 2:
               StructureMineshaftPieces.access$000(var1, var2, var3, this.l.minX, this.l.minY, this.l.maxZ + 1, EnumFacing.SOUTH, var4);
               break;
            case 3:
               StructureMineshaftPieces.access$000(var1, var2, var3, this.l.minX - 1, this.l.minY, this.l.minZ, EnumFacing.WEST, var4);
               break;
            case 4:
               StructureMineshaftPieces.access$000(var1, var2, var3, this.l.maxX + 1, this.l.minY, this.l.minZ, EnumFacing.EAST, var4);
         }
      }
   }

   public StructureMineshaftPieces$Stairs() {
   }
}
