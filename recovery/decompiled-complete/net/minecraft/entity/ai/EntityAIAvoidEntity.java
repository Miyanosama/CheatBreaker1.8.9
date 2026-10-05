package net.minecraft.entity.ai;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import io.netty.handler.codec.http.HttpHeaderDateFormat$1;
import java.util.List;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.Vec3;
import net.minecraft.world.chunk.storage.ChunkLoader;

public class EntityAIAvoidEntity<T extends Entity> extends EntityAIBase {
   public float avoidDistance;
   public Predicate<? super T> avoidTargetSelector;
   public RenderLiving field_0004;
   public HttpHeaderDateFormat$1 field_0009;
   public Predicate<Entity> canBeSeenSelector = new EntityAIAvoidEntity$1(this);
   public double farSpeed;
   public double nearSpeed;
   public PathNavigate entityPathNavigate;
   public PathEntity entityPathEntity;
   public Class<T> classToAvoid;
   public ChunkLoader field_0000;
   public T closestLivingEntity;
   public EntityCreature theEntity;

   @Override
   public void startExecuting() {
      this.entityPathNavigate.setPath(this.entityPathEntity, this.farSpeed);
   }

   public EntityAIAvoidEntity(EntityCreature var1, Class<T> var2, float var3, double var4, double var6) {
      this(var1, var2, Predicates.alwaysTrue(), var3, var4, var6);
   }

   public EntityAIAvoidEntity(EntityCreature var1, Class<T> var2, Predicate<? super T> var3, float var4, double var5, double var7) {
      this.theEntity = var1;
      this.classToAvoid = var2;
      this.avoidTargetSelector = var3;
      this.avoidDistance = var4;
      this.farSpeed = var5;
      this.nearSpeed = var7;
      this.entityPathNavigate = var1.s();
      this.setMutexBits(1);
   }

   @Override
   public void updateTask() {
      if (this.theEntity.h(this.closestLivingEntity) < 49.0) {
         this.theEntity.s().setSpeed(this.nearSpeed);
      } else {
         this.theEntity.s().setSpeed(this.farSpeed);
      }
   }

   @Override
   public boolean shouldExecute() {
      List var1 = this.theEntity
         .o
         .getEntitiesWithinAABB(
            this.classToAvoid,
            this.theEntity.getEntityBoundingBox().expand(this.avoidDistance, 3.0, this.avoidDistance),
            Predicates.and(new Predicate[]{EntitySelectors.NOT_SPECTATING, this.canBeSeenSelector, this.avoidTargetSelector})
         );
      if (var1.isEmpty()) {
         return false;
      } else {
         this.closestLivingEntity = (T)var1.get(0);
         Vec3 var2 = RandomPositionGenerator.findRandomTargetBlockAwayFrom(
            this.theEntity, 16, 7, new Vec3(this.closestLivingEntity.s, this.closestLivingEntity.t, this.closestLivingEntity.u)
         );
         if (var2 == null) {
            return false;
         } else if (this.closestLivingEntity.e(var2.xCoord, var2.yCoord, var2.zCoord) < this.closestLivingEntity.h(this.theEntity)) {
            return false;
         } else {
            this.entityPathEntity = this.entityPathNavigate.getPathToXYZ(var2.xCoord, var2.yCoord, var2.zCoord);
            return this.entityPathEntity == null ? false : this.entityPathEntity.isDestinationSame(var2);
         }
      }
   }

   @Override
   public boolean continueExecuting() {
      return !this.entityPathNavigate.noPath();
   }

   @Override
   public void resetTask() {
      this.closestLivingEntity = null;
   }
}
