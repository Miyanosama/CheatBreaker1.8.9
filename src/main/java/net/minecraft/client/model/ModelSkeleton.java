package net.minecraft.client.model;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntitySkeleton;

public class ModelSkeleton extends ModelZombie {
   public ModelSkeleton(float var1, boolean var2) {
      super(var1, 0.0F, 64, 32);
      if (!var2) {
         this.h = new ModelRenderer(this, 40, 16);
         this.h.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, var1);
         this.h.setRotationPoint(-5.0F, 2.0F, 0.0F);
         this.i = new ModelRenderer(this, 40, 16);
         this.i.mirror = true;
         this.i.addBox(-1.0F, -2.0F, -1.0F, 2, 12, 2, var1);
         this.i.setRotationPoint(5.0F, 2.0F, 0.0F);
         this.j = new ModelRenderer(this, 0, 16);
         this.j.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, var1);
         this.j.setRotationPoint(-2.0F, 12.0F, 0.0F);
         this.k = new ModelRenderer(this, 0, 16);
         this.k.mirror = true;
         this.k.addBox(-1.0F, 0.0F, -1.0F, 2, 12, 2, var1);
         this.k.setRotationPoint(2.0F, 12.0F, 0.0F);
      }
   }

   public ModelSkeleton() {
      this(0.0F, false);
   }

   @Override
   public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {
      super.setRotationAngles(var1, var2, var3, var4, var5, var6, var7);
   }

   @Override
   public void setLivingAnimations(EntityLivingBase var1, float var2, float var3, float var4) {
      this.o = ((EntitySkeleton)var1).getSkeletonType() == 1;
      super.setLivingAnimations(var1, var2, var3, var4);
   }
}
