package net.minecraft.entity.ai;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import io.netty.util.concurrent.DefaultPromise$CauseHolder;
import java.util.Collections;
import java.util.List;
import net.minecraft.client.gui.GuiScreenWorking;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.EntitySelectors;
import net.optifine.render.AabbFrame;
import recovered.unidentified.UnidentifiedClass1294;

public class EntityAINearestAttackableTarget<T extends EntityLivingBase> extends EntityAITarget {
   public EntityAINearestAttackableTarget$Sorter b;
   public EntityLivingBase d;
   public GuiScreenWorking field_0003;
   public DefaultPromise$CauseHolder field_0006;
   public int targetChance;
   public UnidentifiedClass1294 field_0001;
   public AabbFrame field_0008;
   public Predicate<? super T> c;
   public Class<T> targetClass;

   public EntityAINearestAttackableTarget(EntityCreature var1, Class<T> var2, boolean var3, boolean var4) {
      this(var1, var2, 10, var3, var4, (Predicate<? super T>)null);
   }

   @Override
   public void startExecuting() {
      this.e.setAttackTarget(this.d);
      super.startExecuting();
   }

   public EntityAINearestAttackableTarget(EntityCreature var1, Class<T> var2, int var3, boolean var4, boolean var5, Predicate<? super T> var6) {
      super(var1, var4, var5);
      this.targetClass = var2;
      this.targetChance = var3;
      this.b = new EntityAINearestAttackableTarget$Sorter(var1);
      this.setMutexBits(1);
      this.c = new EntityAINearestAttackableTarget$1(this, var6);
   }

   public EntityAINearestAttackableTarget(EntityCreature var1, Class<T> var2, boolean var3) {
      this(var1, var2, var3, false);
   }

   @Override
   public boolean shouldExecute() {
      if (this.targetChance > 0 && this.e.getRNG().nextInt(this.targetChance) != 0) {
         return false;
      } else {
         double var1 = this.f();
         List var3 = this.e
            .o
            .getEntitiesWithinAABB(
               this.targetClass, this.e.getEntityBoundingBox().expand(var1, 4.0, var1), Predicates.and(this.c, EntitySelectors.NOT_SPECTATING)
            );
         Collections.sort(var3, this.b);
         if (var3.isEmpty()) {
            return false;
         } else {
            this.d = (EntityLivingBase)var3.get(0);
            return true;
         }
      }
   }
}
