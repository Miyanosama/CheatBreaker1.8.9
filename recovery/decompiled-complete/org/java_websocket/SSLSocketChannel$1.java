package org.java_websocket;

import javax.net.ssl.SSLEngineResult.HandshakeStatus;
import javax.net.ssl.SSLEngineResult.Status;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.util.EnumFacing$AxisDirection;

// $VF: synthetic class
public class SSLSocketChannel$1 {
   public EnumFacing$AxisDirection field_0000;
   public EntitySpider field_0002;

   static {
      try {
         $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[HandshakeStatus.FINISHED.ordinal()] = 1;
      } catch (NoSuchFieldError var9) {
      }

      try {
         $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[HandshakeStatus.NEED_UNWRAP.ordinal()] = 2;
      } catch (NoSuchFieldError var8) {
      }

      try {
         $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[HandshakeStatus.NEED_WRAP.ordinal()] = 3;
      } catch (NoSuchFieldError var7) {
      }

      try {
         $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[HandshakeStatus.NEED_TASK.ordinal()] = 4;
      } catch (NoSuchFieldError var6) {
      }

      try {
         $SwitchMap$javax$net$ssl$SSLEngineResult$HandshakeStatus[HandshakeStatus.NOT_HANDSHAKING.ordinal()] = 5;
      } catch (NoSuchFieldError var5) {
      }

      $SwitchMap$javax$net$ssl$SSLEngineResult$Status = new int[Status.values().length];

      try {
         $SwitchMap$javax$net$ssl$SSLEngineResult$Status[Status.OK.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         $SwitchMap$javax$net$ssl$SSLEngineResult$Status[Status.BUFFER_UNDERFLOW.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$javax$net$ssl$SSLEngineResult$Status[Status.BUFFER_OVERFLOW.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$javax$net$ssl$SSLEngineResult$Status[Status.CLOSED.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
