package net.minecraft.world.gen.structure;

import java.util.Random;
import net.minecraft.client.gui.GuiSlotRealmsProxy;
import net.minecraft.init.Blocks;

public class ComponentScatteredFeaturePieces$JunglePyramid$Stones extends StructureComponent$BlockSelector {
   public GuiSlotRealmsProxy field_0000;

   @Override
   public void selectBlocks(Random var1, int var2, int var3, int var4, boolean var5) {
      if (var1.nextFloat() < 0.4F) {
         this.a = Blocks.cobblestone.getDefaultState();
      } else {
         this.a = Blocks.mossy_cobblestone.getDefaultState();
      }
   }

   public ComponentScatteredFeaturePieces$JunglePyramid$Stones() {
   }
}
