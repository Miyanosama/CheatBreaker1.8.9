package net.optifine;

import com.google.common.collect.AbstractIterator;
import net.minecraft.client.model.ModelSquid;
import recovered.unidentified.UnidentifiedClass0717;

public class BlockPosM$1$1 extends AbstractIterator {
   public BlockPosM theBlockPosM;
   public ModelSquid field_0000;
   public UnidentifiedClass0717 field_0002;

   public BlockPosM$1$1(BlockPosM$1 var1) {
      this.this$0 = var1;
      super();
      this.theBlockPosM = null;
   }

   public BlockPosM computeNext0() {
      if (this.theBlockPosM == null) {
         this.theBlockPosM = new BlockPosM(this.this$0.val$posFrom.getX(), this.this$0.val$posFrom.getY(), this.this$0.val$posFrom.getZ(), 3);
         return this.theBlockPosM;
      } else if (this.theBlockPosM.equals(this.this$0.val$posTo)) {
         return (BlockPosM)this.endOfData();
      } else {
         int var1 = this.theBlockPosM.getX();
         int var2 = this.theBlockPosM.getY();
         int var3 = this.theBlockPosM.getZ();
         if (var1 < this.this$0.val$posTo.getX()) {
            var1++;
         } else if (var2 < this.this$0.val$posTo.getY()) {
            var1 = this.this$0.val$posFrom.getX();
            var2++;
         } else if (var3 < this.this$0.val$posTo.getZ()) {
            var1 = this.this$0.val$posFrom.getX();
            var2 = this.this$0.val$posFrom.getY();
            var3++;
         }

         this.theBlockPosM.setXyz(var1, var2, var3);
         return this.theBlockPosM;
      }
   }

   public Object computeNext() {
      return this.computeNext0();
   }
}
