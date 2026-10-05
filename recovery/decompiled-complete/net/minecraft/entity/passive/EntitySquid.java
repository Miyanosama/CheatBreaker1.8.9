package net.minecraft.entity.passive;

import javax.vecmath.Vector4f;
import net.minecraft.block.material.Material;
import net.minecraft.client.stream.BroadcastController;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.init.Items;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.optifine.util.IteratorCache$IteratorReadOnly;
import org.apache.log4j.jdbc.JDBCAppender;
import org.slf4j.helpers.BasicMDCAdapter$1;

public class EntitySquid extends EntityWaterMob {
   public BroadcastController field_0011;
   public float field_0018;
   public Material field_0010;
   public float tentacleAngle;
   public float randomMotionVecY;
   public float prevSquidPitch;
   public IteratorCache$IteratorReadOnly field_0019;
   public float rotationVelocity;
   public float squidYaw;
   public BasicMDCAdapter$1 field_0020;
   public float field_0001;
   public float randomMotionVecX;
   public float squidPitch;
   public JDBCAppender field_0007;
   public float randomMotionVecZ;
   public EntityMob field_0017;
   public float squidRotation;
   public float field_0006;
   public float prevSquidYaw;
   public Vector4f field_0009;
   public float lastTentacleAngle;

   @Override
   public Item getDropItem() {
      return null;
   }

   @Override
   public boolean getCanSpawnHere() {
      return this.t > 45.0 && this.t < this.o.F() && super.getCanSpawnHere();
   }

   @Override
   public boolean l_() {
      return false;
   }

   public void func_175568_b(float var1, float var2, float var3) {
      this.randomMotionVecX = var1;
      this.randomMotionVecY = var2;
      this.randomMotionVecZ = var3;
   }

   @Override
   public void dropFewItems(boolean var1, int var2) {
      int var3 = this.V.nextInt(3 + var2) + 1;

      for (int var4 = 0; var4 < var3; var4++) {
         this.a(new ItemStack(Items.dye, 1, EnumDyeColor.BLACK.getDyeDamage()), 0.0F);
      }
   }

   @Override
   public void moveEntityWithHeading(float var1, float var2) {
      this.d(this.v, this.w, this.x);
   }

   @Override
   public String getLivingSound() {
      return null;
   }

   @Override
   public String getHurtSound() {
      return null;
   }

   public EntitySquid(World var1) {
      super(var1);
      this.setSize(0.95F, 0.95F);
      this.V.setSeed(1 + this.F());
      this.rotationVelocity = 1.0F / (this.V.nextFloat() + 1.0F) * 0.2F;
      this.i.addTask(0, new EntitySquid$AIMoveRandom(this));
   }

   @Override
   public float getEyeHeight() {
      return this.K * 0.5F;
   }

   @Override
   public void onLivingUpdate() {
      super.onLivingUpdate();
      this.prevSquidPitch = this.squidPitch;
      this.prevSquidYaw = this.squidYaw;
      this.field_0001 = this.squidRotation;
      this.lastTentacleAngle = this.tentacleAngle;
      this.squidRotation = this.squidRotation + this.rotationVelocity;
      if (this.squidRotation > Math.PI * 2) {
         if (this.o.D) {
            this.squidRotation = (float) (Math.PI * 2);
         } else {
            this.squidRotation = (float)(this.squidRotation - (Math.PI * 2));
            if (this.V.nextInt(10) == 0) {
               this.rotationVelocity = 1.0F / (this.V.nextFloat() + 1.0F) * 0.2F;
            }

            this.o.setEntityState(this, (byte)19);
         }
      }

      if (this.Y) {
         if (this.squidRotation < (float) Math.PI) {
            float var1 = this.squidRotation / (float) Math.PI;
            this.tentacleAngle = MathHelper.sin(var1 * var1 * (float) Math.PI) * (float) Math.PI * 0.25F;
            if (var1 > 0.75) {
               this.field_0006 = 1.0F;
               this.field_0018 = 1.0F;
            } else {
               this.field_0018 *= 0.8F;
            }
         } else {
            this.tentacleAngle = 0.0F;
            this.field_0006 *= 0.9F;
            this.field_0018 *= 0.99F;
         }

         if (!this.o.D) {
            this.v = this.randomMotionVecX * this.field_0006;
            this.w = this.randomMotionVecY * this.field_0006;
            this.x = this.randomMotionVecZ * this.field_0006;
         }

         float var2 = MathHelper.sqrt_double(this.v * this.v + this.x * this.x);
         this.aI = this.aI + (-((float)MathHelper.atan2(this.v, this.x)) * 180.0F / (float) Math.PI - this.aI) * 0.1F;
         this.y = this.aI;
         this.squidYaw = (float)(this.squidYaw + Math.PI * this.field_0018 * 1.5);
         this.squidPitch = this.squidPitch + (-((float)MathHelper.atan2(var2, this.w)) * 180.0F / (float) Math.PI - this.squidPitch) * 0.1F;
      } else {
         this.tentacleAngle = MathHelper.abs(MathHelper.sin(this.squidRotation)) * (float) Math.PI * 0.25F;
         if (!this.o.D) {
            this.v = 0.0;
            this.w -= 0.08;
            this.w *= 0.98F;
            this.x = 0.0;
         }

         this.squidPitch = (float)(this.squidPitch + (-90.0F - this.squidPitch) * 0.02);
      }
   }

   @Override
   public void handleStatusUpdate(byte var1) {
      if (var1 == 19) {
         this.squidRotation = 0.0F;
      } else {
         super.handleStatusUpdate(var1);
      }
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0);
   }

   @Override
   public float getSoundVolume() {
      return 0.4F;
   }

   @Override
   public String getDeathSound() {
      return null;
   }

   @Override
   public boolean V() {
      return this.o.handleMaterialAcceleration(this.getEntityBoundingBox().expand(0.0, -0.6F, 0.0), Material.water, this);
   }

   public boolean func_175567_n() {
      return this.randomMotionVecX != 0.0F || this.randomMotionVecY != 0.0F || this.randomMotionVecZ != 0.0F;
   }
}
