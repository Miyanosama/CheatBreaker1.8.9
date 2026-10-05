package net.minecraft.world.gen.structure;

import java.util.List;
import java.util.Random;
import net.minecraft.block.Block$EnumOffsetType;
import net.minecraft.block.material.Material;
import net.minecraft.client.audio.SoundManager$2$1;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.S01PacketJoinGame;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class StructureMineshaftPieces$Cross extends StructureComponent {
   public S01PacketJoinGame field_0002;
   public Block$EnumOffsetType field_0004;
   public boolean isMultipleFloors;
   public SoundManager$2$1 field_0003;
   public EnumFacing corridorDirection;

   public static StructureBoundingBox func_175813_a(List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5) {
      StructureBoundingBox var6 = new StructureBoundingBox(var2, var3, var4, var2, var3 + 2, var4);
      if (var1.nextInt(4) == 0) {
         var6.maxY += 4;
      }

      switch (StructureMineshaftPieces$1.field_175894_a[var5.ordinal()]) {
         case 1:
            var6.minX = var2 - 1;
            var6.maxX = var2 + 3;
            var6.minZ = var4 - 4;
            break;
         case 2:
            var6.minX = var2 - 1;
            var6.maxX = var2 + 3;
            var6.maxZ = var4 + 4;
            break;
         case 3:
            var6.minX = var2 - 4;
            var6.minZ = var4 - 1;
            var6.maxZ = var4 + 3;
            break;
         case 4:
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

   public StructureMineshaftPieces$Cross() {
   }

   @Override
   public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
      int var4 = this.getComponentType();
      switch (StructureMineshaftPieces$1.field_175894_a[this.corridorDirection.ordinal()]) {
         case 1:
            StructureMineshaftPieces.access$000(var1, var2, var3, this.l.minX + 1, this.l.minY, this.l.minZ - 1, EnumFacing.NORTH, var4);
            StructureMineshaftPieces.access$000(var1, var2, var3, this.l.minX - 1, this.l.minY, this.l.minZ + 1, EnumFacing.WEST, var4);
            StructureMineshaftPieces.access$000(var1, var2, var3, this.l.maxX + 1, this.l.minY, this.l.minZ + 1, EnumFacing.EAST, var4);
            break;
         case 2:
            StructureMineshaftPieces.access$000(var1, var2, var3, this.l.minX + 1, this.l.minY, this.l.maxZ + 1, EnumFacing.SOUTH, var4);
            StructureMineshaftPieces.access$000(var1, var2, var3, this.l.minX - 1, this.l.minY, this.l.minZ + 1, EnumFacing.WEST, var4);
            StructureMineshaftPieces.access$000(var1, var2, var3, this.l.maxX + 1, this.l.minY, this.l.minZ + 1, EnumFacing.EAST, var4);
            break;
         case 3:
            StructureMineshaftPieces.access$000(var1, var2, var3, this.l.minX + 1, this.l.minY, this.l.minZ - 1, EnumFacing.NORTH, var4);
            StructureMineshaftPieces.access$000(var1, var2, var3, this.l.minX + 1, this.l.minY, this.l.maxZ + 1, EnumFacing.SOUTH, var4);
            StructureMineshaftPieces.access$000(var1, var2, var3, this.l.minX - 1, this.l.minY, this.l.minZ + 1, EnumFacing.WEST, var4);
            break;
         case 4:
            StructureMineshaftPieces.access$000(var1, var2, var3, this.l.minX + 1, this.l.minY, this.l.minZ - 1, EnumFacing.NORTH, var4);
            StructureMineshaftPieces.access$000(var1, var2, var3, this.l.minX + 1, this.l.minY, this.l.maxZ + 1, EnumFacing.SOUTH, var4);
            StructureMineshaftPieces.access$000(var1, var2, var3, this.l.maxX + 1, this.l.minY, this.l.minZ + 1, EnumFacing.EAST, var4);
      }

      if (this.isMultipleFloors) {
         if (var3.nextBoolean()) {
            StructureMineshaftPieces.access$000(var1, var2, var3, this.l.minX + 1, this.l.minY + 3 + 1, this.l.minZ - 1, EnumFacing.NORTH, var4);
         }

         if (var3.nextBoolean()) {
            StructureMineshaftPieces.access$000(var1, var2, var3, this.l.minX - 1, this.l.minY + 3 + 1, this.l.minZ + 1, EnumFacing.WEST, var4);
         }

         if (var3.nextBoolean()) {
            StructureMineshaftPieces.access$000(var1, var2, var3, this.l.maxX + 1, this.l.minY + 3 + 1, this.l.minZ + 1, EnumFacing.EAST, var4);
         }

         if (var3.nextBoolean()) {
            StructureMineshaftPieces.access$000(var1, var2, var3, this.l.minX + 1, this.l.minY + 3 + 1, this.l.maxZ + 1, EnumFacing.SOUTH, var4);
         }
      }
   }

   public StructureMineshaftPieces$Cross(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
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
