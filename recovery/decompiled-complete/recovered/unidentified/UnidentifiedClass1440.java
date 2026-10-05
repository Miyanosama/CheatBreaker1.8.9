package recovered.unidentified;

import io.netty.handler.codec.http.CookieHeaderNames;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import junit.awtui.Logo;
import net.minecraft.client.renderer.block.model.ModelBlock;
import net.minecraft.init.Bootstrap$11;
import net.optifine.gui.TooltipManager;

public class UnidentifiedClass1440 extends Dialog {
   public Bootstrap$11 field_0001;
   public ModelBlock field_0003;
   public TooltipManager field_0000;
   public CookieHeaderNames field_0002;

   public UnidentifiedClass1440(Frame var1) {
      super(var1);
      this.setResizable(false);
      this.setLayout(new GridBagLayout());
      this.setSize(330, 138);
      this.setTitle("About");
      Button var2 = new Button("Close");
      var2.addActionListener(new UnidentifiedClass1095(this));
      Label var3 = new Label("JUnit");
      var3.setFont(new Font("dialog", 0, 36));
      Label var4 = new Label("JUnit " + UnidentifiedClass4928.method_29434() + " by Kent Beck and Erich Gamma");
      var4.setFont(new Font("dialog", 0, 14));
      Logo var5 = new Logo();
      GridBagConstraints var6 = new GridBagConstraints();
      var6.gridx = 3;
      var6.gridy = 0;
      var6.gridwidth = 1;
      var6.gridheight = 1;
      var6.anchor = 10;
      this.add(var3, var6);
      GridBagConstraints var7 = new GridBagConstraints();
      var7.gridx = 2;
      var7.gridy = 1;
      var7.gridwidth = 2;
      var7.gridheight = 1;
      var7.anchor = 10;
      this.add(var4, var7);
      GridBagConstraints var8 = new GridBagConstraints();
      var8.gridx = 2;
      var8.gridy = 2;
      var8.gridwidth = 2;
      var8.gridheight = 1;
      var8.anchor = 10;
      var8.insets = new Insets(8, 0, 8, 0);
      this.add(var2, var8);
      GridBagConstraints var9 = new GridBagConstraints();
      var9.gridx = 2;
      var9.gridy = 0;
      var9.gridwidth = 1;
      var9.gridheight = 1;
      var9.anchor = 10;
      this.add(var5, var9);
      this.addWindowListener(new UnidentifiedClass3389(this));
   }
}
