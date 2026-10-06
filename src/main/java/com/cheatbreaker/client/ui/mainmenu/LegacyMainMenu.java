package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.util.RenderUtil;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiConfirmOpenLink;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSelectWorld;
import net.minecraft.client.gui.GuiYesNo;
import net.minecraft.client.gui.GuiYesNoCallback;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;
import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Project;
import net.minecraft.client.gui.GuiButtonLanguage;

public class LegacyMainMenu extends GuiScreen implements GuiYesNoCallback {
   public int recoveredField226;
   public int recoveredField227;
   public double recoveredField228;
   public Object recoveredField229;
   public int recoveredField230;
   public ResourceLocation recoveredField231;
   public String recoveredField232 = "missingno";
   public int recoveredField233;
   public float recoveredField234;
   public GuiButton recoveredField235;
   public String recoveredField236;
   public int recoveredField237;
   public static Logger recoveredField242 = LogManager.getLogger();
   public static Random recoveredField239 = new Random();
   public int recoveredField240;
   public DynamicTexture recoveredField241;
   public static ResourceLocation recoveredField248 = new ResourceLocation("texts/splashes.txt");
   public String recoveredField243;
   public int recoveredField244;
   public ResourceLocation recoveredField245;
   public static ResourceLocation recoveredField238 = new ResourceLocation("textures/gui/title/minecraft.png");
   public String recoveredField247;
   public static ResourceLocation[] recoveredField250 = new ResourceLocation[]{
      new ResourceLocation("textures/gui/title/background/panorama_0.png"),
      new ResourceLocation("textures/gui/title/background/panorama_1.png"),
      new ResourceLocation("textures/gui/title/background/panorama_2.png"),
      new ResourceLocation("textures/gui/title/background/panorama_3.png"),
      new ResourceLocation("textures/gui/title/background/panorama_4.png"),
      new ResourceLocation("textures/gui/title/background/panorama_5.png")
   };
   public ResourceLocation recoveredField249;
   public static String recoveredField246 = "Please click " + EnumChatFormatting.UNDERLINE + "here" + EnumChatFormatting.RESET + " for more information.";
   public float recoveredField251;

   @Override
   public void keyTyped(char var1, int var2) throws java.io.IOException {
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) throws java.io.IOException {
      try {
         super.mouseClicked(var1, var2, var3);
         if (var1 >= 7 && var1 <= 39 && var2 >= 5 && var2 <= 20) {
            MainMenuBase.method_11936();
         }

         synchronized (this.recoveredField229) {
            if (this.recoveredField243.length() > 0
               && var1 >= this.recoveredField240
               && var1 <= this.recoveredField226
               && var2 >= this.recoveredField244
               && var2 <= this.recoveredField237) {
               GuiConfirmOpenLink var5 = new GuiConfirmOpenLink(this, this.recoveredField236, 13, true);
               var5.disableSecurityWarning();
               this.j.displayGuiScreen(var5);
            }
         }
      } catch (Throwable var8) {
         throw var8;
      }
   }

   @Override
   public void confirmClicked(boolean var1, int var2) {
      if (var1 && var2 == 12) {
         ISaveFormat var6 = this.j.getSaveLoader();
         var6.flushCache();
         var6.deleteWorldDirectory("Demo_World");
         this.j.displayGuiScreen(this);
      } else if (var2 == 13) {
         if (var1) {
            try {
               Class var3 = Class.forName("java.awt.Desktop");
               Object var4 = var3.getMethod("getDesktop").invoke(null);
               var3.getMethod("browse", URI.class).invoke(var4, new URI(this.recoveredField236));
            } catch (Throwable var5) {
               recoveredField242.error("Couldn't open link", var5);
            }
         }

         this.j.displayGuiScreen(this);
      }
   }

   @Override
   public void updateScreen() {
      this.recoveredField233++;
      this.recoveredField228 += 0.06283185307179587;
      this.recoveredField251 = (float)((Math.sin(this.recoveredField228) / 2.0 + 0.5) * 180.0);
   }

   @Override
   public boolean b_() {
      return false;
   }

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      if (var1.k == 0) {
         this.j.displayGuiScreen(new GuiOptions(this, this.j.gameSettings));
      }

      if (var1.k == 5) {
         this.j.displayGuiScreen(new GuiLanguage(this, this.j.gameSettings, this.j.getLanguageManager()));
      }

      if (var1.k == 1) {
         this.j.displayGuiScreen(new GuiSelectWorld(this));
      }

      if (var1.k == 2) {
         this.j.displayGuiScreen(new GuiMultiplayer(this));
      }

      if (var1.k == 14) {
         this.method_25836();
      }

      if (var1.k == 4) {
         this.j.shutdown();
      }

      if (var1.k == 11) {
         this.j.launchIntegratedServer("Demo_World", "Demo_World", DemoWorldServer.demoWorldSettings);
      }

      WorldInfo var2;
      if (var1.k == 12 && (var2 = this.j.getSaveLoader().getWorldInfo("Demo_World")) != null) {
         GuiYesNo var3 = GuiSelectWorld.makeDeleteWorldYesNo(this, var2.getWorldName(), 12);
         this.j.displayGuiScreen(var3);
      }
   }

   @Override
   public void initGui() {
      this.recoveredField241 = new DynamicTexture(256, 256);
      this.recoveredField231 = this.j.getTextureManager().getDynamicTextureLocation("background", this.recoveredField241);
      Calendar var1 = Calendar.getInstance();
      var1.setTime(new Date());
      if (var1.get(2) + 1 == 11 && var1.get(5) == 9) {
         this.recoveredField232 = "Happy birthday, ez!";
      } else if (var1.get(2) + 1 == 6 && var1.get(5) == 1) {
         this.recoveredField232 = "Happy birthday, Notch!";
      } else if (var1.get(2) + 1 == 12 && var1.get(5) == 24) {
         this.recoveredField232 = "Merry X-mas!";
      } else if (var1.get(2) + 1 == 1 && var1.get(5) == 1) {
         this.recoveredField232 = "Happy new year!";
      } else if (var1.get(2) + 1 == 10 && var1.get(5) == 31) {
         this.recoveredField232 = "OOoooOOOoooo! Spooky!";
      }

      boolean var2 = true;
      int var3 = this.m / 4 + 48;
      if (this.j.method_20336()) {
         this.method_25839(var3, 24);
      } else {
         this.method_25840(var3, 24);
      }

      this.n.add(new GuiButton(0, this.l / 2 - 100, var3 + 48, 98, 20, I18n.format("menu.options")));
      this.n.add(new GuiButton(4, this.l / 2 + 2, var3 + 48, 98, 20, I18n.format("menu.quit")));
      this.n.add(new GuiButtonLanguage(5, this.l / 2 - 124, var3 + 48));
      Object var4 = this.recoveredField229;
      Object var5 = this.recoveredField229;
      Object var6 = this.recoveredField229;
      synchronized (var6) {
         this.recoveredField227 = this.q.getStringWidth(this.recoveredField243);
         this.recoveredField230 = this.q.getStringWidth(this.recoveredField247);
         int var8 = Math.max(this.recoveredField227, this.recoveredField230);
         this.recoveredField240 = (this.l - var8) / 2;
         this.recoveredField244 = this.n.get(0).i - 24;
         this.recoveredField226 = this.recoveredField240 + var8;
         this.recoveredField237 = this.recoveredField244 + 24;
      }
   }

   public void method_25841(int var1, int var2, float var3) {
      this.j.getFramebuffer().unbindFramebuffer();
      GL11.glViewport(0, 0, 256, 256);
      this.method_25837(var1, var2, var3);
      this.method_25838(var3);
      this.method_25838(var3);
      this.method_25838(var3);
      this.method_25838(var3);
      this.method_25838(var3);
      this.method_25838(var3);
      this.method_25838(var3);
      this.j.getFramebuffer().bindFramebuffer(true);
      GL11.glViewport(0, 0, this.j.displayWidth, this.j.displayHeight);
      Tessellator var4 = Tessellator.getInstance();
      WorldRenderer var5 = var4.getWorldRenderer();
      var5.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      float var6 = this.l > this.m ? 120.0F / this.l : 120.0F / this.m;
      float var7 = this.m * var6 / 256.0F;
      float var8 = this.l * var6 / 256.0F;
      int var9 = this.l;
      int var10 = this.m;
      var5.pos(0.0, var10, recoveredField2942).tex(0.5F - var7, 0.5F + var8).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      var5.pos(var9, var10, recoveredField2942).tex(0.5F - var7, 0.5F - var8).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      var5.pos(var9, 0.0, recoveredField2942).tex(0.5F + var7, 0.5F - var8).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      var5.pos(0.0, 0.0, recoveredField2942).tex(0.5F + var7, 0.5F + var8).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      var4.draw();
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      GL11.glDisable(3008);
      this.method_25841(var1, var2, var3);
      GL11.glEnable(3008);
      short var4 = 274;
      int var5 = this.l / 2 - var4 / 2;
      boolean var6 = true;
      this.drawGradientRect(0, 0, this.l, this.m, -2130706433, 16777215);
      this.drawGradientRect(0, 0, this.l, this.m, 0, Integer.MIN_VALUE);
      GL11.glPushMatrix();
      GL11.glTranslatef(this.l / 2 - 40, this.m / 4 - 40, 0.0F);
      byte var7 = 40;
      GL11.glTranslatef(var7, var7, var7);
      GL11.glRotatef(this.recoveredField251, 0.0F, 0.0F, 1.0F);
      GL11.glTranslatef(-var7, -var7, -var7);
      RenderUtil.drawIcon(this.recoveredField249, var7, 0.0F, 0.0F);
      GL11.glPopMatrix();
      RenderUtil.drawIcon(this.recoveredField245, 40.0F, this.l / 2 - 40, this.m / 4 - 38);
      GL11.glPushMatrix();
      GL11.glTranslatef(this.l / 2 + 90, 70.0F, 0.0F);
      GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
      float var8 = 1.8F
         - MathHelper.abs(MathHelper.sin((float)(Minecraft.getSystemTime() % 1000L) / 1000.0F * 3.998391F * 0.78571427F * 2.0F) * 0.09589041F * 1.0428572F);
      var8 = var8 * 100.0F / (this.q.getStringWidth(this.recoveredField232) + 32);
      GL11.glScalef(var8, var8, var8);
      GL11.glPopMatrix();
      String var9 = "CheatBreaker 1.8.9";
      this.drawString(this.q, var9, 2, this.m - 10, -1);
      String var10 = "Copyright Mojang AB. Do not distribute!";
      this.drawString(this.q, var10, this.l - this.q.getStringWidth(var10) - 2, this.m - 10, -1);
      if (this.recoveredField243 != null && this.recoveredField243.length() > 0) {
         a(this.recoveredField240 - 2, this.recoveredField244 - 2, this.recoveredField226 + 2, this.recoveredField237 - 1, 1428160512);
         this.drawString(this.q, this.recoveredField243, this.recoveredField240, this.recoveredField244, -1);
         this.drawString(this.q, this.recoveredField247, (this.l - this.recoveredField230) / 2, this.n.get(0).i - 12, -1);
      }

      super.drawScreen(var1, var2, var3);
   }

   public void method_25838(float var1) {
      this.j.getTextureManager().bindTexture(this.recoveredField231);
      GL11.glTexParameteri(3553, 10241, 9729);
      GL11.glTexParameteri(3553, 10240, 9729);
      GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, 256, 256);
      GL11.glEnable(3042);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      GL11.glColorMask(true, true, true, false);
      Tessellator var2 = Tessellator.getInstance();
      WorldRenderer var3 = var2.getWorldRenderer();
      var3.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      GL11.glDisable(3008);
      byte var4 = 3;

      for (int var5 = 0; var5 < var4; var5++) {
         int var6 = this.l;
         int var7 = this.m;
         float var8 = (var5 - var4 / 2) / 256.0F;
         var3.pos(var6, var7, recoveredField2942).tex(0.0F + var8, 1.0).color(1.0F, 1.0F, 1.0F, 1.0F / (var5 + 1)).endVertex();
         var3.pos(var6, 0.0, recoveredField2942).tex(1.0F + var8, 1.0).color(1.0F, 1.0F, 1.0F, 1.0F / (var5 + 1)).endVertex();
         var3.pos(0.0, 0.0, recoveredField2942).tex(1.0F + var8, 0.0).color(1.0F, 1.0F, 1.0F, 1.0F / (var5 + 1)).endVertex();
         var3.pos(0.0, var7, recoveredField2942).tex(0.0F + var8, 0.0).color(1.0F, 1.0F, 1.0F, 1.0F / (var5 + 1)).endVertex();
      }

      var2.draw();
      GL11.glEnable(3008);
      GL11.glColorMask(true, true, true, true);
   }

   public LegacyMainMenu() {
      this.recoveredField229 = new Object();
      this.recoveredField251 = 0.0F;
      this.recoveredField249 = new ResourceLocation("client/logo_outer.png");
      this.recoveredField245 = new ResourceLocation("client/logo_inner.png");
      this.recoveredField247 = recoveredField246;
      BufferedReader var1 = null;

      try {
         ArrayList var3 = new ArrayList();
         var1 = new BufferedReader(
            new InputStreamReader(Minecraft.getMinecraft().getResourceManager().getResource(recoveredField248).getInputStream(), Charsets.UTF_8)
         );

         String var2;
         while ((var2 = var1.readLine()) != null) {
            if (!(var2 = var2.trim()).isEmpty()) {
               var3.add(var2);
            }
         }

         if (!var3.isEmpty()) {
            do {
               this.recoveredField232 = (String)var3.get(recoveredField239.nextInt(var3.size()));
            } while (this.recoveredField232.hashCode() == 125780783);
         }
      } catch (IOException var12) {
      } finally {
         if (var1 != null) {
            try {
               var1.close();
            } catch (IOException var11) {
            }
         }
      }

      this.recoveredField234 = recoveredField239.nextFloat();
      this.recoveredField243 = "";
      if (!GLContext.getCapabilities().OpenGL20 && !OpenGlHelper.areShadersSupported()) {
         this.recoveredField243 = I18n.format("title.oldgl1");
         this.recoveredField247 = I18n.format("title.oldgl2");
         this.recoveredField236 = "https://help.mojang.com/customer/portal/articles/325948?ref=game";
      }
   }

   public void method_25839(int var1, int var2) {
      this.n.add(new GuiButton(11, this.l / 2 - 100, var1, I18n.format("menu.playdemo")));
      this.recoveredField235 = new GuiButton(12, this.l / 2 - 100, var1 + var2, I18n.format("menu.resetdemo"));
      this.n.add(this.recoveredField235);
      ISaveFormat var3 = this.j.getSaveLoader();
      WorldInfo var4 = var3.getWorldInfo("Demo_World");
      if (var4 == null) {
         this.recoveredField235.l = false;
      }
   }

   public void method_25840(int var1, int var2) {
      this.n.add(new GuiButton(1, this.l / 2 - 100, var1, I18n.format("menu.singleplayer")));
      this.n.add(new GuiButton(2, this.l / 2 - 100, var1 + var2, I18n.format("menu.multiplayer")));
   }

   public void method_25836() {
      RealmsBridge var1 = new RealmsBridge();
      var1.switchToRealms(this);
   }

   public void method_25837(int var1, int var2, float var3) {
      Tessellator var4 = Tessellator.getInstance();
      WorldRenderer var5 = var4.getWorldRenderer();
      GL11.glMatrixMode(5889);
      GL11.glPushMatrix();
      GL11.glLoadIdentity();
      Project.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
      GL11.glMatrixMode(5888);
      GL11.glPushMatrix();
      GL11.glLoadIdentity();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      GL11.glRotatef(180.0F, 1.0F, 0.0F, 0.0F);
      GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
      GL11.glEnable(3042);
      GL11.glDisable(3008);
      GL11.glDisable(2884);
      GL11.glDepthMask(false);
      OpenGlHelper.glBlendFunc(770, 771, 1, 0);
      byte var6 = 8;

      for (int var7 = 0; var7 < var6 * var6; var7++) {
         GL11.glPushMatrix();
         float var8 = ((float)(var7 % var6) / var6 - 0.5F) / 64.0F;
         float var9 = ((float)(var7 / var6) / var6 - 0.5F) / 64.0F;
         float var10 = 0.0F;
         GL11.glTranslatef(var8, var9, var10);
         GL11.glRotatef(MathHelper.sin((this.recoveredField233 + var3) / 400.0F) * 25.0F + 20.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-(this.recoveredField233 + var3) * 0.055882353F * 1.7894737F, 0.0F, 1.0F, 0.0F);

         for (int var11 = 0; var11 < 6; var11++) {
            GL11.glPushMatrix();
            if (var11 == 1) {
               GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
            }

            if (var11 == 2) {
               GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            }

            if (var11 == 3) {
               GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
            }

            if (var11 == 4) {
               GL11.glRotatef(90.0F, 1.0F, 0.0F, 0.0F);
            }

            if (var11 == 5) {
               GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            }

            this.j.getTextureManager().bindTexture(recoveredField250[var11]);
            var5.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            float var12 = 0.0F;
            var5.pos(-1.0, -1.0, 1.0).tex(0.0F + var12, 0.0F + var12).color(255, 255, 255, 255 / (var7 + 1)).endVertex();
            var5.pos(1.0, -1.0, 1.0).tex(1.0F - var12, 0.0F + var12).color(255, 255, 255, 255 / (var7 + 1)).endVertex();
            var5.pos(1.0, 1.0, 1.0).tex(1.0F - var12, 1.0F - var12).color(255, 255, 255, 255 / (var7 + 1)).endVertex();
            var5.pos(-1.0, 1.0, 1.0).tex(0.0F + var12, 1.0F - var12).color(255, 255, 255, 255 / (var7 + 1)).endVertex();
            var4.draw();
            GL11.glPopMatrix();
         }

         GL11.glPopMatrix();
         GL11.glColorMask(true, true, true, false);
      }

      GL11.glColorMask(true, true, true, true);
      GL11.glMatrixMode(5889);
      GL11.glPopMatrix();
      GL11.glMatrixMode(5888);
      GL11.glPopMatrix();
      GL11.glDepthMask(true);
      GL11.glEnable(2884);
      GL11.glEnable(2929);
   }
}
