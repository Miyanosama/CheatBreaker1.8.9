package io.netty.handler.codec.compression;

import com.jcraft.jzlib.Deflater;
import com.jcraft.jzlib.Inflater;
import com.jcraft.jzlib.JZlib;
import com.jcraft.jzlib.JZlib.WrapperType;
import io.netty.handler.codec.spdy.SpdySessionHandler$3;
import net.minecraft.block.BlockGlowstone;

public class ZlibUtil {
   public SpdySessionHandler$3 __junk5808773828051109387;
   public BlockGlowstone __junk300049004429402430;

   public static void fail(Deflater var0, String var1, int var2) {
      throw deflaterException(var0, var1, var2);
   }

   public static WrapperType convertWrapperType(ZlibWrapper var0) {
      WrapperType var1;
      switch (ZlibUtil$1.$SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[var0.ordinal()]) {
         case 1:
            var1 = JZlib.W_NONE;
            break;
         case 2:
            var1 = JZlib.W_ZLIB;
            break;
         case 3:
            var1 = JZlib.W_GZIP;
            break;
         case 4:
            var1 = JZlib.W_ANY;
            break;
         default:
            throw new Error();
      }

      return var1;
   }

   public static void fail(Inflater var0, String var1, int var2) {
      throw inflaterException(var0, var1, var2);
   }

   public static DecompressionException inflaterException(Inflater var0, String var1, int var2) {
      return new DecompressionException(var1 + " (" + var2 + ')' + (var0.msg != null ? ": " + var0.msg : ""));
   }

   public static CompressionException deflaterException(Deflater var0, String var1, int var2) {
      return new CompressionException(var1 + " (" + var2 + ')' + (var0.msg != null ? ": " + var0.msg : ""));
   }

   public static int wrapperOverhead(ZlibWrapper var0) {
      byte var1;
      switch (ZlibUtil$1.$SwitchMap$io$netty$handler$codec$compression$ZlibWrapper[var0.ordinal()]) {
         case 1:
            var1 = 0;
            break;
         case 2:
         case 4:
            var1 = 2;
            break;
         case 3:
            var1 = 10;
            break;
         default:
            throw new Error();
      }

      return var1;
   }
}
