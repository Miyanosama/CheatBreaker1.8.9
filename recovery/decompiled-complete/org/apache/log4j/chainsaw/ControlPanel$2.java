package org.apache.log4j.chainsaw;

import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import net.minecraft.block.state.pattern.BlockPattern$PatternHelper;
import net.optifine.shaders.uniform.UniformType;
import org.newsclub.net.unix.AFUNIXSocketImpl$AFUNIXOutputStream;

public class ControlPanel$2 implements DocumentListener {
   public BlockPattern$PatternHelper field_0003;
   public ControlPanel this$0;
   public JTextField val$threadField;
   public UniformType field_0004;
   public MyTableModel val$aModel;
   public AFUNIXSocketImpl$AFUNIXOutputStream field_0001;

   public void changedUpdate(DocumentEvent var1) {
      this.val$aModel.setThreadFilter(this.val$threadField.getText());
   }

   public void removeUpdate(DocumentEvent var1) {
      this.val$aModel.setThreadFilter(this.val$threadField.getText());
   }

   public void insertUpdate(DocumentEvent var1) {
      this.val$aModel.setThreadFilter(this.val$threadField.getText());
   }

   public ControlPanel$2(ControlPanel var1, MyTableModel var2, JTextField var3) {
      this.this$0 = var1;
      this.val$aModel = var2;
      this.val$threadField = var3;
      super();
   }
}
