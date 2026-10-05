package net.minecraft.client.gui;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.util.server.ServerMappingLoader;
import com.cheatbreaker.client.util.server.ServerRestrictionAction;
import com.google.common.base.Charsets;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Objects;
import java.util.Map.Entry;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.ui.mainmenu.SmallAnimatedLogoElement;
import com.cheatbreaker.client.ui.mainmenu.LargeAnimatedLogoElement;

public class ServerListEntryNormal implements GuiListExtended.IGuiListEntry {
   public SmallAnimatedLogoElement recoveredField194;
   public LargeAnimatedLogoElement recoveredField195;
   public DynamicTexture field_148305_h;
   public String field_148299_g;
   public ResourceLocation recoveredField196;
   public ResourceLocation recoveredField197;
   public static Logger logger = LogManager.getLogger();
   public ServerData server;
   public ResourceLocation recoveredField198 = new ResourceLocation("client/icons/error-64.png");
   public static ThreadPoolExecutor field_148302_b = new ScheduledThreadPoolExecutor(
      5, new ThreadFactoryBuilder().setNameFormat("Server Pinger #%d").setDaemon(true).build()
   );
   public ResourceLocation recoveredField199;
   public static ResourceLocation UNKNOWN_SERVER = new ResourceLocation("textures/misc/unknown_server.png");
   public long field_148298_f;
   public boolean recoveredField200;
   public boolean recoveredField201;
   public GuiMultiplayer recoveredField202;
   public Minecraft mc;
   public static ResourceLocation SERVER_SELECTION_BUTTONS = new ResourceLocation("textures/gui/server_selection.png");

   @Override
   public void drawEntry(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8) {
      if (!this.server.recoveredField3385) {
         this.server.recoveredField3385 = true;
         this.server.pingToServer = -2L;
         this.server.serverMOTD = "";
         this.server.populationInfo = "";
         field_148302_b.submit(() -> {
            try {
               this.recoveredField202.getOldServerPinger().ping(this.server);
            } catch (UnknownHostException var2x) {
               this.server.pingToServer = -1L;
               this.server.serverMOTD = EnumChatFormatting.DARK_RED + "Can't resolve hostname";
            } catch (Exception var3x) {
               this.server.pingToServer = -1L;
               this.server.serverMOTD = EnumChatFormatting.DARK_RED + "Can't connect to server.";
            }
         });
      }

      GL11.glColor4f(0.85F, 0.85F, 0.85F, 1.0F);
      if (this.server.method_04647()) {
         float var9 = 16.0F;
         float var10 = 8.0F;
         float var11 = 0.0F;
         float var12 = 0.0F;
         float var13 = var2 - 20;
         float var14 = var3 + 14;
         GL11.glEnable(3042);
         Minecraft.getMinecraft().renderEngine.bindTexture(this.recoveredField197);
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
      }

      if (this.server.recoveredField3388) {
         this.recoveredField195.setElementSize(var2 - 19.5F, var3 + 10, 15.0F, 13.71F);
         this.recoveredField195.drawElement(0.0F, 0.0F, true);
      }

      for (Entry var25 : CheatBreaker.getInstance().getGlobalSettings().method_02677().entrySet()) {
         for (String var34 : (String[])var25.getKey()) {
            if (this.server.serverIP.endsWith(var34.toLowerCase())) {
               boolean var15 = ((String[])var25.getValue())[1].equals(String.valueOf(ServerRestrictionAction.BLOCK));
               GL11.glColor4f(0.9F, var15 ? 0.0F : 0.8F, 0.0F, 1.0F);
               this.recoveredField200 = true;
               this.recoveredField201 = var15;
               RenderUtil.drawIcon(
                  var15 ? this.recoveredField196 : this.recoveredField198,
                  5.0F,
                  var2 - (this.server.recoveredField3388 ? 40 : 17),
                  var3 + (this.server.method_04647() ? 4 : 12)
               );
            }
         }
      }

      boolean var24 = this.server.recoveredField3386 > 47;
      boolean var26 = this.server.recoveredField3386 < 47;
      boolean var28 = var24 || var26;
      this.mc
         .fontRendererObj
         .drawString(
            this.server.serverName,
            var2 + 32 + 3,
            var3 + 1,
            this.recoveredField200 ? new Color(0.9F, this.recoveredField201 ? 0.0F : 0.8F, 0.0F, 1.0F).getRGB() : 16777215
         );
      List var30 = this.mc.fontRendererObj.listFormattedStringToWidth(this.server.serverMOTD, var4 - 32 - 2);

      for (int var32 = 0; var32 < Math.min(var30.size(), 2); var32++) {
         this.mc.fontRendererObj.drawString((String)var30.get(var32), var2 + 32 + 3, var3 + 12 + this.mc.fontRendererObj.FONT_HEIGHT * var32, 8421504);
      }

      String var33 = var28 ? EnumChatFormatting.DARK_RED + this.server.recoveredField3391 : this.server.populationInfo;
      int var35 = this.mc.fontRendererObj.getStringWidth(var33);
      this.mc.fontRendererObj.drawString(var33, var2 + var4 - var35 - 15 - 2, var3 + 1, 8421504);
      byte var36 = 0;
      String var16 = null;
      int var17;
      String var18;
      if (var28) {
         var17 = 5;
         var18 = var24 ? "Client out of date!" : "Server out of date!";
         var16 = this.server.recoveredField3387;
      } else if (this.server.recoveredField3385 && this.server.pingToServer != -2L) {
         if (this.server.pingToServer < 0L) {
            var17 = 5;
         } else if (this.server.pingToServer < 150L) {
            var17 = 0;
         } else if (this.server.pingToServer < 300L) {
            var17 = 1;
         } else if (this.server.pingToServer < 600L) {
            var17 = 2;
         } else if (this.server.pingToServer < 1000L) {
            var17 = 3;
         } else {
            var17 = 4;
         }

         if (this.server.pingToServer < 0L) {
            var18 = "(no connection)";
         } else {
            var18 = this.server.pingToServer + "ms";
            var16 = this.server.recoveredField3387;
         }
      } else {
         var36 = 1;
         var17 = (int)(Minecraft.getSystemTime() / 100L + var1 * 2 & 7L);
         if (var17 > 4) {
            var17 = 8 - var17;
         }

         var18 = "Pinging...";
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.mc.getTextureManager().bindTexture(Gui.icons);
      Gui.drawModalRectWithCustomSizedTexture(var2 + var4 - 15, var3, var36 * 10, 176 + var17 * 8, 10, 8, 256.0F, 256.0F);
      if (this.server.getBase64EncodedIconData() != null && !this.server.getBase64EncodedIconData().equals(this.field_148299_g)) {
         this.field_148299_g = this.server.getBase64EncodedIconData();
         this.method_23443();
         this.recoveredField202.getServerList().saveServerList();
      }

      if (this.recoveredField194 != null) {
         this.recoveredField194.setElementSize(var2, var3, 32.0F, 29.5F);
         this.recoveredField194.drawElement(0.0F, 0.0F, true);
      } else if (this.field_148305_h != null) {
         this.drawTextureAt(var2, var3, this.recoveredField199);
      } else {
         this.drawTextureAt(var2, var3, UNKNOWN_SERVER);
      }

      int var19 = var6 - var2;
      int var20 = var7 - var3;
      if (var19 >= var4 - 15 && var19 <= var4 - 5 && var20 >= 0 && var20 <= 8) {
         this.recoveredField202.setHoveringText(var18);
      } else if (var19 >= var4 - var35 - 15 - 2 && var19 <= var4 - 15 - 2 && var20 >= 0 && var20 <= 8) {
         this.recoveredField202.setHoveringText(var16);
      }

      if (this.mc.gameSettings.touchscreen || var8) {
         this.mc.getTextureManager().bindTexture(SERVER_SELECTION_BUTTONS);
         Gui.a(var2, var3, var2 + 32, var3 + 32, -1601138544);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         int var21 = var6 - var2;
         int var22 = var7 - var3;
         if (this.func_178013_b()) {
            if (var21 < 32 && var21 > 16) {
               Gui.drawModalRectWithCustomSizedTexture(var2, var3, 0.0F, 32.0F, 32, 32, 256.0F, 256.0F);
            } else {
               Gui.drawModalRectWithCustomSizedTexture(var2, var3, 0.0F, 0.0F, 32, 32, 256.0F, 256.0F);
            }
         }

         if (this.recoveredField202.func_175392_a(this, var1)) {
            if (var21 < 16 && var22 < 16) {
               Gui.drawModalRectWithCustomSizedTexture(var2, var3, 96.0F, 32.0F, 32, 32, 256.0F, 256.0F);
            } else {
               Gui.drawModalRectWithCustomSizedTexture(var2, var3, 96.0F, 0.0F, 32, 32, 256.0F, 256.0F);
            }
         }

         if (this.recoveredField202.func_175394_b(this, var1)) {
            if (var21 < 16 && var22 > 16) {
               Gui.drawModalRectWithCustomSizedTexture(var2, var3, 64.0F, 32.0F, 32, 32, 256.0F, 256.0F);
            } else {
               Gui.drawModalRectWithCustomSizedTexture(var2, var3, 64.0F, 0.0F, 32, 32, 256.0F, 256.0F);
            }
         }
      }
   }

   @Override
   public boolean mousePressed(int var1, int var2, int var3, int var4, int var5, int var6) {
      if (var5 <= 32) {
         if (var5 < 32 && var5 > 16 && this.func_178013_b()) {
            this.recoveredField202.selectServer(var1);
            this.recoveredField202.connectToSelected();
            return true;
         }

         if (var5 < 16 && var6 < 16 && this.recoveredField202.func_175392_a(this, var1)) {
            this.recoveredField202.func_175391_a(this, var1, GuiScreen.isShiftKeyDown());
            return true;
         }

         if (var5 < 16 && var6 > 16 && this.recoveredField202.func_175394_b(this, var1)) {
            this.recoveredField202.func_175393_b(this, var1, GuiScreen.isShiftKeyDown());
            return true;
         }
      }

      this.recoveredField202.selectServer(var1);
      if (Minecraft.getSystemTime() - this.field_148298_f < 250L) {
         this.recoveredField202.connectToSelected();
      }

      this.field_148298_f = Minecraft.getSystemTime();
      return false;
   }

   public boolean func_178013_b() {
      return true;
   }

   @Override
   public void mouseReleased(int var1, int var2, int var3, int var4, int var5, int var6) {
   }

   @Override
   public void setSelected(int var1, int var2, int var3) {
   }

   public void method_23443() {
      if (this.server.getBase64EncodedIconData() == null) {
         this.mc.getTextureManager().deleteTexture(this.recoveredField199);
         this.field_148305_h = null;
      } else {
         ByteBuf var1 = Unpooled.copiedBuffer(this.server.getBase64EncodedIconData(), Charsets.UTF_8);
         ByteBuf var2 = Base64.decode(var1);

         BufferedImage var3;
         label63: {
            try {
               var3 = TextureUtil.readBufferedImage(new ByteBufInputStream(var2));
               Validate.validState(var3.getWidth() == 64, "Must be 64 pixels wide");
               Validate.validState(var3.getHeight() == 64, "Must be 64 pixels high");
               break label63;
            } catch (Throwable var8) {
               logger.error("Invalid icon for server " + this.server.serverName + " (" + this.server.serverIP + ")", var8);
               this.server.setBase64EncodedIconData((String)null);
            } finally {
               var1.release();
               var2.release();
            }

            return;
         }

         if (this.field_148305_h == null) {
            this.field_148305_h = new DynamicTexture(var3.getWidth(), var3.getHeight());
            this.mc.getTextureManager().loadTexture(this.recoveredField199, this.field_148305_h);
         }

         var3.getRGB(0, 0, var3.getWidth(), var3.getHeight(), this.field_148305_h.getTextureData(), 0, var3.getWidth());
         this.field_148305_h.updateDynamicTexture();
      }
   }

   public ServerData getServerData() {
      return this.server;
   }

   public void drawTextureAt(int var1, int var2, ResourceLocation var3) {
      this.mc.getTextureManager().bindTexture(var3);
      GlStateManager.enableBlend();
      Gui.drawModalRectWithCustomSizedTexture(var1, var2, 0.0F, 0.0F, 32, 32, 32.0F, 32.0F);
      GlStateManager.disableBlend();
   }

   public ServerListEntryNormal(GuiMultiplayer var1, ServerData var2) {
      this.recoveredField196 = new ResourceLocation("client/icons/delete-64.png");
      this.recoveredField197 = new ResourceLocation("client/icons/cb.png");
      this.recoveredField195 = new LargeAnimatedLogoElement();
      this.recoveredField202 = var1;
      this.server = var2;
      this.mc = Minecraft.getMinecraft();
      this.recoveredField199 = new ResourceLocation("servers/" + var2.serverIP + "/icon");
      this.field_148305_h = (DynamicTexture)this.mc.getTextureManager().getTexture(this.recoveredField199);

      try {
         if (Objects.equals(ServerMappingLoader.method_12436(var2.serverIP, "id"), "lunarnetwork")) {
            this.recoveredField194 = new SmallAnimatedLogoElement();
         }
      } catch (Exception var4) {
         var4.printStackTrace();
      }
   }
}
