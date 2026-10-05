package io.netty.channel.sctp.nio;

import com.sun.nio.sctp.SctpChannel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.ChannelOutboundBuffer;
import io.netty.channel.ChannelPromise;
import io.netty.channel.nio.AbstractNioMessageChannel;
import io.netty.channel.sctp.SctpServerChannel;
import io.netty.channel.sctp.SctpServerChannelConfig;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.client.renderer.entity.layers.LayerVillagerArmor;
import net.minecraft.client.resources.ResourcePackRepository$1;
import net.minecraft.world.gen.structure.MapGenNetherBridge;

public class NioSctpServerChannel extends AbstractNioMessageChannel implements SctpServerChannel {
   public SctpServerChannelConfig config = new NioSctpServerChannel$NioSctpServerChannelConfig(this, this, this.javaChannel(), null);
   public ResourcePackRepository$1 __junk4177247596755213897;
   public MapGenNetherBridge __junk4745870892523416536;
   public PingWebSocketFrame __junk991256006057642667;
   public LayerVillagerArmor __junk5239532308977584332;
   public static ChannelMetadata METADATA = new ChannelMetadata(false);

   @Override
   public SocketAddress localAddress0() {
      try {
         Iterator var1 = this.javaChannel().getAllLocalAddresses().iterator();
         if (var1.hasNext()) {
            return (SocketAddress)var1.next();
         }
      } catch (IOException var2) {
      }

      return null;
   }

   @Override
   public ChannelFuture bindAddress(InetAddress var1) {
      return this.bindAddress(var1, this.newPromise());
   }

   @Override
   public void doFinishConnect() {
      throw new UnsupportedOperationException();
   }

   @Override
   public ChannelFuture bindAddress(InetAddress var1, ChannelPromise var2) {
      if (this.eventLoop().inEventLoop()) {
         try {
            this.javaChannel().bindAddress(var1);
            var2.setSuccess();
         } catch (Throwable var4) {
            var2.setFailure(var4);
         }
      } else {
         this.eventLoop().execute(new NioSctpServerChannel$1(this, var1, var2));
      }

      return var2;
   }

   @Override
   public void doDisconnect() {
      throw new UnsupportedOperationException();
   }

   @Override
   public ChannelFuture unbindAddress(InetAddress var1, ChannelPromise var2) {
      if (this.eventLoop().inEventLoop()) {
         try {
            this.javaChannel().unbindAddress(var1);
            var2.setSuccess();
         } catch (Throwable var4) {
            var2.setFailure(var4);
         }
      } else {
         this.eventLoop().execute(new NioSctpServerChannel$2(this, var1, var2));
      }

      return var2;
   }

   @Override
   public int doReadMessages(List<Object> var1) {
      SctpChannel var2 = this.javaChannel().accept();
      if (var2 == null) {
         return 0;
      } else {
         var1.add(new NioSctpChannel(this, var2));
         return 1;
      }
   }

   @Override
   public boolean doConnect(SocketAddress var1, SocketAddress var2) {
      throw new UnsupportedOperationException();
   }

   @Override
   public SctpServerChannelConfig config() {
      return this.config;
   }

   @Override
   public InetSocketAddress localAddress() {
      return (InetSocketAddress)super.localAddress();
   }

   @Override
   public void doClose() {
      this.javaChannel().close();
   }

   public com.sun.nio.sctp.SctpServerChannel javaChannel() {
      return (com.sun.nio.sctp.SctpServerChannel)super.javaChannel();
   }

   @Override
   public boolean isActive() {
      return this.isOpen() && !this.allLocalAddresses().isEmpty();
   }

   @Override
   public void doBind(SocketAddress var1) {
      this.javaChannel().bind(var1, this.config.getBacklog());
   }

   @Override
   public Object filterOutboundMessage(Object var1) {
      throw new UnsupportedOperationException();
   }

   @Override
   public ChannelMetadata metadata() {
      return METADATA;
   }

   @Override
   public ChannelFuture unbindAddress(InetAddress var1) {
      return this.unbindAddress(var1, this.newPromise());
   }

   @Override
   public boolean doWriteMessage(Object var1, ChannelOutboundBuffer var2) {
      throw new UnsupportedOperationException();
   }

   public NioSctpServerChannel() {
      super(null, newSocket(), 16);
   }

   @Override
   public Set<InetSocketAddress> allLocalAddresses() {
      try {
         Set var1 = this.javaChannel().getAllLocalAddresses();
         LinkedHashSet var2 = new LinkedHashSet(var1.size());

         for (SocketAddress var4 : var1) {
            var2.add((InetSocketAddress)var4);
         }

         return var2;
      } catch (Throwable var5) {
         return Collections.emptySet();
      }
   }

   public static com.sun.nio.sctp.SctpServerChannel newSocket() {
      try {
         return com.sun.nio.sctp.SctpServerChannel.open();
      } catch (IOException var1) {
         throw new ChannelException("Failed to open a server socket.", var1);
      }
   }

   public InetSocketAddress remoteAddress() {
      return null;
   }

   @Override
   public SocketAddress remoteAddress0() {
      return null;
   }
}
