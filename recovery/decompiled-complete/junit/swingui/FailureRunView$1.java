package junit.swingui;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import net.minecraft.block.BlockTripWireHook$1;
import net.minecraft.client.gui.GuiPageButtonList$EditBoxEntry;
import net.minecraft.realms.RealmsSimpleScrolledSelectionList;

public class FailureRunView$1 implements ListSelectionListener {
   public GuiPageButtonList$EditBoxEntry field_0001;
   public RealmsSimpleScrolledSelectionList field_0003;
   public FailureRunView field_0000;
   public BlockTripWireHook$1 field_0002;

   public void valueChanged(ListSelectionEvent var1) {
      this.field_0000.testSelected();
   }

   public FailureRunView$1(FailureRunView var1) {
      this.field_0000 = var1;
   }
}
