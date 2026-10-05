package net.minecraft.block;

import io.netty.channel.udt.nio.NioUdtProvider$1;
import net.minecraft.client.renderer.block.model.FaceBakery;

public enum BlockPressurePlate$Sensitivity {
   EVERYTHING,
   MOBS;
   // $VF: synthetic field
   public static BlockPressurePlate$Sensitivity[] $VALUES = new BlockPressurePlate$Sensitivity[]{
      BlockPressurePlate$Sensitivity.EVERYTHING, BlockPressurePlate$Sensitivity.MOBS
   };
   public NioUdtProvider$1 field_0004;
   public FaceBakery field_0001;
}
