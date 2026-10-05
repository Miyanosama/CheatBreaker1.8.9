package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.module.ModuleRule;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import java.util.Collection;
import java.util.regex.Pattern;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.Score;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass0144;
import recovered.unidentified.UnidentifiedClass1798;
import recovered.unidentified.UnidentifiedClass1883;

public class ScoreboardModule extends AbstractModule {
   public Pattern field_0007 = Pattern.compile("(?i)§[0-689A-E]");
   public Setting field_0009;
   public Setting field_0002;
   public Setting field_0003;
   public Setting field_0013;
   public UnidentifiedClass1883 field_0010;
   public Setting field_0015;
   public Setting field_0012;
   public static ModuleRule rule = ModuleRule.MINIMAP_NOT_ALLOWED;
   public Setting field_0005;
   public Setting field_0008;
   public Setting field_0011;
   public Setting field_0001;
   public Setting field_0006;
   public Setting field_0004;
   public Setting field_0014;

   public void method_03079(ScoreObjective var1, int var2, int var3, FontRenderer var4, float var5) {
      GlStateManager.enableBlend();
      Scoreboard var6 = var1.getScoreboard();
      Collection var7 = var6.getSortedScores(var1);
      boolean var8 = this.method_03075();
      boolean var9 = (Boolean)this.field_0001.getValue();
      if (var7.size() <= 15) {
         int var10 = var4.getStringWidth(var1.getDisplayName());
         int var11 = var10 + 16;

         for (Score var13 : var7) {
            ScorePlayerTeam var14 = var6.getPlayersTeam(var13.getPlayerName());
            String var15 = ScorePlayerTeam.formatPlayerName(var14, var13.getPlayerName()) + ": " + EnumChatFormatting.RED + var13.getScorePoints();
            var10 = Math.max(var10, var4.getStringWidth(var15));
         }

         byte var26 = 0;
         byte var27 = 3;
         byte var28 = 0;
         int var29 = 0;
         float var16 = 0.0F;
         float var17 = this.method_03075() ? -4.0F + (Float)this.field_0002.getValue() : 0.0F;

         for (Score var19 : var7) {
            ScorePlayerTeam var20 = var6.getPlayersTeam(var19.getPlayerName());
            String var21 = ScorePlayerTeam.formatPlayerName(var20, var19.getPlayerName());
            String var22 = "" + var19.getScorePoints();
            var29++;
            int var23 = var26 - var29 * var4.FONT_HEIGHT;
            int var24 = var10 + var27 + 6;
            if (var24 < var11) {
               var24 = var11;
            }

            if ((Boolean)this.field_0011.getValue()) {
               Gui.drawRect(var28 - 2 + (var8 ? 14 : 0), var23, var24 + var17, var23 + var4.FONT_HEIGHT, this.field_0008.method_08901());
            }

            var16 = var24 - (var28 - 2 + (var8 ? 18.0F - (Float)this.field_0002.getValue() : 0.0F));
            GlStateManager.enableBlend();
            var4.drawString(
               var9 ? this.method_03076(var21) : var21,
               var28 + (var8 ? 14.0F + (Float)this.field_0002.getValue() / 2.0F : 0.0F),
               var23,
               var9 ? this.field_0003.method_08901() : -1,
               (Boolean)this.field_0015.getValue()
            );
            if (!var8) {
               var4.drawString(var22, var24 - var4.getStringWidth(var22) - 2, var23, this.field_0013.method_08901(), (Boolean)this.field_0015.getValue());
            }

            if (var29 == var7.size()) {
               String var25 = var1.getDisplayName();
               if ((Boolean)this.field_0011.getValue()) {
                  if (this.field_0009.method_08908()) {
                     Gui.drawRect(var28 - 2 + (var8 ? 14 : 0), var23 - var4.FONT_HEIGHT - 1, var24 + var17, var23 - 1, this.field_0014.method_08901());
                  }

                  Gui.drawRect(var28 - 2 + (var8 ? 14 : 0), var23 - 1, var24 + var17, var23, this.field_0008.method_08901());
               }

               if (this.field_0009.method_08908()) {
                  var4.drawString(
                     var9 ? this.method_03076(var25) : var25,
                     var28 + var10 / 2.0F - var4.getStringWidth(var25) / 2.0F + (var8 ? 12.0F + (Float)this.field_0002.getValue() / 2.0F : 0.0F),
                     var23 - var4.FONT_HEIGHT,
                     var9 ? this.field_0003.method_08901() : -1,
                     (Boolean)this.field_0015.getValue()
                  );
               }

               GlStateManager.disableBlend();
            }
         }

         int var30 = var10 + var27 + 6;
         if (var30 < var11) {
            var30 = var11;
         }

         if ((Boolean)this.field_0012.getValue()) {
            float var31 = (Float)this.field_0005.getValue();
            Gui.method_00886(
               var28 - 2 + (var8 ? 14 : 0) - var31,
               -(var7.size() * var4.FONT_HEIGHT + 10) - var31,
               var30 + var31 + var17,
               0.0F + var31,
               var31,
               this.field_0004.method_08901()
            );
         }

         this.method_28812(var16, var7.size() * var4.FONT_HEIGHT + (this.field_0009.method_08908() ? 10 : 1));
      }
   }

   public void method_03080(UnidentifiedClass1798 var1) {
      if (this.method_28866() && (!this.field_0043 || this.minecraft.currentScreen instanceof CBModulesGui)) {
         GL11.glPushMatrix();
         this.scaleAndTranslate(var1.method_12440());
         GL11.glTranslatef(this.method_03075() ? -12.0F : 2.0F, this.field_0012, 0.0F);
         ScoreObjective var2 = this.minecraft.theWorld.Z().getObjectiveInDisplaySlot(1);
         if (var2 != null) {
            this.method_03079(
               var2, var1.method_12440().getScaledHeight(), var1.method_12440().getScaledWidth(), this.minecraft.fontRendererObj, this.method_28770()
            );
         }

         GL11.glPopMatrix();
      }
   }

   public void method_03078(UnidentifiedClass0144 var1) {
      if (this.method_28866()) {
         if (this.minecraft.theWorld.Z().getObjectiveInDisplaySlot(1) == null) {
            GL11.glPushMatrix();
            this.scaleAndTranslate(var1.method_01054());
            GL11.glTranslatef(this.method_03075() ? -12.0F : 2.0F, this.field_0012, 0.0F);
            Scoreboard var2 = new Scoreboard();
            ScoreObjective var3 = new ScoreObjective(var2, "CheatBreaker", IScoreObjectiveCriteria.DUMMY);
            var3.setDisplayName(
               EnumChatFormatting.RED + "" + EnumChatFormatting.BOLD + "Cheat" + EnumChatFormatting.WHITE + "" + EnumChatFormatting.BOLD + "Breaker"
            );
            var2.getValueFromObjective("Steve", var3);
            var2.getValueFromObjective("Alex", var3);
            this.method_03079(
               var3, var1.method_01054().getScaledHeight(), var1.method_01054().getScaledWidth(), this.minecraft.fontRendererObj, this.method_28770()
            );
            GL11.glPopMatrix();
         }
      }
   }

   public boolean method_03075() {
      return rule == ModuleRule.MINIMAP_NOT_ALLOWED ? (Boolean)this.field_0006.getValue() : rule == ModuleRule.field_0003;
   }

   public String method_03076(String var1) {
      return var1 == null ? null : this.field_0007.matcher(var1).replaceAll("§r");
   }

   public ScoreboardModule() {
      super("Scoreboard");
      this.setDefaultAnchor(CBGuiAnchor.RIGHT_MIDDLE);
      new Setting(this, "label").setValue("Background Options");
      this.field_0011 = new Setting(this, "Show Background").setValue(true).method_08914(SettingsDetailLevel.field_0000);
      this.field_0002 = new Setting(this, "Background Width Padding")
         .setValue(4.0F)
         .setMinMax(0.0F, 10.0F)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0012 = new Setting(this, "Show Border").setValue(false).method_08914(SettingsDetailLevel.field_0000);
      this.field_0005 = new Setting(this, "Border Thickness")
         .setValue(1.0F)
         .setMinMax(0.25F, 3.0F)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0012.getValue() && (Boolean)this.field_0011.getValue());
      new Setting(this, "label").setValue("General Options").method_08914(SettingsDetailLevel.field_0000);
      this.field_0006 = new Setting(this, "Remove Scoreboard numbers").setValue(true).method_08914(SettingsDetailLevel.field_0000);
      this.field_0009 = new Setting(this, "Show Scorebaord Title").setValue(true);
      this.field_0015 = new Setting(this, "Text Shadow").setValue(false).method_08914(SettingsDetailLevel.field_0000);
      new Setting(this, "label").setValue("Color Options").method_08914(SettingsDetailLevel.field_0000);
      this.field_0001 = new Setting(this, "Custom Text Color").setValue(false).method_08914(SettingsDetailLevel.field_0003);
      this.field_0003 = new Setting(this, "Text Color")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0001.getValue());
      this.field_0013 = new Setting(this, "Scoreboard Numbers Color")
         .setValue(-43691)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> !(Boolean)this.field_0006.getValue());
      this.field_0014 = new Setting(this, "Top Background Color")
         .setValue(1610612736)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> (Boolean)this.field_0011.getValue());
      this.field_0008 = new Setting(this, "Background Color")
         .setValue(1342177280)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> (Boolean)this.field_0011.getValue());
      this.field_0004 = new Setting(this, "Border Color")
         .setValue(Integer.MIN_VALUE)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> (Boolean)this.field_0012.getValue());
      this.method_28821("Move and customize the Minecraft scoreboard to your liking.");
      this.method_28807("Sidebar");
      this.method_28820(UnidentifiedClass0144.class, this::method_03078);
      this.method_28820(UnidentifiedClass1798.class, this::method_03080);
      this.setDefaultState(true);
   }
}
