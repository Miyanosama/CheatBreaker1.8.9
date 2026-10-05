package net.minecraft.world.gen.structure;

import io.netty.channel.udt.nio.NioUdtMessageConnectorChannel;
import net.minecraft.world.biome.BiomeGenBase;
import recovered.unidentified.UnidentifiedClass3499;

public class ComponentScatteredFeaturePieces {
   public NioUdtMessageConnectorChannel field_0000;
   public BiomeGenBase field_0001;

   public static void registerScatteredFeaturePieces() {
      MapGenStructureIO.registerStructureComponent(ComponentScatteredFeaturePieces$DesertPyramid.class, "TeDP");
      MapGenStructureIO.registerStructureComponent(ComponentScatteredFeaturePieces$JunglePyramid.class, "TeJP");
      MapGenStructureIO.registerStructureComponent(UnidentifiedClass3499.class, "TeSH");
   }
}
