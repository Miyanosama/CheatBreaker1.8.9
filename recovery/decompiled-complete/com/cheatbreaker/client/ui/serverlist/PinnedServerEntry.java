package com.cheatbreaker.client.ui.serverlist;

import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.util.server.ServerMappingLoader;
import com.google.common.base.Charsets;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufInputStream;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.base64.Base64;
import io.netty.handler.timeout.IdleStateHandler;
import java.awt.image.BufferedImage;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import javax.imageio.ImageIO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiListExtended$IGuiListEntry;
import net.minecraft.client.gui.GuiMultiplayer;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import net.minecraft.client.stream.MetadataAchievement;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.network.NetHandlerPlayServer$3;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass0373;
import recovered.unidentified.UnidentifiedClass0557;

public class PinnedServerEntry implements GuiListExtended$IGuiListEntry {
   public ResourceLocation field_0008;
   public long field_148298_f;
   public ResourceLocation field_0007;
   public static ThreadPoolExecutor field_0014 = new ScheduledThreadPoolExecutor(
      5, new ThreadFactoryBuilder().setNameFormat("Server Pinger #%d").setDaemon(true).build()
   );
   public UnidentifiedClass0557 field_0002;
   public static ResourceLocation field_0003 = new ResourceLocation("textures/gui/resource_packs.png");
   public Minecraft field_0017;
   public UnidentifiedClass0373 field_0012;
   public IdleStateHandler field_0004;
   public static Logger field_0018 = LogManager.getLogger();
   public ServerData server;
   public String lastIconB64;
   public ResourceLocation field_0011 = new ResourceLocation("client/icons/star-64.png");
   public static ResourceLocation field_0006 = new ResourceLocation("textures/misc/unknown_pack.png");
   public NetHandlerPlayServer$3 field_0013;
   public DynamicTexture icon;
   public EntitySilverfish field_0000;
   public MetadataAchievement field_0005;
   public GuiMultiplayer field_0010;

   @Override
   public void drawEntry(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8) {
      if (!this.server.field_0007) {
         this.server.field_0007 = true;
         this.server.pingToServer = -2L & -2L;
         this.server.serverMOTD = "";
         this.server.populationInfo = "";
         field_0014.submit(() -> {
            try {
               this.field_0010.getOldServerPinger().method_08034(this.server);
            } catch (UnknownHostException var2x) {
               this.server.pingToServer = -1L & -1L;
               this.server.serverMOTD = EnumChatFormatting.DARK_RED + "Can't resolve hostname";
            } catch (Exception var3x) {
               this.server.pingToServer = -1L & -1L;
               this.server.serverMOTD = EnumChatFormatting.DARK_RED + "Can't connect to server.";
            }
         });
      }

      GL11.glColor4f(1.0F, 0.9F, 0.0F, 1.0F);
      RenderUtil.drawIcon(this.field_0011, 5.0F, var2 - 17, var3 + (this.server.method_04647() ? 4 : 12));
      GL11.glColor4f(0.85F, 0.85F, 0.85F, 1.0F);
      if (this.server.method_04647()) {
         float var9 = 16.0F;
         float var10 = 8.0F;
         float var11 = 0.0F;
         float var12 = 0.0F;
         float var13 = var2 - 20;
         float var14 = var3 + 20;
         GL11.glEnable(3042);
         Minecraft.getMinecraft().renderEngine.bindTexture(this.field_0007);
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

      boolean var22 = this.server.field_0011 > 47;
      boolean var23 = this.server.field_0011 < 47;
      boolean var24 = var22 || var23;
      this.field_0017.fontRendererObj.drawString(this.server.serverName, var2 + 32 + 3, var3 + 1, 16777215);
      List var25 = this.field_0017.fontRendererObj.listFormattedStringToWidth(this.server.serverMOTD, var4 - 32 - 2);

      for (int var26 = 0; var26 < Math.min(var25.size(), 2); var26++) {
         this.field_0017
            .fontRendererObj
            .drawString((String)var25.get(var26), var2 + 32 + 3, var3 + 12 + this.field_0017.fontRendererObj.FONT_HEIGHT * var26, 8421504);
      }

      String var27 = var24 ? EnumChatFormatting.DARK_RED + this.server.field_0014 : this.server.populationInfo;
      int var28 = this.field_0017.fontRendererObj.getStringWidth(var27);
      this.field_0017.fontRendererObj.drawString(var27, var2 + var4 - var28 - 15 - 2, var3 + 1, 8421504);
      byte var15 = 0;
      String var16 = null;
      int var17;
      String var18;
      if (var24) {
         var17 = 5;
         var18 = var22 ? "Client out of date!" : "Server out of date!";
         var16 = this.server.field_0004;
      } else if (this.server.field_0007 && this.server.pingToServer != (-2L & -2L)) {
         if (this.server.pingToServer < (808628290L & 71303585L)) {
            var17 = 5;
         } else if (this.server.pingToServer < (1157891319L & -4093888160142941802L)) {
            var17 = 0;
         } else if (this.server.pingToServer < (402658093L & 122945982L)) {
            var17 = 1;
         } else if (this.server.pingToServer < (6290845104254100056L & 258495225L)) {
            var17 = 2;
         } else if (this.server.pingToServer < (748690408L & 55617516L)) {
            var17 = 3;
         } else {
            var17 = 4;
         }

         if (this.server.pingToServer < (1610681344L & 553529L)) {
            var18 = "(no connection)";
         } else {
            var18 = this.server.pingToServer + "ms";
            var16 = this.server.field_0004;
         }
      } else {
         var15 = 1;
         var17 = (int)(Minecraft.getSystemTime() / (16779492L & 7167202143697454206L) + var1 * 2 & 1586795037908536327L & -1586795039252500337L);
         if (var17 > 4) {
            var17 = 8 - var17;
         }

         var18 = "Pinging...";
      }

      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.field_0017.getTextureManager().bindTexture(Gui.icons);
      Gui.drawModalRectWithCustomSizedTexture(var2 + var4 - 15, var3, var15 * 10, 176 + var17 * 8, 10, 8, 256.0F, 256.0F);
      if (this.server.getBase64EncodedIconData() != null && !this.server.getBase64EncodedIconData().equals(this.lastIconB64)) {
         this.lastIconB64 = this.server.getBase64EncodedIconData();
         this.method_00598();
         this.field_0010.getServerList().saveServerList();
      }

      if (this.field_0002 != null) {
         this.field_0002.setElementSize(var2, var3, 32.0F, 29.5F);
         this.field_0002.drawElement(0.0F, 0.0F, true);
      } else if (this.icon != null) {
         this.method_00595(var2, var3, this.field_0008);
      } else {
         this.method_00595(var2, var3, field_0006);
      }

      int var19 = var6 - var2;
      int var20 = var7 - var3;
      if (var19 >= var4 - 15 && var19 <= var4 - 5 && var20 >= 0 && var20 <= 8) {
         this.field_0010.setHoveringText(var18);
      } else if (var19 >= var4 - var28 - 15 - 2 && var19 <= var4 - 15 - 2 && var20 >= 0 && var20 <= 8) {
         this.field_0010.setHoveringText(var16);
      }

      if (this.field_0017.gameSettings.touchscreen || var8) {
         this.field_0017.getTextureManager().bindTexture(field_0003);
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
      this.field_0007 = new ResourceLocation("client/icons/cb.png");
      this.field_0010 = var1;
      this.server = var2;
      this.field_0017 = Minecraft.getMinecraft();
      this.field_0008 = new ResourceLocation("servers/" + var2.serverIP + "/icon");
      this.icon = (DynamicTexture)this.field_0017.getTextureManager().getTexture(this.field_0008);

      try {
         if (Objects.equals(ServerMappingLoader.method_12436(var2.serverIP, "id"), "lunarnetwork")) {
            this.field_0002 = new UnidentifiedClass0557();
         }
      } catch (Exception var4) {
         var4.printStackTrace();
      }
   }

   public static ServerData method_00596(PinnedServerEntry var0) {
      return var0.server;
   }

   public void method_00595(int var1, int var2, ResourceLocation var3) {
      this.field_0017.getTextureManager().bindTexture(var3);
      GlStateManager.enableBlend();
      Gui.drawModalRectWithCustomSizedTexture(var1, var2, 0.0F, 0.0F, 32, 32, 32.0F, 32.0F);
      GlStateManager.disableBlend();
   }

   public void method_00598() {
      if (this.server.getBase64EncodedIconData() == null) {
         this.field_0017.getTextureManager().getTexture(this.field_0008);
         this.icon = null;
      } else {
         ByteBuf var2 = Unpooled.copiedBuffer(this.server.getBase64EncodedIconData(), Charsets.UTF_8);
         ByteBuf var3 = Base64.decode(var2);

         BufferedImage var1;
         label63: {
            try {
               var1 = ImageIO.read(new ByteBufInputStream(var3));
               Validate.validState(var1.getWidth() == 64, "Must be 64 pixels wide", new Object[0]);
               Validate.validState(var1.getHeight() == 64, "Must be 64 pixels high", new Object[0]);
               break label63;
            } catch (Exception var8) {
               field_0018.error("Invalid icon for server " + this.server.serverName + " (" + this.server.serverIP + ")", var8);
               this.server.setBase64EncodedIconData((String)null);
            } finally {
               var2.release();
               var3.release();
            }

            return;
         }

         if (this.icon == null) {
            this.icon = new DynamicTexture(var1.getWidth(), var1.getHeight());
            this.field_0017.getTextureManager().loadTexture(this.field_0008, this.icon);
         }

         var1.getRGB(0, 0, var1.getWidth(), var1.getHeight(), this.icon.getTextureData(), 0, var1.getWidth());
         this.icon.updateDynamicTexture();
      }
   }

   @Override
   public boolean mousePressed(int var1, int var2, int var3, int var4, int var5, int var6) {
      this.field_0010.selectServer(var1);
      if (Minecraft.getSystemTime() - this.field_148298_f < (1076381178L & 2369415919342715134L)) {
         this.field_0010.connectToSelected();
      }

      this.field_148298_f = Minecraft.getSystemTime();
      if (var5 <= 32 && var5 < 32) {
         this.field_0010.connectToSelected();
         return true;
      } else {
         return false;
      }
   }

   public static GuiMultiplayer method_00599(PinnedServerEntry var0) {
      return var0.field_0010;
   }

   public ServerData getServer() {
      return this.server;
   }
}
