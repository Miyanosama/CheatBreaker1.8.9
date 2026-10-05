package io.netty.handler.codec.socks;

import net.minecraft.client.gui.GuiCommandBlock;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$11;
import recovered.unidentified.UnidentifiedClass3405;

// $VF: synthetic class
public class SocksCmdResponseDecoder$1 {
   public UnidentifiedClass3405 __junk8057584231342783074;
   public LogBrokerMonitor$11 __junk7003125438442156084;
   public GuiCommandBlock __junk2480798982657840470;

   static {
      try {
         $SwitchMap$io$netty$handler$codec$socks$SocksCmdResponseDecoder$State[SocksCmdResponseDecoder$State.CHECK_PROTOCOL_VERSION.ordinal()] = 1;
      } catch (NoSuchFieldError var7) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$socks$SocksCmdResponseDecoder$State[SocksCmdResponseDecoder$State.READ_CMD_HEADER.ordinal()] = 2;
      } catch (NoSuchFieldError var6) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$socks$SocksCmdResponseDecoder$State[SocksCmdResponseDecoder$State.READ_CMD_ADDRESS.ordinal()] = 3;
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
