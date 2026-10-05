package junit.swingui;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.minecraft.item.ItemHangingEntity;
import net.minecraft.scoreboard.GoalColor;
import net.optifine.config.ConnectedParser$1;
import org.apache.log4j.lf5.viewer.categoryexplorer.CategoryNodeEditor$4;

public class TestSelector$3 implements ListSelectionListener {
   public ConnectedParser$1 field_0002;
   public GoalColor field_0004;
   public TestSelector field_0001;
   public ItemHangingEntity field_0003;
   public CategoryNodeEditor$4 field_0000;

   public void valueChanged(ListSelectionEvent var1) {
      this.field_0001.checkEnableOK(var1);
   }

   public TestSelector$3(TestSelector var1) {
      this.field_0001 = var1;
   }
}
