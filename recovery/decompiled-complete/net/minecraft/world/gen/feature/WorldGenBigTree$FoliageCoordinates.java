package net.minecraft.world.gen.feature;

import io.netty.channel.embedded.EmbeddedSocketAddress;
import javazoom.jl.decoder.Bitstream;
import net.minecraft.util.BlockPos;
import org.slf4j.helpers.MessageFormatter;

public class WorldGenBigTree$FoliageCoordinates extends BlockPos {
   public Bitstream field_0001;
   public int field_178000_b;
   public EmbeddedSocketAddress field_0000;
   public MessageFormatter field_0003;

   public WorldGenBigTree$FoliageCoordinates(BlockPos var1, int var2) {
      super(var1.getX(), var1.getY(), var1.getZ());
      this.field_178000_b = var2;
   }

   public int func_177999_q() {
      return this.field_178000_b;
   }
}
