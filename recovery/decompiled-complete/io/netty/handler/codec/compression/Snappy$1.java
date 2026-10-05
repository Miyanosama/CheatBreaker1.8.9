package io.netty.handler.codec.compression;

import io.netty.channel.AbstractChannel$AbstractUnsafe$4;
import io.netty.handler.codec.spdy.SpdyHeaderBlockRawDecoder$1;
import net.minecraft.block.BlockStoneBrick$EnumType;

// $VF: synthetic class
public class Snappy$1 {
   public SpdyHeaderBlockRawDecoder$1 __junk1326584842942281001;
   public AbstractChannel$AbstractUnsafe$4 __junk7069410030594831255;
   public BlockStoneBrick$EnumType __junk904339802709919854;

   static {
      try {
         $SwitchMap$io$netty$handler$codec$compression$Snappy$State[Snappy$State.READY.ordinal()] = 1;
      } catch (NoSuchFieldError var5) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$compression$Snappy$State[Snappy$State.READING_PREAMBLE.ordinal()] = 2;
      } catch (NoSuchFieldError var4) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$compression$Snappy$State[Snappy$State.READING_TAG.ordinal()] = 3;
      } catch (NoSuchFieldError var3) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$compression$Snappy$State[Snappy$State.READING_LITERAL.ordinal()] = 4;
      } catch (NoSuchFieldError var2) {
      }

      try {
         $SwitchMap$io$netty$handler$codec$compression$Snappy$State[Snappy$State.READING_COPY.ordinal()] = 5;
      } catch (NoSuchFieldError var1) {
      }
   }
}
