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
import io.netty.channel.local.LocalChannel$1;
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
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.WorldGenCanopyTree;
import org.apache.commons.lang3.Validate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass0557;
import recovered.unidentified.UnidentifiedClass1449;

public class ServerListEntryNormal implements GuiListExtended$IGuiListEntry {
   public UnidentifiedClass0557 field_0009;
   public UnidentifiedClass1449 field_0018;
   public DynamicTexture field_148305_h;
   public String field_148299_g;
   public ResourceLocation field_0003;
   public ResourceLocation field_0004;
   public static Logger logger = LogManager.getLogger();
   public ServerData server;
   public WorldGenCanopyTree field_0005;
   public ResourceLocation field_0020 = new ResourceLocation("client/icons/error-64.png");
   public static ResourceLocation SERVER_SELECTION_BUTTONS = new ResourceLocation("textures/gui/server_selection.png");
   public ResourceLocation field_0010;
   public LocalChannel$1 field_0012;
   public static ThreadPoolExecutor field_148302_b = new ScheduledThreadPoolExecutor(
      5, new ThreadFactoryBuilder().setNameFormat("Server Pinger #%d").setDaemon(true).build()
   );
   public long field_148298_f;
   public boolean field_0017;
   public boolean field_0001;
   public GuiMultiplayer field_0006;
   public EntityArmorStand field_0011;
   public Minecraft mc;
   public static ResourceLocation UNKNOWN_SERVER = new ResourceLocation("textures/misc/unknown_server.png");

   @Override
   public void drawEntry(int var1, int var2, int var3, int var4, int var5, int var6, int var7, boolean var8) {
      if (!this.server.field_0007) {
         this.server.field_0007 = true;
         this.server.pingToServer = -2L & -1L;
         this.server.serverMOTD = "";
         this.server.populationInfo = "";
         field_148302_b.submit(() -> {
            try {
               this.field_0006.getOldServerPinger().method_08034(this.server);
            } catch (UnknownHostException var2x) {
               this.server.pingToServer = -1L & -1L;
               this.server.serverMOTD = EnumChatFormatting.DARK_RED + "Can't resolve hostname";
            } catch (Exception var3x) {
               this.server.pingToServer = -1L & -1L;
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
         Minecraft.getMinecraft().renderEngine.bindTexture(this.field_0004);
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

      if (this.server.field_0017) {
         this.field_0018.setElementSize(var2 - 19.5F, var3 + 10, 15.0F, 13.71F);
         this.field_0018.drawElement(0.0F, 0.0F, true);
      }

      for (Entry var25 : CheatBreaker.getInstance().getGlobalSettings().method_02677().entrySet()) {
         for (String var34 : (String[])var25.getKey()) {
            if (this.server.serverIP.endsWith(var34.toLowerCase())) {
               boolean var15 = ((String[])var25.getValue())[1].equals(String.valueOf(ServerRestrictionAction.field_0005));
               GL11.glColor4f(0.9F, var15 ? 0.0F : 0.8F, 0.0F, 1.0F);
               this.field_0017 = true;
               this.field_0001 = var15;
               RenderUtil.drawIcon(
                  var15 ? this.field_0003 : this.field_0020, 5.0F, var2 - (this.server.field_0017 ? 40 : 17), var3 + (this.server.method_04647() ? 4 : 12)
               );
            }
         }
      }

      boolean var24 = this.server.field_0011 > 47;
      boolean var26 = this.server.field_0011 < 47;
      boolean var28 = var24 || var26;
      this.mc
         .fontRendererObj
         .drawString(
            this.server.serverName, var2 + 32 + 3, var3 + 1, this.field_0017 ? new Color(0.9F, this.field_0001 ? 0.0F : 0.8F, 0.0F, 1.0F).getRGB() : 16777215
         );
      List var30 = this.mc.fontRendererObj.listFormattedStringToWidth(this.server.serverMOTD, var4 - 32 - 2);

      for (int var32 = 0; var32 < Math.min(var30.size(), 2); var32++) {
         this.mc.fontRendererObj.drawString((String)var30.get(var32), var2 + 32 + 3, var3 + 12 + this.mc.fontRendererObj.FONT_HEIGHT * var32, 8421504);
      }

      String var33 = var28 ? EnumChatFormatting.DARK_RED + this.server.field_0014 : this.server.populationInfo;
      int var35 = this.mc.fontRendererObj.getStringWidth(var33);
      this.mc.fontRendererObj.drawString(var33, var2 + var4 - var35 - 15 - 2, var3 + 1, 8421504);
      byte var36 = 0;
      String var16 = null;
      int var17;
      String var18;
      if (var28) {
         var17 = 5;
         var18 = var24 ? "Client out of date!" : "Server out of date!";
         var16 = this.server.field_0004;
      } else if (this.server.field_0007 && this.server.pingToServer != (-2L & -2L)) {
         if (this.server.pingToServer < (338035778L & 700555428L)) {
            var17 = 5;
         } else if (this.server.pingToServer < (-5740814863946465130L & 5740814863908212887L)) {
            var17 = 0;
         } else if (this.server.pingToServer < (604045614L & 277086508L)) {
            var17 = 1;
         } else if (this.server.pingToServer < (806388440L & 6108854638864245337L)) {
            var17 = 2;
         } else if (this.server.pingToServer < (-4556749227010533384L & 4556749225060729836L)) {
            var17 = 3;
         } else {
            var17 = 4;
         }

         if (this.server.pingToServer < (470944772L & 48267784L)) {
            var18 = "(no connection)";
         } else {
            var18 = this.server.pingToServer + "ms";
            var16 = this.server.field_0004;
         }
      } else {
         var36 = 1;
         var17 = (int)(Minecraft.getSystemTime() / (1955356780L & 2276L) + var1 * 2 & -3383542284797205209L & 1242648719L);
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
         this.field_0006.getServerList().saveServerList();
      }

      if (this.field_0009 != null) {
         this.field_0009.setElementSize(var2, var3, 32.0F, 29.5F);
         this.field_0009.drawElement(0.0F, 0.0F, true);
      } else if (this.field_148305_h != null) {
         this.drawTextureAt(var2, var3, this.field_0010);
      } else {
         this.drawTextureAt(var2, var3, UNKNOWN_SERVER);
      }

      int var19 = var6 - var2;
      int var20 = var7 - var3;
      if (var19 >= var4 - 15 && var19 <= var4 - 5 && var20 >= 0 && var20 <= 8) {
         this.field_0006.setHoveringText(var18);
      } else if (var19 >= var4 - var35 - 15 - 2 && var19 <= var4 - 15 - 2 && var20 >= 0 && var20 <= 8) {
         this.field_0006.setHoveringText(var16);
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

         if (this.field_0006.func_175392_a(this, var1)) {
            if (var21 < 16 && var22 < 16) {
               Gui.drawModalRectWithCustomSizedTexture(var2, var3, 96.0F, 32.0F, 32, 32, 256.0F, 256.0F);
            } else {
               Gui.drawModalRectWithCustomSizedTexture(var2, var3, 96.0F, 0.0F, 32, 32, 256.0F, 256.0F);
            }
         }

         if (this.field_0006.func_175394_b(this, var1)) {
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
            this.field_0006.selectServer(var1);
            this.field_0006.connectToSelected();
            return true;
         }

         if (var5 < 16 && var6 < 16 && this.field_0006.func_175392_a(this, var1)) {
            this.field_0006.func_175391_a(this, var1, GuiScreen.isShiftKeyDown());
            return true;
         }

         if (var5 < 16 && var6 > 16 && this.field_0006.func_175394_b(this, var1)) {
            this.field_0006.func_175393_b(this, var1, GuiScreen.isShiftKeyDown());
            return true;
         }
      }

      this.field_0006.selectServer(var1);
      if (Minecraft.getSystemTime() - this.field_148298_f < (1342447867L & 612892922L)) {
         this.field_0006.connectToSelected();
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
         this.mc.getTextureManager().deleteTexture(this.field_0010);
         this.field_148305_h = null;
      } else {
         ByteBuf var1 = Unpooled.copiedBuffer(this.server.getBase64EncodedIconData(), Charsets.UTF_8);
         ByteBuf var2 = Base64.decode(var1);

         BufferedImage var3;
         label63: {
            try {
               var3 = TextureUtil.readBufferedImage(new ByteBufInputStream(var2));
               Validate.validState(var3.getWidth() == 64, "Must be 64 pixels wide", new Object[0]);
               Validate.validState(var3.getHeight() == 64, "Must be 64 pixels high", new Object[0]);
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
            this.mc.getTextureManager().loadTexture(this.field_0010, this.field_148305_h);
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
      this.field_0003 = new ResourceLocation("client/icons/delete-64.png");
      this.field_0004 = new ResourceLocation("client/icons/cb.png");
      this.field_0018 = new UnidentifiedClass1449();
      this.field_0006 = var1;
      this.server = var2;
      this.mc = Minecraft.getMinecraft();
      this.field_0010 = new ResourceLocation("servers/" + var2.serverIP + "/icon");
      this.field_148305_h = (DynamicTexture)this.mc.getTextureManager().getTexture(this.field_0010);

      try {
         if (Objects.equals(ServerMappingLoader.method_12436(var2.serverIP, "id"), "lunarnetwork")) {
            this.field_0009 = new UnidentifiedClass0557();
         }
      } catch (Exception var4) {
         var4.printStackTrace();
      }
   }
}
