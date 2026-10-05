package net.minecraft.entity.boss;

import com.google.common.base.Predicate;
import io.netty.channel.group.ChannelMatchers$CompositeMatcher;
import io.netty.handler.codec.http.multipart.HttpPostRequestEncoder$ErrorDataEncoderException;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.optifine.entity.model.ModelAdapterQuadruped;

public class EntityWither$1 implements Predicate<Entity> {
   public ModelAdapterQuadruped field_0001;
   public HttpPostRequestEncoder$ErrorDataEncoderException field_0002;
   public ChannelMatchers$CompositeMatcher field_0000;

   public boolean apply(Entity var1) {
      return var1 instanceof EntityLivingBase && ((EntityLivingBase)var1).getCreatureAttribute() != EnumCreatureAttribute.UNDEAD;
   }
}
