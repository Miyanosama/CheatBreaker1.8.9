package com.cheatbreaker.client.ui.module;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Profile;
import com.cheatbreaker.client.ui.element.ProfileElement;
import com.cheatbreaker.client.ui.element.profile.ProfilesListElement;
import java.io.File;
import java.nio.file.Files;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class CBProfileCreateGui extends GuiScreen {
   public Profile profile;
   public float recoveredField2906;
   public boolean recoveredField2907;
   public ProfilesListElement parent;
   public String recoveredField2908;
   public GuiScreen guiScreen;
   public int recoveredField2909;
   public GuiTextField recoveredField2910 = null;

   @Override
   public void keyTyped(char var1, int var2) throws java.io.IOException {
      switch (var2) {
         case 1:
            this.j.displayGuiScreen(this.guiScreen);
            ((CBModulesGui)this.guiScreen).currentScrollableElement = ((CBModulesGui)this.guiScreen).recoveredField796;
            break;
         case 28:
            if (this.recoveredField2910.getText().length() < 3) {
               this.recoveredField2908 = EnumChatFormatting.RED + "Name must be at least 3 characters long.";
            } else if (this.recoveredField2910.getText().equalsIgnoreCase("default")) {
               this.recoveredField2908 = EnumChatFormatting.RED + "That name is already in use.";
            } else if (!this.recoveredField2910.getText().matches("([a-zA-Z0-9-_ \\]\\[]+)")) {
               this.recoveredField2908 = EnumChatFormatting.RED + "Illegal characters in name.";
            } else if (this.profile != null && !this.profile.isEditable()) {
               File var7 = new File(
                  Minecraft.getMinecraft().mcDataDir,
                  "config"
                     + File.separator
                     + "cheatbreaker-client-"
                     + "1.8.9".replaceAll("\\.", "-")
                     + File.separator
                     + "profiles"
                     + File.separator
                     + this.profile.getName()
                     + ".cfg"
               );
               File var9 = new File(
                  Minecraft.getMinecraft().mcDataDir,
                  "config"
                     + File.separator
                     + "cheatbreaker-client-"
                     + "1.8.9".replaceAll("\\.", "-")
                     + File.separator
                     + "profiles"
                     + File.separator
                     + this.recoveredField2910.getText()
                     + ".cfg"
               );
               if (var7.exists()) {
                  try {
                     Files.copy(var7.toPath(), var9.toPath());
                     Files.delete(var7.toPath());
                     this.profile.setName(this.recoveredField2910.getText());
                     this.j.displayGuiScreen(this.guiScreen);
                     ((CBModulesGui)this.guiScreen).currentScrollableElement = ((CBModulesGui)this.guiScreen).recoveredField796;
                  } catch (Exception var6) {
                     this.recoveredField2908 = EnumChatFormatting.RED + "Could not save profile.";
                     var6.printStackTrace();
                  }
               }
            } else {
               Profile var3 = null;

               for (Profile var5 : CheatBreaker.getInstance().getConfigManager().recoveredField3785) {
                  if (var5.getName().toLowerCase().equalsIgnoreCase(this.recoveredField2910.getText())) {
                     var3 = var5;
                     break;
                  }
               }

               if (var3 == null) {
                  CheatBreaker.getInstance().configManager.method_25108(CheatBreaker.getInstance().getConfigManager().recoveredField3784.getName());
                  Profile var8 = new Profile(this.recoveredField2910.getText(), false);
                  CheatBreaker.getInstance().getConfigManager().recoveredField3785.add(var8);
                  CheatBreaker.getInstance().getConfigManager().recoveredField3784 = var8;
                  this.parent.recoveredField3359.add(new ProfileElement(this.parent, this.recoveredField2909, var8, this.recoveredField2906));
                  CheatBreaker.getInstance().configManager.method_25108(CheatBreaker.getInstance().getConfigManager().recoveredField3784.getName());
                  this.j.displayGuiScreen(this.guiScreen);
                  ((CBModulesGui)this.guiScreen).currentScrollableElement = ((CBModulesGui)this.guiScreen).recoveredField796;
               } else {
                  this.recoveredField2908 = EnumChatFormatting.RED + "That name is already in use.";
               }
            }
            break;
         default:
            this.recoveredField2910.textboxKeyTyped(var1, var2);
      }
   }

   @Override
   public void a_() {
      Keyboard.enableRepeatEvents(false);
   }

   @Override
   public void mouseReleased(int var1, int var2, int var3) {
      super.mouseReleased(var1, var2, var3);
   }

   @Override
   public void updateScreen() {
      this.recoveredField2910.updateCursorCounter();
   }

   public CBProfileCreateGui(Profile var1, GuiScreen var2, ProfilesListElement var3, int var4, float var5) {
      this(var2, var3, var4, var5);
      this.profile = var1;
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) throws java.io.IOException {
      super.mouseClicked(var1, var2, var3);
      this.recoveredField2910.mouseClicked(var1, var2, var3);
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.guiScreen.drawScreen(var1, var2, var3);
      this.drawDefaultBackground();
      a(this.l / 2 - 73, this.m / 2 - 19, this.l / 2 + 73, this.m / 2 + 8, -11250604);
      a(this.l / 2 - 72, this.m / 2 - 18, this.l / 2 + 72, this.m / 2 + 7, -3881788);
      GL11.glPushMatrix();
      GL11.glScalef(this.recoveredField2906, this.recoveredField2906, this.recoveredField2906);
      int var4 = (int)(this.l / this.recoveredField2906);
      int var5 = (int)(this.m / this.recoveredField2906);
      float var10002 = var4 / 2 - 70.0F / this.recoveredField2906;
      float var10003 = var5 / 2 - 17.0F / this.recoveredField2906;
      CheatBreaker.getInstance().recoveredField1589.drawString("Profile Name: ", var10002, var10003, 1862270976);
      var10002 = var4 / 2 - 72.0F / this.recoveredField2906;
      var10003 = var5 / 2 + 8.0F / this.recoveredField2906;
      CheatBreaker.getInstance().recoveredField1589.drawString(this.recoveredField2908, var10002, var10003, -1358954496);
      GL11.glPopMatrix();
      this.recoveredField2910.drawTextBox();
   }

   public CBProfileCreateGui(GuiScreen var1, ProfilesListElement var2, int var3, float var4) {
      this.recoveredField2908 = "";
      this.recoveredField2907 = false;
      this.guiScreen = var1;
      this.recoveredField2906 = var4;
      this.parent = var2;
      this.recoveredField2909 = var3;
      this.recoveredField2907 = true;
   }

   @Override
   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      if (!this.recoveredField2907) {
         this.j.displayGuiScreen(this.guiScreen);
         ((CBModulesGui)this.guiScreen).currentScrollableElement = ((CBModulesGui)this.guiScreen).recoveredField796;
      } else {
         this.recoveredField2907 = false;
         this.recoveredField2910 = new GuiTextField(299, this.j.fontRendererObj, this.l / 2 - 70, this.m / 2 - 6, 140, 10);
         if (this.profile != null) {
            this.recoveredField2910.setText(this.profile.getName());
         }

         this.recoveredField2910.setFocused(true);
      }
   }
}
