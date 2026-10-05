package net.minecraft.server.network;

import com.google.common.base.Charsets;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import java.math.BigInteger;
import java.security.PrivateKey;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import javax.crypto.SecretKey;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.login.INetHandlerLoginServer;
import net.minecraft.network.login.client.C00PacketLoginStart;
import net.minecraft.network.login.client.C01PacketEncryptionResponse;
import net.minecraft.network.login.server.S00PacketDisconnect;
import net.minecraft.network.login.server.S01PacketEncryptionRequest;
import net.minecraft.network.login.server.S02PacketLoginSuccess;
import net.minecraft.network.login.server.S03PacketEnableCompression;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.CryptManager;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ITickable;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetHandlerLoginServer implements INetHandlerLoginServer, ITickable {
   public NetHandlerLoginServer.LoginState currentLoginState;
   public NetworkManager networkManager;
   public int connectionTimer;
   public GameProfile loginGameProfile;
   public SecretKey secretKey;
   public MinecraftServer server;
   public static AtomicInteger AUTHENTICATOR_THREAD_ID = new AtomicInteger(0);
   public byte[] verifyToken = new byte[4];
   public String serverId;
   public static Logger logger = LogManager.getLogger();
   public EntityPlayerMP player;
   public static Random RANDOM = new Random();

   public void tryAcceptPlayer() {
      if (!this.loginGameProfile.isComplete()) {
         this.loginGameProfile = this.getOfflineProfile(this.loginGameProfile);
      }

      String var1 = this.server.getConfigurationManager().allowUserToConnect(this.networkManager.getRemoteAddress(), this.loginGameProfile);
      if (var1 != null) {
         this.closeConnection(var1);
      } else {
         this.currentLoginState = NetHandlerLoginServer.LoginState.ACCEPTED;
         if (this.server.getNetworkCompressionTreshold() >= 0 && !this.networkManager.isLocalChannel()) {
            this.networkManager.sendPacket(new S03PacketEnableCompression(this.server.getNetworkCompressionTreshold()), new ChannelFutureListener() {
               public void operationComplete(ChannelFuture var1) throws java.lang.Exception {
                  NetHandlerLoginServer.this.networkManager.setCompressionTreshold(NetHandlerLoginServer.this.server.getNetworkCompressionTreshold());
               }
            });
         }

         this.networkManager.sendPacket(new S02PacketLoginSuccess(this.loginGameProfile));
         EntityPlayerMP var2 = this.server.getConfigurationManager().getPlayerByUUID(this.loginGameProfile.getId());
         if (var2 != null) {
            this.currentLoginState = NetHandlerLoginServer.LoginState.DELAY_ACCEPT;
            this.player = this.server.getConfigurationManager().createPlayerForUser(this.loginGameProfile);
         } else {
            this.server
               .getConfigurationManager()
               .initializeConnectionToPlayer(this.networkManager, this.server.getConfigurationManager().createPlayerForUser(this.loginGameProfile));
         }
      }
   }

   @Override
   public void onDisconnect(IChatComponent var1) {
      logger.info(this.getConnectionInfo() + " lost connection: " + var1.getUnformattedText());
   }

   public String getConnectionInfo() {
      return this.loginGameProfile != null
         ? this.loginGameProfile.toString() + " (" + this.networkManager.getRemoteAddress().toString() + ")"
         : String.valueOf(this.networkManager.getRemoteAddress());
   }

   @Override
   public void update() {
      if (this.currentLoginState == NetHandlerLoginServer.LoginState.READY_TO_ACCEPT) {
         this.tryAcceptPlayer();
      } else if (this.currentLoginState == NetHandlerLoginServer.LoginState.DELAY_ACCEPT) {
         EntityPlayerMP var1 = this.server.getConfigurationManager().getPlayerByUUID(this.loginGameProfile.getId());
         if (var1 == null) {
            this.currentLoginState = NetHandlerLoginServer.LoginState.READY_TO_ACCEPT;
            this.server.getConfigurationManager().initializeConnectionToPlayer(this.networkManager, this.player);
            this.player = null;
         }
      }

      if (this.connectionTimer++ == 600) {
         this.closeConnection("Took too long to log in");
      }
   }

   public NetHandlerLoginServer(MinecraftServer var1, NetworkManager var2) {
      this.currentLoginState = NetHandlerLoginServer.LoginState.HELLO;
      this.serverId = "";
      this.server = var1;
      this.networkManager = var2;
      RANDOM.nextBytes(this.verifyToken);
   }

   @Override
   public void processLoginStart(C00PacketLoginStart var1) {
      Validate.validState(this.currentLoginState == NetHandlerLoginServer.LoginState.HELLO, "Unexpected hello packet");
      this.loginGameProfile = var1.getProfile();
      if (this.server.isServerInOnlineMode() && !this.networkManager.isLocalChannel()) {
         this.currentLoginState = NetHandlerLoginServer.LoginState.KEY;
         this.networkManager.sendPacket(new S01PacketEncryptionRequest(this.serverId, this.server.getKeyPair().getPublic(), this.verifyToken));
      } else {
         this.currentLoginState = NetHandlerLoginServer.LoginState.READY_TO_ACCEPT;
      }
   }

   public GameProfile getOfflineProfile(GameProfile var1) {
      UUID var2 = UUID.nameUUIDFromBytes(("OfflinePlayer:" + var1.getName()).getBytes(Charsets.UTF_8));
      return new GameProfile(var2, var1.getName());
   }

   public void closeConnection(String var1) {
      try {
         logger.info("Disconnecting " + this.getConnectionInfo() + ": " + var1);
         ChatComponentText var2 = new ChatComponentText(var1);
         this.networkManager.sendPacket(new S00PacketDisconnect(var2));
         this.networkManager.closeChannel(var2);
      } catch (Exception var3) {
         logger.error("Error whilst disconnecting player", var3);
      }
   }

   @Override
   public void processEncryptionResponse(C01PacketEncryptionResponse var1) {
      Validate.validState(this.currentLoginState == NetHandlerLoginServer.LoginState.KEY, "Unexpected key packet");
      PrivateKey var2 = this.server.getKeyPair().getPrivate();
      if (!Arrays.equals(this.verifyToken, var1.getVerifyToken(var2))) {
         throw new IllegalStateException("Invalid nonce!");
      } else {
         this.secretKey = var1.getSecretKey(var2);
         this.currentLoginState = NetHandlerLoginServer.LoginState.AUTHENTICATING;
         this.networkManager.enableEncryption(this.secretKey);
         (new Thread("User Authenticator #" + AUTHENTICATOR_THREAD_ID.incrementAndGet()) {
               @Override
               public void run() {
                  GameProfile var1x = NetHandlerLoginServer.this.loginGameProfile;

                  try {
                     String var2x = new BigInteger(
                           CryptManager.getServerIdHash(
                              NetHandlerLoginServer.this.serverId,
                              NetHandlerLoginServer.this.server.getKeyPair().getPublic(),
                              NetHandlerLoginServer.this.secretKey
                           )
                        )
                        .toString(16);
                     NetHandlerLoginServer.this.loginGameProfile = NetHandlerLoginServer.this.server
                        .getMinecraftSessionService()
                        .hasJoinedServer(new GameProfile((UUID)null, var1x.getName()), var2x);
                     if (NetHandlerLoginServer.this.loginGameProfile != null) {
                        NetHandlerLoginServer.logger
                           .info(
                              "UUID of player "
                                 + NetHandlerLoginServer.this.loginGameProfile.getName()
                                 + " is "
                                 + NetHandlerLoginServer.this.loginGameProfile.getId()
                           );
                        NetHandlerLoginServer.this.currentLoginState = NetHandlerLoginServer.LoginState.READY_TO_ACCEPT;
                     } else if (NetHandlerLoginServer.this.server.isSinglePlayer()) {
                        NetHandlerLoginServer.logger.warn("Failed to verify username but will let them in anyway!");
                        NetHandlerLoginServer.this.loginGameProfile = NetHandlerLoginServer.this.getOfflineProfile(var1x);
                        NetHandlerLoginServer.this.currentLoginState = NetHandlerLoginServer.LoginState.READY_TO_ACCEPT;
                     } else {
                        NetHandlerLoginServer.this.closeConnection("Failed to verify username!");
                        NetHandlerLoginServer.logger
                           .error("Username '" + NetHandlerLoginServer.this.loginGameProfile.getName() + "' tried to join with an invalid session");
                     }
                  } catch (AuthenticationUnavailableException var3) {
                     if (NetHandlerLoginServer.this.server.isSinglePlayer()) {
                        NetHandlerLoginServer.logger.warn("Authentication servers are down but will let them in anyway!");
                        NetHandlerLoginServer.this.loginGameProfile = NetHandlerLoginServer.this.getOfflineProfile(var1x);
                        NetHandlerLoginServer.this.currentLoginState = NetHandlerLoginServer.LoginState.READY_TO_ACCEPT;
                     } else {
                        NetHandlerLoginServer.this.closeConnection("Authentication servers are down. Please try again later, sorry!");
                        NetHandlerLoginServer.logger.error("Couldn't verify username because servers are unavailable");
                     }
                  }
               }
            })
            .start();
      }
   }

   public static enum LoginState {
      HELLO,
      KEY,
      AUTHENTICATING,
      READY_TO_ACCEPT,
      DELAY_ACCEPT,
      ACCEPTED;
   }
}
