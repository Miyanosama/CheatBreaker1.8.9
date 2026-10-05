package io.netty.handler.ssl;

import io.netty.handler.codec.http.HttpClientCodec$Decoder;
import net.minecraft.block.properties.PropertyEnum;

// $VF: synthetic class
public class SslContext$1 {
   public HttpClientCodec$Decoder __junk1051634668790061137;
   public PropertyEnum __junk8032183287106700767;

   static {
      try {
         $SwitchMap$io$netty$handler$ssl$SslProvider[SslProvider.JDK.ordinal()] = 1;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$io$netty$handler$ssl$SslProvider[SslProvider.OPENSSL.ordinal()] = 2;
      } catch (NoSuchFieldError var1) {
      }
   }
}
