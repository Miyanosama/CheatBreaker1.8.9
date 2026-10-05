package net.minecraft.client.gui;

import com.cheatbreaker.client.ui.mainmenu.MainMenu;
import io.netty.buffer.PoolArena;
import java.util.List;
import net.minecraft.client.multiplayer.GuiConnecting;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.IChatComponent;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$28;
import org.apache.log4j.spi.DefaultRepositorySelector;

public class GuiDisconnected extends GuiScreen {
   public int field_175353_i;
   public List<String> multilineMessage;
   public GuiScreen parentScreen;
   public LogBrokerMonitor$28 field_0005;
   public String reason;
   public IChatComponent message;
   public DefaultRepositorySelector field_0007;
   public PoolArena field_0004;

   @Override
   public void actionPerformed(GuiButton var1) {
      if (var1.k == 0) {
         this.j.displayGuiScreen(this.parentScreen);
      }

      if (var1.k == 0) {
         if (this.parentScreen instanceof GuiDisconnected) {
            this.j.displayGuiScreen(new GuiMultiplayer(new MainMenu()));
         } else {
            this.j.displayGuiScreen(this.parentScreen);
         }
      }

      if (var1.k == 1 && this.j.currentServerData != null) {
         this.j.displayGuiScreen(new GuiConnecting(this, this.j, this.j.currentServerData));
      }
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.q, this.reason, this.l / 2, this.m / 2 - this.field_175353_i / 2 - this.q.FONT_HEIGHT * 2, 11184810);
      int var4 = this.m / 2 - this.field_175353_i / 2;
      if (this.multilineMessage != null) {
         for (String var6 : this.multilineMessage) {
            this.drawCenteredString(this.q, var6, this.l / 2, var4, 16777215);
            var4 += this.q.FONT_HEIGHT;
         }
      }

      super.drawScreen(var1, var2, var3);
   }

   public GuiDisconnected(GuiScreen var1, String var2, IChatComponent var3) {
      this.parentScreen = var1;
      this.reason = I18n.format(var2);
      this.message = var3;
   }

   @Override
   public void keyTyped(char var1, int var2) {
   }

   @Override
   public void initGui() {
      this.n.clear();
      this.multilineMessage = this.q.listFormattedStringToWidth(this.message.getFormattedText(), this.l - 50);
      this.field_175353_i = this.multilineMessage.size() * this.q.FONT_HEIGHT;
      this.n.add(new GuiButton(0, this.l / 2 - 100, this.m / 2 + this.field_175353_i / 2 + this.q.FONT_HEIGHT, I18n.format("gui.toMenu")));
      this.n.add(new GuiButton(1, this.l / 2 - 100, this.m / 2 + this.field_175353_i / 2 + this.q.FONT_HEIGHT + 25, "Reconnect"));
   }
}
