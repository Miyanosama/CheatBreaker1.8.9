package io.netty.handler.codec.socks;

import io.netty.util.internal.ThreadLocalRandom$1;
import net.minecraft.block.state.pattern.BlockPattern;
import recovered.unidentified.UnidentifiedClass0878;

// $VF: synthetic class
public class SocksCmdRequestDecoder$1 {
   public BlockPattern __junk3503408871059565664;
   public ThreadLocalRandom$1 __junk6462956140360595110;
   public UnidentifiedClass0878 __junk5738674197466376926;

   static {
      try {
         $SwitchMap$io$netty$handler$codec$socks$SocksCmdRequestDecoder$State[SocksCmdRequestDecoder$State.CHECK_PROTOCOL_VERSION.ordinal()] = 1;
      } catch (NoSuchFieldError var7) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$socks$SocksCmdRequestDecoder$State[SocksCmdRequestDecoder$State.READ_CMD_HEADER.ordinal()] = 2;
      } catch (NoSuchFieldError var6) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$socks$SocksCmdRequestDecoder$State[SocksCmdRequestDecoder$State.READ_CMD_ADDRESS.ordinal()] = 3;
      } catch (NoSuchFieldError var5) {
      }

      $SwitchMap$io$netty$handler$codec$socks$SocksAddressType = new int[SocksAddressType.values().length];

      try {
         $SwitchMap$io$netty$handler$codec$socks$SocksAddressType[SocksAddressType.IPv4.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$socks$SocksAddressType[SocksAddressType.DOMAIN.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$socks$SocksAddressType[SocksAddressType.IPv6.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$socks$SocksAddressType[SocksAddressType.UNKNOWN.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
