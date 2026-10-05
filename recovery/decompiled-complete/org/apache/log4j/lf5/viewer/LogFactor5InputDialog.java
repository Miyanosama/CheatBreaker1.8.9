package org.apache.log4j.lf5.viewer;

import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler$1;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LogFactor5InputDialog extends LogFactor5Dialog {
   public WebSocketServerProtocolHandler$1 field_0000;
   public JTextField _textField;
   public static int field_0002;

   public LogFactor5InputDialog(JFrame var1, String var2, String var3, int var4) {
      super(var1, var2, true);
      JPanel var5 = new JPanel();
      var5.setLayout(new FlowLayout());
      JPanel var6 = new JPanel();
      var6.setLayout(new FlowLayout());
      var6.add(new JLabel(var3));
      this._textField = new JTextField(var4);
      var6.add(this._textField);
      this.addKeyListener(new LogFactor5InputDialog$1(this));
      JButton var7 = new JButton("Ok");
      var7.addActionListener(new LogFactor5InputDialog$2(this));
      JButton var8 = new JButton("Cancel");
      var8.addActionListener(new LogFactor5InputDialog$3(this));
      var5.add(var7);
      var5.add(var8);
      this.getContentPane().add(var6, "Center");
      this.getContentPane().add(var5, "South");
      this.pack();
      this.centerWindow(this);
      this.show();
   }

   public static JTextField access$000(LogFactor5InputDialog var0) {
      return var0._textField;
   }

   public String getText() {
      String var1 = this._textField.getText();
      return var1 != null && var1.trim().length() == 0 ? null : var1;
   }

   public LogFactor5InputDialog(JFrame var1, String var2, String var3) {
      this(var1, var2, var3, 30);
   }
}
