package net.minecraft.entity.ai;

import com.cheatbreaker.client.ui.mainmenu.BuildRestrictionsMenu;
import net.minecraft.block.BlockDoublePlant$EnumBlockHalf;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.MathHelper;
import recovered.unidentified.UnidentifiedClass0373;

public class EntityMoveHelper {
   public double posZ;
   public double e;
   public BuildRestrictionsMenu field_0003;
   public boolean f;
   public BlockDoublePlant$EnumBlockHalf field_0000;
   public BlockTallGrass field_0001;
   public double posX;
   public UnidentifiedClass0373 field_0005;
   public EntityLiving entity;
   public double posY;

   public double getZ() {
      return this.posZ;
   }

   public void onUpdateMoveHelper() {
      this.entity.setMoveForward(0.0F);
      if (this.f) {
         this.f = false;
         int var1 = MathHelper.floor_double(this.entity.getEntityBoundingBox().b + 0.5);
         double var2 = this.posX - this.entity.s;
         double var4 = this.posZ - this.entity.u;
         double var6 = this.posY - var1;
         double var8 = var2 * var2 + var6 * var6 + var4 * var4;
         if (var8 >= 2.5000003E-7F) {
            float var10 = (float)(MathHelper.atan2(var4, var2) * 180.0 / Math.PI) - 90.0F;
            this.entity.y = this.limitAngle(this.entity.y, var10, 30.0F);
            this.entity.setAIMoveSpeed((float)(this.e * this.entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()));
            if (var6 > 0.0 && var2 * var2 + var4 * var4 < 1.0) {
               this.entity.r().setJumping();
            }
         }
      }
   }

   public boolean isUpdating() {
      return this.f;
   }

   public double getSpeed() {
      return this.e;
   }

   public EntityMoveHelper(EntityLiving var1) {
      this.entity = var1;
      this.posX = var1.s;
      this.posY = var1.t;
      this.posZ = var1.u;
   }

   public void setMoveTo(double var1, double var3, double var5, double var7) {
      this.posX = var1;
      this.posY = var3;
      this.posZ = var5;
      this.e = var7;
      this.f = true;
   }

   public double getY() {
      return this.posY;
   }

   public float limitAngle(float var1, float var2, float var3) {
      float var4 = MathHelper.wrapAngleTo180_float(var2 - var1);
      if (var4 > var3) {
         var4 = var3;
      }

      if (var4 < -var3) {
         var4 = -var3;
      }

      float var5 = var1 + var4;
      if (var5 < 0.0F) {
         var5 += 360.0F;
      } else if (var5 > 360.0F) {
         var5 -= 360.0F;
      }

      return var5;
   }

   public double getX() {
      return this.posX;
   }
}
