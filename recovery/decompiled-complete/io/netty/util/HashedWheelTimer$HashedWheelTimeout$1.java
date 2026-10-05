package io.netty.util;

import net.minecraft.client.gui.GuiControls;
import net.minecraft.world.gen.structure.StructureStrongholdPieces$PieceWeight;

public class HashedWheelTimer$HashedWheelTimeout$1 implements Runnable {
   public StructureStrongholdPieces$PieceWeight __junk4844285083797055360;
   public GuiControls __junk2849379877891275485;

   public HashedWheelTimer$HashedWheelTimeout$1(HashedWheelTimer$HashedWheelTimeout var1) {
      this.this$0 = var1;
      super();
   }

   @Override
   public void run() {
      HashedWheelTimer$HashedWheelBucket var1 = this.this$0.bucket;
      if (var1 != null) {
         var1.remove(this.this$0);
      }
   }
}
