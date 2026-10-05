package net.minecraft.client.network;

import com.google.common.base.Charsets;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelException;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.net.InetAddress;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerAddress;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.ServerStatusResponse;
import net.minecraft.network.handshake.client.C00Handshake;
import net.minecraft.network.status.INetHandlerStatusClient;
import net.minecraft.network.status.client.C00PacketServerQuery;
import net.minecraft.network.status.client.C01PacketPing;
import net.minecraft.network.status.server.S00PacketServerInfo;
import net.minecraft.network.status.server.S01PacketPong;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class OldServerPinger {
   public static Splitter PING_RESPONSE_SPLITTER = Splitter.on('\u0000').limit(6);
   public static Logger logger = LogManager.getLogger();
   public List<NetworkManager> pingDestinations = Collections.synchronizedList(Lists.newArrayList());

   public void clearPendingNetworks() {
      synchronized (this.pingDestinations) {
         Iterator var2 = this.pingDestinations.iterator();

         while (var2.hasNext()) {
            NetworkManager var3 = (NetworkManager)var2.next();
            if (var3.isChannelOpen()) {
               var2.remove();
               var3.closeChannel(new ChatComponentText("Cancelled"));
            }
         }
      }
   }

   public void pingPendingNetworks() {
      synchronized (this.pingDestinations) {
         Iterator var2 = this.pingDestinations.iterator();

         while (var2.hasNext()) {
            NetworkManager var3 = (NetworkManager)var2.next();
            if (var3.isChannelOpen()) {
               var3.processReceivedPackets();
            } else {
               var2.remove();
               var3.checkDisconnected();
            }
         }
      }
   }

   public void ping(final ServerData var1) throws java.net.UnknownHostException {
      ServerAddress var2 = ServerAddress.fromString(var1.serverIP);
      final NetworkManager var3 = NetworkManager.createNetworkManagerAndConnect(InetAddress.getByName(var2.getIP()), var2.getPort(), false);
      this.pingDestinations.add(var3);
      var1.serverMOTD = "Pinging...";
      var1.pingToServer = -1L;
      var1.recoveredField3387 = null;
      var3.setNetHandler(
         new INetHandlerStatusClient() {
            public boolean field_147403_d = false;
            public boolean field_183009_e = false;
            public long field_175092_e = 0L;

            @Override
            public void onDisconnect(IChatComponent var1x) {
               if (!this.field_147403_d) {
                  OldServerPinger.logger.error("Can't ping " + var1.serverIP + ": " + var1x.getUnformattedText());
                  var1.serverMOTD = EnumChatFormatting.DARK_RED + "Can't connect to server.";
                  var1.populationInfo = "";
                  OldServerPinger.this.tryCompatibilityPing(var1);
               }
            }

            @Override
            public void handleServerInfo(S00PacketServerInfo var1x) {
               if (this.field_183009_e) {
                  var3.closeChannel(new ChatComponentText("Received unrequested status"));
               } else {
                  this.field_183009_e = true;
                  ServerStatusResponse var2x = var1x.getResponse();
                  if (var2x.getServerDescription() != null) {
                     var1.serverMOTD = var2x.getServerDescription().getFormattedText();
                  } else {
                     var1.serverMOTD = "";
                  }

                  if (var2x.getProtocolVersionInfo() != null) {
                     var1.recoveredField3391 = var2x.getProtocolVersionInfo().getName();
                     var1.recoveredField3386 = var2x.getProtocolVersionInfo().getProtocol();
                  } else {
                     var1.recoveredField3391 = "Old";
                     var1.recoveredField3386 = 0;
                  }

                  if (var2x.getPlayerCountData() == null) {
                     var1.populationInfo = EnumChatFormatting.DARK_GRAY + "???";
                  } else {
                     var1.populationInfo = EnumChatFormatting.GRAY
                        + ""
                        + var2x.getPlayerCountData().getOnlinePlayerCount()
                        + ""
                        + EnumChatFormatting.DARK_GRAY
                        + "/"
                        + EnumChatFormatting.GRAY
                        + var2x.getPlayerCountData().getMaxPlayers();
                     if (ArrayUtils.isNotEmpty(var2x.getPlayerCountData().getPlayers())) {
                        StringBuilder var3x = new StringBuilder();

                        for (GameProfile var7 : var2x.getPlayerCountData().getPlayers()) {
                           if (var3x.length() > 0) {
                              var3x.append("\n");
                           }

                           var3x.append(var7.getName());
                        }

                        if (var2x.getPlayerCountData().getPlayers().length < var2x.getPlayerCountData().getOnlinePlayerCount()) {
                           if (var3x.length() > 0) {
                              var3x.append("\n");
                           }

                           var3x.append("... and ")
                              .append(var2x.getPlayerCountData().getOnlinePlayerCount() - var2x.getPlayerCountData().getPlayers().length)
                              .append(" more ...");
                        }

                        var1.recoveredField3387 = var3x.toString();
                     }
                  }

                  if (var2x.getFavicon() != null) {
                     String var8 = var2x.getFavicon();
                     if (var8.startsWith("data:image/png;base64,")) {
                        var1.setBase64EncodedIconData(var8.substring("data:image/png;base64,".length()));
                     } else {
                        OldServerPinger.logger.error("Invalid server icon (unknown format)");
                     }
                  } else {
                     var1.setBase64EncodedIconData((String)null);
                  }

                  this.field_175092_e = Minecraft.getSystemTime();
                  var3.sendPacket(new C01PacketPing(this.field_175092_e));
                  this.field_147403_d = true;
               }
            }

            @Override
            public void handlePong(S01PacketPong var1x) {
               long var2x = this.field_175092_e;
               long var4 = Minecraft.getSystemTime();
               var1.pingToServer = var4 - var2x;
               var3.closeChannel(new ChatComponentText("Finished"));
            }
         }
      );

      try {
         var3.sendPacket(new C00Handshake(47, var2.getIP(), var2.getPort(), EnumConnectionState.STATUS));
         var3.sendPacket(new C00PacketServerQuery());
      } catch (Throwable var5) {
         logger.error(var5);
      }
   }

   public void tryCompatibilityPing(final ServerData var1) {
      final ServerAddress var2 = ServerAddress.fromString(var1.serverIP);
      new Bootstrap().group(NetworkManager.recoveredField2252.getValue()).handler(new ChannelInitializer<Channel>() {
         @Override
         public void initChannel(Channel var1x) throws java.lang.Exception {
            try {
               var1x.config().setOption(ChannelOption.TCP_NODELAY, true);
            } catch (ChannelException var3) {
            }

            var1x.pipeline().addLast(new SimpleChannelInboundHandler<ByteBuf>() {
               public void channelRead0(ChannelHandlerContext var1x, ByteBuf var2x) throws java.lang.Exception {
                  short var3 = var2x.readUnsignedByte();
                  if (var3 == 255) {
                     String var4 = new String(var2x.readBytes(var2x.readShort() * 2).array(), Charsets.UTF_16BE);
                     String[] var5 = Iterables.toArray(OldServerPinger.PING_RESPONSE_SPLITTER.split(var4), String.class);
                     if ("§1".equals(var5[0])) {
                        int var6 = MathHelper.parseIntWithDefault(var5[1], 0);
                        String var7 = var5[2];
                        String var8 = var5[3];
                        int var9 = MathHelper.parseIntWithDefault(var5[4], -1);
                        int var10 = MathHelper.parseIntWithDefault(var5[5], -1);
                        var1.recoveredField3386 = -1;
                        var1.recoveredField3391 = var7;
                        var1.serverMOTD = var8;
                        var1.populationInfo = EnumChatFormatting.GRAY + "" + var9 + "" + EnumChatFormatting.DARK_GRAY + "/" + EnumChatFormatting.GRAY + var10;
                     }
                  }

                  var1x.close();
               }

               @Override
               public void exceptionCaught(ChannelHandlerContext var1x, Throwable var2x) throws java.lang.Exception {
                  var1x.close();
               }

               @Override
               public void channelActive(ChannelHandlerContext var1x) throws java.lang.Exception {
                  super.channelActive(var1x);
                  ByteBuf var2x = Unpooled.buffer();

                  try {
                     var2x.writeByte(254);
                     var2x.writeByte(1);
                     var2x.writeByte(250);
                     char[] var3 = "MC|PingHost".toCharArray();
                     var2x.writeShort(var3.length);

                     for (char var7 : var3) {
                        var2x.writeChar(var7);
                     }

                     var2x.writeShort(7 + 2 * var2.getIP().length());
                     var2x.writeByte(127);
                     var3 = var2.getIP().toCharArray();
                     var2x.writeShort(var3.length);

                     for (char var15 : var3) {
                        var2x.writeChar(var15);
                     }

                     var2x.writeInt(var2.getPort());
                     var1x.channel().writeAndFlush(var2x).addListener(ChannelFutureListener.CLOSE_ON_FAILURE);
                  } finally {
                     var2x.release();
                  }
               }
            });
         }
      }).channel(NioSocketChannel.class).connect(var2.getIP(), var2.getPort());
   }
}
