package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import io.netty.handler.codec.http.DefaultHttpHeaders;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshaker08;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.particle.EntityDropParticleFX$LavaFactory;
import net.optifine.util.MathUtilsTest$OPER;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass0144;
import recovered.unidentified.UnidentifiedClass1369;

public abstract class TextHudModule extends AbstractModule {
   public Setting field_0010;
   public Setting field_0012;
   public Setting field_0003;
   public Setting field_0004;
   public Setting field_0017;
   public Setting field_0014;
   public EntityDropParticleFX$LavaFactory field_0020;
   public Setting field_0016;
   public Setting field_0001;
   public Setting field_0007;
   public DefaultHttpHeaders field_0011;
   public Setting field_0015;
   public Setting field_0002;
   public Setting field_0009;
   public Setting field_0006;
   public Setting field_0019;
   public Setting field_0005;
   public WebSocketClientHandshaker08 field_0013;
   public Setting field_0000;
   public MathUtilsTest$OPER field_0008;
   public Setting field_0018;

   public TextHudModule(String var1, String var2, float var3, boolean var4, boolean var5) {
      super(var1);
      this.setDefaultAnchor(CBGuiAnchor.RIGHT_TOP);
      this.setDefaultTranslations(0.0F, 0.0F);
      this.setDefaultState(false);
      new Setting(this, "label").setValue("Background Options");
      this.field_0005 = new Setting(this, "Show Background", "Draw a background.").setValue(var4);
      this.field_0002 = new Setting(
            this, "Static Background Width", "§2Enabled:§r Background width is set by a slider.\n§4Disabled:§r Background width is set by the text size."
         )
         .setValue(true)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> this.field_0005.method_08908() || this.field_0007.method_08908());
      this.field_0001 = new Setting(
            this,
            "Text Overflow",
            "What the mod will do if the text overflows from the background\n§bExtend Width:§r Increases the background width.\n§bScale Text:§r Scales the text down.\n§bDo Nothing:§r Does no action."
         )
         .setValue("Extend Width")
         .acceptedValues("Extend Width", "Scale Text", "Do Nothing")
         .method_08894(() -> (this.field_0005.method_08908() || this.field_0007.method_08908()) && this.field_0002.method_08908())
         .method_08914(SettingsDetailLevel.field_0001);
      this.field_0014 = new Setting(this, "Show Border", "Draw a border around the background.")
         .setValue(false)
         .method_08894(this.field_0005::method_08908)
         .method_08914(SettingsDetailLevel.field_0000);
      this.field_0009 = new Setting(this, "Background Width Padding", "Change how spaced the background width is from the text.")
         .setValue(6.0F)
         .setMinMax(0.0F, 10.0F)
         .method_08892("px")
         .method_08894(
            () -> (this.field_0005.method_08908() || this.field_0007.method_08908())
               && (!this.field_0002.method_08908() || !this.field_0001.getValue().equals("Do Nothing"))
         )
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0010 = new Setting(this, "Background Width", "Change the width of the background.")
         .setValue(this.method_08395().method_09435())
         .setMinMax(this.method_08395().method_09432(), this.method_08395().method_09434())
         .method_08892("px")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (this.field_0005.method_08908() || this.field_0007.method_08908()) && this.field_0002.method_08908());
      this.field_0018 = new Setting(this, "Background Height", "Change the height of the background.")
         .setValue(this.method_08395().method_09437())
         .setMinMax(this.method_08395().method_09436(), this.method_08395().method_09433())
         .method_08892("px")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> this.field_0005.method_08908() || this.field_0007.method_08908());
      this.field_0017 = new Setting(this, "Border Thickness", "Change the thickness of the border.")
         .setValue(1.0F)
         .setMinMax(0.25F, 3.0F)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> this.field_0005.method_08908() && this.field_0014.method_08908());
      new Setting(this, "label").setValue("General Options");
      this.field_0004 = new Setting(this, "Show While Typing", "Show the mod when opening chat.").setValue(true).method_08914(SettingsDetailLevel.field_0001);
      this.field_0007 = new Setting(this, "Always Center", "Force the text to be centered in the mod's placement.")
         .setValue(var5)
         .method_08894(() -> !this.field_0005.method_08908())
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0015 = new Setting(this, "Text Shadow (Background)", "Add a text shadow when the background is enabled.")
         .setValue(false)
         .method_08894(this.field_0005::method_08908);
      this.field_0000 = new Setting(this, "Text Shadow (No background)", "Add a text shadow when the background is disabled.")
         .setValue(true)
         .method_08894(() -> !this.field_0005.method_08908());
      this.method_00165();
      new Setting(this, "label").setValue("Format Options").method_08914(SettingsDetailLevel.field_0003);
      this.method_01862();
      this.field_0003 = new Setting(this, "Format (Background)")
         .setValue(this.method_21178())
         .method_08894(this.field_0005::method_08908)
         .method_08914(SettingsDetailLevel.field_0001);
      if (this.method_08396()) {
         this.field_0006 = new Setting(this, "Format (No Background)")
            .setValue("[" + this.method_21178() + "]")
            .method_08894(() -> !this.field_0005.method_08908())
            .method_08914(SettingsDetailLevel.field_0001);
      } else {
         this.field_0006 = new Setting(this, "Format (No Background)")
            .setValue(this.method_21178())
            .method_08894(() -> !this.field_0005.method_08908())
            .method_08914(SettingsDetailLevel.field_0001);
      }

      new Setting(this, "label").setValue("Color Options").method_08914(SettingsDetailLevel.field_0000);
      this.field_0012 = new Setting(this, "Text Color", "Sets the color for the text.").setValue(-1).setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE);
      this.method_04335();
      this.field_0019 = new Setting(this, "Background Color", "Sets the color for the background.")
         .setValue(1862270976)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(this.field_0005::method_08908);
      this.field_0016 = new Setting(this, "Border Color", "Sets the color for the border.")
         .setValue(-1627389952)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> this.field_0005.method_08908() && this.field_0014.method_08908());
      this.setPreviewLabel(var2, var3);
      this.method_28820(UnidentifiedClass0144.class, this::method_21171);
      this.method_28820(GuiDrawEvent.class, this::renderHud);
   }

   public void method_00165() {
   }

   public TextHudModule(String var1, String var2) {
      this(var1, var2, 1.5F, true, false);
   }

   public String method_21178() {
      return "%VALUE%";
   }

   public void renderHud(GuiDrawEvent var1) {
      String var2 = this.method_00167();
      if (var2 != null
         && (!this.minecraft.ingameGUI.getChatGUI().getChatOpen() || this.field_0004.method_08908())
         && this.method_28866()
         && (!this.field_0043 || this.minecraft.currentScreen instanceof CBModulesGui)) {
         GL11.glPushMatrix();
         this.scaleAndTranslate(var1.getResolution());
         if (var2.isEmpty()) {
            var2 = "";
         }

         this.method_04331(var2);
         GL11.glPopMatrix();
      }
   }

   public boolean method_08396() {
      return true;
   }

   public abstract String method_00167();

   public String method_00164() {
      return "";
   }

   public void method_21171(UnidentifiedClass0144 var1) {
      if (this.method_28866() && this.method_00167() == null) {
         GL11.glPushMatrix();
         this.scaleAndTranslate(var1.method_01054());
         this.method_04331(this.method_00164());
         GL11.glPopMatrix();
      }
   }

   public int getTextOffsetY() {
      return this.field_0012.method_08901();
   }

   public void method_04331(String var1) {
      String var2 = this.method_00166();
      if (var2 == null || var2.isEmpty()) {
         var2 = "";
      }

      String var3;
      if (this.method_21170() != null && this.method_00167() != null) {
         var3 = this.method_21170();
      } else if (!var2.equals("")) {
         if (!this.field_0005.method_08908()) {
            var3 = this.field_0006.method_08874().replaceAll("%LABEL%", var2).replaceAll("%VALUE%", var1);
         } else {
            var3 = this.field_0003.method_08874().replaceAll("%LABEL%", var2).replaceAll("%VALUE%", var1);
         }
      } else {
         var3 = var1;
      }

      float var4 = this.minecraft.fontRendererObj.getStringWidth(var3);
      float var5 = this.field_0018.method_08905();
      float var6 = !this.field_0002.method_08908() ? var4 + this.field_0009.method_08905() : this.field_0010.method_08905();
      if (this.field_0001.getValue().equals("Extend Width") && this.field_0002.method_08908()) {
         var6 = Math.max(this.field_0010.method_08905(), var4 + this.field_0009.method_08905());
      }

      if (!Boolean.valueOf(this.field_0005.method_08908()) && !Boolean.valueOf(this.field_0007.method_08908())) {
         GL11.glEnable(3042);
         this.method_28812(
            this.minecraft.fontRendererObj.drawString(var3, 0.0F, 0.0F, this.getTextOffsetY(), this.field_0000.method_08908()),
            this.minecraft.fontRendererObj.FONT_HEIGHT
         );
      } else {
         this.method_28812(var6, var5);
         if (this.field_0005.method_08908()) {
            Gui.drawRect(0.0F, 0.0F, var6, var5, this.field_0019.method_08901());
            if (this.field_0014.method_08908()) {
               float var7 = this.field_0017.method_08905();
               Gui.method_00886(-var7, -var7, var6 + var7, var5 + var7, var7, this.field_0016.method_08901());
            }
         }

         GL11.glEnable(3042);
         float var8 = 1.0F;
         if (this.minecraft.fontRendererObj.getStringWidth(var3) > var6 - this.field_0009.method_08905() && this.field_0001.getValue().equals("Scale Text")) {
            var8 = (var6 - this.field_0009.method_08905()) / this.minecraft.fontRendererObj.getStringWidth(var3);
            GL11.glScalef(var8, var8, 1.0F);
            var8 = this.minecraft.fontRendererObj.getStringWidth(var3) / (var6 - this.field_0009.method_08905());
         }

         this.minecraft
            .fontRendererObj
            .drawString(
               var3,
               this.field_0041 / 2.0F * var8 - this.minecraft.fontRendererObj.getStringWidth(var3) / 2 + 0.6F,
               var5 / 2.0F * var8 - 3.49F,
               this.getTextOffsetY(),
               (this.field_0005.method_08908() ? this.field_0015 : this.field_0000).method_08908()
            );
         if (this.minecraft.fontRendererObj.getStringWidth(var3) > var6) {
            GL11.glScalef(var8, var8, 1.0F);
         }
      }

      GL11.glDisable(3042);
   }

   public UnidentifiedClass1369 method_08395() {
      return new UnidentifiedClass1369(10.0F, 13.0F, 24.0F, 40.0F, 56.0F, 80.0F);
   }

   public void method_01862() {
   }

   public void method_04335() {
   }

   public String method_21170() {
      return null;
   }

   public abstract String method_00166();
}
