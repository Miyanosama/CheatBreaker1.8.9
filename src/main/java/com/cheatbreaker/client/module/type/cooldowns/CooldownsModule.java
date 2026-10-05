package com.cheatbreaker.client.module.type.cooldowns;

import com.cheatbreaker.client.config.Setting;
import com.cheatbreaker.client.config.SettingsDetailLevel;
import com.cheatbreaker.client.event.type.GuiDrawEvent;
import com.cheatbreaker.client.event.type.TickEvent;
import com.cheatbreaker.client.module.AbstractModule;
import com.cheatbreaker.client.ui.module.CBGuiAnchor;
import com.cheatbreaker.client.ui.module.CBModulesGui;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.GL11;
import com.cheatbreaker.client.ui.module.ModulePlacementGui;
import com.cheatbreaker.client.event.type.HudPreviewDrawEvent;

public class CooldownsModule extends AbstractModule {
   public static List<CooldownRenderer> real = new ArrayList<>();
   public List<CooldownRenderer> recoveredField649 = new ArrayList<>();
   public Setting recoveredField650;
   public Setting recoveredField651;
   public Setting recoveredField652;
   public Setting recoveredField653;
   public Setting recoveredField654;

   public void method_28308(HudPreviewDrawEvent var1) {
      if (this.method_28866()) {
         if (real.isEmpty()) {
            GL11.glPushMatrix();
            if (this.recoveredField649.isEmpty()) {
               this.recoveredField649.add(new CooldownRenderer("CombatTag", 283, 30000L));
               this.recoveredField649.add(new CooldownRenderer("EnderPearl", 368, 12000L));
            }

            this.scaleAndTranslate(var1.method_01054());
            boolean var2 = ((String)this.recoveredField651.getValue()).equalsIgnoreCase("Vertical");
            byte var3 = 36;
            byte var4 = 36;
            int var5 = var2 ? var3 : this.recoveredField649.size() * var3;
            int var6 = var2 ? this.recoveredField649.size() * var4 : var4;
            this.method_28812((int)var5, (int)var6 - 1);

            for (int var7 = 0; var7 < this.recoveredField649.size(); var7++) {
               CooldownRenderer var8 = this.recoveredField649.get(var7);
               if (((String)this.recoveredField651.getValue()).equalsIgnoreCase("Vertical")) {
                  var8.method_06971(this.recoveredField652, this.recoveredField3889 / 2.0F - var3 / 2, var7 * var4, this.recoveredField654.method_08901());
               } else {
                  var8.method_06971(this.recoveredField652, var7 * var3, 0.0F, this.recoveredField654.method_08901());
               }
            }

            GL11.glPopMatrix();
         }
      }
   }

   public void method_28310(String var1, long var2, int var4) {
      for (CooldownRenderer var6 : real) {
         if (var6.method_06975().equalsIgnoreCase(var1) && var6.method_06969() == var4) {
            var6.method_06967();
            var6.method_06970(var2);
            return;
         }
      }

      real.add(new CooldownRenderer(var1, var4, var2));
   }

   public void method_28311(GuiDrawEvent var1) {
      if (this.method_28866() && (!this.recoveredField3905 || this.minecraft.currentScreen instanceof CBModulesGui)) {
         GL11.glPushMatrix();
         if (real.size() > 0) {
            this.scaleAndTranslate(var1.getResolution());
            boolean var2 = ((String)this.recoveredField651.getValue()).equalsIgnoreCase("Vertical");
            byte var3 = 36;
            byte var4 = 36;
            int var5 = var2 ? var3 : real.size() * var3;
            int var6 = var2 ? real.size() * var4 : var4;
            this.method_28812(var5, var6 - 1);

            for (int var7 = 0; var7 < real.size(); var7++) {
               CooldownRenderer var8 = real.get(var7);
               if (((String)this.recoveredField651.getValue()).equalsIgnoreCase("Vertical")) {
                  var8.method_06971(this.recoveredField652, this.recoveredField3889 / 2.0F - var3 / 2, var7 * var4, this.recoveredField654.method_08901());
               } else {
                  var8.method_06971(this.recoveredField652, var7 * var3, 0.0F, this.recoveredField654.method_08901());
               }
            }
         } else if (!(this.minecraft.currentScreen instanceof CBModulesGui) && !(this.minecraft.currentScreen instanceof ModulePlacementGui)) {
            this.method_28812(50.0F, 24.0F);
            this.scaleAndTranslate(var1.getResolution());
         }

         GL11.glPopMatrix();
      }
   }

   public CooldownsModule() {
      super("Cooldowns", "Normal");
      this.setDefaultAnchor(CBGuiAnchor.MIDDLE_TOP);
      this.setDefaultTranslations(0.0F, 5.0F);
      this.recoveredField652 = new Setting(this, "Color Theme")
         .setValue("Bright")
         .acceptedValues("Bright", "Dark", "Colored", "No Ring")
         .method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField651 = new Setting(this, "List Mode")
         .setValue("Horizontal")
         .acceptedValues("Vertical", "Horizontal")
         .method_08914(SettingsDetailLevel.SIMPLE);
      this.recoveredField650 = new Setting(this, "Use Pearl Cooldown via XP").setValue(true);
      this.recoveredField653 = new Setting(this, "Decimals").setValue(1).setMinMax(0, 3).method_08914(SettingsDetailLevel.MEDIUM);
      this.recoveredField654 = new Setting(this, "Colored color")
         .setValue(-1)
         .setMinMax(Integer.MIN_VALUE, Integer.MAX_VALUE)
         .method_08914(SettingsDetailLevel.SIMPLE)
         .method_08894(() -> this.recoveredField652.getValue().equals("Colored"));
      this.method_28821("Allows servers to display items or abilities that are on cooldown.");
      this.method_28820(TickEvent.class, this::onTick);
      this.method_28820(HudPreviewDrawEvent.class, this::method_28308);
      this.method_28820(GuiDrawEvent.class, this::method_28311);
      this.setDefaultState(true);
   }

   public void onTick(TickEvent var1) {
      if (!real.isEmpty()) {
         real.removeIf(CooldownRenderer::isTimeOver);
      }

      if (!this.recoveredField649.isEmpty()) {
         this.recoveredField649.removeIf(CooldownRenderer::isTimeOver);
      }
   }
}
