package net.minecraft.world.gen.structure;

import io.netty.handler.codec.ByteToMessageCodec$Encoder;
import java.util.List;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagCompound$1;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import org.apache.log4j.varia.Roller;

public class StructureVillagePieces$WoodHut extends StructureVillagePieces$Village {
   public int tablePosition;
   public Roller field_0004;
   public ByteToMessageCodec$Encoder field_0001;
   public NBTTagCompound$1 field_0003;
   public boolean isTallHouse;

   public StructureVillagePieces$WoodHut() {
   }

   @Override
   public void readStructureFromNBT(NBTTagCompound var1) {
      super.readStructureFromNBT(var1);
      this.tablePosition = var1.getInteger("T");
      this.isTallHouse = var1.getBoolean("C");
   }

   public static StructureVillagePieces$WoodHut func_175853_a(
      StructureVillagePieces$Start var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
   ) {
      StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 4, 6, 5, var6);
      return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(var1, var8) == null
         ? new StructureVillagePieces$WoodHut(var0, var7, var2, var8, var6)
         : null;
   }

   public StructureVillagePieces$WoodHut(StructureVillagePieces$Start var1, int var2, Random var3, StructureBoundingBox var4, EnumFacing var5) {
      super(var1, var2);
      this.m = var5;
      this.l = var4;
      this.isTallHouse = var3.nextBoolean();
      this.tablePosition = var3.nextInt(3);
   }

   @Override
   public void writeStructureToNBT(NBTTagCompound var1) {
      super.writeStructureToNBT(var1);
      var1.setInteger("T", this.tablePosition);
      var1.setBoolean("C", this.isTallHouse);
   }

   @Override
   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if (this.h < 0) {
         this.h = this.b(var1, var3);
         if (this.h < 0) {
            return true;
         }

         this.l.offset(0, this.h - this.l.maxY + 6 - 1, 0);
      }

      this.a(var1, var3, 1, 1, 1, 3, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.a(var1, var3, 0, 0, 0, 3, 0, 4, Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
      this.a(var1, var3, 1, 0, 1, 2, 0, 3, Blocks.dirt.getDefaultState(), Blocks.dirt.getDefaultState(), false);
      if (this.isTallHouse) {
         this.a(var1, var3, 1, 4, 1, 2, 4, 3, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
      } else {
         this.a(var1, var3, 1, 5, 1, 2, 5, 3, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
      }

      this.a(var1, Blocks.log.getDefaultState(), 1, 4, 0, var3);
      this.a(var1, Blocks.log.getDefaultState(), 2, 4, 0, var3);
      this.a(var1, Blocks.log.getDefaultState(), 1, 4, 4, var3);
      this.a(var1, Blocks.log.getDefaultState(), 2, 4, 4, var3);
      this.a(var1, Blocks.log.getDefaultState(), 0, 4, 1, var3);
      this.a(var1, Blocks.log.getDefaultState(), 0, 4, 2, var3);
      this.a(var1, Blocks.log.getDefaultState(), 0, 4, 3, var3);
      this.a(var1, Blocks.log.getDefaultState(), 3, 4, 1, var3);
      this.a(var1, Blocks.log.getDefaultState(), 3, 4, 2, var3);
      this.a(var1, Blocks.log.getDefaultState(), 3, 4, 3, var3);
      this.a(var1, var3, 0, 1, 0, 0, 3, 0, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
      this.a(var1, var3, 3, 1, 0, 3, 3, 0, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
      this.a(var1, var3, 0, 1, 4, 0, 3, 4, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
      this.a(var1, var3, 3, 1, 4, 3, 3, 4, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
      this.a(var1, var3, 0, 1, 1, 0, 3, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
      this.a(var1, var3, 3, 1, 1, 3, 3, 3, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
      this.a(var1, var3, 1, 1, 0, 2, 3, 0, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
      this.a(var1, var3, 1, 1, 4, 2, 3, 4, Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
      this.a(var1, Blocks.glass_pane.getDefaultState(), 0, 2, 2, var3);
      this.a(var1, Blocks.glass_pane.getDefaultState(), 3, 2, 2, var3);
      if (this.tablePosition > 0) {
         this.a(var1, Blocks.oak_fence.getDefaultState(), this.tablePosition, 1, 3, var3);
         this.a(var1, Blocks.wooden_pressure_plate.getDefaultState(), this.tablePosition, 2, 3, var3);
      }

      this.a(var1, Blocks.air.getDefaultState(), 1, 1, 0, var3);
      this.a(var1, Blocks.air.getDefaultState(), 1, 2, 0, var3);
      this.placeDoorCurrentPosition(var1, var3, var2, 1, 1, 0, EnumFacing.getHorizontal(this.a(Blocks.oak_door, 1)));
      if (this.a(var1, 1, 0, -1, var3).getBlock().getMaterial() == Material.air && this.a(var1, 1, -1, -1, var3).getBlock().getMaterial() != Material.air) {
         this.a(var1, Blocks.stone_stairs.getStateFromMeta(this.a(Blocks.stone_stairs, 3)), 1, 0, -1, var3);
      }

      for (int var4 = 0; var4 < 5; var4++) {
         for (int var5 = 0; var5 < 4; var5++) {
            this.b(var1, var5, 6, var4, var3);
            this.b(var1, Blocks.cobblestone.getDefaultState(), var5, -1, var4, var3);
         }
      }

      this.a(var1, var3, 1, 1, 2, 1);
      return true;
   }
}
