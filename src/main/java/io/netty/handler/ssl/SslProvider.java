package io.netty.handler.ssl;

import io.netty.handler.codec.socks.SocksMessageType;

public enum SslProvider {
   JDK,
   OPENSSL;
   public static SslProvider[] $VALUES = new SslProvider[]{JDK, SslProvider.OPENSSL};
}
