package junit.extensions;

import javazoom.jl.decoder.JavaLayerException;
import junit.framework.TestCase;
import net.minecraft.enchantment.EnchantmentDamage;
import net.minecraft.world.gen.feature.WorldGenBigTree;

public class ExceptionTestCase extends TestCase {
   public JavaLayerException field_0001;
   public Class fExpected;
   public WorldGenBigTree field_0000;
   public EnchantmentDamage field_0002;

   public ExceptionTestCase(String var1, Class var2) {
      super(var1);
      this.fExpected = var2;
   }

   public void runTest() {
      try {
         super.runTest();
      } catch (Exception var2) {
         if (this.fExpected.isAssignableFrom(var2.getClass())) {
            return;
         }

         throw var2;
      }

      fail("Expected exception " + this.fExpected);
   }
}
