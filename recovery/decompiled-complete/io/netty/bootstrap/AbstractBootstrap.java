package io.netty.bootstrap;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPromise;
import io.netty.channel.DefaultChannelPromise;
import io.netty.channel.EventLoopGroup;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.GlobalEventExecutor;
import io.netty.util.internal.StringUtil;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.LinkedHashMap;
import java.util.Map;
import net.minecraft.client.renderer.EnumFaceDirection$VertexInformation;
import net.minecraft.entity.item.EntityFireworkRocket;

public abstract class AbstractBootstrap<B extends AbstractBootstrap<B, C>, C extends Channel> implements Cloneable {
   public Map<ChannelOption<?>, Object> options = new LinkedHashMap<>();
   public volatile EventLoopGroup group;
   public EntityFireworkRocket __junk7485773020095185552;
   public volatile SocketAddress localAddress;
   public EnumFaceDirection$VertexInformation __junk5762320049176954401;
   public volatile ChannelHandler handler;
   public Map<AttributeKey<?>, Object> attrs = new LinkedHashMap<>();
   public volatile ChannelFactory<? extends C> channelFactory;

   public ChannelFactory<? extends C> channelFactory() {
      return this.channelFactory;
   }

   public ChannelFuture register() {
      this.validate();
      return this.initAndRegister();
   }

   public static void doBind0(ChannelFuture var0, Channel var1, SocketAddress var2, ChannelPromise var3) {
      var1.eventLoop().execute(new AbstractBootstrap$2(var0, var1, var2, var3));
   }

   public ChannelFuture doBind(SocketAddress var1) {
      ChannelFuture var2 = this.initAndRegister();
      Channel var3 = var2.channel();
      if (var2.cause() != null) {
         return var2;
      } else {
         Object var4;
         if (var2.isDone()) {
            var4 = var3.newPromise();
            doBind0(var2, var3, var1, (ChannelPromise)var4);
         } else {
            var4 = new AbstractBootstrap$PendingRegistrationPromise(var3, null);
            var2.addListener(new AbstractBootstrap$1(this, var2, var3, var1, (ChannelPromise)var4));
         }

         return (ChannelFuture)var4;
      }
   }

   public B validate() {
      if (this.group == null) {
         throw new IllegalStateException("group not set");
      } else if (this.channelFactory == null) {
         throw new IllegalStateException("channel or channelFactory not set");
      } else {
         return (B)this;
      }
   }

   public SocketAddress localAddress() {
      return this.localAddress;
   }

   public B channelFactory(ChannelFactory<? extends C> var1) {
      if (var1 == null) {
         throw new NullPointerException("channelFactory");
      } else if (this.channelFactory != null) {
         throw new IllegalStateException("channelFactory set already");
      } else {
         this.channelFactory = var1;
         return (B)this;
      }
   }

   public <T> B option(ChannelOption<T> var1, T var2) {
      if (var1 == null) {
         throw new NullPointerException("option");
      } else {
         if (var2 == null) {
            synchronized (this.options) {
               this.options.remove(var1);
            }
         } else {
            synchronized (this.options) {
               this.options.put(var1, var2);
            }
         }

         return (B)this;
      }
   }

   public abstract void init(Channel var1);

   public B localAddress(int var1) {
      return this.localAddress(new InetSocketAddress(var1));
   }

   public EventLoopGroup group() {
      return this.group;
   }

   public Map<AttributeKey<?>, Object> attrs() {
      return this.attrs;
   }

   public B localAddress(InetAddress var1, int var2) {
      return this.localAddress(new InetSocketAddress(var1, var2));
   }

   public ChannelFuture bind(SocketAddress var1) {
      this.validate();
      if (var1 == null) {
         throw new NullPointerException("localAddress");
      } else {
         return this.doBind(var1);
      }
   }

   public ChannelFuture bind() {
      this.validate();
      SocketAddress var1 = this.localAddress;
      if (var1 == null) {
         throw new IllegalStateException("localAddress not set");
      } else {
         return this.doBind(var1);
      }
   }

   public ChannelFuture bind(int var1) {
      return this.bind(new InetSocketAddress(var1));
   }

   public B localAddress(String var1, int var2) {
      return this.localAddress(new InetSocketAddress(var1, var2));
   }

   public abstract B clone();

   public B localAddress(SocketAddress var1) {
      this.localAddress = var1;
      return (B)this;
   }

   public B handler(ChannelHandler var1) {
      if (var1 == null) {
         throw new NullPointerException("handler");
      } else {
         this.handler = var1;
         return (B)this;
      }
   }

   public B group(EventLoopGroup var1) {
      if (var1 == null) {
         throw new NullPointerException("group");
      } else if (this.group != null) {
         throw new IllegalStateException("group set already");
      } else {
         this.group = var1;
         return (B)this;
      }
   }

   public ChannelHandler handler() {
      return this.handler;
   }

   public ChannelFuture bind(InetAddress var1, int var2) {
      return this.bind(new InetSocketAddress(var1, var2));
   }

   public Map<ChannelOption<?>, Object> options() {
      return this.options;
   }

   public ChannelFuture bind(String var1, int var2) {
      return this.bind(new InetSocketAddress(var1, var2));
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(StringUtil.simpleClassName(this));
      var1.append('(');
      if (this.group != null) {
         var1.append("group: ");
         var1.append(StringUtil.simpleClassName(this.group));
         var1.append(", ");
      }

      if (this.channelFactory != null) {
         var1.append("channelFactory: ");
         var1.append(this.channelFactory);
         var1.append(", ");
      }

      if (this.localAddress != null) {
         var1.append("localAddress: ");
         var1.append(this.localAddress);
         var1.append(", ");
      }

      synchronized (this.options) {
         if (!this.options.isEmpty()) {
            var1.append("options: ");
            var1.append(this.options);
            var1.append(", ");
         }
      }

      synchronized (this.attrs) {
         if (!this.attrs.isEmpty()) {
            var1.append("attrs: ");
            var1.append(this.attrs);
            var1.append(", ");
         }
      }

      if (this.handler != null) {
         var1.append("handler: ");
         var1.append(this.handler);
         var1.append(", ");
      }

      if (var1.charAt(var1.length() - 1) == '(') {
         var1.append(')');
      } else {
         var1.setCharAt(var1.length() - 2, ')');
         var1.setLength(var1.length() - 1);
      }

      return var1.toString();
   }

   public ChannelFuture initAndRegister() {
      Channel var1 = this.channelFactory().newChannel();

      try {
         this.init(var1);
      } catch (Throwable var3) {
         var1.unsafe().closeForcibly();
         return new DefaultChannelPromise(var1, GlobalEventExecutor.INSTANCE).setFailure(var3);
      }

      ChannelFuture var2 = this.group().register(var1);
      if (var2.cause() != null) {
         if (var1.isRegistered()) {
            var1.close();
         } else {
            var1.unsafe().closeForcibly();
         }
      }

      return var2;
   }

   public AbstractBootstrap(AbstractBootstrap<B, C> var1) {
      this.group = var1.group;
      this.channelFactory = var1.channelFactory;
      this.handler = var1.handler;
      this.localAddress = var1.localAddress;
      synchronized (var1.options) {
         this.options.putAll(var1.options);
      }

      synchronized (var1.attrs) {
         this.attrs.putAll(var1.attrs);
      }
   }

   public AbstractBootstrap() {
   }

   public B channel(Class<? extends C> var1) {
      if (var1 == null) {
         throw new NullPointerException("channelClass");
      } else {
         return this.channelFactory(new AbstractBootstrap$BootstrapChannelFactory<>(var1));
      }
   }

   public <T> B attr(AttributeKey<T> var1, T var2) {
      if (var1 == null) {
         throw new NullPointerException("key");
      } else {
         if (var2 == null) {
            synchronized (this.attrs) {
               this.attrs.remove(var1);
            }
         } else {
            synchronized (this.attrs) {
               this.attrs.put(var1, var2);
            }
         }

         return (B)this;
      }
   }
}
