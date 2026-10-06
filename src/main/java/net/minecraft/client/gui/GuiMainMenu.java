package net.minecraft.client.gui;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.mainmenu.AccountLoginButton;
import com.google.common.collect.Lists;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Map.Entry;
import java.util.concurrent.atomic.AtomicInteger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
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
import net.optifine.CustomPanorama;
import net.optifine.CustomPanoramaProperties;
import net.optifine.reflect.Reflector;
import org.apache.commons.io.Charsets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.lwjgl.util.glu.Project;
import com.cheatbreaker.client.ui.mainmenu.TextMenuButton;
import com.cheatbreaker.client.ui.mainmenu.IconMenuButton;

public class GuiMainMenu extends GuiScreen implements GuiYesNoCallback {
   public DynamicTexture viewportTexture;
   public static AtomicInteger field_175373_f = new AtomicInteger(0);
   public String recoveredField1434;
   public ResourceLocation backgroundTexture;
   public int recoveredField1435;
   public static Logger logger = LogManager.getLogger();
   public int recoveredField1436;
   public int recoveredField1437;
   public static Random RANDOM = new Random();
   public List recoveredField1438;
   public int recoveredField1439;
   public AccountLoginButton recoveredField1440;
   public static ResourceLocation splashTexts = new ResourceLocation("texts/splashes.txt");
   public static ResourceLocation minecraftTitleTextures = new ResourceLocation("textures/gui/title/minecraft.png");
   public int recoveredField1441;
   public Object recoveredField1442;
   public int recoveredField1443;
   public static ResourceLocation[] titlePanoramaPaths = new ResourceLocation[]{
      new ResourceLocation("textures/gui/title/background/panorama_0.png"),
      new ResourceLocation("textures/gui/title/background/panorama_1.png"),
      new ResourceLocation("textures/gui/title/background/panorama_2.png"),
      new ResourceLocation("textures/gui/title/background/panorama_3.png"),
      new ResourceLocation("textures/gui/title/background/panorama_4.png"),
      new ResourceLocation("textures/gui/title/background/panorama_5.png")
   };
   public String recoveredField1444;
   public boolean recoveredField1445 = true;
   public String recoveredField1446;
   public static String field_96138_a = "Please click " + EnumChatFormatting.UNDERLINE + "here" + EnumChatFormatting.RESET + " for more information.";
   public float updateCounter;
   public String recoveredField1447;
   public int panoramaTimer;
   public boolean recoveredField1448;

   public GuiMainMenu() {
      this.recoveredField1442 = new Object();
      this.recoveredField1438 = new ArrayList();
      this.recoveredField1448 = false;
      this.recoveredField1434 = field_96138_a;
      this.recoveredField1446 = "missingno";
      BufferedReader var1 = null;

      try {
         ArrayList var2 = Lists.newArrayList();
         var1 = new BufferedReader(
            new InputStreamReader(Minecraft.getMinecraft().getResourceManager().getResource(splashTexts).getInputStream(), Charsets.UTF_8)
         );

         String var3;
         while ((var3 = var1.readLine()) != null) {
            var3 = var3.trim();
            if (!var3.isEmpty()) {
               var2.add(var3);
            }
         }

         if (!var2.isEmpty()) {
            do {
               this.recoveredField1446 = (String)var2.get(RANDOM.nextInt(var2.size()));
            } while (this.recoveredField1446.hashCode() == 125780783);
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

      this.updateCounter = RANDOM.nextFloat();
      this.recoveredField1444 = "";
      if (!GLContext.getCapabilities().OpenGL20 && !OpenGlHelper.areShadersSupported()) {
         this.recoveredField1444 = I18n.format("title.oldgl1");
         this.recoveredField1434 = I18n.format("title.oldgl2");
         this.recoveredField1447 = "https://help.mojang.com/customer/portal/articles/325948?ref=game";
      }
   }

   @Override
   public void keyTyped(char var1, int var2) throws java.io.IOException {
   }

   public void method_22418(int var1, int var2, float var3) {
      this.j.getFramebuffer().unbindFramebuffer();
      GlStateManager.viewport(0, 0, 256, 256);
      this.drawPanorama(var1, var2, var3);
      this.rotateAndBlurSkybox(var3);
      int var4 = 3;
      CustomPanoramaProperties var5 = CustomPanorama.getCustomPanoramaProperties();
      if (var5 != null) {
         var4 = var5.getBlur3();
      }

      for (int var6 = 0; var6 < var4; var6++) {
         this.rotateAndBlurSkybox(var3);
         this.rotateAndBlurSkybox(var3);
      }

      this.j.getFramebuffer().bindFramebuffer(true);
      GlStateManager.viewport(0, 0, this.j.displayWidth, this.j.displayHeight);
      float var13 = this.l > this.m ? 120.0F / this.l : 120.0F / this.m;
      float var7 = this.m * var13 / 256.0F;
      float var8 = this.l * var13 / 256.0F;
      int var9 = this.l;
      int var10 = this.m;
      Tessellator var11 = Tessellator.getInstance();
      WorldRenderer var12 = var11.getWorldRenderer();
      var12.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      var12.pos(0.0, var10, recoveredField2942).tex(0.5F - var7, 0.5F + var8).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      var12.pos(var9, var10, recoveredField2942).tex(0.5F - var7, 0.5F - var8).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      var12.pos(var9, 0.0, recoveredField2942).tex(0.5F + var7, 0.5F - var8).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      var12.pos(0.0, 0.0, recoveredField2942).tex(0.5F + var7, 0.5F + var8).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      var11.draw();
   }

   @Override
   public boolean b_() {
      return false;
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
               var3.getMethod("browse", URI.class).invoke(var4, new URI(this.recoveredField1447));
            } catch (Throwable var5) {
               logger.error("Couldn't open link", var5);
            }
         }

         this.j.displayGuiScreen(this);
      }
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) throws java.io.IOException {
      super.mouseClicked(var1, var2, var3);
      synchronized (this.recoveredField1442) {
         if (this.recoveredField1444.length() > 0
            && var1 >= this.recoveredField1436
            && var1 <= this.recoveredField1439
            && var2 >= this.recoveredField1435
            && var2 <= this.recoveredField1441) {
            GuiConfirmOpenLink var5 = new GuiConfirmOpenLink(this, this.recoveredField1447, 13, true);
            var5.disableSecurityWarning();
            this.j.displayGuiScreen(var5);
         }
      }
   }

   public void rotateAndBlurSkybox(float var1) {
      this.j.getTextureManager().bindTexture(this.backgroundTexture);
      GL11.glTexParameteri(3553, 10241, 9729);
      GL11.glTexParameteri(3553, 10240, 9729);
      GL11.glCopyTexSubImage2D(3553, 0, 0, 0, 0, 0, 256, 256);
      GlStateManager.enableBlend();
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      GlStateManager.colorMask(true, true, true, false);
      Tessellator var2 = Tessellator.getInstance();
      WorldRenderer var3 = var2.getWorldRenderer();
      var3.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      GlStateManager.disableAlpha();
      byte var4 = 3;
      int var5 = 3;
      CustomPanoramaProperties var6 = CustomPanorama.getCustomPanoramaProperties();
      if (var6 != null) {
         var5 = var6.getBlur2();
      }

      for (int var7 = 0; var7 < var5; var7++) {
         float var8 = 1.0F / (var7 + 1);
         int var9 = this.l;
         int var10 = this.m;
         float var11 = (var7 - var4 / 2) / 256.0F;
         var3.pos(var9, var10, recoveredField2942).tex(0.0F + var11, 1.0).color(1.0F, 1.0F, 1.0F, var8).endVertex();
         var3.pos(var9, 0.0, recoveredField2942).tex(1.0F + var11, 1.0).color(1.0F, 1.0F, 1.0F, var8).endVertex();
         var3.pos(0.0, 0.0, recoveredField2942).tex(1.0F + var11, 0.0).color(1.0F, 1.0F, 1.0F, var8).endVertex();
         var3.pos(0.0, var10, recoveredField2942).tex(0.0F + var11, 0.0).color(1.0F, 1.0F, 1.0F, var8).endVertex();
      }

      var2.draw();
      GlStateManager.enableAlpha();
      GlStateManager.colorMask(true, true, true, true);
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

      if (var1.k == 4) {
         this.j.shutdown();
      }

      if (var1.k == 6 && Reflector.GuiModList_Constructor.exists()) {
         this.j.displayGuiScreen((GuiScreen)Reflector.newInstance(Reflector.GuiModList_Constructor, this));
      }

      if (var1.k == 11) {
         this.j.launchIntegratedServer("Demo_World", "Demo_World", DemoWorldServer.demoWorldSettings);
      }

      if (var1.k == 12) {
         ISaveFormat var2 = this.j.getSaveLoader();
         WorldInfo var3 = var2.getWorldInfo("Demo_World");
         if (var3 != null) {
            GuiYesNo var4 = GuiSelectWorld.makeDeleteWorldYesNo(this, var3.getWorldName(), 12);
            this.j.displayGuiScreen(var4);
         }
      }
   }

   public void method_22416(int var1, int var2) {
      this.n.add(new TextMenuButton(1, this.l / 2 - 65, var1 + 24, 130, 24, I18n.format("menu.singleplayer")));
      this.n.add(new TextMenuButton(2, this.l / 2 - 65, var1 + 52, 130, 24, I18n.format("menu.multiplayer")));
   }

   public void method_22417(int var1, int var2) {
      for (int var3 = 0; var3 < this.n.size(); var3++) {
         this.n.get(var3).drawButton(this.j, var1, var2);
      }

      for (int var8 = 0; var8 < this.labelList.size(); var8++) {
         this.labelList.get(var8).drawLabel(this.j, var1, var2);
      }

      if (this.recoveredField1440 != null) {
         this.recoveredField1448 = !this.recoveredField1448
            ? var1 > this.recoveredField1440.h
               && var1 < this.recoveredField1440.h + this.recoveredField1440.method_11180()
               && var2 > this.recoveredField1440.i
               && var2 < this.recoveredField1440.i + this.recoveredField1440.method_11184()
            : var1 > this.recoveredField1440.h
               && var1 < this.recoveredField1440.h + this.recoveredField1440.method_11180()
               && var2 > this.recoveredField1440.i
               && var2 < this.recoveredField1440.i + 25 * this.recoveredField1438.size();
         this.recoveredField1440.drawButton(this.j, var1, var2);
         if (this.recoveredField1448) {
            int var4 = this.recoveredField1438.size();
            int var5 = this.recoveredField1440.method_11184();

            for (int var6 = 0; var6 < var4; var6++) {
               AccountLoginButton var7 = (AccountLoginButton)this.recoveredField1438.get(var6);
               if (var7 != this.recoveredField1440) {
                  var7.drawButton(this.j, var1, var2);
               }

               var5 += var7.method_11184();
            }
         }
      }
   }

   @Override
   public void initGui() {
      this.viewportTexture = new DynamicTexture(256, 256);
      this.backgroundTexture = this.j.getTextureManager().getDynamicTextureLocation("background", this.viewportTexture);
      Calendar var4 = Calendar.getInstance();
      var4.setTime(new Date());
      this.n.add(new TextMenuButton(0, 45, 0, 50, 25, "OPTIONS", false));
      this.n.add(new TextMenuButton(5, 95, 0, 50, 25, "LANGUAGE", false));
      this.n.add(new TextMenuButton(66, 145, 0, 65, 25, "COSMETICS", false));
      this.n.add(new IconMenuButton(4, new ResourceLocation("client/icons/exit-64.png"), this.l - 65, 0, 65, 25, "EXIT", false));
      File var5 = new File(Minecraft.getMinecraft().mcDataDir + File.separator + "launcher_accounts.json");
      ArrayList var6 = new ArrayList();
      if (var5.exists()) {
         try {
            FileReader var7 = new FileReader(var5);
            JsonParser var3 = new JsonParser();
            JsonElement var2 = var3.parse(var7);
            String var1 = "";

            for (Entry var9 : var2.getAsJsonObject().entrySet()) {
               if (((String)var9.getKey()).equalsIgnoreCase("clientToken")) {
                  var1 = ((JsonElement)var9.getValue()).getAsString();
               }

               if (((String)var9.getKey()).equalsIgnoreCase("authenticationDatabase")) {
                  for (Entry var11 : ((JsonElement)var9.getValue()).getAsJsonObject().entrySet()) {
                     HashMap var12 = new HashMap();
                     var12.put("clientToken", var1);

                     for (Entry var14 : ((JsonElement)var11.getValue()).getAsJsonObject().entrySet()) {
                        if (((String)var14.getKey()).equalsIgnoreCase("profiles")) {
                           for (Entry var16 : ((JsonElement)var14.getValue()).getAsJsonObject().entrySet()) {
                              var12.put("uuid", var16.getKey());

                              for (Entry var18 : ((JsonElement)var16.getValue()).getAsJsonObject().entrySet()) {
                                 var12.put("displayName", ((JsonElement)var18.getValue()).getAsString());
                              }
                           }
                        } else if (((String)var14.getKey()).equalsIgnoreCase("username")
                           || ((String)var14.getKey()).equalsIgnoreCase("displayName")
                           || ((String)var14.getKey()).equalsIgnoreCase("uuid")
                           || ((String)var14.getKey()).equalsIgnoreCase("accessToken")) {
                           var12.put(var14.getKey(), ((JsonElement)var14.getValue()).getAsString());
                        }
                     }

                     var6.add(var12);
                  }
               }
            }
         } catch (Exception var21) {
            var21.printStackTrace();
         }
      }

      this.recoveredField1440 = null;
      this.recoveredField1438.clear();
      int var24 = 0;

      for (Map var26 : (Iterable<Map>)(Iterable<?>)(var6)) {
         String var28 = (String)var26.get("displayName");
         AccountLoginButton var29 = new AccountLoginButton(50, var26, this.l - 200, var24 * 25, 130, 25);
         this.recoveredField1438.add(var29);
         if (this.j.getSession() != null && var28.equalsIgnoreCase(this.j.getSession().getUsername())) {
            this.recoveredField1440 = var29;
            if (var24 != 0) {
               AccountLoginButton var30 = (AccountLoginButton)this.recoveredField1438.get(0);
               var30.i = var24 * 25;
               this.recoveredField1440.i = 0;
            }
         }

         var24++;
      }

      if (this.recoveredField1440 == null && !this.recoveredField1438.isEmpty()) {
         this.recoveredField1440 = (AccountLoginButton)this.recoveredField1438.get(0);
      }

      Object var23 = this.recoveredField1442;
      Object var22 = this.recoveredField1442;
      synchronized (var22) {
         this.recoveredField1437 = this.q.getStringWidth(this.recoveredField1444);
         this.recoveredField1443 = this.q.getStringWidth(this.recoveredField1434);
         int var27 = Math.max(this.recoveredField1437, this.recoveredField1443);
         this.recoveredField1436 = (this.l - var27) / 2;
         this.recoveredField1435 = this.n.get(0).i - 24;
         this.recoveredField1439 = this.recoveredField1436 + var27;
         this.recoveredField1441 = this.recoveredField1435 + 24;
      }
   }

   public void drawPanorama(int var1, int var2, float var3) {
      Tessellator var4 = Tessellator.getInstance();
      WorldRenderer var5 = var4.getWorldRenderer();
      GlStateManager.matrixMode(5889);
      GlStateManager.pushMatrix();
      GlStateManager.loadIdentity();
      Project.gluPerspective(120.0F, 1.0F, 0.05F, 10.0F);
      GlStateManager.matrixMode(5888);
      GlStateManager.pushMatrix();
      GlStateManager.loadIdentity();
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      GlStateManager.rotate(180.0F, 1.0F, 0.0F, 0.0F);
      GlStateManager.rotate(90.0F, 0.0F, 0.0F, 1.0F);
      GlStateManager.enableBlend();
      GlStateManager.disableAlpha();
      GlStateManager.disableCull();
      GlStateManager.depthMask(false);
      GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
      byte var6 = 8;
      int var7 = 64;
      CustomPanoramaProperties var8 = CustomPanorama.getCustomPanoramaProperties();
      if (var8 != null) {
         var7 = var8.getBlur1();
      }

      for (int var9 = 0; var9 < var7; var9++) {
         GlStateManager.pushMatrix();
         float var10 = ((float)(var9 % var6) / var6 - 0.5F) / 64.0F;
         float var11 = ((float)(var9 / var6) / var6 - 0.5F) / 64.0F;
         float var12 = 0.0F;
         GlStateManager.translate(var10, var11, var12);
         GlStateManager.rotate(MathHelper.sin((this.panoramaTimer + var3) / 400.0F) * 25.0F + 20.0F, 1.0F, 0.0F, 0.0F);
         GlStateManager.rotate(-(this.panoramaTimer + var3) * 0.1F, 0.0F, 1.0F, 0.0F);

         for (int var13 = 0; var13 < 6; var13++) {
            GlStateManager.pushMatrix();
            if (var13 == 1) {
               GlStateManager.rotate(90.0F, 0.0F, 1.0F, 0.0F);
            }

            if (var13 == 2) {
               GlStateManager.rotate(180.0F, 0.0F, 1.0F, 0.0F);
            }

            if (var13 == 3) {
               GlStateManager.rotate(-90.0F, 0.0F, 1.0F, 0.0F);
            }

            if (var13 == 4) {
               GlStateManager.rotate(90.0F, 1.0F, 0.0F, 0.0F);
            }

            if (var13 == 5) {
               GlStateManager.rotate(-90.0F, 1.0F, 0.0F, 0.0F);
            }

            ResourceLocation[] var14 = titlePanoramaPaths;
            if (var8 != null) {
               var14 = var8.getPanoramaLocations();
            }

            this.j.getTextureManager().bindTexture(var14[var13]);
            var5.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
            int var15 = 255 / (var9 + 1);
            float var16 = 0.0F;
            var5.pos(-1.0, -1.0, 1.0).tex(0.0, 0.0).color(255, 255, 255, var15).endVertex();
            var5.pos(1.0, -1.0, 1.0).tex(1.0, 0.0).color(255, 255, 255, var15).endVertex();
            var5.pos(1.0, 1.0, 1.0).tex(1.0, 1.0).color(255, 255, 255, var15).endVertex();
            var5.pos(-1.0, 1.0, 1.0).tex(0.0, 1.0).color(255, 255, 255, var15).endVertex();
            var4.draw();
            GlStateManager.popMatrix();
         }

         GlStateManager.popMatrix();
         GlStateManager.colorMask(true, true, true, false);
      }

      var5.setTranslation(0.0, 0.0, 0.0);
      GlStateManager.colorMask(true, true, true, true);
      GlStateManager.matrixMode(5889);
      GlStateManager.popMatrix();
      GlStateManager.matrixMode(5888);
      GlStateManager.popMatrix();
      GlStateManager.depthMask(true);
      GlStateManager.enableCull();
      GlStateManager.enableDepth();
   }

   @Override
   public void a_() {
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      GlStateManager.disableAlpha();
      this.method_22418(var1, var2, var3);
      GlStateManager.enableAlpha();
      short var4 = 274;
      int var5 = this.l / 2 - var4 / 2;
      byte var6 = 30;
      this.method_00889(0.0F, 0.0F, this.l, this.m, 1610612735, 805306367);
      drawRect(0.0F, 0.0F, this.l, 25.0F, -819846622);
      String var7 = "CheatBreaker 1.8.9";
      this.drawString(this.q, var7, 2, this.m - 10, -1);
      String var8 = "Copyright Mojang AB. Do not distribute!";
      this.drawString(this.q, var8, this.l - this.q.getStringWidth(var8) - 2, this.m - 10, -1);
      if (this.recoveredField1444 != null && this.recoveredField1444.length() > 0) {
         a(this.recoveredField1436 - 2, this.recoveredField1435 - 2, this.recoveredField1439 + 2, this.recoveredField1441 - 1, 1428160512);
         this.drawString(this.q, this.recoveredField1444, this.recoveredField1436, this.recoveredField1435, -1);
         this.drawString(this.q, this.recoveredField1434, (this.l - this.recoveredField1443) / 2, this.n.get(0).i - 12, -1);
      }

      GL11.glPushMatrix();
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      float var9 = 30.0F;
      float var10 = 15.0F;
      float var11 = 0.0F;
      float var12 = 0.0F;
      float var13 = 10.0F;
      float var14 = 5.0F;
      GL11.glEnable(3042);
      this.j.renderEngine.bindTexture(new ResourceLocation("client/icons/cb.png"));
      GL11.glBegin(7);
      GL11.glTexCoord2d(var11 / 5.0F, var12 / 5.0F);
      GL11.glVertex2d(var13, var14);
      GL11.glTexCoord2d(var11 / 5.0F, (var12 + 5.0F) / 5.0F);
      GL11.glVertex2d(var13, var14 + var10);
      GL11.glTexCoord2d((var11 + 5.0F) / 5.0F, (var12 + 5.0F) / 5.0F);
      GL11.glVertex2d(var13 + var9, var14 + var10);
      GL11.glTexCoord2d((var11 + 5.0F) / 5.0F, var12 / 5.0F);
      GL11.glVertex2d(var13 + var9, var14);
      GL11.glEnd();
      GL11.glDisable(3042);
      GL11.glPopMatrix();
   }

   @Override
   public void updateScreen() {
      this.panoramaTimer++;
   }

   public void switchToRealms() {
      RealmsBridge var1 = new RealmsBridge();
      var1.switchToRealms(this);
   }
}
