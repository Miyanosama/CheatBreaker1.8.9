package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.module.AbstractModule;
import net.minecraft.client.renderer.entity.RenderPlayer;
import org.apache.log4j.pattern.FileDatePatternConverter;
import recovered.unidentified.UnidentifiedClass1464;

public class ParticlesModule extends AbstractModule {
   public Setting field_0010;
   public Setting field_0012;
   public Setting field_0003;
   public Setting field_0004;
   public Setting field_0018;
   public Setting field_0014;
   public Setting field_0021;
   public Setting field_0017;
   public Setting field_0001;
   public Setting field_0007;
   public Setting field_0011;
   public Setting field_0015;
   public Setting field_0002;
   public Setting field_0009;
   public FileDatePatternConverter field_0006;
   public Setting field_0020;
   public Setting field_0005;
   public Setting field_0013;
   public Setting field_0000;
   public RenderPlayer field_0008;
   public Setting field_0019 = new Setting(this, "label").setValue("General Particles");
   public UnidentifiedClass1464 field_0016;

   public ParticlesModule() {
      super("Particles");
      this.field_0014 = new Setting(this, "Show Sprinting Particles").setValue(true);
      this.field_0020 = new Setting(this, "Show Block Breaking Particles").setValue(true);
      this.field_0007 = new Setting(this, "Lazily Load Particles").setValue(false);
      this.field_0011 = new Setting(this, "Lazy Particle Amount").setValue(5.0F).setMinMax(0.1F, 10.0F).method_08892("x");
      this.field_0000 = new Setting(this, "label").setValue("Attack Particles");
      this.field_0004 = new Setting(this, "Show When Damaged").setValue("Override").acceptedValues("OFF", "Vanilla", "Override");
      this.field_0005 = new Setting(this, "Show When Attacking").setValue("Override").acceptedValues("OFF", "Vanilla", "Override");
      this.field_0001 = new Setting(this, "Sharpness Particles")
         .setValue("Vanilla")
         .acceptedValues("Never", "Vanilla", "Always")
         .method_08894(() -> this.field_0004.getValue().equals("Override") || this.field_0005.getValue().equals("Override"));
      this.field_0003 = new Setting(this, "Sharpness Multiplier")
         .setValue(1.0F)
         .setMinMax(0.1F, 10.0F)
         .method_08892("x")
         .method_08894(
            () -> !this.field_0001.getValue().equals("Never")
               && (this.field_0004.getValue().equals("Override") || this.field_0005.getValue().equals("Override"))
         );
      this.field_0010 = new Setting(this, "Crit Particles")
         .setValue("Vanilla")
         .acceptedValues("Never", "Vanilla", "Always")
         .method_08894(() -> this.field_0004.getValue().equals("Override") || this.field_0005.getValue().equals("Override"));
      this.field_0017 = new Setting(this, "Crit Multiplier")
         .setValue(1.0F)
         .setMinMax(0.1F, 10.0F)
         .method_08892("x")
         .method_08894(
            () -> !this.field_0010.getValue().equals("Never")
               && (this.field_0004.getValue().equals("Override") || this.field_0005.getValue().equals("Override"))
         );
      this.field_0015 = new Setting(this, "label").setValue("Effect Particles");
      this.field_0018 = new Setting(this, "Show Splash Particles").setValue(true);
      this.field_0013 = new Setting(this, "Instant Splash Opacity")
         .setValue(100.0F)
         .setMinMax(0.01F, 100.0F)
         .method_08892("%")
         .method_08894(() -> (Boolean)this.field_0018.getValue());
      this.field_0009 = new Setting(this, "Normal Splash Opacity")
         .setValue(100.0F)
         .setMinMax(0.01F, 100.0F)
         .method_08892("%")
         .method_08894(() -> (Boolean)this.field_0018.getValue());
      this.field_0012 = new Setting(this, "Show Active Effect Particles").setValue(true);
      this.field_0002 = new Setting(this, "Active Effect Opacity")
         .setValue(100.0F)
         .setMinMax(0.01F, 100.0F)
         .method_08892("%")
         .method_08894(() -> (Boolean)this.field_0012.getValue());
      this.field_0021 = new Setting(this, "Ambient Effect Opacity")
         .setValue(15.0F)
         .setMinMax(0.01F, 100.0F)
         .method_08892("%")
         .method_08894(() -> (Boolean)this.field_0012.getValue());
      this.setPreviewLabel("Particles", 1.0F);
      this.method_28821("Change how particles appear.");
      this.method_28829("dewgs (Multipliers)");
   }
}
