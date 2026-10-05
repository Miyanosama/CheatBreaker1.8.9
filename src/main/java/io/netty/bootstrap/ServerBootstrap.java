package io.netty.bootstrap;

import io.netty.channel.Channel;
import io.netty.channel.ChannelConfig;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.channel.sctp.DefaultSctpServerChannelConfig;
import io.netty.util.AttributeKey;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;
import net.minecraft.client.gui.GuiResourcePackList;
import net.minecraft.client.particle.EntityCritFX;
import net.minecraft.client.renderer.BlockModelRenderer;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.monster.EntityGuardian;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.world.gen.structure.MapGenVillage;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces;
import net.optifine.entity.model.ModelAdapterBlaze;
import org.scijava.nativelib.NativeLibraryUtil;
import com.cheatbreaker.client.nethandler.server.PacketServerRule;

public class ServerBootstrap extends AbstractBootstrap<ServerBootstrap, ServerChannel> {
   public Map<AttributeKey<?>, Object> childAttrs;
   public volatile ChannelHandler childHandler;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(ServerBootstrap.class);
   public Map<ChannelOption<?>, Object> childOptions = new LinkedHashMap<>();
   public volatile EventLoopGroup childGroup;

   public ServerBootstrap childHandler(ChannelHandler var1) {
      if (var1 == null) {
         throw new NullPointerException("childHandler");
      } else {
         this.childHandler = var1;
         return this;
      }
   }

   public <T> ServerBootstrap childAttr(AttributeKey<T> var1, T var2) {
      if (var1 == null) {
         throw new NullPointerException("childKey");
      } else {
         if (var2 == null) {
            this.childAttrs.remove(var1);
         } else {
            this.childAttrs.put(var1, var2);
         }

         return this;
      }
   }

   public ServerBootstrap group(EventLoopGroup var1, EventLoopGroup var2) {
      super.group(var1);
      if (var2 == null) {
         throw new NullPointerException("childGroup");
      } else if (this.childGroup != null) {
         throw new IllegalStateException("childGroup set already");
      } else {
         this.childGroup = var2;
         return this;
      }
   }

   public ServerBootstrap group(EventLoopGroup var1) {
      return this.group(var1, var1);
   }

   @Override
   public void init(Channel var1) throws java.lang.Exception {
      Map var2 = this.options();
      synchronized (var2) {
         var1.config().setOptions(var2);
      }

      Map var3 = this.attrs();
      synchronized (var3) {
         for (Entry var6 : (Iterable<Entry>)(Iterable<?>)(var3.entrySet())) {
            AttributeKey var7 = (AttributeKey)var6.getKey();
            var1.<Object>attr(var7).set(var6.getValue());
         }
      }

      ChannelPipeline var4 = var1.pipeline();
      if (this.handler() != null) {
         var4.addLast(this.handler());
      }

      final EventLoopGroup var16 = this.childGroup;
      final ChannelHandler var17 = this.childHandler;
      final Entry[] var18;
      synchronized (this.childOptions) {
         var18 = this.childOptions.entrySet().toArray(newOptionArray(this.childOptions.size()));
      }

      final Entry[] var8;
      synchronized (this.childAttrs) {
         var8 = this.childAttrs.entrySet().toArray(newAttrArray(this.childAttrs.size()));
      }

      var4.addLast(new ChannelInitializer<Channel>() {

         @Override
         public void initChannel(Channel var1) throws java.lang.Exception {
            var1.pipeline().addLast(new ServerBootstrap.ServerBootstrapAcceptor(var16, var17, var18, var8));
         }
      });
   }

   public ServerBootstrap validate() {
      super.validate();
      if (this.childHandler == null) {
         throw new IllegalStateException("childHandler not set");
      } else {
         if (this.childGroup == null) {
            logger.warn("childGroup is not set. Using parentGroup instead.");
            this.childGroup = this.group();
         }

         return this;
      }
   }

   public static Entry<AttributeKey<?>, Object>[] newAttrArray(int var0) {
      return new Entry[var0];
   }

   public <T> ServerBootstrap childOption(ChannelOption<T> var1, T var2) {
      if (var1 == null) {
         throw new NullPointerException("childOption");
      } else {
         if (var2 == null) {
            synchronized (this.childOptions) {
               this.childOptions.remove(var1);
            }
         } else {
            synchronized (this.childOptions) {
               this.childOptions.put(var1, var2);
            }
         }

         return this;
      }
   }

   public ServerBootstrap clone() {
      return new ServerBootstrap(this);
   }

   public ServerBootstrap() {
      this.childAttrs = new LinkedHashMap<>();
   }

   public static Entry<ChannelOption<?>, Object>[] newOptionArray(int var0) {
      return new Entry[var0];
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder(super.toString());
      var1.setLength(var1.length() - 1);
      var1.append(", ");
      if (this.childGroup != null) {
         var1.append("childGroup: ");
         var1.append(StringUtil.simpleClassName(this.childGroup));
         var1.append(", ");
      }

      synchronized (this.childOptions) {
         if (!this.childOptions.isEmpty()) {
            var1.append("childOptions: ");
            var1.append(this.childOptions);
            var1.append(", ");
         }
      }

      synchronized (this.childAttrs) {
         if (!this.childAttrs.isEmpty()) {
            var1.append("childAttrs: ");
            var1.append(this.childAttrs);
            var1.append(", ");
         }
      }

      if (this.childHandler != null) {
         var1.append("childHandler: ");
         var1.append(this.childHandler);
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

   public EventLoopGroup childGroup() {
      return this.childGroup;
   }

   public ServerBootstrap(ServerBootstrap var1) {
      super(var1);
      this.childAttrs = new LinkedHashMap<>();
      this.childGroup = var1.childGroup;
      this.childHandler = var1.childHandler;
      synchronized (var1.childOptions) {
         this.childOptions.putAll(var1.childOptions);
      }

      synchronized (var1.childAttrs) {
         this.childAttrs.putAll(var1.childAttrs);
      }
   }

   public static class ServerBootstrapAcceptor extends ChannelInboundHandlerAdapter {
      public Entry<AttributeKey<?>, Object>[] childAttrs;
      public EventLoopGroup childGroup;
      public Entry<ChannelOption<?>, Object>[] childOptions;
      public ChannelHandler childHandler;

      public ServerBootstrapAcceptor(EventLoopGroup var1, ChannelHandler var2, Entry<ChannelOption<?>, Object>[] var3, Entry<AttributeKey<?>, Object>[] var4) {
         this.childGroup = var1;
         this.childHandler = var2;
         this.childOptions = var3;
         this.childAttrs = var4;
      }

      @Override
      public void exceptionCaught(ChannelHandlerContext var1, Throwable var2) throws java.lang.Exception {
         final ChannelConfig var3 = var1.channel().config();
         if (var3.isAutoRead()) {
            var3.setAutoRead(false);
            var1.channel().eventLoop().schedule(new Runnable() {

               @Override
               public void run() {
                  var3.setAutoRead(true);
               }
            }, 1L, TimeUnit.SECONDS);
         }

         var1.fireExceptionCaught(var2);
      }

      public static void forceClose(Channel var0, Throwable var1) {
         var0.unsafe().closeForcibly();
         ServerBootstrap.logger.warn("Failed to register an accepted channel: " + var0, var1);
      }

      @Override
      public void channelRead(ChannelHandlerContext var1, Object var2) {
         final Channel var3 = (Channel)var2;
         var3.pipeline().addLast(this.childHandler);

         for (Entry var7 : this.childOptions) {
            try {
               if (!var3.config().setOption((ChannelOption<Object>)var7.getKey(), var7.getValue())) {
                  ServerBootstrap.logger.warn("Unknown channel option: " + var7);
               }
            } catch (Throwable var10) {
               ServerBootstrap.logger.warn("Failed to set a channel option: " + var3, var10);
            }
         }

         for (Entry var14 : this.childAttrs) {
            var3.<Object>attr((AttributeKey<Object>)var14.getKey()).set(var14.getValue());
         }

         try {
            this.childGroup.register(var3).addListener(new ChannelFutureListener() {

               public void operationComplete(ChannelFuture var1) throws java.lang.Exception {
                  if (!var1.isSuccess()) {
                     ServerBootstrap.ServerBootstrapAcceptor.forceClose(var3, var1.cause());
                  }
               }
            });
         } catch (Throwable var9) {
            forceClose(var3, var9);
         }
      }
   }
}
