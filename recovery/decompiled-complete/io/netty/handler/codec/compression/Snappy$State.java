package io.netty.handler.codec.compression;

import net.minecraft.network.NetHandlerPlayServer$2;
import net.optifine.util.ArrayCache;

public enum Snappy$State {
   READING_TAG,
   READING_PREAMBLE,
   READING_LITERAL,
   READING_COPY,
   READY;
   public ArrayCache __junk7091694337699925418;
   public NetHandlerPlayServer$2 __junk2334132595011209862;
   // $VF: synthetic field
   public static Snappy$State[] $VALUES = new Snappy$State[]{Snappy$State.READY, READING_PREAMBLE, READING_TAG, READING_LITERAL, READING_COPY};
}
