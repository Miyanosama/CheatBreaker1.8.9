package net.minecraft.client.gui;

import net.minecraft.client.resources.I18n;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.ChatComponentTranslation;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.WorldSettings;

public class GuiShareToLan extends GuiScreen {
   public boolean field_146600_i;
   public String field_146599_h = "survival";
   public GuiScreen field_146598_a;
   public GuiButton field_146596_f;
   public GuiButton field_146597_g;

   public void func_146595_g() {
      this.field_146597_g.j = I18n.format("selectWorld.gameMode") + " " + I18n.format("selectWorld.gameMode." + this.field_146599_h);
      this.field_146596_f.j = I18n.format("selectWorld.allowCommands") + " ";
      if (this.field_146600_i) {
         this.field_146596_f.j = this.field_146596_f.j + I18n.format("options.on");
      } else {
         this.field_146596_f.j = this.field_146596_f.j + I18n.format("options.off");
      }
   }

   public GuiShareToLan(GuiScreen var1) {
      this.field_146598_a = var1;
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.q, I18n.format("lanServer.title"), this.l / 2, 50, 16777215);
      this.drawCenteredString(this.q, I18n.format("lanServer.otherPlayers"), this.l / 2, 82, 16777215);
      super.drawScreen(var1, var2, var3);
   }

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      if (var1.k == 102) {
         this.j.displayGuiScreen(this.field_146598_a);
      } else if (var1.k == 104) {
         if (this.field_146599_h.equals("spectator")) {
            this.field_146599_h = "creative";
         } else if (this.field_146599_h.equals("creative")) {
            this.field_146599_h = "adventure";
         } else if (this.field_146599_h.equals("adventure")) {
            this.field_146599_h = "survival";
         } else {
            this.field_146599_h = "spectator";
         }

         this.func_146595_g();
      } else if (var1.k == 103) {
         this.field_146600_i = !this.field_146600_i;
         this.func_146595_g();
      } else if (var1.k == 101) {
         this.j.displayGuiScreen((GuiScreen)null);
         String var2 = this.j.getIntegratedServer().shareToLAN(WorldSettings.GameType.getByName(this.field_146599_h), this.field_146600_i);
         Object var3;
         if (var2 != null) {
            var3 = new ChatComponentTranslation("commands.publish.started", var2);
         } else {
            var3 = new ChatComponentText("commands.publish.failed");
         }

         this.j.ingameGUI.getChatGUI().printChatMessage((IChatComponent)var3);
      }
   }

   @Override
   public void initGui() {
      this.n.clear();
      this.n.add(new GuiButton(101, this.l / 2 - 155, this.m - 28, 150, 20, I18n.format("lanServer.start")));
      this.n.add(new GuiButton(102, this.l / 2 + 5, this.m - 28, 150, 20, I18n.format("gui.cancel")));
      this.n.add(this.field_146597_g = new GuiButton(104, this.l / 2 - 155, 100, 150, 20, I18n.format("selectWorld.gameMode")));
      this.n.add(this.field_146596_f = new GuiButton(103, this.l / 2 + 5, 100, 150, 20, I18n.format("selectWorld.allowCommands")));
      this.func_146595_g();
   }
}
