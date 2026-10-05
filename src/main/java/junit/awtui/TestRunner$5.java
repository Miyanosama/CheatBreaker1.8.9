package junit.awtui;

import java.awt.event.TextEvent;
import java.awt.event.TextListener;
import junit.awtui.TestRunner;

public class TestRunner$5 implements TextListener {
   public TestRunner recoveredField159;

   public void textValueChanged(TextEvent var1) {
      this.recoveredField159.fRun.setEnabled(this.recoveredField159.fSuiteField.getText().length() > 0);
      this.recoveredField159.fStatusLine.setText("");
   }

   public TestRunner$5(TestRunner var1) {
      this.recoveredField159 = var1;
   }
}
