package io.netty.handler.codec.http;

import io.netty.handler.codec.compression.ZlibWrapper;
import net.minecraft.client.renderer.entity.RenderItem$7;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Prison;

// $VF: synthetic class
public class HttpContentCompressor$1 {
   public RenderItem$7 __junk2240580270877149229;
   public StructureStrongholdPieces$Prison __junk2517215790444459979;

   static {
      try {
         $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.GZIP.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.ZLIB.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
      }
   }
}
