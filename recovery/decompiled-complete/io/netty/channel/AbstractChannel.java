package io.netty.channel;

import io.netty.buffer.ByteBufAllocator;
import io.netty.util.DefaultAttributeMap;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.ThreadLocalRandom;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.SocketAddress;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.NotYetConnectedException;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$PieceWeight;

public abstract class AbstractChannel extends DefaultAttributeMap implements Channel {
   public MessageSizeEstimator$Handle estimatorHandle;
   public long hashCode = ThreadLocalRandom.current().nextLong();
   public static ClosedChannelException CLOSED_CHANNEL_EXCEPTION = new ClosedChannelException();
   public VoidChannelPromise unsafeVoidPromise;
   public AbstractChannel$CloseFuture closeFuture;
   public String strVal;
   public static NotYetConnectedException NOT_YET_CONNECTED_EXCEPTION = new NotYetConnectedException();
   public ChannelFuture succeededFuture = new SucceededChannelFuture(this, null);
   public volatile EventLoop eventLoop;
   public boolean strValActive;
   public Channel parent;
   public volatile boolean registered;
   public Channel$Unsafe unsafe;
   public volatile SocketAddress localAddress;
   public DefaultChannelPipeline pipeline;
   public volatile SocketAddress remoteAddress;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(AbstractChannel.class);
   public StructureStrongholdPieces$PieceWeight __junk2524263979650889018;
   public VoidChannelPromise voidPromise = new VoidChannelPromise(this, true);

   @Override
   public ChannelFuture write(Object var1) {
      return this.pipeline.write(var1);
   }

   public void doDeregister() {
   }

   @Override
   public ChannelFuture writeAndFlush(Object var1, ChannelPromise var2) {
      return this.pipeline.writeAndFlush(var1, var2);
   }

   @Override
   public Channel read() {
      this.pipeline.read();
      return this;
   }

   public abstract void doBind(SocketAddress var1);

   public abstract void doDisconnect();

   @Override
   public ChannelFuture newSucceededFuture() {
      return this.succeededFuture;
   }

   @Override
   public Channel$Unsafe unsafe() {
      return this.unsafe;
   }

   public void doRegister() {
   }

   @Override
   public ChannelFuture connect(SocketAddress var1, SocketAddress var2, ChannelPromise var3) {
      return this.pipeline.connect(var1, var2, var3);
   }

   @Override
   public boolean isWritable() {
      ChannelOutboundBuffer var1 = this.unsafe.outboundBuffer();
      return var1 != null && var1.isWritable();
   }

   @Override
   public Channel parent() {
      return this.parent;
   }

   @Override
   public SocketAddress remoteAddress() {
      SocketAddress var1 = this.remoteAddress;
      if (var1 == null) {
         try {
            this.remoteAddress = var1 = this.unsafe().remoteAddress();
         } catch (Throwable var3) {
            return null;
         }
      }

      return var1;
   }

   @Override
   public SocketAddress localAddress() {
      SocketAddress var1 = this.localAddress;
      if (var1 == null) {
         try {
            this.localAddress = var1 = this.unsafe().localAddress();
         } catch (Throwable var3) {
            return null;
         }
      }

      return var1;
   }

   @Override
   public Channel flush() {
      this.pipeline.flush();
      return this;
   }

   public abstract boolean isCompatible(EventLoop var1);

   @Override
   public ChannelFuture newFailedFuture(Throwable var1) {
      return new FailedChannelFuture(this, null, var1);
   }

   public abstract void doBeginRead();

   @Override
   public ChannelFuture closeFuture() {
      return this.closeFuture;
   }

   @Override
   public ChannelFuture write(Object var1, ChannelPromise var2) {
      return this.pipeline.write(var1, var2);
   }

   @Override
   public ChannelFuture disconnect() {
      return this.pipeline.disconnect();
   }

   @Override
   public ChannelFuture deregister(ChannelPromise var1) {
      return this.pipeline.deregister(var1);
   }

   @Override
   public ChannelFuture close() {
      return this.pipeline.close();
   }

   @Override
   public ChannelFuture close(ChannelPromise var1) {
      return this.pipeline.close(var1);
   }

   @Override
   public ChannelProgressivePromise newProgressivePromise() {
      return new DefaultChannelProgressivePromise(this);
   }

   @Override
   public String toString() {
      boolean var1 = this.isActive();
      if (this.strValActive == var1 && this.strVal != null) {
         return this.strVal;
      } else {
         SocketAddress var2 = this.remoteAddress();
         SocketAddress var3 = this.localAddress();
         if (var2 != null) {
            SocketAddress var4;
            SocketAddress var5;
            if (this.parent == null) {
               var4 = var3;
               var5 = var2;
            } else {
               var4 = var2;
               var5 = var3;
            }

            this.strVal = String.format("[id: 0x%08x, %s %s %s]", (int)this.hashCode, var4, var1 ? "=>" : ":>", var5);
         } else if (var3 != null) {
            this.strVal = String.format("[id: 0x%08x, %s]", (int)this.hashCode, var3);
         } else {
            this.strVal = String.format("[id: 0x%08x]", (int)this.hashCode);
         }

         this.strValActive = var1;
         return this.strVal;
      }
   }

   @Override
   public ChannelPromise voidPromise() {
      return this.voidPromise;
   }

   public void invalidateRemoteAddress() {
      this.remoteAddress = null;
   }

   public AbstractChannel(Channel var1) {
      this.unsafeVoidPromise = new VoidChannelPromise(this, false);
      this.closeFuture = new AbstractChannel$CloseFuture(this);
      this.parent = var1;
      this.unsafe = this.newUnsafe();
      this.pipeline = new DefaultChannelPipeline(this);
   }

   public abstract void doWrite(ChannelOutboundBuffer var1);

   @Override
   public ChannelFuture connect(SocketAddress var1, SocketAddress var2) {
      return this.pipeline.connect(var1, var2);
   }

   public int compareTo(Channel var1) {
      if (this == var1) {
         return 0;
      } else {
         long var2 = this.hashCode - var1.hashCode();
         if (var2 > (43648194L & 809523232L)) {
            return 1;
         } else if (var2 < (5407286054553168780L & 1091108864L)) {
            return -1;
         } else {
            var2 = System.identityHashCode(this) - System.identityHashCode(var1);
            if (var2 != (140509312L & 7133881895149961317L)) {
               return (int)var2;
            } else {
               throw new Error();
            }
         }
      }
   }

   @Override
   public ChannelFuture bind(SocketAddress var1) {
      return this.pipeline.bind(var1);
   }

   public abstract SocketAddress remoteAddress0();

   @Override
   public int hashCode() {
      return (int)this.hashCode;
   }

   static {
      CLOSED_CHANNEL_EXCEPTION.setStackTrace(EmptyArrays.EMPTY_STACK_TRACE);
      NOT_YET_CONNECTED_EXCEPTION.setStackTrace(EmptyArrays.EMPTY_STACK_TRACE);
   }

   @Override
   public ChannelFuture writeAndFlush(Object var1) {
      return this.pipeline.writeAndFlush(var1);
   }

   public MessageSizeEstimator$Handle estimatorHandle() {
      if (this.estimatorHandle == null) {
         this.estimatorHandle = this.config().getMessageSizeEstimator().newHandle();
      }

      return this.estimatorHandle;
   }

   public Object filterOutboundMessage(Object var1) {
      return var1;
   }

   @Override
   public ChannelFuture bind(SocketAddress var1, ChannelPromise var2) {
      return this.pipeline.bind(var1, var2);
   }

   @Override
   public boolean isRegistered() {
      return this.registered;
   }

   @Override
   public ChannelPipeline pipeline() {
      return this.pipeline;
   }

   public abstract AbstractChannel$AbstractUnsafe newUnsafe();

   @Override
   public ChannelFuture connect(SocketAddress var1, ChannelPromise var2) {
      return this.pipeline.connect(var1, var2);
   }

   public abstract SocketAddress localAddress0();

   @Override
   public ChannelFuture connect(SocketAddress var1) {
      return this.pipeline.connect(var1);
   }

   public void invalidateLocalAddress() {
      this.localAddress = null;
   }

   @Override
   public ChannelFuture disconnect(ChannelPromise var1) {
      return this.pipeline.disconnect(var1);
   }

   @Override
   public ChannelPromise newPromise() {
      return new DefaultChannelPromise(this);
   }

   @Override
   public boolean equals(Object var1) {
      return this == var1;
   }

   @Override
   public ByteBufAllocator alloc() {
      return this.config().getAllocator();
   }

   public abstract void doClose();

   @Override
   public EventLoop eventLoop() {
      EventLoop var1 = this.eventLoop;
      if (var1 == null) {
         throw new IllegalStateException("channel not registered to an event loop");
      } else {
         return var1;
      }
   }

   @Override
   public ChannelFuture deregister() {
      return this.pipeline.deregister();
   }
}
