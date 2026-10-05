package com.cheatbreaker.client.config;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.ui.element.module.ModuleListElement;
import com.cheatbreaker.client.ui.element.type.ColorPickerColorElement;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.util.dash.DashUtil;
import com.cheatbreaker.client.util.server.ServerRestrictionAction;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.minecraft.block.BlockIce;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.realms.RealmsBufferBuilder;
import net.optifine.shaders.uniform.ShaderUniform3f;
import org.apache.commons.lang3.ArrayUtils;
import recovered.unidentified.UnidentifiedEnum3640;

public class GlobalSettings {
   public KeyBinding field_0060;
   public Setting field_0112;
   public Setting field_0056;
   public KeyBinding field_0104;
   public Setting field_0018;
   public List<String[]> field_0026;
   public boolean field_0113;
   public RealmsBufferBuilder field_0081;
   public Setting field_0027;
   public Setting field_0121;
   public Setting field_0017;
   public Setting field_0061;
   public Setting field_0074;
   public Setting field_0041;
   public Setting field_0092;
   public KeyBinding field_0109;
   public KeyBinding field_0014;
   public Setting field_0037;
   public ShaderUniform3f field_0068;
   public Setting field_0108;
   public Setting field_0012;
   public Setting field_0008;
   public KeyBinding field_0015;
   public Setting field_0006;
   public Setting field_0102;
   public Setting field_0069;
   public Setting field_0091;
   public Setting field_0053;
   public Setting field_0119;
   public Setting field_0075;
   public Setting field_0036;
   public List<ColorPickerColorElement> field_0020;
   public Setting field_0051;
   public Setting field_0035;
   public Setting field_0034;
   public Setting field_0031;
   public Setting field_0005;
   public Setting field_0127;
   public boolean field_0062;
   public Setting field_0078;
   public Setting field_0058;
   public Map<String[], String[]> field_0047;
   public Setting field_0057;
   public Setting field_0004;
   public Setting field_0025;
   public Setting field_0029;
   public Setting field_0071;
   public Setting field_0076;
   public Setting field_0022;
   public Setting field_0030;
   public Setting field_0103;
   public Setting field_0079;
   public Setting field_0126;
   public static Setting field_0099;
   public Setting field_0002;
   public Setting field_0055;
   public Setting field_0073;
   public Setting field_0084;
   public Setting field_0007;
   public Setting field_0066;
   public Setting field_0052;
   public String field_0120;
   public Setting field_0049;
   public Setting field_0077;
   public Setting field_0000;
   public Setting field_0059;
   public Setting field_0106;
   public Setting field_0085;
   public Setting field_0125;
   public Setting field_0023;
   public Setting field_0093;
   public Setting field_0116;
   public Setting field_0040;
   public Setting field_0096;
   public Setting field_0124;
   public int reconnectTime;
   public BlockIce field_0042;
   public Setting field_0114;
   public Setting field_0118;
   public Setting field_0117;
   public Setting field_0070;
   public Setting field_0064;
   public Setting field_0044;
   public Setting field_0098;
   public Setting field_0010;
   public Setting field_0024;
   public Setting field_0111;
   public Setting field_0065;
   public Setting field_0039;
   public Setting field_0087;
   public Setting field_0067;
   public Setting field_0046;
   public Setting field_0016;
   public Setting field_0048;
   public Setting field_0050;
   public Setting crosshairSettingsLabel;
   public Setting field_0021;
   public Setting field_0032;
   public Setting field_0115;
   public List<Setting> field_0013 = new ArrayList<>();
   public Setting field_0088;
   public Setting field_0072;
   public Setting field_0107;
   public List<ColorPickerColorElement> field_0043;
   public Setting field_0033;
   public Setting field_0063;
   public KeyBinding field_0038;
   public Setting field_0011;
   public Setting field_0110;
   public Setting field_0105;
   public Setting field_0003;
   public Setting field_0122;
   public Setting field_0100;
   public Setting field_0123;
   public Setting field_0086;
   public Setting field_0019;
   public Setting field_0045;
   public Setting field_0101;
   public Setting field_0054;
   public Setting field_0089;
   public Setting field_0095;
   public Setting field_0009;
   public Setting field_0001;
   public Setting field_0080;
   public Setting field_0094;
   public String field_0082;
   public Setting field_0083;
   public Setting field_0097;

   public Setting getCrosshairSettingsLabel() {
      return this.crosshairSettingsLabel;
   }

   public GlobalSettings() {
      this.field_0026 = new ArrayList<>();
      this.field_0047 = new HashMap<>();
      this.field_0062 = true;
      this.field_0113 = true;
      this.reconnectTime = 60;
      this.field_0120 = "http://server.noxiuam.gq/crashReport";
      this.field_0082 = "http://moosecbapi.000webhostapp.com/debug-upload.php";
      this.field_0043 = new ArrayList<>();
      this.field_0020 = new ArrayList<>();
      CheatBreaker.getInstance().field_0034.info(CheatBreaker.getInstance().field_0016 + "Created settings");
      this.field_0069 = new Setting(this.field_0013, "label").setValue("Audio Settings");
      this.field_0096 = new Setting(this.field_0013, "Mute CheatBreaker sounds", "Mute notification sounds generated from CheatBreaker.").setValue(false);
      this.field_0034 = new Setting(this.field_0013, "Pin Radio Player", "Pin the radio to the HUD.").setValue(false);
      this.field_0093 = new Setting(this.field_0013, "Radio Volume", "Adjusts the volume of the Dash Radio.").onChange(var0 -> {
         if (DashUtil.isPlayerNotNull()) {
            DashUtil.getDashPlayer().setFloatControlValue(Float.parseFloat(var0.toString()));
         }
      }).setValue(85).setMinMax(0, 100).method_08892("%").method_08915("MEDIUM");
      this.field_0076 = new Setting(this.field_0013, "label").setValue("FPS Boost");
      this.field_0087 = new Setting(this.field_0013, "Enable FPS Boost", "Enables all FPS boost settings in this category.").setValue(true);
      this.field_0027 = new Setting(this.field_0013, "Fullbright", "Set the brightness/gamma to the max.")
         .setValue(true)
         .method_08894(this.field_0087::method_08908)
         .onChange(var1x -> {
            float var2x = Minecraft.getMinecraft().gameSettings.gammaSetting;
            if (var2x != 1000.0F) {
               if (Minecraft.getMinecraft().gameSettings.field_0167 < 1.0F) {
                  var2x = 1.0F;
               }

               Minecraft.getMinecraft().gameSettings.field_0167 = var2x;
               Minecraft.getMinecraft().gameSettings.saveOptions();
            }

            Minecraft.getMinecraft().gameSettings.gammaSetting = this.field_0027.method_08908() ? 1000.0F : Minecraft.getMinecraft().gameSettings.field_0167;
         });
      this.field_0110 = new Setting(this.field_0013, "Entity Shadows", "Draws a shadow below entities.")
         .setValue(true)
         .method_08894(this.field_0087::method_08908)
         .onChange(var0 -> Minecraft.getMinecraft().getRenderManager().options.entityShadows = Boolean.parseBoolean(var0.toString()));
      this.field_0098 = new Setting(this.field_0013, "Hide Grounded Arrows").setValue(false).method_08894(this.field_0087::method_08908);
      this.field_0122 = new Setting(this.field_0013, "Hide Stuck Arrows").setValue(false).method_08894(this.field_0087::method_08908);
      this.field_0057 = new Setting(this.field_0013, "Hide Moving Arrows").setValue(false).method_08894(this.field_0087::method_08908);
      this.field_0073 = new Setting(this.field_0013, "Hide Foliage", "Hides things like tall grass, flowers, and shrubs")
         .setValue(false)
         .onChange(var0 -> CheatBreaker.getInstance().method_19774().renderGlobal.loadRenderers())
         .method_08894(this.field_0087::method_08908);
      this.field_0046 = new Setting(this.field_0013, "Hide Placed Skulls").setValue(false).method_08894(this.field_0087::method_08908);
      this.field_0127 = new Setting(this.field_0013, "label").setValue("FPS Limiting Settings");
      this.field_0039 = new Setting(
            this.field_0013, "Use Custom FPS Limiter", "Overrides the default FPS limiter with a custom one.\n§e§oWill not override when VSync is enabled."
         )
         .setValue(false);
      this.field_0094 = new Setting(this.field_0013, "Limit When Game is Unfocused", "Limits the FPS when you are not interacting with the game.")
         .setValue(false);
      this.field_0029 = new Setting(this.field_0013, "Limit In Pause Menu", "Limits the FPS when you are in the Pause Menu.").setValue(true);
      this.field_0126 = new Setting(this.field_0013, "Maximum FPS")
         .setMinMax(5, 1000)
         .setValue(390)
         .method_08892(" FPS")
         .method_08893("fps-inverse-52", "fps-52")
         .method_08894(() -> (Boolean)this.field_0039.getValue());
      this.field_0089 = new Setting(this.field_0013, "Unfocused FPS")
         .setMinMax(1, 390)
         .setValue(60)
         .method_08892(" FPS")
         .method_08893("fps-inverse-52", "fps-52")
         .method_08894(() -> (Boolean)this.field_0094.getValue());
      this.field_0077 = new Setting(this.field_0013, "Pause Menu FPS")
         .setMinMax(1, 390)
         .setValue(120)
         .method_08892(" FPS")
         .method_08893("fps-inverse-52", "fps-52")
         .method_08894(() -> (Boolean)this.field_0094.getValue());
      this.field_0066 = new Setting(this.field_0013, "Main Menu FPS")
         .setMinMax(30, 500)
         .setValue(90)
         .method_08892(" FPS")
         .method_08893("fps-inverse-52", "fps-52");
      this.field_0084 = new Setting(this.field_0013, "label").setValue("Team View Settings");
      this.field_0067 = new Setting(this.field_0013, "Enable Team View", "Enables the Team View.").setValue(true);
      this.field_0004 = new Setting(this.field_0013, "Show off-screen marker", "Show a marker above players.")
         .setValue(true)
         .method_08894(() -> (Boolean)this.field_0067.getValue());
      this.field_0083 = new Setting(this.field_0013, "Show distance", "Show the distance amount above a your teammates' heads.")
         .setValue(true)
         .method_08894(() -> (Boolean)this.field_0067.getValue());
      this.crosshairSettingsLabel = new Setting(this.field_0013, "Current Menu", "Sets which main menu should show.\n\n0: 2017\n1: 2018\n2: 2016")
         .setMinMax(0, 2)
         .setValue(1);
      this.field_0111 = new Setting(this.field_0013, "label").setValue("Display Settings");
      this.field_0033 = new Setting(this.field_0013, "Borderless Fullscreen").setValue(false).method_08917(var0 -> {
         Minecraft.getMinecraft().method_20403();
         Minecraft.getMinecraft().method_20403();
         CBModulesGui var1x = new CBModulesGui();
         Minecraft.getMinecraft().displayGuiScreen(var1x);
         var1x.currentScrollableElement = var1x.field_0029;
         ((ModuleListElement)CBModulesGui.instance.field_0029).field_0013 = true;
         CBModulesGui.instance.field_0029.field_0013 = CheatBreaker.getInstance().getModuleManager().field_0017;
      });
      this.field_0095 = new Setting(this.field_0013, "Unfullscreen when Unfocused").setValue(false);
      this.field_0010 = new Setting(this.field_0013, "label").setValue("Hud Editor Settings");
      this.field_0119 = new Setting(this.field_0013, "Mod List")
         .setValue("Normal")
         .acceptedValues("Normal", "Compact")
         .method_08914(SettingsDetailLevel.field_0001)
         .onChange(var0 -> {
            if (Minecraft.getMinecraft().currentScreen instanceof CBModulesGui) {
               CBModulesGui var1x = new CBModulesGui();
               Minecraft.getMinecraft().displayGuiScreen(var1x);
               var1x.currentScrollableElement = var1x.field_0017;
            }
         });
      field_0099 = new Setting(this.field_0013, "Dark Mode", "Switch between light and dark mode.").setValue(false);
      this.field_0100 = new Setting(this.field_0013, "Customization Level").setValue("Simple").acceptedValues("Simple", "Medium", "Advanced");
      this.field_0024 = new Setting(this.field_0013, "Show Mod Name (GUI)").setValue(true).method_08914(SettingsDetailLevel.field_0003);
      this.field_0000 = new Setting(this.field_0013, "Snap mods to other mods (GUI)").setValue(true).method_08914(SettingsDetailLevel.field_0000);
      this.field_0032 = new Setting(this.field_0013, "Snapping Strength")
         .setValue(2.0F)
         .setMinMax(1.0F, 10.0F)
         .method_08892("px")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0000.getValue());
      this.field_0036 = new Setting(this.field_0013, "label").setValue("Mod Command Settings").method_08914(SettingsDetailLevel.field_0003);
      this.field_0041 = new Setting(this.field_0013, "Enable Mod Commands").setValue(true).method_08914(SettingsDetailLevel.field_0003);
      this.field_0074 = new Setting(this.field_0013, "Hide Callback Messages").setValue(false).method_08914(SettingsDetailLevel.field_0003);
      this.field_0058 = new Setting(this.field_0013, "label").setValue("Render Settings");
      this.field_0059 = new Setting(this.field_0013, "Show Potion info in inventory")
         .setValue(true)
         .method_08894(() -> CheatBreaker.getInstance().getModuleManager().potionStatus.isEnabled());
      this.field_0078 = new Setting(this.field_0013, "Potion info shifts inventory", "Choose to make the potion info shift the inventory position.")
         .setValue(false);
      this.field_0048 = new Setting(this.field_0013, "Show Achievements").setValue(true);
      this.field_0003 = new Setting(this.field_0013, "Show HUD while in debug view", "Show the CheatBreaker HUD when the debug view is open.").setValue(false);
      this.field_0001 = new Setting(this.field_0013, "GUI Blur", "Blurs the menus.")
         .method_08917(var0 -> Minecraft.getMinecraft().entityRenderer.method_29141())
         .setValue(false);
      this.field_0091 = new Setting(this.field_0013, "Container Background").setValue("CheatBreaker").acceptedValues("Vanilla", "CheatBreaker", "None");
      this.field_0070 = new Setting(
            this.field_0013,
            "Title Combat Indicators",
            "On some servers, a hit cooldown is shown because of the newer versions of the game,\nthis gives you the ability to toggle it."
         )
         .setValue(true);
      this.field_0019 = new Setting(this.field_0013, "label").setValue("Disconnect Settings");
      this.field_0055 = new Setting(
            this.field_0013, "Hold Disconnect Button", "Requires the mouse to be held for a certain period of time when clicking on Disconnect."
         )
         .setValue(false);
      this.field_0064 = new Setting(this.field_0013, "Hold Disconnect In", "Show the prompt in Singleplayer, Multiplayer, or both")
         .setValue("Multiplayer")
         .acceptedValues("Singleplayer", "Multiplayer", "Both")
         .method_08894(this.field_0055::method_08908);
      this.field_0105 = new Setting(
            this.field_0013, "Hold Disconnect Duration", "The amount of time the Mods button must be held down before entering the Mod Menu."
         )
         .setValue(5.0F)
         .setMinMax(1.0F, 10.0F)
         .method_08892("s")
         .method_08894(this.field_0055::method_08908);
      this.field_0056 = new Setting(this.field_0013, "Disable Disconnect Button", "Disable Disconnect button temporarily while in the Game Menu.")
         .setValue(false);
      this.field_0025 = new Setting(this.field_0013, "Disable Disconnect In", "Show the prompt in Singleplayer, Multiplayer, or both")
         .setValue("Multiplayer")
         .acceptedValues("Singleplayer", "Multiplayer", "Both")
         .method_08894(this.field_0056::method_08908);
      this.field_0002 = new Setting(this.field_0013, "Disable Disconnect Duration", "The amount of time the disconnect button will remain disabled.")
         .setValue(1.0F)
         .setMinMax(0.25F, 10.0F)
         .method_08892("s")
         .method_08894(this.field_0056::method_08908);
      this.field_0031 = new Setting(this.field_0013, "Disconnect Confirmation Prompt", "Show a disconnect confirmation prompt before actually disconnecting.")
         .setValue(false);
      this.field_0005 = new Setting(this.field_0013, "Show Reconnect Option", "Show a reconnect prompt")
         .setValue(true)
         .method_08894(this.field_0031::method_08908);
      this.field_0051 = new Setting(this.field_0013, "Show Prompt In", "Show the prompt in Singleplayer, Multiplayer, or both")
         .setValue("Multiplayer")
         .acceptedValues("Singleplayer", "Multiplayer", "Both")
         .method_08894(this.field_0031::method_08908);
      this.field_0072 = new Setting(this.field_0013, "Smart Disconnect", "Only show the disconnect prompt if clicking too soon.")
         .setValue(true)
         .method_08894(this.field_0031::method_08908);
      this.field_0012 = new Setting(this.field_0013, "Wait Duration", "The amount of time the user should wait before the disconnect prompt does not show.")
         .setValue(2.0F)
         .setMinMax(0.25F, 10.0F)
         .method_08892("s")
         .method_08894(() -> this.field_0072.method_08908() && this.field_0031.method_08908());
      this.field_0124 = new Setting(this.field_0013, "label").setValue("Streamer Mode Settings");
      this.field_0112 = new Setting(this.field_0013, "Streamer Mode", "Enables Streamer Mode Features").setValue(false);
      this.field_0079 = new Setting(
            this.field_0013, "Disable Mod Menu Keybind", "Disables the Mod Menu keybind from opening the Mod Menu when Streamer Mode is enabled."
         )
         .setValue(true)
         .method_08894(this.field_0112::method_08908);
      this.field_0075 = new Setting(this.field_0013, "Notify when pressed", "Notifies when the player presses the Mod Menu keybind while disabled.")
         .setValue(true)
         .method_08894(() -> this.field_0112.method_08908() && this.field_0079.method_08908());
      this.field_0008 = new Setting(this.field_0013, "Hold Mods Button", "Requires the mouse to be held for a certain period of time when clicking on Mods.")
         .setValue(true)
         .method_08894(this.field_0112::method_08908);
      this.field_0114 = new Setting(this.field_0013, "Hold Mods Duration", "The amount of time the Mods button must be held down before entering the Mod Menu.")
         .setValue(5.0F)
         .setMinMax(1.0F, 10.0F)
         .method_08892("s")
         .method_08894(() -> this.field_0112.method_08908() && this.field_0008.method_08908());
      this.field_0063 = new Setting(this.field_0013, "Disable Mods Button", "Disable Disconnect button temporarily while in the Game Menu.")
         .setValue(true)
         .method_08894(this.field_0112::method_08908);
      this.field_0118 = new Setting(this.field_0013, "Disable Mods Duration", "The amount of time the Mods button will remain disabled.")
         .setValue(2.0F)
         .setMinMax(1.0F, 10.0F)
         .method_08892("s")
         .method_08894(() -> this.field_0112.method_08908() && this.field_0063.method_08908());
      this.field_0023 = new Setting(this.field_0013, "label").setValue("Resource Pack Settings").method_08914(SettingsDetailLevel.field_0003);
      this.field_0106 = new Setting(this.field_0013, "Wide Pack Menu").setValue(false).method_08914(SettingsDetailLevel.field_0003);
      this.field_0088 = new Setting(this.field_0013, "Transparent background", "Remove the dirt background in the resource pack menu.")
         .setValue(false)
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0103 = new Setting(this.field_0013, "Show Pack Folder Information", "Show the ").setValue(true).method_08914(SettingsDetailLevel.field_0001);
      this.field_0040 = new Setting(this.field_0013, "Show Pack Folder Icons").setValue(true).method_08914(SettingsDetailLevel.field_0001);
      this.field_0061 = new Setting(this.field_0013, "Show Pack Icons").setValue(true).method_08914(SettingsDetailLevel.field_0001);
      this.field_0030 = new Setting(this.field_0013, "Show Pack Descriptions").setValue(true).method_08914(SettingsDetailLevel.field_0001);
      this.field_0054 = new Setting(this.field_0013, "Show Search Bar").setValue(true).method_08914(SettingsDetailLevel.field_0001);
      this.field_0108 = new Setting(
            this.field_0013,
            "Sort Method",
            "Sort packs in a specific order.\n\n§bA-Z:§r Sort packs alphabetically starting from A to Z.\n§bZ-A:§r Sort packs alphabetically in reverse starting from Z to A."
         )
         .setValue("A-Z")
         .acceptedValues("A-Z", "Z-A")
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0121 = new Setting(this.field_0013, "Smooth Scrolling", "Makes scrolling smooth").setValue(false).method_08914(SettingsDetailLevel.field_0003);
      this.field_0050 = new Setting(this.field_0013, "List Background color", "Change the background color in the resource pack lists.")
         .setValue(Integer.MIN_VALUE)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0003);
      this.field_0125 = new Setting(this.field_0013, "label").setValue("Keybind Handling Settings");
      this.field_0092 = new Setting(this.field_0013, "Modern Keybind Handling").setValue(true);
      this.field_0045 = new Setting(this.field_0013, "Exclude Sneak Keybind").setValue(true).method_08894(() -> (Boolean)this.field_0092.getValue());
      this.field_0011 = new Setting(this.field_0013, "Exclude Block/Throw Keybind").setValue(true).method_08894(() -> (Boolean)this.field_0092.getValue());
      this.field_0009 = new Setting(this.field_0013, "label").setValue("Scaling Settings");
      this.field_0115 = new Setting(this.field_0013, "Mod Scale").setValue("Default").acceptedValues("Default", "Small", "Normal", "Large", "Auto");
      this.field_0102 = new Setting(this.field_0013, "Mod Scale Multiplier").setValue(1.0F).setMinMax(0.25F, 2.0F).method_08892("x");
      this.field_0080 = new Setting(this.field_0013, "Use Legacy Scaling").setValue(false);
      this.field_0022 = new Setting(this.field_0013, "label").setValue("Screenshot Settings").method_08914(SettingsDetailLevel.field_0003);
      this.field_0107 = new Setting(this.field_0013, "Make Shutter Sound").setValue(true).method_08914(SettingsDetailLevel.field_0003);
      this.field_0101 = new Setting(this.field_0013, "Copy Screenshot Automatically").setValue(false).method_08914(SettingsDetailLevel.field_0003);
      this.field_0116 = new Setting(this.field_0013, "Send Message").setValue(true).method_08914(SettingsDetailLevel.field_0003);
      this.field_0049 = new Setting(this.field_0013, "Compact Options")
         .setValue(false)
         .method_08914(SettingsDetailLevel.field_0001)
         .method_08894(() -> (Boolean)this.field_0116.getValue());
      this.field_0006 = new Setting(this.field_0013, "Show Open Option")
         .setValue(true)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0116.getValue());
      this.field_0017 = new Setting(this.field_0013, "Show Copy Option")
         .setValue(true)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0116.getValue());
      this.field_0117 = new Setting(this.field_0013, "Show Upload Option")
         .setValue(true)
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> (Boolean)this.field_0116.getValue());
      this.field_0007 = new Setting(this.field_0013, "label").setValue("Cosmetic Settings");
      this.field_0086 = new Setting(this.field_0013, "Show OptiFine Capes").setValue(true);
      this.field_0035 = new Setting(this.field_0013, "Show OptiFine Hats").setValue(true);
      this.field_0044 = new Setting(this.field_0013, "Show CheatBreaker Capes").setValue(true);
      this.field_0085 = new Setting(this.field_0013, "Show CheatBreaker Wings").setValue(true);
      String var1 = System.getProperty("os.name").toLowerCase();
      this.field_0016 = new Setting(this.field_0013, "label").setValue("Discord Rich Presence Settings").method_08894(() -> var1.contains("win"));
      this.field_0097 = new Setting(this.field_0013, "Show Discord Rich Presence").setValue(true).method_08894(() -> var1.contains("win")).onChange(var1x -> {
         if (var1.contains("win") && Minecraft.getMinecraft().currentScreen instanceof CBModulesGui) {
            try {
               if ((Boolean)var1x) {
                  CheatBreaker.getInstance().method_19739();
               } else {
                  CheatBreaker.getInstance().method_19768().close();
               }
            } catch (Exception var3) {
               var3.printStackTrace();
            }
         }
      });
      this.field_0071 = new Setting(this.field_0013, "Show Active Server")
         .setValue(true)
         .method_08894(() -> var1.contains("win") && this.field_0097.method_08908())
         .onChange(var1x -> this.method_02688());
      this.field_0065 = new Setting(this.field_0013, "Show Active Account")
         .setValue(true)
         .method_08894(() -> var1.contains("win") && this.field_0097.method_08908())
         .onChange(var1x -> this.method_02688());
      this.field_0123 = new Setting(this.field_0013, "label").setValue("Color Settings");
      this.field_0018 = new Setting(this.field_0013, "Apply Default Color When Enabling Mods").setValue(true);
      this.field_0052 = new Setting(this.field_0013, "Default color", "Change the default color that will be displayed when mods are enabled.")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE);
      this.field_0026.add(new String[]{"Minemen Club [NA]", "na.minemen.club"});
      this.field_0047
         .put(
            new String[]{"lunar.gg"},
            new String[]{
               "While the Lunar Network is a safe server to play on, Lunar does contain a proprietary packet that may cause client crashes on 1.7.",
               String.valueOf(ServerRestrictionAction.field_0006)
            }
         );
      GameSettings var2 = Minecraft.getMinecraft().gameSettings;
      this.field_0014 = new KeyBinding("Open Menu", 54, "CheatBreaker Client", true);
      this.field_0015 = new KeyBinding("Drag to look", 56, "CheatBreaker Client", true);
      this.field_0038 = new KeyBinding("Hide Nametags", 0, "CheatBreaker Client", true);
      this.field_0104 = new KeyBinding("Back look", 0, "CheatBreaker Client", true);
      this.field_0060 = new KeyBinding("Front look", 0, "CheatBreaker Client", true);
      var2.keyBindings = (KeyBinding[])ArrayUtils.addAll(
         var2.keyBindings, new KeyBinding[]{this.field_0038, this.field_0014, this.field_0015, this.field_0104, this.field_0060}
      );
   }

   public UnidentifiedEnum3640 method_02705() {
      return UnidentifiedEnum3640.values()[this.crosshairSettingsLabel.getValue()];
   }

   public void method_02684(Setting var1) {
      this.crosshairSettingsLabel = var1;
   }

   public void method_02688() {
      try {
         if (Minecraft.getMinecraft().currentScreen instanceof CBModulesGui) {
            String var1 = this.field_0065.method_08908() ? Minecraft.getMinecraft().getSession().getUsername() : null;
            CheatBreaker.getInstance().method_19778(var1);
         }
      } catch (Exception var2) {
         var2.printStackTrace();
      }
   }

   public Map<String[], String[]> method_02677() {
      return this.field_0047;
   }

   public boolean isFavouriteColor(int var1) {
      for (ColorPickerColorElement var2 : this.field_0043) {
         if (var2.color == var1) {
            return true;
         }
      }

      return false;
   }

   public List<String[]> method_02689() {
      return this.field_0026;
   }

   public void removeFavouriteColor(int var1) {
      this.field_0043.removeIf(var1x -> var1x.color == var1);
   }
}
