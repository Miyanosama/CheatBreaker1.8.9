package net.optifine.shaders.config;

import java.util.ArrayList;
import net.optifine.Lang;
import net.optifine.shaders.ShaderUtils;
import net.optifine.shaders.Shaders;

public class ShaderOptionProfile extends ShaderOption {
   public ShaderOption[] options;
   public static final String recoveredField3871 = "<profile>";
   public static final String recoveredField3872 = "<custom>";
   public ShaderProfile[] profiles = null;

   public static String detectProfileName(ShaderProfile[] var0, ShaderOption[] var1) {
      return detectProfileName(var0, var1, false);
   }

   @Override
   public void nextValue() {
      super.nextValue();
      if (this.getValue().equals("<custom>")) {
         super.nextValue();
      }

      this.applyProfileOptions();
   }

   public ShaderOption getOption(String var1) {
      for (int var2 = 0; var2 < this.options.length; var2++) {
         ShaderOption var3 = this.options[var2];
         if (var3.getName().equals(var1)) {
            return var3;
         }
      }

      return null;
   }

   @Override
   public String getValueText(String var1) {
      return var1.equals("<custom>") ? Lang.get("of.general.custom", "<custom>") : Shaders.translate("profile." + var1, var1);
   }

   public static String[] getProfileNames(ShaderProfile[] var0) {
      ArrayList var1 = new ArrayList();

      for (int var2 = 0; var2 < var0.length; var2++) {
         ShaderProfile var3 = var0[var2];
         var1.add(var3.getName());
      }

      var1.add("<custom>");
      return (java.lang.String[])var1.toArray(new String[var1.size()]);
   }

   public void applyProfileOptions() {
      ShaderProfile var1 = this.getProfile(this.getValue());
      if (var1 != null) {
         String[] var2 = var1.getOptions();

         for (int var3 = 0; var3 < var2.length; var3++) {
            String var4 = var2[var3];
            ShaderOption var5 = this.getOption(var4);
            if (var5 != null) {
               String var6 = var1.getValue(var4);
               var5.setValue(var6);
            }
         }
      }
   }

   @Override
   public String getDescriptionText() {
      String var1 = Shaders.translate("profile.comment", (String)null);
      if (var1 != null) {
         return var1;
      } else {
         StringBuffer var2 = new StringBuffer();

         for (int var3 = 0; var3 < this.profiles.length; var3++) {
            String var4 = this.profiles[var3].getName();
            if (var4 != null) {
               String var5 = Shaders.translate("profile." + var4 + ".comment", (String)null);
               if (var5 != null) {
                  var2.append(var5);
                  if (!var5.endsWith(". ")) {
                     var2.append(". ");
                  }
               }
            }
         }

         return var2.toString();
      }
   }

   public static String detectProfileName(ShaderProfile[] var0, ShaderOption[] var1, boolean var2) {
      ShaderProfile var3 = ShaderUtils.detectProfile(var0, var1, var2);
      return var3 == null ? "<custom>" : var3.getName();
   }

   public ShaderOptionProfile(ShaderProfile[] var1, ShaderOption[] var2) {
      super("<profile>", "", detectProfileName(var1, var2), getProfileNames(var1), detectProfileName(var1, var2, true), (String)null);
      this.options = null;
      this.profiles = var1;
      this.options = var2;
   }

   public ShaderProfile getProfile(String var1) {
      for (int var2 = 0; var2 < this.profiles.length; var2++) {
         ShaderProfile var3 = this.profiles[var2];
         if (var3.getName().equals(var1)) {
            return var3;
         }
      }

      return null;
   }

   @Override
   public String getNameText() {
      return Lang.get("of.shaders.profile");
   }

   @Override
   public String getValueColor(String var1) {
      return var1.equals("<custom>") ? "§c" : "§a";
   }

   public void updateProfile() {
      ShaderProfile var1 = this.getProfile(this.getValue());
      if (var1 == null || !ShaderUtils.matchProfile(var1, this.options, false)) {
         String var2 = detectProfileName(this.profiles, this.options);
         this.setValue(var2);
      }
   }
}
