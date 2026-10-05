package io.netty.handler.codec.compression;

import net.minecraft.network.play.server.S10PacketSpawnPainting;
import net.minecraft.util.RegistryNamespacedDefaultedByKey;

// $VF: synthetic class
public class ZlibUtil$1 {
   public S10PacketSpawnPainting __junk3988446281025504507;
   public RegistryNamespacedDefaultedByKey __junk5098399289893142436;

   static {
      try {
         $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.NONE.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.ZLIB.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.GZIP.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.ZLIB_OR_NONE.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
