package io.netty.channel;

import io.netty.handler.codec.socks.SocksAuthResponseDecoder;
import junit.framework.TestSuite;

public class DefaultMessageSizeEstimator implements MessageSizeEstimator {
   public SocksAuthResponseDecoder __junk1821276717236179910;
   public TestSuite __junk6096134456372741596;
   public static MessageSizeEstimator DEFAULT = new DefaultMessageSizeEstimator(0);
   public MessageSizeEstimator$Handle handle;

   public DefaultMessageSizeEstimator(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("unknownSize: " + var1 + " (expected: >= 0)");
      } else {
         this.handle = new DefaultMessageSizeEstimator$HandleImpl(var1, null);
      }
   }

   @Override
   public MessageSizeEstimator$Handle newHandle() {
      return this.handle;
   }
}
