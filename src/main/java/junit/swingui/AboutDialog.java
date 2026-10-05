package junit.swingui;

import junit.swingui.AboutDialog$1;
import junit.runner.Version;
import junit.swingui.AboutDialog$2;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import junit.swingui.TestRunner;
import junit.swingui.TestSelector;

public class AboutDialog extends JDialog {
   public static Class recoveredField840;

   public JLabel createLogo() {
      Icon var1 = TestRunner.getIconResource(
         recoveredField840 == null ? (recoveredField840 = class$("junit.runner.BaseTestRunner")) : recoveredField840, "logo.gif"
      );
      return new JLabel(var1);
   }

   public static Class class$(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError(var2.getMessage());
      }
   }

   public AboutDialog(JFrame var1) {
      super(var1, true);
      this.setResizable(false);
      this.getContentPane().setLayout(new GridBagLayout());
      this.setSize(330, 138);
      this.setTitle("About");

      try {
         this.setLocationRelativeTo(var1);
      } catch (NoSuchMethodError var10) {
         TestSelector.centerWindow(this);
      }

      JButton var2 = new JButton("Close");
      var2.addActionListener(new AboutDialog$1(this));
      this.getRootPane().setDefaultButton(var2);
      JLabel var3 = new JLabel("JUnit");
      var3.setFont(new Font("dialog", 0, 36));
      JLabel var4 = new JLabel("JUnit " + Version.method_29434() + " by Kent Beck and Erich Gamma");
      var4.setFont(new Font("dialog", 0, 14));
      JLabel var5 = this.createLogo();
      GridBagConstraints var6 = new GridBagConstraints();
      var6.gridx = 3;
      var6.gridy = 0;
      var6.gridwidth = 1;
      var6.gridheight = 1;
      var6.anchor = 10;
      this.getContentPane().add(var3, var6);
      GridBagConstraints var7 = new GridBagConstraints();
      var7.gridx = 2;
      var7.gridy = 1;
      var7.gridwidth = 2;
      var7.gridheight = 1;
      var7.anchor = 10;
      this.getContentPane().add(var4, var7);
      GridBagConstraints var8 = new GridBagConstraints();
      var8.gridx = 2;
      var8.gridy = 2;
      var8.gridwidth = 2;
      var8.gridheight = 1;
      var8.anchor = 10;
      var8.insets = new Insets(8, 0, 8, 0);
      this.getContentPane().add(var2, var8);
      GridBagConstraints var9 = new GridBagConstraints();
      var9.gridx = 2;
      var9.gridy = 0;
      var9.gridwidth = 1;
      var9.gridheight = 1;
      var9.anchor = 10;
      this.getContentPane().add(var5, var9);
      this.addWindowListener(new AboutDialog$2(this));
   }
}
