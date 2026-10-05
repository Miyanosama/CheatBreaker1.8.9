package com.cheatbreaker.client.module.staff;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.module.AbstractModule;

public class StaffModule extends AbstractModule {
   public Setting keybind = new Setting(this, "Keybind").setValue(0);

   public StaffModule(String var1) {
      super(var1);
   }

   public Setting getKeybindSetting() {
      return this.keybind;
   }

   public void disableStaffModule() {
      if (this.isEnabled()) {
         this.setState(false);
      }

      this.setStaffModuleEnabled(false);
   }
}
