package io.netty.channel.udt.nio;

import com.barchart.udt.SocketUDT;
import com.barchart.udt.TypeUDT;
import com.barchart.udt.nio.ChannelUDT;
import com.barchart.udt.nio.KindUDT;
import com.barchart.udt.nio.RendezvousChannelUDT;
import com.barchart.udt.nio.SelectorProviderUDT;
import com.barchart.udt.nio.ServerSocketChannelUDT;
import com.barchart.udt.nio.SocketChannelUDT;
import io.netty.bootstrap.ChannelFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.DefaultChannelPipeline;
import io.netty.channel.udt.UdtChannel;
import io.netty.channel.udt.UdtServerChannel;
import java.io.IOException;
import java.nio.channels.spi.SelectorProvider;
import net.minecraft.network.play.client.C0EPacketClickWindow;
import recovered.unidentified.UnidentifiedClass0122;
import recovered.unidentified.UnidentifiedClass3199;

public class NioUdtProvider<T extends UdtChannel> implements ChannelFactory<T> {
   public static ChannelFactory<UdtChannel> MESSAGE_RENDEZVOUS = new NioUdtProvider<>(TypeUDT.DATAGRAM, KindUDT.RENDEZVOUS);
   public KindUDT kind;
   public UnidentifiedClass3199 __junk3229834213414567696;
   public UnidentifiedClass0122 __junk1197370629508480438;
   public static ChannelFactory<UdtServerChannel> BYTE_ACCEPTOR = new NioUdtProvider<>(TypeUDT.STREAM, KindUDT.ACCEPTOR);
   public TypeUDT type;
   public static ChannelFactory<UdtServerChannel> MESSAGE_ACCEPTOR = new NioUdtProvider<>(TypeUDT.DATAGRAM, KindUDT.ACCEPTOR);
   public static ChannelFactory<UdtChannel> BYTE_CONNECTOR = new NioUdtProvider<>(TypeUDT.STREAM, KindUDT.CONNECTOR);
   public static SelectorProvider MESSAGE_PROVIDER = SelectorProviderUDT.DATAGRAM;
   public static ChannelFactory<UdtChannel> BYTE_RENDEZVOUS = new NioUdtProvider<>(TypeUDT.STREAM, KindUDT.RENDEZVOUS);
   public C0EPacketClickWindow __junk3743841957687205469;
   public DefaultChannelPipeline __junk6979169543838096620;
   public static ChannelFactory<UdtChannel> MESSAGE_CONNECTOR = new NioUdtProvider<>(TypeUDT.DATAGRAM, KindUDT.CONNECTOR);
   public static SelectorProvider BYTE_PROVIDER = SelectorProviderUDT.STREAM;

   public T newChannel() {
      switch (NioUdtProvider$1.$SwitchMap$com$barchart$udt$nio$KindUDT[this.kind.ordinal()]) {
         case 1:
            switch (NioUdtProvider$1.$SwitchMap$com$barchart$udt$TypeUDT[this.type.ordinal()]) {
               case 1:
                  return (T)(new NioUdtMessageAcceptorChannel());
               case 2:
                  return (T)(new NioUdtByteAcceptorChannel());
               default:
                  throw new IllegalStateException("wrong type=" + this.type);
            }
         case 2:
            switch (NioUdtProvider$1.$SwitchMap$com$barchart$udt$TypeUDT[this.type.ordinal()]) {
               case 1:
                  return (T)(new NioUdtMessageConnectorChannel());
               case 2:
                  return (T)(new NioUdtByteConnectorChannel());
               default:
                  throw new IllegalStateException("wrong type=" + this.type);
            }
         case 3:
            switch (NioUdtProvider$1.$SwitchMap$com$barchart$udt$TypeUDT[this.type.ordinal()]) {
               case 1:
                  return (T)(new NioUdtMessageRendezvousChannel());
               case 2:
                  return (T)(new NioUdtByteRendezvousChannel());
               default:
                  throw new IllegalStateException("wrong type=" + this.type);
            }
         default:
            throw new IllegalStateException("wrong kind=" + this.kind);
      }
   }

   public static SocketUDT socketUDT(Channel var0) {
      ChannelUDT var1 = channelUDT(var0);
      return var1 == null ? null : var1.socketUDT();
   }

   public static ChannelUDT channelUDT(Channel var0) {
      if (var0 instanceof NioUdtByteAcceptorChannel) {
         return ((NioUdtByteAcceptorChannel)var0).javaChannel();
      } else if (var0 instanceof NioUdtByteConnectorChannel) {
         return ((NioUdtByteConnectorChannel)var0).javaChannel();
      } else if (var0 instanceof NioUdtByteRendezvousChannel) {
         return ((NioUdtByteRendezvousChannel)var0).javaChannel();
      } else if (var0 instanceof NioUdtMessageAcceptorChannel) {
         return ((NioUdtMessageAcceptorChannel)var0).javaChannel();
      } else if (var0 instanceof NioUdtMessageConnectorChannel) {
         return ((NioUdtMessageConnectorChannel)var0).javaChannel();
      } else {
         return var0 instanceof NioUdtMessageRendezvousChannel ? ((NioUdtMessageRendezvousChannel)var0).javaChannel() : null;
      }
   }

   public NioUdtProvider(TypeUDT var1, KindUDT var2) {
      this.type = var1;
      this.kind = var2;
   }

   public static SocketChannelUDT newConnectorChannelUDT(TypeUDT var0) {
      try {
         return SelectorProviderUDT.from(var0).openSocketChannel();
      } catch (IOException var2) {
         throw new ChannelException("failed to open a socket channel", var2);
      }
   }

   public static ServerSocketChannelUDT newAcceptorChannelUDT(TypeUDT var0) {
      try {
         return SelectorProviderUDT.from(var0).openServerSocketChannel();
      } catch (IOException var2) {
         throw new ChannelException("failed to open a server socket channel", var2);
      }
   }

   public KindUDT kind() {
      return this.kind;
   }

   public TypeUDT type() {
      return this.type;
   }

   public static RendezvousChannelUDT newRendezvousChannelUDT(TypeUDT var0) {
      try {
         return SelectorProviderUDT.from(var0).openRendezvousChannel();
      } catch (IOException var2) {
         throw new ChannelException("failed to open a rendezvous channel", var2);
      }
   }
}
