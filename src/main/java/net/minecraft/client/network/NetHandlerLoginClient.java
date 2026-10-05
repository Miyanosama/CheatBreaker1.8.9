package net.minecraft.client.network;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.AuthenticationUnavailableException;
import com.mojang.authlib.exceptions.InvalidCredentialsException;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import java.math.BigInteger;
import java.security.PublicKey;
import javax.crypto.SecretKey;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiDisconnected;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.network.EnumConnectionState;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.login.INetHandlerLoginClient;
import net.minecraft.network.login.client.C01PacketEncryptionResponse;
import net.minecraft.network.login.server.S00PacketDisconnect;
import net.minecraft.network.login.server.S01PacketEncryptionRequest;
import net.minecraft.network.login.server.S02PacketLoginSuccess;
import net.minecraft.network.login.server.S03PacketEnableCompression;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.CryptManager;
import net.minecraft.util.IChatComponent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NetHandlerLoginClient implements INetHandlerLoginClient {
   public GuiScreen previousGuiScreen;
   public NetworkManager networkManager;
   public Minecraft mc;
   public GameProfile gameProfile;
   public static Logger logger = LogManager.getLogger();

   @Override
   public void handleLoginSuccess(S02PacketLoginSuccess var1) {
      this.gameProfile = var1.getProfile();
      this.networkManager.setConnectionState(EnumConnectionState.PLAY);
      this.networkManager.setNetHandler(new NetHandlerPlayClient(this.mc, this.previousGuiScreen, this.networkManager, this.gameProfile));
   }

   @Override
   public void handleEncryptionRequest(S01PacketEncryptionRequest var1) {
      final SecretKey var2 = CryptManager.createNewSharedKey();
      String var3 = var1.getServerId();
      PublicKey var4 = var1.getPublicKey();
      String var5 = new BigInteger(CryptManager.getServerIdHash(var3, var4, var2)).toString(16);
      if (this.mc.getCurrentServerData() != null && this.mc.getCurrentServerData().isOnLAN()) {
         try {
            this.getSessionService().joinServer(this.mc.getSession().getProfile(), this.mc.getSession().getToken(), var5);
         } catch (AuthenticationException var10) {
            logger.warn("Couldn't connect to auth servers but will continue to join LAN");
         }
      } else {
         try {
            this.getSessionService().joinServer(this.mc.getSession().getProfile(), this.mc.getSession().getToken(), var5);
         } catch (AuthenticationUnavailableException var7) {
            this.networkManager
               .closeChannel(
                  new ChatComponentTranslation("disconnect.loginFailedInfo", new ChatComponentTranslation("disconnect.loginFailedInfo.serversUnavailable"))
               );
            return;
         } catch (InvalidCredentialsException var8) {
            this.networkManager
               .closeChannel(
                  new ChatComponentTranslation("disconnect.loginFailedInfo", new ChatComponentTranslation("disconnect.loginFailedInfo.invalidSession"))
               );
            return;
         } catch (AuthenticationException var9) {
            this.networkManager.closeChannel(new ChatComponentTranslation("disconnect.loginFailedInfo", var9.getMessage()));
            return;
         }
      }

      this.networkManager.sendPacket(new C01PacketEncryptionResponse(var2, var4, var1.getVerifyToken()), new GenericFutureListener<Future<? super Void>>() {
         @Override
         public void operationComplete(Future<? super Void> var1) throws java.lang.Exception {
            NetHandlerLoginClient.this.networkManager.enableEncryption(var2);
         }
      });
   }

   @Override
   public void handleDisconnect(S00PacketDisconnect var1) {
      this.networkManager.closeChannel(var1.func_149603_c());
   }

   public MinecraftSessionService getSessionService() {
      return this.mc.getSessionService();
   }

   @Override
   public void onDisconnect(IChatComponent var1) {
      this.mc.displayGuiScreen(new GuiDisconnected(this.previousGuiScreen, "connect.failed", var1));
   }

   public NetHandlerLoginClient(NetworkManager var1, Minecraft var2, GuiScreen var3) {
      this.networkManager = var1;
      this.mc = var2;
      this.previousGuiScreen = var3;
   }

   @Override
   public void handleEnableCompression(S03PacketEnableCompression var1) {
      if (!this.networkManager.isLocalChannel()) {
         this.networkManager.setCompressionTreshold(var1.getCompressionTreshold());
      }
   }
}
