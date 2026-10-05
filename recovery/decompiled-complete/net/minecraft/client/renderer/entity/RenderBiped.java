package net.minecraft.client.renderer.entity;

import io.netty.buffer.UnpooledHeapByteBuf;
import io.netty.handler.codec.spdy.SpdySessionHandler$1;
import net.minecraft.block.BlockOre;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerCustomHead;
import net.minecraft.client.renderer.entity.layers.LayerHeldItem;
import net.minecraft.entity.EntityLiving;
import net.minecraft.util.ResourceLocation;

public class RenderBiped<T extends EntityLiving> extends RenderLiving<T> {
   public static ResourceLocation DEFAULT_RES_LOC = new ResourceLocation("textures/entity/steve.png");
   public UnpooledHeapByteBuf field_0004;
   public SpdySessionHandler$1 field_0005;
   public float field_77070_b;
   public BlockOre field_0002;
   public ModelBiped a;

   public ResourceLocation getEntityTexture(T var1) {
      return DEFAULT_RES_LOC;
   }

   public RenderBiped(RenderManager var1, ModelBiped var2, float var3) {
      this(var1, var2, var3, 1.0F);
      this.a(new LayerHeldItem(this));
   }

   @Override
   public void y_() {
      GlStateManager.translate(0.0F, 0.1875F, 0.0F);
   }

   public RenderBiped(RenderManager var1, ModelBiped var2, float var3, float var4) {
      super(var1, var2, var3);
      this.a = var2;
      this.field_77070_b = var4;
      this.a(new LayerCustomHead(var2.e));
   }
}
