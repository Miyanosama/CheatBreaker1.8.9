package net.minecraft.entity.monster;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.network.NetworkSystem$4;
import net.minecraft.network.play.server.S3FPacketCustomPayload;
import org.java_websocket.framing.PongFrame;

public class EntityPigZombie$AIHurtByAggressor extends EntityAIHurtByTarget {
   public NetworkSystem$4 field_0000;
   public PongFrame field_0002;
   public S3FPacketCustomPayload field_0001;

   public EntityPigZombie$AIHurtByAggressor(EntityPigZombie var1) {
      super(var1, true);
   }

   @Override
   public void setEntityAttackTarget(EntityCreature var1, EntityLivingBase var2) {
      super.setEntityAttackTarget(var1, var2);
      if (var1 instanceof EntityPigZombie) {
         EntityPigZombie.access$000((EntityPigZombie)var1, var2);
      }
   }
}
