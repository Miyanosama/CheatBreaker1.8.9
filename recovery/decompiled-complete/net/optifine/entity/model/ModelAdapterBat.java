package net.optifine.entity.model;

import io.netty.handler.codec.sctp.SctpMessageToMessageDecoder;
import io.netty.handler.ssl.util.FingerprintTrustManagerFactory$2;
import net.minecraft.block.BlockDirectional;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBat;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderBat;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget$Sorter;
import net.minecraft.entity.monster.EntityEndermite;
import net.minecraft.entity.passive.EntityBat;
import net.optifine.reflect.Reflector;
import org.apache.log4j.chainsaw.ControlPanel$7;

public class ModelAdapterBat extends ModelAdapter {
   public EntityEndermite field_0001;
   public FingerprintTrustManagerFactory$2 field_0005;
   public SctpMessageToMessageDecoder field_0000;
   public EntityAINearestAttackableTarget$Sorter field_0003;
   public ControlPanel$7 field_0004;
   public BlockDirectional field_0002;

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"head", "body", "right_wing", "left_wing", "outer_right_wing", "outer_left_wing"};
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      RenderBat var4 = new RenderBat(var3);
      var4.f = var1;
      var4.c = var2;
      return var4;
   }

   @Override
   public ModelBase makeModel() {
      return new ModelBat();
   }

   public ModelAdapterBat() {
      super(EntityBat.class, "bat", 0.25F);
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelBat)) {
         return null;
      } else {
         ModelBat var3 = (ModelBat)var1;
         return var2.equals("head")
            ? (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelBat_ModelRenderers, 0)
            : (
               var2.equals("body")
                  ? (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelBat_ModelRenderers, 1)
                  : (
                     var2.equals("right_wing")
                        ? (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelBat_ModelRenderers, 2)
                        : (
                           var2.equals("left_wing")
                              ? (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelBat_ModelRenderers, 3)
                              : (
                                 var2.equals("outer_right_wing")
                                    ? (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelBat_ModelRenderers, 4)
                                    : (
                                       var2.equals("outer_left_wing")
                                          ? (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelBat_ModelRenderers, 5)
                                          : null
                                    )
                              )
                        )
                  )
            );
      }
   }
}
