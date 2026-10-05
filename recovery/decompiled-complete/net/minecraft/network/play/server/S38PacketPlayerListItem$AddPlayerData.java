package net.minecraft.network.play.server;

import com.google.common.base.Objects;
import com.mojang.authlib.GameProfile;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.IChatComponent$Serializer;
import net.minecraft.world.WorldSettings$GameType;

public class S38PacketPlayerListItem$AddPlayerData {
   public int ping;
   public GameProfile profile;
   public WorldSettings$GameType gamemode;
   public IChatComponent displayName;

   public WorldSettings$GameType getGameMode() {
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
         .add("displayName", this.displayName == null ? null : IChatComponent$Serializer.componentToJson(this.displayName))
         .toString();
   }

   public S38PacketPlayerListItem$AddPlayerData(S38PacketPlayerListItem var1, GameProfile var2, int var3, WorldSettings$GameType var4, IChatComponent var5) {
      this.field_179968_a = var1;
      super();
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
