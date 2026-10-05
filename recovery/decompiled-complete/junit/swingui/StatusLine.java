package junit.swingui;

import io.netty.util.internal.chmv8.ConcurrentHashMapV8$ValueIterator;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JTextField;
import net.minecraft.network.play.client.C03PacketPlayer;
import net.minecraft.network.play.server.S27PacketExplosion;

public class StatusLine extends JTextField {
   public S27PacketExplosion field_0002;
   public static Font PLAIN_FONT = new Font("dialog", 0, 12);
   public ConcurrentHashMapV8$ValueIterator field_0001;
   public C03PacketPlayer field_0003;
   public static Font BOLD_FONT = new Font("dialog", 1, 12);

   public StatusLine(int var1) {
      this.setFont(BOLD_FONT);
      this.setEditable(false);
      this.setBorder(BorderFactory.createBevelBorder(1));
      Dimension var2 = this.getPreferredSize();
      var2.width = var1;
      this.setPreferredSize(var2);
   }

   public void method_26433(String var1) {
      this.setFont(BOLD_FONT);
      this.setForeground(Color.red);
      this.setText(var1);
      this.setToolTipText(var1);
   }

   public void clear() {
      this.setText("");
      this.setToolTipText(null);
   }

   public void method_26434(String var1) {
      this.setFont(PLAIN_FONT);
      this.setForeground(Color.black);
      this.setText(var1);
   }
}
