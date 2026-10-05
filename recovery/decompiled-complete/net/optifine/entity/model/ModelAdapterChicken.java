package net.optifine.entity.model;

import io.netty.handler.codec.http.HttpObjectDecoder$HeaderParser;
import io.netty.util.internal.IntegerHolder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelChicken;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.particle.EntitySmokeFX;
import net.minecraft.client.renderer.GlStateManager$TexGenCoord;
import net.minecraft.client.renderer.entity.RenderChicken;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.passive.EntityChicken;
import org.java_websocket.server.SSLParametersWebSocketServerFactory;

public class ModelAdapterChicken extends ModelAdapter {
   public HttpObjectDecoder$HeaderParser field_0001;
   public IntegerHolder field_0004;
   public EntitySmokeFX field_0000;
   public SSLParametersWebSocketServerFactory field_0002;
   public GlStateManager$TexGenCoord field_0003;

   public ModelAdapterChicken() {
      super(EntityChicken.class, "chicken", 0.3F);
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"head", "body", "right_leg", "left_leg", "right_wing", "left_wing", "bill", "chin"};
   }

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelChicken)) {
         return null;
      } else {
         ModelChicken var3 = (ModelChicken)var1;
         return var2.equals("head")
            ? var3.head
            : (
               var2.equals("body")
                  ? var3.body
                  : (
                     var2.equals("right_leg")
                        ? var3.rightLeg
                        : (
                           var2.equals("left_leg")
                              ? var3.leftLeg
                              : (
                                 var2.equals("right_wing")
                                    ? var3.rightWing
                                    : (var2.equals("left_wing") ? var3.leftWing : (var2.equals("bill") ? var3.bill : (var2.equals("chin") ? var3.chin : null)))
                              )
                        )
                  )
            );
      }
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      return new RenderChicken(var3, var1, var2);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelChicken();
   }
}
