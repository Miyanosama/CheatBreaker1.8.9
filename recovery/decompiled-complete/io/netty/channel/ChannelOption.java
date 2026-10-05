package io.netty.channel;

import io.netty.buffer.ByteBufAllocator;
import io.netty.util.UniqueName;
import io.netty.util.internal.PlatformDependent;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.concurrent.ConcurrentMap;
import net.minecraft.client.model.ModelSnowMan;
import net.optifine.entity.model.anim.ModelVariableType;

public class ChannelOption<T> extends UniqueName {
   public static ChannelOption<Integer> WRITE_BUFFER_LOW_WATER_MARK = valueOf("WRITE_BUFFER_LOW_WATER_MARK");
   public static ChannelOption<Boolean> AUTO_READ = valueOf("AUTO_READ");
   public static ChannelOption<Integer> SO_SNDBUF = valueOf("SO_SNDBUF");
   public static ChannelOption<NetworkInterface> IP_MULTICAST_IF = valueOf("IP_MULTICAST_IF");
   public static ChannelOption<Boolean> AUTO_CLOSE = valueOf("AUTO_CLOSE");
   public static ChannelOption<Integer> WRITE_SPIN_COUNT = valueOf("WRITE_SPIN_COUNT");
   public static ChannelOption<InetAddress> IP_MULTICAST_ADDR = valueOf("IP_MULTICAST_ADDR");
   public static ChannelOption<RecvByteBufAllocator> RCVBUF_ALLOCATOR = valueOf("RCVBUF_ALLOCATOR");
   public static ChannelOption<Long> AIO_WRITE_TIMEOUT = valueOf("AIO_WRITE_TIMEOUT");
   public static ChannelOption<Long> AIO_READ_TIMEOUT = valueOf("AIO_READ_TIMEOUT");
   public ModelSnowMan __junk2550114632402327691;
   public static ChannelOption<Integer> WRITE_BUFFER_HIGH_WATER_MARK = valueOf("WRITE_BUFFER_HIGH_WATER_MARK");
   public static ChannelOption<Integer> IP_MULTICAST_TTL = valueOf("IP_MULTICAST_TTL");
   public static ChannelOption<Boolean> IP_MULTICAST_LOOP_DISABLED = valueOf("IP_MULTICAST_LOOP_DISABLED");
   public ModelVariableType __junk8790119286765117711;
   public static ChannelOption<Integer> SO_RCVBUF = valueOf("SO_RCVBUF");
   public static ChannelOption<Boolean> ALLOW_HALF_CLOSURE = valueOf("ALLOW_HALF_CLOSURE");
   public static ChannelOption<Boolean> SO_KEEPALIVE = valueOf("SO_KEEPALIVE");
   public static ChannelOption<Boolean> SO_BROADCAST = valueOf("SO_BROADCAST");
   public static ChannelOption<Boolean> TCP_NODELAY = valueOf("TCP_NODELAY");
   public static ChannelOption<ByteBufAllocator> ALLOCATOR = valueOf("ALLOCATOR");
   public static ChannelOption<Integer> MAX_MESSAGES_PER_READ = valueOf("MAX_MESSAGES_PER_READ");
   public static ChannelOption<Boolean> SO_REUSEADDR = valueOf("SO_REUSEADDR");
   public static ChannelOption<Integer> SO_TIMEOUT = valueOf("SO_TIMEOUT");
   public static ChannelOption<Integer> IP_TOS = valueOf("IP_TOS");
   public static ChannelOption<MessageSizeEstimator> MESSAGE_SIZE_ESTIMATOR = valueOf("MESSAGE_SIZE_ESTIMATOR");
   public static ChannelOption<Integer> CONNECT_TIMEOUT_MILLIS = valueOf("CONNECT_TIMEOUT_MILLIS");
   public static ChannelOption<Boolean> DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION = valueOf("DATAGRAM_CHANNEL_ACTIVE_ON_REGISTRATION");
   public static ChannelOption<Integer> SO_BACKLOG = valueOf("SO_BACKLOG");
   public static ConcurrentMap<String, Boolean> names = PlatformDependent.newConcurrentHashMap();
   public static ChannelOption<Integer> SO_LINGER = valueOf("SO_LINGER");

   public ChannelOption(String var1) {
      super(names, var1);
   }

   public static <T> ChannelOption<T> valueOf(String var0) {
      return new ChannelOption<>(var0);
   }

   public void validate(T var1) {
      if (var1 == null) {
         throw new NullPointerException("value");
      }
   }
}
