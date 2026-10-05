package junit.swingui;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FailureRunView$1 implements ListSelectionListener {
   public FailureRunView recoveredField665;

   public void valueChanged(ListSelectionEvent var1) {
      this.recoveredField665.testSelected();
   }

   public FailureRunView$1(FailureRunView var1) {
      this.recoveredField665 = var1;
   }
}
