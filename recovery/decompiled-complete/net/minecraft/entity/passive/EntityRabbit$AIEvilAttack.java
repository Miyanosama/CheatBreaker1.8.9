package net.minecraft.entity.passive;

import io.netty.handler.codec.http.ComposedLastHttpContent;
import net.minecraft.client.model.ModelSheep2;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.world.storage.WorldInfo$2;
import org.apache.log4j.chainsaw.ExitAction;

public class EntityRabbit$AIEvilAttack extends EntityAIAttackOnCollide {
   public ExitAction field_0002;
   public EntityLiving field_0003;
   public WorldInfo$2 field_0004;
   public ModelSheep2 field_0000;
   public ComposedLastHttpContent field_0001;

   @Override
   public double func_179512_a(EntityLivingBase var1) {
      return 4.0F + var1.J;
   }

   public EntityRabbit$AIEvilAttack(EntityRabbit var1) {
      super(var1, EntityLivingBase.class, 1.4, true);
   }
}
