package net.minecraft.client.particle;

import net.minecraft.entity.ai.EntityAIPlay;
import net.minecraft.world.World;
import org.apache.log4j.pattern.LoggingEventPatternConverter;
import recovered.unidentified.UnidentifiedClass4723;

public class EntityHugeExplodeFX$Factory implements IParticleFactory {
   public LoggingEventPatternConverter field_0001;
   public UnidentifiedClass4723 field_0002;
   public EntityAIPlay field_0000;

   @Override
   public EntityFX getEntityFX(int var1, World var2, double var3, double var5, double var7, double var9, double var11, double var13, int... var15) {
      return new EntityHugeExplodeFX(var2, var3, var5, var7, var9, var11, var13);
   }
}
