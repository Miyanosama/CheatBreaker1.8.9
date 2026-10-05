package io.netty.util.internal.chmv8;

import java.util.concurrent.RunnableFuture;
import javax.vecmath.AxisAngle4d;
import net.minecraft.client.renderer.BlockModelRenderer$VertexTranslations;

public class ForkJoinTask$AdaptedRunnableAction extends ForkJoinTask<Void> implements RunnableFuture<Void> {
   public Runnable runnable;
   public AxisAngle4d __junk8081271284235059441;
   public static long serialVersionUID;
   public BlockModelRenderer$VertexTranslations __junk9193275583700070763;

   @Override
   public void run() {
      this.invoke();
   }

   public Void getRawResult() {
      return null;
   }

   public void setRawResult(Void var1) {
   }

   public ForkJoinTask$AdaptedRunnableAction(Runnable var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         this.runnable = var1;
      }
   }

   @Override
   public boolean exec() {
      this.runnable.run();
      return true;
   }
}
