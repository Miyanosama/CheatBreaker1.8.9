package net.minecraft.client.gui;

import com.google.common.base.Predicate;
import java.net.IDN;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.client.resources.I18n;
import org.lwjgl.input.Keyboard;

public class GuiScreenAddServer extends GuiScreen {
   public ServerData serverData;
   public Predicate<String> field_181032_r = new Predicate<String>() {
      public boolean apply(String var1) {
         if (var1.length() == 0) {
            return true;
         } else {
            String[] var2 = var1.split(":");
            if (var2.length == 0) {
               return true;
            } else {
               try {
                  String var3 = IDN.toASCII(var2[0]);
                  return true;
               } catch (IllegalArgumentException var4) {
                  return false;
               }
            }
         }
      }
   };
   public GuiButton serverResourcePacks;
   public GuiTextField serverIPField;
   public GuiScreen parentScreen;
   public GuiTextField serverNameField;

   @Override
   public void mouseClicked(int var1, int var2, int var3) throws java.io.IOException {
      super.mouseClicked(var1, var2, var3);
      this.serverIPField.mouseClicked(var1, var2, var3);
      this.serverNameField.mouseClicked(var1, var2, var3);
   }

   public GuiScreenAddServer(GuiScreen var1, ServerData var2) {
      this.parentScreen = var1;
      this.serverData = var2;
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.drawDefaultBackground();
      this.drawCenteredString(this.q, I18n.format("addServer.title"), this.l / 2, 17, 16777215);
      this.drawString(this.q, I18n.format("addServer.enterName"), this.l / 2 - 100, 53, 10526880);
      this.drawString(this.q, I18n.format("addServer.enterIp"), this.l / 2 - 100, 94, 10526880);
      this.serverNameField.drawTextBox();
      this.serverIPField.drawTextBox();
      super.drawScreen(var1, var2, var3);
   }

   @Override
   public void a_() {
      Keyboard.enableRepeatEvents(false);
   }

   @Override
   public void actionPerformed(GuiButton var1) throws java.io.IOException {
      if (var1.l) {
         if (var1.k == 2) {
            this.serverData
               .setResourceMode(
                  ServerData.ServerResourceMode.values()[(this.serverData.getResourceMode().ordinal() + 1) % ServerData.ServerResourceMode.values().length]
               );
            this.serverResourcePacks.j = I18n.format("addServer.resourcePack") + ": " + this.serverData.getResourceMode().getMotd().getFormattedText();
         } else if (var1.k == 1) {
            this.parentScreen.confirmClicked(false, 0);
         } else if (var1.k == 0) {
            this.serverData.serverName = this.serverNameField.getText();
            this.serverData.serverIP = this.serverIPField.getText();
            this.parentScreen.confirmClicked(true, 0);
         }
      }
   }

   @Override
   public void updateScreen() {
      this.serverNameField.updateCursorCounter();
      this.serverIPField.updateCursorCounter();
   }

   @Override
   public void keyTyped(char var1, int var2) throws java.io.IOException {
      this.serverNameField.textboxKeyTyped(var1, var2);
      this.serverIPField.textboxKeyTyped(var1, var2);
      if (var2 == 15) {
         this.serverNameField.setFocused(!this.serverNameField.isFocused());
         this.serverIPField.setFocused(!this.serverIPField.isFocused());
      }

      if (var2 == 28 || var2 == 156) {
         this.actionPerformed(this.n.get(0));
      }

      this.n.get(0).l = this.serverIPField.getText().length() > 0
         && this.serverIPField.getText().split(":").length > 0
         && this.serverNameField.getText().length() > 0;
   }

   @Override
   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      this.n.clear();
      this.n.add(new GuiButton(0, this.l / 2 - 100, this.m / 4 + 96 + 18, I18n.format("addServer.add")));
      this.n.add(new GuiButton(1, this.l / 2 - 100, this.m / 4 + 120 + 18, I18n.format("gui.cancel")));
      this.n
         .add(
            this.serverResourcePacks = new GuiButton(
               2,
               this.l / 2 - 100,
               this.m / 4 + 72,
               I18n.format("addServer.resourcePack") + ": " + this.serverData.getResourceMode().getMotd().getFormattedText()
            )
         );
      this.serverNameField = new GuiTextField(0, this.q, this.l / 2 - 100, 66, 200, 20);
      this.serverNameField.setFocused(true);
      this.serverNameField.setText(this.serverData.serverName);
      this.serverIPField = new GuiTextField(1, this.q, this.l / 2 - 100, 106, 200, 20);
      this.serverIPField.setMaxStringLength(128);
      this.serverIPField.setText(this.serverData.serverIP);
      this.serverIPField.setValidator(this.field_181032_r);
      this.n.get(0).l = this.serverIPField.getText().length() > 0
         && this.serverIPField.getText().split(":").length > 0
         && this.serverNameField.getText().length() > 0;
   }
}
