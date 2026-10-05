package junit.extensions;

import junit.framework.Test;
import junit.framework.TestResult;
import net.minecraft.client.gui.inventory.GuiContainerCreative$ContainerCreative;
import net.minecraft.util.ClassInheritanceMultiMap;

public class TestSetup extends TestDecorator {
   public ClassInheritanceMultiMap field_0000;
   public GuiContainerCreative$ContainerCreative field_0001;

   public void setUp() {
   }

   public TestSetup(Test var1) {
      super(var1);
   }

   public void run(TestResult var1) {
      TestSetup$1 var2 = new TestSetup$1(this, var1);
      var1.runProtected(this, var2);
   }

   public void tearDown() {
   }
}
