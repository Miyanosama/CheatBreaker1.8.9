package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.module.AbstractModule;
import io.netty.channel.epoll.EpollSocketChannel$EpollSocketUnsafe$3;
import net.minecraft.block.Block$EnumOffsetType;
import net.minecraft.network.play.server.S1BPacketEntityAttach;
import net.minecraft.util.ResourceLocation;

public class TextureOptionsModule extends AbstractModule {
   public S1BPacketEntityAttach field_0011;
   public Setting field_0013;
   public Setting field_0003;
   public EpollSocketChannel$EpollSocketUnsafe$3 field_0005;
   public Setting field_0020;
   public Setting field_0015;
   public Setting field_0024;
   public Setting field_0019;
   public Setting field_0001;
   public Block$EnumOffsetType field_0008;
   public Setting field_0012;
   public Setting field_0016;
   public Setting field_0002;
   public PotionCounterModule field_0010;
   public Setting field_0007;
   public Setting field_0022;
   public Setting field_0006;
   public Setting field_0014;
   public Setting field_0000;
   public Setting field_0009;
   public Setting field_0021;
   public Setting field_0017;
   public Setting field_0023;
   public Setting field_0004;
   public Setting field_0018;

   public TextureOptionsModule() {
      super("Pack Tweaks");
      this.method_28821("Change how specific textures appear.");
      this.setDefaultState(true);
      new Setting(this, "label").setValue("Glass Options").method_08914(SettingsDetailLevel.field_0000);
      this.field_0004 = new Setting(
            this,
            "Clear Glass",
            "Removes the glass texture.\n\n§bOFF:§r No glass textures are removed.\n§bREGULAR:§r Only normal glass texture is removed.\n§bALL:§r Normal and stained glass textures are removed."
         )
         .setValue("OFF")
         .acceptedValues("OFF", "REGULAR", "ALL")
         .onChange(var1 -> this.minecraft.renderGlobal.loadRenderers())
         .method_08914(SettingsDetailLevel.field_0000);
      new Setting(this, "label").setValue("String Options").method_08914(SettingsDetailLevel.field_0000).method_08894(() -> "1.8.9".equals("1.7.10"));
      this.field_0012 = new Setting(this, "Colored String", "Show a solid color line instead of the texture pack's string texture.")
         .setValue(false)
         .method_08894(() -> "1.8.9".equals("1.7.10"))
         .onChange(var1 -> this.minecraft.renderGlobal.loadRenderers())
         .method_08914(SettingsDetailLevel.field_0000);
      this.field_0015 = new Setting(this, "Colored String Color")
         .setValue(-65536)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> (Boolean)this.field_0012.getValue() && "1.8.9".equals("1.7.10"));
      new Setting(this, "label").setValue("Fire Options").method_08914(SettingsDetailLevel.field_0000);
      this.field_0009 = new Setting(this, "First Person Fire").setValue(true).method_08914(SettingsDetailLevel.field_0000);
      this.field_0017 = new Setting(this, "Fire Opacity")
         .setValue(90.0F)
         .setMinMax(0.0F, 100.0F)
         .method_08892("%")
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> (Boolean)this.field_0009.getValue());
      this.field_0002 = new Setting(this, "Fire Height")
         .setValue(1.0F)
         .setMinMax(0.0F, 2.0F)
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> (Boolean)this.field_0009.getValue());
      this.field_0016 = new Setting(this, "Fire on Entities").setValue(true).method_08914(SettingsDetailLevel.field_0000);
      new Setting(this, "label").setValue("Vignette Options").method_08914(SettingsDetailLevel.field_0000);
      this.field_0023 = new Setting(this, "Vignette Type")
         .setValue("Vanilla")
         .acceptedValues("Vanilla", "Static", "Amplified")
         .method_08914(SettingsDetailLevel.field_0000);
      this.field_0021 = new Setting(this, "Vignette Opacity")
         .setValue(75.0F)
         .setMinMax(0.0F, 100.0F)
         .method_08892("%")
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> this.field_0023.getValue().equals("Static"));
      this.field_0006 = new Setting(this, "Vignette Minimum Opacity")
         .setValue(0.0F)
         .setMinMax(0.0F, 100.0F)
         .method_08892("%")
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> this.field_0023.getValue().equals("Amplified"));
      this.field_0018 = new Setting(this, "Vignette Maximum Opacity")
         .setValue(100.0F)
         .setMinMax(0.0F, 100.0F)
         .method_08892("%")
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> this.field_0023.getValue().equals("Amplified"));
      this.field_0020 = new Setting(this, "Vignette Opacity Multiplier")
         .setValue(1.0F)
         .setMinMax(0.1F, 10.0F)
         .method_08892("x")
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> this.field_0023.getValue().equals("Amplified"));
      new Setting(this, "label").setValue("Low Health Options").method_08914(SettingsDetailLevel.field_0003);
      this.field_0001 = new Setting(this, "Low Health Overlay").setValue(false).method_08914(SettingsDetailLevel.field_0003);
      this.field_0003 = new Setting(this, "Low Health Start")
         .setValue(3.5F)
         .setMinMax(1.0F, 10.0F)
         .method_08892(" hearts")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> this.field_0001.method_08908());
      this.field_0024 = new Setting(this, "Low Health Opacity")
         .setValue(75.0F)
         .setMinMax(0.0F, 100.0F)
         .method_08892("%")
         .method_08914(SettingsDetailLevel.field_0003)
         .method_08894(() -> this.field_0001.method_08908());
      new Setting(this, "label").setValue("Pumpkin Overlay Options").method_08914(SettingsDetailLevel.field_0000);
      this.field_0022 = new Setting(this, "Show Pumpkin Overlay").setValue(true).method_08914(SettingsDetailLevel.field_0000);
      this.field_0000 = new Setting(this, "Pumpkin Overlay Opacity")
         .setValue(100.0F)
         .setMinMax(0.0F, 100.0F)
         .method_08892("%")
         .method_08914(SettingsDetailLevel.field_0000)
         .method_08894(() -> (Boolean)this.field_0022.getValue());
      new Setting(this, "label").setValue("Inventory Options").method_08914(SettingsDetailLevel.field_0000);
      this.field_0013 = new Setting(this, "Transparent Inventory").setValue(false).method_08914(SettingsDetailLevel.field_0000);
      this.field_0019 = new Setting(this, "Show Inventory Tooltips").setValue(true).method_08914(SettingsDetailLevel.field_0000);
      this.setPreviewIcon(new ResourceLocation("client/icons/mods/packtweaks.png"), 32, 32);
   }
}
