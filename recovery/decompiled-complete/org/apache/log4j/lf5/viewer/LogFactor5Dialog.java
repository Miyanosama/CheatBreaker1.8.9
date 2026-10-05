package org.apache.log4j.lf5.viewer;

import io.netty.handler.codec.marshalling.LimitingByteInput$TooBigObjectException;
import io.netty.handler.codec.socks.SocksAddressType;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Toolkit;
import java.awt.Window;
import javax.swing.JDialog;
import javax.swing.JFrame;
import net.optifine.entity.model.ModelAdapterWitch;

public abstract class LogFactor5Dialog extends JDialog {
   public SocksAddressType field_0001;
   public ModelAdapterWitch field_0003;
   public LimitingByteInput$TooBigObjectException field_0000;
   public static Font DISPLAY_FONT = new Font("Arial", 1, 12);

   public void minimumSizeDialog(Component var1, int var2, int var3) {
      if (var1.getSize().width < var2) {
         var1.setSize(var2, var1.getSize().height);
      }

      if (var1.getSize().height < var3) {
         var1.setSize(var1.getSize().width, var3);
      }
   }

   public LogFactor5Dialog(JFrame var1, String var2, boolean var3) {
      super(var1, var2, var3);
   }

   public void show() {
      this.pack();
      this.minimumSizeDialog(this, 200, 100);
      this.centerWindow(this);
      super.show();
   }

   public GridBagConstraints getDefaultConstraints() {
      GridBagConstraints var1 = new GridBagConstraints();
      var1.weightx = 1.0;
      var1.weighty = 1.0;
      var1.gridheight = 1;
      var1.insets = new Insets(4, 4, 4, 4);
      var1.fill = 0;
      var1.anchor = 17;
      return var1;
   }

   public void centerWindow(Window var1) {
      Dimension var2 = Toolkit.getDefaultToolkit().getScreenSize();
      if (var2.width < var1.getSize().width) {
         var1.setSize(var2.width, var1.getSize().height);
      }

      if (var2.height < var1.getSize().height) {
         var1.setSize(var1.getSize().width, var2.height);
      }

      int var3 = (var2.width - var1.getSize().width) / 2;
      int var4 = (var2.height - var1.getSize().height) / 2;
      var1.setLocation(var3, var4);
   }

   public void wrapStringOnPanel(String var1, Container var2) {
      GridBagConstraints var3 = this.getDefaultConstraints();
      var3.gridwidth = 0;
      var3.insets = new Insets(0, 0, 0, 0);
      GridBagLayout var4 = (GridBagLayout)var2.getLayout();

      while (var1.length() > 0) {
         int var5 = var1.indexOf(10);
         String var6;
         if (var5 >= 0) {
            var6 = var1.substring(0, var5);
            var1 = var1.substring(var5 + 1);
         } else {
            var6 = var1;
            var1 = "";
         }

         Label var7 = new Label(var6);
         var7.setFont(DISPLAY_FONT);
         var4.setConstraints(var7, var3);
         var2.add(var7);
      }
   }
}
