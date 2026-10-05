package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.module.AbstractModule;
import net.minecraft.network.NetworkSystem;
import net.optifine.VersionCheckThread;

public class AnimationsModule extends AbstractModule {
   public Setting field_0009;
   public Setting field_0011;
   public static Setting field_0003;
   public NetworkSystem field_0004;
   public Setting field_0016;
   public Setting field_0013;
   public VersionCheckThread field_0018;
   public Setting field_0015;
   public static Setting field_0001;
   public Setting field_0007;
   public static Setting field_0010;
   public Setting field_0014;
   public Setting field_0002;
   public Setting field_0008;
   public Setting field_0006;
   public Setting field_0017;
   public Setting field_0005;
   public Setting field_0012;
   public Setting field_0000;

   public AnimationsModule() {
      super("Animations");
      this.method_28821("Set visuals to legacy and modern versions alike.");
      this.setPreviewLabel("1.7 <-> 1.8", 1.0F);
      this.method_28829("OrangeMarshall (Original)", "Sk1er (Revised)");
      this.method_28807("1.7 Visuals", "Old Animations");
      this.method_28823("Punch During Usage is disabled.", "minemenclub");
      this.setDefaultState(true);
      new Setting(this, "label").setValue("Position Settings");
      this.field_0005 = new Setting(this, "Item Positions", "Change all item models to be in the same position as 1.7.")
         .setValue(true)
         .method_08916("1.8", "1.7");
      this.field_0011 = new Setting(this, "Bow Pullback", "Change the bow pullback animation to be like 1.7.").setValue(true).method_08916("1.8", "1.7");
      this.field_0000 = new Setting(this, "Block Animation", "Change the sword block animation to be like 1.7.").setValue(true).method_08916("1.8", "1.7");
      this.field_0012 = new Setting(this, "Rod Position", "Change all item models to be in the same position as 1.7.")
         .setValue(true)
         .method_08916("1.8", "1.7");
      this.field_0016 = new Setting(this, "3rd Person Block Animation", "Change the 3rd person blocking animation to be like 1.7.")
         .setValue(true)
         .method_08916("1.8", "1.7");
      new Setting(this, "label").setValue("Interaction Settings");
      this.field_0007 = new Setting(this, "Consume Animation", "Change the eating and drinking animation to look like 1.7.")
         .setValue(true)
         .method_08916("1.8", "1.7");
      this.field_0013 = new Setting(this, "Block-Hitting Animation", "Makes block hitting look much smoother, like it did in 1.7.")
         .setValue(true)
         .method_08916("1.8", "1.7");
      this.field_0014 = new Setting(
            this, "Smooth Sneaking", "Makes the transition between sneaking/not sneaking smooth.\\n§eCombine with longer unsneak to match 1.7."
         )
         .setValue(true);
      this.field_0009 = new Setting(this, "Longer Unsneak", "Makes moving up take longer than moving down\\n§eCombine with smooth sneaking to match 1.7.")
         .setValue(true)
         .method_08894(() -> this.field_0014.method_08908());
      this.field_0008 = new Setting(this, "Punching During Usage", "Allows you to punch blocks whilst using an item.\\n§eVisual only.")
         .setValue(true)
         .method_08916("1.8", "1.7");
      this.field_0002 = new Setting(this, "Item Switching Animation", "Stop held items from playing the switching animation when right clicking on blocks.")
         .setValue(true)
         .method_08916("1.8", "1.7");
      this.field_0015 = new Setting(
            this, "Hit Color Armor", "1.7: Make the hit color apply to the armor.\n1.8: Make the hit color only apply to the player skin."
         )
         .setValue(true)
         .method_08917(var0 -> CheatBreaker.getInstance().getModuleManager().field_0004.field_0002.setValue(var0))
         .method_08916("1.8", "1.7");
      this.field_0006 = new Setting(this, "Hit Color Brightness", "Set the hit color to use 1.7's brightness system, darkening the hit color.")
         .setValue(true)
         .method_08916("1.8", "1.7");
      new Setting(this, "label").setValue("HUD Settings");
      this.field_0017 = new Setting(this, "Health Bar Flashing", "Stops your health bar flashing when you take damage.")
         .setValue(true)
         .method_08916("1.8", "1.7");
   }
}
