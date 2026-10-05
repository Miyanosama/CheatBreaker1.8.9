package junit.swingui;

import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CounterPanel extends JPanel {
   public JTextField fNumberOfFailures;
   public JTextField fNumberOfErrors;
   public int fTotal;
   public Icon fErrorIcon;
   public JTextField fNumberOfRuns;
   public Icon fFailureIcon = TestRunner.getIconResource(this.getClass(), "icons/failure.gif");

   public void addToGrid(Component var1, int var2, int var3, int var4, int var5, double var6, double var8, int var10, int var11, Insets var12) {
      GridBagConstraints var13 = new GridBagConstraints();
      var13.gridx = var2;
      var13.gridy = var3;
      var13.gridwidth = var4;
      var13.gridheight = var5;
      var13.weightx = var6;
      var13.weighty = var8;
      var13.anchor = var10;
      var13.fill = var11;
      var13.insets = var12;
      this.add(var1, var13);
   }

   public void setRunValue(int var1) {
      this.fNumberOfRuns.setText(Integer.toString(var1) + "/" + this.fTotal);
   }

   public JTextField createOutputField(int var1) {
      JTextField var2 = new JTextField("0", var1);
      var2.setMinimumSize(var2.getPreferredSize());
      var2.setMaximumSize(var2.getPreferredSize());
      var2.setHorizontalAlignment(2);
      var2.setFont(StatusLine.BOLD_FONT);
      var2.setEditable(false);
      var2.setBorder(BorderFactory.createEmptyBorder());
      return var2;
   }

   public void setLabelValue(JTextField var1, int var2) {
      var1.setText(Integer.toString(var2));
   }

   public void setTotal(int var1) {
      this.fTotal = var1;
   }

   public void setErrorValue(int var1) {
      this.setLabelValue(this.fNumberOfErrors, var1);
   }

   public void setFailureValue(int var1) {
      this.setLabelValue(this.fNumberOfFailures, var1);
   }

   public CounterPanel() {
      super(new GridBagLayout());
      this.fErrorIcon = TestRunner.getIconResource(this.getClass(), "icons/error.gif");
      this.fNumberOfErrors = this.createOutputField(5);
      this.fNumberOfFailures = this.createOutputField(5);
      this.fNumberOfRuns = this.createOutputField(9);
      this.addToGrid(new JLabel("Runs:", 0), 0, 0, 1, 1, 0.0, 0.0, 10, 0, new Insets(0, 0, 0, 0));
      this.addToGrid(this.fNumberOfRuns, 1, 0, 1, 1, 0.33, 0.0, 10, 2, new Insets(0, 8, 0, 0));
      this.addToGrid(new JLabel("Errors:", this.fErrorIcon, 2), 2, 0, 1, 1, 0.0, 0.0, 10, 0, new Insets(0, 8, 0, 0));
      this.addToGrid(this.fNumberOfErrors, 3, 0, 1, 1, 0.33, 0.0, 10, 2, new Insets(0, 8, 0, 0));
      this.addToGrid(new JLabel("Failures:", this.fFailureIcon, 2), 4, 0, 1, 1, 0.0, 0.0, 10, 0, new Insets(0, 8, 0, 0));
      this.addToGrid(this.fNumberOfFailures, 5, 0, 1, 1, 0.33, 0.0, 10, 2, new Insets(0, 8, 0, 0));
   }

   public void reset() {
      this.setLabelValue(this.fNumberOfErrors, 0);
      this.setLabelValue(this.fNumberOfFailures, 0);
      this.setLabelValue(this.fNumberOfRuns, 0);
      this.fTotal = 0;
   }
}
