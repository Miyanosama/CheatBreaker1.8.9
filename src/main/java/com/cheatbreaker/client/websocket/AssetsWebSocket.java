package com.cheatbreaker.client.websocket;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Profile;
import com.cheatbreaker.client.network.messages.Message;
import com.cheatbreaker.client.ui.mainmenu.AbstractElement;
import com.cheatbreaker.client.ui.overlay.Alert;
import com.cheatbreaker.client.ui.overlay.OverlayGui;
import com.cheatbreaker.client.ui.overlay.element.PrivateMessageElement;
import com.cheatbreaker.client.ui.overlay.friend.FriendRequest;
import com.cheatbreaker.client.ui.overlay.friend.FriendRequestElement;
import com.cheatbreaker.client.util.ClientResourceManager;
import com.cheatbreaker.client.util.UuidParser;
import com.cheatbreaker.client.util.friend.Friend;
import com.cheatbreaker.client.util.friend.Status;
import com.cheatbreaker.client.websocket.client.WSPacketClientFriendRemove;
import com.cheatbreaker.client.websocket.client.WSPacketClientJoinServerResponse;
import com.cheatbreaker.client.websocket.server.WSPacketBulkFriends;
import com.cheatbreaker.client.websocket.server.WSPacketFriendStatusUpdate;
import com.cheatbreaker.client.websocket.server.WSPacketFriendsUpdate;
import com.cheatbreaker.client.websocket.shared.WSPacketFriendRequest;
import com.cheatbreaker.client.websocket.shared.WSPacketFriendUpdate;
import com.cheatbreaker.client.websocket.shared.WSPacketServerUpdate;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.exceptions.InvalidCredentialsException;
import com.mojang.authlib.minecraft.MinecraftSessionService;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import io.netty.buffer.Unpooled;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.math.BigInteger;
import java.net.URI;
import java.nio.ByteBuffer;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.Map.Entry;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.CryptManager;
import net.minecraft.util.EnumChatFormatting;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;
import com.cheatbreaker.client.emote.Emote;
import com.cheatbreaker.client.websocket.AssetsReconnectThread;
import com.cheatbreaker.client.websocket.server.WSPacketJoinServer;
import com.cheatbreaker.client.websocket.server.WSPacketKeyRequest;
import com.cheatbreaker.client.websocket.client.WSPacketClientCosmetics;
import com.cheatbreaker.client.websocket.server.WSPacketFormattedConsoleOutput;
import com.cheatbreaker.client.websocket.shared.WSPacketClientFriendRequestUpdate;
import com.cheatbreaker.client.websocket.client.WSPacketClientProfilesExist;
import com.cheatbreaker.client.websocket.shared.WSPacketMessage;
import com.cheatbreaker.client.websocket.server.WSPacketEmote;
import com.cheatbreaker.client.websocket.client.WSPacketClientPlayerJoin;
import com.cheatbreaker.client.websocket.server.WSPacketForceCrash;
import com.cheatbreaker.client.websocket.shared.WSPacketConsole;
import com.cheatbreaker.client.websocket.client.WSPacketClientKeyResponse;
import com.cheatbreaker.client.util.player.PlayerNametagStyle;
import com.cheatbreaker.client.util.cosmetic.CosmeticsManager;
import com.cheatbreaker.client.websocket.server.WSPacketCosmetics;

public class AssetsWebSocket extends WebSocketClient {
   public Minecraft minecraft = Minecraft.getMinecraft();
   public List<String> recoveredField3017 = new ArrayList<>();

   public void method_10116(WSPacketKeyRequest var1) {
      try {
         byte[] var2 = getKeyResponse(var1.method_04977(), Message.i());
         this.sentToServer(new WSPacketClientKeyResponse(var2));
      } catch (UnsatisfiedLinkError | Exception var3) {
      }
   }

   public void method_10128(WSPacketConsole var1) {
      CheatBreaker.getInstance().method_19794().add(var1.method_21111());
   }

   public void method_10124(WSPacketEmote var1) {
      EntityPlayer var2 = Minecraft.getMinecraft().theWorld.getPlayerEntityByUUID(var1.method_13269());
      Emote var3 = CheatBreaker.getInstance().method_19783().method_01372(var1.method_13270());
      if (var3 != null) {
         if (var2 instanceof AbstractClientPlayer) {
            CheatBreaker.getInstance().method_19783().method_01380((AbstractClientPlayer)var2, var3);
         }
      } else if (var2 instanceof AbstractClientPlayer) {
         CheatBreaker.getInstance().method_19783().method_01379((AbstractClientPlayer)var2);
      }
   }

   public MinecraftSessionService createSessionService() {
      return new YggdrasilAuthenticationService(this.minecraft.getProxy(), UUID.randomUUID().toString()).createMinecraftSessionService();
   }

   public void handleFriendRemove(WSPacketClientFriendRemove var1) {
      String var2 = var1.getPlayerId();
      Friend var3 = CheatBreaker.getInstance().getFriendsManager().getFriend(var2);
      if (var3 != null) {
         CheatBreaker.getInstance().getFriendsManager().getFriends().remove(var2);
         OverlayGui.getInstance().handleFriend(var3, false);
      }
   }

   public void method_10120(WSPacketClientFriendRequestUpdate var1) {
      if (!var1.method_11226()) {
         CheatBreaker.getInstance().getFriendsManager().getFriendRequests().remove(var1.method_11227());
         FriendRequestElement var2 = null;

         for (Object var4 : OverlayGui.getInstance().getFriendRequestsElement().getElements()) {
            if (((FriendRequestElement)var4).getFriendRequest().getPlayerId().equals(var1.method_11227())) {
               var2 = (FriendRequestElement)var4;
            }
         }

         if (var2 != null) {
            OverlayGui.getInstance().getFriendRequestsElement().getElements().add(var2);
            OverlayGui.getInstance().handleFriendRequest(var2.getFriendRequest(), false);
         }
      }
   }

   public void method_10134(WSPacketCosmetics var1) {
      String var2 = var1.method_28748();
      CosmeticsManager var3 = CheatBreaker.getInstance().method_19791();
      UuidParser var4 = CheatBreaker.getInstance().method_19771();
      if (!var2.contains("-")) {
         var2 = var4.method_21033(var1.method_28748());
      } else {
         var2 = var1.method_28748();
      }

      if (var1.method_28750()) {
         var4.method_21030().put(UUID.fromString(var2), new PlayerNametagStyle(var1.method_28749(), "", var1.method_28751()));
      } else {
         var4.method_21030().remove(UUID.fromString(var2));
      }

      CheatBreaker.getInstance().method_19791().method_27043(var2);

      for (ClientResourceManager var6 : var1.method_28752()) {
         try {
            String var7 = var6.method_20848().method_00485();
            switch (var7) {
               case "cape":
                  var3.method_27042().add(var6);
                  break;
               case "emote":
                  CheatBreaker.getInstance().method_19783().method_01369().add(var6.method_20847());
                  break;
               case "dragon_wings":
                  var3.method_27041().add(var6);
            }

            EntityPlayer var11 = this.minecraft.theWorld == null ? null : this.minecraft.theWorld.getPlayerEntityByUUID(UUID.fromString(var2));
            if (var6.method_20849() && var11 instanceof AbstractClientPlayer) {
               if (var6.method_20848().method_00485().equals("cape")) {
                  ((AbstractClientPlayer)var11).setLocationOfCape(var6.method_20859());
                  var11.method_00451(var6);
               } else {
                  var11.method_00396(var6);
               }
            }
         } catch (Exception var9) {
            var9.printStackTrace();
         }
      }
   }

   @Override
   public void onClose(int var1, String var2, boolean var3) {
      CheatBreaker.getInstance()
         .recoveredField1579
         .info(CheatBreaker.getInstance().recoveredField1553 + "Connection closed: " + var2 + " (" + var1 + ") - " + var3);
      new AssetsReconnectThread().start();
      OverlayGui.getInstance().getFriendRequestsElement().getElements().clear();
      OverlayGui.getInstance().getFriendsListElement().getElements().clear();
      CheatBreaker.getInstance().getFriendsManager().getFriends().clear();
      CheatBreaker.getInstance().getFriendsManager().getFriendRequests().clear();
   }

   public void method_10122(WSPacketClientProfilesExist var1) {
      try {
         File var2 = new File(
            Minecraft.getMinecraft().mcDataDir
               + File.separator
               + "config"
               + File.separator
               + "cheatbreaker-client-"
               + "1.8.9".replaceAll("\\.", "-")
               + File.separator
               + "profiles.txt"
         );
         if (!var2.exists()) {
            var2.createNewFile();
         }

         try {
            BufferedWriter var3 = new BufferedWriter(new FileWriter(var2));
            var3.write("################################");
            var3.newLine();
            var3.write("# MC_Client: PROFILES");
            var3.newLine();
            var3.write("################################");
            var3.newLine();

            for (Profile var5 : CheatBreaker.getInstance().getConfigManager().recoveredField3785) {
               var3.write(var5.getName() + ":" + var5.index);
               var3.newLine();
            }

            var3.close();
         } catch (Exception var6) {
         }
      } catch (Exception var7) {
      }
   }

   public void method_10127(WSPacketForceCrash var1) {
      Minecraft.getMinecraft().hasCrashed = true;
      Minecraft.getMinecraft().running = false;
   }

   public void updateClientStatus() {
      this.sentToServer(new WSPacketFriendUpdate("", "", CheatBreaker.getInstance().getStatus().ordinal(), true));
   }

   public void handleServerUpdate(WSPacketServerUpdate var1) {
      String var2 = var1.getServer();
      String var3 = var1.getPlayerId();
      Friend var4 = CheatBreaker.getInstance().getFriendsManager().getFriends().get(var2);
      if (var4 != null) {
         var4.setServer(var3);
      }
   }

   public void handleFriendUpdate(WSPacketFriendUpdate var1) {
      String var2 = var1.getPlayerId();
      String var3 = var1.getName();
      boolean var4 = var1.isOnline();
      Friend var5 = CheatBreaker.getInstance().getFriendsManager().getFriends().get(var2);
      if (var5 == null) {
         var5 = Friend.builder().online(var4).name(var3).playerId(var2).online(var4).onlineStatus(Status.ONLINE).build();
         CheatBreaker.getInstance().getFriendsManager().getFriends().put(var2, var5);
         OverlayGui.getInstance().handleFriend(var5, true);
      }

      if (var1.getOfflineSince() < 10L) {
         int var6 = (int)var1.getOfflineSince();
         Status var7 = Status.ONLINE;

         for (Status var11 : Status.values()) {
            if (var11.ordinal() == var6) {
               var7 = var11;
            }
         }

         var5.setOnlineStatus(var7);
      }

      var5.setOnline(var4);
      var5.method_04074(var3);
      OverlayGui.getInstance().getFriendsListElement().updateSize();
      if (!var4) {
         var5.setOfflineSince(var1.getOfflineSince());
      }
   }

   public void sendClientCosmetics() {
      this.sentToServer(new WSPacketClientCosmetics(CheatBreaker.getInstance().method_19791().method_27046()));
   }

   public void method_10132(AbstractClientPlayer var1) {
      String var2;
      if (var1.getGameProfile() != null
         && this.minecraft.thePlayer != null
         && !this.recoveredField3017.contains(var2 = var1.aK().toString())
         && !var2.equals(this.minecraft.thePlayer.aK().toString())) {
         this.recoveredField3017.add(var2);
         this.sentToServer(new WSPacketClientPlayerJoin(var2));
      }
   }

   public void method_10125(JsonObject var1) {
      String var2 = var1.get("result").getAsString();
      if (var2.equals("SUCCESS")) {
         Alert.displayMessage(EnumChatFormatting.GREEN + "Connected", "Welcome, " + this.minecraft.getSession().getUsername() + ".");
      }
   }

   public void sentToServer(WSPacket var1) {
      if (this.isOpen()) {
         PacketBuffer var2 = new PacketBuffer(Unpooled.buffer());
         var2.writeVarIntToBuffer(WSPacket.REGISTRY.get(var1.getClass()));

         try {
            var1.write(var2);
            if (CheatBreaker.getInstance().method_19798().equalsIgnoreCase("dev")) {
               CheatBreaker.getInstance()
                  .recoveredField1579
                  .info(
                     CheatBreaker.getInstance().recoveredField1553
                        + "[WS NetHandler] (OUT) id: "
                        + WSPacket.REGISTRY.get(var1.getClass())
                        + " Name: "
                        + var1.getClass().getSimpleName()
                  );
            }

            this.send(var2.array());
         } catch (Exception var4) {
            var4.printStackTrace();
         }
      }
   }

   public void handleFriendsUpdate(WSPacketFriendsUpdate var1) {
      CheatBreaker.getInstance().getFriendsManager().getFriends().clear();
      Map var4 = var1.getOnlineMap();
      Map var5 = var1.getOfflineMap();
      CheatBreaker.getInstance().setConsoleAllowed(var1.isConsoleAllowed());
      CheatBreaker.getInstance().setAcceptingFriendRequests(var1.isAcceptingFriendRequests());

      for (Entry var7 : (Iterable<Entry>)(Iterable<?>)(var4.entrySet())) {
         String var3 = (String)var7.getKey();
         String var2 = (String)((List)var7.getValue()).get(0);
         int var8 = Integer.parseInt((String)((List)var7.getValue()).get(1));
         String var9 = (String)((List)var7.getValue()).get(2);
         Status var10 = Status.ONLINE;

         for (Status var14 : Status.values()) {
            if (var14.ordinal() == var8) {
               var10 = var14;
            }
         }

         Friend var20 = Friend.builder().name(var2).playerId(var3).server(var9).onlineStatus(var10).online(true).status("Online").build();
         CheatBreaker.getInstance().getFriendsManager().getFriends().put(var3, var20);
         OverlayGui.getInstance().handleFriend(var20, true);
      }

      for (Entry var18 : (Iterable<Entry>)(Iterable<?>)(var5.entrySet())) {
         String var16 = (String)var18.getKey();
         String var15 = (String)((List)var18.getValue()).get(0);
         Friend var19 = Friend.builder()
            .name(var15)
            .playerId(var16)
            .server("")
            .onlineStatus(Status.ONLINE)
            .online(false)
            .status("Online")
            .offlineSince(Long.parseLong((String)((List)var18.getValue()).get(1)))
            .build();
         CheatBreaker.getInstance().getFriendsManager().getFriends().put(var16, var19);
         OverlayGui.getInstance().handleFriend(var19, true);
      }
   }

   public void method_10123(WSPacketMessage var1) {
      String var2 = var1.method_12691();
      String var3 = var1.method_12690();
      Friend var4 = CheatBreaker.getInstance().getFriendsManager().getFriends().get(var2);
      if (var4 != null) {
         CheatBreaker.getInstance().getFriendsManager().method_26540(var4.getPlayerId(), var3);
         if (CheatBreaker.getInstance().getStatus() != Status.BUSY) {
            CheatBreaker.getInstance().method_19741().method_26702("message");
            Alert.displayMessage(EnumChatFormatting.GREEN + var4.getName() + EnumChatFormatting.RESET + " says:", var3);
         }

         for (AbstractElement var6 : OverlayGui.getInstance().getElements()) {
            if (var6 instanceof PrivateMessageElement && ((PrivateMessageElement)var6).method_01181() == var4) {
               CheatBreaker.getInstance().getFriendsManager().readMessages(var4.getPlayerId());
            }
         }
      }
   }

   public void sendUpdateServer(String var1) {
      this.sentToServer(new WSPacketServerUpdate("", var1));
   }

   public void method_10115(WSPacketJoinServer var1) {
      SecretKey var2 = CryptManager.createNewSharedKey();
      PublicKey var3 = var1.method_03517();
      String var4 = new BigInteger(Objects.requireNonNull(CryptManager.getServerIdHash("", var3, var2))).toString(16);

      try {
         this.createSessionService().joinServer(this.minecraft.getSession().getProfile(), this.minecraft.getSession().getToken(), var4);
      } catch (InvalidCredentialsException var9) {
         if (var9.getMessage() == null) {
            Alert.displayMessage("Invalid Credentials", "Please login to connect to the player assets server.");
         } else {
            Alert.displayMessage("Invalid Credentials", var9.getMessage());
         }

         return;
      } catch (AuthenticationException var10) {
         Alert.displayMessage("Authentication Error", "Please Login to Connect to the Assets Server");
         return;
      } catch (NullPointerException var11) {
         this.close();
         Alert.displayMessage("Invalid Credentials", "A Unknown Error Happened when Connecting to the Assets Server.");
         return;
      }

      try {
         PacketBuffer var5 = new PacketBuffer(Unpooled.buffer());
         WSPacketClientJoinServerResponse var6 = new WSPacketClientJoinServerResponse(var2, var3, var1.method_03518());
         var6.write(var5);
         this.sentToServer(var6);
         File var7 = new File(
            Minecraft.getMinecraft().mcDataDir
               + File.separator
               + "config"
               + File.separator
               + "cheatbreaker-client-"
               + "1.8.9".replaceAll("\\.", "-")
               + File.separator
               + "profiles.txt"
         );
         if (var7.exists()) {
            this.sentToServer(new WSPacketClientProfilesExist());
         }
      } catch (Exception var8) {
         var8.printStackTrace();
      }
   }

   @Override
   public void onOpen(ServerHandshake var1) {
      CheatBreaker.getInstance().recoveredField1579.info(CheatBreaker.getInstance().recoveredField1553 + "Connection established");
      if (Objects.equals(Minecraft.getMinecraft().getSession().getUsername(), Minecraft.getMinecraft().getSession().getPlayerID())) {
         this.close();
      }
   }

   public void handleFriendRequest(WSPacket var1, boolean var2) {
      if (var2) {
         WSPacketFriendStatusUpdate var3 = (WSPacketFriendStatusUpdate)var1;
         FriendRequest var4 = new FriendRequest(var3.getName(), var3.getPlayerId());
         CheatBreaker.getInstance().getFriendsManager().getFriendRequests().put(var3.getPlayerId(), var4);
         OverlayGui.getInstance().handleFriendRequest(var4, true);
         var4.setFriend(var3.isFriend());
         Alert.displayMessage("Friend Request", "Request has been sent.");
      } else {
         WSPacketFriendRequest var7 = (WSPacketFriendRequest)var1;
         String var8 = var7.getPlayerId();
         String var5 = var7.getName();
         FriendRequest var6 = new FriendRequest(var5, var8);
         CheatBreaker.getInstance().getFriendsManager().getFriendRequests().put(var8, var6);
         OverlayGui.getInstance().handleFriendRequest(var6, true);
         if (CheatBreaker.getInstance().getStatus() != Status.BUSY) {
            CheatBreaker.getInstance().method_19741().method_26702("message");
            Alert.displayMessage("Friend Request", var6.getUsername() + " wants to be your friend.");
         }
      }
   }

   public void method_10121(PacketBuffer var1) {
      int var2 = var1.readVarIntFromBuffer();
      Class var3 = WSPacket.REGISTRY.inverse().get(var2);

      try {
         WSPacket var4 = var3 == null ? null : (WSPacket)var3.newInstance();
         if (var4 == null) {
            return;
         }

         if (CheatBreaker.getInstance().method_19798().equalsIgnoreCase("dev")) {
            CheatBreaker.getInstance()
               .recoveredField1579
               .info(CheatBreaker.getInstance().recoveredField1553 + "[WS NetHandler] (IN) id: " + var2 + " Name: " + var4.getClass().getSimpleName());
         }

         var4.read(var1);
         var4.handle(this);
      } catch (Exception var5) {
         CheatBreaker.getInstance().recoveredField1579.error("Error from: " + var3);
         var5.printStackTrace();
      }
   }

   @Override
   public void onMessage(String var1) {
      if (this.isOpen()) {
         super.send(var1);
      }
   }

   public AssetsWebSocket(URI var1, Map<String, String> var2) {
      super(var1, new Draft_6455(), var2, 0);
   }

   @Override
   public void onError(Exception var1) {
      CheatBreaker.getInstance().recoveredField1579.error(CheatBreaker.getInstance().recoveredField1553 + "Error: " + var1.getMessage());
      var1.printStackTrace();
   }

   public void handleBulkFriends(WSPacketBulkFriends var1) {
      CheatBreaker.getInstance().getFriendsManager().getFriendRequests().clear();

      for (JsonElement var4 : var1.getBulkArray()) {
         JsonObject var5 = var4.getAsJsonObject();
         String var6 = var5.get("uuid").getAsString();
         String var7 = var5.get("name").getAsString();
         FriendRequest var8 = new FriendRequest(var7, var6);
         CheatBreaker.getInstance().getFriendsManager().getFriendRequests().put(var6, var8);
         OverlayGui.getInstance().handleFriendRequest(var8, true);
      }
   }

   @Override
   public void onMessage(ByteBuffer var1) {
      this.method_10121(new PacketBuffer(Unpooled.wrappedBuffer(var1.array())));
   }

   public void method_10117(WSPacketFormattedConsoleOutput var1) {
      String var2 = var1.method_05710();
      String var3 = var1.method_05711();
      CheatBreaker.getInstance()
         .method_19794()
         .add(
            EnumChatFormatting.DARK_GRAY
               + "["
               + EnumChatFormatting.RESET
               + var1.method_05710()
               + EnumChatFormatting.DARK_GRAY
               + "] "
               + EnumChatFormatting.RESET
               + var1.method_05711()
         );
   }

   @Override
   public boolean isOpen() {
      return super.isOpen();
   }

   public static byte[] getKeyResponse(byte[] var0, byte[] var1) throws java.security.InvalidKeyException, java.security.NoSuchAlgorithmException, java.security.spec.InvalidKeySpecException, javax.crypto.IllegalBlockSizeException, javax.crypto.BadPaddingException, javax.crypto.NoSuchPaddingException {
      PublicKey var2 = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(var0));
      Cipher var3 = Cipher.getInstance("RSA");
      var3.init(1, var2);
      return var3.doFinal(var1);
   }
}
