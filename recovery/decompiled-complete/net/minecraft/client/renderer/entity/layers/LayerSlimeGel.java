package net.minecraft.client.renderer.entity.layers;

import io.netty.channel.epoll.AbstractEpollChannel$AbstractEpollUnsafe;
import io.netty.channel.local.LocalServerChannel$1;
import io.netty.util.concurrent.SingleThreadEventExecutor;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelSlime;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.RenderSlime;
import net.minecraft.entity.monster.EntitySlime;

public class LayerSlimeGel implements LayerRenderer<EntitySlime> {
   public AbstractEpollChannel$AbstractEpollUnsafe field_0002;
   public ModelBase slimeModel = new ModelSlime(0);
   public LocalServerChannel$1 field_0001;
   public SingleThreadEventExecutor field_0003;
   public RenderSlime slimeRenderer;

   public void doRenderLayer(EntitySlime var1, float var2, float var3, float var4, float var5, float var6, float var7, float var8) {
      if (!var1.isInvisible()) {
         GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
         GlStateManager.method_25207();
         GlStateManager.enableBlend();
         GlStateManager.blendFunc(770, 771);
         this.slimeModel.a(this.slimeRenderer.getMainModel());
         this.slimeModel.render(var1, var2, var3, var5, var6, var7, var8);
         GlStateManager.disableBlend();
         GlStateManager.method_25218();
      }
   }

   @Override
   public boolean shouldCombineTextures() {
      return true;
   }

   public LayerSlimeGel(RenderSlime var1) {
      this.slimeRenderer = var1;
   }
}
