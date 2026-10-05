package io.netty.handler.codec.socks;

import io.netty.handler.codec.ByteToMessageCodec$1;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.optifine.LightMap;
import org.apache.log4j.chainsaw.ControlPanel$5;
import recovered.unidentified.UnidentifiedClass4786;

// $VF: synthetic class
public class SocksAuthRequestDecoder$1 {
   public ByteToMessageCodec$1 __junk664260023940249171;
   public LightMap __junk1672236258513967206;
   public GenLayerZoom __junk4670702502506078850;
   public ControlPanel$5 __junk951035455831571792;
   public UnidentifiedClass4786 __junk8783447477817096255;

   static {
      try {
         $SwitchMap$io$netty$handler$codec$socks$SocksAuthRequestDecoder$State[SocksAuthRequestDecoder$State.CHECK_PROTOCOL_VERSION.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$socks$SocksAuthRequestDecoder$State[SocksAuthRequestDecoder$State.READ_USERNAME.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$socks$SocksAuthRequestDecoder$State[SocksAuthRequestDecoder$State.READ_PASSWORD.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
