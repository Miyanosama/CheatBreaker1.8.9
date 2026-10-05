package net.optifine.entity.model;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSilverfish;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSilverfish;
import net.minecraft.entity.monster.EntitySilverfish;
import net.minecraft.src.Config;
import net.minecraft.world.gen.layer.GenLayerAddMushroomIsland;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Entrance;
import net.optifine.reflect.Reflector;
import recovered.unidentified.UnidentifiedClass4511;

public class ModelAdapterSilverfish extends ModelAdapter {
   public GenLayerAddMushroomIsland field_0001;
   public UnidentifiedClass4511 field_0002;
   public StructureNetherBridgePieces$Entrance field_0000;

   @Override
   public ModelBase makeModel() {
      return new ModelSilverfish();
   }

   public ModelAdapterSilverfish() {
      super(EntitySilverfish.class, "silverfish", 0.3F);
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      RenderSilverfish var4 = new RenderSilverfish(var3);
      var4.f = var1;
      var4.c = var2;
      return var4;
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"body1", "body2", "body3", "body4", "body5", "body6", "body7", "wing1", "wing2", "wing3"};
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelSilverfish)) {
         return null;
      } else {
         ModelSilverfish var3 = (ModelSilverfish)var1;
         String var4 = "body";
         if (var2.startsWith(var4)) {
            ModelRenderer[] var9 = (ModelRenderer[])Reflector.getFieldValue(var3, Reflector.ModelSilverfish_bodyParts);
            if (var9 == null) {
               return null;
            } else {
               String var10 = var2.substring(var4.length());
               int var11 = Config.parseInt(var10, -1);
               var11--;
               return var11 >= 0 && var11 < var9.length ? var9[var11] : null;
            }
         } else {
            String var5 = "wing";
            if (var2.startsWith(var5)) {
               ModelRenderer[] var6 = (ModelRenderer[])Reflector.getFieldValue(var3, Reflector.ModelSilverfish_wingParts);
               if (var6 == null) {
                  return null;
               } else {
                  String var7 = var2.substring(var5.length());
                  int var8 = Config.parseInt(var7, -1);
                  var8--;
                  return var8 >= 0 && var8 < var6.length ? var6[var8] : null;
               }
            } else {
               return null;
            }
         }
      }
   }
}
