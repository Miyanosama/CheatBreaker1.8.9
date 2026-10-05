package io.netty.util;

import io.netty.util.internal.StringUtil;
import net.minecraft.world.gen.structure.StructureOceanMonumentPieces$DoubleYZRoom;
import recovered.unidentified.UnidentifiedClass3246;
import recovered.unidentified.UnidentifiedClass4579;

public class ReferenceCountUtil$ReleasingTask implements Runnable {
   public int decrement;
   public UnidentifiedClass3246 __junk7469813349127246851;
   public ReferenceCounted obj;
   public UnidentifiedClass4579 __junk7692432136695438276;
   public StructureOceanMonumentPieces$DoubleYZRoom __junk6299579352442956860;

   public ReferenceCountUtil$ReleasingTask(ReferenceCounted var1, int var2) {
      this.obj = var1;
      this.decrement = var2;
   }

   @Override
   public void run() {
      try {
         if (!this.obj.release(this.decrement)) {
            ReferenceCountUtil.access$000().warn("Non-zero refCnt: {}", this);
         } else {
            ReferenceCountUtil.access$000().debug("Released: {}", this);
         }
      } catch (Exception var2) {
         ReferenceCountUtil.access$000().warn("Failed to release an object: {}", this.obj, var2);
      }
   }

   @Override
   public String toString() {
      return StringUtil.simpleClassName(this.obj) + ".release(" + this.decrement + ") refCnt: " + this.obj.refCnt();
   }
}
