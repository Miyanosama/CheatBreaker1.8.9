package net.minecraft.entity.monster;

import io.netty.channel.DefaultMessageSizeEstimator;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.player.EntityPlayer;
import org.apache.log4j.pattern.LevelPatternConverter;

public class EntityPigZombie$AITargetAggressor extends EntityAINearestAttackableTarget<EntityPlayer> {
   public LevelPatternConverter field_0001;
   public DefaultMessageSizeEstimator field_0000;

   @Override
   public boolean shouldExecute() {
      return ((EntityPigZombie)this.e).isAngry() && super.shouldExecute();
   }

   public EntityPigZombie$AITargetAggressor(EntityPigZombie var1) {
      super(var1, EntityPlayer.class, true);
   }
}
