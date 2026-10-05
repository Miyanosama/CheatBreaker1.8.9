package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.module.AbstractModule;
import net.minecraft.util.ResourceLocation;

public class DamageTintModule extends AbstractModule {
   public Setting recoveredField3678;
   public Setting recoveredField3679;
   public Setting recoveredField3680;
   public Setting recoveredField3681;

   public DamageTintModule() {
      super("Hit Color");
      this.setDefaultState(true);
      new Setting(this, "label").setValue("General Options");
      this.recoveredField3678 = new Setting(this, "Affect Armor")
         .method_08917(var0 -> CheatBreaker.getInstance().getModuleManager().recoveredField1717.recoveredField3725.setValue(var0))
         .setValue(true);
      this.recoveredField3680 = new Setting(this, "Affected by brightness").setValue(true);
      this.recoveredField3681 = new Setting(this, "Animation Type").setValue("None").acceptedValues("None", "Linear In/Out", "Linear Out");
      new Setting(this, "label").setValue("Color Options");
      this.recoveredField3679 = new Setting(this, "Hit Color").setValue(1727987712).setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE);
      this.method_28821("Customize the damage tint overlay on entities.");
      this.method_28829("aycy (Fade animation)");
      this.method_28807("Damage Tint");
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/hittint.png"), 32, 32);
   }
}
