package io.netty.handler.codec.compression;

import io.netty.util.internal.logging.CommonsLogger;
import net.minecraft.client.renderer.entity.RenderItem$6;
import net.minecraft.init.Bootstrap$9;
import net.minecraft.item.ItemLeaves;

// $VF: synthetic class
public class JdkZlibDecoder$1 {
   public ItemLeaves __junk7818829739705523765;
   public CommonsLogger __junk446349704392972754;
   public RenderItem$6 __junk5822426082128788125;
   public Bootstrap$9 __junk7903638766352951551;

   static {
      try {
         $SwitchMap$io$netty$handler$codec$compression$JdkZlibDecoder$GzipState[JdkZlibDecoder$GzipState.FOOTER_START.ordinal()] = 1;
      } catch (NoSuchFieldError var12) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$compression$JdkZlibDecoder$GzipState[JdkZlibDecoder$GzipState.HEADER_START.ordinal()] = 2;
      } catch (NoSuchFieldError var11) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$compression$JdkZlibDecoder$GzipState[JdkZlibDecoder$GzipState.FLG_READ.ordinal()] = 3;
      } catch (NoSuchFieldError var10) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$compression$JdkZlibDecoder$GzipState[JdkZlibDecoder$GzipState.XLEN_READ.ordinal()] = 4;
      } catch (NoSuchFieldError var9) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$compression$JdkZlibDecoder$GzipState[JdkZlibDecoder$GzipState.SKIP_FNAME.ordinal()] = 5;
      } catch (NoSuchFieldError var8) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$compression$JdkZlibDecoder$GzipState[JdkZlibDecoder$GzipState.SKIP_COMMENT.ordinal()] = 6;
      } catch (NoSuchFieldError var7) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$compression$JdkZlibDecoder$GzipState[JdkZlibDecoder$GzipState.PROCESS_FHCRC.ordinal()] = 7;
      } catch (NoSuchFieldError var6) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$compression$JdkZlibDecoder$GzipState[JdkZlibDecoder$GzipState.HEADER_END.ordinal()] = 8;
      } catch (NoSuchFieldError var5) {
      }

      $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper = new int[ZlibWrapper.values().length];

      try {
         $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.GZIP.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.NONE.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.ZLIB.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[ZlibWrapper.ZLIB_OR_NONE.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
