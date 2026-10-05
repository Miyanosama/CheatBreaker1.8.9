package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.event.type.TickEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import net.minecraft.client.gui.ChatLine;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.GuiNewChat;
import net.minecraft.client.renderer.tileentity.TileEntityPistonRenderer;
import net.minecraft.network.play.server.S0CPacketSpawnPlayer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.apache.log4j.NameValue;
import org.apache.log4j.lf5.util.DateFormatManager;
import org.lwjgl.opengl.GL11;
import recovered.unidentified.UnidentifiedClass0144;

public class ChatModule extends AbstractModule {
   public Setting field_0013;
   public Setting field_0015;
   public Setting field_0003;
   public Setting field_0005;
   public S0CPacketSpawnPlayer field_0024;
   public Setting field_0017;
   public Setting field_0030;
   public Setting field_0023;
   public Setting field_0001;
   public Setting field_0010;
   public String[][] field_0014;
   public TileEntityPistonRenderer field_0018;
   public Pattern field_0002 = Pattern.compile("(?i)§[0-689A-E]");
   public Setting field_0012;
   public Setting field_0009;
   public List<Long> field_0027;
   public Map<String, EnumChatFormatting> field_0008 = new HashMap<>();
   public Setting field_0016;
   public Setting field_0000;
   public int field_0011;
   public Setting field_0025;
   public Setting field_0019;
   public DateFormatManager field_0029;
   public NameValue field_0004;
   public Setting field_0021;
   public Setting field_0026;
   public Setting field_0006;
   public Setting field_0022;
   public Setting field_0028;
   public Setting field_0020;
   public Setting field_0007;

   public void method_04702() {
      this.field_0008.put("Purple", EnumChatFormatting.DARK_PURPLE);
      this.field_0008.put("Red", EnumChatFormatting.DARK_RED);
      this.field_0008.put("Gold", EnumChatFormatting.GOLD);
      this.field_0008.put("Cyan", EnumChatFormatting.DARK_AQUA);
      this.field_0008.put("Blue", EnumChatFormatting.DARK_BLUE);
      this.field_0008.put("Green", EnumChatFormatting.DARK_GREEN);
      this.field_0008.put("Aqua", EnumChatFormatting.AQUA);
   }

   public void method_04698(UnidentifiedClass0144 var1) {
      if (this.method_28866()) {
         if (this.field_0011 == 0 && this.minecraft.currentScreen instanceof CBModulesGui) {
            GL11.glPushMatrix();
            this.scaleAndTranslate(var1.method_01054());
            GuiNewChat var2 = new GuiNewChat(this.minecraft);
            StringBuilder var3 = new StringBuilder("§7§m");

            while (this.minecraft.fontRendererObj.getStringWidth(var3.toString()) * this.field_0006.getValue() / 100.0F < this.field_0020.method_08912() - 6) {
               var3.append("-");
            }

            var2.method_03291(new ChatComponentText(var3.toString()));
            var2.method_03291(new ChatComponentText(""));
            var2.method_03291(new ChatComponentText("Welcome to §cCheatBreaker§f!"));
            var2.method_03291(new ChatComponentText(""));

            for (String[] var7 : this.field_0014) {
               var2.method_03291(new ChatComponentText("- §c" + var7[0] + ": §f" + var7[1]));
            }

            var2.method_03291(new ChatComponentText(""));
            var2.method_03291(new ChatComponentText(var3.toString()));
            var2.method_03276(this.minecraft.ingameGUI.getUpdateCounter(), false);
            this.method_28812(
               ((Integer)this.field_0020.getValue()).intValue() + 4.0F,
               Math.min(this.getActionNotificationStackHeight(), var2.drawnChatLines.size() * 9) * (Float)this.field_0006.getValue() / 100.0F
            );
            GL11.glPopMatrix();
         }
      }
   }

   public void method_04699(TickEvent var1) {
      this.field_0027.removeIf(var0 -> var0 < System.currentTimeMillis() - (605144314L & 8135802022336659706L));
   }

   public int getActionNotificationStackHeight() {
      return this.minecraft.currentScreen instanceof GuiChat ? (Integer)this.field_0017.getValue() : (Integer)this.field_0021.getValue();
   }

   public void drawActionNotifications(GuiDrawEvent var1) {
      if (this.method_28866()) {
         int var2 = this.minecraft.ingameGUI.getChatGUI().getLineCount();
         int var4 = 0;

         for (int var3 = 0;
            var3 + this.minecraft.ingameGUI.getChatGUI().scrollPos < this.minecraft.ingameGUI.getChatGUI().drawnChatLines.size() && var3 < var2;
            var3++
         ) {
            ChatLine var5 = this.minecraft.ingameGUI.getChatGUI().drawnChatLines.get(var3);
            if (var5 != null && this.minecraft.ingameGUI.getUpdateCounter() - var5.getUpdatedCounter() < 200) {
               var4++;
            }
         }

         this.field_0011 = var4;
         if (this.field_0011 != 0 || !(this.minecraft.currentScreen instanceof CBModulesGui)) {
            GL11.glPushMatrix();
            this.scaleAndTranslate(var1.getResolution());
            int var6 = this.minecraft.currentScreen instanceof GuiChat
               ? this.minecraft.ingameGUI.getChatGUI().drawnChatLines.size() * 9
               : -(-this.field_0011 * 9);
            this.minecraft.ingameGUI.getChatGUI().method_03276(this.minecraft.ingameGUI.getUpdateCounter(), this.field_0013.method_08908());
            this.method_28812(
               ((Integer)this.field_0020.getValue()).intValue() + 4.0F * (Float)this.field_0006.getValue() / 100.0F,
               Math.min(this.getActionNotificationStackHeight(), var6) * (Float)this.field_0006.getValue() / 100.0F
            );
            GL11.glPopMatrix();
         }
      }
   }

   public ChatModule() {
      super("Chat");
      this.field_0027 = new ArrayList<>();
      this.field_0011 = 0;
      this.field_0014 = new String[][]{{"Discord", "www.discord.gg/kjgm75FZRC"}, {"GitHub", "https://github.com/TellinqBreaker"}};
      this.setDefaultAnchor(CBGuiAnchor.LEFT_BOTTOM);
      this.setDefaultTranslations(0.0F, -26.0F);
      this.field_0020 = false;
      this.field_0015 = new Setting(this, "Master Opacity")
         .setValue(100.0F)
         .setMinMax(0.05F, 100.0F)
         .method_08892("%")
         .method_08914(SettingsDetailLevel.field_0000);
      this.field_0016 = new Setting(this, "Unlimited Chat").setValue(false).method_08914(SettingsDetailLevel.field_0003);
      this.field_0012 = new Setting(this, "Clear Chat on Relog").setValue(true).method_08914(SettingsDetailLevel.field_0003);
      new Setting(this, "label").setValue("Background Options").method_08914(SettingsDetailLevel.field_0000);
      this.field_0001 = new Setting(this, "Show Background", "Draw a background behind chat.")
         .setValue("ON")
         .acceptedValues("ON", "While Typing", "OFF")
         .method_08914(SettingsDetailLevel.field_0000);
      this.field_0019 = new Setting(this, "Show Input Field Background", "Draw a background behind the chat text input field.")
         .setValue(true)
         .method_08914(SettingsDetailLevel.field_0000);
      this.field_0010 = new Setting(this, "Background Width")
         .setValue("Full")
         .acceptedValues("Full", "Compact")
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> !this.field_0001.getValue().equals("OFF"));
      this.field_0020 = new Setting(this, "Width")
         .setValue(GuiNewChat.calculateChatboxHeight(this.minecraft.gameSettings.chatWidth))
         .setMinMax(40, 320)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(this::method_28866);
      this.field_0017 = new Setting(this, "Focused Height")
         .setValue(GuiNewChat.calculateChatboxHeight(this.minecraft.gameSettings.chatHeightFocused))
         .setMinMax(20, 180)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(this::method_28866);
      this.field_0021 = new Setting(this, "Unfocused Height")
         .setValue(GuiNewChat.calculateChatboxHeight(this.minecraft.gameSettings.chatHeightUnfocused))
         .setMinMax(20, 180)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(this::method_28866);
      this.field_0026 = new Setting(this, "Background Color")
         .setValue(Integer.MIN_VALUE)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> !this.field_0001.getValue().equals("OFF") || (Boolean)this.field_0019.getValue());
      new Setting(this, "label").setValue("Text Options").method_08914(SettingsDetailLevel.field_0000);
      this.field_0007 = new Setting(this, "Text Shadow").setValue(true).method_08914(SettingsDetailLevel.field_0000);
      this.field_0030 = new Setting(this, "Strip Formatting Colors").setValue(false).method_08914(SettingsDetailLevel.field_0003);
      this.field_0003 = new Setting(this, "Text Color")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> this.field_0030.method_08908());
      this.field_0005 = new Setting(this, "Text Opacity")
         .setValue(100.0F)
         .setMinMax(0.0F, 100.0F)
         .method_08892("%")
         .method_08914(SettingsDetailLevel.field_0000);
      this.field_0006 = new Setting(this, "Text Scale")
         .setValue(100.0F)
         .setMinMax(0.0F, 100.0F)
         .method_08892("%")
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(this::method_28866);
      new Setting(this, "label").setValue("Animation Options").method_08914(SettingsDetailLevel.field_0003);
      this.field_0013 = new Setting(this, "Smooth Chat").setValue(false).method_08914(SettingsDetailLevel.field_0003);
      this.field_0028 = new Setting(this, "Smooth Chat Speed")
         .setValue(8.0F)
         .setMinMax(0.25F, 10.0F)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0013.getValue());
      new Setting(this, "label").setValue("Highlighting Options");
      this.field_0009 = new Setting(this, "Highlight Own Name").setValue(false).method_08914(SettingsDetailLevel.field_0000);
      this.method_04702();
      ArrayList var1 = new ArrayList<>(this.field_0008.keySet());
      var1.add("None");
      this.field_0000 = new Setting(this, "Highlight Colour")
         .setValue("None")
         .acceptedValues(var1.toArray(new String[0]))
         .method_08894(() -> this.field_0009.method_08908());
      this.field_0022 = new Setting(this, "Play Notification Sound")
         .setValue(false)
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(this.field_0009::method_08908);
      new Setting(this, "label").setValue("Position Options").method_08914(SettingsDetailLevel.field_0003).method_08894(() -> !this.method_28866());
      this.field_0025 = new Setting(this, "Chat Height Position")
         .setValue(0.0F)
         .setMinMax(-14.0F, 32.0F)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> !this.method_28866());
      this.method_28821("Move and customize the Minecraft chat to your liking.");
      this.method_28829("LlamaLad7 (Smooth Chat)");
      this.method_28820(UnidentifiedClass0144.class, this::method_04698);
      this.method_28820(GuiDrawEvent.class, this::drawActionNotifications);
      this.method_28820(TickEvent.class, this::method_04699);
      this.method_04702();
   }

   public EnumChatFormatting method_04704() {
      return this.field_0008.get(this.field_0000.method_08874());
   }

   public String method_04695(String var1) {
      return var1 == null ? null : this.field_0002.matcher(var1).replaceAll("§r");
   }
}
