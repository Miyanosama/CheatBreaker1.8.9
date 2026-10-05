package net.minecraft.world.gen.structure;

import io.netty.handler.codec.http.websocketx.WebSocket00FrameEncoder;
import io.netty.handler.timeout.WriteTimeoutHandler$1;
import io.netty.util.Recycler$Stack;
import java.util.List;
import java.util.Random;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditor$6;

public class StructureVillagePieces$Field1 extends StructureVillagePieces$Village {
   public Block cropTypeB;
   public Recycler$Stack field_0006;
   public Block cropTypeD;
   public Block cropTypeA;
   public WebSocket00FrameEncoder field_0000;
   public Block cropTypeC;
   public CategoryNodeEditor$6 field_0007;
   public WriteTimeoutHandler$1 field_0004;

   @Override
   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      if (this.h < 0) {
         this.h = this.b(var1, var3);
         if (this.h < 0) {
            return true;
         }

         this.l.offset(0, this.h - this.l.maxY + 4 - 1, 0);
      }

      this.a(var1, var3, 0, 1, 0, 12, 4, 8, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.a(var1, var3, 1, 0, 1, 2, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
      this.a(var1, var3, 4, 0, 1, 5, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
      this.a(var1, var3, 7, 0, 1, 8, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
      this.a(var1, var3, 10, 0, 1, 11, 0, 7, Blocks.farmland.getDefaultState(), Blocks.farmland.getDefaultState(), false);
      this.a(var1, var3, 0, 0, 0, 0, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
      this.a(var1, var3, 6, 0, 0, 6, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
      this.a(var1, var3, 12, 0, 0, 12, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
      this.a(var1, var3, 1, 0, 0, 11, 0, 0, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
      this.a(var1, var3, 1, 0, 8, 11, 0, 8, Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), false);
      this.a(var1, var3, 3, 0, 1, 3, 0, 7, Blocks.water.getDefaultState(), Blocks.water.getDefaultState(), false);
      this.a(var1, var3, 9, 0, 1, 9, 0, 7, Blocks.water.getDefaultState(), Blocks.water.getDefaultState(), false);

      for (int var4 = 1; var4 <= 7; var4++) {
         this.a(var1, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(var2, 2, 7)), 1, 1, var4, var3);
         this.a(var1, this.cropTypeA.getStateFromMeta(MathHelper.getRandomIntegerInRange(var2, 2, 7)), 2, 1, var4, var3);
         this.a(var1, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(var2, 2, 7)), 4, 1, var4, var3);
         this.a(var1, this.cropTypeB.getStateFromMeta(MathHelper.getRandomIntegerInRange(var2, 2, 7)), 5, 1, var4, var3);
         this.a(var1, this.cropTypeC.getStateFromMeta(MathHelper.getRandomIntegerInRange(var2, 2, 7)), 7, 1, var4, var3);
         this.a(var1, this.cropTypeC.getStateFromMeta(MathHelper.getRandomIntegerInRange(var2, 2, 7)), 8, 1, var4, var3);
         this.a(var1, this.cropTypeD.getStateFromMeta(MathHelper.getRandomIntegerInRange(var2, 2, 7)), 10, 1, var4, var3);
         this.a(var1, this.cropTypeD.getStateFromMeta(MathHelper.getRandomIntegerInRange(var2, 2, 7)), 11, 1, var4, var3);
      }

      for (int var6 = 0; var6 < 9; var6++) {
         for (int var5 = 0; var5 < 13; var5++) {
            this.b(var1, var5, 4, var6, var3);
            this.b(var1, Blocks.dirt.getDefaultState(), var5, -1, var6, var3);
         }
      }

      return true;
   }

   @Override
   public void readStructureFromNBT(NBTTagCompound var1) {
      super.readStructureFromNBT(var1);
      this.cropTypeA = Block.getBlockById(var1.getInteger("CA"));
      this.cropTypeB = Block.getBlockById(var1.getInteger("CB"));
      this.cropTypeC = Block.getBlockById(var1.getInteger("CC"));
      this.cropTypeD = Block.getBlockById(var1.getInteger("CD"));
   }

   public StructureVillagePieces$Field1() {
   }

   public Block func_151559_a(Random var1) {
      switch (var1.nextInt(5)) {
         case 0:
            return Blocks.carrots;
         case 1:
            return Blocks.potatoes;
         default:
            return Blocks.wheat;
      }
   }

   @Override
   public void writeStructureToNBT(NBTTagCompound var1) {
      super.writeStructureToNBT(var1);
      var1.setInteger("CA", Block.blockRegistry.getIDForObject(this.cropTypeA));
      var1.setInteger("CB", Block.blockRegistry.getIDForObject(this.cropTypeB));
      var1.setInteger("CC", Block.blockRegistry.getIDForObject(this.cropTypeC));
      var1.setInteger("CD", Block.blockRegistry.getIDForObject(this.cropTypeD));
   }

   public static StructureVillagePieces$Field1 func_175851_a(
      StructureVillagePieces$Start var0, List<StructureComponent> var1, Random var2, int var3, int var4, int var5, EnumFacing var6, int var7
   ) {
      StructureBoundingBox var8 = StructureBoundingBox.getComponentToAddBoundingBox(var3, var4, var5, 0, 0, 0, 13, 4, 9, var6);
      return canVillageGoDeeper(var8) && StructureComponent.findIntersecting(var1, var8) == null
         ? new StructureVillagePieces$Field1(var0, var7, var2, var8, var6)
         : null;
   }

   public StructureVillagePieces$Field1(StructureVillagePieces$Start var1, int var2, Random var3, StructureBoundingBox var4, EnumFacing var5) {
      super(var1, var2);
      this.m = var5;
      this.l = var4;
      this.cropTypeA = this.func_151559_a(var3);
      this.cropTypeB = this.func_151559_a(var3);
      this.cropTypeC = this.func_151559_a(var3);
      this.cropTypeD = this.func_151559_a(var3);
   }
}
