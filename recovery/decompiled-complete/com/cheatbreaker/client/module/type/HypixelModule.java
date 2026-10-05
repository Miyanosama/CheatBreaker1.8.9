package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.module.AbstractModule;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.ai.EntityAIAvoidEntity$1;
import net.minecraft.item.crafting.RecipeRepairItem;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.Teleporter$PortalPosition;
import org.apache.commons.io.IOUtils;
import recovered.unidentified.UnidentifiedClass0026;

public class HypixelModule extends AbstractModule {
   public Setting field_0013;
   public Setting field_0015;
   public Setting field_0003;
   public Setting field_0005;
   public EntityAIAvoidEntity$1 field_0024;
   public List<String> field_0017 = new ArrayList<>();
   public Setting field_0030;
   public Setting field_0023;
   public Setting field_0001;
   public Pattern field_0010;
   public Setting field_0014;
   public Setting field_0018;
   public Setting field_0002;
   public Setting field_0012;
   public Setting field_0009;
   public Pattern field_0027;
   public Setting field_0008;
   public Setting field_0016;
   public Setting field_0000;
   public Teleporter$PortalPosition field_0011;
   public Pattern field_0025 = Pattern.compile(
      "§m----------------------------------------------------Friend request from (?<name>.+)\\[ACCEPT\\] - \\[DENY\\] - \\[IGNORE\\].*"
   );
   public Setting field_0019;
   public Setting field_0029;
   public Setting field_0004;
   public Setting field_0021;
   public Setting field_0026;
   public Setting field_0006;
   public Setting field_0022;
   public RecipeRepairItem field_0028;
   public Pattern field_0020;
   public String[] field_0007;

   public void method_25694(String var1, float var2) {
      new Thread(() -> {
         try {
            Thread.sleep((long)var2 * (-7041994254857271320L & 7041994252859024366L));
         } catch (InterruptedException var4) {
            var4.printStackTrace();
         }

         this.minecraft.thePlayer.sendChatMessage(var1);
      }).start();
   }

   public HypixelModule() {
      super("Hypixel Collection");
      this.field_0027 = Pattern.compile("BED DESTRUCTION > Your Bed .*");
      this.field_0007 = new String[]{"You have been eliminated!", "Reward Summary"};
      this.field_0010 = Pattern.compile("Guild > (?<name>.+) joined\\..*");
      this.field_0020 = Pattern.compile("Friend > (?<name>.+) joined\\..*");
      this.setDefaultState(false);
      this.field_0004 = new Setting(this, "label").setValue("Auto Command Options");
      this.field_0012 = new Setting(this, "Auto Tip").setValue(false);
      this.field_0003 = new Setting(this, "Auto Friend").setValue(false);
      this.field_0022 = new Setting(this, "Auto Friend Mode").setValue("Accept").acceptedValues("Accept", "Deny", "Ignore");
      this.field_0008 = new Setting(this, "Auto Friend Delay")
         .method_08892("s")
         .setValue(0.0F)
         .setMinMax(0.0F, 15.0F)
         .method_08894(this.field_0003::method_08908);
      this.field_0001 = new Setting(this, "label").setValue("Auto Chat Options");
      this.field_0002 = new Setting(this, "Auto GG").setValue(false);
      this.field_0018 = new Setting(this, "Send GG Message Delay")
         .method_08892("s")
         .setValue(0.0F)
         .setMinMax(0.0F, 5.0F)
         .method_08894(this.field_0002::method_08908);
      this.field_0023 = new Setting(this, "Auto GG String")
         .setValue("gg")
         .method_08894(this.field_0002::method_08908)
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0015 = new Setting(this, "Auto GL").setValue(false);
      this.field_0021 = new Setting(this, "Send GL Message Delay")
         .method_08892("s")
         .setValue(0.0F)
         .setMinMax(0.0F, 15.0F)
         .method_08894(this.field_0015::method_08908);
      this.field_0019 = new Setting(this, "Auto GL String")
         .setValue("gl")
         .method_08894(this.field_0015::method_08908)
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0014 = new Setting(this, "Auto WB").setValue(false);
      this.field_0030 = new Setting(this, "Welcome Back Friends").setValue(true).method_08894(this.field_0014::method_08908);
      this.field_0029 = new Setting(this, "Welcome Back Guild Members").setValue(true).method_08894(this.field_0014::method_08908);
      this.field_0000 = new Setting(this, "Send WB Message Delay")
         .method_08892("s")
         .setValue(2.0F)
         .setMinMax(2.0F, 15.0F)
         .method_08894(this.field_0014::method_08908);
      this.field_0009 = new Setting(this, "Auto WB String")
         .setValue("Welcome back %USER%!")
         .method_08894(this.field_0014::method_08908)
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0005 = new Setting(this, "label").setValue("Chat Filter Options");
      this.field_0026 = new Setting(this, "Hide GG").setValue(false);
      this.field_0006 = new Setting(this, "Hide GL").setValue(false);
      this.field_0016 = new Setting(this, "label").setValue("Bed Wars Options");
      this.field_0013 = new Setting(this, "Hardcore Hearts", "Changes your hearts to show hardcore hearts when you lose your own bed.")
         .setValue(false)
         .method_08914(SettingsDetailLevel.field_0000);
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/hypixel.png"), 21, 40);
      this.method_28821("A collection of mods made for the Hypixel Network.");
      this.method_28829("2Pi (Auto GG, Auto Friend, Auto Tip)", "Sk1er (Auto GL, Hardcore Hearts)", "Maximusbarcz (Auto WB)");
      this.method_28820(UnidentifiedClass0026.class, this::method_25691);
      this.method_25693();
   }

   public void method_25693() {
      new Thread(() -> {
         try {
            String var1 = IOUtils.toString(new URL("https://gist.githubusercontent.com/minemanpi/72c38b0023f5062a5f3eba02a5132603/raw/triggers.txt"));
            this.field_0017 = new ArrayList<>(Arrays.asList(var1.split("\n")));
         } catch (Exception var2) {
            var2.printStackTrace();
         }
      }).start();
   }

   public void method_25691(UnidentifiedClass0026 var1) {
      String var3 = var1.method_00228();
      if (this.minecraft.getCurrentServerData() != null && this.minecraft.getCurrentServerData().serverIP.toLowerCase().contains("hypixel")) {
         if (var3.contains(this.field_0007[0]) || var3.contains(this.field_0007[1])) {
            Minecraft.getMinecraft().thePlayer.o.P().setHardcore(false);
         }

         if (this.field_0013.method_08908() && this.field_0027.matcher(var3).matches()) {
            this.minecraft.thePlayer.o.P().setHardcore(true);
         }

         if (this.field_0002.method_08908() && this.field_0017.stream().anyMatch(var3::contains)) {
            this.method_25694("/achat " + this.field_0023.method_08874(), this.field_0018.method_08905());
         }

         if (this.field_0015.method_08908() && var3.startsWith("The game starts in 5 seconds!")) {
            this.method_25694("/achat " + this.field_0019.method_08874(), this.field_0021.method_08905());
         }

         Matcher var2;
         if (this.field_0014.method_08908() && this.field_0029.method_08908() && (var2 = this.field_0010.matcher(var3)).matches()) {
            String var4 = var2.group("name");
            this.method_25694("/gc " + this.field_0009.method_08874().replaceAll("%USER%", var4), this.field_0000.method_08905());
         }

         if (this.field_0014.method_08908() && this.field_0030.method_08908() && (var2 = this.field_0020.matcher(var3)).matches()) {
            String var7 = var2.group("name");
            this.method_25694("/msg " + var7 + " " + this.field_0009.method_08874().replaceAll("%USER%", var7), this.field_0000.method_08905());
         }

         if (this.field_0003.method_08908() && (var2 = this.field_0025.matcher(var3.replace("\n", ""))).matches()) {
            String var8 = var2.group("name");
            if (var8.startsWith("[")) {
               var8 = var8.substring(var8.indexOf("] ") + 2);
            }

            this.method_25694("/friend " + this.field_0022.method_08874().toLowerCase() + " " + var8, this.field_0008.method_08905());
         }
      }
   }
}
