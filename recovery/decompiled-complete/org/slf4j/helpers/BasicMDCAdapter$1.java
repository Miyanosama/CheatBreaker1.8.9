package org.slf4j.helpers;

import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.PooledUnsafeDirectByteBuf$1;
import io.netty.channel.ChannelOutboundBuffer$Entry;
import java.util.HashMap;
import java.util.Map;

public class BasicMDCAdapter$1 extends InheritableThreadLocal<Map<String, String>> {
   public ChannelOutboundBuffer$Entry field_0001;
   public EmptyByteBuf field_0003;
   public PooledUnsafeDirectByteBuf$1 field_0002;

   public Map<String, String> childValue(Map<String, String> var1) {
      return var1 == null ? null : new HashMap<>(var1);
   }

   public BasicMDCAdapter$1(BasicMDCAdapter var1) {
      this.this$0 = var1;
      super();
   }
}
