package net.minecraft.client.network;

import com.google.common.base.Objects;
import com.mojang.authlib.GameProfile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.data.LanguageMetadataSection;
import net.minecraft.entity.monster.EntityIronGolem$AINearestAttackableTargetNonCreeper;
import net.minecraft.network.play.server.S38PacketPlayerListItem$AddPlayerData;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldSettings$GameType;

public class NetworkPlayerInfo {
   public WorldSettings$GameType gameType;
   public EntityIronGolem$AINearestAttackableTargetNonCreeper field_0012;
   public IChatComponent displayName;
   public ResourceLocation locationCape;
   public int field_0001;
   public int field_0002;
   public GameProfile gameProfile;
   public int field_0009;
   public LanguageMetadataSection field_0003;
   public ResourceLocation locationSkin;
   public boolean playerTexturesLoaded = false;
   public String skinType;
   public long field_0008;
   public long field_0004;
   public long field_0010;

   public int method_13002() {
      return this.field_0001;
   }

   public void setResponseTime(int var1) {
      this.field_0002 = var1;
   }

   public NetworkPlayerInfo(GameProfile var1) {
      this.field_0009 = 0;
      this.field_0001 = 0;
      this.field_0008 = 408421904L & 404547699518615877L;
      this.field_0004 = 41943425L & -4160987017390058452L;
      this.field_0010 = 922355114726429106L & -922355116701179900L;
      this.gameProfile = var1;
   }

   public long method_13015() {
      return this.field_0010;
   }

   public void loadPlayerTextures() {
      synchronized (this) {
         if (!this.playerTexturesLoaded) {
            this.playerTexturesLoaded = true;
            Minecraft.getMinecraft().getSkinManager().loadProfileTextures(this.gameProfile, new NetworkPlayerInfo$1(this), true);
         }
      }
   }

   public int method_13014() {
      return this.field_0009;
   }

   public void method_13004(long var1) {
      this.field_0010 = var1;
   }

   public long method_12995() {
      return this.field_0008;
   }

   public NetworkPlayerInfo(S38PacketPlayerListItem$AddPlayerData var1) {
      this.field_0009 = 0;
      this.field_0001 = 0;
      this.field_0008 = 102418180L & 1103169706L;
      this.field_0004 = 2228291622991405200L & -2228291623572074237L;
      this.field_0010 = 3685133313953499140L & -3685133314201259391L;
      this.gameProfile = var1.getProfile();
      this.gameType = var1.getGameMode();
      this.field_0002 = var1.getPing();
      this.displayName = var1.getDisplayName();
   }

   public IChatComponent getDisplayName() {
      return this.displayName;
   }

   public void method_13017(long var1) {
      this.field_0004 = var1;
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
      this.field_0009 = var1;
   }

   public void setGameType(WorldSettings$GameType var1) {
      this.gameType = var1;
   }

   public void method_13001(long var1) {
      this.field_0008 = var1;
   }

   public ResourceLocation getLocationCape() {
      if (this.locationCape == null) {
         this.loadPlayerTextures();
      }

      return this.locationCape;
   }

   public long method_12994() {
      return this.field_0004;
   }

   public int method_13010() {
      return this.field_0002;
   }

   public boolean hasLocationSkin() {
      return this.locationSkin != null;
   }

   public ResourceLocation getLocationSkin() {
      if (this.locationSkin == null) {
         this.loadPlayerTextures();
      }

      return (ResourceLocation)Objects.firstNonNull(this.locationSkin, DefaultPlayerSkin.getDefaultSkin(this.gameProfile.getId()));
   }

   public WorldSettings$GameType getGameType() {
      return this.gameType;
   }

   public void method_13000(int var1) {
      this.field_0001 = var1;
   }
}
