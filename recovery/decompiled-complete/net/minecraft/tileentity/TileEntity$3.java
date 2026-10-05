package net.minecraft.tileentity;

import java.util.concurrent.Callable;
import net.minecraft.block.BlockSilverfish$1;
import net.minecraft.block.state.IBlockState;
import net.minecraft.network.play.server.S38PacketPlayerListItem$Action;
import org.apache.log4j.spi.VectorWriter;

public class TileEntity$3 implements Callable<String> {
   public BlockSilverfish$1 field_0001;
   public S38PacketPlayerListItem$Action field_0003;
   public VectorWriter field_0000;

   public TileEntity$3(TileEntity var1) {
      this.field_150834_a = var1;
      super();
   }

   public String call() {
      IBlockState var1 = this.field_150834_a.b.getBlockState(this.field_150834_a.c);
      int var2 = var1.getBlock().getMetaFromState(var1);
      if (var2 < 0) {
         return "Unknown? (Got " + var2 + ")";
      } else {
         String var3 = String.format("%4s", Integer.toBinaryString(var2)).replace(" ", "0");
         return String.format("%1$d / 0x%1$X / 0b%2$s", var2, var3);
      }
   }
}
