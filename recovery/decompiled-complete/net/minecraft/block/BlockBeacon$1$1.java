package net.minecraft.block;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World$1;
import org.apache.log4j.pattern.DatePatternConverter$DefaultZoneDateFormat;

public class BlockBeacon$1$1 implements Runnable {
   public World$1 field_0001;
   public DatePatternConverter$DefaultZoneDateFormat field_0000;

   @Override
   public void run() {
      TileEntity var1 = this.field_180368_b.field_180358_a.getTileEntity(this.field_180369_a);
      if (var1 instanceof TileEntityBeacon) {
         ((TileEntityBeacon)var1).updateBeacon();
         this.field_180368_b.field_180358_a.addBlockEvent(this.field_180369_a, Blocks.beacon, 1, 0);
      }
   }

   public BlockBeacon$1$1(BlockBeacon$1 var1, BlockPos var2) {
      this.field_180368_b = var1;
      this.field_180369_a = var2;
      super();
   }
}
