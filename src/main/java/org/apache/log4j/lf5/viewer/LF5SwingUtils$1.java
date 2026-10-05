package org.apache.log4j.lf5.viewer;

import javax.swing.JComponent;

public class LF5SwingUtils$1 implements Runnable {
   // $VF: synthetic field
   public JComponent val$component;

   public LF5SwingUtils$1(JComponent var1) {
      this.val$component = var1;
   }

   public void run() {
      this.val$component.repaint();
   }
}
