package recovered.unidentified;

import io.netty.channel.ChannelInitializer;
import io.netty.util.internal.chmv8.ConcurrentHashMapV8$SearchKeysTask;
import javax.vecmath.Matrix3d;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureComponent;

public class UnidentifiedClass3843 extends EntityAIBase {
   public ChannelInitializer field_0003;
   public EntityLiving field_0006;
   public Matrix3d field_0002;
   public ConcurrentHashMapV8$SearchKeysTask field_0005;
   public StructureComponent field_0000;
   public EntityLivingBase field_0001;
   public World field_0007;
   public int field_0004;

   public UnidentifiedClass3843(EntityLiving var1) {
      this.field_0006 = var1;
      this.field_0007 = var1.o;
      this.setMutexBits(3);
   }

   @Override
   public boolean shouldExecute() {
      EntityLivingBase var1 = this.field_0006.getAttackTarget();
      if (var1 == null) {
         return false;
      } else {
         this.field_0001 = var1;
         return true;
      }
   }

   @Override
   public void updateTask() {
      this.field_0006.getLookHelper().setLookPositionWithEntity(this.field_0001, 30.0F, 30.0F);
      double var1 = this.field_0006.J * 2.0F * this.field_0006.J * 2.0F;
      double var3 = this.field_0006.e(this.field_0001.s, this.field_0001.getEntityBoundingBox().b, this.field_0001.u);
      double var5 = 0.8;
      if (var3 > var1 && var3 < 16.0) {
         var5 = 1.33;
      } else if (var3 < 225.0) {
         var5 = 0.6;
      }

      this.field_0006.s().tryMoveToEntityLiving(this.field_0001, var5);
      this.field_0004 = Math.max(this.field_0004 - 1, 0);
      if (var3 <= var1 && this.field_0004 <= 0) {
         this.field_0004 = 20;
         this.field_0006.attackEntityAsMob(this.field_0001);
      }
   }

   @Override
   public void resetTask() {
      this.field_0001 = null;
      this.field_0006.s().clearPathEntity();
   }

   @Override
   public boolean continueExecuting() {
      return !this.field_0001.isEntityAlive()
         ? false
         : (this.field_0006.h(this.field_0001) > 225.0 ? false : !this.field_0006.s().noPath() || this.shouldExecute());
   }
}
