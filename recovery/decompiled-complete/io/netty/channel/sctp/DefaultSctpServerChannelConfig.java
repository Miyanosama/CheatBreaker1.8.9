package io.netty.channel.sctp;

import com.sun.nio.sctp.SctpStandardSocketOptions;
import com.sun.nio.sctp.SctpStandardSocketOptions.InitMaxStreams;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelOption;
import io.netty.channel.DefaultChannelConfig;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.util.NetUtil;
import java.io.IOException;
import java.util.Map;
import net.minecraft.client.Minecraft$2;
import net.minecraft.client.resources.data.AnimationMetadataSection;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentStyle;
import net.minecraft.world.gen.feature.WorldGenIcePath;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$Piece;

public class DefaultSctpServerChannelConfig extends DefaultChannelConfig implements SctpServerChannelConfig {
   public ItemStack __junk8172324064015961529;
   public StructureOceanMonumentPieces __junk3540817800038774007;
   public AnimationMetadataSection __junk5544784878806983798;
   public com.sun.nio.sctp.SctpServerChannel javaChannel;
   public StructureOceanMonumentPieces$Piece __junk4602675477231121457;
   public WorldGenIcePath __junk5704397641999320470;
   public ChatComponentStyle __junk1283195712563952977;
   public volatile int backlog = NetUtil.SOMAXCONN;
   public Minecraft$2 __junk265979000030566632;

   @Override
   public Map<ChannelOption<?>, Object> getOptions() {
      return this.getOptions(super.getOptions(), ChannelOption.SO_RCVBUF, ChannelOption.SO_SNDBUF, SctpChannelOption.SCTP_INIT_MAXSTREAMS);
   }

   @Override
   public SctpServerChannelConfig setConnectTimeoutMillis(int var1) {
      super.setConnectTimeoutMillis(var1);
      return this;
   }

   @Override
   public <T> boolean setOption(ChannelOption<T> var1, T var2) {
      this.validate(var1, var2);
      if (var1 == ChannelOption.SO_RCVBUF) {
         this.setReceiveBufferSize((Integer)var2);
      } else if (var1 == ChannelOption.SO_SNDBUF) {
         this.setSendBufferSize((Integer)var2);
      } else {
         if (var1 != SctpStandardSocketOptions.SCTP_INIT_MAXSTREAMS) {
            return super.setOption(var1, var2);
         }

         this.setInitMaxStreams((InitMaxStreams)var2);
      }

      return true;
   }

   @Override
   public SctpServerChannelConfig setWriteSpinCount(int var1) {
      super.setWriteSpinCount(var1);
      return this;
   }

   @Override
   public SctpServerChannelConfig setBacklog(int var1) {
      if (var1 < 0) {
         throw new IllegalArgumentException("backlog: " + var1);
      } else {
         this.backlog = var1;
         return this;
      }
   }

   @Override
   public SctpServerChannelConfig setInitMaxStreams(InitMaxStreams var1) {
      try {
         this.javaChannel.setOption(SctpStandardSocketOptions.SCTP_INIT_MAXSTREAMS, var1);
         return this;
      } catch (IOException var3) {
         throw new ChannelException(var3);
      }
   }

   @Override
   public SctpServerChannelConfig setMessageSizeEstimator(MessageSizeEstimator var1) {
      super.setMessageSizeEstimator(var1);
      return this;
   }

   @Override
   public SctpServerChannelConfig setWriteBufferLowWaterMark(int var1) {
      super.setWriteBufferLowWaterMark(var1);
      return this;
   }

   @Override
   public SctpServerChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator var1) {
      super.setRecvByteBufAllocator(var1);
      return this;
   }

   @Override
   public SctpServerChannelConfig setAllocator(ByteBufAllocator var1) {
      super.setAllocator(var1);
      return this;
   }

   @Override
   public SctpServerChannelConfig setMaxMessagesPerRead(int var1) {
      super.setMaxMessagesPerRead(var1);
      return this;
   }

   @Override
   public SctpServerChannelConfig setWriteBufferHighWaterMark(int var1) {
      super.setWriteBufferHighWaterMark(var1);
      return this;
   }

   @Override
   public SctpServerChannelConfig setReceiveBufferSize(int var1) {
      try {
         this.javaChannel.setOption(SctpStandardSocketOptions.SO_RCVBUF, var1);
         return this;
      } catch (IOException var3) {
         throw new ChannelException(var3);
      }
   }

   @Override
   public int getBacklog() {
      return this.backlog;
   }

   @Override
   public SctpServerChannelConfig setAutoClose(boolean var1) {
      super.setAutoClose(var1);
      return this;
   }

   public DefaultSctpServerChannelConfig(SctpServerChannel var1, com.sun.nio.sctp.SctpServerChannel var2) {
      super(var1);
      if (var2 == null) {
         throw new NullPointerException("javaChannel");
      } else {
         this.javaChannel = var2;
      }
   }

   @Override
   public int getReceiveBufferSize() {
      try {
         return this.javaChannel.getOption(SctpStandardSocketOptions.SO_RCVBUF);
      } catch (IOException var2) {
         throw new ChannelException(var2);
      }
   }

   @Override
   public SctpServerChannelConfig setSendBufferSize(int var1) {
      try {
         this.javaChannel.setOption(SctpStandardSocketOptions.SO_SNDBUF, var1);
         return this;
      } catch (IOException var3) {
         throw new ChannelException(var3);
      }
   }

   @Override
   public SctpServerChannelConfig setAutoRead(boolean var1) {
      super.setAutoRead(var1);
      return this;
   }

   @Override
   public int getSendBufferSize() {
      try {
         return this.javaChannel.getOption(SctpStandardSocketOptions.SO_SNDBUF);
      } catch (IOException var2) {
         throw new ChannelException(var2);
      }
   }

   @Override
   public <T> T getOption(ChannelOption<T> var1) {
      if (var1 == ChannelOption.SO_RCVBUF) {
         return (T)this.getReceiveBufferSize();
      } else {
         return (T)(var1 == ChannelOption.SO_SNDBUF ? this.getSendBufferSize() : super.getOption(var1));
      }
   }

   @Override
   public InitMaxStreams getInitMaxStreams() {
      try {
         return this.javaChannel.getOption(SctpStandardSocketOptions.SCTP_INIT_MAXSTREAMS);
      } catch (IOException var2) {
         throw new ChannelException(var2);
      }
   }
}
