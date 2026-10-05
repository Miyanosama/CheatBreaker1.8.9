package net.minecraft.entity.monster;

import io.netty.channel.AbstractChannelHandlerContext$WriteTask;
import io.netty.channel.DefaultMessageSizeEstimator$1;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.world.biome.BiomeGenHills;
import net.optifine.reflect.FieldLocatorName;
import org.newsclub.net.unix.AFUNIXSocketImpl$1;

public class EntitySlime$SlimeMoveHelper extends EntityMoveHelper {
   public int field_179924_h;
   public FieldLocatorName field_0004;
   public DefaultMessageSizeEstimator$1 field_0006;
   public float field_179922_g;
   public EntitySlime slime;
   public AbstractChannelHandlerContext$WriteTask field_0009;
   public TextureManager field_0000;
   public BiomeGenHills field_0002;
   public boolean field_179923_j;
   public AFUNIXSocketImpl$1 field_0008;

   public void func_179920_a(float var1, boolean var2) {
      this.field_179922_g = var1;
      this.field_179923_j = var2;
   }

   public EntitySlime$SlimeMoveHelper(EntitySlime var1) {
      super(var1);
      this.slime = var1;
   }

   public void setSpeed(double var1) {
      this.e = var1;
      this.f = true;
   }

   @Override
   public void onUpdateMoveHelper() {
      this.entity.y = this.limitAngle(this.entity.y, this.field_179922_g, 30.0F);
      this.entity.aK = this.entity.y;
      this.entity.aI = this.entity.y;
      if (!this.f) {
         this.entity.setMoveForward(0.0F);
      } else {
         this.f = false;
         if (this.entity.C) {
            this.entity.setAIMoveSpeed((float)(this.e * this.entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()));
            if (this.field_179924_h-- <= 0) {
               this.field_179924_h = this.slime.getJumpDelay();
               if (this.field_179923_j) {
                  this.field_179924_h /= 3;
               }

               this.slime.r().setJumping();
               if (this.slime.makesSoundOnJump()) {
                  this.slime
                     .playSound(
                        this.slime.getJumpSound(),
                        this.slime.getSoundVolume(),
                        ((this.slime.getRNG().nextFloat() - this.slime.getRNG().nextFloat()) * 0.2F + 1.0F) * 0.8F
                     );
               }
            } else {
               this.slime.aZ = this.slime.ba = 0.0F;
               this.entity.setAIMoveSpeed(0.0F);
            }
         } else {
            this.entity.setAIMoveSpeed((float)(this.e * this.entity.getEntityAttribute(SharedMonsterAttributes.movementSpeed).getAttributeValue()));
         }
      }
   }
}
