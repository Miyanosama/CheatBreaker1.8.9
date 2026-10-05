package net.optifine.entity.model;

import com.cheatbreaker.client.ui.element.type.custom.GlobalSettingsElement;
import net.minecraft.block.BlockMushroom;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelGuardian;
import net.minecraft.client.model.ModelQuadruped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.init.Bootstrap$10;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$Prison;
import net.optifine.config.ParserEnchantmentId;

public abstract class ModelAdapterQuadruped extends ModelAdapter {
   public Bootstrap$10 field_0002;
   public StructureStrongholdPieces$Prison field_0003;
   public ModelGuardian field_0001;
   public BlockMushroom field_0004;
   public GlobalSettingsElement field_0005;
   public ParserEnchantmentId field_0000;

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelQuadruped)) {
         return null;
      } else {
         ModelQuadruped var3 = (ModelQuadruped)var1;
         return var2.equals("head")
            ? var3.a
            : (
               var2.equals("body")
                  ? var3.b
                  : (var2.equals("leg1") ? var3.c : (var2.equals("leg2") ? var3.d : (var2.equals("leg3") ? var3.e : (var2.equals("leg4") ? var3.f : null))))
            );
      }
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"head", "body", "leg1", "leg2", "leg3", "leg4"};
   }

   public ModelAdapterQuadruped(Class var1, String var2, float var3) {
      super(var1, var2, var3);
   }
}
