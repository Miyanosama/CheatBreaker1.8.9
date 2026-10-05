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
import net.minecraft.block.BlockDispenser;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces$Feature;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass1221;
import recovered.unidentified.UnidentifiedClass5100;

public class ProfileElement extends AbstractModulesGuiElement {
   public ComponentScatteredFeaturePieces$Feature field_0006;
   public int field_0008 = 0;
   public BlockDispenser field_0005;
   public AbstractScrollableElement field_0009;
   public ResourceLocation field_0011;
   public Profile field_0002;
   public ContainerBrewingStand field_0004;
   public ResourceLocation field_0007 = new ResourceLocation("client/icons/delete-64.png");
   public UnidentifiedClass1221 field_0010;
   public int field_0001;
   public ResourceLocation field_0000;
   public ResourceLocation field_0003;

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      boolean var7 = var1 > this.x + 12 && this.isMouseInside(var1, var2);
      byte var8 = 75;
      Gui.a(
         this.x,
         this.y + this.height - 1,
         this.x + this.width,
         this.y + this.height,
         GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0035 : UnidentifiedClass5100.field_0026
      );
      if (var7) {
         if (this.field_0008 < var8) {
            float var6 = CBModulesGui.getSmoothFloat(790.0F);
            this.field_0008 = (int)(this.field_0008 + var6);
            if (this.field_0008 > var8) {
               this.field_0008 = var8;
            }
         }
      } else if (this.field_0008 > 0) {
         float var16 = CBModulesGui.getSmoothFloat(790.0F);
         this.field_0008 = this.field_0008 - var16 < 0.0F ? 0 : (int)(this.field_0008 - var16);
      }

      if (this.field_0008 > 0) {
         float var17 = (float)this.field_0008 / var8 * 100.0F;
         Gui.a(
            this.x + 12,
            (int)(this.y + (this.height - this.height * var17 / 100.0F)),
            this.x + this.width - (this.field_0002.isEditable() ? 0 : 30),
            this.y + this.height,
            this.field_0001
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
      float var11 = GlobalSettings.field_0099.method_08908() ? 1.0F : 0.0F;
      GL11.glColor4f(var11, var11, var11, 0.35F);
      float var12 = 2.5F;
      if (!this.field_0002.isEditable()) {
         boolean var5 = false;
         boolean var4 = false;
         ProfilesListElement var13 = (ProfilesListElement)this.field_0009;
         if (var13.field_0000.indexOf(this) != 0 && var13.field_0000.indexOf(this) > 1) {
            var5 = true;
            GL11.glPushMatrix();
            if (var9) {
               GL11.glColor4f(var11, var11, var11, 0.65F);
            }

            GL11.glTranslatef(this.x + 6 - var12, this.y + 7.0F, 0.0F);
            GL11.glRotatef(-90.0F, 0.0F, 0.0F, 1.0F);
            RenderUtil.drawIcon(this.field_0000, var12, -1.0F, 0.0F);
            GL11.glPopMatrix();
            GL11.glColor4f(var11, var11, var11, 0.35F);
         }

         if (var13.field_0000.indexOf(this) != var13.field_0000.size() - 1) {
            var4 = true;
            GL11.glPushMatrix();
            if (var10) {
               GL11.glColor4f(0.0F, 0.0F, 0.0F, 0.65F);
            }

            GL11.glTranslatef(this.x + 6 + var12, this.y + 7.0F, 0.0F);
            GL11.glRotatef(90.0F, 0.0F, 0.0F, 1.0F);
            RenderUtil.drawIcon(this.field_0000, var12, 2.0F, 0.0F);
            GL11.glPopMatrix();
         }

         if (!var5 && !var4) {
            RenderUtil.drawIcon(this.field_0000, 2.5F, this.x + 4, this.y + 6.0F);
         }
      } else {
         RenderUtil.drawIcon(this.field_0000, 2.5F, this.x + 4, this.y + 6.0F);
      }

      if (CheatBreaker.getInstance().getConfigManager().field_0009 == this.field_0002) {
         CheatBreaker.getInstance()
            .field_0039
            .drawString(
               this.field_0002.getName().toUpperCase(),
               this.x + 16.0F,
               this.y + 3,
               GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0005 : UnidentifiedClass5100.field_0024
            );
      } else {
         CheatBreaker.getInstance()
            .field_0036
            .drawString(
               this.field_0002.getName().toUpperCase(),
               this.x + 16.0F,
               this.y + 3.5F,
               GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0005 : UnidentifiedClass5100.field_0024
            );
      }

      if (CheatBreaker.getInstance().getConfigManager().field_0009 == this.field_0002) {
         CheatBreaker.getInstance()
            .playRegular14px
            .drawString(
               " (Active)",
               this.x + 17.0F + CheatBreaker.getInstance().field_0039.getStringWidth(this.field_0002.getName().toUpperCase()),
               this.y + 4.0F,
               GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0028 : UnidentifiedClass5100.field_0004
            );
      }

      if (!this.field_0002.isEditable()) {
         boolean var15 = var1 > (this.x + this.width - 30) * this.scale
            && var1 < (this.x + this.width - 13) * this.scale
            && var2 > (this.y + this.yOffset) * this.scale
            && var2 < (this.y + this.height + this.yOffset) * this.scale;
         GL11.glColor4f(var15 ? 0.0F : 0.25F, var15 ? 0.0F : 0.25F, var15 ? 0.5F : 0.25F, 0.65F);
         RenderUtil.drawIcon(this.field_0003, 5.0F, this.x + this.width - 26, this.y + 3.5F);
         boolean var14 = var1 > (this.x + this.width - 17) * this.scale
            && var1 < (this.x + this.width - 2) * this.scale
            && var2 > (this.y + this.yOffset) * this.scale
            && var2 < (this.y + this.height + this.yOffset) * this.scale;
         GL11.glColor4f(var14 ? 0.8F : 0.25F, var14 ? 0.0F : 0.25F, var14 ? 0.0F : 0.25F, 0.65F);
         RenderUtil.drawIcon(this.field_0007, 5.0F, this.x + this.width - 13, this.y + 3.5F);
      }
   }

   public ProfileElement(AbstractScrollableElement var1, int var2, Profile var3, float var4) {
      super(var4);
      this.field_0011 = new ResourceLocation("client/icons/checkmark-64.png");
      this.field_0000 = new ResourceLocation("client/icons/right.png");
      this.field_0003 = new ResourceLocation("client/icons/pencil-64.png");
      this.field_0009 = var1;
      this.field_0001 = var2;
      this.field_0002 = var3;
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
      ProfilesListElement var8 = (ProfilesListElement)this.field_0009;
      if (this.field_0002.isEditable() || !var6 && !var7) {
         if (!this.field_0002.isEditable() && var4) {
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            if (CheatBreaker.getInstance().getConfigManager().field_0009 == this.field_0002) {
               CheatBreaker.getInstance().getConfigManager().field_0009 = CheatBreaker.getInstance().getConfigManager().field_0006.get(0);
               CheatBreaker.getInstance().configManager.method_25103(CheatBreaker.getInstance().getConfigManager().field_0009.getName());
               CheatBreaker.getInstance().getModuleManager().keyStrokes.initialize();
               Minecraft.getMinecraft().ingameGUI.getChatGUI().refreshChat();
            }

            if (!this.field_0002.isEditable()) {
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
                  : new File(var9 + File.separator + this.field_0002.getName().toLowerCase() + ".cfg");
               File var10 = var10000;
               if (var10000.exists() && var10.delete()) {
                  CheatBreaker.getInstance().getConfigManager().field_0006.removeIf(var1x -> var1x == this.field_0002);
                  var8.field_0000.removeIf(var1x -> var1x == this);
               }
            }
         } else if (!this.field_0002.isEditable() && var5) {
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            Minecraft.getMinecraft()
               .displayGuiScreen(
                  new CBProfileCreateGui(this.field_0002, CBModulesGui.instance, (ProfilesListElement)this.field_0009, this.field_0001, this.scale)
               );
         } else if (CheatBreaker.getInstance().getConfigManager().field_0009 != this.field_0002) {
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            CheatBreaker.getInstance().configManager.method_25108(CheatBreaker.getInstance().getConfigManager().field_0009.getName());
            CheatBreaker.getInstance().getConfigManager().field_0009 = this.field_0002;
            CheatBreaker.getInstance().configManager.method_25103(CheatBreaker.getInstance().getConfigManager().field_0009.getName());
            CheatBreaker.getInstance().getModuleManager().keyStrokes.initialize();
            Minecraft.getMinecraft().ingameGUI.getChatGUI().refreshChat();
         }
      } else {
         if (var6
            && ((ProfilesListElement)this.field_0009).field_0000.indexOf(this) != 0
            && ((ProfilesListElement)this.field_0009).field_0000.indexOf(this) > 1) {
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            this.field_0002.index = var8.field_0000.indexOf(this) - 1;
            var8.field_0000.get(var8.field_0000.indexOf(this) - 1).field_0002.index = var8.field_0000.indexOf(this);
            Collections.swap(var8.field_0000, var8.field_0000.indexOf(this), var8.field_0000.indexOf(this) - 1);
         }

         if (var7 && var8.field_0000.indexOf(this) != var8.field_0000.size() - 1) {
            Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
            this.field_0002.index = var8.field_0000.indexOf(this) + 1;
            var8.field_0000.get(var8.field_0000.indexOf(this) + 1).field_0002.index = var8.field_0000.indexOf(this);
            Collections.swap(var8.field_0000, var8.field_0000.indexOf(this), var8.field_0000.indexOf(this) + 1);
         }
      }
   }
}
