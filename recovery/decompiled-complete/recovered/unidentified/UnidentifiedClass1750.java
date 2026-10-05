package recovered.unidentified;

import junit.swingui.TestRunner;
import net.minecraft.block.BlockRedstoneComparator;
import net.minecraft.enchantment.EnchantmentOxygen;

public class UnidentifiedClass1750 implements Runnable {
   public UnidentifiedClass0890 field_0002;
   public BlockRedstoneComparator field_0004;
   public EnchantmentOxygen field_0001;
   public String field_0003;
   public TestRunner field_0000;

   public UnidentifiedClass1750(TestRunner var1, String var2) {
      this.field_0000 = var1;
      this.field_0003 = var2;
   }

   public void run() {
      TestRunner.method_00094(this.field_0000, this.field_0003);
   }
}
