package com.cheatbreaker.client.ui.serverlist;

import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.util.server.ServerMappingLoader;
import com.google.common.base.Charsets;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import java.awt.image.BufferedImage;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.ui.mainmenu.SmallAnimatedLogoElement;

public class PinnedServerEntry implements GuiListExtended.IGuiListEntry {
   public ResourceLocation recoveredField670;
   public long field_148298_f;
   public ResourceLocation recoveredField671;
   public static ResourceLocation recoveredField674 = new ResourceLocation("textures/gui/resource_packs.png");
   public SmallAnimatedLogoElement recoveredField673;
   public static ResourceLocation recoveredField678 = new ResourceLocation("textures/misc/unknown_pack.png");
   public Minecraft recoveredField675;
   public static Logger recoveredField676 = LogManager.getLogger();
   public ServerData server;
   public String lastIconB64;
   public ResourceLocation recoveredField677 = new ResourceLocation("client/icons/star-64.png");
   public static ThreadPoolExecutor recoveredField672 = new ScheduledThreadPoolExecutor(
      5, new ThreadFactoryBuilder().setNameFormat("Server Pinger #%d").setDaemon(true).build()
   );
   public DynamicTexture icon;
   public GuiMultiplayer recoveredField679;

   @Override
   public void drawEntry(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8) {
      if (!this.server.recoveredField3385) {
         this.server.recoveredField3385 = true;
         this.server.pingToServer = -2L;
         this.server.serverMOTD = "";
         this.server.populationInfo = "";
         recoveredField672.submit(() -> {
            try {
               this.recoveredField679.getOldServerPinger().ping(this.server);
            } catch (UnknownHostException var2x) {
               this.server.pingToServer = -1L;
               this.server.serverMOTD = EnumChatFormatting.DARK_RED + "Can't resolve hostname";
            } catch (Exception var3x) {
               this.server.pingToServer = -1L;
               this.server.serverMOTD = EnumChatFormatting.DARK_RED + "Can't connect to server.";
            }
         });
      }

      GL11.glColor4f(1.0F, 0.9F, 0.0F, 1.0F);
      RenderUtil.drawIcon(this.recoveredField677, 5.0F, var2 - 17, var3 + (this.server.method_04647() ? 4 : 12));
      GL11.glColor4f(0.85F, 0.85F, 0.85F, 1.0F);
      if (this.server.method_04647()) {
         float var9 = 16.0F;
         float var10 = 8.0F;
         float var11 = 0.0F;
         float var12 = 0.0F;
         float var13 = var2 - 20;
         float var14 = var3 + 20;
         GL11.glEnable(3042);
         Minecraft.getMinecraft().renderEngine.bindTexture(this.recoveredField671);
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

      boolean var22 = this.server.recoveredField3386 > 47;
      boolean var23 = this.server.recoveredField3386 < 47;
      boolean var24 = var22 || var23;
      this.recoveredField675.fontRendererObj.drawString(this.server.serverName, var2 + 32 + 3, var3 + 1, 16777215);
      List var25 = this.recoveredField675.fontRendererObj.listFormattedStringToWidth(this.server.serverMOTD, var4 - 32 - 2);

      for (int var26 = 0; var26 < Math.min(var25.size(), 2); var26++) {
         this.recoveredField675
            .fontRendererObj
            .drawString((String)var25.get(var26), var2 + 32 + 3, var3 + 12 + this.recoveredField675.fontRendererObj.FONT_HEIGHT * var26, 8421504);
      }

      String var27 = var24 ? EnumChatFormatting.DARK_RED + this.server.recoveredField3391 : this.server.populationInfo;
      int var28 = this.recoveredField675.fontRendererObj.getStringWidth(var27);
      this.recoveredField675.fontRendererObj.drawString(var27, var2 + var4 - var28 - 15 - 2, var3 + 1, 8421504);
      byte var15 = 0;
      String var16 = null;
      int var17;
      String var18;
      if (var24) {
         var17 = 5;
         var18 = var22 ? "Client out of date!" : "Server out of date!";
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
         var15 = 1;
         var17 = (int)(Minecraft.getSystemTime() / 100L + var1 * 2 & 7L);
         if (var17 > 4) {
            var17 = 8 - var17;
         }

         var18 = "Pinging...";
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.recoveredField675.getTextureManager().bindTexture(Gui.icons);
      Gui.drawModalRectWithCustomSizedTexture(var2 + var4 - 15, var3, var15 * 10, 176 + var17 * 8, 10, 8, 256.0F, 256.0F);
      if (this.server.getBase64EncodedIconData() != null && !this.server.getBase64EncodedIconData().equals(this.lastIconB64)) {
         this.lastIconB64 = this.server.getBase64EncodedIconData();
         this.method_00598();
         this.recoveredField679.getServerList().saveServerList();
      }

      if (this.recoveredField673 != null) {
         this.recoveredField673.setElementSize(var2, var3, 32.0F, 29.5F);
         this.recoveredField673.drawElement(0.0F, 0.0F, true);
      } else if (this.icon != null) {
         this.method_00595(var2, var3, this.recoveredField670);
      } else {
         this.method_00595(var2, var3, recoveredField678);
      }

      int var19 = var6 - var2;
      int var20 = var7 - var3;
      if (var19 >= var4 - 15 && var19 <= var4 - 5 && var20 >= 0 && var20 <= 8) {
         this.recoveredField679.setHoveringText(var18);
      } else if (var19 >= var4 - var28 - 15 - 2 && var19 <= var4 - 15 - 2 && var20 >= 0 && var20 <= 8) {
         this.recoveredField679.setHoveringText(var16);
      }

      if (this.recoveredField675.gameSettings.touchscreen || var8) {
         this.recoveredField675.getTextureManager().bindTexture(recoveredField674);
         Gui.a(var2, var3, var2 + 32, var3 + 32, -1601138544);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         int var21 = var6 - var2;
         if (this.method_00594()) {
            if (var21 < 32 && var21 > 16) {
               Gui.drawModalRectWithCustomSizedTexture(var2, var3, 0.0F, 32.0F, 32, 32, 256.0F, 256.0F);
            } else {
               Gui.drawModalRectWithCustomSizedTexture(var2, var3, 0.0F, 0.0F, 32, 32, 256.0F, 256.0F);
            }
         }
      }
   }

   @Override
   public void setSelected(int var1, int var2, int var3) {
   }

   @Override
   public void mouseReleased(int var1, int var2, int var3, int var4, int var5, int var6) {
   }

   public boolean method_00594() {
      return true;
   }

   public PinnedServerEntry(GuiMultiplayer var1, ServerData var2) {
      this.recoveredField671 = new ResourceLocation("client/icons/cb.png");
      this.recoveredField679 = var1;
      this.server = var2;
      this.recoveredField675 = Minecraft.getMinecraft();
      this.recoveredField670 = new ResourceLocation("servers/" + var2.serverIP + "/icon");
      this.icon = (DynamicTexture)this.recoveredField675.getTextureManager().getTexture(this.recoveredField670);

      try {
         if (Objects.equals(ServerMappingLoader.method_12436(var2.serverIP, "id"), "lunarnetwork")) {
            this.recoveredField673 = new SmallAnimatedLogoElement();
         }
      } catch (Exception var4) {
         var4.printStackTrace();
      }
   }

   public static ServerData method_00596(PinnedServerEntry var0) {
      return var0.server;
   }

   public void method_00595(int var1, int var2, ResourceLocation var3) {
      this.recoveredField675.getTextureManager().bindTexture(var3);
      GlStateManager.enableBlend();
      Gui.drawModalRectWithCustomSizedTexture(var1, var2, 0.0F, 0.0F, 32, 32, 32.0F, 32.0F);
      GlStateManager.disableBlend();
   }

   public void method_00598() {
      if (this.server.getBase64EncodedIconData() == null) {
         this.recoveredField675.getTextureManager().getTexture(this.recoveredField670);
         this.icon = null;
      } else {
         ByteBuf var2 = Unpooled.copiedBuffer(this.server.getBase64EncodedIconData(), Charsets.UTF_8);
         ByteBuf var3 = Base64.decode(var2);

         BufferedImage var1;
         label63: {
            try {
               var1 = ImageIO.read(new ByteBufInputStream(var3));
               Validate.validState(var1.getWidth() == 64, "Must be 64 pixels wide");
               Validate.validState(var1.getHeight() == 64, "Must be 64 pixels high");
               break label63;
            } catch (Exception var8) {
               recoveredField676.error("Invalid icon for server " + this.server.serverName + " (" + this.server.serverIP + ")", var8);
               this.server.setBase64EncodedIconData((String)null);
            } finally {
               var2.release();
               var3.release();
            }

            return;
         }

         if (this.icon == null) {
            this.icon = new DynamicTexture(var1.getWidth(), var1.getHeight());
            this.recoveredField675.getTextureManager().loadTexture(this.recoveredField670, this.icon);
         }

         var1.getRGB(0, 0, var1.getWidth(), var1.getHeight(), this.icon.getTextureData(), 0, var1.getWidth());
         this.icon.updateDynamicTexture();
      }
   }

   @Override
   public boolean mousePressed(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.recoveredField679.selectServer(var1);
      if (Minecraft.getSystemTime() - this.field_148298_f < 250L) {
         this.recoveredField679.connectToSelected();
      }

      this.field_148298_f = Minecraft.getSystemTime();
      if (var5 <= 32 && var5 < 32) {
         this.recoveredField679.connectToSelected();
         return true;
      } else {
         return false;
      }
   }

   public static GuiMultiplayer method_00599(PinnedServerEntry var0) {
      return var0.recoveredField679;
   }

   public ServerData getServer() {
      return this.server;
   }
}
