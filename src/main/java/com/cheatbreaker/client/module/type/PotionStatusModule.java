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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.ui.module.ModulePlacementGui;
import com.cheatbreaker.client.event.type.HudPreviewDrawEvent;

public class PotionStatusModule extends AbstractModule {
   public static Map<Integer, Integer> recoveredField2950 = new Builder<Integer, Integer>()
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
   public Setting recoveredField2951;
   public Setting recoveredField2952;
   public Setting recoveredField2953;
   public Setting recoveredField2954;
   public Setting recoveredField2955;
   public Setting recoveredField2956;
   public Setting recoveredField2957;
   public Setting recoveredField2958;
   public Setting recoveredField2959;
   public Setting recoveredField2960;
   public ResourceLocation recoveredField2961 = new ResourceLocation("textures/gui/container/inventory.png");
   public Setting recoveredField2962;
   public Setting recoveredField2963;
   public Setting recoveredField2964;
   public static Map<Integer, Integer> recoveredField2979 = new Builder<Integer, Integer>()
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
   public Setting recoveredField2966;
   public Setting recoveredField2967;
   public Setting recoveredField2968;
   public Setting recoveredField2969;
   public Setting recoveredField2970;
   public Setting recoveredField2971;
   public Setting recoveredField2972;
   public Setting recoveredField2973;
   public Setting recoveredField2974;
   public Setting recoveredField2975;
   public Setting recoveredField2976;
   public Setting recoveredField2977;
   public Setting recoveredField2978;
   public static Map<Integer, Integer> recoveredField2988 = new Builder<Integer, Integer>()
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
   public Setting recoveredField2980;
   public Setting recoveredField2981;
   public Setting recoveredField2982;
   public Setting recoveredField2983;
   public Setting recoveredField2984;
   public Setting recoveredField2985;
   public Setting recoveredField2986;
   public Setting recoveredField2987;
   public static Map<Integer, Integer> recoveredField2965 = new Builder<Integer, Integer>()
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
   public int ticks = 0;
   public Setting recoveredField2989;
   public Setting recoveredField2990;
   public Setting recoveredField2991;
   public Setting recoveredField2992;
   public Setting recoveredField2993;

   public int method_09812(int var1, Object var2, PotionEffect var3) {
      int var5;
      int var4 = var3.getDuration();
      String var6 = (String)var2;
      label59:
      switch (var6) {
         case "Effect":
            Potion var8 = Potion.potionTypes[var3.getPotionID()];
            String var9 = this.recoveredField2992.method_08874();
            switch (var9) {
               case "Default":
                  var5 = recoveredField2950.get(var3.getPotionID());
                  break label59;
               case "Potion Colors":
                  var5 = recoveredField2979.get(var3.getPotionID());
                  break label59;
               case "Color Codes":
                  var5 = recoveredField2988.get(var3.getPotionID());
                  break label59;
               case "Vibrant":
                  var5 = recoveredField2965.get(var3.getPotionID());
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
      float var5 = this.recoveredField2954.method_08905();
      float var6 = var5
         + (
            !this.recoveredField2984.method_08908() && (!this.recoveredField2962.method_08908() || !this.recoveredField2989.method_08908())
               ? this.minecraft.fontRendererObj.FONT_HEIGHT - 1
               : 18.0F
         );
      float var7 = this.recoveredField2984.method_08908() ? 20.0F : 0.0F;
      this.method_09828(var1);

      for (PotionEffect var9 : var1) {
         if (!this.method_09814(var9)
            || this.minecraft.currentScreen instanceof CBModulesGui
            || this.minecraft.currentScreen instanceof ModulePlacementGui
            || this.minecraft.currentScreen instanceof CBProfileCreateGui) {
            boolean var12 = this.shouldBlink(var9.getDuration());
            boolean var13 = this.recoveredField2970.method_08908();
            int var14 = 0;
            int var15 = this.recoveredField2989.method_08908() ? 0 : (this.recoveredField2984.method_08908() ? 5 : 0);
            String var16 = (this.recoveredField2956.method_08908() ? StatCollector.translateToLocal(var9.getEffectName()) : "")
               + (this.recoveredField2980.method_08908() ? this.getLevelName(var9.getAmplifier()) : "");
            if (this.recoveredField2962.method_08908() && (var12 || !this.recoveredField2978.method_08908())) {
               var14 = this.minecraft.fontRendererObj.getStringWidth(var16) + (int)var7;
               int var17 = this.method_09812(this.recoveredField2983.method_08901(), this.recoveredField2967.getValue(), var9);
               if (var2 == CBPositionEnum.RIGHT) {
                  this.minecraft
                     .fontRendererObj
                     .drawString(
                        (var13 ? var16 : this.method_09808(var16)) + "§r",
                        this.method_28810() - var14,
                        (float)var3 + var15,
                        var17,
                        this.recoveredField2963.method_08908()
                     );
               } else if (var2 == CBPositionEnum.LEFT) {
                  this.minecraft
                     .fontRendererObj
                     .drawString((var13 ? var16 : this.method_09808(var16)) + "§r", var7, (float)var3 + var15, var17, this.recoveredField2963.method_08908());
               } else if (var2 == CBPositionEnum.CENTER) {
                  this.minecraft
                     .fontRendererObj
                     .drawString(
                        (var13 ? var16 : this.method_09808(var16)) + "§r",
                        this.method_28810() / 2.0F - var14 / 2 + var7,
                        (float)var3 + var15,
                        var17,
                        this.recoveredField2963.method_08908()
                     );
               }

               if (var14 > var4) {
                  var4 = var14;
               }
            }

            String var11 = Potion.getDurationString(var9);
            int var20 = this.recoveredField2989.method_08908() ? this.minecraft.fontRendererObj.getStringWidth(var11) + (int)var7 : 18;
            int var18 = this.recoveredField2962.method_08908() && !var16.isEmpty() ? 10 : (this.recoveredField2984.method_08908() ? 5 : 0);
            if (this.recoveredField2989.method_08908() && (var12 || !this.recoveredField2964.method_08908())) {
               int var19 = this.method_09812(this.recoveredField2969.method_08901(), this.recoveredField2982.getValue(), var9);
               if (var2 == CBPositionEnum.RIGHT) {
                  this.minecraft
                     .fontRendererObj
                     .drawString(var11 + "§r", this.method_28810() - var20, var3 + var18, var19, this.recoveredField2963.method_08908());
               } else if (var2 == CBPositionEnum.LEFT) {
                  this.minecraft.fontRendererObj.drawString(var11 + "§r", var7, var3 + var18, var19, this.recoveredField2963.method_08908());
               } else if (var2 == CBPositionEnum.CENTER) {
                  this.minecraft
                     .fontRendererObj
                     .drawString(var11 + "§r", this.method_28810() / 2.0F - var20 / 2 + var7, var3 + var18, var19, this.recoveredField2963.method_08908());
               }
            }

            Potion var10;
            if ((var10 = Potion.potionTypes[var9.getPotionID()]).hasStatusIcon()
               && this.recoveredField2984.method_08908()
               && (var12 || !this.recoveredField2958.method_08908())) {
               GL11.glColor4f(1.0F, 1.0F, 1.0F, this.method_09814(var9) ? 0.5F : 1.0F);
               this.minecraft.getTextureManager().bindTexture(this.recoveredField2961);
               int var21 = var10.getStatusIconIndex();
               if (var2 != CBPositionEnum.LEFT && (this.recoveredField2962.method_08908() || this.recoveredField2989.method_08908())) {
                  if (var2 == CBPositionEnum.RIGHT) {
                     RenderUtil.method_22065(this.method_28810() - 20.0F, var3, var21 % 8 * 18, 198 + var21 / 8 * 18, 18, 18);
                  } else if (var2 == CBPositionEnum.CENTER) {
                     RenderUtil.method_22065(
                        this.method_28810() / 2.0F - (this.recoveredField2962.method_08908() ? var14 : var20) / 2,
                        var3,
                        var21 % 8 * 18,
                        198 + var21 / 8 * 18,
                        18,
                        18
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
      float var2 = this.recoveredField2990.method_08905() * 10.0F;
      if (this.recoveredField2977.method_08908() && var1 <= this.recoveredField2968.method_08905() * 20.0F) {
         if (this.ticks > var2 * 2.0F) {
            this.ticks = 0;
         }

         return this.ticks <= var2;
      } else {
         return true;
      }
   }

   public void method_09828(List<PotionEffect> var1) {
      String var2 = this.recoveredField2987.method_08874();
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

      if (this.recoveredField2959.method_08908()) {
         Collections.reverse(var1);
      }
   }

   public boolean method_09814(PotionEffect var1) {
      if (this.method_09815(this.recoveredField2957, var1.getDuration(), this.recoveredField2974.method_08905() * 20.0F) && !var1.getIsPotionDurationMax()) {
         return true;
      } else if (this.recoveredField2971.method_08908() && var1.getIsPotionDurationMax()) {
         return true;
      } else if (this.method_09815(this.recoveredField2976, var1.getAmplifier(), this.recoveredField2955.method_08912() - 1)) {
         return true;
      } else if ((Boolean)this.recoveredField2960.getValue().equals("Only Helpful") && !Potion.potionTypes[var1.getPotionID()].isBadEffect()) {
         return true;
      } else {
         return this.recoveredField2960.getValue().equals("Only Harmful") && Potion.potionTypes[var1.getPotionID()].isBadEffect()
            ? true
            : this.recoveredField2973.method_08869().contains(var1.getPotionID());
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
      this.recoveredField2972 = new Setting(this, "label").setValue("Component Options").method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField2984 = new Setting(this, "Icon").setValue(true).method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField2962 = new Setting(this, "Effect Name").setValue(true).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField2956 = new Setting(this, "Effect Name Text")
         .setValue(true)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(this.recoveredField2962::method_08908);
      this.recoveredField2980 = new Setting(this, "Effect Amplifier Text")
         .setValue(true)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(this.recoveredField2962::method_08908);
      this.recoveredField2966 = new Setting(this, "Show Roman Numerals")
         .setValue(true)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> this.recoveredField2962.method_08908() && this.recoveredField2980.method_08908());
      this.recoveredField2985 = new Setting(this, "Show Level 1")
         .setValue(false)
         .method_08914(SettingsDetailLevel.ADVANCED)
         .method_08894(() -> this.recoveredField2962.method_08908() && this.recoveredField2980.method_08908());
      this.recoveredField2975 = new Setting(this, "Max Roman Numeral")
         .setValue(10)
         .setMinMax(1, 255)
         .method_08914(SettingsDetailLevel.ADVANCED)
         .method_08894(() -> this.recoveredField2962.method_08908() && this.recoveredField2980.method_08908() && this.recoveredField2966.method_08908());
      this.recoveredField2989 = new Setting(this, "Duration").setValue(true).method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField2951 = new Setting(this, "label").setValue("General Options").method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField2952 = new Setting(this, "Show While Typing").setValue(true).method_08914(SettingsDetailLevel.ADVANCED);
      this.recoveredField2953 = new Setting(this, "Show Potion info in inventory").setValue(false).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField2963 = new Setting(this, "Text Shadow")
         .setValue(true)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> this.recoveredField2962.method_08908() || this.recoveredField2989.method_08908());
      this.recoveredField2954 = new Setting(this, "Effect Spacing")
         .setValue(4.0F)
         .setMinMax(0.0F, 15.0F)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField2981 = new Setting(this, "label").setValue("Sorting Options").method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField2987 = new Setting(this, "Sorting Method")
         .setValue("Vanilla")
         .acceptedValues("Vanilla", "Alphabetical", "Duration", "Amplifier")
         .method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField2959 = new Setting(this, "Reverse Sorting").setValue(false).method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField2986 = new Setting(this, "label").setValue("Blink Options").method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField2977 = new Setting(this, "Blink").setValue(true).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField2978 = new Setting(this, "Make Effect Name Text Blink")
         .setValue(false)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> this.recoveredField2977.method_08908() && this.recoveredField2962.method_08908());
      this.recoveredField2958 = new Setting(this, "Make Effect Icon Blink")
         .setValue(false)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> this.recoveredField2977.method_08908() && this.recoveredField2984.method_08908());
      this.recoveredField2964 = new Setting(this, "Make Duration Text Blink")
         .setValue(true)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> this.recoveredField2977.method_08908() && this.recoveredField2989.method_08908());
      this.recoveredField2968 = new Setting(this, "Blink Duration")
         .setValue(10.0F)
         .setMinMax(2.0F, 60.0F)
         .method_08892("s")
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(this.recoveredField2977::method_08908);
      this.recoveredField2990 = new Setting(this, "Blink Speed")
         .setValue(1.0F)
         .setMinMax(0.2F, 3.5F)
         .method_08892("s")
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(this.recoveredField2977::method_08908);
      this.recoveredField2991 = new Setting(this, "label").setValue("Excluding Options").method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField2971 = new Setting(this, "Exclude Permanent Effects").setValue(false).method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField2957 = new Setting(this, "Exclude Set Duration", "Exclude all active effects above a set duration.")
         .setValue("OFF")
         .acceptedValues("OFF", "Effects Above", "Effects Below", "Effects At", "Effects Not At")
         .method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField2974 = new Setting(this, "Excluded Duration", "The duration effects are excluded")
         .setValue(30.0F)
         .setMinMax(2.0F, 90.0F)
         .method_08892("s")
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> !this.recoveredField2957.getValue().equals("OFF"));
      this.recoveredField2976 = new Setting(this, "Exclude Set Amplifier", "Exclude all active effects below a set amplifier.")
         .setValue("OFF")
         .acceptedValues("OFF", "Effects Above", "Effects Below", "Effects At", "Effects Not At")
         .method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField2955 = new Setting(this, "Excluded Amplifier", "The duration effects are excluded")
         .setValue(10)
         .setMinMax(0, 20)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> !this.recoveredField2976.getValue().equals("OFF"));
      this.recoveredField2960 = new Setting(this, "Exclude Helpful/Harmful Effects")
         .setValue("OFF")
         .acceptedValues("OFF", "Only Helpful", "Only Harmful")
         .method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField2973 = new Setting(this, "Exclude Specific Effects")
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
         .method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField2993 = new Setting(this, "label")
         .setValue("Color Options")
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> this.recoveredField2962.method_08908() || this.recoveredField2989.method_08908());
      this.recoveredField2970 = new Setting(this, "Override Name Color With Pack Format")
         .setValue(true)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> this.recoveredField2962.method_08908() && this.recoveredField2956.method_08908());
      this.recoveredField2967 = new Setting(this, "Color Name Based On")
         .setValue("Static")
         .acceptedValues("Static", "Effect", "Duration")
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(this.recoveredField2962::method_08908);
      this.recoveredField2982 = new Setting(this, "Color Duration Based On")
         .setValue("Static")
         .acceptedValues("Static", "Effect", "Duration")
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(this.recoveredField2989::method_08908);
      this.recoveredField2992 = new Setting(this, "Color Type")
         .setValue("Default")
         .acceptedValues("Default", "Potion Colors", "Color Codes", "Good/Bad", "Vibrant")
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> this.recoveredField2982.getValue().equals("Effect") || (Boolean)this.recoveredField2967.getValue().equals("Effect"));
      this.recoveredField2983 = new Setting(this, "Name Color")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> this.recoveredField2962.method_08908() && (Boolean)this.recoveredField2967.getValue().equals("Static"));
      this.recoveredField2969 = new Setting(this, "Duration Color")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> this.recoveredField2989.method_08908() && (Boolean)this.recoveredField2982.getValue().equals("Static"));
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/speed_icon.png"), 28, 28);
      this.method_28821("Displays your active potion effects.");
      this.method_28829("bspkrs", "jadedcat");
      this.method_28807("Potion Status", "Status Effects");
      this.method_28820(TickEvent.class, this::onTick);
      this.method_28820(HudPreviewDrawEvent.class, this::method_09813);
      this.method_28820(GuiDrawEvent.class, this::renderReal);
   }

   public void renderReal(GuiDrawEvent var1) {
      if (this.method_28866() && (!this.recoveredField3905 || this.minecraft.currentScreen instanceof CBModulesGui)) {
         GL11.glPushMatrix();
         if (this.recoveredField2952.method_08908() || !this.minecraft.ingameGUI.getChatGUI().getChatOpen()) {
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
      String var2 = this.recoveredField2956.method_08908() ? " " : "";
      if (var1 < 0) {
         var1 = 127 + Math.abs(128 + var1);
      }

      int var3 = var1 + 1;
      int var4 = this.recoveredField2985.method_08908() ? 0 : 1;
      if (this.recoveredField2966.method_08908() && var1 >= var4 && var1 <= this.recoveredField2975.method_08912()) {
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

   public void method_09813(HudPreviewDrawEvent var1) {
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
