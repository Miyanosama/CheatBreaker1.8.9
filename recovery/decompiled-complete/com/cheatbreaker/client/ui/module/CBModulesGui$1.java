package com.cheatbreaker.client.ui.module;

import io.netty.handler.codec.spdy.SpdyFrameCodec$1;
import io.netty.util.collection.IntObjectHashMap$IteratorImpl;
import net.minecraft.entity.ai.EntityAIRestrictOpenDoor;
import net.minecraft.init.Bootstrap$13;

// $VF: synthetic class
public class CBModulesGui$1 {
   public Bootstrap$13 field_0003;
   public SpdyFrameCodec$1 field_0005;
   public EntityAIRestrictOpenDoor field_0004;
   public IntObjectHashMap$IteratorImpl field_0001;

   static {
      try {
         $SwitchMap$com$cheatbreaker$client$ui$module$CBPositionEnum[CBPositionEnum.LEFT.ordinal()] = 1;
      } catch (NoSuchFieldError var7) {
      }

      try {
         $SwitchMap$com$cheatbreaker$client$ui$module$CBPositionEnum[CBPositionEnum.CENTER.ordinal()] = 2;
      } catch (NoSuchFieldError var6) {
      }

      try {
         $SwitchMap$com$cheatbreaker$client$ui$module$CBPositionEnum[CBPositionEnum.RIGHT.ordinal()] = 3;
      } catch (NoSuchFieldError var5) {
      }

      $SwitchMap$com$cheatbreaker$client$ui$module$SomeRandomAssEnum = new int[SomeRandomAssEnum.values().length];

      try {
         $SwitchMap$com$cheatbreaker$client$ui$module$SomeRandomAssEnum[SomeRandomAssEnum.RIGHT_BOTTOM.ordinal()] = 1;
      } catch (NoSuchFieldError var4) {
      }

      try {
         $SwitchMap$com$cheatbreaker$client$ui$module$SomeRandomAssEnum[SomeRandomAssEnum.LEFT_TOP.ordinal()] = 2;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$com$cheatbreaker$client$ui$module$SomeRandomAssEnum[SomeRandomAssEnum.RIGHT_TOP.ordinal()] = 3;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$com$cheatbreaker$client$ui$module$SomeRandomAssEnum[SomeRandomAssEnum.LEFT_BOTTOM.ordinal()] = 4;
      } catch (NoSuchFieldError var1) {
      }
   }
}
