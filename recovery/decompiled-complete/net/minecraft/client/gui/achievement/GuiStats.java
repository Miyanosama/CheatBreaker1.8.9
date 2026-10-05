package net.minecraft.client.gui.achievement;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiSlot;
import net.minecraft.client.gui.IProgressMeter;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.resources.I18n;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.NetworkSystem$1;
import net.minecraft.network.play.client.C08PacketPlayerBlockPlacement;
import net.minecraft.network.play.client.C16PacketClientStatus;
import net.minecraft.network.play.client.C16PacketClientStatus$EnumState;
import net.minecraft.server.management.BanEntry;
import net.minecraft.stats.StatFileWriter;

public class GuiStats extends GuiScreen implements IProgressMeter {
   public C08PacketPlayerBlockPlacement field_0009;
   public StatFileWriter field_146546_t;
   public BanEntry field_0008;
   public GuiStats$StatsBlock blockStats;
   public GuiScreen parentScreen;
   public GuiStats$StatsGeneral generalStats;
   public boolean doesGuiPauseGame;
   public GuiSlot displaySlot;
   public GuiStats$StatsItem itemStats;
   public GuiStats$StatsMobsList mobStats;
   public String screenTitle = "Select world";
   public NetworkSystem$1 field_0006;

   @Override
   public void actionPerformed(GuiButton var1) {
      if (var1.l) {
         if (var1.k == 0) {
            this.j.displayGuiScreen(this.parentScreen);
         } else if (var1.k == 1) {
            this.displaySlot = this.generalStats;
         } else if (var1.k == 3) {
            this.displaySlot = this.itemStats;
         } else if (var1.k == 2) {
            this.displaySlot = this.blockStats;
         } else if (var1.k == 4) {
            this.displaySlot = this.mobStats;
         } else {
            this.displaySlot.actionPerformed(var1);
         }
      }
   }

   public void drawStatsScreen(int var1, int var2, Item var3) {
      this.drawButtonBackground(var1 + 1, var2 + 1);
      GlStateManager.enableRescaleNormal();
      RenderHelper.enableGUIStandardItemLighting();
      this.k.renderItemIntoGUI(new ItemStack(var3, 1, 0), var1 + 2, var2 + 2);
      RenderHelper.disableStandardItemLighting();
      GlStateManager.disableRescaleNormal();
   }

   public void drawButtonBackground(int var1, int var2) {
      this.drawSprite(var1, var2, 0, 0);
   }

   public void func_175366_f() {
      this.generalStats = new GuiStats$StatsGeneral(this, this.j);
      this.generalStats.registerScrollButtons(1, 1);
      this.itemStats = new GuiStats$StatsItem(this, this.j);
      this.itemStats.registerScrollButtons(1, 1);
      this.blockStats = new GuiStats$StatsBlock(this, this.j);
      this.blockStats.registerScrollButtons(1, 1);
      this.mobStats = new GuiStats$StatsMobsList(this, this.j);
      this.mobStats.registerScrollButtons(1, 1);
   }

   @Override
   public void initGui() {
      this.screenTitle = I18n.format("gui.stats");
      this.doesGuiPauseGame = true;
      this.j.getNetHandler().addToSendQueue(new C16PacketClientStatus(C16PacketClientStatus$EnumState.REQUEST_STATS));
   }

   @Override
   public void handleMouseInput() {
      super.handleMouseInput();
      if (this.displaySlot != null) {
         this.displaySlot.handleMouseInput();
      }
   }

   public GuiStats(GuiScreen var1, StatFileWriter var2) {
      this.doesGuiPauseGame = true;
      this.parentScreen = var1;
      this.field_146546_t = var2;
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      if (this.doesGuiPauseGame) {
         this.drawDefaultBackground();
         this.drawCenteredString(this.q, I18n.format("multiplayer.downloadingStats"), this.l / 2, this.m / 2, 16777215);
         this.drawCenteredString(
            this.q,
            lanSearchStates[(int)(Minecraft.getSystemTime() / (816677622L & -3394400754541264482L) % lanSearchStates.length)],
            this.l / 2,
            this.m / 2 + this.q.FONT_HEIGHT * 2,
            16777215
         );
      } else {
         this.displaySlot.a(var1, var2, var3);
         this.drawCenteredString(this.q, this.screenTitle, this.l / 2, 20, 16777215);
         super.drawScreen(var1, var2, var3);
      }
   }

   @Override
   public void doneLoading() {
      if (this.doesGuiPauseGame) {
         this.func_175366_f();
         this.createButtons();
         this.displaySlot = this.generalStats;
         this.doesGuiPauseGame = false;
      }
   }

   public void createButtons() {
      this.n.add(new GuiButton(0, this.l / 2 + 4, this.m - 28, 150, 20, I18n.format("gui.done")));
      this.n.add(new GuiButton(1, this.l / 2 - 160, this.m - 52, 80, 20, I18n.format("stat.generalButton")));
      GuiButton var1;
      this.n.add(var1 = new GuiButton(2, this.l / 2 - 80, this.m - 52, 80, 20, I18n.format("stat.blocksButton")));
      GuiButton var2;
      this.n.add(var2 = new GuiButton(3, this.l / 2, this.m - 52, 80, 20, I18n.format("stat.itemsButton")));
      GuiButton var3;
      this.n.add(var3 = new GuiButton(4, this.l / 2 + 80, this.m - 52, 80, 20, I18n.format("stat.mobsButton")));
      if (this.blockStats.getSize() == 0) {
         var1.l = false;
      }

      if (this.itemStats.getSize() == 0) {
         var2.l = false;
      }

      if (this.mobStats.getSize() == 0) {
         var3.l = false;
      }
   }

   @Override
   public boolean b_() {
      return !this.doesGuiPauseGame;
   }

   public void drawSprite(int var1, int var2, int var3, int var4) {
      GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
      this.j.getTextureManager().bindTexture(statIcons);
      float var5 = 0.0078125F;
      float var6 = 0.0078125F;
      byte var7 = 18;
      byte var8 = 18;
      Tessellator var9 = Tessellator.getInstance();
      WorldRenderer var10 = var9.getWorldRenderer();
      var10.begin(7, DefaultVertexFormats.POSITION_TEX);
      var10.pos(var1 + 0, var2 + 18, field_0003).tex((var3 + 0) * 0.0078125F, (var4 + 18) * 0.0078125F).endVertex();
      var10.pos(var1 + 18, var2 + 18, field_0003).tex((var3 + 18) * 0.0078125F, (var4 + 18) * 0.0078125F).endVertex();
      var10.pos(var1 + 18, var2 + 0, field_0003).tex((var3 + 18) * 0.0078125F, (var4 + 0) * 0.0078125F).endVertex();
      var10.pos(var1 + 0, var2 + 0, field_0003).tex((var3 + 0) * 0.0078125F, (var4 + 0) * 0.0078125F).endVertex();
      var9.draw();
   }
}
