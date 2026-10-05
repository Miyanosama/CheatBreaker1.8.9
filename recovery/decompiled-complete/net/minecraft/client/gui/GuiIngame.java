package net.minecraft.client.gui;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.module.type.AnimationsModule;
import com.cheatbreaker.client.module.type.TextureOptionsModule;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.module.CBProfileCreateGui;
import io.netty.handler.codec.rtsp.RtspRequestDecoder;
import java.util.Random;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandGameMode;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.boss.BossStatus;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipesBanners$1;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.potion.Potion;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.src.Config;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.FoodStats;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition$MovingObjectType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.world.border.WorldBorder;
import net.optifine.CustomColors;
import net.optifine.CustomGuiProperties$EnumVariant;
import recovered.unidentified.UnidentifiedClass0105;
import recovered.unidentified.UnidentifiedClass0144;
import recovered.unidentified.UnidentifiedClass1798;

public class GuiIngame extends Gui {
   public GuiStreamIndicator streamIndicator;
   public static ResourceLocation field_0031 = new ResourceLocation("textures/gui/widgets.png");
   public RenderItem itemRenderer;
   public int titlesTimer;
   public long field_0006;
   public static ResourceLocation field_0008 = new ResourceLocation("textures/misc/vignette.png");
   public GuiNewChat field_0032;
   public String displayedTitle;
   public int field_0009;
   public Minecraft mc;
   public CommandGameMode field_0005;
   public int titleFadeIn;
   public RecipesBanners$1 field_0022;
   public CustomGuiProperties$EnumVariant field_0014;
   public int updateCounter;
   public RtspRequestDecoder field_0030;
   public long field_0003;
   public String displayedSubTitle;
   public int titleDisplayTime;
   public NBTTagList field_0029;
   public GuiOverlayDebug field_0002;
   public GuiPlayerTabOverlay overlayPlayerList;
   public static ResourceLocation field_0004 = new ResourceLocation("textures/misc/pumpkinblur.png");
   public Random field_0000 = new Random();
   public static boolean field_0027 = false;
   public boolean recordIsPlaying;
   public float field_0025;
   public float field_0016;
   public String recordPlaying = "";
   public int recordPlayingUpFor;
   public ItemStack highlightingItemStack;
   public int field_0007;
   public int titleFadeOut;
   public int remainingHighlightTicks;
   public GuiSpectator field_0010;

   public void setRecordPlaying(String var1, boolean var2) {
      this.recordPlaying = var1;
      this.recordPlayingUpFor = 60;
      this.recordIsPlaying = var2;
   }

   public GuiNewChat getChatGUI() {
      return this.field_0032;
   }

   public void method_28526(String var1) {
      this.displayedTitle = var1;
   }

   public void setDefaultTitlesTimes() {
      this.titleFadeIn = 10;
      this.titleDisplayTime = 70;
      this.titleFadeOut = 20;
   }

   public GuiSpectator getSpectatorGui() {
      return this.field_0010;
   }

   public void method_28523(ScaledResolution var1) {
      GlStateManager.disableDepth();
      GlStateManager.depthMask(false);
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.disableAlpha();
      this.mc.getTextureManager().bindTexture(field_0004);
      Tessellator var2 = Tessellator.getInstance();
      WorldRenderer var3 = var2.getWorldRenderer();
      var3.begin(7, DefaultVertexFormats.POSITION_TEX);
      var3.pos(0.0, var1.getScaledHeight(), -90.0).tex(0.0, 1.0).endVertex();
      var3.pos(var1.getScaledWidth(), var1.getScaledHeight(), -90.0).tex(1.0, 1.0).endVertex();
      var3.pos(var1.getScaledWidth(), 0.0, -90.0).tex(1.0, 0.0).endVertex();
      var3.pos(0.0, 0.0, -90.0).tex(0.0, 0.0).endVertex();
      var2.draw();
      GlStateManager.depthMask(true);
      GlStateManager.enableDepth();
      GlStateManager.enableAlpha();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public void renderPortal(float var1, ScaledResolution var2) {
      if (var1 < 1.0F) {
         var1 *= var1;
         var1 *= var1;
         var1 = var1 * 0.8F + 0.2F;
      }

      GlStateManager.disableAlpha();
      GlStateManager.disableDepth();
      GlStateManager.depthMask(false);
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.color(1.0F, 1.0F, 1.0F, var1);
      this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
      TextureAtlasSprite var3 = this.mc.getBlockRendererDispatcher().getBlockModelShapes().getTexture(Blocks.portal.getDefaultState());
      float var4 = var3.getMinU();
      float var5 = var3.getMinV();
      float var6 = var3.getMaxU();
      float var7 = var3.getMaxV();
      Tessellator var8 = Tessellator.getInstance();
      WorldRenderer var9 = var8.getWorldRenderer();
      var9.begin(7, DefaultVertexFormats.POSITION_TEX);
      var9.pos(0.0, var2.getScaledHeight(), -90.0).tex(var4, var7).endVertex();
      var9.pos(var2.getScaledWidth(), var2.getScaledHeight(), -90.0).tex(var6, var7).endVertex();
      var9.pos(var2.getScaledWidth(), 0.0, -90.0).tex(var6, var5).endVertex();
      var9.pos(0.0, 0.0, -90.0).tex(var4, var5).endVertex();
      var8.draw();
      GlStateManager.depthMask(true);
      GlStateManager.enableDepth();
      GlStateManager.enableAlpha();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
   }

   public void updateTick() {
      if (this.recordPlayingUpFor > 0) {
         this.recordPlayingUpFor--;
      }

      if (this.titlesTimer > 0) {
         this.titlesTimer--;
         if (this.titlesTimer <= 0) {
            this.displayedTitle = "";
            this.displayedSubTitle = "";
         }
      }

      this.updateCounter++;
      this.streamIndicator.updateStreamAlpha();
      if (this.mc.thePlayer != null) {
         ItemStack var1 = this.mc.thePlayer.bi.getCurrentItem();
         if (var1 == null) {
            this.remainingHighlightTicks = 0;
         } else if (this.highlightingItemStack != null
            && var1.getItem() == this.highlightingItemStack.getItem()
            && ItemStack.areItemStackTagsEqual(var1, this.highlightingItemStack)
            && (var1.isItemStackDamageable() || var1.getMetadata() == this.highlightingItemStack.getMetadata())) {
            if (this.remainingHighlightTicks > 0) {
               this.remainingHighlightTicks--;
            }
         } else {
            this.remainingHighlightTicks = 40;
         }

         this.highlightingItemStack = var1;
      }
   }

   public boolean showCrosshair() {
      if (this.mc.gameSettings.field_0109 && !this.mc.thePlayer.hasReducedDebug() && !this.mc.gameSettings.reducedDebugInfo) {
         return false;
      } else if (this.mc.playerController.method_19872()) {
         if (this.mc.pointedEntity != null) {
            return true;
         } else if (this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectPosition$MovingObjectType.BLOCK) {
            BlockPos var1 = this.mc.objectMouseOver.getBlockPos();
            return this.mc.theWorld.getTileEntity(var1) instanceof IInventory;
         } else {
            return false;
         }
      } else {
         return true;
      }
   }

   public void renderGameOverlay(float var1) {
      ScaledResolution var2 = new ScaledResolution(this.mc);
      int var3 = var2.getScaledWidth();
      int var4 = var2.getScaledHeight();
      this.mc.entityRenderer.setupOverlayRendering();
      GlStateManager.enableBlend();
      TextureOptionsModule var5 = CheatBreaker.getInstance().getModuleManager().field_0047;
      boolean var6 = var5.isEnabled();
      if (!Config.method_03876() && (!var5.field_0023.getValue().equals("Static") || !var6)) {
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      } else {
         this.method_28516(this.mc.thePlayer.a_(var1), var2);
      }

      if (var5.field_0001.method_08908() && var5.isEnabled()) {
         this.method_28534(var5.field_0024.method_08905() / 100.0F, var2);
      } else {
         GlStateManager.enableDepth();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      }

      ItemStack var7 = this.mc.thePlayer.bi.armorItemInSlot(3);
      boolean var8 = !var6 || (Boolean)var5.field_0022.getValue();
      if (this.mc.gameSettings.thirdPersonView == 0 && var7 != null && var7.getItem() == Item.getItemFromBlock(Blocks.pumpkin) && var8) {
         this.method_28523(var2);
      }

      if (!this.mc.thePlayer.isPotionActive(Potion.confusion)) {
         float var9 = this.mc.thePlayer.field_0015 + (this.mc.thePlayer.field_0018 - this.mc.thePlayer.field_0015) * var1;
         if (var9 > 0.0F) {
            this.renderPortal(var9, var2);
         }
      }

      if (this.mc.playerController.method_19872()) {
         this.field_0010.renderTooltip(var2, var1);
      } else {
         this.renderTooltip(var2, var1);
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(icons);
      GlStateManager.enableBlend();
      if (this.showCrosshair()) {
         if (CheatBreaker.getInstance().getModuleManager().field_0016.isEnabled()) {
            CheatBreaker.getInstance().getModuleManager().field_0016.method_28369(var3 / 2, var4 / 2, true);
         } else {
            GlStateManager.tryBlendFuncSeparate(775, 769, 1, 0);
            GlStateManager.enableAlpha();
            this.drawTexturedModalRect(var3 / 2 - 7, var4 / 2 - 7, 0, 0, 16, 16);
         }
      }

      GlStateManager.enableAlpha();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      this.mc.mcProfiler.startSection("bossHealth");
      if (!CheatBreaker.getInstance().getModuleManager().field_0048.method_28866() && CheatBreaker.getInstance().getModuleManager().field_0048.isEnabled()) {
         if (BossStatus.bossName != null && BossStatus.statusBarTime > 0) {
            CheatBreaker.getInstance()
               .getModuleManager()
               .field_0048
               .method_26218(BossStatus.bossName, BossStatus.healthScale, var2.getScaledWidth() / 2 - 91, 12);
         }

         this.mc.getTextureManager().bindTexture(icons);
      }

      this.mc.mcProfiler.endSection();
      if (this.mc.playerController.shouldDrawHUD()) {
         this.method_28532(var2);
      }

      GlStateManager.disableBlend();
      if (this.mc.thePlayer.getSleepTimer() > 0) {
         this.mc.mcProfiler.startSection("sleep");
         GlStateManager.disableDepth();
         GlStateManager.disableAlpha();
         int var13 = this.mc.thePlayer.getSleepTimer();
         float var10 = var13 / 100.0F;
         if (var10 > 1.0F) {
            var10 = 1.0F - (var13 - 100) / 10.0F;
         }

         int var11 = (int)(220.0F * var10) << 24 | 1052704;
         a(0, 0, var3, var4, var11);
         GlStateManager.enableAlpha();
         GlStateManager.enableDepth();
         this.mc.mcProfiler.endSection();
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      int var14 = var3 / 2 - 91;
      if (this.mc.thePlayer.isRidingHorse()) {
         this.renderHorseJumpBar(var2, var14);
      } else if (this.mc.playerController.method_19878()) {
         this.renderExpBar(var2, var14);
      }

      if (this.mc.gameSettings.heldItemTooltips && !this.mc.playerController.method_19872()) {
         this.renderSelectedItem(var2);
      } else if (this.mc.thePlayer.isSpectator()) {
         this.field_0010.renderSelectedItem(var2);
      }

      if (this.mc.method_20336()) {
         this.method_28517(var2);
      }

      if (this.mc.gameSettings.field_0191) {
         this.field_0002.renderDebugInfo(var2);
      }

      if (field_0027) {
         this.mc.fontRendererObj.drawStringWithShadow("Uploading screenshot...", 4.0F, var2.getScaledHeight() - 24, -1);
      }

      if (CheatBreaker.getInstance().getModuleManager().chatModule.field_0043 && this.mc.currentScreen instanceof GuiChat) {
         this.mc.fontRendererObj.drawStringWithShadow("Chat is hidden", 4.0F, var2.getScaledHeight() - 24, -8947849);
      }

      if (this.mc.currentScreen instanceof CBModulesGui
         || this.mc.currentScreen instanceof UnidentifiedClass0105
         || this.mc.currentScreen instanceof CBProfileCreateGui) {
         CheatBreaker.getInstance().method_19817().method_21935(new UnidentifiedClass0144(var2));
      }

      CheatBreaker.getInstance().method_19817().method_21935(new UnidentifiedClass1798(var2));
      if (!this.mc.gameSettings.field_0191 || (Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0003.getValue()) {
         CheatBreaker.getInstance().method_19817().method_21935(new GuiDrawEvent(var2));
      }

      if (this.recordPlayingUpFor > 0) {
         this.mc.mcProfiler.startSection("overlayMessage");
         float var15 = this.recordPlayingUpFor - var1;
         int var18 = (int)(var15 * 255.0F / 20.0F);
         if (var18 > 255) {
            var18 = 255;
         }

         if (var18 > 8) {
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)(var3 / 2), (float)(var4 - 68), 0.0F);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            int var12 = 16777215;
            if (this.recordIsPlaying) {
               var12 = MathHelper.hsvToRGB(var15 / 50.0F, 0.7F, 0.6F) & 16777215;
            }

            this.getFontRenderer()
               .drawString(this.recordPlaying, -this.getFontRenderer().getStringWidth(this.recordPlaying) / 2, -4, var12 + (var18 << 24 & 0xFF000000));
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
         }

         this.mc.mcProfiler.endSection();
      }

      if (this.titlesTimer > 0) {
         this.mc.mcProfiler.startSection("titleAndSubtitle");
         float var16 = this.titlesTimer - var1;
         int var19 = 255;
         if (this.titlesTimer > this.titleFadeOut + this.titleDisplayTime) {
            float var22 = this.titleFadeIn + this.titleDisplayTime + this.titleFadeOut - var16;
            var19 = (int)(var22 * 255.0F / this.titleFadeIn);
         }

         if (this.titlesTimer <= this.titleFadeOut) {
            var19 = (int)(var16 * 255.0F / this.titleFadeOut);
         }

         var19 = MathHelper.clamp_int(var19, 0, 255);
         if (var19 > 8) {
            GlStateManager.pushMatrix();
            GlStateManager.translate((float)(var3 / 2), (float)(var4 / 2), 0.0F);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            GlStateManager.pushMatrix();
            GlStateManager.scale(4.0F, 4.0F, 4.0F);
            int var23 = var19 << 24 & 0xFF000000;
            if (CheatBreaker.getInstance().getGlobalSettings().field_0070.method_08908() && this.displayedSubTitle.contains("˙")) {
               this.getFontRenderer()
                  .drawString(this.displayedTitle, -this.getFontRenderer().getStringWidth(this.displayedTitle) / 2, -10.0F, 16777215 | var23, true);
            } else {
               this.getFontRenderer()
                  .drawString(this.displayedTitle, -this.getFontRenderer().getStringWidth(this.displayedTitle) / 2, -10.0F, 16777215 | var23, true);
            }

            GlStateManager.popMatrix();
            GlStateManager.pushMatrix();
            GlStateManager.scale(2.0F, 2.0F, 2.0F);
            if (CheatBreaker.getInstance().getGlobalSettings().field_0070.method_08908() && this.displayedSubTitle.contains("˙")) {
               this.getFontRenderer()
                  .drawString(this.displayedSubTitle, -this.getFontRenderer().getStringWidth(this.displayedSubTitle) / 2, 5.0F, 16777215 | var23, true);
            } else {
               this.getFontRenderer()
                  .drawString(this.displayedSubTitle, -this.getFontRenderer().getStringWidth(this.displayedSubTitle) / 2, 5.0F, 16777215 | var23, true);
            }

            GlStateManager.popMatrix();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
         }

         this.mc.mcProfiler.endSection();
      }

      Scoreboard var17 = this.mc.theWorld.Z();
      ScorePlayerTeam var21 = var17.getPlayersTeam(this.mc.thePlayer.z_());
      if (var21 != null) {
         int var24 = var21.getChatFormat().getColorIndex();
         if (var24 >= 0) {
            var17.getObjectiveInDisplaySlot(3 + var24);
         }
      }

      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.disableAlpha();
      GlStateManager.pushMatrix();
      GlStateManager.translate(0.0F, (float)(var4 - 48), 0.0F);
      if (!CheatBreaker.getInstance().getModuleManager().chatModule.method_28866() || !CheatBreaker.getInstance().getModuleManager().chatModule.isEnabled()) {
         this.mc.mcProfiler.startSection("chat");
         if (CheatBreaker.getInstance().getModuleManager().chatModule.isEnabled()) {
            this.field_0032.method_03276(this.updateCounter, CheatBreaker.getInstance().getModuleManager().chatModule.field_0013.method_08908());
         } else {
            this.field_0032.drawChat(this.updateCounter);
         }

         this.mc.mcProfiler.endSection();
      }

      GlStateManager.popMatrix();
      ScoreObjective var25 = var17.getObjectiveInDisplaySlot(0);
      if (!this.mc.gameSettings.field_0097.isKeyDown()
         || this.mc.isIntegratedServerRunning() && this.mc.thePlayer.sendQueue.getPlayerInfoMap().size() <= 1 && var25 == null) {
         this.overlayPlayerList.updatePlayerList(false);
      } else {
         this.overlayPlayerList.updatePlayerList(true);
         this.overlayPlayerList.renderPlayerlist(var3, var17, var25);
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.disableLighting();
      GlStateManager.enableAlpha();
   }

   public void renderHotbarItem(int var1, int var2, int var3, float var4, EntityPlayer var5) {
      ItemStack var6 = var5.bi.mainInventory[var1];
      if (var6 != null) {
         float var7 = var6.animationsToGo - var4;
         if (var7 > 0.0F) {
            GlStateManager.pushMatrix();
            float var8 = 1.0F + var7 / 5.0F;
            GlStateManager.translate((float)(var2 + 8), (float)(var3 + 12), 0.0F);
            GlStateManager.scale(1.0F / var8, (var8 + 1.0F) / 2.0F, 1.0F);
            GlStateManager.translate((float)(-(var2 + 8)), (float)(-(var3 + 12)), 0.0F);
         }

         this.itemRenderer.renderItemAndEffectIntoGUI(var6, var2, var3);
         if (var7 > 0.0F) {
            GlStateManager.popMatrix();
         }

         this.itemRenderer.renderItemOverlays(this.mc.fontRendererObj, var6, var2, var3);
      }
   }

   public void renderTooltip(ScaledResolution var1, float var2) {
      if (this.mc.getRenderViewEntity() instanceof EntityPlayer) {
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         this.mc.getTextureManager().bindTexture(field_0031);
         EntityPlayer var3 = (EntityPlayer)this.mc.getRenderViewEntity();
         int var4 = var1.getScaledWidth() / 2;
         float var5 = field_0003;
         field_0003 = -90.0F;
         this.drawTexturedModalRect(var4 - 91, var1.getScaledHeight() - 22, 0, 0, 182, 22);
         this.drawTexturedModalRect(var4 - 91 - 1 + var3.bi.currentItem * 20, var1.getScaledHeight() - 22 - 1, 0, 22, 24, 22);
         field_0003 = var5;
         GlStateManager.enableRescaleNormal();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         RenderHelper.enableGUIStandardItemLighting();

         for (int var6 = 0; var6 < 9; var6++) {
            int var7 = var1.getScaledWidth() / 2 - 90 + var6 * 20 + 2;
            int var8 = var1.getScaledHeight() - 16 - 3;
            this.renderHotbarItem(var6, var7, var8, var2, var3);
         }

         RenderHelper.disableStandardItemLighting();
         GlStateManager.disableRescaleNormal();
         GlStateManager.disableBlend();
      }
   }

   public void method_28534(float var1, ScaledResolution var2) {
      TextureOptionsModule var3 = CheatBreaker.getInstance().getModuleManager().field_0047;
      var1 = this.mc.thePlayer.getHealth() / 2.0F <= var3.field_0003.method_08905() ? var1 : 0.0F;
      var1 = MathHelper.clamp_float(var1, 0.0F, 1.0F);
      WorldBorder var4 = this.mc.theWorld.af();
      float var5 = (float)var4.getClosestDistance(this.mc.thePlayer);
      double var6 = Math.min(var4.method_20269() * var4.getWarningTime() * 1000.0, Math.abs(var4.getTargetSize() - var4.getDiameter()));
      double var8 = Math.max((double)var4.getWarningDistance(), var6);
      if (var5 < var8) {
         var5 = 1.0F - (float)(var5 / var8);
      } else {
         var5 = 0.0F;
      }

      this.field_0016 = (float)(this.field_0016 + (var1 - this.field_0016) * 0.01);
      GlStateManager.disableDepth();
      GlStateManager.depthMask(false);
      GlStateManager.tryBlendFuncSeparate(0, 769, 1, 0);
      if (var5 > 0.0F) {
         GlStateManager.color(0.0F, var5, var5, 1.0F);
      } else {
         GlStateManager.color(0.0F, this.field_0016, this.field_0016, 1.0F);
      }

      this.mc.getTextureManager().bindTexture(field_0008);
      Tessellator var10 = Tessellator.getInstance();
      WorldRenderer var11 = var10.getWorldRenderer();
      var11.begin(7, DefaultVertexFormats.POSITION_TEX);
      var11.pos(0.0, var2.getScaledHeight(), -90.0).tex(0.0, 1.0).endVertex();
      var11.pos(var2.getScaledWidth(), var2.getScaledHeight(), -90.0).tex(1.0, 1.0).endVertex();
      var11.pos(var2.getScaledWidth(), 0.0, -90.0).tex(1.0, 0.0).endVertex();
      var11.pos(0.0, 0.0, -90.0).tex(0.0, 0.0).endVertex();
      var10.draw();
      GlStateManager.depthMask(true);
      GlStateManager.enableDepth();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
   }

   public void renderHorseJumpBar(ScaledResolution var1, int var2) {
      this.mc.mcProfiler.startSection("jumpBar");
      this.mc.getTextureManager().bindTexture(Gui.icons);
      float var3 = this.mc.thePlayer.getHorseJumpPower();
      short var4 = 182;
      int var5 = (int)(var3 * (var4 + 1));
      int var6 = var1.getScaledHeight() - 32 + 3;
      this.drawTexturedModalRect(var2, var6, 0, 84, var4, 5);
      if (var5 > 0) {
         this.drawTexturedModalRect(var2, var6, 0, 89, var5, 5);
      }

      this.mc.mcProfiler.endSection();
   }

   public void resetPlayersOverlayFooterHeader() {
      this.overlayPlayerList.resetFooterHeader();
   }

   public void renderSelectedItem(ScaledResolution var1) {
      this.mc.mcProfiler.startSection("selectedItemName");
      if (this.remainingHighlightTicks > 0 && this.highlightingItemStack != null) {
         Object var2 = this.highlightingItemStack.getDisplayName();
         if (this.highlightingItemStack.hasDisplayName()) {
            var2 = EnumChatFormatting.ITALIC + var2;
         }

         int var3 = (var1.getScaledWidth() - this.getFontRenderer().getStringWidth((String)var2)) / 2;
         int var4 = var1.getScaledHeight() - 59;
         if (!this.mc.playerController.shouldDrawHUD()) {
            var4 += 14;
         }

         int var5 = (int)(this.remainingHighlightTicks * 256.0F / 10.0F);
         if (var5 > 255) {
            var5 = 255;
         }

         if (var5 > 0) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            this.getFontRenderer().drawStringWithShadow((String)var2, var3, var4, 16777215 + (var5 << 24));
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
         }
      }

      this.mc.mcProfiler.endSection();
   }

   public int getUpdateCounter() {
      return this.updateCounter;
   }

   public void method_28517(ScaledResolution var1) {
      this.mc.mcProfiler.startSection("demo");
      String var2 = "";
      if (this.mc.theWorld.K() >= (6214943998124613366L & 549836468L)) {
         var2 = I18n.format("demo.demoExpired");
      } else {
         var2 = I18n.format("demo.remainingTime", StringUtils.ticksToElapsedTime((int)((711720895L & 8421065137918957236L) - this.mc.theWorld.K())));
      }

      int var3 = this.getFontRenderer().getStringWidth(var2);
      this.getFontRenderer().drawStringWithShadow(var2, var1.getScaledWidth() - var3 - 10, 5.0F, 16777215);
      this.mc.mcProfiler.endSection();
   }

   public void displayTitle(String var1, String var2, int var3, int var4, int var5) {
      if (var1 == null && var2 == null && var3 < 0 && var4 < 0 && var5 < 0) {
         this.displayedTitle = "";
         this.displayedSubTitle = "";
         this.titlesTimer = 0;
      } else if (var1 != null) {
         this.displayedTitle = var1;
         this.titlesTimer = this.titleFadeIn + this.titleDisplayTime + this.titleFadeOut;
      } else if (var2 != null) {
         this.displayedSubTitle = var2;
      } else {
         if (var3 >= 0) {
            this.titleFadeIn = var3;
         }

         if (var4 >= 0) {
            this.titleDisplayTime = var4;
         }

         if (var5 >= 0) {
            this.titleFadeOut = var5;
         }

         if (this.titlesTimer > 0) {
            this.titlesTimer = this.titleFadeIn + this.titleDisplayTime + this.titleFadeOut;
         }
      }
   }

   public GuiPlayerTabOverlay getTabList() {
      return this.overlayPlayerList;
   }

   public GuiIngame(Minecraft var1) {
      this.field_0025 = 1.0F;
      this.field_0016 = 1.0F;
      this.displayedTitle = "";
      this.displayedSubTitle = "";
      this.field_0009 = 0;
      this.field_0007 = 0;
      this.field_0003 = -3210935818864508896L & 740626631L;
      this.field_0006 = 134955537L & 1646302464L;
      this.mc = var1;
      this.itemRenderer = var1.getRenderItem();
      this.field_0002 = new GuiOverlayDebug(var1);
      this.field_0010 = new GuiSpectator(var1);
      this.field_0032 = new GuiNewChat(var1);
      this.streamIndicator = new GuiStreamIndicator(var1);
      this.overlayPlayerList = new GuiPlayerTabOverlay(var1, this);
      this.setDefaultTitlesTimes();
   }

   public void setRecordPlayingMessage(String var1) {
      this.setRecordPlaying(I18n.format("record.nowPlaying", var1), true);
   }

   public void method_28532(ScaledResolution var1) {
      AnimationsModule var2 = CheatBreaker.getInstance().getModuleManager().field_0041;
      if (this.mc.getRenderViewEntity() instanceof EntityPlayer) {
         EntityPlayer var3 = (EntityPlayer)this.mc.getRenderViewEntity();
         int var4 = MathHelper.ceiling_float_int(var3.getHealth());
         boolean var5 = this.field_0006 > this.updateCounter
            && (this.field_0006 - this.updateCounter) / (8601635L & 1278478607L) % (268716154L & 53084291L) == (3067039502724862019L & 1627923081L);
         if (var4 < this.field_0009 && var3.Z > 0) {
            this.field_0003 = Minecraft.getSystemTime();
            this.field_0006 = this.updateCounter + 20;
         } else if (var4 > this.field_0009 && var3.Z > 0) {
            this.field_0003 = Minecraft.getSystemTime();
            this.field_0006 = this.updateCounter + 10;
         }

         if (Minecraft.getSystemTime() - this.field_0003 > (8518346448894116856L & 1212158952L)) {
            this.field_0009 = var4;
            this.field_0007 = var4;
            this.field_0003 = Minecraft.getSystemTime();
         }

         this.field_0009 = var4;
         int var6 = this.field_0007;
         this.field_0000.setSeed(this.updateCounter * (-4310850364075161865L & 1343541031L));
         boolean var7 = false;
         FoodStats var8 = var3.getFoodStats();
         int var9 = var8.getFoodLevel();
         int var10 = var8.getPrevFoodLevel();
         IAttributeInstance var11 = var3.getEntityAttribute(SharedMonsterAttributes.maxHealth);
         int var12 = var1.getScaledWidth() / 2 - 91;
         int var13 = var1.getScaledWidth() / 2 + 91;
         int var14 = var1.getScaledHeight() - 39;
         float var15 = (float)var11.getAttributeValue();
         float var16 = var3.getAbsorptionAmount();
         int var17 = MathHelper.ceiling_float_int((var15 + var16) / 2.0F / 10.0F);
         int var18 = Math.max(10 - (var17 - 2), 3);
         int var19 = var14 - (var17 - 1) * var18 - 10;
         float var20 = var16;
         int var21 = var3.getTotalArmorValue();
         int var22 = -1;
         if (var3.isPotionActive(Potion.regeneration)) {
            var22 = this.updateCounter % MathHelper.ceiling_float_int(var15 + 5.0F);
         }

         this.mc.mcProfiler.startSection("armor");

         for (int var23 = 0; var23 < 10; var23++) {
            if (var21 > 0) {
               int var24 = var12 + var23 * 8;
               if (var23 * 2 + 1 < var21) {
                  this.drawTexturedModalRect(var24, var19, 34, 9, 9, 9);
               }

               if (var23 * 2 + 1 == var21) {
                  this.drawTexturedModalRect(var24, var19, 25, 9, 9, 9);
               }

               if (var23 * 2 + 1 > var21) {
                  this.drawTexturedModalRect(var24, var19, 16, 9, 9, 9);
               }
            }
         }

         this.mc.mcProfiler.endStartSection("health");

         for (int var35 = MathHelper.ceiling_float_int((var15 + var16) / 2.0F) - 1; var35 >= 0; var35--) {
            byte var37 = 16;
            if (var3.isPotionActive(Potion.poison)) {
               var37 += 36;
            } else if (var3.isPotionActive(Potion.wither)) {
               var37 += 72;
            }

            byte var25 = 0;
            if (var5) {
               var25 = 1;
            }

            int var26 = MathHelper.ceiling_float_int((var35 + 1) / 10.0F) - 1;
            int var27 = var12 + var35 % 10 * 8;
            int var28 = var14 - var26 * var18;
            if (var4 <= 4) {
               var28 += this.field_0000.nextInt(2);
            }

            if (var35 == var22) {
               var28 -= 2;
            }

            byte var29 = 0;
            if (var3.o.P().isHardcoreModeEnabled()) {
               var29 = 5;
            }

            this.drawTexturedModalRect(var27, var28, 16 + var25 * 9, 9 * var29, 9, 9);
            if (var5 && !var2.field_0017.method_08908()) {
               if (var35 * 2 + 1 < var6) {
                  this.drawTexturedModalRect(var27, var28, var37 + 54, 9 * var29, 9, 9);
               }

               if (var35 * 2 + 1 == var6) {
                  this.drawTexturedModalRect(var27, var28, var37 + 63, 9 * var29, 9, 9);
               }
            }

            if (var20 <= 0.0F) {
               if (var35 * 2 + 1 < var4) {
                  this.drawTexturedModalRect(var27, var28, var37 + 36, 9 * var29, 9, 9);
               }

               if (var35 * 2 + 1 == var4) {
                  this.drawTexturedModalRect(var27, var28, var37 + 45, 9 * var29, 9, 9);
               }
            } else {
               if (var20 == var16 && var16 % 2.0F == 1.0F) {
                  this.drawTexturedModalRect(var27, var28, var37 + 153, 9 * var29, 9, 9);
               } else {
                  this.drawTexturedModalRect(var27, var28, var37 + 144, 9 * var29, 9, 9);
               }

               var20 -= 2.0F;
            }
         }

         Entity var36 = var3.m;
         if (var36 == null) {
            this.mc.mcProfiler.endStartSection("food");

            for (int var39 = 0; var39 < 10; var39++) {
               int var42 = var14;
               byte var45 = 16;
               byte var48 = 0;
               if (var3.isPotionActive(Potion.hunger)) {
                  var45 += 36;
                  var48 = 13;
               }

               if (var3.getFoodStats().getSaturationLevel() <= 0.0F && this.updateCounter % (var9 * 3 + 1) == 0) {
                  var42 = var14 + (this.field_0000.nextInt(3) - 1);
               }

               if (var7) {
                  var48 = 1;
               }

               int var51 = var13 - var39 * 8 - 9;
               this.drawTexturedModalRect(var51, var42, 16 + var48 * 9, 27, 9, 9);
               if (var7) {
                  if (var39 * 2 + 1 < var10) {
                     this.drawTexturedModalRect(var51, var42, var45 + 54, 27, 9, 9);
                  }

                  if (var39 * 2 + 1 == var10) {
                     this.drawTexturedModalRect(var51, var42, var45 + 63, 27, 9, 9);
                  }
               }

               if (var39 * 2 + 1 < var9) {
                  this.drawTexturedModalRect(var51, var42, var45 + 36, 27, 9, 9);
               }

               if (var39 * 2 + 1 == var9) {
                  this.drawTexturedModalRect(var51, var42, var45 + 45, 27, 9, 9);
               }
            }
         } else if (var36 instanceof EntityLivingBase) {
            this.mc.mcProfiler.endStartSection("mountHealth");
            EntityLivingBase var38 = (EntityLivingBase)var36;
            int var41 = (int)Math.ceil(var38.getHealth());
            float var44 = var38.getMaxHealth();
            int var47 = (int)(var44 + 0.5F) / 2;
            if (var47 > 30) {
               var47 = 30;
            }

            int var50 = var14;

            for (byte var52 = 0; var47 > 0; var52 += 20) {
               int var30 = Math.min(var47, 10);
               var47 -= var30;

               for (int var31 = 0; var31 < var30; var31++) {
                  byte var32 = 52;
                  byte var33 = 0;
                  if (var7) {
                     var33 = 1;
                  }

                  int var34 = var13 - var31 * 8 - 9;
                  this.drawTexturedModalRect(var34, var50, var32 + var33 * 9, 9, 9, 9);
                  if (var31 * 2 + 1 + var52 < var41) {
                     this.drawTexturedModalRect(var34, var50, var32 + 36, 9, 9, 9);
                  }

                  if (var31 * 2 + 1 + var52 == var41) {
                     this.drawTexturedModalRect(var34, var50, var32 + 45, 9, 9, 9);
                  }
               }

               var50 -= 10;
            }
         }

         this.mc.mcProfiler.endStartSection("air");
         if (var3.a(Material.water)) {
            int var40 = this.mc.thePlayer.getAir();
            int var43 = MathHelper.ceiling_double_int((var40 - 2) * 10.0 / 300.0);
            int var46 = MathHelper.ceiling_double_int(var40 * 10.0 / 300.0) - var43;

            for (int var49 = 0; var49 < var43 + var46; var49++) {
               if (var49 < var43) {
                  this.drawTexturedModalRect(var13 - var49 * 8 - 9, var19, 16, 18, 9, 9);
               } else {
                  this.drawTexturedModalRect(var13 - var49 * 8 - 9, var19, 25, 18, 9, 9);
               }
            }
         }

         this.mc.mcProfiler.endSection();
      }
   }

   public void setRecordPlaying(IChatComponent var1, boolean var2) {
      this.setRecordPlaying(var1.getUnformattedText(), var2);
   }

   public FontRenderer getFontRenderer() {
      return this.mc.fontRendererObj;
   }

   public void method_28518(String var1) {
      this.displayedSubTitle = var1;
   }

   public void method_28516(float var1, ScaledResolution var2) {
      TextureOptionsModule var3 = CheatBreaker.getInstance().getModuleManager().field_0047;
      float var4 = (Float)var3.field_0021.getValue() / 100.0F;
      if (var3.field_0023.getValue().equals("Static") && var3.isEnabled()) {
         var1 = var4;
      } else {
         var1 = 1.0F - var1;
      }

      if (!Config.method_03876()) {
         GlStateManager.enableDepth();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      } else {
         var1 = MathHelper.clamp_float(var1, 0.0F, 1.0F);
         WorldBorder var5 = this.mc.theWorld.af();
         float var6 = (float)var5.getClosestDistance(this.mc.thePlayer);
         double var7 = Math.min(var5.method_20269() * var5.getWarningTime() * 1000.0, Math.abs(var5.getTargetSize() - var5.getDiameter()));
         double var9 = Math.max((double)var5.getWarningDistance(), var7);
         if (var6 < var9) {
            var6 = 1.0F - (float)(var6 / var9);
         } else {
            var6 = 0.0F;
         }

         float var11 = 0.0F;
         float var12 = 1.0F;
         if (var3.field_0023.getValue().equals("Amplified") && var3.isEnabled()) {
            var11 = var3.field_0006.method_08905() / 100.0F;
            var12 = var3.field_0018.method_08905() / 100.0F;
            if (var11 > var12) {
               var3.field_0018.setValue(var3.field_0006.method_08905());
            }

            var1 *= var3.field_0020.method_08905();
         }

         if (var1 < var11) {
            var1 = var11;
         }

         if (var1 > var12) {
            var1 = var12;
         }

         this.field_0025 = (float)(this.field_0025 + (var1 - this.field_0025) * 0.01);
         GlStateManager.disableDepth();
         GlStateManager.depthMask(false);
         GlStateManager.tryBlendFuncSeparate(0, 769, 1, 0);
         if (var6 > 0.0F) {
            GlStateManager.color(0.0F, var6, var6, 1.0F);
         } else {
            GlStateManager.color(this.field_0025, this.field_0025, this.field_0025, 1.0F);
         }

         this.mc.getTextureManager().bindTexture(field_0008);
         Tessellator var13 = Tessellator.getInstance();
         WorldRenderer var14 = var13.getWorldRenderer();
         var14.begin(7, DefaultVertexFormats.POSITION_TEX);
         var14.pos(0.0, var2.getScaledHeight(), -90.0).tex(0.0, 1.0).endVertex();
         var14.pos(var2.getScaledWidth(), var2.getScaledHeight(), -90.0).tex(1.0, 1.0).endVertex();
         var14.pos(var2.getScaledWidth(), 0.0, -90.0).tex(1.0, 0.0).endVertex();
         var14.pos(0.0, 0.0, -90.0).tex(0.0, 0.0).endVertex();
         var13.draw();
         GlStateManager.depthMask(true);
         GlStateManager.enableDepth();
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      }
   }

   public void renderExpBar(ScaledResolution var1, int var2) {
      this.mc.mcProfiler.startSection("expBar");
      this.mc.getTextureManager().bindTexture(Gui.icons);
      int var3 = this.mc.thePlayer.xpBarCap();
      if (var3 > 0) {
         short var4 = 182;
         int var5 = (int)(this.mc.thePlayer.bD * (var4 + 1));
         int var6 = var1.getScaledHeight() - 32 + 3;
         this.drawTexturedModalRect(var2, var6, 0, 64, var4, 5);
         if (var5 > 0) {
            this.drawTexturedModalRect(var2, var6, 0, 69, var5, 5);
         }
      }

      this.mc.mcProfiler.endSection();
      if (this.mc.thePlayer.bB > 0) {
         this.mc.mcProfiler.startSection("expLevel");
         int var9 = 8453920;
         if (Config.isCustomColors()) {
            var9 = CustomColors.getExpBarTextColor(var9);
         }

         String var10 = "" + this.mc.thePlayer.bB;
         int var11 = (var1.getScaledWidth() - this.getFontRenderer().getStringWidth(var10)) / 2;
         int var7 = var1.getScaledHeight() - 31 - 4;
         boolean var8 = false;
         this.getFontRenderer().drawString(var10, var11 + 1, var7, 0);
         this.getFontRenderer().drawString(var10, var11 - 1, var7, 0);
         this.getFontRenderer().drawString(var10, var11, var7 + 1, 0);
         this.getFontRenderer().drawString(var10, var11, var7 - 1, 0);
         this.getFontRenderer().drawString(var10, var11, var7, var9);
         this.mc.mcProfiler.endSection();
      }
   }
}
