package net.minecraft.client;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.util.IProgressUpdate;
import net.minecraft.util.MinecraftError;
import net.optifine.CustomLoadingScreen;
import net.optifine.CustomLoadingScreens;
import net.optifine.reflect.Reflector;

public class LoadingScreenRenderer implements IProgressUpdate {
   public boolean loadingSuccess;
   public Minecraft mc;
   public long systemTime;
   public Framebuffer framebuffer;
   public ScaledResolution scaledResolution;
   public String currentlyDisplayedText;
   public String message = "";

   public LoadingScreenRenderer(Minecraft var1) {
      this.currentlyDisplayedText = "";
      this.systemTime = Minecraft.getSystemTime();
      this.mc = var1;
      this.scaledResolution = new ScaledResolution(var1);
      this.framebuffer = new Framebuffer(var1.displayWidth, var1.displayHeight, false);
      this.framebuffer.setFramebufferFilter(9728);
   }

   @Override
   public void resetProgressAndMessage(String var1) {
      this.loadingSuccess = false;
      this.displayString(var1);
   }

   @Override
   public void setLoadingProgress(int var1) {
      if (!this.mc.running) {
         if (!this.loadingSuccess) {
            throw new MinecraftError();
         }
      } else {
         long var2 = Minecraft.getSystemTime();
         if (var2 - this.systemTime >= (2111493599676858997L & -2111493599764540058L)) {
            this.systemTime = var2;
            ScaledResolution var4 = new ScaledResolution(this.mc);
            int var5 = var4.getScaleFactor();
            int var6 = var4.getScaledWidth();
            int var7 = var4.getScaledHeight();
            if (OpenGlHelper.isFramebufferEnabled()) {
               this.framebuffer.framebufferClear();
            } else {
               GlStateManager.clear(256);
            }

            this.framebuffer.bindFramebuffer(false);
            GlStateManager.matrixMode(5889);
            GlStateManager.loadIdentity();
            GlStateManager.ortho(0.0, var4.getScaledWidth_double(), var4.getScaledHeight_double(), 0.0, 100.0, 300.0);
            GlStateManager.matrixMode(5888);
            GlStateManager.loadIdentity();
            GlStateManager.translate(0.0F, 0.0F, -200.0F);
            if (!OpenGlHelper.isFramebufferEnabled()) {
               GlStateManager.clear(16640);
            }

            boolean var8 = true;
            if (Reflector.FMLClientHandler_handleLoadingScreen.exists()) {
               Object var9 = Reflector.call(Reflector.FMLClientHandler_instance);
               if (var9 != null) {
                  var8 = !Reflector.callBoolean(var9, Reflector.FMLClientHandler_handleLoadingScreen, var4);
               }
            }

            if (var8) {
               Tessellator var17 = Tessellator.getInstance();
               WorldRenderer var10 = var17.getWorldRenderer();
               CustomLoadingScreen var11 = CustomLoadingScreens.getCustomLoadingScreen();
               if (var11 != null) {
                  var11.drawBackground(var4.getScaledWidth(), var4.getScaledHeight());
               } else {
                  this.mc.getTextureManager().bindTexture(Gui.b);
                  float var12 = 32.0F;
                  var10.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
                  var10.pos(0.0, var7, 0.0).tex(0.0, var7 / var12).color(64, 64, 64, 255).endVertex();
                  var10.pos(var6, var7, 0.0).tex(var6 / var12, var7 / var12).color(64, 64, 64, 255).endVertex();
                  var10.pos(var6, 0.0, 0.0).tex(var6 / var12, 0.0).color(64, 64, 64, 255).endVertex();
                  var10.pos(0.0, 0.0, 0.0).tex(0.0, 0.0).color(64, 64, 64, 255).endVertex();
                  var17.draw();
               }

               if (var1 >= 0) {
                  byte var18 = 100;
                  byte var13 = 2;
                  int var14 = var6 / 2 - var18 / 2;
                  int var15 = var7 / 2 + 16;
                  GlStateManager.disableTexture2D();
                  var10.begin(7, DefaultVertexFormats.POSITION_COLOR);
                  var10.pos(var14, var15, 0.0).color(128, 128, 128, 255).endVertex();
                  var10.pos(var14, var15 + var13, 0.0).color(128, 128, 128, 255).endVertex();
                  var10.pos(var14 + var18, var15 + var13, 0.0).color(128, 128, 128, 255).endVertex();
                  var10.pos(var14 + var18, var15, 0.0).color(128, 128, 128, 255).endVertex();
                  var10.pos(var14, var15, 0.0).color(128, 255, 128, 255).endVertex();
                  var10.pos(var14, var15 + var13, 0.0).color(128, 255, 128, 255).endVertex();
                  var10.pos(var14 + var1, var15 + var13, 0.0).color(128, 255, 128, 255).endVertex();
                  var10.pos(var14 + var1, var15, 0.0).color(128, 255, 128, 255).endVertex();
                  var17.draw();
                  GlStateManager.enableTexture2D();
               }

               GlStateManager.enableBlend();
               GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
               this.mc
                  .fontRendererObj
                  .drawStringWithShadow(
                     this.currentlyDisplayedText, (var6 - this.mc.fontRendererObj.getStringWidth(this.currentlyDisplayedText)) / 2, var7 / 2 - 4 - 16, 16777215
                  );
               this.mc
                  .fontRendererObj
                  .drawStringWithShadow(this.message, (var6 - this.mc.fontRendererObj.getStringWidth(this.message)) / 2, var7 / 2 - 4 + 8, 16777215);
            }

            this.framebuffer.unbindFramebuffer();
            if (OpenGlHelper.isFramebufferEnabled()) {
               this.framebuffer.framebufferRender(var6 * var5, var7 * var5);
            }

            this.mc.updateDisplay();

            try {
               Thread.yield();
            } catch (Exception var16) {
            }
         }
      }
   }

   public void displayString(String var1) {
      this.currentlyDisplayedText = var1;
      if (!this.mc.running) {
         if (!this.loadingSuccess) {
            throw new MinecraftError();
         }
      } else {
         GlStateManager.clear(256);
         GlStateManager.matrixMode(5889);
         GlStateManager.loadIdentity();
         if (OpenGlHelper.isFramebufferEnabled()) {
            int var2 = this.scaledResolution.getScaleFactor();
            GlStateManager.ortho(0.0, this.scaledResolution.getScaledWidth() * var2, this.scaledResolution.getScaledHeight() * var2, 0.0, 100.0, 300.0);
         } else {
            ScaledResolution var3 = new ScaledResolution(this.mc);
            GlStateManager.ortho(0.0, var3.getScaledWidth_double(), var3.getScaledHeight_double(), 0.0, 100.0, 300.0);
         }

         GlStateManager.matrixMode(5888);
         GlStateManager.loadIdentity();
         GlStateManager.translate(0.0F, 0.0F, -200.0F);
      }
   }

   @Override
   public void displaySavingString(String var1) {
      this.loadingSuccess = true;
      this.displayString(var1);
   }

   @Override
   public void setDoneWorking() {
   }

   @Override
   public void displayLoadingString(String var1) {
      if (!this.mc.running) {
         if (!this.loadingSuccess) {
            throw new MinecraftError();
         }
      } else {
         this.systemTime = 541070596L & 303106730L;
         this.message = var1;
         this.setLoadingProgress(-1);
         this.systemTime = 54280358L & 675284296L;
      }
   }
}
