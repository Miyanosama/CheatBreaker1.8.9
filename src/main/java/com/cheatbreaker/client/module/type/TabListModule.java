package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.mojang.authlib.GameProfile;
import java.util.List;
import java.util.regex.Pattern;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiPlayerTabOverlay;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.network.NetHandlerPlayClient;
import net.minecraft.client.network.NetworkPlayerInfo;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EnumPlayerModelParts;
import net.minecraft.scoreboard.IScoreObjectiveCriteria;
import net.minecraft.scoreboard.ScoreObjective;
import net.minecraft.scoreboard.Scoreboard;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldSettings;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.event.type.HudPreviewDrawEvent;
import com.cheatbreaker.client.util.player.PlayerNametagStyle;

public class TabListModule extends AbstractModule {
   public Setting recoveredField1206;
   public Setting recoveredField1207;
   public Setting recoveredField1208;
   public Setting recoveredField1209;
   public Pattern recoveredField1210 = Pattern.compile("(?i)§[0-689A-E]");
   public Setting recoveredField1211;
   public Setting recoveredField1212;
   public Setting recoveredField1213;
   public Setting recoveredField1214;
   public Setting recoveredField1215;
   public Setting recoveredField1216;
   public Setting recoveredField1217;
   public Setting recoveredField1218;
   public Setting recoveredField1219;
   public Setting recoveredField1220;
   public static ResourceLocation recoveredField1221 = new ResourceLocation("textures/gui/icons.png");
   public Setting recoveredField1222;
   public Setting recoveredField1223;
   public Setting recoveredField1224;
   public Setting recoveredField1225;
   public Setting recoveredField1226;
   public Setting recoveredField1227;
   public Setting recoveredField1228;
   public Setting recoveredField1229;
   public Setting recoveredField1230;
   public Setting recoveredField1231;

   public void method_20825(GuiDrawEvent var1) {
      if (this.method_28866() || this.recoveredField3905 && !(this.minecraft.currentScreen instanceof CBModulesGui)) {
         ScoreObjective var2 = this.minecraft.theWorld.Z().getObjectiveInDisplaySlot(0);
         GL11.glPushMatrix();
         this.scaleAndTranslate(var1.getResolution());
         ScaledResolution var3 = new ScaledResolution(this.minecraft);
         int var4 = var3.getScaledWidth();
         Scoreboard var5 = this.minecraft.theWorld.Z();
         ScoreObjective var6 = var5.getObjectiveInDisplaySlot(0);
         if (this.minecraft.gameSettings.recoveredField2691.isKeyDown()
            && (!this.minecraft.isIntegratedServerRunning() || this.minecraft.thePlayer.sendQueue.getPlayerInfoMap().size() > 1 || var2 != null)) {
            this.method_20823(0, var5, var6, 0, 0);
         }

         GL11.glPopMatrix();
      }
   }

   public String method_20833() {
      return null;
   }

   public String method_20818(String var1) {
      return var1 == null ? null : this.recoveredField1210.matcher(var1).replaceAll("§r");
   }

   public TabListModule() {
      super("Player List");
      this.setDefaultAnchor(CBGuiAnchor.MIDDLE_TOP);
      this.setDefaultTranslations(0.0F, 12.0F);
      this.recoveredField3912 = false;
      new Setting(this, "label").setValue("General Options").method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField1209 = new Setting(this, "Show Background").setValue(true).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField1226 = new Setting(this, "Show Row Background").setValue(true).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField1228 = new Setting(this, "Highlight Own Row").setValue(false).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField1206 = new Setting(this, "Highlight CheatBreaker Players' Row").setValue(false).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField1207 = new Setting(this, "Use Ping Color for Row Background")
         .setValue(false)
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> (Boolean)this.recoveredField1226.getValue());
      this.recoveredField1211 = new Setting(this, "Row Background Color")
         .setValue(553648127)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> (Boolean)this.recoveredField1226.getValue());
      this.recoveredField1230 = new Setting(this, "Self Row Color")
         .setValue(1084882944)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField1226.getValue() && (Boolean)this.recoveredField1228.getValue());
      this.recoveredField1212 = new Setting(this, "CheatBreaker Row Color")
         .setValue(1088045651)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField1226.getValue() && (Boolean)this.recoveredField1206.getValue());
      this.recoveredField1225 = new Setting(this, "Background Color")
         .setValue(Integer.MIN_VALUE)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.SIMPLE);
      new Setting(this, "label").setValue("Name Options").method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField1227 = new Setting(this, "Name Text Shadow").setValue(true).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField1224 = new Setting(this, "Show CheatBreaker Logo").setValue(true).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField1215 = new Setting(this, "Highlight Own Name").setValue(false).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField1222 = new Setting(this, "Highlight CheatBreaker Players' Name").setValue(false).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField1231 = new Setting(this, "Self Text Color")
         .setValue(-3399134)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField1215.getValue());
      this.recoveredField1229 = new Setting(this, "CheatBreaker Text Color")
         .setValue(-301706)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField1222.getValue());
      new Setting(this, "label")
         .setValue("Ping Options")
         .method_08894(() -> !this.minecraft.isIntegratedServerRunning())
         .method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField1214 = new Setting(this, "Show Ping")
         .setValue(true)
         .method_08894(() -> !this.minecraft.isIntegratedServerRunning())
         .method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField1219 = new Setting(this, "Show Ping as Number")
         .setValue(false)
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> (Boolean)this.recoveredField1214.getValue() && !this.minecraft.isIntegratedServerRunning());
      this.recoveredField1213 = new Setting(this, "Ping Text Shadow")
         .setValue(true)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(
            () -> (Boolean)this.recoveredField1214.getValue() && (Boolean)this.recoveredField1219.getValue() && !this.minecraft.isIntegratedServerRunning()
         );
      this.recoveredField1216 = new Setting(this, "Dynamic Ping Number Color")
         .setValue(true)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(
            () -> (Boolean)this.recoveredField1214.getValue() && (Boolean)this.recoveredField1219.getValue() && !this.minecraft.isIntegratedServerRunning()
         );
      this.recoveredField1220 = new Setting(this, "Static Ping Color")
         .setValue(-171)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.ADVANCED)
         .method_08894(
            () -> (Boolean)this.recoveredField1214.getValue()
               && (Boolean)this.recoveredField1219.getValue()
               && !(Boolean)this.recoveredField1216.getValue()
               && !this.minecraft.isIntegratedServerRunning()
         );
      this.recoveredField1223 = new Setting(this, "Low Ping Color")
         .setValue(-11141291)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.ADVANCED)
         .method_08894(
            () -> (Boolean)this.recoveredField1214.getValue()
               && (Boolean)this.recoveredField1219.getValue()
               && (Boolean)this.recoveredField1216.getValue()
               && !this.minecraft.isIntegratedServerRunning()
         );
      this.recoveredField1218 = new Setting(this, "Medium Ping Color")
         .setValue(-171)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.ADVANCED)
         .method_08894(
            () -> (Boolean)this.recoveredField1214.getValue()
               && (Boolean)this.recoveredField1219.getValue()
               && (Boolean)this.recoveredField1216.getValue()
               && !this.minecraft.isIntegratedServerRunning()
         );
      this.recoveredField1217 = new Setting(this, "High Ping Color")
         .setValue(-43691)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.ADVANCED)
         .method_08894(
            () -> (Boolean)this.recoveredField1214.getValue()
               && (Boolean)this.recoveredField1219.getValue()
               && (Boolean)this.recoveredField1216.getValue()
               && !this.minecraft.isIntegratedServerRunning()
         );
      this.recoveredField1208 = new Setting(this, "Extremely High Ping Color")
         .setValue(-5636096)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.ADVANCED)
         .method_08894(
            () -> (Boolean)this.recoveredField1214.getValue()
               && (Boolean)this.recoveredField1219.getValue()
               && (Boolean)this.recoveredField1216.getValue()
               && !this.minecraft.isIntegratedServerRunning()
         );
      this.method_28821("Customize the player list tab.");
      this.method_28807("Tab");
      this.method_28820(HudPreviewDrawEvent.class, this::method_20824);
      this.method_28820(GuiDrawEvent.class, this::method_20825);
   }

   public void method_20824(HudPreviewDrawEvent var1) {
      if (this.method_28866()) {
         GL11.glPushMatrix();
         this.scaleAndTranslate(var1.method_01054());
         ScaledResolution var2 = new ScaledResolution(this.minecraft);
         int var3 = var2.getScaledWidth();
         Scoreboard var4 = this.minecraft.theWorld.Z();
         Object var5 = null;
         ScoreObjective var6 = (ScoreObjective)(var5 != null ? var5 : var4.getObjectiveInDisplaySlot(1));
         this.method_20823(0, var4, var6, 0, 0);
         GL11.glPopMatrix();
      }
   }

   public void method_20823(int var1, Scoreboard var2, ScoreObjective var3, int var4, int var5) {
      NetHandlerPlayClient var6 = this.minecraft.thePlayer.sendQueue;
      List var7 = GuiPlayerTabOverlay.field_175252_a.sortedCopy(var6.getPlayerInfoMap());
      int var8 = 0;
      int var9 = 0;

      for (NetworkPlayerInfo var11 : (Iterable<NetworkPlayerInfo>)(Iterable<?>)(var7)) {
         int var12 = this.minecraft.fontRendererObj.getStringWidth(this.minecraft.ingameGUI.overlayPlayerList.getPlayerName(var11));
         var8 = Math.max(var8, var12);
         if (var3 != null && var3.getRenderType() != IScoreObjectiveCriteria.EnumRenderType.HEARTS) {
            var12 = this.minecraft.fontRendererObj.getStringWidth(" " + var2.getValueFromObjective(var11.getGameProfile().getName(), var3).getScorePoints());
            var9 = Math.max(var9, var12);
         }
      }

      var7 = var7.subList(0, Math.min(var7.size(), 80));
      int var36 = var7.size();
      int var37 = var36;

      int var39;
      for (var39 = 1; var37 > 20; var37 = (var36 + var39 - 1) / var39) {
         var39++;
      }

      boolean var13 = this.minecraft.isIntegratedServerRunning() || this.minecraft.getNetHandler().getNetworkManager().getIsencrypted();
      int var14;
      if (var3 != null) {
         if (var3.getRenderType() == IScoreObjectiveCriteria.EnumRenderType.HEARTS) {
            var14 = 90;
         } else {
            var14 = var9;
         }
      } else {
         var14 = 0;
      }

      int var15 = Math.min(var39 * ((var13 ? 9 : 0) + var8 + var14 + 13), var1 - 50) / var39;
      boolean var16 = false;
      int var17 = 10;
      int var18 = var15 * var39 + (var39 - 1) * 5;
      List var19 = null;
      List var20 = null;
      if (this.minecraft.ingameGUI.overlayPlayerList.header != null) {
         var19 = this.minecraft.fontRendererObj.listFormattedStringToWidth(this.minecraft.ingameGUI.overlayPlayerList.header.getFormattedText(), var1 - 50);

         for (String var22 : (Iterable<String>)(Iterable<?>)(var19)) {
            var18 = Math.max(var18, this.minecraft.fontRendererObj.getStringWidth(var22));
         }
      }

      if (this.minecraft.ingameGUI.overlayPlayerList.footer != null) {
         var20 = this.minecraft.fontRendererObj.listFormattedStringToWidth(this.minecraft.ingameGUI.overlayPlayerList.footer.getFormattedText(), var1 - 50);

         for (String var45 : (Iterable<String>)(Iterable<?>)(var20)) {
            var18 = Math.max(var18, this.minecraft.fontRendererObj.getStringWidth(var45));
         }
      }

      if (var19 != null) {
         Gui.a(
            var1 / 2 - var18 / 2 - 1, var17 - 1, var1 / 2 + var18 / 2 + 1, var17 + var19.size() * this.minecraft.fontRendererObj.FONT_HEIGHT, Integer.MIN_VALUE
         );

         for (String var46 : (Iterable<String>)(Iterable<?>)(var19)) {
            int var23 = this.minecraft.fontRendererObj.getStringWidth(var46);
            this.minecraft.fontRendererObj.drawStringWithShadow(var46, var1 / 2 - var23 / 2, var17, -1);
            var17 += this.minecraft.fontRendererObj.FONT_HEIGHT;
         }

         var17++;
      }

      Gui.a(var1 / 2 - var18 / 2 - 1, var17 - 1, var1 / 2 + var18 / 2 + 1, var17 + var37 * 9, Integer.MIN_VALUE);

      for (int var43 = 0; var43 < var36; var43++) {
         int var47 = var43 / var37;
         int var49 = var43 % var37;
         int var24 = 0;
         int var25 = var17 + var49 * 9;
         Gui.a(var24, var25, var24 + var15, var25 + 8, 553648127);
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.enableAlpha();
         GlStateManager.enableBlend();
         GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
         if (var43 < var7.size()) {
            NetworkPlayerInfo var26 = (NetworkPlayerInfo)var7.get(var43);
            java.lang.String var27 = this.minecraft.ingameGUI.overlayPlayerList.getPlayerName(var26);
            GameProfile var28 = var26.getGameProfile();
            if (var13) {
               EntityPlayer var29 = this.minecraft.theWorld.getPlayerEntityByUUID(var28.getId());
               boolean var30 = var29 != null
                  && var29.isWearing(EnumPlayerModelParts.CAPE)
                  && (var28.getName().equals("Dinnerbone") || var28.getName().equals("Grumm"));
               this.minecraft.getTextureManager().bindTexture(var26.getLocationSkin());
               int var31 = 8 + (var30 ? 8 : 0);
               int var32 = 8 * (var30 ? -1 : 1);
               Gui.drawScaledCustomSizeModalRect(var24, var25, 8.0F, var31, 8, var32, 8, 8, 64.0F, 64.0F);
               if (var29 != null && var29.isWearing(EnumPlayerModelParts.HAT)) {
                  int var33 = 8 + (var30 ? 8 : 0);
                  int var34 = 8 * (var30 ? -1 : 1);
                  Gui.drawScaledCustomSizeModalRect(var24, var25, 40.0F, var33, 8, var34, 8, 8, 64.0F, 64.0F);
               }

               var24 += 9;
            }

            if (var26.getGameType() == WorldSettings.GameType.SPECTATOR) {
               var27 = EnumChatFormatting.ITALIC + var27;
               this.minecraft.fontRendererObj.drawStringWithShadow((String)var27, var24, var25, -1862270977);
            } else {
               this.minecraft.fontRendererObj.drawStringWithShadow((String)var27, var24, var25, -1);
            }

            if (var3 != null && var26.getGameType() != WorldSettings.GameType.SPECTATOR) {
               int var52 = var24 + var8 + 1;
               int var53 = var52 + var14;
               if (var53 - var52 > 5) {
                  this.minecraft.ingameGUI.overlayPlayerList.drawScoreboardValues(var3, var25, var28.getName(), var52, var53, var26);
               }
            }

            this.minecraft.ingameGUI.overlayPlayerList.drawPing(var15, var24 - (var13 ? 9 : 0), var25, var26);
         }
      }

      if (var20 != null) {
         var17 = var17 + var37 * 9 + 1;
         Gui.a(
            var1 / 2 - var18 / 2 - 1, var17 - 1, var1 / 2 + var18 / 2 + 1, var17 + var20.size() * this.minecraft.fontRendererObj.FONT_HEIGHT, Integer.MIN_VALUE
         );

         for (String var48 : (Iterable<String>)(Iterable<?>)(var20)) {
            int var50 = this.minecraft.fontRendererObj.getStringWidth(var48);
            this.minecraft.fontRendererObj.drawStringWithShadow(var48, var1 / 2 - var50 / 2, var17, -1);
            var17 += this.minecraft.fontRendererObj.FONT_HEIGHT;
         }
      }

      this.method_28812(10.0F, 10.0F);
   }

   public boolean method_20834(String var1) {
      for (PlayerNametagStyle var3 : CheatBreaker.getInstance().method_19771().method_21030().values()) {
         if (var3.method_26420().equalsIgnoreCase(var1)) {
            return true;
         }
      }

      return false;
   }
}
