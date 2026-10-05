package net.minecraft.world.gen.structure;

import com.google.common.collect.Sets;
import java.util.Random;
import java.util.Set;
import net.minecraft.client.renderer.entity.layers.LayerArrow;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumFacing$Plane;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;

public class StructureOceanMonument$StartMonument extends StructureStart {
   public LayerArrow field_0001;
   public Set<ChunkCoordIntPair> field_175791_c = Sets.newHashSet();
   public boolean field_175790_d;

   @Override
   public boolean func_175788_a(ChunkCoordIntPair var1) {
      return this.field_175791_c.contains(var1) ? false : super.func_175788_a(var1);
   }

   @Override
   public void func_175787_b(ChunkCoordIntPair var1) {
      super.func_175787_b(var1);
      this.field_175791_c.add(var1);
   }

   public StructureOceanMonument$StartMonument() {
   }

   @Override
   public void generateStructure(World var1, Random var2, StructureBoundingBox var3) {
      if (!this.field_175790_d) {
         this.a.clear();
         this.func_175789_b(var1, var2, this.getChunkPosX(), this.getChunkPosZ());
      }

      super.generateStructure(var1, var2, var3);
   }

   public void func_175789_b(World var1, Random var2, int var3, int var4) {
      var2.setSeed(var1.J());
      long var5 = var2.nextLong();
      long var7 = var2.nextLong();
      long var9 = var3 * var5;
      long var11 = var4 * var7;
      var2.setSeed(var9 ^ var11 ^ var1.J());
      int var13 = var3 * 16 + 8 - 29;
      int var14 = var4 * 16 + 8 - 29;
      EnumFacing var15 = EnumFacing$Plane.HORIZONTAL.random(var2);
      this.a.add(new StructureOceanMonumentPieces$MonumentBuilding(var2, var13, var14, var15));
      this.c();
      this.field_175790_d = true;
   }

   @Override
   public void writeToNBT(NBTTagCompound var1) {
      super.writeToNBT(var1);
      NBTTagList var2 = new NBTTagList();

      for (ChunkCoordIntPair var4 : this.field_175791_c) {
         NBTTagCompound var5 = new NBTTagCompound();
         var5.setInteger("X", var4.chunkXPos);
         var5.setInteger("Z", var4.chunkZPos);
         var2.appendTag(var5);
      }

      var1.setTag("Processed", var2);
   }

   public StructureOceanMonument$StartMonument(World var1, Random var2, int var3, int var4) {
      super(var3, var4);
      this.func_175789_b(var1, var2, var3, var4);
   }

   @Override
   public void readFromNBT(NBTTagCompound var1) {
      super.readFromNBT(var1);
      if (var1.hasKey("Processed", 9)) {
         NBTTagList var2 = var1.getTagList("Processed", 10);

         for (int var3 = 0; var3 < var2.tagCount(); var3++) {
            NBTTagCompound var4 = var2.getCompoundTagAt(var3);
            this.field_175791_c.add(new ChunkCoordIntPair(var4.getInteger("X"), var4.getInteger("Z")));
         }
      }
   }
}
