package io.netty.channel.embedded;

import io.netty.handler.codec.http.ComposedLastHttpContent;
import java.net.SocketAddress;

public class EmbeddedSocketAddress extends SocketAddress {
   public static long serialVersionUID;
   public ComposedLastHttpContent __junk8347967034419169363;

   @Override
   public String toString() {
      return "embedded";
   }
}
