package junit.extensions;

import junit.framework.Test;
import junit.framework.TestResult;
import net.minecraft.entity.monster.EntitySpider$GroupData;
import net.minecraft.util.BlockPos$1$1;

public class RepeatedTest extends TestDecorator {
   public int fTimesRepeat;
   public BlockPos$1$1 field_0002;
   public EntitySpider$GroupData field_0000;

   public int j_() {
      return super.j_() * this.fTimesRepeat;
   }

   public String toString() {
      return super.toString() + "(repeated)";
   }

   public void run(TestResult var1) {
      for (int var2 = 0; var2 < this.fTimesRepeat && !var1.shouldStop(); var2++) {
         super.run(var1);
      }
   }

   public RepeatedTest(Test var1, int var2) {
      super(var1);
      if (var2 < 0) {
         throw new IllegalArgumentException("Repetition count must be > 0");
      } else {
         this.fTimesRepeat = var2;
      }
   }
}
