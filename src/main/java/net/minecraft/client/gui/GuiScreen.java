package net.minecraft.client.gui;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.fading.ColorFade;
import com.google.common.base.Splitter;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.awt.Toolkit;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.stream.GuiTwitchUserMode;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.shader.Shader;
import net.minecraft.client.shader.ShaderGroup;
import net.minecraft.client.shader.ShaderUniform;
import net.minecraft.entity.EntityList;
import net.minecraft.event.ClickEvent;
import net.minecraft.event.HoverEvent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.nbt.NBTException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.stats.Achievement;
import net.minecraft.stats.StatBase;
import net.minecraft.stats.StatList;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.IChatComponent;
import net.minecraft.util.ScreenShotHelper;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;
import tv.twitch.chat.ChatUserInfo;

public abstract class GuiScreen extends Gui implements GuiYesNoCallback {
   public static Logger LOGGER = LogManager.getLogger();
   public Minecraft j;
   public long lastMouseEvent;
   public List<GuiButton> n = Lists.newArrayList();
   public int recoveredField2526;
   public int m;
   public boolean p;
   public URI clickedLinkURI;
   public FontRenderer q;
   public static Set<String> recoveredField2529 = Sets.newHashSet("http", "https");
   public List<GuiLabel> labelList = Lists.newArrayList();
   public GuiButton selectedButton;
   public int l;
   public int recoveredField2528;
   public static Splitter recoveredField2525 = Splitter.on('\n');
   public static ColorFade recoveredField2527 = new ColorFade(0, -553648128);
   public static ColorFade recoveredField2530 = new ColorFade(0, 1243487774);
   public RenderItem k;

   public void a(IChatComponent var1, int var2, int var3) {
      if (var1 != null && var1.getChatStyle().getChatHoverEvent() != null) {
         HoverEvent var4 = var1.getChatStyle().getChatHoverEvent();
         if (var4.getAction() == HoverEvent.Action.SHOW_ITEM) {
            ItemStack var5 = null;

            try {
               NBTTagCompound var6 = JsonToNBT.getTagFromJson(var4.getValue().getUnformattedText());
               if (var6 instanceof NBTTagCompound) {
                  var5 = ItemStack.loadItemStackFromNBT(var6);
               }
            } catch (NBTException var11) {
            }

            if (var5 != null) {
               this.renderToolTip(var5, var2, var3);
            } else {
               this.drawCreativeTabHoveringText(EnumChatFormatting.RED + "Invalid Item!", var2, var3);
            }
         } else if (var4.getAction() == HoverEvent.Action.SHOW_ENTITY) {
            if (this.j.gameSettings.advancedItemTooltips) {
               try {
                  NBTTagCompound var12 = JsonToNBT.getTagFromJson(var4.getValue().getUnformattedText());
                  if (var12 instanceof NBTTagCompound) {
                     ArrayList var14 = Lists.newArrayList();
                     NBTTagCompound var7 = var12;
                     var14.add(var7.getString("name"));
                     if (var7.hasKey("type", 8)) {
                        String var8 = var7.getString("type");
                        var14.add("Type: " + var8 + " (" + EntityList.getIDFromString(var8) + ")");
                     }

                     var14.add(var7.getString("id"));
                     this.drawHoveringText(var14, var2, var3);
                  } else {
                     this.drawCreativeTabHoveringText(EnumChatFormatting.RED + "Invalid Entity!", var2, var3);
                  }
               } catch (NBTException var10) {
                  this.drawCreativeTabHoveringText(EnumChatFormatting.RED + "Invalid Entity!", var2, var3);
               }
            }
         } else if (var4.getAction() == HoverEvent.Action.SHOW_TEXT) {
            this.drawHoveringText(recoveredField2525.splitToList(var4.getValue().getFormattedText()), var2, var3);
         } else if (var4.getAction() == HoverEvent.Action.SHOW_ACHIEVEMENT) {
            StatBase var13 = StatList.getOneShotStat(var4.getValue().getUnformattedText());
            if (var13 != null) {
               IChatComponent var15 = var13.getStatName();
               ChatComponentTranslation var16 = new ChatComponentTranslation("stats.tooltip.type." + (var13.isAchievement() ? "achievement" : "statistic"));
               var16.getChatStyle().setItalic(true);
               String var17 = var13 instanceof Achievement ? ((Achievement)var13).getDescription() : null;
               ArrayList var9 = Lists.newArrayList(var15.getFormattedText(), var16.getFormattedText());
               if (var17 != null) {
                  var9.addAll(this.q.listFormattedStringToWidth(var17, 150));
               }

               this.drawHoveringText(var9, var2, var3);
            } else {
               this.drawCreativeTabHoveringText(EnumChatFormatting.RED + "Invalid statistic/achievement!", var2, var3);
            }
         }

         GlStateManager.disableLighting();
      }
   }

   public void actionPerformed(GuiButton var1) throws java.io.IOException {
   }

   public static boolean isAltKeyDown() {
      return Keyboard.isKeyDown(56) || Keyboard.isKeyDown(184);
   }

   public void f(String var1) {
      this.sendChatMessage(var1, true);
   }

   public void method_11292() {
      if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField594.getValue()) {
         try {
            if (this.j.entityRenderer.isShaderActive()) {
               ShaderGroup var1 = Minecraft.getMinecraft().entityRenderer.getShaderGroup();

               for (Shader var3 : var1.method_20563()) {
                  ShaderUniform var4 = var3.getShaderManager().method_29622("Progress");
                  if (var4 != null) {
                     var4.set(recoveredField2527.method_21227());
                  }
               }
            }

            GL11.glEnable(2929);
         } catch (IllegalArgumentException var5) {
            Throwables.propagate(var5);
         }
      }
   }

   public void drawCreativeTabHoveringText(String var1, int var2, int var3) {
      this.drawHoveringText(Arrays.asList(var1), var2, var3);
   }

   public void drawHoveringText(List<String> var1, int var2, int var3) {
      if (!var1.isEmpty()) {
         GlStateManager.disableRescaleNormal();
         RenderHelper.disableStandardItemLighting();
         GlStateManager.disableLighting();
         GlStateManager.disableDepth();
         int var4 = 0;

         for (String var6 : var1) {
            int var7 = this.q.getStringWidth(var6);
            if (var7 > var4) {
               var4 = var7;
            }
         }

         int var13 = var2 + 12;
         int var14 = var3 - 12;
         int var15 = 8;
         if (var1.size() > 1) {
            var15 += 2 + (var1.size() - 1) * 10;
         }

         if (var13 + var4 > this.l) {
            var13 -= 28 + var4;
         }

         if (var14 + var15 + 6 > this.m) {
            var14 = this.m - var15 - 6;
         }

         recoveredField2942 = 300.0F;
         this.k.zLevel = 300.0F;
         int var8 = -267386864;
         this.drawGradientRect(var13 - 3, var14 - 4, var13 + var4 + 3, var14 - 3, var8, var8);
         this.drawGradientRect(var13 - 3, var14 + var15 + 3, var13 + var4 + 3, var14 + var15 + 4, var8, var8);
         this.drawGradientRect(var13 - 3, var14 - 3, var13 + var4 + 3, var14 + var15 + 3, var8, var8);
         this.drawGradientRect(var13 - 4, var14 - 3, var13 - 3, var14 + var15 + 3, var8, var8);
         this.drawGradientRect(var13 + var4 + 3, var14 - 3, var13 + var4 + 4, var14 + var15 + 3, var8, var8);
         int var9 = 1347420415;
         int var10 = (var9 & 16711422) >> 1 | var9 & 0xFF000000;
         this.drawGradientRect(var13 - 3, var14 - 3 + 1, var13 - 3 + 1, var14 + var15 + 3 - 1, var9, var10);
         this.drawGradientRect(var13 + var4 + 2, var14 - 3 + 1, var13 + var4 + 3, var14 + var15 + 3 - 1, var9, var10);
         this.drawGradientRect(var13 - 3, var14 - 3, var13 + var4 + 3, var14 - 3 + 1, var9, var9);
         this.drawGradientRect(var13 - 3, var14 + var15 + 2, var13 + var4 + 3, var14 + var15 + 3, var10, var10);

         for (int var11 = 0; var11 < var1.size(); var11++) {
            String var12 = (String)var1.get(var11);
            this.q.drawStringWithShadow(var12, var13, var14, -1);
            if (var11 == 0) {
               var14 += 2;
            }

            var14 += 10;
         }

         recoveredField2942 = 0.0F;
         this.k.zLevel = 0.0F;
         GlStateManager.enableLighting();
         GlStateManager.enableDepth();
         RenderHelper.enableStandardItemLighting();
         GlStateManager.enableRescaleNormal();
      }
   }

   public void initGui() {
   }

   public static void setClipboardString(String var0) {
      if (!StringUtils.isEmpty(var0)) {
         try {
            StringSelection var1 = new StringSelection(var0);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(var1, (ClipboardOwner)null);
         } catch (Exception var2) {
         }
      }
   }

   public void method_11296() {
      if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField594.getValue() && this.j.theWorld != null && this.j.thePlayer != null) {
         this.j.entityRenderer.method_29141();
      }

      if (CheatBreaker.getInstance().recoveredField1577 == null) {
         recoveredField2527.method_20200();
         recoveredField2530.method_20200();
      }
   }

   public void drawScreen(int var1, int var2, float var3) {
      for (int var4 = 0; var4 < this.n.size(); var4++) {
         this.n.get(var4).drawButton(this.j, var1, var2);
      }

      for (int var5 = 0; var5 < this.labelList.size(); var5++) {
         this.labelList.get(var5).drawLabel(this.j, var1, var2);
      }
   }

   @Override
   public void confirmClicked(boolean var1, int var2) {
      if (var2 == 31102009) {
         if (var1) {
            this.openWebLink(this.clickedLinkURI);
            System.out.println("sjndfg");
         }

         this.clickedLinkURI = null;
         this.j.displayGuiScreen(this);
      }
   }

   public void a_() {
   }

   public void handleMouseInput() throws java.io.IOException {
      int var1 = Mouse.getEventX() * this.l / this.j.displayWidth;
      int var2 = this.m - Mouse.getEventY() * this.m / this.j.displayHeight - 1;
      int var3 = Mouse.getEventButton();
      if (Mouse.getEventButtonState()) {
         if (this.j.gameSettings.touchscreen && this.recoveredField2526++ > 0) {
            return;
         }

         this.recoveredField2528 = var3;
         this.lastMouseEvent = Minecraft.getSystemTime();
         this.mouseClicked(var1, var2, this.recoveredField2528);
      } else if (var3 != -1) {
         if (this.j.gameSettings.touchscreen && --this.recoveredField2526 > 0) {
            return;
         }

         this.recoveredField2528 = -1;
         this.mouseReleased(var1, var2, var3);
      } else if (this.recoveredField2528 != -1 && this.lastMouseEvent > 0L) {
         long var4 = Minecraft.getSystemTime() - this.lastMouseEvent;
         this.mouseClickMove(var1, var2, this.recoveredField2528, var4);
      }
   }

   public boolean b_() {
      return true;
   }

   public void keyTyped(char var1, int var2) throws java.io.IOException {
      if (var2 == 1) {
         if ((Boolean)CheatBreaker.getInstance().getGlobalSettings().recoveredField594.getValue()) {
            this.j.entityRenderer.stopUseShader();
         }

         this.j.displayGuiScreen((GuiScreen)null);
         if (this.j.currentScreen == null) {
            this.j.method_20340();
         }
      }
   }

   public void renderToolTip(ItemStack var1, int var2, int var3) {
      List var4 = var1.getTooltip(this.j.thePlayer, this.j.gameSettings.advancedItemTooltips);

      for (int var5 = 0; var5 < var4.size(); var5++) {
         if (var5 == 0) {
            var4.set(var5, var1.getRarity().rarityColor + (String)var4.get(var5));
         } else {
            var4.set(var5, EnumChatFormatting.GRAY + (String)var4.get(var5));
         }
      }

      this.drawHoveringText(var4, var2, var3);
   }

   public boolean handleComponentClick(IChatComponent var1) {
      if (var1 == null) {
         return false;
      } else {
         ClickEvent var2 = var1.getChatStyle().getChatClickEvent();
         if (isShiftKeyDown()) {
            if (var1.getChatStyle().getInsertion() != null) {
               this.setText(var1.getChatStyle().getInsertion(), false);
            }
         } else if (var2 != null) {
            if (var2.getAction() == ClickEvent.Action.OPEN_URL) {
               if (!this.j.gameSettings.chatLinks) {
                  return false;
               }

               try {
                  URI var3 = new URI(var2.getValue());
                  String var4 = var3.getScheme();
                  if (var4 == null) {
                     throw new URISyntaxException(var2.getValue(), "Missing protocol");
                  }

                  if (!recoveredField2529.contains(var4.toLowerCase())) {
                     throw new URISyntaxException(var2.getValue(), "Unsupported protocol: " + var4.toLowerCase());
                  }

                  if (this.j.gameSettings.chatLinksPrompt) {
                     this.clickedLinkURI = var3;
                     this.j.displayGuiScreen(new GuiConfirmOpenLink(this, var2.getValue(), 31102009, false));
                  } else {
                     this.openWebLink(var3);
                  }
               } catch (URISyntaxException var5) {
                  LOGGER.error("Can't open url for " + var2, var5);
               }
            } else if (var2.getAction() == ClickEvent.Action.OPEN_FILE) {
               URI var6 = new File(var2.getValue()).toURI();
               this.openWebLink(var6);
            } else if (var2.getAction() == ClickEvent.Action.SUGGEST_COMMAND) {
               this.setText(var2.getValue(), true);
            } else if (var2.getAction() == ClickEvent.Action.RUN_COMMAND) {
               this.sendChatMessage(var2.getValue(), false);
            } else if (var2.getAction() == ClickEvent.Action.UPLOAD_SCREENSHOT) {
               this.method_11301(var2.getValue());
            } else if (var2.getAction() == ClickEvent.Action.COPY_SCREENSHOT) {
               ScreenShotHelper.method_12619(var2.getValue());
            } else if (var2.getAction() == ClickEvent.Action.TWITCH_USER_INFO) {
               ChatUserInfo var7 = this.j.getTwitchStream().func_152926_a(var2.getValue());
               if (var7 != null) {
                  this.j.displayGuiScreen(new GuiTwitchUserMode(this.j.getTwitchStream(), var7));
               } else {
                  LOGGER.error("Tried to handle twitch user but couldn't find them!");
               }
            } else {
               LOGGER.error("Don't know how to handle " + var2);
            }

            return true;
         }

         return false;
      }
   }

   public void method_11301(String var1) {
      File var2 = new File(this.j.mcDataDir + File.separator + "screenshots" + File.separator + var1);
      if (var2.exists()) {
         GuiIngame.recoveredField2464 = true;
         new Thread(() -> {
            try {
               BufferedImage var3 = ImageIO.read(var2);
               ByteArrayOutputStream var4 = new ByteArrayOutputStream();
               ImageIO.write(var3, "png", var4);
               URL var5 = new URL("https://api.imgur.com/3/image");
               String var6 = URLEncoder.encode("image", "UTF-8") + "=" + URLEncoder.encode(Base64.encodeBase64String(var4.toByteArray()), "UTF-8");
               var6 = var6 + "&" + URLEncoder.encode("key", "UTF-8") + "=" + URLEncoder.encode("7fd132c453b5486", "UTF-8");
               URLConnection var7 = var5.openConnection();
               var7.setDoOutput(true);
               var7.setDoInput(true);
               var7.setRequestProperty("Authorization", "Client-ID 7fd132c453b5486");
               var7.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
               OutputStreamWriter var8 = new OutputStreamWriter(var7.getOutputStream());
               var8.write(var6);
               var8.flush();
               BufferedReader var9 = new BufferedReader(new InputStreamReader(var7.getInputStream()));
               StringBuilder var10 = new StringBuilder();

               String var2x;
               while ((var2x = var9.readLine()) != null) {
                  var10.append(var2x).append(System.lineSeparator());
               }

               var9.close();
               GuiIngame.recoveredField2464 = false;
               Gson var11 = new GsonBuilder().create();
               JsonObject var12 = var11.fromJson(var10.toString(), JsonObject.class);
               String var13 = "https://i.imgur.com/" + var12.get("data").getAsJsonObject().get("id").getAsString() + ".png";
               this.clickedLinkURI = new URI(var13);
               GuiConfirmOpenLink var14 = new GuiConfirmOpenLink(this, var13, 0, false);
               var14.disableSecurityWarning();
               this.j.displayGuiScreen(var14);
            } catch (Exception var15) {
               GuiIngame.recoveredField2464 = false;
               var15.printStackTrace();
            }
         }).start();
      }
   }

   public void drawDefaultBackground() {
      if (this.j.theWorld != null) {
         this.method_11292();
         this.method_11279(this.l, this.m);
      } else {
         this.method_11287(0, this.l, this.m);
      }
   }

   public void onResize(Minecraft var1, int var2, int var3) {
      this.setWorldAndResolution(var1, var2, var3);
   }

   public void handleKeyboardInput() throws java.io.IOException {
      if (Keyboard.getEventKeyState()) {
         this.keyTyped(Keyboard.getEventCharacter(), Keyboard.getEventKey());
      }

      this.j.dispatchKeypresses();
   }

   public void c(int var1) {
      GlStateManager.disableLighting();
      GlStateManager.disableFog();
      Tessellator var2 = Tessellator.getInstance();
      WorldRenderer var3 = var2.getWorldRenderer();
      this.j.getTextureManager().bindTexture(b);
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      float var4 = 32.0F;
      var3.begin(7, DefaultVertexFormats.POSITION_TEX_COLOR);
      var3.pos(0.0, this.m, 0.0).tex(0.0, this.m / 32.0F + var1).color(64, 64, 64, 255).endVertex();
      var3.pos(this.l, this.m, 0.0).tex(this.l / 32.0F, this.m / 32.0F + var1).color(64, 64, 64, 255).endVertex();
      var3.pos(this.l, 0.0, 0.0).tex(this.l / 32.0F, var1).color(64, 64, 64, 255).endVertex();
      var3.pos(0.0, 0.0, 0.0).tex(0.0, var1).color(64, 64, 64, 255).endVertex();
      var2.draw();
   }

   public void setText(String var1, boolean var2) {
   }

   public void method_11295(float var1, float var2) {
      ShaderGroup var3 = Minecraft.getMinecraft().entityRenderer.getShaderGroup();

      try {
         if (this.j.entityRenderer.isShaderActive()) {
            for (Shader var5 : var3.method_20563()) {
               ShaderUniform var6 = var5.getShaderManager().method_29622("Progress");
               if (var6 != null) {
                  var6.set(recoveredField2527.method_21227());
               }
            }
         }
      } catch (IllegalArgumentException var7) {
         Throwables.propagate(var7);
      }

      this.method_11279(var1, var2);
   }

   public static boolean isCtrlKeyDown() {
      return Minecraft.isRunningOnMac ? Keyboard.isKeyDown(219) || Keyboard.isKeyDown(220) : Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157);
   }

   public void openWebLink(URI var1) {
      try {
         Class var2 = Class.forName("java.awt.Desktop");
         Object var3 = var2.getMethod("getDesktop").invoke(null);
         var2.getMethod("browse", URI.class).invoke(var3, var1);
      } catch (Throwable var4) {
         LOGGER.error("Couldn't open link", var4);
      }
   }

   public static String getClipboardString() {
      try {
         Transferable var0 = Toolkit.getDefaultToolkit().getSystemClipboard().getContents(null);
         if (var0 != null && var0.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            return (String)var0.getTransferData(DataFlavor.stringFlavor);
         }
      } catch (Exception var1) {
      }

      return "";
   }

   public void setWorldAndResolution(Minecraft var1, int var2, int var3) {
      net.minecraft.client.WindowsImeSupport.beginScreen(this);
      this.j = var1;
      this.k = var1.getRenderItem();
      this.q = var1.fontRendererObj;
      this.l = var2;
      this.m = var3;
      this.n.clear();
      this.initGui();
      this.method_11296();
      net.minecraft.client.WindowsImeSupport.updateGameInput(this);
   }

   public void handleInput() throws java.io.IOException {
      if (Mouse.isCreated()) {
         while (Mouse.next()) {
            this.handleMouseInput();
         }
      }

      if (Keyboard.isCreated()) {
         while (Keyboard.next()) {
            this.handleKeyboardInput();
         }
      }
   }

   public static boolean isKeyComboCtrlV(int var0) {
      return var0 == 47 && isCtrlKeyDown() && !isShiftKeyDown() && !isAltKeyDown();
   }

   public void setGuiSize(int var1, int var2) {
      this.l = var1;
      this.m = var2;
   }

   public void drawWorldBackground(int var1) {
      if (this.j.theWorld != null) {
         this.j.ingameGUI.method_00889(0.0F, 0.0F, this.l, this.m, -1072689136, -804253680);
      } else {
         this.c(var1);
      }
   }

   public void sendChatMessage(String var1, boolean var2) {
      if (var2) {
         this.j.ingameGUI.getChatGUI().addToSentMessages(var1);
      }

      this.j.thePlayer.sendChatMessage(var1);
   }

   public static boolean isShiftKeyDown() {
      return Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54);
   }

   public void mouseClickMove(int var1, int var2, int var3, long var4) {
   }

   public static boolean isKeyComboCtrlX(int var0) {
      return var0 == 45 && isCtrlKeyDown() && !isShiftKeyDown() && !isAltKeyDown();
   }

   public void updateScreen() {
   }

   public static boolean isKeyComboCtrlA(int var0) {
      return var0 == 30 && isCtrlKeyDown() && !isShiftKeyDown() && !isAltKeyDown();
   }

   public void method_11279(float var1, float var2) {
      if (CheatBreaker.getInstance().getGlobalSettings().recoveredField502.method_08874().equals("CheatBreaker")) {
         this.j.ingameGUI.method_00889(0.0F, 0.0F, var1, var2, recoveredField2527.method_25066(true).getRGB(), recoveredField2530.method_25066(true).getRGB());
      } else if (CheatBreaker.getInstance().getGlobalSettings().recoveredField502.method_08874().equals("Vanilla")) {
         this.method_11287(0, var1, var2);
      }

      if (this.j.isFullScreen() && CheatBreaker.getInstance().getGlobalSettings().recoveredField514) {
         String var3 = "1.8.9 (" + CheatBreaker.getInstance().method_19754() + "/" + CheatBreaker.getInstance().method_19798() + ")";
         CheatBreaker.getInstance().recoveredField1557.drawStringWithShadow(var3, 5.0, this.m - 14.0F, -1879048193);
      }
   }

   public void method_11287(int var1, float var2, float var3) {
      if (this.j.theWorld != null) {
         this.j.ingameGUI.method_00889(0.0F, 0.0F, var2, var3, -1072689136, -804253680);
      } else {
         this.c(var1);
      }
   }

   public void mouseClicked(int var1, int var2, int var3) throws java.io.IOException {
      if (var3 == 0) {
         for (int var4 = 0; var4 < this.n.size(); var4++) {
            GuiButton var5 = this.n.get(var4);
            if (var5.mousePressed(this.j, var1, var2)) {
               this.selectedButton = var5;
               var5.playPressSound(this.j.getSoundHandler());
               this.actionPerformed(var5);
            }
         }
      }
   }

   public static boolean isKeyComboCtrlC(int var0) {
      return var0 == 46 && isCtrlKeyDown() && !isShiftKeyDown() && !isAltKeyDown();
   }

   public void mouseReleased(int var1, int var2, int var3) {
      if (this.selectedButton != null && var3 == 0) {
         this.selectedButton.mouseReleased(var1, var2);
         this.selectedButton = null;
      }
   }
}
