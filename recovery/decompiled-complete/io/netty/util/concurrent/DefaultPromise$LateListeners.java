package io.netty.util.concurrent;

import io.netty.util.internal.MpscLinkedQueue;
import java.util.ArrayDeque;
import net.minecraft.world.gen.structure.StructureNetherBridgePieces$Stairs;
import net.optifine.entity.model.ModelAdapterBanner;
import org.apache.log4j.lf5.viewer.LogBrokerMonitor$16;
import recovered.unidentified.UnidentifiedClass4372;

public class DefaultPromise$LateListeners extends ArrayDeque<GenericFutureListener<?>> implements Runnable {
   public LogBrokerMonitor$16 __junk5194015975539325919;
   public MpscLinkedQueue __junk7392266471002352947;
   public ModelAdapterBanner __junk6976374432396218045;
   public StructureNetherBridgePieces$Stairs __junk27230772916287104;
   public UnidentifiedClass4372 __junk465692076177600671;
   public static long serialVersionUID;

   @Override
   public void run() {
      if (DefaultPromise.access$100(this.this$0) == null) {
         while (true) {
            GenericFutureListener var1 = this.poll();
            if (var1 == null) {
               break;
            }

            DefaultPromise.notifyListener0(this.this$0, var1);
         }
      } else {
         DefaultPromise.access$400(this.this$0.executor(), this);
      }
   }

   public DefaultPromise$LateListeners(DefaultPromise var1) {
      this.this$0 = var1;
      super(2);
   }
}
