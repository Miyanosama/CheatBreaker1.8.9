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
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.event.type.HudPreviewDrawEvent;

public class ChatModule extends AbstractModule {
   public Setting recoveredField814;
   public Setting recoveredField815;
   public Setting recoveredField816;
   public Setting recoveredField817;
   public Setting recoveredField818;
   public Setting recoveredField819;
   public Setting recoveredField820;
   public Setting recoveredField821;
   public String[][] recoveredField822;
   public Pattern recoveredField823 = Pattern.compile("(?i)§[0-689A-E]");
   public Setting recoveredField824;
   public Setting recoveredField825;
   public List<Long> recoveredField826;
   public Map<String, EnumChatFormatting> recoveredField827 = new HashMap<>();
   public Setting recoveredField828;
   public Setting recoveredField829;
   public int recoveredField830;
   public Setting recoveredField831;
   public Setting recoveredField832;
   public Setting recoveredField833;
   public Setting recoveredField834;
   public Setting recoveredField835;
   public Setting recoveredField836;
   public Setting recoveredField837;
   public Setting recoveredField838;
   public Setting recoveredField839;

   public void method_04702() {
      this.recoveredField827.put("Purple", EnumChatFormatting.DARK_PURPLE);
      this.recoveredField827.put("Red", EnumChatFormatting.DARK_RED);
      this.recoveredField827.put("Gold", EnumChatFormatting.GOLD);
      this.recoveredField827.put("Cyan", EnumChatFormatting.DARK_AQUA);
      this.recoveredField827.put("Blue", EnumChatFormatting.DARK_BLUE);
      this.recoveredField827.put("Green", EnumChatFormatting.DARK_GREEN);
      this.recoveredField827.put("Aqua", EnumChatFormatting.AQUA);
   }

   public void method_04698(HudPreviewDrawEvent var1) {
      if (this.method_28866()) {
         if (this.recoveredField830 == 0 && this.minecraft.currentScreen instanceof CBModulesGui) {
            GL11.glPushMatrix();
            this.scaleAndTranslate(var1.method_01054());
            GuiNewChat var2 = new GuiNewChat(this.minecraft);
            StringBuilder var3 = new StringBuilder("§7§m");

            while (
               this.minecraft.fontRendererObj.getStringWidth(var3.toString()) * (Float)this.recoveredField835.getValue() / 100.0F
                  < this.recoveredField838.method_08912() - 6
            ) {
               var3.append("-");
            }

            var2.method_03291(new ChatComponentText(var3.toString()));
            var2.method_03291(new ChatComponentText(""));
            var2.method_03291(new ChatComponentText("Welcome to §cCheatBreaker§f!"));
            var2.method_03291(new ChatComponentText(""));

            for (String[] var7 : this.recoveredField822) {
               var2.method_03291(new ChatComponentText("- §c" + var7[0] + ": §f" + var7[1]));
            }

            var2.method_03291(new ChatComponentText(""));
            var2.method_03291(new ChatComponentText(var3.toString()));
            var2.method_03276(this.minecraft.ingameGUI.getUpdateCounter(), false);
            this.method_28812(
               ((Integer)this.recoveredField838.getValue()).intValue() + 4.0F,
               Math.min(this.getActionNotificationStackHeight(), var2.drawnChatLines.size() * 9) * (Float)this.recoveredField835.getValue() / 100.0F
            );
            GL11.glPopMatrix();
         }
      }
   }

   public void method_04699(TickEvent var1) {
      this.recoveredField826.removeIf(var0 -> var0 < System.currentTimeMillis() - 250L);
   }

   public int getActionNotificationStackHeight() {
      return this.minecraft.currentScreen instanceof GuiChat ? (Integer)this.recoveredField818.getValue() : (Integer)this.recoveredField833.getValue();
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

         this.recoveredField830 = var4;
         if (this.recoveredField830 != 0 || !(this.minecraft.currentScreen instanceof CBModulesGui)) {
            GL11.glPushMatrix();
            this.scaleAndTranslate(var1.getResolution());
            int var6 = this.minecraft.currentScreen instanceof GuiChat
               ? this.minecraft.ingameGUI.getChatGUI().drawnChatLines.size() * 9
               : -(-this.recoveredField830 * 9);
            this.minecraft.ingameGUI.getChatGUI().method_03276(this.minecraft.ingameGUI.getUpdateCounter(), this.recoveredField814.method_08908());
            this.method_28812(
               ((Integer)this.recoveredField838.getValue()).intValue() + 4.0F * (Float)this.recoveredField835.getValue() / 100.0F,
               Math.min(this.getActionNotificationStackHeight(), var6) * (Float)this.recoveredField835.getValue() / 100.0F
            );
            GL11.glPopMatrix();
         }
      }
   }

   public ChatModule() {
      super("Chat");
      this.recoveredField826 = new ArrayList<>();
      this.recoveredField830 = 0;
      this.recoveredField822 = new String[][]{{"Discord", "www.discord.gg/kjgm75FZRC"}, {"GitHub", "https://github.com/TellinqBreaker"}};
      this.setDefaultAnchor(CBGuiAnchor.LEFT_BOTTOM);
      this.setDefaultTranslations(0.0F, -26.0F);
      this.recoveredField3912 = false;
      this.recoveredField815 = new Setting(this, "Master Opacity")
         .setValue(100.0F)
         .setMinMax(0.05F, 100.0F)
         .method_08892("%")
         .method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField828 = new Setting(this, "Unlimited Chat").setValue(false).method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField824 = new Setting(this, "Clear Chat on Relog").setValue(true).method_08914(SettingsDetailLevel.MEDIUM);
      new Setting(this, "label").setValue("Background Options").method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField820 = new Setting(this, "Show Background", "Draw a background behind chat.")
         .setValue("ON")
         .acceptedValues("ON", "While Typing", "OFF")
         .method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField832 = new Setting(this, "Show Input Field Background", "Draw a background behind the chat text input field.")
         .setValue(true)
         .method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField821 = new Setting(this, "Background Width")
         .setValue("Full")
         .acceptedValues("Full", "Compact")
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> !this.recoveredField820.getValue().equals("OFF"));
      this.recoveredField838 = new Setting(this, "Width")
         .setValue(GuiNewChat.calculateChatboxHeight(this.minecraft.gameSettings.chatWidth))
         .setMinMax(40, 320)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(this::method_28866);
      this.recoveredField818 = new Setting(this, "Focused Height")
         .setValue(GuiNewChat.calculateChatboxHeight(this.minecraft.gameSettings.chatHeightFocused))
         .setMinMax(20, 180)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(this::method_28866);
      this.recoveredField833 = new Setting(this, "Unfocused Height")
         .setValue(GuiNewChat.calculateChatboxHeight(this.minecraft.gameSettings.chatHeightUnfocused))
         .setMinMax(20, 180)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(this::method_28866);
      this.recoveredField834 = new Setting(this, "Background Color")
         .setValue(Integer.MIN_VALUE)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> !this.recoveredField820.getValue().equals("OFF") || (Boolean)this.recoveredField832.getValue());
      new Setting(this, "label").setValue("Text Options").method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField839 = new Setting(this, "Text Shadow").setValue(true).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField819 = new Setting(this, "Strip Formatting Colors").setValue(false).method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField816 = new Setting(this, "Text Color")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> this.recoveredField819.method_08908());
      this.recoveredField817 = new Setting(this, "Text Opacity")
         .setValue(100.0F)
         .setMinMax(0.0F, 100.0F)
         .method_08892("%")
         .method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField835 = new Setting(this, "Text Scale")
         .setValue(100.0F)
         .setMinMax(0.0F, 100.0F)
         .method_08892("%")
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(this::method_28866);
      new Setting(this, "label").setValue("Animation Options").method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField814 = new Setting(this, "Smooth Chat").setValue(false).method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField837 = new Setting(this, "Smooth Chat Speed")
         .setValue(8.0F)
         .setMinMax(0.25F, 10.0F)
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> (Boolean)this.recoveredField814.getValue());
      new Setting(this, "label").setValue("Highlighting Options");
      this.recoveredField825 = new Setting(this, "Highlight Own Name").setValue(false).method_08914(SettingsDetailLevel.SIMPLE);
      this.method_04702();
      ArrayList var1 = new ArrayList<>(this.recoveredField827.keySet());
      var1.add("None");
      this.recoveredField829 = new Setting(this, "Highlight Colour")
         .setValue("None")
         .acceptedValues((String[])var1.toArray(new String[0]))
         .method_08894(() -> this.recoveredField825.method_08908());
      this.recoveredField836 = new Setting(this, "Play Notification Sound")
         .setValue(false)
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(this.recoveredField825::method_08908);
      new Setting(this, "label").setValue("Position Options").method_08914(SettingsDetailLevel.MEDIUM).method_08894(() -> !this.method_28866());
      this.recoveredField831 = new Setting(this, "Chat Height Position")
         .setValue(0.0F)
         .setMinMax(-14.0F, 32.0F)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.MEDIUM)
         .method_08894(() -> !this.method_28866());
      this.method_28821("Move and customize the Minecraft chat to your liking.");
      this.method_28829("LlamaLad7 (Smooth Chat)");
      this.method_28820(HudPreviewDrawEvent.class, this::method_04698);
      this.method_28820(GuiDrawEvent.class, this::drawActionNotifications);
      this.method_28820(TickEvent.class, this::method_04699);
      this.method_04702();
   }

   public EnumChatFormatting method_04704() {
      return this.recoveredField827.get(this.recoveredField829.method_08874());
   }

   public String method_04695(String var1) {
      return var1 == null ? null : this.recoveredField823.matcher(var1).replaceAll("§r");
   }
}
