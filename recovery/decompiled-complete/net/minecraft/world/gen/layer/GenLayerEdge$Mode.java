package net.minecraft.world.gen.layer;

import io.netty.handler.codec.compression.SnappyFramedDecoder$ChunkType;
import net.minecraft.client.gui.GuiPageButtonList;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.chunk.VisGraph;
import org.apache.log4j.chainsaw.MyTableModel$1;
import recovered.unidentified.UnidentifiedClass3734;

public enum GenLayerEdge$Mode {
   COOL_WARM,
   SPECIAL,
   HEAT_ICE;

   public UnidentifiedClass3734 field_0007;
   // $VF: synthetic field
   public static GenLayerEdge$Mode[] $VALUES = new GenLayerEdge$Mode[]{COOL_WARM, GenLayerEdge$Mode.HEAT_ICE, GenLayerEdge$Mode.SPECIAL};
   public MyTableModel$1 field_0000;
   public GLAllocation field_0001;
   public VisGraph field_0005;
   public SnappyFramedDecoder$ChunkType field_0002;
   public GuiPageButtonList field_0009;
}
