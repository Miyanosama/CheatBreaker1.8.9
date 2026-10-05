package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.module.AbstractModule;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ResourceLocation;

public class NickHiderModule extends AbstractModule {
   public Setting recoveredField849;
   public Setting recoveredField850;
   public Setting recoveredField851;
   public Setting recoveredField852;

   public NickHiderModule() {
      super("Nick Hider");
      new Setting(this, "label").setValue("Skin Options");
      this.recoveredField852 = new Setting(this, "Hide Own Skin").setValue(false).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField851 = new Setting(this, "Hide Other Skins").setValue(false).method_08914(SettingsDetailLevel.SIMPLE);
      new Setting(this, "label").setValue("Name Options");
      this.recoveredField850 = new Setting(this, "Hide Real Name").setValue(false).method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField849 = new Setting(this, "Custom Name String")
         .setValue(Minecraft.getMinecraft().getSession().getUsername())
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> this.recoveredField850.method_08908());
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/nickhider.png"), 32, 32);
      this.method_28821("Hides your identity client-sided.");
      this.method_28829("Sk1er");
      this.setDefaultState(false);
   }
}
