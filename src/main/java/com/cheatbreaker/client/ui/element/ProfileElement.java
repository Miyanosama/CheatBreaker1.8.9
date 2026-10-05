package com.cheatbreaker.client.ui.element;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.config.Profile;
import com.cheatbreaker.client.ui.element.profile.ProfilesListElement;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.module.CBProfileCreateGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import java.io.File;
import java.util.Collections;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.ui.util.GuiThemeColors;

public class ProfileElement extends AbstractModulesGuiElement {
   public int recoveredField1348 = 0;
   public AbstractScrollableElement recoveredField1349;
   public ResourceLocation recoveredField1350;
   public Profile recoveredField1351;
   public ResourceLocation recoveredField1352 = new ResourceLocation("client/icons/delete-64.png");
   public int recoveredField1353;
   public ResourceLocation recoveredField1354;
   public ResourceLocation recoveredField1355;

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      boolean var7 = var1 > this.x + 12 && this.isMouseInside(var1, var2);
      byte var8 = 75;
      Gui.a(
         this.x,
         this.y + this.height - 1,
         this.x + this.width,
         this.y + this.height,
         GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1652 : GuiThemeColors.recoveredField1674
      );
      if (var7) {
         if (this.recoveredField1348 < var8) {
            float var6 = CBModulesGui.getSmoothFloat(790.0F);
            this.recoveredField1348 = (int)(this.recoveredField1348 + var6);
            if (this.recoveredField1348 > var8) {
               this.recoveredField1348 = var8;
            }
         }
      } else if (this.recoveredField1348 > 0) {
         float var16 = CBModulesGui.getSmoothFloat(790.0F);
         this.recoveredField1348 = this.recoveredField1348 - var16 < 0.0F ? 0 : (int)(this.recoveredField1348 - var16);
      }

      if (this.recoveredField1348 > 0) {
         float var17 = (float)this.recoveredField1348 / var8 * 100.0F;
         Gui.a(
            this.x + 12,
            (int)(this.y + (this.height - this.height * var17 / 100.0F)),
            this.x + this.width - (this.recoveredField1351.isEditable() ? 0 : 30),
            this.y + this.height,
            this.recoveredField1353
         );
      }

      boolean var9 = var1 > this.x * this.scale
         && var1 < (this.x + 12) * this.scale
         && var2 >= (this.y + this.yOffset) * this.scale
         && var2 <= (this.y + this.height / 2 + this.yOffset) * this.scale;
      boolean var10 = var1 > this.x * this.scale
         && var1 < (this.x + 12) * this.scale
         && var2 > (this.y + this.height / 2 + this.yOffset) * this.scale
         && var2 < (this.y + this.height + this.yOffset) * this.scale;
      float var11 = GlobalSettings.recoveredField529.method_08908() ? 1.0F : 0.0F;
      GL11.glColor4f(var11, var11, var11, 0.35F);
      float var12 = 2.5F;
      if (!this.recoveredField1351.isEditable()) {
         boolean var5 = false;
         boolean var4 = false;
         ProfilesListElement var13 = (ProfilesListElement)this.recoveredField1349;
         if (var13.recoveredField3359.indexOf(this) != 0 && var13.recoveredField3359.indexOf(this) > 1) {
            var5 = true;
            GL11.glPushMatrix();
            if (var9) {
               GL11.glColor4f(var11, var11, var11, 0.65F);
            }

            GL11.glTranslatef(this.x + 6 - var12, this.y + 7.0F, 0.0F);
            GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
            RenderUtil.drawIcon(this.recoveredField1354, var12, -1.0F, 0.0F);
            GL11.glPopMatrix();
            GL11.glColor4f(var11, var11, var11, 0.35F);
         }

         if (var13.recoveredField3359.indexOf(this) != var13.recoveredField3359.size() - 1) {
            var4 = true;
            GL11.glPushMatrix();
            if (var10) {
               GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.65F);
            }

            GL11.glTranslatef(this.x + 6 + var12, this.y + 7.0F, 0.0F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            RenderUtil.drawIcon(this.recoveredField1354, var12, 2.0F, 0.0F);
            GL11.glPopMatrix();
         }

         if (!var5 && !var4) {
            RenderUtil.drawIcon(this.recoveredField1354, 2.5F, this.x + 4, this.y + 6.0F);
         }
      } else {
         RenderUtil.drawIcon(this.recoveredField1354, 2.5F, this.x + 4, this.y + 6.0F);
      }

      if (CheatBreaker.getInstance().getConfigManager().recoveredField3784 == this.recoveredField1351) {
         CheatBreaker.getInstance()
            .recoveredField1595
            .drawString(
               this.recoveredField1351.getName().toUpperCase(),
               this.x + 16.0F,
               this.y + 3,
               GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1667 : GuiThemeColors.recoveredField1670
            );
      } else {
         CheatBreaker.getInstance()
            .recoveredField1548
            .drawString(
               this.recoveredField1351.getName().toUpperCase(),
               this.x + 16.0F,
               this.y + 3.5F,
               GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1667 : GuiThemeColors.recoveredField1670
            );
      }

      if (CheatBreaker.getInstance().getConfigManager().recoveredField3784 == this.recoveredField1351) {
         CheatBreaker.getInstance()
            .playRegular14px
            .drawString(
               " (Active)",
               this.x + 17.0F + CheatBreaker.getInstance().recoveredField1595.getStringWidth(this.recoveredField1351.getName().toUpperCase()),
               this.y + 4.0F,
               GlobalSettings.recoveredField529.method_08908() ? GuiThemeColors.recoveredField1671 : GuiThemeColors.recoveredField1661
            );
      }

      if (!this.recoveredField1351.isEditable()) {
         boolean var15 = var1 > (this.x + this.width - 30) * this.scale
            && var1 < (this.x + this.width - 13) * this.scale
            && var2 > (this.y + this.yOffset) * this.scale
            && var2 < (this.y + this.height + this.yOffset) * this.scale;
         GL11.glColor4f(var15 ? 0.0F : 0.25F, var15 ? 0.0F : 0.25F, var15 ? 0.5F : 0.25F, 0.65F);
         RenderUtil.drawIcon(this.recoveredField1355, 5.0F, this.x + this.width - 26, this.y + 3.5F);
         boolean var14 = var1 > (this.x + this.width - 17) * this.scale
            && var1 < (this.x + this.width - 2) * this.scale
            && var2 > (this.y + this.yOffset) * this.scale
            && var2 < (this.y + this.height + this.yOffset) * this.scale;
         GL11.glColor4f(var14 ? 0.8F : 0.25F, var14 ? 0.0F : 0.25F, var14 ? 0.0F : 0.25F, 0.65F);
         RenderUtil.drawIcon(this.recoveredField1352, 5.0F, this.x + this.width - 13, this.y + 3.5F);
      }
   }

   public ProfileElement(AbstractScrollableElement var1, int var2, Profile var3, float var4) {
      super(var4);
      this.recoveredField1350 = new ResourceLocation("client/icons/checkmark-64.png");
      this.recoveredField1354 = new ResourceLocation("client/icons/right.png");
      this.recoveredField1355 = new ResourceLocation("client/icons/pencil-64.png");
      this.recoveredField1349 = var1;
      this.recoveredField1353 = var2;
      this.recoveredField1351 = var3;
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
      boolean var4 = var1 > (this.x + this.width - 17) * this.scale
         && var1 < (this.x + this.width - 2) * this.scale
         && var2 > (this.y + this.yOffset) * this.scale
         && var2 < (this.y + this.height + this.yOffset) * this.scale;
      boolean var5 = var1 > (this.x + this.width - 30) * this.scale
         && var1 < (this.x + this.width - 13) * this.scale
         && var2 > (this.y + this.yOffset) * this.scale
         && var2 < (this.y + this.height + this.yOffset) * this.scale;
      boolean var6 = var1 > this.x * this.scale
         && var1 < (this.x + 12) * this.scale
         && var2 >= (this.y + this.yOffset) * this.scale
         && var2 <= (this.y + this.height / 2 + this.yOffset) * this.scale;
      boolean var7 = var1 > this.x * this.scale
         && var1 < (this.x + 12) * this.scale
         && var2 > (this.y + this.height / 2 + this.yOffset) * this.scale
         && var2 < (this.y + this.height + this.yOffset) * this.scale;
      ProfilesListElement var8 = (ProfilesListElement)this.recoveredField1349;
      if (this.recoveredField1351.isEditable() || !var6 && !var7) {
         if (!this.recoveredField1351.isEditable() && var4) {
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            if (CheatBreaker.getInstance().getConfigManager().recoveredField3784 == this.recoveredField1351) {
               CheatBreaker.getInstance().getConfigManager().recoveredField3784 = CheatBreaker.getInstance().getConfigManager().recoveredField3785.get(0);
               CheatBreaker.getInstance().configManager.method_25103(CheatBreaker.getInstance().getConfigManager().recoveredField3784.getName());
               CheatBreaker.getInstance().getModuleManager().keyStrokes.initialize();
               Minecraft.getMinecraft().ingameGUI.getChatGUI().refreshChat();
            }

            if (!this.recoveredField1351.isEditable()) {
               File var9;
               File var10000 = !(var9 = new File(
                           Minecraft.getMinecraft().mcDataDir
                              + File.separator
                              + "config"
                              + File.separator
                              + "cheatbreaker-client-"
                              + "1.8.9".replaceAll("\\.", "-")
                              + File.separator
                              + "profiles"
                        ))
                        .exists()
                     && !var9.mkdirs()
                  ? null
                  : new File(var9 + File.separator + this.recoveredField1351.getName().toLowerCase() + ".cfg");
               File var10 = var10000;
               if (var10000.exists() && var10.delete()) {
                  CheatBreaker.getInstance().getConfigManager().recoveredField3785.removeIf(var1x -> var1x == this.recoveredField1351);
                  var8.recoveredField3359.removeIf(var1x -> var1x == this);
               }
            }
         } else if (!this.recoveredField1351.isEditable() && var5) {
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            Minecraft.getMinecraft()
               .displayGuiScreen(
                  new CBProfileCreateGui(
                     this.recoveredField1351, CBModulesGui.instance, (ProfilesListElement)this.recoveredField1349, this.recoveredField1353, this.scale
                  )
               );
         } else if (CheatBreaker.getInstance().getConfigManager().recoveredField3784 != this.recoveredField1351) {
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            CheatBreaker.getInstance().configManager.method_25108(CheatBreaker.getInstance().getConfigManager().recoveredField3784.getName());
            CheatBreaker.getInstance().getConfigManager().recoveredField3784 = this.recoveredField1351;
            CheatBreaker.getInstance().configManager.method_25103(CheatBreaker.getInstance().getConfigManager().recoveredField3784.getName());
            CheatBreaker.getInstance().getModuleManager().keyStrokes.initialize();
            Minecraft.getMinecraft().ingameGUI.getChatGUI().refreshChat();
         }
      } else {
         if (var6
            && ((ProfilesListElement)this.recoveredField1349).recoveredField3359.indexOf(this) != 0
            && ((ProfilesListElement)this.recoveredField1349).recoveredField3359.indexOf(this) > 1) {
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            this.recoveredField1351.index = var8.recoveredField3359.indexOf(this) - 1;
            var8.recoveredField3359.get(var8.recoveredField3359.indexOf(this) - 1).recoveredField1351.index = var8.recoveredField3359.indexOf(this);
            Collections.swap(var8.recoveredField3359, var8.recoveredField3359.indexOf(this), var8.recoveredField3359.indexOf(this) - 1);
         }

         if (var7 && var8.recoveredField3359.indexOf(this) != var8.recoveredField3359.size() - 1) {
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            this.recoveredField1351.index = var8.recoveredField3359.indexOf(this) + 1;
            var8.recoveredField3359.get(var8.recoveredField3359.indexOf(this) + 1).recoveredField1351.index = var8.recoveredField3359.indexOf(this);
            Collections.swap(var8.recoveredField3359, var8.recoveredField3359.indexOf(this), var8.recoveredField3359.indexOf(this) + 1);
         }
      }
   }
}
