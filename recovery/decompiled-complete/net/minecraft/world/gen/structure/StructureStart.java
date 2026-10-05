package net.minecraft.world.gen.structure;

import io.netty.channel.sctp.oio.OioSctpChannel$OioSctpChannelConfig;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;
import net.minecraft.block.BlockSkull$2;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.World;

public abstract class StructureStart {
   public StructureBoundingBox boundingBox;
   public LinkedList<StructureComponent> a = new LinkedList<>();
   public BlockSkull$2 field_0004;
   public OioSctpChannel$OioSctpChannelConfig field_0003;
   public int chunkPosX;
   public int chunkPosZ;

   public int getChunkPosX() {
      return this.chunkPosX;
   }

   public void a(World var1, Random var2, int var3) {
      int var4 = var1.F() - var3;
      int var5 = this.boundingBox.getYSize() + 1;
      if (var5 < var4) {
         var5 += var2.nextInt(var4 - var5);
      }

      int var6 = var5 - this.boundingBox.maxY;
      this.boundingBox.offset(0, var6, 0);

      for (StructureComponent var8 : this.a) {
         var8.func_181138_a(0, var6, 0);
      }
   }

   public void writeToNBT(NBTTagCompound var1) {
   }

   public StructureStart(int var1, int var2) {
      this.chunkPosX = var1;
      this.chunkPosZ = var2;
   }

   public void setRandomHeight(World var1, Random var2, int var3, int var4) {
      int var5 = var4 - var3 + 1 - this.boundingBox.getYSize();
      int var6 = 1;
      if (var5 > 1) {
         var6 = var3 + var2.nextInt(var5);
      } else {
         var6 = var3;
      }

      int var7 = var6 - this.boundingBox.minY;
      this.boundingBox.offset(0, var7, 0);

      for (StructureComponent var9 : this.a) {
         var9.func_181138_a(0, var7, 0);
      }
   }

   public StructureBoundingBox getBoundingBox() {
      return this.boundingBox;
   }

   public StructureStart() {
   }

   public NBTTagCompound writeStructureComponentsToNBT(int var1, int var2) {
      NBTTagCompound var3 = new NBTTagCompound();
      var3.setString("id", MapGenStructureIO.getStructureStartName(this));
      var3.setInteger("ChunkX", var1);
      var3.setInteger("ChunkZ", var2);
      var3.setTag("BB", this.boundingBox.toNBTTagIntArray());
      NBTTagList var4 = new NBTTagList();

      for (StructureComponent var6 : this.a) {
         var4.appendTag(var6.createStructureBaseNBT());
      }

      var3.setTag("Children", var4);
      this.writeToNBT(var3);
      return var3;
   }

   public boolean isSizeableStructure() {
      return true;
   }

   public void readStructureComponentsFromNBT(World var1, NBTTagCompound var2) {
      this.chunkPosX = var2.getInteger("ChunkX");
      this.chunkPosZ = var2.getInteger("ChunkZ");
      if (var2.hasKey("BB")) {
         this.boundingBox = new StructureBoundingBox(var2.getIntArray("BB"));
      }

      NBTTagList var3 = var2.getTagList("Children", 10);

      for (int var4 = 0; var4 < var3.tagCount(); var4++) {
         this.a.add(MapGenStructureIO.getStructureComponent(var3.getCompoundTagAt(var4), var1));
      }

      this.readFromNBT(var2);
   }

   public int getChunkPosZ() {
      return this.chunkPosZ;
   }

   public boolean func_175788_a(ChunkCoordIntPair var1) {
      return true;
   }

   public void readFromNBT(NBTTagCompound var1) {
   }

   public void generateStructure(World var1, Random var2, StructureBoundingBox var3) {
      Iterator var4 = this.a.iterator();

      while (var4.hasNext()) {
         StructureComponent var5 = (StructureComponent)var4.next();
         if (var5.getBoundingBox().intersectsWith(var3) && !var5.addComponentParts(var1, var2, var3)) {
            var4.remove();
         }
      }
   }

   public LinkedList<StructureComponent> getComponents() {
      return this.a;
   }

   public void c() {
      this.boundingBox = StructureBoundingBox.getNewBoundingBox();

      for (StructureComponent var2 : this.a) {
         this.boundingBox.expandTo(var2.getBoundingBox());
      }
   }

   public void func_175787_b(ChunkCoordIntPair var1) {
   }
}
