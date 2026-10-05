package com.cheatbreaker.client.module.type;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.event.type.TickEvent;

public abstract class CombatCounterModule extends NumberHudModule {
   public static long recoveredField1835;
   public Setting recoveredField1836;
   public Setting recoveredField1837;
   public Setting recoveredField1838;

   @Override
   public String method_21170() {
      if (this.method_23410()) {
         if (this.recoveredField2245.method_08908()) {
            if (!this.recoveredField1837.method_08874().isEmpty()) {
               return this.recoveredField1837.method_08874().replaceAll("%LABEL%", this.method_00166()).replaceAll("%VALUE%", this.method_00167());
            }
         } else if (!this.recoveredField1836.method_08874().isEmpty()) {
            return this.recoveredField1836.method_08874().replaceAll("%LABEL%", this.method_00166()).replaceAll("%VALUE%", this.method_00167());
         }
      }

      return null;
   }

   public void method_23409(TickEvent var1) {
      if (System.currentTimeMillis() - recoveredField1835 > 2000L) {
         recoveredField1835 = 0L;
         this.method_09625();
      }
   }

   @Override
   public void method_00165() {
      super.method_00165();
      this.recoveredField1838 = new Setting(this, "Hide When Not Attacking").setValue(false).method_08914(SettingsDetailLevel.MEDIUM);
   }

   public CombatCounterModule(String var1, String var2) {
      super(var1, var2);
      this.method_28820(TickEvent.class, this::method_23409);
   }

   public String method_09624() {
      return "";
   }

   public boolean method_23410() {
      return recoveredField1835 == 0L;
   }

   public void method_09625() {
   }

   @Override
   public void method_01862() {
      this.recoveredField1837 = new Setting(this, "Waiting (Background)")
         .setValue(this.method_09624())
         .method_08894(this.recoveredField2245::method_08908)
         .method_08914(SettingsDetailLevel.ADVANCED);
      this.recoveredField1836 = new Setting(this, "Waiting (No Background)")
         .setValue(this.method_08396() && !this.method_09624().isEmpty() ? "[" + this.method_09624() + "]" : this.method_09624())
         .method_08894(() -> !this.recoveredField2245.method_08908())
         .method_08914(SettingsDetailLevel.ADVANCED);
   }
}
