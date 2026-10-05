package io.netty.channel.group;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufHolder;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ServerChannel;
import io.netty.handler.codec.socks.SocksInitResponseDecoder;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.internal.ConcurrentSet;
import io.netty.util.internal.StringUtil;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.resources.model.ModelRotation;
import net.minecraft.tileentity.TileEntitySign;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.EnumDifficulty;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditor$8;

public class DefaultChannelGroup extends AbstractSet<Channel> implements ChannelGroup {
   public ConcurrentSet<Channel> serverChannels = new ConcurrentSet<>();
   public EventExecutor executor;
   public String name;
   public ChannelFutureListener remover;
   public static AtomicInteger nextId = new AtomicInteger();
   public ConcurrentSet<Channel> nonServerChannels = new ConcurrentSet<>();

   @Override
   public int size() {
      return this.nonServerChannels.size() + this.serverChannels.size();
   }

   @Override
   public Object[] toArray() {
      ArrayList var1 = new ArrayList(this.size());
      var1.addAll(this.serverChannels);
      var1.addAll(this.nonServerChannels);
      return var1.toArray();
   }

   @Override
   public <T> T[] toArray(T[] var1) {
      ArrayList var2 = new ArrayList(this.size());
      var2.addAll(this.serverChannels);
      var2.addAll(this.nonServerChannels);
      return (T[])var2.toArray(var1);
   }

   public static Object safeDuplicate(Object var0) {
      if (var0 instanceof ByteBuf) {
         return ((ByteBuf)var0).duplicate().retain();
      } else {
         return var0 instanceof ByteBufHolder ? ((ByteBufHolder)var0).duplicate().retain() : ReferenceCountUtil.retain(var0);
      }
   }

   @Override
   public ChannelGroup flush() {
      return this.flush(ChannelMatchers.all());
   }

   @Override
   public ChannelGroupFuture writeAndFlush(Object var1) {
      return this.writeAndFlush(var1, ChannelMatchers.all());
   }

   @Override
   public ChannelGroupFuture deregister(ChannelMatcher var1) {
      if (var1 == null) {
         throw new NullPointerException("matcher");
      } else {
         LinkedHashMap var2 = new LinkedHashMap(this.size());

         for (Channel var4 : this.serverChannels) {
            if (var1.matches(var4)) {
               var2.put(var4, var4.deregister());
            }
         }

         for (Channel var6 : this.nonServerChannels) {
            if (var1.matches(var6)) {
               var2.put(var6, var6.deregister());
            }
         }

         return new DefaultChannelGroupFuture(this, var2, this.executor);
      }
   }

   @Override
   public boolean contains(Object var1) {
      if (var1 instanceof Channel) {
         Channel var2 = (Channel)var1;
         return var1 instanceof ServerChannel ? this.serverChannels.contains(var2) : this.nonServerChannels.contains(var2);
      } else {
         return false;
      }
   }

   public DefaultChannelGroup(EventExecutor var1) {
      this("group-0x" + Integer.toHexString(nextId.incrementAndGet()), var1);
   }

   @Override
   public boolean equals(Object var1) {
      return this == var1;
   }

   @Override
   public ChannelGroupFuture flushAndWrite(Object var1, ChannelMatcher var2) {
      return this.writeAndFlush(var1, var2);
   }

   @Override
   public ChannelGroupFuture flushAndWrite(Object var1) {
      return this.writeAndFlush(var1);
   }

   @Override
   public ChannelGroupFuture deregister() {
      return this.deregister(ChannelMatchers.all());
   }

   public boolean add(Channel var1) {
      ConcurrentSet var2 = var1 instanceof ServerChannel ? this.serverChannels : this.nonServerChannels;
      boolean var3 = var2.add(var1);
      if (var3) {
         var1.closeFuture().addListener(this.remover);
      }

      return var3;
   }

   @Override
   public Iterator<Channel> iterator() {
      return new CombinedIterator<>(this.serverChannels.iterator(), this.nonServerChannels.iterator());
   }

   @Override
   public String name() {
      return this.name;
   }

   @Override
   public boolean remove(Object var1) {
      if (!(var1 instanceof Channel)) {
         return false;
      } else {
         Channel var3 = (Channel)var1;
         boolean var2;
         if (var3 instanceof ServerChannel) {
            var2 = this.serverChannels.remove(var3);
         } else {
            var2 = this.nonServerChannels.remove(var3);
         }

         if (!var2) {
            return false;
         } else {
            var3.closeFuture().removeListener(this.remover);
            return true;
         }
      }
   }

   @Override
   public ChannelGroup flush(ChannelMatcher var1) {
      for (Channel var3 : this.nonServerChannels) {
         if (var1.matches(var3)) {
            var3.flush();
         }
      }

      return this;
   }

   @Override
   public String toString() {
      return StringUtil.simpleClassName(this) + "(name: " + this.name() + ", size: " + this.size() + ')';
   }

   @Override
   public ChannelGroupFuture write(Object var1, ChannelMatcher var2) {
      if (var1 == null) {
         throw new NullPointerException("message");
      } else if (var2 == null) {
         throw new NullPointerException("matcher");
      } else {
         LinkedHashMap var3 = new LinkedHashMap(this.size());

         for (Channel var5 : this.nonServerChannels) {
            if (var2.matches(var5)) {
               var3.put(var5, var5.write(safeDuplicate(var1)));
            }
         }

         ReferenceCountUtil.release(var1);
         return new DefaultChannelGroupFuture(this, var3, this.executor);
      }
   }

   @Override
   public void clear() {
      this.nonServerChannels.clear();
      this.serverChannels.clear();
   }

   @Override
   public ChannelGroupFuture disconnect() {
      return this.disconnect(ChannelMatchers.all());
   }

   @Override
   public ChannelGroupFuture close() {
      return this.close(ChannelMatchers.all());
   }

   @Override
   public ChannelGroupFuture close(ChannelMatcher var1) {
      if (var1 == null) {
         throw new NullPointerException("matcher");
      } else {
         LinkedHashMap var2 = new LinkedHashMap(this.size());

         for (Channel var4 : this.serverChannels) {
            if (var1.matches(var4)) {
               var2.put(var4, var4.close());
            }
         }

         for (Channel var6 : this.nonServerChannels) {
            if (var1.matches(var6)) {
               var2.put(var6, var6.close());
            }
         }

         return new DefaultChannelGroupFuture(this, var2, this.executor);
      }
   }

   public DefaultChannelGroup(String var1, EventExecutor var2) {
      this.remover = new ChannelFutureListener() {

         public void operationComplete(ChannelFuture var1) throws java.lang.Exception {
            DefaultChannelGroup.this.remove(var1.channel());
         }
      };
      if (var1 == null) {
         throw new NullPointerException("name");
      } else {
         this.name = var1;
         this.executor = var2;
      }
   }

   @Override
   public boolean isEmpty() {
      return this.nonServerChannels.isEmpty() && this.serverChannels.isEmpty();
   }

   @Override
   public ChannelGroupFuture disconnect(ChannelMatcher var1) {
      if (var1 == null) {
         throw new NullPointerException("matcher");
      } else {
         LinkedHashMap var2 = new LinkedHashMap(this.size());

         for (Channel var4 : this.serverChannels) {
            if (var1.matches(var4)) {
               var2.put(var4, var4.disconnect());
            }
         }

         for (Channel var6 : this.nonServerChannels) {
            if (var1.matches(var6)) {
               var2.put(var6, var6.disconnect());
            }
         }

         return new DefaultChannelGroupFuture(this, var2, this.executor);
      }
   }

   public int compareTo(ChannelGroup var1) {
      int var2 = this.name().compareTo(var1.name());
      return var2 != 0 ? var2 : System.identityHashCode(this) - System.identityHashCode(var1);
   }

   @Override
   public int hashCode() {
      return System.identityHashCode(this);
   }

   @Override
   public ChannelGroupFuture writeAndFlush(Object var1, ChannelMatcher var2) {
      if (var1 == null) {
         throw new NullPointerException("message");
      } else {
         LinkedHashMap var3 = new LinkedHashMap(this.size());

         for (Channel var5 : this.nonServerChannels) {
            if (var2.matches(var5)) {
               var3.put(var5, var5.writeAndFlush(safeDuplicate(var1)));
            }
         }

         ReferenceCountUtil.release(var1);
         return new DefaultChannelGroupFuture(this, var3, this.executor);
      }
   }

   @Override
   public ChannelGroupFuture write(Object var1) {
      return this.write(var1, ChannelMatchers.all());
   }
}
