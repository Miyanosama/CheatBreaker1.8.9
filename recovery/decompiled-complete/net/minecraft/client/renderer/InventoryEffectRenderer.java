package net.minecraft.client.renderer;

import com.cheatbreaker.client.CheatBreaker;
import com.cheatbreaker.client.module.type.AutoTextModule;
import java.util.Collection;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.resources.data.IMetadataSerializer;
import net.minecraft.inventory.Container;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.RegistrySimple;

public abstract class InventoryEffectRenderer extends GuiContainer {
   public IMetadataSerializer field_0000;
   public AutoTextModule field_0002;
   public boolean hasActivePotionEffects;
   public RegistrySimple field_0001;

   @Override
   public void initGui() {
      super.initGui();
      this.updateActivePotionEffects();
   }

   public void updateActivePotionEffects() {
      if (!this.j.thePlayer.getActivePotionEffects().isEmpty()) {
         boolean var1 = CheatBreaker.getInstance().getModuleManager().potionStatus.isEnabled()
            && CheatBreaker.getInstance().getModuleManager().potionStatus.field_0038.method_08908();
         boolean var2 = !CheatBreaker.getInstance().getModuleManager().potionStatus.isEnabled()
            && CheatBreaker.getInstance().getGlobalSettings().field_0059.method_08908();
         if (!this.j.thePlayer.getActivePotionEffects().isEmpty() && (var1 || var2)) {
            if (CheatBreaker.getInstance().getGlobalSettings().field_0078.method_08908()) {
               this.i = 160 + (this.l - this.f - 200) / 2;
            }

            this.hasActivePotionEffects = true;
         }
      } else {
         this.i = (this.l - this.f) / 2;
         this.hasActivePotionEffects = false;
      }
   }

   @Override
   public void drawScreen(int var1, int var2, float var3) {
      super.drawScreen(var1, var2, var3);
      if (this.hasActivePotionEffects) {
         this.drawActivePotionEffects();
      }
   }

   public void drawActivePotionEffects() {
      int var1 = this.i - 124;
      int var2 = this.r;
      short var3 = 166;
      Collection var4 = this.j.thePlayer.getActivePotionEffects();
      if (!var4.isEmpty()) {
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.disableLighting();
         int var5 = 33;
         if (var4.size() > 5) {
            var5 = 132 / (var4.size() - 1);
         }

         for (PotionEffect var7 : this.j.thePlayer.getActivePotionEffects()) {
            Potion var8 = Potion.potionTypes[var7.getPotionID()];
            GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
            this.j.getTextureManager().bindTexture(inventoryBackground);
            this.drawTexturedModalRect(var1, var2, 0, 166, 140, 32);
            if (var8.hasStatusIcon()) {
               int var9 = var8.getStatusIconIndex();
               this.drawTexturedModalRect(var1 + 6, var2 + 7, 0 + var9 % 8 * 18, 198 + var9 / 8 * 18, 18, 18);
            }

            String var11 = I18n.format(var8.getName());
            if (var7.getAmplifier() == 1) {
               var11 = var11 + " " + I18n.format("enchantment.level.2");
            } else if (var7.getAmplifier() == 2) {
               var11 = var11 + " " + I18n.format("enchantment.level.3");
            } else if (var7.getAmplifier() == 3) {
               var11 = var11 + " " + I18n.format("enchantment.level.4");
            }

            this.q.drawStringWithShadow(var11, var1 + 10 + 18, var2 + 6, 16777215);
            String var10 = Potion.getDurationString(var7);
            this.q.drawStringWithShadow(var10, var1 + 10 + 18, var2 + 6 + 10, 8355711);
            var2 += var5;
         }
      }
   }

   public InventoryEffectRenderer(Container var1) {
      super(var1);
   }
}
