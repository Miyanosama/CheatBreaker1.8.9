package net.minecraft.entity.monster;

import com.google.common.base.Predicate;
import io.netty.util.concurrent.DefaultPromise$LateListeners;
import junit.swingui.TestRunner$18;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.EntityAILookIdle;

public class IMob$2 implements Predicate<Entity> {
   public TestRunner$18 field_0001;
   public EntityAILookIdle field_0002;
   public DefaultPromise$LateListeners field_0000;

   public boolean apply(Entity var1) {
      return var1 instanceof IMob && !var1.isInvisible();
   }
}
