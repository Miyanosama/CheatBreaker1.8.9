package io.netty.bootstrap;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.ServerChannel;
import io.netty.handler.traffic.GlobalTrafficShapingHandler$1;
import io.netty.util.AttributeKey;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.gui.GuiResourcePackList;
import net.minecraft.client.renderer.BlockModelRenderer$EnumNeighborInfo;
import net.optifine.entity.model.ModelAdapterBlaze;
import org.scijava.nativelib.NativeLibraryUtil;

public class ServerBootstrap extends AbstractBootstrap<ServerBootstrap, ServerChannel> {
   public GlobalTrafficShapingHandler$1 __junk8886882196856215545;
   public GuiResourcePackList __junk3775672238764576613;
   public Map<AttributeKey<?>, Object> childAttrs;
   public volatile ChannelHandler childHandler;
   public static InternalLogger logger = InternalLoggerFactory.getInstance(ServerBootstrap.class);
   public ModelAdapterBlaze __junk2261002857920397659;
   public Map<ChannelOption<?>, Object> childOptions = new LinkedHashMap<>();
   public NativeLibraryUtil __junk1375031076077308575;
   public volatile EventLoopGroup childGroup;
   public BlockModelRenderer$EnumNeighborInfo __junk2546077705176520363;

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
   public void init(Channel var1) {
      Map var2 = this.options();
      synchronized (var2) {
         var1.config().setOptions(var2);
      }

      Map var3 = this.attrs();
      synchronized (var3) {
         for (Entry var6 : var3.entrySet()) {
            AttributeKey var7 = (AttributeKey)var6.getKey();
            var1.<Object>attr(var7).set(var6.getValue());
         }
      }

      ChannelPipeline var4 = var1.pipeline();
      if (this.handler() != null) {
         var4.addLast(this.handler());
      }

      EventLoopGroup var16 = this.childGroup;
      ChannelHandler var17 = this.childHandler;
      Entry[] var18;
      synchronized (this.childOptions) {
         var18 = this.childOptions.entrySet().toArray(newOptionArray(this.childOptions.size()));
      }

      Entry[] var8;
      synchronized (this.childAttrs) {
         var8 = this.childAttrs.entrySet().toArray(newAttrArray(this.childAttrs.size()));
      }

      var4.addLast(new ServerBootstrap$1(this, var16, var17, var18, var8));
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
}
