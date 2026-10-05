package net.minecraft.client.renderer.entity.layers;

import io.netty.channel.oio.AbstractOioChannel$DefaultOioUnsafe;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RendererLivingEntity;
import net.minecraft.world.gen.structure.StructureVillagePieces$Hall;

public class LayerBipedArmor extends LayerArmorBase<ModelBiped> {
   public AbstractOioChannel$DefaultOioUnsafe field_0001;
   public StructureVillagePieces$Hall field_0000;

   @Override
   public void initArmor() {
      this.c = new ModelBiped(0.5F);
      this.d = new ModelBiped(1.0F);
   }

   public LayerBipedArmor(RendererLivingEntity<?> var1) {
      super(var1);
   }

   public void setModelVisible(ModelBiped var1) {
      var1.setInvisible(false);
   }

   public void setModelPartVisible(ModelBiped var1, int var2) {
      this.setModelVisible(var1);
      switch (var2) {
         case 1:
            var1.j.showModel = true;
            var1.k.showModel = true;
            break;
         case 2:
            var1.g.showModel = true;
            var1.j.showModel = true;
            var1.k.showModel = true;
            break;
         case 3:
            var1.g.showModel = true;
            var1.h.showModel = true;
            var1.i.showModel = true;
            break;
         case 4:
            var1.e.showModel = true;
            var1.f.showModel = true;
      }
   }
}
