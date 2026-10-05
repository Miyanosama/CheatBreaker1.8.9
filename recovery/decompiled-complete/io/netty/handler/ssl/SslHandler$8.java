package io.netty.handler.ssl;

import javax.net.ssl.SSLEngineResult.HandshakeStatus;
import javax.net.ssl.SSLEngineResult.Status;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.monster.IMob$2;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.stats.StatBase$4;
import recovered.unidentified.UnidentifiedClass1350;

// $VF: synthetic class
public class SslHandler$8 {
   public StatBase$4 __junk7395063753474076323;
   public UnidentifiedClass1350 __junk3192808464552020307;
   public ItemMultiTexture __junk5622122984067391804;
   public EnchantmentHelper __junk4162796287963525880;
   public IMob$2 __junk4964479574055945623;

   static {
      try {
         $SwitchMap$javax$net$ssl$SSLEngineResult$Status[Status.BUFFER_OVERFLOW.ordinal()] = 1;
      } catch (NoSuchFieldError var6) {
      }

      $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus = new int[HandshakeStatus.values().length];

      try {
         $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[HandshakeStatus.NEED_TASK.ordinal()] = 1;
      } catch (NoSuchFieldError var5) {
      }

      try {
         $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[HandshakeStatus.FINISHED.ordinal()] = 2;
      } catch (NoSuchFieldError var4) {
      }

      try {
         $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[HandshakeStatus.NOT_HANDSHAKING.ordinal()] = 3;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[HandshakeStatus.NEED_WRAP.ordinal()] = 4;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[HandshakeStatus.NEED_UNWRAP.ordinal()] = 5;
      } catch (NoSuchFieldError var1) {
      }
   }
}
