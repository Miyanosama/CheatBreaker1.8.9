package net.minecraft.client.network;

import com.google.common.base.Objects;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import com.mojang.authlib.minecraft.MinecraftProfileTexture.Type;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.network.play.server.S38PacketPlayerListItem;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldSettings;

public class NetworkPlayerInfo {
   public WorldSettings.GameType gameType;
   public IChatComponent displayName;
   public ResourceLocation locationCape;
   public int recoveredField841;
   public int recoveredField842;
   public GameProfile gameProfile;
   public int recoveredField843;
   public ResourceLocation locationSkin;
   public boolean playerTexturesLoaded = false;
   public String skinType;
   public long recoveredField844;
   public long recoveredField845;
   public long recoveredField846;

   public int method_13002() {
      return this.recoveredField841;
   }

   public void setResponseTime(int var1) {
      this.recoveredField842 = var1;
   }

   public NetworkPlayerInfo(GameProfile var1) {
      this.recoveredField843 = 0;
      this.recoveredField841 = 0;
      this.recoveredField844 = 0L;
      this.recoveredField845 = 0L;
      this.recoveredField846 = 0L;
      this.gameProfile = var1;
   }

   public long method_13015() {
      return this.recoveredField846;
   }

   public void loadPlayerTextures() {
      synchronized (this) {
         if (!this.playerTexturesLoaded) {
            this.playerTexturesLoaded = true;
            Minecraft.getMinecraft().getSkinManager().loadProfileTextures(this.gameProfile, new SkinManager.SkinAvailableCallback() {
               @Override
               public void skinAvailable(Type var1, ResourceLocation var2, MinecraftProfileTexture var3) {
                  switch (var1) {
                     case SKIN:
                        NetworkPlayerInfo.this.locationSkin = var2;
                        NetworkPlayerInfo.this.skinType = var3.getMetadata("model");
                        if (NetworkPlayerInfo.this.skinType == null) {
                           NetworkPlayerInfo.this.skinType = "default";
                        }
                        break;
                     case CAPE:
                        NetworkPlayerInfo.this.locationCape = var2;
                  }
               }
            }, true);
         }
      }
   }

   public int method_13014() {
      return this.recoveredField843;
   }

   public void method_13004(long var1) {
      this.recoveredField846 = var1;
   }

   public long method_12995() {
      return this.recoveredField844;
   }

   public NetworkPlayerInfo(S38PacketPlayerListItem.AddPlayerData var1) {
      this.recoveredField843 = 0;
      this.recoveredField841 = 0;
      this.recoveredField844 = 0L;
      this.recoveredField845 = 0L;
      this.recoveredField846 = 0L;
      this.gameProfile = var1.getProfile();
      this.gameType = var1.getGameMode();
      this.recoveredField842 = var1.getPing();
      this.displayName = var1.getDisplayName();
   }

   public IChatComponent getDisplayName() {
      return this.displayName;
   }

   public void method_13017(long var1) {
      this.recoveredField845 = var1;
   }

   public String getSkinType() {
      return this.skinType == null ? DefaultPlayerSkin.getSkinType(this.gameProfile.getId()) : this.skinType;
   }

   public GameProfile getGameProfile() {
      return this.gameProfile;
   }

   public void setDisplayName(IChatComponent var1) {
      this.displayName = var1;
   }

   public ScorePlayerTeam getPlayerTeam() {
      return Minecraft.getMinecraft().theWorld.Z().getPlayersTeam(this.getGameProfile().getName());
   }

   public void method_13016(int var1) {
      this.recoveredField843 = var1;
   }

   public void setGameType(WorldSettings.GameType var1) {
      this.gameType = var1;
   }

   public void method_13001(long var1) {
      this.recoveredField844 = var1;
   }

   public ResourceLocation getLocationCape() {
      if (this.locationCape == null) {
         this.loadPlayerTextures();
      }

      return this.locationCape;
   }

   public long method_12994() {
      return this.recoveredField845;
   }

   public int method_13010() {
      return this.recoveredField842;
   }

   public boolean hasLocationSkin() {
      return this.locationSkin != null;
   }

   public ResourceLocation getLocationSkin() {
      if (this.locationSkin == null) {
         this.loadPlayerTextures();
      }

      return Objects.firstNonNull(this.locationSkin, DefaultPlayerSkin.getDefaultSkin(this.gameProfile.getId()));
   }

   public WorldSettings.GameType getGameType() {
      return this.gameType;
   }

   public void method_13000(int var1) {
      this.recoveredField841 = var1;
   }
}
