package com.cheatbreaker.client.ui.element.module;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.GlobalSettings;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.Setting$Type;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.module.AbstractModule$PreviewType;
import com.cheatbreaker.client.module.type.cooldowns.CooldownRenderer;
import com.cheatbreaker.client.ui.element.AbstractModulesGuiElement;
import com.cheatbreaker.client.ui.element.AbstractScrollableElement;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.cheatbreaker.client.ui.util.font.CBFontRenderer;
import io.netty.handler.codec.compression.JdkZlibEncoder$2;
import io.netty.handler.codec.spdy.SpdyHeaderBlockEncoder;
import java.util.Objects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.client.gui.Gui;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.layer.GenLayerZoom;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Corridor3;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass0105;
import recovered.unidentified.UnidentifiedClass5100;

public class ModulePreviewElement extends AbstractModulesGuiElement {
   public AbstractScrollableElement field_0005;
   public ModulesGuiButtonElement field_0007;
   public GenLayerZoom field_0004;
   public boolean field_0008 = CheatBreaker.getInstance().getGlobalSettings().field_0119.getValue().equals("Compact");
   public static ModulePreviewElement field_0010;
   public ModulesGuiButtonElement field_0002;
   public AbstractModule field_0003;
   public SpdyHeaderBlockEncoder field_0006;
   public StructureNetherBridgePieces$Corridor3 field_0009;
   public JdkZlibEncoder$2 field_0001;
   public ModulesGuiButtonElement field_0000;

   @Override
   public void handleDrawElement(int var1, int var2, float var3) {
      if (this.field_0003.isEnabled()) {
         int var6 = GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0002 : UnidentifiedClass5100.field_0033;
         if (this.field_0003.method_28796()) {
            var6 = -5859328;
         }

         RenderUtil.method_22054(this.x, this.y, this.x + this.width, this.field_0008 ? this.y + 16 : this.y + this.height, 3.0, var6);
      } else {
         RenderUtil.method_22054(
            this.x,
            this.y,
            this.x + this.width,
            this.field_0008 ? this.y + 16 : this.y + this.height,
            3.0,
            GlobalSettings.field_0099.method_08908() ? UnidentifiedClass5100.field_0020 : UnidentifiedClass5100.field_0038
         );
      }

      CBFontRenderer var20 = CheatBreaker.getInstance().field_0039;
      GL11.glPushMatrix();
      byte var7 = 0;
      byte var8 = 0;
      if (!this.field_0008) {
         if (this.field_0003 == CheatBreaker.getInstance().getModuleManager().armourStatus) {
            var7 = -10;
            String var5 = "329/329";
            float var4 = Minecraft.getMinecraft().fontRendererObj.getStringWidth(var5);
            float var10002 = (int)(this.x + 1 + this.width / 2 - var4 / 2.0F);
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(var5, var10002, this.y + this.height / 2 - 18, -1);
         } else if (this.field_0003 == CheatBreaker.getInstance().getModuleManager().potionStatus) {
            var8 = -30;
            float var35 = this.x + 8 + this.width / 2 - 20;
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("Speed II", var35, this.y + this.height / 2 - 36, -1);
            var35 = this.x + 8 + this.width / 2 - 20;
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("0:42", var35, this.y + this.height / 2 - 26, -1);
         } else if (this.field_0003 == CheatBreaker.getInstance().getModuleManager().scoreboard) {
            Gui.a(this.x + 20, this.y + this.height / 2 - 44, this.x + this.width - 20, this.y + this.height / 2 - 6, 1862270976);
            int var37 = this.x + this.width / 2;
            Minecraft.getMinecraft().fontRendererObj.method_08776("Score", var37, this.y + this.height / 2 - 40, -1);
            float var38 = this.x + 24;
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("Steve", var38, this.y + this.height / 2 - 28, -1);
            float var39 = this.x + 24;
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow("Alex", var39, this.y + this.height / 2 - 18, -1);
            Minecraft.getMinecraft().fontRendererObj.method_08776(EnumChatFormatting.RED + "0", this.x + this.width - 26, this.y + this.height / 2 - 18, -1);
            Minecraft.getMinecraft().fontRendererObj.method_08776(EnumChatFormatting.RED + "1", this.x + this.width - 26, this.y + this.height / 2 - 28, -1);
         } else if (this.field_0003 == CheatBreaker.getInstance().getModuleManager().field_0048) {
            var7 = 3;
            String var18 = "Wither";
            float var14 = Minecraft.getMinecraft().fontRendererObj.getStringWidth(var18);
            float var40 = (int)(this.x + 1 + this.width / 2 - var14 / 2.0F);
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(var18, var40, this.y + this.height / 2 - 36, -1);
         } else if (this.field_0003 == CheatBreaker.getInstance().getModuleManager().chatModule) {
            Gui.a(this.x + 10, this.y + this.height / 2 - 35, this.x + this.width - 10, this.y + this.height / 2 - 17, 1862270976);
            Minecraft.getMinecraft()
               .fontRendererObj
               .drawStringWithShadow(EnumChatFormatting.GREEN + "Steve" + EnumChatFormatting.WHITE + ": Hey.", this.x + 10, this.y + this.height / 2 - 34, -1);
            Minecraft.getMinecraft()
               .fontRendererObj
               .drawStringWithShadow(EnumChatFormatting.GREEN + "Alex" + EnumChatFormatting.WHITE + ": Hi!", this.x + 10, this.y + this.height / 2 - 25, -1);
         } else if (this.field_0003 == CheatBreaker.getInstance().getModuleManager().field_0020) {
            int var9 = Minecraft.getMinecraft().fontRendererObj.getStringWidth(Minecraft.getMinecraft().getSession().getUsername());
            String var10 = var9 > 94 ? "Steve" : Minecraft.getMinecraft().getSession().getUsername();
            var9 = Minecraft.getMinecraft().fontRendererObj.getStringWidth(var10);
            Gui.drawRect(
               this.x + this.width / 2 - var9 / 2 - 8,
               this.y + this.height / 2 - 33,
               this.x + this.width / 2 + var9 / 2 + 8,
               this.y + this.height / 2 - 24,
               1862270976
            );
            Minecraft.getMinecraft()
               .fontRendererObj
               .drawString(
                  var10,
                  this.x + this.width / 2 - Minecraft.getMinecraft().fontRendererObj.getStringWidth(var10) / 2 + 7,
                  this.y + this.height / 2 - 32,
                  -1,
                  false
               );
            RenderUtil.method_22064(
               new ResourceLocation("client/logo_white.png"), this.x + this.width / 2 - var9 / 2 - 7, this.y + this.height / 2 - 32, 13.0F, 7.0F
            );
         } else if (this.field_0003 == CheatBreaker.getInstance().getModuleManager().field_0016) {
            GL11.glScalef(2.0F, 2.0F, 0.0F);
            float var22 = 6.0F;
            float var29 = 4.0F;
            float var11 = 2.0F;
            float var12 = (this.x + this.width / 2) / 2.0F;
            float var13 = (this.y + this.height / 2 - 27) / 2.0F;
            Gui.drawBoxWithOutLine(var12 - var29 - var22, var13 - var11 / 2.0F, var12 - var29, var13 + var11 / 2.0F, 0.5F, -1358954496, -1);
            Gui.drawBoxWithOutLine(var12 + var29, var13 - var11 / 2.0F, var12 + var29 + var22, var13 + var11 / 2.0F, 0.5F, -1358954496, -1);
            Gui.drawBoxWithOutLine(var12 - var11 / 2.0F, var13 - var29 - var22, var12 + var11 / 2.0F, var13 - var29, 0.5F, -1358954496, -1);
            Gui.drawBoxWithOutLine(var12 - var11 / 2.0F, var13 + var29, var12 + var11 / 2.0F, var13 + var29 + var22, 0.5F, -1358954496, -1);
            GL11.glScalef(1.0F, 1.0F, 0.0F);
         } else if (this.field_0003 == CheatBreaker.getInstance().getModuleManager().field_0005) {
            float var23 = this.field_0003.getPreviewIconWidth();
            float var15 = this.field_0003.getPreviewIconHeight();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            RenderUtil.method_22064(
               CheatBreaker.getInstance().method_19810(Minecraft.getMinecraft().getSession().getUsername()),
               this.x + this.width / 2 - var23 / 2.0F + var8,
               this.y + var7 + this.height / 2 - 26 - var15 / 2.0F,
               var23,
               var15
            );
         }

         if (this.field_0003 == CheatBreaker.getInstance().getModuleManager().cooldowns) {
            CooldownRenderer var24 = new CooldownRenderer("EnderPearl", 368, 10565418L & 8554092235523253052L);
            float var41 = this.x + this.width / 2 - 18;
            var24.method_06971(CheatBreaker.getInstance().getModuleManager().cooldowns.field_0009, var41, this.y + this.height / 2 - 26 - 18, -1);
         } else if ((this.field_0003.getPreviewType() == null || this.field_0003.getPreviewType() == AbstractModule$PreviewType.LABEL)
            && this.field_0003 != CheatBreaker.getInstance().getModuleManager().scoreboard
            && this.field_0003 != CheatBreaker.getInstance().getModuleManager().chatModule
            && this.field_0003 != CheatBreaker.getInstance().getModuleManager().field_0020) {
            String var19 = "";
            float var17;
            if (this.field_0003.getPreviewType() == null) {
               var17 = 2.0F;

               for (String var33 : this.field_0003.getName().split(" ")) {
                  String var34 = var33.substring(0, 1);
                  var19 = var19 + (Objects.equals(var19, "") ? var34 : var34.toLowerCase());
               }
            } else {
               var17 = this.field_0003.getPreviewLabelSize();
               var19 = this.field_0003.getPreviewLabel();
            }

            GL11.glScalef(var17, var17, var17);
            float var27 = Minecraft.getMinecraft().fontRendererObj.getStringWidth(var19) * var17;
            if (this.field_0003.getPreviewType() == null && !this.field_0008) {
               int var43 = (int)((this.x + 1 + this.width / 2 - var27 / 2.0F) / var17);
               Minecraft.getMinecraft().fontRendererObj.drawString(var19, var43, (int)((this.y + this.height / 2 - 32) / var17), -13750738);
            } else {
               float var42 = (int)((this.x + 1 + this.width / 2 - var27 / 2.0F) / var17);
               Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(var19, var42, (int)((this.y + this.height / 2 - 32) / var17), -1);
            }
         } else if (this.field_0003.getPreviewType() == AbstractModule$PreviewType.ICON) {
            float var25 = this.field_0003.getPreviewIconWidth();
            float var16 = this.field_0003.getPreviewIconHeight();
            GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
            RenderUtil.method_22064(
               this.field_0003.getPreviewIcon(),
               this.x + this.width / 2 - var25 / 2.0F + var8,
               this.y + var7 + this.height / 2 - 26 - var16 / 2.0F,
               var25,
               var16
            );
         }
      }

      GL11.glPopMatrix();
      String var28 = this.field_0003.getName();
      byte var31 = 14;
      if (this.field_0008 && var28.replaceAll(" ", "").length() >= var31) {
         var28 = var28.substring(0, var31).trim() + "..";
      }

      if (this.field_0008) {
         var20.drawString(var28, this.x + 4.0F, this.y + 4, 1593835520);
         var20.drawString(var28, this.x + 3.0F, this.y + 3, -1);
      } else {
         var20.drawCenteredString(var28, this.x + this.width / 2 - 0.5F, this.y + this.height / 2 - 4 + 1, 1593835520);
         var20.drawCenteredString(var28, this.x + this.width / 2 - 1.5F, this.y + this.height / 2 - 4, -1);
      }

      if (!this.field_0008) {
         this.field_0000.displayString = this.field_0003.isEnabled() ? "Disable" : "Enable";
         this.field_0000.yOffset = this.yOffset;
         this.field_0000.method_07930(false);
         if (!this.field_0003.field_0020) {
            this.field_0000
               .setDimensions(
                  this.x + this.width / 2 + 8, this.y + this.height - 38, this.width / 2 - 12, this.y + this.height - 24 - (this.y + this.height - 38)
               );
            this.field_0000.handleDrawElement(var1, var2, var3);
         }
      }

      this.field_0007.displayString = this.field_0008
         ? (this.field_0003.isEnabled() ? "delete-64.png" : "plus-64.png")
         : (
            this.field_0003.getGuiAnchor() == null
               ? (this.field_0003.method_28866() && this.field_0003.isEnabled() ? "Disable" : "Enable")
               : (
                  this.field_0003.method_28866() && this.field_0003.isEnabled()
                     ? "Hide from HUD"
                     : (this.field_0003.method_28790() && !Keyboard.isKeyDown(42) ? "Return to HUD" : "Add to HUD")
               )
         );
      this.field_0007.field_0006 = this.field_0003.method_28866() && this.field_0003.isEnabled() ? -5756117 : -13916106;
      this.field_0007
         .setDimensions(
            this.x + (this.field_0008 ? 87 : 4),
            this.y + (this.field_0008 ? 3 : this.height - 38),
            this.field_0008 ? 10 : (this.field_0003.field_0020 ? this.width - 8 : this.width / 2 + 2),
            this.field_0008 ? 10 : this.y + this.height - 24 - (this.y + this.height - 38)
         );
      this.field_0007.yOffset = this.yOffset;
      this.field_0007.method_07930(this.field_0008);
      this.field_0007.handleDrawElement(var1, var2, var3);
      this.field_0002
         .setDimensions(
            this.x + (this.field_0008 ? 102 : 4),
            this.y + (this.field_0008 ? 3 : this.height - 20),
            this.field_0008 ? 10 : this.width - 8,
            this.field_0008 ? 10 : 16
         );
      this.field_0002.method_07930(this.field_0008);
      this.field_0002.yOffset = this.yOffset;
      this.field_0002.handleDrawElement(var1, var2, var3);
   }

   @Override
   public void handleMouseClick(int var1, int var2, int var3) {
      if (this.field_0002.isMouseInside(var1, var2)) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         ((ModuleListElement)CBModulesGui.instance.field_0029).field_0013 = false;
         ((ModuleListElement)CBModulesGui.instance.field_0029).scrollable = this.field_0005;
         ((ModuleListElement)CBModulesGui.instance.field_0029).module = this.field_0003;
         CBModulesGui.instance.currentScrollableElement = CBModulesGui.instance.field_0029;
      } else if (!this.field_0003.field_0020 && this.field_0000.isMouseInside(var1, var2)) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.field_0003.method_28786(true);
         this.field_0003.setState(!this.field_0003.isEnabled());
         this.field_0000.displayString = this.field_0003.isEnabled() ? "Disable" : "Enable";
         this.field_0000.field_0006 = this.field_0003.isEnabled() ? -5756117 : -13916106;
         if (this.field_0003.isEnabled()) {
            this.method_27154();
            this.field_0003.setState(true);
         }
      } else if (this.field_0007.field_0001 && this.field_0007.isMouseInside(var1, var2)) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         if (!this.field_0003.isEnabled()) {
            this.field_0003.method_28847(true);
            this.method_27154();
            if (this.field_0003.getGuiAnchor() == null || !Keyboard.isKeyDown(42) && this.field_0003.method_28790()) {
               this.field_0003.setState(true);
            } else {
               Minecraft.getMinecraft().displayGuiScreen(new UnidentifiedClass0105(CBModulesGui.instance, this.field_0003));
            }
         } else {
            this.field_0003.method_28847(!this.field_0003.method_28866());
            if (!this.field_0003.method_28866()) {
               if (this.field_0003.field_0020 && this.field_0003.isEnabled()) {
                  this.field_0003.setState(false);
               }
            } else {
               this.method_27154();
               if (this.field_0003.getGuiAnchor() == null || !Keyboard.isKeyDown(42) && this.field_0003.method_28790()) {
                  this.field_0003.setState(true);
               } else {
                  Minecraft.getMinecraft().displayGuiScreen(new UnidentifiedClass0105(CBModulesGui.instance, this.field_0003));
               }
            }
         }

         this.field_0003.method_28786(true);
         this.field_0007.displayString = this.field_0003.getGuiAnchor() == null
            ? (this.field_0003.method_28866() && this.field_0003.isEnabled() ? "Disable" : "Enable")
            : (
               this.field_0003.method_28866() && this.field_0003.isEnabled()
                  ? "Hide from HUD"
                  : (this.field_0003.method_28790() && !Keyboard.isKeyDown(42) ? "Return to HUD" : "Add to HUD")
            );
         this.field_0007.field_0006 = this.field_0003.method_28866() && this.field_0003.isEnabled() ? -5756117 : -13916106;
      }

      if (this.field_0008
         && this.isMouseInside(var1, var2)
         && !this.field_0007.isMouseInside(var1, var2)
         && !this.field_0000.isMouseInside(var1, var2)
         && !this.field_0002.isMouseInside(var1, var2)) {
         Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
         this.field_0003.method_28786(true);
         this.field_0003.setState(!this.field_0003.isEnabled());
         if (this.field_0003.isEnabled()) {
            this.method_27154();
            this.field_0003.setState(true);
         }
      }
   }

   public void method_27153(AbstractModule var1) {
      ((ModuleListElement)CBModulesGui.instance.field_0029).field_0013 = false;
      ((ModuleListElement)CBModulesGui.instance.field_0029).scrollable = this.field_0005;
      ((ModuleListElement)CBModulesGui.instance.field_0029).module = var1;
      CBModulesGui.instance.field_0029.field_0013 = CheatBreaker.getInstance().getModuleManager().field_0017;
      CBModulesGui.instance.currentScrollableElement = CBModulesGui.instance.field_0029;
   }

   public ModulePreviewElement(AbstractScrollableElement var1, AbstractModule var2, float var3) {
      super(var3);
      field_0010 = this;
      this.field_0003 = var2;
      this.field_0005 = var1;
      boolean var4 = var2 != CheatBreaker.getInstance().getModuleManager().minmap && var2 != CheatBreaker.getInstance().getModuleManager().notifications;
      String var5 = var2.getGuiAnchor() == null ? (var2.method_28866() ? "Disable" : "Enable") : (var2.method_28866() ? "Hide from HUD" : "Add to HUD");
      String var6 = var2.isEnabled() ? "Disable" : "Enable";
      String var7 = this.field_0008 ? "cog-64.png" : "Options";
      CBFontRenderer var8 = CheatBreaker.getInstance().field_0039;
      CBFontRenderer var9 = CheatBreaker.getInstance().playRegular14px;
      this.field_0000 = new ModulesGuiButtonElement(
         var9,
         null,
         var6,
         this.x + this.width / 2 + 2,
         this.y + this.height - 38,
         this.x + this.width - 4,
         this.y + this.height - 24,
         var2.isEnabled() ? -5756117 : -13916106,
         var3
      );
      this.field_0002 = new ModulesGuiButtonElement(
         var8, null, var7, this.x + 4, this.y + this.height - 20, this.x + this.width - 4, this.y + this.height - 6, -12418828, var3
      );
      this.field_0007 = new ModulesGuiButtonElement(
         var9,
         null,
         var5,
         this.x + 4,
         this.y + this.height - 38,
         this.x + this.width / 2 - 2,
         this.y + this.height - 24,
         var2.method_28866() ? -5756117 : -13916106,
         var3
      );
      this.field_0007.method_07928(var4);
   }

   public void method_27154() {
      if (this.field_0003 != CheatBreaker.getInstance().getModuleManager().directionHud
         && this.field_0003 != CheatBreaker.getInstance().getModuleManager().field_0018
         && this.field_0003 != CheatBreaker.getInstance().getModuleManager().field_0047
         && this.field_0003 != CheatBreaker.getInstance().getModuleManager().field_0010
         && this.field_0003 != CheatBreaker.getInstance().getModuleManager().field_0004
         && this.field_0003 != CheatBreaker.getInstance().getModuleManager().field_0016
         && this.field_0003 != CheatBreaker.getInstance().getModuleManager().scoreboard
         && (Boolean)CheatBreaker.getInstance().getGlobalSettings().field_0018.getValue()) {
         for (Setting var2 : this.field_0003.getSettingsList()) {
            if (var2.getType() == Setting$Type.field_0002
               && var2.method_08911().toLowerCase().contains("color")
               && !var2.method_08911().toLowerCase().contains("background")
               && !var2.method_08911().toLowerCase().contains("border")
               && !var2.method_08911().toLowerCase().contains("pressed")
               && !var2.method_08911().toLowerCase().contains("amount")
               && !var2.method_08911().toLowerCase().contains("line")) {
               Minecraft.getMinecraft().getSoundHandler().playSound(PositionedSoundRecord.create(new ResourceLocation("gui.button.press"), 1.0F));
               var2.setValue(CheatBreaker.getInstance().getGlobalSettings().field_0052.method_08901());
            }
         }
      }
   }
}
