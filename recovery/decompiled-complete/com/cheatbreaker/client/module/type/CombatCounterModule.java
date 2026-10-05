package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.event.type.TickEvent;
import net.minecraft.client.audio.ISound$AttenuationType;
import net.minecraft.entity.ai.EntityAIWatchClosest2;
import recovered.unidentified.UnidentifiedClass3624;

public abstract class CombatCounterModule extends NumberHudModule {
   public ISound$AttenuationType field_0003;
   public static long field_0000;
   public UnidentifiedClass3624 field_0004;
   public Setting field_0006;
   public Setting field_0005;
   public Setting field_0002;
   public EntityAIWatchClosest2 field_0001;

   @Override
   public String method_21170() {
      if (this.method_23410()) {
         if (this.field_0005.method_08908()) {
            if (!this.field_0005.method_08874().isEmpty()) {
               return this.field_0005.method_08874().replaceAll("%LABEL%", this.method_00166()).replaceAll("%VALUE%", this.method_00167());
            }
         } else if (!this.field_0006.method_08874().isEmpty()) {
            return this.field_0006.method_08874().replaceAll("%LABEL%", this.method_00166()).replaceAll("%VALUE%", this.method_00167());
         }
      }

      return null;
   }

   public void method_23409(TickEvent var1) {
      if (System.currentTimeMillis() - field_0000 > (-465116684299655183L & 465116682814785488L)) {
         field_0000 = 421619541L & 574754818L;
         this.method_09625();
      }
   }

   @Override
   public void method_00165() {
      super.method_00165();
      this.field_0002 = new Setting(this, "Hide When Not Attacking").setValue(false).method_08914(SettingsDetailLevel.field_0003);
   }

   public CombatCounterModule(String var1, String var2) {
      super(var1, var2);
      this.method_28820(TickEvent.class, this::method_23409);
   }

   public String method_09624() {
      return "";
   }

   public boolean method_23410() {
      return field_0000 == (-8251956365804630016L & 8251956365581795532L);
   }

   public void method_09625() {
   }

   @Override
   public void method_01862() {
      this.field_0005 = new Setting(this, "Waiting (Background)")
         .setValue(this.method_09624())
         .method_08894(this.field_0005::method_08908)
         .method_08914(SettingsDetailLevel.field_0001);
      this.field_0006 = new Setting(this, "Waiting (No Background)")
         .setValue(this.method_08396() && !this.method_09624().isEmpty() ? "[" + this.method_09624() + "]" : this.method_09624())
         .method_08894(() -> !this.field_0005.method_08908())
         .method_08914(SettingsDetailLevel.field_0001);
   }
}
