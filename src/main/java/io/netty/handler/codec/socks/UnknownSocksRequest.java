package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditor$1;

public class UnknownSocksRequest extends SocksRequest {

   public UnknownSocksRequest() {
      super(SocksRequestType.UNKNOWN);
   }

   @Override
   public void encodeAsByteBuf(ByteBuf var1) {
   }
}
