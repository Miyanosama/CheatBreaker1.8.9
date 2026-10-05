package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.event.type.TickEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.MathHelper;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.event.type.MouseClickEvent;

public class CoordinatesModule extends AbstractModule {
   public Setting recoveredField3604;
   public Setting recoveredField3605;
   public Setting recoveredField3606;
   public Setting recoveredField3607;
   public Setting recoveredField3608;
   public Setting recoveredField3609;
   public Setting recoveredField3610;
   public Setting recoveredField3611;
   public Setting recoveredField3612;
   public Setting recoveredField3613;
   public List<Long> recoveredField3614 = new ArrayList<>();
   public Setting recoveredField3615;

   public void onRender(GuiDrawEvent var1) {
      if (this.method_28866() && (!this.recoveredField3905 || this.minecraft.currentScreen instanceof CBModulesGui)) {
         GL11.glPushMatrix();
         GlStateManager.enableBlend();
         this.scaleAndTranslate(var1.getResolution());
         int var2 = MathHelper.floor_double(this.minecraft.thePlayer.s);
         int var3 = (int)this.minecraft.thePlayer.boundingBox.b;
         int var4 = MathHelper.floor_double(this.minecraft.thePlayer.u);
         if (!this.minecraft.ingameGUI.getChatGUI().getChatOpen() || (Boolean)this.recoveredField3607.getValue()) {
            if ((Boolean)this.recoveredField3615.getValue().equals("")) {
               int var5 = 0;
               float var6 = 4.0F;
               String var7 = this.minecraft.renderGlobal.getDebugInfoRenders();
               var7 = var7.split(Pattern.quote("/"))[0].split(" ")[1];
               if ((Boolean)this.recoveredField3604.getValue()) {
                  if ((Boolean)this.recoveredField3610.getValue().equals("Horizontal")) {
                     String var8 = (Boolean)this.recoveredField3612.getValue()
                        ? (
                           (Boolean)this.recoveredField3608.getValue()
                              ? String.format("(%1$d, %2$d, C: %3$s)", var2, var4, var7)
                              : String.format("(%1$d, %2$d)", var2, var4)
                        )
                        : (
                           (Boolean)this.recoveredField3608.getValue()
                              ? String.format("(%1$d, %2$d, %3$d, C: %4$s)", var2, var3, var4, var7)
                              : String.format("(%1$d, %2$d, %3$d)", var2, var3, var4)
                        );
                     var5 = this.minecraft.fontRendererObj.drawStringWithShadow(var8, 0.0F, 0.0F, this.recoveredField3613.method_08901());
                  } else {
                     var5 = 50;
                     var6 = (Boolean)this.recoveredField3612.getValue() ? 9.5F : 16.0F;
                     this.minecraft.fontRendererObj.drawStringWithShadow("X: " + var2, 0.0F, 0.0F, this.recoveredField3613.method_08901());
                     if (!(Boolean)this.recoveredField3612.getValue()) {
                        this.minecraft.fontRendererObj.drawStringWithShadow("Y: " + var3, 0.0F, 12.0F, this.recoveredField3613.method_08901());
                     }

                     this.minecraft
                        .fontRendererObj
                        .drawStringWithShadow("Z: " + var4, 0.0F, (Boolean)this.recoveredField3612.getValue() ? 12.0F : 24.0F, this.recoveredField3613.method_08901());
                     if ((Boolean)this.recoveredField3608.getValue()) {
                        this.minecraft
                           .fontRendererObj
                           .drawStringWithShadow("C: " + var7, 0.0F, (Boolean)this.recoveredField3612.getValue() ? 24.0F : 36.0F, this.recoveredField3613.method_08901());
                     }
                  }
               }

               if ((Boolean)this.recoveredField3605.getValue()) {
                  String[] var18 = new String[]{" N", " NE", " E", " SE", " S", " SW", " W", " NW"};
                  double var9 = MathHelper.wrapAngleTo180_float(this.minecraft.thePlayer.y) + 180.0;
                  var9 += 22.5;
                  var9 %= 360.0;
                  String var11 = var18[MathHelper.floor_double(var9 / 45.0)];
                  this.minecraft.fontRendererObj.drawStringWithShadow(var11, var5, var6 - 4.0F, this.recoveredField3606.method_08901());
                  var5 += this.minecraft.fontRendererObj.getStringWidth(var11);
               }

               this.method_28812(
                  var5,
                  Math.max(
                     var6
                        + (!this.recoveredField3610.getValue().equals("Horizontal") && (Boolean)this.recoveredField3604.getValue() ? 18.0F : 0.0F)
                        + ((Boolean)this.recoveredField3608.getValue() ? 12.0F : 0.0F),
                     (float)this.minecraft.fontRendererObj.FONT_HEIGHT
                  )
               );
            } else {
               String[] var14 = ((String)this.recoveredField3615.getValue()).split("%NL%");
               float var15 = -1.0F;
               float var17 = var14.length * (this.minecraft.fontRendererObj.FONT_HEIGHT + 1);
               int var19 = 0;

               for (String var12 : var14) {
                  float var13 = this.minecraft
                     .fontRendererObj
                     .drawStringWithShadow(this.method_01716(var12), 0.0F, (this.minecraft.fontRendererObj.FONT_HEIGHT + 1) * var19, -1);
                  if (var13 > var15) {
                     var15 = var13;
                  }

                  this.method_28812((int)var15, (int)Math.max(var17, (float)this.minecraft.fontRendererObj.FONT_HEIGHT));
                  var19++;
               }
            }
         }

         GlStateManager.disableBlend();
         GL11.glPopMatrix();
      }
   }

   public void method_01723(MouseClickEvent var1) {
      if (var1.method_05882() == 0 && ((String)this.recoveredField3615.getValue()).contains("%CPS%")) {
         this.recoveredField3614.add(System.currentTimeMillis());
      }
   }

   public String method_01716(String var1) {
      String[] var2 = new String[]{"N", "NE", "E", "SE", "S", "SW", "W", "NW"};
      double var3 = MathHelper.wrapAngleTo180_float(this.minecraft.thePlayer.y) + 180.0;
      var3 += 22.5;
      var3 %= 360.0;
      double var12;
      String var5 = var2[MathHelper.floor_double(var12 = var3 / 45.0)];
      int var6 = MathHelper.floor_double(this.minecraft.thePlayer.s);
      int var7 = (int)this.minecraft.thePlayer.boundingBox.b;
      int var8 = MathHelper.floor_double(this.minecraft.thePlayer.u);
      var1 = !this.minecraft.isIntegratedServerRunning() && this.minecraft.theWorld != null
         ? var1.replaceAll("%IP%", this.minecraft.currentServerData.serverIP)
         : var1.replaceAll("%IP%", "?");
      return var1.replaceAll("%FPS%", Minecraft.debugFPS + "")
         .replaceAll("%DIR%", var5)
         .replaceAll("%CPS%", this.recoveredField3614.size() + "")
         .replaceAll("%COORDS%", String.format("%1$d, %2$d, %3$d", var6, var7, var8))
         .replaceAll("%X%", var6 + "")
         .replaceAll("%Y%", var7 + "")
         .replaceAll("%Z%", var8 + "");
   }

   public CoordinatesModule() {
      super("Coordinates");
      this.setDefaultAnchor(CBGuiAnchor.LEFT_TOP);
      this.setDefaultTranslations(-1.0F, 0.0F);
      this.setDefaultState(false);
      this.recoveredField3611 = new Setting(this, "label").setValue("General Options");
      this.recoveredField3607 = new Setting(this, "Show While Typing", "Show the mod when opening chat.")
         .setValue(true)
         .method_08914(SettingsDetailLevel.ADVANCED);
      this.recoveredField3610 = new Setting(this, "Mode", "Layout the mod should display.")
         .setValue("Horizontal")
         .acceptedValues("Horizontal", "Vertical")
         .method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField3604 = new Setting(this, "Coordinates", "Show the coordiantes.").setValue(true).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField3608 = new Setting(this, "C Counter", "Shows the C Counter").setValue(true).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField3612 = new Setting(this, "Hide Y Coordinate", "Hide the Y coordinate.")
         .setValue(false)
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> (Boolean)this.recoveredField3604.getValue());
      this.recoveredField3605 = new Setting(this, "Direction", "Show the direction the player is facing.")
         .setValue(true)
         .method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField3615 = new Setting(this, "Custom Line").setValue("").method_08914(SettingsDetailLevel.ADVANCED);
      this.recoveredField3609 = new Setting(this, "label")
         .setValue("Color Options")
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> (Boolean)this.recoveredField3604.getValue() || (Boolean)this.recoveredField3605.getValue());
      this.recoveredField3613 = new Setting(this, "Coordinates Color", "Change the coordinates text color.")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> (Boolean)this.recoveredField3604.getValue());
      this.recoveredField3606 = new Setting(this, "Direction Color", "Change the direction text color.")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> (Boolean)this.recoveredField3605.getValue());
      this.setPreviewLabel("(16, 65, 120) NW", 1.0F);
      this.method_28821("Shows your X, Y, and Z coordinates as well as your direction.");
      this.method_28820(GuiDrawEvent.class, this::onRender);
      this.method_28820(TickEvent.class, this::method_01718);
      this.method_28820(MouseClickEvent.class, this::method_01723);
   }

   public void method_01718(TickEvent var1) {
      if (((String)this.recoveredField3615.getValue()).contains("%CPS%")) {
         this.recoveredField3614.removeIf(var0 -> var0 < System.currentTimeMillis() - 1000L);
      }
   }
}
