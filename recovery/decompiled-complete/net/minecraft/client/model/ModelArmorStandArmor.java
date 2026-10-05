package net.minecraft.client.model;

import io.netty.channel.rxtx.RxtxChannelOption;
import net.minecraft.block.BlockQuartz$EnumType;
import net.minecraft.client.renderer.StitcherException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityArmorStand;
import net.minecraft.world.chunk.EmptyChunk;

public class ModelArmorStandArmor extends ModelBiped {
   public BlockQuartz$EnumType field_0000;
   public EmptyChunk field_0003;
   public RxtxChannelOption field_0002;
   public StitcherException field_0001;

   public ModelArmorStandArmor() {
      this(0.0F);
   }

   public ModelArmorStandArmor(float var1) {
      this(var1, 64, 32);
   }

   public ModelArmorStandArmor(float var1, int var2, int var3) {
      super(var1, 0.0F, var2, var3);
   }

   @Override
   public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {
      if (var7 instanceof EntityArmorStand) {
         EntityArmorStand var8 = (EntityArmorStand)var7;
         this.e.rotateAngleX = (float) (Math.PI / 180.0) * var8.getHeadRotation().getX();
         this.e.rotateAngleY = (float) (Math.PI / 180.0) * var8.getHeadRotation().getY();
         this.e.rotateAngleZ = (float) (Math.PI / 180.0) * var8.getHeadRotation().getZ();
         this.e.setRotationPoint(0.0F, 1.0F, 0.0F);
         this.g.rotateAngleX = (float) (Math.PI / 180.0) * var8.getBodyRotation().getX();
         this.g.rotateAngleY = (float) (Math.PI / 180.0) * var8.getBodyRotation().getY();
         this.g.rotateAngleZ = (float) (Math.PI / 180.0) * var8.getBodyRotation().getZ();
         this.i.rotateAngleX = (float) (Math.PI / 180.0) * var8.getLeftArmRotation().getX();
         this.i.rotateAngleY = (float) (Math.PI / 180.0) * var8.getLeftArmRotation().getY();
         this.i.rotateAngleZ = (float) (Math.PI / 180.0) * var8.getLeftArmRotation().getZ();
         this.h.rotateAngleX = (float) (Math.PI / 180.0) * var8.getRightArmRotation().getX();
         this.h.rotateAngleY = (float) (Math.PI / 180.0) * var8.getRightArmRotation().getY();
         this.h.rotateAngleZ = (float) (Math.PI / 180.0) * var8.getRightArmRotation().getZ();
         this.k.rotateAngleX = (float) (Math.PI / 180.0) * var8.getLeftLegRotation().getX();
         this.k.rotateAngleY = (float) (Math.PI / 180.0) * var8.getLeftLegRotation().getY();
         this.k.rotateAngleZ = (float) (Math.PI / 180.0) * var8.getLeftLegRotation().getZ();
         this.k.setRotationPoint(1.9F, 11.0F, 0.0F);
         this.j.rotateAngleX = (float) (Math.PI / 180.0) * var8.getRightLegRotation().getX();
         this.j.rotateAngleY = (float) (Math.PI / 180.0) * var8.getRightLegRotation().getY();
         this.j.rotateAngleZ = (float) (Math.PI / 180.0) * var8.getRightLegRotation().getZ();
         this.j.setRotationPoint(-1.9F, 11.0F, 0.0F);
         copyModelAngles(this.e, this.f);
      }
   }
}
