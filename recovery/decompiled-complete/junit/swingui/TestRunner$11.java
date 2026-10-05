package junit.swingui;

import com.cheatbreaker.client.ui.element.ProfileElement;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import net.minecraft.entity.EntityHanging;
import net.optifine.reflect.FieldLocatorTypes;

public class TestRunner$11 extends KeyAdapter {
   public ProfileElement field_0001;
   public TestRunner this$0;
   public EntityHanging field_0000;
   public FieldLocatorTypes field_0002;

   public TestRunner$11(TestRunner var1) {
      this.this$0 = var1;
   }

   public void keyTyped(KeyEvent var1) {
      this.this$0.textChanged();
      if (var1.getKeyChar() == '\n') {
         this.this$0.runSuite();
      }
   }
}
