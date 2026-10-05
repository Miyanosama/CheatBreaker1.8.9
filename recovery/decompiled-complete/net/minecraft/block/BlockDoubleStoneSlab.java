package net.minecraft.block;

import com.cheatbreaker.client.util.HardwareId;
import net.minecraft.client.gui.GuiGameOver;

public class BlockDoubleStoneSlab extends BlockStoneSlab {
   public GuiGameOver field_0000;
   public HardwareId field_0001;

   @Override
   public boolean isDouble() {
      return true;
   }
}
