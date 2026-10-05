package junit.swingui;

import com.cheatbreaker.client.module.type.TabListModule;
import io.netty.util.Signal;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import net.minecraft.client.gui.inventory.GuiFurnace;
import net.minecraft.util.TupleIntJsonSerializable;

public class TestSelector$DoubleClickListener extends MouseAdapter {
   public GuiFurnace field_0002;
   public TupleIntJsonSerializable field_0004;
   public TestSelector field_0001;
   public TabListModule field_0003;
   public Signal field_0000;

   public void mouseClicked(MouseEvent var1) {
      if (var1.getClickCount() == 2) {
         this.field_0001.method_22000();
      }
   }

   public TestSelector$DoubleClickListener(TestSelector var1) {
      this.field_0001 = var1;
   }
}
