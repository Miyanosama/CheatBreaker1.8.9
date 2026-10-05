package org.apache.log4j.lf5.viewer;

import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import net.minecraft.network.PacketBuffer;

public class LogFactor5LoadingDialog extends LogFactor5Dialog {
   public PacketBuffer field_0000;

   public LogFactor5LoadingDialog(JFrame var1, String var2) {
      super(var1, "LogFactor5", false);
      JPanel var3 = new JPanel();
      var3.setLayout(new FlowLayout());
      JPanel var4 = new JPanel();
      var4.setLayout(new GridBagLayout());
      this.wrapStringOnPanel(var2, var4);
      this.getContentPane().add(var4, "Center");
      this.getContentPane().add(var3, "South");
      this.show();
   }
}
