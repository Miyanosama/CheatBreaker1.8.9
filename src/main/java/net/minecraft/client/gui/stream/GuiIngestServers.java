package net.minecraft.client.gui.stream;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.stream.IngestServerTester;
import net.minecraft.util.EnumChatFormatting;
import tv.twitch.broadcast.IngestServer;

public class GuiIngestServers extends GuiScreen {
   public GuiIngestServers.ServerList field_152311_g;
   public String field_152310_f;
   public GuiScreen field_152309_a;

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.field_152311_g.a(var1, var2, var3);
      this.drawCenteredString(this.q, this.field_152310_f, this.l / 2, 20, 16777215);
      super.drawScreen(var1, var2, var3);
   }

   @Override
   public void a_() {
      if (this.j.getTwitchStream().func_152908_z()) {
         this.j.getTwitchStream().func_152932_y().func_153039_l();
      }
   }

   @Override
   public void initGui() {
      this.field_152310_f = I18n.format("options.stream.ingest.title");
      this.field_152311_g = new GuiIngestServers.ServerList(this.j);
      if (!this.j.getTwitchStream().func_152908_z()) {
         this.j.getTwitchStream().func_152909_x();
      }

      this.n.add(new GuiButton(1, this.l / 2 - 155, this.m - 24 - 6, 150, 20, I18n.format("gui.done")));
      this.n.add(new GuiButton(2, this.l / 2 + 5, this.m - 24 - 6, 150, 20, I18n.format("options.stream.ingest.reset")));
   }

   public GuiIngestServers(GuiScreen var1) {
      this.field_152309_a = var1;
   }

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      if (var1.l) {
         if (var1.k == 1) {
            this.j.displayGuiScreen(this.field_152309_a);
         } else {
            this.j.gameSettings.streamPreferredServer = "";
            this.j.gameSettings.saveOptions();
         }
      }
   }

   @Override
   public void handleMouseInput() throws java.io.IOException {
      super.handleMouseInput();
      this.field_152311_g.handleMouseInput();
   }

   public class ServerList extends GuiSlot {
      @Override
      public void drawSlot(int var1, int var2, int var3, int var4, int var5, int var6) {
         IngestServer var7 = this.a.getTwitchStream().func_152925_v()[var1];
         Object var8 = var7.serverUrl.replaceAll("\\{stream_key\\}", "");
         String var9 = (int)var7.bitrateKbps + " kbps";
         String var10 = null;
         IngestServerTester var11 = this.a.getTwitchStream().func_152932_y();
         if (var11 != null) {
            if (var7 == var11.func_153040_c()) {
               var8 = EnumChatFormatting.GREEN.toString() + var8;
               var9 = (int)(var11.func_153030_h() * 100.0F) + "%";
            } else if (var1 < var11.func_153028_p()) {
               if (var7.bitrateKbps == 0.0F) {
                  var9 = EnumChatFormatting.RED + "Down!";
               }
            } else {
               var9 = EnumChatFormatting.OBFUSCATED + "1234" + EnumChatFormatting.RESET + " kbps";
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

         GuiIngestServers.this.drawString(GuiIngestServers.this.q, var7.serverName, var2 + 2, var3 + 5, 16777215);
         GuiIngestServers.this.drawString(GuiIngestServers.this.q, (String)var8, var2 + 2, var3 + GuiIngestServers.this.q.FONT_HEIGHT + 5 + 3, 3158064);
         GuiIngestServers.this.drawString(
            GuiIngestServers.this.q, var9, this.getScrollBarX() - 5 - GuiIngestServers.this.q.getStringWidth(var9), var3 + 5, 8421504
         );
         if (var10 != null) {
            GuiIngestServers.this.drawString(
               GuiIngestServers.this.q,
               var10,
               this.getScrollBarX() - 5 - GuiIngestServers.this.q.getStringWidth(var10),
               var3 + 5 + 3 + GuiIngestServers.this.q.FONT_HEIGHT,
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

      public ServerList(Minecraft var2) {
         super(var2, GuiIngestServers.this.l, GuiIngestServers.this.m, 32, GuiIngestServers.this.m - 35, (int)(var2.fontRendererObj.FONT_HEIGHT * 3.5));
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
}
