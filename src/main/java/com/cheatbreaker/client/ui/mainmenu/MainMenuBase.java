package com.cheatbreaker.client.ui.mainmenu;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.AbstractGui;
import com.cheatbreaker.client.ui.fading.ColorFade;
import com.cheatbreaker.client.ui.mainmenu.element.IconButtonElement;
import com.cheatbreaker.client.ui.mainmenu.element.TextButtonElement;
import com.cheatbreaker.client.ui.overlay.OverlayGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.mojang.authlib.Agent;
import com.mojang.authlib.exceptions.AuthenticationException;
import com.mojang.authlib.yggdrasil.YggdrasilAuthenticationService;
import com.mojang.authlib.yggdrasil.YggdrasilUserAuthentication;
import java.awt.Color;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.GuiLanguage;
import net.minecraft.client.gui.GuiOptions;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Session;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Project;
import com.cheatbreaker.client.ui.mainmenu.FadingTextElement;
import com.cheatbreaker.client.ui.mainmenu.AccountEntry;
import com.cheatbreaker.client.ui.mainmenu.CreditsGui;
import com.cheatbreaker.client.ui.mainmenu.MainMenuMode;

public class MainMenuBase extends AbstractGui {
   public IconButtonElement recoveredField399;
   public String recoveredField400;
   public TextButtonElement recoveredField401;
   public FadingTextElement recoveredField402;
   public FadingTextElement recoveredField403;
   public TextButtonElement recoveredField404;
   public ColorFade recoveredField405;
   public List<AccountEntry> accountsList;
   public FadingTextElement recoveredField406;
   public IconButtonElement recoveredField407;
   public File recoveredField408;
   public AccountList recoveredField409;
   public TextButtonElement recoveredField410;
   public String recoveredField411;
   public ResourceLocation recoveredField412 = new ResourceLocation("client/logo_42.png");
   public float accountButtonWidth;
   public String recoveredField413;
   public static int recoveredField414 = 4100;
   public List<TextButtonElement> recoveredField415;
   public String recoveredField416;
   public ResourceLocation[] recoveredField417;
   public ResourceLocation recoveredField418;

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      GL11.glDisable(3008);
      this.renderSkybox(var1, var2, 1.0F);
      GL11.glEnable(3008);
      super.drawScreen(var1, var2, var3);
   }

   @Override
   public void onMouseReleased(float var1, float var2, int var3) {
   }

   public void updateAccountButtonSize() {
      this.recoveredField409
         .setElementSize(
            this.getScaledWidth() - 35.0F - this.recoveredField409.method_28067(this.accountButtonWidth),
            7.0F,
            this.recoveredField409.method_28067(this.accountButtonWidth),
            17.0F
         );
   }

   public MainMenuBase() {
      this.recoveredField399 = new IconButtonElement(new ResourceLocation("client/icons/delete-64.png"));
      this.recoveredField407 = new IconButtonElement(6.0F, new ResourceLocation("client/icons/globe-24.png"));
      this.recoveredField409 = new AccountList(
         this,
         Minecraft.getMinecraft().getSession().getUsername(),
         CheatBreaker.getInstance().method_19810(Minecraft.getMinecraft().getSession().getUsername())
      );
      this.recoveredField410 = new TextButtonElement("OPTIONS");
      this.recoveredField404 = new TextButtonElement("CHANGELOG");
      this.recoveredField401 = new TextButtonElement("COSMETICS");
      this.recoveredField415 = ImmutableList.of(this.recoveredField410, this.recoveredField401, this.recoveredField404);
      this.recoveredField416 = !CheatBreaker.getInstance().method_19784().isEmpty()
            && !CheatBreaker.getInstance().method_19784().toLowerCase().equals(CheatBreaker.getInstance().method_19798())
         ? "CheatBreaker "
            + CheatBreaker.getInstance().method_19784()
            + " ("
            + CheatBreaker.getInstance().method_19754()
            + "/"
            + CheatBreaker.getInstance().method_19798()
            + ")"
         : "CheatBreaker (" + CheatBreaker.getInstance().method_19754() + "/" + CheatBreaker.getInstance().method_19798() + ")";
      this.recoveredField413 = "Copyright Mojang AB. Do not distribute!";
      this.recoveredField411 = "Unauthorized disclosure of this build in any manner may result in disciplinary action up to and including termination of an assignment.";
      this.recoveredField406 = new FadingTextElement(this.recoveredField416);
      this.recoveredField402 = new FadingTextElement("Copyright Mojang AB. Do not distribute!");
      this.recoveredField403 = new FadingTextElement(
         "Unauthorized disclosure of this build in any manner may result in disciplinary action up to and including termination of an assignment.", 50
      );
      this.recoveredField405 = new ColorFade(251658240, -16777216);
      this.recoveredField417 = new ResourceLocation[]{
         new ResourceLocation("client/panorama/0.png"),
         new ResourceLocation("client/panorama/1.png"),
         new ResourceLocation("client/panorama/2.png"),
         new ResourceLocation("client/panorama/3.png"),
         new ResourceLocation("client/panorama/4.png"),
         new ResourceLocation("client/panorama/5.png")
      };
      this.recoveredField408 = new File(Minecraft.getMinecraft().mcDataDir + File.separator + "launcher_accounts.json");
      this.accountsList = new ArrayList<>();
      this.accountButtonWidth = CheatBreaker.getInstance().robotoRegular13px.getStringWidth(Minecraft.getMinecraft().getSession().getUsername());
      this.recoveredField400 = "";
      this.method_11935();
   }

   public void method_11935() {
      Minecraft var1 = Minecraft.getMinecraft();
      ArrayList var2 = new ArrayList();
      if (this.recoveredField408.exists()) {
         try {
            FileReader var3 = new FileReader(this.recoveredField408);
            JsonParser var4 = new JsonParser();
            JsonElement var5 = var4.parse(var3);
            Iterator var6 = var5.getAsJsonObject().entrySet().iterator();

            while (var6.hasNext()) {
               Entry var7 = (Entry)var6.next();

               for (Entry var9 : ((JsonElement)var7.getValue()).getAsJsonObject().entrySet()) {
                  HashMap var10 = new HashMap();

                  for (Entry var12 : ((JsonElement)var9.getValue()).getAsJsonObject().entrySet()) {
                     if (((String)var12.getKey()).equalsIgnoreCase("minecraftProfile")) {
                        for (Entry var14 : ((JsonElement)var12.getValue()).getAsJsonObject().entrySet()) {
                           if (((String)var14.getKey()).equals("id")) {
                              var10.put("uuid", ((JsonElement)var14.getValue()).getAsString());
                           }

                           if (((String)var14.getKey()).equals("name")) {
                              var10.put("displayName", ((JsonElement)var14.getValue()).getAsString());
                           }
                        }
                     } else if (((String)var12.getKey()).equalsIgnoreCase("username")
                        || ((String)var12.getKey()).equalsIgnoreCase("name")
                        || ((String)var12.getKey()).equalsIgnoreCase("id")
                        || ((String)var12.getKey()).equalsIgnoreCase("accessToken")) {
                        var10.put(var12.getKey(), ((JsonElement)var12.getValue()).getAsString());
                     }
                  }

                  var2.add(var10);
               }

               while (var6.hasNext()) {
                  Object var22 = var6.next();
                  Entry var23 = (Entry)var22;
                  if (var23.getKey().equals("mojangClientToken")) {
                     JsonPrimitive var24 = (JsonPrimitive)var23.getValue();
                     this.recoveredField400 = var24.getAsString();
                  }
               }
            }
         } catch (Exception var15) {
         }
      }

      this.accountsList.clear();
      this.accountButtonWidth = CheatBreaker.getInstance().robotoRegular13px.getStringWidth(Minecraft.getMinecraft().getSession().getUsername());

      for (Map var17 : (Iterable<Map>)(Iterable<?>)(var2)) {
         AccountEntry var18 = new AccountEntry(
            (String)var17.get("username"),
            this.recoveredField400,
            (String)var17.get("accessToken"),
            (String)var17.get("displayName"),
            (String)var17.get("uuid")
         );

         for (AccountEntry var21 : this.accountsList) {
            if (var21.method_09047().equals(var18.method_09047())) {
               return;
            }
         }

         this.accountsList.add(var18);
         float var20 = CheatBreaker.getInstance().robotoRegular13px.getStringWidth(var18.method_09047());
         if (var20 > this.accountButtonWidth) {
            this.accountButtonWidth = var20;
         }

         if (var1.getSession() != null && this.recoveredField400.equalsIgnoreCase(var1.getSession().getUsername())) {
            this.recoveredField409.method_28068(var18.method_09047());
            this.recoveredField409.method_28069(CheatBreaker.getInstance().method_19810(var18.method_09047()));
            this.updateAccountButtonSize();
         }
      }
   }

   public List<AccountEntry> getAccounts() {
      return this.accountsList;
   }

   public void renderSkybox(int var1, int var2, float var3) {
      this.j.getFramebuffer().unbindFramebuffer();
      GL11.glViewport(0, 0, 256, 256);
      this.method_11931(var1, var2, var3);
      this.rotateAndBlurSkybox(var3);
      this.rotateAndBlurSkybox(var3);
      this.rotateAndBlurSkybox(var3);
      this.rotateAndBlurSkybox(var3);
      this.rotateAndBlurSkybox(var3);
      this.rotateAndBlurSkybox(var3);
      this.rotateAndBlurSkybox(var3);
      this.j.getFramebuffer().bindFramebuffer(true);
      GL11.glViewport(0, 0, this.j.displayWidth, this.j.displayHeight);
      float var4 = this.l > this.m ? 120.0F / this.l : 120.0F / this.m;
      float var5 = this.m * var4 / 256.0F;
      float var6 = this.l * var4 / 256.0F;
      float var7 = this.l;
      float var8 = this.m;
      Tessellator var9 = Tessellator.getInstance();
      WorldRenderer var10 = var9.getWorldRenderer();
      var10.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      var10.pos(0.0, var8, recoveredField2942).tex(0.5F - var5, 0.5F + var6).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      var10.pos(var7, var8, recoveredField2942).tex(0.5F - var5, 0.5F - var6).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      var10.pos(var7, 0.0, recoveredField2942).tex(0.5F + var5, 0.5F - var6).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      var10.pos(0.0, 0.0, recoveredField2942).tex(0.5F + var5, 0.5F + var6).color(1.0F, 1.0F, 1.0F, 1.0F).endVertex();
      var9.draw();
   }

   @Override
   public void drawMenu(float var1, float var2) {
      this.j.ingameGUI.method_00889(0.0F, 0.0F, this.getScaledWidth(), this.getScaledHeight(), 1610612735, 805306367);
      this.j.ingameGUI.method_00889(0.0F, 0.0F, this.getScaledWidth(), 160.0F, -553648128, 0);
      boolean var3 = var1 < this.recoveredField410.getX() && var2 < 30.0F;
      Color var4 = this.recoveredField405.method_25066(var3);
      CheatBreaker.getInstance().recoveredField1558.drawString("CheatBreaker", 37.0F, 9.0F, var4.getRGB());
      GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
      RenderUtil.drawIcon(this.recoveredField412, 10.0F, 8.0F, 6.0F);
      CheatBreaker.getInstance().recoveredField1558.drawString("CheatBreaker", 36.0F, 8.0F, -1);
      this.recoveredField399.drawElement(var1, var2, true);
      this.recoveredField407.drawElement(var1, var2, true);
      this.recoveredField409.drawElement(var1, var2, true);

      for (TextButtonElement var6 : this.recoveredField415) {
         var6.drawElement(var1, var2, true);
      }

      this.recoveredField406.drawElement(var1, var2, true);
      this.recoveredField402.drawElement(var1, var2, true);
      if (CheatBreaker.getInstance().method_19806()) {
         this.recoveredField403.drawElement(var1, var2, true);
      }
   }

   @Override
   public void onMouseClicked(float var1, float var2, int var3) {
      this.recoveredField399.handleElementMouseClicked(var1, var2, var3, true);
      this.recoveredField409.handleElementMouseClicked(var1, var2, var3, true);
      if (this.recoveredField399.a_(var1, var2)) {
         this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.shutdown();
      } else if (this.recoveredField410.a_(var1, var2)) {
         this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.displayGuiScreen(new GuiOptions(this, this.j.gameSettings));
      } else if (this.recoveredField407.a_(var1, var2)) {
         this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.displayGuiScreen(new GuiLanguage(this, this.j.gameSettings, this.j.getLanguageManager()));
      } else if (this.recoveredField401.a_(var1, var2)) {
         this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.displayGuiScreen(new CosmeticsMenu());
      } else if (this.recoveredField406.a_(var1, var2)) {
         this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.displayGuiScreen(new BuildInformationMenu());
      } else if (this.recoveredField402.a_(var1, var2)) {
         this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.displayGuiScreen(new CreditsGui());
      } else if (this.recoveredField403.a_(var1, var2) && CheatBreaker.getInstance().method_19806()) {
         this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.displayGuiScreen(new BuildRestrictionsMenu());
      } else if (this.recoveredField404.a_(var1, var2)) {
         this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.j.displayGuiScreen(new ChangelogMenu());
      } else if (var1 >= 8.0F && var1 <= 26.0F && var2 >= 6.0F && var2 <= 26.0F) {
         this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         method_11936();
      } else {
         boolean var4 = var1 < this.recoveredField410.getX() && var2 < 30.0F;
         if (var4 && !(this.j.currentScreen instanceof MainMenu)) {
            this.j.getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            this.j.displayGuiScreen(new MainMenu());
         }
      }
   }

   public void rotateAndBlurSkybox(float var1) {
      this.j.getTextureManager().bindTexture(this.recoveredField418);
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
         float var6 = this.l;
         float var7 = this.m;
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

   @Override
   public void updateScreen() {
      super.updateScreen();
      recoveredField414++;
   }

   @Override
   public void handleMouseInput() throws java.io.IOException {
      super.handleMouseInput();
      if (this.recoveredField409 != null) {
         this.recoveredField409.handleElementMouse();
      }
   }

   @Override
   public void initGui() {
      super.initGui();
      DynamicTexture var1 = new DynamicTexture(256, 256);
      this.recoveredField418 = this.j.getTextureManager().getDynamicTextureLocation("background", var1);
      float var2 = 0.0F;

      for (TextButtonElement var4 : this.recoveredField415) {
         var4.setElementSize(124.0F + var2, 6.0F, CheatBreaker.getInstance().robotoBold14px.getStringWidth(var4.method_26721()) * 1.14F, 20.0F);
         var2 += CheatBreaker.getInstance().robotoBold14px.getStringWidth(var4.method_26721()) + 12.0F;
      }

      this.recoveredField399.setElementSize(this.getScaledWidth() - 30.0F, 7.0F, 23.0F, 17.0F);
      this.recoveredField407.setElementSize(this.getScaledWidth() / 2.0F - 13.0F, this.getScaledHeight() - 17.0F, 26.0F, 18.0F);
      this.recoveredField406
         .setElementSize(5.0F, this.getScaledHeight() - 14.0F, CheatBreaker.getInstance().recoveredField1557.getStringWidth(this.recoveredField416), 18.0F);
      this.recoveredField402
         .setElementSize(
            this.getScaledWidth() - CheatBreaker.getInstance().recoveredField1557.getStringWidth("Copyright Mojang AB. Do not distribute!") - 5.0F,
            this.getScaledHeight() - 14.0F,
            CheatBreaker.getInstance().recoveredField1557.getStringWidth("Copyright Mojang AB. Do not distribute!"),
            18.0F
         );
      if (CheatBreaker.getInstance().method_19806()) {
         this.recoveredField403
            .setElementSize(
               this.getScaledWidth() - CheatBreaker.getInstance().recoveredField1557.getStringWidth("Copyright Mojang AB. Do not distribute!") - 5.0F,
               this.getScaledHeight() - 30.0F,
               CheatBreaker.getInstance().recoveredField1557.getStringWidth("Copyright Mojang AB. Do not distribute!"),
               20.0F
            );
      }

      this.updateAccountButtonSize();
   }

   public static void method_11936() {
      int var0 = CheatBreaker.getInstance().getGlobalSettings().method_02705().ordinal() + 1;
      MainMenuMode var1;
      if (var0 == 3) {
         var1 = MainMenuMode.OLD;
      } else {
         var1 = MainMenuMode.values()[var0];
      }

      CheatBreaker.getInstance().getGlobalSettings().getCrosshairSettingsLabel().setValue(var1.ordinal());
      Minecraft.getMinecraft().displayGuiScreen(MainMenuMode.method_22258(var1));
   }

   public void login(String var1) {
      try {
         AccountEntry var2 = null;

         for (AccountEntry var4 : this.accountsList) {
            if (var4.method_09047().equals(var1)) {
               var2 = var4;
            }
         }

         if (var2 != null) {
            if (var2.method_09049().equalsIgnoreCase(Minecraft.getMinecraft().getSession().getPlayerID())) {
               return;
            }

            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));

            for (Session var26 : CheatBreaker.getInstance().recoveredField1591) {
               if (var26.getProfile().getId().toString().replaceAll("-", "").equalsIgnoreCase(var2.method_09049().replaceAll("-", ""))) {
                  Minecraft.getMinecraft().method_20375(var26);
                  this.recoveredField409.method_28068(var2.method_09047());
                  this.recoveredField409.method_28069(var2.method_09052());
                  this.updateAccountButtonSize();
                  return;
               }
            }

            YggdrasilAuthenticationService var25 = new YggdrasilAuthenticationService(Proxy.NO_PROXY, this.recoveredField400);
            YggdrasilUserAuthentication var27 = (YggdrasilUserAuthentication)var25.createUserAuthentication(Agent.MINECRAFT);
            HashMap var5 = new HashMap();
            var5.put("uuid", var2.method_09049());
            var5.put("displayName", var2.method_09047());
            var5.put("username", var2.method_09048());
            var5.put("accessToken", var2.method_09050());
            var27.loadFromStorage(var5);

            Session var6;
            try {
               var27.logIn();
               var6 = new Session(var27.getSelectedProfile().getName(), var27.getSelectedProfile().getId().toString(), var27.getAuthenticatedToken(), "mojang");
            } catch (AuthenticationException var21) {
               var21.printStackTrace();
               return;
            }

            File var7 = new File(Minecraft.getMinecraft().mcDataDir + File.separator + "launcher_accounts.json");
            if (var7.exists() && !var27.getAuthenticatedToken().equals(var2.method_09050())) {
               try {
                  FileReader var8 = new FileReader(var7);
                  JsonParser var9 = new JsonParser();
                  JsonElement var10 = var9.parse(var8);
                  Entry var11 = null;

                  for (Entry var13 : var10.getAsJsonObject().entrySet()) {
                     if (((String)var13.getKey()).equalsIgnoreCase("authenticationDatabase")) {
                        for (Entry var15 : ((JsonElement)var13.getValue()).getAsJsonObject().entrySet()) {
                           for (Entry var17 : ((JsonElement)var15.getValue()).getAsJsonObject().entrySet()) {
                              if (((String)var17.getKey()).equalsIgnoreCase("profiles")) {
                                 for (Entry var19 : ((JsonElement)var17.getValue()).getAsJsonObject().entrySet()) {
                                    if (((String)var19.getKey()).replaceAll("-", "").equalsIgnoreCase(var6.getPlayerID().replaceAll("-", ""))) {
                                       var11 = var15;
                                    }
                                 }
                              }
                           }
                        }
                     }
                  }

                  if (var11 != null) {
                     var2.method_09051(var27.getAuthenticatedToken());
                     ((JsonElement)var11.getValue()).getAsJsonObject().remove("accessToken");
                     ((JsonElement)var11.getValue()).getAsJsonObject().addProperty("accessToken", var27.getAuthenticatedToken());
                  }

                  Gson var28 = new GsonBuilder().setPrettyPrinting().create();

                  try {
                     DataOutputStream var29 = new DataOutputStream(new FileOutputStream(var7));
                     var29.writeBytes(var28.toJson(var10).replace("\n", "\r\n"));
                     var29.flush();
                     var29.close();
                  } catch (Exception var20) {
                     var20.printStackTrace();
                     return;
                  }
               } catch (Exception var22) {
                  var22.printStackTrace();
                  return;
               }
            }

            System.out.println("Updated accessToken and logged user in.");
            this.recoveredField409.method_28068(var2.method_09047());
            this.recoveredField409.method_28069(var2.method_09052());
            this.updateAccountButtonSize();
            CheatBreaker.getInstance().recoveredField1591.add(var6);
            Minecraft.getMinecraft().method_20375(var6);
            CheatBreaker.getInstance().getAssetsWebSocket().close();
         }
      } catch (Exception var23) {
         var23.printStackTrace();
      }
   }

   public void method_11931(int var1, int var2, float var3) {
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
         GL11.glRotatef(MathHelper.sin((recoveredField414 + var3) / 400.0F) * 25.0F + 20.0F, 1.0F, 0.0F, 0.0F);
         GL11.glRotatef(-(recoveredField414 + var3) * 0.39240506F * 0.2548387F, 0.0F, 1.0F, 0.0F);

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

            this.j.getTextureManager().bindTexture(this.recoveredField417[var11]);
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

   @Override
   public void keyTyped(char var1, int var2) throws java.io.IOException {
      if (var2 != 1 || !(Minecraft.getMinecraft().currentScreen instanceof MainMenu)) {
         if (Keyboard.isKeyDown(42) && Keyboard.getEventKey() == 15) {
            Minecraft.getMinecraft().displayGuiScreen(OverlayGui.createInstance(Minecraft.getMinecraft().currentScreen));
         }

         super.keyTyped(var1, var2);
      }
   }
}
