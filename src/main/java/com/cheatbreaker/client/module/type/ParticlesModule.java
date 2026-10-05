package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.module.AbstractModule;

public class ParticlesModule extends AbstractModule {
   public Setting recoveredField1621;
   public Setting recoveredField1622;
   public Setting recoveredField1623;
   public Setting recoveredField1624;
   public Setting recoveredField1625;
   public Setting recoveredField1626;
   public Setting recoveredField1627;
   public Setting recoveredField1628;
   public Setting recoveredField1629;
   public Setting recoveredField1630;
   public Setting recoveredField1631;
   public Setting recoveredField1632;
   public Setting recoveredField1633;
   public Setting recoveredField1634;
   public Setting recoveredField1635;
   public Setting recoveredField1636;
   public Setting recoveredField1637;
   public Setting recoveredField1638;
   public Setting recoveredField1639 = new Setting(this, "label").setValue("General Particles");

   public ParticlesModule() {
      super("Particles");
      this.recoveredField1626 = new Setting(this, "Show Sprinting Particles").setValue(true);
      this.recoveredField1635 = new Setting(this, "Show Block Breaking Particles").setValue(true);
      this.recoveredField1630 = new Setting(this, "Lazily Load Particles").setValue(false);
      this.recoveredField1631 = new Setting(this, "Lazy Particle Amount").setValue(5.0F).setMinMax(0.1F, 10.0F).method_08892("x");
      this.recoveredField1638 = new Setting(this, "label").setValue("Attack Particles");
      this.recoveredField1624 = new Setting(this, "Show When Damaged").setValue("Override").acceptedValues("OFF", "Vanilla", "Override");
      this.recoveredField1636 = new Setting(this, "Show When Attacking").setValue("Override").acceptedValues("OFF", "Vanilla", "Override");
      this.recoveredField1629 = new Setting(this, "Sharpness Particles")
         .setValue("Vanilla")
         .acceptedValues("Never", "Vanilla", "Always")
         .method_08894(() -> this.recoveredField1624.getValue().equals("Override") || (Boolean)this.recoveredField1636.getValue().equals("Override"));
      this.recoveredField1623 = new Setting(this, "Sharpness Multiplier")
         .setValue(1.0F)
         .setMinMax(0.1F, 10.0F)
         .method_08892("x")
         .method_08894(
            () -> !this.recoveredField1629.getValue().equals("Never")
               && (this.recoveredField1624.getValue().equals("Override") || (Boolean)this.recoveredField1636.getValue().equals("Override"))
         );
      this.recoveredField1621 = new Setting(this, "Crit Particles")
         .setValue("Vanilla")
         .acceptedValues("Never", "Vanilla", "Always")
         .method_08894(() -> this.recoveredField1624.getValue().equals("Override") || (Boolean)this.recoveredField1636.getValue().equals("Override"));
      this.recoveredField1628 = new Setting(this, "Crit Multiplier")
         .setValue(1.0F)
         .setMinMax(0.1F, 10.0F)
         .method_08892("x")
         .method_08894(
            () -> !this.recoveredField1621.getValue().equals("Never")
               && (this.recoveredField1624.getValue().equals("Override") || (Boolean)this.recoveredField1636.getValue().equals("Override"))
         );
      this.recoveredField1632 = new Setting(this, "label").setValue("Effect Particles");
      this.recoveredField1625 = new Setting(this, "Show Splash Particles").setValue(true);
      this.recoveredField1637 = new Setting(this, "Instant Splash Opacity")
         .setValue(100.0F)
         .setMinMax(0.01F, 100.0F)
         .method_08892("%")
         .method_08894(() -> (Boolean)this.recoveredField1625.getValue());
      this.recoveredField1634 = new Setting(this, "Normal Splash Opacity")
         .setValue(100.0F)
         .setMinMax(0.01F, 100.0F)
         .method_08892("%")
         .method_08894(() -> (Boolean)this.recoveredField1625.getValue());
      this.recoveredField1622 = new Setting(this, "Show Active Effect Particles").setValue(true);
      this.recoveredField1633 = new Setting(this, "Active Effect Opacity")
         .setValue(100.0F)
         .setMinMax(0.01F, 100.0F)
         .method_08892("%")
         .method_08894(() -> (Boolean)this.recoveredField1622.getValue());
      this.recoveredField1627 = new Setting(this, "Ambient Effect Opacity")
         .setValue(15.0F)
         .setMinMax(0.01F, 100.0F)
         .method_08892("%")
         .method_08894(() -> (Boolean)this.recoveredField1622.getValue());
      this.setPreviewLabel("Particles", 1.0F);
      this.method_28821("Change how particles appear.");
      this.method_28829("dewgs (Multipliers)");
   }
}
