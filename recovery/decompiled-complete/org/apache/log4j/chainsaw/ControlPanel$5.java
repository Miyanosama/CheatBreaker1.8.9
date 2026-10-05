package org.apache.log4j.chainsaw;

import io.netty.handler.ssl.SslHandler$4;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javazoom.jl.decoder.BitstreamException;
import recovered.unidentified.UnidentifiedClass0212;

public class ControlPanel$5 implements DocumentListener {
   public ControlPanel this$0;
   public SslHandler$4 field_0005;
   public JTextField val$msgField;
   public BitstreamException field_0004;
   public UnidentifiedClass0212 field_0000;
   public MyTableModel val$aModel;

   public void insertUpdate(DocumentEvent var1) {
      this.val$aModel.setMessageFilter(this.val$msgField.getText());
   }

   public ControlPanel$5(ControlPanel var1, MyTableModel var2, JTextField var3) {
      this.this$0 = var1;
      this.val$aModel = var2;
      this.val$msgField = var3;
      super();
   }

   public void removeUpdate(DocumentEvent var1) {
      this.val$aModel.setMessageFilter(this.val$msgField.getText());
   }

   public void changedUpdate(DocumentEvent var1) {
      this.val$aModel.setMessageFilter(this.val$msgField.getText());
   }
}
