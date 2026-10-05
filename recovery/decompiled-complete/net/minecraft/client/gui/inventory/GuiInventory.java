package net.minecraft.client.gui.inventory;

import com.cheatbreaker.client.CheatBreaker;
import io.netty.channel.sctp.oio.OioSctpServerChannel$1;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.achievement.GuiAchievements;
import net.minecraft.client.gui.achievement.GuiStats;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.InventoryEffectRenderer;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.command.CommandBase$CoordinateArg;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.crafting.RecipesBanners$RecipeDuplicatePattern;
import org.apache.log4j.helpers.Loader;

public class GuiInventory extends InventoryEffectRenderer {
   public float oldMouseY;
   public CommandBase$CoordinateArg field_0005;
   public Loader field_0002;
   public OioSctpServerChannel$1 field_0004;
   public RecipesBanners$RecipeDuplicatePattern field_0000;
   public float oldMouseX;

   @Override
   public void updateScreen() {
      if (this.j.playerController.isInCreativeMode()) {
         this.j.displayGuiScreen(new GuiContainerCreative(this.j.thePlayer));
      }

      this.updateActivePotionEffects();
   }

   @Override
   public void drawGuiContainerForegroundLayer(int var1, int var2) {
      this.q.drawString(I18n.format("container.crafting"), 86, 16, 4210752);
   }

   @Override
   public void actionPerformed(GuiButton var1) {
      if (var1.k == 0) {
         this.j.displayGuiScreen(new GuiAchievements(this, this.j.thePlayer.getStatFileWriter()));
      }

      if (var1.k == 1) {
         this.j.displayGuiScreen(new GuiStats(this, this.j.thePlayer.getStatFileWriter()));
      }
   }

   @Override
   public void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.j.getTextureManager().bindTexture(inventoryBackground);
      int var4 = this.i;
      int var5 = this.r;
      if (!(Boolean)CheatBreaker.getInstance().getModuleManager().field_0047.field_0013.getValue()
         || !CheatBreaker.getInstance().getModuleManager().field_0047.isEnabled()) {
         this.drawTexturedModalRect(var4, var5, 0, 0, this.f, this.g);
      }

      drawEntityOnScreen(var4 + 51, var5 + 75, 30, var4 + 51 - this.oldMouseX, var5 + 75 - 50 - this.oldMouseY, this.j.thePlayer);
   }

   @Override
   public void initGui() {
      this.n.clear();
      if (this.j.playerController.isInCreativeMode()) {
         this.j.displayGuiScreen(new GuiContainerCreative(this.j.thePlayer));
      } else {
         super.initGui();
      }
   }

   public GuiInventory(EntityPlayer var1) {
      super(var1.bj);
      this.p = true;
   }

   public static void drawEntityOnScreen(int var0, int var1, int var2, float var3, float var4, EntityLivingBase var5) {
      GlStateManager.enableColorMaterial();
      GlStateManager.pushMatrix();
      GlStateManager.translate((float)var0, (float)var1, 50.0F);
      GlStateManager.scale((float)(-var2), (float)var2, (float)var2);
      GlStateManager.rotate(180.0F, 0.0F, 0.0F, 1.0F);
      float var6 = var5.aI;
      float var7 = var5.y;
      float var8 = var5.z;
      float var9 = var5.prevRotationYawHead;
      float var10 = var5.aK;
      GlStateManager.rotate(135.0F, 0.0F, 1.0F, 0.0F);
      RenderHelper.enableStandardItemLighting();
      GlStateManager.rotate(-135.0F, 0.0F, 1.0F, 0.0F);
      GlStateManager.rotate(-((float)Math.atan(var4 / 40.0F)) * 20.0F, 1.0F, 0.0F, 0.0F);
      var5.aI = (float)Math.atan(var3 / 40.0F) * 20.0F;
      var5.y = (float)Math.atan(var3 / 40.0F) * 40.0F;
      var5.z = -((float)Math.atan(var4 / 40.0F)) * 20.0F;
      var5.aK = var5.y;
      var5.prevRotationYawHead = var5.y;
      GlStateManager.translate(0.0F, 0.0F, 0.0F);
      RenderManager var11 = Minecraft.getMinecraft().getRenderManager();
      var11.setPlayerViewY(180.0F);
      var11.setRenderShadow(false);
      var11.renderEntityWithPosYaw(var5, 0.0, 0.0, 0.0, 0.0F, 1.0F);
      var11.setRenderShadow(true);
      var5.aI = var6;
      var5.y = var7;
      var5.z = var8;
      var5.prevRotationYawHead = var9;
      var5.aK = var10;
      GlStateManager.popMatrix();
      RenderHelper.disableStandardItemLighting();
      GlStateManager.disableRescaleNormal();
      GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
      GlStateManager.disableTexture2D();
      GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      super.drawScreen(var1, var2, var3);
      this.oldMouseX = var1;
      this.oldMouseY = var2;
   }
}
