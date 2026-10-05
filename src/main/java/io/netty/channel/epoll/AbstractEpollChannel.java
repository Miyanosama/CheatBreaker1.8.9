package io.netty.channel.epoll;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.AbstractChannel;
import io.netty.channel.Channel;
import io.netty.channel.ChannelMetadata;
import io.netty.channel.EventLoop;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.internal.OneTimeTask;
import java.net.InetSocketAddress;
import java.nio.channels.UnresolvedAddressException;
import net.minecraft.block.BlockBreakable;
import net.minecraft.client.resources.data.TextureMetadataSectionSerializer;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.block.NetherBrickMapColorBlock;

public abstract class AbstractEpollChannel extends AbstractChannel {
   public int id;
   public int readFlag;
   public volatile boolean active;
   public int flags;
   public volatile int fd;
   public static ChannelMetadata DATA = new ChannelMetadata(false);

   @Override
   public void doRegister() throws java.lang.Exception {
      EpollEventLoop var1 = (EpollEventLoop)this.eventLoop();
      var1.add(this);
   }

   public void setEpollOut() {
      if ((this.flags & 2) == 0) {
         this.flags |= 2;
         this.modifyEvents();
      }
   }

   public InetSocketAddress remoteAddress() {
      return (InetSocketAddress)super.remoteAddress();
   }

   @Override
   public ChannelMetadata metadata() {
      return DATA;
   }

   @Override
   public void doDisconnect() throws java.lang.Exception {
      this.doClose();
   }

   public static void checkResolvable(InetSocketAddress var0) {
      if (var0.isUnresolved()) {
         throw new UnresolvedAddressException();
      }
   }

   @Override
   public boolean isCompatible(EventLoop var1) {
      return var1 instanceof EpollEventLoop;
   }

   public void clearEpollOut() {
      if ((this.flags & 2) != 0) {
         this.flags &= -3;
         this.modifyEvents();
      }
   }

   public ByteBuf newDirectBuffer(ByteBuf var1) {
      return this.newDirectBuffer(var1, var1);
   }

   public AbstractEpollChannel(Channel var1, int var2, int var3, boolean var4) {
      super(var1);
      this.fd = var2;
      this.readFlag = var3;
      this.flags |= var3;
      this.active = var4;
   }

   @Override
   public void doBeginRead() throws java.lang.Exception {
      ((AbstractEpollChannel.AbstractEpollUnsafe)this.unsafe()).readPending = true;
      if ((this.flags & this.readFlag) == 0) {
         this.flags = this.flags | this.readFlag;
         this.modifyEvents();
      }
   }

   @Override
   public void doDeregister() throws java.lang.Exception {
      ((EpollEventLoop)this.eventLoop()).remove(this);
   }

   @Override
   public boolean isActive() {
      return this.active;
   }

   public InetSocketAddress localAddress() {
      return (InetSocketAddress)super.localAddress();
   }

   public abstract AbstractEpollChannel.AbstractEpollUnsafe newUnsafe();

   public void modifyEvents() {
      if (this.isOpen()) {
         ((EpollEventLoop)this.eventLoop()).modify(this);
      }
   }

   @Override
   public void doClose() throws java.lang.Exception {
      this.active = false;
      this.doDeregister();
      int var1 = this.fd;
      this.fd = -1;
      Native.close(var1);
   }

   public void clearEpollIn() {
      if (this.isRegistered()) {
         EventLoop var1 = this.eventLoop();
         final AbstractEpollChannel.AbstractEpollUnsafe var2 = (AbstractEpollChannel.AbstractEpollUnsafe)this.unsafe();
         if (var1.inEventLoop()) {
            var2.clearEpollIn0();
         } else {
            var1.execute(new OneTimeTask() {

               @Override
               public void run() {
                  if (!AbstractEpollChannel.this.config().isAutoRead() && !var2.readPending) {
                     var2.clearEpollIn0();
                  }
               }
            });
         }
      } else {
         this.flags = this.flags & ~this.readFlag;
      }
   }

   @Override
   public boolean isOpen() {
      return this.fd != -1;
   }

   public static ByteBuf newDirectBuffer0(Object var0, ByteBuf var1, ByteBufAllocator var2, int var3) {
      ByteBuf var4 = var2.directBuffer(var3);
      var4.writeBytes(var1, var1.readerIndex(), var3);
      ReferenceCountUtil.safeRelease(var0);
      return var4;
   }

   public ByteBuf newDirectBuffer(Object var1, ByteBuf var2) {
      int var3 = var2.readableBytes();
      if (var3 == 0) {
         ReferenceCountUtil.safeRelease(var1);
         return Unpooled.EMPTY_BUFFER;
      } else {
         ByteBufAllocator var4 = this.alloc();
         if (var4.isDirectBufferPooled()) {
            return newDirectBuffer0(var1, var2, var4, var3);
         } else {
            ByteBuf var5 = ByteBufUtil.threadLocalDirectBuffer();
            if (var5 == null) {
               return newDirectBuffer0(var1, var2, var4, var3);
            } else {
               var5.writeBytes(var2, var2.readerIndex(), var3);
               ReferenceCountUtil.safeRelease(var1);
               return var5;
            }
         }
      }
   }

   public AbstractEpollChannel(int var1, int var2) {
      this(null, var1, var2, false);
   }

   public abstract class AbstractEpollUnsafe extends AbstractChannel.AbstractUnsafe {
      public boolean readPending;

      @Override
      public void flush0() {
         if (!this.isFlushPending()) {
            super.flush0();
         }
      }

      public void clearEpollIn0() {
         if ((AbstractEpollChannel.this.flags & AbstractEpollChannel.this.readFlag) != 0) {
            AbstractEpollChannel.this.flags = AbstractEpollChannel.this.flags & ~AbstractEpollChannel.this.readFlag;
            AbstractEpollChannel.this.modifyEvents();
         }
      }

      public void epollRdHupReady() {
      }

      public void epollOutReady() {
         super.flush0();
      }

      public abstract void epollInReady();

      public boolean isFlushPending() {
         return (AbstractEpollChannel.this.flags & 2) != 0;
      }
   }
}
