package net.optifine.entity.model;

import junit.awtui.TestRunner$1;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelEnderCrystal;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.RenderEnderCrystal;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.item.ItemBow;
import net.minecraft.src.Config;
import net.minecraft.world.GameRules;
import net.optifine.reflect.Reflector;
import recovered.unidentified.UnidentifiedClass1945;

public class ModelAdapterEnderCrystal extends ModelAdapter {
   public TestRunner$1 field_0001;
   public ItemBow field_0004;
   public GameRules field_0000;
   public EntityFallingBlock field_0002;
   public UnidentifiedClass1945 field_0003;

   public ModelAdapterEnderCrystal() {
      this("end_crystal");
   }

   public ModelAdapterEnderCrystal(String var1) {
      super(EntityEnderCrystal.class, var1, 0.5F);
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      Render var4 = var3.getEntityRenderMap().get(EntityEnderCrystal.class);
      if (!(var4 instanceof RenderEnderCrystal)) {
         Config.warn("Not an instance of RenderEnderCrystal: " + var4);
         return null;
      } else {
         RenderEnderCrystal var5 = (RenderEnderCrystal)var4;
         if (!Reflector.RenderEnderCrystal_modelEnderCrystal.exists()) {
            Config.warn("Field not found: RenderEnderCrystal.modelEnderCrystal");
            return null;
         } else {
            Reflector.setFieldValue(var5, Reflector.RenderEnderCrystal_modelEnderCrystal, var1);
            var5.c = var2;
            return var5;
         }
      }
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelEnderCrystal)) {
         return null;
      } else {
         ModelEnderCrystal var3 = (ModelEnderCrystal)var1;
         return var2.equals("cube")
            ? (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelEnderCrystal_ModelRenderers, 0)
            : (
               var2.equals("glass")
                  ? (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelEnderCrystal_ModelRenderers, 1)
                  : (var2.equals("base") ? (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelEnderCrystal_ModelRenderers, 2) : null)
            );
      }
   }

   @Override
   public ModelBase makeModel() {
      return new ModelEnderCrystal(0.0F, true);
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"cube", "glass", "base"};
   }
}
