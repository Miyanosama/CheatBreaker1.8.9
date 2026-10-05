package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.module.AbstractModule;

public class NametagModule extends AbstractModule {
   public Setting recoveredField2911;
   public String[] recoveredField2912 = new String[]{
      " ", "[", "]", "!", "=", "-", "@", "#", "$", "%", "^", "&", "*", "(", ")", "~", "`", "|", ";", ":", "'", "\"", ",", "<", ">", ".", "?", "/"
   };
   public Setting recoveredField2913;
   public Setting recoveredField2914;
   public Setting recoveredField2915;
   public Setting recoveredField2916;
   public Setting recoveredField2917;
   public Setting recoveredField2918;
   public Setting recoveredField2919;
   public Setting recoveredField2920;
   public Setting recoveredField2921;
   public Setting recoveredField2922;

   public NametagModule() {
      super("Nametag");
      this.setDefaultState(true);
      this.recoveredField2919 = new Setting(this, "label").setValue("General Options");
      this.recoveredField2920 = new Setting(this, "Show Background").setValue(true);
      this.recoveredField2918 = new Setting(this, "Text Shadow").setValue(false);
      this.recoveredField2922 = new Setting(this, "Show CheatBreaker Logo").setValue(true);
      this.recoveredField2921 = new Setting(this, "Show Sub Icons").setValue(false).method_08894(this.recoveredField2922::method_08908);
      this.recoveredField2915 = new Setting(this, "Shown own Nametag").setValue(false);
      this.recoveredField2916 = new Setting(this, "Show Nametags when GUI is hidden").setValue(false);
      this.recoveredField2913 = new Setting(this, "label").setValue("Color Options");
      this.recoveredField2917 = new Setting(this, "Custom Text Color").setValue(false);
      this.recoveredField2914 = new Setting(this, "Text Color")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> (Boolean)this.recoveredField2917.getValue());
      this.recoveredField2911 = new Setting(this, "Background Color")
         .setValue(1073741824)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> (Boolean)this.recoveredField2920.getValue());
      this.method_28821("Displays the username above players.");
   }

   public boolean method_21275(String var1) {
      for (String var5 : this.recoveredField2912) {
         if (var1.contains(var5)) {
            return false;
         }
      }

      return true;
   }
}
