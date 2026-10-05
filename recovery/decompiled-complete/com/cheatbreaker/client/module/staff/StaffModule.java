package com.cheatbreaker.client.module.staff;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.module.AbstractModule;
import io.netty.util.concurrent.ScheduledFutureTask;
import net.minecraft.village.MerchantRecipe;
import org.apache.log4j.lf5.viewer.configure.ConfigurationManager;
import org.apache.log4j.pattern.MethodLocationPatternConverter;

public class StaffModule extends AbstractModule {
   public MethodLocationPatternConverter field_0002;
   public MerchantRecipe field_0003;
   public Setting keybind = new Setting(this, "Keybind").setValue(0);
   public ScheduledFutureTask field_0001;
   public ConfigurationManager field_0004;

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
