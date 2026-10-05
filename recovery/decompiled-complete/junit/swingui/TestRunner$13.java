package junit.swingui;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import net.minecraft.client.renderer.entity.RenderHorse;
import net.minecraft.entity.monster.EntityGolem;
import org.slf4j.helpers.BasicMarker;

public class TestRunner$13 implements ChangeListener {
   public TestRunner this$0;
   public EntityGolem field_0003;
   public BasicMarker field_0000;
   public RenderHorse field_0002;

   public TestRunner$13(TestRunner var1) {
      this.this$0 = var1;
   }

   public void stateChanged(ChangeEvent var1) {
      this.this$0.testViewChanged();
   }
}
