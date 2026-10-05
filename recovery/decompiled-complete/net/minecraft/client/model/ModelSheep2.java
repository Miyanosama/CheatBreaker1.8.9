package net.minecraft.client.model;

import io.netty.buffer.ByteBufUtil$ThreadLocalDirectByteBuf$1;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityRabbit$AIPanic;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.network.NetHandlerPlayServer$3;

public class ModelSheep2 extends ModelQuadruped {
   public ByteBufUtil$ThreadLocalDirectByteBuf$1 field_0001;
   public float headRotationAngleX;
   public NetHandlerPlayServer$3 field_0000;
   public EntityRabbit$AIPanic field_0002;

   public ModelSheep2() {
      super(12, 0.0F);
      this.a = new ModelRenderer(this, 0, 0);
      this.a.addBox(-3.0F, -4.0F, -6.0F, 6, 6, 8, 0.0F);
      this.a.setRotationPoint(0.0F, 6.0F, -8.0F);
      this.b = new ModelRenderer(this, 28, 8);
      this.b.addBox(-4.0F, -10.0F, -7.0F, 8, 16, 6, 0.0F);
      this.b.setRotationPoint(0.0F, 5.0F, 2.0F);
   }

   @Override
   public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {
      super.setRotationAngles(var1, var2, var3, var4, var5, var6, var7);
      this.a.rotateAngleX = this.headRotationAngleX;
   }

   @Override
   public void setLivingAnimations(EntityLivingBase var1, float var2, float var3, float var4) {
      super.setLivingAnimations(var1, var2, var3, var4);
      this.a.rotationPointY = 6.0F + ((EntitySheep)var1).getHeadRotationPointY(var4) * 9.0F;
      this.headRotationAngleX = ((EntitySheep)var1).getHeadRotationAngleX(var4);
   }
}
