package net.minecraft.tileentity;

import net.minecraft.block.BlockDaylightDetector;
import net.minecraft.util.ITickable;

public class TileEntityDaylightDetector extends TileEntity implements ITickable {
   @Override
   public void update() {
      if (this.b != null && !this.b.D && this.b.K() % 20L == 0L) {
         this.blockType = this.w();
         if (this.blockType instanceof BlockDaylightDetector) {
            ((BlockDaylightDetector)this.blockType).updatePower(this.b, this.c);
         }
      }
   }
}
