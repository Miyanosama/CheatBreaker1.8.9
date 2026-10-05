package io.netty.channel.oio;

import io.netty.channel.ThreadPerChannelEventLoopGroup;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import net.minecraft.entity.ai.EntitySenses;
import org.apache.log4j.or.ThreadGroupRenderer;

public class OioEventLoopGroup extends ThreadPerChannelEventLoopGroup {

   public OioEventLoopGroup(int var1) {
      this(var1, Executors.defaultThreadFactory());
   }

   public OioEventLoopGroup() {
      this(0);
   }

   public OioEventLoopGroup(int var1, ThreadFactory var2) {
      super(var1, var2);
   }
}
