package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.module.AbstractModule;
import net.minecraft.util.ResourceLocation;

public class HurtcamModule extends AbstractModule {
   public final Setting intensity;

   public HurtcamModule() {
      super("Hurtcam");
      this.setDefaultState(false);
      this.intensity = new Setting(this, "Intensity", "0% disables hurt camera shake; 100% uses vanilla strength.")
         .setValue(100.0F).setMinMax(0.0F, 100.0F).method_08892("%")
         .method_08914(SettingsDetailLevel.SIMPLE);
      this.method_28821("Adjust camera shake when taking damage.");
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/hittint.png"), 32, 32);
   }

   public float getIntensityMultiplier() {
      float percent = this.intensity.method_08905();
      return Float.isNaN(percent) ? 1.0F : Math.max(0.0F, Math.min(100.0F, percent)) / 100.0F;
   }
}
