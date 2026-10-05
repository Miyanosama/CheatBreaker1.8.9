package net.minecraft.client.renderer.entity;

import net.minecraft.client.model.ModelZombie;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.optifine.BetterSnow;
import recovered.unidentified.UnidentifiedClass1812;

public class RenderPigZombie$1 extends LayerBipedArmor {
   public BetterSnow field_0001;
   public UnidentifiedClass1812 field_0000;

   public RenderPigZombie$1(RenderPigZombie var1, RendererLivingEntity var2) {
      this.field_177198_a = var1;
      super(var2);
   }

   @Override
   public void initArmor() {
      this.c = new ModelZombie(0.5F, true);
      this.d = new ModelZombie(1.0F, true);
   }
}
