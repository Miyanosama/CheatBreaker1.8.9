package net.minecraft.entity.ai;

import com.google.common.base.Predicate;
import io.netty.channel.group.DefaultChannelGroupFuture$1;
import net.minecraft.entity.EntityLivingBase;
import org.apache.log4j.net.SocketAppender$Connector;

public class EntityAIFindEntityNearest$1 implements Predicate<EntityLivingBase> {
   public DefaultChannelGroupFuture$1 field_0002;
   public SocketAppender$Connector field_0000;

   public boolean apply(EntityLivingBase var1) {
      double var2 = this.field_179877_a.getFollowRange();
      if (var1.isSneaking()) {
         var2 *= 0.8F;
      }

      return var1.isInvisible()
         ? false
         : (
            var1.g(EntityAIFindEntityNearest.access$000(this.field_179877_a)) > var2
               ? false
               : EntityAITarget.isSuitableTarget(EntityAIFindEntityNearest.access$000(this.field_179877_a), var1, false, true)
         );
   }

   public EntityAIFindEntityNearest$1(EntityAIFindEntityNearest var1) {
      this.field_179877_a = var1;
      super();
   }
}
