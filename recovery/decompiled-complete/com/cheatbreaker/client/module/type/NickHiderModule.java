package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.module.AbstractModule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.Stitcher$Holder;
import net.minecraft.util.ResourceLocation;
import org.json.JSONML;

public class NickHiderModule extends AbstractModule {
   public Stitcher$Holder field_0002;
   public Setting field_0003;
   public Setting field_0000;
   public Setting field_0001;
   public Setting field_0005;
   public JSONML field_0004;

   public NickHiderModule() {
      super("Nick Hider");
      new Setting(this, "label").setValue("Skin Options");
      this.field_0005 = new Setting(this, "Hide Own Skin").setValue(false).method_08914(SettingsDetailLevel.field_0000);
      this.field_0001 = new Setting(this, "Hide Other Skins").setValue(false).method_08914(SettingsDetailLevel.field_0000);
      new Setting(this, "label").setValue("Name Options");
      this.field_0000 = new Setting(this, "Hide Real Name").setValue(false).method_08914(SettingsDetailLevel.field_0000);
      this.field_0003 = new Setting(this, "Custom Name String")
         .setValue(Minecraft.getMinecraft().getSession().getUsername())
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> this.field_0000.method_08908());
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/nickhider.png"), 32, 32);
      this.method_28821("Hides your identity client-sided.");
      this.method_28829("Sk1er");
      this.setDefaultState(false);
   }
}
