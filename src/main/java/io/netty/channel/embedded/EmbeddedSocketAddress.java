package io.netty.channel.embedded;

import io.netty.handler.codec.http.ComposedLastHttpContent;
import java.net.SocketAddress;

public class EmbeddedSocketAddress extends SocketAddress {
   public static final long serialVersionUID = 1400788804624980619L;

   @Override
   public String toString() {
      return "embedded";
   }
}
