package net.minecraft.client.gui.stream;

import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.handler.codec.spdy.SpdyHeaderBlockRawDecoder$State;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.stream.IngestServerTester;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.util.EnumChatFormatting;
import tv.twitch.broadcast.IngestServer;

public class GuiIngestServers$ServerList extends GuiSlot {
   public CrashReportCategory field_0003;
   public SpdyHeaderBlockRawDecoder$State field_0000;
   public WebSocketServerHandshakerFactory field_0002;

   @Override
   public void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
      IngestServer var7 = this.a.getTwitchStream().func_152925_v()[var1];
      Object var8 = var7.serverUrl.replaceAll("\\{stream_key\\}", "");
      String var9 = (int)var7.bitrateKbps + " kbps";
      String var10 = null;
      IngestServerTester var11 = this.a.getTwitchStream().func_152932_y();
      if (var11 != null) {
         if (var7 == var11.func_153040_c()) {
            var8 = EnumChatFormatting.GREEN + var8;
            var9 = (int)(var11.func_153030_h() * 100.0F) + "%";
         } else if (var1 < var11.func_153028_p()) {
            if (var7.bitrateKbps == 0.0F) {
               var9 = EnumChatFormatting.RED + "Down!";
            }
         } else {
            var9 = EnumChatFormatting.field_0000 + "1234" + EnumChatFormatting.RESET + " kbps";
         }
      } else if (var7.bitrateKbps == 0.0F) {
         var9 = EnumChatFormatting.RED + "Down!";
      }

      var2 -= 15;
      if (this.isSelected(var1)) {
         var10 = EnumChatFormatting.BLUE + "(Preferred)";
      } else if (var7.defaultServer) {
         var10 = EnumChatFormatting.GREEN + "(Default)";
      }

      this.field_152435_k.drawString(GuiIngestServers.method_04479(this.field_152435_k), var7.serverName, var2 + 2, var3 + 5, 16777215);
      this.field_152435_k
         .drawString(
            GuiIngestServers.method_04480(this.field_152435_k),
            (String)var8,
            var2 + 2,
            var3 + GuiIngestServers.method_04477(this.field_152435_k).FONT_HEIGHT + 5 + 3,
            3158064
         );
      this.field_152435_k
         .drawString(
            GuiIngestServers.method_04478(this.field_152435_k),
            var9,
            this.getScrollBarX() - 5 - GuiIngestServers.method_04476(this.field_152435_k).getStringWidth(var9),
            var3 + 5,
            8421504
         );
      if (var10 != null) {
         this.field_152435_k
            .drawString(
               GuiIngestServers.method_04483(this.field_152435_k),
               var10,
               this.getScrollBarX() - 5 - GuiIngestServers.method_04481(this.field_152435_k).getStringWidth(var10),
               var3 + 5 + 3 + GuiIngestServers.method_04482(this.field_152435_k).FONT_HEIGHT,
               8421504
            );
      }
   }

   @Override
   public void elementClicked(int var1, boolean var2, int var3, int var4) {
      this.a.gameSettings.streamPreferredServer = this.a.getTwitchStream().func_152925_v()[var1].serverUrl;
      this.a.gameSettings.saveOptions();
   }

   @Override
   public void drawBackground() {
   }

   @Override
   public int getScrollBarX() {
      return super.getScrollBarX() + 15;
   }

   public GuiIngestServers$ServerList(GuiIngestServers var1, Minecraft var2) {
      this.field_152435_k = var1;
      super(var2, var1.l, var1.m, 32, var1.m - 35, (int)(var2.fontRendererObj.FONT_HEIGHT * 3.5));
      this.setShowSelectionBox(false);
   }

   @Override
   public boolean isSelected(int var1) {
      return this.a.getTwitchStream().func_152925_v()[var1].serverUrl.equals(this.a.gameSettings.streamPreferredServer);
   }

   @Override
   public int getSize() {
      return this.a.getTwitchStream().func_152925_v().length;
   }
}
