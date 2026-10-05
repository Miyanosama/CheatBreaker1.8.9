package net.minecraft.client.gui.achievement;

import com.cheatbreaker.client.CheatBreaker;
import io.netty.handler.codec.socks.SocksInitResponseDecoder$State;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemSpade;
import net.minecraft.stats.Achievement;
import net.minecraft.util.JsonUtils;
import net.minecraft.util.ResourceLocation;
import recovered.unidentified.UnidentifiedClass1818;

public class GuiAchievement extends Gui {
   public Minecraft mc;
   public int width;
   public String achievementTitle;
   public ItemSpade field_0010;
   public RenderItem renderItem;
   public static ResourceLocation achievementBg = new ResourceLocation("textures/gui/achievement/achievement_background.png");
   public Achievement theAchievement;
   public long notificationTime;
   public UnidentifiedClass1818 field_0003;
   public SocksInitResponseDecoder$State field_0013;
   public boolean permanentNotification;
   public String achievementDescription;
   public int height;
   public JsonUtils field_0004;

   public void updateAchievementWindowScale() {
      GlStateManager.viewport(0, 0, this.mc.displayWidth, this.mc.displayHeight);
      GlStateManager.matrixMode(5889);
      GlStateManager.loadIdentity();
      GlStateManager.matrixMode(5888);
      GlStateManager.loadIdentity();
      this.width = this.mc.displayWidth;
      this.height = this.mc.displayHeight;
      ScaledResolution var1 = new ScaledResolution(this.mc);
      this.width = var1.getScaledWidth();
      this.height = var1.getScaledHeight();
      GlStateManager.clear(256);
      GlStateManager.matrixMode(5889);
      GlStateManager.loadIdentity();
      GlStateManager.ortho(0.0, this.width, this.height, 0.0, 1000.0, 3000.0);
      GlStateManager.matrixMode(5888);
      GlStateManager.loadIdentity();
      GlStateManager.translate(0.0F, 0.0F, -2000.0F);
   }

   public GuiAchievement(Minecraft var1) {
      this.mc = var1;
      this.renderItem = var1.getRenderItem();
   }

   public void method_27791() {
      if (this.theAchievement != null
         && this.notificationTime != (105914761L & 547670528L)
         && Minecraft.getMinecraft().thePlayer != null
         && CheatBreaker.getInstance().getGlobalSettings().field_0048.method_08908()) {
         double var1 = (Minecraft.getSystemTime() - this.notificationTime) / 3000.0;
         if (!this.permanentNotification) {
            if (var1 < 0.0 || var1 > 1.0) {
               this.notificationTime = 8066545754280592394L & -8066545754674954223L;
               return;
            }
         } else if (var1 > 0.5) {
            var1 = 0.5;
         }

         this.updateAchievementWindowScale();
         GlStateManager.disableDepth();
         GlStateManager.depthMask(false);
         double var3 = var1 * 2.0;
         if (var3 > 1.0) {
            var3 = 2.0 - var3;
         }

         var3 *= 4.0;
         var3 = 1.0 - var3;
         if (var3 < 0.0) {
            var3 = 0.0;
         }

         var3 *= var3;
         var3 *= var3;
         int var5 = this.width - 160;
         int var6 = 0 - (int)(var3 * 36.0);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.enableTexture2D();
         this.mc.getTextureManager().bindTexture(achievementBg);
         GlStateManager.disableLighting();
         this.drawTexturedModalRect(var5, var6, 96, 202, 160, 32);
         if (this.permanentNotification) {
            this.mc.fontRendererObj.drawSplitString(this.achievementDescription, var5 + 30, var6 + 7, 120, -1);
         } else {
            this.mc.fontRendererObj.drawString(this.achievementTitle, var5 + 30, var6 + 7, -256);
            this.mc.fontRendererObj.drawString(this.achievementDescription, var5 + 30, var6 + 18, -1);
         }

         RenderHelper.enableGUIStandardItemLighting();
         GlStateManager.disableLighting();
         GlStateManager.enableRescaleNormal();
         GlStateManager.enableColorMaterial();
         GlStateManager.enableLighting();
         this.renderItem.renderItemAndEffectIntoGUI(this.theAchievement.theItemStack, var5 + 8, var6 + 8);
         GlStateManager.disableLighting();
         GlStateManager.depthMask(true);
         GlStateManager.enableDepth();
      }
   }

   public void displayUnformattedAchievement(Achievement var1) {
      this.achievementTitle = var1.getStatName().getUnformattedText();
      this.achievementDescription = var1.getDescription();
      this.notificationTime = Minecraft.getSystemTime() + (541400550L & 50334661L);
      this.theAchievement = var1;
      this.permanentNotification = true;
   }

   public void displayAchievement(Achievement var1) {
      this.achievementTitle = I18n.format("achievement.get");
      this.achievementDescription = var1.getStatName().getUnformattedText();
      this.notificationTime = Minecraft.getSystemTime();
      this.theAchievement = var1;
      this.permanentNotification = false;
   }

   public void method_27788() {
      this.theAchievement = null;
      this.notificationTime = 541205262L & 101259408L;
   }
}
