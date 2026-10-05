package io.netty.channel;

import io.netty.buffer.UnpooledDirectByteBuf;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.WeakHashMap;
import java.util.Map.Entry;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import net.minecraft.block.BlockColored;
import net.minecraft.block.state.pattern.FactoryBlockPattern;
import net.minecraft.client.renderer.entity.RenderTntMinecart;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.stream.MetadataCombat;
import net.minecraft.entity.ai.EntityAISit;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.util.LongHashMap;
import net.minecraft.world.gen.layer.GenLayerRiverInit;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.optifine.CustomBlockLayers;
import net.optifine.entity.model.ModelAdapterEndermite;
import net.optifine.render.CloudRenderer;
import net.optifine.util.MathUtilsTest;
import org.apache.log4j.lf5.viewer.LogTableModel;

public class DefaultChannelPipeline implements ChannelPipeline {
   public AbstractChannelHandlerContext head;
   public static final boolean $assertionsDisabled = !DefaultChannelPipeline.class.desiredAssertionStatus();
   public Map<EventExecutorGroup, EventExecutor> childExecutors;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(DefaultChannelPipeline.class);
   public AbstractChannelHandlerContext tail;
   public static WeakHashMap<Class<?>, String>[] nameCaches = new WeakHashMap[Runtime.getRuntime().availableProcessors()];
   public Map<String, AbstractChannelHandlerContext> name2ctx = new HashMap<>(4);
   public AbstractChannel channel;

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(StringUtil.simpleClassName(this));
      var1.append('{');
      AbstractChannelHandlerContext var2 = this.head.next;

      while (var2 != this.tail) {
         var1.append('(');
         var1.append(var2.name());
         var1.append(" = ");
         var1.append(var2.handler().getClass().getName());
         var1.append(')');
         var2 = var2.next;
         if (var2 == this.tail) {
            break;
         }

         var1.append(", ");
      }

      var1.append('}');
      return var1.toString();
   }

   @Override
   public Iterator<Entry<String, ChannelHandler>> iterator() {
      return this.toMap().entrySet().iterator();
   }

   @Override
   public ChannelHandlerContext context(ChannelHandler var1) {
      if (var1 == null) {
         throw new NullPointerException("handler");
      } else {
         for (AbstractChannelHandlerContext var2 = this.head.next; var2 != null; var2 = var2.next) {
            if (var2.handler() == var1) {
               return var2;
            }
         }

         return null;
      }
   }

   public String generateName(ChannelHandler var1) {
      WeakHashMap var2 = nameCaches[(int)(Thread.currentThread().getId() % nameCaches.length)];
      Class var3 = var1.getClass();
      String var4;
      synchronized (var2) {
         var4 = (String)var2.get(var3);
         if (var4 == null) {
            var4 = generateName0(var3);
            var2.put(var3, var4);
         }
      }

      synchronized (this) {
         if (this.name2ctx.containsKey(var4)) {
            String var6 = var4.substring(0, var4.length() - 1);
            int var7 = 1;

            while (true) {
               String var8 = var6 + var7;
               if (!this.name2ctx.containsKey(var8)) {
                  var4 = var8;
                  break;
               }

               var7++;
            }
         }

         return var4;
      }
   }

   @Override
   public ChannelPipeline addLast(ChannelHandler... var1) {
      return this.addLast(null, var1);
   }

   @Override
   public ChannelPipeline addLast(EventExecutorGroup var1, ChannelHandler... var2) {
      if (var2 == null) {
         throw new NullPointerException("handlers");
      } else {
         for (ChannelHandler var6 : var2) {
            if (var6 == null) {
               break;
            }

            this.addLast(var1, this.generateName(var6), var6);
         }

         return this;
      }
   }

   public void callHandlerAdded0(ChannelHandlerContext var1) {
      try {
         var1.handler().handlerAdded(var1);
      } catch (Throwable var6) {
         boolean var3 = false;

         try {
            this.remove((AbstractChannelHandlerContext)var1);
            var3 = true;
         } catch (Throwable var5) {
            if (logger.isWarnEnabled()) {
               logger.warn("Failed to remove a handler: " + var1.name(), var5);
            }
         }

         if (var3) {
            this.fireExceptionCaught(
               new ChannelPipelineException(var1.handler().getClass().getName() + ".handlerAdded() has thrown an exception; removed.", var6)
            );
         } else {
            this.fireExceptionCaught(
               new ChannelPipelineException(var1.handler().getClass().getName() + ".handlerAdded() has thrown an exception; also failed to remove.", var6)
            );
         }
      }
   }

   @Override
   public ChannelPipeline fireChannelInactive() {
      this.head.fireChannelInactive();
      return this;
   }

   @Override
   public ChannelFuture bind(SocketAddress var1) {
      return this.tail.bind(var1);
   }

   @Override
   public ChannelFuture write(Object var1, ChannelPromise var2) {
      return this.tail.write(var1, var2);
   }

   @Override
   public List<String> names() {
      ArrayList var1 = new ArrayList();

      for (AbstractChannelHandlerContext var2 = this.head.next; var2 != null; var2 = var2.next) {
         var1.add(var2.name());
      }

      return var1;
   }

   @Override
   public ChannelFuture bind(SocketAddress var1, ChannelPromise var2) {
      return this.tail.bind(var1, var2);
   }

   @Override
   public ChannelHandler last() {
      AbstractChannelHandlerContext var1 = this.tail.prev;
      return var1 == this.head ? null : var1.handler();
   }

   public AbstractChannelHandlerContext getContextOrDie(Class<? extends ChannelHandler> var1) {
      AbstractChannelHandlerContext var2 = (AbstractChannelHandlerContext)this.context(var1);
      if (var2 == null) {
         throw new NoSuchElementException(var1.getName());
      } else {
         return var2;
      }
   }

   public void remove0(AbstractChannelHandlerContext var1) {
      AbstractChannelHandlerContext var2 = var1.prev;
      AbstractChannelHandlerContext var3 = var1.next;
      var2.next = var3;
      var3.prev = var2;
      this.name2ctx.remove(var1.name());
      this.callHandlerRemoved(var1);
   }

   public static void waitForFuture(Future<?> var0) {
      try {
         var0.get();
      } catch (ExecutionException var2) {
         PlatformDependent.throwException(var2.getCause());
      } catch (InterruptedException var3) {
         Thread.currentThread().interrupt();
      }
   }

   @Override
   public ChannelFuture close() {
      return this.tail.close();
   }

   @Override
   public ChannelFuture disconnect() {
      return this.tail.disconnect();
   }

   @Override
   public ChannelFuture writeAndFlush(Object var1, ChannelPromise var2) {
      return this.tail.writeAndFlush(var1, var2);
   }

   @Override
   public Map<String, ChannelHandler> toMap() {
      LinkedHashMap var1 = new LinkedHashMap();

      for (AbstractChannelHandlerContext var2 = this.head.next; var2 != this.tail; var2 = var2.next) {
         var1.put(var2.name(), var2.handler());
      }

      return var1;
   }

   @Override
   public ChannelHandler get(String var1) {
      ChannelHandlerContext var2 = this.context(var1);
      return var2 == null ? null : var2.handler();
   }

   @Override
   public ChannelFuture writeAndFlush(Object var1) {
      return this.tail.writeAndFlush(var1);
   }

   @Override
   public ChannelPipeline fireExceptionCaught(Throwable var1) {
      this.head.fireExceptionCaught(var1);
      return this;
   }

   public static String generateName0(Class<?> var0) {
      return StringUtil.simpleClassName(var0) + "#0";
   }

   public static void checkMultiplicity(ChannelHandlerContext var0) {
      ChannelHandler var1 = var0.handler();
      if (var1 instanceof ChannelHandlerAdapter) {
         ChannelHandlerAdapter var2 = (ChannelHandlerAdapter)var1;
         if (!var2.isSharable() && var2.added) {
            throw new ChannelPipelineException(var2.getClass().getName() + " is not a @Sharable handler, so can't be added or removed multiple times.");
         }

         var2.added = true;
      }
   }

   public void callHandlerRemoved0(AbstractChannelHandlerContext var1) {
      try {
         var1.handler().handlerRemoved(var1);
         var1.setRemoved();
      } catch (Throwable var3) {
         this.fireExceptionCaught(new ChannelPipelineException(var1.handler().getClass().getName() + ".handlerRemoved() has thrown an exception.", var3));
      }
   }

   public void checkDuplicateName(String var1) {
      if (this.name2ctx.containsKey(var1)) {
         throw new IllegalArgumentException("Duplicate handler name: " + var1);
      }
   }

   @Override
   public ChannelPipeline addAfter(EventExecutorGroup var1, String var2, String var3, ChannelHandler var4) {
      synchronized (this) {
         AbstractChannelHandlerContext var6 = this.getContextOrDie(var2);
         this.checkDuplicateName(var3);
         DefaultChannelHandlerContext var7 = new DefaultChannelHandlerContext(this, var1, var3, var4);
         this.addAfter0(var3, var6, var7);
         return this;
      }
   }

   @Override
   public ChannelPipeline addFirst(EventExecutorGroup var1, ChannelHandler... var2) {
      if (var2 == null) {
         throw new NullPointerException("handlers");
      } else if (var2.length != 0 && var2[0] != null) {
         int var3 = 1;

         while (var3 < var2.length && var2[var3] != null) {
            var3++;
         }

         for (int var4 = var3 - 1; var4 >= 0; var4--) {
            ChannelHandler var5 = var2[var4];
            this.addFirst(var1, this.generateName(var5), var5);
         }

         return this;
      } else {
         return this;
      }
   }

   public ChannelHandler replace(final AbstractChannelHandlerContext var1, final String var2, ChannelHandler var3) {
      if ($assertionsDisabled || var1 != this.head && var1 != this.tail) {
         io.netty.util.concurrent.Future var4;
         synchronized (this) {
            boolean var6 = var1.name().equals(var2);
            if (!var6) {
               this.checkDuplicateName(var2);
            }

            final DefaultChannelHandlerContext var7 = new DefaultChannelHandlerContext(this, var1.executor, var2, var3);
            if (!var7.channel().isRegistered() || var7.executor().inEventLoop()) {
               this.replace0(var1, var2, var7);
               return var1.handler();
            }

            var4 = var7.executor().submit(new Runnable() {

               @Override
               public void run() {
                  synchronized (DefaultChannelPipeline.this) {
                     DefaultChannelPipeline.this.replace0(var1, var2, var7);
                  }
               }
            });
         }

         waitForFuture(var4);
         return var1.handler();
      } else {
         throw new AssertionError();
      }
   }

   @Override
   public <T extends ChannelHandler> T get(Class<T> var1) {
      ChannelHandlerContext var2 = this.context(var1);
      return (T)(var2 == null ? null : var2.handler());
   }

   public void teardownAll() {
      this.tail.prev.teardown();
   }

   @Override
   public ChannelPipeline fireUserEventTriggered(Object var1) {
      this.head.fireUserEventTriggered(var1);
      return this;
   }

   @Override
   public ChannelHandlerContext firstContext() {
      AbstractChannelHandlerContext var1 = this.head.next;
      return var1 == this.tail ? null : this.head.next;
   }

   @Override
   public ChannelHandlerContext context(Class<? extends ChannelHandler> var1) {
      if (var1 == null) {
         throw new NullPointerException("handlerType");
      } else {
         for (AbstractChannelHandlerContext var2 = this.head.next; var2 != null; var2 = var2.next) {
            if (var1.isAssignableFrom(var2.handler().getClass())) {
               return var2;
            }
         }

         return null;
      }
   }

   public DefaultChannelPipeline(AbstractChannel var1) {
      this.childExecutors = new IdentityHashMap<>();
      if (var1 == null) {
         throw new NullPointerException("channel");
      } else {
         this.channel = var1;
         this.tail = new DefaultChannelPipeline.TailContext(this);
         this.head = new DefaultChannelPipeline.HeadContext(this);
         this.head.next = this.tail;
         this.tail.prev = this.head;
      }
   }

   @Override
   public ChannelPipeline addFirst(ChannelHandler... var1) {
      return this.addFirst(null, var1);
   }

   @Override
   public Channel channel() {
      return this.channel;
   }

   @Override
   public ChannelPipeline fireChannelActive() {
      this.head.fireChannelActive();
      if (this.channel.config().isAutoRead()) {
         this.channel.read();
      }

      return this;
   }

   @Override
   public ChannelPipeline fireChannelUnregistered() {
      this.head.fireChannelUnregistered();
      if (!this.channel.isOpen()) {
         this.teardownAll();
      }

      return this;
   }

   @Override
   public ChannelPipeline addLast(EventExecutorGroup var1, String var2, ChannelHandler var3) {
      synchronized (this) {
         this.checkDuplicateName(var2);
         DefaultChannelHandlerContext var5 = new DefaultChannelHandlerContext(this, var1, var2, var3);
         this.addLast0(var2, var5);
         return this;
      }
   }

   @Override
   public ChannelFuture connect(SocketAddress var1, SocketAddress var2, ChannelPromise var3) {
      return this.tail.connect(var1, var2, var3);
   }

   @Override
   public ChannelPipeline addLast(String var1, ChannelHandler var2) {
      return this.addLast(null, var1, var2);
   }

   @Override
   public ChannelFuture connect(SocketAddress var1) {
      return this.tail.connect(var1);
   }

   @Override
   public ChannelPipeline fireChannelReadComplete() {
      this.head.fireChannelReadComplete();
      if (this.channel.config().isAutoRead()) {
         this.read();
      }

      return this;
   }

   @Override
   public <T extends ChannelHandler> T remove(Class<T> var1) {
      return (T)this.remove(this.getContextOrDie(var1)).handler();
   }

   @Override
   public ChannelHandler first() {
      ChannelHandlerContext var1 = this.firstContext();
      return var1 == null ? null : var1.handler();
   }

   @Override
   public ChannelFuture write(Object var1) {
      return this.tail.write(var1);
   }

   @Override
   public ChannelPipeline remove(ChannelHandler var1) {
      this.remove(this.getContextOrDie(var1));
      return this;
   }

   @Override
   public ChannelFuture deregister() {
      return this.tail.deregister();
   }

   @Override
   public ChannelPipeline flush() {
      this.tail.flush();
      return this;
   }

   @Override
   public ChannelPipeline addBefore(EventExecutorGroup var1, String var2, String var3, ChannelHandler var4) {
      synchronized (this) {
         AbstractChannelHandlerContext var6 = this.getContextOrDie(var2);
         this.checkDuplicateName(var3);
         DefaultChannelHandlerContext var7 = new DefaultChannelHandlerContext(this, var1, var3, var4);
         this.addBefore0(var3, var6, var7);
         return this;
      }
   }

   public AbstractChannelHandlerContext remove(final AbstractChannelHandlerContext var1) {
      if ($assertionsDisabled || var1 != this.head && var1 != this.tail) {
         AbstractChannelHandlerContext var2;
         io.netty.util.concurrent.Future var3;
         synchronized (this) {
            if (!var1.channel().isRegistered() || var1.executor().inEventLoop()) {
               this.remove0(var1);
               return var1;
            }

            var3 = var1.executor().submit(new Runnable() {

               @Override
               public void run() {
                  synchronized (DefaultChannelPipeline.this) {
                     DefaultChannelPipeline.this.remove0(var1);
                  }
               }
            });
            var2 = var1;
         }

         waitForFuture(var3);
         return var2;
      } else {
         throw new AssertionError();
      }
   }

   public void callHandlerRemoved(final AbstractChannelHandlerContext var1) {
      if (var1.channel().isRegistered() && !var1.executor().inEventLoop()) {
         var1.executor().execute(new Runnable() {

            @Override
            public void run() {
               DefaultChannelPipeline.this.callHandlerRemoved0(var1);
            }
         });
      } else {
         this.callHandlerRemoved0(var1);
      }
   }

   @Override
   public <T extends ChannelHandler> T replace(Class<T> var1, String var2, ChannelHandler var3) {
      return (T)this.replace(this.getContextOrDie(var1), var2, var3);
   }

   @Override
   public ChannelPipeline fireChannelRegistered() {
      this.head.fireChannelRegistered();
      return this;
   }

   @Override
   public ChannelHandlerContext context(String var1) {
      if (var1 == null) {
         throw new NullPointerException("name");
      } else {
         synchronized (this) {
            return this.name2ctx.get(var1);
         }
      }
   }

   @Override
   public ChannelPipeline fireChannelWritabilityChanged() {
      this.head.fireChannelWritabilityChanged();
      return this;
   }

   @Override
   public ChannelPipeline addBefore(String var1, String var2, ChannelHandler var3) {
      return this.addBefore(null, var1, var2, var3);
   }

   public void addBefore0(String var1, AbstractChannelHandlerContext var2, AbstractChannelHandlerContext var3) {
      checkMultiplicity(var3);
      var3.prev = var2.prev;
      var3.next = var2;
      var2.prev.next = var3;
      var2.prev = var3;
      this.name2ctx.put(var1, var3);
      this.callHandlerAdded(var3);
   }

   @Override
   public ChannelPipeline addFirst(String var1, ChannelHandler var2) {
      return this.addFirst(null, var1, var2);
   }

   @Override
   public ChannelFuture close(ChannelPromise var1) {
      return this.tail.close(var1);
   }

   @Override
   public ChannelPipeline addFirst(EventExecutorGroup var1, String var2, ChannelHandler var3) {
      synchronized (this) {
         this.checkDuplicateName(var2);
         DefaultChannelHandlerContext var5 = new DefaultChannelHandlerContext(this, var1, var2, var3);
         this.addFirst0(var2, var5);
         return this;
      }
   }

   @Override
   public ChannelHandler removeLast() {
      if (this.head.next == this.tail) {
         throw new NoSuchElementException();
      } else {
         return this.remove(this.tail.prev).handler();
      }
   }

   public AbstractChannelHandlerContext getContextOrDie(String var1) {
      AbstractChannelHandlerContext var2 = (AbstractChannelHandlerContext)this.context(var1);
      if (var2 == null) {
         throw new NoSuchElementException(var1);
      } else {
         return var2;
      }
   }

   @Override
   public ChannelFuture deregister(ChannelPromise var1) {
      return this.tail.deregister(var1);
   }

   @Override
   public ChannelHandler removeFirst() {
      if (this.head.next == this.tail) {
         throw new NoSuchElementException();
      } else {
         return this.remove(this.head.next).handler();
      }
   }

   @Override
   public ChannelPipeline read() {
      this.tail.read();
      return this;
   }

   @Override
   public ChannelPipeline fireChannelRead(Object var1) {
      this.head.fireChannelRead(var1);
      return this;
   }

   @Override
   public ChannelPipeline addAfter(String var1, String var2, ChannelHandler var3) {
      return this.addAfter(null, var1, var2, var3);
   }

   static {
      for (int var0 = 0; var0 < nameCaches.length; var0++) {
         nameCaches[var0] = new WeakHashMap<>();
      }
   }

   public void addLast0(String var1, AbstractChannelHandlerContext var2) {
      checkMultiplicity(var2);
      AbstractChannelHandlerContext var3 = this.tail.prev;
      var2.prev = var3;
      var2.next = this.tail;
      var3.next = var2;
      this.tail.prev = var2;
      this.name2ctx.put(var1, var2);
      this.callHandlerAdded(var2);
   }

   @Override
   public ChannelFuture connect(SocketAddress var1, ChannelPromise var2) {
      return this.tail.connect(var1, var2);
   }

   @Override
   public ChannelHandler replace(String var1, String var2, ChannelHandler var3) {
      return this.replace(this.getContextOrDie(var1), var2, var3);
   }

   @Override
   public ChannelHandlerContext lastContext() {
      AbstractChannelHandlerContext var1 = this.tail.prev;
      return var1 == this.head ? null : var1;
   }

   @Override
   public ChannelHandler remove(String var1) {
      return this.remove(this.getContextOrDie(var1)).handler();
   }

   @Override
   public ChannelPipeline replace(ChannelHandler var1, String var2, ChannelHandler var3) {
      this.replace(this.getContextOrDie(var1), var2, var3);
      return this;
   }

   public AbstractChannelHandlerContext getContextOrDie(ChannelHandler var1) {
      AbstractChannelHandlerContext var2 = (AbstractChannelHandlerContext)this.context(var1);
      if (var2 == null) {
         throw new NoSuchElementException(var1.getClass().getName());
      } else {
         return var2;
      }
   }

   public void callHandlerAdded(final ChannelHandlerContext var1) {
      if (var1.channel().isRegistered() && !var1.executor().inEventLoop()) {
         var1.executor().execute(new Runnable() {

            @Override
            public void run() {
               DefaultChannelPipeline.this.callHandlerAdded0(var1);
            }
         });
      } else {
         this.callHandlerAdded0(var1);
      }
   }

   public void replace0(AbstractChannelHandlerContext var1, String var2, AbstractChannelHandlerContext var3) {
      checkMultiplicity(var3);
      AbstractChannelHandlerContext var4 = var1.prev;
      AbstractChannelHandlerContext var5 = var1.next;
      var3.prev = var4;
      var3.next = var5;
      var4.next = var3;
      var5.prev = var3;
      if (!var1.name().equals(var2)) {
         this.name2ctx.remove(var1.name());
      }

      this.name2ctx.put(var2, var3);
      var1.prev = var3;
      var1.next = var3;
      this.callHandlerAdded(var3);
      this.callHandlerRemoved(var1);
   }

   public void addAfter0(String var1, AbstractChannelHandlerContext var2, AbstractChannelHandlerContext var3) {
      this.checkDuplicateName(var1);
      checkMultiplicity(var3);
      var3.prev = var2;
      var3.next = var2.next;
      var2.next.prev = var3;
      var2.next = var3;
      this.name2ctx.put(var1, var3);
      this.callHandlerAdded(var3);
   }

   @Override
   public ChannelFuture connect(SocketAddress var1, SocketAddress var2) {
      return this.tail.connect(var1, var2);
   }

   @Override
   public ChannelFuture disconnect(ChannelPromise var1) {
      return this.tail.disconnect(var1);
   }

   public void addFirst0(String var1, AbstractChannelHandlerContext var2) {
      checkMultiplicity(var2);
      AbstractChannelHandlerContext var3 = this.head.next;
      var2.prev = this.head;
      var2.next = var3;
      this.head.next = var2;
      var3.prev = var2;
      this.name2ctx.put(var1, var2);
      this.callHandlerAdded(var2);
   }

   public static final class HeadContext extends AbstractChannelHandlerContext implements ChannelOutboundHandler {
      public Channel.Unsafe unsafe;
      public static String HEAD_NAME = DefaultChannelPipeline.generateName0(DefaultChannelPipeline.HeadContext.class);

      @Override
      public void bind(ChannelHandlerContext var1, SocketAddress var2, ChannelPromise var3) throws java.lang.Exception {
         this.unsafe.bind(var2, var3);
      }

      @Override
      public void close(ChannelHandlerContext var1, ChannelPromise var2) throws java.lang.Exception {
         this.unsafe.close(var2);
      }

      @Override
      public void read(ChannelHandlerContext var1) {
         this.unsafe.beginRead();
      }

      @Override
      public void write(ChannelHandlerContext var1, Object var2, ChannelPromise var3) throws java.lang.Exception {
         this.unsafe.write(var2, var3);
      }

      @Override
      public void exceptionCaught(ChannelHandlerContext var1, Throwable var2) throws java.lang.Exception {
         var1.fireExceptionCaught(var2);
      }

      @Override
      public void handlerAdded(ChannelHandlerContext var1) throws java.lang.Exception {
      }

      @Override
      public void flush(ChannelHandlerContext var1) throws java.lang.Exception {
         this.unsafe.flush();
      }

      @Override
      public void connect(ChannelHandlerContext var1, SocketAddress var2, SocketAddress var3, ChannelPromise var4) throws java.lang.Exception {
         this.unsafe.connect(var2, var3, var4);
      }

      @Override
      public ChannelHandler handler() {
         return this;
      }

      @Override
      public void disconnect(ChannelHandlerContext var1, ChannelPromise var2) throws java.lang.Exception {
         this.unsafe.disconnect(var2);
      }

      @Override
      public void handlerRemoved(ChannelHandlerContext var1) throws java.lang.Exception {
      }

      public HeadContext(DefaultChannelPipeline var1) {
         super(var1, null, HEAD_NAME, false, true);
         this.unsafe = var1.channel().unsafe();
      }

      @Override
      public void deregister(ChannelHandlerContext var1, ChannelPromise var2) throws java.lang.Exception {
         this.unsafe.deregister(var2);
      }
   }

   public static final class TailContext extends AbstractChannelHandlerContext implements ChannelInboundHandler {
      public static String TAIL_NAME = DefaultChannelPipeline.generateName0(DefaultChannelPipeline.TailContext.class);

      @Override
      public void channelRead(ChannelHandlerContext var1, Object var2) throws java.lang.Exception {
         try {
            DefaultChannelPipeline.logger
               .debug("Discarded inbound message {} that reached at the tail of the pipeline. Please check your pipeline configuration.", var2);
         } finally {
            ReferenceCountUtil.release(var2);
         }
      }

      @Override
      public void handlerAdded(ChannelHandlerContext var1) throws java.lang.Exception {
      }

      @Override
      public void channelWritabilityChanged(ChannelHandlerContext var1) throws java.lang.Exception {
      }

      @Override
      public ChannelHandler handler() {
         return this;
      }

      @Override
      public void channelInactive(ChannelHandlerContext var1) throws java.lang.Exception {
      }

      @Override
      public void handlerRemoved(ChannelHandlerContext var1) throws java.lang.Exception {
      }

      @Override
      public void channelUnregistered(ChannelHandlerContext var1) throws java.lang.Exception {
      }

      @Override
      public void channelRegistered(ChannelHandlerContext var1) throws java.lang.Exception {
      }

      @Override
      public void userEventTriggered(ChannelHandlerContext var1, Object var2) throws java.lang.Exception {
      }

      @Override
      public void channelActive(ChannelHandlerContext var1) throws java.lang.Exception {
      }

      @Override
      public void exceptionCaught(ChannelHandlerContext var1, Throwable var2) throws java.lang.Exception {
         DefaultChannelPipeline.logger
            .warn(
               "An exceptionCaught() event was fired, and it reached at the tail of the pipeline. It usually means the last handler in the pipeline did not handle the exception.",
               var2
            );
      }

      public TailContext(DefaultChannelPipeline var1) {
         super(var1, null, TAIL_NAME, true, false);
      }

      @Override
      public void channelReadComplete(ChannelHandlerContext var1) throws java.lang.Exception {
      }
   }
}
