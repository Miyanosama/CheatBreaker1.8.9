package io.netty.handler.codec.http;

import io.netty.channel.AbstractServerChannel$DefaultServerUnsafe;
import net.minecraft.world.gen.ChunkProviderSettings;

// $VF: synthetic class
public class HttpContentEncoder$1 {
   public ChunkProviderSettings __junk5306254748141738917;
   public AbstractServerChannel$DefaultServerUnsafe __junk2160384990320095085;

   static {
      try {
         $SwitchMap$io$netty$handler$codec$http$HttpContentEncoder$State[HttpContentEncoder$State.AWAIT_HEADERS.ordinal()] = 1;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$http$HttpContentEncoder$State[HttpContentEncoder$State.AWAIT_CONTENT.ordinal()] = 2;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$http$HttpContentEncoder$State[HttpContentEncoder$State.PASS_THROUGH.ordinal()] = 3;
      } catch (NoSuchFieldError var1) {
      }
   }
}
