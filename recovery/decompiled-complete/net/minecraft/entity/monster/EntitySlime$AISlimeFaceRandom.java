package net.minecraft.entity.monster;

import net.minecraft.block.material.MaterialTransparent;
import net.minecraft.client.gui.achievement.GuiStats$StatsBlock;
import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.network.EnumConnectionState$4;

public class EntitySlime$AISlimeFaceRandom extends EntityAIBase {
   public int field_179460_c;
   public EntitySlime slime;
   public GuiStats$StatsBlock field_0002;
   public float field_179459_b;
   public MaterialTransparent field_0000;
   public EnumConnectionState$4 field_0001;

   @Override
   public void updateTask() {
      if (--this.field_179460_c <= 0) {
         this.field_179460_c = 40 + this.slime.getRNG().nextInt(60);
         this.field_179459_b = this.slime.getRNG().nextInt(360);
      }

      ((EntitySlime$SlimeMoveHelper)this.slime.q()).func_179920_a(this.field_179459_b, false);
   }

   public EntitySlime$AISlimeFaceRandom(EntitySlime var1) {
      this.slime = var1;
      this.setMutexBits(2);
   }

   @Override
   public boolean shouldExecute() {
      return this.slime.getAttackTarget() == null && (this.slime.C || this.slime.V() || this.slime.ab());
   }
}
