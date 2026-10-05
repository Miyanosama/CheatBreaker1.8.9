package net.minecraft.world.gen.structure;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.Random;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import recovered.unidentified.UnidentifiedClass3988;

public class StructureMineshaftPieces$Room extends StructureComponent {
   public UnidentifiedClass3988 field_0000;
   public List<StructureBoundingBox> roomsLinkedToTheRoom = Lists.newLinkedList();

   public StructureMineshaftPieces$Room(int var1, Random var2, int var3, int var4) {
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

   public StructureMineshaftPieces$Room() {
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

         StructureComponent var7 = StructureMineshaftPieces.access$000(
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

         StructureComponent var16 = StructureMineshaftPieces.access$000(
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

         StructureComponent var17 = StructureMineshaftPieces.access$000(
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

         StructureComponent var18 = StructureMineshaftPieces.access$000(
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
