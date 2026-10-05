package net.minecraft.client.model;

import io.netty.util.DefaultAttributeMap$DefaultAttribute;
import net.minecraft.client.renderer.entity.RenderCreeper;
import net.minecraft.entity.ai.EntityAITasks$EntityAITaskEntry;
import org.apache.log4j.rewrite.RewriteAppender;

public class ModelCow extends ModelQuadruped {
   public DefaultAttributeMap$DefaultAttribute field_0001;
   public EntityAITasks$EntityAITaskEntry field_0003;
   public RenderCreeper field_0000;
   public RewriteAppender field_0002;

   public ModelCow() {
      super(12, 0.0F);
      this.a = new ModelRenderer(this, 0, 0);
      this.a.addBox(-4.0F, -4.0F, -6.0F, 8, 8, 6, 0.0F);
      this.a.setRotationPoint(0.0F, 4.0F, -8.0F);
      this.a.setTextureOffset(22, 0).addBox(-5.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
      this.a.setTextureOffset(22, 0).addBox(4.0F, -5.0F, -4.0F, 1, 3, 1, 0.0F);
      this.b = new ModelRenderer(this, 18, 4);
      this.b.addBox(-6.0F, -10.0F, -7.0F, 12, 18, 10, 0.0F);
      this.b.setRotationPoint(0.0F, 5.0F, 2.0F);
      this.b.setTextureOffset(52, 0).addBox(-2.0F, 2.0F, -8.0F, 4, 6, 1);
      this.c.rotationPointX--;
      this.d.rotationPointX++;
      this.c.rotationPointZ += 0.0F;
      this.d.rotationPointZ += 0.0F;
      this.e.rotationPointX--;
      this.f.rotationPointX++;
      this.e.rotationPointZ--;
      this.f.rotationPointZ--;
      this.h += 2.0F;
   }
}
