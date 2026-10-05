package net.minecraft.util;

import com.google.common.base.Function;
import io.netty.util.concurrent.AbstractEventExecutor$1;
import net.minecraft.client.particle.EntityFootStepFX$Factory;

public class ChatComponentStyle$2 implements Function<IChatComponent, IChatComponent> {
   public EntityFootStepFX$Factory field_0000;
   public AbstractEventExecutor$1 field_0001;

   public IChatComponent apply(IChatComponent var1) {
      IChatComponent var2 = var1.createCopy();
      var2.setChatStyle(var2.getChatStyle().createDeepCopy());
      return var2;
   }
}
