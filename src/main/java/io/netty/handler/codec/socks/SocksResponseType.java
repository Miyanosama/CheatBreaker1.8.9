package io.netty.handler.codec.socks;

import net.minecraft.block.state.pattern.FactoryBlockPattern;

public enum SocksResponseType {
      INIT,
      AUTH,
      CMD,
      UNKNOWN;

   public static SocksResponseType[] $VALUES = new SocksResponseType[]{INIT, AUTH, CMD, UNKNOWN};
}
