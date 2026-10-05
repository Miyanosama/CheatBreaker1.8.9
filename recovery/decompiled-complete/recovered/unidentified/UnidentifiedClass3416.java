package recovered.unidentified;

import com.cheatbreaker.client.module.type.EnvironmentModule;
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

public class UnidentifiedClass3416 extends JDialog {
   public EnvironmentModule field_0000;
   public static Class field_0001;

   public JLabel method_21161() {
      Icon var1 = TestRunner.getIconResource(field_0001 == null ? (field_0001 = method_21162("junit.runner.BaseTestRunner")) : field_0001, "logo.gif");
      return new JLabel(var1);
   }

   public static Class method_21162(String var0) {
      try {
         return Class.forName(var0);
      } catch (ClassNotFoundException var2) {
         throw new NoClassDefFoundError(var2.getMessage());
      }
   }

   public UnidentifiedClass3416(JFrame var1) {
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
      var2.addActionListener(new UnidentifiedClass1318(this));
      this.getRootPane().setDefaultButton(var2);
      JLabel var3 = new JLabel("JUnit");
      var3.setFont(new Font("dialog", 0, 36));
      JLabel var4 = new JLabel("JUnit " + UnidentifiedClass4928.method_29434() + " by Kent Beck and Erich Gamma");
      var4.setFont(new Font("dialog", 0, 14));
      JLabel var5 = this.method_21161();
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
      this.addWindowListener(new UnidentifiedClass5128(this));
   }
}
