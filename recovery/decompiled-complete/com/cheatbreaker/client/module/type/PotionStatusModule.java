package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.event.type.TickEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.ui.module.CBPositionEnum;
import com.cheatbreaker.client.ui.module.CBProfileCreateGui;
import com.cheatbreaker.client.ui.util.RenderUtil;
import com.google.common.collect.ImmutableMap.Builder;
import io.netty.channel.nio.AbstractNioChannel$AbstractNioUnsafe$1;
import io.netty.handler.codec.socks.SocksCmdResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.inventory.ContainerEnchantment$3;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.util.WeightedRandomFishable;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass0105;
import recovered.unidentified.UnidentifiedClass0144;

public class PotionStatusModule extends AbstractModule {
   public static Map<Integer, Integer> field_0025 = new Builder()
      .put(1, -11141121)
      .put(2, -10851199)
      .put(3, -2506685)
      .put(4, -11910633)
      .put(5, -7134173)
      .put(6, -515037)
      .put(7, -12383735)
      .put(8, -1)
      .put(9, -11199158)
      .put(10, -3318613)
      .put(11, -11286957)
      .put(12, -1795526)
      .put(13, -13741415)
      .put(14, -8420462)
      .put(15, -14737629)
      .put(16, -14737503)
      .put(17, -10979757)
      .put(18, -12038840)
      .put(19, -11627727)
      .put(20, -13293017)
      .put(21, -2222326)
      .put(22, -14331227)
      .put(23, -474845)
      .build();
   public ContainerEnchantment$3 field_0027;
   public Setting field_0005;
   public Setting field_0008;
   public Setting field_0038;
   public Setting field_0029;
   public Setting field_0048;
   public Setting field_0037;
   public Setting field_0001;
   public Setting field_0018;
   public Setting field_0026;
   public Setting field_0030;
   public ResourceLocation field_0002 = new ResourceLocation("textures/gui/container/inventory.png");
   public Setting field_0022;
   public Setting field_0017;
   public Setting field_0045;
   public static Map<Integer, Integer> field_0015 = new Builder()
      .put(1, -7405569)
      .put(2, -10528630)
      .put(3, -10240)
      .put(4, -9728589)
      .put(5, -5177344)
      .put(6, -961967)
      .put(7, -8845049)
      .put(8, -2097153)
      .put(9, -1)
      .put(10, -36483)
      .put(11, -11511440)
      .put(12, -24259)
      .put(13, -7229441)
      .put(14, -5592406)
      .put(15, -7975034)
      .put(16, -14263332)
      .put(17, -11818424)
      .put(18, -9474163)
      .put(19, -14689758)
      .put(20, -5276243)
      .put(21, -511965)
      .put(22, -7866)
      .put(23, -103)
      .build();
   public Setting field_0028;
   public Setting field_0000;
   public Setting field_0019;
   public Setting field_0039;
   public Setting field_0031;
   public Setting field_0047;
   public Setting field_0006;
   public AbstractNioChannel$AbstractNioUnsafe$1 field_0034;
   public Setting field_0042;
   public WeightedRandomFishable field_0010;
   public Setting field_0035;
   public Setting field_0046;
   public Setting field_0033;
   public Setting field_0011;
   public Setting field_0041;
   public static Map<Integer, Integer> field_0044 = new Builder()
      .put(1, -8605754)
      .put(2, -10851199)
      .put(3, -2506685)
      .put(4, -11910633)
      .put(5, -7134173)
      .put(6, -515037)
      .put(7, -12383735)
      .put(8, -8887657)
      .put(9, -11199158)
      .put(10, -3318613)
      .put(11, -6732486)
      .put(12, -1795526)
      .put(13, -13741415)
      .put(14, -8420462)
      .put(15, -14737629)
      .put(16, -14737503)
      .put(17, -10979757)
      .put(18, -12038840)
      .put(19, -11627727)
      .put(20, -13293017)
      .put(21, -492253)
      .put(22, -14331227)
      .put(23, -515037)
      .build();
   public Setting field_0043;
   public Setting field_0024;
   public Setting field_0020;
   public Setting field_0012;
   public Setting field_0036;
   public Setting field_0003;
   public Setting field_0007;
   public Setting field_0040;
   public static Map<Integer, Integer> field_0021 = new Builder()
      .put(1, -11141121)
      .put(2, -11184811)
      .put(3, -171)
      .put(4, -11184811)
      .put(5, -5636096)
      .put(6, -43691)
      .put(7, -5636096)
      .put(8, -1)
      .put(9, -5635926)
      .put(10, -43521)
      .put(11, -5592406)
      .put(12, -22016)
      .put(13, -11184641)
      .put(14, -5592406)
      .put(15, -16777216)
      .put(16, -16777046)
      .put(17, -16733696)
      .put(18, -11184811)
      .put(19, -11141291)
      .put(20, -16777216)
      .put(21, 16733525)
      .put(22, -171)
      .put(23, -171)
      .build();
   public int ticks = 0;
   public Setting field_0032;
   public Setting field_0023;
   public Setting field_0013;
   public Setting field_0004;
   public Setting field_0014;
   public SocksCmdResponse field_0016;

   public int method_09812(int var1, Object var2, PotionEffect var3) {
      int var5;
      int var4 = var3.getDuration();
      String var6 = (String)var2;
      label59:
      switch (var6) {
         case "Effect":
            Potion var8 = Potion.potionTypes[var3.getPotionID()];
            String var9 = this.field_0004.method_08874();
            switch (var9) {
               case "Default":
                  var5 = field_0025.get(var3.getPotionID());
                  break label59;
               case "Potion Colors":
                  var5 = field_0044.get(var3.getPotionID());
                  break label59;
               case "Color Codes":
                  var5 = field_0021.get(var3.getPotionID());
                  break label59;
               case "Vibrant":
                  var5 = field_0015.get(var3.getPotionID());
                  break label59;
               default:
                  if (!var8.isBadEffect()) {
                     var5 = -15691760;
                  } else {
                     var5 = -7335920;
                  }
                  break label59;
            }
         case "Duration":
            if (var4 >= 1200) {
               var5 = -16733696;
            } else if (var4 >= 600) {
               var5 = -11141291;
            } else if (var4 >= 200) {
               var5 = -171;
            } else {
               var5 = var4 >= 100 ? -43691 : -5636096;
            }
            break;
         default:
            var5 = var1;
      }

      int var11 = var5 >> 24 & 0xFF;
      int var12 = (int)(var11 * (this.method_09814(var3) ? 0.5F : 1.0F));
      return var5 & 16777215 | var12 << 24;
   }

   public void method_09817(List<PotionEffect> var1) {
      GlStateManager.enableBlend();
      CBPositionEnum var2 = this.getPosition();
      int var3 = 0;
      int var4 = 0;
      float var5 = this.field_0029.method_08905();
      float var6 = var5
         + (
            !this.field_0036.method_08908() && (!this.field_0022.method_08908() || !this.field_0032.method_08908())
               ? this.minecraft.fontRendererObj.FONT_HEIGHT - 1
               : 18.0F
         );
      float var7 = this.field_0036.method_08908() ? 20.0F : 0.0F;
      this.method_09828(var1);

      for (PotionEffect var9 : var1) {
         if (!this.method_09814(var9)
            || this.minecraft.currentScreen instanceof CBModulesGui
            || this.minecraft.currentScreen instanceof UnidentifiedClass0105
            || this.minecraft.currentScreen instanceof CBProfileCreateGui) {
            boolean var12 = this.shouldBlink(var9.getDuration());
            boolean var13 = this.field_0031.method_08908();
            int var14 = 0;
            int var15 = this.field_0032.method_08908() ? 0 : (this.field_0036.method_08908() ? 5 : 0);
            String var16 = (this.field_0037.method_08908() ? StatCollector.translateToLocal(var9.getEffectName()) : "")
               + (this.field_0043.method_08908() ? this.getLevelName(var9.getAmplifier()) : "");
            if (this.field_0022.method_08908() && (var12 || !this.field_0041.method_08908())) {
               var14 = this.minecraft.fontRendererObj.getStringWidth(var16) + (int)var7;
               int var17 = this.method_09812(this.field_0012.method_08901(), this.field_0000.getValue(), var9);
               if (var2 == CBPositionEnum.RIGHT) {
                  this.minecraft
                     .fontRendererObj
                     .drawString(
                        (var13 ? var16 : this.method_09808(var16)) + "§r",
                        this.method_28810() - var14,
                        (float)var3 + var15,
                        var17,
                        this.field_0017.method_08908()
                     );
               } else if (var2 == CBPositionEnum.LEFT) {
                  this.minecraft
                     .fontRendererObj
                     .drawString((var13 ? var16 : this.method_09808(var16)) + "§r", var7, (float)var3 + var15, var17, this.field_0017.method_08908());
               } else if (var2 == CBPositionEnum.CENTER) {
                  this.minecraft
                     .fontRendererObj
                     .drawString(
                        (var13 ? var16 : this.method_09808(var16)) + "§r",
                        this.method_28810() / 2.0F - var14 / 2 + var7,
                        (float)var3 + var15,
                        var17,
                        this.field_0017.method_08908()
                     );
               }

               if (var14 > var4) {
                  var4 = var14;
               }
            }

            String var11 = Potion.getDurationString(var9);
            int var20 = this.field_0032.method_08908() ? this.minecraft.fontRendererObj.getStringWidth(var11) + (int)var7 : 18;
            int var18 = this.field_0022.method_08908() && !var16.isEmpty() ? 10 : (this.field_0036.method_08908() ? 5 : 0);
            if (this.field_0032.method_08908() && (var12 || !this.field_0045.method_08908())) {
               int var19 = this.method_09812(this.field_0039.method_08901(), this.field_0020.getValue(), var9);
               if (var2 == CBPositionEnum.RIGHT) {
                  this.minecraft.fontRendererObj.drawString(var11 + "§r", this.method_28810() - var20, var3 + var18, var19, this.field_0017.method_08908());
               } else if (var2 == CBPositionEnum.LEFT) {
                  this.minecraft.fontRendererObj.drawString(var11 + "§r", var7, var3 + var18, var19, this.field_0017.method_08908());
               } else if (var2 == CBPositionEnum.CENTER) {
                  this.minecraft
                     .fontRendererObj
                     .drawString(var11 + "§r", this.method_28810() / 2.0F - var20 / 2 + var7, var3 + var18, var19, this.field_0017.method_08908());
               }
            }

            Potion var10;
            if ((var10 = Potion.potionTypes[var9.getPotionID()]).hasStatusIcon()
               && this.field_0036.method_08908()
               && (var12 || !this.field_0018.method_08908())) {
               GL11.glColor4f(1.0F, 1.0F, 1.0F, this.method_09814(var9) ? 0.5F : 1.0F);
               this.minecraft.getTextureManager().bindTexture(this.field_0002);
               int var21 = var10.getStatusIconIndex();
               if (var2 != CBPositionEnum.LEFT && (this.field_0022.method_08908() || this.field_0032.method_08908())) {
                  if (var2 == CBPositionEnum.RIGHT) {
                     RenderUtil.method_22065(this.method_28810() - 20.0F, var3, var21 % 8 * 18, 198 + var21 / 8 * 18, 18, 18);
                  } else if (var2 == CBPositionEnum.CENTER) {
                     RenderUtil.method_22065(
                        this.method_28810() / 2.0F - (this.field_0022.method_08908() ? var14 : var20) / 2, var3, var21 % 8 * 18, 198 + var21 / 8 * 18, 18, 18
                     );
                  }
               } else {
                  RenderUtil.method_22065(0.0F, var3, var21 % 8 * 18, 198 + var21 / 8 * 18, 18, 18);
               }
            }

            if (var20 > var4) {
               var4 = var20;
            }

            var3 = (int)(var3 + var6);
         }
      }

      this.method_28812(var4, var3 - var5);
      GlStateManager.disableBlend();
   }

   public boolean shouldBlink(float var1) {
      float var2 = this.field_0023.method_08905() * 10.0F;
      if (this.field_0011.method_08908() && var1 <= this.field_0019.method_08905() * 20.0F) {
         if (this.ticks > var2 * 2.0F) {
            this.ticks = 0;
         }

         return this.ticks <= var2;
      } else {
         return true;
      }
   }

   public void method_09828(List<PotionEffect> var1) {
      String var2 = this.field_0040.method_08874();
      switch (var2) {
         case "Alphabetical":
            var1.sort(Comparator.comparing(var0 -> I18n.format(var0.getEffectName())));
            break;
         case "Duration":
            var1.sort(Comparator.comparingInt(PotionEffect::getDuration));
            break;
         case "Amplifier":
            var1.sort(Comparator.comparingInt(PotionEffect::getAmplifier));
            Collections.reverse(var1);
      }

      if (this.field_0026.method_08908()) {
         Collections.reverse(var1);
      }
   }

   public boolean method_09814(PotionEffect var1) {
      if (this.method_09815(this.field_0001, var1.getDuration(), this.field_0035.method_08905() * 20.0F) && !var1.getIsPotionDurationMax()) {
         return true;
      } else if (this.field_0047.method_08908() && var1.getIsPotionDurationMax()) {
         return true;
      } else if (this.method_09815(this.field_0033, var1.getAmplifier(), this.field_0048.method_08912() - 1)) {
         return true;
      } else if (this.field_0030.getValue().equals("Only Helpful") && !Potion.potionTypes[var1.getPotionID()].isBadEffect()) {
         return true;
      } else {
         return this.field_0030.getValue().equals("Only Harmful") && Potion.potionTypes[var1.getPotionID()].isBadEffect()
            ? true
            : this.field_0042.method_08869().contains(var1.getPotionID());
      }
   }

   @Override
   public boolean method_09815(Setting var1, int var2, float var3) {
      String var4 = var1.method_08874();
      switch (var4) {
         case "Effects Above":
            return var2 > var3;
         case "Effects Below":
            return var2 < var3;
         case "Effects At":
            return var2 == var3;
         case "Effects Not At":
            return var2 != var3;
         default:
            return false;
      }
   }

   public String method_09808(String var1) {
      return var1 == null ? null : Pattern.compile("(?i)§[0-9A-F]").matcher(var1).replaceAll("§r");
   }

   public PotionStatusModule() {
      super("Potion Effects");
      this.setDefaultState(false);
      this.setDefaultAnchor(CBGuiAnchor.LEFT_MIDDLE);
      this.field_0006 = new Setting(this, "label").setValue("Component Options").method_08914(SettingsDetailLevel.field_0000);
      this.field_0036 = new Setting(this, "Icon").setValue(true).method_08914(SettingsDetailLevel.field_0003);
      this.field_0022 = new Setting(this, "Effect Name").setValue(true).method_08914(SettingsDetailLevel.field_0000);
      this.field_0037 = new Setting(this, "Effect Name Text")
         .setValue(true)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(this.field_0022::method_08908);
      this.field_0043 = new Setting(this, "Effect Amplifier Text")
         .setValue(true)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(this.field_0022::method_08908);
      this.field_0028 = new Setting(this, "Show Roman Numerals")
         .setValue(true)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> this.field_0022.method_08908() && this.field_0043.method_08908());
      this.field_0003 = new Setting(this, "Show Level 1")
         .setValue(false)
         .method_08914(SettingsDetailLevel.field_0001)
         .method_08894(() -> this.field_0022.method_08908() && this.field_0043.method_08908());
      this.field_0046 = new Setting(this, "Max Roman Numeral")
         .setValue(10)
         .setMinMax(1, 255)
         .method_08914(SettingsDetailLevel.field_0001)
         .method_08894(() -> this.field_0022.method_08908() && this.field_0043.method_08908() && this.field_0028.method_08908());
      this.field_0032 = new Setting(this, "Duration").setValue(true).method_08914(SettingsDetailLevel.field_0003);
      this.field_0005 = new Setting(this, "label").setValue("General Options").method_08914(SettingsDetailLevel.field_0000);
      this.field_0008 = new Setting(this, "Show While Typing").setValue(true).method_08914(SettingsDetailLevel.field_0001);
      this.field_0038 = new Setting(this, "Show Potion info in inventory").setValue(false).method_08914(SettingsDetailLevel.field_0000);
      this.field_0017 = new Setting(this, "Text Shadow")
         .setValue(true)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> this.field_0022.method_08908() || this.field_0032.method_08908());
      this.field_0029 = new Setting(this, "Effect Spacing")
         .setValue(4.0F)
         .setMinMax(0.0F, 15.0F)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0024 = new Setting(this, "label").setValue("Sorting Options").method_08914(SettingsDetailLevel.field_0003);
      this.field_0040 = new Setting(this, "Sorting Method")
         .setValue("Vanilla")
         .acceptedValues("Vanilla", "Alphabetical", "Duration", "Amplifier")
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0026 = new Setting(this, "Reverse Sorting").setValue(false).method_08914(SettingsDetailLevel.field_0003);
      this.field_0007 = new Setting(this, "label").setValue("Blink Options").method_08914(SettingsDetailLevel.field_0000);
      this.field_0011 = new Setting(this, "Blink").setValue(true).method_08914(SettingsDetailLevel.field_0000);
      this.field_0041 = new Setting(this, "Make Effect Name Text Blink")
         .setValue(false)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> this.field_0011.method_08908() && this.field_0022.method_08908());
      this.field_0018 = new Setting(this, "Make Effect Icon Blink")
         .setValue(false)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> this.field_0011.method_08908() && this.field_0036.method_08908());
      this.field_0045 = new Setting(this, "Make Duration Text Blink")
         .setValue(true)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> this.field_0011.method_08908() && this.field_0032.method_08908());
      this.field_0019 = new Setting(this, "Blink Duration")
         .setValue(10.0F)
         .setMinMax(2.0F, 60.0F)
         .method_08892("s")
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(this.field_0011::method_08908);
      this.field_0023 = new Setting(this, "Blink Speed")
         .setValue(1.0F)
         .setMinMax(0.2F, 3.5F)
         .method_08892("s")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(this.field_0011::method_08908);
      this.field_0013 = new Setting(this, "label").setValue("Excluding Options").method_08914(SettingsDetailLevel.field_0003);
      this.field_0047 = new Setting(this, "Exclude Permanent Effects").setValue(false).method_08914(SettingsDetailLevel.field_0003);
      this.field_0001 = new Setting(this, "Exclude Set Duration", "Exclude all active effects above a set duration.")
         .setValue("OFF")
         .acceptedValues("OFF", "Effects Above", "Effects Below", "Effects At", "Effects Not At")
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0035 = new Setting(this, "Excluded Duration", "The duration effects are excluded")
         .setValue(30.0F)
         .setMinMax(2.0F, 90.0F)
         .method_08892("s")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> !this.field_0001.getValue().equals("OFF"));
      this.field_0033 = new Setting(this, "Exclude Set Amplifier", "Exclude all active effects below a set amplifier.")
         .setValue("OFF")
         .acceptedValues("OFF", "Effects Above", "Effects Below", "Effects At", "Effects Not At")
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0048 = new Setting(this, "Excluded Amplifier", "The duration effects are excluded")
         .setValue(10)
         .setMinMax(0, 20)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> !this.field_0033.getValue().equals("OFF"));
      this.field_0030 = new Setting(this, "Exclude Helpful/Harmful Effects")
         .setValue("OFF")
         .acceptedValues("OFF", "Only Helpful", "Only Harmful")
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0042 = new Setting(this, "Exclude Specific Effects")
         .setValue(new ArrayList())
         .method_08897(
            Potion.moveSpeed.id,
            Potion.moveSlowdown.id,
            Potion.digSpeed.id,
            Potion.digSlowdown.id,
            Potion.damageBoost.id,
            Potion.jump.id,
            Potion.confusion.id,
            Potion.regeneration.id,
            Potion.resistance.id,
            Potion.fireResistance.id,
            Potion.waterBreathing.id,
            Potion.invisibility.id,
            Potion.blindness.id,
            Potion.nightVision.id,
            Potion.hunger.id,
            Potion.weakness.id,
            Potion.poison.id,
            Potion.wither.id,
            Potion.absorption.id
         )
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0014 = new Setting(this, "label")
         .setValue("Color Options")
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> this.field_0022.method_08908() || this.field_0032.method_08908());
      this.field_0031 = new Setting(this, "Override Name Color With Pack Format")
         .setValue(true)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> this.field_0022.method_08908() && this.field_0037.method_08908());
      this.field_0000 = new Setting(this, "Color Name Based On")
         .setValue("Static")
         .acceptedValues("Static", "Effect", "Duration")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(this.field_0022::method_08908);
      this.field_0020 = new Setting(this, "Color Duration Based On")
         .setValue("Static")
         .acceptedValues("Static", "Effect", "Duration")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(this.field_0032::method_08908);
      this.field_0004 = new Setting(this, "Color Type")
         .setValue("Default")
         .acceptedValues("Default", "Potion Colors", "Color Codes", "Good/Bad", "Vibrant")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> this.field_0020.getValue().equals("Effect") || this.field_0000.getValue().equals("Effect"));
      this.field_0012 = new Setting(this, "Name Color")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> this.field_0022.method_08908() && this.field_0000.getValue().equals("Static"));
      this.field_0039 = new Setting(this, "Duration Color")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> this.field_0032.method_08908() && this.field_0020.getValue().equals("Static"));
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/speed_icon.png"), 28, 28);
      this.method_28821("Displays your active potion effects.");
      this.method_28829("bspkrs", "jadedcat");
      this.method_28807("Potion Status", "Status Effects");
      this.method_28820(TickEvent.class, this::onTick);
      this.method_28820(UnidentifiedClass0144.class, this::method_09813);
      this.method_28820(GuiDrawEvent.class, this::renderReal);
   }

   public void renderReal(GuiDrawEvent var1) {
      if (this.method_28866() && (!this.field_0043 || this.minecraft.currentScreen instanceof CBModulesGui)) {
         GL11.glPushMatrix();
         if (this.field_0008.method_08908() || !this.minecraft.ingameGUI.getChatGUI().getChatOpen()) {
            GL11.glPushMatrix();
            this.scaleAndTranslate(var1.getResolution());
            ArrayList var2 = new ArrayList();
            if (this.minecraft.thePlayer != null) {
               var2.addAll(this.minecraft.thePlayer.getActivePotionEffects());
            }

            if (var2.isEmpty()) {
               GL11.glPopMatrix();
               GL11.glPopMatrix();
               return;
            }

            this.method_09817(var2);
            GL11.glPopMatrix();
         }

         GL11.glPopMatrix();
      }
   }

   public String getLevelName(int var1) {
      String var2 = this.field_0037.method_08908() ? " " : "";
      if (var1 < 0) {
         var1 = 127 + Math.abs(128 + var1);
      }

      int var3 = var1 + 1;
      int var4 = this.field_0003.method_08908() ? 0 : 1;
      if (this.field_0028.method_08908() && var1 >= var4 && var1 <= this.field_0046.method_08912()) {
         return var2
            + String.join("", Collections.nCopies(var3, "I"))
               .replace("IIIII", "V")
               .replace("IIII", "IV")
               .replace("VV", "X")
               .replace("VIV", "IX")
               .replace("XXXXX", "L")
               .replace("XXXX", "XL")
               .replace("LL", "C")
               .replace("LXL", "XC")
               .replace("CCCCC", "D")
               .replace("CCCC", "CD")
               .replace("DD", "M")
               .replace("DCD", "CM");
      } else {
         return var1 >= var4 ? var2 + var3 : "";
      }
   }

   public void method_09813(UnidentifiedClass0144 var1) {
      if (this.method_28866()) {
         GL11.glPushMatrix();
         ArrayList var2 = new ArrayList();
         if (this.minecraft.thePlayer != null) {
            var2.addAll(this.minecraft.thePlayer.getActivePotionEffects());
         }

         if (var2.isEmpty()) {
            GL11.glPushMatrix();
            this.scaleAndTranslate(var1.method_01054());
            var2.add(new PotionEffect(Potion.moveSpeed.id, 1200, 3));
            var2.add(new PotionEffect(Potion.damageBoost.id, 30, 3));
            this.method_09817(var2);
            GL11.glPopMatrix();
         }

         GL11.glPopMatrix();
      }
   }

   public void onTick(TickEvent var1) {
      this.ticks++;
   }
}
