package io.netty.handler.codec.socks;

import net.minecraft.network.play.server.S2DPacketOpenWindow;
import net.optifine.expr.ExpressionType;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$3;
import org.apache.log4j.pattern.FileDatePatternConverter;
import recovered.unidentified.UnidentifiedClass1658;

// $VF: synthetic class
public class SocksCmdRequest$1 {
   public ExpressionType __junk4322682329271696247;
   public FileDatePatternConverter __junk2266733840967875827;
   public S2DPacketOpenWindow __junk6389507662094021895;
   public UnidentifiedClass1658 __junk3583255345637028482;
   public LogBrokerMonitor$3 __junk7306281066114151756;

   static {
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
