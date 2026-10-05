package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.module.AbstractModule;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiSelectWorld$List;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.util.EnumChatFormatting;
import recovered.unidentified.UnidentifiedClass3613;

public class AutoTextModule extends AbstractModule {
   public List<Long> field_0003;
   public List<Setting> field_0004;
   public int field_0001;
   public UnidentifiedClass3613 field_0002;
   public String field_0007;
   public GuiSelectWorld$List field_0005;
   public InventoryCrafting field_0008;
   public String[] field_0006 = new String[]{"/skyblock", "/play", "/lobby", "/hub", "/spawn", "/main", "/leave", "/warp", "/rejoin"};
   public int field_0000;

   public String method_26045() {
      return this.field_0007;
   }

   public AutoTextModule() {
      super("Auto Text");
      this.field_0007 = "";
      this.field_0004 = new ArrayList<>();
      this.field_0003 = new ArrayList<>();
      this.field_0000 = 3;
      this.field_0001 = 1;
      this.setDefaultState(false);
      this.setPreviewLabel("Auto Text", 1.0F);
      this.method_28821("Send a command or message with a press of a key or button!");
      this.method_28823("The allowed commands are: " + Arrays.toString((Object[])this.field_0006).replaceAll("\\[", "").replaceAll("]", ""), "hypixel");
      this.field_0004.addAll(this.getSettingsList());
   }

   public void method_26044(String var1) {
      if (Minecraft.getMinecraft().isIntegratedServerRunning()) {
         Minecraft.getMinecraft().thePlayer.sendChatMessage(var1);
      } else {
         if (Minecraft.getMinecraft().getCurrentServerData().serverIP.contains("hypixel")) {
            if (this.method_26047(var1)) {
               Minecraft.getMinecraft().thePlayer.sendChatMessage(var1);
            } else {
               CheatBreaker.getInstance()
                  .getModuleManager()
                  .notifications
                  .queueNotification("error", EnumChatFormatting.RED + "\"" + var1 + "\" is not allowed on Hypixel.", 2851240039063694264L & 1209044920L);
            }
         } else {
            Minecraft.getMinecraft().thePlayer.sendChatMessage(var1);
         }
      }
   }

   public void method_26048(String var1) {
      this.field_0007 = var1;
   }

   public void method_26046() {
      if (this.field_0001 >= 50) {
         CheatBreaker.getInstance()
            .getModuleManager()
            .notifications
            .queueNotification("error", EnumChatFormatting.RED + "You have hit the limit of 50 hotkeys.", 8059027715331462025L & 1342231436L);
      } else {
         Setting var1 = new Setting(this, "Hot key " + this.field_0001).setValue("/Command").method_08887(0).method_08909(false);
         this.field_0004.add(var1);
         this.field_0001++;

         for (Setting var3 : this.getSettingsList()) {
            if (var3.method_08911().equalsIgnoreCase(var1.method_08911())) {
               return;
            }
         }

         this.getSettingsList().add(var1);
         CheatBreaker.getInstance().getConfigManager().method_25109();
      }
   }

   public boolean method_26047(String var1) {
      for (String var5 : this.field_0006) {
         if (var1.startsWith(var5)) {
            return true;
         }
      }

      return false;
   }
}
