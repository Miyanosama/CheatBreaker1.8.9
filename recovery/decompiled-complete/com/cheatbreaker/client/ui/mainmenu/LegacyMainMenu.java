package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.util.RenderUtil;
import io.netty.util.internal.chmv8.CountedCompleter$1;
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
import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.ItemFood;
import net.minecraft.realms.RealmsBridge;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.demo.DemoWorldServer;
import net.minecraft.world.storage.ISaveFormat;
import net.minecraft.world.storage.WorldInfo;
import org.apache.commons.io.Charsets;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditor$3;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Project;
import recovered.unidentified.UnidentifiedClass4238;

public class LegacyMainMenu extends GuiScreen implements GuiYesNoCallback {
   public CategoryNodeEditor$3 field_0013;
   public int field_0026;
   public int field_0012;
   public double field_0023;
   public Object field_0006;
   public CountedCompleter$1 field_0007;
   public int field_0027;
   public ResourceLocation field_0019;
   public String field_0008 = "missingno";
   public ItemFood field_0029;
   public int field_0005;
   public float field_0014;
   public GuiButton field_0017;
   public String field_0010;
   public int field_0021;
   public static ResourceLocation field_0025 = new ResourceLocation("textures/gui/title/minecraft.png");
   public static Random field_0003 = new Random();
   public ModelSilverfish field_0009;
   public int field_0015;
   public DynamicTexture field_0024;
   public static Logger field_0002 = LogManager.getLogger();
   public String field_0001;
   public int field_0004;
   public ResourceLocation field_0000;
   public static String field_0022 = "Please click " + EnumChatFormatting.UNDERLINE + "here" + EnumChatFormatting.RESET + " for more information.";
   public String field_0016;
   public static ResourceLocation field_0020 = new ResourceLocation("texts/splashes.txt");
   public ResourceLocation field_0011;
   public static ResourceLocation[] field_0028 = new ResourceLocation[]{
      new ResourceLocation("textures/gui/title/background/panorama_0.png"),
      new ResourceLocation("textures/gui/title/background/panorama_1.png"),
      new ResourceLocation("textures/gui/title/background/panorama_2.png"),
      new ResourceLocation("textures/gui/title/background/panorama_3.png"),
      new ResourceLocation("textures/gui/title/background/panorama_4.png"),
      new ResourceLocation("textures/gui/title/background/panorama_5.png")
   };
   public float field_0018;

   @Override
   public void keyTyped(char var1, int var2) {
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) {
      try {
         super.mouseClicked(var1, var2, var3);
         if (var1 >= 7 && var1 <= 39 && var2 >= 5 && var2 <= 20) {
            MainMenuBase.method_11936();
         }

         synchronized (this.field_0006) {
            if (this.field_0001.length() > 0 && var1 >= this.field_0015 && var1 <= this.field_0026 && var2 >= this.field_0004 && var2 <= this.field_0021) {
               GuiConfirmOpenLink var5 = new GuiConfirmOpenLink(this, this.field_0010, 13, true);
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
               var3.getMethod("browse", URI.class).invoke(var4, new URI(this.field_0010));
            } catch (Throwable var5) {
               field_0002.error("Couldn't open link", var5);
            }
         }

         this.j.displayGuiScreen(this);
      }
   }

   @Override
   public void updateScreen() {
      this.field_0005++;
      this.field_0023 += 0.06283185307179587;
      this.field_0018 = (float)((Math.sin(this.field_0023) / 2.0 + 0.5) * 180.0);
   }

   @Override
   public boolean b_() {
      return false;
   }

   @Override
   public void actionPerformed(GuiButton var1) {
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
      this.field_0024 = new DynamicTexture(256, 256);
      this.field_0019 = this.j.getTextureManager().getDynamicTextureLocation("background", this.field_0024);
      Calendar var1 = Calendar.getInstance();
      var1.setTime(new Date());
      if (var1.get(2) + 1 == 11 && var1.get(5) == 9) {
         this.field_0008 = "Happy birthday, ez!";
      } else if (var1.get(2) + 1 == 6 && var1.get(5) == 1) {
         this.field_0008 = "Happy birthday, Notch!";
      } else if (var1.get(2) + 1 == 12 && var1.get(5) == 24) {
         this.field_0008 = "Merry X-mas!";
      } else if (var1.get(2) + 1 == 1 && var1.get(5) == 1) {
         this.field_0008 = "Happy new year!";
      } else if (var1.get(2) + 1 == 10 && var1.get(5) == 31) {
         this.field_0008 = "OOoooOOOoooo! Spooky!";
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
      this.n.add(new UnidentifiedClass4238(5, this.l / 2 - 124, var3 + 48));
      Object var4 = this.field_0006;
      Object var5 = this.field_0006;
      Object var6 = this.field_0006;
      synchronized (var6) {
         this.field_0012 = this.q.getStringWidth(this.field_0001);
         this.field_0027 = this.q.getStringWidth(this.field_0016);
         int var8 = Math.max(this.field_0012, this.field_0027);
         this.field_0015 = (this.l - var8) / 2;
         this.field_0004 = this.n.get(0).i - 24;
         this.field_0026 = this.field_0015 + var8;
         this.field_0021 = this.field_0004 + 24;
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
      var5.pos(0.0, var10, field_0003).tex(0.5F - var7, 0.5F + var8).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      var5.pos(var9, var10, field_0003).tex(0.5F - var7, 0.5F - var8).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      var5.pos(var9, 0.0, field_0003).tex(0.5F + var7, 0.5F - var8).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      var5.pos(0.0, 0.0, field_0003).tex(0.5F + var7, 0.5F + var8).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
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
      GL11.glRotatef(this.field_0018, 0.0F, 0.0F, 1.0F);
      GL11.glTranslatef(-var7, -var7, -var7);
      RenderUtil.drawIcon(this.field_0011, var7, 0.0F, 0.0F);
      GL11.glPopMatrix();
      RenderUtil.drawIcon(this.field_0000, 40.0F, this.l / 2 - 40, this.m / 4 - 38);
      GL11.glPushMatrix();
      GL11.glTranslatef(this.l / 2 + 90, 70.0F, 0.0F);
      GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
      float var8 = 1.8F
         - MathHelper.abs(
            MathHelper.sin((float)(Minecraft.getSystemTime() % (1250448360L & -7315596571074884616L)) / 1000.0F * 3.998391F * 0.78571427F * 2.0F)
               * 0.09589041F
               * 1.0428572F
         );
      var8 = var8 * 100.0F / (this.q.getStringWidth(this.field_0008) + 32);
      GL11.glScalef(var8, var8, var8);
      GL11.glPopMatrix();
      String var9 = "CheatBreaker "
         + CheatBreaker.getInstance().method_19784()
         + " ("
         + CheatBreaker.getInstance().method_19754()
         + "/"
         + CheatBreaker.getInstance().method_19798()
         + ")";
      this.drawString(this.q, var9, 2, this.m - 10, -1);
      String var10 = "Copyright Mojang AB. Do not distribute!";
      this.drawString(this.q, var10, this.l - this.q.getStringWidth(var10) - 2, this.m - 10, -1);
      if (this.field_0001 != null && this.field_0001.length() > 0) {
         a(this.field_0015 - 2, this.field_0004 - 2, this.field_0026 + 2, this.field_0021 - 1, 1428160512);
         this.drawString(this.q, this.field_0001, this.field_0015, this.field_0004, -1);
         this.drawString(this.q, this.field_0016, (this.l - this.field_0027) / 2, this.n.get(0).i - 12, -1);
      }

      super.drawScreen(var1, var2, var3);
   }

   public void method_25838(float var1) {
      this.j.getTextureManager().bindTexture(this.field_0019);
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
         var3.pos(var6, var7, field_0003).tex(0.0F + var8, 1.0).color(1.0F, 1.0F, 1.0F, 1.0F / (var5 + 1)).endVertex();
         var3.pos(var6, 0.0, field_0003).tex(1.0F + var8, 1.0).color(1.0F, 1.0F, 1.0F, 1.0F / (var5 + 1)).endVertex();
         var3.pos(0.0, 0.0, field_0003).tex(1.0F + var8, 0.0).color(1.0F, 1.0F, 1.0F, 1.0F / (var5 + 1)).endVertex();
         var3.pos(0.0, var7, field_0003).tex(0.0F + var8, 0.0).color(1.0F, 1.0F, 1.0F, 1.0F / (var5 + 1)).endVertex();
      }

      var2.draw();
      GL11.glEnable(3008);
      GL11.glColorMask(true, true, true, true);
   }

   public LegacyMainMenu() {
      this.field_0006 = new Object();
      this.field_0018 = 0.0F;
      this.field_0011 = new ResourceLocation("client/logo_outer.png");
      this.field_0000 = new ResourceLocation("client/logo_inner.png");
      this.field_0016 = field_0022;
      BufferedReader var1 = null;

      try {
         ArrayList var3 = new ArrayList();
         var1 = new BufferedReader(
            new InputStreamReader(Minecraft.getMinecraft().getResourceManager().getResource(field_0020).getInputStream(), Charsets.UTF_8)
         );

         String var2;
         while ((var2 = var1.readLine()) != null) {
            if (!(var2 = var2.trim()).isEmpty()) {
               var3.add(var2);
            }
         }

         if (!var3.isEmpty()) {
            do {
               this.field_0008 = (String)var3.get(field_0003.nextInt(var3.size()));
            } while (this.field_0008.hashCode() == 125780783);
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

      this.field_0014 = field_0003.nextFloat();
      this.field_0001 = "";
      if (!GLContext.getCapabilities().OpenGL20 && !OpenGlHelper.areShadersSupported()) {
         this.field_0001 = I18n.format("title.oldgl1");
         this.field_0016 = I18n.format("title.oldgl2");
         this.field_0010 = "https://help.mojang.com/customer/portal/articles/325948?ref=game";
      }
   }

   public void method_25839(int var1, int var2) {
      this.n.add(new GuiButton(11, this.l / 2 - 100, var1, I18n.format("menu.playdemo")));
      this.field_0017 = new GuiButton(12, this.l / 2 - 100, var1 + var2, I18n.format("menu.resetdemo"));
      this.n.add(this.field_0017);
      ISaveFormat var3 = this.j.getSaveLoader();
      WorldInfo var4 = var3.getWorldInfo("Demo_World");
      if (var4 == null) {
         this.field_0017.l = false;
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
         GL11.glRotatef(MathHelper.sin((this.field_0005 + var3) / 400.0F) * 25.0F + 20.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-(this.field_0005 + var3) * 0.055882353F * 1.7894737F, 0.0F, 1.0F, 0.0F);

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

            this.j.getTextureManager().bindTexture(field_0028[var11]);
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
