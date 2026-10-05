package net.minecraft.entity.monster;

import io.netty.channel.DefaultChannelPipeline$4;
import io.netty.handler.codec.http.HttpServerCodec;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureVillagePieces;

public class EntityMagmaCube extends EntitySlime {
   public HttpServerCodec field_0001;
   public StructureVillagePieces field_0002;
   public DefaultChannelPipeline$4 field_0000;

   @Override
   public boolean getCanSpawnHere() {
      return this.o.getDifficulty() != EnumDifficulty.PEACEFUL;
   }

   @Override
   public void handleJumpLava() {
      this.w = 0.22F + this.getSlimeSize() * 0.05F;
      this.ai = true;
   }

   @Override
   public boolean isNotColliding() {
      return this.o.checkNoEntityCollision(this.getEntityBoundingBox(), this)
         && this.o.a(this, this.getEntityBoundingBox()).isEmpty()
         && !this.o.isAnyLiquid(this.getEntityBoundingBox());
   }

   @Override
   public float a_(float var1) {
      return 1.0F;
   }

   @Override
   public EntitySlime createInstance() {
      return new EntityMagmaCube(this.o);
   }

   public EntityMagmaCube(World var1) {
      super(var1);
      this.ab = true;
   }

   @Override
   public boolean makesSoundOnLand() {
      return true;
   }

   @Override
   public void applyEntityAttributes() {
      super.applyEntityAttributes();
      this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.2F);
   }

   @Override
   public void jump() {
      this.w = 0.42F + this.getSlimeSize() * 0.1F;
      this.ai = true;
   }

   @Override
   public int b_(float var1) {
      return 15728880;
   }

   @Override
   public boolean canDamagePlayer() {
      return true;
   }

   @Override
   public void fall(float var1, float var2) {
   }

   @Override
   public void dropFewItems(boolean var1, int var2) {
      Item var3 = this.getDropItem();
      if (var3 != null && this.getSlimeSize() > 1) {
         int var4 = this.V.nextInt(4) - 2;
         if (var2 > 0) {
            var4 += this.V.nextInt(var2 + 1);
         }

         for (int var5 = 0; var5 < var4; var5++) {
            this.dropItem(var3, 1);
         }
      }
   }

   @Override
   public int getAttackStrength() {
      return super.getAttackStrength() + 2;
   }

   @Override
   public boolean isBurning() {
      return false;
   }

   @Override
   public void alterSquishAmount() {
      this.squishAmount *= 0.9F;
   }

   @Override
   public String getJumpSound() {
      return this.getSlimeSize() > 1 ? "mob.magmacube.big" : "mob.magmacube.small";
   }

   @Override
   public int getJumpDelay() {
      return super.getJumpDelay() * 4;
   }

   @Override
   public Item getDropItem() {
      return Items.magma_cream;
   }

   @Override
   public int getTotalArmorValue() {
      return this.getSlimeSize() * 3;
   }

   @Override
   public EnumParticleTypes getParticleType() {
      return EnumParticleTypes.FLAME;
   }
}
