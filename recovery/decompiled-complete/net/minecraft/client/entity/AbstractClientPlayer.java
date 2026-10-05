package net.minecraft.client.entity;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.module.type.NickHiderModule;
import com.cheatbreaker.client.module.type.PerspectiveModule;
import com.cheatbreaker.client.util.ClientResourceManager;
import com.mojang.authlib.GameProfile;
import java.io.File;
import net.minecraft.client.Minecraft;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.ImageBufferDownload;
import net.minecraft.client.renderer.ThreadDownloadImageData;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.client.resources.data.FontMetadataSectionSerializer;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.src.Config;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraft.world.WorldSettings$GameType;
import net.optifine.player.CapeUtils;
import net.optifine.player.PlayerConfigurations;
import net.optifine.reflect.Reflector;
import org.apache.log4j.config.PropertySetterException;
import org.java_websocket.drafts.Draft_6455$TranslatedPayloadMetaData;

public abstract class AbstractClientPlayer extends EntityPlayer {
   public PropertySetterException field_0004;
   public boolean elytraOfCape;
   public long reloadCapeTimeMs;
   public ResourceLocation locationOfCape = null;
   public NetworkPlayerInfo playerInfo;
   public static ResourceLocation TEXTURE_ELYTRA = new ResourceLocation("textures/entity/elytra.png");
   public FontMetadataSectionSerializer field_0008;
   public String nameClear;
   public Draft_6455$TranslatedPayloadMetaData field_0002;

   public static ResourceLocation getLocationSkin(String var0) {
      return new ResourceLocation("skins/" + StringUtils.stripControlCodes(var0));
   }

   public NetworkPlayerInfo getPlayerInfo() {
      if (this.playerInfo == null) {
         this.playerInfo = Minecraft.getMinecraft().getNetHandler().getPlayerInfo(this.aK());
      }

      return this.playerInfo;
   }

   @Override
   public Vec3 getLook(float var1) {
      return this.getVectorForRotation(this.z, this.y);
   }

   public String getNameClear() {
      return this.nameClear;
   }

   public boolean isElytraOfCape() {
      return this.elytraOfCape;
   }

   public float getFovModifier() {
      PerspectiveModule var1 = CheatBreaker.getInstance().getModuleManager().field_0012;
      float var2 = 1.0F;
      if (this.bA.isFlying) {
         var2 *= 1.1F;
      }

      IAttributeInstance var3 = this.getEntityAttribute(SharedMonsterAttributes.movementSpeed);
      if (var1.isEnabled()) {
         if (!var1.field_0022.method_08908()) {
            var2 = (float)(var2 * ((var3.getAttributeValue() / this.bA.getWalkSpeed() + 1.0) / 2.0));
         }
      } else {
         var2 = (float)(var2 * ((var3.getAttributeValue() / this.bA.getWalkSpeed() + 1.0) / 2.0));
      }

      if (this.bA.getWalkSpeed() == 0.0F || Float.isNaN(var2) || Float.isInfinite(var2)) {
         var2 = 1.0F;
      }

      if (this.isUsingItem() && this.getItemInUse().getItem() == Items.bow) {
         int var4 = this.method_00420();
         float var5 = var4 / 20.0F;
         if (var5 > 1.0F) {
            var5 = 1.0F;
         } else {
            var5 *= var5;
         }

         if (var1.isEnabled()) {
            var2 *= 1.0F - var5 * var1.field_0012.method_08905();
         } else {
            var2 *= 1.0F - var5 * 0.15F;
         }
      }

      return Reflector.ForgeHooksClient_getOffsetFOV.exists() ? Reflector.callFloat(Reflector.ForgeHooksClient_getOffsetFOV, this, var2) : var2;
   }

   public long getReloadCapeTimeMs() {
      return this.reloadCapeTimeMs;
   }

   public ResourceLocation getLocationCape() {
      if (!Config.isShowCapes()) {
         return null;
      } else {
         if (this.reloadCapeTimeMs != (318914844L & 1073754242L) && System.currentTimeMillis() > this.reloadCapeTimeMs) {
            CapeUtils.method_05779(this);
            this.reloadCapeTimeMs = 348129040L & 170918115L;
         }

         if (this.locationOfCape != null) {
            return this.locationOfCape;
         } else {
            NetworkPlayerInfo var1 = this.getPlayerInfo();
            return var1 == null ? null : var1.getLocationCape();
         }
      }
   }

   public void setReloadCapeTimeMs(long var1) {
      this.reloadCapeTimeMs = var1;
   }

   public String getSkinType() {
      NetworkPlayerInfo var1 = this.getPlayerInfo();
      return var1 == null ? DefaultPlayerSkin.getSkinType(this.aK()) : var1.getSkinType();
   }

   public void setElytraOfCape(boolean var1) {
      this.elytraOfCape = var1;
   }

   public void setLocationOfCape(ResourceLocation var1) {
      this.locationOfCape = var1;
   }

   public boolean hasSkin() {
      NetworkPlayerInfo var1 = this.getPlayerInfo();
      return var1 != null && var1.hasLocationSkin();
   }

   public ResourceLocation getLocationSkin() {
      NickHiderModule var1 = CheatBreaker.getInstance().getModuleManager().field_0005;
      NetworkPlayerInfo var2 = this.getPlayerInfo();
      if (var1.isEnabled() && var1.field_0001.method_08908()) {
         if (!var1.field_0005.method_08908() && Minecraft.getMinecraft().getSession().getUsername().equals(this.z_())) {
            return var2 == null ? DefaultPlayerSkin.getDefaultSkin(this.aK()) : var2.getLocationSkin();
         } else {
            return DefaultPlayerSkin.getDefaultSkin(this.aK());
         }
      } else if (var1.isEnabled() && var1.field_0005.method_08908() && Minecraft.getMinecraft().getSession().getUsername().equals(this.z_())) {
         return DefaultPlayerSkin.getDefaultSkin(this.aK());
      } else {
         return var2 == null ? DefaultPlayerSkin.getDefaultSkin(this.aK()) : var2.getLocationSkin();
      }
   }

   @Override
   public boolean isSpectator() {
      NetworkPlayerInfo var1 = Minecraft.getMinecraft().getNetHandler().getPlayerInfo(this.getGameProfile().getId());
      return var1 != null && var1.getGameType() == WorldSettings$GameType.SPECTATOR;
   }

   public boolean hasPlayerInfo() {
      return this.getPlayerInfo() != null;
   }

   public AbstractClientPlayer(World var1, GameProfile var2) {
      super(var1, var2);
      this.reloadCapeTimeMs = 7031297279269014720L & 67654144L;
      this.elytraOfCape = false;
      this.nameClear = null;
      this.nameClear = var2.getName();
      if (this.nameClear != null && !this.nameClear.isEmpty()) {
         this.nameClear = StringUtils.stripControlCodes(this.nameClear);
      }

      ClientResourceManager var3 = CheatBreaker.getInstance().method_19791().method_27045(this.aK());
      ClientResourceManager var4 = CheatBreaker.getInstance().method_19791().method_27048(this.aK());
      if (var3 != null && CheatBreaker.getInstance().getGlobalSettings().field_0044.method_08908()) {
         this.setLocationOfCape(var3.method_20859());
         this.method_00451(var3);
      } else if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0086.getValue()) {
         CapeUtils.method_05780(this);
      }

      if (var4 != null) {
         this.method_00396(var4);
      }

      PlayerConfigurations.getPlayerConfiguration(this);
   }

   public static ThreadDownloadImageData getDownloadImageSkin(ResourceLocation var0, String var1) {
      TextureManager var2 = Minecraft.getMinecraft().getTextureManager();
      Object var3 = var2.getTexture(var0);
      if (var3 == null) {
         var3 = new ThreadDownloadImageData(
            (File)null,
            String.format("http://skins.minecraft.net/MinecraftSkins/%s.png", StringUtils.stripControlCodes(var1)),
            DefaultPlayerSkin.getDefaultSkin(getOfflineUUID(var1)),
            new ImageBufferDownload()
         );
         var2.loadTexture(var0, (ITextureObject)var3);
      }

      return (ThreadDownloadImageData)var3;
   }

   public boolean hasElytraCape() {
      ResourceLocation var1 = this.getLocationCape();
      return var1 == null ? false : (var1 == this.locationOfCape ? this.elytraOfCape : true);
   }

   public ResourceLocation getLocationOfCape() {
      return this.locationOfCape;
   }
}
