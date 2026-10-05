package net.optifine.entity.model;

import io.netty.channel.FixedRecvByteBufAllocator$HandleImpl;
import io.netty.handler.traffic.TrafficCounter;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$MapReduceMappingsToLongTask;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.multiplayer.ThreadLanServerPing;
import net.minecraft.client.renderer.GlStateManager$PolygonOffsetState;

public abstract class ModelAdapterBiped extends ModelAdapter {
   public FixedRecvByteBufAllocator$HandleImpl field_0000;
   public GlStateManager$PolygonOffsetState field_0002;
   public TrafficCounter field_0003;
   public ThreadLanServerPing field_0001;
   public ConcurrentHashMapV8$MapReduceMappingsToLongTask field_0004;

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelBiped)) {
         return null;
      } else {
         ModelBiped var3 = (ModelBiped)var1;
         return var2.equals("head")
            ? var3.e
            : (
               var2.equals("headwear")
                  ? var3.f
                  : (
                     var2.equals("body")
                        ? var3.g
                        : (
                           var2.equals("left_arm")
                              ? var3.i
                              : (var2.equals("right_arm") ? var3.h : (var2.equals("left_leg") ? var3.k : (var2.equals("right_leg") ? var3.j : null)))
                        )
                  )
            );
      }
   }

   public ModelAdapterBiped(Class var1, String var2, float var3) {
      super(var1, var2, var3);
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"head", "headwear", "body", "left_arm", "right_arm", "left_leg", "right_leg"};
   }
}
