package net.minecraft.network.play.server;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import java.util.List;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldSettings;

public class S38PacketPlayerListItem implements Packet<INetHandlerPlayClient> {
   public S38PacketPlayerListItem.Action action;
   public List<S38PacketPlayerListItem.AddPlayerData> players = Lists.newArrayList();

   public S38PacketPlayerListItem.Action getAction() {
      return this.action;
   }

   public List<S38PacketPlayerListItem.AddPlayerData> getEntries() {
      return this.players;
   }

   public S38PacketPlayerListItem(S38PacketPlayerListItem.Action var1, Iterable<EntityPlayerMP> var2) {
      this.action = var1;

      for (EntityPlayerMP var4 : var2) {
         this.players
            .add(
               new S38PacketPlayerListItem.AddPlayerData(
                  var4.getGameProfile(), var4.ping, var4.theItemInWorldManager.getGameType(), var4.getTabListDisplayName()
               )
            );
      }
   }

   public S38PacketPlayerListItem() {
   }

   public void processPacket(INetHandlerPlayClient var1) {
      var1.handlePlayerListItem(this);
   }

   @Override
   public String toString() {
      return Objects.toStringHelper(this).add("action", this.action).add("entries", this.players).toString();
   }

   @Override
   public void readPacketData(PacketBuffer var1) throws java.io.IOException {
      this.action = var1.readEnumValue(S38PacketPlayerListItem.Action.class);
      int var2 = var1.readVarIntFromBuffer();

      for (int var3 = 0; var3 < var2; var3++) {
         GameProfile var4 = null;
         int var5 = 0;
         WorldSettings.GameType var6 = null;
         IChatComponent var7 = null;
         switch (this.action) {
            case ADD_PLAYER:
               var4 = new GameProfile(var1.readUuid(), var1.readStringFromBuffer(16));
               int var8 = var1.readVarIntFromBuffer();
               int var9 = 0;

               for (; var9 < var8; var9++) {
                  String var10 = var1.readStringFromBuffer(32767);
                  String var11 = var1.readStringFromBuffer(32767);
                  if (var1.readBoolean()) {
                     var4.getProperties().put(var10, new Property(var10, var11, var1.readStringFromBuffer(32767)));
                  } else {
                     var4.getProperties().put(var10, new Property(var10, var11));
                  }
               }

               var6 = WorldSettings.GameType.getByID(var1.readVarIntFromBuffer());
               var5 = var1.readVarIntFromBuffer();
               if (var1.readBoolean()) {
                  var7 = var1.readChatComponent();
               }
               break;
            case UPDATE_GAME_MODE:
               var4 = new GameProfile(var1.readUuid(), (String)null);
               var6 = WorldSettings.GameType.getByID(var1.readVarIntFromBuffer());
               break;
            case UPDATE_LATENCY:
               var4 = new GameProfile(var1.readUuid(), (String)null);
               var5 = var1.readVarIntFromBuffer();
               break;
            case UPDATE_DISPLAY_NAME:
               var4 = new GameProfile(var1.readUuid(), (String)null);
               if (var1.readBoolean()) {
                  var7 = var1.readChatComponent();
               }
               break;
            case REMOVE_PLAYER:
               var4 = new GameProfile(var1.readUuid(), (String)null);
         }

         this.players.add(new S38PacketPlayerListItem.AddPlayerData(var4, var5, var6, var7));
      }
   }

   @Override
   public void writePacketData(PacketBuffer var1) throws java.io.IOException {
      var1.writeEnumValue(this.action);
      var1.writeVarIntToBuffer(this.players.size());

      for (S38PacketPlayerListItem.AddPlayerData var3 : this.players) {
         switch (this.action) {
            case ADD_PLAYER:
               var1.writeUuid(var3.getProfile().getId());
               var1.writeString(var3.getProfile().getName());
               var1.writeVarIntToBuffer(var3.getProfile().getProperties().size());

               for (Property var5 : var3.getProfile().getProperties().values()) {
                  var1.writeString(var5.getName());
                  var1.writeString(var5.getValue());
                  if (var5.hasSignature()) {
                     var1.writeBoolean(true);
                     var1.writeString(var5.getSignature());
                  } else {
                     var1.writeBoolean(false);
                  }
               }

               var1.writeVarIntToBuffer(var3.getGameMode().getID());
               var1.writeVarIntToBuffer(var3.getPing());
               if (var3.getDisplayName() == null) {
                  var1.writeBoolean(false);
               } else {
                  var1.writeBoolean(true);
                  var1.writeChatComponent(var3.getDisplayName());
               }
               break;
            case UPDATE_GAME_MODE:
               var1.writeUuid(var3.getProfile().getId());
               var1.writeVarIntToBuffer(var3.getGameMode().getID());
               break;
            case UPDATE_LATENCY:
               var1.writeUuid(var3.getProfile().getId());
               var1.writeVarIntToBuffer(var3.getPing());
               break;
            case UPDATE_DISPLAY_NAME:
               var1.writeUuid(var3.getProfile().getId());
               if (var3.getDisplayName() == null) {
                  var1.writeBoolean(false);
               } else {
                  var1.writeBoolean(true);
                  var1.writeChatComponent(var3.getDisplayName());
               }
               break;
            case REMOVE_PLAYER:
               var1.writeUuid(var3.getProfile().getId());
         }
      }
   }

   public S38PacketPlayerListItem(S38PacketPlayerListItem.Action var1, EntityPlayerMP... var2) {
      this.action = var1;

      for (EntityPlayerMP var6 : var2) {
         this.players
            .add(
               new S38PacketPlayerListItem.AddPlayerData(
                  var6.getGameProfile(), var6.ping, var6.theItemInWorldManager.getGameType(), var6.getTabListDisplayName()
               )
            );
      }
   }

   public static enum Action {
      ADD_PLAYER,
      UPDATE_GAME_MODE,
      UPDATE_LATENCY,
      UPDATE_DISPLAY_NAME,
      REMOVE_PLAYER;
   }

   public class AddPlayerData {
      public int ping;
      public GameProfile profile;
      public WorldSettings.GameType gamemode;
      public IChatComponent displayName;

      public WorldSettings.GameType getGameMode() {
         return this.gamemode;
      }

      public GameProfile getProfile() {
         return this.profile;
      }

      @Override
      public String toString() {
         return Objects.toStringHelper(this)
            .add("latency", this.ping)
            .add("gameMode", this.gamemode)
            .add("profile", this.profile)
            .add("displayName", this.displayName == null ? null : IChatComponent.Serializer.componentToJson(this.displayName))
            .toString();
      }

      public AddPlayerData(GameProfile var2, int var3, WorldSettings.GameType var4, IChatComponent var5) {
         this.profile = var2;
         this.ping = var3;
         this.gamemode = var4;
         this.displayName = var5;
      }

      public IChatComponent getDisplayName() {
         return this.displayName;
      }

      public int getPing() {
         return this.ping;
      }
   }
}
