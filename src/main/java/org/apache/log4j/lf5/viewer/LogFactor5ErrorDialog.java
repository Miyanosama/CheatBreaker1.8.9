package org.apache.log4j.lf5.viewer;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LogFactor5ErrorDialog extends LogFactor5Dialog {
   public LogFactor5ErrorDialog(JFrame var1, String var2) {
      super(var1, "Error", true);
      JButton var3 = new JButton("Ok");
      var3.addActionListener(new LogFactor5ErrorDialog$1(this));
      JPanel var4 = new JPanel();
      var4.setLayout(new FlowLayout());
      var4.add(var3);
      JPanel var5 = new JPanel();
      var5.setLayout(new GridBagLayout());
      this.wrapStringOnPanel(var2, var5);
      this.getContentPane().add(var5, "Center");
      this.getContentPane().add(var4, "South");
      this.show();
   }
}
