package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.module.AbstractModule;
import net.minecraft.world.chunk.Chunk$3;
import net.minecraft.world.gen.structure.StructureOceanMonument;

public class NametagModule extends AbstractModule {
   public Setting field_0006;
   public String[] field_0008 = new String[]{
      " ", "[", "]", "!", "=", "-", "@", "#", "$", "%", "^", "&", "*", "(", ")", "~", "`", "|", ";", ":", "'", "\"", ",", "<", ">", ".", "?", "/"
   };
   public Setting field_0002;
   public Setting field_0003;
   public Setting field_0012;
   public Setting field_0009;
   public Setting field_0013;
   public Setting field_0011;
   public Setting field_0000;
   public StructureOceanMonument field_0004;
   public Setting field_0007;
   public Setting field_0010;
   public Setting field_0001;
   public Chunk$3 field_0005;

   public NametagModule() {
      super("Nametag");
      this.setDefaultState(true);
      this.field_0000 = new Setting(this, "label").setValue("General Options");
      this.field_0007 = new Setting(this, "Show Background").setValue(true);
      this.field_0011 = new Setting(this, "Text Shadow").setValue(false);
      this.field_0001 = new Setting(this, "Show CheatBreaker Logo").setValue(true);
      this.field_0010 = new Setting(this, "Show Sub Icons").setValue(false).method_08894(this.field_0001::method_08908);
      this.field_0012 = new Setting(this, "Shown own Nametag").setValue(false);
      this.field_0009 = new Setting(this, "Show Nametags when GUI is hidden").setValue(false);
      this.field_0002 = new Setting(this, "label").setValue("Color Options");
      this.field_0013 = new Setting(this, "Custom Text Color").setValue(false);
      this.field_0003 = new Setting(this, "Text Color")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> (Boolean)this.field_0013.getValue());
      this.field_0006 = new Setting(this, "Background Color")
         .setValue(1073741824)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08894(() -> (Boolean)this.field_0007.getValue());
      this.method_28821("Displays the username above players.");
   }

   public boolean method_21275(String var1) {
      for (String var5 : this.field_0008) {
         if (var1.contains(var5)) {
            return false;
         }
      }

      return true;
   }
}
