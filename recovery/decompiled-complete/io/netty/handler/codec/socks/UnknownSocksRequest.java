package io.netty.handler.codec.socks;

import io.netty.buffer.ByteBuf;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceValuesToIntTask;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditor$1;

public class UnknownSocksRequest extends SocksRequest {
   public ConcurrentHashMapV8$MapReduceValuesToIntTask __junk6095539300251033626;
   public CategoryNodeEditor$1 __junk5567957826734474815;

   public UnknownSocksRequest() {
      super(SocksRequestType.UNKNOWN);
   }

   @Override
   public void encodeAsByteBuf(ByteBuf var1) {
   }
}
