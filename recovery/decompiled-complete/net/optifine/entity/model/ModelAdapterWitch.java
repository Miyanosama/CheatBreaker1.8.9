package net.optifine.entity.model;

import net.minecraft.block.BlockPrismarine;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelWitch;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderWitch;
import net.minecraft.entity.monster.EntityWitch;
import net.minecraft.potion.PotionAbsorption;
import net.minecraft.world.biome.BiomeGenStoneBeach;
import net.optifine.reflect.Reflector;
import org.apache.log4j.nt.NTEventLogAppender;
import recovered.unidentified.UnidentifiedClass3460;

public class ModelAdapterWitch extends ModelAdapter {
   public NTEventLogAppender field_0001;
   public PotionAbsorption field_0004;
   public UnidentifiedClass3460 field_0000;
   public BlockPrismarine field_0002;
   public BiomeGenStoneBeach field_0003;

   @Override
   public ModelBase makeModel() {
      return new ModelWitch(0.0F);
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelWitch)) {
         return null;
      } else {
         ModelWitch var3 = (ModelWitch)var1;
         return var2.equals("mole")
            ? (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelWitch_mole)
            : (
               var2.equals("hat")
                  ? (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelWitch_hat)
                  : (
                     var2.equals("head")
                        ? var3.villagerHead
                        : (
                           var2.equals("body")
                              ? var3.villagerBody
                              : (
                                 var2.equals("arms")
                                    ? var3.villagerArms
                                    : (
                                       var2.equals("left_leg")
                                          ? var3.leftVillagerLeg
                                          : (var2.equals("right_leg") ? var3.rightVillagerLeg : (var2.equals("nose") ? var3.villagerNose : null))
                                    )
                              )
                        )
                  )
            );
      }
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"mole", "head", "body", "arms", "right_leg", "left_leg", "nose"};
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      RenderWitch var4 = new RenderWitch(var3);
      var4.f = var1;
      var4.c = var2;
      return var4;
   }

   public ModelAdapterWitch() {
      super(EntityWitch.class, "witch", 0.5F);
   }
}
