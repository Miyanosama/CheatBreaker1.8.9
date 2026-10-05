package org.apache.log4j.chainsaw;

import io.netty.buffer.WrappedByteBuf;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import net.minecraft.client.Minecraft$5;
import net.minecraft.item.ItemEgg;
import net.minecraft.network.NetworkManager;
import recovered.unidentified.UnidentifiedClass1509;

public class ControlPanel$7 implements ActionListener {
   public MyTableModel val$aModel;
   public ItemEgg field_0006;
   public NetworkManager field_0002;
   public WrappedByteBuf field_0005;
   public JButton val$toggleButton;
   public Minecraft$5 field_0001;
   public UnidentifiedClass1509 field_0007;
   public ControlPanel this$0;

   public ControlPanel$7(ControlPanel var1, MyTableModel var2, JButton var3) {
      this.this$0 = var1;
      this.val$aModel = var2;
      this.val$toggleButton = var3;
      super();
   }

   public void actionPerformed(ActionEvent var1) {
      this.val$aModel.toggle();
      this.val$toggleButton.setText(this.val$aModel.isPaused() ? "Resume" : "Pause");
   }
}
