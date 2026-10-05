package com.cheatbreaker.client.config;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.module.staff.StaffModule;
import com.cheatbreaker.client.ui.element.profile.ProfilesListElement;
import com.cheatbreaker.client.ui.element.type.ColorPickerColorElement;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import com.cheatbreaker.client.util.dash.Station;
import io.netty.handler.codec.socks.SocksAuthResponse;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.optifine.entity.model.anim.ModelResolver;
import org.apache.commons.lang3.StringUtils;
import recovered.unidentified.UnidentifiedClass4506;

public class ConfigManager {
   public File field_0005;
   public SocksAuthResponse field_0008;
   public List<ResourceLocation> field_0004 = new ArrayList<>();
   public File field_0007;
   public File field_0001;
   public ModelResolver field_0002;
   public Profile field_0009;
   public List<Profile> field_0006 = new ArrayList<>();
   public File field_0003;
   public File field_0010;
   public BlockPos field_0000;

   public void method_25101() {
      if (this.method_25104()) {
         this.method_25105(this.field_0001);
         this.method_25102(this.field_0010);
         if (CheatBreaker.getInstance().getConfigManager().field_0009 == null) {
            CheatBreaker.getInstance().getConfigManager().field_0009 = this.field_0006.get(0);
         } else {
            this.method_25103(CheatBreaker.getInstance().getConfigManager().field_0009.getName());
         }
      }
   }

   public void method_25103(String var1) {
      if (var1.equalsIgnoreCase("default")) {
         this.field_0009 = this.field_0006.get(0);

         for (AbstractModule var28 : CheatBreaker.getInstance().getModuleManager().field_0008) {
            var28.setState(var28.defaultState);
            var28.method_28786(var28.field_0030);
            var28.method_28819(var28.defaultGuiAnchor);
            var28.setTranslations(var28.defaultXTranslation, var28.defaultYTranslation);
            var28.method_28847(var28.field_0004);

            for (int var29 = 0; var29 < var28.getSettingsList().size(); var29++) {
               try {
                  var28.getSettingsList().get(var29).setValue(var28.method_28782().get(var29), false);
               } catch (Exception var22) {
                  var22.printStackTrace();
               }
            }
         }
      } else {
         File var2 = new File(this.field_0005 + File.separator + "profiles");
         File var3 = !var2.exists() && !var2.mkdirs() ? null : new File(var2 + File.separator + var1 + ".cfg");
         if (!var3.exists()) {
            this.method_25108(var1);
         } else {
            ArrayList var4 = new ArrayList();
            var4.addAll(CheatBreaker.getInstance().getModuleManager().field_0008);
            var4.addAll(CheatBreaker.getInstance().getModuleManager().field_0049);

            try {
               BufferedReader var6 = new BufferedReader(new FileReader(var3));
               AbstractModule var7 = null;

               String var5;
               label290:
               while ((var5 = var6.readLine()) != null) {
                  try {
                     if (!var5.startsWith("#") && var5.length() != 0) {
                        if (var5.startsWith("[")) {
                           for (AbstractModule var36 : var4) {
                              if (("[" + var36.getName() + "]").equalsIgnoreCase(var5)) {
                                 var7 = var36;
                                 break;
                              }
                           }
                        } else if (var7 != null) {
                           if (var5.startsWith("-")) {
                              String[] var30 = var5.replaceFirst("-", "").split("=", 2);
                              if (var30.length == 2) {
                                 try {
                                    String var32 = var30[0];
                                    switch (var32) {
                                       case "State":
                                          if (!var7.method_28787()) {
                                             var7.setState(Boolean.parseBoolean(var30[1]));
                                          }
                                          break;
                                       case "WasRenderHUD":
                                          var7.method_28786(Boolean.parseBoolean(var30[1]));
                                          break;
                                       case "RenderHUD":
                                          var7.method_28847(Boolean.parseBoolean(var30[1]));
                                          break;
                                       case "Position":
                                          if (var7.getGuiAnchor() != null) {
                                             for (CBGuiAnchor var48 : CBGuiAnchor.values()) {
                                                if (var48.getLabel().equalsIgnoreCase(var30[1])) {
                                                   var7.method_28819(var48);
                                                   break;
                                                }
                                             }
                                          }
                                          break;
                                       case "xTranslation":
                                          var7.setXTranslation(Float.parseFloat(var30[1]));
                                          break;
                                       case "yTranslation":
                                          var7.setYTranslation(Float.parseFloat(var30[1]));
                                    }
                                 } catch (Exception var23) {
                                    var23.printStackTrace();
                                 }
                              }
                           } else {
                              String[] var8 = var5.split("=", 2);
                              if (var8.length == 2) {
                                 if (var7 == CheatBreaker.getInstance().getModuleManager().field_0032) {
                                    for (int var9 = 0; var9 < 50; var9++) {
                                       if (var8[1].contains("keycode:")) {
                                          boolean var10 = false;
                                          String var12 = "";
                                          if (var8[1].contains("mouse:")) {
                                             var12 = StringUtils.substringAfter(var8[1], ";mouse:");
                                             var10 = true;
                                          }

                                          String var13 = StringUtils.substringAfter(var8[1], ";keycode:").replaceAll(";mouse:" + var12, "");
                                          String[] var14 = var8[1].split(";");
                                          String var15 = var14[0];
                                          var15 = var15.replaceAll(";mouse:" + var12, "");
                                          if (Integer.parseInt(var13) == 0 && Boolean.parseBoolean(var12)) {
                                             var10 = false;
                                          }

                                          if (!CheatBreaker.getInstance().getModuleManager().field_0032.method_28846(var8[0])) {
                                             Setting var11 = new Setting(CheatBreaker.getInstance().getModuleManager().field_0032.getSettingsList(), var8[0]);
                                             var11.setValue(var15).method_08887(Integer.parseInt(var13));
                                             var11.method_08885(var10);
                                             CheatBreaker.getInstance().getModuleManager().field_0032.field_0004.add(var11);
                                          }
                                       }
                                    }
                                 }

                                 for (Setting var34 : var7.getSettingsList()) {
                                    if (!var34.method_08911().equalsIgnoreCase("label") && var34.method_08911().equalsIgnoreCase(var8[0])) {
                                       if (var7 == CheatBreaker.getInstance().getModuleManager().field_0032) {
                                          break;
                                       }

                                       if (var34.method_08911().endsWith("Keybind") && var8[1].contains("mouse:")) {
                                          boolean var39 = Boolean.parseBoolean(StringUtils.substringAfter(var8[1], ";mouse:"));
                                          String[] var43 = var8[1].split(";");
                                          String var46 = var43[0];
                                          System.out.println("mouse boolean: " + var39);
                                          System.out.println("value: " + var46);
                                          if (Integer.parseInt(var46) == 0 && var39) {
                                             var34.method_08885(false);
                                          }

                                          var34.setValue(Integer.parseInt(var46)).method_08885(var39);
                                          break;
                                       }

                                       try {
                                          switch (UnidentifiedClass4506.field_0001[var34.getType().ordinal()]) {
                                             case 1:
                                                var34.setValue(Boolean.parseBoolean(var8[1]));
                                                continue label290;
                                             case 2:
                                                if (var7.method_28787() && var34 == ((StaffModule)var7).getKeybindSetting()) {
                                                   ((StaffModule)var7).getKeybindSetting().setValue(Integer.parseInt(var8[1]));
                                                   continue label290;
                                                }

                                                if (var8[1].contains("rainbow")) {
                                                   String[] var37 = var8[1].split(";");
                                                   int var42 = Integer.parseInt(var37[0]);
                                                   var34.field_0013 = true;
                                                   if (var42 <= (Integer)var34.method_08878() && var42 >= (Integer)var34.method_08904()) {
                                                      var34.setValue(var42);
                                                   }
                                                } else if (var34.method_08911().endsWith("Keybind")) {
                                                   var34.setValue(Integer.parseInt(var8[1]));
                                                } else {
                                                   int var38 = Integer.parseInt(var8[1]);
                                                   var34.field_0013 = false;
                                                   if (var38 <= (Integer)var34.method_08878() && var38 >= (Integer)var34.method_08904()) {
                                                      var34.setValue(var38);
                                                   }
                                                }
                                                continue label290;
                                             case 3:
                                                float var41 = Float.parseFloat(var8[1]);
                                                if (var41 <= (Float)var34.method_08878() && var41 >= (Float)var34.method_08904()) {
                                                   var34.setValue(var41);
                                                }
                                                continue label290;
                                             case 4:
                                                double var45 = Double.parseDouble(var8[1]);
                                                if (var45 <= (Double)var34.method_08878() && var45 >= (Double)var34.method_08904()) {
                                                   var34.setValue(var45);
                                                }
                                                continue label290;
                                             case 5:
                                                ArrayList var50 = new ArrayList<>(
                                                   Arrays.asList(var8[1].replaceAll("\\[", "").replaceAll("]", "").replaceAll(" ", "").split(","))
                                                );
                                                List var16 = var50.stream().map(Integer::parseInt).collect(Collectors.toList());
                                                var34.setValue(var16);
                                                continue label290;
                                             case 6:
                                                boolean var17 = false;
                                                String[] var18 = var34.getAcceptedValues();
                                                int var19 = var18.length;
                                                int var20 = 0;

                                                for (; var20 < var19; var20++) {
                                                   String var21 = var18[var20];
                                                   if (var21.equalsIgnoreCase(var8[1])) {
                                                      var17 = true;
                                                   }
                                                }

                                                if (var17) {
                                                   var34.setValue(var8[1]);
                                                }
                                                continue label290;
                                             case 7:
                                                if (!var34.method_08911().equalsIgnoreCase("label")) {
                                                   if (var34 == CheatBreaker.getInstance().getModuleManager().field_0033.field_0003) {
                                                      var8[1] = var8[1].replaceAll("%FPS%", "%BOOST%");
                                                   }

                                                   var34.setValue(var8[1].replaceAll("&([abcdefghijklmrABCDEFGHIJKLMNR0-9])|(&$)", "§$1"));
                                                }
                                             default:
                                                continue label290;
                                          }
                                       } catch (Exception var24) {
                                          var24.printStackTrace();
                                          if (var34 == CheatBreaker.getInstance().getModuleManager().keyStrokes.field_0043) {
                                             CheatBreaker.getInstance().getModuleManager().keyStrokes.initialize();
                                             Minecraft.getMinecraft().ingameGUI.getChatGUI().refreshChat();
                                          }
                                       }
                                    }
                                 }
                              }
                           }
                        }
                     }
                  } catch (Exception var25) {
                     var25.printStackTrace();
                  }
               }

               var6.close();
            } catch (IOException var26) {
               var26.printStackTrace();
            }

            this.method_25108(var1);
         }
      }
   }

   public void method_25108(String var1) {
      if (!var1.equalsIgnoreCase("default")) {
         File var2 = new File(this.field_0005 + File.separator + "profiles");
         File var3 = !var2.exists() && !var2.mkdirs() ? null : new File(var2 + File.separator + var1 + ".cfg");
         ArrayList var4 = new ArrayList();
         var4.addAll(CheatBreaker.getInstance().getModuleManager().field_0008);
         var4.addAll(CheatBreaker.getInstance().getModuleManager().field_0049);

         try {
            BufferedWriter var5 = new BufferedWriter(new FileWriter(var3));
            var5.write("################################");
            var5.newLine();
            var5.write("# MC_Client: MODULE SETTINGS");
            var5.newLine();
            var5.write("################################");
            var5.newLine();
            var5.newLine();

            for (AbstractModule var7 : var4) {
               var5.write("[" + var7.getName() + "]");
               var5.newLine();
               var5.write("-State=" + var7.isEnabled());
               var5.newLine();
               var5.write("-WasRenderHUD=" + var7.method_28790());
               var5.newLine();
               if (var7.getGuiAnchor() != null) {
                  var5.write("-Position=" + var7.getGuiAnchor().getLabel());
                  var5.newLine();
               }

               var5.write("-xTranslation=" + var7.getXTranslation());
               var5.newLine();
               var5.write("-yTranslation=" + var7.getYTranslation());
               var5.newLine();
               var5.write("-RenderHUD=" + var7.method_28866());
               var5.newLine();

               for (Setting var9 : var7.getSettingsList()) {
                  if (var9 == CheatBreaker.getInstance().getModuleManager().coordinatesModule.field_0005) {
                     var5.write("# Customize your HUD info display string.");
                     var5.newLine();
                     var5.write("# (& color formatting is allowed)");
                     var5.newLine();
                     var5.write("# Optional uses:");
                     var5.newLine();
                     var5.write("# %FPS% - Display current FPS.");
                     var5.newLine();
                     var5.write("# %DIR% - Display current look direction.");
                     var5.newLine();
                     var5.write("# %CPS% - Display CPS.");
                     var5.newLine();
                     var5.write("# %COORDS% - Display coordinates.");
                     var5.newLine();
                     var5.write("# %IP% - Current server IP.");
                     var5.newLine();
                     var5.write("# %X% - Your X location.");
                     var5.newLine();
                     var5.write("# %Y% - Your (foot) Y location.");
                     var5.newLine();
                     var5.write("# %Z% - Your Z location.");
                     var5.newLine();
                     var5.write("# %NL% - Break into a new line.");
                     var5.newLine();
                  }

                  if (!var9.method_08911().equalsIgnoreCase("label")) {
                     if (var9.getType() == Setting$Type.field_0013 && var9.method_08911().startsWith("Hot key")) {
                        var5.write(
                           var9.method_08911()
                              + "="
                              + (var9.getValue()
                                    + (var9.method_08867() ? ";keycode:" + var9.method_08877() : "")
                                    + (var9.method_08879() ? ";mouse:" + var9.method_08879() : ""))
                                 .replaceAll("§", "&")
                        );
                     } else if (var9.getType() == Setting$Type.field_0002 && var9.method_08911().endsWith("Keybind") && var9.method_08911().contains("Toggle")) {
                        var5.write(var9.method_08911() + "=" + var9.getValue() + (var9.method_08879() ? ";mouse:" + var9.method_08879() : ""));
                     } else if (var9.field_0013) {
                        var5.write(var9.method_08911() + "=" + var9.getValue() + ";rainbow");
                     } else {
                        var5.write(var9.method_08911() + "=" + var9.getValue());
                     }

                     var5.newLine();
                  }
               }

               var5.newLine();
            }

            var5.close();
         } catch (IOException var10) {
            var10.printStackTrace();
         }
      }
   }

   public void method_25106() {
      this.field_0006.add(new Profile("default", true));
      File var1 = new File(
         Minecraft.getMinecraft().mcDataDir
            + File.separator
            + "config"
            + File.separator
            + "cheatbreaker-client-"
            + "1.8.9".replaceAll("\\.", "-")
            + File.separator
            + "profiles"
      );
      if (var1.exists()) {
         for (File var5 : Objects.requireNonNull(var1.listFiles())) {
            Minecraft.getMinecraft().field_0002.method_25825();
            if (var5.getName().endsWith(".cfg")) {
               this.field_0006.add(new Profile(var5.getName().replace(".cfg", ""), false));
            }
         }
      }

      CheatBreaker.getInstance().field_0034.info(CheatBreaker.getInstance().field_0016 + "Loaded " + this.field_0006.size() + " custom profiles");
   }

   public void method_25098() {
      File var1 = this.field_0003;
      if (var1.exists() || var1.mkdirs()) {
         for (ResourceLocation var3 : this.field_0004) {
            String var4 = var3.getResourcePath().replaceAll("([a-zA-Z0-9/]+)/", "");
            File var5 = new File(var1, var4);
            if (!var5.exists()) {
               try {
                  InputStream var6 = Minecraft.getMinecraft().getResourceManager().getResource(var3).getInputStream();
                  Files.copy(var6, var5.toPath());
                  var6.close();
               } catch (IOException var7) {
                  var7.printStackTrace();
               }
            }
         }
      }

      CheatBreaker.getInstance().field_0034.info(CheatBreaker.getInstance().field_0016 + "Created default configuration presets");
   }

   public void method_25102(File var1) {
      try {
         if (!var1.exists()) {
            this.method_25107(var1);
            return;
         }

         BufferedReader var3 = new BufferedReader(new FileReader(var1));

         String var2;
         while ((var2 = var3.readLine()) != null) {
            try {
               UUID var4 = UUID.fromString(var2);
               CheatBreaker.getInstance().getNetHandler().getUuidList().add(var4);
            } catch (Exception var5) {
            }
         }
      } catch (IOException var6) {
      }
   }

   public boolean method_25104() {
      try {
         return (this.field_0005.exists() || this.field_0005.mkdirs())
            && (this.field_0007.exists() || this.field_0007.createNewFile())
            && (this.field_0001.exists() || this.field_0001.createNewFile());
      } catch (IOException var2) {
         var2.printStackTrace();
         return true;
      }
   }

   public boolean method_25096() {
      for (AbstractModule var2 : CheatBreaker.getInstance().getModuleManager().field_0049) {
         var2.setStaffModuleEnabled(true);
         if (var2.isStaffEnabledModule()) {
            return false;
         }
      }

      return true;
   }

   public ConfigManager() {
      this.field_0005 = new File(
         Minecraft.getMinecraft().mcDataDir + File.separator + "config" + File.separator + "cheatbreaker-client-" + "1.8.9".replaceAll("\\.", "-")
      );
      this.field_0003 = new File(this.field_0005 + File.separator + "profiles");
      this.field_0001 = new File(this.field_0005 + File.separator + "global.cfg");
      this.field_0010 = new File(this.field_0005 + File.separator + "mutes.cfg");
      this.field_0007 = new File(this.field_0005 + File.separator + "default.cfg");
   }

   public String method_25100(String var1) {
      File var2 = new File(
         Minecraft.getMinecraft().mcDataDir + File.separator + "config" + File.separator + "cheatbreaker-client-" + "1.8.9".replaceAll("\\.", "-")
      );
      File var3 = new File(var2 + File.separator + "profiles");
      return (var3.exists() || var3.mkdirs()) && new File(var3 + File.separator + var1 + ".cfg").exists() ? this.method_25100(var1 + "1") : var1;
   }

   public void method_25107(File var1) {
      try {
         if (!var1.exists()) {
            var1.createNewFile();
         }

         BufferedWriter var2 = new BufferedWriter(new FileWriter(var1));

         for (UUID var4 : CheatBreaker.getInstance().getNetHandler().getUuidList()) {
            var2.write(var4.toString());
            var2.newLine();
         }

         var2.close();
      } catch (IOException var5) {
      }
   }

   public void method_25099(File var1) {
      try {
         BufferedWriter var2 = new BufferedWriter(new FileWriter(var1));
         var2.write("################################");
         var2.newLine();
         var2.write("# MC_Client: GLOBAL SETTINGS");
         var2.newLine();
         var2.write("################################");
         var2.newLine();
         var2.newLine();
         if (this.field_0009 != null && !this.field_0009.getName().equals("default")) {
            var2.write("ActiveProfile=" + this.field_0009.getName());
            var2.newLine();
         }

         for (Setting var4 : CheatBreaker.getInstance().getGlobalSettings().field_0013) {
            if (!var4.method_08911().equalsIgnoreCase("label")) {
               if (var4.field_0013) {
                  var2.write(var4.method_08911() + "=" + var4.getValue() + ";rainbow");
               } else {
                  var2.write(var4.method_08911() + "=" + var4.getValue());
               }

               var2.newLine();
            }
         }

         for (KeyBinding var6 : Minecraft.getMinecraft().gameSettings.keyBindings) {
            if (var6.field_0013) {
               var2.write("key_" + var6.getKeyDescription() + "=" + var6.getKeyCode());
               var2.newLine();
            }
         }

         String var10 = "";

         for (ColorPickerColorElement var14 : CheatBreaker.getInstance().getGlobalSettings().field_0043) {
            var10 = var10
               + var14.color
               + (
                  CheatBreaker.getInstance().getGlobalSettings().field_0043.indexOf(var14)
                        == CheatBreaker.getInstance().getGlobalSettings().field_0043.size() - 1
                     ? ""
                     : ","
               );
         }

         if (!var10.equals("")) {
            var2.write("FavoriteColors=" + var10);
            var2.newLine();
         }

         StringBuilder var13 = new StringBuilder();

         for (Station var17 : CheatBreaker.getInstance().getRadioManager().getStations()) {
            if (var17.isFavourite()) {
               if (var13.length() != 0) {
                  var13.append(",");
               }

               var13.append(var17.getName());
            }
         }

         if (!var13.toString().equals("")) {
            var2.write("FavoriteStations=" + var13);
            var2.newLine();
         }

         StringBuilder var16 = new StringBuilder();

         for (int var7 : CheatBreaker.getInstance().getModuleManager().xray.method_01553()) {
            if (var16.length() != 0) {
               var16.append(",");
            }

            var16.append(var7);
         }

         var2.write("XrayBlocks=" + var16);
         var2.newLine();
         var2.write("ProfileIndexes=");

         for (Profile var20 : this.field_0006) {
            var2.write("[" + var20.getName() + "," + var20.index + "]");
         }

         var2.newLine();
         var2.close();
      } catch (IOException var8) {
         var8.printStackTrace();
      }
   }

   public void method_25097() {
      if (this.field_0009 == this.field_0006.get(0)) {
         Profile var1;
         CheatBreaker.getInstance().getConfigManager().field_0009 = var1 = new Profile(this.method_25100("Profile 1"), false);
         this.field_0006.add(var1);
         CheatBreaker.getInstance().configManager.method_25109();
         Minecraft var2 = Minecraft.getMinecraft();
         if (var2.currentScreen instanceof CBModulesGui) {
            ProfilesListElement var3 = (ProfilesListElement)((CBModulesGui)var2.currentScreen).field_0018;
            var3.method_03199();
         }
      }
   }

   public void method_25105(File var1) {
      if (!var1.exists()) {
         this.method_25099(var1);
      } else {
         try {
            BufferedReader var3 = new BufferedReader(new FileReader(var1));

            String var2;
            label268:
            while ((var2 = var3.readLine()) != null) {
               try {
                  String[] var5;
                  if (!var2.startsWith("#") && var2.length() != 0 && (var5 = var2.split("=", 2)).length == 2) {
                     if (var5[0].equalsIgnoreCase("FavoriteColors")) {
                        String[] var31 = var5[1].split(",");

                        for (String var56 : var31) {
                           try {
                              CheatBreaker.getInstance().getGlobalSettings().field_0043.add(new ColorPickerColorElement(1.0F, Integer.parseInt(var56), 1.0F));
                           } catch (NumberFormatException var20) {
                              var20.printStackTrace();
                           }
                        }
                     } else if (var5[0].equalsIgnoreCase("FavoriteStations")) {
                        String[] var30 = var5[1].split(",");

                        for (String var55 : var30) {
                           try {
                              for (Station var59 : CheatBreaker.getInstance().getRadioManager().getStations()) {
                                 if (var59.getName().equalsIgnoreCase(var55)) {
                                    var59.setFavourite(true);
                                 }
                              }
                           } catch (NumberFormatException var21) {
                              var21.printStackTrace();
                           }
                        }
                     } else if (var5[0].equalsIgnoreCase("XrayBlocks")) {
                        CheatBreaker.getInstance().getModuleManager().xray.method_01553().clear();
                        String[] var29 = var5[1].split(",");

                        for (String var54 : var29) {
                           try {
                              CheatBreaker.getInstance().getModuleManager().xray.method_01553().add(Integer.parseInt(var54));
                           } catch (NumberFormatException var19) {
                              var19.printStackTrace();
                           }
                        }
                     } else if (var5[0].startsWith("key_")) {
                        for (KeyBinding var48 : Minecraft.getMinecraft().gameSettings.keyBindings) {
                           if (var48.field_0013 && var5[0].equalsIgnoreCase("key_" + var48.getKeyDescription())) {
                              var48.setKeyCode(Integer.parseInt(var5[1]));
                           }
                        }
                     } else if (var5[0].equalsIgnoreCase("ProfileIndexes")) {
                        String[] var27 = var5[1].split("]\\[");

                        for (String var52 : var27) {
                           var52 = var52.replaceFirst("\\[", "");
                           String[] var11 = var52.split(",", 2);

                           try {
                              int var58 = Integer.parseInt(var11[1]);

                              for (Profile var61 : this.field_0006) {
                                 if (var58 != 0 && var61.getName().equalsIgnoreCase(var11[0])) {
                                    var61.index = var58;
                                 }
                              }
                           } catch (NumberFormatException var22) {
                           }
                        }
                     } else if (var5[0].equalsIgnoreCase("ActiveProfile")) {
                        File var4 = null;
                        File var26 = new File(this.field_0005 + File.separator + "profiles");
                        if (var26.exists() || var26.mkdirs()) {
                           var4 = new File(var26 + File.separator + var5[1] + ".cfg");
                        }

                        if (var4 != null && var4.exists()) {
                           Profile var32 = null;

                           for (Profile var46 : this.field_0006) {
                              if (var5[1].equalsIgnoreCase(var46.getName())) {
                                 var32 = var46;
                              }
                           }

                           if (var32 != null && !var32.getName().equalsIgnoreCase("default")) {
                              this.field_0009 = var32;
                           }
                        }
                     } else {
                        for (Setting var7 : CheatBreaker.getInstance().getGlobalSettings().field_0013) {
                           if (!var7.method_08911().equalsIgnoreCase("label") && var7.method_08911().equalsIgnoreCase(var5[0])) {
                              try {
                                 switch (UnidentifiedClass4506.field_0001[var7.getType().ordinal()]) {
                                    case 1:
                                       var7.setValue(Boolean.parseBoolean(var5[1]));
                                       continue label268;
                                    case 2:
                                       if (var5[1].contains("rainbow")) {
                                          String[] var8 = var5[1].split(";");
                                          int var45 = Integer.parseInt(var8[0]);
                                          var7.field_0013 = true;
                                          if (var45 <= (Integer)var7.method_08878() && var45 >= (Integer)var7.method_08904()) {
                                             var7.setValue(var45);
                                          }
                                       } else {
                                          int var38 = Integer.parseInt(var5[1]);
                                          var7.field_0013 = false;
                                          if (var38 <= (Integer)var7.method_08878() && var38 >= (Integer)var7.method_08904()) {
                                             var7.setValue(var38);
                                          }
                                       }
                                       continue label268;
                                    case 3:
                                       float var9 = Float.parseFloat(var5[1]);
                                       if (var9 <= (Float)var7.method_08878() && var9 >= (Float)var7.method_08904()) {
                                          var7.setValue(var9);
                                       }
                                       continue label268;
                                    case 4:
                                       double var10 = Double.parseDouble(var5[1]);
                                       if (var10 <= (Double)var7.method_08878() && var10 >= (Double)var7.method_08904()) {
                                          var7.setValue(var10);
                                       }
                                       continue label268;
                                    case 5:
                                       ArrayList var12 = new ArrayList<>(Arrays.asList(var5[1].split(",")));
                                       List var13 = var12.stream().map(Integer::parseInt).collect(Collectors.toList());
                                       var7.setValue(var13);
                                       continue label268;
                                    case 6:
                                       boolean var14 = false;
                                       String[] var15 = var7.getAcceptedValues();
                                       int var16 = var15.length;
                                       int var17 = 0;

                                       for (; var17 < var16; var17++) {
                                          String var18 = var15[var17];
                                          if (var18.equalsIgnoreCase(var5[1])) {
                                             var14 = true;
                                          }
                                       }

                                       if (var14) {
                                          var7.setValue(var5[1]);
                                       }
                                    default:
                                       continue label268;
                                 }
                              } catch (Exception var23) {
                                 var23.printStackTrace();
                              }
                           }
                        }
                     }
                  }
               } catch (Exception var24) {
                  var24.printStackTrace();
               }
            }

            var3.close();
         } catch (IOException var25) {
            var25.printStackTrace();
         }

         this.method_25099(var1);
      }
   }

   public void method_25109() {
      if (this.method_25104()) {
         this.method_25099(this.field_0001);
         this.method_25107(this.field_0010);
         this.method_25108(CheatBreaker.getInstance().getConfigManager().field_0009.getName());
      }
   }
}
