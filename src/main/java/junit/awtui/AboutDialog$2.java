package junit.awtui;

import junit.awtui.AboutDialog;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AboutDialog$2 extends WindowAdapter {
   public AboutDialog recoveredField1875;

   public void windowClosing(WindowEvent var1) {
      this.recoveredField1875.dispose();
   }

   public AboutDialog$2(AboutDialog var1) {
      this.recoveredField1875 = var1;
   }
}
