package net.optifine.entity.model;

import io.netty.channel.epoll.EpollSocketChannelConfig;
import io.netty.handler.codec.spdy.SpdySession$StreamState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.ModelSquid;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSquid;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.src.Config;
import net.optifine.reflect.Reflector;

public class ModelAdapterSquid extends ModelAdapter {
   public EpollSocketChannelConfig field_0000;
   public SpdySession$StreamState field_0001;

   @Override
   public ModelRenderer getModelRenderer(ModelBase var1, String var2) {
      if (!(var1 instanceof ModelSquid)) {
         return null;
      } else {
         ModelSquid var3 = (ModelSquid)var1;
         if (var2.equals("body")) {
            return (ModelRenderer)Reflector.getFieldValue(var3, Reflector.ModelSquid_body);
         } else {
            String var4 = "tentacle";
            if (var2.startsWith(var4)) {
               ModelRenderer[] var5 = (ModelRenderer[])Reflector.getFieldValue(var3, Reflector.ModelSquid_tentacles);
               if (var5 == null) {
                  return null;
               } else {
                  String var6 = var2.substring(var4.length());
                  int var7 = Config.parseInt(var6, -1);
                  var7--;
                  return var7 >= 0 && var7 < var5.length ? var5[var7] : null;
               }
            } else {
               return null;
            }
         }
      }
   }

   @Override
   public String[] getModelRendererNames() {
      return new String[]{"body", "tentacle1", "tentacle2", "tentacle3", "tentacle4", "tentacle5", "tentacle6", "tentacle7", "tentacle8"};
   }

   @Override
   public IEntityRenderer makeEntityRender(ModelBase var1, float var2) {
      RenderManager var3 = Minecraft.getMinecraft().getRenderManager();
      return new RenderSquid(var3, var1, var2);
   }

   public ModelAdapterSquid() {
      super(EntitySquid.class, "squid", 0.7F);
   }

   @Override
   public ModelBase makeModel() {
      return new ModelSquid();
   }
}
