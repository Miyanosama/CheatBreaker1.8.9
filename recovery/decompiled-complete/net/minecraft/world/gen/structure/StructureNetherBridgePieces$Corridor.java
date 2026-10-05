package net.minecraft.world.gen.structure;

import com.cheatbreaker.client.module.type.notifications.CBNotificationRenderer;
import java.util.List;
import java.util.Random;
import javazoom.jl.decoder.Manager;
import net.minecraft.client.gui.GuiPageButtonList$GuiEntry;
import net.minecraft.client.particle.EntitySmokeFX$Factory;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class StructureNetherBridgePieces$Corridor extends StructureNetherBridgePieces$Piece {
   public GuiPageButtonList$GuiEntry field_0003;
   public Manager field_0005;
   public EntityWitch field_0002;
   public boolean field_111021_b;
   public CBNotificationRenderer field_0000;
   public EntitySmokeFX$Factory field_0001;

   @Override
   public boolean addComponentParts(World var1, Random var2, StructureBoundingBox var3) {
      this.a(var1, var3, 0, 0, 0, 4, 1, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
      this.a(var1, var3, 0, 2, 0, 4, 5, 4, Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
      this.a(var1, var3, 4, 2, 0, 4, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
      this.a(var1, var3, 4, 3, 1, 4, 4, 1, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
      this.a(var1, var3, 4, 3, 3, 4, 4, 3, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick_fence.getDefaultState(), false);
      this.a(var1, var3, 0, 2, 0, 0, 5, 0, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
      this.a(var1, var3, 0, 2, 4, 3, 5, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
      this.a(var1, var3, 1, 3, 4, 1, 4, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
      this.a(var1, var3, 3, 3, 4, 3, 4, 4, Blocks.nether_brick_fence.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);
      if (this.field_111021_b && var3.isVecInside(new BlockPos(this.a(3, 3), this.d(2), this.b(3, 3)))) {
         this.field_111021_b = false;
         this.generateChestContents(var1, var3, var2, 3, 2, 3, a, 2 + var2.nextInt(4));
      }

      this.a(var1, var3, 0, 6, 0, 4, 6, 4, Blocks.nether_brick.getDefaultState(), Blocks.nether_brick.getDefaultState(), false);

      for (int var4 = 0; var4 <= 4; var4++) {
         for (int var5 = 0; var5 <= 4; var5++) {
            this.b(var1, Blocks.nether_brick.getDefaultState(), var4, -1, var5, var3);
         }
      }

      return true;
   }

   @Override
   public void buildComponent(StructureComponent var1, List<StructureComponent> var2, Random var3) {
      this.getNextComponentX((StructureNetherBridgePieces$Start)var1, var2, var3, 0, 1, true);
   }

   @Override
   public void writeStructureToNBT(NBTTagCompound var1) {
      super.writeStructureToNBT(var1);
      var1.setBoolean("Chest", this.field_111021_b);
   }

   public static StructureNetherBridgePieces$Corridor func_175879_a(
      List<StructureComponent> var0, Random var1, int var2, int var3, int var4, EnumFacing var5, int var6
   ) {
      StructureBoundingBox var7 = StructureBoundingBox.getComponentToAddBoundingBox(var2, var3, var4, -1, 0, 0, 5, 7, 5, var5);
      return isAboveGround(var7) && StructureComponent.findIntersecting(var0, var7) == null
         ? new StructureNetherBridgePieces$Corridor(var6, var1, var7, var5)
         : null;
   }

   @Override
   public void readStructureFromNBT(NBTTagCompound var1) {
      super.readStructureFromNBT(var1);
      this.field_111021_b = var1.getBoolean("Chest");
   }

   public StructureNetherBridgePieces$Corridor() {
   }

   public StructureNetherBridgePieces$Corridor(int var1, Random var2, StructureBoundingBox var3, EnumFacing var4) {
      super(var1);
      this.m = var4;
      this.l = var3;
      this.field_111021_b = var2.nextInt(3) == 0;
   }
}
