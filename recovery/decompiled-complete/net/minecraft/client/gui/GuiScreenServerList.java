package net.minecraft.client.gui;

import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder$IncompatibleDataDecoderException;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.resources.I18n;
import net.minecraft.network.play.server.S13PacketDestroyEntities;
import net.minecraft.world.gen.structure.StructureVillagePieces$Road;
import org.java_websocket.server.WebSocketServer$WebSocketWorker;
import org.lwjgl.input.Keyboard;

public class GuiScreenServerList extends GuiScreen {
   public StructureVillagePieces$Road field_0003;
   public S13PacketDestroyEntities field_0006;
   public GuiScreenBook field_0002;
   public GuiScreen field_146303_a;
   public HttpPostRequestDecoder$IncompatibleDataDecoderException field_0000;
   public GuiTextField field_146302_g;
   public WebSocketServer$WebSocketWorker field_0007;
   public ServerData field_146301_f;

   public GuiScreenServerList(GuiScreen var1, ServerData var2) {
      this.field_146303_a = var1;
      this.field_146301_f = var2;
   }

   @Override
   public void updateScreen() {
      this.field_146302_g.updateCursorCounter();
   }

   @Override
   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      this.n.clear();
      this.n.add(new GuiButton(0, this.l / 2 - 100, this.m / 4 + 96 + 12, I18n.format("selectServer.select")));
      this.n.add(new GuiButton(1, this.l / 2 - 100, this.m / 4 + 120 + 12, I18n.format("gui.cancel")));
      this.field_146302_g = new GuiTextField(2, this.q, this.l / 2 - 100, 116, 200, 20);
      this.field_146302_g.setMaxStringLength(128);
      this.field_146302_g.setFocused(true);
      this.field_146302_g.setText(this.j.gameSettings.lastServer);
      this.n.get(0).l = this.field_146302_g.getText().length() > 0 && this.field_146302_g.getText().split(":").length > 0;
   }

   @Override
   public void keyTyped(char var1, int var2) {
      if (this.field_146302_g.textboxKeyTyped(var1, var2)) {
         this.n.get(0).l = this.field_146302_g.getText().length() > 0 && this.field_146302_g.getText().split(":").length > 0;
      } else if (var2 == 28 || var2 == 156) {
         this.actionPerformed(this.n.get(0));
      }
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.q, I18n.format("selectServer.direct"), this.l / 2, 20, 16777215);
      this.drawString(this.q, I18n.format("addServer.enterIp"), this.l / 2 - 100, 100, 10526880);
      this.field_146302_g.drawTextBox();
      super.drawScreen(var1, var2, var3);
   }

   @Override
   public void actionPerformed(GuiButton var1) {
      if (var1.l) {
         if (var1.k == 1) {
            this.field_146303_a.confirmClicked(false, 0);
         } else if (var1.k == 0) {
            this.field_146301_f.serverIP = this.field_146302_g.getText();
            this.field_146303_a.confirmClicked(true, 0);
         }
      }
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) {
      super.mouseClicked(var1, var2, var3);
      this.field_146302_g.mouseClicked(var1, var2, var3);
   }

   @Override
   public void a_() {
      Keyboard.enableRepeatEvents(false);
      this.j.gameSettings.lastServer = this.field_146302_g.getText();
      this.j.gameSettings.saveOptions();
   }
}
