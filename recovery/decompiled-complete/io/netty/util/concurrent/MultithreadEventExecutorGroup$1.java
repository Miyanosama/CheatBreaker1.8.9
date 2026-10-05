package io.netty.util.concurrent;

import net.minecraft.client.gui.GuiResourcePackSelected;
import net.minecraft.entity.monster.IMob$1;
import recovered.unidentified.UnidentifiedClass4400;

public class MultithreadEventExecutorGroup$1 implements FutureListener<Object> {
   public GuiResourcePackSelected __junk8845498525172880101;
   public UnidentifiedClass4400 __junk1486535524678918952;
   public IMob$1 __junk8174477943781642012;

   @Override
   public void operationComplete(Future<Object> var1) {
      if (MultithreadEventExecutorGroup.access$200(this.this$0).incrementAndGet() == MultithreadEventExecutorGroup.access$300(this.this$0).length) {
         MultithreadEventExecutorGroup.access$400(this.this$0).setSuccess(null);
      }
   }

   public MultithreadEventExecutorGroup$1(MultithreadEventExecutorGroup var1) {
      this.this$0 = var1;
      super();
   }
}
