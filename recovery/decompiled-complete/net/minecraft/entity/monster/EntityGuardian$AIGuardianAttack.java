package net.minecraft.entity.monster;

import javax.vecmath.Tuple2d;
import net.minecraft.client.stream.MetadataAchievement;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.network.play.server.S11PacketSpawnExperienceOrb;
import net.minecraft.util.DamageSource;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.biome.BiomeGenSnow;
import net.minecraft.world.storage.WorldInfo$4;
import net.optifine.entity.model.ModelAdapterChest;
import net.optifine.reflect.ReflectorMethod;
import org.json.JSONWriter;

public class EntityGuardian$AIGuardianAttack extends EntityAIBase {
   public WorldInfo$4 field_0004;
   public BiomeGenSnow field_0007;
   public JSONWriter field_0003;
   public Tuple2d field_0006;
   public EntityGuardian theEntity;
   public int tickCounter;
   public ModelAdapterChest field_0008;
   public S11PacketSpawnExperienceOrb field_0005;
   public MetadataAchievement field_0002;
   public ReflectorMethod field_0009;

   public EntityGuardian$AIGuardianAttack(EntityGuardian var1) {
      this.theEntity = var1;
      this.setMutexBits(3);
   }

   @Override
   public void updateTask() {
      EntityLivingBase var1 = this.theEntity.getAttackTarget();
      this.theEntity.s().clearPathEntity();
      this.theEntity.getLookHelper().setLookPositionWithEntity(var1, 90.0F, 90.0F);
      if (!this.theEntity.t(var1)) {
         this.theEntity.setAttackTarget((EntityLivingBase)null);
      } else {
         this.tickCounter++;
         if (this.tickCounter == 0) {
            EntityGuardian.access$000(this.theEntity, this.theEntity.getAttackTarget().F());
            this.theEntity.o.setEntityState(this.theEntity, (byte)21);
         } else if (this.tickCounter >= this.theEntity.func_175464_ck()) {
            float var2 = 1.0F;
            if (this.theEntity.o.getDifficulty() == EnumDifficulty.HARD) {
               var2 += 2.0F;
            }

            if (this.theEntity.isElder()) {
               var2 += 2.0F;
            }

            var1.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this.theEntity, this.theEntity), var2);
            var1.attackEntityFrom(
               DamageSource.causeMobDamage(this.theEntity), (float)this.theEntity.getEntityAttribute(SharedMonsterAttributes.attackDamage).getAttributeValue()
            );
            this.theEntity.setAttackTarget((EntityLivingBase)null);
         } else if (this.tickCounter >= 60 && this.tickCounter % 20 == 0) {
         }

         super.updateTask();
      }
   }

   @Override
   public boolean continueExecuting() {
      return super.continueExecuting() && (this.theEntity.isElder() || this.theEntity.h(this.theEntity.getAttackTarget()) > 9.0);
   }

   @Override
   public boolean shouldExecute() {
      EntityLivingBase var1 = this.theEntity.getAttackTarget();
      return var1 != null && var1.isEntityAlive();
   }

   @Override
   public void resetTask() {
      EntityGuardian.access$000(this.theEntity, 0);
      this.theEntity.setAttackTarget((EntityLivingBase)null);
      EntityGuardian.access$100(this.theEntity).makeUpdate();
   }

   @Override
   public void startExecuting() {
      this.tickCounter = -10;
      this.theEntity.s().clearPathEntity();
      this.theEntity.getLookHelper().setLookPositionWithEntity(this.theEntity.getAttackTarget(), 90.0F, 90.0F);
      this.theEntity.ai = true;
   }
}
