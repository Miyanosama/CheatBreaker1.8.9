package org.apache.log4j.chainsaw;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

public class ControlPanel extends JPanel {
   public static Logger LOG = Logger.getLogger(
      ControlPanel.class$org$apache$log4j$chainsaw$ControlPanel == null
         ? (ControlPanel.class$org$apache$log4j$chainsaw$ControlPanel = class$("org.apache.log4j.chainsaw.ControlPanel"))
         : ControlPanel.class$org$apache$log4j$chainsaw$ControlPanel
   );
   public static Class class$org$apache$log4j$chainsaw$ControlPanel;

   // $VF: synthetic method
   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw (NoClassDefFoundError)new NoClassDefFoundError().initCause(var2);
      }
   }

   public ControlPanel(MyTableModel var1) {
      this.setBorder(BorderFactory.createTitledBorder("Controls: "));
      GridBagLayout var2 = new GridBagLayout();
      GridBagConstraints var3 = new GridBagConstraints();
      this.setLayout(var2);
      var3.ipadx = 5;
      var3.ipady = 5;
      var3.gridx = 0;
      var3.anchor = 13;
      var3.gridy = 0;
      JLabel var4 = new JLabel("Filter Level:");
      var2.setConstraints(var4, var3);
      this.add(var4);
      var3.gridy++;
      var4 = new JLabel("Filter Thread:");
      var2.setConstraints(var4, var3);
      this.add(var4);
      var3.gridy++;
      var4 = new JLabel("Filter Logger:");
      var2.setConstraints(var4, var3);
      this.add(var4);
      var3.gridy++;
      var4 = new JLabel("Filter NDC:");
      var2.setConstraints(var4, var3);
      this.add(var4);
      var3.gridy++;
      var4 = new JLabel("Filter Message:");
      var2.setConstraints(var4, var3);
      this.add(var4);
      var3.weightx = 1.0;
      var3.gridx = 1;
      var3.anchor = 17;
      var3.gridy = 0;
      Level[] var5 = new Level[]{Level.FATAL, Level.ERROR, Level.WARN, Level.INFO, Level.DEBUG, Level.TRACE};
      JComboBox var6 = new JComboBox<>(var5);
      Level var7 = var5[var5.length - 1];
      var6.setSelectedItem(var7);
      var1.setPriorityFilter(var7);
      var2.setConstraints(var6, var3);
      this.add(var6);
      var6.setEditable(false);
      var6.addActionListener(new ControlPanel$1(this, var1, var6));
      var3.fill = 2;
      var3.gridy++;
      JTextField var8 = new JTextField("");
      var8.getDocument().addDocumentListener(new ControlPanel$2(this, var1, var8));
      var2.setConstraints(var8, var3);
      this.add(var8);
      var3.gridy++;
      JTextField var9 = new JTextField("");
      var9.getDocument().addDocumentListener(new ControlPanel$3(this, var1, var9));
      var2.setConstraints(var9, var3);
      this.add(var9);
      var3.gridy++;
      JTextField var10 = new JTextField("");
      var10.getDocument().addDocumentListener(new ControlPanel$4(this, var1, var10));
      var2.setConstraints(var10, var3);
      this.add(var10);
      var3.gridy++;
      JTextField var11 = new JTextField("");
      var11.getDocument().addDocumentListener(new ControlPanel$5(this, var1, var11));
      var2.setConstraints(var11, var3);
      this.add(var11);
      var3.weightx = 0.0;
      var3.fill = 2;
      var3.anchor = 13;
      var3.gridx = 2;
      var3.gridy = 0;
      JButton var12 = new JButton("Exit");
      var12.setMnemonic('x');
      var12.addActionListener(ExitAction.INSTANCE);
      var2.setConstraints(var12, var3);
      this.add(var12);
      var3.gridy++;
      JButton var13 = new JButton("Clear");
      var13.setMnemonic('c');
      var13.addActionListener(new ControlPanel$6(this, var1));
      var2.setConstraints(var13, var3);
      this.add(var13);
      var3.gridy++;
      JButton var14 = new JButton("Pause");
      var14.setMnemonic('p');
      var14.addActionListener(new ControlPanel$7(this, var1, var14));
      var2.setConstraints(var14, var3);
      this.add(var14);
   }
}
