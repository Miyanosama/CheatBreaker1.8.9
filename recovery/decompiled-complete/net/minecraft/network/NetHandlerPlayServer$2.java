package net.minecraft.network;

import net.minecraft.world.gen.layer.GenLayerEdge$1;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces$DesertPyramid;
import net.minecraft.world.gen.structure.MapGenStronghold$Start;
import org.apache.log4j.lf5.util.StreamUtils;

public class NetHandlerPlayServer$2 implements Runnable {
   public MapGenStronghold$Start field_0002;
   public GenLayerEdge$1 field_0001;
   public StreamUtils field_0003;
   public ComponentScatteredFeaturePieces$DesertPyramid field_0000;

   public NetHandlerPlayServer$2(NetHandlerPlayServer var1) {
      this.field_0004 = var1;
      super();
   }

   @Override
   public void run() {
      this.field_0004.netManager.checkDisconnected();
   }
}
