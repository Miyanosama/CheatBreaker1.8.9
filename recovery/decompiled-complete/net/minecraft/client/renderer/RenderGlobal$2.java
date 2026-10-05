package net.minecraft.client.renderer;

import io.netty.handler.stream.ChunkedNioStream;
import net.minecraft.client.renderer.vertex.VertexFormatElement$EnumUsage;

// $VF: synthetic class
public class RenderGlobal$2 {
   public ChunkedNioStream field_0000;

   static {
      try {
         $SwitchMap$net$minecraft$client$renderer$vertex$VertexFormatElement$EnumUsage[VertexFormatElement$EnumUsage.POSITION.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$net$minecraft$client$renderer$vertex$VertexFormatElement$EnumUsage[VertexFormatElement$EnumUsage.UV.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$net$minecraft$client$renderer$vertex$VertexFormatElement$EnumUsage[VertexFormatElement$EnumUsage.COLOR.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
