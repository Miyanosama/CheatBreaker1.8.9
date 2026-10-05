package com.cheatbreaker.client.ui.module;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Profile;
import com.cheatbreaker.client.ui.element.ProfileElement;
import com.cheatbreaker.client.ui.element.profile.ProfilesListElement;
import java.io.File;
import java.nio.file.Files;
import javax.vecmath.Tuple4d;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Minecraft$14;
import net.minecraft.client.audio.MusicTicker$MusicType;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.StringUtils;
import org.apache.log4j.helpers.PatternParser$BasicPatternConverter;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class CBProfileCreateGui extends GuiScreen {
   public StringUtils field_0005;
   public Profile profile;
   public float field_0004;
   public boolean field_0009;
   public Minecraft$14 field_0001;
   public PatternParser$BasicPatternConverter field_0002;
   public Tuple4d field_0011;
   public ProfilesListElement parent;
   public String field_0003;
   public GuiScreen guiScreen;
   public int field_0000;
   public MusicTicker$MusicType field_0006;
   public GuiTextField field_0007 = null;

   @Override
   public void keyTyped(char var1, int var2) {
      switch (var2) {
         case 1:
            this.j.displayGuiScreen(this.guiScreen);
            ((CBModulesGui)this.guiScreen).currentScrollableElement = ((CBModulesGui)this.guiScreen).field_0018;
            break;
         case 28:
            if (this.field_0007.getText().length() < 3) {
               this.field_0003 = EnumChatFormatting.RED + "Name must be at least 3 characters long.";
            } else if (this.field_0007.getText().equalsIgnoreCase("default")) {
               this.field_0003 = EnumChatFormatting.RED + "That name is already in use.";
            } else if (!this.field_0007.getText().matches("([a-zA-Z0-9-_ \\]\\[]+)")) {
               this.field_0003 = EnumChatFormatting.RED + "Illegal characters in name.";
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
                     + this.field_0007.getText()
                     + ".cfg"
               );
               if (var7.exists()) {
                  try {
                     Files.copy(var7.toPath(), var9.toPath());
                     Files.delete(var7.toPath());
                     this.profile.setName(this.field_0007.getText());
                     this.j.displayGuiScreen(this.guiScreen);
                     ((CBModulesGui)this.guiScreen).currentScrollableElement = ((CBModulesGui)this.guiScreen).field_0018;
                  } catch (Exception var6) {
                     this.field_0003 = EnumChatFormatting.RED + "Could not save profile.";
                     var6.printStackTrace();
                  }
               }
            } else {
               Profile var3 = null;

               for (Profile var5 : CheatBreaker.getInstance().getConfigManager().field_0006) {
                  if (var5.getName().toLowerCase().equalsIgnoreCase(this.field_0007.getText())) {
                     var3 = var5;
                     break;
                  }
               }

               if (var3 == null) {
                  CheatBreaker.getInstance().configManager.method_25108(CheatBreaker.getInstance().getConfigManager().field_0009.getName());
                  Profile var8 = new Profile(this.field_0007.getText(), false);
                  CheatBreaker.getInstance().getConfigManager().field_0006.add(var8);
                  CheatBreaker.getInstance().getConfigManager().field_0009 = var8;
                  this.parent.field_0000.add(new ProfileElement(this.parent, this.field_0000, var8, this.field_0004));
                  CheatBreaker.getInstance().configManager.method_25108(CheatBreaker.getInstance().getConfigManager().field_0009.getName());
                  this.j.displayGuiScreen(this.guiScreen);
                  ((CBModulesGui)this.guiScreen).currentScrollableElement = ((CBModulesGui)this.guiScreen).field_0018;
               } else {
                  this.field_0003 = EnumChatFormatting.RED + "That name is already in use.";
               }
            }
            break;
         default:
            this.field_0007.textboxKeyTyped(var1, var2);
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
      this.field_0007.updateCursorCounter();
   }

   public CBProfileCreateGui(Profile var1, GuiScreen var2, ProfilesListElement var3, int var4, float var5) {
      this(var2, var3, var4, var5);
      this.profile = var1;
   }

   @Override
   public void mouseClicked(int var1, int var2, int var3) {
      super.mouseClicked(var1, var2, var3);
      this.field_0007.mouseClicked(var1, var2, var3);
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      this.guiScreen.drawScreen(var1, var2, var3);
      this.drawDefaultBackground();
      a(this.l / 2 - 73, this.m / 2 - 19, this.l / 2 + 73, this.m / 2 + 8, -11250604);
      a(this.l / 2 - 72, this.m / 2 - 18, this.l / 2 + 72, this.m / 2 + 7, -3881788);
      GL11.glPushMatrix();
      GL11.glScalef(this.field_0004, this.field_0004, this.field_0004);
      int var4 = (int)(this.l / this.field_0004);
      int var5 = (int)(this.m / this.field_0004);
      float var10002 = var4 / 2 - 70.0F / this.field_0004;
      float var10003 = var5 / 2 - 17.0F / this.field_0004;
      CheatBreaker.getInstance().field_0068.drawString("Profile Name: ", var10002, var10003, 1862270976);
      var10002 = var4 / 2 - 72.0F / this.field_0004;
      var10003 = var5 / 2 + 8.0F / this.field_0004;
      CheatBreaker.getInstance().field_0068.drawString(this.field_0003, var10002, var10003, -1358954496);
      GL11.glPopMatrix();
      this.field_0007.drawTextBox();
   }

   public CBProfileCreateGui(GuiScreen var1, ProfilesListElement var2, int var3, float var4) {
      this.field_0003 = "";
      this.field_0009 = false;
      this.guiScreen = var1;
      this.field_0004 = var4;
      this.parent = var2;
      this.field_0000 = var3;
      this.field_0009 = true;
   }

   @Override
   public void initGui() {
      Keyboard.enableRepeatEvents(true);
      if (!this.field_0009) {
         this.j.displayGuiScreen(this.guiScreen);
         ((CBModulesGui)this.guiScreen).currentScrollableElement = ((CBModulesGui)this.guiScreen).field_0018;
      } else {
         this.field_0009 = false;
         this.field_0007 = new GuiTextField(299, this.j.fontRendererObj, this.l / 2 - 70, this.m / 2 - 6, 140, 10);
         if (this.profile != null) {
            this.field_0007.setText(this.profile.getName());
         }

         this.field_0007.setFocused(true);
      }
   }
}
